/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.ElkasrEleeny;

import com.masary.common.CONFIG;
import com.masary.database.dto.DonationAgentPaymentRespponseDto;
import com.masary.database.dto.Donation_AgentPaymentRequestDTO;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Abdelsabor
 */
public class ElkasrEleenyPaymentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String DB(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        String lang = "en";
        String nextJSP = "";
        if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
        } else {
            lang = "ar";
        }
        int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
        int service_id = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
        String amount = request.getParameter("Amount");
        String billingAccount = request.getParameter("PhoneNumber");
        Donation_AgentPaymentRequestDTO agentPaymentRequestDTO = new Donation_AgentPaymentRequestDTO();
        agentPaymentRequestDTO.setCITY("NULL");
        agentPaymentRequestDTO.setAMOUNT(amount);
        agentPaymentRequestDTO.setAGENT_IDENTIFIER(billingAccount);
        agentPaymentRequestDTO.setCHANNEL("WEB");
        agentPaymentRequestDTO.setLANG(lang);
        agentPaymentRequestDTO.setSERVICE_ID(service_id);
        agentPaymentRequestDTO.setCUSTOMER_ID(customer_id);
        DonationAgentPaymentRespponseDto agentPaymentRespponseDto = MasaryManager.getInstance().do_agent_payment_without_inquiry(agentPaymentRequestDTO);
        if (agentPaymentRespponseDto.getSTATUS_CODE().equals("200") || agentPaymentRespponseDto.getSTATUS_CODE().equals("202")) {
            request.setAttribute("donationPaymentResponse", agentPaymentRespponseDto);
            Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
            session.setAttribute("BTC", bill_Type);
            nextJSP = CONFIG.ElKasrEleenyResultPage;
        } else {
            nextJSP = CONFIG.PAGE_ERRPR;
            session.setAttribute("ErrorMessage", CONFIG.getBillErrorCode(session) + " " + agentPaymentRespponseDto.getSTATUS_CODE() + " " + agentPaymentRespponseDto.getSTATUS_MESSAGE());
        }
        return nextJSP;
    }

    private boolean validateAmount(double amount) {
        if (amount == Math.ceil(amount)) {
            if ((amount <= 9999999 && amount >= 5)) {
                return true;
            }

        }
        return false;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nextJSP = "";
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher;
        try {
            boolean validateAmount = validateAmount(Double.parseDouble(request.getParameter("Amount")));
            if (validateAmount == false) {
                session.setAttribute("ErrorMessage", "الحد الأدنى للتبرع خمسة جنيهات بدون كسور");
                throw new Exception("الحد الأدنى للتبرع خمسة جنيهات بدون كسور");

            } else {
                nextJSP = DB(request, response);
                dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            session.setAttribute("ErrorMessage", e.getMessage());
            dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
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
