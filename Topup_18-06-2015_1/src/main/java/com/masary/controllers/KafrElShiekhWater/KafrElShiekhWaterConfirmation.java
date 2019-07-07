/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controllers.KafrElShiekhWater;

import com.google.common.base.Preconditions;
import com.masary.common.CONFIG;
import com.masary.integration.KafrElShiekhWaterClient;
import com.masary.integration.dto.KafrElShiekhWaterBillsSuggetionsResponse;
import com.masary.integration.dto.KafrElShiekhWaterInquiryResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class KafrElShiekhWaterConfirmation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String nextJSP = "";
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");
        try {
            if (!isLogin(session)) {
                nextJSP = CONFIG.PAGE_LOGIN;
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                dispatcher.forward(request, response);
                return;
            }

            String lang = "en";
            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            } else {
                lang = "ar";
            }

            String paymentType = request.getParameter("radio");
            String validationId = request.getParameter("validationId");
            KafrElShiekhWaterClient kafrElShiekhWaterClient = new KafrElShiekhWaterClient();
            if (paymentType.equals("full")) {
                KafrElShiekhWaterInquiryResponse kafrElShiekhWaterInquiryResponse = new KafrElShiekhWaterInquiryResponse();
                kafrElShiekhWaterInquiryResponse.setValidationId(validationId);
                kafrElShiekhWaterInquiryResponse.setSubscriberName(request.getParameter("subscriberName"));
                kafrElShiekhWaterInquiryResponse.setBillsNumber(Integer.parseInt(request.getParameter("billsNumber")));
                kafrElShiekhWaterInquiryResponse.setServiceAmount(Double.parseDouble(request.getParameter("billsTotalAmountEGP")));
                kafrElShiekhWaterInquiryResponse.setAppliedFees(Double.parseDouble(request.getParameter("appliedFees")));
                kafrElShiekhWaterInquiryResponse.setToBepaid(Double.parseDouble(request.getParameter("toBepaid")));
                request.setAttribute("kafrElShiekhWaterInquiryResponse", kafrElShiekhWaterInquiryResponse);
                nextJSP = rb.getString("Page_KafrElShiekhWater_Confirmation");
            } else if (paymentType.equals("partial")) {
                KafrElShiekhWaterBillsSuggetionsResponse kafrElShiekhWaterBillsSuggetionsResponse = kafrElShiekhWaterClient.billsSuggestions(validationId, Double.parseDouble(request.getParameter("PaidAmount")), lang, session.getAttribute("Token").toString());
                Preconditions.checkNotNull(kafrElShiekhWaterBillsSuggetionsResponse);
                if (kafrElShiekhWaterBillsSuggetionsResponse.getFirstBillsAmount() == kafrElShiekhWaterBillsSuggetionsResponse.getSecondbillsAmount()) {
                    KafrElShiekhWaterInquiryResponse kafrElShiekhWaterInquiryResponse = kafrElShiekhWaterClient.commissionInquiry(validationId, kafrElShiekhWaterBillsSuggetionsResponse.getFirstBillsAmount(), lang, session.getAttribute("Token").toString());
                    Preconditions.checkNotNull(kafrElShiekhWaterInquiryResponse);
                    request.setAttribute("kafrElShiekhWaterInquiryResponse", kafrElShiekhWaterInquiryResponse);
                    nextJSP = rb.getString("Page_KafrElShiekhWater_Confirmation");
                } else {
                    request.setAttribute("kafrElShiekhWaterBillsSuggetionsResponse", kafrElShiekhWaterBillsSuggetionsResponse);
                    nextJSP = rb.getString("Page_KafrElShiekhWater_Suggestions");
                }

            } else if (paymentType.equals("suggest")) {
                double selectedBills = Double.valueOf(request.getParameter("selectedBills"));
                KafrElShiekhWaterInquiryResponse kafrElShiekhWaterInquiryResponse = kafrElShiekhWaterClient.commissionInquiry(validationId, selectedBills, lang, session.getAttribute("Token").toString());
                Preconditions.checkNotNull(kafrElShiekhWaterInquiryResponse);
                request.setAttribute("kafrElShiekhWaterInquiryResponse", kafrElShiekhWaterInquiryResponse);
                nextJSP = rb.getString("Page_KafrElShiekhWater_Confirmation");
            }

        } catch (Exception e) {
            session.setAttribute("ErrorMessage", e.getMessage() + e);
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
