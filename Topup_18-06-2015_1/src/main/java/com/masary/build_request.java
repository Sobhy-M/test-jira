package com.masary;

import com.masary.database.dto.Customers_Properties;
import com.masary.database.dto.Masary_Balance_Sheet;
import com.masary.database.dto.Masary_Balance_Sheet_Detailes;
import com.masary.database.dto.Masary_Bill_Amounts;
import com.masary.database.dto.Masary_Bill_Balance;
import com.masary.database.dto.Masary_Bill_Biller;
import com.masary.database.dto.Masary_Bill_Request;
import com.masary.database.dto.Masary_Bill_Response;
import com.masary.database.dto.Masary_Bill_Type;
import com.masary.database.dto.Masary_Biller_response;
import com.masary.database.dto.Masary_Payment_Request;
import com.masary.database.dto.Masary_Payment_Response;
import com.masary.database.dto.Masary_Payment_Type;
import com.masary.database.dto.Masary_signon_profile;
import com.masary.database.manager.MasaryManager;
import java.util.Random;
import javax.xml.datatype.XMLGregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michael
 */
public class build_request {

    public void LogBiller_Inq_Req(XMLGregorianCalendar ClientDt, String CustLangPref, String Sender, String Receiver, String MsgCode, String Version, Boolean SuppressEcho, String RqUID, String ChannelRqID, String ClientTerminalSeqId, String OriginatorCode, String TerminalId, String Cursor, String MaxRec, String DeliveryMethod, String ProfileCode) {
//need to know type
        MasaryManager.logger.info("Bill Biller Request");
        StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ejb=\"http://ejb.gateway.ebpp.fawryis.com/\"><soapenv:Body><ejb:process><arg0><Request><SignonRq><ClientDt>");
        builder.append(ClientDt);
        builder.append("</ClientDt><CustLangPref>");
        builder.append(CustLangPref);
        builder.append("</CustLangPref><SuppressEcho>");
        builder.append(SuppressEcho);
        builder.append("</SuppressEcho><SignonProfile><Sender>");
        builder.append(Sender);
        builder.append("</Sender><Receiver>");
        builder.append(Receiver);
        builder.append("</Receiver><MsgCode>");
        builder.append(MsgCode);
        builder.append("</MsgCode><Version>");
        builder.append(Version);
        builder.append("</Version></SignonProfile></SignonRq><PresSvcRq><RqUID>");
        builder.append(RqUID);
        builder.append("</RqUID><MsgRqHdr><NetworkTrnInfo><OriginatorCode>");
        builder.append(OriginatorCode);
        builder.append("</OriginatorCode><TerminalId>");
        builder.append(TerminalId);
        builder.append("</TerminalId></NetworkTrnInfo><ClientTerminalSeqId>");
        builder.append(ClientTerminalSeqId);
        builder.append("</ClientTerminalSeqId></MsgRqHdr><BillerInqRq><CustId><OfficialId>");
        builder.append(ClientTerminalSeqId);
        builder.append("</OfficialId><OfficialIdType>NAT</OfficialIdType></CustId><RecCtrlIn><MaxRec>");
        builder.append(MaxRec);
        builder.append("</MaxRec><Cursor>");
        builder.append(Cursor);
        builder.append("</Cursor></RecCtrlIn><DeliveryMethod>");
        builder.append(DeliveryMethod);
        builder.append("</DeliveryMethod></BillerInqRq></PresSvcRq></Request></arg0></ejb:process></soapenv:Body>");
//        builder.append("</PASSWORD><EXTCODE></EXTCODE><EXTREFNUM></EXTREFNUM><LANGUAGE1>0</LANGUAGE1></COMMAND>");
        MasaryManager.logger.info(builder.toString());
//        return builder.toString();
    }

    public void LogBiller_Inq_Res(Masary_Biller_response biller_response) {
//need to know type
        MasaryManager.logger.info("Bill Biller Response");
        StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns3:processResponse xmlns:ns2=\"http://www.fawry-eg.com/ebpp/IFXMessages/\" xmlns:ns3=\"http://ejb.gateway.ebpp.fawryis.com/\"><return><Response><SignonRs><ClientDt>");
        builder.append(biller_response.getRESPONSE_DATE());
        builder.append("</ClientDt><CustLangPref>");
        builder.append("EN-GB");
        builder.append("</CustLangPref><ServerDt>");
        builder.append(biller_response.getRESPONSE_DATE());
        builder.append("</ServerDt><Language>en-gb</Language><SignonProfile><Sender>MSARYCOL</Sender><Receiver>MS_MOB</Receiver><MsgCode>BillerInqRs</MsgCode><Version>V1.0</Version></SignonProfile></SignonRs><PresSvcRs><RqUID>");
        builder.append(biller_response.getREQID());
        builder.append("</RqUID><MsgRqHdr><NetworkTrnInfo><OriginatorCode>");
        builder.append(biller_response.getORIGINATOR_CODE());
        builder.append("</OriginatorCode><TerminalId>");
        builder.append(biller_response.getTERMINAL_ID());
        builder.append("</TerminalId></NetworkTrnInfo><ClientTerminalSeqId>");
        builder.append(biller_response.getCLIENT_TERMINAL_SEQ_ID());
        builder.append("</ClientTerminalSeqId></MsgRqHdr><Status><StatusCode>");
        builder.append(biller_response.getStatus().getSTATUS_CODE());
        builder.append("</StatusCode><Severity>");
        builder.append(biller_response.getStatus().getSEVERITY());
        builder.append("</Severity><StatusDesc>");
        builder.append(biller_response.getStatus().getSTATUS_DESC());
        builder.append("</StatusDesc></Status><BillerInqRs><CustId><OfficialId>OfficialId</OfficialId><OfficialIdType>NAT</OfficialIdType></CustId><RecCtrlOut><MatchedRec>");
        builder.append(biller_response.getMATCHED_REC());
        builder.append("</MatchedRec><SentRec>20</SentRec><Cursor>1</Cursor></RecCtrlOut><DeliveryMethod>");
        builder.append(biller_response.getDELIVERY_METHOD());
        builder.append("</DeliveryMethod>");
        for (Masary_Bill_Biller biller : biller_response.getBillers()) {
            builder.append("<BillerRec><BillerId>");
            builder.append(biller.getBILLER_ID());
            builder.append("</BillerId><BillerName>");
            builder.append(biller.getBILLER_NAME());
            builder.append("</BillerName>");
            for (Masary_Bill_Type type : biller.getMasary_bill_types()) {
                builder.append("<BillerInfo><BillTypeCode>");
                builder.append(type.getBILL_TYPE_ID());
                builder.append("</BillTypeCode><Name>");
                builder.append(type.getBILL_NAME());
                builder.append("</Name><PmtType>");
                builder.append(type.getPMT_TYPE());
                builder.append("</PmtType><ServiceType>");
                builder.append(type.getSERVICE_TYPE());
                builder.append("</ServiceType><ServiceName>");
                builder.append(type.getSERVICE_NAME());
                builder.append("</ServiceName><BillTypeAcctLabel>");
                builder.append(type.getBILL_LABLE());
                builder.append("</BillTypeAcctLabel><BillRefType>false</BillRefType><PaymentRules><IsFracAcpt>");
                builder.append(type.isIS_FRAC_ACC());
                builder.append("</IsFracAcpt><IsPrtAcpt>");
                builder.append(type.isIS_PART_ACC());
                builder.append("</IsPrtAcpt><IsOvrAcpt>");
                builder.append(type.isIS_OVER_ACC());
                builder.append("</IsOvrAcpt><IsAdvAcpt>");
                builder.append(type.isIS_ADV_ACC());
                builder.append("</IsAdvAcpt></PaymentRules></BillerInfo>");
            }
            builder.append("<BillerStatus>");
            builder.append(biller.getACTIVE());
            builder.append("</BillerStatus></BillerRec>");
        }
        builder.append("</BillerInqRs></PresSvcRs></Response></return></ns3:processResponse></soapenv:Body></soapenv:Envelope>");
