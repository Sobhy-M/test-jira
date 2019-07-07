/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.PetroTrade;

import com.masary.common.CONFIG;
import com.masary.integration.PetroTradeClient;
import com.masary.integration.dto.BillsDetailsRepresentation;
import com.masary.integration.dto.CommissionPresentation;
import com.masary.integration.dto.PetroTradePaymentRequestDTO;
import com.masary.integration.dto.SuggestionBillsRepresentation;
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
public class PetroTradeConfirmation extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String amount = request.getParameter("PaidAmount");
            BillsDetailsRepresentation billsDetailsRepresentation = (BillsDetailsRepresentation) session.getAttribute("inquiryCommessionResponse");
            if (Double.parseDouble(amount) < billsDetailsRepresentation.getOldestBillsAmount()) {
                session.setAttribute("ErrorMessage", (lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_AmountLessThanExceptionAr : CONFIG.ERROR_PETROTRADE_AmountLessThanExceptionEn) + " " + billsDetailsRepresentation.getOldestBillsAmount());
                nextPage = CONFIG.PAGE_ERRPR;

            } else if (Double.parseDouble(amount) > billsDetailsRepresentation.getTotalBillsAmount()) {
                session.setAttribute("ErrorMessage", (lang.equals("ar") ? CONFIG.ERROR_PETROTRADE_AmountBiggerThanExceptionAr : CONFIG.ERROR_PETROTRADE_AmountBiggerThanExceptionEn) + " " + billsDetailsRepresentation.getTotalBillsAmount());
                nextPage = CONFIG.PAGE_ERRPR;
            } else {

                PetroTradeClient petroTradeClient = new PetroTradeClient();
                PetroTradePaymentRequestDTO petrotradeRequest = new PetroTradePaymentRequestDTO();
                petrotradeRequest.setAmount(Double.parseDouble(amount));
                petrotradeRequest.setSubscriberNumber(request.getParameter("MemberNumber"));
                String token = session.getAttribute("Token").toString();
                if (request.getParameter("PaidWay").equals("part")) {
                    SuggestionBillsRepresentation suggestionBillsRepresentation = petroTradeClient.loadSuggestion(petrotradeRequest, lang, token);
                    session.setAttribute("suggestionBillsRepresentation", suggestionBillsRepresentation);
                    if (suggestionBillsRepresentation.getFristSuggestionbillsAmount() == suggestionBillsRepresentation.getSecondSuggestionbillsAmount()) {
                        CommissionPresentation commissionPresentation = petroTradeClient.getPetroTradeCommession(petrotradeRequest, lang, token);
                        session.setAttribute("CommessionResponse", commissionPresentation);
                        nextPage = CONFIG.PAGE_PETROTRADE_PAYMENT;
                    } else {
                        nextPage = CONFIG.PAGE_PETROTRADE_BILLS_SUGGESTATION;
                    }
                } else {
                    CommissionPresentation commissionPresentation = petroTradeClient.getPetroTradeCommession(petrotradeRequest, lang, token);
                    session.setAttribute("CommessionResponse", commissionPresentation);
                    nextPage = CONFIG.PAGE_PETROTRADE_PAYMENT;

                }
            }

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
