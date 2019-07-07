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
public class ZaidElKhierCategoriesResponse {
    
    private zaidElkierSubCategoryRepresentation[] zaidElkierSubCategoryRepresentation;
    private int categoryId;
    private String categoryName;

    public zaidElkierSubCategoryRepresentation[] getZaidElkierSubCategoryRepresentation() {
        return zaidElkierSubCategoryRepresentation;
    }

    public void setZaidElkierSubCategoryRepresentation(zaidElkierSubCategoryRepresentation[] zaidElkierSubCategoryRepresentation) {
        this.zaidElkierSubCategoryRepresentation = zaidElkierSubCategoryRepresentation;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
}
