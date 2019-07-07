/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Elorman;

import com.masary.common.CONFIG;
import com.masary.database.dto.DonationAgentPaymentRespponseDto;
import com.masary.database.dto.RatePlanDTO;
import com.masary.database.manager.MasaryManager;
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
 * @author Masary
 */
public class ElormanHomeController extends HttpServlet {

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
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();
        action = request.getParameter(CONFIG.PARAM_ACTION);
        String Service_ID = "";
        String Operation_ID = "";
        RatePlanDTO ratePlan;
        DonationAgentPaymentRespponseDto respponseDto;
        String lang = "en";
        if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
        } else {
            lang = "ar";
        }
        try {
            Service_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
            Operation_ID = request.getParameter(CONFIG.PARAM_OPERATION_ID);
            if (Operation_ID.equals("6") || Operation_ID.equals("7") || Operation_ID.equals("8") || Operation_ID.equals("9") || Operation_ID.equals("104") || Operation_ID.equals("105") || Operation_ID.equals("106")|| Operation_ID.equals("211")|| Operation_ID.equals("212")|| Operation_ID.equals("213")|| Operation_ID.equals("105")|| Operation_ID.equals("151")|| Operation_ID.equals("152")) {
                ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(Service_ID, session.getAttribute(CONFIG.PARAM_PIN).toString());
                respponseDto = MasaryManager.getInstance().getDonationInfo(Integer.parseInt(request.getParameter(CONFIG.OPERATION_ID)), Integer.parseInt(Service_ID), lang);
                session.setAttribute("donationResponse", respponseDto);
                session.setAttribute("ratePlan", ratePlan);
            }
            if (CONFIG.ACTION_ELORMAN_HOME.equals(action)) {
                nextJSP = CONFIG.ELORMAN_Home_Page;
            }

            session.setAttribute(CONFIG.PARAM_SERVICE_ID, Service_ID);
            session.setAttribute(CONFIG.PARAM_OPERATION_ID, Operation_ID);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            //System.out.println(e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        } finally {

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
