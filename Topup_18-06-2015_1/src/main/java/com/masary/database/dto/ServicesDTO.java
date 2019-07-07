/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Hammad
 */
public class ServicesDTO {
        
    private int serviceID;
    private String serviceName;
    private String serviceBalance;
    private String serviceOrderBalance;
    private String serviceStopBalance;
    private int serviceIsEnabled;

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int idService) {
        this.serviceID = idService;
    }

    public int getServiceIsEnable() {
        return serviceIsEnabled;
    }

    public void setServiceIsEnabled(int isEnabled) {
        this.serviceIsEnabled = isEnabled;
    }
    
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String ServiceName) {
        this.serviceName = ServiceName;
    }

    public String getServiceBalance() {
        return serviceBalance;
    }

    public void setServiceBalance(String balance) {
        this.serviceBalance = balance;
    }
    
    public String getServiceOrderBalance() {
        return serviceOrderBalance;
    }

    public void setServiceOrderBalance(String balance) {
        this.serviceOrderBalance = balance;
    }
    
    public String getServiceStopBalance() {
        return serviceStopBalance;
    }

    public void setServiceStopBalance(String balance) {
        this.serviceStopBalance = balance;
    }
}