//        builder.append("</PASSWORD><EXTCODE></EXTCODE><EXTREFNUM></EXTREFNUM><LANGUAGE1>0</LANGUAGE1></COMMAND>");
        MasaryManager.logger.info(builder.toString());
//        return builder.toString();
    }

    public String LogBill_inquiry_Req(Masary_Bill_Request masary_Bill_Request) {
//need to know type
        MasaryManager.logger.info("Bill Inquery Request For " + masary_Bill_Request.getBILLING_ACCOUNT() + " Service " + masary_Bill_Request.getBILL_TYPE_CODE());
        StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ejb=\"http://ejb.gateway.ebpp.fawryis.com/\"><soapenv:Body><ejb:process><arg0><Request><SignonRq><ClientDt>");
        builder.append(masary_Bill_Request.getREQUEST_DATE());
        builder.append("</ClientDt><CustLangPref>");
        builder.append("ar-eg");
        builder.append("</CustLangPref><SuppressEcho>");
        builder.append(Boolean.FALSE);
        builder.append("</SuppressEcho><SignonProfile><Sender>");
        builder.append(masary_Bill_Request.getMasary_signon_profile().getSENDER());
        builder.append("</Sender><Receiver>");
        builder.append(masary_Bill_Request.getMasary_signon_profile().getRECIEVER());
        builder.append("</Receiver><MsgCode>");
        builder.append(masary_Bill_Request.getMESSAGE_CODE());
        builder.append("</MsgCode><Version>");
        builder.append(masary_Bill_Request.getMasary_signon_profile().getVERSION());
        builder.append("</Version></SignonProfile></SignonRq><IsRetry>");
        builder.append(Boolean.FALSE);
        builder.append("</IsRetry><PresSvcRq><RqUID>");
        builder.append(masary_Bill_Request.getREQUEST_ID());
        builder.append("</RqUID><MsgRqHdr><NetworkTrnInfo><OriginatorCode>");
        builder.append(masary_Bill_Request.getORIGINATOR_CODE());
        builder.append("</OriginatorCode><TerminalId>");
        builder.append(masary_Bill_Request.getTERMINALID());
        builder.append("</TerminalId></NetworkTrnInfo></MsgRqHdr><BillInqRq><RecCtrlIn><MaxRec>");
        builder.append("0");
        builder.append("</MaxRec></RecCtrlIn><IncOpenAmt>");
        builder.append(masary_Bill_Request.getINCOPEN_AMOUNT());
        builder.append("</IncOpenAmt><BillingAcct>");
        builder.append(masary_Bill_Request.getBILLING_ACCOUNT());
        builder.append("</BillingAcct><BankId>");
        builder.append(masary_Bill_Request.getBANK_ID());
        builder.append("</BankId><BillTypeCode>");
        builder.append(masary_Bill_Request.getBILL_TYPE_CODE());
        builder.append("</BillTypeCode><DeliveryMethod>");
        builder.append(masary_Bill_Request.getDELEVERY_METHOD());
        builder.append("</DeliveryMethod></BillInqRq></PresSvcRq></Request></arg0></ejb:process></soapenv:Body></soapenv:Envelope>");
