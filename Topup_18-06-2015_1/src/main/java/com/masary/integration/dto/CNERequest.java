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
public class CNERequest {
    String mobileNumber;
	String subscriberNumber; 

    public CNERequest(String mobileNumber, String subscriberNumber) {
        this.mobileNumber = mobileNumber;
        this.subscriberNumber = subscriberNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
        
        

   

    public String getSubscriberNumber() {
            return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }
        
        
}
