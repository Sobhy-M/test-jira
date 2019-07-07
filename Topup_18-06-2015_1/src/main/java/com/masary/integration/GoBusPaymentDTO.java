package com.masary.integration;

public class GoBusPaymentDTO {

	private String validationId;

	public String getValidationId() {
		return validationId;
	}

	public void setValidationId(String validationId) {
		this.validationId = validationId;
	}

	public GoBusPaymentDTO(String validationId) {
		this.validationId = validationId;
	}

}