//        builder.append("</PASSWORD><EXTCODE></EXTCODE><EXTREFNUM></EXTREFNUM><LANGUAGE1>0</LANGUAGE1></COMMAND>");
        MasaryManager.logger.info("" + builder.toString());
        return builder.toString();
    }

    public String LogBill_inquiry_Res(Masary_Bill_Response bill_Response) {
//need to know type
        MasaryManager.logger.info("Bill Inquery Response For " + bill_Response.getBILLING_ACCOUNT() + " Service " + bill_Response.getBILL_TYPE_CODE());
        StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?><soapenv:Envelopexmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns3:processResponse xmlns:ns2=\"http://www.fawry-eg.com/ebpp/IFXMessages/\" xmlns:ns3=\"http://ejb.gateway.ebpp.fawryis.com/\"><return><Response><SignonRs><ClientDt>");
        builder.append(bill_Response.getCLIENT_DATE());
        builder.append("</ClientDt><CustLangPref>");
        builder.append("ar-eg");
        builder.append("</CustLangPref><ServerDt>");
        builder.append(bill_Response.getSERVER_DATE());
        builder.append("</ServerDt><Language>");
        builder.append(bill_Response.getLanguage());
        builder.append("</Language><SignonProfile><Sender>");
        builder.append(bill_Response.getMasary_signon_profile().getSENDER());
        builder.append("</Sender><Receiver>");
        builder.append(bill_Response.getMasary_signon_profile().getRECIEVER());
        builder.append("</Receiver><MsgCode>");
        builder.append(bill_Response.getMESSAGE_CODE());
        builder.append("</MsgCode><Version>");
        builder.append(bill_Response.getMasary_signon_profile().getVERSION());
        builder.append("</Version></SignonProfile></SignonRs><PresSvcRs><RqUID>");
        builder.append(bill_Response.getREQUEST_ID());
        builder.append("</RqUID><AsyncRqUID>");
        builder.append(bill_Response.getASYNC_REQUEST_ID());
        builder.append("</AsyncRqUID><MsgRqHdr><NetworkTrnInfo><OriginatorCode>");
        builder.append(bill_Response.getORIGINATOR_CODE());
        builder.append("</OriginatorCode><TerminalId>");
        builder.append(bill_Response.getTerminalId());
        builder.append("</TerminalId></NetworkTrnInfo></MsgRqHdr><Status><StatusCode>");
        builder.append(bill_Response.getMasary_Bill_Status().getSTATUS_CODE());
        builder.append("</StatusCode><Severity>");
        builder.append(bill_Response.getMasary_Bill_Status().getSEVERITY());
        builder.append("</Severity><StatusDesc>");
        builder.append(bill_Response.getMasary_Bill_Status().getSTATUS_DESC());
        builder.append("</StatusDesc></Status><BillInqRs><IncOpenAmt>");
        builder.append(bill_Response.getINCOPENAMOUNT());
        builder.append("</IncOpenAmt><DeliveryMethod>");
        builder.append(bill_Response.getDELIVERY_METHOD());
        builder.append("</DeliveryMethod>");
        if (bill_Response.getMasary_Bill_Status().getSTATUS_CODE().equals("200")) {
            builder.append("<BillRec><BillingAcct>");
            builder.append(bill_Response.getBILLING_ACCOUNT());
            builder.append("</BillingAcct><BillerId>1</BillerId><BillTypeCode>");
            builder.append(bill_Response.getBILL_TYPE_CODE());
            builder.append("</BillTypeCode><BillRefNumber>");
            builder.append(bill_Response.getBILL_REF_NUMBER());
            builder.append("</BillRefNumber><BillInfo>");
            for (Masary_Bill_Amounts amount : bill_Response.getAmounts()) {
                builder.append("<BillSummAmt><BillSummAmtCode>");
                builder.append(amount.getBILL_SUMM_AMT_CODE());
                builder.append("</BillSummAmtCode><CurAmt><Amt>");
                builder.append(amount.getCUR_AMOUNT());
                builder.append("</Amt><CurCode>");
                builder.append(amount.getCUR_CODE());
                builder.append("</CurCode></CurAmt></BillSummAmt>");
            }
            builder.append("<DueDt>");
            builder.append(bill_Response.getDUE_DATE());
            builder.append("</DueDt><PaymentRange><Lower><Amt>");
            builder.append(bill_Response.getLOWER_AMOUNT());
            builder.append("</Amt><CurCode>");
            builder.append(bill_Response.getLOWER_CURRUNT_AMOUNT());
            builder.append("</CurCode></Lower><Upper><Amt>");
            builder.append(bill_Response.getUPPER_AMOUNT());
            builder.append("</Amt><CurCode>");
            builder.append(bill_Response.getUPPER_CURRENT_AMOUNT());
            builder.append("</CurCode></Upper></PaymentRange><RulesAwareness>");
            builder.append(bill_Response.getRulesAwareness());
            builder.append("</RulesAwareness></BillInfo><BillStatus>");
            builder.append(bill_Response.getBILL_STATUS());
            builder.append("</BillStatus></BillRec>");
        }
        builder.append("</BillInqRs></PresSvcRs></Response></return></ns3:processResponse></soapenv:Body></soapenv:Envelope>");
