package com.masary.integration.dto;

import java.util.List;

public class ChangePointsDTO {
	private List<Long> points;
    private long walletPoints;
	public List<Long> getPoints() {
		return points;
	}
	public void setPoints(List<Long> points) {
		this.points = points;
	}
	public long getWalletPoints() {
		return walletPoints;
	}
	public void setWalletPoints(long walletPoints) {
		this.walletPoints = walletPoints;
	}



}
