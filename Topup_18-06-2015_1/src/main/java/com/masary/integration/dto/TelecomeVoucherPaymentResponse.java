/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Ahmed Khaled
 */
public class TelecomeVoucherPaymentResponse {

   private String voucherSerial;
	private String validationDate;
	private String voucherPin;
	private String voucherWord;

	
	private String insertDate;
	private String updateDate;
	private double serviceAmount;
	private double appliedFees;
	private double merchantCommission;
	private double tax;
	private double toBepaid;
	private String requestStatus;
	private String ledgerStatus;
	private Long accountId;
	private String deviceType;
	private String globalTrxId;
	private double transactionAmount;
	private Long ledgerTrxId;
	private Long ratePlanId;

    public String getVoucherSerial() {
        return voucherSerial;
    }

    public void setVoucherSerial(String voucherSerial) {
        this.voucherSerial = voucherSerial;
    }

    public String getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(String validationDate) {
        this.validationDate = validationDate;
    }


    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(double serviceAmount) {
        this.serviceAmount = serviceAmount;
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

    public String getLedgerStatus() {
        return ledgerStatus;
    }

    public void setLedgerStatus(String ledgerStatus) {
        this.ledgerStatus = ledgerStatus;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
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

    public Long getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(Long ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
    }

    public String getVoucherPin() {
        return voucherPin;
    }

    public void setVoucherPin(String voucherPin) {
        this.voucherPin = voucherPin;
    }

    public String getVoucherWord() {
        return voucherWord;
    }

    public void setVoucherWord(String voucherWord) {
        this.voucherWord = voucherWord;
    }

    public Long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    
}
