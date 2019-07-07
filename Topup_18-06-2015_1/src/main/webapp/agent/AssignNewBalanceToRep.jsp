<%-- 
    Document   : AssignNewBalanceToRep
    Created on : Sep 13, 2012, 11:07:23 AM
    Author     : Hammad
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    /*  String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("2")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }*/

    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);

    Enumeration e = request.getParameterNames();

    String dateFrom = requestParameters.get("datefrom");
    String dateTo = requestParameters.get("dateto");
    //String newRepBalance = requestParameters.get("newBalance");
    String repID = "";


%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <script language="javascript" type="text/javascript" src="agent/datetimepicker.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getViewCustomers(session)%></title>
    </head>
    <BODY class="body">
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>                
            <div class="content_body" align="center"><br/><br/><br/>

            <% if (dateFrom != null) {
                    repID = requestParameters.get("repID");
                    String[] repServicesIDs = requestParameters.get("repServices").split("\\.");
                    for (int i = 0; i < repServicesIDs.length; i++) {
                        MasaryManager.getInstance().updateNewRepBalances(dateFrom, dateTo, requestParameters.get(repServicesIDs[i]), repServicesIDs[i], repID);
    //                       out.println("Rep No. " + repID + " from " + dateFrom + " to " + dateTo + 
    //                           " will receive " + requestParameters.get(repServicesIDs[i]) + " EGP. for service " + repServicesIDs[i]);
    //                       out.println("......");
                    }

                    out.println("جميع الارصده تم اعادة تخصيصها");
                } else {%>

            <form name="submitNewBalance" action="<%=CONFIG.APP_ROOT%>walletServices?action=ACTION_AGENT_OPERATIONS&PARAM_AGENT_BTN=Assign+New+Rep+Balance" method="post">

                <%

                    repID = e.nextElement().toString();
                    if (repID.equals("action")) {
                        repID = e.nextElement().toString();
                    } else {
                        //do nothing
                    }
                    ////System.out.println("Proceed with rep no  " + repID);
                    Vector<String[]> repsServicesIDs = MasaryManager.getInstance().getAllRepServices(repID);
                    String repsServicesInOneString = "";
                    for (int i = 0; i < repsServicesIDs.size(); i++) {
                        if (!MasaryManager.getInstance().isVoucherService(repsServicesIDs.get(i)[0]).equals("1")) {
                            repsServicesInOneString = repsServicesInOneString + repsServicesIDs.get(i)[0] + ".";
                        }
                    }
                    out.println("تغيير الرصيد المتاح للمندوب رقم " + repID);
                    //  //System.out.println(repsServicesInOneString);
%>

                <br/>
                <input type="hidden" id="repID" name="repID" value="<%=repID%>"/>
                <input type="hidden" id="repServices" name="repServices" value="<%=repsServicesInOneString%>"/>

                <br/>
                من: 
                <input id="datefrom" name="datefrom" type="text" size="25" />
                <a href="javascript:NewCal('datefrom','ddmmyyyy')"><img src="agent/cal.gif" width="16" height="16" border="0" alt="Pick a date"/></a>

                <br/>
                الى:
                <input id="dateto" name ="dateto" type="text" size="25" />
                <a href="javascript:NewCal('dateto','ddmmyyyy')"><img src="agent/cal.gif" width="16" height="16" border="0" alt="Pick a date"/></a>


                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <br/>
                <% for (int i = 0; i < repsServicesIDs.size(); i++) {
                        if (MasaryManager.getInstance().isVoucherService(repsServicesIDs.get(i)[0]).equals("1")) {
                %>
                <br/>
                <%=repsServicesIDs.get(i)[1]%>: <input type="text" id="<%=repsServicesIDs.get(i)[0]%>" name="<%=repsServicesIDs.get(i)[0]%>" disabled/>
                <%   } else {
                %>

                <br/>
                <%=repsServicesIDs.get(i)[1]%>: <input type="text" id="<%=repsServicesIDs.get(i)[0]%>" name="<%=repsServicesIDs.get(i)[0]%>"/>




                <% }
                       } %>


                <br/>
                <br/>
                <input type="submit" value="اعادة تخصيص" id="submitbalance"/>

            </form>

            <%
                }

            %>
            <!-- content body -->

        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
    </div><!-- End of Main body-->
</body>
</html>