/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author amira
 */
public class ValuPaymentRequest {
    
     private String billReference;
     private double paidAmount;

     
     public ValuPaymentRequest( String billReference,double paidAmount){
         this.billReference=billReference;
         this.paidAmount=paidAmount;
     }
    public String getBillReference() {
        return billReference;
    }

    public void setBillReference(String billReference) {
        this.billReference = billReference;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

      
}
