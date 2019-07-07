/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.masary.database.dto;


/**
 *
 * @author user
 */
public class ElectricityBillWithCommissionsRepresentation extends PayedBillsRepresentation{
    private double toBePaied;
	private double servicePrice ;

    /**
     * @return the toBePaied
     */
    public double getToBePaied() {
        return toBePaied;
    }

    /**
     * @param toBePaied the toBePaied to set
     */
    public void setToBePaied(double toBePaied) {
        this.toBePaied = toBePaied;
    }

    /**
     * @return the servicePrice
     */
    public double getServicePrice() {
        return servicePrice;
    }

    /**
     * @param servicePrice the servicePrice to set
     */
    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

  
        
}
