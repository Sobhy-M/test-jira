/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.agentPayment;

import com.masary.common.CONFIG;
import com.masary.database.dto.DonationAgentPaymentRespponseDto;
import com.masary.database.dto.Donation_AgentPaymentRequestDTO;
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
 * @author y
 */
public class agentPaymentPayController extends HttpServlet {

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
        try {
            if (request.getParameter("payButton") != null) {

                HttpSession session = request.getSession();

                int serviceId = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
                int operationId = 3; /*FOR PAYMENT*/

                int customerId = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString());
                String amount = request.getParameter("amount").toString();
                String repCode = session.getAttribute("repCode").toString(); /*AGENT_IDENTIFIER*/

                String lang = session.getAttribute(CONFIG.lang).toString();
//              String nationalId = session.getAttribute(CONFIG.PARAM_NATIONAL_ID).toString();
//              String password = session.getAttribute(CONFIG.PARAM_PASSWORD).toString() ==null ? "":session.getAttribute(CONFIG.PARAM_PASSWORD).toString();

                Donation_AgentPaymentRequestDTO reqDto = new Donation_AgentPaymentRequestDTO(customerId, "", lang, repCode, "WEB", "", "", serviceId, operationId, 0, amount, 0, "");
                DonationAgentPaymentRespponseDto resDto = MasaryManager.getInstance().do_agent_payment_without_inquiry(reqDto);

                if (resDto.getSTATUS_CODE().equals("200")) {
                    /*Set needed Values in Session*/
                    session.setAttribute(CONFIG.PARAM_Transaction_ID, resDto.getTRANSACTION_ID());
                    session.setAttribute("trxDate", resDto.getDATE());
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(CONFIG.agentPaymentReceiptPage);
                    rd.forward(request, response);
                } else {
                    String msg=resDto.getSTATUS_MESSAGE()+ " " + resDto.getSTATUS_CODE();
                    request.setAttribute("ErrorCode",msg);

//                    request.setAttribute("ErrorCode", "خطأ فى العملية  : " + resDto.getSTATUS_CODE());
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(CONFIG.agentPaymentPayConfirmationPage);
                    rd.forward(request, response);
                }

            } else if (request.getParameter("backButton") != null) {

            }

        } catch (Exception ex) {
            MasaryManager.logger.error(ex.toString());
            request.setAttribute("ErrorCode", "خطأ فى العملية");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(CONFIG.agentPaymentPayConfirmationPage);
            rd.forward(request, response);
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
