/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.database.dto.ElectricityBillPaymentResult;
import com.masary.database.dto.GenericElectricityBill;
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
 * @author Ahmed Saeed
 */
public class SouthDeltaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SouthDeltaController.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String nextJSP = "";
        String action;

        HttpSession session = request.getSession();

        Gson gson = new Gson();

        String customerIdS = request.getSession().getAttribute(CONFIG.PARAM_PIN).toString();

        String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);

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

            if (CONFIG.ACTION_CUSTOMER_Bill_Inquiry.equals(action))
            {
                String BTC = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                session.setAttribute("serv_id", BTC);
                nextJSP = CONFIG.PAGE_Electricity_inquiry;
//                session.setAttribute("serviceId", serviceId);
            }else if (CONFIG.ACTION_Delta_Electricity_pay.equals(action)) {

                nextJSP = CONFIG.PAGE_Electricity_PAYMENT;

            } else if (CONFIG.ACTION_Delta_Electricity_Inq.equals(action)) {

                Long participantNumber = Long.parseLong(request.getParameter("BILLING_ACCOUNT"));

                GenericElectricityBill genericBills = MasaryManager.getInstance().doBillInquiry(participantNumber, customerIdS, serviceId);

                Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(serviceId));
                session.setAttribute("BTC", bill_Type);

                if (genericBills.getStatusCode().equals("200")) {

                    session.setAttribute("EBWCR", genericBills);
//                    session.setAttribute("serviceId", serviceId);
                    session.setAttribute("billId", genericBills.getAcquiredBills().get(0).getBillInquiryId());
                    session.setAttribute("billValue", genericBills.getAcquiredBills().get(0).getBillValue());
                    session.setAttribute("participantNumber", genericBills.getAcquiredBills().get(0).getParticipantNumber());
                    session.setAttribute("fees", genericBills.getAcquiredBills().get(0).getFees());
                    session.setAttribute("participantName", genericBills.getAcquiredBills().get(0).getParticipantName());

                    nextJSP = CONFIG.PAGE_Electricity_info;
                } else {
                    nextJSP
                            = CONFIG.PAGE_Electricity_inquiry;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + genericBills.getStatusMessage());
                }

            } else if (CONFIG.ACTION_payment_Inquiry_Req_Confirm.equals(action)) {

                long billId = Long.parseLong(request.getSession().getAttribute("billId").toString());

                double billValue = Double.parseDouble(request.getSession().getAttribute("billValue").toString());

                String participantNumber = request.getSession().getAttribute("participantNumber").toString();

                ElectricityBillPaymentResult paymentResult = MasaryManager.getInstance().doOfflinePayment(Long.parseLong(customerIdS), billId, billValue, Long.parseLong(serviceId));

                if (paymentResult != null && paymentResult.getSTATUS_CODE().equals("200")) {

                    session.setAttribute("PayTxn", paymentResult);
                    nextJSP = CONFIG.PAGE_Elect_PAY_confirm;
                    logger.info("bill with id " + billId + "  has been paid for participant with Id " + participantNumber);
                } else {
                    nextJSP = CONFIG.PAGE_Electricity_inquiry;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + paymentResult.getSTATUS_MESSAGE());
                }

            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            logger.error(ex.toString());
            nextJSP = CONFIG.PAGE_Electricity_inquiry;
            request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session));
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
