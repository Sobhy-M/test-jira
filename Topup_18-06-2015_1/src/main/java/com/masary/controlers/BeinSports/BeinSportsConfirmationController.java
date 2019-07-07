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
import com.masary.integration.dto.BeINSportsInquiryRequestDTO;
import com.masary.integration.dto.BeINSportsInquiryResponseDTO;

public class BeinSportsConfirmationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String nextJSP = "";
		HttpSession session = request.getSession();
		String confirmation="confirmation" ;

		try {

			String lang = "en";
			if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
			} else {
				lang = "ar";
			}

			BeINSportsInquiryRequestDTO beINSportsInquiryRequest = new BeINSportsInquiryRequestDTO();

			beINSportsInquiryRequest.setMsisdn(request.getParameter("PhoneNumber"));
			beINSportsInquiryRequest.setReferenceNumber(request.getParameter("subscribtion"));
			BeINSportsClient beINSportsClient = new BeINSportsClient();
			BeINSportsInquiryResponseDTO beINSportsInquiryResponse = beINSportsClient.beINSportsInquiry(beINSportsInquiryRequest, lang, session.getAttribute("Token").toString());

			request.setAttribute("inquiryResponse", beINSportsInquiryResponse);
			
			if (beINSportsInquiryResponse == null) {
				MasaryManager.logger.info("ErrorMessage " + "الخدمة غير متاحة حاليا الرجاء المحاولة فى وقت لاحق");
				//TODO, add descriptive error message
				session.setAttribute("ErrorMessage",beINSportsInquiryResponse);
				nextJSP = CONFIG.PAGE_ERRPR;
			} else {
				request.setAttribute("action", confirmation);

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
