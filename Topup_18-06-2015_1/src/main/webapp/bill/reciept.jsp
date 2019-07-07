<%-- 
    Document   : bill_payment_confirmation
    Created on : 01/05/2012, 07:12:20 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.Bill_Provider_Response"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Bill_Receipt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    MasaryManager manager = new MasaryManager();
%>
<%

    session = request.getSession();
    String custId = null;
    if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }

    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = manager.getAgent(custId);
    Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
//    receipt = manager.getreceipt(Integer.parseInt(request.getSession().getAttribute("transaction_id").toString()));
//    receipt = manager.getVodafone_Bill_Receipt(Integer.parseInt(request.getSession().getAttribute("transaction_id").toString()));
    receipt = (Masary_Bill_Receipt) session.getAttribute("receipt");
    Masary_Bill_Type BTC = manager.getBTC(String.valueOf(receipt.getBTC()));

%>
<%
    String date = "";
    String time = "";
    try {
        date = receipt.getTransaction_date().substring(0, 10);
        time = receipt.getTransaction_date().substring(11, 16);
    } catch (Exception e) {
        ////System.out.println(e);
    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getREPRINT(session)%></title>
    </head>
    <body class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <div id="menu">
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <div align="menu">
            <form name="BillTransactions" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" >
                <br><br>
                <% receipt.setCustomerName(receipt.getCustomerName() == null ? "Not Avaliable" : receipt.getCustomerName());
                    String[] customerName = receipt.getCustomerName().trim().split(" ");
                    //System.out.println(receipt.getCustomerName().trim());
                    String name = "";
                    if (BTC.getBILLER_ID() == 113 || BTC.getBILLER_ID() == 117 || BTC.getBILLER_ID() == 124) {
                        if (customerName.length == 3) {
                            name = customerName[0] + " " + customerName[1] + " " + customerName[2];
                        } else {
                            name = customerName[0] + " " + customerName[1];
                        }
                    }
                %>
                <%
                    if (receipt.getBTC() == 117) {
                %>
                <applet align="center" archive="TEDataapplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
                    <param name="termID" value="<%=receipt.getPhone()%>"/>
                    <param name="STATUS" value="عملية ناجحه"/>           
                    <param name="type" value="<%=BTC.getStrBTCName(session)%>"/>
                    <param name="date" value="<%=date%>"/>
                    <param name="time" value="<%=time%>"/>
                    <param name="txNO" value="<%=receipt.getTransaction_id()%>"/>
                    <param name="bT" value="<%=agent.getPin()%>"/>
                    <param name="bTC" value="<%=receipt.getBTC()%>"/>
                    <param name="customerName" value="<%=receipt.getCustomerName()%>"/>
                    <param name="refnum" value="<%=receipt.getProviderResponse().getPROVIDER_TRANSACTION_ID()%>"/>
                    <param name="amount" value="<%=receipt.getAmount()%>"/>
                    <param name="serviceFee" value="<%=receipt.getFees()%>"/>
                    <param name="totalAmount" value="<%= receipt.getAmount() + receipt.getFees()%>"/>
                    <param name="dueDate" value="<%=receipt.getDue_date()%>"/>                 
                </applet>
                <%
                } else if (receipt.getBTC() == 111) {
                %>
                <applet align="center" archive="VodafoneBillapplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
                    <param name="termID" value="<%=receipt.getPhone()%>"/>
                    <param name="STATUS" value="عملية ناجحه"/>           
                    <param name="type" value="<%=BTC.getStrBTCName(session)%>"/>
                    <param name="date" value="<%=date%>"/>
                    <param name="time" value="<%=time%>"/>
                    <param name="txNO" value="<%=receipt.getTransaction_id()%>"/>
                    <param name="bT" value="<%=agent.getPin()%>"/>
                    <param name="bTC" value="<%=receipt.getBTC()%>"/>
                    <param name="customerName" value="<%=receipt.getCustomerName()%>"/>
                    <param name="refnum" value="<%=receipt.getProviderResponse().getPROVIDER_TRANSACTION_ID() %>"/>
                    <param name="amount" value="<%=receipt.getAmount()%>"/>
                    <param name="serviceFee" value="<%=receipt.getFees()%>"/>
                    <param name="totalAmount" value="<%= receipt.getAmount() + receipt.getFees()%>"/>
                    <param name="dueDate" value="<%=receipt.getDue_date()%>"/>                 
                </applet>
                <%
                } else if (receipt.getBTC() == 124) {
                %>
                <applet align="center" archive="telecomegyptapplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
                    <param name="termID" value="<%=receipt.getPhone()%>"/>
                    <param name="STATUS" value="عملية ناجحه"/>           
                    <param name="type" value="<%=BTC.getStrBTCName(session)%>"/>
                    <param name="date" value="<%=date%>"/>
                    <param name="time" value="<%=time%>"/>
                    <param name="txNO" value="<%=receipt.getTransaction_id()%>"/>
                    <param name="bT" value="<%=agent.getPin()%>"/>
                    <param name="bTC" value="<%=receipt.getBTC()%>"/>
                    <param name="customerName" value="<%=receipt.getCustomerName()%>"/>
                    <param name="billNO" value="<%=receipt.getPhone()%>"/>
                    <param name="amount" value="<%=receipt.getAmount()%>"/>
                    <param name="serviceFee" value="<%=receipt.getFees()%>"/>
                    <param name="totalAmount" value="<%= receipt.getAmount() + receipt.getFees()%>"/>
                    <param name="dueDate" value="<%=receipt.getProviderResponse().getBILL_DATE()%>"/>                 
                </applet>
                <%
                } else if (receipt.getBTC() == 125) {
                %>
                <applet align="center" archive="TelecomReshargeApplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
                    <param name="termID" value="<%=receipt.getPhone()%>"/>
                    <param name="STATUS" value="عملية ناجحة"/>           
                    <param name="date" value="<%=date%>"/>
                    <param name="time" value="<%=time%>"/>
                    <param name="txNO" value="<%=receipt.getTransaction_id()%>"/>
                    <param name="bT" value="<%=agent.getPin()%>"/>
                    <param name="bTC" value="<%=receipt.getBTC()%>"/>
                    <param name="customerName" value="<%=receipt.getCustomerName()%>"/>
                    <param name="MONEY_PAID" value="<%=receipt.getAmount()%>"/>
                    <param name="serviceFee" value="<%=receipt.getFees()%>"/>
                    <param name="totalAmount" value="<%= receipt.getAmount() + receipt.getFees()%>"/>
                    <param name="QUOTA" value="<%=receipt.getDue_date()%>"/>                 
                </applet>
                <%
                } else if (receipt.getBTC() == 112) {
                %>
                <applet align="center" archive="MobinilBillapplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
                    <param name="termID" value="<%=receipt.getPhone()%>"/>
                    <param name="STATUS" value="عملية ناجحة"/>           
                    <param name="date" value="<%=date%>"/>
                    <param name="time" value="<%=time%>"/>
                    <param name="txNO" value="<%=receipt.getTransaction_id()%>"/>
                    <param name="bT" value="<%=agent.getPin()%>"/>
                    <param name="bTC" value="<%=receipt.getBTC()%>"/>
                    <param name="MONEY_PAID" value="<%=receipt.getAmount()%>"/>
                    <param name="serviceFee" value="<%=receipt.getFees()%>"/>
                    <param name="totalAmount" value="<%= receipt.getAmount() + receipt.getFees()%>"/>                
                </applet>
                 <%
                } else if (receipt.getBTC() == 610) {
                %>
                <applet align="center" archive="MobinilBillapplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
                    <param name="termID" value="<%=receipt.getPhone()%>"/>
                    <param name="STATUS" value="عملية ناجحة"/>           
                    <param name="date" value="<%=date%>"/>
                    <param name="time" value="<%=time%>"/>
                    <param name="txNO" value="<%=receipt.getTransaction_id()%>"/>
                    <param name="bT" value="<%=agent.getPin()%>"/>
                    <param name="bTC" value="<%=receipt.getBTC()%>"/>
                    <param name="MONEY_PAID" value="<%=receipt.getAmount()%>"/>
                    <param name="serviceFee" value="<%=receipt.getFees()%>"/>
                    <param name="totalAmount" value="<%= receipt.getAmount() + receipt.getFees()%>"/>                
                </applet>
                
                <%
                } else {
                %>
                <applet align="center" archive="applet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="182" height="450">
                    <param name="termID" value="<%=agent.getPin()%>"/>
                    <param name="type" value="<%=BTC.getStrBTCName(session)%>"/>
                    <param name="date" value="<%=date%>"/>
                    <param name="time" value="<%=time%>"/>
                    <param name="bT" value="<%=BTC.getBILLER_ID()%>"/>
                    <param name="txNO" value="<%=receipt.getTransaction_id()%>"/>
                    <param name="bTC" value="<%=receipt.getBTC()%>"/>
                    <param name="customerName" value="<%=name%>"/>
                    <param name="billNO" value="<%=receipt.getPhone()%>"/>
                    <param name="amount" value="<%=receipt.getAmount()%>"/>
                    <param name="serviceFee" value="<%=receipt.getFees()%>"/>
                    <param name="totalAmount" value="<%=receipt.getAmount() + receipt.getFees()%>"/>
                    <param name="dueDate" value="<%=receipt.getDue_date().substring(0, 10)%>"/>                   
                </applet>     
                <%
                }
                %>

            </form>
        </div><!-- End content body -->

        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
        <!-- End of Main body-->
    </body>
</html>
