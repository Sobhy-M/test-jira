package com.masary.integration.dto;

public class GoBusInquiryRepresentation {

	private String validationId;
	private long expirationDate;
	private double appliedFees;
	private double merchantCommission;
	private double tax;
	private double toBepaid;
	private String globalTrxId;
	private double transactionAmount;
	private long ratePlanId;
	private String customerName;
	private String jurneyAmount;
	private String goDetails;
	private String backDetails;
	private String goSeatsNumber;
	private String goDate;
	private String backDate;
	private String backSeatsNumber;
	private String providerTransactionNumber;

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getJurneyAmount() {
		return jurneyAmount;
	}

	public void setJurneyAmount(String jurneyAmount) {
		this.jurneyAmount = jurneyAmount;
	}

	public String getGoDetails() {
		return goDetails;
	}

	public void setGoDetails(String goDetails) {
		this.goDetails = goDetails;
	}

	public String getBackDetails() {
		return backDetails;
	}

	public void setBackDetails(String backDetails) {
		this.backDetails = backDetails;
	}

	public String getGoSeatsNumber() {
		this.goSeatsNumber = this.goSeatsNumber.replace(",", " / ");
		return goSeatsNumber;
	}

	public void setGoSeatsNumber(String goSeatsNumber) {
		this.goSeatsNumber = goSeatsNumber;
	}

	public String getGoDate() {
		return goDate;
	}

	public void setGoDate(String goDate) {
		this.goDate = goDate;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public String getBackSeatsNumber() {
		this.backSeatsNumber = this.backSeatsNumber.replace(",", " / ");
		return backSeatsNumber;
	}

	public void setBackSeatsNumber(String backSeatsNumber) {
		this.backSeatsNumber = backSeatsNumber;
	}

	public String getProviderTransactionNumber() {
		return providerTransactionNumber;
	}

	public void setProviderTransactionNumber(String providerTransactionNumber) {
		this.providerTransactionNumber = providerTransactionNumber;
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

}
