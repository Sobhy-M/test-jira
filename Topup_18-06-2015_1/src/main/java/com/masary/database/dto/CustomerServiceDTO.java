/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import com.masary.common.CONFIG;
import java.io.Serializable;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Melad
 */
public class CustomerServiceDTO implements Serializable
{
    private int serviceID;
    private double serviceBalance;
    private String serviceName;
    private String serviceNameArabic;
    private int billerID;
    private double price;
    private int allowAutoAllocat;
    private int isVoucher;
    private int isBill;

    /**
     * Get the value of serviceID
     *
     * @return the value of serviceID
     */
    public int getServiceID() {
        return serviceID;
    }

    public CustomerServiceDTO() {
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getBillerID() {
        return billerID;
    }

    public void setBillerID(int billerID) {
        this.billerID = billerID;
    }

    public String getServiceName(HttpSession session) {
        if (session.getAttribute(CONFIG.lang).equals("ar")) {
            return getServiceNameArabic();
        }
        return serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServiceBalance() {
        return serviceBalance;
    }

    public void setServiceBalance(double serviceBalance) {
        this.serviceBalance = serviceBalance;
    }

    public CustomerServiceDTO(int serviceID, String serviceName, String serviceNameArabic) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceNameArabic = serviceNameArabic;
    }

    public CustomerServiceDTO(int serviceID, String serviceName, double serviceBalance, double price, String serviceNameArabic, int allowAutoAllocat, int isVoucher, int isBill) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceBalance = serviceBalance;
        this.price = price;
        this.serviceNameArabic = serviceNameArabic;
        this.allowAutoAllocat = allowAutoAllocat;
        this.isVoucher = isVoucher;
        this.isBill = isBill;
    }

    public CustomerServiceDTO(int billerID, int serviceID, String serviceName, double serviceBalance, double price, String serviceNameArabic, int allowAutoAllocat, int isVoucher, int isBill) {
        this.billerID = billerID;
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceBalance = serviceBalance;
        this.price = price;
        this.serviceNameArabic = serviceNameArabic;
        this.allowAutoAllocat = allowAutoAllocat;
        this.isVoucher = isVoucher;
        this.isBill = isBill;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getServiceNameArabic() {
        return serviceNameArabic;
    }

    public void setServiceNameArabic(String serviceNameArabic) {
        this.serviceNameArabic = serviceNameArabic;
    }

    public void setAllowAutoAllocat(int allowAutoAllocat) {
        this.allowAutoAllocat = allowAutoAllocat;
    }

    public int allowAutoAllocat() {
        return allowAutoAllocat;
    }

    public void setIsBill(int isBill) {
        this.isBill = isBill;
    }

    public int isBill() {
        return isBill;
    }

    public void setIsVoucher(int isVoucher) {
        this.isVoucher = isVoucher;
    }

    public int isVoucher() {
        return isVoucher;
    }
}
