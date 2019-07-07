/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author AYA
 */
public class TETopupResponse {

    private long transactionId;
    private String mobileNumber;
    private Long insertDate;
    private Long updateDate;
    private double serviceAmount;
    private double appliedFees;
    private double merchantCommission;
    private double tax;
    private double toBePaid;
    private String requestStatus;
    private String ledgerStatus;
    private Long accountId;
    private String deviceType;
    private String globalTrxId;
    private double transactionAmount;
    private Long ledgerTrxId;
    private String advertisement;
    private String providerReferenceNumber;

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

    public Long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Long insertDate) {
        this.insertDate = insertDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(double serviceAmount) {
        this.serviceAmount = serviceAmount;
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

    public double getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(double toBePaid) {
        this.toBePaid = toBePaid;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getLedgerStatus() {
        return ledgerStatus;
    }

    public void setLedgerStatus(String ledgerStatus) {
        this.ledgerStatus = ledgerStatus;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Long getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(Long ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public String getProviderReferenceNumber() {
        return providerReferenceNumber;
    }

    public void setProviderReferenceNumber(String providerReferenceNumber) {
        this.providerReferenceNumber = providerReferenceNumber;
    }

    @Override
    public String toString() {
        return "TETopupResponse{" + "transactionId=" + transactionId + ", mobileNumber=" + mobileNumber + ", insertDate=" + insertDate + ", updateDate=" + updateDate + ", serviceAmount=" + serviceAmount + ", appliedFees=" + appliedFees + ", merchantCommission=" + merchantCommission + ", tax=" + tax + ", toBePaid=" + toBePaid + ", requestStatus=" + requestStatus + ", ledgerStatus=" + ledgerStatus + ", accountId=" + accountId + ", deviceType=" + deviceType + ", globalTrxId=" + globalTrxId + ", transactionAmount=" + transactionAmount + ", ledgerTrxId=" + ledgerTrxId + ", advertisement=" + advertisement + ", providerReferenceNumber=" + providerReferenceNumber+'}';
    }

}
