<%-- 
    Document   : Login.jsp
    Created on : 03/05/2009, 01:18:56 ?
    Author     : Melad
--%>
<%@page language="java" contentType="text/html; charset=windows-1256" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@ page session="false"%>
<%

    MasaryManager.logger.info("IP " + request.getRemoteAddr());
    if (MasaryManager.getInstance().isTheRequestFromMobile(request.getHeader("User-Agent"))) {
        request.getSession().setAttribute(CONFIG.PARAM_FROM_MOBILE, CONFIG.PARAM_FROM_MOBILE);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + CONFIG.PAGE_LOGIN_MOBILE);
        dispatcher.forward(request, response);
        return;
    }
%>

<%
    String authenticated = (String) request.getAttribute(CONFIG.PARAM_AUTHENTICATED);
    String message = (String) request.getSession().getAttribute(CONFIG.PARAM_LOGIN_ERROR);
    String lang = (String) request.getSession().getAttribute(CONFIG.lang);
    request.getSession().invalidate();
    if (lang == null) {
        lang = "ar";
    }
    request.getSession().setAttribute(CONFIG.lang, lang);
%>
<HTML>
    <HEAD>
        <TITLE>Login</TITLE>
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=windows-1256">
        <link href="img/style_login.css" rel="stylesheet" type="text/css">
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-56777377-1', 'auto');
            ga('send', 'pageview');
        </script>
    </HEAD>

    <script language="JavaScript" type="text/JavaScript">
        function checkBrowser() {
        var ua = navigator.userAgent;
        ua=ua.toLowerCase();
        if (ua.indexOf("chrome") >= 0)
        {
        window.location = 'chromeError.jsp';
        }
        }
    </script>

    <script language="JavaScript" type="text/JavaScript">
        function resetFiled() {
        document.login_form.<%=CONFIG.PARAM_Activation_Code%>.value = '';

        }
    </script>
    <script>
        var loc = (window.self === window.top);
        if (!loc) {
            window.top.location.href = "https://e-masary.net";
        }

    </script>

    <BODY BGCOLOR=#FFFFFF onload="resetFiled();"  >
          <div >
            <table width="950" cellspacing="0" cellpadding="5" border="0">
                <tbody>
                    <tr>
                        <td width="780" align="left" rowspan="2">
                            <a href="Login_Controller">
                                <img height="100px" style="border: white" src="img/newlogo.png">
                            </a>
                        </td>
                        <td height="20px" width="161px" align="right">
                            <div >
                            </div>
                        </td>
                    </tr>
                   
                </tbody>
            </table>
        </div>
        <table width="80%"  dir="" border="0" cellpadding="5" cellspacing="0" hight="80%">
            <tr>
                <td valign="middle">
                    <form action="<%=CONFIG.APP_ROOT%>Login_Controller" method="post" align="center" name="login_form" id="saveform" autocomplete="off">
                        <input type="hidden" name="action" value="<%= CONFIG.ACTION_AUTHENTICATE_USER%>">
                        <input type="hidden" name="<%=CONFIG.PARAM_USERNAME%>" value="<%= request.getParameter(CONFIG.PARAM_USERNAME)%>">
                        <input type="hidden" name="<%=CONFIG.PARAM_PASSWORD2%>" value="<%= request.getParameter(CONFIG.PARAM_PASSWORD2)%>">
                        <div align="center">
                            <table dir="rtl" width="50%"  CELLPADDING="0" CELLSPACING="0" HIGHT="202">

                                <% if (authenticated != null && authenticated.equals(CONFIG.FALSE)) {
                                        out.println("<tr><td colspan='3'><font color=red><p>" + CONFIG.getLoginFiled(request.getSession()) + " <br></font></td></tr>");
                                    }
                                    if (message != null) {
                                %>
                                <tr><td colspan='3'><%=message%></td></tr>
                                    <%}%>
                              
                                <TR>
                                    <td><label  WIDTH="225" HEIGHT="32">كود التفعيل</label></td>
                                </TR>
                                <TR>
                                    <td><input type="text" required title="برجاء ادخال الكود التفعيلي الخاص بك" class="log_in_text_box_pass"  autofocus name="<%=CONFIG.PARAM_Activation_Code%>" autocomplete="off"/></td>
                                </TR>
                                <TR>
                                    <TD height="51" valign="middle"><input type="submit" class="log_in_btn" name="btnSubmit" value="" ></TD>
                                </TR>
                               
                            </TABLE>
                        </div>
                    </form>
                    <!-- End of login form -->
                </td>
            </tr>
        </table>
        <!--    <img alt=""  src="img/ssl_godaddy.png" align="left" />-->
    </BODY>
</HTML>