/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.List;

/**
 *
 * @author Michael
 */
public class Masary_Payment_Response {

    String RESPONSE_DATE;
    String SERVER_DATE;
    Masary_signon_profile masary_signon_profile;
    String MESSAGE_CODE;
    String REQUEST_ID;
    String ASYNC_REQUEST_ID;
    String ORIGINATOR_CODE;
    String TERMINAL_ID;
    String CLIENT_TERMINAL_SEQ_ID;
    String POS_SERIAL_NUMBER;
    String STATUS_CODE;
    String BILL_NUMBER;
    String BILLING_ACCOUNT;
    String BILL_REF_NUMBER;
    String NOTIFY_MOBILE;
    String BILL_TYPE_CODE;
    String PAYMENT_TYPE;
    String DELIVERY_METHOD;
    double CURRENT_AMOUNT;
    String ACCOUNT_ID;
    String ACCOUNT_TYPE;
    String ACCOUNT_KEY;
    String ACCOUNT_CUR;
    String PAYMENT_METHOD;
    String PRC_DATE;
    List<Customers_Properties> customers_Properties;
    Masary_Bill_Status masary_Bill_Status;
    List<Masary_Payment_Type> masary_Payment_Types;
    double FEES_AMOUNT;
    String customerName;
    String DUE_DATE;

    public String getDUE_DATE() {
        return DUE_DATE;
    }

