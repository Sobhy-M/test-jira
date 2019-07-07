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
public class WepostPaidInquiryResponse {

    private String validationId;
    private String CurrencyID;
    private String msisdn;
    private String globalTrxId;
    private String requestId;
    private String providerStatusCode;
    private String providerStatusMessage;
    private String billCycleID;
    private String billCycleBeginTime;
    private String billCycleEndTime;
    private String dueDate;
    private double outStandingAmount;
    private double appliedFees;
    private double merchantCommission;
    private double masaryCommission;
    private double tax;
    private double toBepaid;
    private double transactionAmount;
    private long ratePlanId;
    private long accountId;
    private long expirationDate;
    private int functionId;
    private double  totalOutStandingFee;
    

    public double getTotalOutStandingFee() {
		return totalOutStandingFee;
	}

	public void setTotalOutStandingFee(double totalOutStandingFee) {
		this.totalOutStandingFee = totalOutStandingFee;
	}

	public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getProviderStatusCode() {
        return providerStatusCode;
    }

    public void setProviderStatusCode(String providerStatusCode) {
        this.providerStatusCode = providerStatusCode;
    }

    public String getProviderStatusMessage() {
        return providerStatusMessage;
    }

    public void setProviderStatusMessage(String providerStatusMessage) {
        this.providerStatusMessage = providerStatusMessage;
    }

    public String getBillCycleID() {
        return billCycleID;
    }

    public void setBillCycleID(String billCycleID) {
        this.billCycleID = billCycleID;
    }

    public String getBillCycleBeginTime() {
        return billCycleBeginTime;
    }

    public void setBillCycleBeginTime(String billCycleBeginTime) {
        this.billCycleBeginTime = billCycleBeginTime;
    }

    public String getBillCycleEndTime() {
        return billCycleEndTime;
    }

    public void setBillCycleEndTime(String billCycleEndTime) {
        this.billCycleEndTime = billCycleEndTime;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getOutStandingAmount() {
        return outStandingAmount;
    }

    public void setOutStandingAmount(double outStandingAmount) {
        this.outStandingAmount = outStandingAmount;
    }

    public String getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(String CurrencyID) {
        this.CurrencyID = CurrencyID;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
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

    public double getMasaryCommission() {
        return masaryCommission;
    }

    public void setMasaryCommission(double masaryCommission) {
        this.masaryCommission = masaryCommission;
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

    public long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

}
