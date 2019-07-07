package com.masary.integration.dto;

public class KafrElShiekhWaterPaymentRequest {

    private String validationId;

    public KafrElShiekhWaterPaymentRequest(String validationId) {
        this.validationId = validationId;
    }

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

}
