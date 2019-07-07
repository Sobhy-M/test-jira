<%-- 
    Document   : adminPortal
    Created on : 01/05/2010, 06:53:30 م
    Author     : Melad
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="java.util.List"%>



<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            session = request.getSession();
            String role = (String) session.getAttribute(CONFIG.PARAM_ROLE);
            if (role == null || !role.equals("1")) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }
            //String allBalances = MasaryManager.getInstance().getAllBalances();
            String allBalances = "";
            //String ntccBalance = MasaryManager.getInstance().getNTCCBalance();
//            String etisalatBalance = MasaryManager.getInstance().getEtisalatCredits();
            String etisalatBalance = "";
           // Thread.sleep(10000);
            List mobinilSMS = MasaryManager.getInstance().getLastSMS(10, "8");
            List vodafoneSMS = MasaryManager.getInstance().getLastSMS(10, "10");
            List vodafoneTransferSMS = MasaryManager.getInstance().getLastSMS(10, "11");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Admin Portal</title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include> <%}%>
    </div>
    <div class="content_body"><br><br>
        <table>
            <tr>
                <th>
                    All
                </th>
                <td><%=allBalances%></td>
            </tr>
            <tr>
                <th>
                    Etisalat
                </th>
                <td><%=etisalatBalance%></td>
            </tr>
            <tr>
                <th>
                    Mobinil
                </th>
                <td>
                    <table>
                        <tr><td></td></tr>
                        <% for (String sms : (List<String>) mobinilSMS) {

                        %><tr>
                            <td>
                                <%=sms%>
                            </td>
                        </tr><%}
                        %>
                    </table>
                </td>
            </tr>
            <tr>
                <th>
                    Vodafone
                </th>
                <td>
                    <table>
                        <%--<tr><td></td></tr>--%>
                        <% for (String sms : (List<String>) vodafoneSMS) {

                        %><tr>
                            <td>
                                <%=sms%>
                            </td>
                        </tr><%}
                        %>
                    </table>
                </td>
            </tr>
            <tr>
                <th>
                    Vodafone Transfer
                </th>
                <td>
                    <table>
                        <%--<tr><td></td></tr>--%>
                        <% for (String sms : (List<String>) vodafoneTransferSMS) {

                        %><tr>
                            <td>
                                <%=sms%>
                            </td>
                        </tr><%}
                        %>
                    </table>
                </td>
            </tr>
        </table>

    </div><!-- End content body -->

    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
<br>
</body>
</html>
