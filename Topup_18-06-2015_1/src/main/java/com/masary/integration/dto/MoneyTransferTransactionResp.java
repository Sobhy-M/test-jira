/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

import java.util.Date;

/**
 *
 * @author AYA
 */
public class MoneyTransferTransactionResp {

 	private Long requestId;
	private Long initiatorAccount;
	private Long targetAccount;
	private Long ratePlanId;
	private Double transactionAmount;
	private Double appliedFees;
	private Double merchantCommission;
	private Double masaryCommission;
	private Double tax;
	private Double toBepaid;
	private Date insertDate;
	private Date updateDate;
	private Long ledgerId;
	private String globalTrxId;
	private String requestStatus;
	private String deviceType;
	private String ledgerStatus;
	private String hierarchyLevel;
	private String onExceptionList;
	private String delegate;
	private String transferType;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getInitiatorAccount() {
        return initiatorAccount;
    }

    public void setInitiatorAccount(Long initiatorAccount) {
        this.initiatorAccount = initiatorAccount;
    }

    public Long getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Long targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(Double appliedFees) {
        this.appliedFees = appliedFees;
    }

    public Double getMerchantCommission() {
        return merchantCommission;
    }

    public void setMerchantCommission(Double merchantCommission) {
        this.merchantCommission = merchantCommission;
    }

    public Double getMasaryCommission() {
        return masaryCommission;
    }

    public void setMasaryCommission(Double masaryCommission) {
        this.masaryCommission = masaryCommission;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getToBepaid() {
        return toBepaid;
    }

    public void setToBepaid(Double toBepaid) {
        this.toBepaid = toBepaid;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Long ledgerId) {
        this.ledgerId = ledgerId;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLedgerStatus() {
        return ledgerStatus;
    }

    public void setLedgerStatus(String ledgerStatus) {
        this.ledgerStatus = ledgerStatus;
    }

    public String getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public String getOnExceptionList() {
        return onExceptionList;
    }

    public void setOnExceptionList(String onExceptionList) {
        this.onExceptionList = onExceptionList;
    }

    public String getDelegate() {
        return delegate;
    }

    public void setDelegate(String delegate) {
        this.delegate = delegate;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
        
}
