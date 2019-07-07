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
import com.masary.integration.BeINSportsClient;
import com.masary.integration.dto.BeINSportsPaymentRequestDTO;
import com.masary.integration.dto.BeINSportsPaymentResponseDTO;

public class BeinSportsPaymentController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String nextJSP = "";
		HttpSession session = request.getSession();
		String reciept = "reciept";

		try {

			String lang = "en";
			if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
			} else {
				lang = "ar";
			}
 
			

			String validationId = request.getParameter("validationId");
			BeINSportsPaymentRequestDTO beINSportsPaymentRequest = new BeINSportsPaymentRequestDTO();
			BeINSportsClient beINSportsClient = new BeINSportsClient();

			beINSportsPaymentRequest.setValidationId(validationId);
			
			BeINSportsPaymentResponseDTO beINSportsPaymentResponse = beINSportsClient.beINSportsPayment(beINSportsPaymentRequest, lang, session.getAttribute("Token").toString());
			
			if (beINSportsPaymentResponse == null) {
				MasaryManager.logger.info("ErrorMessage " + "الخدمة غير متاحة حاليا الرجاء المحاولة فى وقت لاحق");
				session.setAttribute("ErrorMessage", beINSportsPaymentResponse);
				nextJSP = CONFIG.PAGE_ERRPR;
			} else {
				request.setAttribute("beINSportsPaymentResponse", beINSportsPaymentResponse);
				request.setAttribute("action", reciept);

				nextJSP = CONFIG.PAGE_beINSports_Confirm;
			}
			
		} catch (Exception e) {
			MasaryManager.logger.info("Error:- " + e.getStackTrace());
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;

		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}
	}

}