/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author KEMO
 */
public class BulkSMSReportDTO {
    
    int requestID;
    String message;
    int countMobiles;
    String response;
    String msisdn;
    int statusCode;
    int nretry;
    int refundTxn;

    public int getCountMobiles() {
        return countMobiles;
    }

    public void setCountMobiles(int countMobiles) {
        this.countMobiles = countMobiles;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getNretry() {
        return nretry;
    }

    public void setNretry(int nretry) {
        this.nretry = nretry;
    }

    public int getRefundTxn() {
        return refundTxn;
    }

    public void setRefundTxn(int refundTxn) {
        this.refundTxn = refundTxn;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
}
