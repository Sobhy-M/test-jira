/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.gooBack;

import com.masary.common.CONFIG;
import com.masary.database.dto.DonationAgentPaymentRespponseDto;
import com.masary.database.dto.Donation_AgentPaymentRequestDTO;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.GooAndBackClient;
import com.masary.integration.dto.GooBackPayResponse;
import com.masary.integration.dto.GooBackRequest;
import com.masary.integration.dto.GooBackValidateResponse;
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
public class GooBackController extends HttpServlet {

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
            if (CONFIG.ACTION_GOO_BACK.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                nextJSP = CONFIG.GOO_BACK_PAGE;
                
            } else if (CONFIG.ACTION_GOO_BACK_CONFIRM.equals(action)) {
                GooBackRequest go = new GooBackRequest();
                String mobile = request.getParameter("DonatorPhone");
                String amount = request.getParameter("donationValue");
                go.setMsisdn(mobile);
                go.setAmount(Double.parseDouble(amount));

                String token = session.getAttribute("Token").toString();
                GooAndBackClient andBackClient = new GooAndBackClient();
                GooBackValidateResponse resp =  andBackClient.goAndBackTxnValidation(go, "en", token, request.getRemoteAddr());
                session.setAttribute("validationResp", resp);
                session.setAttribute("mobile", mobile);
                nextJSP = CONFIG.GOO_BACK_CONFIRMATION;
            } else {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                String mobile = request.getParameter("DonatorPhone");
                String amount = request.getParameter("txnValue");

                GooBackRequest go = new GooBackRequest();
                go.setMsisdn(mobile);
                go.setAmount(Double.parseDouble(amount));
//                go.setToken(request.getSession().getAttribute("Token").toString());

                String token = session.getAttribute("Token").toString();
                GooAndBackClient andBackClient = new GooAndBackClient();
                GooBackPayResponse resp =  andBackClient.goAndBackTxnPayment(go, "en", token, request.getRemoteAddr());
                
                if(resp.getRequestStatus().equals("SUCCEEDED")){
                    session.setAttribute("payResponse", resp);
                    nextJSP = CONFIG.GOO_BACK_PAYMENT;
                }else{
                    session.setAttribute("ErrorMessage", resp.getRequestStatus());
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
