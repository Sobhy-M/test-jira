package com.masary.integration.dto;

public class AddNewPinDTO {

	private String pinCode;
	private String pinCodeConfirmation;

	public AddNewPinDTO(String pinCode, String pinCodeConfirmation) {
		this.pinCode = pinCode;
		this.pinCodeConfirmation = pinCodeConfirmation;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPinCodeConfirmation() {
		return pinCodeConfirmation;
	}

	public void setPinCodeConfirmation(String pinCodeConfirmation) {
		this.pinCodeConfirmation = pinCodeConfirmation;
	}

}
