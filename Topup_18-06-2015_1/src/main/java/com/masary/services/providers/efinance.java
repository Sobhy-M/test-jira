/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.services.providers;

import com.masary.database.dto.Request;
//import billpaymentservice.types.Request;
//import billpaymentservice.types.Response;
import com.masary.database.dto.Response;
import com.google.gson.Gson;
import com.masary.Bill_Manager;
import com.masary.XMLGregorianCalendarConverter;
import com.masary.build_request;
import com.masary.database.dto.Customers_Properties;
import com.masary.database.dto.Khdmaty_PayInvoice_Request;
import com.masary.database.dto.Khdmaty_queryInvoice_response;
import com.masary.database.dto.Main_Provider;
import com.masary.database.dto.Masary_Bill_Amounts;
import com.masary.database.dto.Masary_Bill_Request;
import com.masary.database.dto.Masary_Bill_Response;
import com.masary.database.dto.Masary_Bill_Status;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.dto.Masary_Payment_Request;
import com.masary.database.dto.Masary_Payment_Response;
import com.masary.database.dto.Masary_Payment_Type;
import com.masary.database.dto.Masary_signon_profile;
import com.masary.database.dto.eFinance_Inquiry_Res;
import com.masary.database.manager.MasaryManager;
import java.io.FileInputStream;
import java.io.StringReader;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.joda.time.DateTime;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import sun.misc.BASE64Encoder;
import com.masary.database.dto.Masary_Error;
import com.masary.database.dto.eFinanceResponse;
import com.masary.database.dto.eFinance_Inq_Res_amounts;
import com.masary.database.dto.eFinance_Payment_Res;
import com.masary.database.dto.eFinance_Request;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Michael
 */
public class efinance {

    java.util.Date date = new Date();
    XMLGregorianCalendarConverter xMLGregorianCalendarConverter = new XMLGregorianCalendarConverter();
    XMLGregorianCalendar xMLGregorianCalendar = xMLGregorianCalendarConverter.asXMLGregorianCalendar(date);
    Bill_Manager bill_Manager = new Bill_Manager();
    build_request build_request = new build_request();
    private Object financeResponse;

    public Masary_Bill_Response DO_Bill_Inquiry(Main_Provider provider, String BTC, String phone, String custId, HttpSession session) {
//        custId="8220";
        MasaryManager.getInstance().logger.info("{e-finance.DO_Bill_Inquiry(provider=[" + provider + "],BTC=[" + BTC + "],phone=[" + phone + "],custId=[" + custId + "])}");
        Masary_Error Status = new Masary_Error();
        Masary_Bill_Request masary_Bill_Request;
        Masary_Bill_Response bill_Response = new Masary_Bill_Response();
        try {
            Masary_Bill_Type BTC_obj = MasaryManager.getInstance().getBTC(BTC);
            String Sender_Id = "0001";
//            String Sender_Id = "0008";
            String BankId = "0010";
//            String BankId = "1008";
            String RqUID = getRqUID(Sender_Id);
//            Response BillInquiry_Res = null;
//            Response BillFee_Res = null;
            String Signature = "";
            Request BillInquiry_Req = new Request();
            String AccessChannel = "ATM";
            String BillingAcct = phone;
            String BillerId = BTC;
            String ServiceType = BTC_obj.getSERVICE_TYPE();
            masary_Bill_Request = Build_Request_Bill(BTC, phone, RqUID);
            MasaryManager.getInstance().insertbillrequest(masary_Bill_Request, Integer.parseInt(custId), provider);
            String Provider_request = build_request.LogBill_inquiry_Req(masary_Bill_Request);
//            ----------------------------------------------Begin----e-finance------------------------------------
            String Message = getBillInqRqMessage(Sender_Id, RqUID, BankId, AccessChannel, BillingAcct, BillerId, ServiceType);
            BillInquiry_Req.setMessage(Message);
            BillInquiry_Req.setSenderID(Sender_Id);
            Signature = getSignature(BillInquiry_Req);
            BillInquiry_Req.setSignature(Signature);
//            ----------------------------------------------End----e-finance------------------------------------
            Provider_request = Message;
            int Log_id = MasaryManager.getInstance().Insert_Bill_Provider_Log(Provider_request, provider.getPROVIDER_ID(), "Inquiry", Integer.parseInt(custId));
            String eFinance_Response_MSG = sendInquiry_TCPIP(1, Sender_Id, Message);
//            String eFinance_Response_MSG = gettest_Message(1);
//            BillInquiry_Res = enquireBills(BillInquiry_Req);
            MasaryManager.getInstance().Update_Bill_Provider_Log(Log_id, eFinance_Response_MSG);
            eFinance_Inquiry_Res finance_Inquiry_Res = get_eFinance_Inquiry_Res(eFinance_Response_MSG);
//            MasaryManager.getInstance().Update_Bill_Provider_Log(Log_id, BillInquiry_Res.getMessage());
//            eFinance_Inquiry_Res finance_Inquiry_Res = get_eFinance_Inquiry_Res(BillInquiry_Res.getMessage());
            Status = new Masary_Error();
            Status = MasaryManager.getInstance().GETMasary_Error(finance_Inquiry_Res.getStatusCode(), provider);
            if (Status.getSTATUS_CODE() == 200) {
                Request FeeInq = new Request();
                RqUID = getRqUID(Sender_Id);
                String EPayBillRecID = finance_Inquiry_Res.getEPayBillRecID();
                String FeeInq_Message = getFeeInqRqMessage(Sender_Id, RqUID, EPayBillRecID, finance_Inquiry_Res.getAmounts());
                FeeInq.setMessage(FeeInq_Message);
                Log_id = MasaryManager.getInstance().Insert_Bill_Provider_Log(FeeInq_Message, provider.getPROVIDER_ID(), "FeeInquiry", Integer.parseInt(custId));
                Signature = getSignature(FeeInq);
                FeeInq.setSenderID(Sender_Id);
                FeeInq.setSignature(Signature);
//                BillFee_Res = calculateCommission(FeeInq);
                eFinance_Response_MSG = sendInquiry_TCPIP(2, Sender_Id, FeeInq_Message);
//                eFinance_Response_MSG = gettest_Message(2);
                MasaryManager.getInstance().Update_Bill_Provider_Log(Log_id, eFinance_Response_MSG);
                String FeeStatusCode = eFinance_Response_MSG.substring(eFinance_Response_MSG.indexOf("<StatusCode>") + 12, eFinance_Response_MSG.indexOf("</StatusCode>"));
                Status = MasaryManager.getInstance().GETMasary_Error(FeeStatusCode, provider);
                if (eFinance_Response_MSG.startsWith("Error :")) {
                    finance_Inquiry_Res.setStatusCode("-506");
                    finance_Inquiry_Res.setShortDesc("Service unavailable now please try again");
                    Status.setSTATUS_CODE(-506);
                } else if (Status.getSTATUS_CODE() != 200) {
                    MasaryManager.getInstance().logger.error("[eFinance Error : Can't get Fees amount]");
                } else {
                    finance_Inquiry_Res.setFeeAmount(Double.parseDouble(getElementByName("Amt", eFinance_Response_MSG)) + 5);
                    finance_Inquiry_Res.setFeeCurCode(getElementByName("CurCode", eFinance_Response_MSG));
//                MasaryManager.getInstance().Update_Bill_Provider_Log(Log_id, BillFee_Res.getMessage());
//                finance_Inquiry_Res.setFeeAmount(Double.parseDouble(getElementByName("Amt", BillFee_Res.getMessage())));
//                finance_Inquiry_Res.setFeeCurCode(getElementByName("CurCode", BillFee_Res.getMessage()));
                }
                session.setAttribute("eFinance_Inq_response", finance_Inquiry_Res);
            } else {
                finance_Inquiry_Res.setBillingAcct(phone);
            }
            bill_Response = sendbillinquiry_eFinance(finance_Inquiry_Res, BTC, provider);
            //---------------------End-BillResponse----------------
            String providerStatusCode = bill_Response.getMasary_Bill_Status().getSTATUS_CODE();
            bill_Response.getMasary_Bill_Status().setSTATUS_CODE(String.valueOf(Status.getSTATUS_CODE()));
            MasaryManager.getInstance().insert_bill_response(bill_Response, provider);
            bill_Response.getMasary_Bill_Status().setSTATUS_CODE(providerStatusCode);
            build_request.LogBill_inquiry_Res(bill_Response);
        } catch (Exception exc) {
            MasaryManager.getInstance().logger.error(exc);
        }
        return bill_Response;
    }

