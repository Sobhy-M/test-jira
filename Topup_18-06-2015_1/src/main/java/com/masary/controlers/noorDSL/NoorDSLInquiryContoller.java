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
public class NoorDSLInquiryContoller extends HttpServlet {

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
            if (action.equals(CONFIG.ACTIONNOORADSLINQUIRY)) {
                int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute("serv_id", serviceId);
                nextJSP = CONFIG.PAGENOOrADSLINQUIRY;
            } else {
                int serviceId = Integer.parseInt(session.getAttribute("serv_id").toString());
                String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
                String billing_account = request.getParameter(CONFIG.PARAM_MSISDN);
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                Bill_Request bill_Request = new Bill_Request();
                bill_Request.setCUSTOMER_ID(customer_id);
                bill_Request.setBILLING_ACCOUNT(billing_account);
                bill_Request.setCHANNEL("WEB");
                bill_Request.setLANG(lang);
                bill_Request.setSERVICE_ID(serviceId);
                Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().Inquiry(bill_Request));
                bill_Response.setBILLING_ACCOUNT(billing_account);
                
//                 bill_Response.setCUSTOMER_NAME(new String(bill_Response.getCUSTOMER_NAME().getBytes("ISO-8859-1")));
                if (bill_Response.getSTATUS_CODE() == 200) {
                    session.setAttribute("bill_Response", bill_Response);
                    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(serviceId));
                    session.setAttribute("BTC", bill_Type);
                    nextJSP = CONFIG.PAGENOOrADSLGETINFO;
                } else {
                    nextJSP = CONFIG.PAGENOOrADSLINQUIRY;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());

                }
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
//            return;
        } catch (Exception e) {
            MasaryManager.logger.error("Exception" + e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        } finally {
//            out.close();
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
