/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.List;

/**
 *
 * @author DaviD
 */
public class AvilableVouchersDTO {

    private String ProviderId;
    private String ProviderName;
    private String VoucherValues;
    private String categoryId;
    private String categoryName;
    private String AvilableVouchers;
    private List<AvilableVouchersDTO> avilableVouchersDTOs;

    public List<AvilableVouchersDTO> getAvilableVouchersDTOs() {
        return avilableVouchersDTOs;
    }

    public void setAvilableVouchersDTOs(List<AvilableVouchersDTO> avilableVouchersDTOs) {
        this.avilableVouchersDTOs = avilableVouchersDTOs;
    }

    public String getAvilableVouchers() {
        return AvilableVouchers;
    }

    public void setAvilableVouchers(String AvilableVouchers) {
        this.AvilableVouchers = AvilableVouchers;
    }

    public String getProviderId() {
        return ProviderId;
    }

    public void setProviderId(String ProviderId) {
        this.ProviderId = ProviderId;
    }

    public String getProviderName() {
        return ProviderName;
    }

    public void setProviderName(String ProviderName) {
        this.ProviderName = ProviderName;
    }

    public String getVoucherValues() {
        return VoucherValues;
    }

    public void setVoucherValues(String VoucherValues) {
        this.VoucherValues = VoucherValues;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


}
