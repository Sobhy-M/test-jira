/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Abdelsabor
 */
public class EduCentersDTO {

    private String LANG;
    private String CHANNEL;
    private int CUSTOMER_ID;
    private int SERVICE_ID;
    private String CODE_ID;
    private String AMOUNT;
    private String SERVICE_TYPE;

    public String getLANG() {
        return LANG;
    }

    public void setLANG(String LANG) {
        this.LANG = LANG;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(int CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public int getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(int SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public String getCODE_ID() {
        return CODE_ID;
    }

    public void setCODE_ID(String CODE_ID) {
        this.CODE_ID = CODE_ID;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getSERVICE_TYPE() {
        return SERVICE_TYPE;
    }

    public void setSERVICE_TYPE(String SERVICE_TYPE) {
        this.SERVICE_TYPE = SERVICE_TYPE;
    }

    @Override
    public String toString() {
        return "EduCentersDTO{" + "LANG=" + LANG + ", CHANNEL=" + CHANNEL + ", CUSTOMER_ID=" + CUSTOMER_ID + ", SERVICE_ID=" + SERVICE_ID + ", CODE_ID=" + CODE_ID + ", AMOUNT=" + AMOUNT + ", SERVICE_TYPE=" + SERVICE_TYPE + '}';
    }

        
}
