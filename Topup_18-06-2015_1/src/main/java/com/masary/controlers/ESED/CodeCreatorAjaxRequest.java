/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.ESED;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ahmed Khaled
 */
public class CodeCreatorAjaxRequest extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            String left = request.getParameter("LEFTKEY");
            String right = request.getParameter("RIGHTKEY");

            String result = "";
            if (!right.equals("")) {
                right = String.format("%07d", Integer.parseInt(right));
                result = left + "-" + right;

            }
            if (!left.equals("")) {
                if (left.length() != 4) {
                    left = String.format("%03d", Integer.parseInt(left));
                }

                if (left.length() > 4) {
                    StringBuilder sb = new StringBuilder(left);
                    if (left.startsWith("2")) {
                        sb.deleteCharAt(left.indexOf("2", 0));
                    }

                    left = sb.toString();
                } else if (left.length() < 4) {

                    if (left.startsWith("2")) {
                        StringBuilder sb = new StringBuilder(left);
                        sb.deleteCharAt(left.indexOf("2", 0));
                        left = String.format("%03d", Integer.parseInt(sb.toString()));
//                        left = sb.toString();
                    }
                    left = "2" + left;

                }
                result = left + "-" + right;

            }
            if (!right.equals("") && left.equals("")) {
                result = left + "-" + right;

            }

            out.write(result);

        } catch (Exception e) {
            MasaryManager.logger.error("Exception" + e);
        } finally {
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
