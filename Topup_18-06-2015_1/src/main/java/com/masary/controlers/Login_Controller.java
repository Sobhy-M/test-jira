package com.masary.controlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.masary.utils.HttpServletRequestProxy;

/**
 *
 * @author omnya
 */
public class Login_Controller extends HttpServlet
{

	private static final long serialVersionUID = -9125847378196827425L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		LoginComponent loginComponent = new LoginComponentBuilder()
				.withLogoutAction(new LogoutAction()).withResetPasswordAction(new ResetPasswordAction())
				.withUserLoginCheckAction(new UserLoginCheckAction()).build();

		loginComponent.processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	    
		LoginComponent loginComponent = new LoginComponentBuilder().withLoginAction(new LoginAction())
				.withLogoutAction(new LogoutAction()).withResetPasswordAction(new ResetPasswordAction())
				.withUserLoginCheckAction(new UserLoginCheckAction()).build();

		HttpServletRequestProxy httpServletRequestProxy = new HttpServletRequestProxy();
		httpServletRequestProxy.setRequest(request);
		
		loginComponent.processRequest(httpServletRequestProxy, response);
	}
	
}