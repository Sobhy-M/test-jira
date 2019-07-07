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
public class VC_Commission_Structure {

    List<COMMISSION> COMMISSIONS;
    double MM_COMM;
    double MM_Fixed;

    public double getMM_COMM() {
        return MM_COMM;
    }

    public void setMM_COMM(double MM_COMM) {
        this.MM_COMM = MM_COMM;
    }

    public double getMM_Fixed() {
        return MM_Fixed;
    }

    public void setMM_Fixed(double MM_Fixed) {
        this.MM_Fixed = MM_Fixed;
    }

    public List<COMMISSION> getCOMMISSIONS() {
        return COMMISSIONS;
    }

    public void setCOMMISSIONS(List<COMMISSION> COMMISSIONS) {
        this.COMMISSIONS = COMMISSIONS;
    }
}
