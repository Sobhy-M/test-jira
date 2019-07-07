<%--
    Document   : AssignBalance.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="java.text.DecimalFormat"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("2")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    AgentDTO agent =null;
    try {
        session = request.getSession();
        String custId = (String) session.getAttribute(CONFIG.PARAM_CUSTOMER_ID);
        agent = MasaryManager.getInstance().getAgent(custId);
    } catch (Exception e) {
        MasaryManager.logger.error(e.getMessage());
    }
    DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>><%=CONFIG.getAssignBalanceToCustomer(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div class="content_body">
            <form name="AssignBalanceAgent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" onsubmit="return validateAssignBalanceAgent()">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASIGN_CUSTOMER_BALANCE%>" />
            <div class="content_body"><br><br>
                <table >
                    <tr>
                        <th scope="col"><%=CONFIG.getName(session)%></th>
                        <td ><%=agent.getName(session)%> </td>
                        <th rowspan="4"><input type="submit" name="btnSubmit" tabindex="5"
                                               value="<%=CONFIG.getAssign(session)%>" class="Btn"></th>
                    </tr>
                    <tr>
                        <th scope="col"><%=CONFIG.getID(session)%></th>
                        <td><%=agent.getPin()%></td>
                    </tr>
                    <tr>
                        <th scope="col"><%=CONFIG.getBalance(session)%></th>
                        <td><%=myFormatter.format(agent.getServiceBalance(1))%></td>
                    </tr>
                    <tr>
                        <th scope="col"><%=CONFIG.getAddBalance(session)%></th>
                        <td><input type="text" name="<%=CONFIG.PARAM_BALANCE%>">
                            <div id="BalanceDiv"></div>
                        </td>
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