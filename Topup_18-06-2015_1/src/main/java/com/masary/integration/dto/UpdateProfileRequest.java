package com.masary.integration.dto;

public class UpdateProfileRequest {
	 private String customerID;
	    private String newPassword;
	    private String securityQuestion;
	    private String answer;
	    private int sessionTime;
	    private String oldPassword;

	    public String getCustomerID()
	    {
		return customerID;
	    }

	    public void setCustomerID(String customerID)
	    {
		this.customerID = customerID;
	    }

	    public String getNewPassword()
	    {
		return newPassword;
	    }
	    
	    public void setNewPassword(String newPassword)
	    {
		this.newPassword = newPassword;
	    }

	    public String getSecurityQuestion()
	    {
		return securityQuestion;
	    }

	    public void setSecurityQuestion(String securityQuestion)
	    {
		this.securityQuestion = securityQuestion;
	    }

	    public String getAnswer()
	    {
		return answer;
	    }

	    public void setAnswer(String answer)
	    {
		this.answer = answer;
	    }

	    public int getSessionTime()
	    {
		return sessionTime;
	    }

	    public void setSessionTime(int sessionTime)
	    {
		this.sessionTime = sessionTime;
	    }
	    
	    public String getOldPassword()
	    {
		return oldPassword;
	    }
	    
	    public void setOldPassword(String oldPassword)
	    {
		this.oldPassword = oldPassword;
	    }

}
