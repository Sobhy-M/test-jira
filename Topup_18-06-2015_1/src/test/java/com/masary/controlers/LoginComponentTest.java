package com.masary.controlers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.masary.database.manager.BaseManger;
import com.masary.database.manager.MasaryManager;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoginComponent.class, BaseManger.class, MasaryManager.class})
@PowerMockIgnore("javax.net.ssl.*")
public class LoginComponentTest extends TestCase
{
	@BeforeClass
    public static void oneTimeSetup() {
        System.setProperty("log4j.defaultInitOverride", Boolean.toString(true));
        System.setProperty("log4j.ignoreTCL", Boolean.toString(true));
    }
	
	@Test
	public void testProcessRequest_NotLoggedInUser_ForwardRequestToLoginPage() throws Exception
	{
		Logger logger = mock(Logger.class);

		LoginAction loginAction = new TestableLoginAction();
		UserLoginCheckAction userLoginCheckAction = new TestableUserLoginCheckAction();

		spy(BaseManger.class);
		doNothing().when(BaseManger.class, "initDataSource");
		
		Whitebox.setInternalState(MasaryManager.class, "logger", logger);
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
				
		when(request.getSession()).thenReturn(session);

		when(request.getRequestDispatcher("/Login.jsp")).thenReturn(dispatcher);
		
		LoginComponent loginComponent = new LoginComponentBuilder().withLoginAction(loginAction)
				.withUserLoginCheckAction(userLoginCheckAction).build();
		
		loginComponent.processRequest(request, response);
		
		verify(dispatcher).forward(request, response);
	}
	
	class TestableLoginAction extends LoginAction
	{
		
	}
	
	class TestableUserLoginCheckAction extends UserLoginCheckAction
	{
		@Override
		public boolean isLogin(Object customerId)
		{
			return false;
		}
	}
}