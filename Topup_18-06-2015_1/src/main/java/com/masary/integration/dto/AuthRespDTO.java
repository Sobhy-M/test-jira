/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author hammad
 */
public class AuthRespDTO {
    private String token;
    private String idleTimeInMinutes;
    private int accountId;
    private String accountName;
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdleTimeInMinutes() {
        return idleTimeInMinutes;
    }

    public void setIdleTimeInMinutes(String idleTimeInMinutes) {
        this.idleTimeInMinutes = idleTimeInMinutes;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }   
    
}
