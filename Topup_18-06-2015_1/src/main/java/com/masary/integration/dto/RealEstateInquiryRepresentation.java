/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author omar.abdellah
 */
public class RealEstateInquiryRepresentation {

    private String validationId;

    private String customerName;
    private double serviceAmount;
    private String customerAddress;
    private double appliedFees;
    private double merchantCommission;
    private double tax;
    private double toBepaid;
    private double firstBillFeeAmount;
    private double transactionAmount;
    private String realEstateType;
    private Long accountId;

    private String accountNumber;
    private double masaryCommission;

    private long ratePlanId;
    private String billNumber;
    private String ePayBillRecID;
    private String sequence;

    private String curCode;
    private String billingAcct;

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

    public double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
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

    public double getFirstBillFeeAmount() {
        return firstBillFeeAmount;
    }

    public void setFirstBillFeeAmount(double firstBillFeeAmount) {
        this.firstBillFeeAmount = firstBillFeeAmount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getRealEstateType() {
        return realEstateType;
    }

    public void setRealEstateType(String realEstateType) {
        this.realEstateType = realEstateType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getMasaryCommission() {
        return masaryCommission;
    }

    public void setMasaryCommission(double masaryCommission) {
        this.masaryCommission = masaryCommission;
    }

    public long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getePayBillRecID() {
        return ePayBillRecID;
    }

    public void setePayBillRecID(String ePayBillRecID) {
        this.ePayBillRecID = ePayBillRecID;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public String getBillingAcct() {
        return billingAcct;
    }

    public void setBillingAcct(String billingAcct) {
        this.billingAcct = billingAcct;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
