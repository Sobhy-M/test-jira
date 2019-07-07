/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.WEPostPaid;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.WePostPaidClient;
import com.masary.integration.dto.WepostPaidPaymentResponse;
import com.masary.integration.dto.WepostPaidRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class WeBillsPayment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nextPage = "";
        HttpSession session = request.getSession();

        try {
            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }
            String token = session.getAttribute("Token").toString();
            String validationId = request.getParameter("validationId");

            WepostPaidPaymentResponse paymentResponse = WePostPaidClient.wePostPaidPayment(new WepostPaidRequest(request.getParameter("msisdn").replaceFirst("0", ""), validationId), lang, token);

            if (paymentResponse.getGlobalTrxId() != null || paymentResponse.getMsisdn() != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                //get current date time with Date()
                Date date = new Date();
                String date1 = dateFormat.format(date);
                String time = timeFormat.format(date);
                session.setAttribute("time", time);
                session.setAttribute("date1", date1);
                paymentResponse.setMsisdn(request.getParameter("msisdn"));
                session.setAttribute("paymentResponse", paymentResponse);
                nextPage = WePostPaidProperties.PAGE_WePostPaid_PAYMENT;

            } else {
                nextPage = CONFIG.PAGE_ERRPR;

            }

        } catch (Exception ex) {
            MasaryManager.logger.info("ErrorMessage " + ex.getMessage());
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextPage = CONFIG.PAGE_ERRPR;
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
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
