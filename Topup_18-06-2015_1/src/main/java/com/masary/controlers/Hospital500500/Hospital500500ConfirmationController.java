/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Hospital500500;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.Hospital500500Client;
import com.masary.integration.dto.CommissionTransaction;
import com.masary.integration.dto.Representation;
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
public class Hospital500500ConfirmationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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

            Hospital500500Client hospital500500Client = new Hospital500500Client();
            String donationTypeId=        request.getParameter("donationTypeId");
            CommissionTransaction commissionTransaction = hospital500500Client.ratePlanCommissions("513", request.getParameter("amount").toString(), lang, session.getAttribute("Token").toString());
            Representation representation = new Representation();
            representation.setAppliedFees(commissionTransaction.getAppliedFees()+commissionTransaction.getVatValue());
            representation.setMobileNumber(request.getParameter("MobileNumber").toString());
            representation.setMerchantCommission(commissionTransaction.getMerchantCommission());
            representation.setPaymentAmount(Long.parseLong(request.getParameter("amount").toString()));
            representation.setToBepaid(commissionTransaction.getToBePaid());
            
            representation.setTransactionAmount(commissionTransaction.getTransactionAmount());
            
            if (representation != null) {
                request.setAttribute("representation", representation);
     request.setAttribute("donationTypeId", donationTypeId);
                nextJSP = rb.getString("Page_Hospital500500_Receipt");
            } else {
                nextJSP = CONFIG.PAGE_ERRPR;
            }

        } catch (Exception e) {
            MasaryManager.logger.error("Error During Calling tamweel ", e);
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
