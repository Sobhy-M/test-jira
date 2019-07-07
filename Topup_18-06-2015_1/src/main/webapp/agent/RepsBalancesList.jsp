<%-- 
    Document   : RepsBalancesList
    Created on : Sep 12, 2012, 6:05:30 PM
    Author     : Hammad
--%>

<%@page import="java.util.Vector"%>
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
    if (role == null || !role.equals("2")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    Vector<String[]> repsData = MasaryManager.getInstance().getAllRepsData();

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
    </div>                
    <div class="content_body"><br><br>
        <form name="repPage" action="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_ASSIGN_REP_NEW_BALANCE" method="post">

            <table>
                <tr>
                    <th>رقم المندوب</th><th>اسم المندوب</th><th>الرصيد المتاح بالجنيه المصري</th>
                </tr>
                <%
                    String[] dataa = null;
                    for (int i = 0; i < repsData.size(); i++) {
                        dataa = repsData.get(i);
                %>
                <tr>
                    <td>
                        <%=dataa[0]%>
                    </td>
                    <td>
                        <%=dataa[1]%>
                    </td>
                    <td>
                        <%=dataa[2]%>
                    </td>
                    <td>
                        <input type="submit" value="اعادة تخصيص الرصيد" name="<%=dataa[0]%>"/>
                    </td>
                </tr>
                <%}%>
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