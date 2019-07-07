<%-- 
    Document   : bill_payment_confirmation
    Created on : 01/05/2012, 07:12:20 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Payment_Response"%>
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
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(String.valueOf(request.getSession().getAttribute("serv_id").toString()));
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
%>
<%
    Masary_Payment_Response Payment_Response = new Masary_Payment_Response();


    String amount = "";
    String serviceFee = "";
    String totalAmount = "";
    String date = "";
    String time = "";
    try {
        Payment_Response = (Masary_Payment_Response) session.getAttribute("payment_Info");
        amount = Double.toString(Payment_Response.getCURRENT_AMOUNT());
        serviceFee = Double.toString(Payment_Response.getFEES_AMOUNT());
        totalAmount = Double.toString(Payment_Response.getFEES_AMOUNT() + Payment_Response.getCURRENT_AMOUNT());
        date = Payment_Response.getSERVER_DATE().substring(0, 10);
        time = Payment_Response.getSERVER_DATE().substring(11, 16);
    } catch (Exception e) {
        // //System.out.println(e);
    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <form name="BillTransactions" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST">
        <th><H3 style="color: red; font-size: 18px"><%=CONFIG.getBillRecieptMess1(session)%><%=request.getSession().getAttribute("transaction_id").toString()%><%=CONFIG.getBillRecieptMess2(session)%><br><%=CONFIG.getBillRecieptMess3(session)%><%=request.getSession().getAttribute("Provider_Payment_ID").toString()%></H3></th>
        <br><br>
        <%String[] customerName = Payment_Response.getCustomerName().trim().split(" ");
            String name = customerName[0] + " " + customerName[1] + " " + customerName[2];
        %>
        <applet align="center" archive="applet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="390">
            <param name="termID" value="<%=agent.getPin()%>"/>
            <param name="type" value="<%=BTC.getStrBTCName(session)%>"/>
            <param name="date" value="<%=date%>"/>
            <param name="time" value="<%=time%>"/>
            <param name="txNO" value="<%=request.getSession().getAttribute("transaction_id").toString()%>"/>
            <param name="bT" value="<%=BTC.getBILLER_ID()%>"/>
            <param name="bTC" value="<%=request.getSession().getAttribute("serv_id").toString()%>"/>
            <param name="customerName" value="<%=name%>"/>
            <param name="billNO" value="<%=Payment_Response.getBILLING_ACCOUNT()%>"/>
            <param name="amount" value="<%=amount%>"/>
            <param name="serviceFee" value="<%=serviceFee%>"/>
            <param name="totalAmount" value="<%=totalAmount%>"/>
            <param name="dueDate" value="<%=date%>"/>                   
        </applet>
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
