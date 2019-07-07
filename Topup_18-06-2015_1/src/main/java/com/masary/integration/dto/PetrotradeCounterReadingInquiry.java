package com.masary.integration.dto;

public class PetrotradeCounterReadingInquiry {
	
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
	public long getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getProviderCategory() {
		return providerCategory;
	}
	public void setProviderCategory(String providerCategory) {
		this.providerCategory = providerCategory;
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
	public String getSubscriberName() {
		return subscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	public Integer getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Integer closeDate) {
		this.closeDate = closeDate;
	}
	public Double getLastReading() {
		return lastReading;
	}
	public void setLastReading(Double lastReading) {
		this.lastReading = lastReading;
	}
	private double masaryCommission;
	private double merchantCommission;
	private long fromAccountId;
	private String serviceName;
	private String providerCategory;
	private long ratePlanId;
	private double appliedFees ;
	private double tax;
	private double transactionAmount ;
	private double toBepaid;
	private double serviceAmount;
	private String subscriberName;
	private int closeDate;
	private double lastReading;

}
