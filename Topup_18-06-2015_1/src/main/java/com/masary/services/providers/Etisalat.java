package com.masary.services.providers;

import com.google.gson.Gson;
import com.masary.Bill_Manager;
import com.masary.XMLGregorianCalendarConverter;
import com.masary.build_request;
import com.masary.database.dto.Customers_Properties;
import com.masary.database.dto.Etisalat_Request;
import com.masary.database.dto.Etisalat_Response;
import com.masary.database.dto.Khdmaty_PayInvoice_Request;
import com.masary.database.dto.Khdmaty_PayInvoice_response;
import com.masary.database.dto.Khdmaty_queryInvoice_response;
import com.masary.database.dto.Main_Provider;
import com.masary.database.dto.Masary_Bill_Amounts;
import com.masary.database.dto.Masary_Bill_Request;
import com.masary.database.dto.Masary_Bill_Response;
import com.masary.database.dto.Masary_Bill_Status;
import com.masary.database.dto.Masary_Error;
import com.masary.database.dto.Masary_Payment_Request;
import com.masary.database.dto.Masary_Payment_Response;
import com.masary.database.dto.Masary_Payment_Type;
import com.masary.database.dto.Masary_signon_profile;
import com.masary.database.manager.MasaryManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Michael
 */
public class Etisalat {
//    private HttpSession session;

    java.util.Date date = new Date();
    XMLGregorianCalendarConverter xMLGregorianCalendarConverter = new XMLGregorianCalendarConverter();
    XMLGregorianCalendar xMLGregorianCalendar = xMLGregorianCalendarConverter.asXMLGregorianCalendar(date);
    Bill_Manager bill_Manager = new Bill_Manager();
    build_request build_request = new build_request();

    public Masary_Bill_Response DO_Bill_Inquiry(Main_Provider provider, String BTC, String phone, String custId, HttpSession session) {
//        custId="8220";
        MasaryManager.logger.info("{Etisalat.DO_Bill_Inquiry(provider=[" + provider + "],BTC=[" + BTC + "],phone=[" + phone + "],custId=[" + custId + "])}");
        
        Masary_Error Status = new Masary_Error();
        Masary_Bill_Request masary_Bill_Request;
        Masary_Bill_Response bill_Response = new Masary_Bill_Response();
        try {
            masary_Bill_Request = Build_Request_Bill(BTC, phone);
            MasaryManager.getInstance().insertbillrequest(masary_Bill_Request, Integer.parseInt(custId), provider);
            String Provider_request = build_request.LogBill_inquiry_Req(masary_Bill_Request);
            Gson gson = new Gson();
            Etisalat_Request etisalat_Request = MasaryManager.getInstance().getInquiryRequest(phone);
            Provider_request = gson.toJson(etisalat_Request);
            int Log_id = MasaryManager.getInstance().Insert_Bill_Provider_Log(Provider_request, provider.getPROVIDER_ID(), "Inquiry", Integer.parseInt(custId));
            String response = sendRequest(etisalat_Request);
//            String response = "{\"operationType\":7,\"Status_Code\":\"0\",\"Status_Msg\":\"���������� ������ 2.07 EGP ���������� 882343360\",\"Amount\":2.07,\"Balance\":0.0}";
            MasaryManager.getInstance().Update_Bill_Provider_Log(Log_id, response);
            Etisalat_Response etisalat_Response = gson.fromJson(response, Etisalat_Response.class);
            etisalat_Response.setPhone(phone);
            bill_Response = sendbillinquiry_Etisalat(etisalat_Response, BTC, provider);

            String providerStatusCode = bill_Response.getMasary_Bill_Status().getSTATUS_CODE();
            Status = MasaryManager.getInstance().GETMasary_Error(providerStatusCode, provider);
            bill_Response.getMasary_Bill_Status().setSTATUS_CODE(String.valueOf(Status.getSTATUS_CODE()));
            MasaryManager.getInstance().insert_bill_response(bill_Response, provider);
            bill_Response.getMasary_Bill_Status().setSTATUS_CODE(providerStatusCode);
            build_request.LogBill_inquiry_Res(bill_Response);

        } catch (Exception exc) {
            MasaryManager.logger.error(exc.getMessage(), exc);
        }
        return bill_Response;
    }

