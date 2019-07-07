<%-- 
    Document   : ViewAgents.jsp
    Created on : 06/05/2009, 11:10:07 ص
    Author     : Melad
--%>

<%@page import="java.util.Map"%>
<%@page import="com.masary.database.dto.GroupDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("1")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
%>
<%
    List list;
    String state = requestParameters.get("state");
    String type = requestParameters.get("type");
    String group = requestParameters.get("group");
    list = MasaryManager.getInstance().getAgentsGroup(state, type, group);
    if (state == null) {
        state = "Active";
    }
    if (type == null) {
        type = "Agent";
    }
    if (group == null) {
        group = "0";
    }
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    List groupList = MasaryManager.getInstance().getGroups();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getViewAgents(session)%></title>
    </head>
    <BODY class="body">

        <%if (session.getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div class="content_body"><br><br>
            <table><tr>

                <form action="<%=CONFIG.APP_ROOT%>admin?action=ACTION_ADMIN_OPERATIONS&PARAM_ADMIN_BTN=View+Agents" >
                <td>
                    Account State <select name="state" id="choice">
                        <option value="All" <%=(state.equals("All") ? "selected=selected" : "")%> >All</option>
                        <option value="Active" <%=(state.equals("Active") ? "selected=selected" : "")%>>Active</option>
                        <option value="Deactive" <%=(state.equals("Deactive") ? "selected=selected" : "")%>>Deactive</option>
                    </select>
                </td><td>
                    Account Type <select name="type" id="choice">
                        <option value="All"  <%=(type.equals("All") ? "selected=selected" : "")%>>All</option>
                        <option value="Agent"   <%=(type.equals("Agent") ? "selected=selected" : "")%>>Agent</option>
                        <option value="Customer" <%=(type.equals("Customer") ? "selected=selected" : "")%>>Customer</option>
                    </select>
                </td><td>
                    Account Group <select name="group" id="choice">
                        <option value="0" selected="selected">All</option>
                        <%
                            for (GroupDTO groupDTO : (List<GroupDTO>) groupList) {
                        %>
                        <option value="<%=groupDTO.getGroupId()%>" <%=(groupDTO.getGroupId() + "" == group) ? "selected=selected" : ""%>"><%=groupDTO.getGroupName()%></option>
                        <%}%>
                    </select>
                </td><td>
                    <input type="submit" value="<%=CONFIG.getGo(session)%>" name="<%=CONFIG.getGo(session)%>" />
                </td>
            </form>
            </tr>        </table>
        <br>
        <br>
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
        <!-- content body -->
    </div><!-- End of Table Content area-->
</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>
