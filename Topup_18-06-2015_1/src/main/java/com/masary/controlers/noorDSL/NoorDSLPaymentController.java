/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.noorDSL;

import com.masary.common.CONFIG;
import com.masary.database.dto.Bill_Request;
import com.masary.database.dto.Bill_Response;
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
public class NoorDSLPaymentController extends HttpServlet {

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
        HttpSession session = request.getSession();
        String nextJSP = "";
        String action;
        action = request.getParameter(CONFIG.PARAM_ACTION);
        session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());

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

            if (action.equals(CONFIG.ACTIONNOORADSLPayment)) {
                int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute("serv_id", serviceId);
                nextJSP = CONFIG.PAGENOOrADSLPAYMENT;
            } else {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                int service_id = Integer.parseInt(session.getAttribute("serv_id").toString());
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                String fees = request.getParameter("Fees");
                String billing_account = request.getParameter("BILLING_ACCOUNT");
                Bill_Request bill_Request = new Bill_Request();
                bill_Request.setCUSTOMER_ID(customer_id);
                bill_Request.setAMOUNT(Double.parseDouble(request.getParameter("AMOUNT")));
                bill_Request.setCHANNEL("WEB");
                bill_Request.setLANG(lang);
                Bill_Response inquiry_bill_response = (Bill_Response) session.getAttribute("bill_Response");
                bill_Request.setBILL_REFERENCE_NUMBER(inquiry_bill_response.getBILL_REFERENCE_NUMBER());
                MasaryManager.logger.info("Request Payment bill " + bill_Request.toString());
                Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().PAYMENT(bill_Request));
                MasaryManager.logger.info("Response Payment bill " + bill_Response.toString());
                bill_Response.setBILLING_ACCOUNT(billing_account);
                bill_Response.setAMOUNT(Double.parseDouble(request.getParameter("AMOUNT")));
                bill_Response.setCUSTOMER_NAME(new String(request.getParameter("Customer_name").getBytes("ISO-8859-1")));
                bill_Response.setBILL_DATE(request.getParameter("DUE_DATE"));

                if (bill_Response.getSTATUS_CODE() == 200 || bill_Response.getSTATUS_CODE() == 201 || bill_Response.getSTATUS_CODE() == 202) {
                    session.setAttribute("bill_Response", bill_Response);
                    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                    session.setAttribute("BTC", bill_Type);
                    request.setAttribute("Fees", fees.trim());
                    nextJSP = CONFIG.PAGENOOrADSLPAYMENTCONFIRMATION;
                } else {
                    nextJSP = CONFIG.PAGENOOrADSLINQUIRY;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());
                }
            }

//            return;
        } catch (Exception e) {
            MasaryManager.logger.error("Exception" + e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
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

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }
}
