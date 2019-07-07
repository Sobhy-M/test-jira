/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.OrangeCorporateBills;

import com.masary.common.CONFIG;
import com.masary.integration.OrangeCorporateBillsClient;
import com.masary.integration.dto.OrangeCorporatePaymentResponse;
import com.masary.integration.dto.OrangeCorporateRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class OrangeCorporateBillsPaymentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String nextJSP = "";
        String action;
        String lang = "";
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");
        try {
            action = request.getParameter(CONFIG.PARAM_ACTION);
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                lang = "en";
            } else {
                lang = "ar";
            }

            OrangeCorporateRequest orangeCorporateRequest = (OrangeCorporateRequest) session.getAttribute("orangeCorporateRequest");
            
            OrangeCorporateBillsClient orangeCorporateBillsClient = new OrangeCorporateBillsClient();

            OrangeCorporatePaymentResponse orangeCorporatePaymentResponse = orangeCorporateBillsClient.orangeCorporatPayment(orangeCorporateRequest, lang, session.getAttribute("Token").toString());

            if (orangeCorporatePaymentResponse!= null) {
                orangeCorporatePaymentResponse.setAccountNumber(request.getParameter("AccountNumber"));
                orangeCorporatePaymentResponse.setMsisdn(request.getParameter(CONFIG.PARAM_MSISDN));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                Date date1 = new Date(orangeCorporatePaymentResponse.getUpdateDate());
                String date = dateFormat.format(date1);
                String time = timeFormat.format(date1);
                request.setAttribute("time", time);
                request.setAttribute("date", date);
                session.setAttribute("orangeCorporatePaymentResponse", orangeCorporatePaymentResponse);
                nextJSP = rb.getString("PAGE_OrangeCorporateBills_PAYMENT");
            }

        } catch (Exception e) {
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
