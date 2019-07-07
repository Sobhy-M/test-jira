package com.masary.integration.dto;

public class SECIRequestDTO {

	private String mobileNumber;
	private double amount;

	public SECIRequestDTO(String mobileNumber, double amount) {
		this.mobileNumber = mobileNumber;
		this.amount = amount;
	}

	public SECIRequestDTO() {

	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