    public Masary_Error sendpaymentInquiry(String custId, String employeeID, Masary_Bill_Response bill_Response, double amount, Main_Provider provider, HttpSession session, String isValidDTO) {
//        custId="8220";
//        String Khdmaty_amount = String.valueOf(amount);
        MasaryManager.getInstance().logger.info("{e-finance.sendpaymentInquiry(custId=[" + custId + "],employeeID=[" + employeeID + "],amount=[" + amount + "],provider=[" + provider.getPROVIDER_ID() + "])}");
        Masary_Error Status = new Masary_Error();
        try {
            Masary_Payment_Response masary_Payment_Response = new Masary_Payment_Response();
//            Response BillPayment_Res = null;
            String Sender_Id = "0001";
//            String Sender_Id = "0008";
            String BankId = "0010";
//            String BankId = "1008";
            String RqUID = getRqUID(Sender_Id);
            String PmtId = String.valueOf(MasaryManager.getInstance().getCLIENT_TERMINAL_SEQ_ID());
            String AccessChannel = "INTERNET";
            String PmtMethod = "CCARD";
//            String Signature = "";
//            Request PaymentInq = new Request();
            Masary_Payment_Request payment_Request = Build_Request_Payment_Bill(bill_Response, amount, RqUID);

//            payment_Request.setFEES_AMOUNT(bill_Response.);
            MasaryManager.getInstance().insert_payment_request(payment_Request, Integer.parseInt(custId), provider);
            build_request.LogPostpayed_Payment_Req(payment_Request);
            //            ----------------------------------------------Begin----e-finance----------------------------------
            eFinance_Inquiry_Res finance_Inquiry_Res = (eFinance_Inquiry_Res) session.getAttribute("eFinance_Inq_response");
            payment_Request.setFEES_AMOUNT(finance_Inquiry_Res.getFeeAmount());
            String PaymentInq_Message = getPaymentInqRqMessage(Sender_Id, RqUID, finance_Inquiry_Res.getEPayBillRecID(), finance_Inquiry_Res.getSequence(), String.valueOf(finance_Inquiry_Res.getAmount()), finance_Inquiry_Res.getCurCode(), PmtId, String.valueOf(finance_Inquiry_Res.getFeeAmount() - 5), finance_Inquiry_Res.getFeeCurCode(), finance_Inquiry_Res.getBillNumber(), finance_Inquiry_Res.getBillingAcct(), finance_Inquiry_Res.getBillerId(), BankId, AccessChannel, PmtMethod, finance_Inquiry_Res.getServiceType());
            int Log_id = MasaryManager.getInstance().Insert_Bill_Provider_Log(PaymentInq_Message, provider.getPROVIDER_ID(), "Payment", Integer.parseInt(custId));
//            //System.out.println(PaymentInq_Message);
//            PaymentInq.setMessage(PaymentInq_Message);
//            Signature = getSignature(PaymentInq);
//            PaymentInq.setSenderID(Sender_Id);
//            PaymentInq.setSignature(Signature);
//            BillPayment_Res = confirmPayments(PaymentInq);
            String BillPayment_Res_MSG = sendPayment_TCPIP(3, Sender_Id, PaymentInq_Message);
//            String BillPayment_Res_MSG = gettest_Message(3);
            MasaryManager.getInstance().Update_Bill_Provider_Log(Log_id, BillPayment_Res_MSG);
//            eFinanceResponse response = new eFinanceResponse("");
//            String BillPayment_Res_MSG = "";
//            eFinance_Request request = new eFinance_Request();
//            request.setMessage(PaymentInq_Message);
//            request.setRequest_Type(3);
//            request.setSender_Id(Sender_Id);
//            Gson gson = new Gson();
//            String Provider_request = gson.toJson(request);
//            int Transaction_id = MasaryManager.getInstance().DO_BILL_PAYMENT(Integer.parseInt(bill_Response.getBILL_TYPE_CODE()), Integer.parseInt(custId), amount, bill_Response.getBILLING_ACCOUNT(), bill_Response.getCustomerMobile(), Provider_request, provider.getPROVIDER_ID(), response);
//            BillPayment_Res_MSG = response.BillPayment_Res_MSG;
            //            ----------------------------------------------End----e-finance------------------------------------
            eFinance_Payment_Res payment_Res = get_eFinance_Payment_Res(BillPayment_Res_MSG);

            payment_Res.setBILL_REF_NUMBER(finance_Inquiry_Res.getEPayBillRecID());
            masary_Payment_Response = sendpaymentInquiry_eFinance(payment_Res, payment_Request);
            double balance = getBalance(provider, custId);

            if (!employeeID.equals("-1")) {
                if (MasaryManager.getInstance().getAllowedServiceBalance(employeeID, 1000) < amount) {
                    throw new Exception("رصيدك الحالي في هذه الخدمه لا يسمح باجراء العمليه");
                }
            }
            //---------------------------balance_inquiry_response------------------------------
            //----------------------------------------------production----------------------------------------
//            int Transaction_id = manager.INSERT_PAYMENT_RESPONSE(masary_Payment_Response, Integer.parseInt(custId), balance.getBalance());
            //---------------------------------------------------------------------------------------------------
            //-------------------------------------------------test-----------------------------------------------
            Status = MasaryManager.getInstance().GETMasary_Error(masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE(), provider);
            masary_Payment_Response.getMasary_Bill_Status().setSTATUS_CODE(String.valueOf(Status.getSTATUS_CODE()));
            int Transaction_id = MasaryManager.getInstance().INSERT_PAYMENT_RESPONSE_TEST(masary_Payment_Response, Integer.parseInt(custId), balance, provider, bill_Response.getCustomer_name(), isValidDTO);
            //---------------------------------------------------------------------------------------------------
            build_request.LogPostpayed_Payment_Res(masary_Payment_Response);
            masary_Payment_Response.setCustomerName(bill_Response.getCustomer_name());
            session.setAttribute("payment_Info", masary_Payment_Response);
            if (Status.getSTATUS_CODE() == 200) {
                MasaryManager.getInstance().updateUsed_provider(Integer.parseInt(bill_Response.getBILL_TYPE_CODE()), provider);
                session.setAttribute("transaction_id", Transaction_id);
                session.setAttribute("Provider_Payment_ID", payment_Res.getPmtId());
            }
        } catch (Exception e) {
            MasaryManager.logger.error("Exception" + e);
        } finally {
            return Status;
        }
    }

    public double getBalance(Main_Provider provider, String custId) {
        MasaryManager.getInstance().logger.info("{e-finance.getBalance(provider=[" + provider.getPROVIDER_ID() + "])}");
        double balance = 1220.87;
        return balance;
    }

