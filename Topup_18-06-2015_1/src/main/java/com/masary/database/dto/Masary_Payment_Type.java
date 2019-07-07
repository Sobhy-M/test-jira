/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Masary_Payment_Type {
String PAYMENT_ID;
String PAYMENT_ID_TYPE;
String CREATED_DATE;

    public String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public void setCREATED_DATE(String CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }

    public String getPAYMENT_ID() {
        return PAYMENT_ID;
    }

    public void setPAYMENT_ID(String PAYMENT_ID) {
        this.PAYMENT_ID = PAYMENT_ID;
    }

    public String getPAYMENT_ID_TYPE() {
        return PAYMENT_ID_TYPE;
    }

    public void setPAYMENT_ID_TYPE(String PAYMENT_ID_TYPE) {
        this.PAYMENT_ID_TYPE = PAYMENT_ID_TYPE;
    }

}
