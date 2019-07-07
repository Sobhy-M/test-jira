<%--
    Document   : ViewTransaction.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>

<link href="img/style_login.css" rel="stylesheet" type="text/css">
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
            String transId = (String) session.getAttribute(CONFIG.PARAM_Transaction_ID);
            TransactionDTO trans;
            try {
                trans = MasaryManager.getInstance().getTransaction(transId);
            } catch (Exception ex) {
                session.setAttribute("ErrorMessage",
                        "Detailed error  is: " + ex.getMessage());
                response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
                ex.printStackTrace();
                return;
            }
            DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getTransactionReport(session)%></title>
    </head>
    <BODY >

        <table  <%=(session.getAttribute(CONFIG.lang).equals("ar")) ? "dir='rtl'" : ""%> >
            <tr>
                <td colspan="2" ><%=CONFIG.getTransactionNumber(session)%></td>
            </tr>
            <tr>
                <td colspan="2" class="log_in_text_box_user_mobile"><%=trans.getTransId()%> </td>
            </tr>
            <tr>
                <td colspan="2"><%=CONFIG.getFrom(session)%></td>
            </tr>
            <tr>
                <td colspan="2" class="log_in_text_box_user_mobile"><%=trans.getCustomerPayerName()%></td>
            </tr>
            <tr>
                <td colspan="2">To</td>
            </tr>
            <tr>
                <td colspan="2" class="log_in_text_box_user_mobile"><%=trans.getCustomerPayedName()%></td>
            </tr>
            <tr>
                <td colspan="2"><%=CONFIG.getType(session)%></td>
            </tr>
            <tr>
                <td colspan="2" class="log_in_text_box_user_mobile"><%=trans.getType()%></td>
            </tr>
            <tr>
                <td colspan="2"><%=CONFIG.getStatus(session)%></td>
            </tr>
            <tr>
                <td colspan="2" class="log_in_text_box_user_mobile"><%=trans.getStatus()%></td>
            </tr>
            <tr>
                <td colspan="2"><%=CONFIG.getAmount(session)%></td>
            </tr>
            <tr>
                <td  colspan="2" class="log_in_text_box_user_mobile"><%=myFormatter.format(trans.getAmount())%></td>
            </tr>
            <tr>
                <td colspan="2"><%=CONFIG.getDate(session)%></td>
            </tr>
            <tr>
                <td colspan="2" class="log_in_text_box_user_mobile"><%=trans.getDate()%></td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <form action="<%=CONFIG.APP_ROOT%>Web" method=post  name="admin_form" id="saveform">
                        <input type="hidden" name="action" value="<%=CONFIG.ACTION_MAIN%>" />
                        <input type="submit" name="btnSubmit" tabindex="5"
                               value="OK" class="Btn">
                    </form>
                </td>
            </tr>

        </table>
    </body>
</html>



