package com.masary.integration.dto;

public class SECIResponseDTO {

	private int accountId;
	private int amount;
	private int appliedFees;
	private String deviceType;
	private String entityName;
	private String eventName;
	private String globalTrxId;
	private long ledgerTrxId;
	private int masaryCommission;
	private int merchantCommission;
	private String mobileNumber;
	private int requestId;
	private String requestStatus;
	private int tax;
	private int toBepaid;
	private double transactionAmount;
	private String transactionTime;
	private String targetNumber;
	private int paymentId;
	private String insertDate;
	private long updateDate;
	private double paymentAmount;
	private double ratePlanId;

	public String getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(String targetNumber) {
		this.targetNumber = targetNumber;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public   long getUpdateDate() {
		return updateDate;
	}

	public  void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public double getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(double ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAppliedFees() {
		return appliedFees;
	}

	public void setAppliedFees(int appliedFees) {
		this.appliedFees = appliedFees;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getGlobalTrxId() {
		return globalTrxId;
	}

	public void setGlobalTrxId(String globalTrxId) {
		this.globalTrxId = globalTrxId;
	}

	public long getLedgerTrxId() {
		return ledgerTrxId;
	}

	public void setLedgerTrxId(long ledgerTrxId) {
		this.ledgerTrxId = ledgerTrxId;
	}

	public int getMasaryCommission() {
		return masaryCommission;
	}

	public void setMasaryCommission(int masaryCommission) {
		this.masaryCommission = masaryCommission;
	}

	public int getMerchantCommission() {
		return merchantCommission;
	}

	public void setMerchantCommission(int merchantCommission) {
		this.merchantCommission = merchantCommission;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getToBepaid() {
		return toBepaid;
	}

	public void setToBepaid(int toBepaid) {
		this.toBepaid = toBepaid;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
}
