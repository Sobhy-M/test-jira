/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.YallaPay;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.YallaPayClient;
import com.masary.integration.dto.YallaPayInquiryResponse;
import com.masary.integration.dto.YallaPayPaymentRequest;
import com.masary.integration.dto.YallaPayPaymentResponse;
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
public class YallaPayController extends HttpServlet {

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

            if (action.equals(CONFIG.ACTION_YALLAPAY_INQUIRY)) {

                MasaryManager.logger.info("Action:- " + action);
                int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute("serv_id", serviceId);
                MasaryManager.logger.info("Service_Id:- " + serviceId);
                serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                nextJSP = CONFIG.PAGE_YALLAPAY_HOMEPAGE;

            } else if (action.equals(CONFIG.ACTION_YALLAPAY_INFO)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }

                String trxID = request.getParameter("TRXID");

                MasaryManager.logger.info("yallapay Inquiry Request:- " + trxID);
                String token = session.getAttribute("Token").toString();
                MasaryManager.logger.info("Token:- " + token);
                YallaPayClient yallaPayClient = new YallaPayClient();

                YallaPayInquiryResponse yallaPayInquiryResponse = yallaPayClient.yallaPayValidateInquiry(trxID, lang, token, request.getRemoteAddr());

                if (yallaPayInquiryResponse.getTransactionId() == null || yallaPayInquiryResponse.getClientName() == null || yallaPayInquiryResponse.getGlobalTrxId() == null) {
                    session.setAttribute("ErrorMessage", lang.equals("ar") ? CONFIG.yallaPayServiceNotAvailableAr : CONFIG.yallaPayServiceNotAvailableEn);
                    nextJSP = CONFIG.PAGE_ERRPR;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                } else {

                    if (yallaPayInquiryResponse.getBalanceType().equals("wallet")) {
                        yallaPayInquiryResponse.setBalanceType("شحن محفظة");
                    } else if (yallaPayInquiryResponse.getBalanceType().equals("order")) {
                        yallaPayInquiryResponse.setBalanceType("طلب");
                    }
                    session.setAttribute("yallaPayInquiryResponse", yallaPayInquiryResponse);
                    nextJSP = CONFIG.PAGE_YALLAPAY_INFOPAGE;

                }
                MasaryManager.logger.info("YallaPay Inquiry Response:- " + yallaPayInquiryResponse);

            } else if (action.equals(CONFIG.ACTION_YALLAPAY_PAYMENT)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }

//                YallaPayInquiryResponse yallaPayInquiryResponse = (YallaPayInquiryResponse) request.getSession().getAttribute("yallaPayInquiryResponse");
                String trxID = request.getParameter("TRXID");

                MasaryManager.logger.info("YallaPay Payment Request:- " + trxID);
                String token = session.getAttribute("Token").toString();
                MasaryManager.logger.info("Token:- " + token);

                YallaPayPaymentRequest yallaPayPaymentRequest = new YallaPayPaymentRequest();
                yallaPayPaymentRequest.setProcessNumber(trxID);
                YallaPayClient yallaPayClient = new YallaPayClient();

                YallaPayPaymentResponse yallaPayPaymentResponse = yallaPayClient.yallaPayPayment(yallaPayPaymentRequest, lang, token, request.getRemoteAddr());

                if (yallaPayPaymentResponse.getRequestStatus().equals("SUCCEEDED") || yallaPayPaymentResponse.getRequestStatus().equals("200")) {
                   
                    if (yallaPayPaymentResponse.getBalanceType().equals("wallet")) {
                        yallaPayPaymentResponse.setBalanceType("شحن محفظة");
                    } else if (yallaPayPaymentResponse.getBalanceType().equals("order")) {
                        yallaPayPaymentResponse.setBalanceType("طلب");
                    }
                    session.setAttribute("yallaPayPaymentResponse", yallaPayPaymentResponse);
                    MasaryManager.logger.info("yallaPayPaymentResponse" + yallaPayPaymentResponse);
                    nextJSP = CONFIG.PAGE_YALLAPAY_PAYMENTPAGE;
                } else {
                    session.setAttribute("ErrorMessage", yallaPayPaymentResponse.getRequestStatus());
                    MasaryManager.logger.error("ErrorMessage" + yallaPayPaymentResponse.getRequestStatus());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
                MasaryManager.logger.info("YallaPay Payment Response:- " + yallaPayPaymentResponse);

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