//        builder.append("</PASSWORD><EXTCODE></EXTCODE><EXTREFNUM></EXTREFNUM><LANGUAGE1>0</LANGUAGE1></COMMAND>");
        MasaryManager.logger.info("" + builder.toString());
        return builder.toString();
    }

    public String LogPostpayed_Payment_Req(Masary_Payment_Request payment_Request) {
//need to know type
        MasaryManager.logger.info("Bill Payment Request For " + payment_Request.getBILLING_ACCOUNT() + " Service " + payment_Request.getBILL_TYPE_CODE());
        StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ejb=\"http://ejb.gateway.ebpp.fawryis.com/\"><soapenv:Body><ejb:process><arg0><Request><SignonRq><ClientDt>");
        builder.append(payment_Request.getREQUEST_DATE());
        builder.append("</ClientDt><CustLangPref>");
        builder.append("AR-EG");
        builder.append("</CustLangPref><SuppressEcho>");
        builder.append("false");
        builder.append("</SuppressEcho><SignonProfile><Sender>");
        builder.append(payment_Request.getMasary_signon_profile().getSENDER());
        builder.append("</Sender><Receiver>");
        builder.append(payment_Request.getMasary_signon_profile().getRECIEVER());
        builder.append("</Receiver><MsgCode>");
        builder.append(payment_Request.getMESSAGE_CODE());
        builder.append("</MsgCode><Version>");
        builder.append(payment_Request.getMasary_signon_profile().getVERSION());
        builder.append("</Version></SignonProfile></SignonRq><PaySvcRq><RqUID>");
        builder.append(payment_Request.getREQUEST_ID());
        builder.append("</RqUID><AsyncRqUID>");
        builder.append(payment_Request.getASYNC_REQUEST_ID());
        builder.append("</AsyncRqUID><MsgRqHdr><NetworkTrnInfo><OriginatorCode>");
        builder.append(payment_Request.getORIGINATOR_CODE());
        builder.append("</OriginatorCode><TerminalId>");
        builder.append(payment_Request.getTERMINAL_ID());
        builder.append("</TerminalId></NetworkTrnInfo><ClientTerminalSeqId>");
        builder.append(payment_Request.getCLIENT_TERMINAL_SEQ_ID());
        builder.append("</ClientTerminalSeqId>");
        for (Customers_Properties customers_Properties : payment_Request.getCustomers_Properties()) {
            builder.append("<CustomProperties><CustomProperty><Key>");
            builder.append(customers_Properties.getKEY());
            builder.append("</Key><Value>");
            builder.append(customers_Properties.getVALUE());
            builder.append("</Value></CustomProperty></CustomProperties>");
        }
        builder.append("</MsgRqHdr><PmtAddRq><PmtInfo><BillingAcct>");
        builder.append(payment_Request.getBILLING_ACCOUNT());
        builder.append("</BillingAcct><BillRefNumber>");
        builder.append(payment_Request.getBILL_REF_NUMBER());
        builder.append("</BillRefNumber><NotifyMobile>");
        builder.append(payment_Request.getNOTIFY_MOBILE());
        builder.append("</NotifyMobile><BillerId>");
        builder.append("1");
        builder.append("</BillerId><BillTypeCode>");
        builder.append(payment_Request.getBILL_TYPE_CODE());
        builder.append("</BillTypeCode><PmtType>");
        builder.append(payment_Request.getPMT_TYPE());
        builder.append("</PmtType><DeliveryMethod>");
        builder.append(payment_Request.getDELIVERY_METHOD());
        builder.append("</DeliveryMethod><CurAmt><Amt>");
        builder.append(payment_Request.getAMOUNT());
        builder.append("</Amt></CurAmt><DepAccIdFrom><AcctId>");
        builder.append(payment_Request.getACCT_ID());
        builder.append("</AcctId><AcctType>");
        builder.append(payment_Request.getACCT_TYPE());
        builder.append("</AcctType><AcctKey>");
        builder.append(payment_Request.getACCT_KEY());
        builder.append("</AcctKey><AcctCur>");
        builder.append(payment_Request.getACCT_CUR());
        builder.append("</AcctCur></DepAccIdFrom><PmtMethod>");
        builder.append(payment_Request.getPMT_METHOD());
        builder.append("</PmtMethod><PrcDt>");
        builder.append(payment_Request.getPRC_DATE());
        builder.append("</PrcDt></PmtInfo></PmtAddRq></PaySvcRq></Request></arg0></ejb:process></soapenv:Body></soapenv:Envelope>");
