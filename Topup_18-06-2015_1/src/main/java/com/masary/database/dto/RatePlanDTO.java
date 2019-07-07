/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author omnya
 */
public class RatePlanDTO {

    private double masary_fixedAmount;
    private double masary_commission;
    private double fixedAmount;
    private double commission;
    private double applied_fees;
    private double applied_fees_per;
    private int dailyCeiling;
    private int weeklyCeiling;
    private int monthlyCeiling;
    private int serviceID;
    private double taxAmount;
    private double deductAmount;

    public double getMasary_fixedAmount() {
        return masary_fixedAmount;
    }

    public void setMasary_fixedAmount(double masary_fixedAmount) {
        this.masary_fixedAmount = masary_fixedAmount;
    }

    public double getMasary_commission() {
        return masary_commission;
    }

    public void setMasary_commission(double masary_commission) {
        this.masary_commission = masary_commission;
    }

    public double getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(double fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getDailyCeiling() {
        return dailyCeiling;
    }

    public void setDailyCeiling(int dailyCeiling) {
        this.dailyCeiling = dailyCeiling;
    }

    public int getWeeklyCeiling() {
        return weeklyCeiling;
    }

    public void setWeeklyCeiling(int weeklyCeiling) {
        this.weeklyCeiling = weeklyCeiling;
    }

    public int getMonthlyCeiling() {
        return monthlyCeiling;
    }

    public void setMonthlyCeiling(int monthlyCeiling) {
        this.monthlyCeiling = monthlyCeiling;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public double getApplied_fees() {
        return applied_fees;
    }

    public void setApplied_fees(double applied_fees) {
        this.applied_fees = applied_fees;
    }

    public double getApplied_fees_per() {
        return applied_fees_per;
    }

    public void setApplied_fees_per(double applied_fees_per) {
        this.applied_fees_per = applied_fees_per;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(double deductAmount) {
        this.deductAmount = deductAmount;
    }
    
}
