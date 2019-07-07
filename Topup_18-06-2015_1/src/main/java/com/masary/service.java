/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary;

import com.masary.common.CONFIG;
import com.masary.database.dto.AgentDTO;
import com.masary.database.dto.AuthenticationResponse;
import com.masary.database.dto.CustomerServiceDTO;
import com.masary.database.dto.SellVoucherResponse;
import com.masary.database.manager.MasaryManager;
import com.masary.database.manager.TagManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

/**
 * This Servlet response to the requests come to masary as service.
 *
 * @author Melad
 */
public class service extends HttpServlet {

//    private static HostnameVerifier hv;
    private String ip;
    private String uri;
    private String action;
    private String userid;
    private String password;
    private String timestamp;
    private String senderTrx;
    private String trxID;
    private String trxType;
    String amount;
    String msisdn;
    String operatorID;
    String fromNum;
    String oCountry;
    String dCountry;
    private PrintWriter out;
    String msg;
    private String newPassword;
    HttpServletRequest request;
    HttpServletResponse response;
    private String inputLine;

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
    protected synchronized void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        request = req;
        response = resp;
        ip = request.getRemoteAddr();
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        action = request.getParameter(CONFIG.PARAM_ACTION);
//        if (!(CONFIG.ACTION_TOPUP_MBINIL.equals(action)|| CONFIG.ACTION_TOPUP_VODAFONE.equals(action)))
//        {

//        /*********************************/
//        if (MasaryManager.getInstance().isTrusted(ip) == 0) {
//            out.print("-5 You are not authorized to use this service.");
//            MasaryManager.getInstance().logger.info("Not Trusted IP " + ip);
//            return;
//        }
        /**
         * **********************************
         */
//        }
        if (request.getQueryString() == null) {
            StringBuilder query = new StringBuilder("");
            String requstName;
            Enumeration e = request.getParameterNames();
            while (e.hasMoreElements()) {
                requstName = (String) e.nextElement();
                query.append(requstName).append("=").append(request.getParameter(requstName)).append("&");
            }
//            //System.out.println(query.length());
            if (query.length() == 0) {
                uri = request.getRequestURL().toString();
            } else {
                uri = request.getRequestURL().toString() + "?" + query.toString().substring(0, query.length() - 1);
            }
        } else {
            uri = request.getRequestURL().toString() + "?" + request.getQueryString();
        }
//        uri = uri.replaceFirst("password=\\w&", "password=******&");
//        if (!uri.contains("******")) {
//            uri = uri.replaceAll("password=(.*)", "password=******");
//        }
//        msg  += "Your Ip Address is: " + ip);
//        msg  += "Url " + uri);
        userid = request.getParameter(CONFIG.PARAM_USER_ID);
        password = request.getParameter(CONFIG.PARAM_PASSWORD.toLowerCase());
        MasaryManager.logger.info("Service Action " + action + " User " + userid);
        msg = "";
        if (action == null || userid == null || password == null) {
            msg += "-1 Bad request.";
            logIt();
            return;
        }
        if (!authenticate()) {
            logIt();
            return;
        }
        if (CONFIG.ACTION_GET_CREDITS.equalsIgnoreCase(action)) {
            try {
                AgentDTO agent = MasaryManager.getInstance().getAgent(userid);
                msg += "0 Your balance is " + agent.getBalance() + " L.E.\n";
                for (CustomerServiceDTO service : (List<CustomerServiceDTO>) agent.getServices()) {
                    msg += service.getServiceName() + " " + service.getServiceBalance() + " L.E.\n";
                }
                msg += "Date " + Calendar.getInstance().getTime();
            } catch (Exception ex) {
                msg += "-100";
                msg += CONFIG.errorTransaction;
                MasaryManager.logger.error(ex.getMessage());
            }

        } else if (CONFIG.ACTION_Get_TIME.equalsIgnoreCase(action)) {
            getTimeStamp();
        } else if (CONFIG.ACTION_Get_STATUS.equalsIgnoreCase(action)) {
            getTrxStatus();
        } else if (CONFIG.ACTION_TOPUP.equalsIgnoreCase(action)) {
            topupEtisalat();
        } else if (CONFIG.ACTION_TOPUP_MBINIL.equalsIgnoreCase(action)) {
            topupMobinil();
        } else if (CONFIG.ACTION_TOPUP_VODAFONE.equalsIgnoreCase(action)) {
            topupVodafone();
        } else if (CONFIG.ACTION_TOPUP_ANY.equalsIgnoreCase(action)) {
            topupAny();
        } else if (CONFIG.ACTION_VOUCHER_ETISALAT.equalsIgnoreCase(action)) {
            voucherEtisalat();
        } //-----------------------
        else if (CONFIG.ACTION_TOPUP_IBT.equalsIgnoreCase(action)) {
            topupIBT();
        } else if (CONFIG.ACTION_TOPUP_ANY_IBT.equalsIgnoreCase(action)) {
            topupAnyIBT();
        } //-----------------------
        else if (CONFIG.ACTION_ADD_CUSTOMER.equalsIgnoreCase(action)) {
            addCustomer();
        } /**
         * *******************************************************************
         */
        else if (CONFIG.ACTION_VOUCHER_SilkRoad.equalsIgnoreCase(action)) {
            silkRoadVouchers();
        } else if (CONFIG.ACTION_VOUCHER_CASHU.equalsIgnoreCase(action)) {
            cashUVoucher();
        } else if (CONFIG.ACTION_VOUCHER_ONECARD.equalsIgnoreCase(action)) {
            oneCardVouchers();
        } else if (CONFIG.ACTION_VOUCHER_FACEBOOK.equalsIgnoreCase(action)) {
            facebookVouchers();
        } else if (CONFIG.ACTION_VOUCHER_TAHADI.equalsIgnoreCase(action)) {
            tahadiVoucher();
        } else if (CONFIG.ACTION_VOUCHER_CROSSFIRE.equalsIgnoreCase(action)) {
            crossFireVoucher();
        } else if (CONFIG.ACTION_VOUCHER_CONQUER.equalsIgnoreCase(action)) {
            conquerVoucher();
        } else if (CONFIG.ACTION_VOUCHER_ARWOLFTEAM.equalsIgnoreCase(action)) {
            arabicWolfTeamVoucher();
        } else if (CONFIG.ACTION_VOUCHER_ENWOLFTEAM.equalsIgnoreCase(action)) {
            englishWolfTeamVoucher();
        } //-----------------
        //-----------------
        else if (CONFIG.ACTION_VOUCHER_FACEBOOK.equalsIgnoreCase(action)) {
            facebookvouchers();
        } //-----------------
        else if (CONFIG.ACTION_VOUCHER_MOBINIL.equalsIgnoreCase(action)) {
            voucherMobinil();
        } else if (CONFIG.ACTION_VOUCHER_VODAFONE.equalsIgnoreCase(action)) {
            voucherVodafone();
        } else if (CONFIG.ACTION_MONEY_TRANSFER.equalsIgnoreCase(action)) {
            moneyTransfer();
        } else if (CONFIG.ACTION_MONEY_TRANSFER_PAYMENT_REF_CODE.equalsIgnoreCase(action)) {
            moneyTransferPRC();
        } else if (CONFIG.ACTION_ADD_TAG.equalsIgnoreCase(action)) {
            addTag();
        } else if (CONFIG.ACTION_TRACK_AGENT.equalsIgnoreCase(action)) {
            trackAgent();
        } else if (CONFIG.ACTION_ORDER_CREDITS.equalsIgnoreCase(action)) {
            amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
            try {
                MasaryManager.getInstance().sendSMS(MasaryManager.getInstance().getAgent("1").getPhone(),
                        "MSG from " + MasaryManager.getInstance().getCustomerInfo(userid).getCustomerName(null) + ". user id : " + userid + ". Please recharg my account in Masary with " + amount + " EGP. ");
            } catch (Exception ex) {
                MasaryManager.logger.error(ex.getMessage());
            }
        } else if (CONFIG.ACTION_CHANGE_PASSWORD.equalsIgnoreCase(action)) {
            newPassword = request.getParameter(CONFIG.PARAM_NEW_PASSWORD);
            if (newPassword == null) {
                msg += "-1 ";
                msg += "Bad request Enter new password";
                logIt();
                return;
            }
            if (newPassword.length() < 4 || newPassword.length() > 20) {
                msg += "-1 password length must be between 4 and 20 digits ";
                logIt();
                return;
            }
            try {
                MasaryManager.getInstance().updateUserAdmin(userid, newPassword);
                msg += "0 Your password changed successfully ";
            } catch (Exception ex) {
                msg += "-100";
                msg += CONFIG.errorTransaction;
                MasaryManager.logger.error(ex.getMessage());
            }
        } else {
            msg += "-1 Bad request";
        }
        logIt();
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
            throws ServletException,
            IOException {
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
            throws ServletException,
            IOException {
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

    boolean authenticate() {
        AuthenticationResponse authenticationResponse = MasaryManager.getInstance().authenticateSiteUserID(userid, password);
        if (authenticationResponse.getRole().equals("-1")) {
            msg += "-2 Bad user ID or password";

            return false;
        } else if (authenticationResponse.getRole().equals("-2")) {
            msg += "-3 Your account currently not active Contact Masary for more information.";
            return false;
        } else if (authenticationResponse.getRole().equals("-4")) {
            msg += "-100 General Error Contact Masary Admin. ";
            return false;
        }
        return true;
    }

    private void logIt() {
        out.println(msg);
        out.flush();
        MasaryManager.getInstance().writeLog(ip, uri, msg);
    }

    private void getTimeStamp() {
        msg += DateTime.now().toString().substring(0, DateTime.now().toString().indexOf("+"));
        return;
    }

    private void getTrxStatus() {
        trxID = request.getParameter(CONFIG.PARAM_TRX_ID.toLowerCase());
        trxType = request.getParameter(CONFIG.PARAM_TRX_TYPE.toLowerCase());
        if (trxID == null || trxType == null) {
            msg += "-1 Bad request";
            return;
        }
        msg += MasaryManager.getInstance().getTrxStatus(trxID, trxType, userid);
        return;
    }

    private void topupMobinil() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());

        if (!userid.equals("7559") && !userid.equals("7558")) {
            if (timestamp == null) {
                msg += "-1 Bad request.";
                return;
            }
            if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
                msg += "-6 Transaction has timed out.";
                MasaryManager.logger.error("Transaction has timed out.");
                return;
            }
        }
        if (!MasaryManager.getInstance().ValidateTopupType(msisdn, 8)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (!MasaryManager.getInstance().ValidatePhoneNumber(msisdn)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (amount == null || msisdn == null) {
            msg += "-1 Bad request";
            return;
        }
        try {
            if (!MasaryManager.getInstance().ValidateTopupAmount(8, amount)) {
                msg += "-1 Sorry, please enter a valid amount.";
                return;
            }
        } catch (Exception nfe) {
            msg += "-1 Sorry, please enter a valid amount.";
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 8);
            if (serviceBalance < Double.parseDouble(amount)) {
                msg += "-4 You do not have enough balance.";
                return;
            }
            String result = MasaryManager.getInstance().transferMobinilTopUpAsService(userid, msisdn, amount, timestamp, senderTrx);
            msg += result;
        } catch (Exception ex) {
            MasaryManager.logger.error(ex.getMessage());
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                msg += "-100 " + CONFIG.errorTransaction;
            }
        }
    }

    private void topupEtisalat() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());

        if (!userid.equals("7559") && !userid.equals("7558")) {
            if (timestamp == null) {
                msg += "-1 Bad request.";
                return;
            }
            if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
                msg += "-6 Transaction has timed out.";
                MasaryManager.logger.error("Transaction has timed out.");
                return;
            }
        }
        if (!MasaryManager.getInstance().ValidateTopupType(msisdn, 6)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (!MasaryManager.getInstance().ValidatePhoneNumber(msisdn)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (amount == null || msisdn == null) {
            msg += "-1 Bad request.";
            return;
        }
        try {
            if (!MasaryManager.getInstance().ValidateTopupAmount(6, amount)) {
                msg += "-1 Sorry, please enter a valid amount.";
                return;
            }
        } catch (Exception nfe) {
            msg += "-1 Sorry, please enter a valid amount.";
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 6);
            if (serviceBalance < Double.parseDouble(amount)) {
                msg += "-4 You do not have enough balance.";
                return;
            }
            if (msisdn.startsWith("0")) {
                msisdn = msisdn.substring(1);
            }
            String result = MasaryManager.getInstance().transferCustomerTopUpAsService(userid, msisdn, amount, timestamp, senderTrx);
            msg += result;
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + ex.getMessage();//+ CONFIG.MobinilTopupRepetedError;
            } else {

                msg += "-100 " + CONFIG.errorTransaction;
            }
        }
    }

    private void topupVodafone() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());

        if (!userid.equals("7559") && !userid.equals("7558")) {
            if (timestamp == null) {
                msg += "-1 Bad request.";
                return;
            }
            if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
                msg += "-6 Transaction has timed out.";
                MasaryManager.logger.error("Transaction has timed out.");
                return;
            }
        }
        if (amount == null || msisdn == null) {
            msg += "-1 Bad request";
            return;
        }
        if (!MasaryManager.getInstance().ValidateTopupType(msisdn, 10)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (!MasaryManager.getInstance().ValidatePhoneNumber(msisdn)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        try {
            if (!MasaryManager.getInstance().ValidateTopupAmount(10, amount)) {
                msg += "-1 Sorry, please enter a valid amount.";
                return;
            }
        } catch (Exception nfe) {
            msg += "-1 Sorry, please enter a valid amount.";
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 10);
            if (serviceBalance < Double.parseDouble(amount)) {
                msg += "-4 You do not have enough balance.";
                return;
            }
            String result = MasaryManager.getInstance().transferVodafoneTopUpAsService(userid, msisdn, amount, timestamp, senderTrx);
            msg += result;

        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-100 " + CONFIG.errorTransaction;
            }
        }
    }

    private void topupEtisalatIBT(String mobile) {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
//        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());

        if (!MasaryManager.getInstance().ValidateTopupType(mobile, 6)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (!MasaryManager.getInstance().ValidatePhoneNumber(mobile)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (amount == null || mobile == null) {
            msg += "-1 Bad request.";
            return;
        }
        try {
            if (!MasaryManager.getInstance().ValidateTopupAmount(6, amount)) {
                msg += "-1 Sorry, please enter a valid amount.";
                return;
            }
        } catch (Exception nfe) {
            msg += "-1 Sorry, please enter a valid amount.";
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 6);
            if (serviceBalance < Double.parseDouble(amount)) {
                msg += "-4 You do not have enough balance.";
                return;
            }
            if (mobile.startsWith("0")) {
                mobile = mobile.substring(1);
            }
            String result = MasaryManager.getInstance().transferCustomerTopUpAsService(userid, mobile, amount, timestamp, senderTrx);
            msg += result;
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-100 " + CONFIG.errorTransaction;
            }

        }
    }

    private void topupVodafoneIBT(String mobile) {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
//        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());

        if (amount == null || mobile == null) {
            msg += "-1 Bad request";
            return;
        }
        if (!MasaryManager.getInstance().ValidateTopupType(mobile, 10)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (!MasaryManager.getInstance().ValidatePhoneNumber(mobile)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        try {
            if (!MasaryManager.getInstance().ValidateTopupAmount(10, amount)) {
                msg += "-1 Sorry, please enter a valid amount.";
                return;
            }
        } catch (Exception nfe) {
            msg += "-1 Sorry, please enter a valid amount.";
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 10);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 You do not have enough balance.";
                    return;
            }
            String result = MasaryManager.getInstance().transferVodafoneTopUpAsService(userid, mobile, amount, timestamp, senderTrx);
            msg += result;

        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-100 " + CONFIG.errorTransaction;
            }
        }
    }

    private void topupMobinilIBT(String mobile) {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
//        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());

        if (!MasaryManager.getInstance().ValidateTopupType(mobile, 8)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (!MasaryManager.getInstance().ValidatePhoneNumber(mobile)) {
            msg += "-1 Please insert a valid mobile number";
            return;
        }
        if (amount == null || mobile == null) {
            msg += "-1 Bad request";

            return;
        }
        try {
            if (!MasaryManager.getInstance().ValidateTopupAmount(8, amount)) {
                msg += "-1 Sorry, please enter a valid amount.";
                return;
            }
        } catch (Exception nfe) {
            msg += "-1 Sorry, please enter a valid amount.";
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 8);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 You do not have enough balance.";
                    return;
            }

            String result = "";
            float originalAmount = Float.parseFloat(amount);

            float amount1 = 0;
            float amount2 = 0;

            if (userid.equals("845") && (originalAmount == 35 || originalAmount == 105 || originalAmount == 140)) {
                if (originalAmount == 35) {
                    amount1 = 30;
                    amount2 = 5;
                } else if (originalAmount == 105) {
                    amount1 = 100;
                    amount2 = 5;
                } else if (originalAmount == 140) {
                    amount1 = 100;
                    amount2 = 40;
                }
                result = MasaryManager.getInstance().transferMobinilTopUpAsService(userid, mobile, String.valueOf(amount1), "", "");
            } else {
                result = MasaryManager.getInstance().transferMobinilTopUpAsService(userid, mobile, String.valueOf(originalAmount), "", "");
            }
            if (userid.equals("845") && (originalAmount == 35 || originalAmount == 105 || originalAmount == 140) && result.contains(CONFIG.mobinilDone)) {
                Thread waitedTXNwt = new Thread(new waitedTXN(userid, String.valueOf(amount2), mobile));
                waitedTXNwt.start();
            }
            msg += result;
        } catch (Exception ex) {
            MasaryManager.logger.error(ex.getMessage());
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {

                msg += "-100 " + CONFIG.errorTransaction;
            }
        }
    }

    private void topupIBT() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        oCountry = request.getParameter(CONFIG.ORIGINATING_COUNTRY.toLowerCase());
        dCountry = request.getParameter(CONFIG.DESTINATION_COUNTRY.toLowerCase());
        if (amount == null || msisdn == null || oCountry == null || dCountry == null) {
            msg += "-1 Bad request";
            return;
        }
        try {
            String result = MasaryManager.getInstance().transferIBTTopUpAsService(userid, msisdn, amount, oCountry, dCountry);
            msg += result;
        } catch (Exception ex) {
            MasaryManager.logger.error(ex.getMessage());
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                msg += "-100 " + CONFIG.errorTransaction;
            }
        }
    }

    private void topupAnyIBT() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        operatorID = request.getParameter(CONFIG.OPERATOR_ID);
        fromNum = request.getParameter(CONFIG.FROM_NUMBER);
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        if (amount == null || msisdn == null || !msisdn.startsWith("00")) {
            msg += "-1 Bad request";
            return;
        }
        if (msisdn.length() == 14) {
            String operator = msisdn.substring(3, msisdn.length());
            if (operator.startsWith("010")) {
                topupVodafoneIBT(operator);
            } else if (operator.startsWith("012")) {
                topupMobinilIBT(operator);
            } else if (operator.startsWith("011")) {
                topupEtisalatIBT(operator);
            } else {
                msg += "-1, Please insert a mobile number with valid operator";
            }
        } else {
            msg += "-1, Please insert a mobile number";
        }
    }

    private void moneyTransfer() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_TO.toLowerCase());
        if (amount == null || msisdn == null) {
            msg += "-1 ";
            msg += " Bad request";
            MasaryManager.logger.info(msg);
            return;
        }
        try {
            if (Integer.parseInt(amount) <= 0) {
                msg += "-1 ";
                msg += " Amount must be greater than 0.";
                MasaryManager.logger.info(msg);
                return;
            }
        } catch (NumberFormatException nfe) {
            msg += "-1 ";
            msg += " Amount must be number. ";
            MasaryManager.logger.info(msg);
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 1);
            if (serviceBalance < Double.parseDouble(amount)) {
                msg += "-4 ";
                msg += "You do not have enough balance.";
                MasaryManager.logger.info(msg);
                return;
            }
            String result = null;
            if (userid.equals("148")) {
                result = String.valueOf(MasaryManager.getInstance().addBalanceToBill(msisdn, userid, amount));
            } else {
                result = String.valueOf(MasaryManager.getInstance().addBalance(msisdn, userid, amount));
            }
            msg += result + " Successful transaction";
        } catch (Exception ex) {
            MasaryManager.logger.error(ex.getMessage());
            ////25-09 define new if and else ////////////
            if (ex.getMessage().contains("Customer not found")) {
                msg += "-11 ";
                msg += "Customer not found";
                MasaryManager.logger.info(msg);
            } else {
                msg += "-100";
                msg += CONFIG.errorTransaction;
                MasaryManager.logger.info(msg);
            }
        }
    }

    private void voucherEtisalat() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());              // ----------- 16-05-2012 --- Omnya
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
           SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 17, msisdn == null ? "Y" : "N",
                    msisdn == null ? "API" : msisdn, timestamp, senderTrx, "");   // ----------- 16-05-2012 --- Omnya
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                if (sellVoucherResponse.getTransId().startsWith("-2")) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                } else {
                    msg += "-90 ";
                    msg += sellVoucherResponse.getStatusMSG();
                }
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
        MasaryManager.logger.info("voucherEtisalat Result :" + msg);
    }

    private void cashU() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        if (amount == null) {
            msg += "-1 ";
            msg += " Bad ";
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 17);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 21, "Y", "API", timestamp, senderTrx, "");
            msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void SilkRoadvouchers() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 19);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
                }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 19, "Y", "API", timestamp, senderTrx, "");
            msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void facebookvouchers() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 20);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 20, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void oncardvouchers() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 18);
            if (serviceBalance< Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 18, "Y", "API", timestamp, senderTrx, "");
            msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void voucherMobinil() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());              // ----------- 16-05-2012 --- Omnya
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 16, msisdn == null ? "Y" : "N",
                    msisdn == null ? "API" : msisdn, timestamp, senderTrx, "");   // ----------- 16-05-2012 --- Omnya           
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                if (sellVoucherResponse.getTransId().startsWith("-2")) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                } else {
                    msg += "-90 ";
                    msg += sellVoucherResponse.getStatusMSG();
                }
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            MasaryManager.logger.error(ex.getMessage());
            msg += "-90 ";
            msg += ex.getMessage();
        }
        MasaryManager.logger.info("voucherMobinil Result :" + msg);
    }

    private void voucherVodafone() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());              // ----------- 16-05-2012 --- Omnya

        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }

        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 15, msisdn == null ? "Y" : "N",
                    msisdn == null ? "API" : msisdn, timestamp, senderTrx, "");   // ----------- 16-05-2012 --- Omnya
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                if (sellVoucherResponse.getTransId().startsWith("-2")) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                } else {
                    msg += "-90 ";
                    msg += sellVoucherResponse.getStatusMSG();
                }
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            MasaryManager.logger.error(ex.getMessage());
            msg += "-90 ";
            msg += ex.getMessage();
        }
        MasaryManager.logger.info("voucherVodafone Result :" + msg);
    }

    private void addTag() {
        String tagid = request.getParameter(CONFIG.PARAM_TAG_ID);
        String data = request.getParameter(CONFIG.PARAM_TAG_Data);
        StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
        String tagName = null, tagAddress = null, otherData = null;
        try {
            tagName = stringTokenizer.nextToken();
            tagAddress = stringTokenizer.nextToken();
            otherData = stringTokenizer.nextToken();
        } catch (NoSuchElementException ex) {
        }

        try {
            TagManager.getInstance().addTag(userid, tagid, tagName, tagAddress, otherData);
            msg += "0 Done";
        } catch (SQLException ex) {
            MasaryManager.logger.error(ex.getMessage());
            msg += "-100 " + ex.getMessage();
        }
    }

    private void trackAgent() {

        String tagid = request.getParameter(CONFIG.PARAM_TAG_ID);
        try {
            TagManager.getInstance().trackAgent(userid, tagid);
            msg += "0 Done ";
        } catch (SQLException ex) {
            MasaryManager.logger.error(ex.getMessage());
            msg += "-100 " + ex.getMessage();
        }
    }

    private void topupAny() {

        msisdn = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        if (msisdn == null) {
            msg += "-1 Bad request";
            return;
        }
        if (msisdn.startsWith("011")) {
            topupEtisalat();
            return;
        }
        if (msisdn.startsWith("012")) {
            topupMobinil();
            return;
        }
        if (msisdn.startsWith("010")) {
            topupVodafone();
            return;
        }
        msg += "-1 Please insert a valid mobile number";
        return;

    }

    private void cashUVoucher() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());

        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }

        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 21);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 21, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void silkRoadVouchers() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 19);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 19, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void facebookVouchers() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 20);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 20, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void oneCardVouchers() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 18);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 18, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void tahadiVoucher() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 24);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 24, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void crossFireVoucher() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 22);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 22, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void conquerVoucher() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());

        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }

        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 26);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 26, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void arabicWolfTeamVoucher() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());

        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }

        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 23);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 23, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    private void englishWolfTeamVoucher() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        timestamp = request.getParameter(CONFIG.PARAM_TIMESTAMP.toLowerCase());
        senderTrx = request.getParameter(CONFIG.PARAM_SENDER_TRX_ID.toLowerCase());
        if (amount == null || timestamp == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        if (MasaryManager.getInstance().checkTimeOut(timestamp)) {
            msg += "-6 Transaction has timed out.";
            MasaryManager.getInstance().logger.error("Transaction has timed out.");
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 25);
            if (serviceBalance < Double.parseDouble(amount)) {
                    msg += "-4 ";
                    msg += "You do not have enough balance.";
                    return;
            }
            SellVoucherResponse sellVoucherResponse = MasaryManager.getInstance().do_Voucher_transaction(Integer.parseInt(userid),
                    Double.parseDouble(amount), 25, "Y", "API", timestamp, senderTrx, "");
            if (sellVoucherResponse.getTransId().startsWith("-")) {
                MasaryManager.logger.error(sellVoucherResponse.getStatusMSG());
                msg += "-90 ";
                msg += sellVoucherResponse.getStatusMSG();
            } else {
                msg += sellVoucherResponse.getTransId() + " " + sellVoucherResponse.getVoucher() + " " + sellVoucherResponse.getSerialNumber();
            }
        } catch (Exception ex) {
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                MasaryManager.logger.error(ex.getMessage());
                msg += "-90 ";
                msg += ex.getMessage();
            }
        }
    }

    

    private void addCustomer() throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter(CONFIG.PARAM_USERNAME.toLowerCase());
        byte[] bytes = request.getParameter(CONFIG.PARAM_USERNAME_ARABIC.toLowerCase()).getBytes("ISO-8859-1");
        String usernameArabic = new String(bytes, "UTF-8");
        String sQuestion = "What was the name of your first school?";
        String sAnswer = username;
        String phone = request.getParameter(CONFIG.PARAM_MSISDN.toLowerCase());
        String autoAlocate = "Y";
        String id = request.getParameter(CONFIG.PARAM_USER_ID);
        MasaryManager.logger.info(action + " " + username + " IP " + request.getRemoteAddr());
        try {
            if (username.isEmpty() || phone.isEmpty() || !phone.startsWith("01") || phone.length() != 11) {
                msg += "-1 ";
                msg += " Bad request";
                MasaryManager.logger.error(msg);
                logIt();
                return;
            }
            boolean exist = MasaryManager.getInstance().isExistInMasary(phone);
            if (exist == true) {
                ///// 25-09 second part of condation ////////
                if (!id.equals("928") && !id.equals("148")) {
                    MasaryManager.getInstance().addLoyalityPoints(phone);
                    msg += "Points have been added to the wallet associated to " + phone;
                    MasaryManager.logger.error(msg);
                }/////25-09/////
                else {
                    msg += "-10 ";
                    msg += " Enter a Another Number";
                    MasaryManager.logger.error(msg);
                }
            } else {
                ///// 25-09 second part of condation ////////
                if (!id.equals("928") && !id.equals("148")) {
                    String custID = MasaryManager.getInstance().addCustomerFromAPI(id, usernameArabic, usernameArabic, phone, autoAlocate, sQuestion, sAnswer);
                    msg += custID + " " + phone;
                    MasaryManager.logger.error(msg);
                } else {
                    String custID = MasaryManager.getInstance().addCustomerFromAPI(id, username, usernameArabic, phone, autoAlocate, sQuestion, sAnswer);
                    msg += custID + " " + phone;
                    MasaryManager.logger.error(msg);
                }
            }
        } catch (Exception ex) {
            msg += "-100 ";
            msg += ex.getMessage();
        }
    }

    private void moneyTransferPRC() {
        amount = request.getParameter(CONFIG.AMOUNT.toLowerCase());
        msisdn = request.getParameter(CONFIG.PARAM_TO.toLowerCase());
        String prCode = request.getParameter(CONFIG.PAYMENT_REFERENCE_CODE.toLowerCase());
        if (amount == null || msisdn == null || prCode == null) {
            msg += "-1 ";
            msg += " Bad request";
            return;
        }
        try {
            if (Integer.parseInt(amount) <= 0) {
                msg += "-1 ";
                msg += " Amount must be greater than 0.";
                return;
            }
            if (Integer.parseInt(amount) < 1) {
                msg += "-1 ";
                msg += " Amount must be greater than 2.";
                return;
            }
        } catch (NumberFormatException nfe) {
            msg += "-1 ";
            msg += " Amount must be number . ";
            return;
        }
        try {
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(userid, 1);
            if (serviceBalance < Double.parseDouble(amount)) {
                msg += "-4 ";
                msg += "You do not have enough balance.";
                return;
            }
            String result = String.valueOf(MasaryManager.getInstance().addBalance(msisdn, userid, amount));
            msg += result + " Successful transaction " + prCode;
        } catch (Exception ex) {
            MasaryManager.logger.error(ex.getMessage());
            if (CONFIG.MobinilTopupRepetedError.equals(ex.getMessage())) {
                msg += "-90 " + CONFIG.MobinilTopupRepetedError;
            } else {
                msg += "-100";
                msg += CONFIG.errorTransaction;
            }
        }
    }
}
