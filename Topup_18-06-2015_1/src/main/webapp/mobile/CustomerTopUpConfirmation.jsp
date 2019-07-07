<%--
    Document   : ReCharge
    Created on : 27/06/2009, 01:09:17 م
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.CustomerDTO"%>
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
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
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
    int serviceId = Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID));
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    ArrayList<CustomerServiceDTO> services = (ArrayList<CustomerServiceDTO>) session.getAttribute("services");
    CustomerServiceDTO service = MasaryManager.getInstance().getService(serviceId, services);
    double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), serviceId);
    double cash = MasaryManager.getInstance().getCash(String.valueOf(12), request.getParameter(CONFIG.AMOUNT));
    String payedID = null;
    payedID = request.getParameter(CONFIG.PARAM_PAYED_PIN);
    CustomerDTO payedAccount = null;
    if (!payedID.isEmpty()) {
        payedAccount = MasaryManager.getInstance().getCustomerInfo(payedID);
        request.removeAttribute(CONFIG.PARAM_MSISDN);
    }
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getConfirm(session)%>  <%=service.getServiceName(session)%></title>
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
                top:10%;
                background-color:#ffffff;
                z-index:1;
                border:3px double #999;
                width:100%;
                height:100%;
                text-align:center;                                
                background-image:url(img/LOADING.JPG) ;
                background-repeat:no-repeat;
                background-position:center;
            }
        </style>

        <link href="img/jq/theme.min.css" rel="stylesheet" type="text/css">
    </head>
</head>

<BODY class="body" onload="ray.hide();" dir="<%=CONFIG.getDirection(session)%>">
    <script type="text/javascript" src="img/CheckForms.js"></script>
    <div>
        <div class="toolbar">
            <h1><%=CONFIG.getConfirm(session)%>  <%=service.getServiceName(session)%></h1>
        </div>
        <div id="load"  style="display:none;"><%=CONFIG.getPleaseWait(session)%></div>
        <form name="AssignBalanceCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="POST">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_TOPUP%>" />
            <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=serviceId%>" />
            <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="True" />
            <input type="hidden" value="<%=request.getParameter(CONFIG.PARAM_CATEGORY_ID)%>" name="<%=CONFIG.PARAM_CATEGORY_ID%>" >
            <ul style="text-align:<%=CONFIG.getTextDirection(session)%>">
                <li>
                    <%=CONFIG.getBalance(session)%>               
                    <%=myFormatter.format(customerBalance - Double.parseDouble(request.getParameter(CONFIG.AMOUNT)))%>
                </li>
                <%if (agent.isAutoAllocate().equals("N")) {%>
                <li>
                    <%=service.getServiceName(session)%> <%=CONFIG.getBalance(session)%>
                    <%=myFormatter.format(serviceBalance - Double.parseDouble(request.getParameter(CONFIG.AMOUNT)))%>
                </li>
                <%}%>
                <li>
                    <%=CONFIG.getAmount(session)%>
                </li>
                <% if (serviceId != 13) {%>
                <li>
                    <input type="text" style="background-color: #EDEDED" readonly="true" name="<%=CONFIG.AMOUNT%>" value="<%=request.getParameter(CONFIG.AMOUNT)%>">
                </li>
                <%} else {
                %>
                <li>
                    <input type="text"  <%=(Double) session.getAttribute(CONFIG.PARAM_LINK_AMOUNT) == 0.0 ? "" : "style=\"background-color: #EDEDED\" readonly"%> name="<%=CONFIG.PARAM_LINK_AMOUNT%>" value="<%=session.getAttribute(CONFIG.PARAM_LINK_AMOUNT)%>">
                </li>
                <li><%=CONFIG.getEmail(session)%></li>
                <li><input type="text" style="background-color: #EDEDED"  readonly value="<%=session.getAttribute(CONFIG.PARAM_LINK_MAIL)%>" ></li>
                    <%}
                    if (serviceId == 12) {%>
                <li>
                    <%=CONFIG.getCashAmount(session)%>
                </li>
                <li><%=myFormatter.format(cash)%></li>
                    <%}%>
                <li><%=CONFIG.getTo(session)%> </li>
                <li><input style="background-color: #EDEDED" type="text"readonly="true" name="<%=CONFIG.PARAM_MSISDN%>"
                           value="<%=(payedAccount == null ? request.getParameter(CONFIG.PARAM_MSISDN) : payedAccount.getCustomerName(session))%>">
                    <input type="hidden" name="<%=CONFIG.PARAM_PAYED_PIN%>" value="<%=payedID%>">
                </li>
                <li>
                    <input type="submit" name="btnSubmit" id="btnSubmit" style="width: 49%" tabindex="3" value="<%=CONFIG.getConfirm(session)%>"
                           onclick="this.disabled = true; this.form.submit();" >
                    <input type="button" name="btnBack" style="width: 49%" tabindex="4" value="<%=CONFIG.getBack(session)%>" onclick="history.go(-1);
                return true;">
                </li>
            </ul>
        </form>
        <div class="info">
            <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
        </div>
    </div>
    <br>
</body>
</html>
