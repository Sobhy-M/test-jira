/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Main_Provider {

    int PROVIDER_ID;
    String NAME;
    int COUNTRY_ID;
    String MASARY_ID;
    String MASARY_PASSWORD;
    int order;
    int successStatus;
    int noBalanceStatus;
    int time_out;

    public int getTime_out() {
        return time_out;
    }

    public void setTime_out(int time_out) {
        this.time_out = time_out;
    }

    public int getNoBalanceStatus() {
        return noBalanceStatus;
    }

    public void setNoBalanceStatus(int noBalanceStatus) {
        this.noBalanceStatus = noBalanceStatus;
    }

    public int getSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(int successStatus) {
        this.successStatus = successStatus;
    }

    public int getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    public void setCOUNTRY_ID(int COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }

    public int getPROVIDER_ID() {
        return PROVIDER_ID;
    }

    public void setPROVIDER_ID(int PROVIDER_ID) {
        this.PROVIDER_ID = PROVIDER_ID;
    }

    public String getMASARY_ID() {
        return MASARY_ID;
    }

    public void setMASARY_ID(String MASARY_ID) {
        this.MASARY_ID = MASARY_ID;
    }

    public String getMASARY_PASSWORD() {
        return MASARY_PASSWORD;
    }

    public void setMASARY_PASSWORD(String MASARY_PASSWORD) {
        this.MASARY_PASSWORD = MASARY_PASSWORD;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
