/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Abdelsabor
 */
public class O2groupDenominationResponse {

    private long denominationID;

    private String denominationName;

    private Integer denominationValue;

    public long getDenominationID() {
        return denominationID;
    }

    public void setDenominationID(long denominationID) {
        this.denominationID = denominationID;
    }

    public String getDenominationName() {
        return denominationName;
    }

    public void setDenominationName(String denominationName) {
        this.denominationName = denominationName;
    }

    public Integer getDenominationValue() {
        return denominationValue;
    }

    public void setDenominationValue(Integer denominationValue) {
        this.denominationValue = denominationValue;
    }
}
