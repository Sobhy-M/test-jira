/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary;

import com.masary.database.manager.MasaryManager;
import com.masary.utils.SendEmail;
import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

public class UploadServlet extends HttpServlet {

//    private boolean isMultipart;
//    private String filePath;
//    private int maxFileSize = 3 * 1024 * 1024;
//    private int maxMemSize = 2 * 1024 * 1024;
//    private File file;
//
//    public void init() {
//        // Get the file location where it would be stored.
////        filePath = getServletContext().getInitParameter("file-upload");
//        filePath = "";
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
//        // Check that we have a file upload request
//        String Resp = "";
//        isMultipart = ServletFileUpload.isMultipartContent(request);
//        request.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        java.io.PrintWriter out = response.getWriter();
//        if (!isMultipart) {
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet upload</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<p>No file uploaded</p>");
//            out.println("</body>");
//            out.println("</html>");
//            return;
//        }
//
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        // maximum size that will be stored in memory
//        factory.setSizeThreshold(maxMemSize);
//        // Location to save data that is larger than maxMemSize.
////        factory.setRepository(new File("C:\\temp"));
//        factory.setRepository(new File(""));
//
//        // Create a new file upload handler
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        // maximum file size to be uploaded.
//        upload.setSizeMax(maxFileSize);
//
//        try {
//            String filesNames = "";
//            // Parse the request to get file items.
//            List fileItems = upload.parseRequest(request);
//
//            String role = ((FileItem) fileItems.get(2)).getString(); //request.getParameter("role");
//            String walletID = ((FileItem) fileItems.get(3)).getString(); //request.getParameter("aid");
//            String walletName = ((FileItem) fileItems.get(4)).getString(); //request.getParameter("aname");
//            String walletMobile = ((FileItem) fileItems.get(5)).getString(); //request.getParameter("amobile");
//            String companyName = ((FileItem) fileItems.get(6)).getString(); //request.getParameter("cname");
//            String personName = ((FileItem) fileItems.get(7)).getString(); //request.getParameter("pname");
//            String companyAddress = ((FileItem) fileItems.get(8)).getString(); //request.getParameter("address");
//            String gov = ((FileItem) fileItems.get(9)).getString(); //request.getParameter("gov");
//
//            // Process the uploaded file items
//            Iterator i = fileItems.iterator();
//
//            Resp += "<html><head><title>Servlet upload</title></head><body>";
//            Resp += "<br>";
//
//            while (i.hasNext()) {
//                FileItem fi = (FileItem) i.next();
//                if (!fi.isFormField()) {
//                    // Get the uploaded file parameters
//                    String fieldName = fi.getFieldName();
//                    String fileName = fi.getName();
//                    String contentType = fi.getContentType();
//                    boolean isInMemory = fi.isInMemory();
//                    long sizeInBytes = fi.getSize();
//
//                    filesNames = filesNames + fileName + ";";
//
//                    // Write the file
//                    if (fileName.lastIndexOf("\\") >= 0) {
//                        file = new File(filePath
//                                + fileName.substring(fileName.lastIndexOf("\\")));
//                    } else {
//                        file = new File(filePath
//                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
//                    }
//                    fi.write(file);
//                    Resp = Resp + " Uploaded Filename: " + fileName + "<br><br>";
//                }
//            }
//
//            Date date = new Date();
//            String today = date.toString();
//            SendEmail se = new SendEmail();
//
//            String subject = today + "مشترك جديد فى خدمة فودافون كاش ";
//            se.sendVoucherEmail("sales.admin@e-masary.com,oramadan@e-masary.com", filesNames, subject,
//                emailBody(walletID, walletName, personName, companyName, companyAddress, gov, today, walletMobile));
//
////            out.println(Resp + "</body></html>");
//            MasaryManager.getInstance().joinService(Integer.parseInt(walletID), "2003", 0);
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + role + ".jsp");
//            dispatcher.forward(request, response);
//
//        } catch (Exception ex) {
//            MasaryManager.logger.error(ex);
//        }
//    }
//
//    public void doGet(HttpServletRequest request,
//            HttpServletResponse response)
//            throws ServletException, java.io.IOException {
//
//        throw new ServletException("GET method used with "
//                + getClass().getName() + ": POST method required.");
//    }
//
//    private String emailBody(String wID, String wName, String pName, String cName, String address, String gov, String date, String mobile) {
//        String body = "مرسل لكم بيانات مشترك جديد فى خدمة فودافون كاش برجاء مراجعة بياناته و الملفات المرفقة و فى حال عدم إكتمالها أو مخالفتها للشروط المطلوبة برجاء طلب إغلاق الخدمة من الحساب.";
//        body += "\n";
//        body += "\n";
//        body += "رقم المحفظه: " + wID;
//        body += "\n";
//        body += "اسم التاجر: " + wName;
//        body += "\n";
//        body += "اسم صاحب المحل: " + pName;
//        body += "\n";
//        body += "اسم المحل: " + cName;
//        body += "\n";
//        body += "العنوان: " + address;
//        body += "\n";
//        body += "المحافظه: " + gov;
//        body += "\n";
//        body += "التاريخ: " + date;
//        body += "\n";
//        body += "رقم الموبايل: " + mobile;
//        body += "\n";
//        body += "\n";
//        body += "مرفق صورة البطاقة و إستمارة الإشتراك.";
//        return body;
//    }
}
