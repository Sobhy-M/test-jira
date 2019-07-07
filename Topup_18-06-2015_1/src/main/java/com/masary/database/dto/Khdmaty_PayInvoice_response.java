/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Michael
 */
public class Khdmaty_PayInvoice_response {

    String ServiceName;
    String ReturnCode;
    String ReturnMessage;
    String TelephoneCode;
    String TelephoneNumber;
    String CustomerName;
    String Invoice;
    double InvoiceTotal;
    String Request;
    double CenterBalance;
    String BILL_REF_NUMBER;

    public String getBILL_REF_NUMBER() {
        return BILL_REF_NUMBER;
    }

    public void setBILL_REF_NUMBER(String BILL_REF_NUMBER) {
        this.BILL_REF_NUMBER = BILL_REF_NUMBER;
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

    public String getInvoice() {
        return Invoice;
    }

    public void setInvoice(String Invoice) {
        this.Invoice = Invoice;
    }

    public double getInvoiceTotal() {
        return InvoiceTotal / 100;
    }

    public void setInvoiceTotal(double InvoiceTotal) {
        this.InvoiceTotal = InvoiceTotal;
    }

    public String getRequest() {
        return Request;
    }

    public void setRequest(String Request) {
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
