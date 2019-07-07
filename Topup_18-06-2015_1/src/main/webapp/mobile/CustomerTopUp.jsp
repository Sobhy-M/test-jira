<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.ProviderDTO"%>
<%@page import="com.masary.database.dto.CategoryDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>

<%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN_MOBILE);
                return;
            }
%>
<%
            session = request.getSession();
            DecimalFormat myFormatter = CONFIG.getFormater(session);
            AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
            int serviceId = Integer.parseInt((String) request.getSession().getAttribute(CONFIG.PARAM_SERVICE_ID));
            ArrayList<CustomerServiceDTO> serviceList = (ArrayList<CustomerServiceDTO>) session.getAttribute("services");
            CustomerServiceDTO service = MasaryManager.getInstance().getService(serviceId, serviceList);
            double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), serviceId);
            double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
            ProviderDTO provider = null;
            if (serviceId >= 14) {
                provider = MasaryManager.getInstance().getProviderForVoucherService(serviceId);
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <link href="img/jq/theme.min.css" rel="stylesheet" type="text/css">
        <title><%=service.getServiceName(session)%></title>
    </head>
    <BODY dir="<%=CONFIG.getDirection(session)%>">
        <div>
            <div class="toolbar">
                <h1><%=service.getServiceName(session)%></h1>
            </div>
            <form name="BayCreditCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="POST" onsubmit="return validateBayCreditCustomer()">
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_TOPUP%>" />
                <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=serviceId%>" />
                <ul style="text-align:<%=CONFIG.getTextDirection(session)%>">
                    <li><%=CONFIG.getBalance(session)%>
                        <%=myFormatter.format(customerBalance)%></li>
                    <%if (agent.isAutoAllocate().equals("N")) {%>
                    <li><%=service.getServiceName(session)%>  
                        <%=myFormatter.format(serviceBalance)%></li>
                        <%}%>
                        <%if (provider != null) {%>
                    <li><%=CONFIG.getAmount(session)%></li>
                    <li>
                        <select name="<%=CONFIG.AMOUNT%>" id="custTopUpDalanceID"  >
                            <%
                                for (Object value : ((CategoryDTO) provider.getCategories().get(0)).getAvilableValues()) {%>
                            <option value="<%=value%>"><%=value%></option>
                            <%}%>
                        </select>
                    </li>
                    <li>
                        <select name="<%=CONFIG.PARAM_CATEGORY_ID%>">
                            <%
                                for (CategoryDTO category : provider.getCategories()) {%>
                            <option value="<%=category.getId()%>"><%=category.getName()%></option>
                            <%}%>
                        </select>
                    </li>
                    <%} else if (serviceId != 13) {%>
                    <li><%=CONFIG.getAmount(session)%></li>
                    <li><input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1" id="custTopUpDalanceID"  />                    </li>
                        <%}%>
                    <div id="custTopUpDalanceDiv"></div>
                    <%if (serviceId == 1) {%>
                    <li><%=CONFIG.getTo(session)%> (<%=CONFIG.getID(session)%>) </li>
                    <%} else if (serviceId == 12) {%>
                    <li><%=CONFIG.getTo(session)%> (<%=CONFIG.getPhoneNumber(session)%>)</li>
                    <%} else if (serviceId == 13) {%>
                    <input type="hidden" name="action" value="<%=CONFIG.ACTION_LINK_TOPUP%>" />
                    <input type="hidden" name="<%=CONFIG.AMOUNT%>" id="custTopUpDalanceID" value="-1" />
                    <li><%=CONFIG.getLinkWalletId(session)%></li>
                    <%} else {%>
                    <li><%=CONFIG.getTo(session)%> (<%=CONFIG.getMobileNumber(session)%>)</li>
                    <%}%>
                    <li><input type="text" name="<%=CONFIG.PARAM_MSISDN%>"  tabindex="2"></li>
                    <%if (agent.isAutoAllocate().equals("N") && serviceId != 1 && serviceId != 13) {%>
                    <li><%=CONFIG.getTo(session)%> (<%=CONFIG.getAnotherAccount(session)%>)</li>
                    <li><input type="text" name="<%=CONFIG.PARAM_PAYED_PIN%>"  tabindex="3"></li>
                    <%} else {%><input type="hidden" name="<%=CONFIG.PARAM_PAYED_PIN%>"><%}%>
                    <li style="height: 29px">
                        <input type="submit" style="width: 49%;float: right;" name="btnSubmit" tabindex="3" value="<%=CONFIG.getGo(session)%>" >
                        </form>
                        <form action="<%=CONFIG.APP_ROOT%>Web" >
                            <input type="hidden" name="action" value="<%=CONFIG.ACTION_MAIN%>" />
                            <input type="submit" style="width: 49% ;float: left;" value="<%=CONFIG.getBack(session)%>">
                        </form>
                    </li>
                </ul>
                <div class="info">
                    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
                </div>
        </div>
        <br>
    </body>
</html>