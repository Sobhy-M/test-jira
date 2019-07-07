package com.masary.controlers;

public class LoginComponentBuilder
{
	private LoginAction loginAction;
	private UserLoginCheckAction userLoginCheckAction;
	private LogoutAction logoutAction;
	private ResetPasswordAction resetPasswordAction;
	
	public LoginComponentBuilder()
	{
		
	}
	
	public LoginComponentBuilder withLoginAction(LoginAction loginAction)
	{
		this.loginAction = loginAction;
		return this;
	}
	
	public LoginComponentBuilder withUserLoginCheckAction(UserLoginCheckAction userLoginCheckAction)
	{
		this.userLoginCheckAction = userLoginCheckAction;
		return this;
	}
	
	public LoginComponentBuilder withLogoutAction(LogoutAction logoutAction)
	{
		this.logoutAction = logoutAction;
		return this;
	}
	
	public LoginComponentBuilder withResetPasswordAction(ResetPasswordAction resetPasswordAction)
	{
		this.resetPasswordAction = resetPasswordAction;
		return this;
	}
	
	public LoginComponent build()
	{
		return new LoginComponent(loginAction, userLoginCheckAction, logoutAction, resetPasswordAction);
	}
	
}