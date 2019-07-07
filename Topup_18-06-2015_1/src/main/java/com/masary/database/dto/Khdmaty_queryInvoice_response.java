/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Khdmaty_queryInvoice_response {

    String ServiceName;
    String ReturnCode;
    String ReturnMessage;
    String TelephoneCode;
    String TelephoneNumber;
    String CustomerName;
    double InvoiceTotal;
    int Request;
    double CenterBalance;
    String billDate;
    String newDurationInMonths;
    String lastActiveADSLExpiryDate;
    String newExpiryDateAfterRenewal;
    double newCPERentalPackagePrice;
    double newOptionPackPackagePrice;

    public double getNewCPERentalPackagePrice() {
        return newCPERentalPackagePrice;
    }

    public void setNewCPERentalPackagePrice(double newCPERentalPackagePrice) {
        this.newCPERentalPackagePrice = newCPERentalPackagePrice;
    }

    public double getNewOptionPackPackagePrice() {
        return newOptionPackPackagePrice;
    }

    public void setNewOptionPackPackagePrice(double newOptionPackPackagePrice) {
        this.newOptionPackPackagePrice = newOptionPackPackagePrice;
    }

    public String getLastActiveADSLExpiryDate() {
        return lastActiveADSLExpiryDate;
    }

    public void setLastActiveADSLExpiryDate(String lastActiveADSLExpiryDate) {
        this.lastActiveADSLExpiryDate = lastActiveADSLExpiryDate;
    }

    public String getNewDurationInMonths() {
        return newDurationInMonths;
    }

    public void setNewDurationInMonths(String newDurationInMonths) {
        this.newDurationInMonths = newDurationInMonths;
    }

    public String getNewExpiryDateAfterRenewal() {
        return newExpiryDateAfterRenewal;
    }

    public void setNewExpiryDateAfterRenewal(String newExpiryDateAfterRenewal) {
        this.newExpiryDateAfterRenewal = newExpiryDateAfterRenewal;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public double getCenterBalance() {
        return CenterBalance;
    }

    public void setCenterBalance(double CenterBalance) {
        this.CenterBalance = CenterBalance;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public double getInvoiceTotal() {
        if(ServiceName.equals("TEData")){
        return InvoiceTotal+newCPERentalPackagePrice+newOptionPackPackagePrice;
        }else{
        return InvoiceTotal;
        }
    }

    public void setInvoiceTotal(double InvoiceTotal) {
        this.InvoiceTotal = InvoiceTotal;
    }

    public int getRequest() {
        return Request;
    }

    public void setRequest(int Request) {
        this.Request = Request;
    }

    public String getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(String ReturnCode) {
        this.ReturnCode = ReturnCode;
    }

    public String getReturnMessage() {
        return ReturnMessage;
    }

    public void setReturnMessage(String ReturnMessage) {
        this.ReturnMessage = ReturnMessage;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    public String getTelephoneCode() {
        return TelephoneCode;
    }

    public void setTelephoneCode(String TelephoneCode) {
        this.TelephoneCode = TelephoneCode;
    }

    public String getTelephoneNumber() {
        return TelephoneNumber;
    }

    public void setTelephoneNumber(String TelephoneNumber) {
        this.TelephoneNumber = TelephoneNumber;
    }
}
