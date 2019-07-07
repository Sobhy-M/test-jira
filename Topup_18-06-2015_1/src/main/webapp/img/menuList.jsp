<%@ page language="java" contentType="text/html; charset=windows-1256"  pageEncoding="UTF-8"%>

<%@page import="com.masary.integration.dto.LedgerBalanceRespDto"%>
<%@page import="com.masary.integration.LedgerClient"%>
<%@page import="com.masary.database.dto.EmployeeDTO"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Biller"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<HTML>
    <HEAD>


        <script type="text/javascript" src="./js/rainbows.js"></script>
        <script type="text/javascript" src="img/c_config.js"></script>
        <script type="text/javascript" src="img/c_smartmenus.js"></script>
        <link href="img/style.css" rel="stylesheet" type="text/css">
        <link rel="icon" type="image/ico" href="/img/favicon.ico">


  
        <%
            AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
            String meunXML = (String) ((LoginDto) request.getSession().getAttribute("Login")).getMenuXML();
            String[] userBalances = null;
            LedgerBalanceRespDto ledgerBalanceRespDto = null;
            userBalances = MasaryManager.getInstance().customerBalances((String) request.getSession().getAttribute(CONFIG.PARAM_PIN));
            session = request.getSession();
            session.setAttribute(CONFIG.lang, "");
            session.setAttribute("services", MasaryManager.getInstance().getCustomerServices(agent.getPin()));
            session.setAttribute("showTree", MasaryManager.getInstance().showTree(agent.getPin()));
            String loyaltyPoints = "0";
            boolean hasVCash = meunXML.contains("2003") ? true : false;
        %>

</HEAD>
<body>
    <div class="main_body" align="center" >
        <div>
            <table width="940px" cellspacing="0" cellpadding="5" border="0">
                <tbody  >
                <div class="top_header" align="center" style="background:url(img/header_bg_new.png) no-repeat"><!-- Header div-->
                    <tr>
                        <%  if (session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {%>
                        <td ><font size="2">Welcome  <%=agent.getName(null)%>      </font>                </td>
                            <%   } else {%>
                        <td ><font size="2">Welcome  <%=((EmployeeDTO) ((LoginDto) request.getSession().getAttribute("Login")).getEmp()).getEmployeeName()%>     </font>                </td>
                            <%   }%>
                        <td  style="text-align:center" >
                            <%  if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && ((EmployeeDTO) ((LoginDto) request.getSession().getAttribute("Login")).getEmp()).isShowMyBalance()) {%>
                            <table align="center">
                                <tr>
                                    <th>Allowed</th><th>Current Balance</th><th>Vouchers</th><th>Bills</th><%=hasVCash ? "<th>Vodafone Cash</th>" : ""%>
                                </tr>
                                <tr><td><%=((EmployeeDTO) ((LoginDto) request.getSession().getAttribute("Login")).getEmp()).getAllowedBalance()%> points</td><td><%=((EmployeeDTO) ((LoginDto) request.getSession().getAttribute("Login")).getEmp()).getCurBalance()%> points</td><td><%= userBalances[1]%> points</td><td><%= userBalances[2]%> points</td><%=hasVCash ? "<td>" + userBalances[3] + " points</td>" : ""%>
                                </tr>
                            </table>
                        </td>
                        <% } else {%>
                        <%if (agent.isAutoAllocate().equals("F")) {%>
                        <%=hasVCash ? "<table align='center'><tr><th>Services</th><th>Vodafone Cash</th></tr><tr><td>" + userBalances[0] + " points</td> <td>" + userBalances[3] + " points</td></tr></table>" : "Your balance is  " + userBalances[0] + " points "%>

                        <%} else {%>
                    <table align="center">
                        <tr>
                            <th>Services</th><th>Vouchers</th><th>Bills</th><%=hasVCash ? "<th>Vodafone Cash</th>" : ""%>
                                <% if (((AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent()).getParentID().equals("7563")) {%>
                            <th>Loyalty</th>
                                <%}%>
                        </tr>
                        <tr>
                            <% if(ledgerBalanceRespDto != null){ %>
                                <td><%=ledgerBalanceRespDto.getAccountBalance() %> points</td>
                            <% } else{ %>
                                <td><%=userBalances[0] %> points</td>
                            <% } %>
                            
                            <td><%= userBalances[1]%> points</td><td><%= userBalances[2]%> points</td><%=hasVCash ? "<td>" + userBalances[3] + " points</td>" : ""%>
                            <% if (((AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent()).getParentID().equals("7563")) {%>
                            <td><%=loyaltyPoints%> points</td>
                            <%}%>
                        </tr>
                    </table>
                    <%}%>
                    </td>
                    <%  }%>
                    <td style="text-align:center" ><font size="2"><%= CONFIG.formateTime(java.util.Calendar.getInstance().getTime())%></font></td>
                    <td align="right">
                        <div >
                            <div >
                                <div class="rawimages"><span><a href="walletServices?action=ACTION_CHANG_LANG&lang=ar&<%=CONFIG.SESSIONID%>=<%=session.getId()%>">
                                            <img title="Arabic" alt="Arabic" style="border: white" src="img/eg.gif"></a></span>
                                    <span id="active_language"><a href="walletServices?action=ACTION_CHANG_LANG&lang=">
                                            <img title="English (United Kingdom)" alt="English (United Kingdom)" style="border: white" src="img/en.gif">
                                        </a></span>
                                </div>
                            </div>
                        </div>
                    </td>
                    </tr>
                </div>
                </tbody>
            </table>
        </div>
        <br>
        <c:set var="xmltext">
            <%=meunXML%>
        </c:set>
        <div class="content_body">
            <div class="left_menu"><!-- left menu -->
                <c:import url="/img/menuEN.xsl" var="xslt"/>
                <x:transform xml="${xmltext}" xslt="${xslt}"/>
                <a href="<%=CONFIG.APP_ROOT%>walletServices?action=<%=CONFIG.ACTION_LOGOUT%>"><IMG SRC="img/logout.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>

            </body>
</HTML>