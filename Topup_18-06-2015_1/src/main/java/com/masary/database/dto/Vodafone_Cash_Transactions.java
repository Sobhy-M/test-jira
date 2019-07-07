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
public class Vodafone_Cash_Transactions {

    List<Transaction> TRANSACTIONS;
    int ROWCOUNT;

    public int getROWCOUNT() {
        return ROWCOUNT;
    }

    public void setROWCOUNT(int ROWCOUNT) {
        this.ROWCOUNT = ROWCOUNT;
    }

    public List<Transaction> getTRANSACTIONS() {
        return TRANSACTIONS;
    }

    public void setTRANSACTIONS(List<Transaction> TRANSACTIONS) {
        this.TRANSACTIONS = TRANSACTIONS;
    }
}
