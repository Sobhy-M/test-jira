<%--
    Document   : ViewBaslance.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>

<link href="img/style_login.css" rel="stylesheet" type="text/css">
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
    String repId = request.getParameter(CONFIG.PARAM_AGENT_ID);
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    ArrayList<CustomerServiceDTO> services = null;
    AgentDTO agent = null;
    if (repId == null) {
        repId = "";
        services = (ArrayList<CustomerServiceDTO>) session.getAttribute("services");
    } else {
        agent = MasaryManager.getInstance().getAgent(repId);
        services = (ArrayList<CustomerServiceDTO>) agent.getServices();
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getManageCustomerBalance(session)%></title>
    </head>
    <BODY >
        <table  width="100%"  <%=(session.getAttribute(CONFIG.lang).equals("ar")) ? "dir='rtl'" : ""%> >
            <%if (!repId.isEmpty()) {%>
            <tr>
                <td>
                    <%=agent.getName(session)%>
                </td>
            </tr>
            <%}%>

            <form name="AssignBalanceAgent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" >
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_MANAGE_AGENT_SERVICE_BALANCE%>"/>
                <%
                    for (CustomerServiceDTO service : services) {
                %>
                <tr>
                    <td colspan="2" ><%=service.getServiceName(session)%></td>
                </tr>
                <tr>
                    <td class="table_row"><%=myFormatter.format(service.getServiceBalance())%> </td>
                    <%if (repId.isEmpty()) {%>
                    <td  align="char" class="table_row">
                        <input type="submit" value="<%=CONFIG.getAssign(session)%>" <%=service.getServiceID() == 1 ? "disabled" : ""%>   name="<%=service.getServiceID()%>">
                    </td>
                    <%}%>
                </tr>
                <%}%>
            </form>
            <tr>
                <td colspan="2" align="right">
                    <form action="<%=CONFIG.APP_ROOT%>walletServices" method=post  name="admin_form" id="saveform">
                        <br>
                        <input type="hidden" name="action" value="<%=CONFIG.ACTION_MAIN%>" />
                        <input type="submit" name="btnSubmit" tabindex="5"
                               value="<%=CONFIG.getBack(session)%>" class="Btn">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>



