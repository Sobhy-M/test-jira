package com.masary.controlers.BeinSports;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;

public class BeinSportsHomeController extends HttpServlet {

private static final long serialVersionUID = 1L;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		    response.setContentType("text/html;charset=UTF-8");
	        HttpSession session = request.getSession();
		    String nextJSP = "";

		try {
			 if (!isLogin(session)) {

				    nextJSP = CONFIG.PAGE_LOGIN;
	                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
	                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
	                dispatcher.forward(request, response);
	                return;
	            }
			  
			    nextJSP = CONFIG.PAGE_beINSports_Home;

	            
		} catch (Exception e) {
			MasaryManager.logger.info("Error:- " + e.getStackTrace());
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;

		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	 private boolean isLogin(HttpSession session) {
	        return session.getAttribute(CONFIG.PARAM_PIN) != null;
	    }



}
