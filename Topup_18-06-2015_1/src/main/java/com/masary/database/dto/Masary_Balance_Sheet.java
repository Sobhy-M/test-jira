/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.List;

/**
 *
 * @author KEMO
 */
public class Masary_Balance_Sheet {

    String BALANCE_DATE;
    double OPENING_BALANCE;
    double CLOSEING_BALANCE;
    String STATUSCODE;
    String STATUSDESC;
    List<Masary_Balance_Sheet_Detailes> Masary_Balance_Sheet_Detailes;

    public String getSTATUSCODE() {
        return STATUSCODE;
    }

    public void setSTATUSCODE(String STATUSCODE) {
        this.STATUSCODE = STATUSCODE;
    }

    public String getSTATUSDESC() {
        return STATUSDESC;
    }

    public void setSTATUSDESC(String STATUSDESC) {
        this.STATUSDESC = STATUSDESC;
    }

    public List<com.masary.database.dto.Masary_Balance_Sheet_Detailes> getMasary_Balance_Sheet_Detailes() {
        return Masary_Balance_Sheet_Detailes;
    }

    public void setMasary_Balance_Sheet_Detailes(List<com.masary.database.dto.Masary_Balance_Sheet_Detailes> Masary_Balance_Sheet_Detailes) {
        this.Masary_Balance_Sheet_Detailes = Masary_Balance_Sheet_Detailes;
    }

    public String getBALANCE_DATE() {
        return BALANCE_DATE;
    }

    public void setBALANCE_DATE(String BALANCE_DATE) {
        this.BALANCE_DATE = BALANCE_DATE;
    }

    public double getCLOSEING_BALANCE() {
        return CLOSEING_BALANCE;
    }

    public void setCLOSEING_BALANCE(double CLOSEING_BALANCE) {
        this.CLOSEING_BALANCE = CLOSEING_BALANCE;
    }

    public double getOPENING_BALANCE() {
        return OPENING_BALANCE;
    }

    public void setOPENING_BALANCE(double OPENING_BALANCE) {
        this.OPENING_BALANCE = OPENING_BALANCE;
    }
}
