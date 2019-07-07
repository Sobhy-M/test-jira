package com.masary.database;

import com.masary.utils.MasaryEncryption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MasaryManager extends BaseManager {

    private static MasaryManager instance = null;
    public static Logger logger = Logger.getLogger(MasaryManager.class);

    public static MasaryManager getInstance() {
        if (instance == null) {
            PropertyConfigurator.configureAndWatch("ImagesUploader/WEB-INF/classes/log4j.properties");
            instance = new MasaryManager();
        }
        return instance;
    }
    
    
    public boolean checkCustomer(String encId, String encPass) throws Exception {
        boolean result = false;
        String id = MasaryEncryption.decrypt(encId);
        String password = MasaryEncryption.decrypt(encPass);
        
        System.out.println("---------------------------- " + id);
        System.out.println("---------------------------- " + password);
        
        logger.info("---------------------------- " + id);
        logger.info("---------------------------- " + password);
        
        Connection conn = getConnection();
        String sql = null;
        sql = "select ID_CUSTOMER from CUSTOMERS where ID_CUSTOMER=? and PASSWORD=encrypt(?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, Long.parseLong(id));
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            closeStatement(stmt);
            closeConnection(conn);
        }
        return result;
    }
    
}