package com.masary.integration.dto;

import java.io.Serializable;

public class AbuElRishRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mobileNumber; 
	private double amount;
	private long paymentTypeId;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public double getAmount() {
		return amount;
	}

	public AbuElRishRequestDTO(String mobileNumber, double amount, long paymentTypeId) {
		this.mobileNumber = mobileNumber;
		this.amount = amount;
		this.paymentTypeId = paymentTypeId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

}
