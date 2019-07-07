/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author omar
 */
public class ProviderDTO {

    private int Id;
    private String name;
//    private Object avilableValues[];
    private List<CategoryDTO> categories;
    private String serviceNameAr;
    private String serviceNameEn;

    public String getServiceNameAr() {
        return serviceNameAr;
    }

    public void setServiceNameAr(String serviceNameAr) {
        this.serviceNameAr = serviceNameAr;
    }

    public String getServiceNameEn() {
        return serviceNameEn;
    }

    public void setServiceNameEn(String serviceNameEn) {
        this.serviceNameEn = serviceNameEn;
    }
//
//    public Object[] getAvilableValues() {
//        return avilableValues;
//    }
//
//    public void setAvilableValues(Object[] avilableValues) {
//        this.avilableValues = avilableValues;
//    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProviderDTO(int Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public void addCategory(CategoryDTO category) {
        if (categories == null) {
            categories = new ArrayList<CategoryDTO>();

        }
        categories.add(category);
    }
}
