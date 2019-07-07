/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author omnya
 */
public class Bill_Request {

    private String LANG;
    private String BILLING_ACCOUNT;
    private String CHANNEL;
    private int CUSTOMER_ID;
    private int SERVICE_ID;
    private double AMOUNT;
    private String PASSWORD;
    private int BILL_REFERENCE_NUMBER;
    private int QUOTA;
    private int MONEY_PAID;
    private String AREA_CODE;
    private String PAYMENTMETHOD;
    String SENDER_ID;
    String BANKID;

    public Bill_Request() {
    }

    public Bill_Request(String LANG, String BILLING_ACCOUNT, String CHANNEL, int CUSTOMER_ID, int SERVICE_ID, double AMOUNT, String PASSWORD, int BILL_REFERENCE_NUMBER, int QUOTA, int MONEY_PAID, String AREA_CODE, String PAYMENTMETHOD, String SENDER_ID, String BANKID) {
        this.LANG = LANG;
        this.BILLING_ACCOUNT = BILLING_ACCOUNT;
        this.CHANNEL = CHANNEL;
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.SERVICE_ID = SERVICE_ID;
        this.AMOUNT = AMOUNT;
        this.PASSWORD = PASSWORD;
        this.BILL_REFERENCE_NUMBER = BILL_REFERENCE_NUMBER;
        this.QUOTA = QUOTA;
        this.MONEY_PAID = MONEY_PAID;
        this.AREA_CODE = AREA_CODE;
        this.PAYMENTMETHOD = PAYMENTMETHOD;
        this.SENDER_ID = SENDER_ID;
        this.BANKID = BANKID;
    }

    public String getPAYMENTMETHOD() {
        return PAYMENTMETHOD;
    }

    public void setPAYMENTMETHOD(String PAYMENTMETHOD) {
        this.PAYMENTMETHOD = PAYMENTMETHOD;
    }

    public String getSENDER_ID() {
        return SENDER_ID;
    }

    public void setSENDER_ID(String SENDER_ID) {
        this.SENDER_ID = SENDER_ID;
    }

    public String getBANKID() {
        return BANKID;
    }

    public void setBANKID(String BANKID) {
        this.BANKID = BANKID;
    }

   

    public int getQUOTA() {
        return QUOTA;
    }

    public void setQUOTA(int QUOTA) {
        this.QUOTA = QUOTA;
    }

    public int getMONEY_PAID() {
        return MONEY_PAID;
    }

    public void setMONEY_PAID(int MONEY_PAID) {
        this.MONEY_PAID = MONEY_PAID;
    }

    public String getAREA_CODE() {
        return AREA_CODE;
    }

    public void setAREA_CODE(String AREA_CODE) {
        this.AREA_CODE = AREA_CODE;
    }

    public int getBILL_REFERENCE_NUMBER() {
        return BILL_REFERENCE_NUMBER;
    }

    public void setBILL_REFERENCE_NUMBER(int BILL_REFERENCE_NUMBER) {
        this.BILL_REFERENCE_NUMBER = BILL_REFERENCE_NUMBER;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

//    public int getOPERATION_TYPE() {
//        return OPERATION_TYPE;
//    }
//
//    public void setOPERATION_TYPE(int OPERATION_TYPE) {
//        this.OPERATION_TYPE = OPERATION_TYPE;
//    }

   

    public String getLANG() {
        return LANG;
    }

    public void setLANG(String LANG) {
        this.LANG = LANG;
    }

    public String getBILLING_ACCOUNT() {
        return BILLING_ACCOUNT;
    }

    public void setBILLING_ACCOUNT(String BILLING_ACCOUNT) {
        this.BILLING_ACCOUNT = BILLING_ACCOUNT;
    }

 

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(int CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public int getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(int SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    @Override
    public String toString() {
        return "Bill_Request{" + "LANG=" + LANG + ", BILLING_ACCOUNT=" + BILLING_ACCOUNT + ", CHANNEL=" + CHANNEL + ", CUSTOMER_ID=" + CUSTOMER_ID + ", SERVICE_ID=" + SERVICE_ID + ", AMOUNT=" + AMOUNT + ", PASSWORD=" + PASSWORD + ", BILL_REFERENCE_NUMBER=" + BILL_REFERENCE_NUMBER + ", QUOTA=" + QUOTA + ", MONEY_PAID=" + MONEY_PAID + ", AREA_CODE=" + AREA_CODE + ", PAYMENTMETHOD=" + PAYMENTMETHOD + ", SENDER_ID=" + SENDER_ID + ", BANKID=" + BANKID + '}';
    }
    
}
