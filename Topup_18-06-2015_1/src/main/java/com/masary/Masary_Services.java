/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary;

import com.masary.common.CONFIG;
import com.masary.database.dto.AuthenticationResponse;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author omnya
 */
public class Masary_Services extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
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
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String uri;
        String ip = request.getRemoteAddr();
        try {
            if (request.getQueryString() == null) {
                StringBuilder query = new StringBuilder("");
                String requstName;
                Enumeration e = request.getParameterNames();
                while (e.hasMoreElements()) {
                    requstName = (String) e.nextElement();
                    query.append(requstName).append("=").append(request.getParameter(requstName)).append("&");
                }
                if (query.length() == 0) {
                    uri = request.getRequestURL().toString();
                } else {
                    uri = request.getRequestURL().toString() + "?" + query.toString().substring(0, query.length() - 1);
                }
            } else {
                uri = request.getRequestURL().toString() + "?" + request.getQueryString();
            }
            String userid = request.getParameter(CONFIG.PARAM_USER_ID);
            String password = request.getParameter(CONFIG.PARAM_PASSWORD.toLowerCase());
            MasaryManager.logger.info("Service Action " + action + " User " + userid);
            String msg = "";
            if (action == null || userid == null || password == null) {
                msg = "-1 Bad request.";
                out.println(msg);
                logIt(ip, uri, msg);
                return;
            }
            AuthenticationResponse authenticationResponse = authenticate(userid, password);
            if (!authenticationResponse.isValid()) {
                msg = authenticationResponse.getResponse();
                out.println(msg);
                logIt(ip, uri, msg);
                return;
            }
            if (CONFIG.ACTION_ADD_API_CUSTOMER.equalsIgnoreCase(action)) {
                msg = addCustomer(request);
                out.println(msg);
                logIt(ip, uri, msg);
            } else if (CONFIG.ACTION_ADD_POINTS.equalsIgnoreCase(action)) {
                msg = addPoints(request);
                out.println(msg);
                logIt(ip, uri, msg);
            } else if (CONFIG.ACTION_CHECK_CODE.equalsIgnoreCase(action)) {
                msg = checkCode(request);
                //System.out.println("msg=" + msg);
                out.println(msg);
                logIt(ip, uri, msg);
            } else {
                msg = "-1 Bad request";
                out.println(msg);
                logIt(ip, uri, msg);
            }
        } catch (Exception e) {
            // //System.out.println("Bill_Controler : " + e);
        } finally {
//            out.close();
        }
    }

    private void logIt(String ip, String uri, String msg) {
        MasaryManager.getInstance().writeLog(ip, uri, msg);
    }

    private AuthenticationResponse authenticate(String userid, String password) {
        AuthenticationResponse authenticationResponse = MasaryManager.getInstance().authenticateSiteUserID(userid, password);
        authenticationResponse.setValid(true);
        if (authenticationResponse.getRole().equals("-1")) {
            authenticationResponse.setResponse("-2 Bad user ID or password");
            authenticationResponse.setValid(false);
        } else if (authenticationResponse.getRole().equals("-2")) {
            authenticationResponse.setResponse("-3 Your account currently not active Contact Masary for more information.");
            authenticationResponse.setValid(false);
        } else if (authenticationResponse.getRole().equals("-4")) {
            authenticationResponse.setResponse("-100 General Error Contact Masary Admin.");
            authenticationResponse.setValid(false);
        }
        return authenticationResponse;
    }

    private String addCustomer(HttpServletRequest request) {
        String msg = null;
        try {
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter(CONFIG.PARAM_USERNAME.toLowerCase());
            String usernameArabic = request.getParameter(CONFIG.PARAM_USERNAME_ARABIC.toLowerCase());
            if (usernameArabic != null) {
                byte[] bytes = usernameArabic.getBytes("ISO-8859-1");
                usernameArabic = new String(bytes, "UTF-8");
            }
            String msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
            String id = request.getParameter(CONFIG.PARAM_USER_ID);
            String action = request.getParameter(CONFIG.PARAM_ACTION);
            MasaryManager.logger.info(action + " " + username + " IP " + request.getRemoteAddr());
            try {
                if (username.isEmpty() || msisdn.isEmpty() || !msisdn.startsWith("01") || msisdn.length() != 11) {
                    msg = "-1 Bad request.";
                    MasaryManager.logger.error(msg);
                } else {
                    boolean exist = MasaryManager.getInstance().isExistInMasary(msisdn);
                    if (!exist) {
                        String custID = MasaryManager.getInstance().addCustomerFromAPI(username, usernameArabic, msisdn, id);
                        msg = custID + " " + msisdn;
                        MasaryManager.logger.error(msg);
                    } else {
                        msg = "-10 Enter a Another Number.";
                        MasaryManager.logger.error(msg);
                    }
                }
            } catch (Exception ex) {
                msg = "-100 General Error Contact Masary Admin.";
                MasaryManager.logger.error(ex);
            }
        } catch (Exception ex) {
            msg = "-100 General Error Contact Masary Admin.";
            MasaryManager.logger.error(ex);
        }
        return msg;
    }

    private String addPoints(HttpServletRequest request) {
        String amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        String toCustomer = request.getParameter(CONFIG.PARAM_TO.toLowerCase());
        String userid = request.getParameter(CONFIG.PARAM_USER_ID);
        String msg = null;
        if (amount == null || toCustomer == null) {
            msg = "-1 Bad request";
            MasaryManager.logger.error(msg);
        } else if (!Pattern.matches("\\d+", amount)) {
            msg = "-1 Amount must be number.";
            MasaryManager.logger.error(msg);
        } else {
            try {
                toCustomer = MasaryManager.getInstance().getCustomerID(toCustomer);
                if (toCustomer.equals("-1")) {
                    msg = "-11 Customer not found.";
                    MasaryManager.logger.error(msg);
                } else {
                    double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 1);
                    if (serviceBalance < Double.parseDouble(amount)) {
                        msg = "-4 You do not have enough balance.";
                        MasaryManager.logger.info(msg);
                    } else {
                        long result;
                        result = MasaryManager.getInstance().addBalance(toCustomer, userid, amount);
                        msg = result + " Successful transaction";
                    }
                }
            } catch (Exception ex) {
                MasaryManager.logger.error(ex.getMessage());
                msg = "-100 " + ex.getMessage();
                MasaryManager.logger.info(msg);
            }
        }
        return msg;
    }

    private String checkCode(HttpServletRequest request) {
        String codeID = request.getParameter(CONFIG.PARAM_CODE_ID);
        String msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        MasaryManager.logger.info("checkCode with codeID " + codeID + " msisdn " + msisdn);
        String msg = null;
        if (codeID == null || msisdn == null) {
            msg = "-1 Bad request";
            MasaryManager.logger.error(msg);
        } else {
            try {
                msg = MasaryManager.getInstance().checkCode(codeID, msisdn.substring(2));
            } catch (Exception ex) {
                MasaryManager.logger.error(ex.getMessage());
                msg = "-100 General Error Contact Masary Admin.";
                MasaryManager.logger.error(msg);
            }
        }
        return msg;
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