    public Masary_Bill_Request Build_Request_Bill(String BTC, String phone, String RqUID) {
        Masary_Bill_Request masary_Bill_Request = new Masary_Bill_Request();
        try {
            Masary_signon_profile masary_signon_profile = new Masary_signon_profile();
            masary_signon_profile.setSIGNON_PROFILE_ID(1);
            masary_signon_profile.setSENDER("MS_MOB");
            masary_signon_profile.setRECIEVER("MSARYCOL");
            masary_signon_profile.setVERSION("V1.0");
            masary_Bill_Request.setMasary_signon_profile(masary_signon_profile);
            masary_Bill_Request.setMESSAGE_CODE("BillInqRq");
            masary_Bill_Request.setREQUEST_ID(RqUID);
            masary_Bill_Request.setORIGINATOR_CODE("MSARYCOL");
            masary_Bill_Request.setTERMINALID(String.valueOf(MasaryManager.getInstance().getbilling_account("1").getACCOUNT_CODE()));
            masary_Bill_Request.setINCOPEN_AMOUNT(Boolean.TRUE);
            masary_Bill_Request.setBILLING_ACCOUNT(phone);
            masary_Bill_Request.setBANK_ID("MSARYCOL");
            masary_Bill_Request.setBILL_TYPE_CODE(BTC);
            masary_Bill_Request.setDELEVERY_METHOD("MOB");
            java.util.Date datereq = new Date();
            XMLGregorianCalendar xMLGregorianCalendarreq = xMLGregorianCalendarConverter.asXMLGregorianCalendar(datereq);
            masary_Bill_Request.setREQUEST_DATE(xMLGregorianCalendarreq);
        } catch (Exception exc) {
//            //System.out.print(exc);
            MasaryManager.getInstance().logger.error(exc);
        }

        return masary_Bill_Request;
        //---------------------End-BillRequest----------------
    }

    public Masary_Payment_Request Build_Request_Payment_Bill(Masary_Bill_Response bill_Response, double amount, String RqUID) {
        Masary_Payment_Request payment_Request = new Masary_Payment_Request();
        try {
            Masary_signon_profile masary_signon_profile = new Masary_signon_profile();
            masary_signon_profile.setSIGNON_PROFILE_ID(2);
            masary_signon_profile.setSENDER(bill_Response.getMasary_signon_profile().getRECIEVER());
            masary_signon_profile.setRECIEVER(bill_Response.getMasary_signon_profile().getSENDER());
            masary_signon_profile.setVERSION(bill_Response.getMasary_signon_profile().getVERSION());
            payment_Request.setMasary_signon_profile(masary_signon_profile);
            payment_Request.setMESSAGE_CODE("PmtAddRq");
            payment_Request.setREQUEST_ID(RqUID);
            payment_Request.setASYNC_REQUEST_ID(bill_Response.getREQUEST_ID());
            payment_Request.setORIGINATOR_CODE(bill_Response.getORIGINATOR_CODE());
            payment_Request.setTERMINAL_ID(bill_Response.getTerminalId());
            ////System.out.println("Sequential_Id is " + String.valueOf(MasaryManager.getInstance().getCLIENT_TERMINAL_SEQ_ID()));
            payment_Request.setCLIENT_TERMINAL_SEQ_ID(String.valueOf(MasaryManager.getInstance().getCLIENT_TERMINAL_SEQ_ID()));
            List<Customers_Properties> customers_Properties = new ArrayList<Customers_Properties>();
            Customers_Properties customers_Propertie = new Customers_Properties();
            customers_Propertie.setKEY("PosSerialNumber");
            customers_Propertie.setVALUE(MasaryManager.getInstance().getbilling_account("1").getSERIAL_NUMBER());
            customers_Properties.add(customers_Propertie);
            payment_Request.setCustomers_Properties(customers_Properties);
            payment_Request.setBILLING_ACCOUNT(bill_Response.getBILLING_ACCOUNT());
            payment_Request.setBILL_REF_NUMBER(bill_Response.getBILL_REF_NUMBER());
//            if (request.getParameter(CONFIG.PARAM_MSISDN).equals("")) {
            payment_Request.setNOTIFY_MOBILE(bill_Response.getCustomerMobile());
//            } else {
//                payment_Request.setNOTIFY_MOBILE(request.getParameter(CONFIG.PARAM_MSISDN));
//            }
            payment_Request.setBILL_TYPE_CODE(bill_Response.getBILL_TYPE_CODE());
            payment_Request.setPMT_TYPE("POST");
            payment_Request.setDELIVERY_METHOD(bill_Response.getDELIVERY_METHOD());
//                    MasaryManager.logger.info("Ämount is : "+Double.parseDouble(request.getParameter(CONFIG.AMOUNT)));
            payment_Request.setAMOUNT(amount);
            payment_Request.setACCT_ID(String.valueOf(MasaryManager.getInstance().getbilling_account("1").getACCOUNT_CODE()));
            payment_Request.setACCT_KEY(MasaryManager.getInstance().getPin());
            payment_Request.setACCT_TYPE("SDA");
            payment_Request.setACCT_CUR("EGP");
            payment_Request.setPMT_METHOD("CASH");
            java.util.Date daterpyeq = new Date();
            XMLGregorianCalendar xMLGregorianCalendarreq = xMLGregorianCalendarConverter.asXMLGregorianCalendar(daterpyeq);
            payment_Request.setREQUEST_DATE(xMLGregorianCalendarreq);
            payment_Request.setPRC_DATE(xMLGregorianCalendarreq);
            payment_Request.setDUE_DATE(bill_Response.getDUE_DATE());

        } catch (Exception exc) {
            MasaryManager.getInstance().logger.error(exc);
        }

        return payment_Request;
        //---------------------End-BillRequest----------------
    }

    public Khdmaty_PayInvoice_Request Build_Request_Payment_Khdmaty(Khdmaty_queryInvoice_response queryResponse, double amount, int custId) {
        Khdmaty_PayInvoice_Request paymentRequest = new Khdmaty_PayInvoice_Request();
        paymentRequest.setInvoiceTotal(amount);
        paymentRequest.setBillDate(queryResponse.getBillDate());
        paymentRequest.setCustomerName(queryResponse.getCustomerName());
        paymentRequest.setServiceName(queryResponse.getServiceName());
        paymentRequest.setTelephoneCode(queryResponse.getTelephoneCode());
        paymentRequest.setTelephoneNumber(queryResponse.getTelephoneNumber());
        paymentRequest.setCustomerID(custId);
        paymentRequest.setRequest_1(MasaryManager.getInstance().getCLIENT_TERMINAL_SEQ_ID());
        return paymentRequest;
    }

