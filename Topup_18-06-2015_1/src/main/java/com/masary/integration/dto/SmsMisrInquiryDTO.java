/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Ahmed khaled
 */
public class SmsMisrInquiryDTO {

    private Double appliedFees;
    private Double merchantCommission;
    private Double masaryCommisssion;
    private Double tax;
    private Double toBepaid;
    private String globalTrxId;
    private Double transactionAmount;
    private Long ratePlanId;
    private Long accountId;

    private String confirmationCode;
    private String providerTransactionId;

    public Double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(Double appliedFees) {
        this.appliedFees = appliedFees;
    }

    public Double getMerchantCommission() {
        return merchantCommission;
    }

    public void setMerchantCommission(Double merchantCommission) {
        this.merchantCommission = merchantCommission;
    }

    public Double getMasaryCommisssion() {
        return masaryCommisssion;
    }

    public void setMasaryCommisssion(Double masaryCommisssion) {
        this.masaryCommisssion = masaryCommisssion;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getToBepaid() {
        return toBepaid;
    }

    public void setToBepaid(Double toBepaid) {
        this.toBepaid = toBepaid;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public String getProviderTransactionId() {
        return providerTransactionId;
    }

    public void setProviderTransactionId(String providerTransactionId) {
        this.providerTransactionId = providerTransactionId;
    }
    
    

}
