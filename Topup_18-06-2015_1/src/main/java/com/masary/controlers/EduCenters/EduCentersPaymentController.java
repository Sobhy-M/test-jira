/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.EduCenters;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.database.dto.DonationAgentPaymentRespponseDto;
import com.masary.database.dto.EduCentersDTO;
import com.masary.database.dto.Request;
import com.masary.database.manager.MasaryManager;
import static com.masary.database.manager.MasaryManager.logger;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Types;
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
public class EduCentersPaymentController extends HttpServlet {

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
        String nextJsp = "";

        try {
            /* TODO output your page here. You may use following sample code. */
            Gson gson = new Gson();
            String lang = session.getAttribute(CONFIG.lang).toString();
            int custID = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString().toString());
            int serviceID = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
            String code = request.getParameter("EduCode");
            String amount = request.getParameter("EduAmount");
            EduCentersDTO edu = new EduCentersDTO();
            edu.setLANG(lang);
            edu.setCHANNEL("WEB");
            edu.setCUSTOMER_ID(custID);
            edu.setSERVICE_ID(serviceID);
            edu.setCODE_ID(code);
            edu.setAMOUNT(amount);
            if (serviceID == 800) {
                edu.setSERVICE_TYPE(request.getParameter("EduServiceType"));
            } else {
                edu.setSERVICE_TYPE("");
            }
            String jsonRequest = gson.toJson(edu, EduCentersDTO.class);
            String jsonResponse = MasaryManager.getInstance().do_Edu_payment_without_inquiry(jsonRequest);
            DonationAgentPaymentRespponseDto res = gson.fromJson(jsonResponse, DonationAgentPaymentRespponseDto.class);
            if (res.getSTATUS_CODE().equals("200")) {
                nextJsp = CONFIG.EduCentersPaymentPage;
                request.setAttribute("OperationID", res.getTRANSACTION_ID());
            } else {
                nextJsp = CONFIG.PAGE_ERRPR;
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJsp);
                dispatcher.forward(request, response);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJsp);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.error("ERROR in do_agent_payment_without_inquiry method " + e);
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
