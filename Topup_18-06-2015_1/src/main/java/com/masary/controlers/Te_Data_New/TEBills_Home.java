/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Te_Data_New;

import org.apache.log4j.Logger;
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.TEDataBillsClient;
import com.masary.integration.dto.TEDataBillsRequest;
import com.masary.integration.dto.TEDataBillsResponse;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.masary.utils.BuildUUID;

/**
 *
 * @author Tasneem
 */
public class TEBills_Home extends HttpServlet {

    Logger logger = Logger.getLogger(TEBills_Home.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
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
            //action inquiry page: get service-id for request
            if (CONFIG.ACTION_CUSTOMER_Bill_Inquiry.equals(action)) {
                session = request.getSession();
                MasaryManager.logger.info("Action:- " + action);
                int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute("serv_id", serviceId);
                MasaryManager.logger.info("Service_Id:- " + serviceId);
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                BuildUUID build_uuid = new BuildUUID();
                String uuid = build_uuid.CreateUUID();
                uuid = uuid + serviceId;
                session.setAttribute("uuid", uuid);
                nextJSP = CONFIG.PAGE_TEDATA_Bill_Inquiry_REQ;
            } //action info page: get customer info
            else if (CONFIG.ACTION_Get_Bill_Inquiry_Res.equals(action)) {
                TEDataBillsRequest teRequest = new TEDataBillsRequest();
                String billing_account = request.getParameter("BILLING_ACCOUNT");
                teRequest.setLandLine(billing_account);
                String customer_id = request.getSession().getAttribute(CONFIG.PARAM_PIN).toString();
                teRequest.setCustomerNumber(customer_id);
                String token = session.getAttribute("Token").toString();
                String UUId = (String) session.getAttribute("uuid");
                MasaryManager.logger.info("Token:- " + token);
                TEDataBillsClient teClient = new TEDataBillsClient();
                TEDataBillsResponse teInquiryResponse = teClient.TEInquiry(teRequest, token, UUId);
                //code for new object
                if (teInquiryResponse.getLandLine() == null || teInquiryResponse.getCustomerName() == null || teInquiryResponse.getGlobalTrxId() == null) {
                    session.setAttribute("ErrorMessage", teInquiryResponse.getRequestStatus());
                    nextJSP = CONFIG.PAGE_ERRPR;
                    //killing the session
                    session.setAttribute("uuid", "");
                } else {
                    session.setAttribute("tedataInquiryResponse", teInquiryResponse);
                    session.setAttribute("phoneNumber", request.getParameter(CONFIG.PARAM_MSISDN));
                    nextJSP = CONFIG.PAGE_TEDATA_Bill_Inquiry_RESP;
                    session.setAttribute("uuid", "");
                }
                MasaryManager.logger.info("TE Data Inquiry Response:- " + teInquiryResponse);
            }
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
                session.setAttribute("ErrorMessage", CONFIG.errorTransactionar);
            }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TEBills_Home.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TEBills_Home.class.getName()).log(Level.SEVERE, null, ex);
        }
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
