/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Etisal;

import com.masary.common.CONFIG;
import com.masary.database.dto.Bill_Request;
import com.masary.database.dto.Bill_Response;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.EtisalClient;
import com.masary.integration.dto.EtisalRequest;
import com.masary.integration.dto.EtisalInquiryResponse;
import com.masary.integration.dto.EtisalPaymentResponse;
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
 * @author Ahmed khaled
 */
public class EtisalContoller extends HttpServlet {

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

            if (action.equals(CONFIG.ACTIONEtisalNQUIRY)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                MasaryManager.logger.info("Action:- " + action);
                int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute("serv_id", serviceId);
                MasaryManager.logger.info("Service_Id:- " + serviceId);
                serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                nextJSP = CONFIG.PAGEEtisalINQUIRY;
            } else if (action.equals(CONFIG.ACTIONEtisalGETINFO)) {

                EtisalRequest etisalRequest = new EtisalRequest();
                etisalRequest.setPhoneNumber(request.getParameter(CONFIG.PARAM_MSISDN));
                MasaryManager.logger.info("Etisal Inquiry Request:- " + etisalRequest);
                String token = session.getAttribute("Token").toString();
                MasaryManager.logger.info("Token:- " + token);
                EtisalClient etisalClient = new EtisalClient();
                EtisalInquiryResponse etisalInquiryResponse = etisalClient.etisalValidateInquiry(etisalRequest, "En", token);
                
                if (etisalInquiryResponse.getPhoneNumber() == null || etisalInquiryResponse.getClientName() == null || etisalInquiryResponse.getGlobalTrxId() == null) {
                    session.setAttribute("ErrorMessage", etisalInquiryResponse.getRequestStatus());
                    nextJSP = CONFIG.PAGE_ERRPR;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                } else {

                    session.setAttribute("etisalInquiryResponse", etisalInquiryResponse);
                    session.setAttribute("phoneNumber", request.getParameter(CONFIG.PARAM_MSISDN));
                    nextJSP = CONFIG.PAGEEtisalGETINFO;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                }
                MasaryManager.logger.info("Etisal Inquiry Response:- " + etisalInquiryResponse);
            } else if (action.equals(CONFIG.ACTIONEtisalPayment)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }

                EtisalRequest etisalRequest = new EtisalRequest();
                etisalRequest.setPhoneNumber(request.getParameter(CONFIG.PARAM_MSISDN));
                MasaryManager.logger.info("Etisal Inquiry Request:- " + etisalRequest);
                String token = session.getAttribute("Token").toString();
                MasaryManager.logger.info("Token:- " + token);
                EtisalClient etisalClient = new EtisalClient();

                EtisalPaymentResponse etisalPaymentResponse = etisalClient.etisalPayment(etisalRequest, "en", token);

                if (etisalPaymentResponse.getRequestStatus().equals("SUCCEEDED")) {
                    session.setAttribute("etisalPaymentResponse", etisalPaymentResponse);
                    MasaryManager.logger.info("etisalPaymentResponse" + etisalPaymentResponse);
                    nextJSP = CONFIG.PAGEEtisalPAYMENTCONFIRMATION;
                } else {
                    session.setAttribute("ErrorMessage", etisalPaymentResponse.getRequestStatus());
                     MasaryManager.logger.error("ErrorMessage" + etisalPaymentResponse.getRequestStatus());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
                MasaryManager.logger.info("Etisal Payment Response:- " + etisalPaymentResponse);
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