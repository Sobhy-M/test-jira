<%-- 
    Document   : Login.jsp
    Created on : 03/05/2009, 01:18:56 ?
    Author     : Melad
--%>
<%@page import="javax.xml.bind.DatatypeConverter"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="com.masary.common.Captch"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>

<%

    MasaryManager.logger.info("IP " + request.getRemoteAddr());
    if (MasaryManager.getInstance().isTheRequestFromMobile(request.getHeader("User-Agent"))) {
        request.getSession().setAttribute(CONFIG.PARAM_FROM_MOBILE, CONFIG.PARAM_FROM_MOBILE);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + CONFIG.PAGE_LOGIN_MOBILE);
        dispatcher.forward(request, response);
        return;
    }

    String error;
    try {
        error = (String) request.getAttribute("ErrorMessage");
    } catch (Exception ex) {
        error = "";
        MasaryManager.logger.info("No error message on login page.");
    }

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
        <link href="https://cdn.e-masary.net/app/img/style_login.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-1.12.4.js"></script>
        <script type="text/javascript" src="img/CheckForms.js"></script>
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
        document.login_form.<%=CONFIG.PARAM_USERNAME%>.value = '';
        document.login_form.<%=CONFIG.PARAM_PASSWORD2%>.value = '';
        }
    </script>
    <script>
        var loc = (window.self === window.top);
        if (!loc) {
            window.top.location.href = "https://e-masary.net";
        }

        function checkPasswordLength() {
            var passwrod = document.getElementById("password").value;
            if (passwrod.length <= 4) {
                document.getElementById("passwordValidation").innerHTML = 'كلمة السر يجب ان تكون اكثر من 4 حروف او ارقام';
                return false;

            } else {
                return true;
            }
        }
        function generateCaptcha() {
            $.ajax({
                url: 'CaptchaAjaxRequest',
                type: 'get'
            }).done(function (responseData) {


                document.getElementById('imageCaptcha').src = "data:image/png;base64," + responseData;


            }).fail(function () {
                console.log('Failed');
            });
        }


    </script>
    <style>
        div.validation{
            color: #ff0000;
            font-size : 14px;
        }
    </style>
    <BODY BGCOLOR=#FFFFFF onload="generateCaptcha();"   document.getElementById('<%=CONFIG.PARAM_PASSWORD%>').setAttribute('autocomplete','off');">

          <div >
            <table width="100%" cellspacing="0" cellpadding="5" border="0" align="center" >
                <tbody>
                    <tr>
                        <td width="100%" align="center" >
                            <a href="Login_Controller">
                                <img height="100px" style="border: white;float: left" src="https://cdn.e-masary.net/app/img/newlogo.png">
                                <!--<img  style="border: white;" src="img\Anniversary2019.gif">-->
                            </a>
                            <BR>
                        </td>

                    </tr>

                    <tr>
                        <td valign="middle">
                            <form action="<%=CONFIG.APP_ROOT%>Login_Controller" method="post" name="login_form" id="saveform" autocomplete="off" onsubmit="return checkPasswordLength();" >
                                <input type="hidden" name="action" value="<%= CONFIG.ACTION_AUTHENTICATE_USER%>">
                                <input type="hidden" name="captchaText" id="captchaText">
                                <div align="center">
                                    <P><% if (error
                                                != null) {
                                            out.println(error);
                                        } %></P>
                                    <TABLE dir="rtl" WIDTH=409 BORDER=0 CELLPADDING=0 CELLSPACING=0 HIGHT=202>

                                        <% if (authenticated
                                                    != null && authenticated.equals(CONFIG.FALSE)) {
                                                out.println("<tr><td colspan='3'><font color=red><p>" + CONFIG.getLoginFiled(request.getSession()) + " <br></font></td></tr>");
                                            }
                                            if (message
                                                    != null) {
                                        %>
                                        <tr><td colspan='3'><%=message%></td></tr>
                                            <%}%>
                                        <TR>
                                            <TD  COLSPAN=2 valign="bottom"><IMG SRC="https://cdn.e-masary.net/app/img/login_01_ar.png" WIDTH=235 HEIGHT=65 ALT=""></TD>
                                            <TD ROWSPAN=5 valign="top"><IMG SRC="https://cdn.e-masary.net/app/img/login_02_ar_new.png" WIDTH=174 HEIGHT=202 ALT=""></TD>
                                        </TR>
                                        <TR>
                                            <TD ROWSPAN=4 style="display:none;"><IMG SRC="https://cdn.e-masary.net/app/img/login_03.png" WIDTH=10 HEIGHT=137 ALT=""></TD>
                                            <TD><input name="<%=CONFIG.PARAM_USERNAME%>" class="log_in_text_box_user" type="text" value="User ID">
                                            </TD>
                                        </TR>
                                        <TR>
                                            <TD><IMG SRC="https://cdn.e-masary.net/app/img/login_05_ar.png" WIDTH=225 HEIGHT=32 ALT=""></TD>
                                        </TR>
                                        <TR>
                                            <TD><input type="password" id="password" class="log_in_text_box_pass" name="<%=CONFIG.PARAM_PASSWORD2%>" autocomplete="off">
                                                <div id="passwordValidation"  class="validation"></div>
                                            </TD>
                                        </TR>
                                        <TR>
                                            <TD ROWSPAN=1 >
                                                <br/>
                                                <!--<img src="stickyImage" WIDTH=220 HEIGHT=50 ALT="" />-->
                                                <img  id="imageCaptcha" width=220 height=50>
                                                <br/>
                                                <input type="text" id="captchaAnswer" class="log_in_text_box_pass"  name="captchaAnswer" autocomplete="off"/>
                                            </TD>
                                            <td>

                                            </TD>
                                        </TR>
                                        <TR>
                                            <TD height="51" valign="middle"><input type="submit" class="log_in_btn" name="btnSubmit" value="" ></TD>
                                        </TR>
                                        <TR>
                                            <Td colspan="3"><div id="footer"><a  href="<%=CONFIG.PAGE_RESET_PASSWORD%>">نسيت كلمة السر</a> <img alt=""  src="https://cdn.e-masary.net/app/img/ssl_godaddy.png" align="left" /></div></Td>
                                        </TR>
                                    </TABLE>
                                </div>
                            </form>
                            <!-- End of login form -->
                        </td>
                    </tr>
            </table>
    </BODY>
</HTML>