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
public class LedgerBalanceRespDto {
    
    private String eventName;
    private String entityName;
    private int accountId;
    private double accountBalance;
    private double accountBalanceOnhold;
    private long balanceLastUpdate;
    private String accountName;
    private int accountBaseCurrencyId;
    private int accountCategoryId;
    private String accountStatus;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getAccountBalanceOnhold() {
        return accountBalanceOnhold;
    }

    public void setAccountBalanceOnhold(double accountBalanceOnhold) {
        this.accountBalanceOnhold = accountBalanceOnhold;
    }

    public long getBalanceLastUpdate() {
        return balanceLastUpdate;
    }

    public void setBalanceLastUpdate(long balanceLastUpdate) {
        this.balanceLastUpdate = balanceLastUpdate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountBaseCurrencyId() {
        return accountBaseCurrencyId;
    }

    public void setAccountBaseCurrencyId(int accountBaseCurrencyId) {
        this.accountBaseCurrencyId = accountBaseCurrencyId;
    }

    public int getAccountCategoryId() {
        return accountCategoryId;
    }

    public void setAccountCategoryId(int accountCategoryId) {
        this.accountCategoryId = accountCategoryId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    
    
}
