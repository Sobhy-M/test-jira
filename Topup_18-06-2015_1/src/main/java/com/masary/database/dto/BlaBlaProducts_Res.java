/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.List;

/**
 *
 * @author Aya
 */
public class BlaBlaProducts_Res {

    int status;
    String statusText;
    List<Product> value;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public List<Product> getValue() {
        return value;
    }

    public void setValue(List<Product> value) {
        this.value = value;
    }
    
}
