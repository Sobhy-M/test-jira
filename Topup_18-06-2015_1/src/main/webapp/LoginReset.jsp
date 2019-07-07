<%-- 
    Document   : Login.jsp
    Created on : 03/05/2009, 01:18:56 ?
    Author     : Melad
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>

<%@ page session="false"%>
<%

            if (MasaryManager.getInstance().isTheRequestFromMobile(request.getHeader("User-Agent")))
            {
                request.getSession().setAttribute(CONFIG.PARAM_FROM_MOBILE, CONFIG.PARAM_FROM_MOBILE);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + CONFIG.PAGE_LOGIN_MOBILE);
                dispatcher.forward(request, response);
                return;
            }
%>

<%
            //request.getSession().removeAttribute(CONFIG.PARAM_ROLE);
            com.masary.database.manager.MasaryManager.getInstance().logout((String) request.getSession().getAttribute(CONFIG.PARAM_PIN));

            String authenticated = (String) request.getAttribute(CONFIG.PARAM_AUTHENTICATED);
            String message = (String) request.getSession().getAttribute(CONFIG.PARAM_LOGIN_ERROR);
            request.getSession().invalidate();

%>



<HTML>
    <HEAD>
        <TITLE>Login</TITLE>
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=windows-1256">
        <link href="img/style_login.css" rel="stylesheet" type="text/css">
    </HEAD>
    <BODY BGCOLOR=#FFFFFF LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 onload="document.getElementById('<%=CONFIG.PARAM_PASSWORD%>').setAttribute('autocomplete','off');">

        <table width="100%" height="100%" border="0" cellpadding="5" cellspacing="0" hight="100%">
            <tr>
                <td valign="middle">

                    <!-- Start login form -->
                    <form action="<%=CONFIG.APP_ROOT%>Login_Controller" method="post" name="login_form" id="saveform" autocomplete="off">
                        <input type="hidden" name="action"
                               value="<%= CONFIG.ACTION_AUTHENTICATE_USER%>">

                        <div align="center">

                            <TABLE WIDTH=409 BORDER=0 CELLPADDING=0 CELLSPACING=0 HIGHT=202>
                                
                                <tr><td colspan='3' align="center"><p>Congratulations ,you will receive an SMS with the new password soon. <br></td></tr>

                                <% if (authenticated != null && authenticated.equals(CONFIG.FALSE))
            {
                out.println("<tr><td colspan='3'><font color=red><p>Login failed, Wrong username or password <br></font></td></tr>");
            }
            if (message != null)
            {
                                %>
                                <tr><td colspan='3'><%=message%></td></tr>
                                <%}%>
                                <TR >
                                    <TD  COLSPAN=2 valign="bottom"><IMG SRC="img/login_01.png" WIDTH=235 HEIGHT=65 ALT=""></TD>
                                    <TD ROWSPAN=5 valign="top"><IMG SRC="img/login_02.png" WIDTH=174 HEIGHT=202 ALT=""></TD>
                                </TR>
                                <TR>
                                    <TD ROWSPAN=4><IMG SRC="img/login_03.png" WIDTH=10 HEIGHT=137 ALT=""></TD>
                                    <TD><input name="<%=CONFIG.PARAM_USERNAME%>" class="log_in_text_box_user" type="text">
                                    </TD>
                                </TR>
                                <TR>
                                    <TD><IMG SRC="img/login_05.png" WIDTH=225 HEIGHT=32 ALT=""></TD>
                                </TR>
                                <TR>
                                    <TD><input type="password" class="log_in_text_box_pass" name="<%=CONFIG.PARAM_PASSWORD%>" autocomplete="off"></TD>
                                </TR>
                                <TR>
                                    <TD height="51" valign="middle"><input type="submit" class="log_in_btn" name="btnSubmit" value="" ></TD>
                                </TR>
                                 <TR>
                                     <Td colspan="3"><div id="footer"><a s href="passwordRecovery.jsp">Forgit my password</a></div></Td>
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