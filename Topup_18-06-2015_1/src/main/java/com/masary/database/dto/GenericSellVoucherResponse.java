/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melad
 */
public class GenericSellVoucherResponse {

    private String transId;
    private String voucher, serialNumber, StatusMSG;
    private String date;
    private List<GenericVoucherData> vouchersData = new ArrayList<GenericVoucherData>();
//    private ArrayList<String> voucherPin = new ArrayList<String>();
//    private ArrayList<String> voucherSerial = new ArrayList<String>();
    
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

    public List<GenericVoucherData> getVouchersData() {
        return vouchersData;
    }

    public void setVouchersData(List<GenericVoucherData> vouchersData) {
        this.vouchersData = vouchersData;
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

    public GenericSellVoucherResponse(String transId, String voucher, String serialNumber) {
        this.transId = transId;
        this.voucher = voucher;
        this.serialNumber = serialNumber;
    }

    public GenericSellVoucherResponse() {
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
