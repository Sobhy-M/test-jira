/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author omnya
 */
public class EarnListDTO {

    String date;
    String AllEarn;
    String AllFees;
    int transNum;

    public String getAllFees() {
        return AllFees;
    }

    public void setAllFees(String AllFees) {
        this.AllFees = AllFees;
    }

    
   
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAllEarn() {
        return AllEarn;
    }

    public void setAllEarn(String AllEarn) {
        this.AllEarn = AllEarn;
    }

    public int getTransNum() {
        return transNum;
    }

    public void setTransNum(int transNum) {
        this.transNum = transNum;
    }
    
    
    
    
}
