package com.masary.integration.dto;

public class CommonTopupRepresentationDTO {

    private String msisdn;
    private String globalTrxId;
    private String providerTrxId;
    private long transactionTime;
    private double appliedFees;
    private double tax;
    private double toBePaid;
    private String adsWord;
    private double transactionAmount;
    private String transactionDate;

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public String getProviderTrxId() {
        return providerTrxId;
    }

    public void setProviderTrxId(String providerTrxId) {
        this.providerTrxId = providerTrxId;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
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

    public double getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(double toBePaid) {
        this.toBePaid = toBePaid;
    }

    public String getAdsWord() {
        return adsWord;
    }

    public void setAdsWord(String adsWord) {
        this.adsWord = adsWord;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

}
