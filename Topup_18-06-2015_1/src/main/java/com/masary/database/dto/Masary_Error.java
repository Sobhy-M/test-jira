/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import com.masary.common.CONFIG;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KEMO
 */
public class Masary_Error {

    int STATUS_CODE;
    String STATUS_DESC_EN;
    String STATUS_DESC_AR;

    public int getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(int STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public String getSTATUS_DESC_AR() {
        return STATUS_DESC_AR;
    }

    public void setSTATUS_DESC_AR(String STATUS_DESC_AR) {
        this.STATUS_DESC_AR = STATUS_DESC_AR;
    }

    public String getSTATUS_DESC_EN() {
        return STATUS_DESC_EN;
    }

    public void setSTATUS_DESC_EN(String STATUS_DESC_EN) {
        this.STATUS_DESC_EN = STATUS_DESC_EN;
    }

    public String getError(HttpSession session) {
        if (session.getAttribute(CONFIG.lang).equals("ar")) {
            return getSTATUS_DESC_AR();
        }
        return STATUS_DESC_EN;
    }
}
