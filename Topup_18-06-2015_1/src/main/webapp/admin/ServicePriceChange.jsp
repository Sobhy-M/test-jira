<%-- 
    Document   : ServicePriceChange
    Created on : 25/09/2010, 02:47:00 م
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
            ServiceDTO service = MasaryManager.getInstance().getService((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID));

            session = request.getSession();

%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=service.getStrServiceName(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>


        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>

    </div>



    <form name="cHANGEsERVICEpRICE" action="<%=CONFIG.APP_ROOT%>admin" method="post" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_SERVICE_PRICE%>" />
        <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="<%=CONFIG.CONFIRM%>" />
        <div class="content_body"><br><br>
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getServiceName(session)%></th>
                    <td>
                        <%=service.getStrServiceName(session)%>
                    </td>
                    <th rowspan="4"><input type="submit" name="btnSubmit" tabindex="2" value="<%=CONFIG.getSave(session)%>" class="Btn" ></th>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getServicePrice(session)%></th>
                    <td><%=service.getPrice()%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getNewPrice(session)%></th>
                    <td><input  type="text" class="text" value="" name="<%=CONFIG.AMOUNT%>" tabindex="1" />
                    </td>
                </tr>

            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

