package com.masary.controlers.pinMangamnet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.masary.common.CONFIG;
import com.masary.exceptions.LockBalanceCallerException;
import com.masary.integration.dto.GetPinStatusRepresentation;

/**
 * Servlet implementation class ManagePinHome
 */
public class ManagePinHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagePinHome() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getNextJSP(request));
		dispatcher.forward(request, response);
	}

	private String getNextJSP(HttpServletRequest request) {

		ManagePinComponent component = new ManagePinComponent();

		String lang = request.getSession().getAttribute("lang").toString();
		String token = request.getSession().getAttribute("Token").toString();
		String ip = request.getRemoteAddr();
		try {
			GetPinStatusRepresentation pinStatusRepresentation = component.getPinStatus(lang, token, ip);
			if (pinStatusRepresentation.getPinStatus().equals("LOCKED")
					|| pinStatusRepresentation.getPinStatus().equals("UNLOCKED")) {
				request.setAttribute("Status", pinStatusRepresentation.getPinStatus());
				return PinManagmentProperties.MANAGE_PIN_STATUS;
			} else {
				request.getSession().setAttribute("ErrorMessage",
						PinManagmentProperties.getPinCodeNotCreated(request.getSession()));
				return CONFIG.PAGE_ERRPR;
			}

		} catch (LockBalanceCallerException e) {
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			return CONFIG.PAGE_ERRPR;
		} catch (Exception e) {

			if (lang.equals("AR")) {
				request.getSession().setAttribute("ErrorMessage", CONFIG.transactionErrorar);
			} else {
				request.getSession().setAttribute("ErrorMessage", CONFIG.transactionError);
			}
			return CONFIG.PAGE_ERRPR;
		}

	}
}
