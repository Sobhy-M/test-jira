package com.masary.integration.dto;

public class TEData_Inquiry_Response {
	
	 private String validationId;
	 private String subscriberNumber;
	 private String providerStatusCode;
	 private String customerName;
	 private String denominationId;
	 private String retrievalReferenceNumber;
	 private String adslNumber;
	 private double amount;
	 private double appliedFees;
	 private double merchantCommission;
	 private double masaryCommission;
	 private double tax;
	 private double toBepaid;
	 private String globalTrxId;
	 private double transactionAmount;
	 private long ratePlanId;
	 private long accountId; 
	 private long expirationDate;
	 
	 public String getValidationId() {
		return validationId;
	}
	public String getDenominationId() {
		return denominationId;
	}
	public void setDenominationId(String denominationId) {
		this.denominationId = denominationId;
	}
	public String getAdslNumber() {
		return adslNumber;
	}
	public void setAdslNumber(String adslNumber) {
		this.adslNumber = adslNumber;
	}
	public void setValidationId(String validationId) {
		this.validationId = validationId;
	}
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}
	public String getProviderStatusCode() {
		return providerStatusCode;
	}
	public void setProviderStatusCode(String providerStatusCode) {
		this.providerStatusCode = providerStatusCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getRetrievalReferenceNumber() {
		return retrievalReferenceNumber;
	}
	public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
		this.retrievalReferenceNumber = retrievalReferenceNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
