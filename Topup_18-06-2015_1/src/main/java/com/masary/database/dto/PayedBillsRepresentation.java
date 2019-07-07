/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.masary.database.dto;

import java.util.Date;

/**
 *
 * @author user
 */
public class PayedBillsRepresentation extends BaseRepresentationObject{
   private long billInquiryId;

	private long inboundExecutionId;

	private long outboundExecutionId;

	private ProviderRepresentation providerId;

	private String participantNumber;

	private String participantName;

	private String issueDate;

	private double billValue;

	private long paymentDate;

	private long leadgerTransactionId;

	private String globalTransactionId;
	
	private int status;


        
    /**
     * @return the inboundExecutionId
     */
    public long getInboundExecutionId() {
        return inboundExecutionId;
    }

    /**
     * @param inboundExecutionId the inboundExecutionId to set
     */
    public void setInboundExecutionId(long inboundExecutionId) {
        this.inboundExecutionId = inboundExecutionId;
    }

    /**
     * @return the outboundExecutionId
     */
    public long getOutboundExecutionId() {
        return outboundExecutionId;
    }

    /**
     * @param outboundExecutionId the outboundExecutionId to set
     */
    public void setOutboundExecutionId(long outboundExecutionId) {
        this.outboundExecutionId = outboundExecutionId;
    }

    /**
     * @return the providerId
     */
    public ProviderRepresentation getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(ProviderRepresentation providerId) {
        this.providerId = providerId;
    }

    /**
     * @return the participantNumber
     */
    public String getParticipantNumber() {
        return participantNumber;
    }

    /**
     * @param participantNumber the participantNumber to set
     */
    public void setParticipantNumber(String participantNumber) {
        this.participantNumber = participantNumber;
    }

    /**
     * @return the participantName
     */
    public String getParticipantName() {
        return participantName;
    }

    /**
     * @param participantName the participantName to set
     */
    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    /**
     * @return the issueDate
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * @param issueDate the issueDate to set
     */
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @return the billValue
     */
    public double getBillValue() {
        return billValue;
    }

    /**
     * @param billValue the billValue to set
     */
    public void setBillValue(double billValue) {
        this.billValue = billValue;
    }

    /**
     * @return the leadgerTransactionId
     */
    public long getLeadgerTransactionId() {
        return leadgerTransactionId;
    }

    /**
     * @param leadgerTransactionId the leadgerTransactionId to set
     */
    public void setLeadgerTransactionId(long leadgerTransactionId) {
        this.leadgerTransactionId = leadgerTransactionId;
    }

    /**
     * @return the globalTransactionId
     */
    public String getGlobalTransactionId() {
        return globalTransactionId;
    }

    /**
     * @param globalTransactionId the globalTransactionId to set
     */
    public void setGlobalTransactionId(String globalTransactionId) {
        this.globalTransactionId = globalTransactionId;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the billInquiryId
     */
    public long getBillInquiryId() {
        return billInquiryId;
    }

    /**
     * @param billInquiryId the billInquiryId to set
     */
    public void setBillInquiryId(long billInquiryId) {
        this.billInquiryId = billInquiryId;
    }

    /**
     * @return the paymentDate
     */
    public long getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(long paymentDate) {
        this.paymentDate = paymentDate;
    }

   

}
