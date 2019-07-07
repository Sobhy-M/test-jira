/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Ehab
 */
public class Country {

    String countryName;
    String countryISO3;
    int countryID;
    boolean hasGov;
    HashMap<String, String> governoratesList = new HashMap();
    Object[] governoratesName;
    Object[] governoratesISO;

    public Object[] getGovernoratesISO() {
        if (!governoratesList.isEmpty()) {
            return governoratesList.keySet().toArray();
        }
        return governoratesISO;
    }

    public Object[] getGovernoratesName() {
        if (!governoratesList.isEmpty()) {
            return governoratesList.values().toArray();
        }
        return governoratesName;
    }

    public boolean isHasGov() {
        return hasGov;
    }

    public void setHasGov(boolean hasGov) {
        this.hasGov = hasGov;
    }

    public HashMap<String, String> getGovernoratesList() {
        return governoratesList;
    }

    public void setGovernoratesList(HashMap<String, String> governoratesList) {
        this.governoratesList = governoratesList;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryISO3() {
        return countryISO3;
    }

    public void setCountryISO3(String countryISO3) {
        this.countryISO3 = countryISO3;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
