/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.dto;

/**
 *
 * @author Ahmed Saeed
 */
public class ELectricityBillInquiry {

    private long billInquiryId;

    private String participantNumber;

    private String participantName;

    private String issueDate;

    private double billValue;

    private long inboundExecutionId;

    private long providerId;

    String fees;

    public ELectricityBillInquiry() {
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public long getBillInquiryId() {
        return billInquiryId;
    }

    public void setBillInquiryId(long billInquiryId) {
        this.billInquiryId = billInquiryId;
    }

    public String getParticipantNumber() {
        return participantNumber;
    }

    public void setParticipantNumber(String participantNumber) {
        this.participantNumber = participantNumber;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public double getBillValue() {
        return billValue;
    }

    public void setBillValue(double billValue) {
        this.billValue = billValue;
    }

    public long getInboundExecutionId() {
        return inboundExecutionId;
    }

    public void setInboundExecutionId(long inboundExecutionId) {
        this.inboundExecutionId = inboundExecutionId;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }
}
