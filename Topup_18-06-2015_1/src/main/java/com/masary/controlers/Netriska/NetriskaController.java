/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Netriska;

import com.masary.common.CONFIG;
import com.masary.integration.NetriskaClient;
import com.masary.integration.dto.NetriskaInquiryResponse;
import com.masary.integration.dto.NetriskaPaymentRequest;
import com.masary.integration.dto.NetriskaPaymentResponse;
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
 * @author user
 */
public class NetriskaController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String nextJSP = "";
        String action;
        int serviceId;
        try {
            /* TODO output your page here. You may use following sample code. */
            action = request.getParameter(CONFIG.PARAM_ACTION);
            if (CONFIG.ACTION_NETRISKA.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                nextJSP = CONFIG.NETRISKA_COURSES_PAGE;
                
            } else if (CONFIG.ACTION_NETRISKA_CONFIRM.equals(action)) {
                String mobile = request.getParameter("DonatorPhone");
                String amount = request.getParameter("donationValue");
          //      boolean sendTokenHeader = Boolean.parseBoolean(request.getSession().getAttribute("sendTokenHeader").toString());
                String serviceType = request.getParameter("EduServiceType");
                if(serviceType.equals("COURSES"))
                {
                    String token = session.getAttribute("Token").toString();
                    NetriskaClient netriskaClient = new NetriskaClient();
                    NetriskaInquiryResponse resp =  netriskaClient.netriskaInquiry(mobile, amount, "en", token, request.getRemoteAddr());
                    session.setAttribute("validationResp", resp);
                    session.setAttribute("mobile", mobile);
                    nextJSP = CONFIG.NETRISKA_CONFIRMATION;
                }
                else
                {
                    session.setAttribute("ErrorMessage", "لم يتم تفعيل الخدمة بناء على طلب نيتريسكا ");
                    nextJSP = CONFIG.PAGE_ERRPR; 
                }
            } else {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                String mobile = request.getParameter("DonatorPhone");
                String amount = request.getParameter("txnValue");
               // boolean sendTokenHeader = Boolean.parseBoolean(request.getSession().getAttribute("sendTokenHeader").toString());
                NetriskaPaymentRequest go = new NetriskaPaymentRequest();
                go.setMobileNumber(mobile);
                go.setAmount(Double.parseDouble(amount));

                String token = session.getAttribute("Token").toString();
                NetriskaClient netriskaClient = new NetriskaClient();
                NetriskaPaymentResponse resp =  netriskaClient.netriskaPayment(go, "en", token, request.getRemoteAddr());
                
                if(resp.getRequestStatus().contains("SUCCEEDED")){ 
                    session.setAttribute("payResponse", resp);
                    nextJSP = CONFIG.NETRISKA_PAYMENT;
                }else{
                    session.setAttribute("ErrorMessage", resp.getRequestStatus());
                    nextJSP = CONFIG.PAGE_ERRPR;                   
                }

            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } finally {
            out.close();
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

}
