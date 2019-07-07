/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Masary_Bill_Amounts {
String BILL_REF_NUMBER;
String BILL_SUMM_AMT_CODE;
double CUR_AMOUNT;
String CUR_CODE;

    public String getBILL_REF_NUMBER() {
        return BILL_REF_NUMBER;
    }

    public void setBILL_REF_NUMBER(String BILL_REF_NUMBER) {
        this.BILL_REF_NUMBER = BILL_REF_NUMBER;
    }

    public String getBILL_SUMM_AMT_CODE() {
        return BILL_SUMM_AMT_CODE;
    }

    public void setBILL_SUMM_AMT_CODE(String BILL_SUMM_AMT_CODE) {
        this.BILL_SUMM_AMT_CODE = BILL_SUMM_AMT_CODE;
    }

    public double getCUR_AMOUNT() {
        return CUR_AMOUNT;
    }

    public void setCUR_AMOUNT(double CUR_AMOUNT) {
        this.CUR_AMOUNT = CUR_AMOUNT;
    }

    public String getCUR_CODE() {
        return CUR_CODE;
    }

    public void setCUR_CODE(String CUR_CODE) {
        this.CUR_CODE = CUR_CODE;
    }

}
