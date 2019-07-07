package com.masary.database.manager;

import com.masary.database.dto.TransactionInfoDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class USSDEtisalatManager extends BaseManger {

    private static USSDEtisalatManager instance = null;
    
//    private final EtisaltHttpManager etisaltHttpManager;
    
//   private Connection conn;
//   private String callSQL;
//   private CallableStatement sqlStatement;
//   private int count;
//   private String reternString;
//   private String operationId;
//    public static Logger logger = MasaryManager.logger;

    private USSDEtisalatManager()
    {
    }
    
    public static USSDEtisalatManager getInstance() {
        if (instance == null) {
            instance = new USSDEtisalatManager();
        }
        return instance;
    }

    public TransactionInfoDTO transferCredit(String custID, String msisdn, String amount, String lang)
            throws Exception {
        return EtisaltHttpManager.getInstance().transferCredit(custID, msisdn, amount,lang);
    }

    public TransactionInfoDTO transferCustomerTopUp(String custID, String msisdn, String amount, String lang)
            throws Exception {
        return EtisaltHttpManager.getInstance().transferCustomerTopUp(custID, msisdn, amount, lang);
    }

    public String transferCustomerTopUpAsService(String custID, String msisdn, String amount, String senderTime, String senderTrxID)
            throws Exception {
        return EtisaltHttpManager.getInstance().transferCustomerTopUpAsService(custID, msisdn, amount, senderTime, senderTrxID);
    }
    
//   public synchronized String transferTopUpAsAsynchronousService(String custID, String msisdn, String amount) throws Exception {
///* 250 */     return EtisaltHttpManager.getInstance().transferTopUpAsAsynchronousService(custID, msisdn, amount);
//   }
//   private String waitForUSSDOperation(String operationId)
//   {
///* 257 */     this.callSQL = ("{call ?:= ISFINISHED(?,?)}");
///* 258 */     this.count = 0;
///* 259 */     while (this.count < 100) {
//       try {
///* 261 */         if (this.conn == null)
//         {
///* 263 */           this.conn = getConnection();
//         }
///* 265 */         this.sqlStatement = this.conn.prepareCall(this.callSQL);
///* 266 */         this.sqlStatement.registerOutParameter(1, 12);
//sqlStatement.setString(2, operationId);
///* 267 */         this.sqlStatement.registerOutParameter(3, 12);
///* 268 */         this.sqlStatement.execute();
///* 269 */         if (this.sqlStatement.getString(1).equals("1")) {
///* 270 */           String str = this.sqlStatement.getString(3);
//           return str;
//         }
///* 272 */         logger.info("Waiting for Modem response " + operationId);
///* 273 */         Thread.sleep(600L);
//       }
//       catch (Exception ex)
//       {
///* 277 */         logger.error(ex);
//       } finally {
///* 279 */         closeStatement(this.sqlStatement);
//       }
//
///* 282 */       this.count += 1;
//     }
///* 284 */     return "error";
//   }
//   public void cancelOperation(String operationId) throws SQLException {
///* 288 */     EtisaltHttpManager.getInstance().cancelOperation(operationId);
//   }
//   public synchronized String getEtisalatCredits()
//   {
///* 326 */     return EtisaltHttpManager.getInstance().getEtisalatCredits();
//   }
}
/*****************************************************************************************************************************/
/*************************************************** MODEM *******************************************************************/
/*****************************************************************************************************************************/
//package com.masary.database.manager;
//
//import com.masary.common.CONFIG;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.SQLException;
//import oracle.jdbc.OracleTypes;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author Melad
// */
//public class USSDEtisalatManager extends BaseManger {
//
//    private static USSDEtisalatManager instance = null;
//    private Connection conn;
//    private String callSQL;
//    private CallableStatement sqlStatement;
//    private int count;
//    private String reternString;
//    private String operationId;
//    public static Logger logger = MasaryManager.logger;
//
//    private USSDEtisalatManager() {
//    }
//
//    public static USSDEtisalatManager getInstance() {
//        if (instance == null) {
//            instance = new USSDEtisalatManager();
//        }
//        return instance;
//    }
//
//    synchronized public long transferCredit(String custID, String msisdn, String amount) throws Exception {
//        logger.info("Etisalt Transfer " + msisdn + " amount " + amount + " from " + custID);
//        conn = getConnection();
//        try {
//            sqlStatement = null;
//            callSQL = "{call  Etisalat_Credit_Transfer(?,?,?,?)}";
//            sqlStatement = conn.prepareCall(callSQL);
//            sqlStatement.setInt(1, Integer.parseInt(custID));
//            sqlStatement.setString(2, msisdn);
//            sqlStatement.setInt(3, Integer.parseInt(amount));
//            sqlStatement.registerOutParameter(4, OracleTypes.VARCHAR);
//            sqlStatement.execute();
//            operationId = sqlStatement.getString(4);
//            closeStatement(sqlStatement);
//            reternString = waitForUSSDOperation(operationId);
//            if (reternString.equals(CONFIG.error)) {
//                cancelOperation(operationId);
//                throw new Exception(CONFIG.errorTransaction);
//            }
//
//            if (reternString.contains(CONFIG.etisalatError) || reternString.contains(CONFIG.etisalatError1) || reternString.contains(CONFIG.etisalatError2)) {
//                cancelOperation(operationId);
//                if (reternString.contains(" يرجى المحاولة لاحقا")) {
//                    throw new Exception(" Operator Error Please try again later");
//                }
//
//                if (reternString.contains("القيمة غير صحيحة")) {
//                    throw new Exception(" Operation failed  Amount out of range");
//                }
//                if (reternString.contains("شحن متكر")) {
//                    throw new Exception(" Operator Error Please try again later");
//                }
//                if (reternString.contains("Invalid Dial Number")) {
//                    throw new Exception(" Operator Error Invalid Dial Number");
//                }
//                if (reternString.contains(CONFIG.etisalatError1)) {
//                    throw new Exception(" Operator Error Invalid Dial Number");
//                }
//
//
//                throw new Exception(reternString);
//            }
//            if (reternString.contains(CONFIG.etisalatDone)) {
//                return MasaryManager.getInstance().saveEtisalatTrans(msisdn, custID, amount, operationId, 7);
//            }
//            throw new Exception(reternString);
//
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw (ex);
//
//        } finally {
//            closeStatement(sqlStatement);
//            closeConnection(conn);
//        }
////        return EtisaltHttpManager.getInstance().transferCredit(custID, msisdn, amount);
//    }
//
////    synchronized public String transferCredit(String custID, String msisdn, String amount)
////    {
////        try
////        {
////            conn = getConnection();
////            sqlStatement = null;
////
////            callSQL = "{call  Etisalat_Credit_Transfer(" + custID + ",'" + msisdn + "'," + amount + ",?)}";
////            sqlStatement = conn.prepareCall(callSQL);
////            sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
////            sqlStatement.execute();
////            operationId = sqlStatement.getString(1);
////            reternString = waitForUSSDOperation(operationId);
////            if (reternString.equals(CONFIG.error))
////            {
////                cancelOperation(operationId);
////            }
////            if (reternString.contains(CONFIG.etisalatError) || reternString.contains(CONFIG.etisalatError1) || reternString.contains(CONFIG.etisalatError2))
////            {
////                cancelOperation(operationId);
////                if (reternString.contains(CONFIG.BadEtisaltCustomer))
////                {
////                    return CONFIG.BadEtisaltCustomer;
////                }
////                return CONFIG.error;
////            }
////            return operationId;
////
////        } catch (Exception ex)
////        {
////            Logger.getLogger(MasaryManager.class.getName()).log(Level.SEVERE, null, ex);
////            return CONFIG.error;
////        } finally
////        {
////            //         closeStatement(sqlStatement);
////            //       closeConnection(conn);
////        }
////
////
////    }
//    synchronized public long transferCustomerTopUp(String custID, String msisdn, String amount) throws Exception {
//        logger.info("Etisalat Topup " + msisdn + " amount " + amount + " from " + custID);
//        conn = getConnection();
//        try {
//            sqlStatement = null;
//            callSQL = "{call  Etisalat_Customer_Topup(?,?,?,?)}";
//            sqlStatement = conn.prepareCall(callSQL);
//            sqlStatement.setInt(1, Integer.parseInt(custID));
//            sqlStatement.setString(2, msisdn);
//            sqlStatement.setInt(3, Integer.parseInt(amount));
//            sqlStatement.registerOutParameter(4, OracleTypes.VARCHAR);
//            sqlStatement.execute();
//            operationId = sqlStatement.getString(4);
//            if (operationId.equals("-1")) {
//                throw new Exception(CONFIG.MobinilTopupRepetedError);
//            }
//            closeStatement(sqlStatement);
//            reternString = waitForUSSDOperation(operationId);
//            if (reternString.equals(CONFIG.error)) {
//                cancelOperation(operationId);
//                throw new Exception(CONFIG.errorTransaction);
//            }
//
//            if (reternString.contains(CONFIG.etisalatError) || reternString.contains(CONFIG.etisalatError1) || reternString.contains(CONFIG.etisalatError2)) {
//                cancelOperation(operationId);
//                if (reternString.contains(" يرجى المحاولة لاحقا")) {
//                    throw new Exception(" Operator Error Please try again later");
//                }
//                if (reternString.contains("القيمة غير صحيحة")) {
//                    throw new Exception(" Operation failed  Amount out of range");
//                }
//                if (reternString.contains("شحن متكر")) {
//                    throw new Exception(" Operator Error Please try again later");
//                }
//                if (reternString.contains("Invalid Dial Number")) {
//                    throw new Exception(" Operator Error Invalid Dial Number");
//                }
//                if (reternString.contains(CONFIG.etisalatError1)) {
//                    throw new Exception(" Operator Error Invalid Dial Number");
//                }
//                throw new Exception(reternString);
//            }
//            if (reternString.contains(CONFIG.etisalatDone)) {
//                return MasaryManager.getInstance().saveEtisalatTrans(msisdn, custID, amount, operationId, 6);
//            }
//            throw new Exception(reternString);
//
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw (ex);
//
//        } finally {
//            closeStatement(sqlStatement);
//            closeConnection(conn);
//        }
//
//
////        return EtisaltHttpManager.getInstance().transferCustomerTopUp(custID, msisdn, amount);
//    }
//
//    synchronized public String transferCustomerTopUpAsService(String custID, String msisdn, String amount) throws Exception {
//        logger.info("Etisalt Topup " + msisdn + " amount " + amount + " from " + custID);
//        conn = getConnection();
//        try {
//            sqlStatement = null;
//            callSQL = "{call  Etisalat_Customer_Topup(?,?,?,?)}";
//            sqlStatement = conn.prepareCall(callSQL);
//            sqlStatement.setInt(1, Integer.parseInt(custID));
//            sqlStatement.setString(2, msisdn);
//            
//    //        int amountValue = 0;
//    //      if (amount.contains(".")) {
//    //        String[] values = amount.split("\\.");
//    //      amountValue = Integer.parseInt(values[0]);
//    //        } else {
//     //           amountValue = Integer.parseInt(amount);
//      //      }
//            
//            sqlStatement.setFloat(3, Float.parseFloat(amount));
//            sqlStatement.registerOutParameter(4, OracleTypes.VARCHAR);
//            sqlStatement.execute();
//            operationId = sqlStatement.getString(4);
//            if (operationId.equals("-1")) {
//                throw new Exception(CONFIG.MobinilTopupRepetedError);
//            }
//            closeStatement(sqlStatement);
//            reternString = waitForUSSDOperation(operationId);
//            if (reternString.equals(CONFIG.error)) {
//                cancelOperation(operationId);
//                return "-100 " + CONFIG.errorTransaction;
//            }
//
//            if (reternString.contains(CONFIG.etisalatError) || reternString.contains(CONFIG.etisalatError1) || reternString.contains(CONFIG.etisalatError2)) {
//                cancelOperation(operationId);
//                if (reternString.contains(" يرجى المحاولة لاحقا")) {
//                    return "-90 Operator Error Please try again later";
//                }
//
//                if (reternString.contains("القيمة غير صحيحة")) {
//                    return "-90 Operation failed  Amount out of range";
//                }
//                if (reternString.contains("شحن متكر")) {
//                    return "-90 Operator Error Please try again later";
//                }
//                if (reternString.contains("Invalid Dial Number")) {
//                    return "-90 Operator Error Invalid Dial Number";
//                }
//                if (reternString.contains(CONFIG.etisalatError1)) {
//                    return "-90 Operator Error Invalid Dial Number";
//                }
//
//                return "-90 " + reternString;
//            }
//            if (reternString.contains(CONFIG.etisalatDone)) {
//                int transID = MasaryManager.getInstance().saveEtisalatTrans(msisdn, custID, amount, operationId, 6);
//                return transID + "  " + reternString;
//            }
//            throw new Exception(reternString);
//
//        } catch (Exception ex) {
//            logger.error(ex);
//            throw (ex);
//
//        } finally {
//            closeStatement(sqlStatement);
//            closeConnection(conn);
//        }
////        return EtisaltHttpManager.getInstance().transferCustomerTopUpAsService(custID, msisdn, amount);
//
//    }
//    synchronized public String transferTopUpAsAsynchronousService(String custID, String msisdn, String amount) throws Exception {
//        return EtisaltHttpManager.getInstance().transferTopUpAsAsynchronousService(custID, msisdn, amount);
//
//    }
//
//
//    private String waitForUSSDOperation(String operationId) {
//        ////System.out.println(operationId);
//        callSQL = "{call ?:= ISFINISHED(?,?)}";
//        count = 0;
//        while (count < 100) {
//            try {
//                if (conn == null) {
//                    ////System.out.println("Connection null+++++++++++++++++++++++++++");
//                    conn = getConnection();
//                }
//                sqlStatement = conn.prepareCall(callSQL);
//                sqlStatement.registerOutParameter(1, OracleTypes.VARCHAR);
//                sqlStatement.setString(2, operationId);
//                sqlStatement.registerOutParameter(3, OracleTypes.VARCHAR);
//                sqlStatement.execute();
//                if (sqlStatement.getString(1).equals("1")) {
//                    return sqlStatement.getString(3);
//                }
//                logger.info("Waiting for Modem response " + operationId);
//                Thread.sleep(600);
//
//
//            } catch (Exception ex) {
//                logger.error(ex);
//            } finally {
//                closeStatement(sqlStatement);
//                //------------------------6-5-2012
//                closeConnection(conn);
//                //--------------------------------
//            }
//
//            count++;
//        }
//        return CONFIG.error;
//    }
//
//    public void cancelOperation(String operationId) throws SQLException {
//        callSQL = "{call  CANCEL_OPERATION(?)}";
//        sqlStatement = conn.prepareCall(callSQL);
//        sqlStatement.setInt(1, Integer.parseInt(operationId));
//        sqlStatement.execute();
//        logger.info("Cancel " + operationId);
//    }
//
//    synchronized public String getEtisalatCredits() {
//        conn = getConnection();
//        sqlStatement = null;
//        try {
//            callSQL = "{call  GET_ETISALAT_CREDITS(?,?)}";
//            sqlStatement = conn.prepareCall(callSQL);
//            sqlStatement.setInt(1,1);
//            sqlStatement.registerOutParameter(2, OracleTypes.VARCHAR);
//            sqlStatement.execute();
//            operationId = sqlStatement.getString(2);
//            closeStatement(sqlStatement);
//            reternString = waitForUSSDOperation(operationId);
//            ////System.out.println("Wait result "+reternString);
//            if (reternString.equals(CONFIG.error)) {
//                cancelOperation(operationId);
//            }
//            if (reternString.contains(CONFIG.etisalatError) || reternString.contains(CONFIG.etisalatError1) || reternString.contains(CONFIG.etisalatError2)) {
//                cancelOperation(operationId);
//                if (reternString.contains(CONFIG.BadEtisaltCustomer)) {
//                    return CONFIG.BadEtisaltCustomer;
//                }
//                return CONFIG.error;
//            }
//            logger.info("Get Etisalat  Credits " + reternString);
//            return reternString;
//
//        } catch (SQLException ex) {
//            logger.error(ex);
//            return CONFIG.error;
//        } finally {
//            closeStatement(sqlStatement);
//            closeConnection(conn);
//        }
////        return  EtisaltHttpManager.getInstance().getEtisalatCredits();
//
//    }
//}
