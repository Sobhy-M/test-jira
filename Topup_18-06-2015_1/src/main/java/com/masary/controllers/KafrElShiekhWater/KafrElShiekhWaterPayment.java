/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controllers.KafrElShiekhWater;

import com.google.common.base.Preconditions;
import com.masary.common.CONFIG;
import com.masary.integration.KafrElShiekhWaterClient;
import com.masary.integration.dto.KafrElShiekhWaterPaymentRequest;
import com.masary.integration.dto.KafrElShiekhWaterPaymentResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
public class KafrElShiekhWaterPayment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String nextJSP = "";
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");
        try {
            if (!isLogin(session)) {
                nextJSP = CONFIG.PAGE_LOGIN;
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                dispatcher.forward(request, response);
                return;
            }

            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }

            KafrElShiekhWaterClient kafrElShiekhWaterClient = new KafrElShiekhWaterClient();

            KafrElShiekhWaterPaymentRequest kafrElShiekhWaterPaymentRequest = new KafrElShiekhWaterPaymentRequest(request.getParameter("validationId"));

            KafrElShiekhWaterPaymentResponse kafrElShiekhWaterPaymentResponse = kafrElShiekhWaterClient.payment(kafrElShiekhWaterPaymentRequest, lang, session.getAttribute("Token").toString());
            
            Preconditions.checkNotNull(kafrElShiekhWaterPaymentResponse);
            
            Date date = new Date(kafrElShiekhWaterPaymentResponse.getInsertDate());
            
            request.setAttribute("date", date);
            
            request.setAttribute("kafrElShiekhWaterPaymentResponse", kafrElShiekhWaterPaymentResponse);
            
            nextJSP = rb.getString("Page_KafrElShiekhWater_Payment");

        } catch (Exception e) {
            session.setAttribute("ErrorMessage", e.getMessage() + e);
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
