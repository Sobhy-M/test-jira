package com.masary.integration.dto;

public class UpdateProfileResponse {
	String globalTrxId;
	int statusCode;
	public String getGlobalTrxId() {
		return globalTrxId;
	}
	public void setGlobalTrxId(String globalTrxId) {
		this.globalTrxId = globalTrxId;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "UpdateProfileResponse [globalTrxId=" + globalTrxId + ", statusCode=" + statusCode + "]";
	}

	
}
