/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.List;

/**
 *
 * @author Ahmed Saeed
 */
public class GenericElectricityBill {
    
    
    List<ELectricityBillInquiry> acquiredBills;

    public List<ELectricityBillInquiry> getAcquiredBills() {
        return acquiredBills;
    }

    public void setAcquiredBills(List<ELectricityBillInquiry> acquiredBills) {
        this.acquiredBills = acquiredBills;
    }
    
    String statusCode;
    
    String statusMessage;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    
    
    
    
}
