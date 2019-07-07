/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.TelecomeMobileVoucher;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.TelecomeVoucherClient;
import com.masary.integration.dto.TelecomeDenominationsDTO;
import com.masary.integration.dto.TelecomeVoucherPaymentRequest;
import com.masary.integration.dto.TelecomeVoucherPaymentResponse;
import com.masary.utils.BuildUUID;
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
 * @author Ahmed Khaled
 */
public class TelecomeEgyptVoucherController extends HttpServlet {

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

            if (action.equals(CONFIG.ACTION_TELECOMEEGYPT_HOME)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                MasaryManager.logger.info("Action:- " + action);
                int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute("serv_id", serviceId);
                MasaryManager.logger.info("Service_Id:- " + serviceId);
                serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                String token = session.getAttribute("Token").toString();
                BuildUUID build_uuid = new BuildUUID();
                String uuid = build_uuid.CreateUUID();
                uuid = uuid + request.getRemoteAddr() + serviceId ;
                request.getSession().setAttribute("uuid", uuid);

                MasaryManager.logger.info("Token:- " + token);
                MasaryManager.logger.info("UUID String:- " + uuid);
                TelecomeVoucherClient telecomeVoucherClient = new TelecomeVoucherClient();

                TelecomeDenominationsDTO[] telecomeDenominationsDTO = telecomeVoucherClient.telecomeGetDemominations(lang, uuid, token, request.getRemoteAddr());

                session.setAttribute("telecomeDenominationsDTO", telecomeDenominationsDTO);

                nextJSP = CONFIG.PAGE_TELECOMEEGYPT_HOMEPAGE;
                
                //kill uuid
                session.setAttribute("uuid", "");

            } else if (action.equals(CONFIG.ACTION_TELECOMEEGYPT_CONFIRMATION)) {
                int serviceId = Integer.parseInt(session.getAttribute("SERVICE_ID").toString());
                String amount = request.getSession().getAttribute("amount").toString();
                session.setAttribute("voucherAmount", amount);
                session.setAttribute("SERVICE_ID", serviceId);

                BuildUUID build_uuid = new BuildUUID();
                String uuid = build_uuid.CreateUUID();
                uuid = uuid + request.getRemoteAddr() + serviceId ;
                request.getSession().setAttribute("uuid", uuid);
                nextJSP = CONFIG.PAGE_TELECOMEEGYPT_CONFIRMATIONPAGE;
           

            } else if (action.equals(CONFIG.ACTION_TELECOMEEGYPT_PAYMENT)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                MasaryManager.logger.info("Action:- " + action);
                
                String token = session.getAttribute("Token").toString();
                String uuid = session.getAttribute("uuid").toString();
                MasaryManager.logger.info("Token:- " + token);
                MasaryManager.logger.info("uuid:- " + uuid);

                String denominationId = session.getAttribute("denoId").toString();
                TelecomeVoucherPaymentRequest voucherPaymentRequest = new TelecomeVoucherPaymentRequest();
                voucherPaymentRequest.setDenominationId(Long.parseLong(denominationId));

                TelecomeVoucherClient telecomeVoucherClient = new TelecomeVoucherClient();

                TelecomeVoucherPaymentResponse telecomeVoucherPaymentResponse = telecomeVoucherClient.telecomeVoucherPayment(voucherPaymentRequest, lang, uuid, token, request.getRemoteAddr());

                if (telecomeVoucherPaymentResponse.getGlobalTrxId() != null || telecomeVoucherPaymentResponse.getVoucherSerial() != null) {
                    session.setAttribute("telecomeVoucherPaymentResponse", telecomeVoucherPaymentResponse);
                    session.setAttribute("message", "لقد تم تنفيذ العملية بنجاح");
                    nextJSP = CONFIG.PAGE_TELECOMEEGYPT_PAYMENTPAGE;
                    session.setAttribute("uuid", "");
                } else {
                    session.setAttribute("ErrorMessage", telecomeVoucherPaymentResponse.getRequestStatus());
                    nextJSP = CONFIG.PAGE_ERRPR;
                    session.setAttribute("uuid", "");
                }

            }
        } catch (Exception e) {
            MasaryManager.logger.error("Exception" + e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
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
