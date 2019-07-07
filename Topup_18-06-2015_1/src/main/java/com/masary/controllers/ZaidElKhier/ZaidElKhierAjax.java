/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controllers.ZaidElKhier;

import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.ZaidElKhierCategoriesResponse;
import com.masary.integration.dto.zaidElkierSubCategoryRepresentation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author user
 */
public class ZaidElKhierAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {

            int value = Integer.parseInt(request.getParameter("VALUE"));

            ZaidElKhierCategoriesResponse[] inquiryResponse = (ZaidElKhierCategoriesResponse[]) request.getSession().getAttribute("inquiryResponse");

            List<zaidElkierSubCategoryRepresentation> subServices = new ArrayList<zaidElkierSubCategoryRepresentation>();

            for (ZaidElKhierCategoriesResponse z : inquiryResponse) {
                if (z.getCategoryId() == value && z.getZaidElkierSubCategoryRepresentation() != null) {
                    subServices.addAll(Arrays.asList(z.getZaidElkierSubCategoryRepresentation()));
                }
            }

            request.getSession().setAttribute("subServices", subServices);
            List<String> responseData = new ArrayList<String>();

            for (zaidElkierSubCategoryRepresentation z : subServices) {

                responseData.add(z.getSubCategoryName() + "," + String.valueOf(z.getSubServiceId()));

            }
            out.write(StringUtils.join(responseData,"-"));
        } catch (Exception ex) {
           MasaryManager.logger.info(ex);
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
