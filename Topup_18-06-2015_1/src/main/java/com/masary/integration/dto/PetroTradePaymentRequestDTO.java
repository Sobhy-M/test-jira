/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author AYA
 */
public class PetroTradePaymentRequestDTO {

    private String subscriberNumber;
    private Double amount;
    private Double currentReading;

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

	public Double getCurrentReading() {
		return currentReading;
	}

	public void setCurrentReading(Double currentReading) {
		this.currentReading = currentReading;
	}
}
