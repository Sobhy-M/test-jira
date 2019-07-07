<%--
    Document   : AssignBalance.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%

    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("1")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }

%>
<%    AgentDTO agent = null;
    String agentId = (String) request.getSession().getAttribute(CONFIG.PARAM_AGENT_ID);
    try {
        agent = MasaryManager.getInstance().getAgent(agentId);

        session = request.getSession();
        DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getManageAgent(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (session.getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>

        </div>
        <div class="content_body"><br><br>
            <form name="AssignBalanceAgent" action="<%=CONFIG.APP_ROOT%>admin" method="POST" >
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_MANAGE_AGENT_BALANCE%>" />
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getName(session)%></th>
                    <td ><%=agent.getName(session)%> </td>
                    <th rowspan="4">
                        <input type="submit" name="btnEdit" tabindex="5"
                               value="<%=CONFIG.getEditAgent(session)%>" class="Btn" />
                        <br/>
                        <br/>

                        <input type="submit" name="btnSubmit" tabindex="5"
                               value="<%=CONFIG.getResetBalance(session)%>" class="Btn" onclick="return confirm('By Clicking OK You Will reset <%=agent.getName(session)%> Balance \n and all his sellers will lose their crediets ?')"/>
                        <br/>
                        <br/>
                        <input type="button" dir="rtl" name="btnSubmit" tabindex="3"
                               value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1);return true;">
                    </th>

                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getMobileNumber(session)%></th>
                    <td ><%=agent.getPhone()%> </td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getID(session)%></th>
                    <td><%=agent.getPin()%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getBalance(session)%></th>
                    <td><%=myFormatter.format(agent.getBalance())%></td>
                </tr>
                <tr>
                    <th colspan="3"><%=CONFIG.getServices(session)%></th>

                </tr>
                <%
                    for (CustomerServiceDTO service : (List<CustomerServiceDTO>) agent.getServices()) {
                %>
                <tr>
                    <th colspan="2"><%=service.getServiceName(session)%></th>
                    <td><%=myFormatter.format(service.getServiceBalance())%></td>
                </tr>
                <%}
                    } catch (Exception e) {
                        MasaryManager.logger.error(e.getMessage());
                    }%>
            </table>
        </form>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div>
</div><!-- End of Main body-->

</body>
</html>






