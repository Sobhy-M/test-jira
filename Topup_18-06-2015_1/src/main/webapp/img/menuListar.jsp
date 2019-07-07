<%@ page language="java" contentType="text/html; charset=windows-1256"
         pageEncoding="UTF-8"%>
         

<%@page import="com.masary.integration.LedgerClient"%>
<%@page import="com.masary.integration.dto.LedgerBalanceRespDto"%>
<%@page import="com.masary.database.dto.EmployeeDTO"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Biller"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript" src="img/c_configar.js"></script>
<script type="text/javascript" src="img/c_smartmenus.js"></script>
<script type="text/javascript" src="./js/rainbows.js"></script>
<link href="img/stylear.css" rel="stylesheet" type="text/css">
<link rel="icon" type="image/ico" href="/img/favicon.ico">

<%
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    String meunXML = (String) ((LoginDto) request.getSession().getAttribute("Login")).getMenuXML();
    String[] userBalances = null;
    LedgerBalanceRespDto ledgerBalanceRespDto = null;
    userBalances = MasaryManager.getInstance().customerBalances((String) request.getSession().getAttribute(CONFIG.PARAM_PIN));
    session = request.getSession();
    session.setAttribute(CONFIG.lang, "ar");
    session.setAttribute("services", MasaryManager.getInstance().getCustomerServices(agent.getPin()));
    session.setAttribute("showTree", MasaryManager.getInstance().showTree(agent.getPin()));
    String loyaltyPoints = "0";
    boolean hasVCash = meunXML.contains("2003") ? true : false;
%>
<div class="main_body" align="center">
    <div>
        <table width="940px" cellspacing="0" cellpadding="5" border="0">
            <tbody>
            <div class="top_header" align="center" style="background:url(img/header_bg_new.png) no-repeat"><!-- Header div-->
                <tr>
                    <%  if (session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {%>
                    <td ><font size="2">اهلا    <%=agent.getArabicName()%>      </font>                </td>
                        <%   } else {%>
                    <td ><font size="2">اهلا    <%=((EmployeeDTO) ((LoginDto) request.getSession().getAttribute("Login")).getEmp()).getEmployeeArabicName()%>      </font>                </td>
                        <%   }%>

                    <td  style="text-align:center" >
                        <% if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && ((EmployeeDTO) ((LoginDto) request.getSession().getAttribute("Login")).getEmp()).isShowMyBalance()) {%>
                        <table align="center"><tr><th>حدك اليوم</th><th>رصيدك الحالى</th><th>الكروت</th><th>الفواتير</th><%=hasVCash ? "<th>فودافون كاش</th>" : ""%></tr><tr><td><%=((EmployeeDTO) ((LoginDto) request.getSession().getAttribute("Login")).getEmp()).getAllowedBalance()%> نقطه</td><td><%=((EmployeeDTO) ((LoginDto) request.getSession().getAttribute("Login")).getEmp()).getCurBalance()%> نقطه</td><td><%= userBalances[1]%> نقطه</td><td><%= userBalances[2]%> نقطه</td><%=hasVCash ? "<td>" + userBalances[3] + " نقطه</td>" : ""%></tr></table></td>

                    <% } else{%>
                    <%if (agent.isAutoAllocate().equals("F")) {%>
                    <%=hasVCash ? "<table align='center'><tr><th>الخدمات</th><th>فودافون كاش</th></tr><tr><td>" + userBalances[0] + " نقطه</td> <td>" + userBalances[3] + " نقطه</td></tr></table>" : "رصيدك " + userBalances[0] + " نقطه "%>
                    <%} else {%>
                <table align="center">
                    <tr>
                        <th>الخدمات</th><th>الكروت</th><th>الفواتير</th><%=hasVCash ? "<th>فودافون كاش</th>" : ""%>
                            <%if (agent.getParentID().equals("7563")) {%>
                        <th>الولاء</th>
                            <%}%>
                    </tr>
                    <tr>
                   <% if (ledgerBalanceRespDto != null) {%>
                        <td><%=ledgerBalanceRespDto.getAccountBalance()%> نقطه</td>
                        <% } else {%>
                        <td><%=userBalances[0]%> نقطه</td>
                        <% }%>
                        <td><%= userBalances[1]%> نقطه</td><td><%= userBalances[2]%> نقطه</td><%=hasVCash ? "<td>" + userBalances[3] + " نقطه</td>" : ""%>
                        <%if (agent.getParentID().equals("7563")) {%>
                        <td><%=loyaltyPoints%> نقطه</td>
                        <%}%>
                    </tr>
                </table>
                <%}%>
                </td>
                <%  }%>
<!--                <td>
                    <label> نقاط المسابقة: </label>
                    <label><%=loyaltyPoints%></label>
                </td>-->
                <td style="text-align:center" ><font size="2"><%=CONFIG.formateTime(Calendar.getInstance().getTime())%></font></td>
                <!--<td style="text-align:left" ><font size="2"><a href="vfSurvey.jsp">تدريب فودافون كاش</a></font> <img src="img/guide5.png"> </td>-->
                <td style="text-align:left" ><font size="2"><a href="printGuide.jsp">دليل طباعة الكروت</a></font> <img src="img/guide5.png"> </td>
                <td align="left">
                    <div >
                        <div >
                            <div class="rawimages"><span><a href="walletServices?action=ACTION_CHANG_LANG&lang=ar">
                                        <img title="Arabic" alt="Arabic" style="border: white" src="img/eg.gif"></a></span>
                                <span id="active_language"><a href="walletServices?action=ACTION_CHANG_LANG&lang=">
                                        <img title="English (United Kingdom)" alt="English (United Kingdom)" style="border: white" src="img/en.gif">
                                    </a></span>
                            </div>
                        </div>
                    </div>
                </td>
            </div>
        </table>
    </div>
    <br><br>
    <c:set var="xmltext">
        <%=meunXML%>
    </c:set>
    <div class="content_body">
        <div class="left_menu"><!-- left menu -->
            <c:import url="/img/menuAR.xsl" var="xslt"/>
            <x:transform xml="${xmltext}" xslt="${xslt}"/>
            <a href="<%=CONFIG.APP_ROOT%>Login_Controller?action=<%=CONFIG.ACTION_LOGOUT%>"><IMG SRC="img/logoutar.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>

