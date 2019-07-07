/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author amira
 */
public class ValuPaymentResponse {


    private long insertDate;
    private double appliedFees;
    private double merchantCommission;
    private double tax;
    private double toBepaid;
    private String requestStatus;
    private int accountId;
    private String deviceType;
    private String globalTrxId;
    private double transactionAmount;
    private long ledgerTrxId;
    private String ledgerStatus;
    private int ratePlanId;
    private String inquiryReferenceNumber;
    private String paymentrReferenceNumber;
    private double paidAmount;
    private String mobilNumber;
    private String providerResponse;
    private String nationId;
    private double dueAmount;
    private String instMatDate;
    private String instNo;
    private String clientName;
    private String clientId;
    private double appliedFeesPlusTax;

    public long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(long insertDate) {
        this.insertDate = insertDate;
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

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
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

    public long getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(long ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
    }

    public String getLedgerStatus() {
        return ledgerStatus;
    }

    public void setLedgerStatus(String ledgerStatus) {
        this.ledgerStatus = ledgerStatus;
    }

    public int getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(int ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public String getInquiryReferenceNumber() {
        return inquiryReferenceNumber;
    }

    public void setInquiryReferenceNumber(String inquiryReferenceNumber) {
        this.inquiryReferenceNumber = inquiryReferenceNumber;
    }

    public String getPaymentrReferenceNumber() {
        return paymentrReferenceNumber;
    }

    public void setPaymentrReferenceNumber(String paymentrReferenceNumber) {
        this.paymentrReferenceNumber = paymentrReferenceNumber;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getMobilNumber() {
        return mobilNumber;
    }

    public void setMobilNumber(String mobilNumber) {
        this.mobilNumber = mobilNumber;
    }

    public String getProviderResponse() {
        return providerResponse;
    }

    public void setProviderResponse(String providerResponse) {
        this.providerResponse = providerResponse;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getInstMatDate() {
        return instMatDate;
    }

    public void setInstMatDate(String instMatDate) {
        this.instMatDate = instMatDate;
    }

    public String getInstNo() {
        return instNo;
    }

    public void setInstNo(String instNo) {
        this.instNo = instNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    /**
     * @return the appliedFeesPlusTax
     */
    public double getAppliedFeesPlusTax() {
        return appliedFeesPlusTax;
    }

    /**
     * @param appliedFeesPlusTax the appliedFeesPlusTax to set
     */
    public void setAppliedFeesPlusTax(double appliedFeesPlusTax) {
        this.appliedFeesPlusTax = appliedFeesPlusTax;
    }

}
