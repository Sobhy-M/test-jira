package com.masary.integration.dto;

public class AcceptInquiryRequest {
	
	String billReference;
	String biller;
	String mobileNumber;
	
	public String getBillReference() {
		return billReference;
	}
	public void setBillReference(String billReference) {
		this.billReference = billReference;
	}
	public String getBiller() {
		return biller;
	}
	public void setBiller(String biller) {
		this.biller = biller;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
