/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Ehab
 */
public class IBTResonseDTO {

    private String ibtCode;
    private String ibtMsg;
    private String transactionID;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIbtCode() {
        return ibtCode;
    }

    public void setIbtCode(String ibtCode) {
        this.ibtCode = ibtCode;
    }

    public String getIbtMsg() {
        return ibtMsg;
    }

    public void setIbtMsg(String ibtMsg) {
        this.ibtMsg = ibtMsg;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}
