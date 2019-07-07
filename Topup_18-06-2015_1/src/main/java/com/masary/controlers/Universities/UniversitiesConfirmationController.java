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
import org.apache.log4j.Logger;

/**
 *
 * @author Abdelsabor
 */
public class UniversitiesConfirmationController extends HttpServlet {

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
        HttpSession session = request.getSession();
        String nextJSP = "";
        try {
            Bill_Request StudentInquery = new Bill_Request();

            int serviceId = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
            String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
            String billing_account = request.getParameter("StudentCode");
            int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());

            StudentInquery.setCUSTOMER_ID(customer_id);
            StudentInquery.setBILLING_ACCOUNT(billing_account);
            StudentInquery.setCHANNEL("WEB");
            StudentInquery.setLANG(lang);
            StudentInquery.setSERVICE_ID(serviceId);
            Bill_Response StudentResponse = (Bill_Response) (MasaryManager.getInstance().Inquiry(StudentInquery));
            if (StudentResponse.getSTATUS_CODE() == 200) {
                request.setAttribute("billStudentResponse", StudentResponse);
                nextJSP = CONFIG.UniversitiesConfirmation;
            } else {
                nextJSP = CONFIG.UniversitiesInqueryPage;
                session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + StudentResponse.getSTATUS_CODE() + " " + StudentResponse.getSTATUS_MESSAGE());
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
            /* TODO output your page here. You may use following sample code. */
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
