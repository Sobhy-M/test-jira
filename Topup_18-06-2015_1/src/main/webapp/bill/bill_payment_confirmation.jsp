<%-- 
    Document   : bill_payment_confirmation
    Created on : 01/05/2012, 07:12:20 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Payment_Response"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(String.valueOf(request.getSession().getAttribute("serv_id").toString()));
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    Bill_Response billResonse = (Bill_Response) request.getSession().getAttribute("bill_Response");
    String BillDate = "";
    if (BTC.getBILL_TYPE_ID() != 112 && BTC.getBILL_TYPE_ID() != 111 ) {
        BillDate = billResonse.getBILL_DATE().substring(0, 4) + "-" + billResonse.getBILL_DATE().substring(4);
    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
    </head>
    <body class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <form name="BillTransactions" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST">
        <%
            if (Integer.parseInt(request.getSession().getAttribute("serv_id").toString()) != 124) {
        %>
        <th><H3 style="color: red; font-size: 18px"><%=CONFIG.getBillRecieptMess1(session)%>${bill_Response.TRANSACTION_ID}<%=CONFIG.getBillRecieptMess2(session)%><br><%=CONFIG.getBillRecieptMess3(session)%>${bill_Response.PROVIDER_TRANSACTION_ID}</H3></th>
                <%} else if (billResonse.getSTATUS_CODE() == 201) {%>
        <th><H3 style="color: red; font-size: 18px"><%=CONFIG.getBillRecieptMess4(session)%>${bill_Response.TRANSACTION_ID}<%=CONFIG.getBillRecieptMess2(session)%><br><%=CONFIG.getBillRecieptMess3(session)%>${bill_Response.PROVIDER_TRANSACTION_ID}</H3></th>
                <%} else {
                %>
        <th><H3 style="color: red; font-size: 18px"><%=CONFIG.getBillRecieptMess1(session)%>${bill_Response.TRANSACTION_ID}<%=CONFIG.getBillRecieptMess2(session)%><br><%=CONFIG.getBillRecieptMess3(session)%>${bill_Response.PROVIDER_TRANSACTION_ID}</H3></th>
                <%
                    }
                %>
        <br><br>
	
	<%
            if(request.getSession().getAttribute("serv_id").toString().equals("124")){
        %>
	
        <applet align="center" archive="telecomegyptapplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
            <param name="termID" value="${bill_Response.BILLING_ACCOUNT}"/>
            <param name="STATUS" value="${bill_Response.STATUS_MESSAGE}"/>           
            <param name="type" value="<%=BTC.getStrBTCName(session)%>"/>
            <param name="date" value="<%=date1%>"/>
            <param name="time" value="<%=time%>"/>
            <param name="txNO" value="${bill_Response.TRANSACTION_ID}"/>
            <param name="bT" value="<%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%>"/>
            <param name="bTC" value="${serv_id}"/>
            <param name="customerName" value="${bill_Response.CUSTOMER_NAME}"/>
            <param name="billNO" value="${bill_Response.BILLING_ACCOUNT}"/>
            <param name="amount" value="${bill_Response.AMOUNT}"/>
            <param name="serviceFee" value="${Fees}"/>
            <param name="totalAmount" value="${bill_Response.AMOUNT + Fees}"/>
	    <param name="dueDate" value="<%=BillDate%>"/>
	</applet>
	
	<%
           } else if (request.getSession().getAttribute("serv_id").toString().equals("117")){
        %>
        <applet align="center" archive="TEDataapplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
            <param name="termID" value="${bill_Response.BILLING_ACCOUNT}"/>
            <param name="STATUS" value="${bill_Response.STATUS_MESSAGE}"/>           
            <param name="type" value="<%=BTC.getStrBTCName(session)%>"/>
            <param name="date" value="<%=date1%>"/>
            <param name="time" value="<%=time%>"/>
            <param name="txNO" value="${bill_Response.TRANSACTION_ID}"/>
            <param name="bT" value="<%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%>"/>
            <param name="bTC" value="${serv_id}"/>
            <param name="customerName" value="${bill_Response.CUSTOMER_NAME}"/>
            <param name="refnum" value="${bill_Response.PROVIDER_TRANSACTION_ID}"/>
            <param name="amount" value="${bill_Response.AMOUNT}"/>
            <param name="serviceFee" value="${Fees}"/>
            <param name="totalAmount" value="${bill_Response.AMOUNT + Fees}"/>
            <param name="dueDate" value="${bill_Response.BILL_DATE}"/>                 
        </applet>
        
        <%
        } else if (request.getSession().getAttribute("serv_id").toString().equals("111")) {
        %>
         <applet align="center" archive="VodafoneBillapplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
            <param name="termID" value="${bill_Response.BILLING_ACCOUNT}"/>
            <param name="STATUS" value="${bill_Response.STATUS_MESSAGE}"/>           
            <param name="type" value="<%=BTC.getStrBTCName(session)%>"/>
            <param name="date" value="<%=date1%>"/>
            <param name="time" value="<%=time%>"/>
            <param name="txNO" value="${bill_Response.TRANSACTION_ID}"/>
            <param name="bT" value="<%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%>"/>
            <param name="bTC" value="${serv_id}"/>
            <param name="customerName" value="${bill_Response.CUSTOMER_NAME}"/>
            <param name="refnum" value="${bill_Response.PROVIDER_TRANSACTION_ID}"/>
            <param name="amount" value="${bill_Response.AMOUNT}"/>
            <param name="serviceFee" value="${Fees}"/>
            <param name="totalAmount" value="${bill_Response.AMOUNT + Fees}"/>
            <param name="dueDate" value="${bill_Response.BILL_DATE}"/>                 
        </applet>
       <%}
        
         else if (request.getSession().getAttribute("serv_id").toString().equals("112")) {
        %>
         <applet align="center" archive="MobinilBillapplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
            <param name="termID" value="${bill_Response.BILLING_ACCOUNT}"/>
            <param name="STATUS" value="عملية ناجحة"/>           
            <param name="type" value="<%=BTC.getStrBTCName(session)%>"/>
            <param name="date" value="<%=date1%>"/>
            <param name="time" value="<%=time%>"/>
            <param name="txNO" value="${bill_Response.TRANSACTION_ID}"/>
            <param name="bT" value="<%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%>"/>
            <param name="bTC" value="${serv_id}"/>
            <param name="amount" value="${bill_Response.AMOUNT}"/>
            <param name="serviceFee" value="${Fees}"/>
            <param name="totalAmount" value="${bill_Response.AMOUNT + Fees}"/>                 
        </applet>
       
        <%
        } else {
        %>  
        
        <applet align="center" archive="BillApplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="175" height="450">
            <param name="billType" value="<%=request.getSession().getAttribute("serv_id").toString().equals("112") ? "mob" : request.getSession().getAttribute("serv_id").toString().equals("111") ? "vod" : ""%>"/>
            <param name="termID" value="<%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%>"/>
            <param name="date" value="<%=date%>"/>
            <param name="time" value="<%=time%>"/>
            <param name="txNO" value="${bill_Response.TRANSACTION_ID}"/>
            <param name="bTC" value="${serv_id}"/>
            <param name="billNO" value="${bill_Response.BILLING_ACCOUNT}"/>
            <param name="amount" value="${bill_Response.AMOUNT}"/>
            <param name="serviceFee" value="${Fees}"/>
            <param name="totalAmount" value="${bill_Response.AMOUNT + Fees}"/>
        </applet>     
            <%
           }
        %>
        
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
