/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Ahmed Khaled
 */
public class FMFInquiryResponse {

    private long identificationNo;
    private String customerName;
    private double installmentAmount;
    private int installmentNo;
    private String installmentDueDate;
    private String customerCode;
    private int installmentAmountPaid;
    private int statusCode;

    public long getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(long identificationNo) {
        this.identificationNo = identificationNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public int getInstallmentNo() {
        return installmentNo;
    }

    public void setInstallmentNo(int installmentNo) {
        this.installmentNo = installmentNo;
    }

    public String getInstallmentDueDate() {
        return installmentDueDate;
    }

    public void setInstallmentDueDate(String installmentDueDate) {
        this.installmentDueDate = installmentDueDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public int getInstallmentAmountPaid() {
        return installmentAmountPaid;
    }

    public void setInstallmentAmountPaid(int installmentAmountPaid) {
        this.installmentAmountPaid = installmentAmountPaid;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
