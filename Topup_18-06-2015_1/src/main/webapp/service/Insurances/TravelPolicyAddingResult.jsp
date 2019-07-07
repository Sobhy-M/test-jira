<%-- 
    Document   : TravelPolicyAddingResult
    Created on : Dec 2, 2015, 4:16:37 PM
    Author     : ELNAGDY
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">

    <%
        String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
        if (role == null) {
            response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
            return;
        }

        session = request.getSession();
        String transId = session.getAttribute(CONFIG.PARAM_Transaction_ID).toString();
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result</title>
</head>
<body class="body" >
    <script type="text/javascript" src="img/CheckForms.js"></script>
    <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
    <jsp:include page="../../img/menuList.jsp"></jsp:include>
    <%} else {%>
    <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
    </div>
    <div class="content_body"><br><br>
        <fieldset style="width: 37%; direction: rtl;" align="right">  

            <legend align="right" ><font size="5"><%=CONFIG.getTransactionReport(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
        <table border="1" width="100%">
            <tr>
            <tr>
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
            <tr>
                <th scope="col"><%=CONFIG.getTo(session)%></th>
                <td><%=trans.getCustomerPayedName()%></td>
            </tr>
            <tr>
                <th scope="col"><%=CONFIG.getOperationType(session)%></th>
                <td><%=trans.getType()%></td>
            </tr>
            <tr>
                <th scope="col"><%=CONFIG.getOperationStatus(session)%></th>
                <td><%=trans.getStatus()%></td>
            </tr>

            <tr>
                <th scope="col"><%=CONFIG.getGrossamount(session)%></th>
                <td><%=session.getAttribute("Finalamount")%></td>
            </tr>

            <tr>
                <th scope="col"><%=CONFIG.getDocumentGrossAmount(session)%></th>
                <td><%=session.getAttribute("amount")%></td>
            </tr>

            <tr>
                <th scope="col"><%=CONFIG.getServiceCost(session)%></th>
                <td><%=session.getAttribute("ServiceCost")%></td>
            </tr>
            <tr>
                <th scope="col"><%=CONFIG.getDate(session)%></th>
                <td><%=trans.getDate()%></td>
            </tr>


            <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%>  method="post"  name="admin_form" id="saveform">
                <td colspan="2" align="center">
                    <input type="submit" name="btnSubmit" tabindex="5" value="OK" class="Btn">
                </td>
            </form>
            </tr>
        </table>
    </fieldset>
</div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
