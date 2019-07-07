package com.masary.integration.dto;

/**
 *
 * @author Ahmed Khaled
 */
public class OrangeGiftsInquiryRepresentation {

    private String validationId;
    private long expirationDate;
    private long requestID;
    private long voucherID;
    private boolean result;
    private boolean vouchersStatusIsFilled;
    private double voucherAmount;

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public long getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(long voucherID) {
        this.voucherID = voucherID;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isVouchersStatusIsFilled() {
        return vouchersStatusIsFilled;
    }

    public void setVouchersStatusIsFilled(boolean vouchersStatusIsFilled) {
        this.vouchersStatusIsFilled = vouchersStatusIsFilled;
    }

    public double getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(double voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    @Override
    public String toString() {
        return "OrangeGiftsInquiryRepresentation{" + "validationId=" + validationId + ", expirationDate=" + expirationDate + ", requestID=" + requestID + ", voucherID=" + voucherID + ", result=" + result + ", vouchersStatusIsFilled=" + vouchersStatusIsFilled + ", voucherAmount=" + voucherAmount + '}';
    }

}
