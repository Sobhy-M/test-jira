/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Ahmed Khaled
 */
public class WepostPaidRequest {

    private String msisdn;
    private String validationId;
    
    public WepostPaidRequest() {
		}
    public String getMsisdn() {
        return msisdn;
    }

    public WepostPaidRequest(String msisdn, String validationId) {
		super();
		this.msisdn = msisdn;
		this.validationId = validationId;
	}

	public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

}
