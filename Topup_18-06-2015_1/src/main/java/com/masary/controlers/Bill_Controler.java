package com.masary.controlers;
import com.masary.Bill_Manager;
import com.masary.XMLGregorianCalendarConverter;
import com.masary.build_request;
import com.masary.common.CONFIG;
import com.masary.database.dto.Bill_Request;
import com.masary.database.dto.Bill_Response;
import com.masary.database.dto.DonationAgentPaymentRespponseDto;
import com.masary.database.dto.Donation_AgentPaymentRequestDTO;
import com.masary.database.dto.IsValidDTO;
import com.masary.database.dto.Main_Provider;
import com.masary.database.dto.Masary_Balance_Sheet;
import com.masary.database.dto.Masary_Bill_Amounts;
import com.masary.database.dto.Masary_Bill_Receipt;
import com.masary.database.dto.Masary_Bill_Response;
import com.masary.database.dto.Masary_Bill_Status;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.dto.Masary_Error;
import com.masary.database.dto.Vodafone_Bill_Response;
import com.masary.database.manager.MasaryManager;
import com.masary.services.providers.Bill_Provider;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;
import nl.captcha.Captcha;

/**
 *
 * @author Michael
 */
public class Bill_Controler extends HttpServlet {

    /**
     * @Variable definations
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextJSP = "";
        String action;
        HttpSession session = request.getSession();
        java.util.Date date = new Date();
        XMLGregorianCalendarConverter xMLGregorianCalendarConverter = new XMLGregorianCalendarConverter();
        XMLGregorianCalendar xMLGregorianCalendar = xMLGregorianCalendarConverter.asXMLGregorianCalendar(date);
        final int telcomEgyptRechareBill = 125, telcomEgyptBill = 124, teDataBill = 117, electricityBill = 99007, mobinilBill = 112, vodafoneBill = 111, BeatElzakaZkH = 55551, BeatElzakaSDK = 55552, BeatElzakaSkok = 55555, orangeDsl = 115;
        Bill_Manager bill_Manager = new Bill_Manager();
        build_request build_request = new build_request();
//        MasaryManager manager = new MasaryManager();
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
                    if (CONFIG.ACTION_CUSTOMER_Bill_Inquiry.equals(action)) {
                String BTC = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                if (BTC.equals(String.valueOf(telcomEgyptRechareBill))) {
                    nextJSP = CONFIG.PAGE_Telecom_Rechsrge_inquiry;
                } else if (BTC.equals("115")) {
                    nextJSP = CONFIG.OrangeDslInqueryPage;
                } else if (BTC.equals("55552") || BTC.equals("55551") || BTC.equals("55555")) {
                    nextJSP = CONFIG.BeatElzakahInqueryPage;
                } else {
                    nextJSP = CONFIG.PAGE_bill_inquiry;
                }
                session.setAttribute("serv_id", BTC);
            } /**
             * *******************************************************************
             */
            else if ("ACTION_BILL_TRANSACTIONS".equals(action)) {
                String Service_id = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                String bill = request.getParameter("bill");
                nextJSP = CONFIG.PAGE_BILL_TRANSACTIONS;
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, Service_id);
                session.setAttribute("bill", bill);
                session.removeAttribute("DateFrom");
                session.removeAttribute("DateTo");
            } /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_Get_Bill_Transaction.equals(action)) {
                String Service_id = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                session.setAttribute("DateFrom", request.getParameter("From"));
                session.setAttribute("DateTo", request.getParameter("To"));
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, Service_id);
                nextJSP = CONFIG.PAGE_BILL_TRANSACTIONS;
            } /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_Get_Bill_Inquiry_Res.equals(action)) {
                // String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
                int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
//                String password = request.getParameter(CONFIG.PARAM_PASSWORD);
//Service name and number 
                if (service_id == telcomEgyptBill || service_id == teDataBill || service_id == electricityBill || service_id == mobinilBill || service_id == vodafoneBill || service_id == BeatElzakaSDK || service_id == BeatElzakaSkok || service_id == BeatElzakaZkH || service_id == orangeDsl) {
                    String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
                    String billing_account = request.getParameter(CONFIG.PARAM_MSISDN);
                    int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                    Bill_Request bill_Request = new Bill_Request();
                    bill_Request.setCUSTOMER_ID(customer_id);
                    bill_Request.setBILLING_ACCOUNT(billing_account);
                    bill_Request.setCHANNEL("WEB");
                    bill_Request.setLANG(lang);
                    bill_Request.setSERVICE_ID(service_id);
                    Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().Inquiry(bill_Request));
                  if (service_id == teDataBill||service_id == vodafoneBill||service_id == mobinilBill) {
                    //if (service_id == teDataBill) {
//                    if (service_id == teDataBill) {
                        String commFees = MasaryManager.getInstance().getfeesInquiry(customer_id, service_id, Float.parseFloat(String.valueOf(bill_Response.getAMOUNT())));
                        session.setAttribute("commFees", commFees);
                        //System.out.println("commFees"+commFees);
                    }
                    bill_Response.setBILLING_ACCOUNT(billing_account);
                    if (bill_Response.getSTATUS_CODE() == 200) {
                        session.setAttribute("bill_Response", bill_Response);
                        Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                        session.setAttribute("BTC", bill_Type);
                        switch (service_id) {
                            case orangeDsl:
                                nextJSP = CONFIG.OrangeDslInqueryResultPage;
                                break;
                            case BeatElzakaSDK:
                                nextJSP = CONFIG.BeatElzakahInqueryResultPage;
                                break;
                            case BeatElzakaSkok:
                                nextJSP = CONFIG.BeatElzakahInqueryResultPage;
                                break;
                            case BeatElzakaZkH:
                                nextJSP = CONFIG.BeatElzakahInqueryResultPage;
                                break;
                            default:
                                nextJSP = CONFIG.PAGE_bill_info;
                                break;
                        }

                    } else if (service_id == orangeDsl) {
                        nextJSP = CONFIG.OrangeDslInqueryPage;
                        request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());

                    } else if (service_id == BeatElzakaSDK || service_id == BeatElzakaZkH || service_id == BeatElzakaSkok) {
                        nextJSP = CONFIG.BeatElzakahInqueryPage;
                        request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());

                    } else {
                        nextJSP = CONFIG.PAGE_bill_inquiry;
                        request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());

                    }

                } else {
                    String BTC = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                    String phone = request.getParameter(CONFIG.PARAM_MSISDN);
                    String custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
                    Main_Provider provider = getProvider(Integer.parseInt(BTC), 50, custId);
                    Masary_Error Status = new Masary_Error();
                    if (provider.equals(null) || provider.getPROVIDER_ID() == 0) {
                        Status = MasaryManager.getInstance().GETMasary_Error("-506", provider);
                        MasaryManager.logger.error("ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                        nextJSP = CONFIG.PAGE_bill_inquiry;
                        session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));
                    } else {
                        Status = dobillinquiry(BTC, phone, custId, provider, session);
                        try {
                            if (Status.getSTATUS_CODE() == 200) {
                                MasaryManager.logger.info("ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                                Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(BTC);
                                session.setAttribute("BTC", bill_Type);
                                nextJSP = CONFIG.PAGE_bill_info_old;
                                session.setAttribute("paymentreq", "N");
                            } else {
                                MasaryManager.logger.error("ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                                nextJSP = CONFIG.PAGE_bill_inquiry;
                                session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));
                            }
                        } catch (Exception e) {
                            MasaryManager.logger.error("Bill_Controler.BillInquery : " + e.getMessage(), e);

                            if (e.getMessage().contains("ORA-20139")) {
                                session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                                nextJSP = CONFIG.PAGE_ERRPR_SSO;
                            } else {
                                MasaryManager.logger.error("ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                                nextJSP = CONFIG.PAGE_bill_inquiry;
                                session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));
                            }
                        }
                    }
                }
            } /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_Get_Recharge_Inquiry_Res.equals(action)) {

                String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
                int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                String billing_account = request.getParameter(CONFIG.PARAM_MSISDN);
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                request.setAttribute("Fees", request.getParameter("Fees"));
                //System.out.println("Fees" + request.getParameter("Fees"));
                request.setAttribute("BILLING_ACCOUNT", request.getParameter("BILLING_ACCOUNT"));
                request.setAttribute("Commession", request.getParameter("Commession"));
                request.setAttribute("Balance_Diff", request.getParameter("Balance_Diff"));
                request.setAttribute("DeductedAmount", request.getParameter("DeductedAmount"));
                request.setAttribute("ADDITIONAL_INFO", request.getParameter("ADDITIONAL_INFO"));
                request.setAttribute("Customer_Mobile", request.getParameter(CONFIG.NotifyMobile));
                request.setAttribute("MONEY_PAID", request.getParameter(CONFIG.MONEY_PAID));
                //System.out.println("MONEY_PAID" + request.getParameter(CONFIG.MONEY_PAID));
                request.setAttribute("QUOTA", request.getParameter(CONFIG.QUOTA));
                Bill_Request bill_Request = new Bill_Request();
                bill_Request.setCUSTOMER_ID(customer_id);
                bill_Request.setBILLING_ACCOUNT(billing_account);
                bill_Request.setCHANNEL("WEB");
                bill_Request.setQUOTA(Integer.parseInt(request.getAttribute(CONFIG.QUOTA).toString()));
                //System.out.println("" + bill_Request.getQUOTA());
                bill_Request.setLANG(lang);
                bill_Request.setSERVICE_ID(service_id);
                Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().Inquiry(bill_Request));
                bill_Response.setBILLING_ACCOUNT(billing_account);
                bill_Response.setFEES(Double.parseDouble(request.getParameter("Fees")));
                if (bill_Response.getSTATUS_CODE() == 200) {
                    session.setAttribute("bill_Response", bill_Response);
                    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                    session.setAttribute("BTC", bill_Type);
                    request.setAttribute("Customer_name", bill_Response.getCUSTOMER_NAME());
                    nextJSP = CONFIG.Telecom_PAYMENT_PAGE;
                } else {
                    nextJSP = CONFIG.PAGE_Telecom_Rechsrge_inquiry;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());

                }
            } else if (CONFIG.ACTION_payment_Inquiry_Req.equals(action)) {
                int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                request.setAttribute("Fees", request.getParameter("Fees"));
                request.setAttribute("BILLING_ACCOUNT", request.getParameter("BILLING_ACCOUNT"));
                request.setAttribute("DUE_DATE", request.getParameter("DUE_DATE"));
                request.setAttribute("Commession", request.getParameter("Commession"));
                request.setAttribute("Balance_Diff", request.getParameter("Balance_Diff"));
                request.setAttribute("DeductedAmount", request.getParameter("DeductedAmount"));
                request.setAttribute("Customer_name", request.getParameter("Customer_name"));
                request.setAttribute("AMOUNT", request.getParameter("AMOUNT"));
//                request.setAttribute("ADDITIONAL_INFO", request.getParameter("ADDITIONAL_INFO"));
//                request.setAttribute("Customer_Mobile", request.getParameter(CONFIG.NotifyMobile));
                if (service_id == BeatElzakaSDK || service_id == BeatElzakaZkH || service_id == BeatElzakaSkok) {
                    Bill_Request bill_Request = new Bill_Request();
                    bill_Request.setCUSTOMER_ID(customer_id);
                    bill_Request.setAMOUNT(Double.parseDouble(request.getParameter("AMOUNT")));
                    bill_Request.setCHANNEL("WEB");
                    bill_Request.setLANG(lang);
                    Bill_Response inquiry_bill_response = (Bill_Response) session.getAttribute("bill_Response");
                    bill_Request.setBILL_REFERENCE_NUMBER(inquiry_bill_response.getBILL_REFERENCE_NUMBER());
                    Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().Commession_Inquiry(bill_Request));
                    if (bill_Response.getSTATUS_CODE() == 200 || bill_Response.getSTATUS_CODE() == 201 || bill_Response.getSTATUS_CODE() == 202) {
//                        session.setAttribute("bill_Response", bill_Response);
                        Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                        session.setAttribute("BTC", bill_Type);
                        nextJSP = CONFIG.BeatElzakahPaymentPage;

                    } else {
                        nextJSP = CONFIG.BeatElzakahInqueryPage;
                        request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());
                    }

                } else if (service_id == orangeDsl) {
                    nextJSP = CONFIG.OrangeDslPaymentPage;
                } else {
                    nextJSP = CONFIG.BILL_PAYMENT_PAGE;
                }

            } else if (CONFIG.ACTION_payment_Inquiry_Req_Old.equals(action)) {
                String custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
                String BTC = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                double serv_balance = MasaryManager.getInstance().getCustomerServiceBal(custId, 1000);
                double cusAmount = Double.parseDouble(request.getParameter(CONFIG.AMOUNT));
                double fees = 0;
                double billamount = 0;
                Masary_Error Status = new Masary_Error();
                Masary_Bill_Response masary_Bill_Response = (Masary_Bill_Response) session.getAttribute("bill_Info");
                //@Check bill availability
                Status = MasaryManager.getInstance().CHECK_BILL_PAYMENT_AVAILABILITY(masary_Bill_Response.getBILL_REF_NUMBER(), masary_Bill_Response.getBILLING_ACCOUNT());
                if (Status.getSTATUS_CODE() == 200) {
                    Main_Provider provider = masary_Bill_Response.getProvider();
                    Bill_Provider bill_Provider = new Bill_Provider();
                    double provider_balance = bill_Provider.getBalance(provider, custId);
                    for (Masary_Bill_Amounts amo_fes : masary_Bill_Response.getAmounts()) {
                        if (amo_fes.getBILL_SUMM_AMT_CODE().equals("TotalAmtDue")) {
                            billamount = amo_fes.getCUR_AMOUNT();
                        }
                        if (amo_fes.getBILL_SUMM_AMT_CODE().equals("Fees")) {
                            fees = amo_fes.getCUR_AMOUNT();
                        }
                    }

                    nextJSP = CONFIG.BILL_PAYMENT_PAGE_OLD;
                    session.setAttribute("paymentreq", "Y");
                    session.setAttribute("amount", cusAmount);
                } else {
                    MasaryManager.logger.error("ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                    nextJSP = CONFIG.PAGE_bill_info_old;
                    session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));

                }
            }/**
             *
             * *******************************************************************
             */
            else if (CONFIG.ACTION_payment_Inquiry_Req_Confirm.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                String fees = request.getParameter("Fees");
                String billing_account = request.getParameter("BILLING_ACCOUNT");
                Bill_Request bill_Request = new Bill_Request();
                bill_Request.setCUSTOMER_ID(customer_id);
                bill_Request.setAMOUNT(Double.parseDouble(request.getParameter("AMOUNT")));
                bill_Request.setCHANNEL("WEB");
                bill_Request.setLANG(lang);
                Bill_Response inquiry_bill_response = (Bill_Response) session.getAttribute("bill_Response");
                bill_Request.setBILL_REFERENCE_NUMBER(inquiry_bill_response.getBILL_REFERENCE_NUMBER());
                MasaryManager.logger.info("Request Payment bill " + bill_Request.toString());
                Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().PAYMENT(bill_Request));
                MasaryManager.logger.info("Response Payment bill " + bill_Response.toString());
                bill_Response.setBILLING_ACCOUNT(billing_account);
                bill_Response.setAMOUNT(Double.parseDouble(request.getParameter("AMOUNT")));
                if (service_id != mobinilBill && service_id != vodafoneBill && service_id != BeatElzakaZkH && service_id != BeatElzakaSDK && service_id != BeatElzakaSkok) {
                    bill_Response.setCUSTOMER_NAME(new String(request.getParameter("Customer_name").getBytes("ISO-8859-1")));
                    if (service_id != orangeDsl) {
                        bill_Response.setBILL_DATE(request.getParameter("DUE_DATE"));
                    }
                }
                if (bill_Response.getSTATUS_CODE() == 200 || bill_Response.getSTATUS_CODE() == 201 || bill_Response.getSTATUS_CODE() == 202) {
                    session.setAttribute("bill_Response", bill_Response);
                    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                    session.setAttribute("BTC", bill_Type);
                    request.setAttribute("Fees", fees.trim());
                    if (service_id == BeatElzakaSDK || service_id == BeatElzakaZkH || service_id == BeatElzakaSkok) {
                        nextJSP = CONFIG.BeatElzakahConfirmationPage;
                    } else if (bill_Type.getBILL_TYPE_ID() == orangeDsl) {
                        nextJSP = CONFIG.OrangeDslConfirmationPage;
                    } else {

                        nextJSP = CONFIG.PAGE_bill_Recipt;
                    }
                } else {
                    if (service_id == orangeDsl) {
                        nextJSP = CONFIG.OrangeDslInqueryResultPage;
                    } else {
                        nextJSP = CONFIG.PAGE_bill_info;
                    }
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());
                }
            } else if (CONFIG.ACTION_payment_Inquiry_Req_Confirm_Old.equals(action)) {
                String custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
                String BTC = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                double serv_balance = MasaryManager.getInstance().getCustomerServiceBal(custId, 1000);
                String employeeID = (String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
                double CusAmount = Double.parseDouble(request.getParameter(CONFIG.AMOUNT));
                String Customer_Mobile = request.getParameter(CONFIG.NotifyMobile);

                String preLog = "customerId=" + custId + ", serviceId=" + BTC + ", amount=" + CusAmount
                        + ", customerServiceBalance=" + serv_balance
                        + ", employeeId=" + employeeID + ", customerMobile=" + Customer_Mobile;

                MasaryManager.logger.info(preLog + ", executing etisalat bill payment transaction");

                double fees = 0;
                double billamount = 0;
                Masary_Error Status = new Masary_Error();
                Masary_Bill_Response masary_Bill_Response = (Masary_Bill_Response) session.getAttribute("bill_Info");
                masary_Bill_Response.setCustomerMobile(Customer_Mobile);
                //@Check bill availability
                Status = MasaryManager.getInstance().CHECK_BILL_PAYMENT_AVAILABILITY(masary_Bill_Response.getBILL_REF_NUMBER(), masary_Bill_Response.getBILLING_ACCOUNT());
                if (Status.getSTATUS_CODE() == 200) {
                    Main_Provider provider = masary_Bill_Response.getProvider();
                    Bill_Provider bill_Provider = new Bill_Provider();
                    double provider_balance = bill_Provider.getBalance(provider, custId);
                    for (Masary_Bill_Amounts amo_fes : masary_Bill_Response.getAmounts()) {
                        if (amo_fes.getBILL_SUMM_AMT_CODE().equals("TotalAmtDue")) {
                            billamount = amo_fes.getCUR_AMOUNT();
                        }
                        if (amo_fes.getBILL_SUMM_AMT_CODE().equals("Fees")) {
                            fees = amo_fes.getCUR_AMOUNT();
                        }
                    }
//                    double deductedAmount = MasaryManager.getInstance().GetDeductedAmount(Integer.parseInt(custId), Integer.parseInt(BTC), CusAmount, provider.getPROVIDER_ID());

                    //1. First Step in etisalat to call Is valid 2 do directely Updated by Keroles
                    IsValidDTO isvalidObject = MasaryManager.getInstance().IsValidToDoTransaction(Integer.parseInt(custId), Integer.parseInt(BTC), CusAmount);
                    double deductedAmount = isvalidObject.getAMOUNT();
                    MasaryManager.logger.info(preLog + ", jsonObject from db " + isvalidObject);

                    if (isvalidObject.getReturnedValue() != 1) {
                        switch (isvalidObject.getReturnedValue()) {
                            case -1: {
                                Status = MasaryManager.getInstance().GETMasary_Error("-501", provider);
                                break;
                            }// end case
                            default: {
                                Status = MasaryManager.getInstance().GETMasary_Error("-506", provider);
                                break;
                            }// end case
                        }// end switch

                        MasaryManager.logger.error(preLog + ", with ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                        session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));
                        nextJSP = CONFIG.BILL_PAYMENT_PAGE_OLD;
                    }// end if
                    else if (provider_balance < deductedAmount) {
                        MasaryManager.getInstance().updateUsed_provider(Integer.parseInt(BTC), provider);
                        provider = getProvider(Integer.parseInt(BTC), deductedAmount, custId);
                        provider_balance = bill_Provider.getBalance(provider, custId);
                        if (provider.equals(null) || provider.getPROVIDER_ID() == 0) {
                            Status = MasaryManager.getInstance().GETMasary_Error("-506", provider);
                            MasaryManager.logger.error(preLog + ", No Provider is available with ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                            nextJSP = CONFIG.PAGE_bill_info_old;
                            session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));
                        } else {
                            Status = dobillinquiry(BTC, masary_Bill_Response.getBILLING_ACCOUNT(), custId, provider, session);
                            Status = CheckBillType(BTC, CusAmount, custId, serv_balance, provider_balance, billamount, fees, provider, deductedAmount);
                            if (Status.getSTATUS_CODE() == 200) {
                                //                                nextJSP = CONFIG.BILL_PAYMENT_PAGE_OLD;
                                //                                session.setAttribute("paymentreq", "Y");
                                //                                session.setAttribute("amount", CusAmount);
                            } else {
                                MasaryManager.logger.error(preLog + ", ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                                nextJSP = CONFIG.BILL_PAYMENT_PAGE_OLD;
                                MasaryManager.getInstance().CancelPending(isvalidObject.getACTIVE_PENDINGID());// added by keroles to cancel pending in case of Error
                                session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));
                            }
                        }
                    } else {
                        // End First step
                        Status = CheckBillType(BTC, CusAmount, custId, serv_balance, provider_balance, billamount, fees, provider, deductedAmount);
                        if (Status.getSTATUS_CODE() == 200) {
                            Status = bill_Provider.sendpaymentInquiry(custId, employeeID, masary_Bill_Response, CusAmount, provider, session, isvalidObject.getReturnedJsonString());
                            //@check payment status (from provider)
                            try {
                                if (Status.getSTATUS_CODE() == 200) {
                                    MasaryManager.logger.info(preLog + ", ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                                    session.setAttribute("paymentreq", "Y");
                                    nextJSP = CONFIG.PAGE_bill_payment_confirmation_old;
                                } else {
                                    MasaryManager.logger.error(preLog + ", ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                                    session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));
                                    nextJSP = CONFIG.BILL_PAYMENT_PAGE_OLD;
                                }
                            } catch (Exception e) {
                                MasaryManager.logger.error(preLog + ", Bill_Controler.doPayment: ", e);

                                if (e.getMessage().contains("ORA-20139")) {
                                    session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                                    nextJSP = CONFIG.PAGE_ERRPR_SSO;
                                } else {
                                    nextJSP = session.getAttribute(CONFIG.PARAM_ROLE).toString() + ".jsp";

                                }
                            }
                        } else {
                            MasaryManager.logger.error(preLog + ", ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                            nextJSP = CONFIG.BILL_PAYMENT_PAGE_OLD;
                            MasaryManager.getInstance().CancelPending(isvalidObject.getACTIVE_PENDINGID());// added by keroles to cancel pending in case of Error
                            session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));
                        }
                    }// end else// end else
                } else {
                    MasaryManager.logger.error(preLog + ", ErrorCode : " + Status.getSTATUS_CODE() + " ErrorDesc : " + Status.getSTATUS_DESC_EN());
                    nextJSP = CONFIG.BILL_PAYMENT_PAGE_OLD;
                    session.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + Status.getSTATUS_CODE() + " " + Status.getError(session));
                }
            }// end if 
            /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_Resharge_Payment.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                String fees = request.getParameter("Fees");
                String billing_account = request.getParameter("BILLING_ACCOUNT");
                Bill_Request bill_Request = new Bill_Request();
                bill_Request.setCUSTOMER_ID(customer_id);
                bill_Request.setMONEY_PAID(Integer.parseInt(request.getParameter(CONFIG.MONEY_PAID)));
                bill_Request.setQUOTA(Integer.parseInt(request.getParameter(CONFIG.QUOTA)));
                //System.out.println("Quota " + bill_Request.getQUOTA());
                bill_Request.setCHANNEL("WEB");
                bill_Request.setLANG(lang);
                Bill_Response inquiry_bill_response = (Bill_Response) session.getAttribute("bill_Response");
                bill_Request.setBILL_REFERENCE_NUMBER(inquiry_bill_response.getBILL_REFERENCE_NUMBER());
                Bill_Response bill_Response = (Bill_Response) (MasaryManager.getInstance().PAYMENT(bill_Request));
                bill_Response.setBILLING_ACCOUNT(billing_account);
                bill_Response.setCUSTOMER_NAME(new String(request.getParameter("Customer_name").getBytes("ISO-8859-1")));
                if (bill_Response.getSTATUS_CODE() == 200 || bill_Response.getSTATUS_CODE() == 201 || bill_Response.getSTATUS_CODE() == 202) {
                    session.setAttribute("bill_Response", bill_Response);
                    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                    session.setAttribute("BTC", bill_Type);
                    request.setAttribute("Fees", fees.trim());
                    bill_Response.setFEES(Double.parseDouble(fees));
                    nextJSP = CONFIG.PAGE_Telecom_Rechsrge_confirmation;
                } else {
                    nextJSP = CONFIG.PAGE_Telecom_Rechsrge_inquiry;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());
                }
            } 
            else if (CONFIG.ACTION_Get_Balance_Sheet_Men.equals(action)) {
                nextJSP = CONFIG.PAGE_Balance_Sheet;
            } /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_Get_Billers.equals(action)) {
                nextJSP = CONFIG.PAGE_Billers;
            } /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_Get_Pin_Change_Men.equals(action)) {
                nextJSP = CONFIG.PAGE_PIN_CHANGE;
            } /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_Print_Reciept.equals(action)) {
                session.setAttribute("transaction_id", request.getParameter("TXN_ID"));
                session.setAttribute("serv_id", request.getParameter("Serv_Id"));
                Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
//                if (request.getParameter("Serv_Id").equals("115")) {
//                if (request.getParameter("Serv_Id").equals("112") || request.getParameter("Serv_Id").equals("115")) {
//                    nextJSP = CONFIG.PAGE_Mobinil_Bil_Reciept;
//                } else if (request.getParameter("Serv_Id").equals("111")) {
//                    receipt = MasaryManager.getInstance().getVodafone_Bill_Receipt(Integer.parseInt(request.getParameter("transaction_id")));
//                    session.setAttribute("receipt", receipt);
////                    nextJSP = CONFIG.PAGE_reciept;
//                } else {
                receipt = MasaryManager.getInstance().getreceipt(Integer.parseInt(request.getParameter("transaction_id")));
                session.setAttribute("receipt", receipt);
                nextJSP = CONFIG.PAGE_reciept;
//                }

//                //System.out.println("nextJSP = " + nextJSP);
            } /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_Do_Bill_Payment.equals(action)) {
                nextJSP = CONFIG.PAGE_Mobinil_Bill_Payment_Confirm;
            } /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_Do_Mobinil_Bill_Payment_Confirm.equals(action)) {
//                try {
//                    int Transaction = 0;
//                    String AM = request.getParameter(CONFIG.AMOUNT);
//                    String BTC = request.getParameter(CONFIG.PARAM_SERVICE_ID);
//                    String Bill_Ref_N = build_request.getReqIdBiller();
//                    String Request_id = build_request.getReqIdBiller();
//                    int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
////                    if (BTC.equals("111")) {
////                        Transaction = MasaryManager.getInstance().INSERT_Vodafone_BILL_PAYMENT(customer_id, Request_id, Bill_Ref_N, Integer.parseInt(AM), request.getParameter(CONFIG.PARAM_MSISDN), "Pending", "Pending");
////                    } else {
//                        Transaction = MasaryManager.getInstance().INSERT_Manul_BILL_PAYMENT(customer_id, Request_id, Bill_Ref_N, Integer.parseInt(AM), request.getParameter(CONFIG.PARAM_MSISDN), "Pending", "Pending",BTC);
////                        // -------------- Comment Link-DSL 
////                        //   Transaction = MasaryManager.getInstance().INSERT_MANUAL_BILL_PAYMENT(customer_id, Request_id, Bill_Ref_N, Integer.parseInt(request.getParameter("amount").toString()), request.getParameter(CONFIG.PARAM_MSISDN), "Pending", "Pending", Integer.parseInt(BTC));
////                    }
//                    if (Transaction < 1) {
//                        session.setAttribute("ERROR", "Yes");
//                        nextJSP = CONFIG.PAGE_Mobinil_Bill_Payment_Confirm;
//                    } else {
//                        session.setAttribute("Payment_Amount", AM);
//                        session.setAttribute("transaction_id", Transaction);
//                        nextJSP = CONFIG.PAGE_Mobinil_Bil_Reciept;
//                    }
//                } catch (Exception e) {
//                    nextJSP = session.getAttribute(CONFIG.PARAM_ROLE).toString() + ".jsp";
//                }
//            } /**
//             * *******************************************************************
//             */
                try {
                    Vodafone_Bill_Response VodafoneResponse = new Vodafone_Bill_Response();
                    int Transaction = 0;
                    String AM = request.getParameter(CONFIG.AMOUNT);
                    String BTC = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                    String Bill_Ref_N = build_request.getReqIdBiller();
                    String Request_id = build_request.getReqIdBiller();
                    int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
//                    if (BTC.equals("111")) {
//                        VodafoneResponse = MasaryManager.getInstance().PayVodafoneBill(String.valueOf(customer_id), request.getParameter(CONFIG.PARAM_MSISDN), AM, null, null);
//                        if (VodafoneResponse.getTransaction_id() > 0) {
////                                Transaction = Integer.parseInt(VodafoneResponse.substring(VodafoneResponse.indexOf("Transaction Id : ") + 17, VodafoneResponse.length()).trim());
//                            Transaction = VodafoneResponse.getTransaction_id();
//                            session.setAttribute("Payment_Amount", AM);
//                            session.setAttribute("transaction_id", Transaction);
//                            Masary_Bill_Receipt receipt = MasaryManager.getInstance().getVodafone_Bill_Receipt(Transaction);
//                            session.setAttribute("receipt", receipt);
//                            nextJSP = CONFIG.PAGE_reciept;
//                        } else {
////                                session.setAttribute("ERROR MESSAGE", VodafoneResponse.substring(VodafoneResponse.indexOf("Message : ") + 10, VodafoneResponse.length()));
//                            session.setAttribute("ERROR MESSAGE", VodafoneResponse.getResult());
//                            nextJSP = CONFIG.PAGE_BillPayment_Result;
//                        }
//
////                        Transaction = MasaryManager.getInstance().INSERT_Vodafone_BILL_PAYMENT(customer_id, Request_id, Bill_Ref_N, Integer.parseInt(AM), request.getParameter(CONFIG.PARAM_MSISDN), "Pending", "Pending");
//                    } else {
                    Transaction = MasaryManager.getInstance().INSERT_Manul_BILL_PAYMENT(customer_id, Request_id, Bill_Ref_N, Integer.parseInt(AM), request.getParameter(CONFIG.PARAM_MSISDN), "Pending", "Pending", BTC);
//                            Transaction = MasaryManager.getInstance().INSERT_MOBINIL_BILL_PAYMENT(customer_id, Request_id, Bill_Ref_N, Integer.parseInt(AM), request.getParameter(CONFIG.PARAM_MSISDN), "Pending", "Pending");
                    if (Transaction < 1) {
                        session.setAttribute("ERROR", "Yes");
                        nextJSP = CONFIG.PAGE_Mobinil_Bill_Payment_Confirm;
                    } else {
                        session.setAttribute("Payment_Amount", AM);
                        session.setAttribute("transaction_id", Transaction);
                        nextJSP = CONFIG.PAGE_Mobinil_Bil_Reciept;
                    }
//                    }
                } catch (Exception e) {
                    if (e.getMessage().contains("ORA-20139")) {
                        session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                        nextJSP = CONFIG.PAGE_ERRPR_SSO;
                    } else {
                        nextJSP = session.getAttribute(CONFIG.PARAM_ROLE).toString() + ".jsp";

                    }

                }
            } 
            else if (CONFIG.ACTION_ADD_BTC.equals(action)) {
                try {
                    Masary_Bill_Type bill_Type = new Masary_Bill_Type();
                    bill_Type.setBILLER_ID(Integer.parseInt(request.getParameter("BILLER_ID")));
                    bill_Type.setBILL_TYPE_ID(Integer.parseInt(request.getParameter("BILL_TYPE_ID")));
                    bill_Type.setBILL_NAME(request.getParameter("BILL_NAME"));
                    bill_Type.setPMT_TYPE(request.getParameter("PMT_TYPE"));
                    bill_Type.setSERVICE_TYPE(request.getParameter("SERVICE_TYPE"));
                    bill_Type.setSERVICE_NAME(request.getParameter("SERVICE_NAME"));
                    bill_Type.setBILL_LABLE(request.getParameter("BILL_LABLE"));
                    bill_Type.setIS_FRAC_ACC(Boolean.valueOf(request.getParameter("IS_FRAC_ACC")));
                    bill_Type.setIS_PART_ACC(Boolean.valueOf(request.getParameter("IS_PART_ACC")));
                    bill_Type.setIS_OVER_ACC(Boolean.valueOf(request.getParameter("IS_OVER_ACC")));
                    bill_Type.setIS_ADV_ACC(Boolean.valueOf(request.getParameter("IS_ADV_ACC")));
                    session.setAttribute("New_BTC", bill_Type);
                    nextJSP = CONFIG.PAGE_NewBiller;

                    MasaryManager.logger.info(action + "  IP " + request.getRemoteAddr());
                } catch (Exception e) {
                    MasaryManager.logger.error("Exception : " + e.getMessage(), e);
                    if (e.getMessage().contains("ORA-20139")) {
                        session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                        nextJSP = CONFIG.PAGE_ERRPR_SSO;
                    } else {
                    }
                }
            } /**
             * *******************************************************************
             */
            else if (CONFIG.ACTION_ADD_BTC_CONFIRM.equals(action)) {
                try {
                    Masary_Bill_Type bill_Type = (Masary_Bill_Type) request.getSession().getAttribute("New_BTC");
                    bill_Type.setSERVICE_AR_NAME(request.getParameter("ServiceName"));
                    bill_Type.setBILL_LABLE_AR(request.getParameter("BILL_LABLE"));
                    MasaryManager.getInstance().INSERT_MASARY_Bill_TYPE(bill_Type);
                    nextJSP = CONFIG.PAGE_Billers;
                    MasaryManager.logger.info(action + "  IP " + request.getRemoteAddr());
                } catch (Exception e) {

                    MasaryManager.logger.error("Exception : " + e.getMessage(), e);

                    if (e.getMessage().contains("ORA-20139")) {
                        session.setAttribute("ErrorMessage", CONFIG.getReEnter(session));
                        nextJSP = CONFIG.PAGE_ERRPR_SSO;
                    } else {
                    }
                }
            } /*
             * Added by Keroles controller agent payment 03-09-2015
             */ else if (CONFIG.ACTION_AGENT_PAYMENT.equals(action)) {
                String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                session.setAttribute(CONFIG.PROVIDER_ID, request.getParameter(CONFIG.PROVIDER_ID));
                session.setAttribute(CONFIG.OPERATION_ID, request.getParameter(CONFIG.OPERATION_ID));
                nextJSP = CONFIG.PAGE_Agent_Payment;
            } else if (CONFIG.ACTION_Do_Agent_Payment.equals(action)) {
                String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                session.setAttribute(CONFIG.PROVIDER_ID, request.getParameter(CONFIG.PROVIDER_ID));
                session.setAttribute(CONFIG.OPERATION_ID, request.getParameter(CONFIG.OPERATION_ID));
                nextJSP = CONFIG.PAGE_Agent_Bill_Payment_Confirm;
            } else if (CONFIG.ACTION_Do_Agent_Payment_Confirm.equals(action)) {

                nextJSP = PayAgentPayment(request);

            } else if (CONFIG.ACTION_GET_DONATION.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                session.setAttribute(CONFIG.PARAM_SERVICE_ID, serviceId);
                if (!serviceId.equals("602")) {
                    session.setAttribute(CONFIG.PROVIDER_ID, request.getParameter(CONFIG.PROVIDER_ID));
                    session.setAttribute(CONFIG.OPERATION_ID, request.getParameter(CONFIG.OPERATION_ID));
                    DonationAgentPaymentRespponseDto respponseDto = MasaryManager.getInstance().getDonationInfo(Integer.parseInt(request.getParameter(CONFIG.OPERATION_ID)), Integer.parseInt(serviceId), lang);
                    session.setAttribute("donationResponse", respponseDto);
                }
                nextJSP = CONFIG.PAGE_DONATION;
            } else if (CONFIG.ACTION_GET_DONATION_CONFIRMATION.equals(action)) {
//                String program = request.getParameter(CONFIG.PROGRAM);
//                String Customer_name = new String(request.getParameter("Customer_name").getBytes("ISO-8859-1"));
//                String amount = request.getParameter(CONFIG.AMOUNT);
//                String billingAccount = request.getParameter("BILLING_ACCOUNT");
                nextJSP = CONFIG.PAGE_DONATION_Confirmation;
            } else if (CONFIG.ACTION_PAYMENT_DONATION.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));

