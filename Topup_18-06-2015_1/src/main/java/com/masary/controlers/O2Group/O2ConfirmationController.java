/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.O2Group;

import com.masary.common.CONFIG;
import com.masary.integration.O2Client;
import com.masary.integration.dto.O2groupAcountDetailsResponse;
import java.io.IOException;
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
public class O2ConfirmationController extends HttpServlet {

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
        HttpSession session = request.getSession();
        O2Client o2Client = new O2Client();
        RequestDispatcher dispatcher;
        try {
            String token = session.getAttribute("Token").toString();
            String ip = request.getRemoteAddr();
            String mobileNumber = request.getParameter("MSISDN");

            O2groupAcountDetailsResponse o2groupAcountDetailsResponse = o2Client.getAccountDetails(mobileNumber, token, ip);
            request.setAttribute("o2groupAcountDetailsResponse", o2groupAcountDetailsResponse);
            nextJSP = CONFIG.O2GroupConfirmationPage;
            dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            if (e.getMessage().contains("ProviderPaymentFailure")) {
                session.setAttribute("ErrorMessage", CONFIG.transactionErrorar);
            } else {
                session.setAttribute("ErrorMessage", CONFIG.transactionErrorar);
            }
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
