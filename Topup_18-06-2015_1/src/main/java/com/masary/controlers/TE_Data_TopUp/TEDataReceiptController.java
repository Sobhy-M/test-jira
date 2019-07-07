package com.masary.controlers.TE_Data_TopUp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.TEDataClient;
import com.masary.integration.dto.TEData_Payment_Request;
import com.masary.integration.dto.TEData_Payment_Response;

public class TEDataReceiptController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String nextJSP = "";
		try {
			String lang = "en";
			if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
			} else {
				lang = "ar";
			}
			String token = request.getSession().getAttribute("Token").toString();
			String validationId = request.getParameter("validationId");
			MasaryManager.logger.info("tedata-topup Payment");

			TeDataTopupComponent topupComponent = new TeDataTopupComponent();
			TEData_Payment_Response payment_Response = topupComponent.makePayment(validationId,lang,token);
			payment_Response.setToBepaid(Math.floor(payment_Response.getToBepaid()*100)/100);
			request.setAttribute("tedataPaymentResponse", payment_Response);
			 
			nextJSP = CONFIG.TEData_Receipt_Page;
		}

		catch (Exception e) {
			MasaryManager.logger.info("Error:- " + e.getStackTrace());
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;

		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}

	}

	

}
