<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.Bill_Response"%>
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
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    Bill_Response UniBillCommissionResponse = (Bill_Response) request.getAttribute("UniBillCommissionResponse");
    Masary_Bill_Type UniBillType = (Masary_Bill_Type) request.getAttribute("UniBillType");

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
            <form name="UniversitiesConfirmation" action="UniversitiesPaymentController" method="POST">
                <input type="hidden" name="uniReferenceNum" value="<%=request.getParameter("uniReferenceNum")%>" />
                <input type="hidden" name="uniFirstAmount" value="<%=request.getParameter("uniFirstAmount")%>" />
                <input type="hidden" name="uniName" value="<%=new String(request.getParameter("uniName").getBytes("ISO-8859-1"), "utf-8")%>" />

                <table>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=new String(request.getParameter("uniName").getBytes("ISO-8859-1"), "utf-8")%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getStudentName(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="StudentName"  type="text" name="StudentName" value="<%=new String(request.getParameter("StudentName").getBytes("ISO-8859-1"), "utf-8")%>" autocomplete="off" readonly style="background-color: #EDEDED;width: 300px">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getStudentCode(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="StudentCode" value="<%=request.getParameter("StudentCode")%>"    type="text" required name="StudentCode" autocomplete="off" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getUNIName(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="getUniName" value="<%=new String(request.getParameter("uniName").getBytes("ISO-8859-1"), "utf-8")%>"   type="text"  name="getUniName" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p><%= CONFIG.getNeededStudnetAmount(session)%></p>
                        </td>
                        <td>
                            <input name="RequriedAmount" value="<%=UniBillCommissionResponse.getAMOUNT()%>" autocomplete="off" id="RequriedAmount" type="text" readonly style="background-color: #EDEDED;">
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
