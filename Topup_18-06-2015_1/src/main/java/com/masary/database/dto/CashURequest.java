/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.math.BigDecimal;
import org.joda.time.DateTime;

/**
 *
 * @author Aya
 */
public class CashURequest {

    private String email;
    private String userName;
    private String vendorId;
    private BigDecimal amount;
    private String currency;
    private String isretry;
    private String userId;
    private int OPERATION_ID;
    private String refID;
    private String LANG;
    private int CUSTOMER_ID;
    private int SERVICE_ID;
    private String PASSWORD;
    private String BILL_REFERENCE_NUMBER;

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public int getOPERATION_ID() {
        return OPERATION_ID;
    }

    public void setOPERATION_ID(int OPERATION_ID) {
        this.OPERATION_ID = OPERATION_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIsretry() {
        return isretry;
    }

    public void setIsretry(String isretry) {
        this.isretry = isretry;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLANG() {
        return LANG;
    }

    public void setLANG(String LANG) {
        this.LANG = LANG;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(int CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public int getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(int SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getBILL_REFERENCE_NUMBER() {
        return BILL_REFERENCE_NUMBER;
    }

    public void setBILL_REFERENCE_NUMBER(String BILL_REFERENCE_NUMBER) {
        this.BILL_REFERENCE_NUMBER = BILL_REFERENCE_NUMBER;
    }


    
}
