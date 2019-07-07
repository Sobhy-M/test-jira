/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers;

import com.masary.common.CONFIG;
import com.masary.database.manager.MasaryManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author omnya
 */
public class Admin_Controller extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();
        action = request.getParameter(CONFIG.PARAM_ACTION);

        try {
            if (!isLogin(session)) {
                if (!(CONFIG.ACTION_AUTHENTICATE_USER.equals(action) || CONFIG.ACTION_RESET_PASSWORD.equals(action))) {
                    nextJSP = CONFIG.PAGE_LOGIN;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                    dispatcher.forward(request, response);
                    return;
                }
                session.setAttribute(CONFIG.LOGIN_IP, request.getRemoteAddr());
            }
            if (CONFIG.ACTION_ADMIN_OPERATIONS.equals(action)) {
                nextJSP = adminOperations(request);
            } else if (CONFIG.ACTION_ADD_AGENT.equals(action)) {
                nextJSP = addAgent(request);
            } else if (CONFIG.ACTION_EDIT_AGENT.equals(action)) {
                nextJSP = editAgent(request);
            } else if (CONFIG.ACTION_ASIGN_AGENT_BALANCE.equals(action)) {
                nextJSP = assignAgentBalance(request);
            } else if (CONFIG.ACTION_MANAGE_AGENT_BALANCE.equals(action)) {
                nextJSP = manageAgentBalance(request);
            } else if (CONFIG.ACTION_MANAGE_ADMIN.equals(action)) {
                nextJSP = manageAdmin(request);
            } else if (CONFIG.ACTION_MANAGE_ADMIN_BALANCE.equals(action)) {
                nextJSP = manageAdminBalance(request);
            } else if (CONFIG.ACTION_VIEW_ALL_SERVICES.equals(action)) {
                nextJSP = enableAndDisableService(request);
            } else if (CONFIG.ACTION_REFUND.equals(action)) {
                nextJSP = refund(request);
            } else if (CONFIG.ACTION_SEARCH.equals(action)) {
                nextJSP = search();
            } else if (CONFIG.ACTION_SERVICE_PRICE.equals(action)) {
                nextJSP = servicePrice(request);
            } else if (CONFIG.ACTION_Get_Balance_Sheet_Men.equals(action)) {
                nextJSP = CONFIG.PAGE_Balance_Sheet;
            } else if (CONFIG.ACTION_Get_Pin_Change_Men.equals(action)) {
                nextJSP = CONFIG.PAGE_PIN_CHANGE;
            }
            try {
                session.setAttribute(CONFIG.PAGE, nextJSP);
            } catch (Exception e) {
                nextJSP = CONFIG.PAGE_ERRPR;
                session.setAttribute("ErrorMessage", e.getMessage());
                MasaryManager.logger.error(e);
            }

        } catch (Exception e) {
            nextJSP = CONFIG.PAGE_ERRPR;
            session.setAttribute("ErrorMessage", e.getMessage());
            MasaryManager.logger.error(e);
        } finally {
//            out.close();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
        }
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

    private String refund(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String confirm = request.getParameter(CONFIG.CONFIRM);
        if (CONFIG.CONFIRM.equals(confirm)) {
            String transId = (String) request.getParameter(CONFIG.PARAM_Transaction_ID);
            try {
                transId = MasaryManager.getInstance().refund(transId, (String) request.getSession().getAttribute(CONFIG.PARAM_PIN));
                session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
                request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
                nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION + "?v_and_s=" + " ";
            } catch (Exception ex) {
                MasaryManager.logger.error(ex);
                session.setAttribute(
                        "ErrorMessage", ex.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;
            }
        } else {
            nextJSP = CONFIG.PAGE_REFUND;
        }
        return nextJSP;
    }

    private String adminOperations(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String btn = request.getParameter(CONFIG.PARAM_ADMIN_BTN);
        MasaryManager.logger.info(action + "  " + btn + " IP " + request.getRemoteAddr());
        if (btn.equals("Add Agent")) {
            nextJSP = CONFIG.PAGE_ADD_AGENT;
        } else if (btn.equals("View Agents")) {
            nextJSP = CONFIG.PAGE_VIEW_AGENTS;
        } else if (btn.equals("View Services")) {
            nextJSP = CONFIG.PAGE_VIEW_SERVICES;
        } else if (btn.equals("Upload 2MVouchers")) {
            nextJSP = CONFIG.PAGE_VOUCHER_UPLOAD;
        } else if (btn.equals("View Providers")) {
            nextJSP = CONFIG.PAGE_VOUCHER_PROVIDERS;
        } else if (btn.equals("Send Sync")) {
            MasaryManager.getInstance().sendSync();
        } else if (btn.equals("View availabe vouchers")) {
            nextJSP = CONFIG.PAGE_VOUCHER_VALUES;
        } else if (btn.equals("View Bank Account Information")) {
            request.getSession().removeAttribute("include");
            request.getSession().removeAttribute("transFromAg");
            request.getSession().removeAttribute("transToAg");
            String agentID = request.getParameter("custIdForAccInfo");
            session.setAttribute("custIdForAccSession", agentID);
            List transFromAg = MasaryManager.getInstance().getTransactionsByPayer(agentID);
            List transToAg = MasaryManager.getInstance().getTransactionsByPayed(agentID);
            request.getSession().setAttribute("transFromAg", transFromAg);
            request.getSession().setAttribute("transToAg", transToAg);
            nextJSP = CONFIG.PAGE_VIEW_BANK_ACCOUNT;
        } else if (btn.equals("Manage Account")) {
            nextJSP = CONFIG.PAGE_MANAGE_ACCOUNT_ADMIN;
        } else if (btn.equals("Manage Balance")) {
            nextJSP = CONFIG.PAGE_MANAGE_BALANCE_ADMIN;
        } else if (btn.equals("ALL Balances")) {
            nextJSP = CONFIG.PAGE_MANAGE_BALANCE;
        } else if (btn.equals("Refund")) {
            nextJSP = CONFIG.PAGE_REFUND;
        } else if (btn.equals("Search")) {
            nextJSP = CONFIG.PAGE_SEARCH;
        } else if (btn.equals("Service Price")) {
            nextJSP = CONFIG.PAGE_SERVICE_PRICE;
        } else if (btn.equals("Track")) {
            session.setAttribute(CONFIG.PARAM_AGENT_ID, request.getParameter("custIdForAccInfo"));
            nextJSP = CONFIG.PAGE_TRACK;
        }
        return nextJSP;
    }

    private String search() {
        String nextJSP = CONFIG.PAGE_SEARCH;
        return nextJSP;
    }

    public String enableAndDisableService(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        Enumeration e = request.getParameterNames();
        String name = null, serviceID = null;
        nextJSP = "";
        try {
            while (e.hasMoreElements()) {
                serviceID = (String) e.nextElement();
                if (!serviceID.equals("action")) {
                    byte[] bytes = request.getParameter(serviceID).getBytes("ISO-8859-1");
                    name = new String(bytes, "UTF-8");
                    int status = 0;
                    if (name.equals(CONFIG.getDeactivate(session))) {
                        status = 0;
                    } else if (name.equals(CONFIG.getActivate(session))) {
                        status = 1;
                    }
                    MasaryManager.getInstance().setServiceEnabledOrDisabled(serviceID, status);
                    nextJSP = CONFIG.PAGE_VIEW_SERVICES;
                }
            }
            MasaryManager.logger.info(action + " " + serviceID + " " + name + " IP " + request.getRemoteAddr());
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        return nextJSP;
    }

    private String addAgent(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        try {
            String action = request.getParameter(CONFIG.PARAM_ACTION);
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter(CONFIG.PARAM_USERNAME);
            byte[] bytes = request.getParameter(CONFIG.PARAM_USERNAME_ARABIC).getBytes("ISO-8859-1");
            String usernameArabic = new String(bytes, "UTF-8");
            String sQuestion = request.getParameter(CONFIG.PARAM_QUESTION);
            String sAnswer = request.getParameter(CONFIG.PARAM_ANSWER);
            String phone = request.getParameter(CONFIG.PARAM_MSISDN);
            String confirm = request.getParameter(CONFIG.CONFIRM);
            session.setAttribute(CONFIG.PARAM_USERNAME_ARABIC, usernameArabic);
            String isRetailGroup = ("on".equals(request.getParameter(CONFIG.PARAM_RETAIL_GROUP)) ? "Y" : "N");
            MasaryManager.logger.info(action + " " + username + " IP " + request.getRemoteAddr());
            if (confirm != null) {
                nextJSP = CONFIG.PAGE_ADD_AGENT_CONFIRM;
                request.removeAttribute(CONFIG.CONFIRM);
            } else {
                String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
                try {
                    if (username.equals("") || sQuestion.equals("") || sAnswer.equals("")) {
                        throw new Exception(CONFIG.getFillInAllData(session));
                    }
                    MasaryManager.getInstance().addAgent(id, username, usernameArabic, phone, isRetailGroup, "F", sQuestion, sAnswer, request);
                    nextJSP = "1.jsp";
                } catch (Exception ex) {
                    MasaryManager.logger.error(ex);
                    session.setAttribute("ErrorMessage", ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
            }
        } catch (UnsupportedEncodingException ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        return nextJSP;
    }

    private String servicePrice(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        Enumeration e = request.getParameterNames();
        String name = null, serviceId = null;
        nextJSP = "";
        String confirm = request.getParameter(CONFIG.CONFIRM);
        if (confirm != null) {
            String newPrice = request.getParameter(CONFIG.AMOUNT);
            serviceId = (String) session.getAttribute(CONFIG.PARAM_SERVICE_ID);
            try {
                MasaryManager.getInstance().changeServicePrice(serviceId, newPrice);
                nextJSP = CONFIG.PAGE_SERVICE_PRICE;
            } catch (SQLException ex) {
                MasaryManager.logger.error(ex);
                session.setAttribute(
                        "ErrorMessage", ex.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;
            }
        } else {
            while (e.hasMoreElements()) {
                serviceId = (String) e.nextElement();
                try {
                    byte[] bytes = request.getParameter(serviceId).getBytes("ISO-8859-1");
                    name = new String(bytes, "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    MasaryManager.logger.error(ex);
                }
                if (name.equals(CONFIG.getChange(session))) {
                    session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                    nextJSP = CONFIG.PAGE_SERVICE_PRICE_CHANGE;
                    break;
                }
            }
        }
        return nextJSP;
    }

    private String manageAgentBalance(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String edit = request.getParameter("btnEdit");
        if (edit == null) {
            String agentId = (String) session.getAttribute(CONFIG.PARAM_AGENT_ID);
            String parentID = (String) session.getAttribute(CONFIG.PARAM_PIN);
            try {
                String transId = String.valueOf(MasaryManager.getInstance().resetBalance(parentID, agentId));
                session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
                request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
                nextJSP
                        = CONFIG.PAGE_VIEW_TRANSACTION;
            } catch (Exception ex) {
                MasaryManager.logger.error(ex);
                session.setAttribute(
                        "ErrorMessage", ex.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;
            }
        } else {
            nextJSP = CONFIG.PAGE_MANAGE_EDIT_AGENT;
        }
        MasaryManager.logger.info(action + " " + edit + " IP " + request.getRemoteAddr());
        return nextJSP;
    }

    private String manageAdminBalance(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String balance = (String) request.getParameter(CONFIG.PARAM_BALANCE);
        String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
        String role = (String) session.getAttribute(CONFIG.PARAM_ROLE);
        String btn = getButton(request);
        MasaryManager.logger.info(action + " " + id + " IP " + request.getRemoteAddr());
        try {
            if (Double.parseDouble(balance) > 0) {
                MasaryManager.getInstance().updateUserBalance(id, balance, (btn.equals(CONFIG.getChange(session)) ? 0 : 1));
                nextJSP = role + ".jsp";
            } else {
                throw new Exception(CONFIG.getBalanceError(session));
            }
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        return nextJSP;
    }

    private String getButton(HttpServletRequest request) {
        byte[] bytes;
        try {
            bytes = request.getParameter("btnSubmit").getBytes("ISO-8859-1");
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            MasaryManager.logger.error(ex);
            return null;
        }
    }

    private String manageAdmin(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        String password = request.getParameter(CONFIG.PARAM_PASSWORD);
        String confirmPassword = request.getParameter(CONFIG.PARAM_CONFIRM_PASSWORD);
        String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
        String sQ = request.getParameter(CONFIG.PARAM_QUESTION);
        String answer = request.getParameter(CONFIG.PARAM_ANSWER);
        String role = (String) session.getAttribute(CONFIG.PARAM_ROLE);
        String sessionStr = request.getParameter(CONFIG.PARAM_SEESSIONTIME);
        int sessionTimeOut = Integer.parseInt(sessionStr);
		String oldPassword = request.getParameter(CONFIG.PARAM_OLD_PASSWORD);

        MasaryManager.logger.info(action + " " + id + " IP " + request.getRemoteAddr());
        try {
            if (password.length() < 4 || password.length() > 20) {
                throw new Exception(CONFIG.getPasswordLengthError(session));
            }
            if (password.equals(confirmPassword)) {
                System.out.println("password before" + password);
                MasaryManager.getInstance().updateUser(id, password, sQ, answer, sessionTimeOut,oldPassword);
                nextJSP = role + ".jsp";
            } else {
                throw new Exception(CONFIG.getPasswordConfirmationError(session));
            }
        } catch (Exception ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        return nextJSP;
    }

    private String editAgent(HttpServletRequest request) {
        String nextJSP = "";
        try {
            HttpSession session = request.getSession();
            String action = request.getParameter(CONFIG.PARAM_ACTION);
            String pin = request.getParameter(CONFIG.PARAM_PIN);
            String name = request.getParameter(CONFIG.PARAM_NAME);
            byte[] bytes = request.getParameter(CONFIG.PARAM_USERNAME_ARABIC).getBytes("ISO-8859-1");
            String arabicName = new String(bytes, "UTF-8");
            String phone = request.getParameter(CONFIG.PARAM_PHONE);
            MasaryManager.logger.info(action + " " + pin + " IP " + request.getRemoteAddr());
            try {
                MasaryManager.getInstance().updateAgent(pin, name, arabicName, phone, request);
                nextJSP = CONFIG.PAGE_VIEW_AGENTS;
            } catch (SQLException ex) {
                MasaryManager.logger.error(ex);
                session.setAttribute("ErrorMessage", ex.getMessage());
                nextJSP = CONFIG.PAGE_ERRPR;
            }
        } catch (UnsupportedEncodingException ex) {
            MasaryManager.logger.error(ex);
        }
        return nextJSP;
    }

    private String assignAgentBalance(HttpServletRequest request) {
        String nextJSP = "";
        HttpSession session = request.getSession();
        String action = request.getParameter(CONFIG.PARAM_ACTION);
        Enumeration e = request.getParameterNames();
        String name = null, agentId = null;
        try {
            while (e.hasMoreElements()) {
                agentId = (String) e.nextElement();
                name = request.getParameter(agentId);
                try {
                    byte[] bytes = request.getParameter(agentId).getBytes("ISO-8859-1");
                    name = new String(bytes, "UTF-8");
                } catch (UnsupportedEncodingException ex) {
                    MasaryManager.logger.error(ex);
                }
                if (name.equals(CONFIG.getAssign(session))) {
                    break;
                }
                if (name.equals(CONFIG.getManageAgent(session))) {
                    nextJSP = CONFIG.MANAGE_ROLES;
                    session.setAttribute(CONFIG.PARAM_AGENT_ID, agentId);
                    break;
                }
                if (name.equals(CONFIG.getDeactivate(session))) {
                    MasaryManager.getInstance().setAgentStatus(agentId, false);
                    nextJSP = CONFIG.PAGE_VIEW_AGENTS;
                    break;
                }
                if (name.equals(CONFIG.getActivate(session))) {
                    MasaryManager.getInstance().setAgentStatus(agentId, true);
                    nextJSP = CONFIG.PAGE_VIEW_AGENTS;
                    break;
                }
            }
        } catch (SQLException ex) {
            MasaryManager.logger.error(ex);
            session.setAttribute("ErrorMessage", ex.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        }
        if (!(CONFIG.PAGE_VIEW_AGENTS.equals(nextJSP) || CONFIG.MANAGE_ROLES.equals(nextJSP))) {
            String balance = request.getParameter(CONFIG.PARAM_BALANCE);
            String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);
            if (balance == null) {
                session.setAttribute(CONFIG.PARAM_AGENT_ID, agentId);
                nextJSP = CONFIG.PAGE_ASSIGN_AGENTS;
            } else {
                agentId = (String) session.getAttribute(CONFIG.PARAM_AGENT_ID);
                String id = (String) session.getAttribute(CONFIG.PARAM_PIN);
                try {
                    String transId = String.valueOf(MasaryManager.getInstance().addBalance(agentId, id, balance));
                    if (serviceId != null) {
                        MasaryManager.getInstance().updateTransType(transId, serviceId);
                        MasaryManager.getInstance().assignServiceBalance(agentId, serviceId, balance, (id.equals("1") ? 1 : 0));
                    }
                    session.setAttribute(CONFIG.PARAM_Transaction_ID, transId);
                    request.setAttribute(CONFIG.PARAM_NEXT_PAGE, CONFIG.PAGE_VIEW_AGENTS);
                    nextJSP = CONFIG.PAGE_VIEW_TRANSACTION;
                } catch (Exception ex) {
                    MasaryManager.logger.error(ex);
                    session.setAttribute("ErrorMessage",
                            ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
            }
        }
        MasaryManager.logger.info(action + " " + agentId + " IP " + request.getRemoteAddr());
        return nextJSP;
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
