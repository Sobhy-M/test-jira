package com.masary.controlers.NewElectricity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.masary.common.CONFIG;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class ElectricityHomeController extends HttpServlet {

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
        Date date = new Date();
        //System.out.println("SysDate" + date.getDate());
        String nextJSP = "";
        int serviceId;

        try {
            /* TODO output your page here. You may use following sample code. */
            if (date.getDate() == 1) {
                session.setAttribute("ErrorMessage", " خدمة تحصيل فواتير شركة جنوب القاهرة للكهرباء غير متاحة خلال اليوم الاول من كل شهر بناء على متطلبات شركة جنوب القاهرة للكهرباء");
                nextJSP = CONFIG.PAGE_ERRPR;
            } else {
                nextJSP = CONFIG.ElectricityInquiry;
                serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (IOException e) {
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