    public Masary_Error sendpaymentInquiry(String custId, String employeeID, Masary_Bill_Response bill_Response, double amount, Main_Provider provider, HttpSession session, String isValidDTO) {
//        custId="8220";
        MasaryManager.getInstance().logger.info("{Etisalat.sendpaymentInquiry(custId=[" + custId + "],employeeID=[" + employeeID + "],amount=[" + amount + "],provider=[" + provider.getPROVIDER_ID() + "])}");
        Masary_Error Status = new Masary_Error();
        try {
            Masary_Payment_Response masary_Payment_Response = new Masary_Payment_Response();
            Masary_Payment_Request payment_Request = Build_Request_Payment_Bill(bill_Response, amount);
            MasaryManager.getInstance().insert_payment_request(payment_Request, Integer.parseInt(custId), provider);
            String Provider_request = build_request.LogPostpayed_Payment_Req(payment_Request);
            Gson gson = new Gson();
            Etisalat_Request etisalat_Request = MasaryManager.getInstance().getPaymentRequest(bill_Response.getBILLING_ACCOUNT(), amount);
            Provider_request = gson.toJson(etisalat_Request);
            int Log_id = MasaryManager.getInstance().Insert_Bill_Provider_Log(Provider_request, provider.getPROVIDER_ID(), "Payment", Integer.parseInt(custId));
            String response = sendRequest(etisalat_Request);
//            String response = "{\"operationType\":8,\"Status_Code\":\"0\",\"Status_Msg\":\"������ �������������� ���������� ������ �������������� 1363985495 \",\"Transaction_ID\":\"1363985495\",\"Amount\":0.0,\"Balance\":0.0}";
            MasaryManager.getInstance().Update_Bill_Provider_Log(Log_id, response);
            Etisalat_Response etisalat_Response = gson.fromJson(response, Etisalat_Response.class);
            etisalat_Response.setBill_Ref_No(bill_Response.getBILL_REF_NUMBER());
            etisalat_Response.setAmount(amount);
            etisalat_Response.setPhone(bill_Response.getBILLING_ACCOUNT());
            masary_Payment_Response = sendpaymentInquiry_Etisalat(etisalat_Response, payment_Request);
            double balance = getBalance(provider, custId);
            //-----------------------------------------------------------------------------
            if (!employeeID.equals("-1")) {
                if (MasaryManager.getInstance().getAllowedServiceBalance(employeeID, 1000) < amount) {
                    throw new Exception("رصيدك الحالي في هذه الخدمه لا يسمح باجراء العمليه");
                }
            }
            Status = MasaryManager.getInstance().GETMasary_Error(masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE(), provider);
            masary_Payment_Response.getMasary_Bill_Status().setSTATUS_CODE(String.valueOf(Status.getSTATUS_CODE()));
            masary_Payment_Response.setCustomerName("Not Available Now");
            int Transaction_id = MasaryManager.getInstance().INSERT_PAYMENT_RESPONSE_TEST(masary_Payment_Response, Integer.parseInt(custId), balance, provider, masary_Payment_Response.getCustomerName(), isValidDTO);
            //---------------------------------------------------------------------------------------------------
            build_request.LogPostpayed_Payment_Res(masary_Payment_Response);
            session.setAttribute("payment_Info", masary_Payment_Response);
            if (Status.getSTATUS_CODE() == 200) {
                MasaryManager.getInstance().updateUsed_provider(Integer.parseInt(bill_Response.getBILL_TYPE_CODE()), provider);
                session.setAttribute("transaction_id", Transaction_id);
                session.setAttribute("Provider_Payment_ID", "0");
            }
        } catch (Exception e) {
            MasaryManager.logger.error(e.getMessage(), e);
        } 
        
        return Status;
    }

