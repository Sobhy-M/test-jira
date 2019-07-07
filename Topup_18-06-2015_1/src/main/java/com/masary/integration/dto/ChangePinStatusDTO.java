package com.masary.integration.dto;

public class ChangePinStatusDTO {

	private String pinCode;
	private String newStatus;

	public ChangePinStatusDTO(String pinCode, String newStatus) {
		this.pinCode = pinCode;
		this.newStatus = newStatus;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

}
