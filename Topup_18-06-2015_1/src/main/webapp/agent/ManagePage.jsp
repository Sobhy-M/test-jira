<%--
    Document   : ManagePage.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("2")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getManageAccount(session)%> </title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="Manage agent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_MANAGE_AGENT%>" />
        <div class="content_body"><br><br>
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getName(session)%></th>
                    <td><%=agent.getName(null)%></td>
                    <th rowspan="10"><input type="submit" name="btnSubmit" tabindex="5" 
                                            value="<%=CONFIG.getChange(session)%>" class="Btn"></th>

                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getArabicName(session)%></th>
                    <td><%=agent.getArabicName()%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getID(session)%></th>
                    <td><%=agent.getPin()%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getBalance(session)%></th>
                    <td><%=myFormatter.format(customerBalance)%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getNewPassword(session)%></th>
                    <td><input type="password" class="text"  name="<%=CONFIG.PARAM_PASSWORD%>" tabindex="1" /></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getConfirmPassword(session)%></th>
                    <td><input type="password" class="text"  name="<%=CONFIG.PARAM_CONFIRM_PASSWORD%>" tabindex="2" /></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getSecurityQuestion(session)%></th>
                    <td>
                        <%--<input  type="text" class="text"  value="" name="<%=CONFIG.PARAM_QUESTION%>" tabindex="4" />--%>
                        <select name="<%=CONFIG.PARAM_QUESTION%>" id="secquestion" class="text" tabindex="4">
                            <option value="" selected> <%=CONFIG.getSelectOne(session)%></option>
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
                    <th scope="col"><%=CONFIG.getAnswer(session)%></th>
                    <td> <input  type="text" class="text" value=""  name="<%=CONFIG.PARAM_ANSWER%>"  tabindex="5"/></td>
                </tr>
                <tr>
                    <th rowspan="2" scope="col"><%=CONFIG.getSESSIONTIMEOUT(session)%>:</th>
                    <td>
                        <%--<input  type="text" class="text"  value="" name="<%=CONFIG.PARAM_QUESTION%>" tabindex="4" />--%>
                        <select name="<%=CONFIG.PARAM_SEESSIONTIME%>" id="sessionTimeOut" class="text" tabindex="4">
                            <option value="4" selected>4</option>
                            <option value="16">16</option> 
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="color: red;" >
                        برجاء تحديد المدة الإفترضية
                    </td>
                </tr>
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>


