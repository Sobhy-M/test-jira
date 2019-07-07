package com.masary.controlers.OrangeTopup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masary.common.CONFIG;
import com.masary.controlers.component.OrangeTopupComponent;

/**
 * Servlet implementation class OrangeTopupConfirmation
 */

public class OrangeTopupConfirmation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrangeTopupConfirmation() {
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

			OrangeTopupComponent orangeTopupComponent = new OrangeTopupComponent();
			nextJSP = orangeTopupComponent.getConfirmationJsp(request); 

		} catch (Exception e) {

			nextJSP = CONFIG.PAGE_ERRPR;
			request.getSession().setAttribute("ErrorMessage",
					OrangeTopupProperties.getErrorDuringTransaction(request.getSession()));

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
