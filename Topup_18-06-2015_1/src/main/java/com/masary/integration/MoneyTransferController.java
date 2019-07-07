/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.MoneyTransferRequest;
import com.masary.integration.dto.MoneyTransferTransactionResp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AYA
 */
public class MoneyTransferController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String nextPage = "";
		HttpSession session = request.getSession();
		String amount = request.getParameter(CONFIG.AMOUNT);
		String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
		String action = request.getParameter(CONFIG.PARAM_ACTION);
		try {
			/* TODO output your page here. You may use following sample code. */
			if (action.equals("ACTION_CUSTOMER_OPERATIONS")) {
				nextPage = CONFIG.PAGE_CUSTOMER_TRANSACTION;
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
				dispatcher.forward(request, response);
			} else if (action.equals("ACTION_CUST_TRANSFER")) {
				nextPage = customerTransfer(request);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
				dispatcher.forward(request, response);
			} else if (TopupServiceIntegrationStatus.moneyTransferServiceStatus) {

				MoneyTransferRequest moneyTransferRequest = new MoneyTransferRequest();

				moneyTransferRequest.setAccountId(Long.parseLong(request.getParameter("")));
				moneyTransferRequest.setAmount(Double.parseDouble(request.getParameter("")));
				moneyTransferRequest.setIp(request.getSession().getAttribute("").toString());
				moneyTransferRequest.setToken(request.getSession().getAttribute("").toString());
				MoneyTransferClient moneyTransferClient = new MoneyTransferClient();
				moneyTransferClient.doMoneyTransfer(moneyTransferRequest);
				MoneyTransferTransactionResp moneyTransferresponse = new MoneyTransferTransactionResp();
				session.setAttribute(CONFIG.PARAM_Transaction_ID, moneyTransferresponse.getGlobalTrxId());
				session.setAttribute(CONFIG.PARAM_TAX_AMOUNT, moneyTransferresponse.getAppliedFees());
				session.setAttribute(CONFIG.AMOUNT, amount);
				// request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
				nextPage = CONFIG.PAGE_TRANSFER_FEES_RESULT;
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
				dispatcher.forward(request, response);
			} else {
				nextPage = customerTransfer(request);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
				dispatcher.forward(request, response);
			}

		} catch (Exception ex) {
			session.setAttribute("ErrorMessage", ex.getMessage());
			nextPage = CONFIG.PAGE_ERRPR;
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
			dispatcher.forward(request, response);
		} finally {
			out.close();

		}
	}

	private String customerTransfer(HttpServletRequest request) {
		String nextJSP = "";
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
		String amount = request.getParameter(CONFIG.AMOUNT);
		String payedPin = request.getParameter(CONFIG.PARAM_PAYED_PIN);
		String fees = request.getParameter(CONFIG.FEES);
		try {
			if (Double.parseDouble(amount) < 1) {
				throw new Exception(CONFIG.getBalanceError(session));
			}
			if (payedPin.equals(id)) {
				throw new Exception(CONFIG.getCanntTransferError(session));
			}
			if (Double.parseDouble(payedPin) < 1) {
				throw new Exception(CONFIG.getNotValidId(session, false));
			}
			if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")
					&& MasaryManager.getInstance().isEmployee(payedPin)) {
				throw new Exception("لا يمكن اجراء عملية تحويل مصاري الى هذا الموظف");
			}
			double masaryBalance = MasaryManager.getInstance().getCustomerServiceBal(id, 1);
			if (Double.parseDouble(amount) > masaryBalance) {
				throw new Exception("رصيدك الحالى اقل من المبلغ المدفوع");
			}
			String transId = String.valueOf(MasaryManager.getInstance().addBalance(payedPin, id, amount));

			if (transId.equals("-41")) {
				session.setAttribute("ErrorMessage",
						session.getAttribute("lang").equals("ar") ? CONFIG.balanceLockedErrorMessageAr
								: CONFIG.balanceLockedErrorMessageEn);
				return CONFIG.PAGE_ERRPR;
			}
			if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
				MasaryManager.getInstance()
						.insertEmployeeTransactions(session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString(), transId);
			}
			session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
			session.setAttribute(CONFIG.PARAM_TAX_AMOUNT, fees);
			session.setAttribute(CONFIG.AMOUNT, amount);
			request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
			nextJSP = CONFIG.PAGE_TRANSFER_FEES_RESULT;
		} catch (NumberFormatException ex) {
			MasaryManager.logger.error(ex);
			if (ex.getMessage().contains(amount)) {
				session.setAttribute("ErrorMessage", CONFIG.getNotValidId(session, true));
			} else {
				session.setAttribute("ErrorMessage", CONFIG.getNotValidId(session, false));
			}

			nextJSP = CONFIG.PAGE_ERRPR;
		} catch (Exception ex) {
			MasaryManager.logger.error(ex);
			if (ex.getMessage().contains("ORA-20139")) {
				session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
				nextJSP = CONFIG.PAGE_ERRPR_SSO;
			} else {
				session.setAttribute("ErrorMessage", ex.getMessage());
				nextJSP = CONFIG.PAGE_ERRPR;
			}
		}
		return nextJSP;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
