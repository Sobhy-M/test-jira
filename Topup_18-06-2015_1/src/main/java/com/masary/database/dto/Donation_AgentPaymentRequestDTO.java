/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author KEMO
 */
public class Donation_AgentPaymentRequestDTO {

    int CUSTOMER_ID;
    String PASSWORD;
    String LANG;
    String AGENT_IDENTIFIER;
    String CHANNEL;
    String NAME;
    String CITY;
    int SERVICE_ID;
    int OPERATION_ID;
    int IS_SC;
    String AMOUNT;
    int BILL_REFERENCE_NUMBER;
    String NATIONAL_ID;

    /* two constructor Added By Moamen 22-8-2016 */
    public Donation_AgentPaymentRequestDTO() {
    }

    public Donation_AgentPaymentRequestDTO(int CUSTOMER_ID, String PASSWORD, String LANG, String AGENT_IDENTIFIER,
            String CHANNEL, String NAME, String CITY, int SERVICE_ID, int OPERATION_ID, int IS_SC, String AMOUNT, int BILL_REFERENCE_NUMBER, String NATIONAL_ID) {

        this.CUSTOMER_ID = CUSTOMER_ID;
        this.PASSWORD = PASSWORD;
        this.LANG = LANG;
        this.AGENT_IDENTIFIER = AGENT_IDENTIFIER;
        this.CHANNEL = CHANNEL;
        this.NAME = NAME;
        this.CITY = CITY;
        this.SERVICE_ID = SERVICE_ID;
        this.OPERATION_ID = OPERATION_ID;
        this.IS_SC = IS_SC;
        this.AMOUNT = AMOUNT;
        this.BILL_REFERENCE_NUMBER = BILL_REFERENCE_NUMBER;
        this.NATIONAL_ID = NATIONAL_ID;

    }

    public int getBILL_REFERENCE_NUMBER() {
        return BILL_REFERENCE_NUMBER;
    }

    public String getNATIONAL_ID() {
        return NATIONAL_ID;
    }

    public void setNATIONAL_ID(String NATIONAL_ID) {
        this.NATIONAL_ID = NATIONAL_ID;
    }

    public void setBILL_REFERENCE_NUMBER(int BILL_REFERENCE_NUMBER) {
        this.BILL_REFERENCE_NUMBER = BILL_REFERENCE_NUMBER;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getOPERATION_ID() {
        return OPERATION_ID;
    }

    public void setOPERATION_ID(int OPERATION_ID) {
        this.OPERATION_ID = OPERATION_ID;
    }

    public String getAGENT_IDENTIFIER() {
        return AGENT_IDENTIFIER;
    }

    public void setAGENT_IDENTIFIER(String AGENT_IDENTIFIER) {
        this.AGENT_IDENTIFIER = AGENT_IDENTIFIER;
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

    public int getIS_SC() {
        return IS_SC;
    }

    public void setIS_SC(int IS_SC) {
        this.IS_SC = IS_SC;
    }

    public String getLANG() {
        return LANG;
    }

    public void setLANG(String LANG) {
        this.LANG = LANG;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(int SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

}
