/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary;

import java.io.IOException;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * This servlet used to serve the wap push requests
 * @author Melad
 */
public class GetApplication extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringTokenizer userAgentTokens = new StringTokenizer(request.getHeader("User-Agent"), ";", false);
        String device = null;
        String clientName = null;
        int i = 0;
        while (userAgentTokens.hasMoreTokens()) {
            device = userAgentTokens.nextToken();
            if (i == 2) {
                break;
            }
            i++;
        }
        device = device.toLowerCase();
        if (device.contains("nokia")) {
            clientName = "nokia.jar";
        } else if (device.contains("sony")) {
            clientName = "sony.jar";
        } else {
            clientName = "genric.jar";
        }
        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher(("/client/"+clientName));
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
}
