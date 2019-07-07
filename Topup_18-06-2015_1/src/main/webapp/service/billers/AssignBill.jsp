<%--
    Document   : AssignBill.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.*"%>
<%@page import="java.util.List"%>

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
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Assign Bills to customers</title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
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
                    <form name="AssignBill" action="<%=CONFIG.APP_ROOT%>Web" method="post" onsubmit="return validateBill() ">
                        <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASIGN_CUSTOMER_BILL%>" />
                        <table >
                            <tr>
                                <th scope="col">Name</th>
                                <td ><%=agent.getName(request.getSession())%> </td>
                                <th rowspan="5"><input type="submit" name="btnSubmit" tabindex="4"
                                                       value="Assign" class="Btn"></th>
                            </tr>
                            <tr>
                                <th scope="col">ID</th>
                                <td><%=agent.getPin()%></td>
                            </tr>
                            <tr>
                                <th scope="col">Amount</th>
                                <td><input type="text" name="<%=CONFIG.PARAM_BALANCE%>" tabindex="1"></td>
                            </tr>
                            <tr>
                                <th scope="col">Bill Month </th>
                                <td><input type="text" name="<%=CONFIG.PARAM_MONTH%>" tabindex="2"></td>
                            </tr>
                            <tr>
                                <th scope="col">Bill Year</th>
                                <td><input type="text" name="<%=CONFIG.PARAM_YEAR%>" tabindex="3"></td>
                            </tr>
                        </table>
                    </form>
                </div><!-- End of Table Content area-->
            </div><!-- End content body -->
            <div style="clear: both;">&nbsp;</div>
                        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->
    </body>
</html>






