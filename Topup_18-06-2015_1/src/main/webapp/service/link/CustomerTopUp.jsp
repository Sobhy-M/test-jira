<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="com.masary.database.dto.ProviderDTO"%>
<%@page import="com.masary.database.dto.CategoryDTO"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    int serviceId = 13;
    String serviceName =  MasaryManager.getInstance().getService(serviceId,((ArrayList<CustomerServiceDTO>) session.getAttribute("services"))).getServiceName(session);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=serviceName%></title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="BayCreditCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="post">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_LINK_TOPUP%>" />
        <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=serviceId%>" />
        <div class="content_body"  ><br><br>
            <th><H3><%=serviceName%></H3></th>
            <table>
                <tr>
                    <th scope="col" ><%=CONFIG.getBalance(request.getSession())%></th>
                    <td><%=myFormatter.format(customerBalance)%></td>
                    <th rowspan="7"><input type="submit" name="btnSubmit" tabindex="2"
                                           value="<%=CONFIG.getGo(request.getSession())%>" class="Btn"></th>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getLinkWalletId(request.getSession())%></th>
                    <td><input id="custTopUpMobileId" type="text" name="<%=CONFIG.PARAM_LINK_WALLET_ID%>" tabindex="1"></td>
                </tr>
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->
<br>
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>

