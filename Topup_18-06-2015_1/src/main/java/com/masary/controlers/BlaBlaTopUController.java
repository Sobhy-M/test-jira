/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masary.common.CONFIG;
import com.masary.database.dto.RatePlanDTO;
import com.masary.database.manager.MasaryManager;

/**
 *
 * @author Aya
 */
public class BlaBlaTopUController extends HttpServlet {

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
            if (CONFIG.BlaBlaTopUp.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(SERVICE_ID, (String) session.getAttribute(CONFIG.PARAM_PIN));
                nextJSP = CONFIG.PAGE_BlaBla_TopUp;
                session.setAttribute("ratePlan", ratePlan);
            } else if (CONFIG.BlaBlaTopUpConfirmation.equals(action)) {
                String Amount = request.getParameter(CONFIG.AMOUNT);
                String amount_dolar = request.getParameter(CONFIG.AMOUNTDolar);
                session.setAttribute(CONFIG.AMOUNTDolar, amount_dolar);
                nextJSP = CONFIG.PAGE_BlaBla_TopUp_Confirmation;
            } else if (CONFIG.BlaBlaTopUpResult.equals(action)) {
                try {
                    String Customer_Id = (String) session.getAttribute(CONFIG.PARAM_PIN);
                    String BlaBla_Account = request.getParameter(CONFIG.PARAM_MSISDN);
                    String Amount = request.getParameter(CONFIG.AMOUNT);
                    String amount_dolar = (String) session.getAttribute(CONFIG.AMOUNTDolar);
                    if (BlaBla_Account.startsWith("01")&& BlaBla_Account.length()==11) {
                        BlaBla_Account = 2+BlaBla_Account;
                        //System.out.print(BlaBla_Account);
                    } else if (BlaBla_Account.startsWith("001")) {
                        BlaBla_Account = 2+BlaBla_Account.substring(1);
                        //System.out.print(BlaBla_Account);
                    }
                     else if (BlaBla_Account.startsWith("00201")&&BlaBla_Account.length()==14) {
                        BlaBla_Account = BlaBla_Account.substring(2);
                        //System.out.print(BlaBla_Account);
                    }
                     else if (BlaBla_Account.startsWith("00")&&BlaBla_Account.length()>13) {
                        BlaBla_Account = BlaBla_Account.substring(2);
                        //System.out.print(BlaBla_Account);
                    }
//                    String result = "";
                    String result = MasaryManager.getInstance().BlaBla_TopUp(Customer_Id, BlaBla_Account, Amount, amount_dolar, null, null);
                    if (result != null) {
                        session.setAttribute(CONFIG.PARAM_Transaction_ID, result);
                        nextJSP = CONFIG.PAGE_VIEW_CUSTOMER_TRANSACTION;
                    } else {
                        session.setAttribute("ErrorMessage", CONFIG.errorTransaction);
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }
                    session.setAttribute(CONFIG.PAGE, nextJSP);
                } catch (Exception ex) {
                    session.setAttribute("ErrorMessage", ex.getMessage());
                    nextJSP = CONFIG.PAGE_ERRPR;
                }
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
//            return;
        } catch (ServletException e) {
            MasaryManager.logger.error(e.getMessage());
            MasaryManager.logger.error(e.getStackTrace());
        } catch (IOException e) {
            MasaryManager.logger.error(e.getMessage());
            MasaryManager.logger.error(e.getStackTrace());
        } finally {
//            out.close();
        }
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
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
