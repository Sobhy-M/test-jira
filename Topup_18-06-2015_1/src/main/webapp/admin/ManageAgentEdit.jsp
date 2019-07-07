<%--
    Document   : AssignBalance.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="java.util.List"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("1")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    List<ServiceDTO> serviceList = MasaryManager.getInstance().getAllServices();
    ArrayList<CustomerServiceDTO> agentServiceList = (ArrayList<CustomerServiceDTO>) session.getAttribute("services");
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<html>
    <head>
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getEditAgent(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div class="content_body"><br><br>
            <form name="EditAgent" action="<%=CONFIG.APP_ROOT%>admin" method="POST" onsubmit="return ValidateFormUpdateAgent()">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_EDIT_AGENT%>" />
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getName(session)%></th>
                    <td >
                        <input id="UpAgName"name="<%=CONFIG.PARAM_NAME%>" type="text" value="<%=agent.getName(null)%>"/>
                        <br/>
                        <div id="UpAgNameDiv"></div>
                    </td>
                    <th rowspan="<%=serviceList.size() + 4%>"><input type="submit" name="btnSubmit" tabindex="6"
                                                                     value="<%=CONFIG.getSave(session)%>" class="Btn">
                        <br/>
                        <br/>
                        <input type="button" dir="rtl" name="btnSubmit" tabindex="3"
                               value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1);
                return true;">
                    </th>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getArabicName(session)%></th>
                    <td><input type="text" class="text"  name="<%=CONFIG.PARAM_USERNAME_ARABIC%>" value="<%=agent.getArabicName()%>" tabindex="2" /></td>
                </tr>

                <tr>
                    <th scope="col"><%=CONFIG.getMobileNumber(session)%></th>
                    <td >
                        <input id="UpAgTelephoneId" name="<%=CONFIG.PARAM_PHONE%>" type="text" value="<%=agent.getPhone()%>"/>
                        <br/>
                        <div id="UpAgphoneDiv"></div>
                    </td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getID(session)%></th>
                    <td >
                        <input   readonly style="border:0" name="<%=CONFIG.PARAM_PIN%>" type="text" value="<%=agent.getPin()%>" readonly >
                    </td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getBalance(session)%></th>
                    <td >
                        <%=myFormatter.format(customerBalance)%>
                    </td>
                </tr>
                <%
                    int i = 0;
                    String checked = "";
                    for (ServiceDTO service : serviceList) {
                        if (MasaryManager.getInstance().getService(service.getIdService(), agentServiceList) != null) {
                            checked = "checked disabled";
                        } else {
                            checked = "";
                        }

                %>
                <tr>
                    <td>
                        <%=service.getStrServiceName(session)%>
                    </td>
                    <td>
                        <input name="<%=service.getIdService()%>" type="checkbox" id="<%=service.getIdService()%>" tabindex="<%=5 + i%>" "<%=checked%>"/>
                        <%  if (!checked.equals("")) {
                        %>
                        <INPUT type="hidden" name="<%=service.getIdService()%>" value="on"/>
                        <%}%>
                    </td>
                </tr>

                <%}%>
            </table>
        </form>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>
