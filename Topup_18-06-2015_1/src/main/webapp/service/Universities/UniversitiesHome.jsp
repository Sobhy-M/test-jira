<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    MasaryManager.logger.info("Edu centers Home Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    String uniName = "defult";
    int serviceId = Integer.parseInt(session.getAttribute("SERVICE_ID").toString());
    if(serviceId == 99001)
    {
        uniName = CONFIG.getSouthVallyUni(session);
    }
    else if (serviceId == 99006)
    {
        uniName = CONFIG.getBeniSuefUni(session);
    }
    else if (serviceId == 22877)
    {
        uniName = CONFIG.getAinShamsUni(session);
    }
    else if (serviceId == 99004)
    {
        uniName = CONFIG.getElmansoraUni(session);
    }
    else if(serviceId == 99010)
    {
         uniName = CONFIG.getCairoUni(session);
    }
    else if(serviceId == 99003)
    {
         uniName = CONFIG.getSuezCanalUni(session);
    }
    else if(serviceId == 99016)
    {
         uniName = CONFIG.getBeniSuefUniMsc(session);
    }
    else if(serviceId == 99017)
    {
         uniName = CONFIG.getFEAU(session);
    }
    else if (serviceId == 22889)
    {
        uniName = CONFIG.getFayoumUni(session);
    }
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getUniService(request.getSession())%></title>
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
        <div class="content_body">
            <form name="UniversitiesHome" action="UniversitiesConfirmationController" method="POST">
                <input type="hidden" name="uniName" value="<%=uniName%>" />
                <table>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=uniName%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getStudentCode(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="StudentCode" maxlength="30" title="<%=CONFIG.getStudentCodeTitle(request.getSession())%>"  type="text" required name="StudentCode" autocomplete="off" autofocus>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getConfirm(session)%>" class="Btn"></td>
                    </tr>
                </table>
            </form>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
