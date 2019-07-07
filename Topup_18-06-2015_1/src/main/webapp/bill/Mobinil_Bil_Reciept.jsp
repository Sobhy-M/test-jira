<%--
    Document   : Mobinil_Bil_Reciept
    Created on : 12/09/2012, 11:56:33 ص
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.Masary_Bill_Receipt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Payment_Response"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    String custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    session = request.getSession();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    Masary_Bill_Receipt Reciept = MasaryManager.getInstance().get_Mobinil_receipt(Integer.parseInt(request.getSession().getAttribute("transaction_id").toString()),
            Integer.parseInt(request.getSession().getAttribute("serv_id").toString()));
    String date = "";
    String time = "";
    try {
        date = Reciept.getTransaction_date().substring(0, 10);
        time = Reciept.getTransaction_date().substring(11, 16);
    } catch (Exception e) {
    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reciept</title>
    </head>
    <body class="body" >
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <form name="BillTransactions" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST">
        <%if (request.getParameter("secondPrinted") == null) {%>
        <th><H3 style="color: red; font-size: 18px"><%=CONFIG.getBillRecieptMess(session)%></H3></th>
        <br>
        <%}%>
        <div align="ceneter"><H3 style="color: red; font-size: 18px" ><%=CONFIG.getPaymentInstruction(session)%></H3></div>
        <br>

        <applet align="center" archive="BillApplet.jar" code="<%=request.getSession().getAttribute(CONFIG.lang).equals("") ? "eng" : "arab"%>Template" width="175" height="450">
            <param name="billType" value="<%=request.getSession().getAttribute("serv_id").toString().equals("112") ? "mob" : request.getSession().getAttribute("serv_id").toString().equals("111") ? "vod" : ""%>"/>
            <param name="termID" value="<%=agent.getPin()%>"/>
            <param name="date" value="<%=date%>"/>
            <param name="time" value="<%=time%>"/>
            <param name="txNO" value="<%=Reciept.getTransaction_id()%>"/>
            <param name="bTC" value="<%=Reciept.getBTC()%>"/>
            <param name="billNO" value="<%=Reciept.getPhone()%>"/>
            <param name="amount" value="<%=Reciept.getAmount()%>"/>
            <param name="serviceFee" value="<%=Reciept.getFees()%>"/>
            <param name="totalAmount" value="<%=Reciept.getAmount() + Reciept.getFees()%>"/>
        </applet>
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
