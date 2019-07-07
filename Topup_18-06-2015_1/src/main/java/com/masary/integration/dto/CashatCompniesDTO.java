package com.masary.integration.dto;

import java.util.List;

public class CashatCompniesDTO {

	private List<CashatCompniesInfo> companies;
	private String warnSTR;
	private long minTransactionAmount;
	private long maxTransactionAmount;

	public List<CashatCompniesInfo> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CashatCompniesInfo> companies) {
		this.companies = companies;
	}

	public String getWarnSTR() {
		return warnSTR;
	}

	public void setWarnSTR(String warnSTR) {
		this.warnSTR = warnSTR;
	}

	public long getMinTransactionAmount() {
		return minTransactionAmount;
	}

	public void setMinTransactionAmount(long minTransactionAmount) {
		this.minTransactionAmount = minTransactionAmount;
	}

	public long getMaxTransactionAmount() {
		return maxTransactionAmount;
	}

	public void setMaxTransactionAmount(long maxTransactionAmount) {
		this.maxTransactionAmount = maxTransactionAmount;
	}

}
