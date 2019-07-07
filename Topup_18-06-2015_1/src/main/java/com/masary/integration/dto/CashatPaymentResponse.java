package com.masary.integration.dto;

public class CashatPaymentResponse {
	
	private long paymentId;
	private long ratePlanId;
	private long insertDate;
	private long updateDate;
	private long ledgerTrxId;
	private long accountId;
	private double paymentAmount;
	private double appliedFees;
	private double MerchantCommission;
	private double tax;
	private double toBepaid;
	private double transactionAmount;
	private String companyName;
	private String companyNameAR;
	private String repName;
	private String repNationalId;
	private String requestStatus;
	private String ledgerStatus;
	private String deviceType;
	private String globalTrxId;
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public long getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(long ratePlanId) {
		this.ratePlanId = ratePlanId;
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
	public long getLedgerTrxId() {
		return ledgerTrxId;
	}
	public void setLedgerTrxId(long ledgerTrxId) {
		this.ledgerTrxId = ledgerTrxId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyNameAR() {
		return companyNameAR;
	}
	public void setCompanyNameAR(String companyNameAR) {
		this.companyNameAR = companyNameAR;
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getRepNationalId() {
		return repNationalId;
	}
	public void setRepNationalId(String repNationalId) {
		this.repNationalId = repNationalId;
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
	

}
