/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.masary.database.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael
 */
public class Masary_Biller {
    String biller_id;
    String biller_name;
    String biller_name_ar;
    boolean active;
    List<CustomerServiceDTO> services;
    List<Masary_Bill_Type> btc ;

    public Masary_Biller() {
        services= new ArrayList();
        btc= new ArrayList();
    }
    
    public List<Masary_Bill_Type> getBtc() {
        return btc;
    }

    public void setBtc(List<Masary_Bill_Type> btc) {
        this.btc = btc;
    }

     public void addBTCService(Masary_Bill_Type btcS) {
        this.btc.add(btcS);
    }
    
    public List<CustomerServiceDTO> getServices() {
        return services;
    }
  
    public void setServices(List<CustomerServiceDTO> services) {
        this.services = services;
    }

    public void addService(CustomerServiceDTO service) {
        this.services.add(service);
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBiller_id() {
        return biller_id;
    }

    public void setBiller_id(String biller_id) {
        this.biller_id = biller_id;
    }

    public String getBiller_name() {
        return biller_name;
    }

    public void setBiller_name(String biller_name) {
        this.biller_name = biller_name;
    }

    public String getBiller_name_ar() {
        return biller_name_ar;
    }

    public void setBiller_name_ar(String biller_name_ar) {
        this.biller_name_ar = biller_name_ar;
    }
    


}
