/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.PetroTrade;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.PetroTradeClient;
import com.masary.integration.dto.BillsDetailsRepresentation;
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
public class PetroTradeInfoController extends HttpServlet {

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
        String nextPage = "";
        HttpSession session = request.getSession();
//        String amount = request.getParameter(CONFIG.AMOUNT);
//        String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        try {
            /* TODO output your page here. You may use following sample code. */
            if (action.equals(CONFIG.ACTION_INQUIRY_PETROTRADE)) {
                nextPage = CONFIG.PAGE_PETROTRADE_HOME;

            } else if (action.equals(CONFIG.ACTION_GETINFO_PETROTRADE)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                String token = session.getAttribute("Token").toString();
                PetroTradeClient petroTradeClient = new PetroTradeClient();
                String subscribeNumber = request.getParameter("MemberNumber");
                try {
                    BillsDetailsRepresentation inquiryCommessionResponse = petroTradeClient.petroTradeInquiry(subscribeNumber, lang, token);
                    session.setAttribute("inquiryCommessionResponse", inquiryCommessionResponse);
                    nextPage = CONFIG.PAGE_PETROTRADE_INFO;

                } catch (Exception ex) {
                    MasaryManager.logger.info("ErrorMessage " + ex.getMessage());
                    session.setAttribute("ErrorMessage", ex.getMessage());
                    nextPage = CONFIG.PAGE_ERRPR;

                }

            }
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
