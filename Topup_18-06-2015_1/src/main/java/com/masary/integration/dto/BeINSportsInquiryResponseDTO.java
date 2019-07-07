package com.masary.integration.dto;

public class BeINSportsInquiryResponseDTO {

	    private String validationId;
	    private long expirationDate;
	    private String clientName;
	    private String billNumber;
	    private Double fees;
	    private Double minAmount;
	    private String ePayBillRecID;
	    private String sequence;
	    private String curCode;
	    private String referenceNumber;
	    private String billRefInfo;
	    private String msisdn;
	    private Double amount;
	    private String programName;
	    private String coverageEndDate;

	    private double masaryCommission;
	    private double merchantCommission;
	    private long fromAccountId;
	    private String serviceName;
	    private String providerCategory;
	    private long ratePlanId;
	    private double appliedFees ;
	    private double tax;
	    private double transactionAmount ;
	    private double toBepaid;
	    private double serviceAmount;
	    
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
		public String getClientName() {
			return clientName;
		}
		public void setClientName(String clientName) {
			this.clientName = clientName;
		}
		public String getBillNumber() {
			return billNumber;
		}
		public void setBillNumber(String billNumber) {
			this.billNumber = billNumber;
		}
		public Double getFees() {
			return fees;
		}
		public void setFees(Double fees) {
			this.fees = fees;
		}
		public Double getMinAmount() {
			return minAmount;
		}
		public void setMinAmount(Double minAmount) {
			this.minAmount = minAmount;
		}
		public String getePayBillRecID() {
			return ePayBillRecID;
		}
		public void setePayBillRecID(String ePayBillRecID) {
			this.ePayBillRecID = ePayBillRecID;
		}
		public String getSequence() {
			return sequence;
		}
		public void setSequence(String sequence) {
			this.sequence = sequence;
		}
		public String getCurCode() {
			return curCode;
		}
		public void setCurCode(String curCode) {
			this.curCode = curCode;
		}
		public String getReferenceNumber() {
			return referenceNumber;
		}
		public void setReferenceNumber(String referenceNumber) {
			this.referenceNumber = referenceNumber;
		}
		public String getBillRefInfo() {
			return billRefInfo;
		}
		public void setBillRefInfo(String billRefInfo) {
			this.billRefInfo = billRefInfo;
		}
		public String getMsisdn() {
			return msisdn;
		}
		public void setMsisdn(String msisdn) {
			this.msisdn = msisdn;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public String getProgramName() {
			return programName;
		}
		public void setProgramName(String programName) {
			this.programName = programName;
		}
		public String getCoverageEndDate() {
			return coverageEndDate;
		}
		public void setCoverageEndDate(String coverageEndDate) {
			this.coverageEndDate = coverageEndDate;
		}
		public double getMasaryCommission() {
			return masaryCommission;
		}
		public void setMasaryCommission(double masaryCommission) {
			this.masaryCommission = masaryCommission;
		}
		public double getMerchantCommission() {
			return merchantCommission;
		}
		public void setMerchantCommission(double merchantCommission) {
			this.merchantCommission = merchantCommission;
		}
		public long getFromAccountId() {
			return fromAccountId;
		}
		public void setFromAccountId(long fromAccountId) {
			this.fromAccountId = fromAccountId;
		}
		public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		public String getProviderCategory() {
			return providerCategory;
		}
		public void setProviderCategory(String providerCategory) {
			this.providerCategory = providerCategory;
		}
		public long getRatePlanId() {
			return ratePlanId;
		}
		public void setRatePlanId(long ratePlanId) {
			this.ratePlanId = ratePlanId;
		}
		public double getAppliedFees() {
			return appliedFees;
		}
		public void setAppliedFees(double appliedFees) {
			this.appliedFees = appliedFees;
		}
		public double getTax() {
			return tax;
		}
		public void setTax(double tax) {
			this.tax = tax;
		}
		public double getTransactionAmount() {
			return transactionAmount;
		}
		public void setTransactionAmount(double transactionAmount) {
			this.transactionAmount = transactionAmount;
		}
		public double getToBepaid() {
			return toBepaid;
		}
		public void setToBepaid(double toBepaid) {
			this.toBepaid = toBepaid;
		}
		public double getServiceAmount() {
			return serviceAmount;
		}
		public void setServiceAmount(double serviceAmount) {
			this.serviceAmount = serviceAmount;
		}
		
}
