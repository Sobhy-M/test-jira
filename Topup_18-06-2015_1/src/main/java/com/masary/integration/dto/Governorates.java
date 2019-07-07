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
public class Governorates {

    private List<DovernorateDto> governorateDtos;

    private String warnStr;

    public List<DovernorateDto> getGovernorateDtos() {
        return governorateDtos;
    }

    public void setGovernorateDtos(List<DovernorateDto> governorateDtos) {
        this.governorateDtos = governorateDtos;
    }

    public String getWarnStr() {
        return warnStr;
    }

    public void setWarnStr(String warnStr) {
        this.warnStr = warnStr;
    }

    

  

}
