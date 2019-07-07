package com.masary.integration.dto;

public class OrangeGiftsPaymentRepresentation {

    private long ledgerTrxId;
    private long accountId;
    private long amount;
    private String date;
    private String time;
    private String voucherID;
    private String globalTrxId;
    private String phoneNumber;
    private String requestStatus;

    public long getLedgerTrxId() {
        return ledgerTrxId;
    }

    public void setLedgerTrxId(long ledgerTrxId) {
        this.ledgerTrxId = ledgerTrxId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public String getGlobalTrxId() {
        return globalTrxId;
    }

    public void setGlobalTrxId(String globalTrxId) {
        this.globalTrxId = globalTrxId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OrangeGiftsPaymentRepresentation{" + "ledgerTrxId=" + ledgerTrxId + ", accountId=" + accountId + ", amount=" + amount + ", date=" + date + ", time=" + time + ", voucherID=" + voucherID + ", globalTrxId=" + globalTrxId + ", phoneNumber=" + phoneNumber + ", requestStatus=" + requestStatus + '}';
    }

}
