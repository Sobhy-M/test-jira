<%--
    Document   : ViewBankInformation.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.manager.TagManager"%>
<%@page import="com.masary.database.dto.*"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getTrack(session)%></title>
    </head>    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>


        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>

    </div>

    <div class="content_body"><br><br>

        <h3>
            <%--<h2>Last transactions</h2>--%>
            <th><%=CONFIG.getTrack(session)%> <%=agent.getName(session)%></th></h3>
        <table>
            <th> <%=CONFIG.getDate(session)%></th>
            <th> <%=CONFIG.getTagId(session)%></th>
            <th><%=CONFIG.getTagInfo(session)%></th>
            <%List<TrackDTO> trackInfo = TagManager.getInstance().getTrack(agent.getPin());
                        for (TrackDTO track : trackInfo) {%>
            <tr>
                <td><%=track.getTrackDate()%></td>
                <td><%=track.getTagId()%></td>
                <td><%=track.getTagData()%></td>
            </tr>
            <%}%>
        </table>
    </div><!-- End content body -->



    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>


