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
public class O2groupPaymentDto {

    private String o2GroupAccountId;
    private Long denominationId;

    public String getO2GroupAccountId() {
        return o2GroupAccountId;
    }

    public void setO2GroupAccountId(String o2GroupAccountId) {
        this.o2GroupAccountId = o2GroupAccountId;
    }

    public Long getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(Long denominationId) {
        this.denominationId = denominationId;
    }
}
