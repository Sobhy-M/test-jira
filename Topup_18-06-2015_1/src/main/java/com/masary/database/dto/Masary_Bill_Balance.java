/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author KEMO
 */
public class Masary_Bill_Balance {
    double balance;
    String StatusCode;
    String code;
    String StatusDesc;

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String StatusDesc) {
        this.StatusDesc = StatusDesc;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String StatusCode) {
        this.StatusCode = StatusCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
