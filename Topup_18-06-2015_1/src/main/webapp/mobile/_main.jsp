<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
         pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="img/c_config_mobile.js"></script>
<script type="text/javascript" src="img/c_smartmenus.js"></script>
<link href="style.css" rel="stylesheet" type="text/css">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    ArrayList<CustomerServiceDTO> serviceList = (ArrayList<CustomerServiceDTO>) session.getAttribute("services");
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getWelcome(session)%>  <%=agent.getName(session)%> </title>
    </head>
    <body>
        <ul  id="Menu1" class="M">
            <li><a><%=CONFIG.getWelcome(session)%> <%=agent.getName(session)%></a></li>
                <%
                    for (CustomerServiceDTO service : serviceList) {
                %>
            <li> <a  href="<%=CONFIG.APP_ROOT%>Web?action=ACTION_CUSTOMER_RECHARGE_MOBILE&PARAM_CUSTOMER_BTN=<%=CONFIG.CUSTOMERTOPUPen%>&<%=CONFIG.PARAM_SERVICE_ID + "=" + service.getServiceID()%>" ><%=service.getServiceName(session)%></a></li>
                <%}
                %>
            <li><a  href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_BALANCE_MOBILE"><%=CONFIG.getBalance(session)%>  <%= myFormatter.format(customerBalance)%></a></li>
            <li><a  href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_GET_LAST_TRANSACTIONS"><%=CONFIG.getLastTransactions(session)%></a></li>
            <li><a href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_CHANGE_LANG_MOBLIE"><%=CONFIG.getLanguage(session)%></a></li>
        </ul>
    </body>
</html>
