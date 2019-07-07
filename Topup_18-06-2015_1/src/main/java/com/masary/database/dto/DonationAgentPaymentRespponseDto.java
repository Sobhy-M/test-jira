/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author KEMO
 */
public class DonationAgentPaymentRespponseDto {

    String OPERATION_NAME;
    double FEES;
    double FIXED_AMOUNT;
    int PROVIDER_ID;
    int OPERATION_ID;
    String STATUS_CODE;
    String STATUS_MESSAGE;
    double AMOUNT;
    String AGENT_IDENTIFIER;
    String CODE_DETAILS;
    int IS_FRACTION;
    int IS_PARTIAL;
    int IS_OVER;
    int IS_ADVANCED;
    int TRANSACTION_ID;
    String PROVIDER_TRANSACTION_ID;
    int BILL_REFERENCE_NUMBER;
    private String DATE;

    public int getBILL_REFERENCE_NUMBER() {
        return BILL_REFERENCE_NUMBER;
    }

    public void setBILL_REFERENCE_NUMBER(int BILL_REFERENCE_NUMBER) {
        this.BILL_REFERENCE_NUMBER = BILL_REFERENCE_NUMBER;
    }

    public String getAGENT_IDENTIFIER() {
        return AGENT_IDENTIFIER;
    }

    public void setAGENT_IDENTIFIER(String AGENT_IDENTIFIER) {
        this.AGENT_IDENTIFIER = AGENT_IDENTIFIER;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getCODE_DETAILS() {
        return CODE_DETAILS;
    }

    public void setCODE_DETAILS(String CODE_DETAILS) {
        this.CODE_DETAILS = CODE_DETAILS;
    }

    public int getIS_ADVANCED() {
        return IS_ADVANCED;
    }

    public void setIS_ADVANCED(int IS_ADVANCED) {
        this.IS_ADVANCED = IS_ADVANCED;
    }

    public int getIS_FRACTION() {
        return IS_FRACTION;
    }

    public void setIS_FRACTION(int IS_FRACTION) {
        this.IS_FRACTION = IS_FRACTION;
    }

    public int getIS_OVER() {
        return IS_OVER;
    }

    public void setIS_OVER(int IS_OVER) {
        this.IS_OVER = IS_OVER;
    }

    public int getIS_PARTIAL() {
        return IS_PARTIAL;
    }

    public void setIS_PARTIAL(int IS_PARTIAL) {
        this.IS_PARTIAL = IS_PARTIAL;
    }

    public String getPROVIDER_TRANSACTION_ID() {
        return PROVIDER_TRANSACTION_ID;
    }

    public void setPROVIDER_TRANSACTION_ID(String PROVIDER_TRANSACTION_ID) {
        this.PROVIDER_TRANSACTION_ID = PROVIDER_TRANSACTION_ID;
    }

    public int getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public void setTRANSACTION_ID(int TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

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

    public Double getFIXED_AMOUNT() {
        return FIXED_AMOUNT;
    }

    public void setFIXED_AMOUNT(Double FIXED_AMOUNT) {
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

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    @Override
    public String toString() {
        return "DonationAgentPaymentRespponseDto{" + "OPERATION_NAME=" + OPERATION_NAME + ", FEES=" + FEES + ", FIXED_AMOUNT=" + FIXED_AMOUNT + ", PROVIDER_ID=" + PROVIDER_ID + ", OPERATION_ID=" + OPERATION_ID + ", STATUS_CODE=" + STATUS_CODE + ", STATUS_MESSAGE=" + STATUS_MESSAGE + ", AMOUNT=" + AMOUNT + ", AGENT_IDENTIFIER=" + AGENT_IDENTIFIER + ", CODE_DETAILS=" + CODE_DETAILS + ", IS_FRACTION=" + IS_FRACTION + ", IS_PARTIAL=" + IS_PARTIAL + ", IS_OVER=" + IS_OVER + ", IS_ADVANCED=" + IS_ADVANCED + ", TRANSACTION_ID=" + TRANSACTION_ID + ", PROVIDER_TRANSACTION_ID=" + PROVIDER_TRANSACTION_ID + ", BILL_REFERENCE_NUMBER=" + BILL_REFERENCE_NUMBER + ", DATE=" + DATE + '}';
    }

}
