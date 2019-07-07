/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author user
 */
public class ZaidElKhierRequest {

    private double amount;
    private double appliedFees;
    private double commission;
    private double deductedAmount;
    private double toBePaid;
    private String mobileNumber;
    private String subCategoryName;
    private int subServiceID;
    private boolean confirmed;

    public int getSubServiceID() {
        return subServiceID;
    }

    public void setSubServiceID(int subServiceID) {
        this.subServiceID = subServiceID;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAppliedFees() {
        return appliedFees;
    }

    public void setAppliedFees(double appliedFees) {
        this.appliedFees = appliedFees;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getDeductedAmount() {
        return deductedAmount;
    }

    public void setDeductedAmount(double deductedAmount) {
        this.deductedAmount = deductedAmount;
    }

    public double getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(double toBePaid) {
        this.toBePaid = toBePaid;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