    public Masary_Bill_Response sendbillinquiry_eFinance(eFinance_Inquiry_Res resp, String BTC, Main_Provider provider) {

        Masary_Bill_Response bill_Response = new Masary_Bill_Response();
        Masary_Bill_Status status = new Masary_Bill_Status();
//        Masary_Error Status = new Masary_Error();
        bill_Response.setCLIENT_DATE(xMLGregorianCalendar);
        java.util.Date datereq = new Date();
        XMLGregorianCalendar xMLGregorianCalendarreq = xMLGregorianCalendarConverter.asXMLGregorianCalendar(datereq);
        bill_Response.setSERVER_DATE(xMLGregorianCalendarreq.toString());
        bill_Response.setLanguage("en-gb");
        Masary_signon_profile masary_signon_profiler = new Masary_signon_profile();
        masary_signon_profiler.setSIGNON_PROFILE_ID(2);
        masary_signon_profiler.setSENDER("MSARYCOL");
        masary_signon_profiler.setRECIEVER("MS_MOB");
        masary_signon_profiler.setVERSION("V1.0");
        bill_Response.setMasary_signon_profile(masary_signon_profiler);
        bill_Response.setMESSAGE_CODE("BillInqRs");
        bill_Response.setTerminalId("12344");
        bill_Response.setINCOPENAMOUNT(Boolean.TRUE);
        bill_Response.setORIGINATOR_CODE("MSARYCOL");
        bill_Response.setDELIVERY_METHOD("MOB");
        List<Masary_Bill_Amounts> Amounts = new ArrayList<Masary_Bill_Amounts>();
        Masary_Bill_Amounts amount = new Masary_Bill_Amounts();
        amount.setBILL_SUMM_AMT_CODE("TotalAmtDue");
        amount.setBILL_REF_NUMBER(bill_Response.getBILL_REF_NUMBER());
        amount.setCUR_AMOUNT(resp.getAmount());
        amount.setCUR_CODE("EGP");
        Amounts.add(amount);
        Masary_Bill_Amounts amount1 = new Masary_Bill_Amounts();
        amount1.setBILL_SUMM_AMT_CODE("Fees");
        amount1.setBILL_REF_NUMBER(bill_Response.getBILL_REF_NUMBER());
        amount1.setCUR_AMOUNT(resp.getFeeAmount());
        amount1.setCUR_CODE("EGP");
        Amounts.add(amount1);
        bill_Response.setAmounts(Amounts);
        bill_Response.setLOWER_AMOUNT(resp.getMin_amount());
        bill_Response.setLOWER_CURRUNT_AMOUNT("EGP");
        bill_Response.setUPPER_AMOUNT(resp.getMax_amount());
        bill_Response.setUPPER_CURRENT_AMOUNT("EGP");
        bill_Response.setRulesAwareness("yy");
        bill_Response.setBILL_STATUS("BillUnpaid");
        status.setSTATUS_CODE(resp.getStatusCode());
        status.setSEVERITY("info");
        status.setSTATUS_DESC(resp.getShortDesc());
        bill_Response.setMasary_Bill_Status(status);
        bill_Response.setBILL_TYPE_CODE(BTC);
        bill_Response.setBILLING_ACCOUNT(resp.getBillingAcct());
        bill_Response.setBILL_REF_NUMBER(resp.getEPayBillRecID());
        bill_Response.setREQUEST_ID(resp.getRqUID());
        bill_Response.setASYNC_REQUEST_ID(resp.getRqUID());
        if (resp.getStatusCode().equals("0")) {
            bill_Response.setCustomer_name(getStudentName(resp.getMSG_ar()));
            bill_Response.setDUE_DATE(resp.getDueDt().substring(0, 10));
            bill_Response.setAdditional_Info(resp.getMSG_ar());
        }
        return bill_Response;

        //-------------------------------------End Test------------------------------------------------

    }

