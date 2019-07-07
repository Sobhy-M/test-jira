/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Kerolous
 */
public class MubasherResponseDto {

    private String ERROR_TEXT;
    private String USER_ID;
    private String PASSWORD;
    private int TRANSACTION_ID;
    private String TRANSACTION_DATE;
    private String ORIGINAL_AMOUNT;
    private String TAX_AMOUNT;
    private String TOTAL_FEES;
    private String TOTAL_COMMSSION;
    private String MERCHANT_AMOUNT;
    private String CUSTOMER_AMOUNT;

    public String getERROR_TEXT() {
        return ERROR_TEXT;
    }

    public void setERROR_TEXT(String ERROR_TEXT) {
        this.ERROR_TEXT = ERROR_TEXT;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public void setTRANSACTION_ID(int TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    public String getTRANSACTION_DATE() {
        return TRANSACTION_DATE;
    }

    public void setTRANSACTION_DATE(String TRANSACTION_DATE) {
        this.TRANSACTION_DATE = TRANSACTION_DATE;
    }

    public String getORIGINAL_AMOUNT() {
        return ORIGINAL_AMOUNT;
    }

    public void setORIGINAL_AMOUNT(String ORIGINAL_AMOUNT) {
        this.ORIGINAL_AMOUNT = ORIGINAL_AMOUNT;
    }

    public String getTAX_AMOUNT() {
        return TAX_AMOUNT;
    }

    public void setTAX_AMOUNT(String TAX_AMOUNT) {
        this.TAX_AMOUNT = TAX_AMOUNT;
    }

    public String getTOTAL_FEES() {
        return TOTAL_FEES;
    }

    public void setTOTAL_FEES(String TOTAL_FEES) {
        this.TOTAL_FEES = TOTAL_FEES;
    }

    public String getTOTAL_COMMSSION() {
        return TOTAL_COMMSSION;
    }

    public void setTOTAL_COMMSSION(String TOTAL_COMMSSION) {
        this.TOTAL_COMMSSION = TOTAL_COMMSSION;
    }

    public String getMERCHANT_AMOUNT() {
        return MERCHANT_AMOUNT;
    }

    public void setMERCHANT_AMOUNT(String MERCHANT_AMOUNT) {
        this.MERCHANT_AMOUNT = MERCHANT_AMOUNT;
    }

    public String getCUSTOMER_AMOUNT() {
        return CUSTOMER_AMOUNT;
    }

    public void setCUSTOMER_AMOUNT(String CUSTOMER_AMOUNT) {
        this.CUSTOMER_AMOUNT = CUSTOMER_AMOUNT;
    }

   

}
