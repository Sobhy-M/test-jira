<%--
    Document   : voucherProviders
    Created on : 10/03/2010, 02:22:23 م
    Author     : DaviD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AvilableVouchersDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null ) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }

%>
<%
           // MasaryManager.id = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
            List list = MasaryManager.getInstance().getAvilableVouchersDTO((String) request.getSession().getAttribute(CONFIG.PARAM_PIN));
            session = request.getSession();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getVoucherStatistics(session)%></title>
    </head>
    <BODY class="body">

        <%if (session.getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>
    <div class="content_body"><br><br>

        <table>
            <thead>
                <tr>
                    <th scope="col">Operator Id</th>
                    <th scope="col" width="150">Operator Name</th>
                    <th scope="col">Voucher Value</th>
                    <th SCOPE="COL">Avilable Vouchers</th>
                </tr>
            </thead>
            <tbody>
                <%
                for (int idx = 0; idx < list.size(); idx++) {
                                AvilableVouchersDTO vouchersDTO = (AvilableVouchersDTO)list.get(idx);

                %>
                <tr>
                    <th scope="col" width="150"><%=vouchersDTO.getProviderId()%></th>
                    <th scope="col"><%=vouchersDTO.getProviderName()%></th>
                    <th SCOPE="COL"><%=vouchersDTO.getVoucherValues()%></th>
                    <th SCOPE="COL"><%=vouchersDTO.getAvilableVouchers()%></th>
                </tr>
                <% }
                %>
            </tbody>
        </table>



        <!-- content body -->
    </div><!-- End of Table Content area-->
</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>