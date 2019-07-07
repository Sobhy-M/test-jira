/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Melad
 */
public class ReceiveSMS extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        MasaryManager.getInstance().logger.info("Receive SMS from IP " + request.getRemoteAddr());
        String msisdn = request.getParameter(CONFIG.PARAM_MSISDN).trim();
//        String text = request.getParameter(CONFIG.PARAM_TEXT);
        byte[] bytes = request.getParameter(CONFIG.PARAM_TEXT).getBytes("ISO-8859-1");
        String text = new String(bytes, "UTF-8");
        String encoding = request.getParameter(CONFIG.PARAM_ENCODING);
        String operatorId = request.getParameter(CONFIG.PARAM_OPERATOR_ID);
        String shortCode = request.getParameter(CONFIG.PARAM_SHORT_CODE);
        String name = request.getParameter(CONFIG.PARAM_USERNAME);
        String password = request.getParameter(CONFIG.PARAM_PASSWORD);
        if ((!"razytech".equals(name)) || (!"razytech".equals(password))) {
            response.getOutputStream().println("Bad username or password ");
            return;
        }

        try {

            if (msisdn.startsWith("20")) {
                msisdn = msisdn.substring(1);
            }
            String result = MasaryManager.getInstance().receiveSMS(msisdn, text, encoding, operatorId, shortCode);
            response.getOutputStream().println(result);
        } catch (Exception ex) {
            response.getOutputStream().println("Error ");
            ex.printStackTrace();
            MasaryManager.getInstance().logger.info("Error Receive SMS  " + ex.getMessage());
        }



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
