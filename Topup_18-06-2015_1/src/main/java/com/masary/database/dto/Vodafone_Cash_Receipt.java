/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Vodafone_Cash_Receipt {

    String TRANSACTION_ID;
    String OPERATOR_TRANSACTION_ID;
    int CUSTOMER_ID;
    String TRANSACTION_DATE;
    String CUSTOMER_PHONE;
    double AMOUNT;
    double CUSTOMER_FEE;
    String FEES_DECLARATION;
    int REQUEST_ID;
    int OPERATION_TYPE;
    double TOTAL;

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }
    

    public int getOPERATION_TYPE() {
        return OPERATION_TYPE;
    }

    public void setOPERATION_TYPE(int OPERATION_TYPE) {
        this.OPERATION_TYPE = OPERATION_TYPE;
    }
    

    public int getREQUEST_ID() {
        return REQUEST_ID;
    }

    public void setREQUEST_ID(int REQUEST_ID) {
        this.REQUEST_ID = REQUEST_ID;
    }
    

    public String getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public void setTRANSACTION_ID(String TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    public String getOPERATOR_TRANSACTION_ID() {
        return OPERATOR_TRANSACTION_ID;
    }

    public void setOPERATOR_TRANSACTION_ID(String OPERATOR_TRANSACTION_ID) {
        this.OPERATOR_TRANSACTION_ID = OPERATOR_TRANSACTION_ID;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(int CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getTRANSACTION_DATE() {
        return TRANSACTION_DATE;
    }

    public void setTRANSACTION_DATE(String TRANSACTION_DATE) {
        this.TRANSACTION_DATE = TRANSACTION_DATE;
    }

    public String getCUSTOMER_PHONE() {
        return CUSTOMER_PHONE;
    }

    public void setCUSTOMER_PHONE(String CUSTOMER_PHONE) {
        this.CUSTOMER_PHONE = CUSTOMER_PHONE;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public double getCUSTOMER_FEE() {
        return CUSTOMER_FEE;
    }

    public void setCUSTOMER_FEE(double CUSTOMER_FEE) {
        this.CUSTOMER_FEE = CUSTOMER_FEE;
    }

    public String getFEES_DECLARATION() {
        return FEES_DECLARATION;
    }

    public void setFEES_DECLARATION(String FEES_DECLARATION) {
        this.FEES_DECLARATION = FEES_DECLARATION;
    }
}
