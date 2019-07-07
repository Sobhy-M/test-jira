/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.ArrayList;

/**
 *
 * @author Melad
 */
public class SellVoucherResponse {

    private String transId;
    private String voucher, serialNumber, StatusMSG;
    private String date;
    private ArrayList<String> voucherPin = new ArrayList<String>();
    private ArrayList<String> voucherSerial = new ArrayList<String>();
    private BulkVoucherDTO voucherInfo;

    public BulkVoucherDTO getVoucherInfo() {
        return voucherInfo;
    }

    public void setVoucherInfo(BulkVoucherDTO voucherInfo) {
        this.voucherInfo = voucherInfo;
    }

    public String geDate() {
        return date;
    }

    public ArrayList<String> getVoucherPin() {
        return voucherPin;
    }

    public void setVoucherPin(ArrayList<String> voucherPin) {
        this.voucherPin = voucherPin;
    }

    public ArrayList<String> getVoucherSerial() {
        return voucherSerial;
    }

    public void setVoucherSerial(ArrayList<String> voucherSerial) {
        this.voucherSerial = voucherSerial;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatusMSG() {
        return StatusMSG;
    }

    public void setStatusMSG(String StatusMSG) {
        this.StatusMSG = StatusMSG;
    }

    public SellVoucherResponse(String transId, String voucher, String serialNumber) {
        this.transId = transId;
        this.voucher = voucher;
        this.serialNumber = serialNumber;
    }

    public SellVoucherResponse() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }
}
