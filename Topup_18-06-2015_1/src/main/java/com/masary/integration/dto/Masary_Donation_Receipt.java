package com.masary.integration.dto;

public class Masary_Donation_Receipt {
	 private int TransactionId;
	    private String transaction_date;
	    public int getTransactionId() {
			return TransactionId;
		}
		public void setTransactionId(int transactionId) {
			TransactionId = transactionId;
		}
		public String getTransaction_date() {
			return transaction_date;
		}
		public void setTransaction_date(String transaction_date) {
			this.transaction_date = transaction_date;
		}
		public int getCustomerPayer() {
			return CustomerPayer;
		}
		public void setCustomerPayer(int customerPayer) {
			CustomerPayer = customerPayer;
		}
		public int getCustomerPayed() {
			return CustomerPayed;
		}
		public void setCustomerPayed(int customerPayed) {
			CustomerPayed = customerPayed;
		}
		public int getAmount() {
			return Amount;
		}
		public void setAmount(int amount) {
			Amount = amount;
		}
		public int getTrxStatusId() {
			return TrxStatusId;
		}
		public void setTrxStatusId(int trxStatusId) {
			TrxStatusId = trxStatusId;
		}
		public int getServiceId() {
			return ServiceId;
		}
		public void setServiceId(int serviceId) {
			ServiceId = serviceId;
		}
		private int CustomerPayer;
	    private int CustomerPayed;
	    private int Amount;
	    private int TrxStatusId;
	    private int ServiceId;
	    

}
