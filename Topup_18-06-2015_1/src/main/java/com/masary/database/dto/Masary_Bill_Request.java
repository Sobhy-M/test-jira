/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Michael
 */
public class Masary_Bill_Request {

    String REQUEST_ID;
    XMLGregorianCalendar REQUEST_DATE;
    String MESSAGE_CODE;
    String ASYNC_REQUEST_ID;
    String ORIGINATOR_CODE;
    Boolean INCOPEN_AMOUNT;
    String BILLING_ACCOUNT;
    String BANK_ID;
    String BILL_TYPE_CODE;
    String DELEVERY_METHOD;
    int PROVIDER_ID;
    int CUSTOMER_ID;
    String TERMINALID;
    Masary_signon_profile masary_signon_profile;

    public String getTERMINALID() {
        return TERMINALID;
    }

    public void setTERMINALID(String TERMINALID) {
        this.TERMINALID = TERMINALID;
    }

    public String getASYNC_REQUEST_ID() {
        return ASYNC_REQUEST_ID;
    }

    public void setASYNC_REQUEST_ID(String ASYNC_REQUEST_ID) {
        this.ASYNC_REQUEST_ID = ASYNC_REQUEST_ID;
    }

    public String getBANK_ID() {
        return BANK_ID;
    }

    public void setBANK_ID(String BANK_ID) {
        this.BANK_ID = BANK_ID;
    }

    public String getBILLING_ACCOUNT() {
        return BILLING_ACCOUNT;
    }

    public void setBILLING_ACCOUNT(String BILLING_ACCOUNT) {
        this.BILLING_ACCOUNT = BILLING_ACCOUNT;
    }

    public String getBILL_TYPE_CODE() {
        return BILL_TYPE_CODE;
    }

    public void setBILL_TYPE_CODE(String BILL_TYPE_CODE) {
        this.BILL_TYPE_CODE = BILL_TYPE_CODE;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(int CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getDELEVERY_METHOD() {
        return DELEVERY_METHOD;
    }

    public void setDELEVERY_METHOD(String DELEVERY_METHOD) {
        this.DELEVERY_METHOD = DELEVERY_METHOD;
    }

    public Boolean getINCOPEN_AMOUNT() {
        return INCOPEN_AMOUNT;
    }

    public void setINCOPEN_AMOUNT(Boolean INCOPEN_AMOUNT) {
        this.INCOPEN_AMOUNT = INCOPEN_AMOUNT;
    }

    public String getMESSAGE_CODE() {
        return MESSAGE_CODE;
    }

    public void setMESSAGE_CODE(String MESSAGE_CODE) {
        this.MESSAGE_CODE = MESSAGE_CODE;
    }

   

    public String getORIGINATOR_CODE() {
        return ORIGINATOR_CODE;
    }

    public void setORIGINATOR_CODE(String ORIGINATOR_CODE) {
        this.ORIGINATOR_CODE = ORIGINATOR_CODE;
    }

    public int getPROVIDER_ID() {
        return PROVIDER_ID;
    }

    public void setPROVIDER_ID(int PROVIDER_ID) {
        this.PROVIDER_ID = PROVIDER_ID;
    }

    public XMLGregorianCalendar getREQUEST_DATE() {
        return REQUEST_DATE;
    }

    public void setREQUEST_DATE(XMLGregorianCalendar REQUEST_DATE) {
        this.REQUEST_DATE = REQUEST_DATE;
    }

    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }

    public Masary_signon_profile getMasary_signon_profile() {
        return masary_signon_profile;
    }

    public void setMasary_signon_profile(Masary_signon_profile masary_signon_profile) {
        this.masary_signon_profile = masary_signon_profile;
    }

}
