/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Kerolous
 */
public class EtisalatClubResponse {
    
    private Boolean clubOptIn;
    private String amount;
    private String message;
    private String messageEn;
    private String statusCode;

    public EtisalatClubResponse(Boolean clubOptIn, String amount, String message, String statusCode) {
        this.clubOptIn = clubOptIn;
        this.amount = amount;
        this.message = message;
        this.statusCode = statusCode;
    }

    public EtisalatClubResponse() {
    }
    

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    
    public Boolean getClubOptIn() {
        return clubOptIn;
    }

    public void setClubOptIn(Boolean clubOptIn) {
        this.clubOptIn = clubOptIn;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the messageEn
     */
    public String getMessageEn() {
        return messageEn;
    }

    /**
     * @param messageEn the messageEn to set
     */
    public void setMessageEn(String messageEn) {
        this.messageEn = messageEn;
    }
    
    
    
    
}
