package com.masary.integration.dto;

public class AcceptPaymentResponse {
	
	private long insertDate;
	private long updateDate;
	private double appliedFees;
	private double merchantCommission;
	private double tax;
	private double toBepaid;
	private String requestStatus;
	private long accountId;
	private String deviceType;
	private String globalTrxId;
	private double transactionAmount;
	private long ledgerTrxId;
	private String ledgerStatus;
	private long ratePlanId;
	public long getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
	}
	private String billerID;
	private String billerName;
	private String clientName;
	private double dueAmount;
	private String mobileNo;
	private String paymentReferenceId;
	private String inquiryReferenceId;
	
	public long getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(long insertDate) {
		this.insertDate = insertDate;
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
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
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
	public long getLedgerTrxId() {
		return ledgerTrxId;
	}
	public void setLedgerTrxId(long ledgerTrxId) {
		this.ledgerTrxId = ledgerTrxId;
	}
	public String getLedgerStatus() {
		return ledgerStatus;
	}
	public void setLedgerStatus(String ledgerStatus) {
		this.ledgerStatus = ledgerStatus;
	}
	public long getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(long ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getBillerID() {
		return billerID;
	}
	public void setBillerID(String billerID) {
		this.billerID = billerID;
	}
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public double getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPaymentReferenceId() {
		return paymentReferenceId;
	}
	public void setPaymentReferenceId(String paymentReferenceId) {
		this.paymentReferenceId = paymentReferenceId;
	}
	public String getInquiryReferenceId() {
		return inquiryReferenceId;
	}
	public void setInquiryReferenceId(String inquiryReferenceId) {
		this.inquiryReferenceId = inquiryReferenceId;
	}
	
	
}
