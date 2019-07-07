/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Etisalat_Response {

    int operationType;
    String Status_Code;
    String Status_Msg;
    String Transaction_ID;
    String Phone;
    double Amount;
    String Bill_Ref_No;
    double Balance;

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double Balance) {
        this.Balance = Balance;
    }
    

    public String getBill_Ref_No() {
        return Bill_Ref_No;
    }

    public void setBill_Ref_No(String Bill_Ref_No) {
        this.Bill_Ref_No = Bill_Ref_No;
    }
    

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getTransaction_ID() {
        return Transaction_ID;
    }

    public void setTransaction_ID(String Transaction_ID) {
        this.Transaction_ID = Transaction_ID;
    }

    public String getStatus_Code() {
        return Status_Code;
    }

    public void setStatus_Code(String Status_Code) {
        this.Status_Code = Status_Code;
    }

    public String getStatus_Msg() {
        return Status_Msg;
    }

    public void setStatus_Msg(String Status_Error) {
        this.Status_Msg = Status_Error;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }
    
}
