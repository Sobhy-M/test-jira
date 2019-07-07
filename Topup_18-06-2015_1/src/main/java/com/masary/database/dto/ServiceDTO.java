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
public class ServiceDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idService;
    private String strServiceName;
    private String strServiceNameArabic;
    private double price;

    public ServiceDTO() {
    }

    public ServiceDTO(int idService) {
        this.idService = idService;
    }

    public ServiceDTO(int idService, String strServiceName, double price,String strServiceNameArabic) {
        this.idService = idService;
        this.strServiceName = strServiceName;
        this.price = price;
        this.strServiceNameArabic=strServiceNameArabic;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getStrServiceName(HttpSession session) {
        if (session.getAttribute(CONFIG.lang).equals("ar")) {
            return  getStrServiceNameArabic();
        }
        return strServiceName;
    }

    public void setStrServiceName(String strServiceName) {
        this.strServiceName = strServiceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStrServiceNameArabic() {
        return strServiceNameArabic;
    }

    public void setStrServiceNameArabic(String strServiceNameArabic) {
        this.strServiceNameArabic = strServiceNameArabic;
    }


}
