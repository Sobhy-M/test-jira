<%--
    Document   : serviceAllocation.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>
<%@page import="com.masary.database.dto.CategoryDTO"%>
<%@page import="com.masary.database.dto.ProviderDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%

            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }

%>
<%

String agentId = null;
    if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).isShowTransfer()) {
        agentId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        agentId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }

            AgentDTO agent_V = MasaryManager.getInstance().getAgent_V(agentId);
            session = request.getSession();
            DecimalFormat myFormatter = CONFIG.getFormater(session);
            int serviceID = Integer.parseInt(request.getParameter("serv").toString());
            double servicevalue = Double.parseDouble(request.getParameter("value").toString());
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getManageCustomerBalance(session)%></title>



        <script type="text/javascript">
            function getQantity(price)
            {
                //  alert(price);
                //                if(document.AssignBalanceAgentService.<%=CONFIG.AMOUNT%>.value*price>document.getElementById("maxQuantity").innerHTML)
                //                {
                //                    alert("<%=CONFIG.getAmountHint(session)%>")
                //                    document.AssignBalanceAgentService.<%=CONFIG.AMOUNT%>.value=document.getElementById("maxQuantity").innerHTML
                //                }
                document.getElementById("quantity").innerHTML=document.AssignBalanceAgentService.<%=CONFIG.AMOUNT%>.value*price;
            }
        </script>



    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <div class="main_body" align="center">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div class="content_body"><br><br>
            <form name="AssignBalanceAgentService" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" >
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASSIGN_VOUCHER_TO_CUSTOMER%>"/>

                <table>
                    <tr>
                        <th><%=CONFIG.getServices(session)%></th>
                        <th><%=CONFIG.getVoucherCount(session)%></th>
                        <th><%=CONFIG.getVoucherAmount(session)%></th>
                        <th><%=CONFIG.getVoucherCustTo(session)%></th>
                    </tr>
                    <%
                                String custId = "";
                if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).isShowTransfer()) {
                    custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
                } else {
                    custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
                }
                                int VoucherCount = 0;
                                int ServiceId = 0;
                                ProviderDTO provider = null;

                                for (CustomerServiceDTO service : (List<CustomerServiceDTO>) agent_V.getServices()) {
                                    ServiceId = service.getServiceID();
                                    provider = MasaryManager.getInstance().getProviderForVoucherService_V(ServiceId, Integer.parseInt(custId));
                                    for (Object value : ((CategoryDTO) provider.getCategories().get(0)).getAvilableValues()) {
                                        VoucherCount = MasaryManager.getInstance().getvouchercount(ServiceId, Integer.parseInt(custId), Double.parseDouble(value.toString()));
                    %>
                    <tr>

                        <th><%=service.getServiceName(session)%> <%=value.toString()%></th>
                        <th><%=VoucherCount%></th>
                        <%if ((ServiceId == serviceID) & (Double.parseDouble(value.toString()) == servicevalue)) {%>
                        <input type="hidden" name="serv" value="<%=ServiceId%>"/>
                        <input type="hidden" name="value" value="<%=value%>"/>
                        <input type="hidden" name="available" value="<%=VoucherCount%>"/>
                        <td> <input type="text" value="" name="amount"></td>
                        <td> <input type="text" value="" name="Cust_Id"></td>
                        <td><input type="submit" name="<%= service.getServiceID()%>" value="<%=CONFIG.getAssignToAgent(session)%>" ></td>
                                <%}else{
                                %>
                        <td> <input type="text" value="" name="amount_<%=ServiceId%>" disabled></td>
                        <td> <input type="text" value="" name="Cust_Id_<%=ServiceId%>" disabled></td>
                        <td><input type="submit" name="<%= service.getServiceID()%>" value="<%=CONFIG.getAssignToAgent(session)%>" disabled></td>
                        <%
}%>
                </tr>
                    <%
                                    }
                                }%>
                </table>
            </form>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div>
</div><!-- End of Main body-->

</body>
</html>