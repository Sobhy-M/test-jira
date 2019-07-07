/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Aya
 */
public class Bill_Provider_Response {

    private String STATUS_CODE;
    private String REQUEST_ID;
    private String STATUS_MESSAGE;
    private String BILL_DATE;
    private String CUSTOMER_NAME;
    private double AMOUNT;

    public String getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(String STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
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
    
}
