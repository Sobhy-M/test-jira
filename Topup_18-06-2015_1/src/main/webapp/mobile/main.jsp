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
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>

<html>
    <head>
        <style type="text/css" media="screen">@import "img/jq/theme.min.css";</style>
        <link href="img/jq/theme.min.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getWelcome(session)%>  <%=agent.getName(session)%> </title>
    </head>

    <body dir="rtl">
        <div>
            <div class="toolbar">
                <h1><%=CONFIG.getWelcome(session)%> <%=agent.getName(session)%></h1>                
            </div>
            <ul>
                <%
                    for (CustomerServiceDTO service : serviceList) {
                        if (service.getServiceID() == 13) {
                %>
                <li> <a  href="<%=CONFIG.APP_ROOT%>Web?action=ACTION_CUSTOMER_RECHARGE_MOBILE&PARAM_CUSTOMER_BTN=<%=CONFIG.CUSTOMERTOPUPen%>&<%=CONFIG.PARAM_SERVICE_ID + "=" + service.getServiceID()%>" ><%=CONFIG.getMasrawy(session)%></a></li>
                <li> <a  href="<%=CONFIG.APP_ROOT%>Web?action=ACTION_CUSTOMER_RECHARGE_MOBILE&PARAM_CUSTOMER_BTN=<%=CONFIG.CUSTOMERTOPUPen%>&<%=CONFIG.PARAM_SERVICE_ID + "=" + service.getServiceID()%>" ><%=CONFIG.getShofha(session)%></a></li>
                <li> <a  href="<%=CONFIG.APP_ROOT%>Web?action=ACTION_CUSTOMER_RECHARGE_MOBILE&PARAM_CUSTOMER_BTN=<%=CONFIG.CUSTOMERTOPUPen%>&<%=CONFIG.PARAM_SERVICE_ID + "=" + service.getServiceID()%>" ><%=CONFIG.getMazika(session)%></a></li>
                <li> <a  href="<%=CONFIG.APP_ROOT%>Web?action=ACTION_CUSTOMER_RECHARGE_MOBILE&PARAM_CUSTOMER_BTN=<%=CONFIG.CUSTOMERTOPUPen%>&<%=CONFIG.PARAM_SERVICE_ID + "=" + service.getServiceID()%>" ><%=CONFIG.getEl3ab(session)%></a></li>
                    <%
                            continue;
                        }
                    %>
                <li> <a  href="<%=CONFIG.APP_ROOT%>Web?action=ACTION_CUSTOMER_RECHARGE_MOBILE&PARAM_CUSTOMER_BTN=<%=CONFIG.CUSTOMERTOPUPen%>&<%=CONFIG.PARAM_SERVICE_ID + "=" + service.getServiceID()%>" ><%=service.getServiceName(session)%></a></li>
                    <%}
                    %>
                    <%if (agent.isAutoAllocate().equals("N")) {%>

                <li><a  href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_BALANCE_MOBILE"><%=CONFIG.getBalance(session)%>  <%= myFormatter.format(customerBalance)%></a></li>
                    <%} else {%>
                <li><a  href="#"><%=CONFIG.getBalance(session)%>  <%= myFormatter.format(customerBalance)%></a></li>
                    <%}%>

                <li><a  href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_GET_LAST_TRANSACTIONS"><%=CONFIG.getLastTransactions(session)%></a></li>
                    <%if (agent.isSupervisor()) {%>
                <li><a href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_REPORTS_MOBLIE"><%=CONFIG.getReports(session)%></a></li>
                    <%}%>
                <li><a  href="<%=CONFIG.APP_ROOT%>Login_Controller?action=ACTION_LOGOUT"><%=CONFIG.getLogout(session)%></a></li>
                <li><a href="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_CHANGE_LANG_MOBLIE"><%=CONFIG.getLanguage(session)%></a></li>
            </ul>
            <div class="info">
                <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
            </div>
        </div>
    </body>
</html>