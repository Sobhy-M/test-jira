<%--
    Document   : ReCharge
    Created on : 27/06/2009, 01:09:17 م
    Author     : Melad
--%>

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
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>تاكيد التحويل</title>
        
        <script type="text/javascript">
            function disableDoubleSubmission(){
                document.getElementById("btnSubmit").disabled = true;
            }
        </script>
        
    </head>
    <BODY class="body">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="AssignBalanceCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="POST" onsubmit="disableDoubleSubmission()">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_SELL_CREADIT%>" />
        <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="True" />
        <div class="content_body" dir="rtl" ><br><br>
            <H3>تاكيد التحويل </H3>
            <table  >
                <tr >
                    <th scope="col" style="text-align:right">الرقم التعريفى</th>
                    <td ><%=agent.getPin()%></td>
                    <th rowspan="3"><input type="submit" name="btnSubmit" id="btnSubmit" tabindex="3"
                                           value="تاكيد البيانات " class="Btn"></th>
                </tr>
                <tr>
                    <th scope="col" style="text-align:right">رصيدك فى مصارى</th>
                    <td><%=myFormatter.format(customerBalance - Double.parseDouble(request.getParameter(CONFIG.AMOUNT)))%></td>
                </tr>
                <tr>
                    <th scope="col" style="text-align:right">رصيدك فى  اتصالات</th>
                    <td><%=myFormatter.format(customerBalance - Double.parseDouble(request.getParameter(CONFIG.AMOUNT)))%></td>

                </tr>
                <tr>
                    <th scope="col" style="text-align:right">الرقم التعريفى المحول اليه</th>
                    <td><input type="text"  readonly="true" name="<%=CONFIG.PARAM_PAYED_PIN%>" value="<%=request.getParameter(CONFIG.PARAM_PAYED_PIN)%>"></td>
                    <th rowspan="2" ><input type="button" dir="rtl" name="btnSubmit" tabindex="3"
                                            value="رجوع" class="Btn" onclick="history.go(-1);
                                                    return true;"></th>
                </tr>
                <tr>
                    <th scope="col" style="text-align:right"> القيمه المطلوبه</th>
                    <td><input type="text"  readonly="true" name="<%=CONFIG.AMOUNT%>" value="<%=request.getParameter(CONFIG.AMOUNT)%>"></td>
                </tr>
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div>
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>
