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
public class OrangeCorporateInquiryResponse {

    private String providerStatusCode;
    private String providerStatusMessage;
    private String msisdn;
    private String accountNumber;
    private String globalTrxId;
    private String validationId;
    private double totalOpenAmount;
    private double paidAmount;
    private double appliedFees;
    private double merchantCommission;
    private double masaryCommission;
    private double tax;
    private double toBepaid;
    private double transactionAmount;
    private long ratePlanId;
    private long accountId;
    private long expirationDate;

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

    public double getMasaryCommission() {
        return masaryCommission;
    }

    public void setMasaryCommission(double masaryCommission) {
        this.masaryCommission = masaryCommission;
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

    public long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

    @Override
    public String toString() {
        return "OrangeCorporateInquiryResponse{" + "providerStatusCode=" + providerStatusCode + ", providerStatusMessage=" + providerStatusMessage + ", msisdn=" + msisdn + ", accountNumber=" + accountNumber + ", globalTrxId=" + globalTrxId + ", validationId=" + validationId + ", totalOpenAmount=" + totalOpenAmount + ", paidAmount=" + paidAmount + ", appliedFees=" + appliedFees + ", merchantCommission=" + merchantCommission + ", masaryCommission=" + masaryCommission + ", tax=" + tax + ", toBepaid=" + toBepaid + ", transactionAmount=" + transactionAmount + ", ratePlanId=" + ratePlanId + ", accountId=" + accountId + ", expirationDate=" + expirationDate + '}';
    }

}
