package com.masary.integration.dto;

public class ChangePinRequestDTO {

	private String binCode;
	private String newStatus;
	private String ip;
	private String token;
	private String lang;

	public ChangePinRequestDTO(String binCode, String newStatus, String ip, String token, String lang) {
		this.binCode = binCode;
		this.newStatus = newStatus;
		this.ip = ip;
		this.token = token;
		this.lang = lang;
	}

	public String getBinCode() {
		return binCode;
	}

	public void setBinCode(String binCode) {
		this.binCode = binCode;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
