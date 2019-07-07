/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.O2Group;

import com.masary.common.CONFIG;
import com.masary.integration.O2Client;
import com.masary.integration.dto.O2groupInquiryResponse;
import com.masary.integration.dto.O2groupPayementResponse;
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
 * @author Abdelsabor
 */
public class O2PaymentController extends HttpServlet {

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
        String nextJSP = "";
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        O2Client o2Client = new O2Client();
        try {
            nextJSP = CONFIG.O2GroupPaymentPage;
            String token = session.getAttribute("Token").toString();
            String voucherID = request.getParameter("voucherID");
            String accountID = request.getParameter("MSISDN");
            String ip = request.getRemoteAddr();
            O2groupPayementResponse o2groupPayementResponse = o2Client.doO2GroupPayment(token, ip, voucherID, accountID);
            request.setAttribute("o2groupPayementResponse", o2groupPayementResponse);
            dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            session.setAttribute("ErrorMessage", CONFIG.transactionErrorar);
            dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        } finally {

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
