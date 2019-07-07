/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.PetroTrade;

import com.masary.common.CONFIG;
import com.masary.integration.PetroTradeClient;
import com.masary.integration.dto.PetroTradePaymentRequestDTO;
import com.masary.integration.dto.TransactionRepresentation;
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
 * @author AYA
 */
public class PetroTradePaymentConfirmation extends HttpServlet {

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
        HttpSession session = request.getSession();
        String nextPage = "";
        try {            
            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }
            String amount = request.getParameter(CONFIG.AMOUNT);
            PetroTradeClient petroTradeClient = new PetroTradeClient();
            PetroTradePaymentRequestDTO petrotradeRequest = new PetroTradePaymentRequestDTO();
            petrotradeRequest.setAmount(Double.parseDouble(amount));
            petrotradeRequest.setSubscriberNumber(request.getParameter("MemberNumber"));
            String token = session.getAttribute("Token").toString();
            TransactionRepresentation transactionResponse = petroTradeClient.petroTradePayment(petrotradeRequest, lang, token, request.getRemoteAddr());
            session.setAttribute("transactionResponse", transactionResponse);
            nextPage = CONFIG.PAGE_PETROTRADE_PAYMENT_CONFIRMATION;
        } catch (Exception ex) {
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextPage = CONFIG.PAGE_ERRPR;
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
            dispatcher.forward(request, response);
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
