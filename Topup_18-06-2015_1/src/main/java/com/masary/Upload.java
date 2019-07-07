package com.masary;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * This servlet used to upload voucher excel files.
 * @author Melad
 */
public class Upload extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        MasaryManager.logger.info("Upload from IP "+request.getRemoteAddr());
        String nextJsp = null;
        if (CONFIG.PARAM_OUT.equals(request.getSession().getAttribute(CONFIG.PARAM_OUT))) {
            nextJsp = CONFIG.PAGE_VOUCHER_UPLOAD_OUT;
        } else {
            nextJsp = CONFIG.PAGE_VOUCHER_UPLOAD;
        }
        String groups = "";
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
                List fileItemsList = servletFileUpload.parseRequest(request);
                Iterator it = fileItemsList.iterator();
                String fileContent = null;
                String providerId = null;
                while (it.hasNext()) {
                    FileItem fileItemTemp = (FileItem) it.next();
                    if (fileItemTemp.isFormField()) {
                        if (fileItemTemp.getFieldName().equals(CONFIG.PARAM_PROVIDER_ID)) {
                            providerId = fileItemTemp.getString();
                        } else {
                            groups += fileItemTemp.getString() + ",";
                        }
                    } else {
                        fileContent = fileItemTemp.getString();
                    }
                }
                groups = groups.substring(0, groups.length() - 1);
//                //System.out.println(groups);
                // //System.out.println(providerId);
                 String id = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
                MasaryManager.getInstance().addVouchers(fileContent, providerId, groups,id);
                request.setAttribute(CONFIG.CONFIRM, CONFIG.CONFIRM);
            } catch (Exception ex) {
                nextJsp = CONFIG.PAGE_ERRPR;
                request.setAttribute(
                        "ErrorMessage", "Detailed error is :" + ex.getMessage());
               MasaryManager.logger.error(ex.getMessage());
            }
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJsp);
        dispatcher.forward(request, response);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
//        processRequest(request, response);
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
            throws ServletException,
            IOException {
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
