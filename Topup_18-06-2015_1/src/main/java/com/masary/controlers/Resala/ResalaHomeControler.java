/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Resala;

import com.masary.common.CONFIG;
import com.masary.database.dto.DonationAgentPaymentRespponseDto;
import com.masary.database.manager.MasaryManager;
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
public class ResalaHomeControler extends HttpServlet {

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
		HttpSession session = request.getSession();
		String nextJSP = "";
		int serviceId;
		String lang = "en";
		if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
		} else {
			lang = "ar";
		}
		try {
			/* TODO output your page here. You may use following sample code. */
			nextJSP = CONFIG.ResalaHomePage;
			serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
			session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
			if (request.getParameter(CONFIG.OPERATION_ID).equals("110") || request.getParameter(CONFIG.OPERATION_ID).equals("92") || request.getParameter(CONFIG.OPERATION_ID).equals("93")) {
				DonationAgentPaymentRespponseDto respponseDto = MasaryManager.getInstance()
						.getDonationInfo(Integer.parseInt(request.getParameter(CONFIG.OPERATION_ID)), serviceId, lang);
				session.setAttribute("donationResponse", respponseDto);
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
			dispatcher.forward(request, response);
		} catch (NumberFormatException e) {
			session.setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			session.setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
			dispatcher.forward(request, response);
		} catch (IOException e) {
			session.setAttribute("ErrorMessage", e.getMessage());
			nextJSP = CONFIG.PAGE_ERRPR;
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
			dispatcher.forward(request, response);
		} finally {
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
