<%@page import="com.google.gson.Gson"%>
<%@page import="com.masary.database.dto.Vodafone_Cash_Receipt"%>
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
    Vodafone_Cash_Receipt receipt = (Vodafone_Cash_Receipt) request.getSession().getAttribute("Receipt");
//        Gson gson = new Gson();
//    String Receipt_str = MasaryManager.getInstance().GET_RECEIPT_Vodafone_Cash("1463765");
//    Vodafone_Cash_Receipt receipt = gson.fromJson(Receipt_str, Vodafone_Cash_Receipt.class);

%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vodafone Cash Receipt</title>
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
                <form name="Vodafone Cash Receipt" action="" method="POST" >
                    <br><br>
                    <applet  code="printApplet" name="0" archive="CashService.jar" width="150" height="302" > 
                        <param name="serviceType" value="<%= receipt.getOPERATION_TYPE()%>"/>
                    <param name="TID" value="<%= receipt.getTRANSACTION_ID()%>"/>
                    <param name="from" value="<%=receipt.getCUSTOMER_ID()%>"/>
                    <param name="time" value="<%=receipt.getTRANSACTION_DATE().substring(11)%>"/>
                    <param name="date" value="<%=receipt.getTRANSACTION_DATE().substring(0, 10)%>"/>
                    <param name="mNumber" value="<%=receipt.getCUSTOMER_PHONE()%>"/>
                    <param name="amount" value="<%= receipt.getAMOUNT()%>"/>
                    <param name="fees" value="<%= receipt.getCUSTOMER_FEE()%>"/>
                    <param name="totalAmount" value="<%= receipt.getTOTAL()%>"/>
                </applet>
            </form>
        </div><!-- End content body -->

        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
        <!-- End of Main body-->
    </body>
</html>