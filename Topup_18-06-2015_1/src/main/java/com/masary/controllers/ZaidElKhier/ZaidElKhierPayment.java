/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controllers.ZaidElKhier;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.ZaidElKhierClient;
import com.masary.integration.dto.ZaidElKhierCategoriesResponse;
import com.masary.integration.dto.ZaidElKhierPaymentResponse;
import com.masary.integration.dto.ZaidElKhierRequest;
import com.masary.integration.dto.zaidElkierSubCategoryRepresentation;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author user
 */
public class ZaidElKhierPayment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nextPage = "";
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        try {
            if (!isLogin(session)) {

                nextPage = CONFIG.PAGE_LOGIN;
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
                dispatcher.forward(request, response);
                return;
            }

            if (action.equals("confirm")) {

                ZaidElKhierRequest zaidElKhierRequest = new ZaidElKhierRequest();
                List<zaidElkierSubCategoryRepresentation> subCategories = (List<zaidElkierSubCategoryRepresentation>) session.getAttribute("subServices");

                String subServiceName = "";
                for (ZaidElKhierCategoriesResponse category : (ZaidElKhierCategoriesResponse[]) session.getAttribute("inquiryResponse")) {
                    if (category.getCategoryId() == Integer.parseInt(request.getParameter("serviceId"))) {
                        subServiceName = category.getCategoryName();
                        for (zaidElkierSubCategoryRepresentation subCategory : subCategories) {
                            if (subCategory.getSubServiceId() == Integer.parseInt(request.getParameter("subServiceId")) && subCategories.size() != 1) {
                                subServiceName += " - " + subCategory.getSubCategoryName();
                            }
                        }
                    }
                }
                zaidElKhierRequest.setAmount(Double.parseDouble(request.getParameter("amount")));
                zaidElKhierRequest.setMobileNumber(request.getParameter("msisdn"));
                zaidElKhierRequest.setAppliedFees(Double.parseDouble(request.getParameter("Fees")));
                zaidElKhierRequest.setCommission(Double.parseDouble(request.getParameter("Commession")));
                zaidElKhierRequest.setDeductedAmount(Double.parseDouble(request.getParameter("DeductedAmount")));
                zaidElKhierRequest.setSubCategoryName(subServiceName);
                zaidElKhierRequest.setToBePaid(Double.parseDouble(request.getParameter("ToBePaid")));
                zaidElKhierRequest.setSubServiceID(Integer.parseInt(request.getParameter("subServiceId")));
                zaidElKhierRequest.setConfirmed(false);
                session.setAttribute("zaidElKhierDto", zaidElKhierRequest);

                nextPage = ZaidElKhierProperties.PAGE_ZaidElKhier_PAYMENT;

            } else {

                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                ZaidElKhierRequest zaidElKhierRequest = (ZaidElKhierRequest) session.getAttribute("zaidElKhierDto");
                
                CloseableHttpClient httpClient = HttpClients.createDefault();
            	HttpGet httpGet = new HttpGet();
                
                ZaidElKhierPaymentResponse zaidElKhierPaymentResponse = new ZaidElKhierClient(httpClient, httpGet, MasaryManager.logger).zaidElKhierPayment(zaidElKhierRequest, lang, session.getAttribute("Token").toString());
                
                if (zaidElKhierPaymentResponse == null) {
                    MasaryManager.logger.info("ErrorMessage " + zaidElKhierPaymentResponse);
                    session.setAttribute("ErrorMessage", zaidElKhierPaymentResponse);
                    nextPage = CONFIG.PAGE_ERRPR;
                } else {
                    zaidElKhierRequest.setConfirmed(true);
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date dueDate = new Date(zaidElKhierPaymentResponse.getUpdateDate());
                    String date = dt.format(dueDate);
                    session.setAttribute("date", date);
                    session.setAttribute("zaidElKhierPaymentResponse", zaidElKhierPaymentResponse);
                    nextPage = ZaidElKhierProperties.PAGE_ZaidElKhier_PAYMENT;
                }

            }

        } catch (Exception ex) {
            MasaryManager.logger.info("ErrorMessage " + ex.getMessage());
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextPage = CONFIG.PAGE_ERRPR;

        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextPage);
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
