<%-- 
    Document   : Login.jsp
    Created on : 03/05/2009, 01:18:56 ?
    Author     : Melad
--%>

<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@ page session="false"%>
<?xml version="1.0"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN"
    "http://www.wapforum.org/DTD/wml_1.1.xml">


<%
            //    response.setContentType("text/vnd.wap.wml");
            request.getSession().removeAttribute(CONFIG.PARAM_ROLE);
            String authenticated = (String) request.getAttribute(CONFIG.PARAM_AUTHENTICATED);
            request.getSession().setAttribute(CONFIG.PARAM_FROM_MOBILE, CONFIG.PARAM_FROM_MOBILE);
            MasaryManager.logger.info("IP " + request.getRemoteAddr());
            MasaryManager.logger.info(request.getHeader("User-Agent"));

%>


<HTML>
    <HEAD>
        <TITLE>Login</TITLE>
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
        <style type="text/css" media="screen">@import "img/jq/theme.min.css";</style>
        <link href="../img/jq/theme.min.css" rel="stylesheet" type="text/css">
    </HEAD>
    <BODY >
        <div>
            <div class="toolbar">
                <h1>Login</h1>
            </div>
            <div align="center">   <td align="center"><b><font size="3" color="green">! عزيزي العميل يرجي الانتباه انه تم وقف جميع الارقام القديمه والعمل فقط بالارقام الجديده المكونه من 11 رقم .. وشكرا </font></b></td></div>
            <form action="<%=CONFIG.APP_ROOT%>Login_Controller" method="post" name="login_form" id="saveform">
                <ul>
                    <!-- Start login form -->
                    <input type="hidden" name="action"
                           value="<%= CONFIG.ACTION_AUTHENTICATE_USER%>">
                    <% if (authenticated != null && authenticated.equals(CONFIG.FALSE)) {
                                    out.println("<li><font color=red><p>Login failed, Wrong username or password <br></font></li>");
                                }
                    %>
                    <li style="text-align: left">Mobile number or Id</li>
                    <%--<TD ROWSPAN=5 valign="top"><IMG SRC="img/login_02.png" WIDTH=174 HEIGHT=202 ALT=""></TD>--%>
                    <%--<TD ROWSPAN=4><IMG SRC="img/login_03.png" WIDTH=10 HEIGHT=137 ALT=""></TD>--%>
                    <li><input name="<%=CONFIG.PARAM_USERNAME%>" type="text"></li>
                    <li style="text-align: left">Password</li>
                    <li><input type="password" name="<%=CONFIG.PARAM_PASSWORD%>"></li>
                    <li><input type="submit"  name="btnSubmit" value="Login" ></li>
                    <!-- End of login form -->
                </ul>
            </form>
            <div class="info">
                <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
            </div>
        </div>
    </BODY>
</HTML>