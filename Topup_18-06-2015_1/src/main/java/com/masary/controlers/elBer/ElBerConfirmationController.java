/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.elBer;

import com.masary.common.CONFIG;
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
 * @author hammad
 */
public class ElBerConfirmationController extends HttpServlet {

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
        HttpSession session = request.getSession();
        String nextJSP = "";
        String action;
        String operationId;
        String serviceId;

        try {
            action = request.getParameter(CONFIG.PARAM_ACTION);
            if (CONFIG.ACTION_ELBER_CONFIRMATION.equals(action)) {
                nextJSP = CONFIG.ElBerConfirmationPage;
            }

            operationId = session.getAttribute(CONFIG.OPERATION_ID).toString();
            serviceId = session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString();
            String amount = request.getParameter("donationValue");
            String billingAccount = request.getParameter("DonatorPhone");

            session.setAttribute(CONFIG.PAGE, nextJSP);
            session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
            session.setAttribute(CONFIG.OPERATION_ID, operationId);
            request.setAttribute("donationValue", amount);
            request.setAttribute("DonatorPhone", billingAccount);
            if (Integer.parseInt(amount) < 10 && (operationId.equals("102") || operationId.equals("7"))) {
                session.setAttribute("ErrorMessage", "الحد الأدني للتبرع 10 جنيه بدون كسور");
                nextJSP = CONFIG.PAGE_ERRPR;
            } else if (Integer.parseInt(amount) < 5 && (operationId.equals("1") || operationId.equals("2") || operationId.equals("4") || operationId.equals("5"))) {
                session.setAttribute("ErrorMessage", "الحد الأدني للتبرع 5 جنيه بدون كسور");
                nextJSP = CONFIG.PAGE_ERRPR;

            } else if (Integer.parseInt(amount) < 100 && operationId.equals("8")) {
                session.setAttribute("ErrorMessage", "الحد الأدني للتبرع 100 جنيه بدون كسور");
                nextJSP = CONFIG.PAGE_ERRPR;

            } else if (Integer.parseInt(amount) < 500 && (operationId.equals("3") || operationId.equals("6"))) {
                session.setAttribute("ErrorMessage", "الحد الأدني للتبرع 500 جنيه بدون كسور");
                nextJSP = CONFIG.PAGE_ERRPR;
            }

        } catch (Exception e) {
            //System.out.println(e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
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
