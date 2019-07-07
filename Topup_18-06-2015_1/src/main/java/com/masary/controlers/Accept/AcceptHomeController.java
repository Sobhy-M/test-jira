package com.masary.controlers.Accept;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;

public class AcceptHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String nextJSP = "";
		String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);
		String operationType = 	new String(request.getParameter(CONFIG.PARAM_OPERATIONTYPE).toString().getBytes("ISO-8859-1"), "utf-8");
		try {

			if (!isLogin(session)) {

				nextJSP = CONFIG.PAGE_LOGIN;
				session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
				dispatcher.forward(request, response);
				return;
			}
			session.setAttribute("serviceId", serviceId);
			session.setAttribute("operationType", operationType);
			nextJSP = CONFIG.PAGE_ACCEPT_Inquiry;

		} catch (Exception e) {
			MasaryManager.logger.info("Error:- " + e.getStackTrace());
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;

		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}
	}

	private boolean isLogin(HttpSession session) {
		return session.getAttribute(CONFIG.PARAM_PIN) != null;
	}

}
