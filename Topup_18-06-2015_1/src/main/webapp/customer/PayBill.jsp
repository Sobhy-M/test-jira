
<%--
Document   : PayBill.jsp
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
        if (role == null ) {
            response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
            return;
        }
%>
<%
        String agentID = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
        List list = MasaryManager.getInstance().getMFIPill(agentID);
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Bills</title>
    </head>
    <BODY class="body">
      <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>
                <div class="content_body"><br><br>
                    <form action="<%=CONFIG.APP_ROOT%>walletServices" method="post">
                        <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_PAY_BILL%>" />
                        <table >
                            <thead>
                                <tr>
                                    <th scope="col">Biller Name</th>
                                    <th scope="col">Month</th>
                                    <th SCOPE="COL">Amount</th>
                                    <th SCOPE="COL">Action</th>
                                </tr>
                            </thead>
                            <%
        int i, length = list.size();
        for (i = 0; i < length; i++) {
            BillDTO bill = (BillDTO) list.get(i);
                            %>
                            <tbody>
                                <tr>
                                    <th  scope="row"> <%=bill.getBillerName()%></th>
                                    <td ><%=bill.getMonth()%></td>
                                    <td ><%=bill.getAmount()%></td>
                                    <td> <input type="submit" value="<%=CONFIG.PAY%>"  name="<%=bill.getId()%>">
                                    </td>
                                    <td>
                                        <%
        }
                                        %>
                                    </td>
                                </tr>
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
