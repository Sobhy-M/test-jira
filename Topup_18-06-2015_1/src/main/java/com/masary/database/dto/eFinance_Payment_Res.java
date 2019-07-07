/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class eFinance_Payment_Res {
    String StatusCode;
    String ShortDesc;
    String RqUID;
    String PmtId;
    String PmtIdType;
    String BILL_REF_NUMBER;

    public String getBILL_REF_NUMBER() {
        return BILL_REF_NUMBER;
    }

    public void setBILL_REF_NUMBER(String BILL_REF_NUMBER) {
        this.BILL_REF_NUMBER = BILL_REF_NUMBER;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getShortDesc() {
        return ShortDesc;
    }

    public void setShortDesc(String ShortDesc) {
        this.ShortDesc = ShortDesc;
    }

    public String getRqUID() {
        return RqUID;
    }

    public void setRqUID(String RqUID) {
        this.RqUID = RqUID;
    }

    public String getPmtId() {
        return PmtId;
    }

    public void setPmtId(String PmtId) {
        this.PmtId = PmtId;
    }

    public String getPmtIdType() {
        return PmtIdType;
    }

    public void setPmtIdType(String PmtIdType) {
        this.PmtIdType = PmtIdType;
    }
    
}
