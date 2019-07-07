/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controllers.TeDataBss;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.TEDataBillsClient;
import com.masary.integration.dto.TEDataBillsRequest;
import com.masary.integration.dto.TEDataBillsResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
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
public class TeDataCustomerInquiry extends HttpServlet {

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
            TEDataBillsClient teDataBillsClient = new TEDataBillsClient();
            String billing_account = request.getParameter("BILLING_ACCOUNT");
            String token = session.getAttribute("Token").toString();

            if (teDataBillsClient.actionDestination(billing_account, token)) {
        	// Simba Customer
                TEDataBillsRequest teRequest = new TEDataBillsRequest();

                teRequest.setLandLine(billing_account);
                String customer_id = request.getSession().getAttribute(CONFIG.PARAM_PIN).toString();
                teRequest.setCustomerNumber(customer_id);

                String UUId = (String) session.getAttribute("uuid");
                MasaryManager.logger.info("Token:- " + token);
                TEDataBillsClient teClient = new TEDataBillsClient();
                TEDataBillsResponse teInquiryResponse = teClient.TEInquiry(teRequest, token, UUId);
                //code for new object
                if (teInquiryResponse.getLandLine() == null || teInquiryResponse.getCustomerName() == null || teInquiryResponse.getGlobalTrxId() == null) {
                    session.setAttribute("ErrorMessage", teInquiryResponse.getRequestStatus());
                    nextJSP = CONFIG.PAGE_ERRPR;
                    //killing the session
                    session.setAttribute("uuid", "");
                } else {
                    session.setAttribute("tedataInquiryResponse", teInquiryResponse);
                    session.setAttribute("phoneNumber", request.getParameter(CONFIG.PARAM_MSISDN));
                    nextJSP = CONFIG.PAGE_TEDATA_Bill_Inquiry_RESP;
                    session.setAttribute("uuid", "");
                }
                MasaryManager.logger.info("TE Data Inquiry Response:- " + teInquiryResponse);
            } else {
        	// BSS Customer
                nextJSP = rb.getString("Page_TeDataBills_InquiryBss");
            }

        } catch (Exception e) {
            MasaryManager.logger.error("Error During Calling tedata", e);
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

    //<editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
