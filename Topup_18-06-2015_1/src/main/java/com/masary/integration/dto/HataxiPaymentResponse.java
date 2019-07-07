/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author hammad
 */
public class HataxiPaymentResponse {
    private long transactionId;
    private String mobileNumber;
    private long insertDate;
    private long updateDate;
    private String globalTrxId;
    private long ledgerTrxId;
    private Double serviceAmount; //value user entered 
    private Double transactionAmount; //will be deducted from merchant 
    private Double appliedFees; 
    private Double toBePaid; 
    private Double mercherntCommission;
    private String transactionStatus;
    private String ledgerStatus;
    private String deviceType;
    private long payerAccountId;	

    public Double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(Double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(Double appliedFees) {
        this.appliedFees = appliedFees;
    }

    public Double getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(Double toBePaid) {
        this.toBePaid = toBePaid;
    }

    
    
    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(long insertDate) {
        this.insertDate = insertDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public long getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(long ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
    }

    public Double getMercherntCommission() {
        return mercherntCommission;
    }

    public void setMercherntCommission(Double mercherntCommission) {
        this.mercherntCommission = mercherntCommission;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getLedgerStatus() {
        return ledgerStatus;
    }

    public void setLedgerStatus(String ledgerStatus) {
        this.ledgerStatus = ledgerStatus;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public long getPayerAccountId() {
        return payerAccountId;
    }

    public void setPayerAccountId(long payerAccountId) {
        this.payerAccountId = payerAccountId;
    }
    
    
}
