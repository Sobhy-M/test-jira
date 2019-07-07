/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.ElMesbahElModea;


import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.ElMesbahElModeaClient;

import com.masary.integration.dto.Governorates;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author omar.abdellah
 */
@WebServlet(name = "ElMesbahElModeaControllerHome", urlPatterns = {"/ElMesbahElModeaControllerHome"})
public class ElMesbahElModeaControllerHome extends HttpServlet {

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
         
            
            ElMesbahElModeaClient elMesbahElModeaClient = new ElMesbahElModeaClient();
            Governorates governorates = elMesbahElModeaClient.governorates(lang, session.getAttribute("Token").toString());
         
            
            if (governorates != null) {
            session.setAttribute("governorates", governorates);
            
            nextJSP = rb.getString("Page_ElMesbahElModea_Home");
}
        } catch (Exception e) {
            MasaryManager.logger.error("Error During Calling ", e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;

        } finally {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        }
    }

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
    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
