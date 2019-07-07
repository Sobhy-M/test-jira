package com.masary.controlers.Accept;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.AcceptClient;
import com.masary.integration.dto.AcceptInquiryRequest;
import com.masary.integration.dto.AcceptInquiryResponse;

public class AcceptInquiryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		String referenceNumber = request.getParameter("ReferenceNumber");
		String serviceId = (String) session.getAttribute("serviceId");

		String nextJSP = "";
		try {

			String lang = "en";
			if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
			} else {
				lang = "ar";
			}

			AcceptInquiryRequest acceptInquiryRequest = new AcceptInquiryRequest();
			acceptInquiryRequest.setBillReference(request.getParameter("ReferenceNumber"));
			acceptInquiryRequest.setBiller(serviceId);
			acceptInquiryRequest.setMobileNumber(request.getParameter("PhoneNumber"));

			AcceptClient acceptClient = new AcceptClient();
			AcceptInquiryResponse acceptInquiryResponse = acceptClient.acceptValidateInquiry(acceptInquiryRequest, lang,
					session.getAttribute("Token").toString(),serviceId);

			if (acceptInquiryResponse == null) {
				MasaryManager.logger.info("ErrorMessage " + "الخدمة غير متاحة حاليا الرجاء المحاولة فى وقت لاحق");
				session.setAttribute("ErrorMessage", acceptInquiryResponse);
				nextJSP = CONFIG.PAGE_ERRPR;
			} else {
				request.setAttribute("inquiryResponse", acceptInquiryResponse);

				nextJSP = CONFIG.PAGE_ACCEPT_INFO;
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
