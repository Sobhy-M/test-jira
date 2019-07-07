package com.masary.controlers.moneyTransfer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.masary.common.CONFIG;
import com.masary.database.dto.AgentDTO;
import com.masary.database.dto.LoginDto;
import com.masary.database.manager.MasaryManager;

/**
 * Servlet implementation class MoneyTransferHome
 */
public class MoneyTransferHome extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoneyTransferHome() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getNextJSP(request));
			dispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private String getNextJSP(HttpServletRequest request) {

		String lang = request.getSession().getAttribute("lang").toString();
		AgentDTO agent = null;
		try {
			agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();

			double masaryBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1);
			request.setAttribute("masaryBalance", masaryBalance);
			request.setAttribute("agent", agent);
		} catch (Exception e) {
			MasaryManager.logger.info("Error During getting home Controller for money Transafer", e);
			if (lang.equals("ar")) {
				request.getSession().setAttribute("ErrorMessage", CONFIG.transactionErrorar);
			} else {
				request.getSession().setAttribute("ErrorMessage", CONFIG.transactionError);
			}
			return CONFIG.PAGE_ERRPR;
		}

		return MoneyTransferProperties.HOME_JSP;

	}

}
