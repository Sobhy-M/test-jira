/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author user
 */
public class zaidElkierSubCategoryRepresentation {

    private String subCategoryName;
    private int subCategoryId;
    private long subServiceId;

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public long getSubServiceId() {
        return subServiceId;
    }

    public void setSubServiceId(long subServiceId) {
        this.subServiceId = subServiceId;
    }

    @Override
    public String toString() {
        return subCategoryName + "-" + subServiceId;
    }

}
