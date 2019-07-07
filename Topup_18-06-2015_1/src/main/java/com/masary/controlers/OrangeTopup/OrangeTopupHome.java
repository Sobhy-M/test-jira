package com.masary.controlers.OrangeTopup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.impl.client.HttpClients;
import com.masary.common.CONFIG;
import com.masary.integration.OrangeTopupHttpClient;
import com.masary.integration.dto.TopupDenominationDTO;

/**
 * Servlet implementation class OrangeTopupHome
 */
public class OrangeTopupHome extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrangeTopupHome() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextJSP = "";
		try {
			OrangeTopupUtils topupUtils = new OrangeTopupUtils();
			
			topupUtils.checkDenominationsAvaliablity(request);
			nextJSP = OrangeTopupProperties.orangeTopupHome;
			
		} catch (Exception e) {
			nextJSP = CONFIG.PAGE_ERRPR;
			request.getSession().setAttribute("ErrorMessage", CONFIG.serviceIsInactiveMessageِAr);
		}

		request.getRequestDispatcher(nextJSP).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}


}
