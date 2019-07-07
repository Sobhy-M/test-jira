package com.masary.integration.dto;

public class TEData_Payment_Response {
	private String providerStatusCode;
	private String customerName;
	private String subscriberNumber;
	private String retrievalReferenceNumber;
	private long insertDate;
	private long updateDate;
	private int invoiceNumber;
	private int receiptNumber;
	private double packageAmount;
	private double amount;
	private double appliedFees;
	private double MerchantCommission;
	private double tax;
	private double toBepaid;
	private String requestStatus;
	private String packageId;
	private String packageQuota;
	public String getPackageQuota() {
		return packageQuota;
	}
	public void setPackageQuota(String packageQuota) {
		this.packageQuota = packageQuota;
	}
	private String ledgerStatus;
	private String adslNumber;
	private String entityName;
	private String eventName;
	private long accountId;
	private String deviceType;
	private String globalTrxId;
	private double transactionAmount;
	private long ledgerTrxId;
	private long ratePlanId;
	private String targetNumber;
	private String packageName;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public int getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(int receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public double getPackageAmount() {
		return packageAmount;
	}
	public void setPackageAmount(double packageAmount) {
		this.packageAmount = packageAmount;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getAdslNumber() {
		return adslNumber;
	}
	public void setAdslNumber(String adslNumber) {
		this.adslNumber = adslNumber;
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
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}
	public String getRetrievalReferenceNumber() {
		return retrievalReferenceNumber;
	}
	public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
		this.retrievalReferenceNumber = retrievalReferenceNumber;
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
		return MerchantCommission;
	}
	public void setMerchantCommission(double merchantCommission) {
		MerchantCommission = merchantCommission;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getToBepaid() {
		toBepaid = Math.floor(toBepaid*100)/100;
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
	public long getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(long ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getTargetNumber() {
		return targetNumber;
	}
	public void setTargetNumber(String targetNumber) {
		this.targetNumber = targetNumber;
	}
	

}
