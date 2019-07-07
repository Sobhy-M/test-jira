/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author KEMO
 */
public class GenericVoucherData {
    
    private String voucherPin;
    private String voucherSn;
    private Double amount;
    private Integer isOffer;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public Integer getIsOffer() {
        return isOffer;
    }

    public void setIsOffer(Integer isOffer) {
        this.isOffer = isOffer;
    }

    public String getVoucherPin() {
        return voucherPin;
    }

    public void setVoucherPin(String voucherPin) {
        this.voucherPin = voucherPin;
    }

    public String getVoucherSn() {
        return voucherSn;
    }

    public void setVoucherSn(String voucherSn) {
        this.voucherSn = voucherSn;
    }
    
    
}
