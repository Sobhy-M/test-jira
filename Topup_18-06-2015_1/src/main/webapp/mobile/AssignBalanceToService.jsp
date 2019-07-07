<%--
    Document   : serviceAllocation.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
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
    ArrayList<CustomerServiceDTO> serviceList = (ArrayList<CustomerServiceDTO>) session.getAttribute("services");
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    int serviceID = Integer.parseInt((String) request.getSession().getAttribute(CONFIG.PARAM_SERVICE_ID));
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getManageCustomerBalance(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <form name="AssignBalanceAgent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" onsubmit="return validateServiceAssign()" >
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASSIGN_AGENT_SERVICE_BALANCE%>"/>
            <table  width="100%">
                <tr align="center">
                    <td class="log_in_text_box_user_mobile" colspan="4"><%=CONFIG.getServices(session)%></td>
                </tr>
                <%
                    for (CustomerServiceDTO service : (List<CustomerServiceDTO>) serviceList) {
                %>
                <tr >
                    <td align="left"  ><%=service.getServiceName(session)%></td>
                </tr>
                <tr>
                    <%
                        if (service.getServiceID() == serviceID) {

                    %>
                    <%--<td class="log_in_text_box_user_mobile" colspan="2"><%=myFormatter.format(service.getServiceBalance())%></td>--%>
                    <td align="right" >
                        <input type="text" name="<%=CONFIG.AMOUNT%>" />
                    </td>
                </tr>
                <%
                } else {
                %>
                <td class="log_in_text_box_user_mobile" colspan="2" align="right"><%=myFormatter.format(service.getServiceBalance())%></td>
                <%                                }
                    }%>
                <tr><td colspan="4"><input type="submit" value="<%=CONFIG.getSave(session)%>"/> </td></tr>
            </table>
        </form>


    </body>
</html>






