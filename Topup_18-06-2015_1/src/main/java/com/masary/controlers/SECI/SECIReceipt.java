package com.masary.controlers.SECI;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.masary.common.CONFIG;
import com.masary.integration.SeciClient;
import com.masary.integration.dto.SECIRequestDTO;
import com.masary.integration.dto.SECIResponseDTO;

public class SECIReceipt extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextJSP = "";
		HttpSession session = request.getSession();
		try {
			String amount = request.getParameter("Amount");
			String mobileNum = request.getParameter("PhoneNumber");
			String lang = session.getAttribute("lang").toString();
			String token = session.getAttribute("Token").toString();

			SECIRequestDTO seciRequestDTO = new SECIRequestDTO(mobileNum, Double.parseDouble(amount));

			SeciClient seciClient = new SeciClient();

			SECIResponseDTO seciResponseDTO = seciClient.SECIPayment(seciRequestDTO, lang, token);

			request.setAttribute("seciResponseDTO", seciResponseDTO);

			nextJSP = CONFIG.SECI_Receipt_Page;

		} catch (Exception e) {
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;

		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
