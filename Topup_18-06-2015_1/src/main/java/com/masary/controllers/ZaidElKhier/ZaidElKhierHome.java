/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controllers.ZaidElKhier;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.ZaidElKhierClient;
import com.masary.integration.dto.ZaidElKhierCategoriesResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author user
 */
public class ZaidElKhierHome extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nextPage = "";
        HttpSession session = request.getSession();

        try {
            if (!isLogin(session)) {

                nextPage = CONFIG.PAGE_LOGIN;
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
                dispatcher.forward(request, response);
                return;
            }
            
            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }

            session.setAttribute("serv_id", ZaidElKhierProperties.Param_ZaidElKhier_ServiceId);
            MasaryManager.logger.info("Service_Id:- " + ZaidElKhierProperties.Param_ZaidElKhier_ServiceId);

            
        	CloseableHttpClient httpClient = HttpClients.createDefault();
        	HttpGet httpGet = new HttpGet();
            
            ZaidElKhierCategoriesResponse[] zaidElKhierCategoriesResponse =  new ZaidElKhierClient(httpClient, httpGet, MasaryManager.logger).getCategories(lang, session.getAttribute("Token").toString());
            
            session.setAttribute("inquiryResponse", zaidElKhierCategoriesResponse);

            nextPage = ZaidElKhierProperties.PAGE_ZaidElKhier_HOME;

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
