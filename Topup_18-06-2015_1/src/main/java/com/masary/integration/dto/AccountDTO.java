/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration.dto;

/**
 *
 * @author Nourhan
 */
import com.google.common.base.Preconditions;

public class AccountDTO {

    private String accountId;
    private String customerId;

    public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	private Long ratePlanId;
    private String accountName;
    private String accountType;
    private Long accountCategory;
    private String accountStatus;
    private String mobileNumber;
    private String password;
    private String oldPassword;
    private int sessionTimeOut;
    public int getSessionTimeOut() {
		return sessionTimeOut;
	}

	public void setSessionTimeOut(int sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	private String userName;
    private String address;
    private String country;
    private String governerate;
    private String postalNumber;
    private String alternativeMobile;
    private String landline;
    private String email;
    private String birthDate;
    private String nationalId;
    private String question;
    private String answer;
    private String parent;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGovernerate() {
        return governerate;
    }

    public void setGovernerate(String governerate) {
        this.governerate = governerate;
    }

    public String getPostalNumber() {
        return postalNumber;
    }

    public void setPostalNumber(String postalNumber) {
        this.postalNumber = postalNumber;
    }

    public String getAlternativeMobile() {
        return alternativeMobile;
    }

    public void setAlternativeMobile(String alternativeMobile) {
        this.alternativeMobile = alternativeMobile;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Long ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(Long accountCategory) {
        this.accountCategory = accountCategory;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getmobileNumber() {
        return mobileNumber;
    }

    public void setmobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean checkNullParamters() {
        try {
            Preconditions.checkNotNull(this.getAccountId());
            Preconditions.checkNotNull(this.getRatePlanId());
            Preconditions.checkNotNull(this.getAccountName());
            Preconditions.checkNotNull(this.getAccountType());
            Preconditions.checkNotNull(this.getAccountCategory());
            Preconditions.checkNotNull(this.getAccountStatus());
            Preconditions.checkNotNull(this.getmobileNumber());
            Preconditions.checkNotNull(this.getUserName());
            Preconditions.checkNotNull(this.getPassword());
        } catch (NullPointerException ex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AccountDTO [accountId=" + accountId + ", ratePlanId=" + ratePlanId + ", accountName=" + accountName
                + ", accountType=" + accountType + ", accountCategory=" + accountCategory + ", accountStatus="
                + accountStatus + ", phoneNumber=" + mobileNumber + "]";
    }

}