    public double getBalance(Main_Provider provider, String custId) {
        MasaryManager.getInstance().logger.info("{Etisalat.getBalance(provider=[" + provider.getPROVIDER_ID() + "])}");
        double balance = 0;
//        double balance = 6220.87;
        try {
            Gson gson = new Gson();
            Etisalat_Request etisalat_Request = MasaryManager.getInstance().getBalanceRequest();
            String Provider_request = gson.toJson(etisalat_Request);
            int Log_id = MasaryManager.getInstance().Insert_Bill_Provider_Log(Provider_request, provider.getPROVIDER_ID(), "getBalance", Integer.parseInt(custId));
            String response = sendRequest(etisalat_Request);
            Etisalat_Response etisalat_Response = gson.fromJson(response, Etisalat_Response.class);
            balance = etisalat_Response.getBalance();
            MasaryManager.getInstance().Update_Bill_Provider_Log(Log_id, String.valueOf(balance));
        } catch (Exception e) {
            MasaryManager.logger.error(e.getMessage(), e);
        }
        return balance;
    }

    public Masary_Bill_Request Build_Request_Bill(String BTC, String phone) {
        Masary_Bill_Request masary_Bill_Request = new Masary_Bill_Request();
        try {
            Masary_signon_profile masary_signon_profile = new Masary_signon_profile();
            masary_signon_profile.setSIGNON_PROFILE_ID(1);
            masary_signon_profile.setSENDER("MS_MOB");
            masary_signon_profile.setRECIEVER("MSARYCOL");
            masary_signon_profile.setVERSION("V1.0");
            masary_Bill_Request.setMasary_signon_profile(masary_signon_profile);
            masary_Bill_Request.setMESSAGE_CODE("BillInqRq");
            masary_Bill_Request.setREQUEST_ID(build_request.getReqIdBiller());
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
            MasaryManager.logger.error(exc.getMessage(), exc);
        }

        return masary_Bill_Request;
        //---------------------End-BillRequest----------------
    }

