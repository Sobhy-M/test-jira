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
public class EtisalatGiftsInquiryRequest {

    private String voucherCode;
    private String mobileNumber;
    private double voucherAmount;

    public EtisalatGiftsInquiryRequest(String voucherCode, String mobileNumber, String voucherAmount) {
        this.voucherCode = voucherCode;

        this.mobileNumber = mobileNumber;
        this.voucherAmount = Double.valueOf(voucherAmount);
        
    }

    public EtisalatGiftsInquiryRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public double getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(double voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

}
