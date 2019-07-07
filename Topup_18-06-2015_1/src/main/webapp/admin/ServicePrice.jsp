<%-- 
    Document   : ServicePrice
    Created on : 25/09/2010, 11:18:21 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="java.util.List"%>

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
            List<ServiceDTO> serviceList = MasaryManager.getInstance().getAllServices();
            session = request.getSession();

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getServicePrice(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>


        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>

    </div>
    <form name="ChangePrice" action="<%=CONFIG.APP_ROOT%>admin" method="post">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_SERVICE_PRICE%>" />
        <div class="content_body"><br><br>
            <table >

                <tr>
                    <th colspan="3">
                        <%=CONFIG.getServices(session)%>
                    </th>
                </tr>
                <%
                            int i = 0;
                            for (ServiceDTO service : serviceList) {
                %>
                <tr>
                    <td>
                        <%=service.getStrServiceName(session)%>
                    </td>
                    <td>
                        <%=service.getPrice()%>
                    </td>
                    <td>
                        <input name="<%=service.getIdService()%>" type="submit" id="<%=service.getIdService()%>" value="<%=CONFIG.getChange(session)%>" tabindex="<%=5 + i%>">
                    </td>
                </tr>
                <%
                                i++;
                            }
                %>
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>