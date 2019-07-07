/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.masary.database.dto;

import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Michael
 */
public class Masary_Payment_Request {
String REQUEST_ID;
XMLGregorianCalendar REQUEST_DATE;
int CUSTOMER_ID;
String MESSAGE_CODE;
String ASYNC_REQUEST_ID;
String ORIGINATOR_CODE;
String TERMINAL_ID;
String CLIENT_TERMINAL_SEQ_ID;
String POS_SERIAL_NUMBER;
String BILLING_ACCOUNT;
String NOTIFY_MOBILE;
String BILL_TYPE_CODE;
String PMT_TYPE;
String DELIVERY_METHOD;
double AMOUNT;
String ACCT_ID;
String ACCT_TYPE;
String ACCT_KEY;
String ACCT_CUR;
String PMT_METHOD;
XMLGregorianCalendar PRC_DATE;
String BILL_REF_NUMBER;
List<Customers_Properties> customers_Properties;
Masary_signon_profile masary_signon_profile;
String DUE_DATE;
double FEES_AMOUNT;

    public double getFEES_AMOUNT() {
        return FEES_AMOUNT;
    }

    public void setFEES_AMOUNT(double FEES_AMOUNT) {
        this.FEES_AMOUNT = FEES_AMOUNT;
    }

    public String getDUE_DATE() {
        return DUE_DATE;
    }

    public void setDUE_DATE(String DUE_DATE) {
        this.DUE_DATE = DUE_DATE;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public Masary_signon_profile getMasary_signon_profile() {
        return masary_signon_profile;
    }

    public void setMasary_signon_profile(Masary_signon_profile masary_signon_profile) {
        this.masary_signon_profile = masary_signon_profile;
    }


    public List<Customers_Properties> getCustomers_Properties() {
        return customers_Properties;
    }

    public void setCustomers_Properties(List<Customers_Properties> customers_Properties) {
        this.customers_Properties = customers_Properties;
    }

    public String getBILL_REF_NUMBER() {
        return BILL_REF_NUMBER;
    }

    public void setBILL_REF_NUMBER(String BILL_REF_NUMBER) {
        this.BILL_REF_NUMBER = BILL_REF_NUMBER;
    }


    public String getACCT_CUR() {
        return ACCT_CUR;
    }

    public void setACCT_CUR(String ACCT_CUR) {
        this.ACCT_CUR = ACCT_CUR;
    }

    public String getACCT_ID() {
        return ACCT_ID;
    }

    public void setACCT_ID(String ACCT_ID) {
        this.ACCT_ID = ACCT_ID;
    }

    public String getACCT_KEY() {
        return ACCT_KEY;
    }

    public void setACCT_KEY(String ACCT_KEY) {
        this.ACCT_KEY = ACCT_KEY;
    }

    public String getACCT_TYPE() {
        return ACCT_TYPE;
    }

    public void setACCT_TYPE(String ACCT_TYPE) {
        this.ACCT_TYPE = ACCT_TYPE;
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

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(int CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
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

    public String getPMT_METHOD() {
        return PMT_METHOD;
    }

    public void setPMT_METHOD(String PMT_METHOD) {
        this.PMT_METHOD = PMT_METHOD;
    }

    public String getPMT_TYPE() {
        return PMT_TYPE;
    }

    public void setPMT_TYPE(String PMT_TYPE) {
        this.PMT_TYPE = PMT_TYPE;
    }

    public String getPOS_SERIAL_NUMBER() {
        return POS_SERIAL_NUMBER;
    }

    public void setPOS_SERIAL_NUMBER(String POS_SERIAL_NUMBER) {
        this.POS_SERIAL_NUMBER = POS_SERIAL_NUMBER;
    }

    public XMLGregorianCalendar getPRC_DATE() {
        return PRC_DATE;
    }

    public void setPRC_DATE(XMLGregorianCalendar PRC_DATE) {
        this.PRC_DATE = PRC_DATE;
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

    public String getTERMINAL_ID() {
        return TERMINAL_ID;
    }

    public void setTERMINAL_ID(String TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID;
    }

}
