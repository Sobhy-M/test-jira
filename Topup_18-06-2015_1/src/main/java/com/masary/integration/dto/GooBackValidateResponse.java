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
public class GooBackValidateResponse {

    private double masaryCommission;
    private double merchantCommission;
    private long fromAccountId;
    private String serviceName;
    private String providerCategory;
    private int ratePlanId;
    private double appliedFees;
    private double tax;
    private double transactionAmount;
    private double toBepaid;

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

    public int getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(int ratePlanId) {
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

    
}