    public void setDUE_DATE(String DUE_DATE) {
        this.DUE_DATE = DUE_DATE;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getFEES_AMOUNT() {
        return FEES_AMOUNT;
    }

    public void setFEES_AMOUNT(double FEES_AMOUNT) {
        this.FEES_AMOUNT = FEES_AMOUNT;
    }

    public List<Masary_Payment_Type> getMasary_Payment_Types() {
        return masary_Payment_Types;
    }

    public void setMasary_Payment_Types(List<Masary_Payment_Type> masary_Payment_Types) {
        this.masary_Payment_Types = masary_Payment_Types;
    }

    public Masary_Bill_Status getMasary_Bill_Status() {
        return masary_Bill_Status;
    }

    public void setMasary_Bill_Status(Masary_Bill_Status masary_Bill_Status) {
        this.masary_Bill_Status = masary_Bill_Status;
    }

    public List<Customers_Properties> getCustomers_Properties() {
        return customers_Properties;
    }

    public void setCustomers_Properties(List<Customers_Properties> customers_Properties) {
        this.customers_Properties = customers_Properties;
    }

    public String getACCOUNT_CUR() {
        return ACCOUNT_CUR;
    }

    public void setACCOUNT_CUR(String ACCOUNT_CUR) {
        this.ACCOUNT_CUR = ACCOUNT_CUR;
    }

    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getACCOUNT_KEY() {
        return ACCOUNT_KEY;
    }

    public void setACCOUNT_KEY(String ACCOUNT_KEY) {
        this.ACCOUNT_KEY = ACCOUNT_KEY;
    }

    public String getACCOUNT_TYPE() {
        return ACCOUNT_TYPE;
    }

    public void setACCOUNT_TYPE(String ACCOUNT_TYPE) {
        this.ACCOUNT_TYPE = ACCOUNT_TYPE;
    }

    public String getASYNC_REQUEST_ID() {
        return ASYNC_REQUEST_ID;
    }

    public void setASYNC_REQUEST_ID(String ASYNC_REQUEST_ID) {
        this.ASYNC_REQUEST_ID = ASYNC_REQUEST_ID;
    }

    public String getBILLING_ACCOUNT() {
        return BILLING_ACCOUNT;
    }

    public void setBILLING_ACCOUNT(String BILLING_ACCOUNT) {
        this.BILLING_ACCOUNT = BILLING_ACCOUNT;
    }

    public String getBILL_NUMBER() {
        return BILL_NUMBER;
    }

    public void setBILL_NUMBER(String BILL_NUMBER) {
        this.BILL_NUMBER = BILL_NUMBER;
    }

    public String getBILL_REF_NUMBER() {
        return BILL_REF_NUMBER;
    }

    public void setBILL_REF_NUMBER(String BILL_REF_NUMBER) {
        this.BILL_REF_NUMBER = BILL_REF_NUMBER;
    }

    public String getBILL_TYPE_CODE() {
        return BILL_TYPE_CODE;
    }

    public void setBILL_TYPE_CODE(String BILL_TYPE_CODE) {
        this.BILL_TYPE_CODE = BILL_TYPE_CODE;
    }

    public String getCLIENT_TERMINAL_SEQ_ID() {
        return CLIENT_TERMINAL_SEQ_ID;
    }

    public void setCLIENT_TERMINAL_SEQ_ID(String CLIENT_TERMINAL_SEQ_ID) {
        this.CLIENT_TERMINAL_SEQ_ID = CLIENT_TERMINAL_SEQ_ID;
    }

    public double getCURRENT_AMOUNT() {
        return CURRENT_AMOUNT;
    }

    public void setCURRENT_AMOUNT(double CURRENT_AMOUNT) {
        this.CURRENT_AMOUNT = CURRENT_AMOUNT;
    }

    public String getDELIVERY_METHOD() {
        return DELIVERY_METHOD;
    }

    public void setDELIVERY_METHOD(String DELIVERY_METHOD) {
        this.DELIVERY_METHOD = DELIVERY_METHOD;
    }

    public String getMESSAGE_CODE() {
        return MESSAGE_CODE;
    }

    public void setMESSAGE_CODE(String MESSAGE_CODE) {
        this.MESSAGE_CODE = MESSAGE_CODE;
    }

    public String getNOTIFY_MOBILE() {
        return NOTIFY_MOBILE;
    }

    public void setNOTIFY_MOBILE(String NOTIFY_MOBILE) {
        this.NOTIFY_MOBILE = NOTIFY_MOBILE;
    }

    public String getORIGINATOR_CODE() {
        return ORIGINATOR_CODE;
    }

    public void setORIGINATOR_CODE(String ORIGINATOR_CODE) {
        this.ORIGINATOR_CODE = ORIGINATOR_CODE;
    }

    public String getPAYMENT_METHOD() {
        return PAYMENT_METHOD;
    }

    public void setPAYMENT_METHOD(String PAYMENT_METHOD) {
        this.PAYMENT_METHOD = PAYMENT_METHOD;
    }

    public String getPAYMENT_TYPE() {
        return PAYMENT_TYPE;
    }

    public void setPAYMENT_TYPE(String PAYMENT_TYPE) {
        this.PAYMENT_TYPE = PAYMENT_TYPE;
    }

    public String getPOS_SERIAL_NUMBER() {
        return POS_SERIAL_NUMBER;
    }

    public void setPOS_SERIAL_NUMBER(String POS_SERIAL_NUMBER) {
        this.POS_SERIAL_NUMBER = POS_SERIAL_NUMBER;
    }

    public String getPRC_DATE() {
        return PRC_DATE;
    }

    public void setPRC_DATE(String PRC_DATE) {
        this.PRC_DATE = PRC_DATE;
    }

    public String getREQUEST_ID() {
        return REQUEST_ID;
    }

    public void setREQUEST_ID(String REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }

    public String getRESPONSE_DATE() {
        return RESPONSE_DATE;
    }

    public void setRESPONSE_DATE(String RESPONSE_DATE) {
        this.RESPONSE_DATE = RESPONSE_DATE;
    }

    public String getSERVER_DATE() {
        return SERVER_DATE;
    }

    public void setSERVER_DATE(String SERVER_DATE) {
        this.SERVER_DATE = SERVER_DATE;
    }

    public String getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(String STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public String getTERMINAL_ID() {
        return TERMINAL_ID;
    }

    public void setTERMINAL_ID(String TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID;
    }

    public Masary_signon_profile getMasary_signon_profile() {
        return masary_signon_profile;
    }

    public void setMasary_signon_profile(Masary_signon_profile masary_signon_profile) {
        this.masary_signon_profile = masary_signon_profile;
    }
}
