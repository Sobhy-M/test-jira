/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.services.providers;

import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AYA
 */
public class RatePlanServiceInformation extends HttpServlet {

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
        try {
            /* TODO output your page here. You may use following sample code. */
            String commFees = "";
            String serviceId = request.getParameter("SERVICE_ID");
            request.getSession().setAttribute("SERVICE_ID", serviceId);
            int customerId = Integer.parseInt(request.getParameter("CUSTOMER_ID"));
            double amount = Double.parseDouble(request.getParameter("AMOUNT"));
            double nearestAmountLess = 0;
            double nearestAmountMore = 0;
            
            if (serviceId.equals("10") || serviceId.equals("6")) {
                String topupAmount = request.getParameter("TopupAmount");
                if (!topupAmount.isEmpty() && topupAmount != "null" && !topupAmount.equals("null") && serviceId.equals("10") || serviceId.equals("6")) {
                    commFees = MasaryManager.getInstance().getCommessionAmountInfo(request.getSession(), customerId, Integer.parseInt(serviceId), Double.parseDouble(topupAmount));
                } else {
                    commFees = MasaryManager.getInstance().getCommessionAmountInfo(request.getSession(), customerId, Integer.parseInt(serviceId), amount);
                }
            } else if (serviceId.equals("17") && (amount == 10.01 || amount == 15.01 || amount == 25.01 || amount == 5.01 || amount == 7.01)) {
                String topupAmount = request.getParameter("TopupDenmo");
                //System.out.println("request.getParameter(\"TopupDenmo\")"+request.getParameter("TopupDenmo"));
                commFees = MasaryManager.getInstance().getCommessionAmountInfo(request.getSession(), customerId, Integer.parseInt(serviceId), Double.parseDouble(topupAmount));

            }else if (serviceId.equals("8")){
               ArrayList<Double> orangeDenominations = (ArrayList<Double>)request.getSession().getAttribute("orangeDenominations");
                if (orangeDenominations.contains(amount)){
                    commFees = MasaryManager.getInstance().getCommessionAmountInfo(request.getSession(), customerId, Integer.parseInt(serviceId), amount);
                }else{
                    commFees = MasaryManager.getInstance().getCommessionAmountInfo(request.getSession(), customerId, Integer.parseInt(serviceId), 0);
                    
                    for (int i = 0; i < orangeDenominations.size(); i++) {
                        if (orangeDenominations.get(i) >= amount) {
                            nearestAmountMore = orangeDenominations.get(i);
                            break;
                        }
                    }
                    nearestAmountLess = orangeDenominations.get(orangeDenominations.indexOf(nearestAmountMore)-1);
                }
            } else {
                commFees = MasaryManager.getInstance().getCommessionAmountInfo(request.getSession(), customerId, Integer.parseInt(serviceId), amount);

            }
            if (commFees.equals("")) {
                request.getSession().setAttribute("ErrorMessage", "يرجى إعادة المحاولة وإعادة اختيار الخدمة");

            }

            MasaryManager.logger.info(commFees);
            out.write(commFees+"-"+nearestAmountLess+"-"+nearestAmountMore);
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
