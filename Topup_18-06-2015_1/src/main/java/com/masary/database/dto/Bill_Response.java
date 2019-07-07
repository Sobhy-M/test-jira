/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author omnya
 */
public class Bill_Response {

    private int STATUS_CODE;
    private int BILL_REFERENCE_NUMBER;
    private String STATUS_MESSAGE;
    private String BILL_DATE;
    private String CUSTOMER_NAME;
    private double AMOUNT;
    private double FEES;
    private double COMMESSION;
    private String TRANSACTION_ID;
    private String PROVIDER_TRANSACTION_ID;
    private int IS_FRACTION;
    private int IS_PARTIAL;
    private int IS_OVER;
    private int IS_ADVANCED;
    private String ADDITIONAL_INFO;
    private String BILLING_ACCOUNT;
    private String ADDRESS;
    private String AmountArray;
    private String BillRefInfo;
    float minimumPayment;

    public double getCOMMESSION() {
        return COMMESSION;
    }

    public void setCOMMESSION(double COMMESSION) {
        this.COMMESSION = COMMESSION;
    }

    private eFinance_Inq_Res_amounts[] amounts;

    public float getMinimumPayment() {
        return minimumPayment;
    }

    public void setMinimumPayment(float minimumPayment) {
        this.minimumPayment = minimumPayment;
    }

    public String getBillRefInfo() {
        return BillRefInfo;
    }

    public void setBillRefInfo(String BillRefInfo) {
        this.BillRefInfo = BillRefInfo;
    }

    public eFinance_Inq_Res_amounts[] getAmounts() {
        return amounts;
    }

    public void setAmounts(eFinance_Inq_Res_amounts[] amounts) {
        this.amounts = amounts;
    }

    public String getAmountArray() {
        return AmountArray;
    }

    public void setAmountArray(String AmountArray) {
        this.AmountArray = AmountArray;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getBILLING_ACCOUNT() {
        return BILLING_ACCOUNT;
    }

    public void setBILLING_ACCOUNT(String BILLING_ACCOUNT) {
        this.BILLING_ACCOUNT = BILLING_ACCOUNT;
    }

    public String getADDITIONAL_INFO() {
        return ADDITIONAL_INFO;
    }

    public void setADDITIONAL_INFO(String ADDITIONAL_INFO) {
        this.ADDITIONAL_INFO = ADDITIONAL_INFO;
    }

    public double getFEES() {
        return FEES;
    }

    public void setFEES(double FEES) {
        this.FEES = FEES;
    }

    public int getIS_FRACTION() {
        return IS_FRACTION;
    }

    public void setIS_FRACTION(int IS_FRACTION) {
        this.IS_FRACTION = IS_FRACTION;
    }

    public int getIS_PARTIAL() {
        return IS_PARTIAL;
    }

    public void setIS_PARTIAL(int IS_PARTIAL) {
        this.IS_PARTIAL = IS_PARTIAL;
    }

    public int getIS_OVER() {
        return IS_OVER;
    }

    public void setIS_OVER(int IS_OVER) {
        this.IS_OVER = IS_OVER;
    }

    public int getIS_ADVANCED() {
        return IS_ADVANCED;
    }

    public void setIS_ADVANCED(int IS_ADVANCED) {
        this.IS_ADVANCED = IS_ADVANCED;
    }

    public String getPROVIDER_TRANSACTION_ID() {
        return PROVIDER_TRANSACTION_ID;
    }

    public void setPROVIDER_TRANSACTION_ID(String PROVIDER_TRANSACTION_ID) {
        this.PROVIDER_TRANSACTION_ID = PROVIDER_TRANSACTION_ID;
    }

    public int getBILL_REFERENCE_NUMBER() {
        return BILL_REFERENCE_NUMBER;
    }

    public void setBILL_REFERENCE_NUMBER(int BILL_REFERENCE_NUMBER) {
        this.BILL_REFERENCE_NUMBER = BILL_REFERENCE_NUMBER;
    }

    public int getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(int STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public String getSTATUS_MESSAGE() {
        return STATUS_MESSAGE;
    }

    public void setSTATUS_MESSAGE(String STATUS_MESSAGE) {
        this.STATUS_MESSAGE = STATUS_MESSAGE;
    }

    public String getBILL_DATE() {
        return BILL_DATE;
    }

    public void setBILL_DATE(String BILL_DATE) {
        this.BILL_DATE = BILL_DATE;
    }

    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public void setTRANSACTION_ID(String TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    @Override
    public String toString() {
        return "Bill_Response{" + "STATUS_CODE=" + STATUS_CODE + ", BILL_REFERENCE_NUMBER=" + BILL_REFERENCE_NUMBER + ", STATUS_MESSAGE=" + STATUS_MESSAGE + ", BILL_DATE=" + BILL_DATE + ", CUSTOMER_NAME=" + CUSTOMER_NAME + ", AMOUNT=" + AMOUNT + ", FEES=" + FEES + ", TRANSACTION_ID=" + TRANSACTION_ID + ", PROVIDER_TRANSACTION_ID=" + PROVIDER_TRANSACTION_ID + ", IS_FRACTION=" + IS_FRACTION + ", IS_PARTIAL=" + IS_PARTIAL + ", IS_OVER=" + IS_OVER + ", IS_ADVANCED=" + IS_ADVANCED + ", ADDITIONAL_INFO=" + ADDITIONAL_INFO + ", BILLING_ACCOUNT=" + BILLING_ACCOUNT + ", ADDRESS=" + ADDRESS + ", AmountArray=" + AmountArray + ", BillRefInfo=" + BillRefInfo + ", amounts=" + amounts + '}';
    }

}
