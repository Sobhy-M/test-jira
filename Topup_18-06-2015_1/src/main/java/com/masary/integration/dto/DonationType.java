/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

import java.util.List;

/**
 *
 * @author omar.abdellah
 */
public class DonationType {

    List<TypesRepresentitions> typesRepresentitions;

    private String warnMessage;

 

    public String getWarnMessage() {
        return warnMessage;
    }

    public void setWarnMessage(String warnMessage) {
        this.warnMessage = warnMessage;
    }

    public List<TypesRepresentitions> getTypesRepresentitions() {
        return typesRepresentitions;
    }

    public void setTypesRepresentitions(List<TypesRepresentitions> typesRepresentitions) {
        this.typesRepresentitions = typesRepresentitions;
    }

    
    

}
