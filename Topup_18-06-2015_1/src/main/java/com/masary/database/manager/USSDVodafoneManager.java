/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.manager;

import com.masary.common.CONFIG;
import com.masary.database.dto.TransactionInfoDTO;
import static com.masary.database.manager.VodafoneHttpManager.logger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;

/**
 *
 * @author Hammad
 */
public class USSDVodafoneManager extends BaseManger {

	private static USSDVodafoneManager instance = null;
	private Connection conn;
	private String callSQL;
	private CallableStatement sqlStatement;
	// private int count;
	// private String reternString;
	private String operationId;
	public static Logger logger = MasaryManager.logger;

	private USSDVodafoneManager() {
	}

	public static USSDVodafoneManager getInstance() {
		if (instance == null) {
			instance = new USSDVodafoneManager();
		}
		return instance;
	}

	public synchronized TransactionInfoDTO transferVodafoneCustomerTopUp(String custID, String msisdn, String amount,
			String lang) throws Exception {

		// logger.info("Vodafone Topup " + msisdn + " amount " + amount + " from " +
		// custID);
		// conn = getConnection();
		// try {
		// sqlStatement = null;
		// callSQL = "{call \"vodafone_CUSTOMER_TOPUP\"(" + custID + ",'" + msisdn +
		// "'," + amount + ",?)}";
		// sqlStatement = conn.prepareCall(callSQL);
		// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
		// sqlStatement.execute();
		// operationId = sqlStatement.getString(1);
		// if (operationId.equals("-1")) {
		// throw new Exception(CONFIG.MobinilTopupRepetedError);
		// }
		// closeStatement(sqlStatement);
		// reternString = waitForVodafoneUSSDOperation(operationId);
		// if (reternString.equals(CONFIG.error)) {
		// cancelVodafoneOperation(operationId);
		// throw new Exception(CONFIG.errorTransaction);
		// }
		// if (reternString.contains(CONFIG.vodafoneDone)) {
		// return MasaryManager.getInstance().saveVodafoneTrans(msisdn, custID, amount,
		// operationId, 10);
		// } else {
		// cancelVodafoneOperation(operationId);
		// throw new Exception(reternString);
		// }
		//
		//
		// } catch (Exception ex) {
		// logger.error(ex);
		// throw (ex);
		//
		// } finally {
		// closeStatement(sqlStatement);
		// closeConnection(conn);
		// }

		return VodafoneHttpManager.getInstance().transferCustomerTopUp(custID, msisdn, amount, lang);
	}

