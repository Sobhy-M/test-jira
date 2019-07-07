<%--
    Document   : serviceAllocation.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.LoginDto"%>
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
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    int serviceID = Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID));
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    ArrayList<CustomerServiceDTO> serviceList = (ArrayList<CustomerServiceDTO>) session.getAttribute("services");
    double masaryBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getManageCustomerBalance(session)%></title>



        <script type="text/javascript">
            function roundNumber(rnum, rlength) { // Arguments: number to round, number of decimal places
                var newnumber = Math.round(rnum * Math.pow(10, rlength)) / Math.pow(10, rlength);
                return parseFloat(newnumber)
            }
            function getQantity(price)
            {
                if ((<%=serviceID%> == 1000) && !<%=MasaryManager.getInstance().IsNormalCust(Integer.parseInt(agent.getPin()))%>) {
                    var amoun = ((document.AssignBalanceAgentService.tony.value * price) * 100) / 97.5;
                    var integernumber = roundNumber(amoun, 2);
                    var fract = amoun - integernumber;
                    if (fract > 0) {
                        amoun = roundNumber((integernumber + 0.01), 2);
                    } else {
                        amoun = integernumber;
                    }
                    document.getElementById("quantity").innerHTML = amoun;
                    document.getElementById("hidden").value = amoun;
                } else {
                    document.getElementById("quantity").innerHTML = document.AssignBalanceAgentService.tony.value * price;
                    document.getElementById("hidden").value = document.AssignBalanceAgentService.tony.value * price;
                }
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
                <form name="AssignBalanceAgentService" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" onsubmit="return validateServiceAssign()" >
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASSIGN_AGENT_SERVICE_BALANCE%>"/>
                <table >
                    <tr>
                        <th colspan="2"><%=CONFIG.getServiceName(session)%></th>
                        <th ><%=CONFIG.getServiceBalance(session)%></th>
                        <th><%=CONFIG.getMaxQuantaty(session)%></th>
                        <th ><%=CONFIG.getAmount(session)%></th>
                        <th ><%=CONFIG.getCost(session)%></th>
                    </tr>
                    <%
                        for (CustomerServiceDTO service : serviceList) {
                    %>
                    <tr>
                        <th colspan="2"><%=service.getServiceName(session)%></th>
<!--                        <td><%=myFormatter.format(service.getServiceBalance())%></td>-->
                        <%if (service.getServiceID() == 1000) {%>
                        <td><%=service.getServiceBalance()%></td>
                        <%} else {%>
                        <td><%=service.getServiceBalance()%></td>
                        <%
                            }
                            if (service.getServiceID() == serviceID) {
                        %>
                        <%if (serviceID == 1000) {%>
                        <td id="maxQuantity"><%=masaryBalance / service.getPrice()%></td>
                        <%} else {%>
                        <td id="maxQuantity"><%=Math.floor(masaryBalance / service.getPrice())%></td>
                        <%}%>
                        <td>
                            <input type="text" name="tony" onkeyup="getQantity(<%=service.getPrice()%>);"/>
                            <input type="hidden" name="<%=CONFIG.AMOUNT%>" value="" id="hidden"/>
                            <div id="BalanceDiv"></div>
                        </td>
                        <td id="quantity">0</td>
                    </tr>
                    <%
                    } else {
                    %>
                    <td colspan="2"></td>   <%                    }
                        }%>
                    <tr><td colspan="6"><input type="submit" value="<%=CONFIG.getSave(session)%>"/> </td></tr>
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






