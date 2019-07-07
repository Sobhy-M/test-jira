/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class COMMISSION {

    int FEES_TYPE ;
    int MERCHANT_SHARE_TYPE;
    int OPERATION_TYPE;
    int MAXIMUM ;
    int MINIMUM ;
    double FEES;
    double MERCHANT_SHARE;

    public double getFEES() {
        return FEES;
    }

    public void setFEES(double FEES) {
        this.FEES = FEES;
    }

    public double getMERCHANT_SHARE() {
        return MERCHANT_SHARE;
    }

    public void setMERCHANT_SHARE(double MERCHANT_SHARE) {
        this.MERCHANT_SHARE = MERCHANT_SHARE;
    }
    

    public int getFEES_TYPE() {
        return FEES_TYPE;
    }

    public void setFEES_TYPE(int FEES_TYPE) {
        this.FEES_TYPE = FEES_TYPE;
    }

    public int getMERCHANT_SHARE_TYPE() {
        return MERCHANT_SHARE_TYPE;
    }

    public void setMERCHANT_SHARE_TYPE(int MERCHANT_SHARE_TYPE) {
        this.MERCHANT_SHARE_TYPE = MERCHANT_SHARE_TYPE;
    }

    public int getOPERATION_TYPE() {
        return OPERATION_TYPE;
    }

    public void setOPERATION_TYPE(int OPERATION_TYPE) {
        this.OPERATION_TYPE = OPERATION_TYPE;
    }

    public int getMAXIMUM() {
        return MAXIMUM;
    }

    public void setMAXIMUM(int MAXIMUM) {
        this.MAXIMUM = MAXIMUM;
    }

    public int getMINIMUM() {
        return MINIMUM;
    }

    public void setMINIMUM(int MINIMUM) {
        this.MINIMUM = MINIMUM;
    }
    
}
