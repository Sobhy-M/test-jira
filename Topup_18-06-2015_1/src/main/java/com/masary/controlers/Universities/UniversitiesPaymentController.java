/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Universities;

import com.masary.common.CONFIG;
import com.masary.database.dto.Bill_Request;
import com.masary.database.dto.Bill_Response;
import com.masary.database.dto.RatePlanDTO;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Abdelsabor
 */
public class UniversitiesPaymentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static Logger log = Logger.getLogger(UniversitiesConfirmationController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Bill_Request billPaymentRequest = new Bill_Request();
        HttpSession session = request.getSession();
        String nextJsp = "";
        try {

            /* TODO output your page here. You may use following sample code. */
            int serviceId = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
            String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
            int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
            Double amount = Double.parseDouble(request.getParameter("uniFirstAmount"));
            int uniReferenceNum = Integer.parseInt(request.getParameter("uniReferenceNum"));
            billPaymentRequest.setSERVICE_ID(serviceId);
            billPaymentRequest.setLANG(lang);
            billPaymentRequest.setCUSTOMER_ID(customer_id);
            billPaymentRequest.setCHANNEL("WEB");
            billPaymentRequest.setAMOUNT(amount);
            billPaymentRequest.setBILL_REFERENCE_NUMBER(uniReferenceNum);
            Bill_Response billPaymentResponse = (Bill_Response) (MasaryManager.getInstance().PAYMENT(billPaymentRequest));
            RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(String.valueOf(serviceId), String.valueOf(customer_id));
            if (billPaymentResponse.getSTATUS_CODE() == 200) {
                nextJsp = CONFIG.UniversitiesPaymentPage;
                request.setAttribute("billPaymentResponse", billPaymentResponse);
                request.setAttribute("uniRatePlan", ratePlan);
            } else {
                nextJsp = CONFIG.UniversitiesInqueryPage;
                session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + billPaymentResponse.getSTATUS_CODE() + " " + billPaymentResponse.getSTATUS_MESSAGE());
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJsp);
            dispatcher.forward(request, response);
        } catch (Exception e) {

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
