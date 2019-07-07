/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author AYA
 */
public class BillsDetailsRepresentation {

    private String customerName;
    private int billsNumber;
    private double totalBillsAmount;
    private double oldestBillsAmount;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getBillsNumber() {
        return billsNumber;
    }

    public void setBillsNumber(int billsNumber) {
        this.billsNumber = billsNumber;
    }

    public double getTotalBillsAmount() {
        return totalBillsAmount;
    }

    public void setTotalBillsAmount(double totalBillsAmount) {
        this.totalBillsAmount = totalBillsAmount;
    }

    public double getOldestBillsAmount() {
        return oldestBillsAmount;
    }

    public void setOldestBillsAmount(double oldestBillsAmount) {
        this.oldestBillsAmount = oldestBillsAmount;
    }

}
