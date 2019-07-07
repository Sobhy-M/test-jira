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
public class GooBackRequest {
    private String mobileNumber;
    private double amount;

    public String getMsisdn() {
        return mobileNumber;
    }

    public void setMsisdn(String msisdn) {
        this.mobileNumber = msisdn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
