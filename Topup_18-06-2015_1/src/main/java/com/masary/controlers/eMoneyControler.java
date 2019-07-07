/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.controlers;

import com.google.gson.Gson;
import com.masary.common.CONFIG;
import com.masary.database.dto.*;
import com.masary.database.manager.MasaryManager;
import com.masary.services.providers.Bill_Provider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;

/**
 *
 * @author Michael
 */
public class eMoneyControler extends HttpServlet {

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
            if (CONFIG.VODAFONE_CASH.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                String OPERATION_TYPE = request.getParameter(CONFIG.PARAM_OPERATION_TYPE);
                RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(SERVICE_ID, (String) session.getAttribute(CONFIG.PARAM_PIN));
                if (SERVICE_ID.equals("2003")) {
                    if (OPERATION_TYPE.equals("1")) {
                        nextJSP = CONFIG.PAGE_VODAFONE_CASHIN;
                    } else if (OPERATION_TYPE.equals("2")) {
                        nextJSP = CONFIG.PAGE_VODAFONE_CASHOUT;
                    } else if (OPERATION_TYPE.equals("3")) {
                        String custID = (String) session.getAttribute(CONFIG.PARAM_PIN);
                        try {
                            Vodafone_Cash_Transactions list = MasaryManager.getInstance().GET_VC_Transaction_List(Integer.parseInt(custID), "", "", 1);
                            session.setAttribute("List", list);
                            session.setAttribute("Page", 1);
                            session.removeAttribute("availableOptions_Key");
                            session.removeAttribute("availableOptions_Value");
                            nextJSP = CONFIG.PAGE_VODAFONE_TXN_INQUIRY;
                        } catch (Exception ex) {
                            MasaryManager.logger.error(ex);
                            session.setAttribute("ErrorMessage", ex.getMessage());
                            nextJSP = CONFIG.PAGE_ERRPR;
                        }

                    }
                    session.setAttribute("ratePlan", ratePlan);

                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.CHECKIN.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                if (SERVICE_ID.equals("2003")) {
                    nextJSP = CONFIG.PAGE_VC_CashIn_Confirmation;
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.CHECKIN_CONFIRMATION.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                int custId;
                if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
                    custId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString());
                } else {
                    custId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                }
                if (SERVICE_ID.equals("2003")) {
                    double Amount = Double.parseDouble(request.getParameter(CONFIG.AMOUNT));
                    String Mobile = request.getParameter(CONFIG.PARAM_MSISDN);
                    VC_CustomerInfo CustomerInfo = new VC_CustomerInfo();
//                    try {
//                        CustomerInfo.setCustomerName(request.getParameter("CustomerName"));
//                        CustomerInfo.setNationalID(request.getParameter(CONFIG.PARAM_NATIONAL_ID));
//                        CustomerInfo.setBirthDate(request.getParameter(CONFIG.PARAM_BIRTH_DATE));
//                        CustomerInfo.setLand_Line(request.getParameter(CONFIG.PARAM_Land_Line));
//                    } catch (Exception e) {
//                    }
                    Gson gson = new Gson();
                    String CustomerInformation = gson.toJson(CustomerInfo);
                    try {
                        String Result = MasaryManager.getInstance().Vodafone_Cash_IN(custId, Mobile, Amount, CustomerInformation, session.getAttribute(CONFIG.lang).toString());
                        session.setAttribute("Result", Result);
                        if (Result.contains("MAS 200:") && Result.contains("TXN_ID = ")) {
                            String Transaction_id = Result.substring(Result.indexOf("TXN_ID = ") + 9, Result.length());
                            String Receipt_str = MasaryManager.getInstance().GET_RECEIPT_Vodafone_Cash(Transaction_id);
                            Vodafone_Cash_Receipt receipt = gson.fromJson(Receipt_str, Vodafone_Cash_Receipt.class);
                            session.setAttribute("Receipt", receipt);
                        } else {
                            session.removeAttribute("Receipt");
                        }
                        nextJSP = CONFIG.PAGE_VC_CashIn_Result;
//                    nextJSP = CONFIG.PAGE_VC_Deposit_Result;
                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex);
                        session.setAttribute("ErrorMessage", ex.getMessage());
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }

                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }

