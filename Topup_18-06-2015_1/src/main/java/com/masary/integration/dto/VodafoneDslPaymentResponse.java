/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Ahmed Khaled
 */
public class VodafoneDslPaymentResponse {

    private long requestId;

    private String requestStatus;

    private long accountId;

    private String targetNumber;

    private int amount;

    private long ledgerTransactionId;

    private String ledgerTransactionStatus;

    private String deviceType;

    private String globalTrxId;

    private String operatorTransactionId;

    private String opratorStatusCode;

    private long insertTime;

    private long updateTime;

    private double appliedFees;

    private double merchantCommission;

    private double tax;

    private double transactionAmount;

    private int toBePaid;

    private String externalTRXId;

    private double rateplanAmount;

    private double providerAmount;
    
    private String advertisementStatment;

    public String getAdvertisementStatment() {
        return advertisementStatment;
    }

    public void setAdvertisementStatment(String advertisementStatment) {
        this.advertisementStatment = advertisementStatment;
    }
    
    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getLedgerTransactionId() {
        return ledgerTransactionId;
    }

    public void setLedgerTransactionId(long ledgerTransactionId) {
        this.ledgerTransactionId = ledgerTransactionId;
    }

    public String getLedgerTransactionStatus() {
        return ledgerTransactionStatus;
    }

    public void setLedgerTransactionStatus(String ledgerTransactionStatus) {
        this.ledgerTransactionStatus = ledgerTransactionStatus;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public String getOperatorTransactionId() {
        return operatorTransactionId;
    }

    public void setOperatorTransactionId(String operatorTransactionId) {
        this.operatorTransactionId = operatorTransactionId;
    }

    public String getOpratorStatusCode() {
        return opratorStatusCode;
    }

    public void setOpratorStatusCode(String opratorStatusCode) {
        this.opratorStatusCode = opratorStatusCode;
    }

    public long getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(long insertTime) {
        this.insertTime = insertTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(double appliedFees) {
        this.appliedFees = appliedFees;
    }

    public double getMerchantCommission() {
        return merchantCommission;
    }

    public void setMerchantCommission(double merchantCommission) {
        this.merchantCommission = merchantCommission;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(int toBePaid) {
        this.toBePaid = toBePaid;
    }

    public String getExternalTRXId() {
        return externalTRXId;
    }

    public void setExternalTRXId(String externalTRXId) {
        this.externalTRXId = externalTRXId;
    }

    public double getRateplanAmount() {
        return rateplanAmount;
    }

    public void setRateplanAmount(double rateplanAmount) {
        this.rateplanAmount = rateplanAmount;
    }

    public double getProviderAmount() {
        return providerAmount;
    }

    public void setProviderAmount(double providerAmount) {
        this.providerAmount = providerAmount;
    }
    
    
}
