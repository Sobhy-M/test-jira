<%--
    Document   : TransferFees
    Created on : Jul 12, 2016, 10:59:23 AM
    Author     : Ahmed Saeed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>

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
<%    session = request.getSession();
    String transId = (String) session.getAttribute(CONFIG.PARAM_Transaction_ID);
    String taxAmount = (String) session.getAttribute(CONFIG.PARAM_TAX_AMOUNT);
    taxAmount = taxAmount == null ? "0" : taxAmount;

    TransactionDTO trans;
    try {
        trans = MasaryManager.getInstance().getTransaction(transId);

    } catch (Exception ex) {
        session.setAttribute("ErrorMessage", "Detailed error code is:" + ex.getMessage());
        response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
        ex.printStackTrace();
        return;
    }
    DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=CONFIG.getTransactionReport(session)%></title>
        <script type="text/javascript">
            function zPrint(oTgt)
            {
                oTgt.focus();
                oTgt.print();
            }
        </script>


    </head>
    <BODY class="body" >
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <div class="content_body"><br><br>
                <fieldset style="width: 37%; direction: rtl;" align="right">

                    <legend align="right" ><font size="5"><%=CONFIG.getTransactionReport(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>
                <table border="1" width="100%">
                    <tr>
                        <!-- رقم العمليه -->
                        <th scope="col"><%=CONFIG.getTransactionNumber(session)%></th>
                        <td ><%=trans.getTransId()%> </td>
                    <tr>
                        <th scope="col"><%=CONFIG.getFrom(session)%></th>
                            <%  if (session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {%>
                        <td><%=trans.getCustomerPayerName()%></td>
                        <% } else {%>
                        <td> <%=MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).getEmployeeName(session)%></td>
                        <% }%>
                    </tr>
                    <%if ((session.getAttribute("howSell") == null) || (session.getAttribute("howSell").equals("sms"))) {%>
                    <tr>
                        <th scope="col"><%=CONFIG.getTo(session)%></th>
                        <td><%=trans.getCustomerPayedName()%></td>
                    </tr>
                    <% }%>
                    <tr>
                        <th scope="col"><%=CONFIG.getType(session)%></th>
                        <td><%=trans.getType()%></td>
                    </tr>
                    <tr>
                        <th scope="col"><%=CONFIG.getStatus(session)%></th>
                        <td><%=trans.getStatus()%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getAmount(session)%></th>
                        <td><%=trans.getAmount() + Double.parseDouble(taxAmount)%><%= CONFIG.getTransferFeesCurrency(session) %>
                        </td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getRechargeAmount(session)%></th>
                        <td><%=myFormatter.format(trans.getAmount())%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getTAX_FEES(session)%></th>
                        <td><%=taxAmount%><%= CONFIG.getTransferFeesCurrency(session) %></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getDate(session)%></th>
                        <td><%=trans.getDate()%></td>

                    <tr>
                    <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%>  method="post"  name="admin_form" id="saveform">
                        <td colspan="2" align="center">
                            <input type="submit" name="btnSubmit" tabindex="5" value="OK" class="Btn">
                        </td>
                    </form>

                    </tr>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                        <%= CONFIG.getTRANSFER_FEES_TRANS_DETAILS(session) %>
                </details>
        </div>

    </body>
</html>