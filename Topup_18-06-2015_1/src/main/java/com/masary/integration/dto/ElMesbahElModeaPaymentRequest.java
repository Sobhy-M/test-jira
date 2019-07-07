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
public class ElMesbahElModeaPaymentRequest {
     private double amount;
  private String mobileNumber;
 private double governorateId;

    public ElMesbahElModeaPaymentRequest(String amount, String mobileNumber, String governorateId) {
        this.amount = Double.parseDouble(amount);
        this.mobileNumber = mobileNumber;
        this.governorateId = Double.parseDouble(governorateId);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public double getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(double governorateId) {
        this.governorateId = governorateId;
    }
 
 
}
