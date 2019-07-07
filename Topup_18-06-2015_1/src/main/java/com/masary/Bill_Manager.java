/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;

import com.masary.common.CONFIG;
import com.masary.database.dto.Customers_Properties;
import com.masary.database.dto.Masary_Bill_Amounts;
import com.masary.database.dto.Masary_Bill_Response;
import com.masary.database.dto.Masary_Bill_Status;
import com.masary.database.dto.Masary_Payment_Response;
import com.masary.database.dto.Masary_Payment_Type;
import com.masary.database.dto.Masary_signon_profile;
import com.masary.database.manager.MasaryManager;

/**
 *
 * @author Michael
 */
public class Bill_Manager extends HttpServlet {

    java.util.Date date = new Date();
    XMLGregorianCalendarConverter xMLGregorianCalendarConverter = new XMLGregorianCalendarConverter();
    XMLGregorianCalendar xMLGregorianCalendar = xMLGregorianCalendarConverter.asXMLGregorianCalendar(date);
    String nextJSP = "";
    HttpSession session;
    build_request build_request=new build_request();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        session = request.getSession();
        String action = request.getParameter("action");
        String custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }

    
    public Masary_Bill_Response sendbillinquiry_test(String BTC, String phone) {
        //---------------------------------------------test---------------------------------------------
        Masary_Bill_Response bill_Response = new Masary_Bill_Response();
        bill_Response.setCLIENT_DATE(xMLGregorianCalendar);
        bill_Response.setSERVER_DATE("2012-04-22T16:43:06.453");
        bill_Response.setLanguage("en-gb");
        Masary_signon_profile masary_signon_profiler = new Masary_signon_profile();
        masary_signon_profiler.setSIGNON_PROFILE_ID(2);
        masary_signon_profiler.setSENDER("MSARYCOL");
        masary_signon_profiler.setRECIEVER("MS_MOB");
        masary_signon_profiler.setVERSION("V1.0");
        bill_Response.setMasary_signon_profile(masary_signon_profiler);
//                    bill_Response.setSIGNON_PROFILE_ID(2);
        bill_Response.setMESSAGE_CODE("BillInqRs");
        bill_Response.setREQUEST_ID("321bb883-7158-4591-aed4-fe812277f122");
        bill_Response.setASYNC_REQUEST_ID("321bb883-7158-4591-aed4-fe812277f122");
        bill_Response.setORIGINATOR_CODE("MSARYCOL");
        bill_Response.setTerminalId("12344");
        bill_Response.setSTATUS_CODE("200");
        bill_Response.setINCOPENAMOUNT(Boolean.TRUE);
        bill_Response.setDELIVERY_METHOD("MOB");
        bill_Response.setBILLING_ACCOUNT(phone);
        bill_Response.setBILL_TYPE_CODE(BTC);
        bill_Response.setBILL_REF_NUMBER("7852e2ef-4062-422a-979f-1ea3431deee3");
        List<Masary_Bill_Amounts> Amounts = new ArrayList<Masary_Bill_Amounts>();
        Masary_Bill_Amounts amount = new Masary_Bill_Amounts();
        amount.setBILL_SUMM_AMT_CODE("TotalAmtDue");
        amount.setBILL_REF_NUMBER("7852e2ef-4062-422a-979f-1ea3431deee3");
        amount.setCUR_AMOUNT(100.00);
        amount.setCUR_CODE("EGP");
        Amounts.add(amount);
        Masary_Bill_Amounts amount1 = new Masary_Bill_Amounts();
        amount1.setBILL_SUMM_AMT_CODE("Fees");
        amount1.setBILL_REF_NUMBER("7852e2ef-4062-422a-979f-1ea3431deee3");
        amount1.setCUR_AMOUNT(1.00);
        amount1.setCUR_CODE("EGP");
        Amounts.add(amount1);
        bill_Response.setAmounts(Amounts);
        bill_Response.setDUE_DATE("2009-01-01");
        bill_Response.setLOWER_AMOUNT(1.00);
        bill_Response.setLOWER_CURRUNT_AMOUNT("EGP");
        bill_Response.setUPPER_AMOUNT(10.00);
        bill_Response.setUPPER_CURRENT_AMOUNT("EGP");
        bill_Response.setRulesAwareness("yy");
        bill_Response.setBILL_STATUS("BillUnpaid");
        Masary_Bill_Status status = new Masary_Bill_Status();
        status.setSTATUS_CODE("200");
        status.setSEVERITY("info");
        status.setSTATUS_DESC("Success");
        bill_Response.setMasary_Bill_Status(status);

        return bill_Response;

        //-------------------------------------End Test------------------------------------------------

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

    public Masary_Payment_Response sendpaymentInquiry_Test(String BTC, String phone) {
        Masary_Payment_Response masary_Payment_Response = new Masary_Payment_Response();

        try {
            //---------------------------------------test----------------------------------------------------------------

            masary_Payment_Response.setRESPONSE_DATE("2012-04-22T16:45:26.971");
            masary_Payment_Response.setSERVER_DATE("2012-04-22T16:43:12.557");
            Masary_signon_profile masary_signon_profileres = new Masary_signon_profile();
            masary_signon_profileres.setSIGNON_PROFILE_ID(1);
            masary_signon_profileres.setSENDER("MSARYCOL");
            masary_signon_profileres.setRECIEVER("MS_MOB");
            masary_signon_profileres.setVERSION("V1.0");
            masary_Payment_Response.setMasary_signon_profile(masary_signon_profileres);
            masary_Payment_Response.setMESSAGE_CODE("PmtAddRs");
            masary_Payment_Response.setREQUEST_ID("f37bec7e-7020-ca8e-c2bf-82a41e1a8014");
            masary_Payment_Response.setASYNC_REQUEST_ID("321bb883-7158-4591-aed4-fe812277f122");
            masary_Payment_Response.setORIGINATOR_CODE("GW_POS4");
            masary_Payment_Response.setTERMINAL_ID("12344");
            masary_Payment_Response.setCLIENT_TERMINAL_SEQ_ID("1");
            List<Customers_Properties> customers_Propertiesres = new ArrayList<Customers_Properties>();
            Customers_Properties customers_Propertieres = new Customers_Properties();
            customers_Propertieres.setKEY("PosSerialNumber");
            customers_Propertieres.setVALUE("900-900-900");
            customers_Propertiesres.add(customers_Propertieres);
            masary_Payment_Response.setCustomers_Properties(customers_Propertiesres);
            Masary_Bill_Status masary_Bill_Status = new Masary_Bill_Status();
            masary_Bill_Status.setSTATUS_CODE("200");
            masary_Bill_Status.setSEVERITY("Info");
            masary_Bill_Status.setSTATUS_DESC("Success.");
            masary_Payment_Response.setMasary_Bill_Status(masary_Bill_Status);
            List<Masary_Payment_Type> masary_Payment_Types = new ArrayList<Masary_Payment_Type>();
            Masary_Payment_Type masary_Payment_Type = new Masary_Payment_Type();
            masary_Payment_Type.setPAYMENT_ID("568de521-a509-408c-907b-402ac378a22f");
            masary_Payment_Type.setPAYMENT_ID_TYPE("FPTN");
            masary_Payment_Type.setCREATED_DATE("2012-04-22T16:43:11.994");
            masary_Payment_Types.add(masary_Payment_Type);
            Masary_Payment_Type masary_Payment_Type1 = new Masary_Payment_Type();
            masary_Payment_Type1.setPAYMENT_ID("1c640193-0f6a-42ab-b9ba-40b4dc566d48");
            masary_Payment_Type1.setPAYMENT_ID_TYPE("BNKPTN");
            masary_Payment_Type1.setCREATED_DATE("2012-04-22T16:43:11.776");
            masary_Payment_Types.add(masary_Payment_Type1);
            Masary_Payment_Type masary_Payment_Type2 = new Masary_Payment_Type();
            masary_Payment_Type2.setPAYMENT_ID("1335105791915170");
            masary_Payment_Type2.setPAYMENT_ID_TYPE("BNKDTN");
            masary_Payment_Type2.setCREATED_DATE("2012-04-22T16:43:11.920");
            masary_Payment_Types.add(masary_Payment_Type2);
            Masary_Payment_Type masary_Payment_Type3 = new Masary_Payment_Type();
            masary_Payment_Type3.setPAYMENT_ID("243079");
            masary_Payment_Type3.setPAYMENT_ID_TYPE("FCRN");
            masary_Payment_Type3.setCREATED_DATE("2012-04-22T16:43:11.776");
            masary_Payment_Types.add(masary_Payment_Type3);
            masary_Payment_Response.setMasary_Payment_Types(masary_Payment_Types);
            masary_Payment_Response.setBILLING_ACCOUNT("0120000200");
            masary_Payment_Response.setBILL_REF_NUMBER("7852e2ef-4062-422a-979f-1ea3431deee3");
            masary_Payment_Response.setNOTIFY_MOBILE("0120000200");
            masary_Payment_Response.setBILL_TYPE_CODE("113");
            masary_Payment_Response.setPAYMENT_TYPE("POST");
            masary_Payment_Response.setDELIVERY_METHOD("MOB");
            masary_Payment_Response.setCURRENT_AMOUNT(10.00);
            masary_Payment_Response.setFEES_AMOUNT(1.00);
            masary_Payment_Response.setACCOUNT_ID("12344");
            masary_Payment_Response.setACCOUNT_TYPE("SDA");
            masary_Payment_Response.setACCOUNT_KEY("1234");
            masary_Payment_Response.setACCOUNT_CUR("EGP");
            masary_Payment_Response.setPAYMENT_METHOD("CASH");
            masary_Payment_Response.setPRC_DATE("2012-04-22");
//-------------------------------------------------------------------------------------------------------

        } catch (Exception e) {
            MasaryManager.logger.error("WSDL ERROR :" + e);
        }
        return masary_Payment_Response;

    }

}