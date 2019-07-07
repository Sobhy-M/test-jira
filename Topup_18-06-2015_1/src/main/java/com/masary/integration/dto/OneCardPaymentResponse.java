/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

import java.util.Date;

/**
 *
 * @author hammad
 */
public class OneCardPaymentResponse {
    private Long cardId;
    private Long oneCardAccountId;
    private Double merchantCommission;
    private Long accountId;
    private Double appliedFees ;
    private Double tax;
    private Double transactionAmount ; //amount to be deducted from merchant
    private Double toBePaid;// amount to be paid by end user
    private long insertDate;
    private long updateDate;
    private String globalTrxId;
    private Long ledgerTrxId;
    private String transactionStatus;
    private String ledgerStatus;
    private String deviceType;
    private String operatorResponseCode;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getOneCardAccountId() {
        return oneCardAccountId;
    }

    public void setOneCardAccountId(Long oneCardAccountId) {
        this.oneCardAccountId = oneCardAccountId;
    }

    public Double getMerchantCommission() {
        return merchantCommission;
    }

    public void setMerchantCommission(Double merchantCommission) {
        this.merchantCommission = merchantCommission;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(Double appliedFees) {
        this.appliedFees = appliedFees;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Double getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(Double toBePaid) {
        this.toBePaid = toBePaid;
    }

    public long getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(long insertDate) {
        this.insertDate = insertDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public Long getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(Long ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getLedgerStatus() {
        return ledgerStatus;
    }

    public void setLedgerStatus(String ledgerStatus) {
        this.ledgerStatus = ledgerStatus;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOperatorResponseCode() {
        return operatorResponseCode;
    }

    public void setOperatorResponseCode(String operatorResponseCode) {
        this.operatorResponseCode = operatorResponseCode;
    }
    
    
}
