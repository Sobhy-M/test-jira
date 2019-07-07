/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.onecard;

import com.masary.common.CONFIG;
import com.masary.integration.GooAndBackClient;
import com.masary.integration.OneCardClient;
import com.masary.integration.dto.GooBackPayResponse;
import com.masary.integration.dto.GooBackRequest;
import com.masary.integration.dto.GooBackValidateResponse;
import com.masary.integration.dto.OneCardDenominationResponse;
import com.masary.integration.dto.OneCardInquiryResponse;
import com.masary.integration.dto.OneCardPaymentRequest;
import com.masary.integration.dto.OneCardPaymentResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hammad
 */
public class OneCardController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String nextJSP = "";
        String action;
        int serviceId;
        OneCardPaymentResponse resp = null;
        try {
            action = request.getParameter(CONFIG.PARAM_ACTION);
            if (CONFIG.ACTION_ONE_CARD.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                String token = session.getAttribute("Token").toString();
                
                OneCardClient oneCardClient = new OneCardClient();
                List<OneCardDenominationResponse> vouchersList = oneCardClient.getVouchersDenominationList(lang, token, request.getRemoteAddr());
                
                serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                session.setAttribute("vouchersList", vouchersList);
                nextJSP = CONFIG.ONE_CARD_PAGE;
                
            } else if (CONFIG.ACTION_ONE_CARD_CONFIRM.equals(action)) {
                String denoId = request.getParameter("donationValue");
                String accountId = request.getParameter("DonatorPhone");
                String token = session.getAttribute("Token").toString();
                
                OneCardClient oneCardClient = new OneCardClient();
                OneCardInquiryResponse oneCardInquiryResponse =  oneCardClient.oneCardInquiry(denoId, "en", token, request.getRemoteAddr());
                session.setAttribute("validationResp", oneCardInquiryResponse);
                session.setAttribute("accountId", accountId);
                session.setAttribute("denoId", denoId);
                nextJSP = CONFIG.ONE_CARD_CONFIRMATION;
            } else {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                String accountId = (String)request.getSession().getAttribute("accountId");
                String denoId = (String)request.getSession().getAttribute("denoId");

                OneCardPaymentRequest cardPaymentRequest = new OneCardPaymentRequest();
                cardPaymentRequest.setDenominationId(Long.parseLong(denoId));
                cardPaymentRequest.setOneCardAccountId(Long.parseLong(accountId));

                String token = session.getAttribute("Token").toString();
                OneCardClient oneCardClient = new OneCardClient();
                resp =  oneCardClient.oneCardPayment(cardPaymentRequest, "en", token, request.getRemoteAddr());
                
                if(resp.getTransactionStatus().contains("SUCCEEDED")){
                    session.setAttribute("payResponse", resp);
                    nextJSP = CONFIG.ONE_CARD_PAYMENT;
                }else{
                    session.setAttribute("ErrorMessage", resp.getTransactionStatus());
                    nextJSP = CONFIG.PAGE_ERRPR;                   
                }

            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } finally {
            out.close();
        }
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
