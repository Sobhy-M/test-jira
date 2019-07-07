/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

import java.io.Serializable;

/**
 *
 * @author Melad
 */
public class TransactionDTO implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	String transId;
	String customerPayerName;
	String customerPayedName;
	double amount;
	String date;
	String status;
	String type;
	String sellType;
	double commession;
	double fees;
	double orignal_amount;
	int printedNo;
	int isBill;
	int trxTypeID;
	double total_balance;
	long ledgerID;

	public TransactionDTO() {

	}

	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	public double getFees() {
		return fees;
	}

	public double getTotal_balance() {
		return total_balance;
	}

	public void setTotal_balance(double total_balance) {
		this.total_balance = total_balance;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public double getOrignal_amount() {
		return orignal_amount;
	}

	public void setOrignal_amount(double orignal_amount) {
		this.orignal_amount = orignal_amount;
	}

	public double getCommession() {
		return commession;
	}

	public void setCommession(double commession) {
		this.commession = commession;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public long getLedgerID() {
		return ledgerID;
	}

	public void setLedgerID(long ledgerID) {
		this.ledgerID = ledgerID;
	}

	public TransactionDTO(String transId, String customerPayerName, String customerPayedName, double amount,
			String date, String status, String type, double commession) {
		this.transId = transId;
		this.customerPayerName = customerPayerName;
		this.customerPayedName = customerPayedName;
		this.amount = amount;
		this.date = date;
		this.status = status;
		this.type = type;
		this.commession = commession;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCustomerPayedName() {
		return customerPayedName;
	}

	public void setCustomerPayedName(String customerPayedName) {
		this.customerPayedName = customerPayedName;
	}

	public String getCustomerPayerName() {
		return customerPayerName;
	}

	public void setCustomerPayerName(String customerPayerName) {
		this.customerPayerName = customerPayerName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public int getPrintedNo() {
		return printedNo;
	}

	public void setPrintedNo(int printedNo) {
		this.printedNo = printedNo;
	}

	public int getIsBill() {
		return isBill;
	}

	public void setIsBill(int isBill) {
		this.isBill = isBill;
	}

	public int getTrxTypeID() {
		return trxTypeID;
	}

	public void setTrxTypeID(int trxTypeID) {
		this.trxTypeID = trxTypeID;
	}
}
