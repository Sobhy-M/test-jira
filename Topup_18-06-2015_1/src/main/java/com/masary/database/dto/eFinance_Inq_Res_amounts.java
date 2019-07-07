/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author omnya
 */
public class eFinance_Inq_Res_amounts {
    String Sequence;
    String Amt;
    String CurCode;
    String month;

    public String getSequence() {
        return Sequence;
    }

    public void setSequence(String Sequence) {
        this.Sequence = Sequence;
    }

    public String getAmt() {
        return Amt;
    }

    public void setAmt(String Amt) {
        this.Amt = Amt;
    }

    public String getCurCode() {
        return CurCode;
    }

    public void setCurCode(String CurCode) {
        this.CurCode = CurCode;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "eFinance_Inq_Res_amounts{" + "Sequence=" + Sequence + ", Amt=" + Amt + ", CurCode=" + CurCode + ", month=" + month + '}';
    }
    
}
