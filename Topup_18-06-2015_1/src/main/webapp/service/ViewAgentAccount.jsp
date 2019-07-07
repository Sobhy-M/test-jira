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
    if (role == null || !role.equals("4")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    List transList = MasaryManager.getInstance().getTransactionsByPayed(agent.getPin());
    double totalAmount = 0;
    DecimalFormat myFormatter = new DecimalFormat("###,###.###  EGP");
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Bank and transactions Information</title>
    </head>
    <BODY class="body">

        <div class="main_body" align="center">
            <div class="top_header" align="center" style="background:url(img/header_bg.png) no-repeat"><!-- Header div-->
                <div class="top_banner">

                    <IMG SRC="img/1x.jpg" WIDTH="646" ALT="" border="0">
                    <jsp:include page="../img/welcome.jsp"></jsp:include>

                    </div>

                </div>
                <div class="content_body">

                    <div class="left_menu"><!-- left menu -->
                        <a href="<%=CONFIG.APP_ROOT + "4.jsp"%>"><IMG SRC="img/home.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a><br>
                    <a href="<%=CONFIG.APP_ROOT%>Login_Controller"><IMG SRC="img/logout.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>
                </div>

                <div class="content_body"><br><br>
                    <table >
                        <tr>
                            <th scope="col"><%=agent.getName(request.getSession())%> Balance</th>
                            <td > <%=myFormatter.format(customerBalance)%> </td>
                        </tr>
                    </table>
                    <h2>Last transactions</h2>
                    <table>
                        <thead>
                        <th scope="col"> Transaction Number</th>
                        <th> Date</th>
                        <th>Customer Name</th>
                        <th>Amount</th>
                        </thead>
                        <tbody>
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
                                <td><%=myFormatter.format(trans.getAmount())%></td>
                            </tr>
                            <%
                                    totalAmount += trans.getAmount();
                                }
                                transList = MasaryManager.getInstance().getTransactionsByPayer(agent.getPin());
                                for (int i = 0; i < transList.size(); i++) {
                                    trans = (TransactionDTO) transList.get(i);
                            %>
                            <tr>
                                <th scope="row"><%=trans.getTransId()%> </th>
                                <td><%=trans.getDate()%></td>
                                <td><%=trans.getCustomerPayerName()%></td>
                                <td><%=trans.getCustomerPayedName()%></td>
                                <td><%=myFormatter.format(trans.getAmount())%></td>
                            </tr>
                            <%
                                    totalAmount -= trans.getAmount();
                                }
                            %>
                            <tr>
                                <th scope="row" colspan="4">Total </th>
                                <td><%=myFormatter.format(totalAmount)%></td>
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

