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

public class CashatMerchantHome extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String nextPage = "";
		HttpSession session = request.getSession();

		try {
			if (!isLogin(session)) {

				nextPage = CONFIG.PAGE_LOGIN;
				session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
				dispatcher.forward(request, response);
				return;
			}
			nextPage = checkCompaniesAvaliablty(request);

		} catch (Exception ex) {
			MasaryManager.logger.error("ErrorMessage " + ex.getMessage(),ex);
			session.setAttribute("ErrorMessage", "حدث خطأ اثناء تنفيذ العملية");
			nextPage = CONFIG.PAGE_ERRPR;
		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
			dispatcher.forward(request, response);
		}

	}

	private boolean isLogin(HttpSession session) {
		return session.getAttribute(CONFIG.PARAM_PIN) != null;
	}

	private String checkCompaniesAvaliablty(HttpServletRequest request) throws Exception {

		String nextPage = "";
		HttpSession session = request.getSession();
		String lang = "en";
		if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
		} else {
			lang = "ar";
		}
        int accountId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
		MasaryManager.logger.info("Account "+accountId+" check cashat merchant companies avalablity");
		CashatCompniesDTO cashatCompnies = null;
		
		if (request.getSession().getAttribute("cashatMerchentsCompanies") == null) {
			MasaryManager.logger.info("Account "+accountId+" didn't found cashat merchant companies and call client to get it");
			CashatMerchantClient cashatClient = new CashatMerchantClient();
			cashatCompnies = cashatClient.getCompanies(lang, request.getSession().getAttribute("Token").toString());

			if (cashatCompnies == null) {
				MasaryManager.logger.error("ErrorMessage " + "Account "+accountId+" didn't found cashat merchant companies");
				session.setAttribute("ErrorMessage", "حدث خطأ أثناء تنفيذ العملية");
				nextPage = CONFIG.PAGE_ERRPR;
			} else {
				request.getSession().setAttribute("cashatMerchentsCompanies", cashatCompnies);
			}

		}
		nextPage = CashatMerchantProperties.PAGE_CashatMerchant_Home;

		return nextPage;

	}

}