//        builder.append("</PASSWORD><EXTCODE></EXTCODE><EXTREFNUM></EXTREFNUM><LANGUAGE1>0</LANGUAGE1></COMMAND>");
        MasaryManager.logger.info(builder.toString());
        return builder.toString();
    }

    public String LogPostpayed_Payment_Res(Masary_Payment_Response masary_Payment_Response) {
//need to know type
        MasaryManager.logger.info("Bill Payment Response For " + masary_Payment_Response.getBILLING_ACCOUNT() + " Service " + masary_Payment_Response.getBILL_TYPE_CODE());
        StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?><soapenv:Envelopexmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns3:processResponse xmlns:ns2=\"http://www.fawry-eg.com/ebpp/IFXMessages/\" xmlns:ns3=\"http://ejb.gateway.ebpp.fawryis.com/\"><return><Response><SignonRs><ClientDt>");
        builder.append(masary_Payment_Response.getRESPONSE_DATE());
        builder.append("</ClientDt><CustLangPref>");
        builder.append("AR-EG");
        builder.append("</CustLangPref><ServerDt>");
        builder.append(masary_Payment_Response.getSERVER_DATE());
        builder.append("</ServerDt><Language>en-gb</Language><SignonProfile><Sender>");
        Masary_signon_profile masary_signon_profile = masary_Payment_Response.getMasary_signon_profile();
        builder.append(masary_signon_profile.getSENDER());
        builder.append("</Sender><Receiver>");
        builder.append(masary_signon_profile.getRECIEVER());
        builder.append("</Receiver><MsgCode>");
        builder.append(masary_Payment_Response.getMESSAGE_CODE());
        builder.append("</MsgCode><Version>");
        builder.append(masary_Payment_Response.getMasary_signon_profile().getVERSION());
        builder.append("</Version></SignonProfile></SignonRs><PaySvcRs><RqUID>");
        builder.append(masary_Payment_Response.getREQUEST_ID());
        builder.append("</RqUID><AsyncRqUID>");
        builder.append(masary_Payment_Response.getASYNC_REQUEST_ID());
        builder.append("</AsyncRqUID><MsgRqHdr><NetworkTrnInfo><OriginatorCode>");
        builder.append(masary_Payment_Response.getORIGINATOR_CODE());
        builder.append("</OriginatorCode><TerminalId>");
        builder.append(masary_Payment_Response.getTERMINAL_ID());
        builder.append("</TerminalId></NetworkTrnInfo><ClientTerminalSeqId>");
        builder.append(masary_Payment_Response.getCLIENT_TERMINAL_SEQ_ID());
        builder.append("</ClientTerminalSeqId>");
        for (Customers_Properties customers_Properties : masary_Payment_Response.getCustomers_Properties()) {
            builder.append("<CustomProperties><CustomProperty><Key>");
            builder.append(customers_Properties.getKEY());
            builder.append("</Key><Value>");
            builder.append(customers_Properties.getVALUE());
            builder.append("</Value></CustomProperty></CustomProperties>");
        }
        builder.append("</MsgRqHdr><Status><StatusCode>");
        builder.append(masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE());
        builder.append("</StatusCode><Severity>");
        builder.append(masary_Payment_Response.getMasary_Bill_Status().getSEVERITY());
        builder.append("</Severity><StatusDesc>");
        builder.append(masary_Payment_Response.getMasary_Bill_Status().getSTATUS_DESC());
        builder.append("</StatusDesc></Status>");
        if (masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE().equals("200")) {
            builder.append("<PmtAddRs><PmtInfoVal><status><StatusCode>");
            builder.append(masary_Payment_Response.getMasary_Bill_Status().getSTATUS_CODE());
            builder.append("</StatusCode><Severity>");
            builder.append(masary_Payment_Response.getMasary_Bill_Status().getSEVERITY());
            builder.append("</Severity><StatusDesc>");
            builder.append(masary_Payment_Response.getMasary_Bill_Status().getSTATUS_DESC());
            builder.append("</StatusDesc></status>");
            for (Masary_Payment_Type masary_Payment_Type : masary_Payment_Response.getMasary_Payment_Types()) {
                builder.append("<PmtTransId><PmtId>");
                builder.append(masary_Payment_Type.getPAYMENT_ID());
                builder.append("</PmtId><PmtIdType>");
                builder.append(masary_Payment_Type.getPAYMENT_ID_TYPE());
                builder.append("</PmtIdType><CreatedDt>");
                builder.append(masary_Payment_Type.getCREATED_DATE());
                builder.append("</CreatedDt></PmtTransId>");
            }
            builder.append("<PmtInfo><BillingAcct>");
            builder.append(masary_Payment_Response.getBILLING_ACCOUNT());
            builder.append("</BillingAcct><BillRefNumber>");
            builder.append(masary_Payment_Response.getBILL_REF_NUMBER());
            builder.append("</BillRefNumber><NotifyMobile>");
            builder.append(masary_Payment_Response.getNOTIFY_MOBILE());
            builder.append("</NotifyMobile><BillerId>");
            builder.append("1</BillerId><BillTypeCode>");
            builder.append(masary_Payment_Response.getBILL_TYPE_CODE());
            builder.append("</BillTypeCode><PmtType>");
            builder.append(masary_Payment_Response.getPAYMENT_TYPE());
            builder.append("</PmtType><DeliveryMethod>");
            builder.append(masary_Payment_Response.getDELIVERY_METHOD());
            builder.append("</DeliveryMethod><CurAmt><Amt>");
            builder.append(masary_Payment_Response.getCURRENT_AMOUNT());
            builder.append("</Amt><CurCode>EGP</CurCode></CurAmt><FeesAmt><Amt>");
            builder.append(masary_Payment_Response.getFEES_AMOUNT());
            builder.append("</Amt><CurCode>EGP</CurCode></FeesAmt><DepAccIdFrom><AcctId>");
            builder.append(masary_Payment_Response.getACCOUNT_ID());
            builder.append("</AcctId><AcctType>");
            builder.append(masary_Payment_Response.getACCOUNT_TYPE());
            builder.append("</AcctType><AcctKey>");
            builder.append(masary_Payment_Response.getACCOUNT_KEY());
            builder.append("</AcctKey><AcctCur>");
            builder.append(masary_Payment_Response.getACCOUNT_CUR());
            builder.append("</AcctCur></DepAccIdFrom><PmtMethod>");
            builder.append(masary_Payment_Response.getPAYMENT_METHOD());
            builder.append("</PmtMethod><PrcDt>");
            builder.append(masary_Payment_Response.getPRC_DATE());
            builder.append("</PrcDt></PmtInfo></PmtInfoVal></PmtAddRs>");
        }
        builder.append("</PaySvcRs></Response></return></ns3:processResponse></soapenv:Body></soapenv:Envelope>");
        MasaryManager.logger.info(builder.toString());
        return builder.toString();
    }

    public String LogBalance_Inq_Req() {
//need to know type
        MasaryManager.logger.info("Bill Balance Request");
        StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pos=\"http://www.fawryis.com/POSBalanceInquiry/\"><soapenv:Header/><soapenv:Body><pos:inquire><posCode><code>88998</code><password>1234</password></posCode></pos:inquire></soapenv:Body></soapenv:Envelope>");
        MasaryManager.logger.info(builder.toString());
        return builder.toString();
    }

    public String LogBalance_Inq_Res(Masary_Bill_Balance balance) {
//need to know type
        MasaryManager.logger.info("Bill Balance Response");
        MasaryManager.logger.info("Bill Balance Is " + balance.getBalance());
        StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns2:inquireResponse xmlns:ns2=\"http://www.fawryis.com/POSBalanceInquiry/\"><out><Code>");
        builder.append(balance.getCode());
        builder.append("</Code><Balance>");
        builder.append(balance.getBalance());
        builder.append("</Balance><Status><StatusCode>");
        builder.append(balance.getStatusCode());
        builder.append("</StatusCode><StatusDesc>");
        builder.append(balance.getStatusDesc());
        builder.append("</StatusDesc></Status></out></ns2:inquireResponse></soapenv:Body></soapenv:Envelope>");
        MasaryManager.logger.info(builder.toString());
        return builder.toString();
    }

    public void LogBalanceSheet_Inq_Req(String PosCode, String Pin, String SerialNumber, String SheetDt) {
//need to know type
        MasaryManager.logger.info("Bill Balance Sheet Request");
        StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pos=\"http://www.fawryis.com/POSBalanceInquiry/\"><soapenv:Header/><soapenv:Body><pos:getBalanceSheet><in><Terminal><PosCode>");
        builder.append(PosCode);
        builder.append("</PosCode><Pin>");
        builder.append(Pin);
        builder.append("</Pin><SerialNumber>");
        builder.append(SerialNumber);
        builder.append("</SerialNumber></Terminal><SheetDt>");
        builder.append(SheetDt);
        builder.append("</SheetDt></in></pos:getBalanceSheet></soapenv:Body></soapenv:Envelope>");
        MasaryManager.logger.info(builder.toString());
