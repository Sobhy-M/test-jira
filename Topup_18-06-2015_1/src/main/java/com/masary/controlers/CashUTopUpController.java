/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers;

import com.masary.common.CONFIG;
import com.masary.database.dto.Bill_Response;
import com.masary.database.dto.CashURequest;
import com.masary.database.dto.CashUResponse;
import com.masary.database.dto.Masary_Bill_Receipt;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.dto.RatePlanDTO;
import com.masary.database.manager.MasaryManager;
//import com.sun.tools.xjc.reader.dtd.bindinfo.BIAttribute;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aya
 */
public class CashUTopUpController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
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
                if (CONFIG.CashUTopUp.equals(action)) {
                    String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(SERVICE_ID, (String) session.getAttribute(CONFIG.PARAM_PIN));
                    nextJSP = CONFIG.PAGE_CashU_TopUp;
                    session.setAttribute("ratePlan", ratePlan);
                } else if (CONFIG.CashUTopUpConfirmation.equals(action)) {
                    String Amount = request.getParameter(CONFIG.AMOUNT);
                    String AmountEGP = request.getParameter(CONFIG.AMOUNTEGP);
                    int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                    String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
                    int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                    request.setAttribute("Fees", request.getParameter("Fees"));
                    request.setAttribute("Commession", request.getParameter("Commession"));
                    request.setAttribute("Balance_Diff", request.getParameter("Balance_Diff"));
                    request.setAttribute(" ", request.getParameter("DeductedAmount"));
                    CashURequest cashURequest = new CashURequest();
                    if (Amount == null) {
                        cashURequest.setAmount(new BigDecimal(AmountEGP));
                    } else {
                        cashURequest.setAmount(new BigDecimal(Amount));

                    }
                    cashURequest.setUserName(request.getParameter(CONFIG.UserNameCahsU));
                    cashURequest.setEmail(request.getParameter(CONFIG.Email));
                    cashURequest.setUserId(request.getParameter(CONFIG.AccountCahsU));
                    cashURequest.setCUSTOMER_ID(customer_id);
                    cashURequest.setLANG(lang);
                    cashURequest.setSERVICE_ID(service_id);
                    CashUResponse cashUResponse = MasaryManager.getInstance().GetInfoAccount(cashURequest);
                    cashUResponse.setFEES(Double.parseDouble(request.getParameter("Fees")));
                    if (cashUResponse.getSTATUS_CODE() == 200) {
                        session.setAttribute("cashUResponse", cashUResponse);
                        session.setAttribute("Amount", cashURequest.getAmount());
                        Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                        session.setAttribute("BTC", bill_Type);
                        nextJSP = CONFIG.PAGE_CashU_TopUp_Confirmation;
                    } else {
                        nextJSP = CONFIG.PAGE_CashU_TopUp;
                        request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + cashUResponse.getSTATUS_CODE() + " " + cashUResponse.getSTATUS_MESSAGE());
                    }
                } else if (CONFIG.CashUTopUpResult.equals(action)) {
                    try {
                        String CashU_Account = request.getParameter(CONFIG.AccountCahsU);
                        String Amount = request.getParameter(CONFIG.AMOUNT);
                        String AMOUNTDolar = request.getParameter(CONFIG.AMOUNTDolar);
                        int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                        String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
                        int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                        request.setAttribute("Fees", request.getParameter("Fees"));
                        request.setAttribute("Commession", request.getParameter("Commession"));
                        request.setAttribute("Balance_Diff", request.getParameter("Balance_Diff"));
                        request.setAttribute("DeductedAmount", request.getParameter("DeductedAmount"));
                        request.setAttribute("AMOUNTDolar", AMOUNTDolar);
                        CashUResponse inquiry_response = (CashUResponse) session.getAttribute("cashUResponse");
                        CashURequest cashURequest = new CashURequest();
                        cashURequest.setAmount(new BigDecimal(Amount));
                        cashURequest.setCUSTOMER_ID(customer_id);
                        cashURequest.setLANG(lang);
                        cashURequest.setSERVICE_ID(service_id);
                        cashURequest.setUserId(inquiry_response.getUserId());
                        //System.out.println("UserID" + inquiry_response.getUserId());
                        cashURequest.setBILL_REFERENCE_NUMBER(inquiry_response.getBILL_REFERENCE_NUMBER());
                        cashURequest.setRefID(inquiry_response.getBILL_REFERENCE_NUMBER());
                        CashUResponse cashUResponse = MasaryManager.getInstance().TopUpCASHU(cashURequest);
                        cashUResponse.setFEES(Double.parseDouble(request.getParameter("Fees")));
                        cashUResponse.setHolderName(inquiry_response.getHolderName());
                        if (cashUResponse.getSTATUS_CODE() == 200) {
                            cashUResponse.setUserId(inquiry_response.getUserId());
                            session.setAttribute("cashUResponse", cashUResponse);
                            Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                            session.setAttribute("BTC", bill_Type);
                            nextJSP = CONFIG.PAGE_CashU_TopUp_Result;
                        } else {
                            nextJSP = CONFIG.PAGE_CashU_TopUp;
                            request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + cashUResponse.getSTATUS_CODE() + " " + cashUResponse.getSTATUS_MESSAGE());
                        }
                        session.setAttribute(CONFIG.PAGE, nextJSP);
                    } catch (Exception ex) {
                        session.setAttribute("ErrorMessage", ex.getMessage());
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }
                } else if (CONFIG.ACTION_Print_Reciept.equals(action)) {
                    session.setAttribute("transaction_id", request.getParameter("TXN_ID"));
                    session.setAttribute("serv_id", request.getParameter("Serv_Id"));
                    Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
                    receipt = MasaryManager.getInstance().getreceipt(Integer.parseInt(request.getParameter("transaction_id")));
                    session.setAttribute("receipt", receipt);
                    nextJSP = CONFIG.PAGE_reciept;

                }

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
                dispatcher.forward(request, response);
//                    //            return;
            } catch (Exception e) {
                MasaryManager.logger.error(e.getMessage());
                MasaryManager.logger.error(e.getStackTrace());
            } finally {
                //            out.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(CashUTopUpController.class.getName()).log(Level.SEVERE, null, ex);
            MasaryManager.logger.error(ex.getStackTrace());

        } finally {
            out.close();
//            out.close();
        }
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
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
