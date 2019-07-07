<%--
    Document   : ViewBankInformation.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.*"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
%>
<%
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    String repID = requestParameters.get(CONFIG.PARAM_AGENT_ID);
    AgentDTO rep = MasaryManager.getInstance().getAgent(repID);
    Map<ServiceDTO, Integer> dailyTransactions = MasaryManager.getInstance().getDailyTransactions(repID);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=rep.getName(session)%></title>
    </head>
    <BODY class="body">
        <%if (session.getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div class="content_body"><br><br>
            <table >
                <tr>
                    <th scope="col" colspan="2"><%=CONFIG.getID(session)%></th>
                <td colspan="2"><%=rep.getPin()%></td>
            </tr>
            <tr >
                <th scope="col" colspan="2"><%=CONFIG.getName(session)%></th>
                <td colspan="2"><%=rep.getName(session)%> </td>
            </tr>
            <tr>
                <%
                    for (CustomerServiceDTO service : (List<CustomerServiceDTO>) rep.getServices()) {
                %>
            <tr>
                <th colspan="2"><%=service.getServiceName(session)%></th>
                <td><%=myFormatter.format(service.getServiceBalance())%></td>
            </tr>
            <%
                }%>
            </tr>
        </table>
        <% if (dailyTransactions.size() > 0) {%>
        <br>
        <br>
        <%=CONFIG.getTransactions(session)%> 
        <br>
        <table>
            <th><%=CONFIG.getServiceName(session)%></th>
            <th><%=CONFIG.getTransactions(session)%></th>
                <%
                    Set s = dailyTransactions.entrySet();
                    Iterator it = s.iterator();
                    while (it.hasNext()) {
                        Map.Entry m = (Map.Entry) it.next();
                        ServiceDTO service = (ServiceDTO) m.getKey();
                        Integer count = (Integer) m.getValue();
                %>
            <tr>
                <td><%=service.getStrServiceName(session)%> </td>
                <td> <%=count%></td>
            </tr>
            <%    }%>
        </table>
        <%}%>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
