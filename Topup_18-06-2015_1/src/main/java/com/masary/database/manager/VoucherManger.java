/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.manager;

import com.masary.common.CONFIG;
import com.masary.database.dto.AvilableVouchersDTO;
import com.masary.database.dto.CategoryDTO;
import com.masary.database.dto.ProviderDTO;
import com.masary.database.dto.SellVoucherResponse;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;

/**
 *
 * @author Melad
 */
public class VoucherManger extends BaseManger {

    private static VoucherManger instance;
    public static Logger logger = MasaryManager.logger;
    private Connection conn;
    private CallableStatement sqlStatement;
    private String callSQL;
    private String operationId;
    private String reternString;
    private int count;
    private String voucher;

    private VoucherManger() {
    }

    public static VoucherManger getInstance() {
        if (instance == null) {
            instance = new VoucherManger();
        }
        return instance;
    }

    void addVouchers(String vouchers, String providerId, String groups, String ownerId) throws Exception {
        logger.info("Add voucher to " + providerId + " groups " + groups + " owner id" + ownerId);
        StringTokenizer st = new StringTokenizer(vouchers, ",;\n\r");
        String voucher;
        String serial;
        int rows = 0, count = 0;
        int amount;
        int sum = 0;
        try {

            while (st.hasMoreTokens()) {

                voucher = st.nextToken();
                count++;
                if (count <= 3) {
                    continue;
                }
                if (voucher.isEmpty()) {
                    continue;
                }
                serial = st.nextToken();
                amount = Integer.parseInt(st.nextToken());
                saveVoucher(voucher, serial, amount, providerId, groups, ownerId);
                sum += amount;
                rows++;

            }
            conn = getConnection();
            conn.commit();
            if (!"1".equals(ownerId)) {
                addBalanceTovoucherUploader(ownerId, providerId, sum);
            }
        } catch (Exception ex) {
            logger.error(ex);
            conn = getConnection();
            conn.rollback();
            throw new Exception(ex.getMessage() + "  <br> Please Check Row Number :" + (rows + 1));
        }
    }

    private int addBalanceTovoucherUploader(String uploaderId, String providerId, int amouont) throws SQLException {
        conn = getConnection();
        sqlStatement = null;
        callSQL = "{call ?:= ADD_BALNCE_TO_Voucher_uploader(" + uploaderId + "," + providerId + "," + amouont + ")}";

        sqlStatement = conn.prepareCall(callSQL);
        sqlStatement.registerOutParameter(1, Types.INTEGER);
        sqlStatement.execute();
        int trans = sqlStatement.getInt(1);
        closeStatement(sqlStatement);
        closeConnection(conn);
        return trans;

    }

    private void saveVoucher(String voucher, String serial, int amount, String providerId, String groups, String ownerId) throws Exception {
        conn = getConnection();
        if (conn.getAutoCommit()) {
            conn.setAutoCommit(false);
        }
        sqlStatement = null;
        int x = 0;

        try {
            callSQL = "{call  INSERT_VAOUCHERS2(" + providerId + ",'" + voucher + "','" + serial + "'," + amount + ",'" + groups + "'," + ownerId + ", ?)}";
//            //System.out.println("insert voucher " + callSQL);
            sqlStatement = conn.prepareCall(callSQL);
            sqlStatement.registerOutParameter(1, Types.INTEGER);
            sqlStatement.execute();
            x = sqlStatement.getInt(1);
            if (x == -1) {
                throw new Exception("Error - this provider does not support this voucher value, please try again.");
            } else if (x == -2) {
                throw new Exception("Error - The Voucher can't be repeated, please try again.");
            }
        } catch (Exception ex) {

            logger.error(ex);
            throw ex;
        } finally {
            closeStatement(sqlStatement);
        }
    }

    private String waitForVoucherOperation(String operationId) {
        callSQL = "{call ? :=  IS_VOUCHER2M_FINISHED(?,?)}";
        count = 0;
        while (count < 200) {
            try {
                if (conn == null) {
                    ////System.out.println("Connection null+++++++++++++++++++++++++++");
                    conn = getConnection();
                }
                sqlStatement = conn.prepareCall(callSQL);
                sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
                sqlStatement.registerOutParameter(3, OracleTypes.VARCHAR);
                sqlStatement.setString(2, operationId);
                sqlStatement.execute();
                if (sqlStatement.getString(1).equals("1")) {
                    return sqlStatement.getString(3);
                }
                Thread.sleep(600);
            } catch (Exception ex) {
                logger.error(ex);
            } finally {
                closeStatement(sqlStatement);
            }
            count++;
        }
        return CONFIG.error;
    }

    private void cancelVoucherOperation(String operationId) throws SQLException {

        callSQL = "{call  CANCEL_VOUCHER2M_OPERATION(" + operationId + ")}";
        sqlStatement = conn.prepareCall(callSQL);
        sqlStatement.execute();
        closeStatement(sqlStatement);
        logger.info("Cancel Voucher operation " + operationId);
    }

