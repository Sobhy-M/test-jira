<%-- 
    Document   : bill_payment
    Created on : 01/05/2012, 07:11:56 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.Masary_Balance_Sheet_Detailes"%>
<%@page import="com.masary.database.dto.Masary_Balance_Sheet"%>
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
    session = request.getSession();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <!--        <link href="bill/CalendarControl.css" rel="stylesheet" type="text/css"/>-->
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <script language="javascript" type="text/javascript" src="bill/datetimepicker.js"></script>
        <!--        <script src="bill/CalendarControl.js" language="javascript"></script>-->
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
    <%try {
            String Error = request.getSession().getAttribute("ErrorCode").toString();
%>
    <font style="color: red; font-size: 15px;"><%=request.getSession().getAttribute("ErrorCode").toString()%></font>    
        <%
                request.getSession().removeAttribute("ErrorCode");
            } catch (Exception e) {
            }
        %>
    <form name="Balance_Sheet" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_Get_Balance_Sheet%>" />
        <input id="Date" name="Date" type="text" size="25" />
        <a href="javascript:NewCal('Date','ddmmyyyy')"><img src="bill/cal.gif" width="16" height="16" border="0" alt="Pick a date"/></a>
        <br/>
        <input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getSearch(session)%>" class="Btn"><br><br><br>
        <% try {

                Masary_Balance_Sheet balance_Sheet = (Masary_Balance_Sheet) session.getAttribute("balance_Sheet");
                if (balance_Sheet.getSTATUSCODE().equals("200")) {
        %>
        <table>
            <th>Date</th><th>Opening Balance</th><th>Closing Balance</th>
            <tr><td><%=balance_Sheet.getBALANCE_DATE()%></td><td><%=balance_Sheet.getOPENING_BALANCE()%></td><td><%=balance_Sheet.getCLOSEING_BALANCE()%></td></tr>
        </table><br><br>
        <table>
            <th>ACTION_TYPE_CODE</th><th>ACTION_NATURE</th><th>Amount</th>
                <%for (Masary_Balance_Sheet_Detailes detailes : balance_Sheet.getMasary_Balance_Sheet_Detailes()) {%>
            <tr><td><%=detailes.getACTION_TYPE_CODE()%></td><td><%=detailes.getACTION_NATURE()%></td><td><%=detailes.getAMOUNT()%></td></tr>
            <%}
            %>
        </table>
        <%
                }
            } catch (Exception e) {
            }%>
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
