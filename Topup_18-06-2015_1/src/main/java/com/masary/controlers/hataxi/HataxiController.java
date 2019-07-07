/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.hataxi;

import com.masary.common.CONFIG;
import com.masary.integration.HataxiClient;
import com.masary.integration.dto.HataxiInquiryResponse;
import com.masary.integration.dto.HataxiPaymentRequest;
import com.masary.integration.dto.HataxiPaymentResponse;
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
 * @author hammad
 */
public class HataxiController extends HttpServlet {

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
        try {
            /* TODO output your page here. You may use following sample code. */
            action = request.getParameter(CONFIG.PARAM_ACTION);
            if (CONFIG.ACTION_HATAXI.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                nextJSP = CONFIG.HATAXI_PAGE;
                
            } else if (CONFIG.ACTION_HATAXI_CONFIRM.equals(action)) {
                String mobile = request.getParameter("DonatorPhone");
                String amount = request.getParameter("donationValue");

                String token = session.getAttribute("Token").toString();
                HataxiClient hataxiClient = new HataxiClient();
                HataxiInquiryResponse resp =  hataxiClient.hataxiInquiry(mobile, amount, "en", token, request.getRemoteAddr());
                session.setAttribute("validationResp", resp);
                session.setAttribute("mobile", mobile);
                nextJSP = CONFIG.HATAXI_CONFIRMATION;
            } else {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                String mobile = request.getParameter("DonatorPhone");
                String amount = request.getParameter("txnValue");

                HataxiPaymentRequest go = new HataxiPaymentRequest();
                go.setMobileNumber(mobile);
                go.setAmount(Double.parseDouble(amount));
//                go.setToken(request.getSession().getAttribute("Token").toString());

                String token = session.getAttribute("Token").toString();
                HataxiClient hataxiClient = new HataxiClient();
                HataxiPaymentResponse resp =  hataxiClient.hataxiPayment(go, "en", token, request.getRemoteAddr());
                
                if(resp.getTransactionStatus().contains("SUCCEEDED")){ 
                    session.setAttribute("payResponse", resp);
                    nextJSP = CONFIG.HATAXI_PAYMENT;
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
