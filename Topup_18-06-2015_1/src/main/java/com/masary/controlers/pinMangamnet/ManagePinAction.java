package com.masary.controlers.pinMangamnet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.masary.common.CONFIG;
import com.masary.exceptions.LockBalanceCallerException;
import com.masary.integration.dto.ChangePinRequestDTO;

/**
 * Servlet implementation class ManagePinAction
 */
public class ManagePinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagePinAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getNextJSP(request));
		dispatcher.forward(request, response);
	}

	private String getNextJSP(HttpServletRequest request) {

		ManagePinComponent component = new ManagePinComponent();

		String lang = request.getSession().getAttribute("lang").toString();
		String token = request.getSession().getAttribute("Token").toString();
		String ip = request.getRemoteAddr();
		String newStatus = request.getParameter("newStatus");
		String pinCode = request.getParameter("pinCodehidden");

		ChangePinRequestDTO requestDTO = new ChangePinRequestDTO(pinCode, newStatus, ip, token, lang);

		try {
			component.changePinStatus(requestDTO);
			return PinManagmentProperties.PIN_CHANGED_JSP;
		} catch (LockBalanceCallerException e) {
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			return CONFIG.PAGE_ERRPR;
		} catch (Exception e) {
			if (lang.equals("ar")) {
				request.getSession().setAttribute("ErrorMessage", CONFIG.transactionErrorar);
			} else {
				request.getSession().setAttribute("ErrorMessage", CONFIG.transactionError);
			}
			return CONFIG.PAGE_ERRPR;
		}

	}

}
