/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.internetEgypt;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.InternetEgyptService;
import com.masary.integration.dto.InternetEgyptPaymentResponse;
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
 * @author Mustafa
 */
public class InternetEgyptPaymentController extends HttpServlet {

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
        String nextJSP = "";
        String action;
        action = request.getParameter(CONFIG.PARAM_ACTION);
        session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());

        try {
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }

            if (action.equals(CONFIG.ACTIONInternetEgyptPayment)) {
                int serviceId = Integer.parseInt(session.getAttribute("serv_id").toString());
                String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
                String mobile = request.getParameter(CONFIG.PARAM_MSISDN);
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                session.setAttribute(CONFIG.lang, lang);
                String token = session.getAttribute("Token").toString();
                InternetEgyptService internetEgyptService = new InternetEgyptService();
                InternetEgyptPaymentResponse resp = new InternetEgyptPaymentResponse();
                resp = internetEgyptService.internetEgyptPaymentWithoutAuthentication(mobile, token);

                
                if (resp == null) {
                    session.setAttribute("ErrorMessage", resp);
                    nextJSP = CONFIG.PAGE_ERRPR;
                } else {
                    session.setAttribute("paymentResponse", resp);
                   nextJSP = CONFIG.PAGEInternetEgyptPAYMENT;

                }
            }
            }catch (Exception e) {
            MasaryManager.logger.error("Exception" + e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }
}