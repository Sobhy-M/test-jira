/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.ElMesbahElModea;


import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.ElMesbahElModeaClient;
import com.masary.integration.dto.Representation;
import com.masary.integration.dto.ElMesbahElModeaPaymentRequest;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author omar.abdellah
 */
public class ElMesbahElModeaControllerPayment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");
        String nextJSP = "";
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
           
            ElMesbahElModeaPaymentRequest paymentRequest = new ElMesbahElModeaPaymentRequest(request.getParameter("amount"), request.getParameter("MobileNumber"),request.getParameter("governorateId"));
            ElMesbahElModeaClient elMesbahElModeaClient = new ElMesbahElModeaClient();
            Representation representation = elMesbahElModeaClient.payment(paymentRequest, lang, session.getAttribute("Token").toString());
            java.util.Date dateValue = new java.util.Date(representation.getInsertDate());
          
            if (representation != null) {
                request.setAttribute("representation", representation);
                request.setAttribute("dateValue", dateValue);
                nextJSP = rb.getString("Page_ElMesbahElModea_ReprintPage");
            } else {
                nextJSP = CONFIG.PAGE_ERRPR;
            }

        } catch (Exception e) {
            MasaryManager.logger.error("Error During Calling  ", e);
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
