/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class efinance_receipt {
    String TRANSACTION_ID;
    String TRANSACTION_DATE;
    String CUSTOMER_NAME;
    String SERVICE_NAME;
    String BILLINGACCT;
    String DUEDT;
    String AMOUNT;
    String FEE;
    String DESCRIPTION;
    String PAYMENTID;
    String BILL_NUMBER;
    String ADDITIONAL_INFO;

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

    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    public String getSERVICE_NAME() {
        return SERVICE_NAME;
    }

    public void setSERVICE_NAME(String SERVICE_NAME) {
        this.SERVICE_NAME = SERVICE_NAME;
    }

    public String getBILLINGACCT() {
        return BILLINGACCT;
    }

    public void setBILLINGACCT(String BILLINGACCT) {
        this.BILLINGACCT = BILLINGACCT;
    }

    public String getDUEDT() {
        return DUEDT;
    }

    public void setDUEDT(String DUEDT) {
        this.DUEDT = DUEDT;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getFEE() {
        return FEE;
    }

    public void setFEE(String FEE) {
        this.FEE = FEE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getPAYMENTID() {
        return PAYMENTID;
    }

    public void setPAYMENTID(String PAYMENTID) {
        this.PAYMENTID = PAYMENTID;
    }

    public String getBILL_NUMBER() {
        return BILL_NUMBER;
    }

    public void setBILL_NUMBER(String BILL_NUMBER) {
        this.BILL_NUMBER = BILL_NUMBER;
    }

    public String getADDITIONAL_INFO() {
        return ADDITIONAL_INFO;
    }

    public void setADDITIONAL_INFO(String ADDITIONAL_INFO) {
        this.ADDITIONAL_INFO = ADDITIONAL_INFO;
    }
    

}