	synchronized public TransactionInfoDTO transferVodafoneCustomerTransfer(String custID, String msisdn, String amount,
			String lang) throws Exception {

		logger.info("Vodafone Transfer " + msisdn + " amount " + amount + " from " + custID);

		// int totalAmount = Integer.parseInt(amount);
		// if (totalAmount <= 2000) {
		// return
		// VodafoneHttpManager.getInstance().transferVodafoneCustomerTransfer(custID,
		// msisdn, amount);
		// } else {

		try {
			logger.info("Try to get a connection..");
			conn = getConnection();
			logger.info("A connection is fetched..");

			logger.info("Setting parameters and preparing the SQL statement..");

			sqlStatement = null;
			callSQL = ("{call ?:=  PK_TRANS.Set_Air_Operation(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER); // operation id
			sqlStatement.setInt(2, Integer.parseInt(custID)); // customerID_in
			sqlStatement.setString(3, msisdn); // mSISDNID_in
			sqlStatement.setFloat(4, Float.parseFloat(amount)); // amount_in
			sqlStatement.setInt(5, 11); // serviceID_in
			sqlStatement.registerOutParameter(6, OracleTypes.NUMBER); // taxAmount_out
			sqlStatement.registerOutParameter(7, OracleTypes.NUMBER); // RESPONSE_STATUS_code
			sqlStatement.registerOutParameter(8, OracleTypes.VARCHAR); // english_message
			sqlStatement.registerOutParameter(9, OracleTypes.VARCHAR); // arabic_message
			sqlStatement.registerOutParameter(10, OracleTypes.VARCHAR); // savedResult
			sqlStatement.registerOutParameter(11, OracleTypes.NUMBER); // transaction_ID
			sqlStatement.setInt(12, 1); // isUSSD_in
			sqlStatement.setString(13, null); // senderDateTime_in
			sqlStatement.setString(14, null); // senderTransID_in

			logger.info("Before SQL execution..");
			sqlStatement.execute();
			logger.info("After SQL execution..");

			operationId = sqlStatement.getString(1);
			logger.info("Operation ID " + operationId);

			float taxAmount = sqlStatement.getFloat(6);

			int code = sqlStatement.getInt(7);
			logger.info("Status Code " + code);

			String englishMessage = sqlStatement.getString(8);
			logger.info("English Msg " + englishMessage);

			String arabicMessage = sqlStatement.getString(9);
			logger.info("Arabic Msg " + arabicMessage);

			String result = sqlStatement.getString(10);
			logger.info("Result " + result);

			int transId = sqlStatement.getInt(11);
			logger.info("Transaction " + transId);

			if (operationId.equals("-1")) {
				throw new Exception(
						lang.equals("ar") ? CONFIG.MobinilTopupRepetedErrorar : CONFIG.MobinilTopupRepetedError);
			}

			if (operationId.equals("-100")) {
				throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
			}

			if (operationId.equals("-80")) {
				throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
			}

			if (operationId.equals("-200")) {
				throw new Exception(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
			}

			if (operationId.equals("-41")) {
				throw new Exception(
						lang.equals("ar") ? CONFIG.balanceLockedErrorMessageAr : CONFIG.balanceLockedErrorMessageEn);
			}

			if (result.trim().toLowerCase().contains("successful")) {
				TransactionInfoDTO txninfo = new TransactionInfoDTO();
				txninfo.setTransaction_ID(transId);
				txninfo.setAmount(taxAmount);
				return txninfo;
			}

			logger.error(result);
			throw new Exception(lang.equals("ar") ? CONFIG.transactionErrorar : CONFIG.transactionError);

		} catch (Exception ex) {
			logger.error(ex);
			throw (ex);

		} finally {
			closeStatement(sqlStatement);
		}
		// }
	}

	synchronized public String transferVodafoneCustomerTopUpAsService(String custID, String msisdn, String amount,
			String senderTime, String senderTrxID) throws Exception {
		// logger.info("Vodafone Topup " + msisdn + " amount " + amount + " from " +
		// custID);
		// conn = getConnection();
		// try {
		// sqlStatement = null;
		// callSQL = "{call \"vodafone_CUSTOMER_TOPUP\"(" + custID + ",'" + msisdn +
		// "'," + amount+ "'," + senderTime + "'," + senderTrxID + ",?)}";
		// sqlStatement = conn.prepareCall(callSQL);
		// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
		// sqlStatement.execute();
		// operationId = sqlStatement.getString(1);
		// closeStatement(sqlStatement);
		// if (operationId.equals("-1")) {
		// throw new Exception(CONFIG.MobinilTopupRepetedError);
		// }
		// reternString = waitForVodafoneUSSDOperation(operationId);
		// if (reternString.equals(CONFIG.error)) {
		// cancelVodafoneOperation(operationId);
		// return "-100 " + CONFIG.errorTransaction;
		// }
		// if (reternString.contains(CONFIG.vodafoneDone)) {
		// int transID = MasaryManager.getInstance().saveVodafoneTrans(msisdn, custID,
		// amount, operationId, 10);
		// return transID + " " + reternString;
		// } else {
		// cancelVodafoneOperation(operationId);
		// return "-90 " + reternString;
		// }
		//
		//
		// } catch (Exception ex) {
		// logger.error(ex);
		// throw (ex);
		// } finally {
		// closeStatement(sqlStatement);
		// closeConnection(conn);
		// }
		return VodafoneHttpManager.getInstance().transferVodafoneCustomerTopUpAsService(custID, msisdn, amount,
				senderTime, senderTrxID);
	}
	// private String waitForVodafoneUSSDOperation(String operationId) {
	// ////System.out.println(operationId);
	// callSQL = "{call ?:= IS_Vodafone_FINISHED(?,?)}";
	// count = 0;
	// while (count < 200) {
	// try {
	// if (conn == null) {
	// ////System.out.println("Connection null+++++++++++++++++++++++++++");
	// conn = getConnection();
	// }
	// sqlStatement = conn.prepareCall(callSQL);
	// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
	// sqlStatement.setString(2, operationId);
	// sqlStatement.registerOutParameter(3, OracleTypes.VARCHAR);
	// sqlStatement.execute();
	// if (sqlStatement.getString(1).equals("1")) {
	// return sqlStatement.getString(3);
	// }
	//
	// logger.info("Waiting for Modem response " + operationId);
	// Thread.sleep(1000);
	//
	// } catch (Exception ex) {
	// logger.error(ex);
	// } finally {
	// closeStatement(sqlStatement);
	// //------------------------6-5-2012
	// closeConnection(conn);
	// //--------------------------------
	// }
	// count++;
	// }
	// return CONFIG.error;
	// }
	// public void cancelVodafoneOperation(String operationId) throws SQLException {
	// callSQL = "{call CANCEL_Vodafone_OPERATION(?)}";
	// sqlStatement = conn.prepareCall(callSQL);
	// sqlStatement.setInt(1, Integer.parseInt(operationId));
	// sqlStatement.execute();
	// logger.info("Cancel " + operationId);
	// closeStatement(sqlStatement);
	// }
	// synchronized public String getVodafoneCredits() {
	//// conn = getConnection();
	//// sqlStatement = null;
	//// try {
	//// callSQL = "{call GET_Vodafone_CREDITS(1,?)}";
	//// sqlStatement = conn.prepareCall(callSQL);
	//// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
	//// sqlStatement.execute();
	//// operationId = sqlStatement.getString(1);
	//// reternString = waitForVodafoneUSSDOperation(operationId);
	//// ////System.out.println("Wait result "+reternString);
	//// if (reternString.equals(CONFIG.error)) {
	//// cancelVodafoneOperation(operationId);
	//// }
	//// if (reternString.contains(CONFIG.etisalatError) ||
	// reternString.contains(CONFIG.etisalatError1) ||
	// reternString.contains(CONFIG.etisalatError2)) {
	//// cancelVodafoneOperation(operationId);
	//// if (reternString.contains(CONFIG.BadEtisaltCustomer)) {
	//// return CONFIG.BadEtisaltCustomer;
	//// }
	//// return CONFIG.error;
	//// }
	//// return reternString;
	////
	//// } catch (SQLException ex) {
	//// logger.info(ex);
	//// return CONFIG.error;
	//// } finally {
	//// closeStatement(sqlStatement);
	//// closeConnection(conn);
	//// }
	// return VodafoneHttpManager.getInstance().getVodafoneCredits();
	// }
	// synchronized public String transferVodafoneCustomerTransferAsService(String
	// custID, String msisdn, String amount) throws Exception {
	//
	// logger.info("Vodafone Transfer " + msisdn + " amount " + amount + " from " +
	// custID);
	// conn = getConnection();
	// try {
	// sqlStatement = null;
	// callSQL = "{call vodafone_CUSTOMER_Transfer(?,?,?,?)}";
	// sqlStatement = conn.prepareCall(callSQL);
	// sqlStatement.setInt(1, Integer.parseInt(custID));
	// sqlStatement.setString(2, msisdn);
	// sqlStatement.setInt(3, Integer.parseInt(amount));
	// sqlStatement.registerOutParameter(4, OracleTypes.VARCHAR);
	// sqlStatement.execute();
	// operationId = sqlStatement.getString(4);
	// if (operationId.equals("-1")) {
	// throw new Exception(CONFIG.MobinilTopupRepetedError);
	// }
	// closeStatement(sqlStatement);
	// reternString = waitForVodafoneUSSDOperation(operationId);
	// if (reternString.equals(CONFIG.error)) {
	// cancelVodafoneOperation(operationId);
	// return "-100 " + CONFIG.errorTransaction;
	// }
	// if (reternString.contains(CONFIG.vodafoneDone)) {
	// int transID = MasaryManager.getInstance().saveVodafoneTrans(msisdn, custID,
	// amount, operationId, 11);
	// return transID + " " + reternString;
	// } else {
	// cancelVodafoneOperation(operationId);
	// return "-90 " + reternString;
	// }
	// } catch (Exception ex) {
	// logger.error(ex);
	// throw (ex);
	// } finally {
	// closeStatement(sqlStatement);
	// closeConnection(conn);
	//
	// }
	// // return
	// VodafoneHttpManager.getInstance().transferVodafoneCustomerTransferAsService(custID,
	// msisdn, amount);
	// }
	// synchronized public String transferVodafoneTopUpAsAsynchronousService(String
	// custID, String msisdn, String amount) throws Exception {
	//// logger.info("Vodafone TopUp AsAsynchronous Service " + msisdn + " amount "
	// + amount + " from " + custID);
	//// conn = getConnection();
	//// try {
	//// sqlStatement = null;
	//// callSQL = "{call \"vodafone_CUSTOMER_TOPUP\"(" + custID + ",'" + msisdn +
	// "'," + amount + ",?)}";
	//// sqlStatement = conn.prepareCall(callSQL);
	//// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
	//// sqlStatement.execute();
	//// operationId = sqlStatement.getString(1);
	//// closeStatement(sqlStatement);
	//// if (operationId.equals("-1")) {
	//// throw new Exception(CONFIG.MobinilTopupRepetedError);
	//// }
	//// } catch (Exception ex) {
	//// logger.error(ex);
	//// throw (ex);
	//// } finally {
	//// closeStatement(sqlStatement);
	//// closeConnection(conn);
	//// }
	//// int transID =
	// MasaryManager.getInstance().saveAsynchronousTransaction(msisdn, custID,
	// amount, operationId, 10);
	//// completeTheJob(operationId, transID);
	//// return transID + " Your request is being processed";
	// return
	// VodafoneHttpManager.getInstance().transferVodafoneTopUpAsAsynchronousService(custID,
	// msisdn, amount);
	// }
	// private void completeTheJob(final String operationId, final int transId) {
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// reternString = waitForVodafoneUSSDOperation(operationId);
	// if (reternString.contains(CONFIG.vodafoneDone)) {
	// MasaryManager.getInstance().updateTransactionStatus(transId, 99);
	// return;
	// } else {
	// try {
	// MasaryManager.getInstance().updateTransactionStatus(transId, 80);
	// cancelVodafoneOperation(operationId);
	// return;
	// } catch (SQLException ex) {
	// logger.error(ex);
	// }
	// }
	// }
	// }).start();
	// }
}
/**
 * ************************************************************************************************************
 */
/**
 * ********************************************* MODEM
 * ********************************************************
 */
/**
 * ************************************************************************************************************
 */
// package com.masary.database.manager;
//
// import com.masary.common.CONFIG;
// import java.sql.CallableStatement;
// import java.sql.Connection;
// import java.sql.SQLException;
// import oracle.jdbc.OracleTypes;
// import org.apache.log4j.Logger;
//
/// **
// *
// * @author Melad
// */
// public class USSDVodafoneManager extends BaseManger {
//
// private static USSDVodafoneManager instance = null;
// private Connection conn;
// private String callSQL;
// private CallableStatement sqlStatement;
// private int count;
// private String reternString;
// private String operationId;
// public static Logger logger = MasaryManager.logger;
//
// private USSDVodafoneManager() {
// }
//
// public static USSDVodafoneManager getInstance() {
// if (instance == null) {
// instance = new USSDVodafoneManager();
// }
// return instance;
// }
//
// synchronized public long transferVodafoneCustomerTopUp(String custID, String
// msisdn, String amount) throws Exception {
//
// logger.info("Vodafone Topup " + msisdn + " amount " + amount + " from " +
// custID);
// conn = getConnection();
// try {
// sqlStatement = null;
// callSQL = "{call \"vodafone_CUSTOMER_TOPUP\"(" + custID + ",'" + msisdn +
// "'," + amount + ",?)}";
// sqlStatement = conn.prepareCall(callSQL);
// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
// sqlStatement.execute();
// operationId = sqlStatement.getString(1);
// if (operationId.equals("-1")) {
// throw new Exception(CONFIG.MobinilTopupRepetedError);
// }
// closeStatement(sqlStatement);
// reternString = waitForVodafoneUSSDOperation(operationId);
// if (reternString.equals(CONFIG.error)) {
// cancelVodafoneOperation(operationId);
// throw new Exception(CONFIG.errorTransaction);
// }
// if (reternString.contains(CONFIG.vodafoneDone)) {
// return MasaryManager.getInstance().saveVodafoneTrans(msisdn, custID, amount,
// operationId, 10);
// } else {
// cancelVodafoneOperation(operationId);
// throw new Exception(reternString);
// }
//
//
// } catch (Exception ex) {
// logger.error(ex);
// throw (ex);
//
// } finally {
// closeStatement(sqlStatement);
// closeConnection(conn);
// }
// }
//
// synchronized public long transferVodafoneCustomerTransfer(String custID,
// String msisdn, String amount) throws Exception {
//
// logger.info("Vodafone Transfer " + msisdn + " amount " + amount + " from " +
// custID);
// conn = getConnection();
// try {
// sqlStatement = null;
// callSQL = "{call vodafone_CUSTOMER_Transfer(" + custID + ",'" + msisdn + "',"
// + amount + ",?)}";
// sqlStatement = conn.prepareCall(callSQL);
// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
// sqlStatement.execute();
// operationId = sqlStatement.getString(1);
// if (operationId.equals("-1")) {
// throw new Exception(CONFIG.MobinilTopupRepetedError);
// }
// closeStatement(sqlStatement);
// reternString = waitForVodafoneUSSDOperation(operationId);
// if (reternString.equals(CONFIG.error)) {
// cancelVodafoneOperation(operationId);
// throw new Exception(CONFIG.errorTransaction);
// }
// if (reternString.contains(CONFIG.vodafoneDone)) {
// return MasaryManager.getInstance().saveVodafoneTrans(msisdn, custID, amount,
// operationId, 11);
// } else {
// cancelVodafoneOperation(operationId);
// throw new Exception(reternString);
// }
// } catch (Exception ex) {
// logger.error(ex);
// throw (ex);
// } finally {
// closeStatement(sqlStatement);
// closeConnection(conn);
// }
// }
//
// synchronized public String transferVodafoneCustomerTopUpAsService(String
// custID, String msisdn, String amount) throws Exception {
// logger.info("Vodafone Topup " + msisdn + " amount " + amount + " from " +
// custID);
// conn = getConnection();
// try {
// sqlStatement = null;
// callSQL = "{call \"vodafone_CUSTOMER_TOPUP\"(" + custID + ",'" + msisdn +
// "'," + amount + ",?)}";
// sqlStatement = conn.prepareCall(callSQL);
// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
// sqlStatement.execute();
// operationId = sqlStatement.getString(1);
// closeStatement(sqlStatement);
// if (operationId.equals("-1")) {
// throw new Exception(CONFIG.MobinilTopupRepetedError);
// }
// reternString = waitForVodafoneUSSDOperation(operationId);
// if (reternString.equals(CONFIG.error)) {
// cancelVodafoneOperation(operationId);
// return "-100 " + CONFIG.errorTransaction;
// }
// if (reternString.contains(CONFIG.vodafoneDone)) {
// int transID = MasaryManager.getInstance().saveVodafoneTrans(msisdn, custID,
// amount, operationId, 10);
// return transID + " " + reternString;
// } else {
// cancelVodafoneOperation(operationId);
// return "-90 " + reternString;
// }
//
//
// } catch (Exception ex) {
// logger.error(ex);
// throw (ex);
// } finally {
// closeStatement(sqlStatement);
// closeConnection(conn);
// }
// }
//
//
// private String waitForVodafoneUSSDOperation(String operationId) {
// ////System.out.println(operationId);
// callSQL = "{call ?:= IS_Vodafone_FINISHED('" + operationId + "',?)}";
// count = 0;
// while (count < 200) {
// try {
// if (conn == null) {
// ////System.out.println("Connection null+++++++++++++++++++++++++++");
// conn = getConnection();
// }
// sqlStatement = conn.prepareCall(callSQL);
// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
// sqlStatement.registerOutParameter(2, OracleTypes.VARCHAR);
// sqlStatement.execute();
// if (sqlStatement.getString(1).equals("1")) {
// return sqlStatement.getString(2);
// }
//
// logger.info("Waiting for Modem response " + operationId);
// Thread.sleep(1000);
//
// } catch (Exception ex) {
// logger.error(ex);
// } finally {
// closeStatement(sqlStatement);
// }
// count++;
// }
// return CONFIG.error;
// }
//
// public void cancelVodafoneOperation(String operationId) throws SQLException {
// callSQL = "{call CANCEL_Vodafone_OPERATION(" + operationId + ")}";
// sqlStatement = conn.prepareCall(callSQL);
// sqlStatement.execute();
// closeStatement(sqlStatement);
// logger.info("Cancel " + operationId);
// }
//
// synchronized public String getVodafoneCredits() {
// conn = getConnection();
// sqlStatement = null;
// try {
// callSQL = "{call GET_Vodafone_CREDITS(1,?)}";
// sqlStatement = conn.prepareCall(callSQL);
// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
// sqlStatement.execute();
// operationId = sqlStatement.getString(1);
// reternString = waitForVodafoneUSSDOperation(operationId);
// ////System.out.println("Wait result "+reternString);
// if (reternString.equals(CONFIG.error)) {
// cancelVodafoneOperation(operationId);
// }
// if (reternString.contains(CONFIG.etisalatError) ||
// reternString.contains(CONFIG.etisalatError1) ||
// reternString.contains(CONFIG.etisalatError2)) {
// cancelVodafoneOperation(operationId);
// if (reternString.contains(CONFIG.BadEtisaltCustomer)) {
// return CONFIG.BadEtisaltCustomer;
// }
// return CONFIG.error;
// }
// return reternString;
//
// } catch (SQLException ex) {
// logger.info(ex);
// return CONFIG.error;
// } finally {
// closeStatement(sqlStatement);
// closeConnection(conn);
// }
// }
//
// synchronized public String transferVodafoneTopUpAsAsynchronousService(String
// custID, String msisdn, String amount) throws Exception {
// logger.info("Vodafone TopUp AsAsynchronous Service " + msisdn + " amount " +
// amount + " from " + custID);
// conn = getConnection();
// try {
// sqlStatement = null;
// callSQL = "{call \"vodafone_CUSTOMER_TOPUP\"(" + custID + ",'" + msisdn +
// "'," + amount + ",?)}";
// sqlStatement = conn.prepareCall(callSQL);
// sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
// sqlStatement.execute();
// operationId = sqlStatement.getString(1);
// closeStatement(sqlStatement);
// if (operationId.equals("-1")) {
// throw new Exception(CONFIG.MobinilTopupRepetedError);
// }
// } catch (Exception ex) {
// logger.error(ex);
// throw (ex);
// } finally {
// closeStatement(sqlStatement);
// closeConnection(conn);
// }
// int transID = MasaryManager.getInstance().saveAsynchronousTransaction(msisdn,
// custID, amount, operationId, 10);
// completeTheJob(operationId, transID);
// return transID + " Your request is being processed";
// }
// private void completeTheJob(final String operationId, final int transId) {
// new Thread(new Runnable() {
//
// @Override
// public void run() {
// reternString = waitForVodafoneUSSDOperation(operationId);
// if (reternString.contains(CONFIG.vodafoneDone)) {
// MasaryManager.getInstance().updateTransactionStatus(transId, 99);
// return;
// } else {
// try {
// MasaryManager.getInstance().updateTransactionStatus(transId, 80);
// cancelVodafoneOperation(operationId);
// return;
// } catch (SQLException ex) {
// logger.error(ex);
// }
// }
// }
// }).start();
// }
// }
