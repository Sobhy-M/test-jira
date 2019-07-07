<%--
    Document   : ManagePage.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null || !role.equals("1")) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }
%>
<%
            session = request.getSession();
            DecimalFormat myFormatter = CONFIG.getFormater(session);
            String transId = (String) request.getParameter(CONFIG.PARAM_Transaction_ID);
            TransactionDTO trans = null;
            if (transId != null) {
                try {
                    trans = MasaryManager.getInstance().getTransaction(transId);
                } catch (Exception ex) {
                    session.setAttribute("ErrorMessage",
                            "Detailed error code is:" + ex);
                    response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
                    ex.printStackTrace();
                    return;
                }
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getRefund(session)%></title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>
    <form name="Refund" action="<%=CONFIG.APP_ROOT%>admin" method="get" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_REFUND%>" />
        <div class="content_body"><br><br>
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getTransactionNumber(session)%></th>

                    <td><input type="text" class="text" <%=(transId != null) ? "readonly" : ""%>  name="<%=CONFIG.PARAM_Transaction_ID%>" tabindex="1" value="<%=(transId != null) ? transId : ""%>"/></td>
                    <td><input type="submit" name="btnSubmit" tabindex="3"
                               value="<%=CONFIG.getGo(session)%>" class="Btn"></td>
                </tr>

                <% if (transId != null) {%>

                <tr>
                    <th scope="col"><%=CONFIG.getFrom(session)%></th>
                    <td><%=trans.getCustomerPayerName()%></td>
                    <td rowspan="6" >
                        <input type="button" name="btnSubmit" tabindex="3"
                               value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go( -1 );return true;">
                    </td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getTo(session)%></th>
                    <td><%=trans.getCustomerPayedName()%></td>
                </tr>
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
                    <td><%=myFormatter.format(trans.getAmount())%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getDate(session)%></th>
                    <td><%=trans.getDate()%></td>
                </tr>
                <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="<%=CONFIG.CONFIRM%>"
                       <%}%>

            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div></div><!-- End of Main body--></body></html>