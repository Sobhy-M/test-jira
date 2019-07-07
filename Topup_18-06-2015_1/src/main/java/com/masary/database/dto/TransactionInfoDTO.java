/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author omnya
 */
public class TransactionInfoDTO {
 private  long transaction_ID;
 private double amount;

    public long getTransaction_ID() {
        return transaction_ID;
    }

    public void setTransaction_ID(long transaction_ID) {
        this.transaction_ID = transaction_ID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
 
 
}
