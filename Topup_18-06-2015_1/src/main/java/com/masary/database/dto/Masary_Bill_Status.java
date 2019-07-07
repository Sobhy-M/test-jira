/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Masary_Bill_Status {
String STATUS_CODE;
String SEVERITY;
String STATUS_DESC;

    public String getSEVERITY() {
        return SEVERITY;
    }

    public void setSEVERITY(String SEVERITY) {
        this.SEVERITY = SEVERITY;
    }

    public String getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(String STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public String getSTATUS_DESC() {
        return STATUS_DESC;
    }

    public void setSTATUS_DESC(String STATUS_DESC) {
        this.STATUS_DESC = STATUS_DESC;
    }

}
