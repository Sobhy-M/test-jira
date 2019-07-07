<%-- 
    Document   : SearchCustomers
    Created on : Jan 27, 2011, 11:05:06 AM
    Author     : Melad
--%>

<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
    String field = request.getParameter(CONFIG.PARAM_FIELD);
    String value = "";
    List list = null;
    if (field != null && !field.equals("")) {
        try {
            byte[] bytes = request.getParameter(CONFIG.PARAM_VALUE).getBytes("ISO-8859-1");
            value = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            MasaryManager.logger.error(ex);
        }
        list = MasaryManager.getInstance().getAgents(field, value);
    }
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getSearch(session)%></title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include> <%}%>
        </div>
        <div class="content_body"><br><br>
            <form action="<%=CONFIG.APP_ROOT%>admin" method="post" >
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_SEARCH%>" />
            <table>
                <tr>
                    <td>
                        <%=CONFIG.getField(session)%>
                    </td>
                    <td>
                        <select name="<%=CONFIG.PARAM_FIELD%>">
                            <option value="ID_CUSTOMER">Customer_id</option>
                            <option value="MSISDN"> Phone</option>
                            <option value="Id_Parent">Parent</option>
                            <option value="STR_DISPLAY_NAME">English name</option>
                            <option value="STR_DISPLAY_NAME_ARABIC">Arabic Name</option>
                        </select>
                    </td>
                    <td>
                        <input class="text" type="text"  name="<%=CONFIG.PARAM_VALUE%>" >
                    </td>
                    <td>
                        <input type="submit" value="<%=CONFIG.getSearch(session)%>">
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <br>
        <%if (list != null) {%>
        <form action="<%=CONFIG.APP_ROOT%>admin" method="post">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ASIGN_AGENT_BALANCE%>" />
            <table >
                <thead>
                    <tr>
                        <th scope="col"><%=CONFIG.getID(session)%></th>
                        <th scope="col" width="150"><%=CONFIG.getName(session)%></th>
                        <th scope="col"><%=CONFIG.getBalance(session)%></th>
                        <th SCOPE="COL"><%=CONFIG.getAssign(session)%></th>
                        <th SCOPE="COL"><%=CONFIG.getManageAgent(session)%></th>

                        <th SCOPE="COL"><%=CONFIG.getStatus(session)%></th>
                    </tr>
                </thead>
                <%
                    int i, length = list.size();
                    for (i = 0; i < length; i++) {
                        AgentDTO agent = (AgentDTO) list.get(i);
                %>
                <tbody>
                    <tr>
                        <th  scope="row"> <%=agent.getPin()%></th>
                        <td ><%=agent.getName(session)%></td>
                        <td ><%=myFormatter.format(agent.getBalance())%></td>
                        <td> <input type="submit" value="<%=CONFIG.getAssign(session)%>"  name="<%=agent.getPin()%>">                                    </td>
                        <td> <input type="submit" value="<%=CONFIG.getManageAgent(session)%>"  name="<%=agent.getPin()%>">                                    </td>
                        <td>
                            <%
                                if (agent.isActive()) {
                            %>
                            <input type="submit" onclick="return confirm('<%=CONFIG.getAreyouSure(session)%>');" value="<%=CONFIG.getDeactivate(session)%>" name="<%=agent.getPin()%>">
                            <%
                            } else {
                            %>
                            <input type="submit" onclick="return confirm('<%=CONFIG.getAreyouSure(session)%>');"  value="<%=CONFIG.getActivate(session)%>" name="<%=agent.getPin()%>">
                            <%
                                }
                            %>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </form>
        <%}%>

    </div>



    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
<br>
</body>
</html>
