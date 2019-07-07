<%--
    Document   : ViewTransaction.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>

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
            session = request.getSession();
            String custId = (String) session.getAttribute(CONFIG.PARAM_PIN);
            List reps = MasaryManager.getInstance().getRepsForSupervisor(custId);
%>
<html>
    <head>
        <style type="text/css" media="screen">@import "img/jq/theme.min.css";</style>
        <link href="img/jq/theme.min.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getReports(session)%></title>
    </head>

    <body dir="rtl">
        <div>
            <div class="toolbar">
                <h1><%=CONFIG.getReports(session)%></h1>
            </div>
            <ul>
                <%
                            for (AgentDTO agent : (List<AgentDTO>) reps) {

                %>
                <li> <a  href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_BALANCE_MOBILE&PARAM_AGENT_ID=<%=agent.getPin()%>" ><%=agent.getName(session)%></a></li>
                <%}
                %>
                <li><a href="<%=CONFIG.APP_ROOT%>walletServices?action=<%=CONFIG.ACTION_MAIN%>"> <%=CONFIG.getBack(session)%> </a></li>
            </ul>
            <div class="info">
                <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
            </div>
        </div>
    </body>
</html>