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
        String transId = (String) request.getSession().getAttribute(CONFIG.PARAM_Transaction_ID);
        TransactionDTO trans;
        try {
            trans = MasaryManager.getInstance().getTransaction(transId);
        } catch (Exception ex) {
            session.setAttribute("ErrorMessage",
                    "Detailed error code is:" + ex.getMessage());
            response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
            ex.printStackTrace();
            return;
        }
        DecimalFormat myFormatter = new DecimalFormat("###,###.###  EGP");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Transaction Report</title>
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
                    <a href="<%=CONFIG.APP_ROOT + "2.jsp"%>"><IMG SRC="img/home.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a><br>
                    <a href="<%=CONFIG.APP_ROOT%>Login_Controller"><IMG SRC="img/logout.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>
                </div>
                <div class="content_body"><br><br>
                    <table >
                        <tr>
                            <th scope="col">Transaction Number</th>
                            <td ><%=trans.getTransId()%> </td>
                            <th rowspan="5">
                                <form action="<%=CONFIG.APP_ROOT%>Web" method=post  name="admin_form" id="saveform">
                                    <input type="hidden" name="action" value="<%= CONFIG.ACTION_AGENT_OPERATIONS%>">
                                    <input type="hidden" value="View Customers" name="<%=CONFIG.PARAM_AGENT_BTN%>" />
                                    <input type="submit" name="btnSubmit" tabindex="5"
                                           value="OK" class="Btn">
                                </form>
                            </th>


                        </tr>
                        <tr>
                            <th scope="col">From</th>
                            <td><%=trans.getCustomerPayerName()%></td>
                        </tr>
                        <tr>
                            <th scope="col">To</th>
                            <td><%=trans.getCustomerPayedName()%></td>
                        </tr>

                           <tr>
                            <th scope="col">Type</th>
                            <td><%=trans.getType()%></td>
                        </tr>
                        <tr>
                            <th scope="col">Status</th>
                            <td><%=trans.getStatus()%></td>
                        </tr>
                  
                        <tr>
                            <th scope="col">Amount</th>
                            <td><%=myFormatter.format( trans.getAmount())%></td>
                        </tr>
                        <tr>
                            <th scope="col">Time</th>
                            <td><%=trans.getDate()%></td>

                        </tr>

                    </table>
                </div><!-- End of Table Content area-->
            </div><!-- End content body -->



            <div style="clear: both;">&nbsp;</div>

                        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->

    </body>
</html>



