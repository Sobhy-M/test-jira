package com.masary.integration.dto;

public class GoBusPaymentData {

	private String validationId;
	private String token;
	private String ip;
	private String lang;

	public GoBusPaymentData(String validationId, String token, String ip, String lang) {
		this.validationId = validationId;
		this.token = token;
		this.ip = ip;
		this.lang = lang;
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

	public String getValidationId() {
		return validationId;
	}

	public void setValidationId(String validationId) {
		this.validationId = validationId;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
