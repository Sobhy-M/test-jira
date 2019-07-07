/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.agentPayment;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author y
 */
public class agentPaymetGetInfoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String validationMessage = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            if (request.getParameter("payButton") != null) {
                HttpSession session = request.getSession();

                session.setAttribute("companyCode", request.getParameter("companyCode"));
                session.setAttribute("repCode", request.getParameter("repCode"));
                session.setAttribute(CONFIG.AMOUNT, request.getParameter("amount"));

                if (checkValidation(request) == true) {

//                    int serviceId = Integer.parseInt(request.getParameter("companyCode"));
//                    int customerId = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString());
//                    float amount = Float.parseFloat(request.getParameter("amount"));
//                    String commFees = MasaryManager.getInstance().getfeesInquiry(customerId, serviceId, amount);
//                    String[] commesionFees = commFees.split("-");
//                    session.setAttribute(CONFIG.FEES,commesionFees[1] );
//                    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(String.valueOf(serviceId), String.valueOf(customerId));
//                    session.setAttribute(CONFIG.FEES, String.valueOf(ratePlan.getCommission()));
                    if (Integer.parseInt(request.getParameter("amount")) > 50000 &&Integer.parseInt(request.getParameter("amount")) <1000 && request.getParameter("companyCode").equals("18235")) {
                        request.setAttribute("ErrorCode", validationMessage);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher(CONFIG.PAGE_ERRPR);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher(CONFIG.agentPaymentPayConfirmationPage);
                        rd.forward(request, response);
                    }
                } else {
                    request.setAttribute("ErrorCode", validationMessage);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(CONFIG.agentPaymentGetInfoPage);
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("ErrorCode", "خطأ فى العملية");
                RequestDispatcher rd = getServletContext().getRequestDispatcher(CONFIG.agentPaymentGetInfoPage);
            }
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            request.setAttribute("ErrorCode", "خطأ فى العملية");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(CONFIG.agentPaymentGetInfoPage);
            rd.forward(request, response);
        } finally {
            out.close();
        }
    }

    private boolean checkValidation(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String companyCode = request.getParameter("companyCode");
        String repCode = request.getParameter("repCode");
        String usernName = session.getAttribute(CONFIG.PARAM_PIN).toString();
        AgentInquiry inquiry = new AgentInquiry();
        inquiry.run(companyCode, repCode, usernName);
        if (inquiry.getResponseCode() == HttpURLConnection.HTTP_OK) {
            session.setAttribute("companyName", inquiry.getAgentName());
            session.setAttribute("repName", inquiry.getRepName());
            return true;
        } else {
            validationMessage = inquiry.getResponseMessage();
            return false;
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
