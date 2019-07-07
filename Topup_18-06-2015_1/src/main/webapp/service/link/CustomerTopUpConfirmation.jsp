<%--
    Document   : ReCharge
    Created on : 27/06/2009, 01:09:17 م
    Author     : Melad
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.StudentDTO"%>

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
    double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), serviceId);
    double amount = (Double) session.getAttribute(CONFIG.PARAM_LINK_AMOUNT);
    String mail = (String) session.getAttribute(CONFIG.PARAM_LINK_MAIL);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>

<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">

        <title><%=serviceName%></title>
        <script type="text/javascript">
            var ray = {
                ajax: function(st)
                {
                    document.getElementById("btnSubmit").disabled = true;
                    this.show('load');
                },
                hide: function(st)
                {
                    var load = Document.getElementById("load");
                    load.style = "display:none;";
                },
                show: function(el)
                {
                    this.getID(el).style.display = '';
                },
                getID: function(el)
                {
                    return document.getElementById(el);
                }
            }
        </script>
        <style type="text/css">
            #load{
                position:absolute;
                top:50%;
                z-index:1;
                border:3px double #999;
                width:300px;
                height:300px;
                margin-top:-150px;
                margin-left:-150px;
                background:#ffffff;
                top:50%;
                left:50%;
                text-align:center;
                line-height:300px;
                font-family:"Trebuchet MS", verdana, arial,tahoma;
                font-size:18pt;
                background-image:url(https://cdn.e-masary.net/app/img/loading.gif);
                background-position:50% 40%;
            }
        </style>
    </head>
    <BODY class="body" onload="ray.hide();">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(request.getSession())%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>                <form name="AssignBalanceCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="POST" onsubmit="return ray.ajax()">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_LINK_TOPUP%>" />
        <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=serviceId%>" />
        <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="True" />
        <input type="hidden" name="<%=CONFIG.AMOUNT%>" value="True" />
        <div class="content_body" ><br><br>
            <th><H3><%=CONFIG.getConfirm(request.getSession()) + " " + serviceName%></H3></th>
            <table  >
                <tr >
                    <th scope="col" ><%=CONFIG.getID(request.getSession())%></th>
                    <td ><%=agent.getPin()%></td>
                    <th rowspan="4"><input type="submit" id="btnSubmit" name="btnSubmit" tabindex="3"
                                           value="<%=CONFIG.getConfirm(request.getSession())%>" class="Btn"></th>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getBalance(request.getSession())%></th>
                    <td><%=myFormatter.format(customerBalance - amount)%></td>
                </tr>
                <%if (agent.isAutoAllocate().equals("N")) {%>
                <tr>
                    <th scope="col" ><%=CONFIG.getServiceBalance(request.getSession())%></th>
                    <td colspan="2"><%=myFormatter.format(serviceBalance - amount)%></td>
                </tr>
                <%}%>
                <tr>
                    <th scope="col" ><%=CONFIG.getLinkWalletId(request.getSession())%></th>
                    <td><input type="text"  readonly="true" name="<%=CONFIG.PARAM_LINK_WALLET_ID%>" value="<%=request.getParameter(CONFIG.PARAM_LINK_WALLET_ID)%>"></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getEmail(request.getSession())%></th>
                    <td><%=mail%></td>
                </tr>
                <tr>
                    <th scope="col" ><%=CONFIG.getAmount(request.getSession())%></th>
                        <%if (amount == 0.0) {%>

                    <td><input type="text" name="<%=CONFIG.PARAM_LINK_AMOUNT%>"></td>

                    <%} else {%>

                    <td><%=amount%></td>
                    <% }%>

                    <th rowspan="4" ><input type="button" dir="rtl" name="btnSubmit" tabindex="3"
                                            value="<%=CONFIG.getBack(request.getSession())%>" class="Btn" onclick="history.go(-1);
                return true;"></th>
                </tr>
            </table>
        </div><!-- End of Table Content area-->
    </form>
    <!-- content body -->

</div>

<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>
