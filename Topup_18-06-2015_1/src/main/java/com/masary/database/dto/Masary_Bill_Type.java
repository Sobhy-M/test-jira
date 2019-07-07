/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import com.masary.common.CONFIG;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KEMO
 */
public class Masary_Bill_Type {

    int BILLER_ID;
    int BILL_TYPE_ID;
    String BILL_NAME;
    String PMT_TYPE;
    String SERVICE_TYPE;
    String SERVICE_NAME;
    String BILL_LABLE;
    boolean IS_FRAC_ACC;
    boolean IS_PART_ACC;
    boolean IS_OVER_ACC;
    boolean IS_ADV_ACC;
    String SERVICE_AR_NAME;
    String BILL_LABLE_AR;
    String customerFees;

    public String getCustomerFees() {
        return customerFees;
    }

    public void setCustomerFees(String customerFees) {
        this.customerFees = customerFees;
    }

    public String getBILL_LABLE_AR() {
        return BILL_LABLE_AR;
    }

    public void setBILL_LABLE_AR(String BILL_LABLE_AR) {
        this.BILL_LABLE_AR = BILL_LABLE_AR;
    }

    public String getSERVICE_AR_NAME() {
        return SERVICE_AR_NAME;
    }

    public void setSERVICE_AR_NAME(String SERVICE_AR_NAME) {
        this.SERVICE_AR_NAME = SERVICE_AR_NAME;
    }

    public int getBILLER_ID() {
        return BILLER_ID;
    }

    public void setBILLER_ID(int BILLER_ID) {
        this.BILLER_ID = BILLER_ID;
    }

    public String getBILL_LABLE() {
        return BILL_LABLE;
    }

    public void setBILL_LABLE(String BILL_LABLE) {
        this.BILL_LABLE = BILL_LABLE;
    }

    public String getBILL_NAME() {
        return BILL_NAME;
    }

    public void setBILL_NAME(String BILL_NAME) {
        this.BILL_NAME = BILL_NAME;
    }

    public int getBILL_TYPE_ID() {
        return BILL_TYPE_ID;
    }

    public void setBILL_TYPE_ID(int BILL_TYPE_ID) {
        this.BILL_TYPE_ID = BILL_TYPE_ID;
    }

    public boolean isIS_ADV_ACC() {
        return IS_ADV_ACC;
    }

    public void setIS_ADV_ACC(boolean IS_ADV_ACC) {
        this.IS_ADV_ACC = IS_ADV_ACC;
    }

    public boolean isIS_FRAC_ACC() {
        return IS_FRAC_ACC;
    }

    public void setIS_FRAC_ACC(boolean IS_FRAC_ACC) {
        this.IS_FRAC_ACC = IS_FRAC_ACC;
    }

    public boolean isIS_OVER_ACC() {
        return IS_OVER_ACC;
    }

    public void setIS_OVER_ACC(boolean IS_OVER_ACC) {
        this.IS_OVER_ACC = IS_OVER_ACC;
    }

    public boolean isIS_PART_ACC() {
        return IS_PART_ACC;
    }

    public void setIS_PART_ACC(boolean IS_PART_ACC) {
        this.IS_PART_ACC = IS_PART_ACC;
    }

    public String getPMT_TYPE() {
        return PMT_TYPE;
    }

    public void setPMT_TYPE(String PMT_TYPE) {
        this.PMT_TYPE = PMT_TYPE;
    }

    public String getSERVICE_NAME() {
        return SERVICE_NAME;
    }

    public void setSERVICE_NAME(String SERVICE_NAME) {
        this.SERVICE_NAME = SERVICE_NAME;
    }

    public String getSERVICE_TYPE() {
        return SERVICE_TYPE;
    }

    public void setSERVICE_TYPE(String SERVICE_TYPE) {
        this.SERVICE_TYPE = SERVICE_TYPE;
    }

    public String getStrBTCName(HttpSession session) {
        if (session.getAttribute(CONFIG.lang).equals("ar")) {
            return getSERVICE_AR_NAME();
        }
        return this.SERVICE_NAME;
    }

    public String get_BILL_LABLE(HttpSession session) {
        if (session.getAttribute(CONFIG.lang).equals("ar")) {
            return getBILL_LABLE_AR();
        }
        return this.getBILL_LABLE();
    }
}