//        return builder.toString();
    }

    public void LogBalanceSheet_Inq_Res(Masary_Balance_Sheet balance_Sheet) {
//need to know type
        MasaryManager.logger.info("Bill Balance Sheet Response");
        StringBuilder builder = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns2:getBalanceSheetResponse xmlns:ns2=\"http://www.fawryis.com/POSBalanceInquiry/\"><out><BalanceSheet>");
        if (balance_Sheet.getSTATUSCODE().equals("200")) {
            builder.append("<OpeningBalance>");
            builder.append(balance_Sheet.getOPENING_BALANCE());
            builder.append("</OpeningBalance><ClosingBalance>");
            builder.append(balance_Sheet.getCLOSEING_BALANCE());
            builder.append("</ClosingBalance>");
            for (Masary_Balance_Sheet_Detailes detailes : balance_Sheet.getMasary_Balance_Sheet_Detailes()) {
                builder.append("<BalanceDetails><ActionTypeCode>");
                builder.append(detailes.getACTION_TYPE_CODE());
                builder.append("</ActionTypeCode><ActionNature>");
                builder.append(detailes.getACTION_NATURE());
                builder.append("</ActionNature><Amount>");
                builder.append(detailes.getAMOUNT());
                builder.append("</Amount></BalanceDetails>");
            }
        }
        builder.append("</BalanceSheet><Status><StatusCode>");
        builder.append(balance_Sheet.getSTATUSCODE());
        builder.append("</StatusCode><StatusDesc>");
        builder.append(balance_Sheet.getSTATUSDESC());
        builder.append("</StatusDesc></Status></out></ns2:getBalanceSheetResponse></soapenv:Body></soapenv:Envelope>");
        MasaryManager.logger.info(builder.toString());
