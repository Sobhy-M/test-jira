/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.AddWallet;

import com.masary.common.CONFIG;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abdelsabor
 */
public class AddNewWalletAgentInformation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void getInfoReady(HttpServletRequest request) {
        String fullNameArabic = request.getParameter("ArabicName1") + " " + request.getParameter("ArabicName2") + " " + request.getParameter("ArabicName3") + " " + request.getParameter("ArabicName4");
        String fullNameEnglish = request.getParameter("EnglishName1") + " " + request.getParameter("EnglishName2") + " " + request.getParameter("EnglishName3") + " " + request.getParameter("EnglishName4");
        String nationalID_1 = request.getParameter("NATIONAL_ID_1");
        String nationalID_2 = request.getParameter("NATIONAL_ID_2");
        request.setAttribute("FullArabicName", fullNameArabic);
        request.setAttribute("FullEnglishName", fullNameEnglish);
        request.setAttribute("NATIONAL_ID", nationalID_2 + nationalID_1);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */

            getInfoReady(request);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + CONFIG.PAGE_ADD_CUSTOMER_ImagesPage);
            dispatcher.forward(request, response);
        } catch (Exception e) {

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
