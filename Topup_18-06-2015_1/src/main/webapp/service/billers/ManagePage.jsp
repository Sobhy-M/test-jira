<%--
    Document   : ManagePage.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
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
    DecimalFormat myFormatter = new DecimalFormat("###,###.###  EGP");
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Manage Your Account</title>
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
                <form name="Manage agent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" >
                    <input type="hidden" name="action" value="<%=CONFIG.ACTION_MANAGE_AGENT%>" />
                    <div class="content_body"><br><br>
                        <table >
                            <tr>
                                <th scope="col">Name</th>
                                <td><%=agent.getName(request.getSession())%></td>
                                <th rowspan="5"><input type="submit" name="btnSubmit" tabindex="3"
                                                       value="Change" class="Btn"></th>
                            </tr>
                            <tr>
                                <th scope="col">Pin</th>
                                <td><%=agent.getPin()%></td>
                            </tr>
                            <tr>
                                <th scope="col">Balance</th>
                                <td><%=myFormatter.format(customerBalance)%></td>
                            </tr>
                            <tr>
                                <th scope="col">New Password</th>
                                <td><input type="password" class="text"  name="<%=CONFIG.PARAM_PASSWORD%>" tabindex="1" /></td>
                            </tr>
                            <tr>
                                <th scope="col">Confirm Password</th>
                                <td><input type="password" class="text"  name="<%=CONFIG.PARAM_CONFIRM_PASSWORD%>" tabindex="2" /></td>
                            </tr>
                        </table>
                    </div><!-- End of Table Content area-->
                </form>
            </div><!-- End content body -->
            <div style="clear: both;">&nbsp;</div>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->
    </body>
</html>


