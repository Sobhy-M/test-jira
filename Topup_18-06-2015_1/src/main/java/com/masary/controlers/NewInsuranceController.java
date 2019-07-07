/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Abdelsabor
 */
public class NewInsuranceController extends HttpServlet {
    String FileSave = "D:/New folder/";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String certificationNumber = "";
        String customerId = "";
        String passportNo = "";
        String firstName = "";
        String middleName = "";
        String lastName = "";
        String birthdate = "";
        String gender = "";
        String startDate = "";
        String endDate = "";
        String period = "";
        String area = "";
        String amount = "";
        String phone = "";
        String Address = "";
        String City = "";
        String action = "";
        String nextJSP = "";
        String image = null;
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        customerId = request.getSession().getAttribute(CONFIG.PARAM_PIN).toString();
        PrintWriter out = response.getWriter();
        try {
            boolean IsMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!IsMultiPart) {

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (Exception e) {

                }
                Iterator itr = items.iterator();
                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        if (fieldName.equals(CONFIG.PARAM_ACTION)) {
                            action = item.getString();
                        }
                        if (!isLogin(session)) {
                            if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                                nextJSP = CONFIG.PAGE_LOGIN;
                                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                                dispatcher.forward(request, response);
                                return;
                            }
                            session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
                        }
                        if (CONFIG.ACTION_Make_Insurance.equals(action)) {
                            nextJSP = CONFIG.PAGE_GIG;
                        }
                        if (CONFIG.ACTION_Make_CONFIRMATION_Insurance.equals(action)) {
                            nextJSP = CONFIG.PAGE_GIG_Confirmation;
                                if (fieldName.equals("age")) {
                                session.setAttribute("age", item.getString());
                            }
                            if (fieldName.equals("CertificateNumber")) {
                                session.setAttribute("CertificateNumber", item.getString());
                            }
                            if (fieldName.equals("PassPortNumber")) {
                                session.setAttribute("PassPortNumber", item.getString());
                            }
                            if (fieldName.equals("FirstName")) {
                                session.setAttribute("FirstName", item.getString());
                            }
                            if (fieldName.equals("MiddleName")) {
                                session.setAttribute("MiddleName", item.getString());
                            }
                            if (fieldName.equals("LastName")) {
                                session.setAttribute("LastName", item.getString());
                            }
                            if (fieldName.equals("BirthDate")) {
                                session.setAttribute("BirthDate", item.getString());
                            }
                            if (fieldName.equals("Gender")) {
                                session.setAttribute("Gender", item.getString());
                            }
                            if (fieldName.equals("StartDate")) {
                                session.setAttribute("StartDate", item.getString());
                            }
                            if (fieldName.equals("endDate")) {
                                session.setAttribute("endDate", item.getString());
                            }
                            if (fieldName.equals("PeriodOfInsurance")) {
                                session.setAttribute("PeriodOfInsurance", item.getString());
                            }
                            if (fieldName.equals("AreaOfCover")) {
                                session.setAttribute("AreaOfCover", item.getString());
                            }
                            if (fieldName.equals("amount")) {
                                session.setAttribute("amount", item.getString());
                            }
                            if (fieldName.equals("phone")) {
                                session.setAttribute("phone", item.getString());
                            }
                            if (fieldName.equals("Address")) {
                                session.setAttribute("Address", new String(item.getString().getBytes("ISO-8859-1"), "utf-8"));
                            }
                            if (fieldName.equals("City")) {
                                session.setAttribute("City", new String(item.getString().getBytes("ISO-8859-1"), "utf-8"));
                            }
                            if (fieldName.equals("amount")) {
                                session.setAttribute("amount", item.getString());
                            }
                            if (fieldName.equals("NetAmount")) {
                                session.setAttribute("NetAmount", item.getString());
                            }
                            if (fieldName.equals("Taxes")) {
                                session.setAttribute("Taxes", item.getString());
                            }
                            if (fieldName.equals("ServiceCost")) {
                                session.setAttribute("ServiceCost", item.getString());
                            }
                            if (fieldName.equals("Finalamount")) {
                                session.setAttribute("Finalamount", item.getString());
                            }
                        }
                        if (CONFIG.ACTION_RESULT_Insurance.equals(action)) {
                            if (fieldName.equals("CertificateNumber")) {
                                certificationNumber = item.getString();     
                                session.setAttribute( "CertificateNumber",item.getString());
                                                                continue;

                            }
                            if (fieldName.equals("PassPortNumber")) {
                                passportNo = item.getString();
                                session.setAttribute( "PassPortNumber",item.getString());
                                                                continue;

                            }
                            if (fieldName.equals("FirstName")) {
                                firstName = item.getString();
                                session.setAttribute( "FirstName",item.getString()); 
                                continue;
                            }
                            if (fieldName.equals("MiddleName")) {
                                middleName = item.getString(); 
                                session.setAttribute( "MiddleName",item.getString());

                            }
                            if (fieldName.equals("LastName")) {
                                lastName = item.getString();
                                session.setAttribute( "LastName",item.getString());

                            }
                            if (fieldName.equals("BirthDate")) {
                                birthdate = item.getString(); 
                                session.setAttribute( "BirthDate",item.getString());
                                 continue;

                            }
                            if (fieldName.equals("Gender")) {
                                gender = item.getString();
                                session.setAttribute( "Gender",item.getString());
                                continue;

                            }
                            if (fieldName.equals("StartDate")) {
                                startDate = item.getString(); 
                                session.setAttribute( "StartDate",item.getString());

                            }
                            if (fieldName.equals("endDate")) {
                                endDate = item.getString(); 
                                session.setAttribute( "endDate",item.getString());
                                continue;
                            }
                            if (fieldName.equals("PeriodOfInsurance")) {
                                period = item.getString();
                                session.setAttribute( "PeriodOfInsurance",item.getString());
                                continue;
                            }
                            if (fieldName.equals("AreaOfCover")) {
                                area = item.getString(); 
                                session.setAttribute( "AreaOfCover",item.getString());

                            }
                            if (fieldName.equals("amount")) {
                                session.setAttribute( "amount",item.getString());
                                continue;
                            }
                            if (fieldName.equals("phone")) {
                                phone = item.getString();
                                session.setAttribute( "phone",item.getString());
                                continue;
                            }
                            if (fieldName.equals("Address")) {
                                Address = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
                                session.setAttribute( "Address",item.getString());
                                continue;
                            }
                            if (fieldName.equals("City")) {
                                City = new String(item.getString().getBytes("ISO-8859-1"), "utf-8"); 
                                session.setAttribute("City", City);
                                continue;
                            }
                            if (fieldName.equals("amount")) {
                                session.setAttribute("amount", item.getString());
                                continue;
                            }
                            if (fieldName.equals("NetAmount")) {
                                session.setAttribute("NetAmount", item.getString());
                                continue;
                            }
                            if (fieldName.equals("Taxes")) {
                                session.setAttribute("Taxes", item.getString());
                                continue;
                            }
                            if (fieldName.equals("ServiceCost")) {
                                session.setAttribute("ServiceCost", item.getString());
                                continue;
                            }
                            if (fieldName.equals("Finalamount")) {
                                amount = item.getString(); 
                                session.setAttribute("Finalamount", item.getString());
                                
                            }
                        }
                    } else {
                        String ItemName = item.getName();
                        if (ItemName == null || ItemName.equals("")) {
                            continue;
                        }
                        String FileName = FilenameUtils.getName(ItemName);
                        File f = checkExist(FileName);
                        image = f.toString();
                        session.setAttribute( "ImageURL",image);
                        item.write(f);
                    }
                }
            }
            if (CONFIG.ACTION_RESULT_Insurance.equals(action)) {
                int transactionId = MasaryManager.getInstance().addTravelPolicy( passportNo, firstName, middleName, lastName, birthdate, gender, startDate, endDate, period, area, customerId, amount, phone, Address, City);
                if (transactionId > 0) {
                    session.setAttribute(CONFIG.PARAM_Transaction_ID, transactionId);
                    nextJSP = CONFIG.PAGE_GIG_RESULT;
                } else {
                    session.setAttribute("ErrorMessage", CONFIG.errorTransaction);
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
                //System.out.println("TransactionId" + transactionId);
                session.setAttribute( "ImageURL",null);
            }
            session.setAttribute(CONFIG.PAGE, nextJSP);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            //System.out.println(e);
            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        } finally {
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NewInsuranceController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NewInsuranceController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private File checkExist(String FileName) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        File f = new File(FileSave + "/" + FileName);
        if (f.exists()) {
            StringBuffer sb = new StringBuffer(FileName);
            sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
            f = new File(FileSave + "/" + sb.toString());
        }
        return f;
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

}
