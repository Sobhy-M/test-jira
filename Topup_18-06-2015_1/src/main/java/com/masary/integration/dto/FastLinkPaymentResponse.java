package com.masary.integration.dto;

public class FastLinkPaymentResponse {
	  private String clientName;
	    private String phoneNumber;
	    private long insertDate;
	    private long startDate;
	    private long endDate;
	    private double billAmount;
	    private double appliedFees;
	    private double merchantCommission;
	    private double tax;
	    private double toBepaid;
	    private String requestStatus;
	    private int accountId;
	    private String deviceType;
	    private String globalTrxId;
	    private double transactionAmount;
	    private long ledgerTrxId;
	    private int ratePlanId;
		private double appliedFeesAddedTax;

	    public double getAppliedFeesAddedTax() {
			return (Math.floor(appliedFeesAddedTax*100)/100);

		}

		public void setAppliedFeesAddedTax(double appliedFeesAddedTax) {
			this.appliedFeesAddedTax = appliedFeesAddedTax;
		}

		public String getClientName() {
	        return clientName;
	    }

	    public void setClientName(String clientName) {
	        this.clientName = clientName;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public long getInsertDate() {
	        return insertDate;
	    }

	    public void setInsertDate(long insertDate) {
	        this.insertDate = insertDate;
	    }

	    public long getStartDate() {
	        return startDate;
	    }

	    public void setStartDate(long startDate) {
	        this.startDate = startDate;
	    }

	    public long getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(long endDate) {
	        this.endDate = endDate;
	    }

	    public double getBillAmount() {
			return (Math.floor(billAmount*100)/100);

	    }

	    public void setBillAmount(double billAmount) {
	        this.billAmount = billAmount;
	    }

	    public double getAppliedFees() {
			return (Math.floor(appliedFees*100)/100);

	    }

	    public void setAppliedFees(double appliedFees) {
	        this.appliedFees = appliedFees;
	    }

	    public double getMerchantCommission() {
			return (Math.floor(merchantCommission*100)/100);

	    }

	    public void setMerchantCommission(double merchantCommission) {
	        this.merchantCommission = merchantCommission;
	    }

	    public double getTax() {
			return (Math.floor(tax*100)/100);

	    }

	    public void setTax(double tax) {
	        this.tax = tax;
	    }

	    public double getToBepaid() {
			return (Math.floor(toBepaid*100)/100);

	    }

	    public void setToBepaid(double toBepaid) {
	        this.toBepaid = toBepaid;
	    }

	    public String getRequestStatus() {
	        return requestStatus;
	    }

	    public void setRequestStatus(String requestStatus) {
	        this.requestStatus = requestStatus;
	    }

	    public int getAccountId() {
	        return accountId;
	    }

	    public void setAccountId(int accountId) {
	        this.accountId = accountId;
	    }

	    public String getDeviceType() {
	        return deviceType;
	    }

	    public void setDeviceType(String deviceType) {
	        this.deviceType = deviceType;
	    }

	    public String getGlobalTrxId() {
	        return globalTrxId;
	    }

	    public void setGlobalTrxId(String globalTrxId) {
	        this.globalTrxId = globalTrxId;
	    }

	    public double getTransactionAmount() {
			return (Math.floor(transactionAmount*100)/100);

	    }

	    public void setTransactionAmount(double transactionAmount) {
	        this.transactionAmount = transactionAmount;
	    }

	    public long getLedgerTrxId() {
	        return ledgerTrxId;
	    }

	    public void setLedgerTrxId(long ledgerTrxId) {
	        this.ledgerTrxId = ledgerTrxId;
	    }

	    public int getRatePlanId() {
	        return ratePlanId;
	    }

	    public void setRatePlanId(int ratePlanId) {
	        this.ratePlanId = ratePlanId;
	    }

	    

	}
