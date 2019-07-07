<%--
    Document   : AssignBalance.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

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
    if (role == null || !role.equals("1")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }

%>
<%    session = request.getSession();
    String agentId = (String) session.getAttribute(CONFIG.PARAM_AGENT_ID);
    try {
        AgentDTO agent = MasaryManager.getInstance().getAgent(agentId);

        DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<html>
    <head>
        <style  type="text/css">
            #servDiv {
                overflow: auto;
                azimuth: leftwards;
                height: 500px;
                width: 250px;
                overflow-y: scroll;
                border:1px ; 
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAssignBalanceToAgent(session)%></title>

    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>

        </div>
        <div class="content_body"><br><br>
            <form name="AssignBalanceAgent" action="<%=CONFIG.APP_ROOT%>admin" method="POST" onsubmit="return validateBalance()">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASIGN_AGENT_BALANCE%>" />
            <table >
                <tr >
                    <th scope="col" colspan="2"><%=CONFIG.getName(session)%></th>
                    <td colspan="2"><%=agent.getName(session)%> </td>
                    <th rowspan="<%=5 + agent.getServices().size()%>"  ><input type="submit" id="btnSubmit" name="btnSubmit" tabindex="5"
                                                                               value="<%=CONFIG.getAssign(session)%>" class="Btn"></th>

                </tr>
                <tr>
                    <th scope="col" colspan="2"><%=CONFIG.getID(session)%></th>
                    <td colspan="2"><%=agent.getPin()%></td>
                </tr>
                <tr>
                    <th scope="col" colspan="2"><%=CONFIG.getBalance(session)%></th>
                    <td colspan="2"><%=myFormatter.format(agent.getServiceBalance(1))%></td>
                </tr>
                <tr >
                    <td colspan="3" >                     
                        <div class="displayTableFrame"  name="servDiv" id="servDiv" style="width: 100%">
                            <table border="0" style="width: 100%"  >
                                <tr>
                                    <%
                                        for (CustomerServiceDTO service : (List<CustomerServiceDTO>) agent.getServices()) {
                                    %>
                                <tr>
                                    <th colspan="2"><%=service.getServiceName(session)%></th>
                                    <td><%=myFormatter.format(service.getServiceBalance())%></td>
                                    <td> <input type="radio" value="<%=service.getServiceID()%>"   name="<%=CONFIG.PARAM_SERVICE_ID%>">                                    </td>
                                </tr>
                                <%
                                        }
                                    } catch (Exception e) {
                                        MasaryManager.logger.error(e.getMessage());
                                    }%>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th scope="col" colspan="2"><%=CONFIG.getAddBalance(session)%></th>
                    <td colspan="2"><input  id="addBal" type="text" name="<%=CONFIG.PARAM_BALANCE%>">
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





