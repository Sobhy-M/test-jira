<%-- 
    Document   : ViewCustomers.jsp
    Created on : 06/05/2009, 11:10:07 ص
    Author     : Melad
--%>
<%@page import="com.masary.database.dto.ServicesDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
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
            List list = MasaryManager.getInstance().getServices();
            DecimalFormat myFormatter = CONFIG.getFormater(session);
%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getViewCustomers(session)%></title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>                <div class="content_body"><br><br>
        <form action="<%=CONFIG.APP_ROOT%>admin" method="post">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_VIEW_ALL_SERVICES %>" />
            <table >
                <thead>
                    <tr>
                        <th scope="col"><%=CONFIG.getID(session)%></th>
                        <th scope="col" ><%=CONFIG.getName(session)%></th>
                        <th scope="col"><%=CONFIG.getBalance(session)%></th>
                        <th scope="col"><%=CONFIG.getOrderBalance(session)%></th>
                        <th scope="col"><%=CONFIG.getStopBalance(session)%></th>
                        <th SCOPE="COL"><%=CONFIG.getManageAccount(session)%></th>
                    </tr>
                </thead>
                <%
                            int i, length = list.size();
                            for (i = 0; i < length; i++) {
                                ServicesDTO service = (ServicesDTO) list.get(i);
                %>
                <tbody>
                    <tr>
                        <th  scope="row"> <%=service.getServiceID() %></th>
                        <td ><%=service.getServiceName() %></td>
                        <td ><%=service.getServiceBalance() %></td>
                        <td ><%=service.getServiceOrderBalance() %></td>
                        <td ><%=service.getServiceStopBalance() %></td>

                        <td>
                            <%
                                if (service.getServiceIsEnable()==1 ) {
                            %>

                            <input type="submit" onclick="return confirm('<%=CONFIG.getAreyouSure(session)%>');" value="<%=CONFIG.getDeactivate(session)%>" name="<%=service.getServiceID()%>">
                            <%
                                                                    } else {
                            %>
                            <input type="submit" onclick="return confirm('<%=CONFIG.getAreyouSure(session)%>');"  value="<%=CONFIG.getActivate(session)%>" name="<%=service.getServiceID() %>">
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