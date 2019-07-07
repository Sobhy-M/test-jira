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
import com.masary.integration.dto.FastLinkInquiryRequest;
import com.masary.integration.dto.FastLinkInquiryResponse;

public class FastLinkInquiryController extends HttpServlet{
	/**
	 * 
	 */
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
			FastLinkInquiryRequest fastLinkInquiryRequest = new FastLinkInquiryRequest();

			fastLinkInquiryRequest.setMsisdn(request.getParameter(CONFIG.PARAM_MSISDN));
		 	FastLinkClient fastLinkClient = new FastLinkClient();
			FastLinkInquiryResponse fastLinkInquiryResponse = fastLinkClient.fastLinkInquiry(fastLinkInquiryRequest.getMsisdn(), lang, session.getAttribute("Token").toString());
 
			request.setAttribute("inquiryResponse", fastLinkInquiryResponse);
			
			if (fastLinkInquiryResponse == null) {
				MasaryManager.logger.info("ErrorMessage " + "الخدمة غير متاحة حاليا الرجاء المحاولة فى وقت لاحق");
				//TODO, add descriptive error message 
				session.setAttribute("ErrorMessage",fastLinkInquiryResponse);
				nextJSP = CONFIG.PAGE_ERRPR;
			} else {

		
				nextJSP = CONFIG.PAGE_FastLink_Info;
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
