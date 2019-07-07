/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.math.BigDecimal;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.DateTime;

/**
 *
 * @author Aya
 */
public class CashUResponse {

    private int STATUS_CODE;
    private String STATUS_MESSAGE;
    private String vendorId;
    private BigDecimal amount;
    private String currency;
    private String userId;
    private String cashUTrnID;
    private BigDecimal balance;
    private String balanceCurr;
    private XMLGregorianCalendar trnDate;
    private XMLGregorianCalendar reconDate;
    private String email;
    private String userName;
    private String holderName;
    private String refID;
    private double FEES;
    private int TRANSACTION_ID;
    private String PROVIDER_TRANSACTION_ID;
    private int IS_FRACTION;
    private int IS_PARTIAL;
    private int IS_OVER;
    private int IS_ADVANCED;
    private String ADDITIONAL_INFO;
    private String BILL_REFERENCE_NUMBER;

    public double getFEES() {
        return FEES;
    }

    public void setFEES(double FEES) {
        this.FEES = FEES;
    }

    public int getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public void setTRANSACTION_ID(int TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

  

    public String getPROVIDER_TRANSACTION_ID() {
        return PROVIDER_TRANSACTION_ID;
    }

    public void setPROVIDER_TRANSACTION_ID(String PROVIDER_TRANSACTION_ID) {
        this.PROVIDER_TRANSACTION_ID = PROVIDER_TRANSACTION_ID;
    }

    public int getIS_FRACTION() {
        return IS_FRACTION;
    }

    public void setIS_FRACTION(int IS_FRACTION) {
        this.IS_FRACTION = IS_FRACTION;
    }

    public int getIS_PARTIAL() {
        return IS_PARTIAL;
    }

    public void setIS_PARTIAL(int IS_PARTIAL) {
        this.IS_PARTIAL = IS_PARTIAL;
    }

    public int getIS_OVER() {
        return IS_OVER;
    }

    public void setIS_OVER(int IS_OVER) {
        this.IS_OVER = IS_OVER;
    }

    public int getIS_ADVANCED() {
        return IS_ADVANCED;
    }

    public void setIS_ADVANCED(int IS_ADVANCED) {
        this.IS_ADVANCED = IS_ADVANCED;
    }

    public String getADDITIONAL_INFO() {
        return ADDITIONAL_INFO;
    }

    public void setADDITIONAL_INFO(String ADDITIONAL_INFO) {
        this.ADDITIONAL_INFO = ADDITIONAL_INFO;
    }

    public String getBILL_REFERENCE_NUMBER() {
        return BILL_REFERENCE_NUMBER;
    }

    public void setBILL_REFERENCE_NUMBER(String BILL_REFERENCE_NUMBER) {
        this.BILL_REFERENCE_NUMBER = BILL_REFERENCE_NUMBER;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
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

    public String getSTATUS_MESSAGE() {
        return STATUS_MESSAGE;
    }

    public void setSTATUS_MESSAGE(String STATUS_MESSAGE) {
        this.STATUS_MESSAGE = STATUS_MESSAGE;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCashUTrnID() {
        return cashUTrnID;
    }

    public void setCashUTrnID(String cashUTrnID) {
        this.cashUTrnID = cashUTrnID;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBalanceCurr() {
        return balanceCurr;
    }

    public void setBalanceCurr(String balanceCurr) {
        this.balanceCurr = balanceCurr;
    }

    public XMLGregorianCalendar getTrnDate() {
        return trnDate;
    }

    public void setTrnDate(XMLGregorianCalendar trnDate) {
        this.trnDate = trnDate;
    }

    public XMLGregorianCalendar getReconDate() {
        return reconDate;
    }

    public void setReconDate(XMLGregorianCalendar reconDate) {
        this.reconDate = reconDate;
    }

    public int getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(int STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }
}
