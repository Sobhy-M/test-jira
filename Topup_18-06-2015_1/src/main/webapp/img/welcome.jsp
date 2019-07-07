<%-- 
    Document   : welcome
    Created on : 28/06/2009, 11:35:59 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.*"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">--%>

<%
    DecimalFormat myFormatter = new DecimalFormat("###,###.### EGP");
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    </head>
    <body>
        <div >
            <table  width="100%">

                <td ><font size="2">Welcome  <%=agent.getName(null)%>      </font>                </td>
                <td  style="text-align:center" ><font size="2">Your Balance  <%= myFormatter.format(customerBalance)%></font></td>
                <td style="text-align:right" ><font size="2"><%= java.util.Calendar.getInstance().getTime().toLocaleString()%></font></td>
            </table>
        </div>
    </body>
</html>
