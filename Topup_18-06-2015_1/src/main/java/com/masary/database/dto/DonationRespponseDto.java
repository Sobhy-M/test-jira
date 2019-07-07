/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author KEMO
 */
public class DonationRespponseDto {
    
    String OPERATION_NAME;
    double FEES;
    double FIXED_AMOUNT;
    int PROVIDER_ID;
    int OPERATION_ID;
    String STATUS_CODE;
    String STATUS_MESSAGE;

    public String getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(String STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public String getSTATUS_MESSAGE() {
        return STATUS_MESSAGE;
    }

    public void setSTATUS_MESSAGE(String STATUS_MESSAGE) {
        this.STATUS_MESSAGE = STATUS_MESSAGE;
    }
    
    public double getFEES() {
        return FEES;
    }

    public void setFEES(double FEES) {
        this.FEES = FEES;
    }

    public double getFIXED_AMOUNT() {
        return FIXED_AMOUNT;
    }

    public void setFIXED_AMOUNT(double FIXED_AMOUNT) {
        this.FIXED_AMOUNT = FIXED_AMOUNT;
    }

    public int getOPERATION_ID() {
        return OPERATION_ID;
    }

    public void setOPERATION_ID(int OPERATION_ID) {
        this.OPERATION_ID = OPERATION_ID;
    }

    public String getOPERATION_NAME() {
        return OPERATION_NAME;
    }

    public void setOPERATION_NAME(String OPERATION_NAME) {
        this.OPERATION_NAME = OPERATION_NAME;
    }

    public int getPROVIDER_ID() {
        return PROVIDER_ID;
    }

    public void setPROVIDER_ID(int PROVIDER_ID) {
        this.PROVIDER_ID = PROVIDER_ID;
    }
    
    
    
}
