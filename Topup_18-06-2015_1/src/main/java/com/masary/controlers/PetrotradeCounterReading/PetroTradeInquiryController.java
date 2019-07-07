package com.masary.controlers.PetrotradeCounterReading;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;

/**
 * Servlet implementation class PetroTradeInquiryController
 */
public class PetroTradeInquiryController extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String nextJSP = "";
		String action;
		action = request.getParameter(CONFIG.PARAM_ACTION);
		int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
		session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
		try {

			if (!isLogin(session)) {
				if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
					nextJSP = CONFIG.PAGE_LOGIN;
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
					dispatcher.forward(request, response);
					return;
				}
				session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
			}
			session.setAttribute("ErrorCode", "");

			nextJSP = CONFIG.PAGE_PETROTRADE_CounterReading_Inquiry;

		} catch (Exception e) {
			MasaryManager.logger.info("Error:- " + e.getStackTrace());
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;
		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);

			out.close();
		}
	}

	private boolean isLogin(HttpSession session) {
		return session.getAttribute(CONFIG.PARAM_PIN) != null;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public String getServletInfo() {
		return "Short description";
	}

}
