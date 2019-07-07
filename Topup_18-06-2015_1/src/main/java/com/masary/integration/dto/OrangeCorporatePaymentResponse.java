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
public class OrangeCorporatePaymentResponse {

    private String providerStatusCode;
    private String providerStatusMessage;
    private String globalTrxId;
    private String targetNumber;
    private String validationId;
    private String msisdn;
    private String accountNumber;
    private String requestStatus;
    private String deviceType;
    private String ledgerStatus;
    private double totalOpenAmount;
    private double paidAmount;
    private double appliedFees;
    private double merchantCommission;
    private double tax;
    private double toBepaid;
    private double masaryCommission;
    private double transactionAmount;
    private long requestId;
    private long accountId;
    private long ledgerTrxId;
    private long ratePlanId;
    private long insertDate;
    private long updateDate;

    public String getProviderStatusCode() {
        return providerStatusCode;
    }

    public void setProviderStatusCode(String providerStatusCode) {
        this.providerStatusCode = providerStatusCode;
    }

    public String getProviderStatusMessage() {
        return providerStatusMessage;
    }

    public void setProviderStatusMessage(String providerStatusMessage) {
        this.providerStatusMessage = providerStatusMessage;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public String getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLedgerStatus() {
        return ledgerStatus;
    }

    public void setLedgerStatus(String ledgerStatus) {
        this.ledgerStatus = ledgerStatus;
    }

    public double getTotalOpenAmount() {
        return totalOpenAmount;
    }

    public void setTotalOpenAmount(double totalOpenAmount) {
        this.totalOpenAmount = totalOpenAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
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

    public double getToBepaid() {
        return toBepaid;
    }

    public void setToBepaid(double toBepaid) {
        this.toBepaid = toBepaid;
    }

    public double getMasaryCommission() {
        return masaryCommission;
    }

    public void setMasaryCommission(double masaryCommission) {
        this.masaryCommission = masaryCommission;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(long ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
    }

    public long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    @Override
    public String toString() {
        return "OrangeCorporatePaymentResponse{" + "providerStatusCode=" + providerStatusCode + ", providerStatusMessage=" + providerStatusMessage + ", globalTrxId=" + globalTrxId + ", targetNumber=" + targetNumber + ", validationId=" + validationId + ", msisdn=" + msisdn + ", accountNumber=" + accountNumber + ", insertDate=" + insertDate + ", updateDate=" + updateDate + ", requestStatus=" + requestStatus + ", deviceType=" + deviceType + ", ledgerStatus=" + ledgerStatus + ", totalOpenAmount=" + totalOpenAmount + ", paidAmount=" + paidAmount + ", appliedFees=" + appliedFees + ", merchantCommission=" + merchantCommission + ", tax=" + tax + ", toBepaid=" + toBepaid + ", masaryCommission=" + masaryCommission + ", transactionAmount=" + transactionAmount + ", requestId=" + requestId + ", accountId=" + accountId + ", ledgerTrxId=" + ledgerTrxId + ", ratePlanId=" + ratePlanId + '}';
    }

}
