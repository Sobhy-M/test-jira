<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
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
    MasaryManager.logger.info("Edu centers Patyment Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    String serviceName = "";
    int serviceId = Integer.parseInt(session.getAttribute("SERVICE_ID").toString());
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    if (serviceId == 800) {
        serviceName = CONFIG.getB2bGroup(session) + "(" + request.getParameter("EduServiceType") + ")";
    } else if (serviceId == 801) {
        serviceName = CONFIG.getBinarySystems(session);
    } else if (serviceId == 804) {
        serviceName = CONFIG.getSkillsBank(session);
    } else if (serviceId == 803) {
        serviceName = CONFIG.getMEC(session);
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
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
            <% if (serviceId == 804) {%>
                printwindow.document.write('<tr><th colspan="2"><img src="./img/skills_bank.bmp" alt="skills Bank Icon" width="auto" height="auto"></th></tr>');
            <%} else {%>
                printwindow.document.write('<tr><th colspan="2"><%=request.getParameter("EduServiceName")%></th></tr>');
            <%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("EduCode")%></td><td style="text-align: right;padding-right:25px;width: 50%;">كود الحجز</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("EduAmount")%></td><td style="text-align: right;padding-right:25px;width: 50%;">المبلغ بالجنية </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("EduServiceCost")%></td><td style="text-align: right;padding-right:25px;width: 50%;">تكلفة الخدمة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("EduTotalAmount")%></td><td style="text-align: right;padding-right:25px;width: 50%;">المبلغ الإجمالي</td></tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم البائع</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getAttribute("OperationID")%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>خدمة عملاء مصاري: 16994</td></tr >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size:16px;"><p>www.e-masary.com</td></tr>');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
            }
        </script>
    </head>
    <body class="body" onload="setDivPrint()">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
        <div class="content_body">
            <form name="EduCentersPayment" action="<%=CONFIG.APP_ROOT + role + ".jsp"%>" method="POST">
                <table>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=serviceName%>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.GetSuccessfulTransaction(request.getSession())%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.GetOperationID(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="OperationID" value="<%=request.getAttribute("OperationID")%>" type="text"  name="OperationID" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getCommession(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="EduCode" maxlength="30" value="<%=request.getParameter("Commission")%>" title="<%=CONFIG.getEduCodeTitle(request.getSession())%>" type="text" name="EduCode" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getIsDeddued(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="IsDeddued" type="text" value="<%=request.getParameter("EduDedducedAmount")%>"  name="IsDeddued" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.GetClose(session)%>" class="Btn"></td>
                    </tr>
                    <tr style="display: none">
                        <th colspan="2"><img src="./img/skills_bank.bmp" alt="skills Bank Icon" width="50" height="50"></th>
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
