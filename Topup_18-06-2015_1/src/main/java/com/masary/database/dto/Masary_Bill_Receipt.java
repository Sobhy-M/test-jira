/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author KEMO
 */
public class Masary_Bill_Receipt {

    private int Customer_id;
    private String transaction_date;
    private long Transaction_id;
    private int BTC;
    private String phone;
    private String Due_date;
    private double Amount;
    private double fees;
    private String customerName;
    private String globalTrxID;
    private double toBePaid;
    private String LedgerTrxId;
    private int currentReading;
    private String subscriberName;
    private double tax;
    private String subscriberNumber;
    private Bill_Response providerResponse;
    private double billsNumber;
    private String elecNo;
    private String billingRunName;
    private double merchantCommission;
    private double transactionAmount;
    private double totalDueAmount;
    private int accountId;
	private String payment_type_name;

	public String getPayment_type_name() {
		return payment_type_name;
	}

	public void setPayment_type_name(String payment_type_name) {
		this.payment_type_name = payment_type_name;
	}


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(double totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }
    
    public double getMerchantCommission() {
        return merchantCommission;
    }

    public void setMerchantCommission(double merchantCommission) {
        this.merchantCommission = merchantCommission;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getBillingRunName() {
        return billingRunName;
    }

    public void setBillingRunName(String billingRunName) {
        this.billingRunName = billingRunName;
    }

    public String getElecNo() {
        return elecNo;
    }

    public void setElecNo(String elecNo) {
        this.elecNo = elecNo;
    }

    public int getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(int currentReading) {
        this.currentReading = currentReading;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getGlobalTrxID() {
        return globalTrxID;
    }

    public void setGlobalTrxID(String globalTrxID) {
        this.globalTrxID = globalTrxID;
    }

    public Double getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(Double toBePaid) {
        this.toBePaid = toBePaid;
    }

    public String getLedgerTrxId() {
        return LedgerTrxId;
    }

    public void setLedgerTrxId(String ledgerTrxId) {
        LedgerTrxId = ledgerTrxId;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public double getBillsNumber() {
        return billsNumber;
    }

    public void setBillsNumber(double billsNumber) {
        this.billsNumber = billsNumber;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public Bill_Response getProviderResponse() {
        return providerResponse;
    }

    public void setProviderResponse(Bill_Response providerResponse) {
        this.providerResponse = providerResponse;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public int getBTC() {
        return BTC;
    }

    public void setBTC(int BTC) {
        this.BTC = BTC;
    }

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int Customer_id) {
        this.Customer_id = Customer_id;
    }

    public String getDue_date() {
        return Due_date;
    }

    public void setDue_date(String Due_date) {
        this.Due_date = Due_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public long getTransaction_id() {
        return Transaction_id;
    }

    public void setTransaction_id(long Transaction_id) {
        this.Transaction_id = Transaction_id;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Masary_Bill_Receipt{" + "Customer_id=" + Customer_id + ", Transaction_date=" + transaction_date + ", Transaction_id=" + Transaction_id + ", BTC=" + BTC + ", Phone=" + phone + ", Due_date=" + Due_date + ", Amount=" + Amount + ", fees=" + fees + ", customerName=" + customerName + ", providerResponse=" + providerResponse + '}';
    }

}
