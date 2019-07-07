/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author omar.abdellah
 */
public class TamweelPartialInquiryRequest {

    private String validationId;
    private double amount;

    public TamweelPartialInquiryRequest(String validationId, double amount) {
        this.validationId = validationId;

        this.amount = amount;
    }

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
