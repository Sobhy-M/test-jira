/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.List;

/**
 *
 * @author KEMO
 */
public class Masary_Bill_Biller {

    int BILLER_ID;
    String BILLER_NAME;
    String CREATION_DATE;
    String ACTIVE;
    String BILLER_NAME_AR;
    List<Masary_Bill_Type> masary_bill_types;

    public List<Masary_Bill_Type> getMasary_bill_types() {
        return masary_bill_types;
    }

    public void setMasary_bill_types(List<Masary_Bill_Type> masary_bill_types) {
        this.masary_bill_types = masary_bill_types;
    }

    public String getACTIVE() {
        return ACTIVE;
    }

    public void setACTIVE(String ACTIVE) {
        this.ACTIVE = ACTIVE;
    }

    public int getBILLER_ID() {
        return BILLER_ID;
    }

    public void setBILLER_ID(int BILLER_ID) {
        this.BILLER_ID = BILLER_ID;
    }

    public String getBILLER_NAME() {
        return BILLER_NAME;
    }

    public void setBILLER_NAME(String BILLER_NAME) {
        this.BILLER_NAME = BILLER_NAME;
    }

    public String getBILLER_NAME_AR() {
        return BILLER_NAME_AR;
    }

    public void setBILLER_NAME_AR(String BILLER_NAME_AR) {
        this.BILLER_NAME_AR = BILLER_NAME_AR;
    }

    public String getCREATION_DATE() {
        return CREATION_DATE;
    }

    public void setCREATION_DATE(String CREATION_DATE) {
        this.CREATION_DATE = CREATION_DATE;
    }
}
