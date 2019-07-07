<%--
    Document   : serviceAllocation.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
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
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    AgentDTO agent_V = agent_V = MasaryManager.getInstance().getAgent_V(agent.getPin());
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getManageCustomerBalance(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

    <%if (agent.isAutoAllocate().equals("Y")) {%>
    <div class="content_body">
        <form name="AssignBalanceAgent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" >
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_MANAGE_AGENT_SERVICE_BALANCE%>"/>
            <p style="font-size: large"> <%=CONFIG.getVoucherTitleAlign(session)%></p>
            <table>
                <tr>
                    <th><%=CONFIG.getServices(session)%></th>
                    <th><%=CONFIG.getServicePrice(session)%></th>
                    <th><%=CONFIG.getMaxQuantaty(session)%></th>
                    <th colspan="3"><%=CONFIG.getServiceBalance(session)%></th>
                </tr>
                <%
                    for (CustomerServiceDTO service : (List<CustomerServiceDTO>) agent.getServices()) {
                        if (service.getServiceID() == 1000 || (service.getServiceID() == 1) || (service.getServiceID() == 15) || (service.getServiceID() == 16) || (service.getServiceID() == 17)) {
                %>
                <tr>
                    <th><%=service.getServiceName(session)%></th>
                    <th><%=service.getPrice()%></th>
                    <th><%=Math.floor(agent.getServiceBalance(1) / service.getPrice())%></th>
                    <td><%=service.getServiceBalance()%></td>
                    <%if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).isShowTransfer()) {%>
                    <td> <input type="submit" value="<%=CONFIG.getAssign(session)%>" <%=((service.getServiceID() != 0)) ? "disabled" : ""%>   name="<%=service.getServiceID()%>">                                    </td>
                        <%} else {%>       
                    <td> <input type="submit" value="<%=CONFIG.getAssign(session)%>" <%=((service.getServiceID() == 1) || (service.getServiceID() == 15) || (service.getServiceID() == 16) || (service.getServiceID() == 17) || (agent.isAutoAllocate().equals("Y") && service.getServiceID() != 1000)) ? "disabled" : ""%>   name="<%=service.getServiceID()%>">                                    </td>
                    <%}%>
                </tr>
                <%
                        }
                    }%>
            </table>
        </form>
        <p style="font-size: large"> <%=CONFIG.getVoucherTitle(session)%></p>
        <table>
            <tr>
                <th><%=CONFIG.getServices(session)%></th>
                <th colspan="3"><%=CONFIG.getVoucherCount(session)%></th>
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
                <td>
                    <%if (role.equals("2") || (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).isShowTransfer())) {%>
                    <a href="<%=CONFIG.APP_ROOT%>walletServices?action=<%=CONFIG.ACTION_MANAGE_AGENT_SERVICE_BALANCE_V%>&serv=<%=service.getServiceID()%>&value=<%=value%>"><input type="button"  name="<%= service.getServiceID()%>" value="<%=CONFIG.getAssignToAgent(session)%>" ></a>
                        <%}%>
                </td>
            </tr>
            <%
                    }
                }%>
        </table>
    </div><!-- End of Table Content area-->
    <%} else {%>
    <div class="content_body">
        <form name="AssignBalanceAgent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" >
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_MANAGE_AGENT_SERVICE_BALANCE%>"/>
            <p style="font-size: large"> <%=CONFIG.getVoucherTitleAlign(session)%></p>
            <table>
                <tr>
                    <th><%=CONFIG.getServices(session)%></th>
                    <th><%=CONFIG.getServicePrice(session)%></th>
                    <th><%=CONFIG.getMaxQuantaty(session)%></th>
                    <th colspan="4"><%=CONFIG.getServiceBalance(session)%></th>
                </tr>
                <%
                    for (CustomerServiceDTO service : (List<CustomerServiceDTO>) agent.getServices()) {
                        if (service.isBill() == 0) {
                %>
                <tr>
                    <th><%=service.getServiceName(session)%></th>
                    <th><%=service.getPrice()%></th>
                    <th><%=Math.floor(agent.getServiceBalance(1) / service.getPrice())%></th>
                    <td><%=myFormatter.format(service.getServiceBalance())%></td>
                    <%if (service.getServiceID() == 1000) {%>
                    <td><%=service.getServiceBalance()%></td>
                    <%} else {%>
                    <td><%=myFormatter.format(service.getServiceBalance())%></td>
                    <%}%>
                    <%if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).isShowTransfer()) {%>
                    <td> <input type="submit" value="<%=CONFIG.getAssign(session)%>" <%=((service.getServiceID() != 0)) ? "disabled" : ""%>   name="<%=service.getServiceID()%>">                                    </td>
                        <%} else {%>       
                    <td> <input type="submit" value="<%=CONFIG.getAssign(session)%>" <%=((service.getServiceID() == 1) || (service.isVoucher() == 1) || (agent.isAutoAllocate().equals("Y") && service.getServiceID() != 1000)) ? "disabled" : ""%>   name="<%=service.getServiceID()%>">  </td>
                        <%}%>
                        <%  if (role.equals("2") || (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).isShowTransfer())) {%>
                    <td> <input type="submit" value="<%=CONFIG.getAssignToAgent(session)%>" <%=((service.getServiceID() == 1) || (service.isVoucher() == 1) || (agent.isAutoAllocate().equals("Y") && service.getServiceID() != 1000)) ? "disabled" : ""%>   name="<%=service.getServiceID()%>"> </td>
                        <%}%>
                </tr>
                <%
                        }
                    }%>
            </table>
        </form>
        <p style="font-size: large"> <%=CONFIG.getVoucherTitle(session)%></p>
        <table>
            <tr>
                <th><%=CONFIG.getServices(session)%></th>
                <th colspan="3"><%=CONFIG.getVoucherCount(session)%></th>
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
                <td>
                    <%if (role.equals("2") || (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).isShowTransfer())) {%>
                    <a href="<%=CONFIG.APP_ROOT%>walletServices?action=<%=CONFIG.ACTION_MANAGE_AGENT_SERVICE_BALANCE_V%>&serv=<%=service.getServiceID()%>&value=<%=value%>"><input type="button"  name="<%= service.getServiceID()%>" value="<%=CONFIG.getAssignToAgent(session)%>" ></a>
                        <%}%>
                </td>
            </tr>
            <%
                    }
                }%>
        </table>
    </div><!-- End of Table Content area-->
    <%}%>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div>
</div><!-- End of Main body-->
</body>
</html>