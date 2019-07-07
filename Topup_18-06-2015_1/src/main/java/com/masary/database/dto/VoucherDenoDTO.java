/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Aya
 */
public class VoucherDenoDTO {
    private String denomenation;
    private int available;

    public String getDenomenation() {
        return denomenation;
    }

    public void setDenomenation(String denomenation) {
        this.denomenation = denomenation;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
    
}
