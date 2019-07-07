package com.masary.database.manager;

import NewSYS.Getinfo;
import com.google.gson.Gson;
import com.masary.common.CONFIG;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import com.masary.database.dto.AgentDTO;
import com.masary.database.dto.AuthenticationResponse;
import com.masary.database.dto.AvilableVouchersDTO;
import com.masary.database.dto.BillDTO;
import com.masary.database.dto.Bill_Request;
import com.masary.database.dto.Bill_Response;
import com.masary.database.dto.BulkSMSReportDTO;
import com.masary.database.dto.BulkVoucherDTO;
import com.masary.database.dto.CashURequest;
import com.masary.database.dto.CashUResponse;
import com.masary.database.dto.CategoryDTO;
import com.masary.database.dto.Country;
import com.masary.database.dto.CustomerDTO;
import com.masary.database.dto.CustomerProfile;
import com.masary.database.dto.CustomerServiceDTO;
import com.masary.database.dto.Customers_Properties;
import com.masary.database.dto.DonationAgentPaymentRespponseDto;
import com.masary.database.dto.Donation_AgentPaymentRequestDTO;
import com.masary.database.dto.ELectricityBillInquiry;
import com.masary.database.dto.EarnListDTO;
import com.masary.database.dto.ElectricityBillPaymentResult;
import com.masary.database.dto.EmployeeDTO;
import com.masary.database.dto.Etisalat_Request;
import com.masary.database.dto.GenericElectricityBill;
import com.masary.database.dto.GenericSellVoucherResponse;
import com.masary.database.dto.GenericVoucherData;
import com.masary.database.dto.Governorate;
import com.masary.database.dto.GroupDTO;
import com.masary.database.dto.IsValidDTO;
import com.masary.database.dto.LoginDto;
import com.masary.database.dto.Main_Provider;
import com.masary.database.dto.Masary_Bill_Amounts;
import com.masary.database.dto.Masary_Bill_Receipt;
import com.masary.database.dto.Masary_Bill_Request;
import com.masary.database.dto.Masary_Bill_Response;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.dto.Masary_Biller;
import com.masary.database.dto.Masary_Billing_Account;
import com.masary.database.dto.Masary_Error;
import com.masary.database.dto.Masary_Payment_Request;
import com.masary.database.dto.Masary_Payment_Response;
import com.masary.database.dto.Masary_Payment_Type;
import com.masary.database.dto.MubasherResponseDto;
import com.masary.database.dto.MunasherItemsInfo;
import com.masary.database.dto.ProviderDTO;
import com.masary.database.dto.RatePlanDTO;
import com.masary.database.dto.SellVoucherResponse;
import com.masary.database.dto.ServiceDTO;
import com.masary.database.dto.ServicesDTO;
import com.masary.database.dto.TransactionDTO;
import com.masary.database.dto.TransactionInfoDTO;
import com.masary.database.dto.VC_Commission_Structure;
import com.masary.database.dto.VC_Transaction_Status;
import com.masary.database.dto.VfSurveyDTO;
import com.masary.database.dto.VodafoneHttpResponse;
import com.masary.database.dto.Vodafone_Bill_Response;
import com.masary.database.dto.Vodafone_Cash_Transactions;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Array;
import java.sql.Clob;
import java.sql.Struct;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.servlet.http.HttpSession;
import oracle.jdbc.driver.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.StructDescriptor;
import org.joda.time.DateTime;
import com.masary.database.dto.VoucherDenoDTO;
import com.masary.integration.dto.ElectricityPaymentResponse;
import com.masary.utils.SystemSettingsUtil;
import com.masary.utils.TimedHashMap;
import java.io.FileNotFoundException;

public class MasaryManager extends BaseManger {

	private static MasaryManager instance = null;

	public static Logger logger = Logger.getLogger(CONFIG.APP_LOG);

	private static TimedHashMap<String, String> mmTimedMap = new TimedHashMap<String, String>(120000, 1000);

	public static MasaryManager getInstance() {
		if (instance == null) {

			initLog4j();

			instance = new MasaryManager();
		}
		return instance;
	}

	public static void initLog4j() {
		String baseFileName = "log4j.properties";
		InputStream baseInputStream = MasaryManager.class.getClassLoader().getResourceAsStream(baseFileName);
		PropertyConfigurator.configure(baseInputStream);
	}

	public long addBalance(String payedId, String payerId, String balance) throws Exception {

		String preLog = "addBalance from=" + payerId + ", to=" + payedId + ", amount=" + balance;

		logger.info(preLog);

		Connection conn = null;
		CallableStatement sqlStatement = null;

		try {

			String v = mmTimedMap.put(String.valueOf(payerId) + String.valueOf(payedId) + String.valueOf(balance),
					String.valueOf(payedId));
			if (v == null) {
				MasaryManager.logger.info(preLog + ", Repeated Transaction");

				throw new Exception("You can't assign balance to this customer, Transaction is repeated");
			}

			conn = getConnection();

			if (balance.isEmpty() || payerId.isEmpty()) {
				throw new Exception("Please Complete all the input data ");
			}
			String callSQL = "{ call ? :=  pk_Trans.Assign_Balance(?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.registerOutParameter(5, OracleTypes.NUMBER);
			sqlStatement.setInt(2, Integer.parseInt(payerId));
			sqlStatement.setInt(3, Integer.parseInt(payedId));
			sqlStatement.setInt(4, Integer.parseInt(balance));
			sqlStatement.execute();

			long transId = sqlStatement.getLong(5);

			MasaryManager.logger.info(preLog + ", transId= " + transId);
			
			if (sqlStatement.getLong(1) == -41) {
				return -41;
			}
			
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0 ");
			}
			if (transId == -2) {
				throw new Exception("You can't assign balance to yourself");
			}
			if (transId == 0) {
				throw new Exception("You can't assign balance to this customer");
			}
			if (transId == -10) {
				throw new Exception("You can't assign balance to this customer only Masary Admin Can do it");
			}
			
			return transId;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage().contains("ORA-200")) {
				throw new Exception(
						e.getMessage().substring(e.getMessage().indexOf(":") + 1, e.getMessage().indexOf(".")));
			} else if (e.getMessage().contains("ORA-20139")) {
				throw new Exception(e.getMessage());
			} else {
				throw new Exception(CONFIG.errorTransactionar);
			}
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public TransactionInfoDTO transferCustomerTopUp(String custID, String msisdn, String amount, int topupType,
			String catId, String lang) throws Exception {
		if (!ValidateTopupAmount(topupType, amount)) {
			throw new Exception("Sorry, please enter a valid amount.");
		}
		switch (topupType) {
		case 6:
			return USSDEtisalatManager.getInstance().transferCustomerTopUp(custID, msisdn, amount, lang);
		case 7:
			return USSDEtisalatManager.getInstance().transferCredit(custID, msisdn, amount, lang);
		case 8:
			return MobinilHttpManager.getInstance().transferCustomerTopUp(custID, msisdn, amount, lang);
		case 9:
			return USSDMobinilManager.getInstance().transferMobinilCustomerTransfer(custID, msisdn, amount, lang);
		case 10:
			return USSDVodafoneManager.getInstance().transferVodafoneCustomerTopUp(custID, msisdn, amount, lang);
		case 11:
			return USSDVodafoneManager.getInstance().transferVodafoneCustomerTransfer(custID, msisdn, amount, lang);
		default:
			return null;
		}
	}

