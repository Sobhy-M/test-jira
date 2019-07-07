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
public class WepostPaidPaymentResponse {

    private int functionId;
    private long ledgerTrxId;
    private long ratePlanId;
    private long accountId;
    private double outStandingAmount;
    private double appliedFees;
    private double MerchantCommission;
    private double tax;
    private double toBepaid;
    public String getAdvStatment() {
		return advStatment;
	}

	public void setAdvStatment(String advStatment) {
		this.advStatment = advStatment;
	}

	private double transactionAmount;
    public double getTotalOutStandingFee() {
		return totalOutStandingFee;
	}

	public void setTotalOutStandingFee(double totalOutStandingFee) {
		this.totalOutStandingFee = totalOutStandingFee;
	}

	private String providerRequestId;
    private String providerStatusCode;
    private String providerStatusMessage;
    private String providerReferenceNumber;
    private String billCycleID;
    private String billCycleBeginTime;
    private double totalOutStandingFee;

    public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	private String billCycleEndTime;
    private String dueDate;
    private String CurrencyID;
    private String msisdn;
    private String requestStatus;
    private String ledgerStatus;
    private String deviceType;
    private String globalTrxId;
    private String targetNumber;
    private String advStatment;
    private String insertDate;

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public long getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(long ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
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

    public double getOutStandingAmount() {
        return outStandingAmount;
    }

    public void setOutStandingAmount(double outStandingAmount) {
        this.outStandingAmount = outStandingAmount;
    }

    public double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(double appliedFees) {
        this.appliedFees = appliedFees;
    }

    public double getMerchantCommission() {
        return MerchantCommission;
    }

    public void setMerchantCommission(double MerchantCommission) {
        this.MerchantCommission = MerchantCommission;
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

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getProviderRequestId() {
        return providerRequestId;
    }

    public void setProviderRequestId(String providerRequestId) {
        this.providerRequestId = providerRequestId;
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

    public String getProviderReferenceNumber() {
        return providerReferenceNumber;
    }

    public void setProviderReferenceNumber(String providerReferenceNumber) {
        this.providerReferenceNumber = providerReferenceNumber;
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

    public String getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(String targetNumber) {
        this.targetNumber = targetNumber;
    }
    
    
}
