/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Ehab
 */
public class Governorate {
    
    int countryID;
    String governorateName;
    String governorateISO;

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getGovernorateISO() {
        return governorateISO;
    }

    public void setGovernorateISO(String governorateISO) {
        this.governorateISO = governorateISO;
    }

    public String getGovernorateName() {
        return governorateName;
    }

    public void setGovernorateName(String governorateName) {
        this.governorateName = governorateName;
    }
   
}
