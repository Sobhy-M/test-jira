/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Tasneem
 */
public class TEDataBillsRequest {
 private String landLine;
	private String customerNumber;
	public String getLandLine() {
		return landLine;
	}
	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	@Override
	public String toString() {
		return "LandLineAndPreviousGlobalTrxId [landLine=" + landLine + ", customerNumber=" + customerNumber + "]";
	}    
  
}
