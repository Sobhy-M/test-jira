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
    String serviceName = "";
    int serviceId = Integer.parseInt(request.getParameter("SERVICE_ID"));
    if (serviceId == 800) {
        serviceName = CONFIG.getB2bGroup(session);
    } else if (serviceId == 801) {
        serviceName = CONFIG.getBinarySystems(session);
    } else if (serviceId == 803) {
        serviceName = CONFIG.getMEC(session);
    } else if (serviceId == 804) {
        serviceName = CONFIG.getSkillsBank(session);
    } else {
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
        <script>
            function onchangeValue() {
                var amount = document.getElementById("EduAmount");
//              
                if (Number(amount.value) < 15 | Number(amount.value) > 10000)
                {
                    document.getElementById("AmountValidation").innerHTML = 'برجاء ادخال القيمة اكبر من 15 واصغر من 10000';
                    document.getElementById("AmountValidation").style.color = "red";
                    return false;

                } 

            }
        </script>
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
            <form name="EduCentersHome" action="EduCentersConfirmationController" method="POST" >
                <input type="hidden" name="serviceName" value="<%=serviceName%>" />
                <table>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=serviceName%>
                        </td>
                    </tr>
                    <%if (serviceId == 800) {%>
                    <tr>
                        <td><%=CONFIG.getServiceType(session)%></td>
                        <td>
                            <select required name="EduServiceType" id="EduServiceType">
                                <option selected  value="" ><%= CONFIG.getServiceType(session)%></option>
                                <option  value="<%= CONFIG.getB2bGroupoWTOI(session)%>" ><%= CONFIG.getB2bGroupoWTOI(session)%></option>
                                <option  value="<%= CONFIG.getB2bGroupoB2BEvents(session)%>" ><%= CONFIG.getB2bGroupoB2BEvents(session)%></option>
                            </select> 
                        </td>
                    </tr>
                    <%}%>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getEduCode(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="EduCode" maxlength="30" title="<%=CONFIG.getEduCodeTitle(request.getSession())%>" type="text" required name="EduCode" autocomplete="off" autofocus>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p><%= CONFIG.getEduAmount(session)%></p>
                        </td>
                        <td>
                            <% if (serviceId == 804) {%>
                            <input name="EduAmount" autocomplete="off" onchange="onchangeValue()" id="EduAmount" pattern="^[0-9]\d*(\.\d{1,9})?" type="text"  required title="<%=CONFIG.getEduAmountTitle(session)%>">
                            <div id="AmountValidation"></div>
                            <%} else {%>
                            <input name="EduAmount" autocomplete="off" id="EduAmount" pattern="^[0-9]\d*(\.\d{1,9})?" type="text"  required title="<%=CONFIG.getEduAmountTitle(session)%>">
                            <%}%>
                        </td>
                    </tr>
                    <tr>
                        <% if (serviceId == 804) {%>
                        <td colspan="2" style="text-align: center"><input type="submit" name="btnSubmit"  onclick="return onchangeValue()" tabindex="3" value="<%=CONFIG.getConfirm(session)%>" class="Btn"></td>
                            <%} else {%>
                        <td colspan="2" style="text-align: center"><input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getConfirm(session)%>" class="Btn"></td>
                            <%}%>
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
