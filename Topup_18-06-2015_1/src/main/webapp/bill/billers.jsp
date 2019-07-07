<%-- 
    Document   : billers
    Created on : May 30, 2012, 11:06:24 AM
    Author     : KEMO
--%>

<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Bill_Biller"%>
<%@page import="com.masary.Bill_Manager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<!DOCTYPE html>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    String From = "";
    String To = "";
            session = request.getSession();
            String custId = null;
            if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")){
                custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
            }else{
                custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
            }             
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        
        <table>
            <%
                Bill_Manager manager = new Bill_Manager();
                for (Masary_Bill_Biller biller : manager.sendbillerinquery()) {
            %>
            <tr><td colspan="8"><h2 style="text-align: center"><%=biller.getBILLER_NAME()%></h2></td></tr>
            <tr>
                <th><%= CONFIG.getServiceName(session)%></th>
                <th><%= CONFIG.getPMT_Type(session)%></th>
                <th><%= CONFIG.getSERVICE_TYPE(session)%></th>
                <th><%= CONFIG.getBILL_LABLE(session)%></th>
                <th><%=CONFIG.getFraction(session) %></th>
                <th><%=CONFIG.getPartial(session) %></th>
                <th><%=CONFIG.getOver(session) %></th>
                <th><%=CONFIG.getAdvanced(session) %></th>
            </tr>
            <%for (Masary_Bill_Type type : biller.getMasary_bill_types()) {%>
            <form name="BillTransactions" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST">
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_ADD_BTC%>" />
                <input type="hidden" name="BILLER_ID" value="<%=type.getBILLER_ID() %>" />
                <input type="hidden" name="BILL_TYPE_ID" value="<%=type.getBILL_TYPE_ID()%>" />
                <input type="hidden" name="BILL_NAME" value="<%=type.getBILL_NAME() %>" />
                <input type="hidden" name="PMT_TYPE" value="<%=type.getPMT_TYPE() %>" />
                <input type="hidden" name="SERVICE_TYPE" value="<%=type.getSERVICE_TYPE() %>" />
                <input type="hidden" name="SERVICE_NAME" value="<%=type.getSERVICE_NAME() %>" />
                <input type="hidden" name="BILL_LABLE" value="<%=type.getBILL_LABLE() %>" />
                <input type="hidden" name="IS_FRAC_ACC" value="<%=type.isIS_FRAC_ACC() %>" />
                <input type="hidden" name="IS_PART_ACC" value="<%=type.isIS_PART_ACC() %>" />
                <input type="hidden" name="IS_OVER_ACC" value="<%=type.isIS_OVER_ACC() %>" />
                <input type="hidden" name="IS_ADV_ACC" value="<%=type.isIS_ADV_ACC() %>" />
            <tr>
                <td><%=type.getBILL_NAME()%></td>
                <td><%=type.getPMT_TYPE()%></td>
                <td><%=type.getSERVICE_TYPE()%></td>
                <td><%=type.getBILL_LABLE()%></td>
                <td><input type="checkbox" name="checkname" onClick="return false;" <%=type.isIS_FRAC_ACC() ? "checked" : ""%>></td>
                <td><input type="checkbox" name="checkname" onClick="return false;" <%=type.isIS_PART_ACC() ? "checked" : ""%>></td>
                <td><input type="checkbox" name="checkname" onClick="return false;" <%=type.isIS_OVER_ACC() ? "checked" : ""%>></td>
                <td><input type="checkbox" name="checkname" onClick="return false;" <%=type.isIS_ADV_ACC() ? "checked" : ""%>></td>
                <%if(MasaryManager.getInstance().bill_found(type.getBILL_TYPE_ID())==0){%><td><input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getAdd(session)%>" class="Btn"></td><%}%>
            </tr>
            </form>
            <%}
                }%>
        </table>
    
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>