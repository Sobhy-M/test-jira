package com.masary.integration.dto;

public class AbuElRishResponseDTO {

	private Long paymentId;
	private String mobileNumber;

    private Long payemntTypeId;
    private long insertDate;

    private long updateDate;

    private String globalTrxId;
    private Long ledgerTrxId;

    private Integer paymentAmount;
    private String requestStatus;
    private String ledgerStatus;
    private String deviceType;

    private Long accountId;

    private Double appliedFees;
    private Double MerchantCommission;
    private Double tax;
    private Double toBepaid;
    private Double transactionAmount;
    private Long ratePlanId;
    private String targetNumber;


    public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Long getPayemntTypeId() {
		return payemntTypeId;
	}
	public void setPayemntTypeId(Long payemntTypeId) {
		this.payemntTypeId = payemntTypeId;
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
	public String getGlobalTrxId() {
		return globalTrxId;
	}
	public void setGlobalTrxId(String globalTrxId) {
		this.globalTrxId = globalTrxId;
	}
	public Long getLedgerTrxId() {
		return ledgerTrxId;
	}
	public void setLedgerTrxId(Long ledgerTrxId) {
		this.ledgerTrxId = ledgerTrxId;
	}
	public Integer getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
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
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Double getAppliedFees() {
		return appliedFees;
	}
	public void setAppliedFees(Double appliedFees) {
		this.appliedFees = appliedFees;
	}
	public Double getMerchantCommission() {
		return MerchantCommission;
	}
	public void setMerchantCommission(Double merchantCommission) {
		MerchantCommission = merchantCommission;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getToBepaid() {
		return toBepaid;
	}
	public void setToBepaid(Double toBepaid) {
		this.toBepaid = toBepaid;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Long getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(Long ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getTargetNumber() {
		return targetNumber;
	}
	public void setTargetNumber(String targetNumber) {
		this.targetNumber = targetNumber;
	}
	
}
