/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Electricity;

import com.masary.common.CONFIG;
import com.masary.integration.ElectricityClient;
import com.masary.integration.dto.ElectricityInquiryResponse;
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
 * @author Mustafa
 */
public class Electricity_InfoInquiryController extends HttpServlet {

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
        String action;
        HttpSession session = request.getSession();

        action = request.getParameter(CONFIG.PARAM_ACTION);
        session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
        String nextJSP = "";
        int service_id ;
        try {
            
            service_id = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
            String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
            int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
            String accountNumber = request.getParameter("AccountNumber");
            
                service_id = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());

                session.setAttribute(CONFIG.lang, lang);
                
                String token = session.getAttribute("Token").toString();
                String uuid = session.getAttribute("uuid").toString();
                
                ElectricityInquiryResponse resp=new ElectricityInquiryResponse();
                ElectricityClient electricityClient=new ElectricityClient();
                
                  resp =electricityClient.electricityInquiry(lang,accountNumber,Integer.toString(service_id),uuid,token);
               
                if(resp==null){
                    session.setAttribute("ErrorMessage", resp);
                    nextJSP = CONFIG.PAGE_ERRPR;  
                    
                }else{
                    session.setAttribute("electricityInquiryRepresentation", resp);
                     nextJSP = CONFIG.PAGE_Electricity_new_inquiry;
                     
                }
                String Ip = request.getRemoteAddr();
               session.setAttribute("uuid", "");
               BuildUUID build_uuid =new BuildUUID ();
               String New_uuid =build_uuid.CreateUUID();
               New_uuid=New_uuid+Ip+service_id;
               session.setAttribute("uuid", New_uuid);
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
            
            } catch (Exception e) {
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
            session.setAttribute("uuid","");
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