/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.List;

/**
 *
 * @author Michael
 */
public class eFinance_Inquiry_Res {

    String MSG_ar;
    String MSG_en;
    String EPayBillRecID;
    String BillNumber;
    String BillingAcct;
    String BillerId;
    String DueDt;
    double amount;
    double Min_amount;
    double Max_amount;
    String StatusCode;
    String ShortDesc;
    String CurCode;
    String Sequence;
    double FeeAmount;
    String FeeCurCode;
    String RqUID;
    String ServiceType;
    List<eFinance_Inq_Res_amounts> amounts;

    public List<eFinance_Inq_Res_amounts> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<eFinance_Inq_Res_amounts> amounts) {
        this.amounts = amounts;
    }
    

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String ServiceType) {
        this.ServiceType = ServiceType;
    }

    public String getRqUID() {
        return RqUID;
    }

    public void setRqUID(String RqUID) {
        this.RqUID = RqUID;
    }

    public double getFeeAmount() {
        return FeeAmount;
    }

    public void setFeeAmount(double FeeAmount) {
        this.FeeAmount = FeeAmount;
    }

    public String getFeeCurCode() {
        return FeeCurCode;
    }

    public void setFeeCurCode(String FeeCurCode) {
        this.FeeCurCode = FeeCurCode;
    }

    public String getCurCode() {
        return CurCode;
    }

    public void setCurCode(String CurCode) {
        this.CurCode = CurCode;
    }

    public String getSequence() {
        return Sequence;
    }

    public void setSequence(String Sequence) {
        this.Sequence = Sequence;
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

    public String getMSG_ar() {
        return MSG_ar;
    }

    public void setMSG_ar(String MSG_ar) {
        this.MSG_ar = MSG_ar;
    }

    public String getMSG_en() {
        return MSG_en;
    }

    public void setMSG_en(String MSG_en) {
        this.MSG_en = MSG_en;
    }

    public String getEPayBillRecID() {
        return EPayBillRecID;
    }

    public void setEPayBillRecID(String EPayBillRecID) {
        this.EPayBillRecID = EPayBillRecID;
    }

    public String getBillNumber() {
        return BillNumber;
    }

    public void setBillNumber(String BillNumber) {
        this.BillNumber = BillNumber;
    }

    public String getBillingAcct() {
        return BillingAcct;
    }

    public void setBillingAcct(String BillingAcct) {
        this.BillingAcct = BillingAcct;
    }

    public String getBillerId() {
        return BillerId;
    }

    public void setBillerId(String BillerId) {
        this.BillerId = BillerId;
    }

    public String getDueDt() {
        return DueDt;
    }

    public void setDueDt(String DueDt) {
        this.DueDt = DueDt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getMin_amount() {
        return Min_amount;
    }

    public void setMin_amount(double Min_amount) {
        this.Min_amount = Min_amount;
    }

    public double getMax_amount() {
        return Max_amount;
    }

    public void setMax_amount(double Max_amount) {
        this.Max_amount = Max_amount;
    }
}
