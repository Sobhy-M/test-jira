/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Mustafa
 */
public class ElectricityPaymentRequest {
    private String accountNumber;
	private String electricityCompanyID;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getElectricityCompanyID() {
        return electricityCompanyID;
    }

    public void setElectricityCompanyID(String electricityCompanyID) {
        this.electricityCompanyID = electricityCompanyID;
    }
	
        
}
