/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author KEMO
 */
public class Masary_Billing_Account {
int ACCOUNT_ID;
int ACCOUNT_CODE;
String ACCOUNT_PASS;
String ACCOUNT_SEC_PIN;
String TERMINAL_TYPE;
String SERIAL_NUMBER;

    public int getACCOUNT_CODE() {
        return ACCOUNT_CODE;
    }

    public void setACCOUNT_CODE(int ACCOUNT_CODE) {
        this.ACCOUNT_CODE = ACCOUNT_CODE;
    }

    public int getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(int ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getACCOUNT_PASS() {
        return ACCOUNT_PASS;
    }

    public void setACCOUNT_PASS(String ACCOUNT_PASS) {
        this.ACCOUNT_PASS = ACCOUNT_PASS;
    }

    public String getACCOUNT_SEC_PIN() {
        return ACCOUNT_SEC_PIN;
    }

    public void setACCOUNT_SEC_PIN(String ACCOUNT_SEC_PIN) {
        this.ACCOUNT_SEC_PIN = ACCOUNT_SEC_PIN;
    }

    public String getSERIAL_NUMBER() {
        return SERIAL_NUMBER;
    }

    public void setSERIAL_NUMBER(String SERIAL_NUMBER) {
        this.SERIAL_NUMBER = SERIAL_NUMBER;
    }

    public String getTERMINAL_TYPE() {
        return TERMINAL_TYPE;
    }

    public void setTERMINAL_TYPE(String TERMINAL_TYPE) {
        this.TERMINAL_TYPE = TERMINAL_TYPE;
    }

}
