

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
        <title>شراء مخزون جديد</title>
    </head>
    <BODY class="body">
        <div class="main_body" align="center">
            <div class="top_header" align="center" style="background:url(img/header_bg.png) no-repeat"><!-- Header div-->
                <div class="top_banner">
                    <IMG SRC="img/1x.jpg" WIDTH="646" ALT="" border="0">
                    <jsp:include page="../../img/welcome.jsp"></jsp:include>
                    </div>
                </div>
                <div class="content_body">
                    <div class="left_menu"><!-- left menu -->
                        <a href="<%=CONFIG.APP_ROOT + "3.jsp"%>"><IMG SRC="img/home.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a><br>
                        <jsp:include page="../../img/menuList.jsp"></jsp:include>
                    <a href="<%=CONFIG.APP_ROOT%>Web"><IMG SRC="img/logout.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>
                </div>
                <form name="AssignBalanceCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="POST" onsubmit="return validateBalance()">
                    <input type="hidden" name="action" value="<%=CONFIG.ACTION_SELL_CREADIT%>" />
                    <div class="content_body"><br><br>
                        <table >
                            <tr>
                                <th scope="col">المشترى</th>
                                <td ><%=agent.getName()%> </td>
                                <th rowspan="5"><input type="submit" name="btnSubmit" tabindex="3"
                                                       value="Assign" class="Btn"></th>
                            </tr>
                            <tr>
                                <th scope="col">الرقم التعريفى</th>
                                <td><%=agent.getPin()%></td>
                            </tr>
                            <tr>
                                <th scope="col">رصيدك فى مصارى</th>
                                <td><%=myFormatter.format(customerBalance)%></td>
                            </tr>
                            <tr>
                                <th scope="col">رصيدك فى  اتصالات</th>
                                <td>؟؟</td>
                            </tr>
                            <tr>
                                <th scope="col">القيمه المطلوبه</th>
                                <td><input type="text" name="<%=CONFIG.PARAM_PAYED_PIN%>" tabindex="1"></td>
                            </tr>
                            <tr>
                                <th scope="col">Amount</th>
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

