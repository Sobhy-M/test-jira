
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    DecimalFormat myFormatter = new DecimalFormat("###,###.###  EGP");
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double etisalatTransferBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 7);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <title>بيع رصيد</title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="AssignBalanceCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="POST" onsubmit="return validateCustomerTransaction();">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_SELL_CREADIT%>" />
        <div class="content_body"><br><br>
            <table  dir="rtl" style="text-align:right" >
                <tr >
                    <th scope="col" style="text-align:right">البائع</th>
                    <td ><%=agent.getName(request.getSession())%> </td>
                    <th rowspan="6" style="text-align:right"><input type="submit" name="btnSubmit" tabindex="3"
                                                                    value="ارسال البيانات" class="Btn" ></th>
                </tr>
                <tr>
                    <th scope="col" style="text-align:right">الرقم التعريفى</th>
                    <td><%=agent.getPin()%></td>
                </tr>
                <tr>
                    <th scope="col" style="text-align:right">رصيدك فى مصارى</th>
                    <td><%=myFormatter.format(customerBalance)%></td>
                </tr>
                <tr>
                    <th scope="col" style="text-align:right">رصيدك فى  اتصالات</th>
                    <td><%=etisalatTransferBalance%></td>
                </tr>
                <tr>
                    <th scope="col" style="text-align:right"> الرقم التعريفى المحول اليه</th>
                    <td><input type="text" name="<%=CONFIG.PARAM_PAYED_PIN%>" tabindex="1"></td>
                </tr>
                <tr>
                    <th scope="col" style="text-align:right">القيمه المطلوبه</th>
                    <td><input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="2"></td>
                </tr>
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>



