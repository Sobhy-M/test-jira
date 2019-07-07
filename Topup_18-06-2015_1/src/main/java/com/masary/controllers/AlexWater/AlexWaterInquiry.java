/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controllers.AlexWater;

import com.masary.common.CONFIG;
import com.masary.integration.AlexWaterClient;
import com.masary.integration.dto.AlexWaterInquiryResponse;
import com.masary.integration.dto.AlexWaterRequest;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Barakat Mostafa
 */
public class AlexWaterInquiry extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
        String nextJSP = "";

        try {
            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }

            String electricityNumber = request.getParameter("electricityNumber");
            AlexWaterRequest alexWaterRequest = new AlexWaterRequest();
            alexWaterRequest.setElectricityNumber(electricityNumber);
            AlexWaterInquiryResponse alexWaterInquiryResponse = AlexWaterClient.alexWaterInquiry(alexWaterRequest, lang, session.getAttribute("Token").toString());
            
            if (alexWaterInquiryResponse.getGlobalTrxId() != null) {
                session.setAttribute("alexWaterInquiryResponse", alexWaterInquiryResponse);
                nextJSP = AlexWaterProperities.InquiryPage;

            } else {
                session.setAttribute("ErrorMessage", alexWaterInquiryResponse);
                nextJSP = CONFIG.PAGE_ERRPR;

            }

        } catch (Exception e) {
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
            session.setAttribute("uuid", "");

        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
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
