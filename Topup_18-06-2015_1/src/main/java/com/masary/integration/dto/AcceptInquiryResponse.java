package com.masary.integration.dto;

public class AcceptInquiryResponse {

	private String validationId;
	private long expirationDate;
	private double appliedFees;
	private double merchantCommission;
	private double masaryCommisssion;
	private double tax;
	private double toBepaid;
	private String globalTrxId;
	private double transactionAmount;
	private long ratePlanId;
	private long accountId;
	private String billerID;
	private String billerName;
	private String billerCode;
	private String clientName;
	private double dueAmount;
	private String mobileNo;
	private String paymentReferenceId;
	private String inquiryReferenceId;
	private String orderName;
	
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getValidationId() {
		return validationId;
	}
	public void setValidationId(String validationId) {
		this.validationId = validationId;
	}
	public long getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
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
	public double getMasaryCommisssion() {
		return masaryCommisssion;
	}
	public void setMasaryCommisssion(double masaryCommisssion) {
		this.masaryCommisssion = masaryCommisssion;
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
	public String getBillerCode() {
		return billerCode;
	}
	public void setBillerCode(String billerCode) {
		this.billerCode = billerCode;
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
