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
public class VodafoneTopupDslRequest {

    private String msisdn;
    private long denominationId;
    private String externalTRXId;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public long getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(long denominationId) {
        this.denominationId = denominationId;
    }

    public String getExternalTRXId() {
        return externalTRXId;
    }

    public void setExternalTRXId(String externalTRXId) {
        this.externalTRXId = externalTRXId;
    }
    
    

}
