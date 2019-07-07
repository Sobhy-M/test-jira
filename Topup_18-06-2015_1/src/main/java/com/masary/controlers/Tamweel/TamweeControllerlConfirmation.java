/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Tamweel;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.TamweelClient;
import com.masary.integration.dto.TamweelInquiryRepresentation;
import com.masary.integration.dto.TamweelPartialInquiryRequest;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author omar.abdellah
 */
public class TamweeControllerlConfirmation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");
        String nextJSP = "";
        try {
            if (!isLogin(session)) {
                nextJSP = CONFIG.PAGE_LOGIN;
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                dispatcher.forward(request, response);
                return;
            }
            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }
            String paymentType = request.getParameter("paymentType");
            TamweelInquiryRepresentation tamweelInquiryRepresentation = new TamweelInquiryRepresentation();

            if (paymentType.equals("Partialpayment")) {
                TamweelPartialInquiryRequest tamweelPartialInquiryRequest = new TamweelPartialInquiryRequest(request.getParameter("validationId"), Double.parseDouble(request.getParameter("partialAmount")));
                TamweelClient tamweelClient = new TamweelClient();
                tamweelInquiryRepresentation = tamweelClient.tamweelPartialInquiry(tamweelPartialInquiryRequest, lang, session.getAttribute("Token").toString());

            } else {

                tamweelInquiryRepresentation.setCustomerName(request.getParameter("customerName"));
                tamweelInquiryRepresentation.setInstallmentType(request.getParameter("InstallmentType"));
                tamweelInquiryRepresentation.setCustomerCode(request.getParameter("CodeNumber"));
                tamweelInquiryRepresentation.setInstallmentDate(request.getParameter("InstallmentDate"));
                tamweelInquiryRepresentation.setInstallmentAmount(Double.parseDouble(request.getParameter("TotalAmount")));
                tamweelInquiryRepresentation.setLateCharge(Double.parseDouble(request.getParameter("LateCharge")));
                tamweelInquiryRepresentation.setMerchantCommission(Double.parseDouble(request.getParameter("merchantCommission")));

                tamweelInquiryRepresentation.setAppliedFees(Double.parseDouble(request.getParameter("appliedFees")));
                tamweelInquiryRepresentation.setToBepaid(Double.parseDouble(request.getParameter("toBepaid")));
                tamweelInquiryRepresentation.setTransactionAmount(Double.parseDouble(request.getParameter("transactionAmount")));
                tamweelInquiryRepresentation.setServiceAmount(Double.parseDouble(request.getParameter("serviceAmount")));
                tamweelInquiryRepresentation.setValidationId(request.getParameter("validationId"));
            }
            if (tamweelInquiryRepresentation != null) {

                request.setAttribute("tamweelInquiryRepresentation", tamweelInquiryRepresentation);
                nextJSP = rb.getString("Page_Tamweel_Confirmation");
            } else {
                nextJSP = CONFIG.PAGE_ERRPR;
            }

        } catch (Exception e) {
            MasaryManager.logger.error("Error During Calling orange gifts", e);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
