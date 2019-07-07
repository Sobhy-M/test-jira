/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

import java.util.List;

/**
 *
 * @author Mustafa
 */
public class ElectricityInquiryResponse {

	private String accountNumber;
	private String clientName;
	private String serviceId;
	private String address;
	private double masaryCommission;
	private double merchantCommission;
	private long fromAccountId;
	private String serviceName;
	private String providerCategory;
	private long ratePlanId;
	private double appliedFees;
	private double tax;
	private double transactionAmount;
	private String billDate;
	private double toBepaid;
	private String billNumber;
	private List<ElectricityBillsRepresentation> bills;
	private double billAmount;

	public String getAccountNumber() {
		return accountNumber;
	}

	public List<ElectricityBillsRepresentation> getBills() {
		return bills;
	}

	public void setBills(List<ElectricityBillsRepresentation> bills) {
		this.bills = bills;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public double getToBepaid() {
		return toBepaid;
	}

	public void setToBepaid(double toBepaid) {
		this.toBepaid = toBepaid;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

}
