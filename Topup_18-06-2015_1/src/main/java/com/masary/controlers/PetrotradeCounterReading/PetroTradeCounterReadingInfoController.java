package com.masary.controlers.PetrotradeCounterReading;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.PetroTradeClient;
import com.masary.integration.dto.PetrotradeCounterReadingInquiry;

/**
 * Servlet implementation class PetroTradeCounterReadingInfoController
 */
public class PetroTradeCounterReadingInfoController extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String nextJSP = "";
        String action;
        action = request.getParameter(CONFIG.PARAM_ACTION);
        String lang = "";
        try {
            /* TODO output your page here. You may use following sample code. */

            if (action.equals(CONFIG.ACTION_GETINFO_PETROTRADE_COUNTERREADING)) {
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                MasaryManager.logger.info("Action:- " + action);
                lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }

                String token = session.getAttribute("Token").toString();
                String subscribeNumber = request.getParameter("subscriberNumber");
                try {
                    PetrotradeCounterReadingInquiry petroTradeCounterReadingInquiry = PetroTradeClient.petroTradeCounterReadingInquiry(subscribeNumber, lang, token);
                    if (petroTradeCounterReadingInquiry.getSubscriberName() != null) {
                        session.setAttribute("subscriberNumber", subscribeNumber);
                        session.setAttribute("petroTradeCounterReadingInquiry", petroTradeCounterReadingInquiry);
                        nextJSP = CONFIG.PAGE_PETROTRADE_CounterReading_Info;
                    } else {
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }

                } catch (Exception ex) {
                    MasaryManager.logger.info("ErrorMessage " + ex.getMessage());
                    session.setAttribute("ErrorMessage", ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;

                }

            }
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
            out.close();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

}