    public List<AvilableVouchersDTO> getAvilableVouchersDTO(String userId) {
        Connection conn = getConnection();
        PreparedStatement sqlStatement = null;
        List<AvilableVouchersDTO> avilableVouchersDTOs = new ArrayList();
        try {
            String sql = "select * from  AVILABLE_VOUCHERS_VW where \"id_owner\"= ? order by 1";
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, userId);
            ResultSet rs = sqlStatement.executeQuery();
            while (rs.next()) {
                AvilableVouchersDTO avilableVouchersDTO = new AvilableVouchersDTO();
                avilableVouchersDTO.setProviderId(rs.getString("ID_V_PROVIDERS"));
                avilableVouchersDTO.setProviderName(rs.getString("PROVIDER_NAME"));
                avilableVouchersDTO.setVoucherValues(rs.getString("VOUCHER_VALUE"));
                avilableVouchersDTO.setAvilableVouchers(rs.getString("VOUCHERS_NUMBER"));
                avilableVouchersDTOs.add(avilableVouchersDTO);
            }
            rs.close();
            return avilableVouchersDTOs;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            closeStatement(sqlStatement);
            closeConnection(conn);
        }
        return avilableVouchersDTOs;
    }

    public List<ProviderDTO> getVoucherProviders() {
        conn = getConnection();
        PreparedStatement sqlStatement = null;
        List<ProviderDTO> providerDTOs = new ArrayList<ProviderDTO>();
        try {
            String sql = "select  ID_V_PROVIDERS,STR_NAME from  V_PROVIDERS";
            sqlStatement = conn.prepareStatement(sql);
            ResultSet rs = sqlStatement.executeQuery();
            ProviderDTO provider = null;
            while (rs.next()) {
                provider = new ProviderDTO((rs.getInt(1)), rs.getString(2));
                provider.setCategories(getCatigories(rs.getInt(1)));
                providerDTOs.add(provider);
            }
            rs.close();
            return providerDTOs;

        } catch (SQLException e) {
            logger.error(e);
        } finally {
            closeStatement(sqlStatement);
            closeConnection(conn);
        }
        return providerDTOs;
    }

    public ProviderDTO getProviderForVoucherService(int serviceId) {
        conn = getConnection();
        PreparedStatement sqlStatement = null;
        ProviderDTO provider = null;
        try {
            String sql = "select ID_V_PROVIDERS,STR_NAME from  V_PROVIDERS where ID_V_PROVIDERS = ? ";
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setInt(1, serviceId);
            ResultSet rs = sqlStatement.executeQuery();
            if (rs.next()) {
                provider = new ProviderDTO((rs.getInt(1)), rs.getString(2));
                provider.setCategories(getCatigories(rs.getInt(1)));
            }
            rs.close();
            return provider;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            closeStatement(sqlStatement);
            closeConnection(conn);
        }
        return provider;
    }

    private List<CategoryDTO> getCatigories(int providerId) throws SQLException {
        PreparedStatement sqlStatement = null;
        List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
        ResultSet rs = null;
        try {
//            String sql = "select  ID_V_PROVIDERS,Provider,CardValues from  V_PROVIDERS_AND_VALUES_VW where ID_V_PROVIDERS=" + serviceId;
            String sql = "select id_cat,str_name from  v_cats where id_v_provider = ? ";
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setInt(1, providerId);
            rs = sqlStatement.executeQuery();
            CategoryDTO category = null;
            while (rs.next()) {
                category = new CategoryDTO(rs.getString(2), rs.getInt(1));
                category.setAvilableValues(getVoucherValues(rs.getInt(1)));
                categories.add(category);
            }
            rs.close();
            return categories;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
//            rs.close();
            closeStatement(sqlStatement);
        }
        return categories;
    }

    public ProviderDTO getProviderForVoucherService_V(int serviceId, int Customer_id) {
        conn = getConnection();
        PreparedStatement sqlStatement = null;
        ProviderDTO provider = null;
        try {
            String sql = "select  ID_V_PROVIDERS,STR_NAME from  V_PROVIDERS where ID_V_PROVIDERS = ? ";
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setInt(1, serviceId);
            ResultSet rs = sqlStatement.executeQuery();
            if (rs.next()) {
                provider = new ProviderDTO((rs.getInt(1)), rs.getString(2));
                provider.setCategories(getCatigories_V(rs.getInt(1), Customer_id));
            }
            rs.close();
            return provider;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            closeStatement(sqlStatement);
            closeConnection(conn);
        }
        return provider;
    }

    private List<CategoryDTO> getCatigories_V(int providerId, int Customer_id) throws SQLException {
        PreparedStatement sqlStatement = null;
        List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
        ResultSet rs = null;
        try {
//            String sql = "select  ID_V_PROVIDERS,Provider,CardValues from  V_PROVIDERS_AND_VALUES_VW where ID_V_PROVIDERS=" + serviceId;
            String sql = "select id_cat,str_name from  v_cats where id_v_provider = ? ";
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setInt(1, providerId);
            rs = sqlStatement.executeQuery();
            CategoryDTO category = null;
            while (rs.next()) {
                category = new CategoryDTO(rs.getString(2), rs.getInt(1));
                category.setAvilableValues(getVoucherValues_V(rs.getInt(1), Customer_id, providerId));
                categories.add(category);
            }
            rs.close();
            return categories;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
//            rs.close();
            closeStatement(sqlStatement);
        }
        return categories;
    }

    private Object[] getVoucherValues_V(int catigoryId, int Customer_id, int Provider_Id) {
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;
        List arr = new ArrayList();
        try {
            String sql = "select VOUCHER_VALUE "
                    + "from  V_CATS_VALUES where ID_CAT = ? and voucher_value in"
                    + "(SELECT v.id_v_providers_value FROM  V_PROVIDERS_VALUES v where v.id_v_providers = ? and v.id_v_providers_values in "
                    + "(SELECT vo.id_v_providers_values FROM  vouchers vo where vo.ID_OWNER = ? and vo.ID_V_PROVIDERS=v.id_v_providers and vo.status=0)) order by 1";
            // //System.out.println(sql);
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setInt(1, catigoryId);
            sqlStatement.setInt(2, Provider_Id);
            sqlStatement.setInt(3, Customer_id);
            rs = sqlStatement.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString(1));
            }
            rs.close();
            return arr.toArray();
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            closeStatement(sqlStatement);
        }
        return arr.toArray();
    }

    private Object[] getVoucherValues(int catigoryId) {
        PreparedStatement sqlStatement = null;
        List arr = new ArrayList();
        try {
            String sql = "select VOUCHER_VALUE from  V_CATS_VALUES where ID_CAT = ? order by 1";
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setInt(1, catigoryId);
            ResultSet rs = sqlStatement.executeQuery();
            while (rs.next()) {
                arr.add(rs.getString(1));
            }
            rs.close();
            return arr.toArray();
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            closeStatement(sqlStatement);
        }
        return arr.toArray();
    }

    private void getVoucher(String operationId) {
        if (conn == null) {
            conn = getConnection();
        }
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;
        try {
//            String sql = "select  ID_V_PROVIDERS,Provider,CardValues from  V_PROVIDERS_AND_VALUES_VW where ID_V_PROVIDERS=" + serviceId;
            String sql = "select 'Voucher:'|| cryptit.decrypt(str_vaoucher)||' Serial:'||sn from  vouchers where id_vouchers = ?";
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, operationId);
            rs = sqlStatement.executeQuery();
            if (rs.next()) {
                voucher = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            closeStatement(sqlStatement);
        }
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public SellVoucherResponse do_Voucher_transaction(int Customer_ID, double Value, int Provider_ID, String Printing, String Phone_ID, String senderTime, String senderTrxID) throws Exception {
        SellVoucherResponse sellVoucherResponse = new SellVoucherResponse();
        Connection conn = getConnection();
        CallableStatement sqlStatement = null;
        try {
            String callSQL = "{ call ? :=  DO_VOUCHER_TRANSACTION(?,?,?,?,?,?,?,?,?) }";
            sqlStatement = conn.prepareCall(callSQL);
            sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
            sqlStatement.registerOutParameter(9, OracleTypes.VARCHAR);
            sqlStatement.registerOutParameter(10, OracleTypes.VARCHAR);
            sqlStatement.setInt(2, Customer_ID);
            sqlStatement.setDouble(3, Value);
            sqlStatement.setInt(4, Provider_ID);
            sqlStatement.setString(5, Printing);
            sqlStatement.setString(6, Phone_ID);
            sqlStatement.setString(7, senderTime);
            sqlStatement.setString(8, senderTrxID);
            sqlStatement.execute();
            sellVoucherResponse.setTransId(sqlStatement.getString(1));
            sellVoucherResponse.setVoucher(sqlStatement.getString(9));
            sellVoucherResponse.setSerialNumber(sqlStatement.getString(10));
            if (sellVoucherResponse.getTransId().equals("-1")) {
                throw new Exception("Service temporarily unavailable");
            } else if (sellVoucherResponse.getTransId().equals("-2")) {
                throw new Exception("You don't have enogh balance");
            } else if (sellVoucherResponse.getTransId().equals("-3")) {
                throw new Exception("You don't have voucher with this value ");
            } else if (sellVoucherResponse.getTransId().equals("-4")) {
                throw new Exception("Service temporarily unavailable Call masary office");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new Exception(e.getMessage());
        } finally {
            closeStatement(sqlStatement);
            closeConnection(conn);
            return sellVoucherResponse;
        }
    }
}
