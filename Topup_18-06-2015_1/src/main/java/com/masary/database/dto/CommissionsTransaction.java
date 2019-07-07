/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author user
 */
public class CommissionsTransaction {

    private double masaryCommission;
    private double merchantCommission;
    private double merchantFees;

    /**
     * @return the masaryCommission
     */
    public double getMasaryCommission() {
        return masaryCommission;
    }

    /**
     * @param masaryCommission the masaryCommission to set
     */
    public void setMasaryCommission(double masaryCommission) {
        this.masaryCommission = masaryCommission;
    }

    /**
     * @return the merchantCommission
     */
    public double getMerchantCommission() {
        return merchantCommission;
    }

    /**
     * @param merchantCommission the merchantCommission to set
     */
    public void setMerchantCommission(double merchantCommission) {
        this.merchantCommission = merchantCommission;
    }

    /**
     * @return the merchantFees
     */
    public double getMerchantFees() {
        return merchantFees;
    }

    /**
     * @param merchantFees the merchantFees to set
     */
    public void setMerchantFees(double merchantFees) {
        this.merchantFees = merchantFees;
    }
    
}