                //----------------------------------------With Customer Info---------------------------------------
//                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
//                if (SERVICE_ID.equals("2003")) {
//                    nextJSP = CONFIG.PAGE_VC_CashIn_Customer_Info;
//                } else {
//                    nextJSP = CONFIG.PAGE_bill_inquiry;
//                }
            } else if (CONFIG.CHECKIN_Customer_Info.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                int custId;
                if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
                    custId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString());
                } else {
                    custId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                }
                if (SERVICE_ID.equals("2003")) {
                    double Amount = Double.parseDouble(request.getParameter(CONFIG.AMOUNT));
                    String Mobile = request.getParameter(CONFIG.PARAM_MSISDN);
                    VC_CustomerInfo CustomerInfo = new VC_CustomerInfo();
                    try {
                        CustomerInfo.setCustomerName(request.getParameter("CustomerName"));
                        CustomerInfo.setNationalID(request.getParameter(CONFIG.PARAM_NATIONAL_ID));
                        CustomerInfo.setBirthDate(request.getParameter(CONFIG.PARAM_BIRTH_DATE));
                        CustomerInfo.setLand_Line(request.getParameter(CONFIG.PARAM_Land_Line));
                    } catch (Exception e) {
                    }
                    Gson gson = new Gson();
                    String CustomerInformation = gson.toJson(CustomerInfo);
                    try {
                        String Result = MasaryManager.getInstance().Vodafone_Cash_IN(custId, Mobile, Amount, CustomerInformation, session.getAttribute(CONFIG.lang).toString());
                        session.setAttribute("Result", Result);
                        if (Result.contains("MAS 200:") && Result.contains("TXN_ID = ")) {
                            String Transaction_id = Result.substring(Result.indexOf("TXN_ID = ") + 9, Result.length());
                            String Receipt_str = MasaryManager.getInstance().GET_RECEIPT_Vodafone_Cash(Transaction_id);
                            Vodafone_Cash_Receipt receipt = gson.fromJson(Receipt_str, Vodafone_Cash_Receipt.class);
                            session.setAttribute("Receipt", receipt);
                        } else {
                            session.removeAttribute("Receipt");
                        }
                        nextJSP = CONFIG.PAGE_VC_CashIn_Result;
//                    nextJSP = CONFIG.PAGE_VC_Deposit_Result;

                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex);
                        session.setAttribute("ErrorMessage", ex.getMessage());
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.CHECKOUT.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                if (SERVICE_ID.equals("2003")) {
                    nextJSP = CONFIG.PAGE_VC_Cash_Out_Confirmation;
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.CHECKOUT_CONFIRMATION.equals(action)) {

                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                int custId;
                if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
                    custId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString());
                } else {
                    custId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                }
                if (SERVICE_ID.equals("2003")) {
                    double Amount = Double.parseDouble(request.getParameter(CONFIG.AMOUNT));
                    String Mobile = request.getParameter(CONFIG.PARAM_MSISDN);
                    VC_CustomerInfo CustomerInfo = new VC_CustomerInfo();
//                    try {
//                        CustomerInfo.setCustomerName(request.getParameter("CustomerName"));
//                        CustomerInfo.setNationalID(request.getParameter(CONFIG.PARAM_NATIONAL_ID));
//                        CustomerInfo.setBirthDate(request.getParameter(CONFIG.PARAM_BIRTH_DATE));
//                        CustomerInfo.setLand_Line(request.getParameter(CONFIG.PARAM_Land_Line));
//                    } catch (Exception e) {
//                    }
                    Gson gson = new Gson();
                    String CustomerInformation = gson.toJson(CustomerInfo);
                    try {
                        String Result = MasaryManager.getInstance().Vodafone_Cash_OUT(custId, Mobile, Amount, CustomerInformation, session.getAttribute(CONFIG.lang).toString());
                        String Result_T = "";
                        if (Result.contains("MAS 200:") && Result.contains("OperationID =")) {
                            int request_id = Integer.parseInt(Result.substring(Result.indexOf("OperationID =") + 14));
                            String status_msg = Result.substring(Result.indexOf(":") + 1, Result.indexOf("OperationID ="));
                            Result_T = status_msg + " " + CONFIG.getRequestId(session) + " : " + request_id;
                        } else {
                            Result_T = Result;
                        }
                        session.setAttribute("Result", Result_T);
//                    //System.out.println(Result);
                        nextJSP = CONFIG.PAGE_VC_Check_Out_Result;
                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex);
                        session.setAttribute("ErrorMessage", ex.getMessage());
                        nextJSP = CONFIG.PAGE_ERRPR;

                    }

                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }

                //-------------------with Customer Info--------------------------------
