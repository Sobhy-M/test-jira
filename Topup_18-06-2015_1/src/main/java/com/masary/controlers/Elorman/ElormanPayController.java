/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Elorman;

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
 * @author Masary
 */
public class ElormanPayController extends HttpServlet {

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
        String Customer_name = request.getParameter("DonatorName");
        String amount = request.getParameter("donationValue");
        String billingAccount = request.getParameter("DonatorPhone");
        String govenrate = null;
        Donation_AgentPaymentRequestDTO agentPaymentRequestDTO = new Donation_AgentPaymentRequestDTO();
        agentPaymentRequestDTO.setNAME(Customer_name);
        agentPaymentRequestDTO.setCITY("NULL");
        agentPaymentRequestDTO.setAMOUNT(amount);
        agentPaymentRequestDTO.setAGENT_IDENTIFIER(billingAccount);
        agentPaymentRequestDTO.setCHANNEL("WEB");
        agentPaymentRequestDTO.setLANG(lang);
        agentPaymentRequestDTO.setSERVICE_ID(service_id);
        agentPaymentRequestDTO.setOPERATION_ID(Integer.parseInt(session.getAttribute(CONFIG.OPERATION_ID).toString()));
        agentPaymentRequestDTO.setCUSTOMER_ID(customer_id);
        DonationAgentPaymentRespponseDto agentPaymentRespponseDto = MasaryManager.getInstance().do_agent_payment_without_inquiry(agentPaymentRequestDTO);
        if (agentPaymentRespponseDto.getSTATUS_CODE().equals("200")) {
            session.setAttribute("donationPaymentResponse", agentPaymentRespponseDto);
            Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
            session.setAttribute("BTC", bill_Type);
            nextJSP = CONFIG.ACTION_ELORMAN_PAY;
        } else {
            nextJSP = CONFIG.PAGE_ERRPR;
            session.setAttribute("ErrorMessage", CONFIG.getBillErrorCode(session) + " " + agentPaymentRespponseDto.getSTATUS_CODE() + " " + agentPaymentRespponseDto.getSTATUS_MESSAGE());
        }
        return nextJSP;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            action = request.getParameter(CONFIG.PARAM_ACTION);
            String DbNextJSP = DB(request, response);
            if (CONFIG.ACTION_ELORMAN_PAY.equals(action) && DbNextJSP.equals(action)) {
                nextJSP = CONFIG.ELORMAN_Pay_Page;
            } else {
                nextJSP = CONFIG.PAGE_ERRPR;
            }
            session.setAttribute(CONFIG.PAGE, nextJSP);
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
