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
import com.masary.integration.dto.AcceptPaymentRequest;
import com.masary.integration.dto.AcceptPaymentResponse;


public class AcceptPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        HttpSession session = request.getSession();
	        String nextJSP = "";

			try {

				String lang = "en";
				if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
				} else {
					lang = "ar";
				}
	 
				String validationId = request.getParameter("validationId");
				AcceptPaymentRequest acceptPaymentRequest = new AcceptPaymentRequest();
				AcceptClient AcceptClient = new AcceptClient();
				acceptPaymentRequest.setValidationId(validationId);
				AcceptPaymentResponse acceptPaymentResponse = AcceptClient.acceptValidatePayment(acceptPaymentRequest, lang, session.getAttribute("Token").toString(),session.getAttribute("serviceId").toString());
				
				if (acceptPaymentResponse == null) {
					MasaryManager.logger.info("ErrorMessage " + "الخدمة غير متاحة حاليا الرجاء المحاولة فى وقت لاحق");
					session.setAttribute("ErrorMessage", acceptPaymentResponse);
					nextJSP = CONFIG.PAGE_ERRPR;
				} else {
					request.setAttribute("acceptPaymentResponse", acceptPaymentResponse);

	                nextJSP = CONFIG.PAGE_ACCEPT_PAYMENT;
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
