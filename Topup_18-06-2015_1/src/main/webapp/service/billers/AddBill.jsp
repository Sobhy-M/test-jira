<%--
    Document   : AddBill.jsp
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
        //MasaryManager.id = "";
        List list = MasaryManager.getInstance().getAgents("1");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Add Bills to customers</title>
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


                <div class="content_body"><br><br>
                    <form action="<%=CONFIG.APP_ROOT%>Web" method="post">
                        <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASIGN_CUSTOMER_BILL%>" />
                        <table >
                            <thead>
                                <tr>
                                    <th scope="col">Customer Key</th>
                                    <th scope="col" >Name</th>
                                    <th scope="col" >Mobile</th>
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
                                    <td ><%=agent.getPhone()%></td>
                                    <td> <input type="submit" value="<%=CONFIG.ASSIGN_BILL%>"  name="<%=agent.getPin()%>">
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
           <br><br>
           <div style="clear: both;">&nbsp;</div>
                        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->
    </body>
</html>
