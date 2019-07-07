/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.Date;

/**
 *
 * @author Kerolous
 */
public class IsValidDTO {

    private int TRANSACTION_ID;
    private String TRANSACTION_DATE;
    private int CUSTOMER_PAYER;
    private int CUSTOMER_PAYED;
    private Double ORIGINAL_AMOUNT;
    private Double AMOUNT;
    private Double APPLIED_FEES;
    private int TXN_STATUS_ID;
    private int TXN_TYPE_ID;
    private int FROM_RATE_PLAN_ID;

    private Double FROM_COMM_AMOUNT;
    private Double TAX_AMOUNT;
    private Double FROM_SERVICE_BALANCE;
    private Double FROM_CUSTOMER_BALANCE;
    private int ORIGINAL_CUSTOMER_ID;
    private int TRANS_SERVICE;
    private int TO_RATE_PLAN_ID;
    private Double TO_COMM_AMOUNT;
    private Double TO_SERVICE_BALANCE;
    private Double TO_CUSTOMER_BALANCE;
    private int ACTIVE_PENDINGID;
    private int LEDGER_ID;
    private String MSISDN_ID;
    private int CANCEL_PENDING_T;
    private int returnedValue;
    private String returnedJsonString;

    public String getReturnedJsonString() {
        return returnedJsonString;
    }

    public void setReturnedJsonString(String returnedJsonString) {
        this.returnedJsonString = returnedJsonString;
    }
    public int getReturnedValue() {
        return returnedValue;
    }

    public void setReturnedValue(int returnedValue) {
        this.returnedValue = returnedValue;
    }

