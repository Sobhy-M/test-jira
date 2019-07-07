/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Voucher {

    int Voucher_ID;
    String Voucher_Pin;
    String Voucher_SN;
    int Transaction_ID;

    public int getVoucher_ID() {
        return Voucher_ID;
    }

    public void setVoucher_ID(int Voucher_ID) {
        this.Voucher_ID = Voucher_ID;
    }

    public String getVoucher_Pin() {
        return Voucher_Pin;
    }

    public void setVoucher_Pin(String Voucher_Pin) {
        this.Voucher_Pin = Voucher_Pin;
    }

    public String getVoucher_SN() {
        return Voucher_SN;
    }

    public void setVoucher_SN(String Voucher_SN) {
        this.Voucher_SN = Voucher_SN;
    }

    public int getTransaction_ID() {
        return Transaction_ID;
    }

    public void setTransaction_ID(int Transaction_ID) {
        this.Transaction_ID = Transaction_ID;
    }
}
