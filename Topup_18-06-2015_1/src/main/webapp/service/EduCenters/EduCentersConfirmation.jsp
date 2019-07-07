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
    MasaryManager.logger.info("Edu centers Confirmation Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    RatePlanDTO ratePlan = (RatePlanDTO) request.getAttribute("ratePlan");
    Double serviceCost = ratePlan.getApplied_fees() + (Double.parseDouble(request.getParameter("EduAmount")) * ratePlan.getApplied_fees_per() / 100);
    Double Commission = Double.parseDouble(request.getParameter("EduAmount"))*(ratePlan.getCommission()/100) + ratePlan.getFixedAmount();
    Double dedducedAmount = Double.parseDouble(request.getParameter("EduAmount")) + serviceCost - Commission;
    Double totalAmount = Double.parseDouble(request.getParameter("EduAmount")) + serviceCost;
    session = request.getSession();
    String serviceName = "";
    int serviceId = Integer.parseInt(session.getAttribute("SERVICE_ID").toString());
    if (serviceId == 800) {
        serviceName = CONFIG.getB2bGroup(session) + "(" + request.getParameter("EduServiceType") + ")";
    } else if (serviceId == 801) {
        serviceName = CONFIG.getBinarySystems(session);
    } else if (serviceId == 803) {
        serviceName = CONFIG.getMEC(session);
    } else if (serviceId == 804) {
        serviceName = CONFIG.getSkillsBank(session);
    }else {
        serviceName = CONFIG.getKorsatak(session);
    }
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getEduCentersServic(request.getSession())%></title>
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
            <form name="EduCentersConfirmation" action="EduCentersPaymentController" method="POST">
                <input type="hidden" name="EduServiceType" value="<%=request.getParameter("EduServiceType")%>"/>
                <input type="hidden" name="Commission" value="<%=Commission%>"/>
                <input type="hidden" name="EduAmount" value="<%=request.getParameter("EduAmount")%>"/>
                <input type="hidden" name="EduDedducedAmount" value="<%=dedducedAmount%>"/>
                <input type="hidden" name="EduServiceName" value="<%=serviceName%>"/>
                <table>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=serviceName%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getEduCode(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="EduCode" value="<%=request.getParameter("EduCode")%>" maxlength="30" title="<%=CONFIG.getEduCodeTitle(request.getSession())%>"   type="text" required name="EduCode"   readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p><%= CONFIG.getEduAmount(session)%></p>
                        </td>
                        <td>
                            <input name="EduAmount" value="<%=request.getParameter("EduAmount")%>" autocomplete="off" id="EduAmount" type="text" required title="<%=CONFIG.getEduAmountTitle(session)%>" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p><%= CONFIG.getEduServiceCost(session)%></p>
                        </td>
                        <td>
                            <input name="EduServiceCost" autocomplete="off" id="EduServiceCost" value="<%=serviceCost%>" type="text"  readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p><%= CONFIG.getEduTotalAmount(session)%></p>
                        </td>
                        <td>
                            <input name="EduTotalAmount" autocomplete="off" id="EduTotalAmount" type="text" value="<%=totalAmount%>" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">
                            <input type="submit" name="btnSubmit"  value="<%=CONFIG.getpayment(session)%>" class="Btn" >
                        </td>
                        <td style="text-align: left">
                            <input type="button" name="btnBack"  value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1)">
                        </td>
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