//        return builder.toString();
    }

    public String getReqId() {
        String Req_Id = "";
        Req_Id = Long.toHexString(Double.doubleToLongBits(Math.random())) + "-" + Long.toHexString(Double.doubleToLongBits(Math.random())) + "-" + Long.toHexString(Double.doubleToLongBits(Math.random()));
        return Req_Id;
    }

    public String getReqIdBiller() {
        String Req_Id = "";
        Random rand = new Random();
//Req_Id=Long.toHexString(Double.doubleToLongBits(Math.random())).toString().substring(0,8)+"-"+rand.nextInt(10000)+"-"+rand.nextInt(10000)+"-"+rand.nextInt(10000)+"-"+Long.toHexString(Double.doubleToLongBits(Math.random())).toString().substring(0,12);
        Req_Id = Long.toHexString(Double.doubleToLongBits(Math.random())).toString().substring(0, 8) + "-" + Long.toHexString(Double.doubleToLongBits(Math.random())).toString().substring(0, 4) + "-" + Long.toHexString(Double.doubleToLongBits(Math.random())).toString().substring(0, 4) + "-" + Long.toHexString(Double.doubleToLongBits(Math.random())).toString().substring(0, 4) + "-" + Long.toHexString(Double.doubleToLongBits(Math.random())).toString().substring(0, 12);
        return Req_Id;
    }
}
