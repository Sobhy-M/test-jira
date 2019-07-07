package com.masary.controlers.OrangeTopup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masary.common.CONFIG;
import com.masary.controlers.component.OrangeTopupComponent;
import com.masary.integration.dto.CommonTopupRepresentationDTO;
import com.masary.integration.dto.CommonTopupRequestDTO;

/**
 * Servlet implementation class OrangeTopupPayment
 */
public class OrangeTopupPayment extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrangeTopupPayment() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextJSP = "";

		try {

			OrangeTopupComponent topupComponent = new OrangeTopupComponent();
			CommonTopupRequestDTO commonTopupRequestDTO = topupComponent.prepareCommonTopupRequestDTO(request);
			CommonTopupRepresentationDTO transactionDTO = topupComponent.doPaymentTransaction(request,
					commonTopupRequestDTO);
			
			request.setAttribute("transactionDTO", transactionDTO);
			nextJSP = OrangeTopupProperties.orangeTopupPayment;
		} catch (Exception e) {
			nextJSP = CONFIG.PAGE_ERRPR;
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
		}

		request.getRequestDispatcher(nextJSP).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
