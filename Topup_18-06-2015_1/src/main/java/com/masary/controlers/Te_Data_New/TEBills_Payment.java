/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Te_Data_New;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.TEDataBillsClient;
import com.masary.integration.dto.TEDataBillsRequest;
import com.masary.integration.dto.TEDataBillsResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tasneem
 */
public class TEBills_Payment extends HttpServlet {

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
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();
        action = request.getParameter(CONFIG.PARAM_ACTION);
        try {

            //login authentication
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }
            //get id and set attributes
            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }
            TEDataBillsRequest teRequest = new TEDataBillsRequest();
            String billing_account = request.getParameter("BILLING_ACCOUNT");
            String customer_id = request.getSession().getAttribute(CONFIG.PARAM_PIN).toString();
            teRequest.setLandLine(billing_account);
            teRequest.setCustomerNumber("0");
            String token = session.getAttribute("Token").toString();
            String uuid = session.getAttribute("uuid").toString();
            MasaryManager.logger.info("Token:- " + token);
            TEDataBillsClient tebillsClient = new TEDataBillsClient();
            TEDataBillsResponse tebillsPaymentResponse = tebillsClient.TEPayment(teRequest, token, uuid);
            if (tebillsPaymentResponse.getRequestStatus().equals("SUCCEEDED")) {
                session.setAttribute("tedataPaymentResponse", tebillsPaymentResponse);
                MasaryManager.logger.info("tedataPaymentResponse" + tebillsPaymentResponse);
                nextJSP = CONFIG.PAGE_TEDATA_Bill_Payment_REQ;
                session.setAttribute("uuid", "");
            } else {
                session.setAttribute("ErrorMessage", tebillsPaymentResponse.getRequestStatus());
                MasaryManager.logger.error("ErrorMessage" + tebillsPaymentResponse.getRequestStatus());
                nextJSP = CONFIG.PAGE_ERRPR;
                session.setAttribute("uuid", "");
            }
            MasaryManager.logger.info("TE Data Payment Response:- " + tebillsPaymentResponse);
        } catch (Exception ex) {
            if (ex.getMessage().equals("1171")) {
                session.setAttribute("ErrorMessage", "1171".concat("  خطأ من مزود الخدمة برجاء المحاولة في وقت لاحق "));
            } else if (ex.getMessage().equals("1172")) {
                session.setAttribute("ErrorMessage", "1172".concat("خطأ من مزود الخدمة برجاء المحاولة في وقت لاحق   "));
            } else if (ex.getMessage().equals("1173")) {
                session.setAttribute("ErrorMessage", "1173".concat("غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة   "));
            } else if (ex.getMessage().equals("1174")) {
                session.setAttribute("ErrorMessage", "1174".concat("غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة    "));
            } else if (ex.getMessage().equals("1175")) {
                session.setAttribute("ErrorMessage", "1175".concat("غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة    "));
            } else if (ex.getMessage().equals("1176")) {
                session.setAttribute("ErrorMessage", "1176".concat("خطأ من مزود الخدمة برجاء المحاولة في وقت لاحق   "));
            } else if (ex.getMessage().equals("1177")) {
                session.setAttribute("ErrorMessage", "1177".concat("غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة   "));
            } else if (ex.getMessage().equals("1178")) {
                session.setAttribute("ErrorMessage", "1178".concat("غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة   "));
            } else if (ex.getMessage().equals("1179")) {
                session.setAttribute("ErrorMessage", "1179".concat("غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة   "));
            } else if (ex.getMessage().equals("11710")) {
                session.setAttribute("ErrorMessage", "11710".concat("عفوا هذا الرقم غير صحيح   "));
            } else if (ex.getMessage().equals("11711")) {
                session.setAttribute("ErrorMessage", "11711".concat("عفوا هذا الرقم غير صحيح   "));
            } else if (ex.getMessage().equals("11712")) {
                session.setAttribute("ErrorMessage", "11712".concat("خطأ من مزود الخدمة برجاء المحاولة في وقت لاحق   "));
            } else if (ex.getMessage().equals("11713")) {
                session.setAttribute("ErrorMessage", "11713".concat("غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة   "));
            } else if (ex.getMessage().equals("11714")) {
                session.setAttribute("ErrorMessage", "11714".concat("غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة   "));
            } else if (ex.getMessage().equals("11715")) {
                session.setAttribute("ErrorMessage", "11715".concat("عفوا لا يوجد فواتير مستحقة لهذا الرقم   "));
            } else if (ex.getMessage().equals("11716")) {
                session.setAttribute("ErrorMessage", "11716".concat("غير مسموح بالدفع برجاء الرجوع الى مزود الخدمة  "));
            } else if (ex.getMessage().equals("52")) {
                session.setAttribute("ErrorMessage", "52".concat("غير مسموح بالدفع لنفس الرقم خلال 24 ساعة  "));
            } else {
                session.setAttribute("ErrorMessage",CONFIG.errorTransactionar);
            }
            nextJSP = CONFIG.PAGE_ERRPR;
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        }

        // nextJSP = CONFIG.PAGE_TEDATA_Bill_Payment_REQ;
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
