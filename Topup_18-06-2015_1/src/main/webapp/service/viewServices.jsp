<%--
    Document   : ViewSErvices.jsp
    Created on : 06/05/2009, 11:10:07 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
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
    List list = MasaryManager.getInstance().getAgents((String) request.getSession().getAttribute(CONFIG.PARAM_PIN));
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Agents</title>
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
                        <a href="<%=CONFIG.APP_ROOT + "1.jsp"%>"><IMG SRC="img/home.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a><br>
                        <jsp:include page="../img/menuList.jsp"></jsp:include>
                    <a href="<%=CONFIG.APP_ROOT%>Login_Controller"><IMG SRC="img/logout.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>
                </div>

                <div class="content_body"><br><br>
                    <form action="<%=CONFIG.APP_ROOT%>walletServices" method="post">
                        <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASIGN_AGENT_BALANCE%>" />
                        <table >
                            <thead>
                                <tr>
                                    <th scope="col">Agent Id</th>
                                    <th scope="col" width="150">Name</th>
                                    <th scope="col">Balance</th>
                                    <th SCOPE="COL">Assign Balance </th>
                                    <th SCOPE="COL">Manage Balance</th>

                                    <th SCOPE="COL">Action</th>
                                </tr>
                            </thead>
                            <%
                                int i, length = list.size();
                                for (i = 0; i < length; i++) {
                                    AgentDTO agent = (AgentDTO) list.get(i);
                            %>
                            <tbody>
                                <tr>
                                    <th  scope="row"> <%=agent.getPin()%></th>
                                    <td ><%=agent.getName(request.getSession())%></td>
                                    <td ><%=myFormatter.format(agent.getBalance())%></td>
                                    <td> <input type="submit" value="<%=CONFIG.ASSIGN%>"  name="<%=agent.getPin()%>">                                    </td>
                                    <td> <input type="submit" value="<%=CONFIG.MANAGE%>"  name="<%=agent.getPin()%>">                                    </td>
                                    <td>
                                        <%
                                            if (agent.isActive()) {
                                        %>

                                        <input type="submit" onclick="return confirm('Are you Sure ?');" value="<%=CONFIG.getDeactivate(request.getSession())%>" name="<%=agent.getPin()%>">
                                        <%
                                        } else {
                                        %>
                                        <input type="submit" onclick="return confirm('Are you Sure ?');"  value="<%=CONFIG.getActivate(request.getSession())%>" name="<%=agent.getPin()%>">
                                        <%
                                            }
                                        %>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </form>
                    <!-- content body -->
                </div><!-- End of Table Content area-->
            </div><!-- End content body -->



            <div style="clear: both;">&nbsp;</div>

            <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->

    </body>
</html>
