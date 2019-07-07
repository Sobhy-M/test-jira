package com.masary.controlers.moneyTransfer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;

/**
 * Servlet implementation class MoneyTransferPayment
 */
public class MoneyTransferPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoneyTransferPayment() {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getNextJSP(request));
		dispatcher.forward(request, response);

	}

	private String getNextJSP(HttpServletRequest request) {
		String nextJSP = "";
		String id = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
		String amount = request.getParameter(CONFIG.AMOUNT);
		String payedPin = request.getParameter(CONFIG.PARAM_PAYED_PIN);
		String fees = request.getParameter(CONFIG.FEES);
		try {
			if (Double.parseDouble(amount) < 1) {
				throw new Exception(CONFIG.getBalanceError(request.getSession()));
			}
			if (payedPin.equals(id)) {
				throw new Exception(CONFIG.getCanntTransferError(request.getSession()));
			}
			if (Double.parseDouble(payedPin) < 1) {
				throw new Exception(CONFIG.getNotValidId(request.getSession(), false));
			}
			if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")
					&& MasaryManager.getInstance().isEmployee(payedPin)) {
				throw new Exception("لا يمكن اجراء عملية تحويل مصاري الى هذا الموظف");
			}
			double masaryBalance = MasaryManager.getInstance().getCustomerServiceBal(id, 1);
			if (Double.parseDouble(amount) > masaryBalance) {
				throw new Exception("رصيدك الحالى اقل من المبلغ المدفوع");
			}
			String transId = String.valueOf(MasaryManager.getInstance().addBalance(payedPin, id, amount));

			if (transId.equals("-41")) {
				request.getSession().setAttribute("ErrorMessage",
						request.getSession().getAttribute("lang").equals("ar") ? CONFIG.balanceLockedErrorMessageAr
								: CONFIG.balanceLockedErrorMessageEn);
				return CONFIG.PAGE_ERRPR;
			}
			if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
				MasaryManager.getInstance().insertEmployeeTransactions(
						request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString(), transId);
			}
			request.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
			request.setAttribute(CONFIG.PARAM_TAX_AMOUNT, fees);
			request.setAttribute(CONFIG.AMOUNT, amount);
			request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
			nextJSP = MoneyTransferProperties.PAYMENT_JSP;
		} catch (NumberFormatException ex) {
			MasaryManager.logger.error(ex);
			if (ex.getMessage().contains(amount)) {
				request.getSession().setAttribute("ErrorMessage", CONFIG.getNotValidId(request.getSession(), true));
			} else {
				request.getSession().setAttribute("ErrorMessage", CONFIG.getNotValidId(request.getSession(), false));
			}

			nextJSP = CONFIG.PAGE_ERRPR;
		} catch (Exception ex) {
			MasaryManager.logger.error(ex);
			if (ex.getMessage().contains("ORA-20139")) {
				request.getSession().setAttribute("ErrorMessage", CONFIG.getReEnter(request.getSession()));
				nextJSP = CONFIG.PAGE_ERRPR_SSO;
			} else {
				request.getSession().setAttribute("ErrorMessage", ex.getMessage());
				nextJSP = CONFIG.PAGE_ERRPR;
			}
		}
		return nextJSP;
	}
}
