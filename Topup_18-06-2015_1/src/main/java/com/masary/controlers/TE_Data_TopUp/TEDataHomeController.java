package com.masary.controlers.TE_Data_TopUp;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.TEDataClient;
import com.masary.integration.dto.TedataDenominationRepresentation;

public class TEDataHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String nextJSP = "";

		try {
			if (!isLogin(session)) {

				nextJSP = CONFIG.PAGE_LOGIN;
				/*
				 * TODO check is login how user not login and try to set value in session :V 
				 */
				session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
				dispatcher.forward(request, response);
				return;
			}
			MasaryManager.logger.info("tedata-topup check Denomination availabilty");

			checkDenominationAvaliablty(request);


			nextJSP = CONFIG.TEData_Home_Page;

		} catch (Exception e) {
			MasaryManager.logger.info("Error:- " + e.getStackTrace());
			request.getSession().setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;

		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}
	}

	private boolean isLogin(HttpSession session) {
		return session.getAttribute(CONFIG.PARAM_PIN) != null;
	}

	private void checkDenominationAvaliablty(HttpServletRequest request) throws Exception {

		MasaryManager.logger.info("Check tedata-topup Denomination avalablity");

		String lang = "en";
		if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
		} else {
			lang = "ar";
		}

		if (request.getSession().getAttribute("tedataDenominationRepresentation") == null) {
			
			MasaryManager.logger.info("tedata-topup Denomination not found and call client to get it");

			TEDataClient tEDataClient = new TEDataClient();
			List<TedataDenominationRepresentation> tedataDenominationRepresentation = tEDataClient.getDenomination(lang,
					request.getSession().getAttribute("Token").toString());

			request.getSession().setAttribute("tedataDenominationRepresentation", tedataDenominationRepresentation);
		}
	}
}
