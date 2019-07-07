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
public class PaymentRequest {
   private double amount;
  private String mobileNumber;
 private double donationTypeId;
    public PaymentRequest(String amount, String mobileNumber,String donationTypeId) {
        this.amount = Double.parseDouble(amount);
        this.mobileNumber = mobileNumber;
          this.donationTypeId = Double.parseDouble(donationTypeId);
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

    public double getDonationTypeId() {
        return donationTypeId;
    }

    public void setDonationTypeId(double donationTypeId) {
        this.donationTypeId = donationTypeId;
    }
  
  
}