//                String program = new String(request.getParameter(CONFIG.PROGRAM).getBytes("ISO-8859-1"), "utf-8");
                String Customer_name = new String(request.getParameter("Customer_name").getBytes("ISO-8859-1"), "utf-8");
                //System.out.println("Customer_name" + Customer_name);
                String amount = request.getParameter(CONFIG.AMOUNT);
                String billingAccount = request.getParameter("BILLING_ACCOUNT");
                String govenrate = new String(request.getParameter("governrate").getBytes("ISO-8859-1"), "utf-8");
                Donation_AgentPaymentRequestDTO agentPaymentRequestDTO = new Donation_AgentPaymentRequestDTO();
                agentPaymentRequestDTO.setNAME(Customer_name);
                agentPaymentRequestDTO.setCITY(govenrate);
                agentPaymentRequestDTO.setAMOUNT(amount);
                agentPaymentRequestDTO.setAGENT_IDENTIFIER(billingAccount);
                agentPaymentRequestDTO.setCHANNEL("WEB");
                agentPaymentRequestDTO.setLANG(lang);
                agentPaymentRequestDTO.setSERVICE_ID(service_id);
                if (service_id == 601) {
                    agentPaymentRequestDTO.setOPERATION_ID(Integer.parseInt(session.getAttribute(CONFIG.OPERATION_ID).toString()));
                }
                agentPaymentRequestDTO.setCUSTOMER_ID(customer_id);
                DonationAgentPaymentRespponseDto agentPaymentRespponseDto = MasaryManager.getInstance().do_agent_payment_without_inquiry(agentPaymentRequestDTO);
                if (agentPaymentRespponseDto.getSTATUS_CODE().equals("200")) {
                    session.setAttribute("donationPaymentResponse", agentPaymentRespponseDto);
                    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
                    session.setAttribute("BTC", bill_Type);
//                    request.setAttribute("Fees", fees.trim());
                    nextJSP = CONFIG.PAGE_PAY_DONATION;

                } else {
                    nextJSP = CONFIG.PAGE_DONATION;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + agentPaymentRespponseDto.getSTATUS_CODE() + " " + agentPaymentRespponseDto.getSTATUS_MESSAGE());
                }
            } else if (action.equals(CONFIG.ACTION_AGENT_BILL_INQUIRY)) {
                String serviceId = request.getParameter(CONFIG.PARAM_SERVICE_ID);
                String operationId = request.getParameter(CONFIG.PARAM_OPERATION_ID);
                nextJSP = CONFIG.PAGE_AGENT_INQUIRY;
                session.setAttribute("serv_id", serviceId);
                session.setAttribute("oper_id", operationId);
            } else if (CONFIG.ACTION_Get_Agent_Inquiry_Res.equals(action)) {

                int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                int operationId = Integer.parseInt(request.getParameter(CONFIG.PARAM_OPERATION_ID));
                String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";
                String agentIdentifier = request.getParameter(CONFIG.PARAM_MSISDN);
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                Donation_AgentPaymentRequestDTO agentInquiryRequest = new Donation_AgentPaymentRequestDTO();
                agentInquiryRequest.setCUSTOMER_ID(customer_id);
                agentInquiryRequest.setAGENT_IDENTIFIER(agentIdentifier);
                agentInquiryRequest.setCHANNEL("WEB");
                agentInquiryRequest.setLANG(lang);
                agentInquiryRequest.setSERVICE_ID(service_id);
                agentInquiryRequest.setIS_SC(1);
                agentInquiryRequest.setOPERATION_ID(operationId);
                DonationAgentPaymentRespponseDto agentInquiryResponse = (DonationAgentPaymentRespponseDto) (MasaryManager.getInstance().do_agent_inquiry(agentInquiryRequest));
                agentInquiryResponse.setAGENT_IDENTIFIER(agentIdentifier);
                if (agentInquiryResponse.getSTATUS_CODE().equals("200")) {
                    session.setAttribute("bill_Response", agentInquiryResponse);
                    session.setAttribute("serv_id", service_id);
//                    Masary_Bill_Type bill_Type = MasaryManager.getInstance().getBillType(String.valueOf(service_id));
//                    session.setAttribute("BTC", bill_Type);
                    nextJSP = CONFIG.PAGE_AGENT_INFO;
                } else {
                    nextJSP = CONFIG.PAGE_AGENT_INQUIRY;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + agentInquiryResponse.getSTATUS_CODE() + " " + agentInquiryResponse.getSTATUS_MESSAGE());
                }
            } else if (CONFIG.ACTION_AgentPayment_Inquiry.equals(action)) {

                request.setAttribute("Fees", request.getParameter("Fees"));
                request.setAttribute("AGENT_IDENTIFIER", request.getParameter("AGENT_IDENTIFIER"));
                request.setAttribute("CODE_DETAILS", request.getParameter("CODE_DETAILS"));
                request.setAttribute("Commession", request.getParameter("Commession"));
                request.setAttribute("Balance_Diff", request.getParameter("Balance_Diff"));
                request.setAttribute("DeductedAmount", request.getParameter("DeductedAmount"));
                request.setAttribute("AMOUNT", request.getParameter("AMOUNT"));
                nextJSP = CONFIG.AGENT_PAYMENT_PAGE;

            } else if (CONFIG.ACTION_AgentPayment_Inquiry_Confirm.equals(action)) {
                String lang = "en";
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
                } else {
                    lang = "ar";
                }
                int service_id = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
                int customer_id = Integer.parseInt(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
                String agentIdentifier = request.getParameter("AGENT_IDENTIFIER");
                String fees = request.getParameter("Fees");
                //Bulid Payment request
                Donation_AgentPaymentRequestDTO payment_Request = new Donation_AgentPaymentRequestDTO();
                payment_Request.setCUSTOMER_ID(customer_id);
                payment_Request.setAMOUNT(request.getParameter("AMOUNT"));
                payment_Request.setCHANNEL("WEB");
                payment_Request.setLANG(lang);
                payment_Request.setSERVICE_ID(service_id);
                payment_Request.setIS_SC(1);
                DonationAgentPaymentRespponseDto inquiry_bill_response = (DonationAgentPaymentRespponseDto) session.getAttribute("bill_Response");
                payment_Request.setBILL_REFERENCE_NUMBER(inquiry_bill_response.getBILL_REFERENCE_NUMBER());
                //End Bulid Payment request

                DonationAgentPaymentRespponseDto bill_Response = (DonationAgentPaymentRespponseDto) (MasaryManager.getInstance().do_agent_payment(payment_Request));
                bill_Response.setAGENT_IDENTIFIER(agentIdentifier);
                bill_Response.setAMOUNT(Double.parseDouble(request.getParameter("AMOUNT")));
                bill_Response.setFEES(Double.parseDouble(fees));

                if (bill_Response.getSTATUS_CODE().equals("200")) {
                    session.setAttribute("donationPaymentResponse", bill_Response);
                    session.setAttribute("SERVICE_ID", (String.valueOf(service_id)));
                    request.setAttribute("Fees", fees.trim());
                    nextJSP = CONFIG.PAGE_PAY_DONATION;

                } else {
                    nextJSP = CONFIG.PAGE_AGENT_INFO;
                    request.setAttribute("ErrorCode", CONFIG.getBillErrorCode(session) + " " + bill_Response.getSTATUS_CODE() + " " + bill_Response.getSTATUS_MESSAGE());
                }
            }
            /*
             * End controller agent payment 03-09-2015
             */

            /**
             * *******************************************************************
             */
            try {
                session.setAttribute(CONFIG.PAGE, nextJSP);
            } catch (Exception e) {
                MasaryManager.logger.error("Exception : " + e.getMessage(), e);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + nextJSP);
            dispatcher.forward(request, response);
