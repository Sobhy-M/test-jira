/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.MisrElkher;

import com.masary.common.CONFIG;
import com.masary.database.dto.DonationAgentPaymentRespponseDto;
import com.masary.database.dto.Donation_AgentPaymentRequestDTO;
import com.masary.database.dto.Masary_Bill_Type;
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
 * @author AYA
 */
public class MisrElKheirController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String nextJSP = "";
        String action;
        int serviceId;
        try {
            /* TODO output your page here. You may use following sample code. */
            action = request.getParameter(CONFIG.PARAM_ACTION);
            if (CONFIG.ACTION_MISR_ELKHEIR.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                session.setAttribute(CONFIG.PROVIDER_ID, request.getParameter(CONFIG.PROVIDER_ID));
                session.setAttribute(CONFIG.OPERATION_ID, request.getParameter(CONFIG.OPERATION_ID));
                DonationAgentPaymentRespponseDto respponseDto = MasaryManager.getInstance().getDonationInfo(Integer.parseInt(request.getParameter(CONFIG.OPERATION_ID)), serviceId, lang);
                session.setAttribute("donationResponse", respponseDto);
                nextJSP = CONFIG.MisrElKherHomePage;
            } else if (CONFIG.ACTION_GET_DONATION_CONFIRMATION.equals(action)) {
                String amount =request.getParameter(CONFIG.AMOUNT);
                if (Double.parseDouble(amount) < 20.0 && request.getParameter(CONFIG.OPERATION_ID).equals("98")) {
                    request.setAttribute("ErrorCode", "	أقل قيمة يمكن قبولها 20 جنيه ");
                    nextJSP = CONFIG.MisrElKherHomePage;
                } else {

                    nextJSP = CONFIG.MisrElKherConfirmation;
                }
            } else {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
//                String program = new String(request.getParameter(CONFIG.PROGRAM).getBytes("ISO-8859-1"), "utf-8");
                String amount = request.getParameter(CONFIG.AMOUNT);
                String billingAccount = request.getParameter("BILLING_ACCOUNT");
                Donation_AgentPaymentRequestDTO agentPaymentRequestDTO = new Donation_AgentPaymentRequestDTO();
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
//                    request.setAttribute("Fees", fees.trim());
                    nextJSP = CONFIG.MisrElKherPaymentPage;

                } else {
                    nextJSP = CONFIG.MisrElKherHomePage;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + agentPaymentRespponseDto.getSTATUS_CODE() + " " + agentPaymentRespponseDto.getSTATUS_MESSAGE());
                }

            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
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
