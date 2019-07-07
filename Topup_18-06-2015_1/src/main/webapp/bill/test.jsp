<%-- 
    Document   : test
    Created on : 17/11/2013, 06:43:35 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.Request"%>
<%@page import="com.masary.services.providers.efinance"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <%efinance e = new efinance();
                Request BillInquiry_Req = new Request();
                String Message = "<EFBPS>\n"
                        + "    <SignonRq>\n"
                        + "        <ClientDt>2013-11-17T14:48:16</ClientDt>\n"
                        + "        <LanguagePref>ar-eg</LanguagePref>\n"
                        + "        <SignonProfile>\n"
                        + "            <Sender>0001</Sender>\n"
                        + "            <Receiver>EPAY</Receiver>\n"
                        + "            <MsgCode>RBINQRQ</MsgCode>\n"
                        + "        </SignonProfile>\n"
                        + "    </SignonRq>\n"
                        + "    <BankSvcRq>\n"
                        + "        <RqUID>1311170026846</RqUID>\n"
                        + "        <BillInqRq>\n"
                        + "            <BankId>0010</BankId>\n"
                        + "            <AccessChannel>ATM</AccessChannel>\n"
                        + "            <AccountId>\n"
                        + "                <BillingAcct>1214120130100181</BillingAcct>\n"
                        + "                <BillerId>99001</BillerId>\n"
                        + "            </AccountId>\n"
                        + "            <ServiceType>CAU</ServiceType>\n"
                        + "            <IncPaidBills>false</IncPaidBills>\n"
                        + "        </BillInqRq>\n"
                        + "    </BankSvcRq>\n"
                        + "</EFBPS>";
                BillInquiry_Req.setMessage(Message);
                BillInquiry_Req.setSenderID("0001");
            %>
            <%=e.getSignature(BillInquiry_Req)%>
        </h1>
    </body>
</html>
