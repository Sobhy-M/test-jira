/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.FMF;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.FMFClient;
import com.masary.integration.dto.FMFCommissionResponse;
import com.masary.integration.dto.FMFPaymentRequest;
import com.masary.integration.dto.FMFPaymentResponse;
import com.masary.integration.dto.FMFTotalPaymentResponse;
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
public class FMFPaymentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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

            if (action.equals(CONFIG.ACTION_FMF_CONFIRMATION)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }

                String partialPaymentAmount = request.getParameter("PARTIALPAYMENT");

                String identificationNo = request.getParameter("IDENTIFICATIONNO");
                String token = session.getAttribute("Token").toString();
                MasaryManager.logger.info("Token:- " + token);
                FMFPaymentResponse paymentResponse = new FMFPaymentResponse();
                FMFPaymentRequest paymentTotalRequest = new FMFPaymentRequest();
                FMFClient fmfClient = new FMFClient();

                if (partialPaymentAmount == null) {// total payment
                    String totalPaymentAmount = request.getParameter("INSTALLMENTAMOUNT");
                    session.setAttribute("customerAmount", totalPaymentAmount);
                    paymentTotalRequest.setAmount(totalPaymentAmount);
                    paymentTotalRequest.setIdentificationNumber(identificationNo);

                    FMFCommissionResponse fmfCommissionResponse = fmfClient.fmfCommission(paymentTotalRequest, lang, token);

                    if (fmfCommissionResponse.getCustomerCode() == null || fmfCommissionResponse.getGlobalTrxId() == null) {
                        session.setAttribute("ErrorMessage", lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);
                        nextJSP = CONFIG.PAGE_ERRPR;
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                        dispatcher.forward(request, response);
                    } else if (fmfCommissionResponse.getStatusCode() == 200) {
                        paymentResponse.setAppliedFees(fmfCommissionResponse.getAppliedFees());
                        paymentResponse.setCustomerCode(fmfCommissionResponse.getCustomerCode());
                        paymentResponse.setCustomerName(fmfCommissionResponse.getCustomerName());
                        paymentResponse.setGlobalTrxId(fmfCommissionResponse.getGlobalTrxId());
                        paymentResponse.setIdentificationNo(fmfCommissionResponse.getIdentificationNo());
                        paymentResponse.setToBepaid(fmfCommissionResponse.getToBepaid());
                        paymentResponse.setInstallmentDueDate(fmfCommissionResponse.getInstallmentDueDate());
                        paymentResponse.setInstallmentAmount(fmfCommissionResponse.getInstallmentAmount());
                        paymentResponse.setMerchantCommission(fmfCommissionResponse.getMerchantCommission());
                        session.setAttribute("fmfTotalPaymentResponse", fmfCommissionResponse);
                        session.setAttribute("fmfPaymentResponse", paymentResponse);
                        nextJSP = CONFIG.PAGE_FMF_CONFIRMATION;
                    }

                } else {//partial payment
                    MasaryManager.logger.info("FMF Partial Submit Amount:- " + partialPaymentAmount);
                    session.setAttribute("customerAmount", partialPaymentAmount);
                    paymentTotalRequest.setAmount(partialPaymentAmount);
                    paymentTotalRequest.setIdentificationNumber(identificationNo);

                    FMFCommissionResponse fmfPartialPaymentResponse = fmfClient.fmfCommission(paymentTotalRequest, lang, token);

                    if (fmfPartialPaymentResponse.getStatusCode() == 200) {
                        paymentResponse.setAppliedFees(fmfPartialPaymentResponse.getAppliedFees());
                        paymentResponse.setCustomerCode(fmfPartialPaymentResponse.getCustomerCode());
                        paymentResponse.setCustomerName(fmfPartialPaymentResponse.getCustomerName());
                        paymentResponse.setGlobalTrxId(fmfPartialPaymentResponse.getGlobalTrxId());
                        paymentResponse.setIdentificationNo(fmfPartialPaymentResponse.getIdentificationNo());
                        paymentResponse.setToBepaid(fmfPartialPaymentResponse.getToBepaid());
                        paymentResponse.setInstallmentDueDate(fmfPartialPaymentResponse.getInstallmentDueDate());
                        paymentResponse.setInstallmentAmount(Double.parseDouble(partialPaymentAmount));
                        paymentResponse.setMerchantCommission(fmfPartialPaymentResponse.getMerchantCommission());
                        session.setAttribute("fmfPartialPaymentResponse", paymentResponse);
                        session.setAttribute("fmfPaymentResponse", paymentResponse);
                        nextJSP = CONFIG.PAGE_FMF_CONFIRMATION;
                    } else {
                        session.setAttribute("ErrorMessage", lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);
                        nextJSP = CONFIG.PAGE_ERRPR;
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                        dispatcher.forward(request, response);
                    }
                }

            } else if (action.equals(CONFIG.ACTION_FMF_PAYMENT)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                String token = session.getAttribute("Token").toString();
                MasaryManager.logger.info("Token:- " + token);
                FMFPaymentRequest paymentTotalRequest = new FMFPaymentRequest();

                String amount = (String) session.getAttribute("customerAmount");
                String identificationNo = request.getParameter("IDENTIFICATIONNO");

                paymentTotalRequest.setAmount(amount);
                paymentTotalRequest.setIdentificationNumber(identificationNo);
                FMFPaymentResponse paymentResponse = new FMFPaymentResponse();
                FMFClient fmfClient = new FMFClient();

                FMFTotalPaymentResponse fmfTotalPaymentResponse = fmfClient.fmfPayment(paymentTotalRequest, lang, token);

                if (fmfTotalPaymentResponse.getStatusCode() == 200) {
                    paymentResponse.setAppliedFees(fmfTotalPaymentResponse.getAppliedFees());
                    paymentResponse.setCustomerCode(fmfTotalPaymentResponse.getCustomerCode());
                    paymentResponse.setCustomerName(fmfTotalPaymentResponse.getCustomerName());
                    paymentResponse.setGlobalTrxId(fmfTotalPaymentResponse.getGlobalTrxId());
                    paymentResponse.setIdentificationNo(fmfTotalPaymentResponse.getIdentificationNo());
                    paymentResponse.setToBepaid(fmfTotalPaymentResponse.getToBepaid());
                    paymentResponse.setInstallmentDueDate(fmfTotalPaymentResponse.getInstallmentDueDate());
                    paymentResponse.setInstallmentAmount(fmfTotalPaymentResponse.getInstallmentAmount());
                    paymentResponse.setPaidAmount(Double.parseDouble(amount));
                    paymentResponse.setMerchantCommission(fmfTotalPaymentResponse.getMerchantCommission());

                    session.setAttribute("fmfPaymentResponse", paymentResponse);

                    nextJSP = CONFIG.PAGE_FMF_PAYMENT;
                } else {
                    session.setAttribute("ErrorMessage", lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);
                    nextJSP = CONFIG.PAGE_ERRPR;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
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
