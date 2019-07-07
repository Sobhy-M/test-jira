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
public class Masary_Bill_Response {

    String RESPONSE_DATE;
    XMLGregorianCalendar CLIENT_DATE;
    String SERVER_DATE;
    String MESSAGE_CODE;
    String REQUEST_ID;
    String ASYNC_REQUEST_ID;
    String ORIGINATOR_CODE;
    String STATUS_CODE;
    Boolean INCOPENAMOUNT;
    String DELIVERY_METHOD;
    String BILL_NUMBER;
    String BILLING_ACCOUNT;
    String BILL_TYPE_CODE;
    String BILL_REF_NUMBER;
    String DUE_DATE;
    String EXP_DATE;
    String ISSUE_DATE;
    double LOWER_AMOUNT;
    String LOWER_CURRUNT_AMOUNT;
    double UPPER_AMOUNT;
    String UPPER_CURRENT_AMOUNT;
    String BILL_STATUS;
    String TerminalId;
    String RulesAwareness;
    String Language;
    List<Masary_Bill_Amounts> Amounts;
    Masary_signon_profile masary_signon_profile;
    Masary_Bill_Status masary_Bill_Status;
    Main_Provider provider;
    String Customer_name;
    String Additional_Info;
    String CustomerMobile;

    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }
    

    public String getAdditional_Info() {
        return Additional_Info;
    }

    public void setAdditional_Info(String Additional_Info) {
        this.Additional_Info = Additional_Info;
    }
    
    

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String Customer_name) {
        this.Customer_name = Customer_name;
    }
    

    public Main_Provider getProvider() {
        return provider;
    }

    public void setProvider(Main_Provider provider) {
        this.provider = provider;
    }

    public Masary_Bill_Status getMasary_Bill_Status() {
        return masary_Bill_Status;
    }

    public void setMasary_Bill_Status(Masary_Bill_Status masary_Bill_Status) {
        this.masary_Bill_Status = masary_Bill_Status;
    }

    public Masary_signon_profile getMasary_signon_profile() {
        return masary_signon_profile;
    }

    public void setMasary_signon_profile(Masary_signon_profile masary_signon_profile) {
        this.masary_signon_profile = masary_signon_profile;
    }
    

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public XMLGregorianCalendar getCLIENT_DATE() {
        return CLIENT_DATE;
    }

    public void setCLIENT_DATE(XMLGregorianCalendar CLIENT_DATE) {
        this.CLIENT_DATE = CLIENT_DATE;
    }

    public String getRulesAwareness() {
        return RulesAwareness;
    }

    public void setRulesAwareness(String RulesAwareness) {
        this.RulesAwareness = RulesAwareness;
    }

    public List<Masary_Bill_Amounts> getAmounts() {
        return Amounts;
    }

    public void setAmounts(List<Masary_Bill_Amounts> Amounts) {
        this.Amounts = Amounts;
    }

    public String getTerminalId() {
        return TerminalId;
    }

    public void setTerminalId(String TerminalId) {
        this.TerminalId = TerminalId;
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

    public String getBILL_STATUS() {
        return BILL_STATUS;
    }

    public void setBILL_STATUS(String BILL_STATUS) {
        this.BILL_STATUS = BILL_STATUS;
    }

    public String getBILL_TYPE_CODE() {
        return BILL_TYPE_CODE;
    }

    public void setBILL_TYPE_CODE(String BILL_TYPE_CODE) {
        this.BILL_TYPE_CODE = BILL_TYPE_CODE;
    }

    public String getDELIVERY_METHOD() {
        return DELIVERY_METHOD;
    }

    public void setDELIVERY_METHOD(String DELIVERY_METHOD) {
        this.DELIVERY_METHOD = DELIVERY_METHOD;
    }

    public String getDUE_DATE() {
        return DUE_DATE;
    }

    public void setDUE_DATE(String DUE_DATE) {
        this.DUE_DATE = DUE_DATE;
    }

    public String getEXP_DATE() {
        return EXP_DATE;
    }

    public void setEXP_DATE(String EXP_DATE) {
        this.EXP_DATE = EXP_DATE;
    }

    public Boolean getINCOPENAMOUNT() {
        return INCOPENAMOUNT;
    }

    public void setINCOPENAMOUNT(Boolean INCOPENAMOUNT) {
        this.INCOPENAMOUNT = INCOPENAMOUNT;
    }

    public String getISSUE_DATE() {
        return ISSUE_DATE;
    }

    public void setISSUE_DATE(String ISSUE_DATE) {
        this.ISSUE_DATE = ISSUE_DATE;
    }

    public double getLOWER_AMOUNT() {
        return LOWER_AMOUNT;
    }

    public void setLOWER_AMOUNT(double LOWER_AMOUNT) {
        this.LOWER_AMOUNT = LOWER_AMOUNT;
    }

    public String getLOWER_CURRUNT_AMOUNT() {
        return LOWER_CURRUNT_AMOUNT;
    }

    public void setLOWER_CURRUNT_AMOUNT(String LOWER_CURRUNT_AMOUNT) {
        this.LOWER_CURRUNT_AMOUNT = LOWER_CURRUNT_AMOUNT;
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

    public double getUPPER_AMOUNT() {
        return UPPER_AMOUNT;
    }

    public void setUPPER_AMOUNT(double UPPER_AMOUNT) {
        this.UPPER_AMOUNT = UPPER_AMOUNT;
    }

    public String getUPPER_CURRENT_AMOUNT() {
        return UPPER_CURRENT_AMOUNT;
    }

    public void setUPPER_CURRENT_AMOUNT(String UPPER_CURRENT_AMOUNT) {
        this.UPPER_CURRENT_AMOUNT = UPPER_CURRENT_AMOUNT;
    }
}