    public Masary_Payment_Request Build_Request_Payment_Bill(Masary_Bill_Response bill_Response, double amount) {
        Masary_Payment_Request payment_Request = new Masary_Payment_Request();
        try {
            Masary_signon_profile masary_signon_profile = new Masary_signon_profile();
            masary_signon_profile.setSIGNON_PROFILE_ID(2);
            masary_signon_profile.setSENDER(bill_Response.getMasary_signon_profile().getRECIEVER());
            masary_signon_profile.setRECIEVER(bill_Response.getMasary_signon_profile().getSENDER());
            masary_signon_profile.setVERSION(bill_Response.getMasary_signon_profile().getVERSION());
            payment_Request.setMasary_signon_profile(masary_signon_profile);
            payment_Request.setMESSAGE_CODE("PmtAddRq");
            payment_Request.setREQUEST_ID(build_request.getReqIdBiller());
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
            payment_Request.setNOTIFY_MOBILE("01009912516");
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

        } catch (Exception exc) {
            MasaryManager.logger.error(exc.getMessage(), exc);
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

    public Masary_Bill_Response sendbillinquiry_Etisalat(Etisalat_Response etisalat_Response, String BTC, Main_Provider provider) {
        //---------------------------------------------test---------------------------------------------


        String billDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Masary_Bill_Response bill_Response = new Masary_Bill_Response();
        bill_Response.setCustomer_name("Not available now");
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
//                    bill_Response.setSIGNON_PROFILE_ID(2);
        bill_Response.setMESSAGE_CODE("BillInqRs");
        bill_Response.setREQUEST_ID(build_request.getReqIdBiller());
        bill_Response.setASYNC_REQUEST_ID(build_request.getReqIdBiller());
        bill_Response.setORIGINATOR_CODE("MSARYCOL");
        bill_Response.setTerminalId("12344");
        bill_Response.setSTATUS_CODE("200");
        bill_Response.setINCOPENAMOUNT(Boolean.TRUE);
        bill_Response.setDELIVERY_METHOD("MOB");
        bill_Response.setBILLING_ACCOUNT(etisalat_Response.getPhone());
        bill_Response.setBILL_TYPE_CODE(BTC);
        bill_Response.setBILL_REF_NUMBER(build_request.getReqIdBiller());
        List<Masary_Bill_Amounts> Amounts = new ArrayList<Masary_Bill_Amounts>();
        Masary_Bill_Amounts amount = new Masary_Bill_Amounts();
        amount.setBILL_SUMM_AMT_CODE("TotalAmtDue");
        amount.setBILL_REF_NUMBER(bill_Response.getBILL_REF_NUMBER());
        amount.setCUR_AMOUNT(etisalat_Response.getAmount());
        amount.setCUR_CODE("EGP");
        Amounts.add(amount);
        Masary_Bill_Amounts amount1 = new Masary_Bill_Amounts();
        amount1.setBILL_SUMM_AMT_CODE("Fees");
        amount1.setBILL_REF_NUMBER(bill_Response.getBILL_REF_NUMBER());
        amount1.setCUR_AMOUNT(1.00);
        amount1.setCUR_CODE("EGP");
        Amounts.add(amount1);
        bill_Response.setAmounts(Amounts);
        bill_Response.setDUE_DATE(billDate);
        bill_Response.setLOWER_AMOUNT(0.00);
        bill_Response.setLOWER_CURRUNT_AMOUNT("EGP");
        bill_Response.setUPPER_AMOUNT(0.00);
        bill_Response.setUPPER_CURRENT_AMOUNT("EGP");
        bill_Response.setRulesAwareness("yy");
        bill_Response.setBILL_STATUS("BillUnpaid");
        if ((etisalat_Response.getStatus_Code().equals("0") && etisalat_Response.getAmount() < 1.0) || (etisalat_Response.getStatus_Code().equals("1") && etisalat_Response.getAmount() < 1.0) || (etisalat_Response.getStatus_Code().equals("2") && etisalat_Response.getAmount() < 1.0)) {
            etisalat_Response.setStatus_Code("35");
        }
        Masary_Bill_Status status = new Masary_Bill_Status();
        Masary_Error Status = new Masary_Error();
        status.setSTATUS_CODE(etisalat_Response.getStatus_Code());
        status.setSEVERITY("info");
        status.setSTATUS_DESC(etisalat_Response.getStatus_Msg());
        bill_Response.setMasary_Bill_Status(status);
        return bill_Response;
        //-------------------------------------End Test------------------------------------------------

    }

    public Masary_Payment_Response sendpaymentInquiry_Khdmaty(Khdmaty_PayInvoice_response Khdmaty_Response, Masary_Payment_Request payment_Request) {
        Masary_Payment_Response masary_Payment_Response = new Masary_Payment_Response();

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
            masary_Payment_Response.setCLIENT_TERMINAL_SEQ_ID(Khdmaty_Response.getRequest());
            List<Customers_Properties> customers_Propertiesres = new ArrayList<Customers_Properties>();
            Customers_Properties customers_Propertieres = new Customers_Properties();
            customers_Propertieres.setKEY("PosSerialNumber");
            customers_Propertieres.setVALUE("900-900-900");
            customers_Propertiesres.add(customers_Propertieres);
            masary_Payment_Response.setCustomers_Properties(customers_Propertiesres);
            Masary_Bill_Status masary_Bill_Status = new Masary_Bill_Status();
            masary_Bill_Status.setSTATUS_CODE(Khdmaty_Response.getReturnCode());
            masary_Bill_Status.setSEVERITY("Info");
            masary_Bill_Status.setSTATUS_DESC(Khdmaty_Response.getReturnMessage());
            masary_Payment_Response.setMasary_Bill_Status(masary_Bill_Status);
            List<Masary_Payment_Type> masary_Payment_Types = new ArrayList<Masary_Payment_Type>();
            Masary_Payment_Type masary_Payment_Type = new Masary_Payment_Type();
            masary_Payment_Type.setPAYMENT_ID(Khdmaty_Response.getInvoice());
            masary_Payment_Type.setPAYMENT_ID_TYPE("FPTN");
            masary_Payment_Type.setCREATED_DATE(xMLGregorianCalendarreq.toString());
            masary_Payment_Types.add(masary_Payment_Type);
            Masary_Payment_Type masary_Payment_Type1 = new Masary_Payment_Type();
            masary_Payment_Type1.setPAYMENT_ID(Khdmaty_Response.getInvoice());
            masary_Payment_Type1.setPAYMENT_ID_TYPE("BNKPTN");
            masary_Payment_Type1.setCREATED_DATE(xMLGregorianCalendarreq.toString());
            masary_Payment_Types.add(masary_Payment_Type1);
            Masary_Payment_Type masary_Payment_Type2 = new Masary_Payment_Type();
            masary_Payment_Type2.setPAYMENT_ID(Khdmaty_Response.getInvoice());
            masary_Payment_Type2.setPAYMENT_ID_TYPE("BNKDTN");
            masary_Payment_Type2.setCREATED_DATE(xMLGregorianCalendarreq.toString());
            masary_Payment_Types.add(masary_Payment_Type2);
            Masary_Payment_Type masary_Payment_Type3 = new Masary_Payment_Type();
            masary_Payment_Type3.setPAYMENT_ID(Khdmaty_Response.getInvoice());
            masary_Payment_Type3.setPAYMENT_ID_TYPE("FCRN");
            masary_Payment_Type3.setCREATED_DATE(xMLGregorianCalendarreq.toString());
            masary_Payment_Types.add(masary_Payment_Type3);
            masary_Payment_Response.setMasary_Payment_Types(masary_Payment_Types);
            masary_Payment_Response.setBILLING_ACCOUNT("(" + Khdmaty_Response.getTelephoneCode() + ")" + Khdmaty_Response.getTelephoneNumber());
            masary_Payment_Response.setBILL_REF_NUMBER(Khdmaty_Response.getBILL_REF_NUMBER());
            masary_Payment_Response.setNOTIFY_MOBILE("0120000200");
            masary_Payment_Response.setBILL_TYPE_CODE(payment_Request.getBILL_TYPE_CODE());
            masary_Payment_Response.setPAYMENT_TYPE("POST");
            masary_Payment_Response.setDELIVERY_METHOD("MOB");
            masary_Payment_Response.setCURRENT_AMOUNT(Khdmaty_Response.getInvoiceTotal());
            masary_Payment_Response.setFEES_AMOUNT(3.00);
            masary_Payment_Response.setACCOUNT_ID("12344");
            masary_Payment_Response.setACCOUNT_TYPE("SDA");
            masary_Payment_Response.setACCOUNT_KEY("1234");
            masary_Payment_Response.setACCOUNT_CUR("EGP");
            masary_Payment_Response.setPAYMENT_METHOD("CASH");
            masary_Payment_Response.setPRC_DATE("2012-04-22");
//            masary_Payment_Response.setBILL_REF_NUMBER(null);
//-------------------------------------------------------------------------------------------------------

        } catch (Exception e) {
            MasaryManager.logger.error("WSDL ERROR :" + e.getMessage(), e);
        }
        return masary_Payment_Response;

    }

    public Masary_Payment_Response sendpaymentInquiry_Etisalat(Etisalat_Response etisalat_Response, Masary_Payment_Request payment_Request) {

        Masary_Payment_Response masary_Payment_Response = new Masary_Payment_Response();
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
            masary_Payment_Response.setCLIENT_TERMINAL_SEQ_ID(etisalat_Response.getTransaction_ID());
            List<Customers_Properties> customers_Propertiesres = new ArrayList<Customers_Properties>();
            Customers_Properties customers_Propertieres = new Customers_Properties();
            customers_Propertieres.setKEY("PosSerialNumber");
            customers_Propertieres.setVALUE("900-900-900");
            customers_Propertiesres.add(customers_Propertieres);
            masary_Payment_Response.setCustomers_Properties(customers_Propertiesres);
            Masary_Bill_Status masary_Bill_Status = new Masary_Bill_Status();
            masary_Bill_Status.setSTATUS_CODE(etisalat_Response.getStatus_Code());
            masary_Bill_Status.setSEVERITY("Info");
            masary_Bill_Status.setSTATUS_DESC(etisalat_Response.getStatus_Msg());
            masary_Payment_Response.setMasary_Bill_Status(masary_Bill_Status);
            List<Masary_Payment_Type> masary_Payment_Types = new ArrayList<Masary_Payment_Type>();
            Masary_Payment_Type masary_Payment_Type = new Masary_Payment_Type();
            masary_Payment_Type.setPAYMENT_ID(etisalat_Response.getBill_Ref_No());
            masary_Payment_Type.setPAYMENT_ID_TYPE("FPTN");
            masary_Payment_Type.setCREATED_DATE(xMLGregorianCalendarreq.toString());
            masary_Payment_Types.add(masary_Payment_Type);
            Masary_Payment_Type masary_Payment_Type1 = new Masary_Payment_Type();
            masary_Payment_Type1.setPAYMENT_ID(etisalat_Response.getBill_Ref_No());
            masary_Payment_Type1.setPAYMENT_ID_TYPE("BNKPTN");
            masary_Payment_Type1.setCREATED_DATE(xMLGregorianCalendarreq.toString());
            masary_Payment_Types.add(masary_Payment_Type1);
            Masary_Payment_Type masary_Payment_Type2 = new Masary_Payment_Type();
            masary_Payment_Type2.setPAYMENT_ID(etisalat_Response.getBill_Ref_No());
            masary_Payment_Type2.setPAYMENT_ID_TYPE("BNKDTN");
            masary_Payment_Type2.setCREATED_DATE(xMLGregorianCalendarreq.toString());
            masary_Payment_Types.add(masary_Payment_Type2);
            Masary_Payment_Type masary_Payment_Type3 = new Masary_Payment_Type();
            masary_Payment_Type3.setPAYMENT_ID(etisalat_Response.getBill_Ref_No());
            masary_Payment_Type3.setPAYMENT_ID_TYPE("FCRN");
            masary_Payment_Type3.setCREATED_DATE(xMLGregorianCalendarreq.toString());
            masary_Payment_Types.add(masary_Payment_Type3);
            masary_Payment_Response.setMasary_Payment_Types(masary_Payment_Types);
            masary_Payment_Response.setBILLING_ACCOUNT(etisalat_Response.getPhone());
            masary_Payment_Response.setBILL_REF_NUMBER(etisalat_Response.getBill_Ref_No());
            masary_Payment_Response.setNOTIFY_MOBILE("0120000200");
            masary_Payment_Response.setBILL_TYPE_CODE(payment_Request.getBILL_TYPE_CODE());
            masary_Payment_Response.setPAYMENT_TYPE("POST");
            masary_Payment_Response.setDELIVERY_METHOD("MOB");
            masary_Payment_Response.setCURRENT_AMOUNT(etisalat_Response.getAmount());
            masary_Payment_Response.setFEES_AMOUNT(1.00);
            masary_Payment_Response.setACCOUNT_ID("12344");
            masary_Payment_Response.setACCOUNT_TYPE("SDA");
            masary_Payment_Response.setACCOUNT_KEY("1234");
            masary_Payment_Response.setACCOUNT_CUR("EGP");
            masary_Payment_Response.setPAYMENT_METHOD("CASH");
            masary_Payment_Response.setPRC_DATE("2012-04-22");
//            masary_Payment_Response.setBILL_REF_NUMBER(null);
//-------------------------------------------------------------------------------------------------------

        } catch (Exception e) {
            MasaryManager.logger.error("WSDL ERROR :" + e.getMessage(), e);
        }
        return masary_Payment_Response;

    }

    public String sendRequest(Etisalat_Request etisalat_Request) {
        String response = "";
        try {
            // Test configuration
//            Socket client = new Socket("localhost", 12014);

        // Production configuration
            Socket client = new Socket("10.29.10.201", 12014);
            //getting the o/p stream of that connection
            PrintStream out = new PrintStream(client.getOutputStream());
            //sending the message to server
            Gson gson = new Gson();
            String request = gson.toJson(etisalat_Request);
            out.print(request + "\n");
            out.flush();
            //reading the response using input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            response = in.readLine();
            //closing the streams
            in.close();
            out.close();
        } catch (Exception err) {
            MasaryManager.logger.error(err.getMessage(), err);
        } 
        
        return response;
    }
}