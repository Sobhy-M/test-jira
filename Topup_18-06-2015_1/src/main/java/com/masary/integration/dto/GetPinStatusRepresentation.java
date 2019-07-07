package com.masary.integration.dto;

public class GetPinStatusRepresentation {

	private long walletId;
	private String pinStatus;

	public long getWalletId() {
		return walletId;
	}

	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}

	public String getPinStatus() {
		return pinStatus;
	}

	public void setPinStatus(String pinStatus) {
		this.pinStatus = pinStatus;
	}

}
