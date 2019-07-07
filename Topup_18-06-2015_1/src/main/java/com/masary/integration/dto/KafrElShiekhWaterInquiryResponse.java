package com.masary.integration.dto;

public class KafrElShiekhWaterInquiryResponse {

    private String validationId;
    private String subscriberName;
    private String subscriptionNumber;
    private String mobileNumber;
    private int billsNumber;
    private double totalBillsAmount;
    private double oldestBillsAmount;
    private double masaryCommission;
    private double merchantCommission;
    private long ratePlanId;
    private double appliedFees;
    private double tax;
    private double transactionAmount;
    private double toBepaid;
    private double serviceAmount;
    private String inquiryId;
    private long accountId;
    private String oldestBillDate;
    private String newestBillDate;
    private String billDate;

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public void setSubscriptionNumber(String subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getBillsNumber() {
        return billsNumber;
    }

    public void setBillsNumber(int billsNumber) {
        this.billsNumber = billsNumber;
    }

    public double getTotalBillsAmount() {
        return totalBillsAmount;
    }

    public void setTotalBillsAmount(double totalBillsAmount) {
        this.totalBillsAmount = totalBillsAmount;
    }

    public double getOldestBillsAmount() {
        return oldestBillsAmount;
    }

    public void setOldestBillsAmount(double oldestBillsAmount) {
        this.oldestBillsAmount = oldestBillsAmount;
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

    public String getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getOldestBillDate() {
        return oldestBillDate;
    }

    public void setOldestBillDate(String oldestBillDate) {
        this.oldestBillDate = oldestBillDate;
    }

    public String getNewestBillDate() {
        return newestBillDate;
    }

    public void setNewestBillDate(String newestBillDate) {
        this.newestBillDate = newestBillDate;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

}
