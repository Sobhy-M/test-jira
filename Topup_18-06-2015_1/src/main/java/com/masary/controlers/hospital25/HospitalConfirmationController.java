/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.hospital25;

import com.masary.common.CONFIG;
import java.io.IOException;
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
public class HospitalConfirmationController extends HttpServlet {

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
            if (CONFIG.ACTION_HOSPITAL_25_CONFIRMATION.equals(action)) {
                nextJSP = CONFIG.Hospital25PageConfirmationPage;
            }
            
            operationId = session.getAttribute(CONFIG.OPERATION_ID).toString();
            serviceId = session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString();
            String amount = request.getParameter("donationValue");
            String billingAccount = request.getParameter("DonatorPhone");
            
            int intAmount = Integer.parseInt(amount);
            if(intAmount < 0){
                session.setAttribute("ErrorMessage", "لا يمكن التبرع بأقل من 1 جنيه");
                nextJSP = CONFIG.PAGE_ERRPR;
            }else{
                session.setAttribute(CONFIG.PAGE, nextJSP);            
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                session.setAttribute(CONFIG.OPERATION_ID, operationId);            
                request.setAttribute("donationValue", amount);
                request.setAttribute("DonatorPhone", billingAccount);
            }

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
