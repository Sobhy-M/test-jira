package com.masary.controlers.FastLink;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.FastLinkClient;
import com.masary.integration.dto.BeINSportsPaymentRequestDTO;
import com.masary.integration.dto.FastLinkPaymentResponse;
import com.masary.integration.dto.FastLinkPaymentRequest;

public class FastLinkPaymentController  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String nextJSP = "";
		HttpSession session = request.getSession();

		try {

			String lang = "en";
			if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
			} else {
				lang = "ar";
			}

			String validationId = request.getParameter("validationId");
			FastLinkPaymentRequest fastLinkPaymentRequest = new FastLinkPaymentRequest();
			FastLinkClient fastLinkClient = new FastLinkClient();
  
			fastLinkPaymentRequest.setValidationId(validationId);
			
			FastLinkPaymentResponse fastLinkPaymentResponse = fastLinkClient.fastLinkPayment(fastLinkPaymentRequest, lang, session.getAttribute("Token").toString());
			
			if (fastLinkPaymentResponse == null) { 
				MasaryManager.logger.info("ErrorMessage " + "الخدمة غير متاحة حاليا الرجاء المحاولة فى وقت لاحق");
				session.setAttribute("ErrorMessage", fastLinkPaymentResponse);
				nextJSP = CONFIG.PAGE_ERRPR;
			} else {
				request.setAttribute("fastLinkPaymentResponse", fastLinkPaymentResponse);

			
			nextJSP = CONFIG.PAGE_FastLink_Payment;
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