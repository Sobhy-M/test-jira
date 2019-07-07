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
public class TopupResponse {

    private String requestStatus;
    private String ledgerTransactionId;
    private String ledgerTransactionStatus;
    private String requestId;
    private long updateTime;

    private String insertTime;
    private Double merchantCommission;

    private Double appliedFees;

    private Long requestTransactionId;

    private String operatorTransactionId;

    private String targetNumber;
    private String eventName;
    private String entityName;

    private Double amount;

    private Long serviceId;

    private Double tax;

    private String globalTrxId;

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getLedgerTransactionId() {
        return ledgerTransactionId;
    }

    public void setLedgerTransactionId(String ledgerTransactionId) {
        this.ledgerTransactionId = ledgerTransactionId;
    }

    public String getLedgerTransactionStatus() {
        return ledgerTransactionStatus;
    }

    public void setLedgerTransactionStatus(String ledgerTransactionStatus) {
        this.ledgerTransactionStatus = ledgerTransactionStatus;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

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

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getMerchantCommission() {
        return merchantCommission;
    }

    public void setMerchantCommission(Double merchantCommission) {
        this.merchantCommission = merchantCommission;
    }

    public Double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(Double appliedFees) {
        this.appliedFees = appliedFees;
    }

    public Long getRequestTransactionId() {
        return requestTransactionId;
    }

    public void setRequestTransactionId(Long requestTransactionId) {
        this.requestTransactionId = requestTransactionId;
    }

    public String getOperatorTransactionId() {
        return operatorTransactionId;
    }

    public void setOperatorTransactionId(String operatorTransactionId) {
        this.operatorTransactionId = operatorTransactionId;
    }

    public String getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    @Override
    public String toString() {
        return "OrangeTopupResponse{" + "requestStatus=" + requestStatus + ", ledgerTransactionId=" + ledgerTransactionId + ", ledgerTransactionStatus=" + ledgerTransactionStatus + ", requestId=" + requestId + ", updateTime=" + updateTime + ", insertTime=" + insertTime + ", merchantCommission=" + merchantCommission + ", appliedFees=" + appliedFees + ", requestTransactionId=" + requestTransactionId + ", operatorTransactionId=" + operatorTransactionId + ", targetNumber=" + targetNumber + ", eventName=" + eventName + ", entityName=" + entityName + ", amount=" + amount + ", serviceId=" + serviceId + ", tax=" + tax + ", globalTrxId=" + globalTrxId + '}';
    }

}
