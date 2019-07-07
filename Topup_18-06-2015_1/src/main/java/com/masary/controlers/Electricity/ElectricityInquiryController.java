/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers.Electricity;

import com.masary.common.CONFIG;
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
public class ElectricityInquiryController extends HttpServlet {

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
        RequestDispatcher dispatcher;
        String nextJSP = "";
        int serviceId;
        try {
            /* TODO output your page here. You may use following sample code. */
            
            nextJSP = CONFIG.PAGE_ElectricityInquiry;
            serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
            session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
            BuildUUID build_uuid =new BuildUUID ();
            String uuid =build_uuid.CreateUUID();
            uuid =uuid+serviceId ; 
            session.setAttribute("uuid", uuid);
            dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            session.setAttribute("ErrorMessage", e.getMessage() + e);
            nextJSP = CONFIG.PAGE_ERRPR;
            dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            session.setAttribute("ErrorMessage", e.getMessage() + e);
            nextJSP = CONFIG.PAGE_ERRPR;
            dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            session.setAttribute("ErrorMessage", e.getMessage() + e);
            nextJSP = CONFIG.PAGE_ERRPR;
            dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
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
