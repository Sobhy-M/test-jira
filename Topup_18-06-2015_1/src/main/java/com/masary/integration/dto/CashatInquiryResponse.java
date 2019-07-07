package com.masary.integration.dto;

import java.math.BigDecimal;

public class CashatInquiryResponse {

	private String validationId;
	private String globalTrxId;
	private String companyName;
	private String repName;
	private String companyNameAr;
	private String repNationalId;
	private long accountId;
	private long companyID;
	private long repId;
	private long amount;
	private long ratePlanId;
	private double appliedFees;
	private double merchantCommission;
	private double tax;
	private String toBepaid;
	private double masaryCommission;
	private double appliedFeesAndTax;
	private String transactionAmount;
	private double paymentAmount;

	public String getValidationId() {
		return validationId;
	}

	public void setValidationId(String validationId) {
		this.validationId = validationId;
	}

	public String getGlobalTrxId() {
		return globalTrxId;
	}

	public void setGlobalTrxId(String globalTrxId) {
		this.globalTrxId = globalTrxId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public String getCompanyNameAr() {
		return companyNameAr;
	}

	public void setCompanyNameAr(String companyNameAr) {
		this.companyNameAr = companyNameAr;
	}

	public String getRepNationalId() {
		return repNationalId;
	}

	public void setRepNationalId(String repNationalId) {
		this.repNationalId = repNationalId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}

	public long getRepId() {
		return repId;
	}

	public void setRepId(long repId) {
		this.repId = repId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
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

	public String getToBepaid() {

		BigDecimal result = new BigDecimal(this.toBepaid);
		return result.toPlainString();
	}

	public void setToBepaid(String toBepaid) {
		this.toBepaid = toBepaid;
	}

	public double getMasaryCommission() {
		return masaryCommission;
	}

	public void setMasaryCommission(double masaryCommission) {
		this.masaryCommission = masaryCommission;
	}

	public double getAppliedFeesAndTax() {
		return appliedFeesAndTax;
	}

	public void setAppliedFeesAndTax(double appliedFeesAndTax) {
		this.appliedFeesAndTax = appliedFeesAndTax;
	}

	public String getTransactionAmount() {
		BigDecimal result = new BigDecimal(this.transactionAmount);
		return result.toPlainString();
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

}
