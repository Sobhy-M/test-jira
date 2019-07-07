<%--
    Document   : ViewBankInformation.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

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
    if (role == null || !role.equals("2")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    List transList = MasaryManager.getInstance().getTransactionsByPayer(agent.getPin());
    double totalAmount = 0;
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAccountInformation(session)%></title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div class="content_body"><br><br>
            <table >
                <tr>
                    <th scope="col"><%=agent.getName(session)%> <%=CONFIG.getBalance(session)%></th>
                <td > <%=myFormatter.format(customerBalance)%> </td>
            </tr>
        </table>
        <table>
            <thead>
            <th scope="col"> <%=CONFIG.getTransactionNumber(session)%></th>
            <th> <%=CONFIG.getDate(session)%></th>
            <th><%=CONFIG.getFrom(session)%></th>
            <th><%=CONFIG.getTo(session)%> </th>
            <th><%=CONFIG.getType(session)%></th>
            <th><%=CONFIG.getStatus(session)%></th>
            <th><%=CONFIG.getAmount(session)%></th>
            </thead>
            <tbody>
                <%
                    TransactionDTO trans = null;
                    for (int i = 0; i < transList.size(); i++) {
                        trans = (TransactionDTO) transList.get(i);
                %>
                <tr>
                    <th scope="row"><%=trans.getTransId()%> </th>
                    <td><%=trans.getDate()%></td>
                    <td><%=trans.getCustomerPayerName()%></td>
                    <td><%=trans.getCustomerPayedName()%></td>
                    <td><%=trans.getType()%></td>
                    <td><%=trans.getStatus()%></td>
                    <td><%=myFormatter.format(trans.getAmount())%></td>
                </tr>
                <%
                        totalAmount -= trans.getAmount();
                    }
                    transList = MasaryManager.getInstance().getTransactionsByPayed(agent.getPin());
                    for (int i = 0; i < transList.size(); i++) {
                        trans = (TransactionDTO) transList.get(i);
                %>
                <tr>
                    <th scope="row"><%=trans.getTransId()%> </th>
                    <td><%=trans.getDate()%></td>
                    <td><%=trans.getCustomerPayerName()%></td>
                    <td><%=trans.getCustomerPayedName()%></td>
                    <td><%=trans.getType()%></td>
                    <td><%=trans.getStatus()%></td>
                    <td><%=myFormatter.format(trans.getAmount())%></td>
                </tr>
                <%
                        totalAmount += trans.getAmount();
                    }
                %>
                <tr>
                    <th scope="row" colspan="6"><%=CONFIG.getTotal(session)%> </th>
                    <td><%=totalAmount%></td>
                </tr>
            </tbody>
        </table>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
