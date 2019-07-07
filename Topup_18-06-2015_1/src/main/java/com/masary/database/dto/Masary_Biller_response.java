/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author KEMO
 */
public class Masary_Biller_response {

    String REQID;
    XMLGregorianCalendar RESPONSE_DATE;
    String CHANEL_REQ_ID;
    String ORIGINATOR_CODE;
    String TERMINAL_ID;
    String CLIENT_TERMINAL_SEQ_ID;
    String STATUS_CODE;
    String MATCHED_REC;
    String DELIVERY_METHOD;
    List<Masary_Bill_Biller> billers;
    Masary_Bill_Status status;

    public Masary_Bill_Status getStatus() {
        return status;
    }

    public void setStatus(Masary_Bill_Status status) {
        this.status = status;
    }

    public String getCHANEL_REQ_ID() {
        return CHANEL_REQ_ID;
    }

    public void setCHANEL_REQ_ID(String CHANEL_REQ_ID) {
        this.CHANEL_REQ_ID = CHANEL_REQ_ID;
    }

    public String getCLIENT_TERMINAL_SEQ_ID() {
        return CLIENT_TERMINAL_SEQ_ID;
    }

    public void setCLIENT_TERMINAL_SEQ_ID(String CLIENT_TERMINAL_SEQ_ID) {
        this.CLIENT_TERMINAL_SEQ_ID = CLIENT_TERMINAL_SEQ_ID;
    }

    public String getDELIVERY_METHOD() {
        return DELIVERY_METHOD;
    }

    public void setDELIVERY_METHOD(String DELIVERY_METHOD) {
        this.DELIVERY_METHOD = DELIVERY_METHOD;
    }

    public String getMATCHED_REC() {
        return MATCHED_REC;
    }

    public void setMATCHED_REC(String MATCHED_REC) {
        this.MATCHED_REC = MATCHED_REC;
    }

    public String getORIGINATOR_CODE() {
        return ORIGINATOR_CODE;
    }

    public void setORIGINATOR_CODE(String ORIGINATOR_CODE) {
        this.ORIGINATOR_CODE = ORIGINATOR_CODE;
    }

    public String getREQID() {
        return REQID;
    }

    public void setREQID(String REQID) {
        this.REQID = REQID;
    }

    public XMLGregorianCalendar getRESPONSE_DATE() {
        return RESPONSE_DATE;
    }

    public void setRESPONSE_DATE(XMLGregorianCalendar RESPONSE_DATE) {
        this.RESPONSE_DATE = RESPONSE_DATE;
    }

    public String getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(String STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public String getTERMINAL_ID() {
        return TERMINAL_ID;
    }

    public void setTERMINAL_ID(String TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID;
    }

    public List<Masary_Bill_Biller> getBillers() {
        return billers;
    }

    public void setBillers(List<Masary_Bill_Biller> billers) {
        this.billers = billers;
    }
    
    
}
