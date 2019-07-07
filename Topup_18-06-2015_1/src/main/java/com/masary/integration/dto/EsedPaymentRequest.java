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
public class EsedPaymentRequest {

    private String nationalIdOrCodeNumber;

    private String globalTrxId;

    public String getNationalIdOrCodeNumber() {
        return nationalIdOrCodeNumber;
    }

    public void setNationalIdOrCodeNumber(String nationalIdOrCodeNumber) {
        this.nationalIdOrCodeNumber = nationalIdOrCodeNumber;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

}
