package com.masary.database.dto;

public class AddNewPinRequestDTO {

	private String pinCode;
	private String pinCodeConfirmation;
	private String token;
	private String ip;
	private String lang;

	public AddNewPinRequestDTO(String pinCode, String pinCodeConfirmation, String token, String ip, String lang) {
		this.pinCode = pinCode;
		this.pinCodeConfirmation = pinCodeConfirmation;
		this.token = token;
		this.ip = ip;
		this.lang = lang;
	}

	public String getPinCodeConfirmation() {
		return pinCodeConfirmation;
	}

	public void setPinCodeConfirmation(String pinCodeConfirmation) {
		this.pinCodeConfirmation = pinCodeConfirmation;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
