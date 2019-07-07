package com.masary.controlers.AbuElrish;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.AbuElRishClient;
import com.masary.integration.dto.AbuElRishRequestDTO;
import com.masary.integration.dto.AbuElRishResponseDTO;

/**
 * Servlet implementation class AbuElrishReceiptController
 */
public class AbuElrishReceiptController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextJSP = "";
		HttpSession session = request.getSession();
		try {
			String Amount = request.getParameter("Amount");
			String PhoneNumber = request.getParameter("PhoneNumber");
			String paymentTypeId = request.getParameter("abuelrish");
			String lang = session.getAttribute("lang").toString();
			String token = session.getAttribute("Token").toString();

			AbuElRishRequestDTO aboelrishRequestDTO = new AbuElRishRequestDTO(PhoneNumber, Double.parseDouble(Amount),
					Long.parseLong(paymentTypeId));

			AbuElRishClient aboelrishClient = new AbuElRishClient();

			AbuElRishResponseDTO aboelrishResponseDTO = aboelrishClient.AbuElRishPayment(aboelrishRequestDTO, lang,
					token);

			request.setAttribute("abuelrishResponseDTO", aboelrishResponseDTO);

			nextJSP = CONFIG.PAGE_AbuElRish_Receipt;

		} catch (Exception e) {
			MasaryManager.logger.error("Exception" + e.getMessage());
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

}
