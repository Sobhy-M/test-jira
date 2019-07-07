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
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    List transList = MasaryManager.getInstance().getTransactionsByPayer((String) session.getAttribute("custIdForAccSession"));
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<%!    double totalAmount = 0;
    double fromM = 0;
    double toM = 0;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getTransactions(session)%></title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div class="content_body"><br><br>

            <h3> <th><%=CONFIG.getBalance(session)%></th>
            <td ><%=myFormatter.format(customerBalance)%> </td>
            <br/>

            <%--<h2>Last transactions</h2>--%>
            <th><%=CONFIG.getTransactionsFrom(session)%> <%=agent.getName(session)%></th></h3>
        <table>
            <thead>
            <th scope="col"> <%=CONFIG.getTransactionNumber(session)%></th>
            <th> <%=CONFIG.getDate(session)%></th>
            <th><%=CONFIG.getFrom(session)%></th>
            <th><%=CONFIG.getTo(session)%></th>
            <th><%=CONFIG.getType(session)%></th>
            <th><%=CONFIG.getStatus(session)%></th>
            <th><%=CONFIG.getAmount(session)%></th>
            </thead>
            <tbody>
                <%
                    totalAmount = 0;
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
                    <%

                            totalAmount += trans.getAmount();
                            fromM = totalAmount;
                        }%>
                </tr>
                <tr>
                    <th scope="row" colspan="6"><%=CONFIG.getTotal(session)%> </th>
                    <td><%=myFormatter.format(fromM)%></td>
                </tr>
            </tbody></table>
        <br/>
        <h3><th><%=CONFIG.getTransactionsTo(session)%> <%=agent.getName(session)%></th></h2>
            <table>
                <thead>
                <th scope="col"> <%=CONFIG.getTransactionNumber(session)%></th>
                <th> <%=CONFIG.getDate(session)%></th>
                <th><%=CONFIG.getFrom(session)%></th>
                <th><%=CONFIG.getTo(session)%></th>
                <th><%=CONFIG.getType(session)%></th>
                <th><%=CONFIG.getStatus(session)%></th>
                <th><%=CONFIG.getAmount(session)%></th>
                </thead>
                <tbody><%
                    transList = MasaryManager.getInstance().getTransactionsByPayed((String) session.getAttribute("custIdForAccSession"));
                    totalAmount = 0;
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
                            toM = totalAmount;
                        }

                    %>
                    <tr>
                        <th scope="row" colspan="6"><%=CONFIG.getTotal(session)%> </th>
                        <td><%=myFormatter.format(toM)%></td>
                    </tr>
                    <tr>
                        <th scope="row" colspan="6"><%=CONFIG.getNet(session)%> </th>
                        <td><%
                            if (Integer.parseInt(agent.getPin()) == 1) {%>
                            <%=myFormatter.format(fromM - toM)%>
                            <%} else {%>
                            <%=myFormatter.format(toM - fromM)%>
                            <%}
                                fromM = 0;
                                toM = 0;

                            %></td>
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






