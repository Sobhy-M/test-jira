package com.masary.controlers.PetrotradeCounterReading;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.PetroTradeClient;
import com.masary.integration.dto.PetroTradePaymentRequestDTO;
import com.masary.integration.dto.PetrotradeCounterReadingInquiry;
import com.masary.integration.dto.PetrotradeCounterReadingPayment;

/**
 * Servlet implementation class PetroTradeCounterReadingPaymentController
 */
public class PetroTradeCounterReadingPaymentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String nextJSP = "";
        String action;
        action = request.getParameter(CONFIG.PARAM_ACTION);
        try {
            /* TODO output your page here. You may use following sample code. */

            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }
            MasaryManager.logger.info("Action:- " + action);

            String token = session.getAttribute("Token").toString();
            String currentReading = request.getParameter("reading");
            String subscriberNumber = (String) session.getAttribute("subscriberNumber");

            PetroTradePaymentRequestDTO PetroTradePaymentRequest = new PetroTradePaymentRequestDTO();
            PetroTradePaymentRequest.setCurrentReading(Double.parseDouble(currentReading));
            PetroTradePaymentRequest.setSubscriberNumber(subscriberNumber);
            PetrotradeCounterReadingPayment petrotradeCounterReadingPayment = PetroTradeClient.petroTradeCounterReadingRegistration(PetroTradePaymentRequest, lang, token);
            if (petrotradeCounterReadingPayment.getGlobalTrxId() != null) {

                session.setAttribute("petrotradeCounterReadingPayment", petrotradeCounterReadingPayment);
                nextJSP = CONFIG.PAGE_PETROTRADE_CounterReading_Payment;
            } else {
                nextJSP = CONFIG.PAGE_ERRPR;
            }

        } catch (Exception ex) {
            MasaryManager.logger.info("ErrorMessage " + ex.getMessage());
            session.setAttribute("ErrorCode", ex.getMessage());
            nextJSP = CONFIG.PAGE_PETROTRADE_CounterReading_Info;

        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
            out.close();
        }
    }

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
