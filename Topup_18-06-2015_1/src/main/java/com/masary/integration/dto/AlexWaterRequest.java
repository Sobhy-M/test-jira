/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Barakat Mostafa
 */
public class AlexWaterRequest {
    private String validationId;
    private String electricityNumber;

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

    public String getElectricityNumber() {
        return electricityNumber;
    }

    public void setElectricityNumber(String electricityNumber) {
        this.electricityNumber = electricityNumber;
    }
    
}
