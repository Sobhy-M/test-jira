<%--
    Document   : AssignBalance.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
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
    AgentDTO cust = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    List list = MasaryManager.getInstance().getRoles();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Manage Roles</title>
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
                        <a href="<%=CONFIG.APP_ROOT + "2.jsp"%>"><IMG SRC="img/home.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a><br>
                        <jsp:include page="../img/menuList.jsp"></jsp:include>
                    <a href="<%=CONFIG.APP_ROOT%>Login_Controller"><IMG SRC="img/logout.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>
                </div>
                <form name="ManageRoles" action="<%=CONFIG.APP_ROOT%>walletServices" method="post" >
                    <input type="hidden" name="action" value="<%=CONFIG.ACTION_MANAGE_ROLES%>" />
                    <div class="content_body"><br><br>
                        <table >
                            <tr>
                                <th scope="col">Manage Roles For <%=cust.getName()%> </th>
                            </tr>
                        </table><br><br>
                        <table >
                            <thead>
                                <tr>
                                    <th scope="col" colspan="2">Role Name</th>
                                    <th scope="col" >Granted</th>
                                </tr>
                            </thead>
                            <%
                                int i, length = list.size();
                                for (i = 0; i < length; i++) {
                                    String roleName = (String) list.get(i);
                            %>
                            <tbody>
                                <tr>
                                    <th  scope="row" colspan="2"> <%=roleName%></th>
                                    <%
                                        if (i < 5) {
                                    %>
                                    <td><input type="checkbox"  name="<%=roleName%>" disabled <%= cust.hasRole(roleName) ? "checked" : ""%> >     </td>
                                        <%                                    } else {
                                        %>
                                    <td><input type="checkbox"  name="<%=roleName%>" <%= cust.hasRole(roleName) ? "checked" : ""%>>      </td>
                                        <%                                }%>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                            <tr><td colspan="3" align="right"><input type="submit" value="ok"></td></tr>
                        </table>
                    </div><!-- End of Table Content area-->
                </form>
            </div><!-- End content body -->
            <div style="clear: both;">&nbsp;</div>
            <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->
    </body>
</html>






