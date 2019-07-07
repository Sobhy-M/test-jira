<%-- 
    Document   : viewBulkResult
    Created on : Mar 5, 2014, 12:38:51 PM
    Author     : omnya
--%>

<%@page import="com.masary.database.dto.SellVoucherResponse"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.BulkVoucherDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ArrayList<BulkVoucherDTO> voucherInfo = (ArrayList<BulkVoucherDTO>) session.getAttribute("voucherInfoList");
    ArrayList<SellVoucherResponse> voucherList = (ArrayList<SellVoucherResponse>) session.getAttribute("voucherList");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="body" <%=voucherList.size() == 0 ? "" : "onload='form1.submit();'"%> > 
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         
        <%}%>
    </div>


    <div class="content_body"><br><br>
        <form  action="<%=CONFIG.PAGE_EXPORT_EXCEL%>" method="post" id="form1">    
            <table id="t1" border="1">
                <thead>
                <th> <%=CONFIG.getVoucherType(session)%></th>
                <th> <%=CONFIG.getAmount_V(session)%></th>
                <th><%=CONFIG.getVoucherNO(session)%></th>
                <th><%=CONFIG.getDone(session)%></th>
                <th><%=CONFIG.getError(session)%></th>
                </thead>
                <tbody>
                    <%
                        try {
                            for (int i = 0; i < voucherInfo.size(); i++) {
                    %>
                    <tr>
                        <td><%=voucherInfo.get(i).getService_Name()%></td>
                        <td><%=voucherInfo.get(i).getDenom()%></td>
                        <td><%=voucherInfo.get(i).getVoucherCount()%></td>
                        <td><%=voucherInfo.get(i).isIsFound() ? "<img alt='' height='42' width='42' src='img/Done.png' align='left' />" : "<img alt='' height='42' width='42' src='img/Not_Done.png' align='left' />"%></td>                       
                        <td><%=voucherInfo.get(i).isIsFound() ? "" : voucherInfo.get(i).getReason()%></td>
                    </tr>
                    <%  }
                        } catch (Exception ex) {
                            MasaryManager.logger.error(ex);
                        }
                    %>
                </tbody>
            </table>
        </form>
    </div>
    <br>
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>

