package com.masary.controlers.AbuElrish;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.masary.common.CONFIG;

public class AbuElrishConfirmationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	/*
	 * public AbuElrishConfirmationController() { super(); }
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextJSP = "";
		try {
			String amount = request.getParameter("Amount");
			String phoneNum = request.getParameter("PhoneNumber");

			if (!validateInputs(amount, phoneNum)) {
				request.getSession().setAttribute("ErrorMessage", "validateInputs");
				nextJSP = CONFIG.PAGE_ERRPR;
			} else {
				String abuelrishId = request.getParameter("abuelrish");
				if (abuelrishId.equals("1")) {
					request.setAttribute("donationType", "صدقة");
				}
				if (abuelrishId.equals("2")) {
					request.setAttribute("donationType", "زكاة");
				}
				nextJSP = CONFIG.PAGE_AbuElRish_Confirmation;
			}

		} catch (Exception e) {
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

	private boolean validateInputs(String amount, String phoneNum) {

		try {
			if (phoneNum == null || phoneNum.trim().isEmpty()) {
				return false;
			}
			double doubleAmount = Double.parseDouble(amount);
			long longAmount = (long) doubleAmount;
			if (doubleAmount > longAmount) {
				return false;
			}

		} catch (Exception ex) {
			return false;
		}
		return true;
	}
}
