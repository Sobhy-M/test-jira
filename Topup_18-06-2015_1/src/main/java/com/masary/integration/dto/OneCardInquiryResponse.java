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
public class OneCardInquiryResponse {
    private String globalTrxId ;
    private double denominationPrice;
    private double masaryCommission;
    private double merchantCommission;
    private double appliedFees;
    private double tax;
    private double transactionAmount;
    private double toBepaid;
    private long denominationId;
    private long rateplanId ;
    private long accountId;
    private long numberOfPoints;

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public double getDenominationPrice() {
        return denominationPrice;
    }

    public void setDenominationPrice(double denominationPrice) {
        this.denominationPrice = denominationPrice;
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

    public long getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(long denominationId) {
        this.denominationId = denominationId;
    }

    public long getRateplanId() {
        return rateplanId;
    }

    public void setRateplanId(long rateplanId) {
        this.rateplanId = rateplanId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(long numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }
    
    
}
