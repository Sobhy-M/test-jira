/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael
 */
public class Transaction {

    int OPERATION_ID;
    String CREATION_DATE;
    String TRANSACTION_ID;
    String TRANSACTION_DATE;
    String STATUS;
    String CUSTOMER_PHONE;
    double MAIN_AMOUNT;
    double AMOUNT;
    String TRANSACTION_TYPE;
    String SERVICE;
    double COMMESSION;
    double FEES;
    double TOTAL;
    int IS_PEANDING;
    

    
    public double getMAIN_AMOUNT() {
        return MAIN_AMOUNT;
    }

    public void setMAIN_AMOUNT(double MAIN_AMOUNT) {
        this.MAIN_AMOUNT = MAIN_AMOUNT;
    }
    public int getIS_PENDING() {
        return IS_PEANDING;
    }

    public void setIS_PENDING(int IS_PENDING) {
        this.IS_PEANDING = IS_PENDING;
    }

    public int getOPERATION_ID() {
        return OPERATION_ID;
    }

    public void setOPERATION_ID(int OPERATION_ID) {
        this.OPERATION_ID = OPERATION_ID;
    }

    public String getCREATION_DATE() {
        return CREATION_DATE;
    }

    public void setCREATION_DATE(String CREATION_DATE) {
        this.CREATION_DATE = CREATION_DATE;
    }

    public String getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public void setTRANSACTION_ID(String TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    public String getTRANSACTION_DATE() {
        return TRANSACTION_DATE;
    }

    public void setTRANSACTION_DATE(String TRANSACTION_DATE) {
        this.TRANSACTION_DATE = TRANSACTION_DATE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCUSTOMER_PHONE() {
        return CUSTOMER_PHONE;
    }

    public void setCUSTOMER_PHONE(String CUSTOMER_PHONE) {
        this.CUSTOMER_PHONE = CUSTOMER_PHONE;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getTRANSACTION_TYPE() {
        return TRANSACTION_TYPE;
    }

    public void setTRANSACTION_TYPE(String TRANSACTION_TYPE) {
        this.TRANSACTION_TYPE = TRANSACTION_TYPE;
    }

    public String getSERVICE() {
        return SERVICE;
    }

    public void setSERVICE(String SERVICE) {
        this.SERVICE = SERVICE;
    }

    public double getCOMMESSION() {
        return COMMESSION;
    }

    public void setCOMMESSION(double COMMESSION) {
        this.COMMESSION = COMMESSION;
    }

    public double getFEES() {
        return FEES;
    }

    public void setFEES(double FEES) {
        this.FEES = FEES;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getTRANSACTION_TYPE(HttpSession session) {
        if (session.getAttribute(CONFIG.lang).equals("ar")) {
            if (TRANSACTION_TYPE.equals("CASH_IN")) {
                return "ايداع";
            } else if (TRANSACTION_TYPE.equals("CASH_OUT")) {
                return "سحب";
            }
        }
        return TRANSACTION_TYPE;
    }

    public String getSTATUS(HttpSession session) {
    	try
    	{
    		VC_Transaction_Status status = MasaryManager.getInstance().get_VC_TXN_Status(this.OPERATION_ID);
//          //System.out.println(status.getMASARY_STATUS_MSG_AR()+"-"+status.getMASARY_STATUS_MSG());
          if (session.getAttribute(CONFIG.lang).equals("ar")) {
              return status.getMASARY_STATUS_MSG_AR();
          }
          return status.getMASARY_STATUS_MSG();
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
        
    }
}
