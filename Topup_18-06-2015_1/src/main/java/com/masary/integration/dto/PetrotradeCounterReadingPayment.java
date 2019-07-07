package com.masary.integration.dto;

public class PetrotradeCounterReadingPayment {
	private long transactionId;
 	private String insertDate;
	private String updateDate;
 	private double appliedFees;
	private double merchantCommission;
	private double tax;
	private double toBepaid;
	private String requestStatus;
	private String ledgerStatus;
 	private String deviceType;
	private String globalTrxId;
	private double transactionAmount;
	private long ledgerTrxId;
	private String subscriberName;
 	private String subscriberNumber;
	private String lastReading;
	private String areaName;
	private String closeDate;
	private double currentReading ;
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
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
	public long getLedgerTrxId() {
		return ledgerTrxId;
	}
	public void setLedgerTrxId(long ledgerTrxId) {
		this.ledgerTrxId = ledgerTrxId;
	}
	public String getSubscriberName() {
		return subscriberName;
	}
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}
	public String getLastReading() {
		return lastReading;
	}
	public void setLastReading(String lastReading) {
		this.lastReading = lastReading;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public double getCurrentReading() {
		return currentReading;
	}
	public void setCurrentReading(double currentReading) {
		this.currentReading = currentReading;
	}
	
	
}
