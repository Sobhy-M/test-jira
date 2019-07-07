/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.manager;

import com.masary.common.CONFIG;
import com.masary.database.dto.TransactionInfoDTO;
import com.masary.database.dto.VodafoneHttpResponse;
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
public class MobinilHttpManager extends BaseManger {

	private static MobinilHttpManager instance = null;
	private Connection conn;
	private String callSQL;
	private CallableStatement sqlStatement;
	private int count;
	private String operationId;
	public static Logger logger = MasaryManager.logger;

	private MobinilHttpManager() {
	}
	//

	public static MobinilHttpManager getInstance() {
		if (instance == null) {
			instance = new MobinilHttpManager();
		}
		return instance;
	}

	synchronized public TransactionInfoDTO transferCustomerTopUp(String custID, String msisdn, String amount,
			String lang) throws Exception {
		logger.info("Mobinil Topup " + msisdn + " amount " + amount + " from " + custID);
		VodafoneHttpResponse response = null;
		try {

			logger.info("Try to get a connection..");
			conn = getConnection();
			logger.info("A connection is fetched..");

			logger.info("Setting parameters and preparing the SQL statement..");
			sqlStatement = null;
			callSQL = ("{call ?:= DO_TOPUP_TRANSACTION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER); // operation id
			sqlStatement.setInt(2, Integer.parseInt(custID)); // customerID_in
			sqlStatement.setString(3, msisdn); // mSISDNID_in
			sqlStatement.setFloat(4, Float.parseFloat(amount)); // amount_in
			sqlStatement.setInt(5, 8); // serviceID_in
			sqlStatement.registerOutParameter(6, OracleTypes.NUMBER); // taxAmount_out
			sqlStatement.registerOutParameter(7, OracleTypes.NUMBER); // RESPONSE_STATUS_code
			sqlStatement.registerOutParameter(8, OracleTypes.VARCHAR); // english_message
			sqlStatement.registerOutParameter(9, OracleTypes.VARCHAR); // arabic_message
			sqlStatement.registerOutParameter(10, OracleTypes.VARCHAR); // savedResult
			sqlStatement.registerOutParameter(11, OracleTypes.NUMBER); // transaction_ID
			sqlStatement.registerOutParameter(12, OracleTypes.NUMBER); // response_Code
			sqlStatement.setInt(13, 0); // isUSSD_in
			sqlStatement.setString(14, lang.toUpperCase()); // user_lang
			sqlStatement.setString(15, null); // senderDateTime_in
			sqlStatement.setString(16, null); // senderTransID_in

			logger.info("Before SQL execution..");
			sqlStatement.execute();
			logger.info("After SQL execution..");

			operationId = sqlStatement.getString(1);
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

			if (response.getEnglishMessage().trim().toLowerCase().contains("being processed")) {
				TransactionInfoDTO txninfo = new TransactionInfoDTO();
				txninfo.setTransaction_ID(transId);
				txninfo.setAmount(taxAmount);
				return txninfo;
			}

			logger.error(response.getEnglishMessage());
			throw new Exception(lang.equals("ar") ? CONFIG.transactionErrorar : CONFIG.transactionError);
			// throw new Exception(response.getEnglishMessage());

		} catch (Exception ex) {
			// added by hammad for orange capping
			if (ex.getMessage().contains("ORA-21046")) {
				throw new Exception(ex.getMessage());
			} else {
				logger.error(ex);
				throw (ex);
			}

		} finally {
			closeStatement(sqlStatement);
		}
	}

	synchronized public String transferVodafoneCustomerTopUpAsService(String custID, String msisdn, String amount,
			String senderTime, String senderTrxID) throws Exception {
		logger.info("Mobinil Topup " + msisdn + " amount " + amount + " from " + custID);
		VodafoneHttpResponse response = null;
		try {
			logger.info("Try to get a connection..");
			conn = getConnection();
			logger.info("A connection is fetched..");

			logger.info("Setting parameters and preparing the SQL statement..");

			sqlStatement = null;
			callSQL = ("{call ?:= PK_TRANS.Set_Air_Operation(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			sqlStatement = conn.prepareCall(callSQL);
			sqlStatement.registerOutParameter(1, OracleTypes.NUMBER); // operation id
			sqlStatement.setInt(2, Integer.parseInt(custID)); // customerID_in
			sqlStatement.setString(3, msisdn); // mSISDNID_in
			sqlStatement.setFloat(4, Float.parseFloat(amount)); // amount_in
			sqlStatement.setInt(5, 8); // serviceID_in
			sqlStatement.registerOutParameter(6, OracleTypes.NUMBER); // taxAmount_out
			sqlStatement.registerOutParameter(7, OracleTypes.NUMBER); // RESPONSE_STATUS_code
			sqlStatement.registerOutParameter(8, OracleTypes.VARCHAR); // english_message
			sqlStatement.registerOutParameter(9, OracleTypes.VARCHAR); // arabic_message
			sqlStatement.registerOutParameter(10, OracleTypes.VARCHAR); // savedResult
			sqlStatement.registerOutParameter(11, OracleTypes.NUMBER); // transaction_ID
			sqlStatement.setInt(12, 0); // isUSSD_in
			sqlStatement.setString(13, senderTime); // senderDateTime_in
			sqlStatement.setString(14, senderTrxID); // senderTransID_in

			logger.info("Before SQL execution..");
			sqlStatement.execute();
			logger.info("After SQL execution..");

			operationId = sqlStatement.getString(1);
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

			if (response.getEnglishMessage().trim().toLowerCase().contains("being processed")) {
				float[] results = new float[2];
				results[0] = transId;
				results[1] = taxAmount;
				return transId + " " + response.getResult();
			}

			logger.error(response.getEnglishMessage());
			throw new Exception(CONFIG.transactionError);

		} catch (Exception ex) {
			logger.error(ex);
			throw (ex);

		} finally {
			closeStatement(sqlStatement);
		}
	}
}
