/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author KEMO
 */
public class Masary_Balance_Sheet_Detailes {

    String ACTION_NATURE;
    String ACTION_TYPE_CODE;
    double AMOUNT;

    public String getACTION_NATURE() {
        return ACTION_NATURE;
    }

    public void setACTION_NATURE(String ACTION_NATURE) {
        this.ACTION_NATURE = ACTION_NATURE;
    }

    public String getACTION_TYPE_CODE() {
        return ACTION_TYPE_CODE;
    }

    public void setACTION_TYPE_CODE(String ACTION_TYPE_CODE) {
        this.ACTION_TYPE_CODE = ACTION_TYPE_CODE;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }
    
}