    public Masary_Payment_Response sendpaymentInquiry_eFinance(eFinance_Payment_Res payment_Res, Masary_Payment_Request payment_Request) {
        Masary_Payment_Response masary_Payment_Response = new Masary_Payment_Response();
        Masary_Bill_Status status = new Masary_Bill_Status();
//        Masary_Error Status = new Masary_Error();
        if (payment_Res.getStatusCode().equals("0")) {
            try {
                //---------------------------------------test----------------------------------------------------------------
                java.util.Date datereq = new Date();
                XMLGregorianCalendar xMLGregorianCalendarreq = xMLGregorianCalendarConverter.asXMLGregorianCalendar(datereq);
                masary_Payment_Response.setRESPONSE_DATE(xMLGregorianCalendarreq.toString());
                masary_Payment_Response.setSERVER_DATE(xMLGregorianCalendarreq.toString());
                Masary_signon_profile masary_signon_profileres = new Masary_signon_profile();
                masary_signon_profileres.setSIGNON_PROFILE_ID(1);
                masary_signon_profileres.setSENDER("MSARYCOL");
                masary_signon_profileres.setRECIEVER("MS_MOB");
                masary_signon_profileres.setVERSION("V1.0");
                masary_Payment_Response.setMasary_signon_profile(masary_signon_profileres);
                masary_Payment_Response.setMESSAGE_CODE("PmtAddRs");
                masary_Payment_Response.setREQUEST_ID(payment_Request.getREQUEST_ID());
                masary_Payment_Response.setASYNC_REQUEST_ID(payment_Request.getREQUEST_ID());
                masary_Payment_Response.setORIGINATOR_CODE("GW_POS4");
                masary_Payment_Response.setTERMINAL_ID("12344");
                masary_Payment_Response.setCLIENT_TERMINAL_SEQ_ID(payment_Res.getPmtId());
                List<Customers_Properties> customers_Propertiesres = new ArrayList<Customers_Properties>();
                Customers_Properties customers_Propertieres = new Customers_Properties();
                customers_Propertieres.setKEY("PosSerialNumber");
                customers_Propertieres.setVALUE("900-900-900");
                customers_Propertiesres.add(customers_Propertieres);
                masary_Payment_Response.setCustomers_Properties(customers_Propertiesres);
                Masary_Bill_Status masary_Bill_Status = new Masary_Bill_Status();
                masary_Bill_Status.setSTATUS_CODE(payment_Res.getStatusCode());
                masary_Bill_Status.setSEVERITY("Info");
                masary_Bill_Status.setSTATUS_DESC(payment_Res.getShortDesc());
                masary_Payment_Response.setMasary_Bill_Status(masary_Bill_Status);
                List<Masary_Payment_Type> masary_Payment_Types = new ArrayList<Masary_Payment_Type>();
                Masary_Payment_Type masary_Payment_Type = new Masary_Payment_Type();
                masary_Payment_Type.setPAYMENT_ID(payment_Res.getPmtId());
                masary_Payment_Type.setPAYMENT_ID_TYPE(payment_Res.getPmtIdType());
                masary_Payment_Type.setCREATED_DATE(xMLGregorianCalendarreq.toString());
                masary_Payment_Types.add(masary_Payment_Type);
                masary_Payment_Response.setMasary_Payment_Types(masary_Payment_Types);
                masary_Payment_Response.setBILLING_ACCOUNT(payment_Request.getBILLING_ACCOUNT());
                masary_Payment_Response.setBILL_REF_NUMBER(payment_Res.getBILL_REF_NUMBER());
                masary_Payment_Response.setNOTIFY_MOBILE(payment_Request.getNOTIFY_MOBILE());
                masary_Payment_Response.setBILL_TYPE_CODE(payment_Request.getBILL_TYPE_CODE());
                masary_Payment_Response.setPAYMENT_TYPE("POST");
                masary_Payment_Response.setDELIVERY_METHOD("MOB");
                masary_Payment_Response.setCURRENT_AMOUNT(payment_Request.getAMOUNT());
                masary_Payment_Response.setFEES_AMOUNT(payment_Request.getFEES_AMOUNT());
                masary_Payment_Response.setACCOUNT_ID("12344");
                masary_Payment_Response.setACCOUNT_TYPE("SDA");
                masary_Payment_Response.setACCOUNT_KEY("1234");
                masary_Payment_Response.setACCOUNT_CUR("EGP");
                masary_Payment_Response.setPAYMENT_METHOD("CASH");
                masary_Payment_Response.setPRC_DATE("2012-04-22");
                masary_Payment_Response.setDUE_DATE(payment_Request.getDUE_DATE());
//-------------------------------------------------------------------------------------------------------

            } catch (Exception e) {
                MasaryManager.logger.error("WSDL ERROR :" + e);
            }
        } else {
//        Status = MasaryManager.getInstance().GETMasary_Error(String.valueOf(resp.getReturnCode()), provider);
            status.setSTATUS_CODE(payment_Res.getStatusCode());
            status.setSEVERITY("info");
            status.setSTATUS_DESC(payment_Res.getShortDesc());
            masary_Payment_Response.setMasary_Bill_Status(status);
        }
        return masary_Payment_Response;

    }

//    private static String centerBalance() {
//        org.tempuri.KhdamatyServices service = new org.tempuri.KhdamatyServices();
//        org.tempuri.KhdamatyServicesSoap port = service.getKhdamatyServicesSoap();
//        return port.centerBalance();
//    }
//
//    private static Response enquireBills(billpaymentservice.types.Request request1) {
////        billpaymentservice.wsdl.BillPaymentService service = new billpaymentservice.wsdl.BillPaymentService();
////        billpaymentservice.wsdl.BillPaymentServiceSEI port = service.getBillPaymentServiceSEIPort();
////        return port.enquireBills(request1);
//        Response res = new Response();
//        res.setMessage("<EFBPS>\n"
//                + "    <SignonRq>\n"
//                + "        <ClientDt>2013-04-14T15:41:47.163</ClientDt>\n"
//                + "        <LanguagePref>ar-eg</LanguagePref>\n"
//                + "        <SignonProfile>\n"
//                + "            <Sender>EPAY</Sender>\n"
//                + "            <Receiver>0008</Receiver>\n"
//                + "            <MsgCode>RBINQRS</MsgCode>\n"
//                + "        </SignonProfile>\n"
//                + "    </SignonRq>\n"
//                + "    <BankSvcRs>\n"
//                + "        <Status>\n"
//                + "            <StatusCode>0</StatusCode>\n"
//                + "            <ShortDesc>Success</ShortDesc>\n"
//                + "        </Status>\n"
//                + "        <RqUID>1304140810</RqUID>\n"
//                + "        <BillInqRs>\n"
//                + "            <RecCount>1</RecCount>\n"
//                + "            <BillRec>\n"
//                + "                <BillUploadStatusCode>BillNew</BillUploadStatusCode>\n"
//                + "                <BillPresentmentStatusCode>BillUnpaid</BillPresentmentStatusCode>\n"
//                + "                <DisplayLabel>\n"
//                + "                    <LanguagePref>en-gb</LanguagePref>\n"
//                + "                    <Text> Student Fees for Year 2010-2011</Text>\n"
//                + "                </DisplayLabel>\n"
//                + "                <DisplayLabel>\n"
//                + "                    <LanguagePref>ar-eg</LanguagePref>\n"
//                + "                    <Text> للعام 2010-2011 - انتظام</Text>\n"
//                + "                </DisplayLabel>\n"
//                + "                <Msg>\n"
//                + "                    <LanguagePref>ar-eg</LanguagePref>\n"
//                + "                    <Text>مصروفات الطالب طارق  بدر  قاسم  محمد-الفرقة الثانية-2010-2011-انتظام</Text>\n"
//                + "                </Msg>\n"
//                + "                <Msg>\n"
//                + "                    <LanguagePref>en-gb</LanguagePref>\n"
//                + "                    <Text>مصروفات الطالب طارق  بدر  قاسم  محمد-الفرقة الثانية-2010-2011-انتظام</Text>\n"
//                + "                </Msg>\n"
//                + "                <EPayBillRecID>329da7bb-7860-4659-8a10-3cb8596bc364</EPayBillRecID>\n"
//                + "                <BillInfo>\n"
//                + "                    <BillCategory>BBP</BillCategory>\n"
//                + "                    <ServiceType>FayoumUNV</ServiceType>\n"
//                + "                    <BillNumber>102079-248</BillNumber>\n"
//                + "                    <AccountId>\n"
//                + "                        <BillingAcct>149120080100030</BillingAcct>\n"
//                + "                        <BillerId>22888</BillerId>\n"
//                + "                    </AccountId>\n"
//                + "                    <CurAmt>\n"
//                + "                        <Sequence>1</Sequence>\n"
//                + "                        <OriginalAmt>260</OriginalAmt>\n"
//                + "                        <AmtDue>260</AmtDue>\n"
//                + "                        <CurCode>818</CurCode>\n"
//                + "                        <ExactPmt>true</ExactPmt>\n"
//                + "                        <PymtMode>Range</PymtMode>\n"
//                + "                        <RangeInfo>\n"
//                + "                            <MinAmt>260</MinAmt>\n"
//                + "                            <MaxAmt>260</MaxAmt>\n"
//                + "                        </RangeInfo>\n"
//                + "                        <ShortDesc>\n"
//                + "                            <LanguagePref>en-gb</LanguagePref>\n"
//                + "                            <Text>College fees</Text>\n"
//                + "                        </ShortDesc>\n"
//                + "                        <ShortDesc>\n"
//                + "                            <LanguagePref>ar-eg</LanguagePref>\n"
//                + "                            <Text>مصروفات دراسية</Text>\n"
//                + "                        </ShortDesc>\n"
//                + "                        <SettleAccCode>255</SettleAccCode>\n"
//                + "                    </CurAmt>\n"
//                + "                    <DueDt>2011-06-30T00:00:00</DueDt>\n"
//                + "                </BillInfo>\n"
//                + "            </BillRec>\n"
//                + "        </BillInqRs>\n"
//                + "    </BankSvcRs>\n"
//                + "</EFBPS>");
//        return res;
//    }
//
//    private static Response calculateCommission(billpaymentservice.types.Request request1) {
////        billpaymentservice.wsdl.BillPaymentService service = new billpaymentservice.wsdl.BillPaymentService();
////        billpaymentservice.wsdl.BillPaymentServiceSEI port = service.getBillPaymentServiceSEIPort();
////        return port.calculateCommission(request1);
//        Response res = new Response();
//        res.setMessage("<EFBPS>\n"
//                + "    <SignonRq>\n"
//                + "        <ClientDt>2013-04-14T15:43:21.670</ClientDt>\n"
//                + "        <LanguagePref>ar-eg</LanguagePref>\n"
//                + "        <SignonProfile>\n"
//                + "            <Sender>EPAY</Sender>\n"
//                + "            <Receiver>0008</Receiver>\n"
//                + "            <MsgCode>RFINQRS</MsgCode>\n"
//                + "        </SignonProfile>\n"
//                + "    </SignonRq>\n"
//                + "    <BankSvcRs>\n"
//                + "        <Status>\n"
//                + "            <StatusCode>0</StatusCode>\n"
//                + "            <ShortDesc>Request processed successfully</ShortDesc>\n"
//                + "        </Status>\n"
//                + "        <RqUID>1304140811</RqUID>\n"
//                + "        <FeeInqRs>\n"
//                + "            <FeesAmt>\n"
//                + "                <Amt>0</Amt>\n"
//                + "                <CurCode>818</CurCode>\n"
//                + "            </FeesAmt>\n"
//                + "        </FeeInqRs>\n"
//                + "    </BankSvcRs>\n"
//                + "</EFBPS>");
//        return res;
//    }
//    
//    private static Response confirmPayments(billpaymentservice.types.Request request1) {
////        billpaymentservice.wsdl.BillPaymentService service = new billpaymentservice.wsdl.BillPaymentService();
////        billpaymentservice.wsdl.BillPaymentServiceSEI port = service.getBillPaymentServiceSEIPort();
////        return port.confirmPayments(request1);
//        Response res = new Response();
//        res.setMessage("<EFBPS>\n"
//                + "    <SignonRq>\n"
//                + "        <ClientDt>2013-04-14T15:53:54.052</ClientDt>\n"
//                + "        <LanguagePref>ar-eg</LanguagePref>\n"
//                + "        <SignonProfile>\n"
//                + "            <Sender>EPAY</Sender>\n"
//                + "            <Receiver>0008</Receiver>\n"
//                + "            <MsgCode>RPADVRS</MsgCode>\n"
//                + "        </SignonProfile>\n"
//                + "    </SignonRq>\n"
//                + "    <BankSvcRs>\n"
//                + "        <Status>\n"
//                + "            <StatusCode>0</StatusCode>\n"
//                + "            <ShortDesc>Payment Advice Accepted</ShortDesc>\n"
//                + "        </Status>\n"
//                + "        <RqUID>1304140812</RqUID>\n"
//                + "        <PmtAdviceRs>\n"
//                + "            <PmtRecAdviceStatus>\n"
//                + "                <PmtTransId>\n"
//                + "                    <PmtId>201399000333308006</PmtId>\n"
//                + "                    <PmtIdType>EPTN</PmtIdType>\n"
//                + "                </PmtTransId>\n"
//                + "            </PmtRecAdviceStatus>\n"
//                + "        </PmtAdviceRs>\n"
//                + "    </BankSvcRs>\n"
//                + "</EFBPS>");
//        return res;
//    }
    public String getRqUID(String Sender_Id) {
        String RqUID = "";
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            Date date = new Date();
            RqUID = dateFormat.format(date) + Sender_Id.substring(0, 2) + MasaryManager.getInstance().getCLIENT_TERMINAL_SEQ_ID();
//            //System.out.println(RqUID);
        } catch (Exception e) {
        } finally {
            return RqUID;
        }

    }

    public String getBillInqRqMessage(String Sender_Id, String RqUID, String BankId, String AccessChannel, String BillingAcct, String BillerId, String ServiceType) {
        String ClientDt = getTimeStamp();
        StringBuilder Message = new StringBuilder("<EFBPS><SignonRq><ClientDt>");
        Message.append(ClientDt);
        Message.append("</ClientDt><LanguagePref>ar-eg</LanguagePref><SignonProfile><Sender>");
        Message.append(Sender_Id);
        Message.append("</Sender><Receiver>EPAY</Receiver><MsgCode>RBINQRQ</MsgCode></SignonProfile></SignonRq><BankSvcRq><RqUID>");
        Message.append(RqUID);
        Message.append("</RqUID><BillInqRq><BankId>");
        Message.append(BankId);
        Message.append("</BankId><AccessChannel>");
        Message.append(AccessChannel);
        Message.append("</AccessChannel><AccountId><BillingAcct>");
        Message.append(BillingAcct);
        Message.append("</BillingAcct><BillerId>");
        Message.append(BillerId);
        Message.append("</BillerId></AccountId><ServiceType>");
        Message.append(ServiceType);
        Message.append("</ServiceType><IncPaidBills>false</IncPaidBills></BillInqRq></BankSvcRq></EFBPS>");
        return Message.toString();
    }

    public String getFeeInqRqMessage(String Sender_Id, String RqUID, String EPayBillRecID, List<eFinance_Inq_Res_amounts> amounts) {
        String ClientDt = getTimeStamp();
        StringBuilder Message = new StringBuilder("<EFBPS><SignonRq><ClientDt>");
        Message.append(ClientDt);
        Message.append("</ClientDt><LanguagePref>ar-eg</LanguagePref><SignonProfile><Sender>");
        Message.append(Sender_Id);
        Message.append("</Sender><Receiver>EPAY</Receiver><MsgCode>RFINQRQ</MsgCode></SignonProfile></SignonRq><BankSvcRq><RqUID>");
        Message.append(RqUID);
        Message.append("</RqUID><FeeInqRq><EPayBillRecID>");
        Message.append(EPayBillRecID);
        Message.append("</EPayBillRecID>");
        for (eFinance_Inq_Res_amounts amount : amounts) {
            Message.append("<PayAmt><Sequence>");
            Message.append(amount.getSequence());
            Message.append("</Sequence><Amt>");
            Message.append(amount.getAmt());
            Message.append("</Amt><CurCode>");
            Message.append(amount.getCurCode());
            Message.append("</CurCode></PayAmt>");
        }
        Message.append("</FeeInqRq></BankSvcRq></EFBPS>");
        return Message.toString();
    }

    public String getPaymentInqRqMessage(String Sender_Id, String RqUID, String EPayBillRecID, String Sequence1, String Amount1, String CurCode1, String PmtId, String Fee, String FeeCode, String BillNumber, String BillingAcct, String BillerId, String BankId, String AccessChannel, String PmtMethod, String ServiceType) {
        String ClientDt = getTimeStamp();
        String PrcDt = getTimeStamp();
        StringBuilder Message = new StringBuilder("<EFBPS><SignonRq><ClientDt>");
        Message.append(ClientDt);
        Message.append("</ClientDt><LanguagePref>ar-eg</LanguagePref><SignonProfile><Sender>");
        Message.append(Sender_Id);
        Message.append("</Sender><Receiver>EPAY</Receiver><MsgCode>RPADVRQ</MsgCode></SignonProfile></SignonRq><BankSvcRq><RqUID>");
        Message.append(RqUID);
        Message.append("</RqUID><PmtAdviceRq><EPayBillRecID>");
        Message.append(EPayBillRecID);
        Message.append("</EPayBillRecID><PmtRec><PmtTransId><PmtId>");
        Message.append(PmtId);
        Message.append("</PmtId><PmtIdType>BNKPTN</PmtIdType></PmtTransId><PmtInfo><PayAmt><Sequence>");
        Message.append(Sequence1);
        Message.append("</Sequence><Amt>");
        Message.append(Amount1);
        Message.append("</Amt><CurCode>");
        Message.append(CurCode1);
        Message.append("</CurCode></PayAmt><FeesAmt><Amt>");
        Message.append(Fee);
        Message.append("</Amt><CurCode>");
        Message.append(FeeCode);
        Message.append("</CurCode></FeesAmt><PrcDt>");
        Message.append(PrcDt);
        Message.append("</PrcDt><BillNumber>");
        Message.append(BillNumber);
        Message.append("</BillNumber><AccountId><BillingAcct>");
        Message.append(BillingAcct);
        Message.append("</BillingAcct><BillerId>");
        Message.append(BillerId);
        Message.append("</BillerId></AccountId><BankId>");
        Message.append(BankId);
        Message.append("</BankId>");
//        Message.append("<DistrictCode>");
//        Message.append(DistrictCode);
//        Message.append("</DistrictCode><BranchCode>");
//        Message.append(BranchCode);
//        Message.append("</BranchCode>");
        Message.append("<AccessChannel>");
        Message.append(AccessChannel);
        Message.append("</AccessChannel><PmtMethod>");
        Message.append(PmtMethod);
        Message.append("</PmtMethod><ServiceType>");
        Message.append(ServiceType);
        Message.append("</ServiceType>");
//        Message.append("<PmtRefInfo>");
//        Message.append("</PmtRefInfo>");
        Message.append("</PmtInfo></PmtRec></PmtAdviceRq></BankSvcRq></EFBPS>");
        return Message.toString();
    }

    public String ReqLogs(String Message, String Signature, String Sinder_id) {
        StringBuilder Request = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"urn:BillPaymentService/types\">\n<soapenv:Header/>\n<soapenv:Body>\n<typ:enquireBills>\n<Request_1>");
        Request.append(Message);
        Request.append("<senderID>");
        Request.append(Sinder_id);
        Request.append("</senderID>\n<signature>");
        Request.append(Signature);
        Request.append("</signature>\n</Request_1>\n</typ:enquireBills>\n</soapenv:Body>\n</soapenv:Envelope>");
        return Request.toString();
    }

    private String getTimeStamp() {
        return DateTime.now().toString().substring(0, DateTime.now().toString().indexOf("."));
    }

    public String getSignature(Request request1) {
        String sign = "";
        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            KeyStore keystore = KeyStore.getInstance("PKCS12");
//            FileInputStream fi = new FileInputStream("C:\\Documents and Settings\\Michael\\Desktop\\e-finance\\TestCert.pfx");
//            FileInputStream fi = new FileInputStream("/usr/local/app/eFinance_Inquiry_Production/eMasary.pfx");
            FileInputStream fi = new FileInputStream("D:\\eFinance Project\\Production\\e-finance\\eMasary.pfx");
            keystore.load(fi, null);
            PrivateKey privateKey = (PrivateKey) keystore.getKey("le-computerscom-90517d20-430d-4f1b-81f0-b0943a277dc9", "pass".toCharArray());
//            //System.out.println(keystore);
            signer.initSign(privateKey);
            signer.update(request1.getMessage().getBytes("UTF-8"));
            byte[] signature = signer.sign();
            sign = new BASE64Encoder().encode(signature);
//            //System.out.println(sign);
        } catch (Exception e) {
            //System.out.println("Exp : " + e);
        }
        return sign;
    }

    public String getElementByName(String Name, String response) {
        String Response = "";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(response));
            Document doc = db.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName(Name);
            Element element = (Element) nodes.item(0);
            Response = getCharacterDataFromElement(element);
        } catch (Exception ex) {
//            Main.logger.error(ex.getMessage());
            //System.out.println(ex);
        } finally {
            return Response;
        }
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }

    public eFinance_Inquiry_Res get_eFinance_Inquiry_Res(String ResponseMessage) {
        eFinance_Inquiry_Res inquiry_Res = new eFinance_Inquiry_Res();
        if (ResponseMessage.startsWith("Error :")) {
            inquiry_Res.setStatusCode("-506");
            inquiry_Res.setShortDesc("Service unavailable now please try again");
        } else {
            inquiry_Res.setStatusCode(getElementByName("StatusCode", ResponseMessage));
            inquiry_Res.setShortDesc(getElementByName("ShortDesc", ResponseMessage));
            if (inquiry_Res.getStatusCode().equals("0")) {
                inquiry_Res.setEPayBillRecID(getElementByName("EPayBillRecID", ResponseMessage));
                inquiry_Res.setDueDt(getElementByName("DueDt", ResponseMessage));
                inquiry_Res.setBillNumber(getElementByName("BillNumber", ResponseMessage));
                inquiry_Res.setBillerId(getElementByName("BillerId", ResponseMessage));
                inquiry_Res.setBillingAcct(getElementByName("BillingAcct", ResponseMessage));
                inquiry_Res.setMax_amount(Double.parseDouble(getElementByName("MaxAmt", ResponseMessage)));
                inquiry_Res.setMin_amount(Double.parseDouble(getElementByName("MinAmt", ResponseMessage)));
                List<eFinance_Inq_Res_amounts> amounts = getInquiry_Amount(ResponseMessage);
                double totalAmount = 0;
                for (eFinance_Inq_Res_amounts amount : amounts) {
                    totalAmount = totalAmount + Double.parseDouble(amount.getAmt());
                }
                inquiry_Res.setAmounts(amounts);
                inquiry_Res.setAmount(totalAmount);
                inquiry_Res.setMSG_ar(getSubMessage("Text", getSubMessage("Msg", ResponseMessage)));
                inquiry_Res.setMSG_en(getSubMessage("Text", getSubMessage("Msg", ResponseMessage)));
                inquiry_Res.setCurCode(getElementByName("CurCode", ResponseMessage));
                inquiry_Res.setSequence(getElementByName("Sequence", ResponseMessage));
                inquiry_Res.setRqUID(getElementByName("RqUID", ResponseMessage));
                inquiry_Res.setServiceType(getElementByName("ServiceType", ResponseMessage));
            }
        }
        return inquiry_Res;
    }

    public eFinance_Payment_Res get_eFinance_Payment_Res(String ResponseMessage) {
        eFinance_Payment_Res payment_Res = new eFinance_Payment_Res();
        if (ResponseMessage.startsWith("Error :")) {
            payment_Res.setStatusCode("-506");
            payment_Res.setShortDesc("Service unavailable now please try again");
        } else {
            payment_Res.setStatusCode(getElementByName("StatusCode", ResponseMessage));
            payment_Res.setShortDesc(getElementByName("ShortDesc", ResponseMessage));
            if (payment_Res.getStatusCode().equals("0")) {
                payment_Res.setRqUID(getElementByName("RqUID", ResponseMessage));
                payment_Res.setPmtId(getElementByName("PmtId", ResponseMessage));
                payment_Res.setPmtIdType(getElementByName("PmtIdType", ResponseMessage));
            }
        }
        return payment_Res;

    }

    public static String getSubMessage(String element, String Message) {
        String closeElement = "</" + element + ">";
        element = "<" + element + ">";
        if (Message.indexOf(element) == -1) {
            return "-100";
        }
        return Message.substring(Message.indexOf(element) + element.length(), Message.indexOf(closeElement));

    }

    public String sendInquiry_TCPIP(int Request_Type, String Sender_Id, String Message) {
        String Response_MSG = "";
        eFinance_Request request = new eFinance_Request();
        request.setMessage(Message);
        request.setRequest_Type(Request_Type);
        request.setSender_Id(Sender_Id);
        Gson gson = new Gson();
        String Provider_request = gson.toJson(request);
        try {
//            Socket client = new Socket("41.131.18.84", 12013);
//            Socket client = new Socket("213.158.169.206", 12013);
            Socket client = new Socket("213.158.169.206", 13013);
//            Socket client = new Socket("localhost", 12013);
            PrintStream out = new PrintStream(client.getOutputStream());
            out.print(Provider_request + "\n");
            out.flush();
            //reading the response using input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
            Response_MSG = in.readLine();
//            byte[] bytes = Response_MSG.getBytes("ISO-8859-1");
//            Response_MSG = new String(bytes, "UTF-8");
            //closing the streams
            in.close();
            out.close();
        } catch (Exception e) {
            Response_MSG = "Error : " + e.getMessage();
            //System.out.println("Error : " + e.getMessage());
        }
        return Response_MSG;
    }

    public String sendPayment_TCPIP(int Request_Type, String Sender_Id, String Message) {
        String Response_MSG = "";
        eFinance_Request request = new eFinance_Request();
        request.setMessage(Message);
        request.setRequest_Type(Request_Type);
        request.setSender_Id(Sender_Id);
        Gson gson = new Gson();
        String Provider_request = gson.toJson(request);
        try {
//            Socket client = new Socket("41.131.18.84", 13013);
//            Socket client = new Socket("213.158.169.206", 13013);
            Socket client = new Socket("213.158.169.206", 13014);
//            Socket client = new Socket("localhost", 13013);
            PrintStream out = new PrintStream(client.getOutputStream());
            out.print(Provider_request + "\n");
            out.flush();
            //reading the response using input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            Response_MSG = in.readLine();
            //closing the streams
            in.close();
            out.close();
        } catch (Exception e) {
            Response_MSG = "Error : " + e.getMessage();
            //System.out.println("Error : " + e.getMessage());
        }
        return Response_MSG;
    }

    public String getStudentName(String Message) {
        String Name = "NotAvailable";
        if (Message.indexOf("مصروفات الطالب") >= 0) {
            Name = Message.substring(Message.indexOf("مصروفات الطالب") + 15, Message.indexOf("-"));
        } else if (Message.indexOf("- الفرقة") >= 0) {
            Name = Message.substring(0, Message.indexOf("- الفرقة"));
        } else if (Message.indexOf("| الفرقة :") >= 0) {
            Name = Message.substring(0, Message.indexOf("| الفرقة :"));
        }
//        else if(Message.indexOf("")>=0){
//        Name=Message.substring(Message.indexOf("مصروفات الطالب")+15, Message.indexOf("-"));
//        }
        return Name;
    }

    public String gettest_Message(int type) {
        String Message = "";
        if (type == 1) {
//            Message = "<EFBPS><SignonRq><ClientDt>2013-10-09T12:17:33.084</ClientDt><LanguagePref>ar-eg</LanguagePref><SignonProfile><Sender>EPAY</Sender><Receiver>0008</Receiver><MsgCode>RBINQRS</MsgCode></SignonProfile></SignonRq><BankSvcRs><Status><StatusCode>0</StatusCode><ShortDesc>Success</ShortDesc></Status><RqUID>1310090026488</RqUID><BillInqRs><RecCount>1</RecCount><BillRec><BillUploadStatusCode>BillNew</BillUploadStatusCode><BillPresentmentStatusCode>BillUnpaid</BillPresentmentStatusCode><DisplayLabel><LanguagePref>en-gb</LanguagePref><Text> Student Fees for Year 2010-2011</Text></DisplayLabel><DisplayLabel><LanguagePref>ar-eg</LanguagePref><Text> للعام 2010-2011 - انتظام</Text></DisplayLabel><Msg><LanguagePref>ar-eg</LanguagePref><Text>مصروفات الطالب وليد  حمدى محمد حسن-الفرقة الرابعة-2010-2011-انتظام</Text></Msg><Msg><LanguagePref>en-gb</LanguagePref><Text>مصروفات الطالب وليد  حمدى محمد حسن-الفرقة الرابعة-2010-2011-انتظام</Text></Msg><EPayBillRecID>f45b35c8-ead6-49d6-87fa-586f5365aa87</EPayBillRecID><BillInfo><BillCategory>BBP</BillCategory><ServiceType>FayoumUNV</ServiceType><BillNumber>104101-248</BillNumber><AccountId><BillingAcct>149120030100006</BillingAcct><BillerId>22888</BillerId></AccountId><CurAmt><Sequence>1</Sequence><OriginalAmt>260</OriginalAmt><AmtDue>260</AmtDue><CurCode>818</CurCode><ExactPmt>true</ExactPmt><PymtMode>Range</PymtMode><RangeInfo><MinAmt>260</MinAmt><MaxAmt>260</MaxAmt></RangeInfo><ShortDesc><LanguagePref>en-gb</LanguagePref><Text>College fees</Text></ShortDesc><ShortDesc><LanguagePref>ar-eg</LanguagePref><Text>مصروفات دراسية</Text></ShortDesc><SettleAccCode>255</SettleAccCode></CurAmt><DueDt>2011-06-30T00:00:00</DueDt></BillInfo></BillRec></BillInqRs></BankSvcRs></EFBPS>";
            Message = "<EFBPS><SignonRq><ClientDt>2013-12-22T15:46:54.627</ClientDt><LanguagePref>ar-eg</LanguagePref><SignonProfile><Sender>EPAY</Sender><Receiver>0001</Receiver><MsgCode>RBINQRS</MsgCode></SignonProfile></SignonRq><BankSvcRs><Status><StatusCode>0</StatusCode><ShortDesc>Success</ShortDesc></Status><RqUID>1312220067670</RqUID><BillInqRs><RecCount>1</RecCount><BillRec><BillUploadStatusCode>BillNew</BillUploadStatusCode><BillPresentmentStatusCode>BillUnpaid</BillPresentmentStatusCode><DisplayLabel><LanguagePref>en-gb</LanguagePref><Text> Student Fees for Year 2013/2014</Text></DisplayLabel><DisplayLabel><LanguagePref>ar-eg</LanguagePref><Text> للعام 2013/2014</Text></DisplayLabel><Msg><LanguagePref>ar-eg</LanguagePref><Text>امل   فتحى محمود السيد| الفرقة :الثانية - انتظام| قسم :مزدوج كيمياء/ حيوان</Text></Msg><Msg><LanguagePref>en-gb</LanguagePref><Text>امل   فتحى محمود السيد| الفرقة :الثانية - انتظام| قسم :مزدوج كيمياء/ حيوان</Text></Msg><EPayBillRecID>fa25b761-1b74-40e5-93c3-2f23d0006b44</EPayBillRecID><BillInfo><BillCategory>BBP</BillCategory><ServiceType>CAU</ServiceType><AccountId><BillingAcct>1061001356</BillingAcct><BillerId>99002</BillerId></AccountId><CurAmt><Sequence>2</Sequence><OriginalAmt>45.90</OriginalAmt><AmtDue>45.90</AmtDue><CurCode>818</CurCode><ExactPmt>true</ExactPmt><PymtMode>Range</PymtMode><RangeInfo><MinAmt>45.90</MinAmt><MaxAmt>45.90</MaxAmt></RangeInfo><ShortDesc><LanguagePref>en-gb</LanguagePref><Text>رسوم كاملة - Budgetary Amount </Text></ShortDesc><ShortDesc><LanguagePref>ar-eg</LanguagePref><Text>رسوم كاملة- رفع الكفائة </Text></ShortDesc><SettleAccCode>290</SettleAccCode></CurAmt><CurAmt><Sequence>1</Sequence><OriginalAmt>102</OriginalAmt><AmtDue>102</AmtDue><CurCode>818</CurCode><ExactPmt>true</ExactPmt><PymtMode>Range</PymtMode><RangeInfo><MinAmt>102</MinAmt><MaxAmt>102</MaxAmt></RangeInfo><ShortDesc><LanguagePref>en-gb</LanguagePref><Text>رسوم كاملة - Faculty Amount </Text></ShortDesc><ShortDesc><LanguagePref>ar-eg</LanguagePref><Text>رسوم كاملة - 33 ع ح </Text></ShortDesc><SettleAccCode>290</SettleAccCode></CurAmt><DueDt>2014-06-30T00:00:00</DueDt><BillRefInfo>32</BillRefInfo></BillInfo></BillRec></BillInqRs></BankSvcRs></EFBPS>";
//            Message = "<EFBPS><SignonRq><ClientDt>2013-10-28T12:23:42.556</ClientDt><LanguagePref>ar-eg</LanguagePref><SignonProfile><Sender>EPAY</Sender><Receiver>0008</Receiver><MsgCode>RBINQRS</MsgCode></SignonProfile></SignonRq><BankSvcRs><Status><StatusCode>8099</StatusCode><ShortDesc>No matched bill found</ShortDesc></Status><RqUID>1310280026677</RqUID></BankSvcRs></EFBPS>";
        } else if (type == 2) {
            Message = "<EFBPS><SignonRq><ClientDt>2013-10-09T12:17:39.031</ClientDt><LanguagePref>ar-eg</LanguagePref><SignonProfile><Sender>EPAY</Sender><Receiver>0008</Receiver><MsgCode>RFINQRS</MsgCode></SignonProfile></SignonRq><BankSvcRs><Status><StatusCode>0</StatusCode><ShortDesc>Request processed successfully</ShortDesc></Status><RqUID>1310090026489</RqUID><FeeInqRs><EPayBillRecID>f45b35c8-ead6-49d6-87fa-586f5365aa87</EPayBillRecID><FeesAmt><Amt>0</Amt><CurCode>818</CurCode></FeesAmt></FeeInqRs></BankSvcRs></EFBPS>";
        } else {
            Message = "<EFBPS><SignonRq><ClientDt>2013-10-09T12:18:29.627</ClientDt><LanguagePref>ar-eg</LanguagePref><SignonProfile><Sender>EPAY</Sender><Receiver>0008</Receiver><MsgCode>RPADVRS</MsgCode></SignonProfile></SignonRq><BankSvcRs><Status><StatusCode>0</StatusCode><ShortDesc>Payment Advice Accepted</ShortDesc></Status><RqUID>1310090026490</RqUID><PmtAdviceRs><PmtRecAdviceStatus><PmtTransId><PmtId>201399000222219629</PmtId><PmtIdType>EPTN</PmtIdType></PmtTransId></PmtRecAdviceStatus></PmtAdviceRs></BankSvcRs></EFBPS>";
        }

        return Message;

    }

    public List<eFinance_Inq_Res_amounts> getInquiry_Amount(String response_V) {
//        double amount = 0;
        String BillInfo = response_V.substring(response_V.indexOf("<BillInfo>"), response_V.indexOf("</BillInfo>") + 11);
        List<eFinance_Inq_Res_amounts> amounts = new ArrayList<eFinance_Inq_Res_amounts>();
        eFinance_Inq_Res_amounts amountObj = null;
        while (BillInfo.indexOf("<CurAmt>") > 0) {
            amountObj = new eFinance_Inq_Res_amounts();
            String Amount = BillInfo.substring(BillInfo.indexOf("<CurAmt>"), BillInfo.indexOf("</CurAmt>") + 9);
//            amount = amount + Double.parseDouble(getElementByName("AmtDue", Amount));
            amountObj.setAmt(getElementByName("AmtDue", Amount));
            amountObj.setCurCode(getElementByName("CurCode", Amount));
            amountObj.setSequence(getElementByName("Sequence", Amount));
            amounts.add(amountObj);
            BillInfo = BillInfo.replace(Amount, "");
        }
        return amounts;
    }
}
