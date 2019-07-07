<%--
    Document   : bill_transactions
    Created on : 01/05/2012, 07:11:56 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    String From = "";
    String To = "";
    session = request.getSession();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    Masary_Bill_Type BTC = new Masary_Bill_Type();
    ServiceDTO serviceDTO = new ServiceDTO();
    String isbill = "N";
    try {
        isbill = request.getSession().getAttribute("bill").toString();
        BTC = MasaryManager.getInstance().getBTC(request.getSession().getAttribute(CONFIG.PARAM_SERVICE_ID).toString());

    } catch (Exception e) {
        MasaryManager.logger.error(e.getMessage());
    }
    if (isbill.equals("N")) {
        serviceDTO = MasaryManager.getInstance().getService(request.getSession().getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
    }
    String Service_id = request.getSession().getAttribute(CONFIG.PARAM_SERVICE_ID).toString();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <script language="javascript" type="text/javascript" src="bill/datetimepicker.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="BillTransactions" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_Get_Bill_Transaction%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute(CONFIG.PARAM_SERVICE_ID).toString()%>" />
        <input type="hidden" name="bill" value="<%=isbill%>" />
        <div align="center"><h2>
                <%  if (isbill.equals("Y")) {%>
                <%=BTC.getStrBTCName(session)%>
                <%} else {%>
                <%=serviceDTO.getStrServiceName(session)%>
                <%}%>
            </h2></div><br><br>
        <table border="0">
            <tr><td>
                    <%=CONFIG.getFrom(session)%> :
                </td><td>
                    <input id="From" name="From" type="text" size="25" />
                    <a href="javascript:NewCal('From','ddmmyyyy')"><img src="bill/cal.gif" width="16" height="16" border="0" alt="Pick a date"/></a>
                    <br/>
                </td></tr>
            <tr><td>
                    <%=CONFIG.getTo(session)%> :
                </td><td>
                    <input id="To" name="To" type="text" size="25" />
                    <a href="javascript:NewCal('To','ddmmyyyy')"><img src="bill/cal.gif" width="16" height="16" border="0" alt="Pick a date"/></a>
                    <br/>
                </td></tr>
        </table>
        <input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getSearch(session)%>" class="Btn"><br><br><br>
    </form>
    <% try {
            From = session.getAttribute("DateFrom").toString();
            To = session.getAttribute("DateTo").toString();
    %>
    <table>
        <th><%=CONFIG.getDate(session)%></th><th><%=CONFIG.getID(session)%></th><th><%=CONFIG.getpayer(session)%></th><th><%=CONFIG.getPayed(session)%></th><th><%=CONFIG.getVoucherAmount(session)%></th><th><%=CONFIG.getStatus(session)%></th><th><%=CONFIG.getService(session)%></th>
        <%
            if (isbill.equals("N")) {
                for (TransactionDTO transactionDTO : MasaryManager.getInstance().getTransactions_To(agent.getPin(), From, To, request.getSession().getAttribute(CONFIG.PARAM_SERVICE_ID).toString())) {
        %>
        <tr><td><%=transactionDTO.getDate()%></td><td><%=transactionDTO.getTransId()%></td><td><%=transactionDTO.getCustomerPayerName()%></td><td><%=transactionDTO.getCustomerPayedName()%></td><td><%=transactionDTO.getAmount()%></td><td><%=transactionDTO.getStatus()%></td><td><%=transactionDTO.getType()%></td></tr>
        <%
            }
        } else if (isbill.equals("Y")) {
            if (Service_id.equals("112")) {
                for (TransactionDTO transactionDTO : MasaryManager.getInstance().get_Mobinil_BillTransactions_To(agent.getPin(), From, To, Service_id)) {
        %>
        <form name="<%=transactionDTO.getTransId()%>" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST">
            <tr>
                <td><%=transactionDTO.getDate()%></td><td><%=transactionDTO.getTransId()%></td><td><%=transactionDTO.getCustomerPayerName()%></td><td><%=transactionDTO.getCustomerPayedName()%></td><td><%=transactionDTO.getAmount()%></td><td><%=transactionDTO.getStatus()%></td><td><%=transactionDTO.getType()%></td><td><%=transactionDTO.getCommession()%></td>
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_Print_Reciept%>" />
            <input type="hidden" name="Serv_Id" value="<%=Service_id%>" />
            <input type="hidden" name="TXN_ID" value="<%=transactionDTO.getTransId()%>" />
            <td><button  type="submit" name="btnSubmit" alt="Print reciept copy" tabindex="3" value="Print" class="Btn"><img src="img/report.png" alt="Print reciept copy" height="30" width="30"/></button></td>

            </tr>
        </form>
        <%
            }
        } else if (Service_id.equals("111")) {
            for (TransactionDTO transactionDTO : MasaryManager.getInstance().get_Vodafone_BillTransactions_To(agent.getPin(), From, To, Service_id)) {
        %>
        <form name="<%=transactionDTO.getTransId()%>" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST">
            <tr>
                <td><%=transactionDTO.getDate()%></td><td><%=transactionDTO.getTransId()%></td><td><%=transactionDTO.getCustomerPayerName()%></td><td><%=transactionDTO.getCustomerPayedName()%></td><td><%=transactionDTO.getAmount()%></td><td><%=transactionDTO.getStatus()%></td><td><%=transactionDTO.getType()%></td><td><%=transactionDTO.getCommession()%></td>
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_Print_Reciept%>" />
            <input type="hidden" name="Serv_Id" value="<%=Service_id%>" />
            <input type="hidden" name="TXN_ID" value="<%=transactionDTO.getTransId()%>" />
            <td><button  type="submit" name="btnSubmit" alt="Print reciept copy" tabindex="3" value="Print" class="Btn"><img src="img/report.png" alt="Print reciept copy" height="30" width="30"/></button></td>

            </tr>
        </form>
        <%
            }
        } else {
            for (TransactionDTO transactionDTO : MasaryManager.getInstance().getBillTransactions_To(agent.getPin(), From, To, Service_id)) {
        %>
        <form name="<%=transactionDTO.getTransId()%>" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST">
            <tr>
                <td><%=transactionDTO.getDate()%></td><td><%=transactionDTO.getTransId()%></td><td><%=transactionDTO.getCustomerPayerName()%></td><td><%=transactionDTO.getCustomerPayedName()%></td><td><%=transactionDTO.getAmount()%></td><td><%=transactionDTO.getStatus()%></td><td><%=transactionDTO.getType()%></td><td><%=transactionDTO.getCommession()%></td>
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_Print_Reciept%>" />
            <input type="hidden" name="Serv_Id" value="<%=Service_id%>" />
            <input type="hidden" name="TXN_ID" value="<%=transactionDTO.getTransId()%>" />
            <td><button  type="submit" name="btnSubmit" alt="Print reciept copy" tabindex="3" value="Print" class="Btn"><img src="img/report.png" alt="Print reciept copy" height="30" width="30"/></button></td>
            </tr>
        </form>
        <%
                        }
                    }

                }
            } catch (Exception e) {
                MasaryManager.logger.error(e.getMessage());
            }%>
    </table>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
