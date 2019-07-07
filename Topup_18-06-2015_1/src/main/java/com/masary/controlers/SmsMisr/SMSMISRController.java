/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.SmsMisr;

import com.masary.common.CONFIG;
import com.masary.integration.dto.SMS_MisrPackageResponse;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.SMS_MisrClient;
import com.masary.integration.dto.SmsMisrInquiryDTO;
import com.masary.integration.dto.SmsMisrPaymentDTO;
import com.masary.integration.dto.SmsMisrRequest;
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
 * @author user
 */
public class SMSMISRController extends HttpServlet {

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

            if (action.equals(CONFIG.ACTION_SMSMISR_INQUIRY)) {
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

                MasaryManager.logger.info("Token:- " + session.getAttribute("Token").toString());

                SMS_MisrClient smsMisrClient = new SMS_MisrClient();

                SMS_MisrPackageResponse[] smsMisrPackagesDTO = smsMisrClient.smsMisrGetPackages(lang, session.getAttribute("Token").toString());

                session.setAttribute("smsMisrPackagesDTO", smsMisrPackagesDTO);

                nextJSP = CONFIG.PAGE_SMSMISR_HOME;

            } else if (action.equals(CONFIG.ACTION_SMSMISR_GETINFO)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                MasaryManager.logger.info("Action:- " + action);
                MasaryManager.logger.info("Service_Id:- " + session.getAttribute("serv_id").toString());
                MasaryManager.logger.info("Token:- " + session.getAttribute("Token").toString());

                String customerCode = request.getParameter("customerCode");
                String packageId = request.getParameter("package");

                SmsMisrRequest smsMisrRequest = new SmsMisrRequest();

                smsMisrRequest.setCustomerCode(customerCode);
                smsMisrRequest.setPackageId(Long.parseLong(packageId));

                SMS_MisrClient smsMisrClient = new SMS_MisrClient();
                SmsMisrInquiryDTO smsMisrInquiryResponse = smsMisrClient.smsMisrInquiry(smsMisrRequest, lang, session.getAttribute("Token").toString());

                if (smsMisrInquiryResponse.getConfirmationCode() != null || smsMisrInquiryResponse.getGlobalTrxId() != null || smsMisrInquiryResponse.getProviderTransactionId() != null) {
                    session.setAttribute("smsMisrInquiryResponse", smsMisrInquiryResponse);
                    session.setAttribute("smsMisrRequest", smsMisrRequest);
                    nextJSP = CONFIG.PAGE_SMSMISR_INFO;
                } else {
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
            } else if (action.equals(CONFIG.ACTION_SMSMISR_CONFIRMATION)) {

                nextJSP = CONFIG.PAGE_SMSMISR_CONFIRMATION;

            } else if (action.equals(CONFIG.ACTION_SMSMISR_PAYMENT)) {

                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                MasaryManager.logger.info("Action:- " + action);

                MasaryManager.logger.info("Service_Id:- " + session.getAttribute("serv_id").toString());

                MasaryManager.logger.info("Token:- " + session.getAttribute("Token").toString());

                SmsMisrRequest smsMisrRequest = (SmsMisrRequest) request.getSession().getAttribute("smsMisrRequest");

                String transactionId = request.getParameter("transactionId");

                smsMisrRequest.setTransactionId(transactionId);

                SMS_MisrClient smsMisrClient = new SMS_MisrClient();

                SmsMisrPaymentDTO smsMisrPaymentResponse = smsMisrClient.smsMisrPayment(smsMisrRequest, lang, session.getAttribute("Token").toString());

                if (smsMisrPaymentResponse.getGlobalTrxId() != null || !smsMisrPaymentResponse.getRequestStatus().equals("")) {

                    session.setAttribute("smsMisrPaymentResponse", smsMisrPaymentResponse);
                    session.setAttribute("smsMisrRequest", smsMisrRequest);
                    nextJSP = CONFIG.PAGE_SMSMISR_PAYMENT;
                } else {
                    nextJSP = CONFIG.PAGE_ERRPR;
                }

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
