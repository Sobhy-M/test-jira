/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.ESED;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.ESEDClient;
import com.masary.integration.dto.EsedInquiryResponse;
import com.masary.integration.dto.EsedPaymentRequest;
import com.masary.integration.dto.EsedPaymentResponse;
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
 * @author Ahmed Khaled
 */
public class ESEDPaymentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String nextJSP = "";
        String action;
        action = request.getParameter(CONFIG.PARAM_ACTION);
        session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());

        try {
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }

            if (action.equals(CONFIG.ACTION_ESED_PAYMENT_CONFIRMATION)) {

                nextJSP = CONFIG.PAGE_ESED_PAYMENT_CONFIRMATION;

            } else if (action.equals(CONFIG.ACTION_ESED_PAYMENT)) {

                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }

                String nationalCodeNumber = request.getParameter("NATIONALCODENUMBER");

                EsedPaymentRequest esedPaymentRequest = new EsedPaymentRequest();
                EsedInquiryResponse esedInquiryResponse = (EsedInquiryResponse) request.getSession().getAttribute("esedInquiryResponse");

                esedPaymentRequest.setGlobalTrxId(Long.toString(esedInquiryResponse.getGlobalTrxId()));
                esedPaymentRequest.setNationalIdOrCodeNumber(nationalCodeNumber);

                MasaryManager.logger.info("Etisal Inquiry Request:- " + esedPaymentRequest);
                String token = session.getAttribute("Token").toString();
                MasaryManager.logger.info("Token:- " + token);
                ESEDClient esedClient = new ESEDClient();

                EsedPaymentResponse esedPaymentResponse = esedClient.esedPayment(esedPaymentRequest, lang, token);

                if (esedPaymentResponse.getRequestStatus().equals("SUCCEEDED") || esedPaymentResponse.getRequestStatus().equals("200")) {
                    session.setAttribute("esedPaymentResponse", esedPaymentResponse);
                    MasaryManager.logger.info("esedPaymentResponse" + esedPaymentResponse);
                    nextJSP = CONFIG.PAGE_ESED_PAYMENT;
                } else {
                    session.setAttribute("ErrorMessage", esedPaymentResponse.getRequestStatus());
                    MasaryManager.logger.error("ErrorMessage" + esedPaymentResponse.getRequestStatus());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
                MasaryManager.logger.info("ESED Payment Response:- " + esedPaymentResponse);

            }

        } catch (Exception e) {
            MasaryManager.logger.error("Exception" + e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        }
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
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
