package com.masary.controlers.ChangePoints;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.controlers.CashatMasary.CashatMasaryProperties;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.ChangePointsDTO;
import com.masary.integration.dto.TedataDenominationRepresentation;

/**
 * Servlet implementation class LoyaltyPointsConfirmation
 */
public class LoyaltyPointsConfirmation extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

			ChangePointsDTO loyaltyPointsRepresentation = (ChangePointsDTO) request.getSession()
					.getAttribute("loyaltyPointsRepresentation");
			loyaltyPointsRepresentation.getWalletPoints();
			loyaltyPointsRepresentation.getPoints();
			long choosenAmount = Long.parseLong(request.getParameter("points"));
			if (!(checkAmountInPointsList(choosenAmount, loyaltyPointsRepresentation.getPoints())
					&& checkAmountValidation(choosenAmount, loyaltyPointsRepresentation))) {
				
				session.setAttribute("ErrorMessage","يرجي العلم بانه بان هذا العدد من النقاط اكبر من النقاط المتاحة");
				nextPage = CONFIG.PAGE_ERRPR;

			} else {
				String selectedPoints = request.getParameter("points");
				request.setAttribute("selectedPoints", Integer.valueOf(selectedPoints));
				nextPage = CONFIG.LoyaltyPoints_Confirmation_Page;
			}

		} catch (Exception ex) {
			MasaryManager.logger.info("ErrorMessage " + ex.getMessage());
			session.setAttribute("ErrorMessage", ex.getMessage());
			nextPage = CONFIG.PAGE_ERRPR;
		} finally {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
			dispatcher.forward(request, response);
		}

	}

	private boolean checkAmountValidation(long choosenAmount, ChangePointsDTO changePointsDTO) {

		if (!checkAmountInPointsList(choosenAmount, changePointsDTO.getPoints())) {

			return false;
		}

		if (choosenAmount > changePointsDTO.getWalletPoints()) {
			return false;
		}

		return true;
	}

	private boolean checkAmountInPointsList(long choosenAmount, List<Long> points) {

		for (Long i : points) {
			if (i == choosenAmount)
				return true;
		}
		return false;
	}

	private boolean isLogin(HttpSession session) {
		return session.getAttribute(CONFIG.PARAM_PIN) != null;
	}
}