    public int getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public void setTRANSACTION_ID(int TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    public String getTRANSACTION_DATE() {
        return TRANSACTION_DATE;
    }

    public void setTRANSACTION_DATE(String TRANSACTION_DATE) {
        this.TRANSACTION_DATE = TRANSACTION_DATE;
    }

    public int getCUSTOMER_PAYER() {
        return CUSTOMER_PAYER;
    }

    public void setCUSTOMER_PAYER(int CUSTOMER_PAYER) {
        this.CUSTOMER_PAYER = CUSTOMER_PAYER;
    }

    public int getCUSTOMER_PAYED() {
        return CUSTOMER_PAYED;
    }

    public void setCUSTOMER_PAYED(int CUSTOMER_PAYED) {
        this.CUSTOMER_PAYED = CUSTOMER_PAYED;
    }

    public Double getORIGINAL_AMOUNT() {
        return ORIGINAL_AMOUNT;
    }

    public void setORIGINAL_AMOUNT(Double ORIGINAL_AMOUNT) {
        this.ORIGINAL_AMOUNT = ORIGINAL_AMOUNT;
    }

    public Double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(Double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public Double getAPPLIED_FEES() {
        return APPLIED_FEES;
    }

    public void setAPPLIED_FEES(Double APPLIED_FEES) {
        this.APPLIED_FEES = APPLIED_FEES;
    }

    public int getTXN_STATUS_ID() {
        return TXN_STATUS_ID;
    }

    public void setTXN_STATUS_ID(int TXN_STATUS_ID) {
        this.TXN_STATUS_ID = TXN_STATUS_ID;
    }

    public int getTXN_TYPE_ID() {
        return TXN_TYPE_ID;
    }

    public void setTXN_TYPE_ID(int TXN_TYPE_ID) {
        this.TXN_TYPE_ID = TXN_TYPE_ID;
    }

    public int getFROM_RATE_PLAN_ID() {
        return FROM_RATE_PLAN_ID;
    }

    public void setFROM_RATE_PLAN_ID(int FROM_RATE_PLAN_ID) {
        this.FROM_RATE_PLAN_ID = FROM_RATE_PLAN_ID;
    }

    public Double getFROM_COMM_AMOUNT() {
        return FROM_COMM_AMOUNT;
    }

    public void setFROM_COMM_AMOUNT(Double FROM_COMM_AMOUNT) {
        this.FROM_COMM_AMOUNT = FROM_COMM_AMOUNT;
    }

    public Double getTAX_AMOUNT() {
        return TAX_AMOUNT;
    }

    public void setTAX_AMOUNT(Double TAX_AMOUNT) {
        this.TAX_AMOUNT = TAX_AMOUNT;
    }

    public Double getFROM_SERVICE_BALANCE() {
        return FROM_SERVICE_BALANCE;
    }

    public void setFROM_SERVICE_BALANCE(Double FROM_SERVICE_BALANCE) {
        this.FROM_SERVICE_BALANCE = FROM_SERVICE_BALANCE;
    }

    public Double getFROM_CUSTOMER_BALANCE() {
        return FROM_CUSTOMER_BALANCE;
    }

    public void setFROM_CUSTOMER_BALANCE(Double FROM_CUSTOMER_BALANCE) {
        this.FROM_CUSTOMER_BALANCE = FROM_CUSTOMER_BALANCE;
    }

    public int getORIGINAL_CUSTOMER_ID() {
        return ORIGINAL_CUSTOMER_ID;
    }

    public void setORIGINAL_CUSTOMER_ID(int ORIGINAL_CUSTOMER_ID) {
        this.ORIGINAL_CUSTOMER_ID = ORIGINAL_CUSTOMER_ID;
    }

    public int getTRANS_SERVICE() {
        return TRANS_SERVICE;
    }

    public void setTRANS_SERVICE(int TRANS_SERVICE) {
        this.TRANS_SERVICE = TRANS_SERVICE;
    }

    public int getTO_RATE_PLAN_ID() {
        return TO_RATE_PLAN_ID;
    }

    public void setTO_RATE_PLAN_ID(int TO_RATE_PLAN_ID) {
        this.TO_RATE_PLAN_ID = TO_RATE_PLAN_ID;
    }

    public Double getTO_COMM_AMOUNT() {
        return TO_COMM_AMOUNT;
    }

    public void setTO_COMM_AMOUNT(Double TO_COMM_AMOUNT) {
        this.TO_COMM_AMOUNT = TO_COMM_AMOUNT;
    }

    public Double getTO_SERVICE_BALANCE() {
        return TO_SERVICE_BALANCE;
    }

    public void setTO_SERVICE_BALANCE(Double TO_SERVICE_BALANCE) {
        this.TO_SERVICE_BALANCE = TO_SERVICE_BALANCE;
    }

    public Double getTO_CUSTOMER_BALANCE() {
        return TO_CUSTOMER_BALANCE;
    }

    public void setTO_CUSTOMER_BALANCE(Double TO_CUSTOMER_BALANCE) {
        this.TO_CUSTOMER_BALANCE = TO_CUSTOMER_BALANCE;
    }

    public int getACTIVE_PENDINGID() {
        return ACTIVE_PENDINGID;
    }

    public void setACTIVE_PENDINGID(int ACTIVE_PENDINGID) {
        this.ACTIVE_PENDINGID = ACTIVE_PENDINGID;
    }

    public int getLEDGER_ID() {
        return LEDGER_ID;
    }

    public void setLEDGER_ID(int LEDGER_ID) {
        this.LEDGER_ID = LEDGER_ID;
    }

    public String getMSISDN_ID() {
        return MSISDN_ID;
    }

    public void setMSISDN_ID(String MSISDN_ID) {
        this.MSISDN_ID = MSISDN_ID;
    }

    public int getCANCEL_PENDING_T() {
        return CANCEL_PENDING_T;
    }

    public void setCANCEL_PENDING_T(int CANCEL_PENDING_T) {
        this.CANCEL_PENDING_T = CANCEL_PENDING_T;
    }

    @Override
    public String toString() {
        return "IsValidDTO{" + "TRANSACTION_ID=" + TRANSACTION_ID + ", TRANSACTION_DATE=" + TRANSACTION_DATE + ", CUSTOMER_PAYER=" + CUSTOMER_PAYER + ", CUSTOMER_PAYED=" + CUSTOMER_PAYED + ", ORIGINAL_AMOUNT=" + ORIGINAL_AMOUNT + ", AMOUNT=" + AMOUNT + ", APPLIED_FEES=" + APPLIED_FEES + ", TXN_STATUS_ID=" + TXN_STATUS_ID + ", TXN_TYPE_ID=" + TXN_TYPE_ID + ", FROM_RATE_PLAN_ID=" + FROM_RATE_PLAN_ID + ", FROM_COMM_AMOUNT=" + FROM_COMM_AMOUNT + ", TAX_AMOUNT=" + TAX_AMOUNT + ", FROM_SERVICE_BALANCE=" + FROM_SERVICE_BALANCE + ", FROM_CUSTOMER_BALANCE=" + FROM_CUSTOMER_BALANCE + ", ORIGINAL_CUSTOMER_ID=" + ORIGINAL_CUSTOMER_ID + ", TRANS_SERVICE=" + TRANS_SERVICE + ", TO_RATE_PLAN_ID=" + TO_RATE_PLAN_ID + ", TO_COMM_AMOUNT=" + TO_COMM_AMOUNT + ", TO_SERVICE_BALANCE=" + TO_SERVICE_BALANCE + ", TO_CUSTOMER_BALANCE=" + TO_CUSTOMER_BALANCE + ", ACTIVE_PENDINGID=" + ACTIVE_PENDINGID + ", LEDGER_ID=" + LEDGER_ID + ", MSISDN_ID=" + MSISDN_ID + ", CANCEL_PENDING_T=" + CANCEL_PENDING_T + ", returnedValue=" + returnedValue + '}';
    }

}
