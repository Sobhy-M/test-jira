/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Barakat Mostafa
 */
public class AlexWaterInquiryResponse {

    private String validationId;
    private double appliedFees;
    private double merchantCommission;
    private double masaryCommisssion;
    private double tax;
    private double toBepaid;
    private String globalTrxId;
    private double transactionAmount;
    private int ratePlanId;
    private int accountId;
    private String billId;
    private String billingrunName;
    private String billingrunNo;
    private String customerID;
    private String customerName;
    private double totalDueAmount;
    private String elecNo;
    private String expirationTime;

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
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

    public double getMasaryCommisssion() {
        return masaryCommisssion;
    }

    public void setMasaryCommisssion(double masaryCommisssion) {
        this.masaryCommisssion = masaryCommisssion;
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

    public int getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(int ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillingrunName() {
        return billingrunName;
    }

    public void setBillingrunName(String billingrunName) {
        this.billingrunName = billingrunName;
    }

    public String getBillingrunNo() {
        return billingrunNo;
    }

    public void setBillingrunNo(String billingrunNo) {
        this.billingrunNo = billingrunNo;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(double totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public String getElecNo() {
        return elecNo;
    }

    public void setElecNo(String elecNo) {
        this.elecNo = elecNo;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

}
