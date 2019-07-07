package com.masary.controlers.SECI;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;


public class SECIHome extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nextJSP = "";
		try {

			nextJSP = CONFIG.PAGE_SECI;

		} catch (Exception e) {
			MasaryManager.logger.info("Error:- " + e.getStackTrace());
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;
		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
