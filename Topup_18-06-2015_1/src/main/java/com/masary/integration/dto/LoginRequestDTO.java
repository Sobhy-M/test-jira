package com.masary.integration.dto;


public class LoginRequestDTO {

	 private String customerID;
	    private String password;
	    private String loginInfo;
	    private String lang;
	    private String appRoot;

	    public String getCustomerID()
	    {
		return customerID;
	    }

	    public void setCustomerID(String customerID)
	    {
		this.customerID = customerID;
	    }

	    public String getPassword()
	    {
		return password;
	    }

	    public void setPassword(String password)
	    {
		this.password = password;
	    }

	    public String getLoginInfo()
	    {
		return loginInfo;
	    }

	    public void setLoginInfo(String loginInfo)
	    {
		this.loginInfo = loginInfo;
	    }

	    public String getLang()
	    {
		return lang;
	    }

	    public void setLang(String lang)
	    {
		this.lang = lang;
	    }

	    public String getAppRoot()
	    {
		return appRoot;
	    }
	    
	    public void setAppRoot(String appRoot)
	    {
		this.appRoot = appRoot;
	    }
}
