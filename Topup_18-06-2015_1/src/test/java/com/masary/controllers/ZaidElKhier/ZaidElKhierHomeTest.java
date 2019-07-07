package com.masary.controllers.ZaidElKhier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.masary.common.CONFIG;

public class ZaidElKhierHomeTest
{

	@Test
	public void testDoGet_ValidParameters_ForwardRequestToHomePage() throws Exception
	{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		ServletConfig servletConfig = mock(ServletConfig.class);
		Logger logger = Logger.getLogger(this.getClass());
				
		when(request.getSession()).thenReturn(session);

		when(session.getAttribute(CONFIG.PARAM_PIN)).thenReturn(true);
		when(session.getAttribute(CONFIG.lang)).thenReturn("en");
//		when(MasaryManager.logger).thenReturn(logger);

//		Mockito.doNothing().when(MasaryManager.logger.info(anyString()));
		
		ZaidElKhierHome zaidElKhierHome = new ZaidElKhierHome();
		zaidElKhierHome.init(servletConfig);
		zaidElKhierHome.doPost(request, response);
		
		verify(dispatcher).forward(request, response);
	}
}
