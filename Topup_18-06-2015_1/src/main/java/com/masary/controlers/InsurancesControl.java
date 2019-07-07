/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author ELNAGDY
 */
public class InsurancesControl extends HttpServlet {

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
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();
        action = request.getParameter(CONFIG.PARAM_ACTION);

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
            if (CONFIG.ACTION_Make_Insurance.equals(action)) {
                nextJSP = CONFIG.PAGE_GIG;
            }
            if (CONFIG.ACTION_Make_CONFIRMATION_Insurance.equals(action)) {
                nextJSP = CONFIG.PAGE_GIG_Confirmation;
            }
            if (CONFIG.ACTION_RESULT_Insurance.equals(action)) {
                String customerId = (String) session.getAttribute(CONFIG.PARAM_PIN);
                String certificationNumber = request.getParameter("CertificateNumber");
                String passportNo = request.getParameter("PassPortNumber");
                String firstName = request.getParameter("FirstName");
                String middleName = request.getParameter("MiddleName");
                String lastName = request.getParameter("LastName");
                String birthdate = request.getParameter("BirthDate");
                String gender = request.getParameter("Gender");
                String startDate = request.getParameter("StartDate");
                String endDate = request.getParameter("endDate");
                String period = request.getParameter("PeriodOfInsurance");
                String area = request.getParameter("AreaOfCover");
                String amount = request.getParameter("amount");
                String phone = request.getParameter("phone");
                String Address = request.getParameter("Address");
                String City = request.getParameter("City");
                int transactionId = MasaryManager.getInstance().addTravelPolicy(passportNo, firstName, middleName, lastName, birthdate, gender, startDate, endDate, period, area, customerId, amount, phone, Address, City);
                if (transactionId > 0) {
                    session.setAttribute(CONFIG.PARAM_Transaction_ID, transactionId);
                    nextJSP = CONFIG.PAGE_GIG_RESULT;
                } else {
                    session.setAttribute("ErrorMessage", CONFIG.errorTransaction);
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
                //System.out.println("TransactionId" + transactionId);
            }
            session.setAttribute(CONFIG.PAGE, nextJSP);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception e) {
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

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }
}
