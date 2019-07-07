<%-- 
    Document   : CashUResult
    Created on : 01/05/2012, 07:12:20 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.CashUResponse"%>
<%@page import="org.omg.PortableInterceptor.INACTIVE"%>
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

    String custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);


    AgentDTO agent = MasaryManager.getInstance().getAgent(custId);
//    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(String.valueOf(request.getSession().getAttribute("serv_id").toString()));
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);


    CashUResponse cashUResonse = (CashUResponse) request.getSession().getAttribute("cashUResponse");

%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <form name="BillTransactions" action="<%=CONFIG.APP_ROOT%>CashUTopUpController" method="POST">


        <th><H3 style="color: red; font-size: 18px"><%=CONFIG.getBillRecieptMess4(session)%><%=cashUResonse.getTRANSACTION_ID() %><%=CONFIG.getBillRecieptMess2(session)%><br><%=CONFIG.getBillRecieptMess3(session)%><%=cashUResonse.getCashUTrnID() %></H3></th>

        <br><br>

        <applet align="center" archive="CashUApplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="185" height="430">
            <param name="termID" value="<%=cashUResonse.getUserId() %>"/>
            <param name="STATUS" value="عملية ناجحة"/>           
            <param name="date" value="<%=date1%>"/>
            <param name="time" value="<%=time%>"/>
            <param name="txNO" value="<%=cashUResonse.getTRANSACTION_ID()%>"/>
            <!--<param name="bT" value="" />-->
            <param name="bTC" value="${serv_id}"/>
            <param name="customerName" value="<%= cashUResonse.getHolderName() %>"/>
            <param name="AMOUNT" value="<%= request.getParameter(CONFIG.AMOUNT)%>"/>
            <param name="serviceFee" value="<%= cashUResonse.getFEES()%>"/>
            <param name="totalAmount" value="<%= Integer.parseInt(request.getParameter(CONFIG.AMOUNT)) + (int)cashUResonse.getFEES() %>"/>                
        </applet>
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
