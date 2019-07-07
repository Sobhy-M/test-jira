package com.masary.integration.dto;

public class GoBusPaymentRepresentation {

	private long transactionId;
 	private long insertDate;
	private long updateDate;
 	private double appliedFees;
	private double merchantCommission;	
	private double tax;
	private double toBepaid;			
	private String requestStatus;
	private String ledgerStatus;
 	private String deviceType;
	private String globalTrxId;			
	private double transactionAmount;	 
	private Long ledgerTrxId;
	
	private String jurneyType;
	private String customerName;
	private String jurneyAmount;
	private String goDetails;
	private String backDetails;
	private String goSeatsNumber;
	private String goDate;
	private String backDate;
	private String backSeatsNumber;
	private String providerTransactionNumber;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(long insertDate) {
		this.insertDate = insertDate;
	}

	public long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
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

	public String getJurneyType() {
		return jurneyType;
	}

	public void setJurneyType(String jurneyType) {
		this.jurneyType = jurneyType;
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

}
