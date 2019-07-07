/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Valu;

import com.masary.common.CONFIG;
import com.masary.integration.ValuClient;
import com.masary.integration.dto.ValuPaymentRequest;
import com.masary.integration.dto.ValuPaymentResponse;
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
 * @author amira
 */
public class ValuPaymentController extends HttpServlet {

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
        String lang = "";
        String nextJSP = "";
        try {
            /* TODO output your page here. You may use following sample code. */

            if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                lang = "en";
            } else {
                lang = "ar";
            }
            String token = session.getAttribute("Token").toString();
            String serviceId= session.getAttribute("serv_id").toString();

            BuildUUID build_uuid =new BuildUUID ();
            String uuid = session.getAttribute("uuid").toString();
            
            if(uuid.equals("")){
            	uuid =build_uuid.CreateUUID();
            	uuid=uuid+serviceId;
            	session.setAttribute("uuid", uuid);
            }
            
                     
            ValuClient valuClient = new ValuClient();
//            String subscribeNumber = request.getParameter(CONFIG.PARAM_MSISDN);
            String billReference = request.getParameter("InquiryReferenceId");
            double paidAmount = Double.parseDouble(request.getParameter("paidAmount"));

            ValuPaymentResponse paymentResponse = valuClient.valuPayment(new ValuPaymentRequest(billReference, paidAmount), lang, token,uuid);

            if (paymentResponse.getGlobalTrxId() != null || paymentResponse.getMobilNumber() != null || paymentResponse.getDueAmount() != 0.0) {
                session.setAttribute("PaymentResponse", paymentResponse);
                nextJSP = CONFIG.PAGE_VALU_PAYMENT;
//                session.setAttribute("uuid", "");

            } else {
                nextJSP = CONFIG.PAGE_ERRPR;
//                session.setAttribute("uuid", "");

            }

        } catch (Exception ex) {
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        } finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
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
