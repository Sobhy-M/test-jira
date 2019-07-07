<%-- 
    Document   : ViewCustomers.jsp
    Created on : 06/05/2009, 11:10:07 ص
    Author     : Melad
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
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
    //MasaryManager.id = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    List list=null;
    session = request.getSession();
    try {
         list = MasaryManager.getInstance().getAgents((String) session.getAttribute(CONFIG.PARAM_PIN));
    } catch (Exception e) {
        MasaryManager.logger.error(e.getMessage());
    }
    DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getViewCustomers(session)%></title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>                <div class="content_body"><br><br>
            <form action="<%=CONFIG.APP_ROOT%>walletServices" method="post">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASIGN_CUSTOMER_BALANCE%>" />
            <table >
                <thead>
                    <tr>
                        <th scope="col"><%=CONFIG.getID(session)%></th>
                        <th scope="col" ><%=CONFIG.getName(session)%></th>
                        <th scope="col" ><%=CONFIG.getMobileNumber(session)%></th>
                        <th scope="col"><%=CONFIG.getBalance(session)%></th>
                        <th SCOPE="COL"><%=CONFIG.getAssign(session)%></th>
                        <th SCOPE="COL"><%=CONFIG.getManageAccount(session)%></th>
                    </tr>
                </thead>
                <%
                    int i, length = list.size();
                    for (i = 0; i < length; i++) {
                        AgentDTO agent = (AgentDTO) list.get(i);
                %>
                <tbody>
                    <tr>
                        <th  scope="row"> <%=agent.getPin()%></th>
                        <td ><%=agent.getName(session)%></td>
                        <td ><%=agent.getPhone()%></td>
                        <td ><%=myFormatter.format(agent.getBalance())%></td>
                        <td> <input type="submit" value="<%=CONFIG.getAssign(session)%>"  name="<%=agent.getPin()%>"></td>
                        <td>
                            <input type="submit" onclick="return confirm('<%=CONFIG.getAreyouSure(session)%>');" value="<%=agent.isActive() ? CONFIG.getDeactivate(session) : CONFIG.getActivate(session)%>" name="<%=agent.getPin()%>">
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </form>
        <!-- content body -->
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>