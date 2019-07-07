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
public class TransactionsMenuResponse {
   
String globalTrxId;
    String pendingId;
    double amount;
    int serviceId;
    String payed;
    int payer;
    String status;
    long trxTime;
    long insertionTime;
    int ledgerTrxId;

    @Override
    public String toString() {
        return "TransactionsMenuRequest{" + "globalTrxId=" + globalTrxId + ", pendingId=" + pendingId + ", amount=" + amount + ", serviceId=" + serviceId + ", payed=" + payed + ", payer=" + payer + ", status=" + status + ", trxTime=" + trxTime + ", insertionTime=" + insertionTime + ", ledgerTrxId=" + ledgerTrxId + '}';
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public String getPendingId() {
        return pendingId;
    }

    public void setPendingId(String pendingId) {
        this.pendingId = pendingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getPayed() {
        return payed;
    }

    public void setPayed(String payed) {
        this.payed = payed;
    }

    public int getPayer() {
        return payer;
    }

    public void setPayer(int payer) {
        this.payer = payer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTrxTime() {
        return trxTime;
    }

    public void setTrxTime(long trxTime) {
        this.trxTime = trxTime;
    }

    public long getInsertionTime() {
        return insertionTime;
    }

    public void setInsertionTime(long insertionTime) {
        this.insertionTime = insertionTime;
    }

    public int getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(int ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
    }

}
