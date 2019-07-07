<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<link href="img/style.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Password recovery page</title>
    </head>
    <BODY class="body">
        <div class="main_body" align="center">
            <div>
                <table width="940px" cellspacing="0" cellpadding="5" border="0">
                    <tbody>
                    <div class="top_header" align="center" style="background:url(img/header_bg_new.png) no-repeat"><!-- Header div-->
                        <tr>

                        </tr>
                    </div>
                    </tbody>
                </table>
            </div>
            <br>
            <div class="content_body">
                <br><br>
                <br><br>
                <%
                            String error = (String) session.getAttribute("ErrorMessage");
                       //     //System.out.println(error);
                            if (error != null) {
                %>
                <font color="red" size="2"><%=error%></font>
                <%}%>
                <form action="<%=CONFIG.APP_ROOT%>Login_Controller" method=post  name="admin_form" id="reset password">
                    <input type="hidden" name="action" value="<%=CONFIG.ACTION_RESET_PASSWORD%>"/>
                    <table style=" border-top: 3px solid #C1DAD7;">
                        <%--  <tr bgcolor="red">
                              <td  colspan="3" style="background-color:#4f6b72">

                                </td>
                            </tr>--%>
                        <tr>
                            <th scope="col">Mobile Number</th>
                            <td align="left"><input type="text" class="text"  name="<%=CONFIG.PARAM_MSISDN%>" tabindex="1" />

                                <div id="AdCustPhoneHelp">
                                    <%--<font style="font:message-box">Example: (0020123456789) Or (0123456789)</font>--%>
                                </div>
                                <div id="AdCustPhoneDiv"></div>
                            </td>
                            <th rowspan="5"><input type="submit" name="btnSubmit" tabindex="4" value="Reset my password" class="Btn">
                            </th>
                        </tr>
                        <tr>
                            <th scope="col">Security Question</th>
                            <td align="left">
                                <%--<input type="text" class="text"  name="<%=CONFIG.PARAM_QUESTION%>" tabindex="4" />--%>
                                <select name="<%=CONFIG.PARAM_QUESTION%>" id="secquestion" class="text" tabindex="2">
                                    <option value="" selected>- Select One -</option>
                                    <option value="Where did you meet your spouse?">Where did you meet your spouse?</option>
                                    <option value="What was the name of your first school?">What was the name of your first school?</option>
                                    <option value="Who was your childhood hero?">Who was your childhood hero?</option>
                                    <option value="What is your favorite pastime?">What is your favorite pastime?</option>
                                    <option value="What is your favorite sports team?">What is your favorite sports team?</option>
                                    <option value="What is your father middle name?">What is your father's middle name?</option>
                                    <option value="What was your high school mascot?">What was your high school mascot?</option>
                                    <option value="What make was your first car or bike?">What make was your first car or bike?</option>
                                    <option value="What is your pets name?">What is your pet's name?</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="col">Answer</th>
                            <td align="left"> <input type="text" class="text"  name="<%=CONFIG.PARAM_ANSWER%>"  tabindex="3"/></td>
                        </tr>
                        <tr>
                            <th>Validation</th>
                            <td>
                                <img src="stickyImg" alt="Captcha"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="col">Validation Answer</th>
                            <td align="left"> <input type="text" class="text"  name="<%=CONFIG.PARAM_ANSWER_CAPTCHA%>"  tabindex="3"/></td>
                        </tr>
                    </table>

                </form>
            </div>
            <div style="clear: both;">&nbsp;</div>

            <div id="footer"><jsp:include page="img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->
    </body>
</html>



