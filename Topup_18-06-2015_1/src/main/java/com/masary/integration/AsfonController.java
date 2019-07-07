/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.integration.dto.AsfonItRequest;
import com.masary.integration.dto.AsfonResponse;
import com.google.gson.Gson;
import com.masary.common.CONFIG;
import static com.masary.common.CONFIG.lang;
import com.masary.database.manager.MasaryManager;
import com.masary.integration.dto.StandardHttpJsonResponseDTO;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author AYA
 */
public class AsfonController extends HttpServlet {

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
        String nextJSP = "";
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        StandardHttpJsonResponseDTO standardHttpJsonResponseDTO = new StandardHttpJsonResponseDTO();
        try {
            /* TODO output your page here. You may use following sample code. */
            if (action.equals("ACTION_Education_HOME")) {
                String asfonItUrl = SystemSettingsUtil.getInstance().loadProperty("asfon.ListExam.url");
                Gson gson = new Gson();
                CloseableHttpClient httpclient = HttpClients.createDefault();
                String basicAuth = request.getSession().getAttribute("Token").toString();
                HttpGet httpGet = new HttpGet(asfonItUrl);
                String token = request.getSession().getAttribute("Token").toString();
                httpGet.addHeader("Accept-Language", "en-US,en;q=0.5");
                httpGet.addHeader("Content-Type", "application/json");
//                httpGet.addHeader("token", token);
                httpGet.addHeader("Authorization", token);
                httpGet.addHeader("ip", request.getSession().getAttribute("ip").toString());
                httpGet.addHeader("deviceType", "Web");
                CloseableHttpResponse httpresponse = httpclient.execute(httpGet);
                String json = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
                MasaryManager.logger.info(httpresponse.getStatusLine());
                MasaryManager.logger.info("JSON List Exam: " + json);
                if (httpresponse.getStatusLine().toString().contains("200")) {
                    AsfonResponse[] exams = gson.fromJson(json, AsfonResponse[].class);
                    MasaryManager.logger.info("get exams transaction asfon it integration  " + exams);
                    request.getSession().setAttribute("exams", exams);
                    nextJSP = CONFIG.AsfonHomePage;
                } else {
                    request.getSession().setAttribute("ErrorCode", "400");
                    request.getSession().setAttribute("ErrorMessage", CONFIG.errorTransactionar);
                    throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                }
            } else if (action.equals("Get_Price_Exam")) {
                nextJSP = CONFIG.AsfonConfirmation;
            } else {

                String asfonItUrl = SystemSettingsUtil.getInstance().loadProperty("asfon.reserveExam.url");
                Gson gson = new Gson();
                AsfonItRequest asfonItRequest = new AsfonItRequest();
                asfonItRequest.setExamId(Long.parseLong(request.getParameter(CONFIG.itemId)));
                asfonItRequest.setNationalId(request.getParameter("nationalId"));
                CloseableHttpClient httpclient = HttpClients.createDefault();
                String token = request.getSession().getAttribute("Token").toString();
                HttpPost httpPost = new HttpPost(asfonItUrl);
                httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
                httpPost.addHeader("Content-Type", "application/json");
                httpPost.addHeader("Authorization", token);

                httpPost.addHeader("ip", request.getSession().getAttribute("ip").toString());
                httpPost.addHeader("deviceType", "Web");
                String urlParameters = gson.toJson(asfonItRequest, AsfonItRequest.class);
                StringEntity params = new StringEntity(urlParameters);
                httpPost.setEntity(params);
                CloseableHttpResponse httpresponse = httpclient.execute(httpPost);
                String json = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
                MasaryManager.logger.info(httpresponse.getStatusLine());
                MasaryManager.logger.info("JSON: " + json);
                if (httpresponse.getStatusLine().toString().contains("200")) {
                    AsfonResponse asfonResponse = gson.fromJson(json, AsfonResponse.class);
                    MasaryManager.logger.info("get exams transaction asfon it integration  " + asfonResponse);
                    request.getSession().setAttribute("asfonResponseReverse", asfonResponse);
                    nextJSP = CONFIG.AsfonPaymentPage;
                } else {
                    standardHttpJsonResponseDTO = gson.fromJson(json, StandardHttpJsonResponseDTO.class);
                    if (standardHttpJsonResponseDTO.getStatus().equals("406") && standardHttpJsonResponseDTO.getMessage().equals("5")) {
                        request.getSession().setAttribute("ErrorCode", "406");
                        request.getSession().setAttribute("ErrorMessage", CONFIG.errorTransactionar);
                        throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("12")) {
                        request.getSession().setAttribute("ErrorCode", "500");
                        request.getSession().setAttribute("ErrorMessage", CONFIG.errorTransactionar);
                        throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    } else if (standardHttpJsonResponseDTO.getStatus().equals("500") && standardHttpJsonResponseDTO.getMessage().equals("15")) {
                        request.getSession().setAttribute("ErrorCode", "500");
                        request.getSession().setAttribute("ErrorMessage", CONFIG.errorTransactionar);
                        throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
                    } else if (standardHttpJsonResponseDTO.getStatus().equals("400") && standardHttpJsonResponseDTO.getMessage().equals("200417")) {
                        request.getSession().setAttribute("ErrorCode", "400");
                        request.getSession().setAttribute("ErrorMessage", CONFIG.errorNationaIdar);
                        throw new IOException(lang.equals("ar") ? CONFIG.errorNationaIdar : CONFIG.errorNationaId);
                    } else {
                        request.getSession().setAttribute("ErrorCode", "500");
                        request.getSession().setAttribute("ErrorMessage", CONFIG.errorNationaIdar);
                        throw new IOException(lang.equals("ar") ? CONFIG.errorTransactionar : CONFIG.errorTransaction);
//                    throw new Exception(standardHttpJsonResponseDTO.getException());
                    }

                }

            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            MasaryManager.logger.error("Exception" + e.getMessage());
            MasaryManager.logger.error(e);
//            request.getSession().setAttribute("ErrorCode", "400");
//            request.getSession().setAttribute("ErrorMessage", CONFIG.errorNationaIdar);
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

//        try {
        processRequest(request, response);
//        } catch (Exception ex) {
//            Logger.getLogger(AsfonController.class.getName()).log(Level.SEVERE, null, ex);
//        }

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