//                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
//                if (SERVICE_ID.equals("2003")) {
//                    nextJSP = CONFIG.PAGE_VC_CashOut_Customer_Info;
//                } else {
//                    nextJSP = CONFIG.PAGE_bill_inquiry;
//                }
            } else if (CONFIG.CHECKOUT_Customer_Info.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                int custId;
                if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
                    custId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).toString());
                } else {
                    custId = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                }
                if (SERVICE_ID.equals("2003")) {
                    double Amount = Double.parseDouble(request.getParameter(CONFIG.AMOUNT));
                    String Mobile = request.getParameter(CONFIG.PARAM_MSISDN);
                    VC_CustomerInfo CustomerInfo = new VC_CustomerInfo();
                    try {
                        CustomerInfo.setCustomerName(request.getParameter("CustomerName"));
                        CustomerInfo.setNationalID(request.getParameter(CONFIG.PARAM_NATIONAL_ID));
                        CustomerInfo.setBirthDate(request.getParameter(CONFIG.PARAM_BIRTH_DATE));
                        CustomerInfo.setLand_Line(request.getParameter(CONFIG.PARAM_Land_Line));
                    } catch (Exception e) {
                    }
                    Gson gson = new Gson();
                    String CustomerInformation = gson.toJson(CustomerInfo);
                    try {
                        String Result = MasaryManager.getInstance().Vodafone_Cash_OUT(custId, Mobile, Amount, CustomerInformation, session.getAttribute(CONFIG.lang).toString());
                        String Result_T = "";
                        if (Result.contains("MAS 200:") && Result.contains("OperationID =")) {
                            int request_id = Integer.parseInt(Result.substring(Result.indexOf("OperationID =") + 14));
                            String status_msg = Result.substring(Result.indexOf(":") + 1, Result.indexOf("OperationID ="));
                            Result_T = status_msg + " " + CONFIG.getRequestId(session) + " : " + request_id;
                        } else {
                            Result_T = Result;
                        }
                        session.setAttribute("Result", Result_T);
                        nextJSP = CONFIG.PAGE_VC_Check_Out_Result;

                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex);
                        session.setAttribute("ErrorMessage", ex.getMessage());
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.TRANSACTION_INQUIRY.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                int customer_ID = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString());
                if (SERVICE_ID.equals("2003")) {
                    String SearchField = request.getParameter("search");
                    String selectionOptions = request.getParameter("availableOptions");
                    String Mobile = "";
                    String Request_ID = "";
                    if (selectionOptions.equals("Mobile")) {
                        Mobile = SearchField;
                        session.setAttribute("availableOptions_Key", "Mobile");
                        session.setAttribute("availableOptions_Value", Mobile);
                    } else {
                        Request_ID = SearchField;
                        session.setAttribute("availableOptions_Key", "Request_ID");
                        session.setAttribute("availableOptions_Value", Request_ID);
                    }
                    try {
                        Vodafone_Cash_Transactions list = MasaryManager.getInstance().GET_VC_Transaction_List(customer_ID, Request_ID, Mobile, 1);
//                    Vodafone_Cash_Transactions list = MasaryManager.getInstance().GET_Vodafone_cach_Transaction_List1(customer_ID, Request_ID, Mobile);
                        session.setAttribute("Page", 1);
                        session.setAttribute("List", list);
                        nextJSP = CONFIG.PAGE_VODAFONE_TXN_INQUIRY;
                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex);
                        session.setAttribute("ErrorMessage", ex.getMessage());
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.TRANSACTION_INQUIRY_FROM_OPERATIONS.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                int customer_ID = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString());
                String Request_ID = request.getParameter(CONFIG.PARAM_OPERATION_ID);
                //System.out.println("Request_ID : " + Request_ID);
                int page = Integer.parseInt(session.getAttribute("Page").toString());
                if (SERVICE_ID.equals("2003")) {
                    String Result = MasaryManager.getInstance().VC_Transaction_Inquiry(customer_ID, Request_ID);
                    String Result_T = "";
                    if (Result.contains("MAS 200:") && Result.contains("TXN_ID = ")) {
                        int transaction_id = Integer.parseInt(Result.substring(Result.indexOf("TXN_ID =") + 9));
                        String status_msg = Result.substring(Result.indexOf(":") + 1, Result.indexOf("TXN_ID = "));
                        Result_T = status_msg + " " + CONFIG.getTransactionNumber(session) + " : " + transaction_id;
                    } else {
                        Result_T = Result;
                    }
                    session.setAttribute("Result", Result_T);

                    //-----------------------------------------To update list after inquiry-------------------------------
                    String SearchField = "";
                    String selectionOptions = "";
                    try {
                        selectionOptions = (String) session.getAttribute("availableOptions_Key");
                        SearchField = (String) session.getAttribute("availableOptions_Value");
                        if (SearchField == null) {
                            SearchField = "";
                        }
                        if (selectionOptions == null) {
                            selectionOptions = "";
                        }
                    } catch (Exception e) {
                        SearchField = "";
                        selectionOptions = "";
                    }
                    String Mobile = "";
                    String RequestID = "";
                    if (selectionOptions.equals("Mobile")) {
                        Mobile = SearchField;
                    } else {
                        RequestID = SearchField;
                    }
                    try {
                        Vodafone_Cash_Transactions list = MasaryManager.getInstance().GET_VC_Transaction_List(customer_ID, RequestID, Mobile, page);
                        session.setAttribute("List", list);
                        session.setAttribute(CONFIG.PARAM_OPERATION_ID, Request_ID);
                        nextJSP = CONFIG.PAGE_VODAFONE_TXN_INQUIRY;
                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex);
                        session.setAttribute("ErrorMessage", ex.getMessage());
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }

                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.VC_Cancel_Transaction.equals(action)) {
                int page = Integer.parseInt(session.getAttribute("Page").toString());
                int customer_ID = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString());
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
//                int customer_ID = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString());
                String Request_ID = request.getParameter(CONFIG.PARAM_OPERATION_ID);
                if (SERVICE_ID.equals("2003")) {
                    try {
                        MasaryManager.getInstance().VC_Cancel_Transaction(Request_ID);
                        //-----------------------------------------To update list after inquiry-------------------------------
                        String SearchField = "";
                        String selectionOptions = "";
                        try {
                            selectionOptions = (String) session.getAttribute("availableOptions_Key");
                            SearchField = (String) session.getAttribute("availableOptions_Value");
                            if (SearchField == null) {
                                SearchField = "";
                            }
                            if (selectionOptions == null) {
                                selectionOptions = "";
                            }
                        } catch (Exception e) {
                            SearchField = "";
                            selectionOptions = "";
                        }
                        String Mobile = "";
                        String RequestID = "";
                        if (selectionOptions.equals("Mobile")) {
                            Mobile = SearchField;
                        } else {
                            RequestID = SearchField;
                        }
                        Vodafone_Cash_Transactions list = MasaryManager.getInstance().GET_VC_Transaction_List(customer_ID, RequestID, Mobile, page);
                        session.setAttribute("List", list);
                        session.setAttribute(CONFIG.PARAM_OPERATION_ID, Request_ID);
                        nextJSP = CONFIG.PAGE_VODAFONE_TXN_INQUIRY;
                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex);
                        session.setAttribute("ErrorMessage", ex.getMessage());
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.CHECKIN_RECEIPT.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                if (SERVICE_ID.equals("2003")) {
                    nextJSP = CONFIG.PAGE_Vodafone_Cash_RECEIPT;
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.PRINT_RECEIPT_FROM_REPORT.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                String Transaction_id = request.getParameter(CONFIG.PARAM_Transaction_ID);
                Gson gson = new Gson();
                if (SERVICE_ID.equals("2003")) {
                    String Receipt_str = MasaryManager.getInstance().GET_RECEIPT_Vodafone_Cash(Transaction_id);
                    Vodafone_Cash_Receipt receipt = gson.fromJson(Receipt_str, Vodafone_Cash_Receipt.class);
                    session.setAttribute("Receipt", receipt);
                    nextJSP = CONFIG.PAGE_Vodafone_Cash_RECEIPT;
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            } else if (CONFIG.VC_NAVIGATOR.equals(action)) {
                String SERVICE_ID = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                int customer_ID = Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString());
                int page = Integer.parseInt(request.getParameter("Page"));
//                //System.out.println(page);
                if (SERVICE_ID.equals("2003")) {
                    String SearchField = "";
                    String selectionOptions = "";
                    try {
                        selectionOptions = (String) session.getAttribute("availableOptions_Key");
                        SearchField = (String) session.getAttribute("availableOptions_Value");
                        if (SearchField == null) {
                            SearchField = "";
                        }
                        if (selectionOptions == null) {
                            selectionOptions = "";
                        }
                    } catch (Exception e) {
                        SearchField = "";
                        selectionOptions = "";
                    }
//                    //System.out.println("sop : "+selectionOptions);
//                    //System.out.println("sf : "+SearchField);
                    String Mobile = "";
                    String Request_ID = "";
                    if (selectionOptions.equals("Mobile")) {
                        Mobile = SearchField;
                    } else {
                        Request_ID = SearchField;
                    }
                    try {
                        Vodafone_Cash_Transactions list = MasaryManager.getInstance().GET_VC_Transaction_List(customer_ID, Request_ID, Mobile, page);
                        session.setAttribute("Page", page);
//                    Vodafone_Cash_Transactions list = MasaryManager.getInstance().GET_Vodafone_cach_Transaction_List1(customer_ID, Request_ID, Mobile);
                        session.setAttribute("List", list);
                        nextJSP = CONFIG.PAGE_VODAFONE_TXN_INQUIRY;
                    } catch (Exception ex) {
                        MasaryManager.logger.error(ex);
                        session.setAttribute("ErrorMessage", ex.getMessage());
                        nextJSP = CONFIG.PAGE_ERRPR;
                    }
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
            }
            /**
             * *******************************************************************
             */
            try {
                session.setAttribute(CONFIG.PAGE, nextJSP);
            } catch (Exception e) {
                ////System.out.println(e);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
//            return;
        } catch (Exception e) {
            // //System.out.println("Bill_Controler : " + e);
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
