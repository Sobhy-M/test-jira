package com.masary.controlers.CashatMerchant;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.CashatMerchantClient;
import com.masary.integration.dto.CashatCompniesDTO;
import com.masary.integration.dto.CashatInquiryRequest;
import com.masary.integration.dto.CashatInquiryResponse;

public class CashatMerchantConfirmation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String nextPage = "";
		HttpSession session = request.getSession();

		try {

			nextPage = cashatInquiry(request);

		} catch (Exception ex) {
			MasaryManager.logger.error("ErrorMessage " + ex.getMessage(),ex);
			String errorMessage = ex.getMessage();

			if (errorMessage.equals("20")) {
				CashatCompniesDTO cashatCompniesDTO = (CashatCompniesDTO) session.getAttribute("cashatMerchentsCompanies");
				errorMessage = CashatMerchantProperties._errorCashatAmountAr.replace("{minAmount}",
						String.valueOf(cashatCompniesDTO.getMinTransactionAmount()));
				session.setAttribute("ErrorMessage", errorMessage);
			}else {
				session.setAttribute("ErrorMessage", ex.getMessage());
			}
			
			nextPage = CONFIG.PAGE_ERRPR;
		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
			dispatcher.forward(request, response);
		}

	}

	private String cashatInquiry(HttpServletRequest request) throws Exception {

		String nextPage = "";
		HttpSession session = request.getSession();
		String lang = "en";
		if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
		} else {
			lang = "ar";
		}
		 int accountId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
		long companyId = -1;

		try {
			companyId = Long.parseLong(request.getParameter("Idcompany"));
		} catch (Exception e) {
			MasaryManager.logger.error("ErrorMessage " +"Account "+accountId+" didn't choose company from companies list");
			session.setAttribute("ErrorMessage", "من فضلك اختر من القائمة");
			return CONFIG.PAGE_ERRPR;

		}

		long amount = Long.parseLong(request.getParameter("amount"));
		CashatInquiryRequest cashatInquiryRequest = new CashatInquiryRequest();
		cashatInquiryRequest.setCompanyID(companyId);
		cashatInquiryRequest.setRepNationalId(request.getParameter("nationalId"));
		cashatInquiryRequest.setAmount(amount);
		
		//TODO, add log message
		MasaryManager.logger.info("Account "+accountId+" will call the client to inquire with the data of "+"CompanyId"+companyId+
				",RepNationalId"+request.getParameter("nationalId")+",Amount"+amount);
		CashatMerchantClient cashatClient = new CashatMerchantClient();
		CashatInquiryResponse cashatInquiryResponse = cashatClient.cashatInquiry(cashatInquiryRequest, lang,
				request.getSession().getAttribute("Token").toString());

		if (cashatInquiryResponse == null) {
			MasaryManager.logger.error("ErrorMessage " + "Account "+accountId+" failed to inquire");
			session.setAttribute("ErrorMessage", "حدث خطأ فى تنفيذ العملية");
			nextPage = CONFIG.PAGE_ERRPR;
		} else {
			request.setAttribute("cashatInquiryResponse", cashatInquiryResponse);
			nextPage = CashatMerchantProperties.PAGE_CashatMerchant_Confrimation;

		}
		return nextPage;
	}

}
