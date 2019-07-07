/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author hammad
 */
public class OneCardPaymentRequest {
    private long denominationId;
    private long oneCardAccountId;

    public long getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(long denominationId) {
        this.denominationId = denominationId;
    }

    public long getOneCardAccountId() {
        return oneCardAccountId;
    }

    public void setOneCardAccountId(long oneCardAccountId) {
        this.oneCardAccountId = oneCardAccountId;
    }
}
