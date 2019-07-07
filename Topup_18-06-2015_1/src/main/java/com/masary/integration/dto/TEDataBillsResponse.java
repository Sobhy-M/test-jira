/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Tasneem
 */
public class TEDataBillsResponse {

    private String statusCode;
    private String landLine;
    private String statusMessage;
    private String customerName;
    private String customerNumber;
    private Double totalDueForRenewal;
    private String newExpiryDateAfterRenewal;
    private String receiptNumber;
    private long requestId;
    private long insertDate;
    private long updateDate;
    private double appliedFees;
    private double merchantCommission;
    private double tax;
    private double toBepaid;
    private double masaryCommission;
    private String requestStatus;
    private Long accountId;
    private String deviceType;
    private String ledgerStatus;
    private Long ledgerTrxId;
    private Long ratePlanId;
    private double transactionAmount;
    private String globalTrxId;
    private String providerStatusCode;
    private String providerStatusMessage;
    private String retrialStatusCode;
    private String retrialStatusMessage;
    private String ReturnDesc;
    private String bssProviderRequestId;
    private String resultCode;
    private String advertisingStatement;
    private String systemResource;
    private String targetNumber;
    private int retrial;
    private long functionId;
    private double amount;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getLandLine() {
        return landLine;
    }

    public void setLandLine(String landLine) {
        this.landLine = landLine;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Double getTotalDueForRenewal() {
        return totalDueForRenewal;
    }

    public void setTotalDueForRenewal(Double totalDueForRenewal) {
        this.totalDueForRenewal = totalDueForRenewal;
    }

    public String getNewExpiryDateAfterRenewal() {
        return newExpiryDateAfterRenewal;
    }

    public void setNewExpiryDateAfterRenewal(String newExpiryDateAfterRenewal) {
        this.newExpiryDateAfterRenewal = newExpiryDateAfterRenewal;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
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

    public double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(double appliedFees) {
        this.appliedFees = appliedFees;
    }

    public double getMerchantCommission() {
        return merchantCommission;
    }

    public void setMerchantCommission(double merchantCommission) {
        this.merchantCommission = merchantCommission;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getToBepaid() {
        return toBepaid;
    }

    public void setToBepaid(double toBepaid) {
        this.toBepaid = toBepaid;
    }

    public double getMasaryCommission() {
        return masaryCommission;
    }

    public void setMasaryCommission(double masaryCommission) {
        this.masaryCommission = masaryCommission;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    public Long getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(Long ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
    }

    public Long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public String getProviderStatusCode() {
        return providerStatusCode;
    }

    public void setProviderStatusCode(String providerStatusCode) {
        this.providerStatusCode = providerStatusCode;
    }

    public String getProviderStatusMessage() {
        return providerStatusMessage;
    }

    public void setProviderStatusMessage(String providerStatusMessage) {
        this.providerStatusMessage = providerStatusMessage;
    }

    public String getRetrialStatusCode() {
        return retrialStatusCode;
    }

    public void setRetrialStatusCode(String retrialStatusCode) {
        this.retrialStatusCode = retrialStatusCode;
    }

    public String getRetrialStatusMessage() {
        return retrialStatusMessage;
    }

    public void setRetrialStatusMessage(String retrialStatusMessage) {
        this.retrialStatusMessage = retrialStatusMessage;
    }

    public String getReturnDesc() {
        return ReturnDesc;
    }

    public void setReturnDesc(String ReturnDesc) {
        this.ReturnDesc = ReturnDesc;
    }

    public String getBssProviderRequestId() {
        return bssProviderRequestId;
    }

    public void setBssProviderRequestId(String bssProviderRequestId) {
        this.bssProviderRequestId = bssProviderRequestId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getAdvertisingStatement() {
        return advertisingStatement;
    }

    public void setAdvertisingStatement(String advertisingStatement) {
        this.advertisingStatement = advertisingStatement;
    }

    public String getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    public int getRetrial() {
        return retrial;
    }

    public void setRetrial(int retrial) {
        this.retrial = retrial;
    }

    public long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(long functionId) {
        this.functionId = functionId;
    }

    public String getSystemResource() {
        return systemResource;
    }

    public void setSystemResource(String systemResource) {
        this.systemResource = systemResource;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TeDataBillsPaymentRepresentation{" + "statusCode=" + statusCode + ", landLine=" + landLine + ", statusMessage=" + statusMessage + ", customerName=" + customerName + ", customerNumber=" + customerNumber + ", totalDueForRenewal=" + totalDueForRenewal + ", newExpiryDateAfterRenewal=" + newExpiryDateAfterRenewal + ", receiptNumber=" + receiptNumber + ", requestId=" + requestId + ", insertDate=" + insertDate + ", updateDate=" + updateDate + ", appliedFees=" + appliedFees + ", merchantCommission=" + merchantCommission + ", tax=" + tax + ", toBepaid=" + toBepaid + ", masaryCommission=" + masaryCommission + ", requestStatus=" + requestStatus + ", accountId=" + accountId + ", deviceType=" + deviceType + ", ledgerStatus=" + ledgerStatus + ", ledgerTrxId=" + ledgerTrxId + ", ratePlanId=" + ratePlanId + ", transactionAmount=" + transactionAmount + ", globalTrxId=" + globalTrxId + ", providerStatusCode=" + providerStatusCode + ", providerStatusMessage=" + providerStatusMessage + ", retrialStatusCode=" + retrialStatusCode + ", retrialStatusMessage=" + retrialStatusMessage + ", ReturnDesc=" + ReturnDesc + ", bssProviderRequestId=" + bssProviderRequestId + ", resultCode=" + resultCode + ", advertisingStatement=" + advertisingStatement + ", systemResource=" + systemResource + ", targetNumber=" + targetNumber + ", retrial=" + retrial + ", functionId=" + functionId + ", amount=" + amount + "}";
    }

    
}
