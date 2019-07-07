
<%@page import="com.masary.utils.BuildUUID"%>
<%-- 
    Document   : electricity Service
    Created on : July 05, 2017, 12:44:08 PM
    Author     : Mustafa
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
    MasaryManager.logger.info("ElectricityBillInquiry_Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
   
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
        String rolePage = CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp";

%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
    <head>
       <script src="./js/LongLiveEgyptJS/LongLiveDonationController.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        <title><%=CONFIG.getElectricityServiceName(request.getSession()) %></title>
    </head>
       <body onload="onloadFunction()">
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        
        <form action="Electricity_InfoInquiryController" id="Electricity_InfoInquiryController" method="POST" style="font-weight: bold">
             <input type="hidden" name="action" value="<%=CONFIG.ACTION_Electricity_CONFIRM%>" />
             <h2 align="center" ><%=CONFIG.INFORNEEDED %></h2>
            <table>
                   
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.GetElectronic_pay_number(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="AccountNumber" maxlength="30" title="<%=CONFIG.GetOperationIdTitle_Ar(request.getSession())%>" pattern="^[0-9]\d*(\.\d{1,9})?"  type="text" required name="AccountNumber" autocomplete="off" autofocus>
                        </td>
                    </tr>
                    <% if(request.getParameter(CONFIG.PARAM_SERVICE_ID).equals("99018")) {%>
                    <tr>
                        <td colspan="2"><div style="color: red; font: 15;">في حالة الرد بأن كود العميل غير موجود, برجاء إعادة المحاولة بعد إضافة 7  إلى يسار كود العميل</div></td>
                    </tr>
                    <%}%>
                    <tr>
                        <td colspan="2" style="text-align: center">
                         <input type="submit" name="btnSubmit" tabindex="3" value="<%=CONFIG.getCheck(session)%>" class="Btn">
                            <input type="button" value="الغاء"  id="submitbutton" onclick="window.location.href='<%=rolePage %>';"  />
                     </td>
                    </tr>
                </table>

        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
    </html>