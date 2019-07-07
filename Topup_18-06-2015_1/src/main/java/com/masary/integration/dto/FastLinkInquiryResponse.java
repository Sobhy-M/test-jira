package com.masary.integration.dto;

public class FastLinkInquiryResponse {
	 private String clientName;
	    private String phoneNumber;
	    private long endDate;
	    private long startDate;
	    private double billAmount;
	    private String globalTrxId;
	    private double masaryCommisssion;
	    private double merchantCommission;
	    private double appliedFees;
	    private double tax;
	    private double transactionAmount;
	    private double toBepaid;
	    private int ratePlanId;
	    private int accountId;
	    private long ledgerTrxId;
	    private String deviceType;
	    private long requestId;
	    private String requestStatus;
		private double appliedFeesAddedTax;
		private String validationId;

	    

	    public String getValidationId() {
			return validationId;
		}

		public void setValidationId(String validationId) {
			this.validationId = validationId;
		}

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


	    public String getGlobalTrxId() {
	        return globalTrxId;
	    }

	    public void setGlobalTrxId(String globalTrxId) {
	        this.globalTrxId = globalTrxId;
	    }

	    public double getMerchantCommission() {
			return(Math.floor(merchantCommission*100)/100);

	    }

	    public void setMerchantCommission(double merchantCommission) {
	        this.merchantCommission = merchantCommission;
	    }

	    public double getAppliedFees() {
			return (Math.floor(appliedFees*100)/100);

	    }

	    public void setAppliedFees(double appliedFees) {
	        this.appliedFees = appliedFees;
	    }

	    public double getTax() {
			return (Math.floor(tax*100)/100);

	    }

	    public void setTax(double tax) {
	        this.tax = tax;
	    }

	    public double getTransactionAmount() {
	    	
	    	
			return (Math.floor(transactionAmount*100)/100);

	    }

	    public void setTransactionAmount(double transactionAmount) {
	        this.transactionAmount = transactionAmount;
	    }

	    public double getToBepaid() {
			return (Math.floor(toBepaid*100)/100);

	    }

	    public void setToBepaid(double toBepaid) {
	        this.toBepaid = toBepaid;
	    }

	    public int getAccountId() {
	        return accountId;
	    }

	    public void setAccountId(int accountId) {
	        this.accountId = accountId;
	    }

	    public long getLedgerTrxId() {
	        return ledgerTrxId;
	    }

	    public void setLedgerTrxId(long ledgerTrxId) {
	        this.ledgerTrxId = ledgerTrxId;
	    }

	    public String getDeviceType() {
	        return deviceType;
	    }

	    public void setDeviceType(String deviceType) {
	        this.deviceType = deviceType;
	    }

	    public long getRequestId() {
	        return requestId;
	    }

	    public void setRequestId(long requestId) {
	        this.requestId = requestId;
	    }

	    public String getRequestStatus() {
	        return requestStatus;
	    }

	    public void setRequestStatus(String requestStatus) {
	        this.requestStatus = requestStatus;
	    }

	    public long getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(long endDate) {
	        this.endDate = endDate;
	    }

	    public long getStartDate() {
	        return startDate;
	    }

	    public void setStartDate(long startDate) {
	        this.startDate = startDate;
	    }

	    public double getBillAmount() {
			return (Math.floor(billAmount*100)/100);

	    }

	    public void setBillAmount(double billAmount) {
	        this.billAmount = billAmount;
	    }

	    public double getMasaryCommisssion() {
			return (Math.floor(masaryCommisssion*100)/100);

	    }

	    public void setMasaryCommisssion(double masaryCommisssion) {
	        this.masaryCommisssion = masaryCommisssion;
	    }

	    public int getRatePlanId() {
	        return ratePlanId;
	    }

	    public void setRatePlanId(int ratePlanId) {
	        this.ratePlanId = ratePlanId;
	    }
	    
	    
	    
	    

	}


