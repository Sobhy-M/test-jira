package com.masary.integration.dto;

public class CashatInquiryRequest {
	
	private String repNationalId;
	private long amount;
	private long companyID;
	
	public long getCompanyID() {
		return companyID;
	}
	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}
	public String getRepNationalId() {
		return repNationalId;
	}
	public void setRepNationalId(String repNationalId) {
		this.repNationalId = repNationalId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long d) {
		this.amount = d;
	}

}
