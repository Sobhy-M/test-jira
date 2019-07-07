/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.FMF;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.FMFClient;
import com.masary.integration.dto.FMFInquiryResponse;
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
public class FMFHomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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

            if (action.equals(CONFIG.ACTION_FMF_INQUIRY)) {
                MasaryManager.logger.info("Action:- " + action);
                int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute("serv_id", serviceId);
                MasaryManager.logger.info("Service_Id:- " + serviceId);
                nextJSP = CONFIG.PAGE_FMF_HOME;

            } else if (action.equals(CONFIG.ACTION_FMF_GETINFO)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }

                String customerCode = request.getParameter("CUSTOMERCODE");

                MasaryManager.logger.info("FMF Inquiry Request:- " + customerCode);
                String token = session.getAttribute("Token").toString();
                MasaryManager.logger.info("Token:- " + token);
                FMFClient fmfClient = new FMFClient();

                FMFInquiryResponse fmfInquiryResponse = fmfClient.fmfInquiry(customerCode, lang, token);

                if (fmfInquiryResponse.getCustomerCode() == null || fmfInquiryResponse.getIdentificationNo() == 0 || fmfInquiryResponse.getInstallmentNo() == 0) {
                    session.setAttribute("ErrorMessage", lang.equals("ar") ? CONFIG.FMFServiceNotAvailableAr : CONFIG.FMFServiceNotAvailableEn);
                    nextJSP = CONFIG.PAGE_ERRPR;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                } else {
                    session.setAttribute("fmfInquiryResponse", fmfInquiryResponse);
                    nextJSP = CONFIG.PAGE_FMF_INFO;

                }
                MasaryManager.logger.info("FMF Inquiry Response:- " + fmfInquiryResponse);

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
