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
public class VodafoneDslDenomination {

    private long denominationId;
    private int denominationAmount;
    private double providerAmount;
    private String denominationName;

    public String getDenominationName() {
        return denominationName;
    }

    public void setDenominationName(String denominationName) {
        this.denominationName = denominationName;
    }

    public long getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(long denominationId) {
        this.denominationId = denominationId;
    }

    public int getDenominationAmount() {
        return denominationAmount;
    }

    public void setDenominationAmount(int denominationAmount) {
        this.denominationAmount = denominationAmount;
    }

    public double getProviderAmount() {
        return providerAmount;
    }

    public void setProviderAmount(double providerAmount) {
        this.providerAmount = providerAmount;
    }

}