//            return;
        } catch (Exception e) {
            MasaryManager.logger.error("Exception : " + e.getMessage(), e);

            session.setAttribute("ErrorMessage", e.getMessage());
            nextJSP = CONFIG.PAGE_ERRPR;
        } finally {
//            out.close();
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

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(CONFIG.PARAM_PIN) != null;
    }

    private Masary_Error dobillinquiry(String BTC, String phone, String custId, Main_Provider provider, HttpSession session) throws UnsupportedEncodingException {
        Masary_Error Status = new Masary_Error();
        try {
//            Masary_Bill_Response bill_Response = DO_Bill_Inquiry(BTC, phone, custId);
            Bill_Provider bill_Provider = new Bill_Provider();
            Masary_Bill_Response bill_Response = bill_Provider.billInquiry(provider, BTC, phone, custId, session);
            Status = MasaryManager.getInstance().GETMasary_Error(bill_Response.getMasary_Bill_Status().getSTATUS_CODE(), provider);
            bill_Response.setProvider(provider);
            session.setAttribute("bill_Info", bill_Response);
        } catch (Exception e) {
            MasaryManager.logger.error("Exception" + e.getMessage(), e);
        } finally {
            return Status;
        }
    }

    /*
     * Added by Keroles controller agent payment 03-09-2015
     */
    private String PayAgentPayment(HttpServletRequest request) {

        try {
            HttpSession session = request.getSession();
            int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
            int operationId = Integer.parseInt(request.getParameter(CONFIG.OPERATION_ID));
            String amount = request.getParameter(CONFIG.AMOUNT);
            String agentIdentifier = request.getParameter(CONFIG.PARAM_MSISDN);
            int custId = Integer.parseInt((String) request.getSession().getAttribute(CONFIG.PARAM_PIN));
            String lang = request.getSession().getAttribute(CONFIG.lang).equals("") ? "en" : "ar";

            Donation_AgentPaymentRequestDTO agentPaymentRequestDTO = new Donation_AgentPaymentRequestDTO();
            agentPaymentRequestDTO.setSERVICE_ID(serviceId);
            agentPaymentRequestDTO.setOPERATION_ID(operationId);
            agentPaymentRequestDTO.setAMOUNT(amount);
            agentPaymentRequestDTO.setCUSTOMER_ID(custId);
            agentPaymentRequestDTO.setAGENT_IDENTIFIER(agentIdentifier);
            agentPaymentRequestDTO.setCHANNEL("WEB");
            agentPaymentRequestDTO.setLANG(lang);
            String national_id = request.getParameter("national_ID");
            agentPaymentRequestDTO.setNATIONAL_ID(national_id);

            DonationAgentPaymentRespponseDto agentPaymentRespponseDto = MasaryManager.getInstance().do_agent_payment_without_inquiry(agentPaymentRequestDTO);
            MasaryManager.logger.info("Error code " + agentPaymentRespponseDto.getSTATUS_CODE());
            if (agentPaymentRespponseDto.getSTATUS_CODE().equals("200")) {

                session.setAttribute("donationPaymentResponse", agentPaymentRespponseDto);
                session.setAttribute("SERVICE_ID", (String.valueOf(serviceId)));

//                    request.setAttribute("Fees", fees.trim());
                return CONFIG.PAGE_PAY_AGENTPAYMENT;
            } else {
                request.getSession().setAttribute("ErrorCode", CONFIG.getBillErrorCode(request.getSession())
                        .concat(agentPaymentRespponseDto.getSTATUS_CODE())
                        .concat(" ")
                        .concat(agentPaymentRespponseDto.getSTATUS_MESSAGE()));
                return CONFIG.PAGE_Agent_Payment;

            }

        } catch (Exception e) {
            MasaryManager.logger.error("Exception" + e.getMessage(), e);

            request.getSession().setAttribute("ErrorCode", e.getMessage());
            return CONFIG.PAGE_Agent_Payment;
        }

    }

    /*
     * End controller agent payment 03-09-2015
     */
    public Masary_Error CheckBillType(String BTC, double CusAmount, String CustId, double serv_balance, double masry_balance, double billamount, double fees, Main_Provider provider, double deductedAmount) {
        Masary_Error Status = new Masary_Error();
        try {
            Masary_Bill_Type bill_type = MasaryManager.getInstance().getBTC(BTC);
            double trunccusamount = Math.floor(CusAmount);
//            double deductedAmount = MasaryManager.getInstance().GetDeductedAmount(Integer.parseInt(CustId), Integer.parseInt(BTC), CusAmount, provider.getPROVIDER_ID());
            if ((deductedAmount > serv_balance) || deductedAmount == -1) {
                Status = MasaryManager.getInstance().GETMasary_Error("-501", provider);
            } else if (!bill_type.isIS_PART_ACC() && CusAmount < billamount) {
                Status = MasaryManager.getInstance().GETMasary_Error("-502", provider);
            } else if (!bill_type.isIS_OVER_ACC() && CusAmount > billamount && billamount != 0) {
                Status = MasaryManager.getInstance().GETMasary_Error("-503", provider);
            } else if (!bill_type.isIS_ADV_ACC() && billamount == 0) {
                Status = MasaryManager.getInstance().GETMasary_Error("-504", provider);
            } else if (!bill_type.isIS_FRAC_ACC() && (CusAmount - trunccusamount) > 0) {
                Status = MasaryManager.getInstance().GETMasary_Error("-505", provider);
            } else if ((CusAmount > masry_balance) || deductedAmount == -2 || deductedAmount == -3 || deductedAmount == -4) {
                Status = MasaryManager.getInstance().GETMasary_Error("-506", provider);
            } else {
                Status = MasaryManager.getInstance().GETMasary_Error("200", provider);
            }
        } catch (Exception ex) {

            MasaryManager.logger.error("Exception " + ex.getMessage(), ex);
        }

        return Status;
    }

    public Main_Provider getProvider(int BTC, double amount, String custId) {
        Bill_Provider bill_prov = new Bill_Provider();
        Main_Provider provider = new Main_Provider();

//        if (BTC == 113) {
//            provider.setPROVIDER_ID(0);
//            return provider;
//        }
        List<Main_Provider> providers = MasaryManager.getInstance().get_providers(BTC);
        if (providers.isEmpty()) {
            provider = new Main_Provider();
            provider.setPROVIDER_ID(0);
            return provider;
        }
        Main_Provider used_provider = MasaryManager.getInstance().get_used_provider(BTC);
        provider = getNextProvider(used_provider, providers);
        double Balance = bill_prov.getBalance(provider, custId);

        if (Balance < amount) {

            MasaryManager.getInstance().updateUsed_provider(BTC, provider);

            for (int i = 0; i < providers.size(); i++) {
                if (providers.get(i).getPROVIDER_ID() == provider.getPROVIDER_ID()) {
                    providers.remove(i);
                }
            }
            if (providers.isEmpty()) {

                provider = new Main_Provider();
                provider.setPROVIDER_ID(0);
                return provider;
            }
            if (Balance < 50) {
                MasaryManager.getInstance().invalidateProvider(provider);
            }
            providers = MasaryManager.getInstance().get_providers(BTC);
            if (providers.isEmpty()) {

                provider = new Main_Provider();
                provider.setPROVIDER_ID(0);
                return provider;
            }
            used_provider = MasaryManager.getInstance().get_used_provider(BTC);
            provider = getNextProvider(used_provider, providers);
            Balance = bill_prov.getBalance(provider, custId);

            provider.setPROVIDER_ID(0);
            return provider;

        }

        return provider;
    }

    public Main_Provider getNextProvider(Main_Provider used_provider, List<Main_Provider> providers) {
        Main_Provider Maxprovider = used_provider;
        Main_Provider minprovider = used_provider;
        //---------------------------Maxprovider------------------------------
        for (Main_Provider mProvider : providers) {
            if (mProvider.getOrder() > used_provider.getOrder()) {
                Maxprovider = mProvider;
            }
        }
        //---------------------------Minprovider------------------------------
        for (Main_Provider mProvider : providers) {
            if (mProvider.getOrder() < used_provider.getOrder()) {
                minprovider = mProvider;
            }
        }
        //-----------------------If equal Max return Min else return next-------------------------
        if (Maxprovider.equals(used_provider)) {
            return minprovider;
        } else {
            for (Main_Provider mProvider : providers) {
                if (mProvider.getOrder() == used_provider.getOrder() + 1) {
                    return mProvider;
                }
            }
        }
        return used_provider;
    }
}
