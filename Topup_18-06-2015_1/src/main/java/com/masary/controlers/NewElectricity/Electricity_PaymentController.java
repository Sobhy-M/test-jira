/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.NewElectricity;

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
 * @author Abdelsabor
 */
public class Electricity_PaymentController extends HttpServlet {

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
        String lang = "en";
        int service_id;
        int customer_id;
        try {
            /* TODO output your page here. You may use following sample code. */
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }
            service_id = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
            customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());

            if (request.getParameter("action").equals("Commession_inquiry")) {
                Bill_Request bill_Request = new Bill_Request();
                bill_Request.setCUSTOMER_ID(customer_id);
                bill_Request.setAMOUNT(Double.parseDouble(request.getParameter(CONFIG.AMOUNT)));
                bill_Request.setCHANNEL("WEB");
                bill_Request.setLANG(lang);
                Bill_Response inquiry_bill_response = (Bill_Response) session.getAttribute("bill_Response");
                bill_Request.setBILL_REFERENCE_NUMBER(inquiry_bill_response.getBILL_REFERENCE_NUMBER());
                Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().Commession_Inquiry(bill_Request));
                if (bill_Response.getSTATUS_CODE() == 200 || bill_Response.getSTATUS_CODE() == 201 || bill_Response.getSTATUS_CODE() == 202) {
                    session.setAttribute("commession_response", bill_Response);
                    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                    session.setAttribute("BTC", bill_Type);
                    nextJSP = CONFIG.ElectricityPaymentPage;

                } else {
                    nextJSP = CONFIG.ElectricityInquiry;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());
                }

            } else {
                String billing_account = request.getParameter("BILLING_ACCOUNT");
                Bill_Request bill_Request = new Bill_Request();
                bill_Request.setCUSTOMER_ID(customer_id);
                bill_Request.setAMOUNT(Double.parseDouble(request.getParameter(CONFIG.AMOUNT)));
                bill_Request.setCHANNEL("WEB");
                bill_Request.setLANG(lang);

                Bill_Response inquiry_bill_response = (Bill_Response) session.getAttribute("bill_Response");
                bill_Request.setBILL_REFERENCE_NUMBER(inquiry_bill_response.getBILL_REFERENCE_NUMBER());

                MasaryManager.logger.info("Request Payment bill " + bill_Request.toString());
                Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().PAYMENT(bill_Request));

                MasaryManager.logger.info("Response Payment bill " + bill_Response.toString());

                bill_Response.setBILLING_ACCOUNT(billing_account);
//                bill_Response.setAMOUNT(Double.parseDouble(request.getParameter("ImediaBillValue")));

                if (bill_Response.getSTATUS_CODE() == 200 || bill_Response.getSTATUS_CODE() == 201 || bill_Response.getSTATUS_CODE() == 202) {
                    session.setAttribute("bill_Response", bill_Response);
                    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                    session.setAttribute("BTC", bill_Type);
//                request.setAttribute("Fees", fees.trim());
                    nextJSP = CONFIG.ElectricityConfirmation;
                } else {
                    nextJSP = CONFIG.ElectricityInquiry;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());
                }
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);

        } catch (ServletException e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            MasaryManager.logger.error("Exception" + e);
            session.setAttribute("ErrorMessage", e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            MasaryManager.logger.error("Exception" + e);
            session.setAttribute("ErrorMessage", e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            MasaryManager.logger.error("Exception" + e);
            session.setAttribute("ErrorMessage", e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } finally {
            out.close();
        }
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
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