	public ArrayList<VfSurveyDTO> getVodaQuestions() {
		ArrayList<VfSurveyDTO> list = new ArrayList<VfSurveyDTO>();
		Connection conn = null;
		String sql = null;
		sql = "select qid, q_string, op1, op2, op3, answer from vf_survey";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			VfSurveyDTO survey;
			while (rs.next()) {
				survey = new VfSurveyDTO();
				survey.setQuestionID(rs.getInt(1));
				survey.setQuestionText(rs.getString(2));
				survey.setOption1(rs.getString(3));
				survey.setOption2(rs.getString(4));
				survey.setOption3(rs.getString(5));
				survey.setAnswer(rs.getString(6));
				list.add(survey);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public String getUploadEncryptionKey() {
		String result = "";
		Connection conn = null;
		String sql = null;
		sql = "select PROPERTY_VALUE from CONFIGURATION where PROPERTY_NAME='upload_enc_key'";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
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

	public List<EarnListDTO> getEarnList(String customerID, String dateFrom, String dateTo) throws Exception {

		List<EarnListDTO> list = new ArrayList<EarnListDTO>();

		Connection conn = null;
		CallableStatement sqlStatement = null;
		ResultSet eList = null;
		EarnListDTO Earn;
		try {
			conn = getConnection();
			String callSQL = "{call ? :=   get_cust_comm(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.CURSOR);
			sqlStatement.setString(2, customerID);
			sqlStatement.setString(3, dateFrom);
			sqlStatement.setString(4, dateTo);
			sqlStatement.execute();
			eList = (ResultSet) sqlStatement.getObject(1);
			while (eList.next()) {

				Earn = new EarnListDTO();
				Earn.setDate(eList.getString("trans_date"));
				Earn.setAllEarn(eList.getString("tot_comm"));
				Earn.setAllFees(eList.getString("tot_fees"));
				Earn.setTransNum(eList.getInt("day_count"));
				list.add(Earn);
			}
			eList.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return list;
	}

	public long addBalanceToBill(String agentId, String payerId, String balance) throws Exception {

		logger.info("addBalance from " + payerId + " to " + agentId + " amount " + balance);
		Connection conn = null;
		CallableStatement sqlStatement = null;

		try {

			if (balance.isEmpty() || payerId.isEmpty()) {
				throw new Exception("Please Complete all the input data ");
			}

			conn = getConnection();
			String callSQL = "{ call ? :=  ADD_BALNCE_TO_BILLS(?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, Integer.parseInt(agentId));
			sqlStatement.setInt(3, Integer.parseInt(payerId));
			sqlStatement.setInt(4, Integer.parseInt(balance));
			sqlStatement.execute();

			long transId = sqlStatement.getLong(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0 ");
			}
			if (transId == -2) {
				throw new Exception("Customer not found ");
			}
			if (transId == -3) {
				throw new Exception("You don't have enough balance ");
			}

			return transId;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	int saveVodafoneTrans2(String msisdn, String custID, String amount, String operationId, int trxType)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;

		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  SAVEVODAFONETRANSACTION2(?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, msisdn);
			sqlStatement.setInt(3, Integer.parseInt(custID));

			sqlStatement.setFloat(4, Float.parseFloat(amount));
			sqlStatement.setInt(5, Integer.parseInt(operationId));
			sqlStatement.setInt(6, trxType);

			sqlStatement.execute();

			int transId = sqlStatement.getInt(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You do not have enough balance ");
			}

			return transId;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	float[] saveAirTransaction(String msisdn, String custID, String amount, String operationId, int trxType, int isUSSD)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;

		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  PK_TRANS.Set_Air_Transaction(?,?,?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER); // TXN ID
			sqlStatement.setInt(2, Integer.parseInt(custID)); // customer id
			sqlStatement.setInt(3, trxType); // service id
			sqlStatement.setFloat(4, Float.parseFloat(amount)); // amount
			sqlStatement.registerOutParameter(5, OracleTypes.NUMBER); // tax OUT
			sqlStatement.setInt(6, Integer.parseInt(operationId)); // operation id
			sqlStatement.setString(7, msisdn);
			sqlStatement.setInt(8, isUSSD);

			sqlStatement.execute();

			int transId = sqlStatement.getInt(1);
			float taxAmount = sqlStatement.getFloat(5);

			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You do not have enough balance ");
			}

			float[] res = new float[2];
			res[0] = transId;
			res[1] = taxAmount;

			return res;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public void addAgent(String id, String username, String arabicUserName, String phone, String isRetailGroup,
			String autoAllocate, String sQuestion, String sAnswer, HttpServletRequest request) throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		String password;

		try {
			conn = getConnection();
			String callSQL = "{call  ADDAGENT ( '" + username + "','" + arabicUserName + "','" + phone + "','"
					+ isRetailGroup + "','" + autoAllocate + "','" + sQuestion + "','" + sAnswer + "'," + id + ",?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			sqlStatement.registerOutParameter(2, java.sql.Types.NUMERIC);
			sqlStatement.execute();
			password = sqlStatement.getString(1);
			int custId = sqlStatement.getInt(2);
			closeStatement(sqlStatement);
			if (custId == -1) {
				throw new Exception("Enter another phone number");
			}
			sendSMS(phone, "Welcome to masary your agent id " + custId + " Your Password  " + password);
			sendWab(phone);
			String serviceId, serviceValue;
			Enumeration parameters = request.getParameterNames();
			while (parameters.hasMoreElements()) {
				serviceId = (String) parameters.nextElement();
				serviceValue = request.getParameter(serviceId);
				if ("on".equals(serviceValue)) {
					joinService(custId, serviceId, 0);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public void insertWaitedTXN(String custID, String amount, String mobile, String TXN) throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;

		try {
			conn = getConnection();
			String callSQL = "{call  WAITED_TXN_OPERATIONS (?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setInt(1, Integer.parseInt(custID));
			sqlStatement.setFloat(2, Float.parseFloat(amount));
			sqlStatement.setString(3, mobile);
			sqlStatement.setInt(4, Integer.parseInt(TXN));
			sqlStatement.execute();

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public void updateAgent(String pin, String name, String arabicName, String phone, HttpServletRequest request)
			throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sql = "update  CUSTOMERS set STR_DISPLAY_NAME='" + name + "',STR_DISPLAY_NAME_ARABIC='" + arabicName
					+ "', MSISDN ='" + phone + "' where ID_CUSTOMER=" + pin;

			stmt = conn.prepareStatement(sql);
			stmt.execute();
			closeStatement(stmt);
			int custId = Integer.parseInt(pin);
			String serviceId, serviceValue;
			Enumeration parameters = request.getParameterNames();

			while (parameters.hasMoreElements()) {
				serviceId = (String) parameters.nextElement();
				serviceValue = request.getParameter(serviceId);
				if ("on".equals(serviceValue)) {
					joinService(custId, serviceId, 0);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public void addBill(String custId, String id, String amount, String month, String year) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sql = "insert into  MFI_BILLS (id,ID_CUSTOMER,MFI_ID,AMOUNT,PILLMONTH,ISPAYED) VALUES ( GETBILLID(),"
					+ custId + "," + id + "," + amount + ",to_Date('" + month + "/" + year + "','mm/yyyy'),0)";
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public void insertEmployeeTransactions(String empID, String TXNno) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sql = "insert into  employee_txn values ((select max(employee_txn_id)+1 from  employee_txn), ?, ?) ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, empID);
			stmt.setString(2, TXNno);
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public void addCustomer(String id, String username, String arabicUserName, String phone, String autoAllocate,
			String canAddEmployee, String sQuestion, String sAnswer, HttpServletRequest request) throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		String password;

		try {
			conn = getConnection();
			String callSQL = "{call  ADDCUSTOMER( N'" + username + "','" + arabicUserName + "','" + phone + "','"
					+ autoAllocate + "','" + canAddEmployee + "','" + sQuestion + "','" + sAnswer + "',?," + id
					+ ",?)}";

			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			sqlStatement.registerOutParameter(2, java.sql.Types.NUMERIC);
			sqlStatement.execute();
			password = sqlStatement.getString(1);
			int custId = sqlStatement.getInt(2);

			if (custId == -1) {
				throw new Exception("Enter another phone number");
			}

			sendSMS(phone, "Welcome to Masary. Your customer ID " + custId + " Your password " + password);
			sendWab(phone);

			String serviceId, serviceValue;
			Enumeration parameters = request.getParameterNames();

			while (parameters.hasMoreElements()) {
				serviceId = (String) parameters.nextElement();
				serviceValue = request.getParameter(serviceId);
				if ("on".equals(serviceValue)) {
					joinService(custId, serviceId, 0);
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}

	}

	public void addEmployee(String id, String username, String arabicUserName, String phone, String autoAllocate,
			String canAddEmployee, String sQuestion, String sAnswer/* , HttpServletRequest request */)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		String password;

		try {
			conn = getConnection();
			String callSQL = "{call  ADDEMPLOYEE( N'" + username + "','" + arabicUserName + "','" + phone + "','"
					+ autoAllocate + "','" + canAddEmployee + "','" + sQuestion + "','" + sAnswer + "',?," + id
					+ ",?)}";

			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			sqlStatement.registerOutParameter(2, java.sql.Types.NUMERIC);
			sqlStatement.execute();
			password = sqlStatement.getString(1);
			int custId = sqlStatement.getInt(2);

			if (custId == -1) {
				throw new Exception("Enter another phone number");
			}

			sendSMS(phone, "Welcome to Masary. Your customer ID " + custId + " Your password " + password);
			sendWab(phone);

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public List<AgentDTO> getEmployees(String id) {
		List<AgentDTO> list = new ArrayList<AgentDTO>();
		Connection conn = null;
		String sql = null;
		sql = "select cust.ID_CUSTOMER,cust.MSISDN,cust.STR_DISPLAY_NAME,cust.STR_DISPLAY_NAME_ARABIC,cust.BOL_IS_ACTIVE "
				+ "from  VW_CUSTOMERScust where cust.ID_PARENT = ? order by 1";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			AgentDTO agent;
			while (rs.next()) {
				agent = new AgentDTO();
				agent.setPin(rs.getString("ID_CUSTOMER"));
				agent.setName(rs.getString("STR_DISPLAY_NAME"));
				agent.setArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
				agent.setPhone(rs.getString("MSISDN"));
				agent.setActive(rs.getString("BOL_IS_ACTIVE").equals("Y") ? true : false);
				agent.setCanAddEmployee(false);
				list.add(agent);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public String addCustomerFromAPI(String id, String username, String arabicUserName, String phone,
			String autoAllocate, String sQuestion, String sAnswer) throws Exception {
		return "";
	}

	// ------------------------------------END-Omnya-[24-02-2014]-------------------------------------
	public String addCustomerFromAPI(String username, String arabicUserName, String msisdn, String parentID)
			throws Exception {
		logger.info("Add customer from API under " + parentID);
		String result = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ? :=   ADD_CUSTOMER_FROM_MOBILE(? , ? , ? , ?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
			sqlStatement.setString(2, username);
			sqlStatement.setString(3, arabicUserName);
			sqlStatement.setString(4, msisdn);
			sqlStatement.setString(5, parentID);
			sqlStatement.execute();
			String custId = sqlStatement.getString(1);
			result = custId;
			if (custId.equals("-1")) {
				result = "-10 Enter another phone number";
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			result = "-100 General Error Contact Masary Admin.";
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return result;
	}

	public String getCustomerID(String id) throws Exception {
		Connection conn = null;
		String cusID = "-1";
		String sql = "select ID_CUSTOMER " + "from  CUSTOMERS where ID_CUSTOMER = ? or MSISDN = ? ";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				cusID = rs.getString("ID_CUSTOMER");
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return cusID;
	}

	public String checkCode(String codeID, String msisdn) {
		logger.info("checkCode with codeID " + codeID + " msisdn " + msisdn);
		String result = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ? :=  CHECK_MASARY_COMPITION_CODES(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, codeID);
			sqlStatement.setString(3, msisdn);
			sqlStatement.execute();
			result = sqlStatement.getString(1);
			result = "200 " + result;
			logger.info("checkCode with msisdn " + msisdn + " result " + result);
		} catch (SQLException ex) {
			result = "-100 General Error Contact Masary Admin.";
			logger.error(ex);
		} catch (Exception ex) {
			result = "-100 General Error Contact Masary Admin.";
			logger.error(ex);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}

		return result;
	}
	// ------------------------------------END-Omnya-[24-02-2014]-------------------------------------

	public void addInitialLoyalityPoints(String msisdn) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int added_points = 0;
		try {
			conn = getConnection();
			String sql = "insert into  loyality_points values ((select id_customer from  customers where msisdn=?), ?, sysdate)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, msisdn);
			stmt.setInt(2, added_points);
			stmt.execute();
			conn.commit();
		} catch (Exception ex) {
			logger.debug(ex);
			//// System.out.println(ex.toString());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public ProviderDTO getProviderForVoucherService(int serviceId) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		ProviderDTO provider = null;
		try {
			conn = getConnection();
			String sql = "select ID_V_PROVIDERS,STR_NAME,s.str_service_name,s.str_service_name_ar "
					+ "from  V_PROVIDERS, services s " + "where  ID_V_PROVIDERS = s.id_service and ID_V_PROVIDERS = ? ";
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setInt(1, serviceId);
			ResultSet rs = sqlStatement.executeQuery();
			if (rs.next()) {
				provider = new ProviderDTO((rs.getInt(1)), rs.getString(2));
				provider.setServiceNameEn(rs.getString(3));
				provider.setServiceNameAr(rs.getString(4));
				provider.setCategories(getCatigories(rs.getInt(1)));
			}
			rs.close();
			return provider;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return provider;
	}

	private List<CategoryDTO> getCatigories(int providerId) throws SQLException {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
		ResultSet rs = null;
		try {
			conn = getConnection();
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
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return categories;
	}

	private Object[] getVoucherValues(int catigoryId) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		List arr = new ArrayList();
		try {
			conn = getConnection();
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
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return arr.toArray();
	}

	public Object[] getVoucherValuesInternational(int providerId) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		List arr = new ArrayList();
		try {
			conn = getConnection();
			String sql = "select ID_V_PROVIDERS_VALUE from V_PROVIDERS_VALUES where ID_V_PROVIDERS=? order by 1";
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setInt(1, providerId);
			ResultSet rs = sqlStatement.executeQuery();
			while (rs.next()) {
				arr.add(rs.getString(1));
			}
			rs.close();
			return arr.toArray();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return arr.toArray();
	}

	public ProviderDTO getProviderForVoucherService_V(int serviceId, int Customer_id) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		ProviderDTO provider = null;
		try {
			conn = getConnection();
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
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return provider;

	}

	private List<CategoryDTO> getCatigories_V(int providerId, int Customer_id) throws SQLException {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
		ResultSet rs = null;
		try {
			conn = getConnection();
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
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return categories;
	}

	private Object[] getVoucherValues_V(int catigoryId, int Customer_id, int Provider_Id) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		ResultSet rs = null;
		List arr = new ArrayList();
		try {
			conn = getConnection();
			String sql = "select VOUCHER_VALUE " + "from  V_CATS_VALUES where ID_CAT = ? and voucher_value in"
					+ "(SELECT v.id_v_providers_value FROM  V_PROVIDERS_VALUES v where v.id_v_providers = ? and v.id_v_providers_values in "
					+ "(SELECT vo.id_v_providers_values FROM  vouchers vo where vo.ID_OWNER = ? and vo.ID_V_PROVIDERS=v.id_v_providers and vo.status=0)) order by 1";
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
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return arr.toArray();
	}

	public List<CustomerDTO> getAllCustomers() {
		Connection conn = null;
		PreparedStatement sqlStatement = null;

		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		try {
			conn = getConnection();
			String sql = "select  ID_CUSTOMER , STR_DISPLAY_NAME,STR_DISPLAY_NAME_ARABIC from  CUSTOMERS order by ID_CUSTOMER";
			sqlStatement = conn.prepareStatement(sql);
			ResultSet rs = sqlStatement.executeQuery();

			while (rs.next()) {

				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setCustomerID(rs.getInt("ID_CUSTOMER"));
				customerDTO.setCustomerName(rs.getString("STR_DISPLAY_NAME"));
				customerDTO.setCustomerArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
				customerDTOs.add(customerDTO);
			}
			rs.close();
			return customerDTOs;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return customerDTOs;
	}

	public String getNotifications(int role_id) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;

		String note = "";
		try {
			conn = getConnection();
			String sql = "select not_str from  notifications where not_type=? and role_id=?";
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setInt(1, 1);
			sqlStatement.setInt(2, role_id);

			ResultSet rs = sqlStatement.executeQuery();

			while (rs.next()) {
				note = rs.getString("not_str");
			}
			rs.close();
			return note;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return note;
	}

	public String getWarning(int role_id) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;

		String warning = "";
		try {
			conn = getConnection();
			String sql = "select not_str from  notifications where not_type=2 and role_id=" + role_id;
			sqlStatement = conn.prepareStatement(sql);
			ResultSet rs = sqlStatement.executeQuery();

			while (rs.next()) {
				warning = rs.getString("not_str");
			}
			rs.close();
			return warning;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return warning;
	}

	public int getvouchercount(int servID, int CusID, double denemonation) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		try {
			conn = getConnection();
			String sql = "SELECT count(vo.id_v_providers_values) "
					+ "FROM  vouchers vo where vo.ID_OWNER=? and vo.ID_V_PROVIDERS=? "
					+ "and VO.ID_V_PROVIDERS_VALUES=(select vp.id_v_providers_values "
					+ "from  v_providers_values vp where vp.id_v_providers=vo.id_v_providers and vp.id_v_providers_value=?) "
					+ "and vo.status=0 order by 1";
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setInt(1, CusID);
			sqlStatement.setInt(2, servID);
			sqlStatement.setDouble(3, denemonation);

			ResultSet rs = sqlStatement.executeQuery();

			while (rs.next()) {
				return rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return 0;
	}

	public List<CustomerDTO> getAllCustomers(String agentId) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;

		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		try {
			conn = getConnection();
			String sql = "select  ID_CUSTOMER , STR_DISPLAY_NAME,STR_DISPLAY_NAME_ARABIC from  CUSTOMERS where id_parent = ?"
					+ "or id_customer =(select id_parent from  CUSTOMERS where id_customer = ? ) order by STR_DISPLAY_NAME ";
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setString(1, agentId);
			sqlStatement.setString(2, agentId);
			ResultSet rs = sqlStatement.executeQuery();

			while (rs.next()) {

				if (rs.getInt("ID_CUSTOMER") != 1) {
					CustomerDTO customerDTO = new CustomerDTO();
					customerDTO.setCustomerID(rs.getInt("ID_CUSTOMER"));
					customerDTO.setCustomerName(rs.getString("STR_DISPLAY_NAME"));
					customerDTO.setCustomerArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
					customerDTOs.add(customerDTO);
				}
			}
			rs.close();
			return customerDTOs;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return customerDTOs;
	}

	public List<ServiceDTO> getAllServices() {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		List<ServiceDTO> serviceDTOs = new ArrayList<ServiceDTO>();
		try {
			conn = getConnection();
			String sql = "select  id_Service, STR_Service_NAME,price,STR_Service_NAME_AR from  Services order by id_Service ";
			sqlStatement = conn.prepareStatement(sql);
			ResultSet rs = sqlStatement.executeQuery();
			while (rs.next()) {
				ServiceDTO ServiceDTO = new ServiceDTO();
				ServiceDTO.setIdService(rs.getInt("id_Service"));
				ServiceDTO.setStrServiceName(rs.getString("STR_Service_NAME"));
				ServiceDTO.setPrice(rs.getDouble("price"));
				ServiceDTO.setStrServiceNameArabic(rs.getString("STR_Service_NAME_AR"));
				serviceDTOs.add(ServiceDTO);
			}
			rs.close();
			return serviceDTOs;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return serviceDTOs;
	}

	public int isVoucherService(int serviceID) {
		int res = 0;
		Connection conn = null;
		String sql = "select is_voucher from  services where id_service = ?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, serviceID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				res = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return res;
	}

	public AuthenticationResponse authenticateSiteAdmin(String userName, String password) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select ROLE_ID,id from   WEB_USERS,  CUSTOMERS"
					+ " where ID_CUSTOMER = id and  lower(USER_NAME)=lower(?) and PASSWORD= encrypt(?)";
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setString(1, userName);
			sqlStatement.setString(2, password);
			rs = sqlStatement.executeQuery();
			if (rs.next()) {
				return new AuthenticationResponse(rs.getString("id"), rs.getString("ROLE_ID"), "-1");
			}
			rs.close();
			return new AuthenticationResponse("-1", "-1", "-1");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new AuthenticationResponse("-1", "-1", "-1");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);

		}
	}

	public AuthenticationResponse authenticateSiteUserID(String userName, String password) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		try {
			conn = getConnection();
			String sql = "";
			if (userName.equals("928") || userName.equals("7563")) {
				sql = "select ID_CUSTOMER, ID_PARENT, BOL_IS_ACTIVE  from  CUSTOMERS where ID_CUSTOMER=? and MSISDN=?";
			} else {
				sql = "select ID_CUSTOMER, ID_PARENT, BOL_IS_ACTIVE  from  CUSTOMERS where ID_CUSTOMER=? and PASSWORD= encrypt(?)";
			}
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setString(1, userName);
			if (userName.equals("928") || userName.equals("7563")) {
				sqlStatement.setString(2, userName);
			} else {
				sqlStatement.setString(2, password);
			}
			ResultSet rs = sqlStatement.executeQuery();
			if (rs.next()) {
				String id = rs.getString("ID_CUSTOMER");
				String parent = rs.getString("ID_PARENT");
				if (rs.getString("BOL_IS_ACTIVE").equals("Y")) {
					{
						closeStatement(sqlStatement);
						sql = "select ROLE_ID from  WEB_USERS where id=?";
						sqlStatement = conn.prepareStatement(sql);
						sqlStatement.setInt(1, Integer.parseInt(id));
						rs.close();
						rs = sqlStatement.executeQuery();
						if (rs.next()) {
							if (!rs.getString("ROLE_ID").equals("8")) {
								return new AuthenticationResponse(id, rs.getString("ROLE_ID"), "-1"); // Agent
							} else {
								return new AuthenticationResponse(parent, "3", id);
							}
						} else {
							return new AuthenticationResponse(id, "3", "-1"); // Customer
						}
					}
				} else {
					return new AuthenticationResponse("-1", "-2", "-1");
				}
			}
			rs.close();
			return new AuthenticationResponse("-1", "-1", "-1");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AuthenticationResponse("-1", "-1", "-1");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
			try {
				logger.info("getConnection" + conn.isClosed());
			} catch (SQLException ex) {
				java.util.logging.Logger.getLogger(MasaryManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public AuthenticationResponse authenticateSiteUser(String userName, String password) {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		try {
			conn = getConnection();
			if (userName.isEmpty() || password.isEmpty()) {
				throw new Exception("Empty fields userid or password");
			}
			String sql = "select ID_CUSTOMER, ID_PARENT, BOL_IS_ACTIVE  from  CUSTOMERS "
					+ "where ( MSISDN=? or ID_CUSTOMER=? ) and PASSWORD= encrypt(?)";
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setString(1, userName);
			sqlStatement.setInt(2, Integer.parseInt(userName));
			sqlStatement.setString(3, password);
			ResultSet rs = sqlStatement.executeQuery();
			if (rs.next()) {
				String id = rs.getString("ID_CUSTOMER");
				String parent = rs.getString("ID_PARENT");
				if (rs.getString("BOL_IS_ACTIVE").equals("Y")) {
					{
						closeStatement(sqlStatement);
						sql = "select ROLE_ID from  WEB_USERS where id=?";
						sqlStatement = conn.prepareStatement(sql);
						sqlStatement.setInt(1, Integer.parseInt(id));
						rs.close();
						rs = sqlStatement.executeQuery();
						if (rs.next()) {
							if (!rs.getString("ROLE_ID").equals("8")) {
								return new AuthenticationResponse(id, rs.getString("ROLE_ID"), "-1"); // Agent
							} else {
								return new AuthenticationResponse(parent, "3", id);
							}
						} else {
							return new AuthenticationResponse(id, "3", "-1"); // Customer
						}
					}
				} else {
					return new AuthenticationResponse("-1", "-2", "-1");
				}
			}
			rs.close();
			return new AuthenticationResponse("-1", "-1", "-1");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new AuthenticationResponse("-1", "-1", "-1");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);

		}
	}

	public List<AgentDTO> getAgents(String id) {
		List<AgentDTO> list = new ArrayList<AgentDTO>();
		Connection conn = null;
		String sql = null;
		sql = "select cust.ID_CUSTOMER," + "cust.MSISDN," + "cust.STR_DISPLAY_NAME," + "cust.STR_DISPLAY_NAME_ARABIC,"
				+ "cust.BOL_IS_ACTIVE," + "cust.current_balance as BALANCE " + "from  CUSTOMERS cust "
				+ "where cust.ID_PARENT=? order by 1";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			AgentDTO agent;
			while (rs.next()) {
				agent = new AgentDTO();
				agent.setPin(rs.getString("ID_CUSTOMER"));
				agent.setName(rs.getString("STR_DISPLAY_NAME"));
				agent.setArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
				agent.setBalance(rs.getDouble("BALANCE"));
				agent.setPhone(rs.getString("MSISDN"));
				agent.setActive(rs.getString("BOL_IS_ACTIVE").equals("Y") ? true : false);
				list.add(agent);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<ServicesDTO> getServices() {
		MasaryManager.logger.info("List all services in Admin wallet");
		List<ServicesDTO> list = new ArrayList<ServicesDTO>();
		PreparedStatement st = null;
		Connection conn = null;

		try {
			conn = getConnection();
			String sql = "select id_service, str_service_name, is_enabled, balance, reorder_balance, "
					+ "stop_balance from  services where id_service in (?,?,?,?,?,?)";
			st = conn.prepareStatement(sql);
			st.setInt(1, 6);
			st.setInt(2, 7);
			st.setInt(3, 8);
			st.setInt(4, 9);
			st.setInt(5, 10);
			st.setInt(6, 11);
			ResultSet rs = st.executeQuery();
			ServicesDTO servicesDTO = null;
			while (rs.next()) {
				servicesDTO = new ServicesDTO();
				servicesDTO.setServiceID(rs.getInt(1));
				servicesDTO.setServiceName(rs.getString(2));
				servicesDTO.setServiceBalance(rs.getString(4));
				servicesDTO.setServiceOrderBalance(rs.getString(5));
				servicesDTO.setServiceStopBalance(rs.getString(6));
				servicesDTO.setServiceIsEnabled(rs.getInt(3));
				list.add(servicesDTO);
			}
			rs.close();
		} catch (Exception ex) {
			MasaryManager.logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(st);
			closeConnection(conn);
		}
		return list;
	}

	public List<AgentDTO> getAgents(String field, String value) {
		List<AgentDTO> list = new ArrayList<AgentDTO>();
		Connection conn = null;
		String sql = null;
		sql = "select cust.ID_CUSTOMER," + "cust.MSISDN," + "cust.STR_DISPLAY_NAME," + "cust.STR_DISPLAY_NAME_ARABIC,"
				+ "cust.BOL_IS_ACTIVE," + "cust.current_balance as BALANCE " + "from  CUSTOMERS cust where  cust."
				+ field;
		if (field.contains("NAME")) {
			sql = sql + " like concat('%',concat(?,'%')) ";
		} else {
			sql = sql + " =? ";
		}
		sql = sql + " order by 1";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, value);
			ResultSet rs = stmt.executeQuery();
			AgentDTO agent;
			while (rs.next()) {
				agent = new AgentDTO();
				agent.setPin(rs.getString("ID_CUSTOMER"));
				agent.setName(rs.getString("STR_DISPLAY_NAME"));
				agent.setArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
				agent.setBalance(rs.getDouble("BALANCE"));
				agent.setPhone(rs.getString("MSISDN"));
				agent.setActive(rs.getString("BOL_IS_ACTIVE").equals("Y") ? true : false);
				list.add(agent);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<GroupDTO> getGroups() {
		List<GroupDTO> list = new ArrayList<GroupDTO>();
		Connection conn = null;
		String sql = null;
		sql = "select ID_GROUB,STR_GROUB_NAME from  groups";

		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			GroupDTO groupDTO;
			while (rs.next()) {
				groupDTO = new GroupDTO(rs.getInt(1), rs.getString(2));
				list.add(groupDTO);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<AgentDTO> getAgentsGroup(String state, String type, String group) {
		List<AgentDTO> list = new ArrayList<AgentDTO>();
		Connection conn = null;
		String sql = null;
		String typeSQl = "1=1";
		if (type == null || type.equals("Agent")) {
			typeSQl = " id_parent=1  ";
		} else if (type.equals("Customer")) {
			typeSQl = " id_parent!=1  ";
		}
		sql = "select cust.ID_CUSTOMER," + "cust.MSISDN,cust.STR_DISPLAY_NAME," + "cust.STR_DISPLAY_NAME_ARABIC,"
				+ "cust.BOL_IS_ACTIVE," + "cust.current_balance as BALANCE " + "from  CUSTOMERS cust "
				+ "where (cust.BOL_IS_ACTIVE = ? or cust.BOL_IS_ACTIVE = ? ) and " + typeSQl + " and 1=1 order by 1";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, (state == null || state.equals("Active")) ? "Y" : (state.equals("Deactive") ? "N" : "Y"));
			stmt.setString(2, (state == null || state.equals("Active")) ? "Y" : (state.equals("Deactive") ? "N" : "N"));
			ResultSet rs = stmt.executeQuery();
			AgentDTO agent;
			while (rs.next()) {
				agent = new AgentDTO();
				agent.setPin(rs.getString("ID_CUSTOMER"));
				agent.setName(rs.getString("STR_DISPLAY_NAME"));
				agent.setArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
				agent.setBalance(rs.getDouble("BALANCE"));
				agent.setPhone(rs.getString("MSISDN"));
				agent.setActive(rs.getString("BOL_IS_ACTIVE").equals("Y") ? true : false);
				list.add(agent);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<String> getRoles() {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String sql = "select STR_ROLE from  roles order by id_role  ";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("STR_ROLE"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<String> getLastSMS(int count, String modemId) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String sql = "select sms from(select sms  from  modemlogs where modem_id = ? order by insertdate desc) where rownum < ? order by 1 desc";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, modemId);
			stmt.setInt(1, count);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("sms"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);

		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<TransactionDTO> getTransactionsByPayed(String payedPin) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,cust2.STR_DISPLAY_NAME payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type "
				+ " from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype "
				+ "where CUSTOMER_PAYED=?  and CUSTOMER_PAYER=cust1.ID_CUSTOMER and  CUSTOMER_PAYED=cust2.ID_CUSTOMER and trx.txn_type_id= txntype.id_txn_type and trx.txn_status_id= txnstatus.id_txn_status  order by TRANSACTION_ID";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(payedPin));
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<TransactionDTO> getTransactionsByPayer(String payerPin) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char(cust2.STR_DISPLAY_NAME) payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype "
				+ "where CUSTOMER_PAYER=?" + " and CUSTOMER_PAYER=cust1.ID_CUSTOMER"
				+ " and CUSTOMER_PAYED=cust2.ID_CUSTOMER" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  " + " union all "
				+ " select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char( sales.EXTRERNAL_ENTITY)  payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, sales"
				+ " , TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype " + " where CUSTOMER_PAYER=?"
				+ " and CUSTOMER_PAYER=cust1.ID_CUSTOMER" + " and CUSTOMER_PAYED is null"
				+ " and trx.transaction_id= sales.TRANSID" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  " + " order by TRANSACTION_DATE";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			int pinn = Integer.parseInt(payerPin);
			stmt.setInt(1, pinn);
			stmt.setInt(2, pinn);

			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;

	}

	public List<TransactionDTO> getTransactionsByEmployeePayer(String payerPin) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char(cust2.STR_DISPLAY_NAME) payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type "
				+ "from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype "
				+ "where CUSTOMER_PAYER=? and CUSTOMER_PAYER=cust1.id_parent and CUSTOMER_PAYED=cust2.id_parent "
				+ "and trx.txn_type_id= txntype.id_txn_type and trx.txn_status_id= txnstatus.id_txn_status  "
				+ "union all "
				+ "select trx.TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char( sales.EXTRERNAL_ENTITY)  payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  "
				+ "from  VW_CUSTOMERS cust1, sales , TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype,  employee_txn emptxn "
				+ "where CUSTOMER_PAYER=? and CUSTOMER_PAYER=cust1.id_parent and CUSTOMER_PAYED is null "
				+ "and trx.transaction_id= sales.TRANSID and trx.txn_type_id= txntype.id_txn_type "
				+ "and trx.txn_status_id= txnstatus.id_txn_status and emptxn.transaction_id = trx.transaction_id "
				+ "order by TRANSACTION_DATE";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			int pinn = Integer.parseInt(payerPin);
			stmt.setInt(1, pinn);
			stmt.setInt(2, pinn);
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;

	}

	public List<TransactionDTO> getTransactionsByPayerForEmployee(String payerPin, String emp_id) {
		String empName = "";
		try {
			empName = getEmployee(emp_id).getEmployeeName();
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(MasaryManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = " select distinct trx.TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char(cust2.STR_DISPLAY_NAME) payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype,  employee_txn emptxn "
				+ " where CUSTOMER_PAYER=?" + " and CUSTOMER_PAYER=cust1.ID_CUSTOMER"
				+ " and  CUSTOMER_PAYED=cust2.ID_CUSTOMER" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  " + " union all "
				+ " select trx.TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char( sales.EXTRERNAL_ENTITY)  payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, sales"
				+ " , TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype,  employee_txn emptxn "
				+ " where CUSTOMER_PAYER=?" + " and CUSTOMER_PAYER=cust1.ID_CUSTOMER" + " and CUSTOMER_PAYED is null"
				+ " and trx.transaction_id= sales.TRANSID" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  " + " and emptxn.TRANSACTION_ID=trx.transaction_id"
				+ " and emptxn.EMPLOYEE_ID=?" + " order by TRANSACTION_DATE";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			int pinn = Integer.parseInt(payerPin);
			stmt.setInt(1, pinn);
			stmt.setInt(2, pinn);
			stmt.setInt(3, Integer.parseInt(emp_id));
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), empName, rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;

	}

	public List<TransactionDTO> getTransactionsByPayedAndDate(String payedPin, String dayFrom, String monthFrom,
			String yearFrom, String dayTo, String monthTo, String yearTo) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,cust2.STR_DISPLAY_NAME payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  "
				+ "from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype "
				+ "where CUSTOMER_PAYED=?"
				+ " and CUSTOMER_PAYER=cust1.ID_CUSTOMER and  CUSTOMER_PAYED=cust2.ID_CUSTOMER and trx.txn_type_id= txntype.id_txn_type "
				+ "and trx.txn_status_id= txnstatus.id_txn_status  "
				+ " and trx.transaction_date > to_date(?) and trx.transaction_date < to_date(?) "
				+ "order by TRANSACTION_ID";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(payedPin));
			stmt.setString(2, dayFrom + "-" + monthFrom + "-" + yearFrom);
			stmt.setString(3, dayTo + "-" + monthTo + "-" + yearTo);
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;

	}

	public List<TransactionDTO> getTransactionsByPayerAndDate(String payerPin, String dayFrom, String monthFrom,
			String yearFrom, String dayTo, String monthTo, String yearTo) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char(cust2.STR_DISPLAY_NAME) payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype "
				+ "where CUSTOMER_PAYER=?" + " and CUSTOMER_PAYER=cust1.ID_CUSTOMER"
				+ " and  CUSTOMER_PAYED=cust2.ID_CUSTOMER" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  "
				+ " and trx.transaction_date > to_date(?) and trx.transaction_date < to_date(?) " + " union all "
				+ " select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char( sales.EXTRERNAL_ENTITY)  payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, sales"
				+ " , TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype " + " where CUSTOMER_PAYER=?"
				+ " and CUSTOMER_PAYER=cust1.ID_CUSTOMER" + " and  CUSTOMER_PAYED is null"
				+ " and trx.transaction_id= sales.TRANSID" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  "
				+ " and trx.transaction_date > to_date(?) and trx.transaction_date < to_date(?) "
				+ " order by TRANSACTION_DATE";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(payerPin));
			stmt.setString(2, dayFrom + "-" + monthFrom + "-" + yearFrom);
			stmt.setString(3, dayTo + "-" + monthTo + "-" + yearTo);
			stmt.setInt(4, Integer.parseInt(payerPin));
			stmt.setString(5, dayFrom + "-" + monthFrom + "-" + yearFrom);
			stmt.setString(6, dayTo + "-" + monthTo + "-" + yearTo);
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<TransactionDTO> getTransactionsByPayerAndDateForEmployee(String payerPin, String emp_id, String dayFrom,
			String monthFrom, String yearFrom, String dayTo, String monthTo, String yearTo) {
		String empName = "";
		try {
			empName = getEmployee(emp_id).getEmployeeName();
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(MasaryManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select distinct trx.TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char(cust2.STR_DISPLAY_NAME) payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype,  employee_txn emptxn "
				+ "where CUSTOMER_PAYER=?" + " and CUSTOMER_PAYER=cust1.ID_CUSTOMER"
				+ " and  CUSTOMER_PAYED=cust2.ID_CUSTOMER" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  "
				+ " and trx.transaction_date > to_date(?) and trx.transaction_date < to_date(?) " + " union all "
				+ " select trx.TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char( sales.EXTRERNAL_ENTITY)  payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, sales"
				+ " , TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype,  employee_txn emptxn "
				+ " where CUSTOMER_PAYER=?" + " and CUSTOMER_PAYER=cust1.ID_CUSTOMER" + " and  CUSTOMER_PAYED is null"
				+ " and trx.transaction_id= sales.TRANSID" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  "
				+ " and trx.transaction_date > to_date(?) and trx.transaction_date < to_date(?) "
				+ " and emptxn.TRANSACTION_ID=trx.transaction_id" + " and emptxn.EMPLOYEE_ID=?"
				+ " order by TRANSACTION_DATE";

		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(payerPin));
			stmt.setString(2, dayFrom + "-" + monthFrom + "-" + yearFrom);
			stmt.setString(3, dayTo + "-" + monthTo + "-" + yearTo);
			stmt.setInt(4, Integer.parseInt(payerPin));
			stmt.setString(5, dayFrom + "-" + monthFrom + "-" + yearFrom);
			stmt.setString(6, dayTo + "-" + monthTo + "-" + yearTo);
			stmt.setInt(7, Integer.parseInt(emp_id));
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), empName, rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<TransactionDTO> getTransactionsByEmployeePayerAndDate(String payerPin, String dayFrom, String monthFrom,
			String yearFrom, String dayTo, String monthTo, String yearTo) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char(cust2.STR_DISPLAY_NAME) payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type "
				+ "from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype "
				+ "where CUSTOMER_PAYER=? and CUSTOMER_PAYER=cust1.id_parent and CUSTOMER_PAYED=cust2.id_parent "
				+ "and trx.txn_type_id= txntype.id_txn_type and trx.txn_status_id= txnstatus.id_txn_status  "
				+ "union all "
				+ "select trx.TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char( sales.EXTRERNAL_ENTITY)  payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  "
				+ "from  VW_CUSTOMERS cust1, sales , TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype,  employee_txn emptxn "
				+ "where CUSTOMER_PAYER=? and CUSTOMER_PAYER=cust1.id_parent and CUSTOMER_PAYED is null "
				+ "and trx.transaction_id= sales.TRANSID and trx.txn_type_id= txntype.id_txn_type "
				+ "and trx.txn_status_id= txnstatus.id_txn_status and emptxn.transaction_id = trx.transaction_id "
				+ " and trx.transaction_date > to_date(?) and trx.transaction_date < to_date(?) "
				+ "order by TRANSACTION_DATE";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(payerPin));
			stmt.setInt(2, Integer.parseInt(payerPin));
			stmt.setString(3, dayFrom + "-" + monthFrom + "-" + yearFrom);
			stmt.setString(4, dayTo + "-" + monthTo + "-" + yearTo);
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;

	}

	public List<TransactionDTO> getTodayTransactionsByPayer(String payerPin) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select * from  (select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char(cust2.STR_DISPLAY_NAME) payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype "
				+ "where CUSTOMER_PAYER=?" + " and CUSTOMER_PAYER=cust1.ID_CUSTOMER"
				+ " and  CUSTOMER_PAYED=cust2.ID_CUSTOMER" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  " + " union all "
				+ " select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char( sales.EXTRERNAL_ENTITY)  payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type  from  VW_CUSTOMERS cust1, sales"
				+ " , TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype " + " where CUSTOMER_PAYER=?"
				+ " and CUSTOMER_PAYER=cust1.ID_CUSTOMER" + " and  CUSTOMER_PAYED is null"
				+ " and trx.transaction_id= sales.TRANSID" + " and trx.txn_type_id= txntype.id_txn_type "
				+ " and trx.txn_status_id= txnstatus.id_txn_status  "
				+ " order by TRANSACTION_DATE desc ) where rownum < 6";

		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			int pinn = Integer.parseInt(payerPin);
			stmt.setInt(1, pinn);
			stmt.setInt(2, pinn);

			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<BillDTO> getMFIPill(String pin) {
		List<BillDTO> list = new ArrayList<BillDTO>();
		Connection conn = null;
		String sql = "select id,AMOUNT ,to_char( PILLMONTH,'mm/YYYY') MONTH ,STR_DISPLAY_NAME from  MFI_BILLS , VW_CUSTOMERS where  MFI_BILLS.ID_CUSTOMER="
				+ pin + " and ISPAYED=0 AND  MFI_BILLS.MFI_ID= VW_CUSTOMERS.ID_CUSTOMER ";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			BillDTO bill;
			ResultSet rs = null;
			rs = stmt.executeQuery();
			while (rs.next()) {
				bill = new BillDTO(rs.getString("id"), rs.getString("AMOUNT"), rs.getString("MONTH"),
						rs.getString("STR_DISPLAY_NAME"));
				list.add(bill);
			}
			rs.close();
		} catch (SQLException se) {
			logger.error(se.getMessage(), se);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}

		return list;
	}

	public AgentDTO getAgent(String pin) throws Exception {
		logger.info("getAgent with " + pin);
		AgentDTO agent = new AgentDTO();
		Gson gson = new Gson();
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  GETAGENTDTO(?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.CLOB);
			sqlStatement.setString(2, pin);
			sqlStatement.execute();
			Clob c = sqlStatement.getClob(1);
			agent = gson.fromJson(sqlStatement.getClob(1).getSubString(1, (int) c.length()), AgentDTO.class);
			logger.info("getAgent getServices().size() " + agent.getServices().size());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(e.getStackTrace());
			throw (e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return agent;
	}

	public EmployeeDTO getEmployee(String pin) throws Exception {
		logger.info("getEmployee with " + pin);
		Connection conn = null;
		String sql = "select ID_CUSTOMER, MSISDN, STR_DISPLAY_NAME, "
				+ "STR_DISPLAY_NAME_ARABIC,ID_PARENT,SHOW_MY_BALANCE,"
				+ "TRANSFER_2_SAME,REP_ALLOWED_BAL,CURRENT_BALANCE " + "from  VW_CUSTOMERS" + " where ID_CUSTOMER = ?";
		EmployeeDTO employee = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pin);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				employee = new EmployeeDTO();
				employee.setEmployeeID(rs.getString("ID_CUSTOMER"));
				employee.setEmployeeName(rs.getString("STR_DISPLAY_NAME"));
				employee.setEmployeeArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
				employee.setEmployeePhone(rs.getString("MSISDN"));
				employee.setParentId("ID_PARENT");
				employee.setShowMyBalance(rs.getInt("SHOW_MY_BALANCE") == 1);
				employee.setShowTransfer(rs.getInt("TRANSFER_2_SAME") == 1);
				employee.setAllowedBalance(rs.getDouble("REP_ALLOWED_BAL"));
				employee.setCurBalance(rs.getDouble("CURRENT_BALANCE"));
			} else {
				throw new Exception("Wallet doesn't exist");
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.error(e.getStackTrace());
			throw new Exception("Error in getting agent data");
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return employee;
	}

	public CustomerDTO getCustomerInfo(String customerID) throws Exception {
		logger.info("getCustomerInfo with " + customerID);
		Connection conn = null;
		String sql = "select ID_CUSTOMER, MSISDN, STR_DISPLAY_NAME, "
				+ "STR_DISPLAY_NAME_ARABIC,ID_PARENT,CURRENT_BALANCE " + "from  CUSTOMERS" + " where ID_CUSTOMER = ?";
		CustomerDTO customer = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				customer = new CustomerDTO();
				customer.setCustomerID(rs.getInt("ID_CUSTOMER"));
				customer.setCustomerName(rs.getString("STR_DISPLAY_NAME"));
				customer.setCustomerArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
				customer.setCustomerPhone(rs.getString("MSISDN"));
				customer.setParentId(rs.getInt("ID_PARENT"));
				customer.setCurrentBalance(rs.getDouble("CURRENT_BALANCE"));
			} else {
				throw new Exception("Wallet doesn't exist");
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.error(e.getStackTrace());
			throw new Exception("Error in getting agent data");
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return customer;
	}

	public AgentDTO getAgent_V(String pin) throws Exception {
		logger.info("getAgent_V with " + pin);
		Connection conn = null;
		String sql = "select c.ID_CUSTOMER," + "c.BOL_IS_ACTIVE," + "c.MSISDN," + "c.STR_DISPLAY_NAME,"
				+ "c.STR_DISPLAY_NAME_ARABIC," + "c.BOL_IS_AUTO_ALLOCATE," + "c.CAN_ADD_EMPLOYEE,"
				+ "trunc(c.current_balance,2) as balance," + "c.SALES_REP_COUNT as reps_count,"
				+ "nvl(csGrp.group_id,-1) g " + "from  CUSTOMERS c left outer join  customer_group csGrp "
				+ "on c.ID_CUSTOMER=csGrp.customer_id where c.ID_CUSTOMER = ? ";
		AgentDTO agent = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(pin));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				agent = new AgentDTO();
				agent.setPin(rs.getString("ID_CUSTOMER"));
				agent.setName(rs.getString("STR_DISPLAY_NAME"));
				agent.setArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
				agent.setBalance(rs.getDouble("BALANCE"));
				agent.setPhone(rs.getString("MSISDN"));
				agent.setAutoAllocate(rs.getString("BOL_IS_AUTO_ALLOCATE"));
				agent.setIsSupervisor(rs.getInt("reps_count"));
				agent.setGroupId(rs.getInt("g"));
			} else {
				throw new Exception("Error in getting agent data");
			}
			rs.close();
			closeStatement(stmt);
			sql = "select s.ID_SERVICE, s.STR_SERVICE_NAME, trunc(cs.SERVICE_BALANCE,2), s.price, s.STR_SERVICE_NAME_AR, s.allow_auto_allocate, s.is_voucher, s.is_bill "
					+ "from  services s,  customer_services cs "
					+ "where s.id_service= cs.service_id and s.id_service in(?,?,?,?)and cs.CUSTOMER_ID=? "
					+ "order by cs.service_id";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 15);
			stmt.setInt(2, 16);
			stmt.setInt(3, 17);
			stmt.setInt(4, 31);
			stmt.setInt(5, Integer.parseInt(agent.getPin()));
			rs = stmt.executeQuery();
			CustomerServiceDTO service;
			while (rs.next()) {
				service = new CustomerServiceDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				agent.addService(service);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			logger.error(e.getStackTrace());
			throw new Exception("Error in getting agent data");
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return agent;
	}

	public boolean isExistInMasary(String msisdn) {
		logger.info("isExistInMasary with " + msisdn);
		boolean result = false;
		Connection conn = null;
		String sql = "select ID_CUSTOMER from  CUSTOMERS where MSISDN=?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, msisdn);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
			logger.error(ex.getStackTrace());

		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return result;
	}

	public int getAllowedServiceBalance(String custID, int serviceID) {
		logger.info("getAllowedServiceBalance with " + custID + " " + serviceID);
		int result = 0;
		Connection conn = null;
		String sql = "select allowed_bal from  vw_customer_services where customer_id=? and service_id=?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(custID));
			stmt.setInt(2, serviceID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return result;
	}

	public boolean isEmployee(String custID) {
		logger.info("isEmployee with " + custID);
		boolean result = false;
		Connection conn = null;
		String sql = "select role_id from  web_users where id=?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(custID));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) == 8) {
					result = true;
				}
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return result;
	}

	public String[] customerBalances(String pin) {
		logger.info("customerBalances with " + pin);
		String[] results = new String[4];
		CallableStatement sqlStatement = null;
		ResultSet cusBalances;
		Connection conn = null;
		String callSQL = "{ call ? :=  Customer_Balances(?) }";
		try {
			conn = getConnection();
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(2, pin);
			sqlStatement.registerOutParameter(1, OracleTypes.CURSOR);
			sqlStatement.execute();
			cusBalances = (ResultSet) sqlStatement.getObject(1);
			int i = 0;
			while (cusBalances.next()) {
				results[i] = cusBalances.getString("Services_Balance");
				i++;
			}
			cusBalances.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return results;
	}

	public TransactionDTO getTransaction(String transId) throws Exception {
		logger.info("getTransaction with " + transId);
		Connection conn = getConnection();
		TransactionDTO transDTO = null;
		CallableStatement sqlStatement = null;
		try {
			String callSQL = "{call  getTransaction(?,?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(1, transId);
			sqlStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			sqlStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
			sqlStatement.registerOutParameter(4, java.sql.Types.NUMERIC);
			sqlStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
			sqlStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			sqlStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
			sqlStatement.execute();
			transDTO = new TransactionDTO(transId, sqlStatement.getString(2), sqlStatement.getString(3),
					sqlStatement.getDouble(4), sqlStatement.getString(5), sqlStatement.getString(6),
					sqlStatement.getString(7), 0);

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return transDTO;
	}

	public int acceptedTransacrion(String phoneNumber, String amount, String customerID) throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= ACCEPTED_TXN(PHONE => ?,AMOUNT => ?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			sqlStatement.setString(2, phoneNumber);
			sqlStatement.setString(3, amount);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int payBill(String custID, String billId) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		int transID = -10;
		int balance = -1;
		try {
			conn = getConnection();
			String callSQL = "{call  PAYBILL( ? ,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
			sqlStatement.registerOutParameter(4, java.sql.Types.NUMERIC);
			sqlStatement.setString(1, custID);
			sqlStatement.setString(2, billId);
			sqlStatement.execute();
			transID = sqlStatement.getInt(3);
			balance = sqlStatement.getInt(4);
		} catch (SQLException se) {
			logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return transID;
	}

	public int resetBalance(String parentID, String agentId) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= RESETBALANCE(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			sqlStatement.setString(2, agentId);
			sqlStatement.setString(3, parentID);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public void setAgentStatus(String agentId, boolean status) throws SQLException {
		String active = status ? "Y" : "N";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "update  CUSTOMERS set BOL_IS_ACTIVE = ? where ID_CUSTOMER = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, active);
			stmt.setString(2, agentId);
			stmt.execute();
		} catch (SQLException se) {
			logger.error(se);
			throw se;
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		if (active.equals("N")) {
			conn = getConnection();
			stmt = null;
			try {
				String sql = "update CC.CUSTOMER_DATA set DISABLED_REASON = 'المحفظة موقوفة عن طريق المسؤل' where ID_CUSTOMER = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, agentId);
				stmt.execute();
			} catch (SQLException se) {
				logger.error(se);
				throw se;
			} finally {
				closeStatement(stmt);
				closeConnection(conn);
			}
		}
	}

	public void setServiceEnabledOrDisabled(String serviceID, int staus) {

		CallableStatement sqlStatement = null;
		Connection conn = null;

		try {
			conn = getConnection();

			String callSQL = "{call ? :=  SERVICES_ENABLE_DISABLE(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, serviceID);
			sqlStatement.setInt(3, staus);
			sqlStatement.execute();
		} catch (Exception ex) {
			MasaryManager.logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public void setRoles(String custID, List<String> roles) {
		for (int i = roles.size() - 1; i >= 0; i--) {
			setRole(custID, roles.get(i));
		}

	}

	public TransactionInfoDTO transferCredit(String custID, String msisdn, String amount, String lang)
			throws Exception {
		return USSDEtisalatManager.getInstance().transferCredit(custID, msisdn, amount, lang);
	}

	public String login(String id, String orgin) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= login(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.registerOutParameter(4, OracleTypes.DATE);
			sqlStatement.setString(2, id);
			sqlStatement.setString(3, orgin);
			sqlStatement.execute();
			return sqlStatement.getString(1);
		} catch (SQLException se) {
			logger.error(se);
			return "error";
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String resetPasword(String msisdn, String question, String answer) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ? :=  reset_password(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, msisdn);
			sqlStatement.setString(3, question);
			sqlStatement.setString(4, answer);
			sqlStatement.execute();
			return sqlStatement.getString(1);
		} catch (SQLException se) {
			logger.error(se);
			return "error";
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String getAllBalances() {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call  GETMODEMLOGS(?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.execute();
			return sqlStatement.getString(1);

		} catch (SQLException se) {
			logger.error(se);
			return "error";
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public void logout(String id) {
		if (id == null) {
			return;
		}
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call  logout( ?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(1, id);
			sqlStatement.execute();
		} catch (SQLException se) {
			logger.error(se);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public SellVoucherResponse getVoucher(String customerID, String transID) {
		SellVoucherResponse sellVoucherResponse = new SellVoucherResponse();
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		ArrayList<String> voucherPinList = new ArrayList<String>();
		ArrayList<String> voucherSerialList = new ArrayList<String>();
		BulkVoucherDTO voucherInfo = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			String sql = "select  PBXMOB.cryptit.decrypt(v.str_vaoucher)str_vaoucher,sn,vp.str_name,vv.id_v_providers_value\n"
					+ "from  PBXMOB.vouchers v, PBXMOB.v_providers_values vv , PBXMOB.v_providers vp, PBXMOB.TRANSACTIONS tr\n"
					+ "where v.TRANSID = ? and tr.TRANSACTION_ID = v.TRANSID\n"
					+ "and vv.id_v_providers = v.id_v_providers and vv.id_v_providers_values = v.id_v_providers_values\n"
					+ "and vp.id_v_providers=v.id_v_providers and tr.CUSTOMER_PAYER=?";
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setString(1, transID);
			sqlStatement.setString(2, customerID);
			rs = sqlStatement.executeQuery();
			while (rs.next()) {
				voucherInfo = new BulkVoucherDTO();
				voucherInfo.setService_Name(rs.getString("str_name"));
				voucherInfo.setDenom(rs.getDouble("id_v_providers_value"));
				sellVoucherResponse.setTransId(transID);
				voucherPinList.add(rs.getString("str_vaoucher"));
				voucherSerialList.add(rs.getString("sn"));
			}
			sellVoucherResponse.setVoucherInfo(voucherInfo);
			sellVoucherResponse.setVoucherPin(voucherPinList);
			sellVoucherResponse.setVoucherSerial(voucherSerialList);
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return sellVoucherResponse;
	}

	public void updateVoucher(String transID) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String sql = "update  vouchers set PRINTED_VERSION_NO = ? where TRANSID = ? ";
			sqlStatement = conn.prepareCall(sql);
			sqlStatement.setInt(1, 2);
			sqlStatement.setString(2, transID);
			sqlStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String transferCustomerTopUpAsService(String custID, String msisdn, String amount, String senderTime,
			String senderTrxID) throws Exception {
		return USSDEtisalatManager.getInstance().transferCustomerTopUpAsService(custID, msisdn, amount, senderTime,
				senderTrxID);
	}

	public int updateUser(String id, String password, String sQ, String answer, int sessionTime, String oldPassword)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			System.out.println("password" + password);
			String callSQL = "{call ?:= UPDATE_CUSTOMER_DETAILS(?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, id);
			sqlStatement.setString(3, password);
			sqlStatement.setString(4, sQ);
			sqlStatement.setString(5, answer);
			sqlStatement.setInt(6, sessionTime);
			sqlStatement.setString(7, oldPassword);

			sqlStatement.execute();
			return sqlStatement.getInt(1);

			// if (sqlStatement.getInt(1) == 1) {
			// logger.info("Customer " + id + "Updated Successfully ");
			// }
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage().contains("ORA-20139")) {
				throw new Exception(e.getMessage());
			} else {
				throw new Exception(e.getMessage());
			}
		} catch (Exception ex) {
			logger.info("Error in update details For Customer " + id + " " + ex);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return 0;
	}

	public String resetEmployeePassword(String id) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		String password = null;

		try {
			conn = getConnection();
			Random generator = new Random();
			int tempPass = generator.nextInt(900000) + 100000;
			password = Integer.toString(tempPass);
			String sql = "update  CUSTOMERS set password= encrypt(?), UPDATECHK = 1 where ID_CUSTOMER = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setString(2, id);
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}

		return password;
	}

	public void updateUserAdmin(String id, String password) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sql = "update  CUSTOMERS set password =  encrypt(?) where ID_CUSTOMER = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, password);
			stmt.setString(2, id);
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public void updateUserBalance(String id, String balance, int change) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call  UPDATE_BALANCE(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(1, id);
			sqlStatement.setString(2, balance);
			sqlStatement.setInt(3, change);
			sqlStatement.execute();
		} catch (SQLException se) {
			logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public double getCash(String serviceId, String amount) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ? :=  GET_CASH_VALUE(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, serviceId);
			sqlStatement.setString(3, amount);
			sqlStatement.execute();
			return sqlStatement.getDouble(1);
		} catch (SQLException se) {
			logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String sendSMS(String phoneNumber, String body) {
		if (phoneNumber.length() < 10) {
			logger.info("Send SMS to " + phoneNumber + " response No Phone");
			return "No Phone";
		}
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= SEND_SMS(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, phoneNumber);
			sqlStatement.setString(3, body);
			sqlStatement.execute();
			logger.info("Send SMS to " + phoneNumber + " response " + sqlStatement.getString(1));
			return sqlStatement.getString(1);
		} catch (Exception ex) {
			logger.debug(ex);
			return "-1";
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String sendWab(String phoneNumber) {
		return sendSMS(phoneNumber, "Wap url www.e-masary.net" + CONFIG.APP_ROOT);
	}

	public void sendSMSReport(final long transID, final String amount, final String customerPayerPin,
			final String customerPayedPin, final boolean isMobile) {
		if (!isMobile) {
			new Thread(new Runnable() {
				private String phone = null;
				private String name;
				private String balance;
				DecimalFormat myFormatter = new DecimalFormat("###,###.### EGP");

				public void run() {
					try {
						StringBuffer sms = new StringBuffer();
						AgentDTO agentPayer = getAgent(customerPayerPin);
						AgentDTO agentPayed = getAgent(customerPayedPin);
						phone = agentPayed.getPhone();
						name = agentPayer.getName();
						balance = myFormatter.format(agentPayed.getBalance());
						if (phone != null) {
							sms.append("Your account has been recharged From : ");
							sms.append(name);
							sms.append(" With ");
							sms.append(myFormatter.format(Double.parseDouble(amount)));
							sms.append(". Your new balance is ");
							sms.append(balance);
							sms.append(" Trans#:");
							sms.append(transID);
							MasaryManager.getInstance().sendSMS(phone, sms.toString());
						}
					} catch (Exception ex) {
						logger.error(ex);
					}
				}
			}).start();
		}
	}

	private void setRole(String custID, String role) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call  SETROlE(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(1, custID);
			sqlStatement.setString(2, role);
			sqlStatement.execute();
		} catch (SQLException ex) {
			logger.debug(ex);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int saveEtisalatTrans(String payedId, String payerID, String amount, String operationID, int trxType)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  saveEtisalatTransaction(?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, payedId);
			sqlStatement.setInt(3, Integer.parseInt(payerID));
			sqlStatement.setFloat(4, Float.parseFloat(amount));
			sqlStatement.setInt(5, Integer.parseInt(operationID));
			sqlStatement.setInt(6, trxType);
			sqlStatement.execute();
			int transId = sqlStatement.getInt(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You do not have enough balance ");
			}

			return transId;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int saveEtisalatTrans2(String payedId, String payerID, String amount, String operationID, int trxType)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  saveEtisalatTransaction2(?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, payedId);
			sqlStatement.setInt(3, Integer.parseInt(payerID));
			sqlStatement.setFloat(4, Float.parseFloat(amount));
			sqlStatement.setInt(5, Integer.parseInt(operationID));
			sqlStatement.setInt(6, trxType);
			sqlStatement.execute();
			int transId = sqlStatement.getInt(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You do not have enough balance ");
			}

			return transId;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int saveNTCCTrans(String payedId, String payerID, String amount, String operationID, int trxType)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  SAVENTCCTRANSACTION('" + payedId + "'," + payerID + "," + amount + ","
					+ operationID + "," + trxType + ") }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.execute();
			int transId = sqlStatement.getInt(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You do not have enough balance ");
			}
			return transId;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String getNTCCBalance() throws IOException {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new URL("http://62.135.17.130:8888/NTCC_nbClient/WS?un=ms&ps=masary&ac=getMbal&an=ghgjhgm")
							.openConnection().getInputStream()));
			return bufferedReader.readLine();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return "";
		} finally {
			bufferedReader.close();
		}
	}

	public void writeLog(String ip, String uri, String msg) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "INSERT INTO  LOGS (REQUEST, REQUEST_IP, RESPONSE, MODULE) VALUES (?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uri);
			stmt.setString(2, ip);
			stmt.setString(3, msg);
			stmt.setInt(4, 1);
			stmt.execute();
			conn.commit();
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public String transferMobinilTopUpAsService(String userid, String msisdn, String amount, String senderTime,
			String senderTrxID) throws Exception {
		return USSDMobinilManager.getInstance().transferMobinilCustomerTopUpAsService(userid, msisdn, amount,
				senderTime, senderTrxID);
	}

	public String transferIBTTopUpAsService(String userid, String msisdn, String amount, String oCountry,
			String dCountry) throws Exception {
		logger.info("IBT Topup Service " + msisdn + " amount " + amount + " from " + oCountry + " to " + dCountry
				+ " ID " + userid);
		Connection con = null;
		PreparedStatement stmt = null;
		String isBlocked = null;
		try {
			con = getConnection();
			String sql = "select is_blocked from  topup_country where country_ac = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, oCountry);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				isBlocked = rs.getString(1);
			}
			rs.close();
			if (isBlocked == null) {
				return "-110 " + oCountry + " country is not listed in Masary system";
			} else if (isBlocked.equals("Y")) {
				return "-111 " + "This country is blocked through Masary system";
			} else {
				String res = "XXX " + "Successful transaction from " + oCountry + " to " + dCountry + " to charge "
						+ msisdn;
				return res;
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw (ex);
		} finally {
			closeStatement(stmt);
			closeConnection(con);
		}
	}

	int saveMobinilTrans(String msisdn, String custID, String amount, String operationId, int trxType)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  saveMobinilTransaction(?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, msisdn);
			sqlStatement.setInt(3, Integer.parseInt(custID));
			sqlStatement.setFloat(4, Float.parseFloat(amount));
			sqlStatement.setInt(5, Integer.parseInt(operationId));
			sqlStatement.setInt(6, trxType);
			sqlStatement.execute();
			int transId = sqlStatement.getInt(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You do not have enough balance ");
			}

			return transId;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String getParentOfUser(String pin) {
		String res = "";
		Connection conn = null;
		String sql = "select ID_PARENT from  customers where ID_CUSTOMER=?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(pin));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				res = rs.getString(1);
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return res;
	}

	int saveVodafoneTrans(String msisdn, String custID, String amount, String operationId, int trxType)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  saveVodafoneTransaction(?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, msisdn);
			sqlStatement.setInt(3, Integer.parseInt(custID));
			sqlStatement.setFloat(4, Float.parseFloat(amount));
			sqlStatement.setInt(5, Integer.parseInt(operationId));
			sqlStatement.setInt(6, trxType);

			sqlStatement.execute();
			int transId = sqlStatement.getInt(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You do not have enough balance ");
			}

			return transId;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	int saveAsynchronousTransaction(String msisdn, String custID, String amount, String operationId, int trxType)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  SAVE_asynchronous_TRANSACTION('" + msisdn + "'," + custID + "," + amount
					+ "," + operationId + "," + trxType + ") }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.execute();
			int transId = sqlStatement.getInt(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You do not have enough balance ");
			}

			return transId;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String transferVodafoneTopUpAsService(String userid, String msisdn, String amount, String senderTime,
			String senderTrxID) throws Exception {
		return USSDVodafoneManager.getInstance().transferVodafoneCustomerTopUpAsService(userid, msisdn, amount,
				senderTime, senderTrxID);
	}

	public String refund(String transId, String custId) throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  REFUND(?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, transId);
			sqlStatement.setString(3, custId);
			sqlStatement.execute();
			transId = sqlStatement.getString(1);
			if (transId.equals("-1")) {
				throw new Exception("This transaction already refunded");
			}
			return transId;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public void joinService(int custId, String serviceId, int amount) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:=  JOIN_SERVICE(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			sqlStatement.setInt(2, custId);
			sqlStatement.setString(3, serviceId);
			sqlStatement.execute();
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public CustomerServiceDTO getService(int serviceId, List<CustomerServiceDTO> serviceList) {
		for (CustomerServiceDTO service : serviceList) {
			if (service.getServiceID() == serviceId) {
				return service;
			}
		}
		return null;
	}

	public ServiceDTO getService(String serviceId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select  id_Service, STR_Service_NAME,price,STR_SERVICE_NAME_AR from  Services where id_Service = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, serviceId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new ServiceDTO(Integer.parseInt(serviceId), rs.getString(2), rs.getDouble(3), rs.getString(4));
			}
			rs.close();
			return null;
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return null;
	}

	public void assignServiceBalance(String custId, String serviceId, String amount, int fromAdmin) throws Exception {
		int result;
		Connection conn = null;
		CallableStatement sqlStatement = null;

		try {
			if (amount.isEmpty() || Double.parseDouble(amount) < 0) {
				throw new Exception("Not Valid Amount");
			}

			conn = getConnection();
			String callSQL = "{call ?:=  ADD_BALANCE_TO_SERVICE(?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setInt(2, Integer.parseInt(custId));
			sqlStatement.setInt(3, Integer.parseInt(serviceId));
			sqlStatement.setDouble(4, Double.parseDouble(amount));
			sqlStatement.setInt(5, fromAdmin);
			sqlStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			sqlStatement.execute();
			result = sqlStatement.getInt(1);
			if (result == -2) {
				throw new Exception("You don't have enough balance");
			}
			if (result == -3) {
				throw new Exception("You can't assign all this amount");
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public void assignVoucherToCustomer(HttpSession session, int serviceId, double value, int amount, int custId,
			int Cust_From) throws Exception {
		CustomerDTO customer = MasaryManager.getInstance().getCustomerInfo(String.valueOf(custId));
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			if (amount < 0) {
				MasaryManager.logger.info("Assign Voucher To Customer Result : Error Amount Must be Positive");
				throw new Exception("Not Valid Amount");
			}

			conn = getConnection();
			String callSQL = "{call ?:=  ASSIGN_VOUCHER_TO_CUSTOMER(?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			sqlStatement.setInt(2, Cust_From);
			sqlStatement.setInt(3, custId);
			sqlStatement.setInt(4, serviceId);
			sqlStatement.setDouble(5, value);
			sqlStatement.setInt(6, amount);
			sqlStatement.execute();
			if (sqlStatement.getInt(1) == 1) {
				MasaryManager.logger.info("Assign Voucher To Customer Result : You Assign " + amount + " "
						+ getService(String.valueOf(serviceId)).getStrServiceName(session) + " " + value + " "
						+ "To Customer " + " " + custId);
				throw new Exception(CONFIG.getAssignVoucherResultPart1(session) + amount + " "
						+ getService(String.valueOf(serviceId)).getStrServiceName(session) + " " + value + " "
						+ CONFIG.getAssignVoucherResultPart2(session) + " " + customer.getCustomerName(session));
			}
			if (sqlStatement.getInt(1) == -2) {
				MasaryManager.logger.info("Assign Voucher To Customer Result : Error You don't have enough balance");
				throw new Exception("You don't have enough balance");
			}
			if (sqlStatement.getInt(1) == -3) {
				MasaryManager.logger.info("Assign Voucher To Customer Result : Error You can't assign all this amount");
				throw new Exception("You can't assign all this amount");
			}
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public long addBalanceToAgentService(String payerId, String payedId, String serviceId, String amount)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		long transId;
		try {
			if (amount.isEmpty() || Integer.parseInt(amount) < 0) {
				throw new Exception("Not Valed Amount");
			}

			conn = getConnection();
			String callSQL = "{call ?:=  ADD_BALNCE_TO_AGENT_SERVICE( ? , ? , ? , ?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(2, payerId);
			sqlStatement.setString(3, payedId);
			sqlStatement.setString(4, serviceId);
			sqlStatement.setString(5, amount);
			sqlStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			sqlStatement.execute();
			transId = sqlStatement.getLong(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You don't have enough balance");
			}
			if (transId == -4) {
				throw new Exception("Customer " + payedId + " is not registered with this service");
			}

			logger.info(("Service " + serviceId + " trans from " + payerId + " to " + payedId + " amount " + amount));
			return transId;
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public static String wapIdentity[] = { "cldc", "j2me", "midp", "symbos", "wapbrowser", "up.browser", "up/4", "mib",
			"cellphone", "go.web", "nokia", "panasonic", "wap", "wml-browser", "wml", "opera mobi" };

	public boolean isTheRequestFromMobile(String requestAgent) {
		logger.info(requestAgent);
		requestAgent = requestAgent.toLowerCase();
		for (String string : wapIdentity) {
			if (requestAgent.contains(string)) {
				return true;
			}
		}
		return false;
	}

	public boolean ValidatePhoneNumber(String phNumber) {
		boolean valResult = false;
		String numPattern;
		if (phNumber.length() == 11
				&& (phNumber.startsWith("010") || phNumber.startsWith("011") || phNumber.startsWith("012"))) {
			numPattern = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
			valResult = phNumber.matches(numPattern) || phNumber.matches(numPattern);
		}
		return valResult;
	}

	public boolean ValidateTopupType(String msisdn, int topupType) {
		switch (topupType) {
		case 7:
			return msisdn.startsWith("011");
		case 9:
			return msisdn.startsWith("012");
		case 11:
			return msisdn.startsWith("010");
		case 12:
			return true;
		case 14:
			return msisdn.startsWith("0");
		default:
			return true;
		}
	}

	public void addVouchers(String vouchers, String providerId, String groups, String ownerId) throws Exception {
		VoucherManger.getInstance().addVouchers(vouchers, providerId, groups, ownerId);
	}

	public long saveVoucherTrans(String msisdn, String custID, String amount, String operationId, int trxType)
			throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  saveVOUCHER2MTransaction(?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, msisdn);
			sqlStatement.setInt(3, Integer.parseInt(custID));
			sqlStatement.setDouble(4, Double.parseDouble(amount));
			sqlStatement.setInt(5, Integer.parseInt(operationId));
			sqlStatement.setInt(6, trxType);
			sqlStatement.execute();
			long transId = sqlStatement.getLong(1);
			if (transId == -1) {
				throw new Exception("Amount must be grater than 0");
			}
			if (transId == -2) {
				throw new Exception("Customer not found");
			}
			if (transId == -3) {
				throw new Exception("You don't have enough balance ");
			}

			return transId;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public List<ProviderDTO> getVoucherProviders() {
		return VoucherManger.getInstance().getVoucherProviders();
	}

	public List<AvilableVouchersDTO> getAvilableVouchersDTO(String userId) {
		return VoucherManger.getInstance().getAvilableVouchersDTO(userId);
	}

	public int isTrusted(String ip) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  IS_TRUSTED(?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(2, ip);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return 0;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String getAgentID(String msisdn) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select id_customer from  CUSTOMERS where msisdn = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, msisdn);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			rs.close();
			return "";
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return "";
	}

	public List<String> getLast10Numbers(String custId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		List<String> lastNumbers = new ArrayList();
		try {
			conn = getConnection();
			String sql = "select msisdn from (select nvl(customer_payed,extrernal_entity) msisdn,count(*) from  transactions left outer join   sales on transaction_id=transid where  customer_payer="
					+ custId + " group by nvl(customer_payed,extrernal_entity) order by 2 desc) where rownum<11";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lastNumbers.add(rs.getString(1));
			}
			rs.close();
			return lastNumbers;
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return lastNumbers;
	}

	public Map<ServiceDTO, Integer> getDailyTransactions(String agentId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		Map serviceTransactions = new HashMap<ServiceDTO, Integer>();
		try {
			conn = getConnection();
			String sql = "select count(*),id_service,str_service_name,str_service_name_ar from  transactions, services where customer_payer=? and transaction_date>=to_date(to_char(sysdate,'yyyy-mm-dd')||'03:00','yyyy-mm-dd hh24:mi') and id_service=txn_type_id  group by id_service, str_service_name, str_service_name_ar  order by id_service";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(agentId));
			ResultSet rs = stmt.executeQuery();
			ServiceDTO service = null;
			Integer count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
				service = new ServiceDTO(rs.getInt(2), rs.getString(3), 0, rs.getString(4));
				serviceTransactions.put(service, count);
			}
			rs.close();
			return serviceTransactions;
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return null;
	}

	public boolean ValidateTopupAmount(int topupType, String amount) {
		if (!(topupType == 6 || topupType == 8 || topupType == 10)) {
			return true;
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select count(*) from  OPERATOR_DENOMINATION where OPERATO_ID=? and AMOUNT=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, topupType);
			stmt.setFloat(2, Float.parseFloat(amount));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
			rs.close();
			return false;
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return false;
	}

	public void changeServicePrice(String serviceId, String newPrice) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sql = "update  services set price=" + newPrice + " where ID_SERVICE=" + serviceId;
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public boolean autoAllocate(AgentDTO agent, int serviceId, String amount) throws Exception {
		if (agent.isAutoAllocate().equals("N")) {
			return false;
		}
		if (agent.getServiceBalance(1) < Double.parseDouble(amount)) {
			return false;
		}
		assignServiceBalance(agent.getPin(), String.valueOf(serviceId), amount, 0);
		agent = getAgent(agent.getPin());
		return agent.getServiceBalance(serviceId) >= Double.parseDouble(amount);

	}

	public boolean autoAllocate(AgentDTO agent, int serviceId, int amount) throws Exception {
		if (agent.isAutoAllocate().equals("N")) {
			return false;
		}
		if (agent.getServiceBalance(1) < amount) {
			return false;
		}
		assignServiceBalance(agent.getPin(), String.valueOf(serviceId), String.valueOf(amount), 0);
		agent = getAgent(agent.getPin());
		return agent.getServiceBalance(serviceId) >= amount;
	}

	public List<AgentDTO> getRepsForSupervisor(String agentId) {
		List<AgentDTO> list = new ArrayList<AgentDTO>();
		Connection conn = null;
		String sql = null;
		sql = "select ID_CUSTOMER,STR_DISPLAY_NAME,STR_DISPLAY_NAME_ARABIC,BOL_IS_ACTIVE"
				+ " from  CUSTOMERS , TEMP_SUPERVISORS " + "where ID_SUPERVISOR=? AND ID_CUSTOMER=ID_REP order by 1 ";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(agentId));

			ResultSet rs = stmt.executeQuery();
			AgentDTO agent;
			while (rs.next()) {
				agent = new AgentDTO();
				agent.setPin(rs.getString("ID_CUSTOMER"));
				agent.setName(rs.getString("STR_DISPLAY_NAME"));
				agent.setArabicName(rs.getString("STR_DISPLAY_NAME_ARABIC"));
				agent.setActive(rs.getString("BOL_IS_ACTIVE").equals("Y") ? true : false);
				list.add(agent);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public void saveDynamicsTXN(String customerID, String accountID, String masaryTXN, String dynamicsTXN) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "INSERT INTO  DYNAMICS_OPERATIONS values (?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(customerID));
			stmt.setString(2, accountID);
			stmt.setInt(3, Integer.parseInt(masaryTXN));
			stmt.setString(4, dynamicsTXN);
			stmt.execute();
			conn.commit();
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public String receiveSMS(final String msisdn, String text, String encoding, String operatorId, String shortCode)
			throws Exception {
		MasaryManager.getInstance().logger.info("Receive SMS from MSISDN=" + msisdn + " text=" + text + " encoding="
				+ encoding + " operator=" + operatorId);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "INSERT INTO  RECEIVED_SMS  (MSISDN, TEXT, ENCODING, OPERATOR,SHORTCODE) VALUES (?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, msisdn);
			stmt.setString(2, text);
			stmt.setString(3, encoding);
			stmt.setString(4, operatorId);
			stmt.setString(5, shortCode);
			stmt.execute();
			conn.commit();
			return "Done";
		} catch (Exception ex) {
			logger.debug(ex);
			throw ex;
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public void addAuthenticationFailure(String userName) {
		logger.info("Add authentication failure to " + userName);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "update   customers set INT_WRONG_CREDENTIALS=INT_WRONG_CREDENTIALS+1 where MSISDN = ? or ID_CUSTOMER = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, userName);
			stmt.execute();
			conn.commit();
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public void removeAuthenticationFailure(String userName) {
		logger.info("Add authentication failure to " + userName);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "update   customers set INT_WRONG_CREDENTIALS=0 where MSISDN = ? or ID_CUSTOMER = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, userName);
			stmt.execute();
			conn.commit();
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public void sendSync() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				logger.info("Send Sync from admin");
				Connection conn = null;
				CallableStatement sqlStatement = null;
				try {
					conn = getConnection();
					String callSQL = "{ call  SEND_BALANCE_MAIL() }";
					sqlStatement = conn.prepareCall(callSQL);
					sqlStatement.execute();

				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				} finally {
					closeStatement(sqlStatement);
					closeConnection(conn);
				}
			}
		}).start();
	}

	public void updateTransType(String transId, String serviceId) {
		logger.info("Change transcation to  " + serviceId);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "update   transactions set TXN_TYPE_ID = ? where TRANSACTION_ID = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, serviceId);
			stmt.setString(2, transId);
			stmt.execute();
			conn.commit();
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public boolean validateToneKey(String key) {
		if (key == null) {
			return false;
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select WEB_KEY_VALID  from CARINA.SERIAL where WEB_KEY_VALID=1 and  WEB_KEY = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, key);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				sql = "update CARINA.SERIAL set WEB_KEY_VALID=0 where WEB_KEY = ? ";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, key);
				stmt.execute();
				conn.commit();
				return true;
			}
			rs.close();
			return false;
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return true;
	}

	public void updateTransactionStatus(int transId, int status) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call  update_TRANSACTION_status( ? , ?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setInt(1, transId);
			sqlStatement.setInt(2, status);
			sqlStatement.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int healthycheck() {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = getConnection();
			String sql = "select ID_CUSTOMER from  CUSTOMERS where  ID_CUSTOMER = 4";
			sqlStatement = conn.prepareStatement(sql);
			rs = sqlStatement.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
			rs.close();
			return x;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return -1;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public List<Masary_Biller> getbiller(int pin) throws Exception {
		List<Masary_Biller> billers = new ArrayList<Masary_Biller>();
		Connection conn = null;
		String sql = "select biller_id,biller_name,biller_name_ar from  masary_biller where active='Y' "
				+ "and biller_id in(select biller_id from  services where id_service in(select service_id "
				+ "from  customer_services where customer_id=?)and id_service not in "
				+ "(select bill_type_id from  masary_bill_types)) order by biller_id";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pin);
			ResultSet rs = stmt.executeQuery();
			Masary_Biller biller = null;
			while (rs.next()) {
				biller = new Masary_Biller();
				biller.setBiller_id(rs.getString("biller_id"));
				biller.setBiller_name(rs.getString("biller_name"));
				biller.setBiller_name_ar(rs.getString("biller_name_ar"));
				biller.setActive(true);
				billers.add(biller);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return billers;
	}

	public List<Masary_Biller> getBTCbiller() throws Exception {
		List<Masary_Biller> billers = new ArrayList<Masary_Biller>();
		Connection conn = null;
		String sql = "select biller_id,biller_name,biller_name_ar " + "from  masary_bill_biller"
				+ " where active='Y' and biller_id in(select biller_id from  masary_bill_types) order by biller_id";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Masary_Biller biller = null;
			while (rs.next()) {
				biller = new Masary_Biller();
				biller.setBiller_id(rs.getString("biller_id"));
				biller.setBiller_name(rs.getString("biller_name"));
				biller.setBiller_name_ar(rs.getString("biller_name_ar"));
				biller.setActive(true);
				billers.add(biller);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return billers;
	}

	public List<Masary_Bill_Type> getMenuBTC(int pin, int biller_id) throws Exception {
		List<Masary_Bill_Type> BTCs = new ArrayList<Masary_Bill_Type>();
		Connection conn = null;
		String sql = "  select biller_id,bill_type_id,bill_name,pmt_type,service_type,service_name,"
				+ "service_ar_name,bill_lable,bill_lable_ar,is_frac_acc,is_part_acc,is_over_acc,is_adv_acc "
				+ "from  masary_bill_types where biller_id=?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, biller_id);
			ResultSet rs = stmt.executeQuery();
			Masary_Bill_Type BTC = null;
			while (rs.next()) {
				BTC = new Masary_Bill_Type();
				BTC.setBILLER_ID(rs.getInt(1));
				BTC.setBILL_TYPE_ID(rs.getInt(2));
				BTC.setBILL_NAME(rs.getString(3));
				BTC.setPMT_TYPE(rs.getString(4));
				BTC.setSERVICE_TYPE(rs.getString(5));
				BTC.setSERVICE_NAME(rs.getString(6));
				BTC.setSERVICE_AR_NAME(rs.getString(7));
				BTC.setBILL_LABLE(rs.getString(8));
				BTC.setBILL_LABLE_AR(rs.getString(9));
				if (rs.getString(10).equals("Y")) {
					BTC.setIS_FRAC_ACC(true);
				} else if (rs.getString(10).equals("N")) {
					BTC.setIS_FRAC_ACC(false);
				}
				if (rs.getString(11).equals("Y")) {
					BTC.setIS_PART_ACC(true);
				} else if (rs.getString(11).equals("N")) {
					BTC.setIS_PART_ACC(false);
				}
				if (rs.getString(12).equals("Y")) {
					BTC.setIS_OVER_ACC(true);
				} else if (rs.getString(12).equals("N")) {
					BTC.setIS_OVER_ACC(false);
				}
				if (rs.getString(13).equals("Y")) {
					BTC.setIS_ADV_ACC(true);
				} else if (rs.getString(13).equals("N")) {
					BTC.setIS_ADV_ACC(false);
				}
				BTCs.add(BTC);
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return BTCs;
	}

	public List<CustomerServiceDTO> getMenuServ(int pin, int biller_id) throws Exception {
		List<CustomerServiceDTO> Services = new ArrayList<CustomerServiceDTO>();
		Connection conn = null;
		String sql = "select s.ID_SERVICE, s.STR_SERVICE_NAME, cs.SERVICE_BALANCE, s.price, s.STR_SERVICE_NAME_AR, "
				+ "s.allow_auto_allocate, s.is_voucher, s.is_bill " + "from  services s, customer_services cs "
				+ "where s.id_service=cs.service_id and cs.customer_id=? and s.biller_id=? "
				+ "and s.id_service not in(select bill_type_id from  masary_bill_types)and s.active='Y' "
				+ "order by s.SERV_ORDER;";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, pin);
			stmt.setInt(2, biller_id);
			ResultSet rs = stmt.executeQuery();
			CustomerServiceDTO service;
			while (rs.next()) {
				service = new CustomerServiceDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				Services.add(service);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return Services;
	}

	public Masary_Bill_Type getBTC(String bill_type_id) throws Exception {
		Connection conn = null;
		String sql = "  select distinct  mb.biller_id,mb.bill_type_id,mb.bill_name,mb.pmt_type,mb.service_type,"
				+ "mb.service_name,mb.service_ar_name,mb.bill_lable,mb.bill_lable_ar,mb.is_frac_acc,mb.is_part_acc,mb.is_over_acc,mb.is_adv_acc,"
				+ "(decode(cs.CUSTOMER_APPLIED_FEES_TYPE, 'ON THRO', cs.customer_applied_fees*100 || '%',cs.customer_applied_fees )) customer_fees "
				+ "from  masary_bill_types mb, COMMISSIONING_STRUCTURE cs "
				+ "where mb.bill_type_id=? and cs.BILL_TYPE_CODE = ?";
		PreparedStatement stmt = null;
		Masary_Bill_Type BTC = new Masary_Bill_Type();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bill_type_id);
			stmt.setString(2, bill_type_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				BTC.setBILLER_ID(rs.getInt(1));
				BTC.setBILL_TYPE_ID(rs.getInt(2));
				BTC.setBILL_NAME(rs.getString(3));
				BTC.setPMT_TYPE(rs.getString(4));
				BTC.setSERVICE_TYPE(rs.getString(5));
				BTC.setSERVICE_NAME(rs.getString(6));
				BTC.setSERVICE_AR_NAME(rs.getString(7));
				BTC.setBILL_LABLE(rs.getString(8));
				BTC.setBILL_LABLE_AR(rs.getString(9));
				BTC.setCustomerFees(rs.getString("customer_fees"));
				BTC.setIS_FRAC_ACC(rs.getString(10).equals("Y") ? true : false);
				BTC.setIS_PART_ACC(rs.getString(11).equals("Y") ? true : false);
				BTC.setIS_OVER_ACC(rs.getString(12).equals("Y") ? true : false);
				BTC.setIS_ADV_ACC(rs.getString(13).equals("Y") ? true : false);
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return BTC;
	}

	public Masary_Bill_Type getBillType(String serv_id) {
		Masary_Bill_Type bill_Type = new Masary_Bill_Type();
		Connection conn = null;
		String sql = null;
		sql = "select service_ar_name,biller_id ,bill_type_id,bill_name,pmt_type,service_type,service_name,bill_lable,bill_lable_ar,is_frac_acc,is_part_acc,is_over_acc,is_adv_acc "
				+ " from  masary_bill_types" + " where bill_type_id=?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, serv_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				bill_Type.setBILLER_ID(rs.getInt("biller_id"));
				bill_Type.setBILL_TYPE_ID(rs.getInt("bill_type_id"));
				bill_Type.setBILL_NAME(rs.getString("bill_name"));
				bill_Type.setPMT_TYPE(rs.getString("pmt_type"));
				bill_Type.setSERVICE_TYPE(rs.getString("service_type"));
				bill_Type.setSERVICE_NAME(rs.getString("service_name"));
				bill_Type.setBILL_LABLE(rs.getString("bill_lable"));
				bill_Type.setBILL_LABLE_AR(rs.getString("bill_lable_ar"));
				bill_Type.setSERVICE_AR_NAME(rs.getString("service_ar_name"));
				String is_frac = rs.getString("is_frac_acc");
				if (is_frac.equals("Y")) {
					bill_Type.setIS_FRAC_ACC(true);
				} else {
					bill_Type.setIS_FRAC_ACC(false);
				}
				String is_part_acc = rs.getString("is_part_acc");
				if (is_part_acc.equals("Y")) {
					bill_Type.setIS_PART_ACC(true);
				} else {
					bill_Type.setIS_PART_ACC(false);
				}
				String is_over_acc = rs.getString("is_over_acc");
				if (is_over_acc.equals("Y")) {
					bill_Type.setIS_OVER_ACC(true);
				} else {
					bill_Type.setIS_OVER_ACC(false);
				}
				String is_adv_acc = rs.getString("is_adv_acc");
				if (is_adv_acc.equals("Y")) {
					bill_Type.setIS_ADV_ACC(true);
				} else {
					bill_Type.setIS_ADV_ACC(false);
				}
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return bill_Type;
	}

	public List<TransactionDTO> getTransactions_To(String payedPin, String DateFrom, String Dateto, String Service_id) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char(cust2.STR_DISPLAY_NAME) payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type"
				+ " from  VW_CUSTOMERS cust1, VW_CUSTOMERS cust2, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype"
				+ "              where CUSTOMER_PAYER=?" + "               and CUSTOMER_PAYER=cust1.ID_CUSTOMER"
				+ "               and CUSTOMER_PAYED=cust2.ID_CUSTOMER"
				+ "              and trx.txn_type_id= txntype.id_txn_type "
				+ "              and trx.txn_status_id= txnstatus.id_txn_status"
				+ "              and txntype.id_txn_type=?"
				+ "              and trx.transaction_date between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY HH24:MI')"
				+ "              union all "
				+ "              select TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char( sales.EXTRERNAL_ENTITY)  payed ,amount,TRANSACTION_DATE , txnstatus.str_txn_status, txntype.str_txn_type "
				+ "              from  VW_CUSTOMERS cust1, sales"
				+ "              , TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype "
				+ "             where CUSTOMER_PAYER=?" + "               and CUSTOMER_PAYER=cust1.ID_CUSTOMER"
				+ "               and CUSTOMER_PAYED is null" + "               and trx.transaction_id= sales.TRANSID"
				+ "               and trx.txn_type_id= txntype.id_txn_type "
				+ "               and trx.txn_status_id= txnstatus.id_txn_status  "
				+ "               and txntype.id_txn_type=?"
				+ "               and trx.transaction_date between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY HH24:MI')"
				+ "              order by TRANSACTION_DATE";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, payedPin);
			stmt.setString(2, Service_id);
			stmt.setString(3, DateFrom);
			stmt.setString(4, Dateto + " 23:59");
			stmt.setString(5, payedPin);
			stmt.setString(6, Service_id);
			stmt.setString(7, DateFrom);
			stmt.setString(8, Dateto + " 23:59");
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("str_txn_type"), 0);
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public List<TransactionDTO> getBillTransactions_To(String payedPin, String DateFrom, String Dateto,
			String Service_id) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select trx.TRANSACTION_ID,cust1.STR_DISPLAY_NAME payer,to_char( sales.EXTRERNAL_ENTITY)  payed ,trx.amount,trx.TRANSACTION_DATE , txnstatus.str_txn_status,btc.service_name,rec.merchant_commession"
				+ "              from  VW_CUSTOMERS cust1, sales, TRANSACTIONS trx,  txn_status  txnstatus,  txn_types txntype , masary_bill_transactions btra, masary_bill_types btc, reconciliations rec"
				+ "             where trx.CUSTOMER_PAYER=?" + "               and trx.CUSTOMER_PAYER=cust1.ID_CUSTOMER"
				+ "               and rec.transaction_id=trx.transaction_id"
				+ "               and trx.CUSTOMER_PAYED is null"
				+ "               and trx.transaction_id= sales.TRANSID"
				+ "               and trx.transaction_id=btra.transaction_id"
				+ "               and trx.txn_type_id= txntype.id_txn_type "
				+ "               and trx.txn_status_id= txnstatus.id_txn_status  "
				+ "               and txntype.id_txn_type=?" + " and trx.transaction_id=btra.transaction_id"
				+ " and btc.bill_type_id=btra.service_id" + " and btc.bill_type_id=?"
				+ "               and trx.transaction_date between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY HH24:MI')"
				+ "              order by trx.TRANSACTION_DATE";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, payedPin);
			stmt.setString(2, Service_id);
			stmt.setString(3, Service_id);
			stmt.setString(4, DateFrom);
			stmt.setString(5, Dateto + " 23:59");
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("str_txn_status"),
						rs.getString("service_name"), rs.getDouble("merchant_commession"));
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public int bill_found(int bill_Type) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		int result = 0;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= MASARY_BILL_TYPE_FOUND(?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, bill_Type);
			sqlStatement.execute();
			result = sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return result;
	}

	public int insertbillrequest(Masary_Bill_Request masary_Bill_Request, int pin, Main_Provider provider)
			throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		String incope_amount = "Y";
		if (masary_Bill_Request.getINCOPEN_AMOUNT()) {
			incope_amount = "Y";
		} else {
			incope_amount = "N";
		}
		try {
			conn = getConnection();
			String callSQL = "{call ?:= INSERT_MASARY_BILL_REQUEST(?,?,1,1,?,?,?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, pin);
			sqlStatement.setString(3, masary_Bill_Request.getREQUEST_ID());
			sqlStatement.setString(4, masary_Bill_Request.getBILL_TYPE_CODE());
			sqlStatement.setString(5, masary_Bill_Request.getORIGINATOR_CODE());
			sqlStatement.setString(6, masary_Bill_Request.getTERMINALID());
			sqlStatement.setString(7, incope_amount);
			sqlStatement.setString(8, masary_Bill_Request.getBILLING_ACCOUNT());
			sqlStatement.setString(9, masary_Bill_Request.getBANK_ID());
			sqlStatement.setString(10, masary_Bill_Request.getDELEVERY_METHOD());
			sqlStatement.setInt(11, provider.getPROVIDER_ID());
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public void insert_bill_response(Masary_Bill_Response bill_Response, Main_Provider provider) throws SQLException {
		try {
			insertbillresponse(bill_Response, provider);
			if (bill_Response.getMasary_Bill_Status().getSTATUS_CODE().equals("200")) {
				for (Masary_Bill_Amounts bill_Amount : bill_Response.getAmounts()) {
					INSERT_MASARY_BILL_AMOUNTS(bill_Amount.getBILL_REF_NUMBER(), bill_Amount.getBILL_SUMM_AMT_CODE(),
							bill_Amount.getCUR_AMOUNT(), bill_Amount.getCUR_CODE());
				}
			}
		} catch (SQLException e) {
		}
	}

	public void insert_payment_request(Masary_Payment_Request payment_Request, int pin, Main_Provider provider)
			throws SQLException {
		try {
			INSERT_MASARY_PAYMENT_REQUEST(payment_Request, pin, provider);
			for (Customers_Properties customers_Propertie : payment_Request.getCustomers_Properties()) {
				INSERT_PMT_CUT_PROP(customers_Propertie.getKEY(), customers_Propertie.getVALUE(),
						payment_Request.getREQUEST_ID());
			}
		} catch (SQLException e) {
		}
	}

	public int INSERT_PAYMENT_RESPONSE(Masary_Payment_Response masary_Payment_Response, int pin, double balance)
			throws SQLException {
		int Transaction_id = 0;
		try {
			Transaction_id = INSERT_MASARY_PAYMENT_RESPONSE(masary_Payment_Response, pin, balance);
			if (masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE().equals("200")) {
				for (Customers_Properties customers_Propertie : masary_Payment_Response.getCustomers_Properties()) {
					INSERT_PMT_CUT_PROP(customers_Propertie.getKEY(), customers_Propertie.getVALUE(),
							masary_Payment_Response.getREQUEST_ID());
				}
				for (Masary_Payment_Type masary_Payment_Type : masary_Payment_Response.getMasary_Payment_Types()) {
					INSERT_MASARY_PAYMENT_TYPE(masary_Payment_Response.getREQUEST_ID(),
							masary_Payment_Type.getPAYMENT_ID(), masary_Payment_Type.getPAYMENT_ID_TYPE(),
							masary_Payment_Type.getCREATED_DATE());
				}
			}
		} catch (SQLException e) {
		}
		return Transaction_id;
	}

	public int INSERT_PAYMENT_RESPONSE_TEST(Masary_Payment_Response masary_Payment_Response, int pin, double balance,
			Main_Provider provider, String customer_name, String isValidDTO) throws SQLException {

		int Transaction_id = 0;
		try {
			Transaction_id = INSERT_MASARY_PAYMENT_RESPONSE_TEST(masary_Payment_Response, pin, balance, provider,
					customer_name, isValidDTO);

			if (masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE().equals("200")) {
				for (Customers_Properties customers_Propertie : masary_Payment_Response.getCustomers_Properties()) {
					INSERT_PMT_CUT_PROP(customers_Propertie.getKEY(), customers_Propertie.getVALUE(),
							masary_Payment_Response.getREQUEST_ID());
				}
				for (Masary_Payment_Type masary_Payment_Type : masary_Payment_Response.getMasary_Payment_Types()) {
					INSERT_MASARY_PAYMENT_TYPE(masary_Payment_Response.getREQUEST_ID(),
							masary_Payment_Type.getPAYMENT_ID(), masary_Payment_Type.getPAYMENT_ID_TYPE(),
							masary_Payment_Type.getCREATED_DATE());
				}
			}
		} catch (SQLException e) {
		}
		return Transaction_id;
	}

	public int INSERT_MASARY_PAYMENT_RESPONSE(Masary_Payment_Response masary_Payment_Response, int pin, double balance)
			throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		String Server_date = (masary_Payment_Response.getSERVER_DATE().substring(0, 10)) + " "
				+ (masary_Payment_Response.getSERVER_DATE().substring(11, 16));
		try {
			String callSQL = "";
			if (masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE().equals("200")) {
				callSQL = "{call ?:= INSERT_MASARY_PAYMENT_RESPONSE(" + pin + ",to_date('" + Server_date
						+ "','YYYY-MM-DD HH24:MI'),"
						+ masary_Payment_Response.getMasary_signon_profile().getSIGNON_PROFILE_ID() + ",4,'"
						+ masary_Payment_Response.getREQUEST_ID() + "','"
						+ masary_Payment_Response.getASYNC_REQUEST_ID() + "','"
						+ masary_Payment_Response.getORIGINATOR_CODE() + "','"
						+ masary_Payment_Response.getTERMINAL_ID() + "','"
						+ masary_Payment_Response.getCLIENT_TERMINAL_SEQ_ID() + "','"
						+ masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE() + "','"
						+ masary_Payment_Response.getBILLING_ACCOUNT() + "','"
						+ masary_Payment_Response.getBILL_REF_NUMBER() + "','"
						+ masary_Payment_Response.getNOTIFY_MOBILE() + "',"
						+ masary_Payment_Response.getBILL_TYPE_CODE() + ",'" + masary_Payment_Response.getPAYMENT_TYPE()
						+ "','" + masary_Payment_Response.getDELIVERY_METHOD() + "',"
						+ masary_Payment_Response.getCURRENT_AMOUNT() + "," + masary_Payment_Response.getFEES_AMOUNT()
						+ ",'" + masary_Payment_Response.getACCOUNT_ID() + "','"
						+ masary_Payment_Response.getACCOUNT_TYPE() + "','" + masary_Payment_Response.getACCOUNT_KEY()
						+ "','" + masary_Payment_Response.getACCOUNT_CUR() + "','"
						+ masary_Payment_Response.getPAYMENT_METHOD() + "',to_date('"
						+ masary_Payment_Response.getPRC_DATE() + "','YYYY-MM-DD')," + balance + ")}";
			} else {
				callSQL = "{call ?:= INSERT_MASARY_PAYMENT_RESPONSE(" + pin + ",to_date('" + Server_date
						+ "','YYYY-MM-DD HH24:MI'),"
						+ masary_Payment_Response.getMasary_signon_profile().getSIGNON_PROFILE_ID() + ",4,'"
						+ masary_Payment_Response.getREQUEST_ID() + "','"
						+ masary_Payment_Response.getASYNC_REQUEST_ID() + "','"
						+ masary_Payment_Response.getORIGINATOR_CODE() + "','"
						+ masary_Payment_Response.getTERMINAL_ID() + "','"
						+ masary_Payment_Response.getCLIENT_TERMINAL_SEQ_ID() + "','"
						+ masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE() + "','"
						+ masary_Payment_Response.getBILLING_ACCOUNT() + "','"
						+ masary_Payment_Response.getBILL_REF_NUMBER() + "','"
						+ masary_Payment_Response.getNOTIFY_MOBILE() + "',"
						+ masary_Payment_Response.getBILL_TYPE_CODE() + ",'" + masary_Payment_Response.getPAYMENT_TYPE()
						+ "','" + masary_Payment_Response.getDELIVERY_METHOD() + "',"
						+ masary_Payment_Response.getCURRENT_AMOUNT() + "," + masary_Payment_Response.getFEES_AMOUNT()
						+ ",'" + masary_Payment_Response.getACCOUNT_ID() + "','"
						+ masary_Payment_Response.getACCOUNT_TYPE() + "','" + masary_Payment_Response.getACCOUNT_KEY()
						+ "','" + masary_Payment_Response.getACCOUNT_CUR() + "','"
						+ masary_Payment_Response.getPAYMENT_METHOD() + "',sysdate," + balance + ")}";
			}

			conn = getConnection();
			MasaryManager.logger.info(callSQL);
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	private int INSERT_MASARY_PAYMENT_RESPONSE_TEST(Masary_Payment_Response masary_Payment_Response, int pin,
			double balance, Main_Provider provider, String customer_name, String isValidDTO) throws SQLException {

		Connection conn = null;
		CallableStatement sqlStatement = null;
		java.util.Date today = new java.util.Date();

		String Server_date = (masary_Payment_Response.getSERVER_DATE().substring(0, 10)) + " "
				+ (masary_Payment_Response.getSERVER_DATE().substring(11, 16));
		try {
			String callSQL = "{call ?:=INSERT_MASARY_PAYMENT_RESP_et(?,to_date(?,'YYYY-MM-DD HH24:MI'),?,?,?,?,?,?,?"
					+ ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI'),?,?,'',?,?)}";

			conn = getConnection();
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, pin);
			sqlStatement.setString(3, Server_date);
			sqlStatement.setInt(4, masary_Payment_Response.getMasary_signon_profile().getSIGNON_PROFILE_ID());
			sqlStatement.setInt(5, 4);
			sqlStatement.setString(6, masary_Payment_Response.getREQUEST_ID());
			sqlStatement.setString(7, masary_Payment_Response.getASYNC_REQUEST_ID());
			sqlStatement.setString(8, masary_Payment_Response.getORIGINATOR_CODE());
			sqlStatement.setString(9, masary_Payment_Response.getTERMINAL_ID());
			sqlStatement.setString(10, masary_Payment_Response.getCLIENT_TERMINAL_SEQ_ID());
			sqlStatement.setString(11, masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE());
			sqlStatement.setString(12, masary_Payment_Response.getBILLING_ACCOUNT());
			sqlStatement.setString(13, masary_Payment_Response.getBILL_REF_NUMBER());
			sqlStatement.setString(14, masary_Payment_Response.getNOTIFY_MOBILE());
			sqlStatement.setString(15, masary_Payment_Response.getBILL_TYPE_CODE());
			sqlStatement.setString(16, masary_Payment_Response.getPAYMENT_TYPE());
			sqlStatement.setString(17, masary_Payment_Response.getDELIVERY_METHOD());
			sqlStatement.setDouble(18, masary_Payment_Response.getCURRENT_AMOUNT());
			sqlStatement.setDouble(19, masary_Payment_Response.getFEES_AMOUNT());
			sqlStatement.setString(20, masary_Payment_Response.getACCOUNT_ID());
			sqlStatement.setString(21, masary_Payment_Response.getACCOUNT_TYPE());
			sqlStatement.setString(22, masary_Payment_Response.getACCOUNT_KEY());
			sqlStatement.setString(23, masary_Payment_Response.getACCOUNT_CUR());
			sqlStatement.setString(24, masary_Payment_Response.getPAYMENT_METHOD());
			sqlStatement.setString(25,
					masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE().equals("200")
							? masary_Payment_Response.getPRC_DATE()
							: today.toString());
			sqlStatement.setDouble(26, balance);
			sqlStatement.setInt(27, provider.getPROVIDER_ID());
			sqlStatement.setString(28, customer_name);
			sqlStatement.setString(29, isValidDTO);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int INSERT_MASARY_PAYMENT_REQUEST(Masary_Payment_Request payment_Request, int pin, Main_Provider provider)
			throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= INSERT_MASARY_PAYMENT_REQUEST(?,3,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, payment_Request.getMasary_signon_profile().getSIGNON_PROFILE_ID());
			sqlStatement.setString(3, payment_Request.getREQUEST_ID());
			sqlStatement.setString(4, payment_Request.getASYNC_REQUEST_ID());
			sqlStatement.setString(5, payment_Request.getORIGINATOR_CODE());
			sqlStatement.setString(6, payment_Request.getTERMINAL_ID());
			sqlStatement.setString(7, payment_Request.getCLIENT_TERMINAL_SEQ_ID());
			sqlStatement.setString(8, payment_Request.getBILLING_ACCOUNT());
			sqlStatement.setString(9, payment_Request.getBILL_REF_NUMBER());
			sqlStatement.setString(10, payment_Request.getNOTIFY_MOBILE());
			sqlStatement.setInt(11, Integer.parseInt(payment_Request.getBILL_TYPE_CODE()));
			sqlStatement.setString(12, payment_Request.getPMT_TYPE());
			sqlStatement.setString(13, payment_Request.getDELIVERY_METHOD());
			sqlStatement.setDouble(14, payment_Request.getAMOUNT());
			sqlStatement.setString(15, payment_Request.getACCT_ID());
			sqlStatement.setString(16, payment_Request.getACCT_KEY());
			sqlStatement.setString(17, payment_Request.getACCT_TYPE());
			sqlStatement.setString(18, payment_Request.getACCT_CUR());
			sqlStatement.setString(19, payment_Request.getPMT_METHOD());
			sqlStatement.setInt(20, pin);
			sqlStatement.setInt(21, provider.getPROVIDER_ID());
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int INSERT_PMT_CUT_PROP(String key, String value, String request_id) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= INSERT_PMT_CUT_PROP(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(2, key);
			sqlStatement.setString(3, value);
			sqlStatement.setString(4, request_id);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int insertbillresponse(Masary_Bill_Response bill_Response, Main_Provider provider) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		String incope_amount = "Y";
		java.util.Date today = new java.util.Date();
		if (bill_Response.getINCOPENAMOUNT()) {
			incope_amount = "Y";
		} else {
			incope_amount = "N";
		}
		String callSQL = "";
		String Server_date = (bill_Response.getSERVER_DATE().substring(0, 10)) + " "
				+ (bill_Response.getSERVER_DATE().substring(11, 16));
		try {
			conn = getConnection();
			callSQL = "{call ?:= INSERT_MASARY_BILL_RESPONSE(to_date(?,'YYYY-MM-DD HH24:MI'),?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'YYYY-MM-DD HH24:MI'),?,?,?,?,?,?,?)}";
			MasaryManager.logger.info(callSQL);
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, Server_date);
			sqlStatement.setInt(3, bill_Response.getMasary_signon_profile().getSIGNON_PROFILE_ID());
			sqlStatement.setInt(4, 2);
			sqlStatement.setString(5, bill_Response.getREQUEST_ID());
			sqlStatement.setString(6, bill_Response.getASYNC_REQUEST_ID());
			sqlStatement.setString(7, bill_Response.getORIGINATOR_CODE());
			sqlStatement.setString(8, bill_Response.getTerminalId());
			sqlStatement.setString(9, bill_Response.getMasary_Bill_Status().getSTATUS_CODE());
			sqlStatement.setString(10, incope_amount);
			sqlStatement.setString(11, bill_Response.getDELIVERY_METHOD());
			sqlStatement.setString(12, bill_Response.getBILLING_ACCOUNT());
			sqlStatement.setString(13, bill_Response.getBILL_TYPE_CODE());
			sqlStatement.setString(14, bill_Response.getBILL_REF_NUMBER());
			sqlStatement.setString(15,
					bill_Response.getMasary_Bill_Status().getSTATUS_CODE().equals("200") ? bill_Response.getDUE_DATE()
							: today.toString());
			sqlStatement.setDouble(16, bill_Response.getLOWER_AMOUNT());
			sqlStatement.setString(17, bill_Response.getLOWER_CURRUNT_AMOUNT());
			sqlStatement.setDouble(18, bill_Response.getUPPER_AMOUNT());
			sqlStatement.setString(19, bill_Response.getUPPER_CURRENT_AMOUNT());
			sqlStatement.setString(20, bill_Response.getRulesAwareness());
			sqlStatement.setString(21, bill_Response.getBILL_STATUS());
			sqlStatement.setInt(22, provider.getPROVIDER_ID());
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int INSERT_MASARY_BILL_AMOUNTS(String BILL_REFNUMBER, String BILL_SUMMAMT_CODE, double CURAMOUNT,
			String curcode) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= INSERT_MASARY_BILL_AMOUNTS(?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(2, BILL_REFNUMBER);
			sqlStatement.setString(3, BILL_SUMMAMT_CODE);
			sqlStatement.setDouble(4, CURAMOUNT);
			sqlStatement.setString(5, curcode);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int INSERT_MASARY_PAYMENT_TYPE(String request_id, String payment_id, String payment_id_type,
			String created_date) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		String createddate = (created_date.substring(0, 10)) + " " + (created_date.substring(11, 16));
		try {
			conn = getConnection();
			String callSQL = "{call ?:= INSERT_MASARY_PAYMENT_TYPE(?,?,?,to_date(?,'YYYY-MM-DD HH24:MI'))}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(2, request_id);
			sqlStatement.setString(3, payment_id);
			sqlStatement.setString(4, payment_id_type);
			sqlStatement.setString(5, createddate);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public Masary_Billing_Account getbilling_account(String billing_account_id) throws Exception {
		Connection conn = null;
		String sql = "select account_code,account_pass,account_sec_pin,terminal_type,serial_number from  masary_billing_account where account_id=?";
		PreparedStatement stmt = null;
		Masary_Billing_Account account = new Masary_Billing_Account();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(billing_account_id));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				account.setACCOUNT_ID(Integer.parseInt(billing_account_id));
				account.setACCOUNT_CODE(rs.getInt(1));
				account.setACCOUNT_PASS(rs.getString(2));
				account.setACCOUNT_SEC_PIN(rs.getString(3));
				account.setTERMINAL_TYPE(rs.getString(4));
				account.setSERIAL_NUMBER(rs.getString(5));
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return account;
	}

	public String getPin() {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		String Pin = "";
		try {
			conn = getConnection();
			String sql = "select account_pass from  masary_billing_account";
			sqlStatement = conn.prepareStatement(sql);
			ResultSet rs = sqlStatement.executeQuery();
			while (rs.next()) {
				Pin = rs.getString("account_pass");
			}
			rs.close();
			return Pin;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return Pin;
	}

	public Masary_Bill_Receipt getreceipt(int transaction_Id) throws Exception {
		Connection conn = null;
		String sql = "select to_char(pmt_txn.transaction_date,'DD-MM-YYYY HH24:MI'),pmt_txn.customer_id,"
				+ " pmt_txn.service_id,pmt_res.billing_account,to_char(bill_res.due_date,'YYYY-MM-RR HH24:MI'),"
				+ " pmt_res.current_amount,pmt_res.fees_amount," + " pmt_res.CUSTOMER_NAME as customer_name"
				+ " from  masary_bill_transactions pmt_txn" + " , masary_payment_response pmt_res"
				+ " , masary_bill_response bill_res  " + " where" + " pmt_txn.transaction_id=?"
				+ " and pmt_res.request_id=pmt_txn.request_id"
				+ " and bill_res.bill_ref_number=pmt_res.bill_ref_number";
		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction_Id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				receipt.setTransaction_id(transaction_Id);
				receipt.setTransaction_date(rs.getString(1));
				receipt.setCustomer_id(rs.getInt(2));
				receipt.setBTC(rs.getInt(3));
				receipt.setPhone(rs.getString(4));
				receipt.setDue_date(rs.getString(5));
				receipt.setAmount(rs.getDouble(6));
				receipt.setFees(rs.getDouble(7));
				receipt.setCustomerName(rs.getString("customer_name"));
				logger.info("getreceipt info for print " + receipt.getTransaction_id() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getNewbillreceipt(int transaction_Id) throws Exception {
		Connection conn = null;
		Bill_Response response_P = new Bill_Response();
		Gson gson = new Gson();
		String sql = " select l.CUSTOMER_PHONE," + "l.PROVIDER_RESPONSE,tj.APPLIED_FEES,"
				+ "tj.ORIGINAL_AMOUNT,l.RESPONSE_DATE,l.CUSTOMER_ID from bills.logs l ," + "TRANSACTION_JOURNAL tj "
				+ "where l.TRANSACTION_ID = ? and " + "l.TRANSACTION_ID = tj.TRANSACTION_ID ";

		PreparedStatement stmt = null;
		Masary_Bill_Receipt masaryBillReceipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction_Id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				masaryBillReceipt.setTransaction_id(transaction_Id);
				masaryBillReceipt.setPhone(rs.getString(1));
				response_P = gson.fromJson(rs.getString(2), Bill_Response.class);
				masaryBillReceipt.setProviderResponse(response_P);
				masaryBillReceipt.setFees(rs.getDouble(3));
				masaryBillReceipt.setAmount(rs.getDouble(4));
				masaryBillReceipt.setBTC(124);
				masaryBillReceipt.setTransaction_date(rs.getString(5));
				masaryBillReceipt.setCustomer_id(rs.getInt(6));
				logger.info("getreceipt info for print " + masaryBillReceipt.getTransaction_id());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return masaryBillReceipt;
	}

	public Masary_Bill_Receipt getVodafone_Bill_Receipt(int transaction_Id) throws Exception {
		Connection conn = null;
		String sql = "SELECT " + "to_char(txn.transaction_date,'DD-MM-YYYY HH24:MI'), " + "txn.customer_payer, "
				+ "txn.txn_type_id, " + "s.EXTRERNAL_ENTITY, " + "to_char(txn.transaction_date,'DD-MM-YYYY HH24:MI'), "
				+ "ol.AMOUNT, "
				+ "(SELECT APPLIED_FEES FROM RATE_PLAN_SERVICE WHERE SERVICE_ID = txn.txn_type_id AND RATE_PLAN_ID = (SELECT CUSTOMERS.RATE_PLAN_ID FROM CUSTOMERS WHERE CUSTOMERS.ID_CUSTOMER = txn.customer_payer)), "
				+ "'Name Not Available' " + "FROM TRANSACTIONS txn,sales s,OPERATION_LOG ol " + "WHERE "
				+ "txn.transaction_id = s.TRANSID " + "AND txn.transaction_id = ol.TRANSACTION_ID "
				+ "AND txn.transaction_id = ? ";
		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction_Id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				receipt.setTransaction_id(transaction_Id);
				receipt.setTransaction_date(rs.getString(1));
				receipt.setCustomer_id(rs.getInt(2));
				receipt.setBTC(rs.getInt(3));
				receipt.setPhone(rs.getString(4));
				receipt.setDue_date(rs.getString(5));
				receipt.setAmount(rs.getDouble(6));
				receipt.setFees(rs.getDouble(7));
				receipt.setCustomerName(rs.getString(8));
				logger.info("getreceipt info for print " + receipt.getTransaction_id() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public int INSERT_MASARY_Bill_TYPE(Masary_Bill_Type bill_Type) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		String part = "N";
		String Frac = "N";
		String Over = "N";
		String Adv = "N";
		if (bill_Type.isIS_PART_ACC()) {
			part = "Y";
		}
		if (bill_Type.isIS_FRAC_ACC()) {
			Frac = "Y";
		}
		if (bill_Type.isIS_OVER_ACC()) {
			Over = "Y";
		}
		if (bill_Type.isIS_ADV_ACC()) {
			Adv = "Y";
		}
		try {
			conn = getConnection();
			String callSQL = "{call ?:= INSERT_BILL_TYPE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setInt(2, bill_Type.getBILLER_ID());
			sqlStatement.setInt(3, bill_Type.getBILL_TYPE_ID());
			sqlStatement.setString(4, bill_Type.getBILL_NAME());
			sqlStatement.setString(5, bill_Type.getPMT_TYPE());
			sqlStatement.setString(6, bill_Type.getSERVICE_TYPE());
			sqlStatement.setString(7, bill_Type.getSERVICE_NAME());
			sqlStatement.setString(8, bill_Type.getBILL_LABLE());
			sqlStatement.setString(9, Frac);
			sqlStatement.setString(10, part);
			sqlStatement.setString(11, Over);
			sqlStatement.setString(12, Adv);
			sqlStatement.setString(13, bill_Type.getSERVICE_AR_NAME());
			sqlStatement.setString(14, bill_Type.getBILL_LABLE_AR());
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public String ChangePin(String new_pin) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sql = "update  masary_billing_account set account_pass=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(new_pin));
			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return "OK";
	}

	public int getCLIENT_TERMINAL_SEQ_ID() {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		int Pin = 1;
		try {
			conn = getConnection();
			String sql = "select  CLIENT_TERMINAL_SEQ.nextval from dual";
			sqlStatement = conn.prepareStatement(sql);
			ResultSet rs = sqlStatement.executeQuery();
			while (rs.next()) {
				Pin = rs.getInt(1);
			}
			rs.close();
			return Pin;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return Pin;
	}

	public boolean IsNormalCust(int CustID) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= ISNORMAL_CUSTOMER(?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, CustID);
			sqlStatement.execute();
			if (sqlStatement.getInt(1) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return false;
	}

	public void addLoyalityPoints(String msisdn) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int added_points = 10;
		try {
			conn = getConnection();
			String sql = "insert into  loyality_points values ((select id_customer from  customers where msisdn=?), (select points from  loyality_points where customer_id=(select id_customer from  customers where msisdn=?) and insert_date = (select max(insert_date) from  loyality_points where customer_id=(select id_customer from  customers where msisdn=?)))+ ?, sysdate)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, msisdn);
			stmt.setString(2, msisdn);
			stmt.setString(3, msisdn);
			stmt.setInt(4, added_points);
			stmt.execute();
			conn.commit();
		} catch (Exception ex) {
			logger.debug(ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public String getLoyaltyPoints(String pin) {
		String result = "0";
		Connection conn = null;
		int id = Integer.parseInt(pin);
		String sql = "select points from  loyality_points where customer_id=? and insert_date = (select max(insert_date) from  loyality_points where customer_id=?)";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return result;
	}

	public ArrayList<CustomerServiceDTO> getCustomerServices(String customerID) {
		logger.info("Get all services for a cstomer." + customerID);
		ArrayList<CustomerServiceDTO> services = new ArrayList<CustomerServiceDTO>();
		Connection conn = null;
		String sql = "select service_id,Str_Service_Name,str_service_name_ar "
				+ "from  customer_services,  services,  customers"
				+ " where service_id = id_service  and customer_id = id_customer and id_customer = ?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerID);
			ResultSet rs = stmt.executeQuery();
			int serviceID;
			while (rs.next()) {
				serviceID = rs.getInt(1);
				CustomerServiceDTO service = new CustomerServiceDTO(serviceID, rs.getString(2), rs.getString(3));
				services.add(service);
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}

		return services;
	}

	public int showTree(String customerID) {
		int show = 0;
		logger.info("showTree " + customerID);
		Connection conn = null;
		String sql = "select show_tree " + "from  customer_profile" + " where  customer_id = ?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				show = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}

		return show;
	}

	public Vector getAllRepsData() {
		logger.info("Get the name and the allowed balance for a rep.");
		Vector<String[]> results = new Vector();
		Connection conn = null;
		String sql = "select id_customer, str_display_name_arabic, rep_allowed_bal from  vw_reps order by id_customer";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String[] subResults = new String[3];
				subResults[0] = rs.getString(1);
				subResults[1] = rs.getString(2);
				subResults[2] = rs.getString(3);
				results.add(subResults);
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return results;
	}

	public void updateNewRepBalances(String dateFrom, String dateTo, String amount, String serviceID, String repID) {
		logger.info("Update the balance plan for a rep in period from " + dateFrom + " to " + dateTo + " with amount "
				+ amount + " for service " + serviceID);
		if (!amount.trim().equals("") && amount != null) {
			Connection conn = null;
			CallableStatement sqlStatement = null;
			try {
				conn = getConnection();
				SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date reformattedStr = myFormat.parse(dateFrom);
				Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
				String dateFromForSQL = formatter.format(reformattedStr);
				reformattedStr = myFormat.parse(dateTo);
				String dateToForSQL = formatter.format(reformattedStr);
				String callSQL = "{call  PK_TRANS.Register_Daily_Balance(?,?,?,?,?)}";
				sqlStatement = conn.prepareCall(callSQL);
				sqlStatement.setString(1, repID);
				sqlStatement.setString(2, serviceID);
				sqlStatement.setString(3, amount);
				sqlStatement.setString(4, dateFromForSQL);
				sqlStatement.setString(5, dateToForSQL);
				sqlStatement.execute();
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			} finally {
				closeStatement(sqlStatement);
				closeConnection(conn);
			}
		}
	}

	public List<TransactionDTO> get_Mobinil_BillTransactions_To(String payedPin, String DateFrom, String Dateto,
			String Service_id) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select" + " trx.TRANSACTION_ID" + ",cust1.STR_DISPLAY_NAME payer"
				+ ",to_char( sales.EXTRERNAL_ENTITY)  payed " + ",trx.amount" + ",trx.TRANSACTION_DATE " + ", ms.STATUS"
				+ ",btc.service_name" + ",CS.MERCHANTS_SHARE " + "from" + "  CUSTOMERS cust1 " + ", sales"
				+ ", TRANSACTIONS trx" + ",  txn_types txntype " + ", masary_bill_transactions btra"
				+ ", masary_bill_types btc" + ", COMMISSIONING_STRUCTURE CS" + " , MANUAL_BILL_PAYMENT ms " + "where"
				+ " trx.CUSTOMER_PAYER=? " + "and trx.CUSTOMER_PAYER=cust1.ID_CUSTOMER "
				+ "and trx.CUSTOMER_PAYED is null " + "and trx.transaction_id= sales.TRANSID  "
				+ "and trx.transaction_id=btra.transaction_id " + "and trx.txn_type_id= txntype.id_txn_type  "
				+ "and ms.request_id= btra.request_id  " + "and txntype.id_txn_type=? "
				+ "and trx.transaction_id=btra.transaction_id " + "and btc.bill_type_id=btra.service_id "
				+ "and btc.bill_type_id=? " + "and CS.BILL_TYPE_CODE=btc.bill_type_id "
				+ "and trx.transaction_date between to_date(?,'DD-MM-YYYY') "
				+ "and to_date(?,'DD-MM-YYYY HH24:MI') order by trx.TRANSACTION_DATe";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, payedPin);
			stmt.setString(2, Service_id);
			stmt.setString(3, Service_id);
			stmt.setString(4, DateFrom);
			stmt.setString(5, Dateto + " 23:59");
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("STATUS"),
						rs.getString("service_name"), rs.getDouble("MERCHANTS_SHARE"));
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	public Masary_Bill_Receipt get_Mobinil_receipt(int transaction_Id, int BTC) throws Exception {
		Connection conn = null;
		String sql = "select " + "to_char(pmt_txn.transaction_date,'DD-MM-YYYY HH24:MI')" + ",pmt_txn.customer_id"
				+ ",pmt_txn.service_id" + ",sal.extrernal_entity" + ",'2012-07-01 00:00'"
				+ ",(select nvl(mb.amount,0) from  MANUAL_BILL_PAYMENT  mb where mb.request_id =  pmt_txn.request_id) amount"
				+ ",CS.CUSTOMER_APPLIED_FEES " + "from" + "  masary_bill_transactions pmt_txn" + ", sales sal"
				+ ", transactions txn" + ", COMMISSIONING_STRUCTURE CS " + "where" + " pmt_txn.transaction_id=?"
				+ " and sal.transid=pmt_txn.transaction_id " + "and txn.transaction_id=pmt_txn.transaction_id"
				+ " and CS.BILL_TYPE_CODE=? " + "and amount < cs.maximum_amount and amount >=  cs.minimum_amount";
		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction_Id);
			stmt.setInt(2, BTC);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				receipt.setTransaction_id(transaction_Id);
				receipt.setTransaction_date(rs.getString(1));
				receipt.setCustomer_id(rs.getInt(2));
				receipt.setBTC(rs.getInt(3));
				receipt.setPhone(rs.getString(4));
				receipt.setDue_date(rs.getString(5));
				receipt.setAmount(rs.getDouble(6));
				receipt.setFees(rs.getDouble(7));
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public int INSERT_Manul_BILL_PAYMENT(int customer_id, String request_id, String bill_ref_number, int amount,
			String phone, String status, String note, String bTC) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= INSERT_MANUAL_BILL_PAYMENT(?,?,?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, customer_id);
			sqlStatement.setString(3, request_id);
			sqlStatement.setString(4, bill_ref_number);
			sqlStatement.setInt(5, amount);
			sqlStatement.setString(6, phone);
			sqlStatement.setString(7, status);
			sqlStatement.setString(8, note);
			sqlStatement.setString(9, bTC);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return 0;
	}

	public int INSERT_Vodafone_BILL_PAYMENT(int customer_id, String request_id, String bill_ref_number, int amount,
			String phone, String status, String note) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= INSERT_VODAFONE_BILL_PAYMENT(?,?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, customer_id);
			sqlStatement.setString(3, request_id);
			sqlStatement.setString(4, bill_ref_number);
			sqlStatement.setInt(5, amount);
			sqlStatement.setString(6, phone);
			sqlStatement.setString(7, status);
			sqlStatement.setString(8, note);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return 0;
	}

	public List<TransactionDTO> get_Vodafone_BillTransactions_To(String payedPin, String DateFrom, String Dateto,
			String Service_id) {
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		String sql = "select" + " trx.TRANSACTION_ID" + ",cust1.STR_DISPLAY_NAME payer"
				+ ",to_char( sales.EXTRERNAL_ENTITY)  payed " + ",trx.amount" + ",trx.TRANSACTION_DATE " + ", ms.STATUS"
				+ ",btc.service_name" + ",CS.MERCHANTS_SHARE " + "from" + "  CUSTOMERS cust1 " + ", sales"
				+ ", TRANSACTIONS trx" + ",  txn_types txntype " + ", masary_bill_transactions btra"
				+ ", masary_bill_types btc" + ", COMMISSIONING_STRUCTURE CS" + " , vodafone_bill_payment ms " + "where"
				+ " trx.CUSTOMER_PAYER=? " + "and trx.CUSTOMER_PAYER=cust1.ID_CUSTOMER "
				+ "and trx.CUSTOMER_PAYED is null " + "and trx.transaction_id= sales.TRANSID  "
				+ "and trx.transaction_id=btra.transaction_id " + "and trx.txn_type_id= txntype.id_txn_type  "
				+ "and ms.request_id= btra.request_id  " + "and txntype.id_txn_type=? "
				+ "and trx.transaction_id=btra.transaction_id " + "and btc.bill_type_id=btra.service_id "
				+ "and btc.bill_type_id=? " + "and CS.BILL_TYPE_CODE=btc.bill_type_id "
				+ "and trx.transaction_date between to_date(?,'DD-MM-YYYY') "
				+ "and to_date(?,'DD-MM-YYYY HH24:MI') order by trx.TRANSACTION_DATe";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, payedPin);
			stmt.setString(2, Service_id);
			stmt.setString(3, Service_id);
			stmt.setString(4, DateFrom);
			stmt.setString(5, Dateto + " 23:59");
			ResultSet rs = stmt.executeQuery();
			TransactionDTO trans;
			while (rs.next()) {
				trans = new TransactionDTO(rs.getString("TRANSACTION_ID"), rs.getString("payer"), rs.getString("payed"),
						rs.getDouble("amount"), rs.getString("TRANSACTION_DATE"), rs.getString("STATUS"),
						rs.getString("service_name"), rs.getDouble("MERCHANTS_SHARE"));
				list.add(trans);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return list;
	}

	// loginInfo_in Example:
	// @@ip_address=@@machine_id=@@imsi=@@device_type=@@sw_version=@@browser=@@platform=@@
	public LoginDto customerLogin(String customerID, String password, String loginInfo) {
		String preLog = "userId=[" + customerID + "], ";

		LoginDto login = new LoginDto();
		List<AgentDTO> list = new ArrayList<AgentDTO>();
		AgentDTO agent;
		AgentDTO rep;
		EmployeeDTO employee;
		long returnResult;
		ResultSet cusInfo = null;
		ResultSet supervisorRep = null;
		String customerMenu;
		logger.info(preLog + "customer Login with " + customerID);
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  Customer_Login3_NEW(?,?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, customerID);
			sqlStatement.setString(3, password);
			sqlStatement.setString(4, loginInfo);
			sqlStatement.registerOutParameter(5, OracleTypes.CURSOR); // custInfo
			sqlStatement.registerOutParameter(6, OracleTypes.CLOB); // customerMenu
			sqlStatement.registerOutParameter(7, OracleTypes.CURSOR); // supervisorReps
			sqlStatement.execute();
			returnResult = sqlStatement.getLong(1);

			logger.info(preLog + "loginResult=[" + returnResult + "]");

			if (returnResult == -1) { // Invalid User ID or Password
				login.setResponse(new AuthenticationResponse("-1", "-1", "-1"));
				return login;
			} else if (returnResult == -2) { // In-Active Customer
				login.setResponse(new AuthenticationResponse("-1", "-2", "-1"));
				return login;
			} else if (returnResult == -5) { // In-Active Customer
				login.setResponse(new AuthenticationResponse("-1", "-5", "-5"));
				return login;
			} else if (returnResult == -10) { // Invalid Device ID and Activation Code Sent to customer
				login.setResponse(new AuthenticationResponse("-10", "-10", "-10"));
				return login;
			} else if (returnResult == -30) { // Wrong Activation Code entered 3 times (Deactivate customer for 10
												// miniutes)
				login.setResponse(new AuthenticationResponse("-30", "-30", "-30"));
				return login;
			} else if (returnResult == -60) { // Wrong Activation Code entered 6 times (Deactivate customer for 15
												// miniutes)
				login.setResponse(new AuthenticationResponse("-60", "-60", "-60"));
				return login;
			} else if (returnResult == -90) { // Wrong Activation Code entered 9 times (Deactivate customer till manual
												// interaction from Customer Care representative)
				login.setResponse(new AuthenticationResponse("-90", "-90", "-90"));
				return login;
			} else if (returnResult == -50) { // No Device ID provided, Interaction with system admin is required
				login.setResponse(new AuthenticationResponse("-50", "-50", "-50"));
				return login;
			} else {
				logger.info(preLog + "Valid user ...");
				login.setSSo((int) returnResult);
				cusInfo = (ResultSet) sqlStatement.getObject(5);
				supervisorRep = (ResultSet) sqlStatement.getObject(7);
				Clob clob = sqlStatement.getClob(6);
				customerMenu = clob.getSubString(1, (int) clob.length());
				if (cusInfo.next()) {
					agent = new AgentDTO();
					agent.setSso((int) returnResult);
					agent.setPin(cusInfo.getString("ID_CUSTOMER"));
					agent.setName(cusInfo.getString("STR_DISPLAY_NAME"));
					agent.setArabicName(cusInfo.getString("STR_DISPLAY_NAME_ARABIC"));
					agent.setBalance(cusInfo.getDouble("BALANCE"));
					agent.setPhone(cusInfo.getString("MSISDN"));
					agent.setAutoAllocate(cusInfo.getString("BOL_IS_AUTO_ALLOCATE"));
					agent.setCanAddEmployee("Y".equals(cusInfo.getString("CAN_ADD_EMPLOYEE")));
					agent.setActive("Y".equals(cusInfo.getString("BOL_IS_ACTIVE")));
					agent.setIsSupervisor(cusInfo.getInt("reps_count"));
					agent.setGroupId(cusInfo.getInt("g"));
					agent.setRuleID(cusInfo.getString("RoleID"));
					agent.setParentID(cusInfo.getString("ID_PARENT"));
					agent.setWarning(cusInfo.getString("Warning_Not"));
					agent.setNotifications(cusInfo.getString("Notification_Not"));
					MasaryManager.logger.info("AgentDTO" + agent);
					login.setAgent(agent);
					customerMenu = customerMenu.replace("/topup/", CONFIG.APP_ROOT);
					login.setMenuXML(customerMenu);
					if (!agent.getRuleID().equals("8")) {
						login.setResponse(new AuthenticationResponse(agent.getPin(), agent.getRuleID(), "-1"));
					} else {
						employee = new EmployeeDTO();
						employee.setEmployeeID(agent.getPin());
						employee.setEmployeeName(agent.getName());
						employee.setEmployeeArabicName(agent.getArabicName());
						employee.setEmployeePhone(agent.getPhone());
						employee.setParentId(agent.getParentID());
						employee.setShowMyBalance(cusInfo.getInt("SHOW_MY_BALANCE") == 1);
						employee.setShowTransfer(cusInfo.getInt("TRANSFER_2_SAME") == 1);
						employee.setAllowedBalance(cusInfo.getDouble("REP_ALLOWED_BAL"));
						employee.setCurBalance(agent.getBalance());
						login.setEmp(employee);
						login.setResponse(new AuthenticationResponse(agent.getParentID(), "3", agent.getPin()));
						MasaryManager.logger.info("employee" + employee);
					}
					while (supervisorRep.next()) {
						rep = new AgentDTO();
						rep.setPin(supervisorRep.getString("ID_CUSTOMER"));
						rep.setName(supervisorRep.getString("STR_DISPLAY_NAME"));
						rep.setArabicName(supervisorRep.getString("STR_DISPLAY_NAME_ARABIC"));
						rep.setActive(supervisorRep.getString("BOL_IS_ACTIVE").equals("Y") ? true : false);
						MasaryManager.logger.info("rep" + rep);

						list.add(rep);
					}
					login.setSupervisorRep(list);
				}
				try {
					cusInfo.close();
					supervisorRep.close();
				} catch (Exception ex) {
					logger.error(preLog + "Error while closing the cursors" + ex, ex);
				}
				logger.info(login);

				return login;
			}
		} catch (SQLException e) {
			logger.error(preLog + e.getMessage(), e);
			login.setResponse(new AuthenticationResponse("-1", "-1", "-1"));
			return login;
		} catch (Exception e) {
			logger.error(preLog + e.getMessage(), e);
			login.setResponse(new AuthenticationResponse("-1", "-1", "-1"));
			return login;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public boolean checkTimeOut(String timeStamp) {
		logger.info("timeStamp:" + timeStamp);
		boolean hasOut = false;
		try {
			DateTime dt = new DateTime();
			if (DateTime.parse(timeStamp).isAfterNow()) {
				return true;
			}
			dt = DateTime.now().plusMillis(0);
			logger.info("timeStamp now:" + dt.toString());
			DateTime dt2 = DateTime.parse(timeStamp).plusMillis(25000);
			hasOut = dt.isAfter(dt2);
		} catch (Exception e) {
			logger.error("Exception=" + e.getMessage());
			return true;
		}
		return hasOut;
	}

	public List<Country> getCountries(String lang) {
		List<Country> countriesList = new ArrayList();
		ResultSet coutries = null;
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  GET_ALL_COUNTRIES(?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, lang.toUpperCase());
			sqlStatement.registerOutParameter(3, OracleTypes.CURSOR);
			sqlStatement.execute();
			long returnResult = sqlStatement.getLong(1);
			if (returnResult == 1) {
				coutries = (ResultSet) sqlStatement.getObject(3);
				while (coutries.next()) {
					Country country = new Country();
					country.setCountryISO3(coutries.getString("country_ac3"));
					country.setCountryID(coutries.getInt("COUNTRY_ID"));
					country.setCountryName(coutries.getString("string_text"));
					country.setHasGov(coutries.getString("hasGovernorate").equals("0") ? false : true);
					if (country.isHasGov()) {
						country.setGovernoratesList(getGovernorates(lang, country.getCountryID()));
					}
					countriesList.add(country);
				}
			}
			coutries.close();
		} catch (SQLException e) {
			logger.error("Error:" + e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return countriesList;
	}

	public HashMap<String, String> getGovernorates(String lang, int countryID) {
		HashMap<String, String> governoratesList = new HashMap();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select g.iso,g.country_id ,(SELECT l.string_text  " + "   FROM  main_string_lang l "
					+ "    where l.string_code = g.iso and l.lang_code = ? ) AS string_text"
					+ " from  Governorate g where g.country_id = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, lang.toUpperCase());
			stmt.setInt(2, countryID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Governorate governorate = new Governorate();
				governorate.setGovernorateISO(rs.getString("iso"));
				governorate.setGovernorateName(rs.getString("string_text"));
				governoratesList.put(governorate.getGovernorateISO(), governorate.getGovernorateName());
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error:" + e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return governoratesList;
	}

	public Getinfo getPasswordForNewCustomer(String customer_Phone) {
		String password = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		NewSYS.Getinfo info_NewSys = new Getinfo();

		try {
			conn = getConnection();
			String sql = "select password ,id_customer  from customers where msisdn=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customer_Phone);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				info_NewSys.setCustomerID(rs.getInt("id_customer"));
				info_NewSys.setPassword(rs.getString("password"));
			}

			rs.close();
		} catch (SQLException e) {
			logger.error("Error In get password For Customer phone  :" + customer_Phone + " " + e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return info_NewSys;
	}

	public long addCustomerFullDetails(CustomerProfile cp) throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		String preLog = null;
		try {

			preLog = "CustomerPhone=" + cp.getCustomerPhone() + ", nationalID=" + cp.getNationalID() + ", "
					+ "parentId=" + cp.getParentId();

			logger.info(preLog + ", add Customer Full Details " + cp.toString());

			conn = getConnection();

			if (!CustomerProfile.checkMandatoryFiledsForCreate(cp)) {
				return -1000;
			}
			String homeAddress = "@@address1=" + cp.getHomeAddress().getAddressDetails() + "@@address2=" + ""
					+ "@@country=" + "63" + "@@gov=" + cp.getHomeAddress().getGovernate() + "@@city="
					+ cp.getHomeAddress().getCity() + "@@postcode=" + "" + "@@area=" + cp.getHomeAddress().getRegion()
					+ "@@";
			String workAddress = "@@address1=" + "" + "@@address2=" + "" + "@@country=" + "" + "@@gov=" + "" + "@@city="
					+ "" + "@@postcode=" + "" + "@@area=" + "" + "@@";
			if (cp.getAccountType().equals("merchant")) {
				workAddress = "@@address1=" + cp.getWorkAddress().getAddressDetails() + "@@address2=" + ""
						+ "@@country=" + "63" + "@@gov=" + cp.getWorkAddress().getGovernate() + "@@city="
						+ cp.getWorkAddress().getCity() + "@@postcode=" + "" + "@@area="
						+ cp.getWorkAddress().getRegion() + "@@";
			}
			ArrayDescriptor arrDesc = ArrayDescriptor.createDescriptor("SERVICEARRAY",
					conn.unwrap(OracleConnection.class));
			Array arrayService = new ARRAY(arrDesc, conn.unwrap(OracleConnection.class), toArray(cp.getServiceList()));
			String callSQL = "{call ? :=   ADD_CUSTOMER_FULL_DETAILS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(2, cp.getCustomerName());
			sqlStatement.setString(3, cp.getCustomerArabicName());
			sqlStatement.setString(4, cp.getCustomerPhone());
			sqlStatement.setString(5, cp.getCanAddEmp());
			sqlStatement.setString(6, cp.getCustomerQuestion());
			sqlStatement.setString(7, cp.getCustomerAnswer());
			sqlStatement.setInt(8, cp.getParentId());
			sqlStatement.setString(9, cp.getEmail());
			sqlStatement.setString(10, cp.getNationalID());
			sqlStatement.setObject(11, homeAddress);
			sqlStatement.setArray(12, arrayService);
			sqlStatement.setString(13, cp.getAlternativeEmail());
			sqlStatement.setString(14, cp.getAlternativePhone());
			sqlStatement.setString(15, cp.getLandLinePhone());
			sqlStatement.setString(16, cp.getBirthDate());
			sqlStatement.setString(17, cp.getPreferredLang());
			sqlStatement.setObject(18, workAddress);
			sqlStatement.setString(19, cp.getAccountType());
			sqlStatement.setString(20, cp.getGender());
			sqlStatement.setString(21, cp.getShopName());
			sqlStatement.setString(22, cp.getActivityType());
			sqlStatement.setString(23, cp.getImage1Url());
			sqlStatement.setString(24, cp.getImage2Url());
			sqlStatement.setString(25, cp.getImage3Url());
			sqlStatement.setString(26, cp.getImage4Url());
			sqlStatement.setString(27, cp.getImage5Url());
			sqlStatement.setString(28, "WEB");
			sqlStatement.registerOutParameter(1, java.sql.Types.NUMERIC);

			sqlStatement.execute();
			long custId = sqlStatement.getInt(1);
			closeStatement(sqlStatement);

			logger.info(preLog + " Customer Added Successfully Customer ID = " + custId);
			return custId;
		} catch (SQLException e) {
			logger.error(preLog + ", Error:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			logger.error(preLog + ", Error:" + e.getMessage(), e);
			throw new Exception(e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}

	}

	public HashMap<String, String> getAllLanguage() {
		HashMap<String, String> languageList = new HashMap();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select id_language,str_language from  languages ";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				languageList.put(rs.getString("id_language"), rs.getString("str_language"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error:" + e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return languageList;
	}

	public Object[] toArray(ArrayList arrList) {
		Object[] listArray = new Object[arrList.size()];
		for (int i = 0; i < arrList.size(); i++) {
			listArray[i] = arrList.get(i);
		}
		return listArray;
	}

	public List<TransactionDTO> getTransactionsList(String customerID, String dateFrom, String dateTo,
			ArrayList services, String trxType, String Logid) throws Exception {
		HashMap<String, Integer> trxTypes = new HashMap<String, Integer>();
		trxTypes.put("ALL", 0);
		trxTypes.put("FROM", 1);
		trxTypes.put("TO", 2);
		List<TransactionDTO> list = new ArrayList<TransactionDTO>();
		Connection conn = null;
		CallableStatement sqlStatement = null;
		ResultSet trxList = null;
		TransactionDTO trans;
		try {
			conn = getConnection();
			ArrayDescriptor arrDesc = ArrayDescriptor.createDescriptor("SERVICEARRAY",
					conn.unwrap(OracleConnection.class)); // tomcat 7
			Array arrayService = new ARRAY(arrDesc, conn.unwrap(OracleConnection.class), toArray(services)); // tomcat 7
			String callSQL = "{call ? :=   GET_TRANSACTION_LIST(?,?,?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.CURSOR);
			sqlStatement.registerOutParameter(7, OracleTypes.DOUBLE);
			sqlStatement.registerOutParameter(8, OracleTypes.DOUBLE);
			sqlStatement.setString(2, customerID);
			sqlStatement.setString(3, dateFrom);
			sqlStatement.setString(4, dateTo);
			sqlStatement.setArray(5, arrayService);
			sqlStatement.setInt(6, trxTypes.get(trxType));
			sqlStatement.setString(9, Logid);
			sqlStatement.execute();
			trxList = (ResultSet) sqlStatement.getObject(1);
			double sumTo = sqlStatement.getDouble(7);
			double sumFrom = sqlStatement.getDouble(8);
			List<Double> l = new ArrayList<Double>();
			l.add(sumTo);
			l.add(sumFrom);
			setSumOfTrx(l);
			while (trxList.next()) {
				trans = new TransactionDTO(trxList.getString("TRANSACTION_ID"), trxList.getString("payer"),
						trxList.getString("payed"), trxList.getDouble("amount"), trxList.getString("TRANSACTION_DATE"),
						trxList.getString("str_txn_status"), trxList.getString("str_service_name"),
						trxList.getDouble("comm"));
				trans.setFees(trxList.getDouble("fees"));
				trans.setOrignal_amount(trxList.getDouble("original_amount"));
				trans.setPrintedNo(trxList.getInt("PRINTED_no"));
				trans.setIsBill(trxList.getInt("isBill"));
				trans.setTrxTypeID(trxList.getInt("TXN_TYPE_ID"));
				trans.setTotal_balance(trxList.getDouble("balance"));
				trans.setSellType(trxList.getString("sell_type"));
				trans.setLedgerID(trxList.getLong("LEDGER_ID"));
				list.add(trans);
			}
			trxList.close();
		} catch (SQLException e) {
			logger.error("Error:" + e.getMessage());
			throw new Exception(e.getMessage());

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return list;
	}

	List<Double> sumOfTrx = new ArrayList<Double>();

	public void setSumOfTrx(List<Double> l) {
		this.sumOfTrx = l;
	}

	public List<Double> getSumOfTrx() {
		return this.sumOfTrx;
	}

	public String getTrxStatus(String trxID, String trxType, String customerID) {
		String result = "";
		CallableStatement sqlStatement = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  GET_TRANSACTION_STATUS( ?,?,? ) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, trxID);
			sqlStatement.setString(3, trxType);
			sqlStatement.setString(4, customerID);
			sqlStatement.execute();
			result = sqlStatement.getString(1);
		} catch (SQLException e) {
			MasaryManager.logger.error("Exepation error=" + e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return result;
	}

	public int getSessionTimeOut(int customer_id) {
		int session_time = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select nvl((select session_time from  CUSTOMER_PROFILE where CUSTOMER_ID = ? ),4) from dual ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				session_time = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error In get Session Time For Customer :" + customer_id + " " + e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return session_time;
	}

	public Masary_Error GETMasary_Error(String Error_code, Main_Provider provider) {
		Connection conn = null;
		Masary_Error error = new Masary_Error();
		String sql = "select status_desc_en,status_desc_ar,STATUS_CODE from  masary_bill_status where FAWRY_CODE=? and provider_id=? and rownum=1";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, Error_code);
			stmt.setInt(2, provider.getPROVIDER_ID());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				error.setSTATUS_CODE(Integer.parseInt(rs.getString("STATUS_CODE")));
				error.setSTATUS_DESC_EN(rs.getString("status_desc_en"));
				error.setSTATUS_DESC_AR(rs.getString("status_desc_ar"));
			} else {
				error.setSTATUS_CODE(Integer.parseInt(Error_code));
				error.setSTATUS_DESC_EN("Please, Call Masary Customer Service");
				error.setSTATUS_DESC_AR("رجاءأ الاتّصال بمكتب مساعدة مصاري");
			}
			rs.close();
		} catch (SQLException e) {
			error.setSTATUS_CODE(Integer.parseInt(Error_code));
			error.setSTATUS_DESC_EN("Please, Call Masary Customer Service");
			error.setSTATUS_DESC_AR("رجاءأ الاتّصال بمكتب مساعدة مصاري");
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}

		return error;
	}

	public double GetDeductedAmount(int Customer_ID, int BTC, double billAmount, int provider_id) {
		double res = 0;
		CallableStatement sqlStatement = null;
		Connection conn = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  GET_DEDUCTED_AMOUNT( ? , ? , ?,? ) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, Customer_ID);
			sqlStatement.setInt(3, BTC);
			sqlStatement.setDouble(4, billAmount);
			sqlStatement.setInt(5, provider_id);
			sqlStatement.execute();
			res = sqlStatement.getDouble(1);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage().contains("Insufficient service balance to do transaction. Cannot proceed.")
					|| e.getMessage().contains("Insufficient balance to do transaction. Cannot proceed.")) {
				res = -1;
			} else if (e.getMessage().contains("Customer does not assigned specificed service.")) {
				res = -2;
			} else if (e.getMessage().contains("This customer is currently inactive.")) {
				res = -3;
			} else {
				res = -4;
			}
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return res;
	}

	public List<Main_Provider> get_providers(int BTC) {
		List<Main_Provider> providers = new ArrayList<Main_Provider>();
		Connection conn = null;
		String sql = " select prov.provider_id,prov.provider_name,prov.user_name,prov.password,prov.provider_order "
				+ "from   masary_service_provider prov " + "where prov.is_enable=1 "
				+ "and prov.provider_id in (select serv.provider_id from  masary_provider_services serv where serv.service_id=?)";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, BTC);
			ResultSet rs = stmt.executeQuery();
			Main_Provider provider;
			while (rs.next()) {
				provider = new Main_Provider();
				provider.setPROVIDER_ID(rs.getInt("provider_id"));
				provider.setMASARY_PASSWORD(rs.getString("password"));
				provider.setOrder(rs.getInt("provider_order"));
				provider.setMASARY_ID(rs.getString("user_name"));
				providers.add(provider);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return providers;
	}

	public Main_Provider get_used_provider(int BTC) {
		Main_Provider provider = new Main_Provider();
		Connection conn = null;
		String sql = " select prov.provider_id,prov.provider_name,prov.user_name,prov.password,prov.provider_order "
				+ "from   masary_service_provider prov "
				+ "where prov.provider_id=(select btc.used_provider from  masary_bill_types BTC where btc.bill_type_id=?)";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, BTC);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				provider.setPROVIDER_ID(rs.getInt("provider_id"));
				provider.setMASARY_PASSWORD(rs.getString("password"));
				provider.setOrder(rs.getInt("provider_order"));
				provider.setMASARY_ID(rs.getString("user_name"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return provider;
	}

	public void updateUsed_provider(int BTC, Main_Provider provider) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			String sql = "update  masary_bill_types set used_provider=? where bill_type_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, provider.getPROVIDER_ID());
			stmt.setInt(2, BTC);
			stmt.execute();
		} catch (Exception e) {
			logger.info(e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public void invalidateProvider(Main_Provider provider) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "update  MASARY_SERVICE_PROVIDER set IS_ENABLE=0 where PROVIDER_ID=?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, provider.getPROVIDER_ID());
			stmt.execute();
		} catch (Exception e) {
			logger.info(e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public int Insert_Bill_Provider_Log(String request_in, int providerId_in, String action_in, int CUSTOMERID_in)
			throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= INSERT_BILL_PROVIDER_LOG(?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, request_in);
			sqlStatement.setInt(3, providerId_in);
			sqlStatement.setString(4, action_in);
			sqlStatement.setInt(5, CUSTOMERID_in);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public int Update_Bill_Provider_Log(int logId_in, String response_in) throws SQLException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= UPDATE_BILL_PROVIDER_LOG(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, logId_in);
			sqlStatement.setString(3, response_in);
			sqlStatement.execute();
			return sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			throw se;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public Etisalat_Request getInquiryRequest(String phone) {
		Etisalat_Request etisalat_Request = new Etisalat_Request();
		Connection conn = null;
		String sql = null;
		sql = "select (select property_value from  configuration where property_name='Etisalat_Bill_user') username"
				+ ",(select property_value from  configuration where property_name='Etisalat_Bill_pass') pass "
				+ "from dual";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				etisalat_Request.setMsisdn(phone);
				etisalat_Request.setAmount(1);
				etisalat_Request.setOperationType(7);
				etisalat_Request.setUserName(rs.getString("username"));
				etisalat_Request.setPassword(rs.getString("pass"));
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return etisalat_Request;
	}

	public Etisalat_Request getPaymentRequest(String phone, double amount) {
		Etisalat_Request etisalat_Request = new Etisalat_Request();
		Connection conn = null;
		String sql = null;
		sql = "select (select property_value from  configuration where property_name='Etisalat_Bill_user') username"
				+ ",(select property_value from  configuration where property_name='Etisalat_Bill_pass') pass "
				+ "from dual";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				etisalat_Request.setMsisdn(phone);
				etisalat_Request.setAmount(amount);
				etisalat_Request.setOperationType(8);
				etisalat_Request.setUserName(rs.getString("username"));
				etisalat_Request.setPassword(rs.getString("pass"));
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return etisalat_Request;
	}

	public Etisalat_Request getBalanceRequest() {
		Etisalat_Request etisalat_Request = new Etisalat_Request();
		Connection conn = null;
		String sql = null;
		sql = "select (select property_value from  configuration where property_name='Etisalat_Bill_user') username"
				+ ",(select property_value from  configuration where property_name='Etisalat_Bill_pass') pass "
				+ "from dual";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				etisalat_Request.setOperationType(1);
				etisalat_Request.setUserName(rs.getString("username"));
				etisalat_Request.setPassword(rs.getString("pass"));
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return etisalat_Request;
	}

	public Masary_Error CHECK_BILL_PAYMENT_AVAILABILITY(String BRN, String Phone) {
		Masary_Error Status = new Masary_Error();
		int result = -1;
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ?:= CHECK_BILL_PAY_AVAILABILITY(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, BRN);
			sqlStatement.setString(3, Phone);
			sqlStatement.execute();
			result = sqlStatement.getInt(1);
		} catch (SQLException se) {
			MasaryManager.logger.error(se);
			Status.setSTATUS_CODE(506);
			Status.setSTATUS_DESC_EN("The service is not available now,Please try again.");
			Status.setSTATUS_DESC_AR("الخدمه معطله الان برجاء المحاوله فى وقت لاحق.");
		} finally {
			switch (result) {
			case 1:
				Status.setSTATUS_CODE(-91);
				Status.setSTATUS_DESC_EN("You can't pay without Inquiry request.");
				Status.setSTATUS_DESC_AR("لا يمكنك الدفع بدون استعلام");
				break;
			case 2:
				Status.setSTATUS_CODE(-90);
				Status.setSTATUS_DESC_EN("You can't pay twice before 6 minutes");
				Status.setSTATUS_DESC_AR("لا يمكنك الدفع مرتين فى خلال 6 دقائق");
				break;
			case 0:
				Status.setSTATUS_CODE(200);
				Status.setSTATUS_DESC_EN("Success.");
				Status.setSTATUS_DESC_AR("عمليه ناجحه.");
				break;
			default:
				Status.setSTATUS_CODE(506);
				Status.setSTATUS_DESC_EN("The service is not available now,Please try again.");
				Status.setSTATUS_DESC_AR("الخدمه معطله الان برجاء المحاوله فى وقت لاحق.");
				break;
			}
			closeStatement(sqlStatement);
			closeConnection(conn);
		}

		return Status;
	}

	public SellVoucherResponse do_Voucher_transaction(int Customer_ID, double Value, int Provider_ID, String Printing,
			String Phone_ID, String senderTime, String senderTrxID, String lang) throws Exception {
		logger.info("do_Voucher_transaction Customer_ID " + Customer_ID + " Value " + Value + " Provider_ID "
				+ Provider_ID);
		SellVoucherResponse sellVoucherResponse = new SellVoucherResponse();
		String statusMSG = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  DO_VOUCHER_TRANSACTION(?,?,?,?,?,?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.registerOutParameter(9, OracleTypes.VARCHAR);
			sqlStatement.registerOutParameter(10, OracleTypes.VARCHAR);
			sqlStatement.registerOutParameter(11, OracleTypes.VARCHAR);
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
			sellVoucherResponse.setDate(sqlStatement.getString(11));
			if (sellVoucherResponse.getTransId().equals("-1")) {
				statusMSG = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-2")) {
				statusMSG = lang.equals("ar") ? CONFIG.Balanc_Messagear : CONFIG.Balanc_Messageen;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-3")) {
				statusMSG = lang.equals("ar") ? CONFIG.voucherErrorar : CONFIG.voucherError;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-4")
					|| sellVoucherResponse.getTransId().equals("-100")) {
				statusMSG = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
				sellVoucherResponse.setStatusMSG(statusMSG);
			}
			logger.info("do_Voucher_transaction statusMSG " + sellVoucherResponse.getStatusMSG());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw (e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
			logger.info("do_Voucher_transaction Result " + sellVoucherResponse.getTransId() + " "
					+ sellVoucherResponse.getSerialNumber());
		}

		return sellVoucherResponse;
	}

	public String Vodafone_Cash_IN(int CUSTOMER_ID_IN, String CUSTOMER_PHONE_IN, double AMOUNT_IN,
			String CUSTOMER_INFO_IN, String lang) throws Exception {
		String Result = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := eMoney.PK_Vodafone.CASH_IN(?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, CUSTOMER_ID_IN);
			sqlStatement.setString(3, CUSTOMER_PHONE_IN);
			sqlStatement.setDouble(4, AMOUNT_IN);
			sqlStatement.setString(5, CUSTOMER_INFO_IN);
			sqlStatement.setString(6, lang);
			sqlStatement.execute();
			Result = sqlStatement.getString(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage().contains("ORA-20139")) {
				throw new Exception(e.getMessage());
			} else {
				throw new Exception(e.getMessage());
			}
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}

		return Result;
	}

	public String Vodafone_Cash_OUT(int CUSTOMER_ID_IN, String CUSTOMER_PHONE_IN, double AMOUNT_IN,
			String CUSTOMER_INFO_IN, String lang) throws Exception {
		String Result = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := eMoney.PK_Vodafone.CASH_OUT(?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, CUSTOMER_ID_IN);
			sqlStatement.setString(3, CUSTOMER_PHONE_IN);
			sqlStatement.setDouble(4, AMOUNT_IN);
			sqlStatement.setString(5, CUSTOMER_INFO_IN);
			sqlStatement.setString(6, lang);
			sqlStatement.execute();
			Result = sqlStatement.getString(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage().contains("ORA-20139")) {
				throw new Exception(e.getMessage());
			} else {
				throw new Exception(e.getMessage());
			}
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}

		return Result;
	}

	public String GET_RECEIPT_Vodafone_Cash(String TRANSACTION_ID) throws Exception {
		String Result = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := eMoney.PK_Vodafone.GET_RECEIPT(?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, TRANSACTION_ID);
			sqlStatement.execute();
			Result = sqlStatement.getString(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw (e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return Result;
	}

	public String VC_Transaction_Inquiry(int customer_ID, String Request_ID) throws Exception {
		String Result = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := eMoney.PK_Vodafone.TRANSACTION_INQUIRY(?,MASARY_OPERATION_ID_IN=>?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, customer_ID);
			sqlStatement.setString(3, Request_ID);
			sqlStatement.execute();
			Result = sqlStatement.getString(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw (e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return Result;
	}

	public void VC_Cancel_Transaction(String Request_ID) throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call eMoney.PK_Vodafone.CANCEL_TRANSACTION(?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(1, Request_ID);
			sqlStatement.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage().contains("ORA-20139")) {
				throw new Exception(e.getMessage());
			} else {
				throw new Exception(e.getMessage());
			}
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public VC_Commission_Structure VC_Get_Deducted_Amount() {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		Gson gson = new Gson();
		VC_Commission_Structure list = new VC_Commission_Structure();
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=eMoney.PK_Vodafone.GET_COMMESSION_STRUCTION() }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.execute();
			String Commission_List = sqlStatement.getString(1);
			list = gson.fromJson(Commission_List.toString(), VC_Commission_Structure.class);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return list;
	}

	public String VC_Get_Deducted_Amount_STR(int customerID_in, int serviceID_in, double amount2Check_in,
			int OPERATION_TYPE_IN) {
		String Result = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=eMoney.PK_Vodafone.getDeductedAmount(?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, customerID_in);
			sqlStatement.setInt(3, serviceID_in);
			sqlStatement.setDouble(4, amount2Check_in);
			sqlStatement.setInt(5, OPERATION_TYPE_IN);
			sqlStatement.execute();
			double DeductedAmount = sqlStatement.getDouble(1);
			double Fees = sqlStatement.getDouble(6);
			double Commession = sqlStatement.getDouble(7);
			Result = "DeductedAmount:" + DeductedAmount + " Fees:" + Fees + " Commession:" + Commession;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return Result;
	}

	public void setMasary_Money_Comm(VC_Commission_Structure commission_Structure, String customer_id,
			int serviceID_in) {
		Connection conn = null;
		String sql = null;
		sql = "SELECT r.commission,r.fixed_amount"
				+ "      FROM  customers c, rate_plan_service r WHERE c.id_customer = ? AND c.rate_plan_id = r.rate_plan_id AND r.service_id = ?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customer_id);
			stmt.setInt(2, serviceID_in);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				commission_Structure.setMM_COMM(rs.getDouble("commission") * -1);
				commission_Structure.setMM_Fixed(rs.getDouble("fixed_amount"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
	}

	public Vodafone_Cash_Transactions GET_VC_Transaction_List(int customer_ID, String Request_ID, String Customer_Phone,
			int index) throws Exception {
		Vodafone_Cash_Transactions List = new Vodafone_Cash_Transactions();
		Gson gson = new Gson();
		String Result = GET_VC_Transaction_List_IN(customer_ID, Request_ID, Customer_Phone, index);
		try {
			List = gson.fromJson(Result, Vodafone_Cash_Transactions.class);
			return List;
		} catch (Exception ex) {
			List = gson.fromJson(Result, Vodafone_Cash_Transactions.class);
			return List;
		}
	}

	public String GET_VC_Transaction_List_IN(int customer_ID, String Request_ID, String Customer_Phone, int index)
			throws Exception {
		String Result = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		int ParameterIndex = 2;
		try {
			conn = getConnection();
			String callSQL = "";
			callSQL = "{ call ? := EMONEY.PK_VODAFONE.GET_TRANSACTION_LIST(";
			callSQL = callSQL + " CUSTOMER_ID_IN =>   ?";
			if (!Request_ID.equals("")) {
				callSQL = callSQL + " ,REQUEST_ID_IN =>    ?";
			}
			if (!Customer_Phone.equals("")) {
				callSQL = callSQL + " ,CUSTOMER_PHONE_IN =>    ?";
			}
			callSQL = callSQL + " ,ROW_INDEX_IN => ?";
			callSQL = callSQL + ")}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, customer_ID);
			if (!Request_ID.equals("")) {
				sqlStatement.setString(++ParameterIndex, Request_ID);
			}
			if (!Customer_Phone.equals("")) {
				sqlStatement.setString(++ParameterIndex, Customer_Phone);
			}
			sqlStatement.setInt(++ParameterIndex, index);
			sqlStatement.execute();
			Result = sqlStatement.getString(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			if (e.getMessage().contains("ORA-20139")) {
				throw new Exception(e.getMessage());
			} else {
				throw new Exception(e.getMessage());
			}
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return Result;
	}

	public VC_Transaction_Status get_VC_TXN_Status(int request_id) throws Exception {
		VC_Transaction_Status status = new VC_Transaction_Status();
		String Result = "";
		Gson gson = new Gson();
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "";
			callSQL = "{ call ? := EMONEY.PK_VODAFONE.GET_TXN_STATUS(MASARY_OPERATION_ID_IN => ?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, request_id);
			sqlStatement.execute();
			Result = sqlStatement.getString(1);
			status = gson.fromJson(Result, VC_Transaction_Status.class);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw (e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return status;
	}

	public void sendMail(String recipient, String subject, String msg) throws Exception {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call  SEND_MAIL(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setString(1, recipient);
			sqlStatement.setString(2, subject);
			sqlStatement.setString(3, msg);
			sqlStatement.execute();
		} catch (Exception ex) {
			logger.error(ex);
			throw (ex);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public RatePlanDTO getCustomerCommAndFees(String service_ID, String customer_ID) {
		RatePlanDTO ratePlan = null;
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ? :=  PK_RATEPLAN.get_Customer_CommAndFees(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.CURSOR);
			sqlStatement.setString(2, customer_ID);
			sqlStatement.setString(3, service_ID);
			sqlStatement.execute();
			ResultSet rs = (ResultSet) sqlStatement.getObject(1);
			while (rs.next()) {
				ratePlan = new RatePlanDTO();
				ratePlan.setServiceID(rs.getInt(1));
				ratePlan.setCommission(rs.getDouble(2));
				ratePlan.setFixedAmount(rs.getDouble(3));
				ratePlan.setApplied_fees(rs.getDouble(4));
				ratePlan.setApplied_fees_per(rs.getDouble(5));
				ratePlan.setMasary_commission(rs.getDouble(6));
				ratePlan.setMasary_fixedAmount(rs.getDouble(7));
			}
			rs.close();
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return ratePlan;
	}

	public List<ProviderDTO> getVoucherProvidersForBulk(String customerID) throws SQLException {
		Connection conn = null;
		PreparedStatement sqlStatement = null;
		List<ProviderDTO> providers = new ArrayList<ProviderDTO>();
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select ID_V_PROVIDERS,STR_NAME,s.str_service_name,s.str_service_name_ar"
					+ " from  V_PROVIDERS,services s , customer_services cs "
					+ "where  ID_V_PROVIDERS = s.id_service and s.ACTIVE = 'Y' "
					+ " and cs.customer_id = ? and cs.service_id = s.id_service";
			sqlStatement = conn.prepareStatement(sql);
			sqlStatement.setString(1, customerID);
			rs = sqlStatement.executeQuery();
			ProviderDTO provider = null;
			while (rs.next()) {
				provider = new ProviderDTO(rs.getInt(1), rs.getString(2));
				provider.setServiceNameEn(rs.getString(3));
				provider.setServiceNameAr(rs.getString(4));
				provider.setCategories(getCatigories(rs.getInt(1)));
				providers.add(provider);
			}
			rs.close();
			return providers;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return providers;
	}

	public String getWalletEmail(String customer_id) {
		String email = "";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select nvl((select EMAIL from  CUSTOMER_PROFILE where CUSTOMER_ID = ? ),'') from dual ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customer_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				email = rs.getString(1);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error In get EMAIL For Customer :" + customer_id + " " + e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return email;
	}

	public SellVoucherResponse do_Bulk_transaction(int Customer_ID, double Value, int Provider_ID, String Printing,
			String Phone_ID, String senderTime, String senderTrxID, String lang, int count) throws Exception {
		logger.info(
				"do_Bulk_transaction Customer_ID " + Customer_ID + " Value " + Value + " Provider_ID " + Provider_ID);
		SellVoucherResponse sellVoucherResponse = new SellVoucherResponse();
		ArrayList<String> voucherPinList;
		ArrayList<String> voucherSerialList;
		String statusMSG = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;

		try {
			conn = getConnection();
			StructDescriptor vouchertruct = StructDescriptor.createDescriptor("VOUCHER_INFO",
					conn.unwrap(OracleConnection.class));
			String callSQL = "{ call ? :=  DO_BULK_VOUCHER(?,?,?,?,?,?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);

			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.registerOutParameter(9, OracleTypes.ARRAY, "VOUCHER_INFO_TABLE");
			sqlStatement.registerOutParameter(10, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, Customer_ID);
			sqlStatement.setDouble(3, Value);
			sqlStatement.setInt(4, Provider_ID);
			sqlStatement.setString(5, Printing);
			sqlStatement.setString(6, Phone_ID);
			sqlStatement.setString(7, senderTime);
			sqlStatement.setString(8, senderTrxID);
			sqlStatement.setInt(11, count);
			sqlStatement.execute();

			sellVoucherResponse.setTransId(sqlStatement.getString(1));
			sellVoucherResponse.setDate(sqlStatement.getString(10));
			if (sellVoucherResponse.getTransId().equals("-1")) {
				statusMSG = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-2")) {
				statusMSG = lang.equals("ar") ? CONFIG.Balanc_Messagear : CONFIG.Balanc_Messageen;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-3")) {
				statusMSG = lang.equals("ar") ? CONFIG.voucherErrorar : CONFIG.voucherError;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-4")
					|| sellVoucherResponse.getTransId().equals("-100")) {
				statusMSG = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else {
				voucherPinList = new ArrayList<String>();
				voucherSerialList = new ArrayList<String>();
				Array result = (Array) sqlStatement.getObject(9);
				Object[] data = (Object[]) (result).getArray();
				for (Object tmp : data) {
					Struct row = (Struct) tmp;
					voucherPinList.add(row.getAttributes()[0].toString());
					voucherSerialList.add(row.getAttributes()[1].toString());
				}
				sellVoucherResponse.setVoucherPin(voucherPinList);
				sellVoucherResponse.setVoucherSerial(voucherSerialList);
			}

			logger.info("do_Bulk_transaction statusMSG " + sellVoucherResponse.getStatusMSG());
		} catch (SQLException e) {

			logger.error(e.getMessage(), e);
			throw (e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw (e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
			logger.info("do_Bulk_transaction Result " + sellVoucherResponse.getTransId() + " "
					+ sellVoucherResponse.getSerialNumber());
		}
		return sellVoucherResponse;
	}

	public GenericSellVoucherResponse do_Generic_Bulk_transaction(int Customer_ID, double Value, int Provider_ID,
			String Printing, String Phone_ID, String senderTime, String senderTrxID, String lang, int count)
			throws Exception {
		logger.info(
				"do_Bulk_transaction Customer_ID " + Customer_ID + " Value " + Value + " Provider_ID " + Provider_ID);
		GenericSellVoucherResponse sellVoucherResponse = new GenericSellVoucherResponse();
		String statusMSG = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;

		try {
			conn = getConnection();
			StructDescriptor vouchertruct = StructDescriptor.createDescriptor("VOUCHER_INFO",
					conn.unwrap(OracleConnection.class));
			String callSQL = "{ call ? :=  DO_GENERIC_BULK_VOUCHER(?,?,?,?,?,?,?,?,?,?,?) }";
			sqlStatement = conn.prepareCall(callSQL);

			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.registerOutParameter(9, OracleTypes.ARRAY, "VOUCHER_OFFER_INFO_TABLE");
			sqlStatement.registerOutParameter(10, OracleTypes.VARCHAR);
			sqlStatement.registerOutParameter(12, OracleTypes.NUMBER);
			sqlStatement.setInt(2, Customer_ID);
			sqlStatement.setDouble(3, Value);
			sqlStatement.setInt(4, Provider_ID);
			sqlStatement.setString(5, Printing);
			sqlStatement.setString(6, Phone_ID);
			sqlStatement.setString(7, senderTime);
			sqlStatement.setString(8, senderTrxID);
			sqlStatement.setInt(11, count);
			sqlStatement.execute();

			sellVoucherResponse.setTransId(sqlStatement.getString(1));
			sellVoucherResponse.setDate(sqlStatement.getString(10));
			if (sellVoucherResponse.getTransId().equals("-1")) {
				statusMSG = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-2")) {
				statusMSG = lang.equals("ar") ? CONFIG.Balanc_Messagear : CONFIG.Balanc_Messageen;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-3")) {
				statusMSG = lang.equals("ar") ? CONFIG.voucherErrorar : CONFIG.voucherError;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-4")
					|| sellVoucherResponse.getTransId().equals("-100")) {
				statusMSG = lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-666")
					|| sellVoucherResponse.getTransId().equals("-666")) {
				statusMSG = lang.equals("ar") ? CONFIG.errorVoucherTransactionAR : CONFIG.errorVoucherTransactionEn;
				sellVoucherResponse.setStatusMSG(statusMSG);
			} else if (sellVoucherResponse.getTransId().equals("-50")
					|| sellVoucherResponse.getTransId().equals("-50")) {
				statusMSG = lang.equals("ar") ? CONFIG.errorVoucherLimitAR : CONFIG.errorVoucherLimitEn;
				sellVoucherResponse.setStatusMSG(statusMSG);

			} else if (sellVoucherResponse.getTransId().equals("-41")
					|| sellVoucherResponse.getTransId().equals("-41")) {
				statusMSG = lang.equals("ar") ? CONFIG.balanceLockedErrorMessageAr : CONFIG.balanceLockedErrorMessageEn;
				sellVoucherResponse.setStatusMSG(statusMSG);

			} else {
				Array result = (Array) sqlStatement.getObject(9);
				Object[] data = (Object[]) (result).getArray();
				for (Object tmp : data) {
					Struct row = (Struct) tmp;
					GenericVoucherData voucherData = new GenericVoucherData();
					voucherData.setVoucherPin(row.getAttributes()[0].toString());
					voucherData.setVoucherSn(row.getAttributes()[1].toString());
					voucherData.setAmount(Double.parseDouble(row.getAttributes()[2].toString()));
					voucherData.setIsOffer(Integer.parseInt(row.getAttributes()[3].toString()));
					sellVoucherResponse.getVouchersData().add(voucherData);
				}

				BulkVoucherDTO bvdto = new BulkVoucherDTO();
				bvdto.setVoucherCount(sqlStatement.getInt(12));
				sellVoucherResponse.setVoucherInfo(bvdto);
			}
			logger.info("do_Bulk_transaction statusMSG " + sellVoucherResponse.getStatusMSG());
			return sellVoucherResponse;
		} catch (SQLException e) {
			if (e.getMessage().contains("ORA-20139")) {
				throw new Exception(e.getMessage());
			} // added by hammad for orange capping
			else if (e.getMessage().contains("ORA-20551")) {
				throw new Exception(
						lang.equals("ar") ? CONFIG.OrangeCappingExceedLimitAr : CONFIG.OrangeCappingExceedLimit);
			} else {
				logger.error(e.getMessage(), e);
				throw (e);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw (e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
			logger.info("do_Bulk_transaction Result " + sellVoucherResponse.getTransId() + " "
					+ sellVoucherResponse.getSerialNumber());
		}

	}

	public long SendBulkSMS(String customerID, String message, ArrayList mobiles, String language, String filePath,
			int countMobiles) throws Exception {
		long transID = 0;
		Connection conn = null;
		CallableStatement sqlStatement = null;
		logger.info("Send Bulk SMS transaction " + message + " lang " + language + " count mobile " + countMobiles);

		try {
			conn = getConnection();
			ArrayDescriptor arrDesc = ArrayDescriptor.createDescriptor("BULK_SMS.MOBILES",
					conn.unwrap(OracleConnection.class));// tomcat 7
			Array arrayMobiles = new ARRAY(arrDesc, conn.unwrap(OracleConnection.class), toArray(mobiles)); // tomcat 7
			String callSQL = "{call ? :=  BULK_SMS.PK_SEND_BULK_SMS.DO_Transaction_Bulk_SMS(?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);

			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, message);
			sqlStatement.setString(3, customerID);
			sqlStatement.setString(4, filePath);
			sqlStatement.setArray(5, arrayMobiles);
			sqlStatement.setString(6, language);
			sqlStatement.setInt(7, countMobiles);
			sqlStatement.execute();
			transID = sqlStatement.getLong(1);
			return transID;
		} catch (SQLException e) {
			logger.error("Bulk SMS Error:" + e.getMessage());
			throw new Exception(e.getMessage());

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public double getBulk_SMS_DeductedAmount(String message, String language, int countMobiles) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		logger.info("Get Deducted Amount of " + message + " lang " + language + " count mobile " + countMobiles);
		try {
			conn = getConnection();
			String callSQL = "{call ? :=  BULK_SMS.PK_SEND_BULK_SMS.Get_Deducted_Amount(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.DOUBLE);
			sqlStatement.setString(2, message);
			sqlStatement.setString(3, language);
			sqlStatement.setInt(4, countMobiles);
			sqlStatement.execute();
			return sqlStatement.getDouble(1);
		} catch (SQLException e) {
			logger.error("Get Deducted Amount of Bulk SMS Error:" + e.getMessage());
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return 0;
	}

	public List<BulkSMSReportDTO> getBulkSMSReport(long transID) throws Exception {
		CallableStatement sqlStatement = null;
		Connection conn = null;
		List<BulkSMSReportDTO> bulkSMSReportlist = new ArrayList<BulkSMSReportDTO>();
		try {
			conn = getConnection();
			String callSQL = "{ call BULK_SMS.PK_SEND_BULK_SMS.BULK_SMS_REPORT(?,?) }";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setLong(1, transID);
			sqlStatement.registerOutParameter(2, OracleTypes.CURSOR);
			sqlStatement.execute();
			ResultSet rs = (ResultSet) sqlStatement.getObject(2);
			BulkSMSReportDTO bulkSMSReport = null;
			while (rs.next()) {
				bulkSMSReport = new BulkSMSReportDTO();
				bulkSMSReport.setRequestID(rs.getInt("REQUEST_ID"));
				bulkSMSReport.setMessage(rs.getString("MESSAGE"));
				bulkSMSReport.setCountMobiles(rs.getInt("COUNT_MOBILE"));
				bulkSMSReport.setResponse(rs.getString("RESPONSE"));
				bulkSMSReport.setMsisdn(rs.getString("MSISDN"));
				bulkSMSReport.setStatusCode(rs.getInt("STATUS_CODE"));
				bulkSMSReport.setNretry(rs.getInt("N_RETRY"));
				bulkSMSReport.setRefundTxn(rs.getInt("REFUND_TRX"));
				bulkSMSReportlist.add(bulkSMSReport);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return bulkSMSReportlist;
	}

	public String getBulkSMSPrice() {
		Connection conn = null;
		String sql = null;
		sql = "SELECT S.PRICE FROM  SERVICES S WHERE S.ID_SERVICE = 91";
		PreparedStatement stmt = null;
		String price = "";
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				price = String.valueOf(rs.getDouble(1));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return price;
	}

	synchronized public Vodafone_Bill_Response PayVodafoneBill(String custID, String msisdn, String amount,
			String senderTime, String senderTrxID) throws Exception {
		logger.info("Vodafone Bill " + msisdn + " amount " + amount + " from " + custID);
		Connection conn = null;
		CallableStatement sqlStatement = null;
		Vodafone_Bill_Response vodafone_Bill_Response = new Vodafone_Bill_Response();
		try {

			logger.info("Try to get a connection..");
			conn = getConnection();
			logger.info("A connection is fetched..");
			logger.info("Setting parameters and preparing the SQL statement..");
			sqlStatement = null;
			String callSQL = ("{call ?:=  DO_TOPUP_TRANSACTION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER); // operation id
			sqlStatement.setInt(2, Integer.parseInt(custID)); // customerID_in
			sqlStatement.setString(3, msisdn); // mSISDNID_in
			sqlStatement.setFloat(4, Float.parseFloat(amount)); // amount_in
			sqlStatement.setInt(5, 111);// serviceID_in
			sqlStatement.registerOutParameter(6, OracleTypes.NUMBER); // taxAmount_out
			sqlStatement.registerOutParameter(7, OracleTypes.NUMBER); // RESPONSE_STATUS_code
			sqlStatement.registerOutParameter(8, OracleTypes.VARCHAR); // english_message
			sqlStatement.registerOutParameter(9, OracleTypes.VARCHAR); // arabic_message
			sqlStatement.registerOutParameter(10, OracleTypes.VARCHAR); // savedResult
			sqlStatement.registerOutParameter(11, OracleTypes.NUMBER); // transaction_ID
			sqlStatement.registerOutParameter(12, OracleTypes.NUMBER); // response_Code
			sqlStatement.setInt(13, 0); // isUSSD_in
			sqlStatement.setString(14, "AR"); // isUSSD_in
			sqlStatement.setString(15, null); // senderDateTime_in
			sqlStatement.setString(16, null); // senderTransID_in

			logger.info("Before SQL execution..");
			sqlStatement.execute();
			logger.info("After SQL execution..");

			String operationId = sqlStatement.getString(1);
			logger.info("Operation ID " + operationId);

			float taxAmount = sqlStatement.getFloat(6);

			int code = sqlStatement.getInt(7);
			logger.info("Status Code " + code);
			vodafone_Bill_Response.setCode(code);

			String englishMsg = sqlStatement.getString(8);
			logger.info("English Msg " + englishMsg);
			vodafone_Bill_Response.setEnglishMessage(englishMsg);

			String arabicMsg = sqlStatement.getString(9);
			logger.info("Arabic Msg " + arabicMsg);
			vodafone_Bill_Response.setArabicMessage(arabicMsg);

			String result = sqlStatement.getString(10);
			logger.info("Result " + result);
			vodafone_Bill_Response.setResult(result);

			int transId = sqlStatement.getInt(11);
			logger.info("Transaction " + transId);
			vodafone_Bill_Response.setTransaction_id(transId);
			if (operationId.equals("-1")) {
				throw new Exception(CONFIG.MobinilTopupRepetedError);
			}

			if (operationId.equals("-100")) {
				throw new Exception(CONFIG.errorTransaction);
			}

			if (operationId.equals("-80")) {
				throw new Exception(CONFIG.errorTransaction);
			}

			if (vodafone_Bill_Response.getEnglishMessage().trim().toLowerCase().contains("successful")) {
				float[] results = new float[2];
				results[0] = transId;
				results[1] = taxAmount;
				vodafone_Bill_Response.setTransaction_id(transId);
				return vodafone_Bill_Response;
			}

			logger.error(vodafone_Bill_Response.getEnglishMessage());
			throw new Exception(vodafone_Bill_Response.getEnglishMessage());

		} catch (Exception ex) {
			logger.error(ex);
			if (ex.getMessage().contains("ORA-20139")) {
				throw new Exception(ex.getMessage());

			} else {
				vodafone_Bill_Response.setResult(ex.getMessage());
				return vodafone_Bill_Response;
			}

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public Bill_Response Inquiry(Bill_Request request) {
		Bill_Response response = new Bill_Response();
		Gson gson = new Gson();
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := Bills.ONLINE_BILLS.INQUIRY(REQUEST_IN => ?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, gson.toJson(request));
			logger.info(gson.toJson(request));
			sqlStatement.execute();
			String result = sqlStatement.getString(1);
			response = gson.fromJson(result, Bill_Response.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setSTATUS_CODE(-999);
			response.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
		} finally {

			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return response;

	}

	public Bill_Response PAYMENT(Bill_Request request) {
		Bill_Response response = new Bill_Response();
		Gson gson = new Gson();
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := Bills.ONLINE_BILLS.PAYMENT(REQUEST_IN => ?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, gson.toJson(request));
			sqlStatement.execute();
			String result = sqlStatement.getString(1);
			response = gson.fromJson(result, Bill_Response.class);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			response.setSTATUS_CODE(-999);
			response.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return response;
	}

	public Bill_Response Commession_Inquiry(Bill_Request request) {
		Bill_Response response = new Bill_Response();
		Gson gson = new Gson();
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := Bills.ONLINE_BILLS.COMMESSION_INQUIRY(REQUEST_IN => ?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, gson.toJson(request));
			sqlStatement.execute();
			String result = sqlStatement.getString(1);
			response = gson.fromJson(result, Bill_Response.class);

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			response.setSTATUS_CODE(-999);
			response.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
		} finally {
			logger.info(response.toString());
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return response;

	}

	synchronized public String BlaBla_TopUp(String custID, String blablaAccount, String amount, String amount_dolar,
			String senderTime, String senderTrxID) throws Exception {
		logger.info("BlaBlaTopUp " + blablaAccount + " amount " + amount + " from " + custID);
		Connection conn = null;
		CallableStatement sqlStatement = null;
		VodafoneHttpResponse response = null;
		try {
			logger.info("Try to get a connection..");
			conn = getConnection();
			logger.info("A connection is fetched..");
			logger.info("Setting parameters and preparing the SQL statement..");
			sqlStatement = null;
			String callSQL = ("{call ?:=  DO_TOPUP_TRANSACTION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER); // operation id
			sqlStatement.setInt(2, Integer.parseInt(custID)); // customerID_in
			sqlStatement.setString(3, blablaAccount); // mSISDNID_in
			sqlStatement.setDouble(4, Double.parseDouble(amount)); // amount_in
			sqlStatement.setInt(5, 33); // serviceID_in
			sqlStatement.registerOutParameter(6, OracleTypes.NUMBER); // taxAmount_out
			sqlStatement.registerOutParameter(7, OracleTypes.NUMBER); // RESPONSE_STATUS_code
			sqlStatement.registerOutParameter(8, OracleTypes.VARCHAR); // english_message
			sqlStatement.registerOutParameter(9, OracleTypes.VARCHAR); // arabic_message
			sqlStatement.registerOutParameter(10, OracleTypes.VARCHAR); // savedResult
			sqlStatement.registerOutParameter(11, OracleTypes.NUMBER); // transaction_ID
			sqlStatement.registerOutParameter(12, OracleTypes.NUMBER); // response_Code
			sqlStatement.setInt(13, 0); // isUSSD_in
			sqlStatement.setString(14, "EN"); // lANG_in
			sqlStatement.setString(15, senderTime); // senderDateTime_in
			sqlStatement.setString(16, senderTrxID); // senderTransID_in
			sqlStatement.setDouble(17, Double.parseDouble(amount_dolar));

			logger.info("Before SQL execution..");
			sqlStatement.execute();
			logger.info("After SQL execution..");

			String operationId = sqlStatement.getString(1);
			logger.info("Operation ID " + operationId);

			float taxAmount = sqlStatement.getFloat(6);

			response = new VodafoneHttpResponse();
			int code = sqlStatement.getInt(7);
			logger.info("Status Code " + code);
			response.setCode(code);

			String englishMsg = sqlStatement.getString(8);
			logger.info("English Msg " + englishMsg);
			response.setEnglishMessage(englishMsg);

			String arabicMsg = sqlStatement.getString(9);
			logger.info("Arabic Msg " + arabicMsg);
			response.setArabicMessage(arabicMsg);

			String result = sqlStatement.getString(10);
			logger.info("Result " + result);
			response.setResult(result);

			int transId = sqlStatement.getInt(11);
			logger.info("Transaction " + transId);

			if (operationId.equals("-1")) {
				throw new Exception(CONFIG.MobinilTopupRepetedError);
			}

			if (operationId.equals("-100")) {
				throw new Exception(CONFIG.errorTransaction);
			}

			if (operationId.equals("-80")) {
				throw new Exception(CONFIG.errorTransaction);
			}

			if (response.getEnglishMessage().trim().toLowerCase().contains("successful")) {
				float[] results = new float[2];
				results[0] = transId;
				results[1] = taxAmount;
				return "" + transId;
			}
			logger.error(response.getEnglishMessage());
			throw new Exception(CONFIG.transactionError);
		} catch (Exception ex) {
			logger.error(ex);
			throw ex;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public CashUResponse GetInfoAccount(CashURequest request) {
		CashUResponse response = new CashUResponse();
		Gson gson = new Gson();
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := Bills.PK_CASHU.GETACCOUNTINFO(REQUEST_IN => ?)}";

			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, gson.toJson(request));
			sqlStatement.execute();
			String result = sqlStatement.getString(1);
			response = gson.fromJson(result, CashUResponse.class);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			response.setSTATUS_CODE(-999);
			response.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return response;
	}

	public CashUResponse TopUpCASHU(CashURequest request) {
		CashUResponse response = new CashUResponse();
		Gson gson = new Gson();
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := Bills.PK_CASHU.Do_TopUp(REQUEST_IN => ?)}";

			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, gson.toJson(request));
			sqlStatement.execute();
			String result = sqlStatement.getString(1);
			response = gson.fromJson(result, CashUResponse.class);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			response.setSTATUS_CODE(-999);
			response.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return response;
	}

	public double getCustomerServiceBal(String customerID, int serviceID) {
		logger.info("getCustomerServiceBal with " + customerID + " " + serviceID);
		double serviceBalance = 0;
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? :=  Get_Customer_Service_Balance2(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, customerID);
			sqlStatement.setInt(3, serviceID);
			sqlStatement.execute();
			serviceBalance = sqlStatement.getDouble(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return serviceBalance;
	}

	public List<Object[]> getAgentTree(String agentID) {
		logger.info("getAgentTree with " + agentID);
		Connection conn = null;
		PreparedStatement stmt = null;
		List<Object[]> customersID = new ArrayList();
		Object[] info;
		try {
			conn = getConnection();
			String sql = "Select U.Id_Customer,U.STR_DISPLAY_NAME " + "From  Customers U, Customers Cu "
					+ "WHERE  u.ID_CUSTOMER = cu.ID_CUSTOMER " + "start with u.ID_CUSTOMER = ? "
					+ "CONNECT BY PRIOR cu.ID_CUSTOMER = cu.id_parent";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, agentID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				info = new Object[] { rs.getLong(1), rs.getString(2) };
				customersID.add(info);
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return customersID;
	}

	public List<Integer> getAmounts(int service_ID) {
		List<Integer> values = new ArrayList<Integer>();
		Connection conn = null;
		String sql = "select value from BILLS.CHARGE_BUNDLE_VALUES where SERVICE_ID=?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, service_ID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				values.add(rs.getInt(1));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return values;

	}

	public int getquota(int amount, int serviceId) {
		int result = 0;
		Connection conn = null;
		String sql = "select BUNDLE from BILLS.CHARGE_BUNDLE_VALUES where SERVICE_ID=? and VALUE=?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, serviceId);
			stmt.setInt(2, amount);
			ResultSet rs = stmt.executeQuery();
			result = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return result;
	}

	public Masary_Bill_Receipt getOnlineBillsReceipt(int transaction_Id) throws Exception {

		Bill_Response response = new Bill_Response();
		Bill_Response response_P = new Bill_Response();
		Gson gson = new Gson();
		Connection conn = null;
		String sql = "select bb.response, bb.response_date, CUSTOMER_ID, SERVICE_ID, CUSTOMER_PHONE, "
				+ "(select ll.response from BILLS.logs ll where ll.transaction_id = ?) Response_P "
				+ "from BILLS.logs bb " + "where bb.bill_reference_number = "
				+ "(select b.bill_reference_number from BILLS.logs b where b.transaction_id = ?) "
				+ "and bb.operation_id = 1";
		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction_Id);
			stmt.setInt(2, transaction_Id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				response = gson.fromJson(rs.getString(1), Bill_Response.class);
				response_P = gson.fromJson(rs.getString(6), Bill_Response.class);

				receipt.setTransaction_id(transaction_Id);

				receipt.setTransaction_date(rs.getString(2));
				receipt.setCustomer_id(rs.getInt(3));
				receipt.setBTC(rs.getInt(4));
				receipt.setPhone(rs.getString(5));
				receipt.setDue_date(response.getBILL_DATE());
				receipt.setAmount(response.getAMOUNT());
				receipt.setFees(response.getFEES());
				receipt.setCustomerName(response.getCUSTOMER_NAME());
				receipt.setProviderResponse(response_P);

				logger.info("getreceipt info for print " + receipt.getTransaction_id() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getDonationReceipt(int transaction_Id) throws Exception {

		Bill_Response response = new Bill_Response();
		Bill_Response response_P = new Bill_Response();
		Gson gson = new Gson();
		Connection conn = null;
		String sql = "SELECT tt.TRANSACTION_ID,bb.response_date,CUSTOMER_ID,s.STR_SERVICE_NAME_AR AS SERVICE_NAME,SERVICE_ID,CUSTOMER_PHONE,"
				+ "tj.ORIGINAL_AMOUNT,tj.TAX_AMOUNT,tj.APPLIED_FEES,tt.AMOUNT "
				+ "FROM BILLS.logs bb,PBXMOB.SERVICES s,PBXMOB.TRANSACTION_JOURNAL tj,PBXMOB.TRANSACTIONS tt "
				+ "WHERE s.ID_SERVICE = bb.service_id AND tj.TRANSACTION_ID = bb.TRANSACTION_ID "
				+ "AND tt.TRANSACTION_ID = bb.TRANSACTION_ID AND bb.TRANSACTION_ID = ?";
		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction_Id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// response = gson.fromJson(rs.getString("response"), Bill_Response.class);
				// response_P = gson.fromJson(rs.getString("Response_P"), Bill_Response.class);

				receipt.setTransaction_id(transaction_Id);

				receipt.setTransaction_date(rs.getString("response_date"));
				receipt.setCustomer_id(rs.getInt("CUSTOMER_ID"));
				receipt.setBTC(rs.getInt("SERVICE_ID"));
				receipt.setPhone(rs.getString("CUSTOMER_PHONE"));
				receipt.setDue_date(rs.getString("response_date"));
				receipt.setTax(Double.valueOf(rs.getString("TAX_AMOUNT")));
				receipt.setTotalDueAmount(Double.valueOf(rs.getString("ORIGINAL_AMOUNT")));
				receipt.setTransaction_id(Long.parseLong(rs.getString("TRANSACTION_ID")));
				receipt.setTransactionAmount(Double.valueOf(rs.getString("AMOUNT")));
				receipt.setFees(Double.valueOf(rs.getString("APPLIED_FEES")));
				// receipt.setCustomerName(response.getCUSTOMER_NAME());
				// receipt.setProviderResponse(response_P);
				receipt.setPayment_type_name(rs.getString("SERVICE_NAME"));

				logger.info("getreceipt info for print " + receipt.getTransaction_id() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getNewSystemBillsReceipt(long ledger_id) throws Exception {
		Connection conn = null;
		String sql = "select e.account_id, To_Char(e.UPDATE_DATE,'dd-mm-yyyy hh12:mi:ss AM'), e.land_line, e.NEW_EXPIRY_DATE, e.global_trx_id,"
				+ "e.BILL_AMOUNT, e.Applied_fees, e.CUSTOMER_NAME, e.RECEIPT_NUMBER from  TEDATA_BILLS.TEDATA_TRANSACTIONS@DBLEDGER e where e.ledger_trx_id= ?";
		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		Bill_Response billResponse = new Bill_Response();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, ledger_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				receipt.setCustomer_id(rs.getInt(1));
				receipt.setTransaction_date(rs.getString(2));
				receipt.setPhone(rs.getString(3));
				receipt.setDue_date(rs.getString(4));
				receipt.setTransaction_id(rs.getLong(5));
				receipt.setAmount(rs.getDouble(6));
				receipt.setFees(rs.getInt(7));
				receipt.setCustomerName(rs.getString(8));
				billResponse.setPROVIDER_TRANSACTION_ID(rs.getString(9));
				receipt.setProviderResponse(billResponse);

				logger.info("getreceipt info for reprint " + receipt.getTransaction_id() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getNewSystemPetrotradeBillsReceipt(long ledger_id) throws Exception {
		Connection conn = null;
		String sql = "select  p.GLOBAL_TRX_ID,To_Char(P.UPDATE_DATE,'ddmmyyyy hh24:mi:ss'),   "
				+ " p.AMOUNT, p.TO_BE_PAID, p.APPLIED_FEES, p.TAX, p.SUBSCRIBER_NAME, p.SUBSCRIBER_NUMBER, p.BILLS_NUMBER  from  PETROTRADE.PETRO_TRADE_TRANSACTIONS@DBLEDGER p where p.LEDGER_TRX_ID=?";

		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, ledger_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				receipt.setGlobalTrxID(rs.getString(1));
				receipt.setTransaction_date(rs.getString(2));

				receipt.setAmount(rs.getDouble(3));
				receipt.setToBePaid(rs.getDouble(4));
				receipt.setFees(rs.getDouble(5));
				receipt.setTax(rs.getDouble(6));

				receipt.setSubscriberName(rs.getString(7));
				receipt.setSubscriberNumber(rs.getString(8));
				receipt.setBillsNumber(rs.getDouble(9));

				logger.info("getreceipt info for reprint " + receipt.getGlobalTrxID() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	/*
	 * 
	 * public Masary_Bill_Receipt getNewSystemBeINSportsReceipt(int ledger_id)
	 * throws Exception { Connection conn = null; String sql =
	 * "select  p.GLOBAL_TRX_ID,To_Char(P.UPDATE_DATE,'dd-mm-yyyy hh24:mi:ss') update_date,"
	 * +
	 * "     p.ACCOUNT_ID, p.TO_BE_PAID, p.APPLIED_FEES, p.PAYEMNT_AMOUNT, p.TRANSACTION_AMOUNT,"
	 * + "     p.MERCHANT_COMMISSION, p.MOBILE_NUMBER , abu.PAYMENT_TYPE_NAME" +
	 * "     from  ABOELRIESH.ABOELRIESH_TRANSACTIONS@DBLEDGER p , ABOELRIESH.ABOELRIESH_PAYMENT_TYPE@DBLEDGER abu where p.LEDGER_TRX_ID=?"
	 * + "     and  p.PAYEMNT_TYPE_ID = abu.PAYMENT_TYPE_ID ";
	 * 
	 * PreparedStatement stmt = null; Masary_Bill_Receipt receipt = new
	 * Masary_Bill_Receipt(); try { conn = getConnection(); stmt =
	 * conn.prepareStatement(sql); stmt.setInt(1, ledger_id); ResultSet rs =
	 * stmt.executeQuery();
	 * 
	 * while (rs.next()) { receipt.setGlobalTrxID(rs.getString("GLOBAL_TRX_ID"));
	 * receipt.setTransaction_date(rs.getString("update_date"));
	 * 
	 * receipt.setAccountId(rs.getInt("ACCOUNT_ID"));
	 * receipt.setToBePaid(rs.getDouble("TO_BE_PAID"));
	 * receipt.setFees(rs.getDouble("APPLIED_FEES"));
	 * receipt.setAmount(rs.getDouble("PAYEMNT_AMOUNT"));
	 * receipt.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
	 * receipt.setMerchantCommission(rs.getDouble("MERCHANT_COMMISSION"));
	 * receipt.setPhone(rs.getString("MOBILE_NUMBER"));
	 * receipt.setPayment_type_name(rs.getString("PAYMENT_TYPE_NAME"));
	 * 
	 * logger.info("getreceipt info for reprint " + receipt.getGlobalTrxID()); }
	 * rs.close(); } catch (Exception e) { logger.error(e.getMessage(), e); }
	 * finally { closeStatement(stmt); closeConnection(conn); } return receipt; }
	 */
	public Masary_Bill_Receipt getNewSystemAboElRishReceipt(long ledger_id) throws Exception {
		Connection conn = null;
		String sql = "select  p.GLOBAL_TRX_ID,To_Char(P.UPDATE_DATE,'dd-mm-yyyy hh24:mi:ss') update_date,"
				+ "     p.ACCOUNT_ID, p.TO_BE_PAID, p.APPLIED_FEES, p.PAYEMNT_AMOUNT, p.TRANSACTION_AMOUNT,"
				+ "     p.MERCHANT_COMMISSION, p.MOBILE_NUMBER , abu.PAYMENT_TYPE_NAME"
				+ "     from  ABOELRIESH.ABOELRIESH_TRANSACTIONS@DBLEDGER p , ABOELRIESH.ABOELRIESH_PAYMENT_TYPE@DBLEDGER abu where p.LEDGER_TRX_ID=?"
				+ "     and  p.PAYEMNT_TYPE_ID = abu.PAYMENT_TYPE_ID ";

		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, ledger_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				receipt.setGlobalTrxID(rs.getString("GLOBAL_TRX_ID"));
				receipt.setTransaction_date(rs.getString("update_date"));

				receipt.setAccountId(rs.getInt("ACCOUNT_ID"));
				receipt.setToBePaid(rs.getDouble("TO_BE_PAID"));
				receipt.setFees(rs.getDouble("APPLIED_FEES"));
				receipt.setAmount(rs.getDouble("PAYEMNT_AMOUNT"));
				receipt.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
				receipt.setMerchantCommission(rs.getDouble("MERCHANT_COMMISSION"));
				receipt.setPhone(rs.getString("MOBILE_NUMBER"));
				receipt.setPayment_type_name(rs.getString("PAYMENT_TYPE_NAME"));

				logger.info("getreceipt info for reprint " + receipt.getGlobalTrxID());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getNewSystemSECIReceipt(long ledger_id) throws Exception {
		Connection conn = null;
		String sql = "select  p.GLOBAL_TRX_ID,To_Char(P.UPDATE_DATE,'dd-mm-yyyy hh24:mi:ss') update_date,   "
				+ " p.ACCOUNT_ID, p.TO_BE_PAID, p.APPLIED_FEES, p.PAYEMNT_AMOUNT, p.TRANSACTION_AMOUNT, p.MERCHANT_COMMISSION, p.MOBILE_NUMBER  from  SECI.SECI_TRANSACTIONS@DBLEDGER p where p.LEDGER_TRX_ID=?";

		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, ledger_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				receipt.setGlobalTrxID(rs.getString("GLOBAL_TRX_ID"));
				receipt.setTransaction_date(rs.getString("update_date"));

				receipt.setAccountId(rs.getInt("ACCOUNT_ID"));
				receipt.setToBePaid(rs.getDouble("TO_BE_PAID"));
				receipt.setFees(rs.getDouble("APPLIED_FEES"));
				receipt.setAmount(rs.getDouble("PAYEMNT_AMOUNT"));
				receipt.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
				receipt.setMerchantCommission(rs.getDouble("MERCHANT_COMMISSION"));
				receipt.setPhone(rs.getString("MOBILE_NUMBER"));

				logger.info("getreceipt info for reprint " + receipt.getGlobalTrxID());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getNewSystemPetrotradeCounterReceipt(long ledger_id) throws Exception {
		Connection conn = null;
		String sql = "select  p.GLOBAL_TRX_ID,To_Char(P.UPDATE_DATE,'ddmmyyyy hh24:mi:ss'),   "
				+ " p.TRANSACTION_AMOUNT, p.TO_BE_PAID, p.APPLIED_FEES, p.TAX, p.SUBSCRIBER_NAME, p.SUBSCRIBER_NUMBER ,p.LEDGER_TRX_ID,p.CURRENT_READING from  PETROTRADE.PETRO_TRADE_REGISTRATION@DBLEDGER p where p.LEDGER_TRX_ID=?";

		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, ledger_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				receipt.setGlobalTrxID(rs.getString(1));
				receipt.setTransaction_date(rs.getString(2));

				receipt.setAmount(rs.getDouble(3));
				receipt.setToBePaid(rs.getDouble(4));
				receipt.setFees(rs.getDouble(5));
				receipt.setTax(rs.getDouble(6));

				receipt.setSubscriberName(rs.getString(7));
				receipt.setSubscriberNumber(rs.getString(8));

				receipt.setLedgerTrxId(rs.getString(9));
				receipt.setCurrentReading(rs.getInt(10));

				logger.info("getreceipt info for reprint " + receipt.getGlobalTrxID() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getNewSystemAlexWaterReceipt(long ledger_id) throws Exception {
		Connection conn = null;
		String sql = "select  aw.LEDGER_TRX_ID,To_Char(aw.INSERT_DATE,'ddmmyyyy hh24:mi:ss'),   "
				+ " aw.TOTAL_DUE_AMOUNT, aw.TO_BE_PAID, aw.APPLIED_FEES, "
				+ "aw.TAX, aw.ACCOUNT_ID, aw.ELEC_NO, aw.BILLINGRUN_NAME ,aw.CUSTOMER_NAME ,aw.MERCHANT_COMMISSION ,aw.TRANSACTION_AMOUNT "
				+ "from  ALEXWATER.ALEXWATER_TRANSACTIONS@DBLEDGER aw where aw.LEDGER_TRX_ID=?";

		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, ledger_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				receipt.setLedgerTrxId(rs.getString("LEDGER_TRX_ID"));
				receipt.setTransaction_date(rs.getString(2));

				receipt.setTotalDueAmount(rs.getDouble("TOTAL_DUE_AMOUNT"));
				receipt.setToBePaid(rs.getDouble("TO_BE_PAID"));
				receipt.setFees(rs.getDouble("APPLIED_FEES"));
				receipt.setTax(rs.getDouble("TAX"));
				receipt.setCustomerName(rs.getString("CUSTOMER_NAME"));
				receipt.setElecNo(rs.getString("ELEC_NO"));
				receipt.setMerchantCommission(rs.getDouble("MERCHANT_COMMISSION"));
				receipt.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
				receipt.setBillingRunName(rs.getString("BILLINGRUN_NAME"));
				receipt.setAccountId(rs.getInt("ACCOUNT_ID"));

				logger.info("getreceipt info for reprint " + receipt.getLedgerTrxId() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getNewSystemWePostPaidReceipt(long ledger_id) throws Exception {
		Connection conn = null;
		String sql = "select  we.LEDGER_TRX_ID,To_Char(we.INSERT_DATE,'ddmmyyyy hh24:mi:ss'), "
				+ " we.TO_BE_PAID, we.APPLIED_FEES, we.TAX, we.MSISDN, we.TOTAL_OUT_STANDING_FEE , we.GLOBAL_TRX_ID "
				+ "from  WE_BILLS.WE_BILLS_TRANSACTIONS@DBLEDGER we where we.LEDGER_TRX_ID=?";

		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, ledger_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				receipt.setLedgerTrxId(rs.getString("LEDGER_TRX_ID"));
				receipt.setTransaction_date(rs.getString(2));
				receipt.setTotalDueAmount(rs.getDouble("TOTAL_OUT_STANDING_FEE"));
				receipt.setToBePaid(rs.getDouble("TO_BE_PAID"));
				receipt.setFees(rs.getDouble("APPLIED_FEES"));
				receipt.setTax(rs.getDouble("TAX"));
				receipt.setPhone(rs.getString("MSISDN"));
				receipt.setGlobalTrxID(rs.getString("GLOBAL_TRX_ID"));

				logger.info("getreceipt info for reprint " + receipt.getLedgerTrxId() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getNewSystemZaidElKhierReceipt(long ledger_id) throws Exception {
		Connection conn = null;
		String sql = "SELECT TO_CHAR(zk.INSERT_DATE, 'dd-mm-yyyy hh24:mi:ss') Transaction_Date, "
				+ "  zk.TO_BE_PAID,  zk.APPLIED_FEES,  zk.MOBILE_NUMBER,  zk.ACCOUNT_ID, "
				+ "  (SELECT case when sc.SUB_SERVICE_ID = 5983 then cat.CATEGORY_NAME else "
				+ "cat.CATEGORY_NAME ||' - '|| sc.SUB_CATEGORY_NAME end CATEGORY_NAME "
				+ "FROM ZAIDELKIER.ZAIDELKIER_CATEGORY@DBLEDGER cat, "
				+ "  ZAIDELKIER.ZAIDELKIER_SUB_CATEGORY@DBLEDGER sc " + "WHERE cat.CATEGORY_ID = sc.CATEGORY_ID "
				+ "AND sc.SUB_SERVICE_ID = zk.SUB_SERVICEID) AS SUB_SERVICE, " + "  zk.PAYEMNT_AMOUNT, "
				+ "  zk.MERCHANT_COMMISSION, " + "  zk.TRANSACTION_AMOUNT, " + "  zk.GLOBAL_TRX_ID "
				+ "FROM ZAIDELKIER.ZAIDELKIER_TRANSACTIONS@DBLEDGER zk " + "WHERE zk.LEDGER_TRX_ID = ?";

		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, ledger_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				receipt.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
				receipt.setTransaction_date(rs.getString("Transaction_Date"));
				receipt.setToBePaid(rs.getDouble("TO_BE_PAID"));
				receipt.setFees(rs.getDouble("APPLIED_FEES"));
				receipt.setAccountId(rs.getInt("ACCOUNT_ID"));
				receipt.setPhone(rs.getString("MOBILE_NUMBER"));
				receipt.setGlobalTrxID(rs.getString("GLOBAL_TRX_ID"));
				receipt.setAmount(rs.getDouble("PAYEMNT_AMOUNT"));
				System.out.println("rs =" + rs.getString("SUB_SERVICE"));
				receipt.setSubscriberName(rs.getString("SUB_SERVICE"));
				receipt.setMerchantCommission(rs.getDouble("MERCHANT_COMMISSION"));

				logger.info("getreceipt info for reprint " + receipt.getGlobalTrxID() + receipt.getTransaction_date()
						+ receipt.getAccountId());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public ElectricityPaymentResponse reprintElectricityReceipt(long ledger_id) throws Exception {
		Connection conn = null;
		String sql = "SELECT ACCOUNT_ID,APPLIED_FEES,GLOBAL_TRX_ID,INSERT_DATE,MERCHANT_COMMISSION,TAX,TO_BE_PAID, TRANSACTION_AMOUNT,BILL_AMOUNT,"
				+ "BILL_DATE, BILL_NUMBER,CLIENT_NAME,SERVICE_ID,CLINET_NUMBER "
				+ " FROM ELECTRICITY.ELECTRICITY_TRANSACTIONS@DBLEDGER where LEDGER_TRX_ID = ?";

		PreparedStatement stmt = null;
		ElectricityPaymentResponse receipt = new ElectricityPaymentResponse();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, ledger_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				receipt.setAccountId(rs.getLong("ACCOUNT_ID"));
				receipt.setAppliedFees(rs.getDouble("APPLIED_FEES"));
				receipt.setBillAmount(rs.getDouble("BILL_AMOUNT"));
				receipt.setBillDate(rs.getString("BILL_DATE"));
				receipt.setBillNumber(rs.getString("BILL_NUMBER"));
				receipt.setClientName(rs.getString("CLIENT_NAME"));
				receipt.setGlobalTrxId(rs.getString("GLOBAL_TRX_ID"));
				receipt.setInsertDate(rs.getString("INSERT_DATE"));
				receipt.setMerchantCommission(rs.getDouble("MERCHANT_COMMISSION"));
				receipt.setTax(rs.getDouble("TAX"));
				receipt.setToBepaid(rs.getDouble("TO_BE_PAID"));
				receipt.setTransactionAmount(rs.getDouble("TRANSACTION_AMOUNT"));
				receipt.setTransactionId(rs.getLong("SERVICE_ID"));
				receipt.setAccountNumber(rs.getString("CLINET_NUMBER"));
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public Masary_Bill_Receipt getTE_Recharge_Receipt(int transaction_Id) throws Exception {

		Bill_Request request = new Bill_Request();
		Bill_Response response = new Bill_Response();
		Gson gson = new Gson();
		Connection conn = null;
		String sql = "select bb.request, bb.response_date, CUSTOMER_ID, SERVICE_ID, CUSTOMER_PHONE, "
				+ "(select lo.response from BILLS.logs lo where lo.bill_reference_number = "
				+ "(select b.bill_reference_number from BILLS.logs b where b.transaction_id = ?) "
				+ "and lo.operation_id = 1) Response " + "from BILLS.logs bb where bb.transaction_id = ?";
		PreparedStatement stmt = null;
		Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, transaction_Id);
			stmt.setInt(2, transaction_Id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				request = gson.fromJson(rs.getString(1), Bill_Request.class);
				response = gson.fromJson(rs.getString(6), Bill_Response.class);

				receipt.setTransaction_id(transaction_Id);

				receipt.setTransaction_date(rs.getString(2));
				receipt.setCustomer_id(rs.getInt(3));
				receipt.setBTC(rs.getInt(4));
				receipt.setPhone(rs.getString(5));
				receipt.setDue_date(String.valueOf(request.getQUOTA()));
				receipt.setAmount(request.getMONEY_PAID());
				receipt.setFees(response.getFEES());
				receipt.setCustomerName(response.getCUSTOMER_NAME());
				logger.info("getreceipt info for print " + receipt.getTransaction_id() + receipt.getCustomerName());
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return receipt;
	}

	public void logout(int customer_id, int sso) {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call  logout(?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setInt(1, customer_id);
			sqlStatement.setInt(2, sso);
			sqlStatement.execute();
		} catch (SQLException se) {
			logger.error(se);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public ArrayList<VoucherDenoDTO> getAvailableDenomination(int serviceId) {
		ArrayList<VoucherDenoDTO> availablaDenmo = new ArrayList<VoucherDenoDTO>();
		VoucherDenoDTO voucherDenoDTO = null;
		ResultSet voucherInfo = null;
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := Service_Vouchers_Availability(?)}";

			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.CURSOR);
			sqlStatement.setInt(2, serviceId);
			sqlStatement.execute();
			voucherInfo = (ResultSet) sqlStatement.getObject(1);
			while (voucherInfo.next()) {
				voucherDenoDTO = new VoucherDenoDTO();
				voucherDenoDTO.setDenomenation(voucherInfo.getString("Denomination_Value"));
				voucherDenoDTO.setAvailable(voucherInfo.getInt("have_vouchers"));
				availablaDenmo.add(voucherDenoDTO);
			}

			voucherInfo.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return availablaDenmo;
	}

	public String getfeesInquiry(int customerID, int serviceId, float amount) {
		String response = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := PK_RATEPLAN.Calculate_Service_Commission(?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER); // Commisson
			sqlStatement.setInt(2, customerID);
			sqlStatement.setInt(3, serviceId);
			sqlStatement.setFloat(4, amount);
			sqlStatement.registerOutParameter(5, OracleTypes.NUMBER); // Applied Fees
			sqlStatement.execute();
			response = sqlStatement.getFloat(1) + "-" + sqlStatement.getFloat(5);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return response;
	}

	/*
	 * Start of agent payment project DB functions
	 * ------------------------------------------------------
	 */
	public DonationAgentPaymentRespponseDto getDonationInfo(int operationId, int serviceId, String lang) {

		Gson gson = new Gson();
		logger.info("get Donation Info operation Id  " + operationId + " Provider Id " + serviceId);

		DonationAgentPaymentRespponseDto donationResponse = new DonationAgentPaymentRespponseDto();
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := BILLS.GET_DONATION_PROGRAM_INFO(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, serviceId);
			sqlStatement.setInt(3, operationId);
			sqlStatement.setString(4, lang);
			sqlStatement.execute();
			String result = sqlStatement.getString(1);
			donationResponse = gson.fromJson(result, DonationAgentPaymentRespponseDto.class);
		} catch (SQLException e) {
			donationResponse.setSTATUS_CODE("-999");
			donationResponse.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
			logger.error("ERROR in do_agent_inquiry method " + e);
			logger.info(e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return donationResponse;
	}

	public DonationAgentPaymentRespponseDto do_agent_payment_without_inquiry(
			Donation_AgentPaymentRequestDTO paymentWzoutInquiryRequest) {

		Gson gson = new Gson();
		logger.info("do_agent_payment without Inquiry cust id " + gson.toJson(paymentWzoutInquiryRequest));
		DonationAgentPaymentRespponseDto AgentPWZoutResponse = new DonationAgentPaymentRespponseDto();
		String response = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := BILLS.PAYMENT_WITHOUT_INQUIRY(?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, gson.toJson(paymentWzoutInquiryRequest));
			logger.info(gson.toJson(paymentWzoutInquiryRequest));
			sqlStatement.execute();
			response = sqlStatement.getString(1);
			AgentPWZoutResponse = gson.fromJson(response, DonationAgentPaymentRespponseDto.class);
		} catch (SQLException e) {
			logger.error("ERROR in do_agent_payment_without_inquiry method " + e);
			AgentPWZoutResponse.setSTATUS_CODE("-999");
			AgentPWZoutResponse.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return AgentPWZoutResponse;
	}

	public DonationAgentPaymentRespponseDto do_agent_inquiry(Donation_AgentPaymentRequestDTO inquiryRequest) {

		DonationAgentPaymentRespponseDto agentPayment = new DonationAgentPaymentRespponseDto();
		Gson gson = new Gson();
		logger.info("do_agent_Inquiry cust id " + gson.toJson(inquiryRequest));
		String response = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := BILLS.INQUIRY(?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, gson.toJson(inquiryRequest));
			sqlStatement.execute();
			response = sqlStatement.getString(1);
			agentPayment = gson.fromJson(response, DonationAgentPaymentRespponseDto.class);
		} catch (SQLException e) {
			logger.error("ERROR in do_agent_inquiry method " + e);
			agentPayment.setSTATUS_CODE("-999");
			agentPayment.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return agentPayment;
	}

	public DonationAgentPaymentRespponseDto do_agent_payment(Donation_AgentPaymentRequestDTO paymentRequest) {

		DonationAgentPaymentRespponseDto agentPayment = new DonationAgentPaymentRespponseDto();
		Gson gson = new Gson();
		logger.info("do_agent_payment Before Payment " + gson.toJson(paymentRequest));
		String response = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := BILLS.PAYMENT(?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, gson.toJson(paymentRequest));

			sqlStatement.execute();
			response = sqlStatement.getString(1);
			agentPayment = gson.fromJson(response, DonationAgentPaymentRespponseDto.class);

		} catch (SQLException e) {
			logger.error("ERROR in do_agent_payment method " + e);
			agentPayment.setSTATUS_CODE("-999");
			agentPayment.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return agentPayment;
	}

	/*
	 * End of agent payment project DB functions
	 * ------------------------------------------------------------------------
	 */
	public ArrayList<String> getAreas() {
		ArrayList<String> areas = new ArrayList<String>();
		Connection conn = null;
		String sql = "select AREA_NAME from Insurances.Areas ";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				areas.add(rs.getString(1));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}

		return areas;
	}

	public ArrayList<String> getPeriods() {
		ArrayList<String> days = new ArrayList<String>();
		Connection conn = null;
		String sql = "select  NO_DAYS ndays from Insurances.periods_area  where age_form=0 and age_to=18 and id_area=1 order by id";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				days.add(rs.getString(1));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}

		return days;
	}

	public double[] getAmount(String idArea, String noDays, int age) {
		double amount[] = new double[3];
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := INSURANCES.GET_AMOUNT(?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, idArea);
			sqlStatement.setString(3, noDays);
			sqlStatement.setInt(4, age);
			sqlStatement.registerOutParameter(5, OracleTypes.NUMBER);
			sqlStatement.registerOutParameter(6, OracleTypes.NUMBER);
			sqlStatement.execute();
			amount[0] = sqlStatement.getDouble(1);
			amount[1] = sqlStatement.getDouble(5);
			amount[2] = sqlStatement.getDouble(6);

		} catch (SQLException ex) {
			logger.error("ERROR in INSURANCES GET_AMOUNT method " + ex);

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return amount;
	}

	public int addTravelPolicy(String passportNo, String firstName, String middleName, String lastName,
			String birthdate, String gender, String startDate, String endDate, String period, String area,
			String customerId, String amount, String Phone, String Address, String City) throws FileNotFoundException {
		Connection conn = null;
		CallableStatement sqlStatement = null;
		int result = 0;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := INSURANCES.PAYMENT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setString(2, passportNo);
			sqlStatement.setString(3, firstName);
			sqlStatement.setString(4, middleName);
			sqlStatement.setString(5, lastName);
			sqlStatement.setString(6, birthdate);
			sqlStatement.setString(7, gender);
			sqlStatement.setString(8, startDate);
			sqlStatement.setString(9, endDate);
			sqlStatement.setString(10, period);
			sqlStatement.setString(11, area.trim());
			sqlStatement.setInt(12, Integer.parseInt(customerId));
			sqlStatement.setDouble(13, Double.parseDouble(amount));
			sqlStatement.setString(14, Phone);
			sqlStatement.setString(15, Address);
			sqlStatement.setString(16, City);
			sqlStatement.execute();
			result = sqlStatement.getInt(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}

		return result;
	}

	public String getDenomerationMessage(String serviceName, String denomeration) {
		String result = "";
		Connection conn = null;
		String sql = "select ARMESSAGE from DENOMINATIONS_MESSAGES where PLATFORM='Normal' and OPERATORNAME=? and DENOMINATION=?";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, serviceName);
			stmt.setString(2, denomeration);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
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

	public IsValidDTO IsValidToDoTransaction(int customerId, int serviceId, double amount) {
		String preLog = "customer  " + customerId + " Service id  " + serviceId + " amount " + amount;

		logger.info(preLog);

		Connection conn = null;
		CallableStatement sqlStatement = null;
		IsValidDTO isValidDTO = new IsValidDTO();
		String returnResult;
		Gson gson = new Gson();

		try {
			conn = getConnection();
			String callSQL = "{ call ? := ISVALID_2DO_JSON(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, customerId);
			sqlStatement.setInt(3, serviceId);
			sqlStatement.setDouble(4, amount);
			sqlStatement.execute();

			returnResult = sqlStatement.getString(1);

			isValidDTO = gson.fromJson(returnResult, IsValidDTO.class);
			isValidDTO.setReturnedJsonString(returnResult);

			logger.info(preLog + " is valid to do returned result" + returnResult + " id " + customerId
					+ " full object " + isValidDTO);

			return isValidDTO;
		} catch (SQLException e) {
			logger.error(preLog + " " + e.getMessage(), e);

			if (e.getMessage().contains("Insufficient service balance to do transaction. Cannot proceed.")
					|| e.getMessage().contains("Insufficient balance to do transaction. Cannot proceed.")) {
				isValidDTO.setReturnedValue(-1);
			} else if (e.getMessage().contains("Customer does not assigned specificed service.")) {
				isValidDTO.setReturnedValue(-2);
			} else if (e.getMessage().contains("This customer is currently inactive.")) {
				isValidDTO.setReturnedValue(-3);
			} else {
				isValidDTO.setReturnedValue(-4);
			}

			return isValidDTO;
		} // end catch
		catch (Exception ex) {
			logger.error(preLog + " " + ex.getMessage(), ex);

			isValidDTO.setReturnedValue(-4);

			return isValidDTO;
		} // end catch
		finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		} // end finally
	}// end of method IsValidToDoTransaction

	public ElectricityBillPaymentResult doOfflinePayment(long customerId, long billId, double billValue,
			long serviceId) {

		CallableStatement sqlStatement = null;

		ElectricityBillPaymentResult electricityBillPaymentResult = null;

		Connection conn = null;
		try {
			conn = getConnection();
			electricityBillPaymentResult = new ElectricityBillPaymentResult();
			electricityBillPaymentResult.setSTATUS_MESSAGE("خطآ فى تنفيذ العملية");

			String callSQL = "{ call ? := BILLS.DO_OFFLINE_PAYMENT(?,?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);

			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);

			sqlStatement.setLong(2, customerId);
			sqlStatement.setLong(3, billId);
			sqlStatement.setDouble(4, billValue);
			sqlStatement.setLong(5, serviceId);
			sqlStatement.execute();

			String jsonResult = sqlStatement.getString(1);

			Gson gson = new Gson();

			electricityBillPaymentResult = gson.fromJson(jsonResult, ElectricityBillPaymentResult.class);

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);

		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return electricityBillPaymentResult;
	}

	public GenericElectricityBill doBillInquiry(long participantNumber, String customerId, String serviceId)
			throws SQLException {
		Connection conn = null;
		PreparedStatement sqlStatement = null;

		GenericElectricityBill genericBillsList = new GenericElectricityBill();

		List<ELectricityBillInquiry> arrayListOfBills = new ArrayList<ELectricityBillInquiry>();
		String fees = "";
		try {

			conn = getConnection();

			String feesSQLQuery, active;

			long isEnabled;

			feesSQLQuery = "SELECT * FROM SERVICES WHERE ID_SERVICE=?";

			sqlStatement = conn.prepareStatement(feesSQLQuery);

			ResultSet resultSet;

			sqlStatement.setLong(1, Long.parseLong(serviceId));

			resultSet = sqlStatement.executeQuery();

			if (resultSet.next()) {

				isEnabled = resultSet.getLong("IS_ENABLED");
				active = resultSet.getString("ACTIVE");
			} else {

				genericBillsList.setStatusCode("-600");
				genericBillsList.setStatusMessage("خطأ فى قاعدة البيانات");
				return genericBillsList;
			}

			if (isEnabled != 1 || !(active.equalsIgnoreCase("Y"))) {

				genericBillsList.setStatusCode("-100");
				genericBillsList.setStatusMessage("عفوآ, الخدمة غير متاحة حاليآ");
				return genericBillsList;
			}

			feesSQLQuery = "SELECT NVL(APPLIED_FEES,0)" + "FROM PBXMOB.RATE_PLAN_SERVICE " + "WHERE SERVICE_ID = ? "
					+ "AND RATE_PLAN_ID IN (SELECT CUSTOMERS.RATE_PLAN_ID " + "FROM PBXMOB.CUSTOMERS "
					+ "WHERE CUSTOMERS.ID_CUSTOMER = ?)";

			sqlStatement = conn.prepareStatement(feesSQLQuery);

			sqlStatement.setString(1, serviceId);
			sqlStatement.setString(2, customerId);
			logger.info("SQL : " + feesSQLQuery);
			resultSet = sqlStatement.executeQuery();

			if (resultSet.next()) {
				fees = resultSet.getString(1);
				logger.info("Fees = " + fees);
			} else {
				genericBillsList.setStatusCode("-800");
				genericBillsList.setStatusMessage("خطأ فى قاعدة البيانات");
				return genericBillsList;

			}

			feesSQLQuery = "select * from BILLS.BILL_INQUIRY where PARTICIPANT_NUMBER = " + participantNumber;
			sqlStatement = conn.prepareStatement(feesSQLQuery);
			resultSet = sqlStatement.executeQuery();
			if (!resultSet.next()) {

				feesSQLQuery = "select * from BILLS.PAID_BILLS where PARTICIPANT_NUMBER = " + participantNumber;
				sqlStatement = conn.prepareStatement(feesSQLQuery);
				resultSet = sqlStatement.executeQuery();

				if (resultSet.next()) {
					genericBillsList.setStatusCode("-444");
					genericBillsList.setStatusMessage("هذه الفاتوره مدفوعة من قبل");
					return genericBillsList;
				}

				genericBillsList.setStatusCode("-333");
				genericBillsList.setStatusMessage("لا توجد فاتورة حاليآ لهذا الرقم");
				return genericBillsList;
			}

			do {

				String billParticipantNumber = resultSet.getString("PARTICIPANT_NUMBER");
				ELectricityBillInquiry bill = new ELectricityBillInquiry();
				bill.setBillInquiryId(resultSet.getLong("BILL_INQUIRY_ID"));
				bill.setBillValue(resultSet.getDouble("BILL_VALUE"));
				bill.setInboundExecutionId(resultSet.getLong("INBOUND_EXECUTION_ID"));
				bill.setIssueDate(resultSet.getString("ISSUE_DATE"));
				bill.setParticipantName(resultSet.getString("PARTICIPANT_NAME"));
				bill.setParticipantNumber(billParticipantNumber);
				bill.setProviderId(resultSet.getLong("PROVIDER_ID"));
				bill.setFees(fees);

				arrayListOfBills.add(bill);

				genericBillsList.setStatusCode("200");
				genericBillsList.setStatusMessage("عملية استعلام ناجحة");
				genericBillsList.setAcquiredBills(arrayListOfBills);
				int acquiredBillsSize = genericBillsList.getAcquiredBills().size();
				if (acquiredBillsSize > 1) {
					logger.error("participant with number " + billParticipantNumber + " has " + acquiredBillsSize
							+ " bills");
				}

			} while (resultSet.next());

			// resultSet.close();
		} catch (Exception e) {
			genericBillsList.setStatusCode("-999");
			genericBillsList.setStatusMessage("General Error");
			logger.error("" + e);
			return genericBillsList;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return genericBillsList;
	}

	public void CancelPending(int activePending) {
		logger.info("Cancel Pending to Id " + activePending);
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call pk_trans.CancelPending(?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.setInt(1, activePending);
			sqlStatement.execute();

		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	// author : Ahmed Saeed
	public String callMASARY_TRANSFER_FEES(long fromUser, long toUser, double amount) {

		String preLog = "Transfer Masary Money fromUser=" + fromUser + ", toUser=" + toUser + ", amount=" + amount;

		logger.info(preLog);

		Connection conn = null;
		CallableStatement sqlStatement = null;

		String returnResult = null;

		try {
			conn = getConnection();
			String callSQL = "{ call ? := MASARY_TRANSFER_FEES(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);

			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);

			sqlStatement.setLong(2, fromUser);
			sqlStatement.setLong(3, toUser);
			sqlStatement.setDouble(4, amount);

			sqlStatement.execute();

			returnResult = String.valueOf(sqlStatement.getDouble(1));

			logger.info(preLog + ", TransferFees=" + returnResult);

			return returnResult;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			returnResult = "-3";

			return returnResult;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			returnResult = "-3";

			return returnResult;
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
	}

	public double getClubCommision(int custId) {
		double result = -1;
		Connection conn = null;
		String sql = "SELECT nvl(sum(amount),0) FROM TRANSACTIONS where CUSTOMER_PAYED = ? and TXN_TYPE_ID = 333";
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, custId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getDouble(1);
			}
			rs.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// //System.out.println("EX" + e.getMessage());
			result = -1;
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}

		return result;

	}

	public String getClubMessage() {

		try {
			// get the property value and print it out
			String msg = SystemSettingsUtil.getInstance().loadProperty("etisalat.club.msg");
			String isEnable = SystemSettingsUtil.getInstance().loadProperty("etisalat.club.enable");
			return msg + "@@" + isEnable;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}

	}

	public String getConfigration(String propertyName) throws SQLException {
		String propertyValue = "";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			String Query = "SELECT c.PROPERTY_VALUE FROM CONFIGURATION c WHERE c.PROPERTY_NAME='" + propertyName + "'";
			statement = conn.prepareStatement(Query);
			rs = statement.executeQuery();
			rs.next();
			propertyValue = rs.getString("PROPERTY_VALUE");
			if (propertyValue == null) {
				propertyValue = "0";
			}

			rs.close();
		} catch (Exception ex) {
			propertyValue = "0";
			logger.error(ex.getMessage());
		} finally {

			closeStatement(statement);
			closeConnection(conn);
		}
		return propertyValue;
	}

	// add fees and commession function ---aya
	public String getCommessionAmountInfo(HttpSession session, int customer_ID, int service_ID, double amount) {

		String response = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ? :=  Get_Cust_Service_Amount_Fees(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.CURSOR);
			sqlStatement.setInt(2, customer_ID);
			sqlStatement.setInt(3, service_ID);
			sqlStatement.setDouble(4, amount);
			sqlStatement.execute();
			ResultSet rs = (ResultSet) sqlStatement.getObject(1);
			while (rs.next()) {
				response = String.valueOf(rs.getDouble(1)) + "-" + String.valueOf(rs.getDouble(2)) + "-"
						+ String.valueOf(rs.getDouble(3)) + "-" + String.valueOf(rs.getDouble(4)) + "-"
						+ String.valueOf(rs.getDouble(5)) + "-" + String.valueOf(rs.getDouble(6)) + "-"
						+ String.valueOf(rs.getDouble(7)) + "-" + String.valueOf(rs.getDouble(8)) + "-"
						+ String.valueOf(rs.getDouble(9) + "-" + String.valueOf(rs.getDouble(10)) + "-"
								+ String.valueOf(rs.getDouble(11)) + "-" + String.valueOf(rs.getDouble(12)));
				session.setAttribute("TotalAmount", String.valueOf(rs.getDouble(12)));
				// System.out.println("Response" +response.toString());
			}

			rs.close();
		} catch (Exception ex) {
			logger.error("getCommessionAmountInfo error" + ex);
			response = "";
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return response;
	}

	public String getCustomerLoyaltyPoints(String customerId) {

		String response = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{call ? :=   LOYALTY.SPK_LOYALTY.GET_CUSTOMER_LPOINT(?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER);
			sqlStatement.setInt(2, Integer.parseInt(customerId));
			sqlStatement.execute();
			response = String.valueOf(sqlStatement.getDouble(1));
		} catch (Exception ex) {
			logger.error("getCustomerLoyaltyPoints error" + ex);
			response = "-1";
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return response;
	}

	public HashMap<String, String> getWalletsGovernorates() {
		HashMap<String, String> governoratesList = new HashMap();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select * from PBXMOB.VW_GOV";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				governoratesList.put(rs.getString("GOV_CODE"), rs.getString("GOV_NAME"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error:" + e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return governoratesList;
	}

	public HashMap<String, String> getWalletsCities(String governoratesName) {
		HashMap<String, String> cityList = new HashMap();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select * from PBXMOB.VW_CITY where GOV_ISO=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, governoratesName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cityList.put(rs.getString("CITY_ISO"), rs.getString("CITY_NAME"));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Error:" + e.getMessage());
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return cityList;
	}

	/**
	 * ***********************Mubasher
	 *
	 ********************************
	 * @return
	 */
	public MunasherItemsInfo[] getMubasherList() {
		String result = "";
		Gson gson = new Gson();
		MunasherItemsInfo[] list = new MunasherItemsInfo[10];
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := Get_Mubasher_List()}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR); // Item
			sqlStatement.execute();
			result = sqlStatement.getString(1);
			list = gson.fromJson(result, MunasherItemsInfo[].class);
			// //System.out.println("Result" + result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return list;
	}

	public MubasherResponseDto[] doItemTransaction(int custId, String itemId, String msisdn) {
		String response = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		// MubasherResponseDto mubasherResponseDto = null;
		Gson gson = new Gson();
		MubasherResponseDto[] list = new MubasherResponseDto[10];

		try {
			conn = getConnection();
			String callSQL = "{call ? := Do_Item_Transaction(?,?,?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setInt(2, custId);
			sqlStatement.setInt(3, Integer.parseInt(itemId));
			sqlStatement.setString(4, msisdn);
			sqlStatement.execute();
			response = String.valueOf(sqlStatement.getString(1));
			list = gson.fromJson(response, MubasherResponseDto[].class);
			// mubasherResponseDto = gson.fromJson(response, MubasherResponseDto.class);
			logger.info("Final Response " + response);
			// System.out.println("Final Response " + response);
			return list;
		} catch (Exception ex) {
			logger.error("do transaction Mubasher error" + ex);
			// //System.out.println("do transaction Mubasher error " + ex.getMessage());
			// mubasherResponseDto = new MubasherResponseDto("General error");
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return list;
	}

	public String do_Edu_payment_without_inquiry(String paymentWzoutInquiryRequest) {
		Gson gson = new Gson();
		logger.info("do_agent_payment without Inquiry cust id " + paymentWzoutInquiryRequest);
		DonationAgentPaymentRespponseDto eduPWZoutResponse = new DonationAgentPaymentRespponseDto();
		String response = "";
		Connection conn = null;
		CallableStatement sqlStatement = null;
		try {
			conn = getConnection();
			String callSQL = "{ call ? := EDUCATION_CENTER.SPK_DO_EDUCENTER.PAYMENT_WITHOUT_INQUIRY(?)}";
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			sqlStatement.setString(2, paymentWzoutInquiryRequest);
			sqlStatement.execute();
			response = sqlStatement.getString(1);
		} catch (Exception e) {
			logger.error("ERROR in do_agent_payment_without_inquiry method " + e);
			eduPWZoutResponse.setSTATUS_CODE("-999");
			eduPWZoutResponse.setSTATUS_MESSAGE("Internal Error, Please Call Masary Team");
			response = gson.toJson(eduPWZoutResponse, DonationAgentPaymentRespponseDto.class);
		} finally {
			closeStatement(sqlStatement);
			closeConnection(conn);
		}
		return response;
	}

	public boolean getServiceStatus(int serviceId) {

		Connection conn = null;
		PreparedStatement stmt = null;
		int status = 0;
		try {
			conn = getConnection();
			String sql = "SELECT (x.is_Enabled * x.is_Service_Enabled) as isEnabled FROM (\n"
					+ "select (select CAST(nvl(c.property_value,'0') AS INTEGER) from pbxmob.configuration c \n"
					+ "          where c.property_name = 'INTEGRATE_ENABLED') as is_Enabled,\n"
					+ "       (select CAST(NVL(st.INTEGRATION_FLAG,'0') AS INTEGER)\n"
					+ "         from SERVICE_INTEGRATION_STATUS st where st.SERVICE_ID = ?) as is_Service_Enabled  \n"
					+ "  FROM DUAL)x";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, serviceId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				status = rs.getInt(1);
			}
			rs.close(); // close result set By keroles 30-05-2016 used
			// closeStatement(stmt);
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return status == 1;
	}

	public ArrayList<Double> getOperatorDenominations(int serviceId) {
		ArrayList<Double> orangeDenominations = new ArrayList<Double>();

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			String sql = "select amount from OPERATOR_DENOMINATION where OPERATO_ID = ? order by AMOUNT";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, serviceId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				orangeDenominations.add(rs.getDouble("AMOUNT"));
			}
			rs.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			closeStatement(stmt);
			closeConnection(conn);
		}
		return orangeDenominations;
	}
}// end of class MasaryManager
