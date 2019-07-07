/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.integration.dto.CommissionTransaction;
import com.google.gson.Gson;
import com.masary.database.manager.MasaryManager;
import com.masary.utils.SystemSettingsUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Ahmed Saeed
 */
public class RatePlanCommissions extends HttpServlet {

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

        String ratePlanCommissionsUrl = SystemSettingsUtil.getInstance().loadProperty("commissions.rateplan.url");
        String proxyAuthorizationToken = request.getSession().getAttribute("Token").toString();
//        MasaryManager.logger.info("******************** " + proxyAuthorizationToken);
        String ratePlanCommissionsUrlWithServiceId = ratePlanCommissionsUrl.replace("{serviceId}", request.getParameter("SERVICE_ID"));
        String ratePlanCommissionsUrlWithAmount = ratePlanCommissionsUrlWithServiceId.replace("{amount}", request.getParameter("AMOUNT"));
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {

            HttpGet httpGet = new HttpGet(ratePlanCommissionsUrlWithAmount);
//            if (sendTokenHeader) {
//                httpGet.addHeader("token", token);
//            }else{
//                httpGet.addHeader("Authorization", token);
//            }

                httpGet.addHeader("Authorization", proxyAuthorizationToken);
                httpGet.addHeader("deviceType", "Web");
                httpGet.addHeader("ip", request.getRemoteAddr());

                CloseableHttpResponse ratePlanCommissionResponse = httpclient.execute(httpGet);

                String ratePlanCommissionResponseBody = EntityUtils.toString(ratePlanCommissionResponse.getEntity(), "UTF-8");
                MasaryManager.logger.info("RatePlan Service response is : " + ratePlanCommissionResponseBody);
                Gson gson = new Gson();

                CommissionTransaction commissionTransaction;

                //If RatePlan service responds successfully
                if (ratePlanCommissionResponse.getStatusLine().getStatusCode() == 200) {
                    commissionTransaction = gson.fromJson(ratePlanCommissionResponseBody, CommissionTransaction.class);
                    //Write returned commissionTransaction in the form of old system response
                    out.write(commissionTransaction.toOldDBResponse());
                } else if (ratePlanCommissionResponse.getStatusLine().getStatusCode() == 500) {
                    MasaryManager.logger.info("Failed to get commission for customer " + request.getSession().getAttribute(""));
                    request.getSession().setAttribute("ErrorMessage", "يرجى إعادة المحاولة وإعادة اختيار الخدمة");
                    ratePlanCommissionResponseBody = "";
                    out.write(ratePlanCommissionResponseBody);
                } else {
                    MasaryManager.logger.info("Failed to get commission for that customer");
                    request.getSession().setAttribute("ErrorMessage", "يرجى إعادة المحاولة وإعادة اختيار الخدمة");
                    ratePlanCommissionResponseBody = "";
                    out.write(ratePlanCommissionResponseBody);

                }

            }catch (Exception ex) {
            MasaryManager.logger.error("Error during calling rateplan on new system" + ex);
        }finally {
            //TODO, close HttpClient
            out.close();
            httpclient.close();

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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
