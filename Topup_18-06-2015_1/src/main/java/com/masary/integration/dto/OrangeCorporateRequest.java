/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author user
 */
public class OrangeCorporateRequest {

    private String validationId1;
    private String validationId;
    private String msisdn;
    private String accountNumber;
    private String accountNumberOriginal;
    private String msisdnOriginal;
    private double paidAmount;
    private boolean partialPayment;

    public String getAccountNumberOriginal() {
        return accountNumberOriginal;
    }

    public void setAccountNumberOriginal(String accountNumberOriginal) {
        this.accountNumberOriginal = accountNumberOriginal;
    }

    public String getMsisdnOriginal() {
        return msisdnOriginal;
    }

    public void setMsisdnOriginal(String msisdnOriginal) {
        this.msisdnOriginal = msisdnOriginal;
    }

    public boolean isPartialPayment() {
        return partialPayment;
    }

    public void setPartialPayment(boolean partialPayment) {
        this.partialPayment = partialPayment;
    }

    public String getValidationId1() {
        return validationId1;
    }

    public void setValidationId1(String validationId1) {
        this.validationId1 = validationId1;
    }

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
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

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Override
    public String toString() {
        return "OrangeCorporateRequest{" + "validationId1=" + validationId1 + ", validationId=" + validationId + ", msisdn=" + msisdn + ", accountNumber=" + accountNumber + ", paidAmount=" + paidAmount + ", partialPayment=" + partialPayment + '}';
    }

}
