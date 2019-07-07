package com.masary.controlers.pinMangamnet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.masary.common.CONFIG;
import com.masary.database.dto.AddNewPinRequestDTO;
import com.masary.exceptions.LockBalanceCallerException;

/**
 * Servlet implementation class CreatePinConfirmation
 */
public class CreatePinConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreatePinConfirmation() {
		super();
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
		String newPinCode = request.getParameter("newPinCodehidden");
		String newPinCodeConfirmation = request.getParameter("newPinCodeConfirmationHidden");

		if (!newPinCode.equals(newPinCodeConfirmation)) {
			request.getSession().setAttribute("ErrorMessage",
					PinManagmentProperties.getPinAndConfirmationNotEqual(request.getSession()));
			return CONFIG.PAGE_ERRPR;
		}

		AddNewPinRequestDTO requestDTO = new AddNewPinRequestDTO(newPinCode, newPinCodeConfirmation, token, ip, lang);

		try {
			component.addNewPin(requestDTO);
			return PinManagmentProperties.PIN_CREATED_JSP;
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
