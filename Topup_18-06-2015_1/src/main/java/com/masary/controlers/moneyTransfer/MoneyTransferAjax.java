package com.masary.controlers.moneyTransfer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.dto.CustomerDTO;
import com.masary.database.manager.MasaryManager;

/**
 * Servlet implementation class MoneyTransferAjax
 */
public class MoneyTransferAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoneyTransferAjax() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter(CONFIG.PARAM_ACTION);
		if (action.equals("GET_AGENT_TO")) {
			AgentTo(request, response);
		} else if (action.equals("ADD_TRANSFER_FEES")) {
			addTransferFees(request, response);
		}

	}

	private void addTransferFees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String CUSTOMER_ID = request.getParameter("CUSTOMER_ID");
			String TO_CUSTOMER = request.getParameter("TO_CUSTOMER");
			String AMOUNT = request.getParameter("AMOUNT");
			long fromUser = Long.parseLong(CUSTOMER_ID);
			long toUser = Long.parseLong(TO_CUSTOMER);
			double amount = Long.parseLong(AMOUNT);
			String feesValue = MasaryManager.getInstance().callMASARY_TRANSFER_FEES(fromUser, toUser, amount);
			out.println(feesValue);
		} catch (Exception e) {
			MasaryManager.logger.error(e);
			MasaryManager.logger.error(e.getStackTrace());

		} finally {
			out.close();
		}
	}

	private void AgentTo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		try {
			String CUSTOMER_ID = request.getParameter("CUSTOMER_ID");
			CustomerDTO customer = MasaryManager.getInstance().getCustomerInfo(CUSTOMER_ID);
			out.println(customer.getCustomerName(session));
		} catch (Exception e) {
			MasaryManager.logger.error(e.getMessage());
			out.print("");
		} finally {
			out.close();
		}
	}

}
