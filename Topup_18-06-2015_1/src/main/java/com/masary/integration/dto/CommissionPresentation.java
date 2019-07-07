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
public class CommissionPresentation {

    /// Masary Data 
    private double masaryCommission;
    private double merchantCommission;
    private long fromAccountId;
    private String serviceName;
    private String providerCategory;
    private long ratePlanId;
    private double appliedFees;
    private double tax;
    private double transactionAmount;
    private double toBepaid;
    private double serviceAmount;
    /// Provider Data  
    private String subscriberName;
    private double amount;
    private double billsNumber;
    private String oldestBillDate;
    private String secondBillDate;
    private String subscriberNumber;

    public String getOldestBillDate() {
        return oldestBillDate;
    }

    public void setOldestBillDate(String oldestBillDate) {
        this.oldestBillDate = oldestBillDate;
    }

    public String getSecondBillDate() {
        return secondBillDate;
    }

    public void setSecondBillDate(String secondBillDate) {
        this.secondBillDate = secondBillDate;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBillsNumber() {
        return billsNumber;
    }

    public void setBillsNumber(double billsNumber) {
        this.billsNumber = billsNumber;
    }

    public double getMasaryCommission() {
        return masaryCommission;
    }

    public void setMasaryCommission(double masaryCommission) {
        this.masaryCommission = masaryCommission;
    }

    public double getMerchantCommission() {
        return merchantCommission;
    }

    public void setMerchantCommission(double merchantCommission) {
        this.merchantCommission = merchantCommission;
    }

    public long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getProviderCategory() {
        return providerCategory;
    }

    public void setProviderCategory(String providerCategory) {
        this.providerCategory = providerCategory;
    }

    public long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(double appliedFees) {
        this.appliedFees = appliedFees;
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

    public double getToBepaid() {
        return toBepaid;
    }

    public void setToBepaid(double toBepaid) {
        this.toBepaid = toBepaid;
    }

    public double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

}
