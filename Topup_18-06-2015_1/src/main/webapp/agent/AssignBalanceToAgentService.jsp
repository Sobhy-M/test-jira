<%--
    Document   : serviceAllocation.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.CustomerDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="com.masary.database.dto.CustomerDTO"%>

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
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    int serviceID = Integer.parseInt((String) request.getSession().getAttribute(CONFIG.PARAM_SERVICE_ID));
    AgentDTO agent = null;
    double serviceBalance = 0.0;
    String serviceName = "";
    double customerBalance = 0.0;
    try {
        agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
        serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), serviceID);
        serviceName = MasaryManager.getInstance().getService(serviceID, ((ArrayList<CustomerServiceDTO>) session.getAttribute("services"))).getServiceName(session);
        customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
    } catch (Exception e) {
        MasaryManager.logger.error(e.getMessage());
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAssignToAgent(session)%></title>
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
                <form name="AssignBalanceAgent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" onsubmit="return validateBalance()">
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASIGN_AGENT_SERVICE_BALANCE%>" />
                <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="<%=CONFIG.CONFIRM%>" />
                <input type="hidden" value="<%=serviceID%>"   name="<%=CONFIG.PARAM_SERVICE_ID%>">
                <table >
                    <tr >
                        <th scope="col" colspan="2"><%=CONFIG.getName(session)%></th>
                        <td colspan="2"><%=agent.getName(session)%> </td>
                        <th rowspan="6"  ><input type="submit" name="btnSubmit" tabindex="5" id="btnSubmit"
                                                 value="<%=CONFIG.getAssign(session)%>" class="Btn"></th>
                    </tr>
                    <tr>
                        <th scope="col" colspan="2"><%=CONFIG.getID(session)%></th>
                        <td colspan="2"><%=agent.getPin()%></td>
                    </tr>
                    <tr>
                        <th scope="col" colspan="2"><%=CONFIG.getBalance(session)%></th>
                        <td colspan="2"><%=myFormatter.format(customerBalance)%></td>
                    </tr>
                    <tr>
                        <th scope="col" colspan="2" ><%=serviceName%></th>
                        <td colspan="2"><%=myFormatter.format(serviceBalance)%></td>
                    </tr>
                    <tr>
                        <th scope="col" colspan="2" ><%=CONFIG.getTo(session)%> (<%=CONFIG.getID(session)%>)</th>
                        <td colspan="2" ><input type="text" id="to" name="<%=CONFIG.PARAM_PAYED_PIN%>" tabindex="1">
                        </td>
                    </tr>
                    <tr>
                        <th scope="col" colspan="2"><%=CONFIG.getAddBalance(session)%></th>
                        <td colspan="2"><input  id="addBal" type="text" tabindex="2" name="<%=CONFIG.PARAM_BALANCE%>">
                            <div id="balDiv"></div>
                        </td>
                    </tr>
                </table>
            </form>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>