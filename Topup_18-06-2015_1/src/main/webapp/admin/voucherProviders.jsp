<%-- 
    Document   : voucherProviders
    Created on : 10/03/2010, 02:22:23 م
    Author     : omar
--%>

<%@page import="com.masary.database.dto.CategoryDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.ProviderDTO"%>
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
            //MasaryManager.id = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
            List<ProviderDTO> list = MasaryManager.getInstance().getVoucherProviders();
            session = request.getSession();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getViewProviders(session)%></title>
    </head>
    <BODY class="body">

        <%if (session.getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>
    <div class="content_body"><br><br>
        <table >

            <%
                        for (ProviderDTO provider : list) {

            %>

            <tr >
                <th  scope="row" > <%=provider.getName()%></th>
                <%     for (CategoryDTO category : provider.getCategories()) {

                %>

            <tr >
                <th  scope="row" > <%=category.getName()%></th>

                <%
                         for (Object v : category.getAvilableValues()) {


                %>
                <td > <%=v%></td>
                <%}%>
            </tr>
            <%
                                        }%>
            </tr>
            <%
                        }%>



        </table>

        <!-- content body -->
    </div><!-- End of Table Content area-->
</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>
