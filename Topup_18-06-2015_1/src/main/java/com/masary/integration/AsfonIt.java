/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.integration;

import com.masary.integration.dto.AsfonResponse;
import com.google.gson.Gson;
import com.masary.common.CONFIG;
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
 * @author AYA
 */
public class AsfonIt extends HttpServlet {

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
            String asfonItUrl = SystemSettingsUtil.getInstance().loadProperty("asfon.PriceExam.url") + Integer.parseInt(request.getParameter("itemId"));
            Gson gson = new Gson();
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String token = request.getSession().getAttribute("Token").toString();
            HttpGet httpGet = new HttpGet(asfonItUrl);
            httpGet.addHeader("Accept-Language", "en-US,en;q=0.5");
            httpGet.addHeader("Content-Type", "application/json");
//            httpGet.addHeader("token", token);
            httpGet.addHeader("Authorization", token);

            httpGet.addHeader("ip", request.getSession().getAttribute("ip").toString());
            httpGet.addHeader("deviceType", "Web");
            CloseableHttpResponse httpresponse = httpclient.execute(httpGet);
            String json = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
            MasaryManager.logger.info(httpresponse.getStatusLine());
            MasaryManager.logger.info("JSON Price Exam: " + json);
            if (httpresponse.getStatusLine().toString().contains("200")) {
                AsfonResponse examInfo = gson.fromJson(json, AsfonResponse.class);
                out.write(json);
            }
        } catch (Exception e) {
            MasaryManager.logger.info(e.getMessage());
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
