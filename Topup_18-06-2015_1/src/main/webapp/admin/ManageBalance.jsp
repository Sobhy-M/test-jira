<%--
    Document   : ManagePage.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
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
<%
    session = request.getSession();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getManageAccount(session)%></title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="Manage agent" action="<%=CONFIG.APP_ROOT%>admin" method="get" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_MANAGE_ADMIN_BALANCE%>" />
        <div class="content_body"><br><br>
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getName(session)%></th>
                    <td><%=agent.getName(session)%></td>
                    <th rowspan="2"><input type="submit" name="btnSubmit" tabindex="3"
                                           value="<%=CONFIG.getChange(session)%>" class="Btn"></th>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getID(session)%></th>
                    <td><%=agent.getPin()%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getBalance(session)%></th>
                    <td><%=myFormatter.format(customerBalance)%></td>
                    <th rowspan="2"><input type="submit" name="btnSubmit" tabindex="3"
                                           value="<%=CONFIG.getAddBalance(session)%>" class="Btn"></th>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getAmount(session)%></th>
                    <td><input type="text" class="text"  name="<%=CONFIG.PARAM_BALANCE%>" tabindex="1" /></td>
                </tr>
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>


