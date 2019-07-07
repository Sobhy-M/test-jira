package com.masary.controlers.TE_Data_TopUp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.TEData_Inquiry_Request;
import com.masary.integration.dto.TEData_Inquiry_Response;

public class TEDataConfirmationController extends HttpServlet {

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
			String token = session.getAttribute("Token").toString();
			MasaryManager.logger.info("tedata-topup Inquiry");

			TeDataTopupComponent topupComponent = new TeDataTopupComponent();			
			TEData_Inquiry_Request tedataInquiryRequest = topupComponent.getInquiryRequest(request);
			
			TEData_Inquiry_Response tedataInquiryResponse = topupComponent.makeInquiry(tedataInquiryRequest,lang,token);				
					
			request.setAttribute("tedataInquiryResponse", tedataInquiryResponse);

			nextJSP = CONFIG.TEData_Confirmation_Page;
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
