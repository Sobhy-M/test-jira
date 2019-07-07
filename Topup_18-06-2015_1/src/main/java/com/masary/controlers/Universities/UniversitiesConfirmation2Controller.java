/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Universities;

import com.masary.common.CONFIG;
import com.masary.database.dto.Bill_Request;
import com.masary.database.dto.Bill_Response;
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
 * @author Abdelsabor
 */
public class UniversitiesConfirmation2Controller extends HttpServlet {

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
        HttpSession session = request.getSession();
        String nextJSP = "";
        PrintWriter out = response.getWriter();

        try {
            /* TODO output your page here. You may use following sample code. */
            int serviceId = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
            String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
            int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
            Bill_Request commissionInquery = new Bill_Request();
            commissionInquery.setAMOUNT(Double.parseDouble(request.getParameter("uniAmount")));
            commissionInquery.setCUSTOMER_ID(customer_id);
            commissionInquery.setCHANNEL("WEB");
            commissionInquery.setLANG(lang);
            commissionInquery.setBILL_REFERENCE_NUMBER(Integer.parseInt(request.getParameter("uniReferenceNum")));

            Bill_Response billCommissionResponse = (Bill_Response) (MasaryManager.getInstance().Commession_Inquiry(commissionInquery));

            if (billCommissionResponse.getSTATUS_CODE() == 200 || billCommissionResponse.getSTATUS_CODE() == 201 || billCommissionResponse.getSTATUS_CODE() == 202) {
                request.setAttribute("UniBillCommissionResponse", billCommissionResponse);
                Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(serviceId));
                request.setAttribute("UniBillType", bill_Type);
                nextJSP = CONFIG.UniversitiesConfirmation2;

            } else {
                nextJSP = CONFIG.UniversitiesInqueryPage;
                session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + billCommissionResponse.getSTATUS_CODE() + " " + billCommissionResponse.getSTATUS_MESSAGE());
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            nextJSP = CONFIG.UniversitiesInqueryPage;

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
