<%--
    Document   : ManagePage.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.EmployeeDTO"%>
<%@page pageEncoding="UTF-8" %>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    String empID = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    EmployeeDTO emp = null;
    if (!empID.equals("-1")) {
        emp = MasaryManager.getInstance().getEmployee(empID);
    } else {
        emp = null;
    }
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>

<html>
    <head>
        <!--<script type="text/javascript" src="./js/jquery.js"></script>-->
        <!--<script type="text/javascript" src="./js/jquery.query-2.1.7.js"></script>-->
        <script type="text/javascript" src="./js/rainbows.js"></script>
        <!--<script type="text/javascript" src="js/jquery.min.js"></script>-->
        <title><%=CONFIG.getManageAccount(session)%></title>
    </head>

    <script>
        $(document).ready(function () {
            $('#sessionTimeOut').blur(function () {
                var val = $(this).val();
                if ((val <= 3) || (val > 60)) {
                    var mssg = "برجاء اختيار وقت ما بين 4 دقائق الى60 دقيقة";
                    document.getElementById("to1").innerHTML = mssg;
                    document.getElementById("changeButt").disabled = true;

                } else {
                    document.getElementById("to1").innerHTML = "";
                    document.getElementById("changeButt").disabled = false;
                }
            });
        });
    </script>

    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
    <%
        if (empID.equals("-1")) {
    %>
    <form name="Manage_agent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_MANAGE_AGENT%>" />
        <div class="content_body"><br><br>
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getName(session)%></th>
                    <td><%=agent.getName(null)%></td>
                    <th rowspan="10"><input  id="changeButt"type="submit" name="btnSubmit" tabindex="3"
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
					<th scope="col"><%=CONFIG.getOldPassword(session)%></th>
					<td><input type="password" class="text"
						name="<%=CONFIG.PARAM_OLD_PASSWORD%>" tabindex="1" value="" /></td>
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
                        <input type="text" name="<%=CONFIG.PARAM_SEESSIONTIME%>" id="sessionTimeOut"  class="text" tabindex="4" >

                    </td>
                </tr>
                <tr>
                    <td id="to1" style="color: red;" >
                        رجاء ملاحظة ان المدة الأفتراضية 4 دقائق
                    </td>
                </tr>
            </table>
        </div><!-- End of Table Content area-->
    </form>
    <%
    } else {
    %>

    <form name="Manage_agent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" >
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
                    <td><%=emp.getEmployeeID()%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getBalance(session)%></th>
                    <td><%=myFormatter.format(emp.getCurBalance())%></td>
                </tr>
                  <tr>
					<th scope="col"><%=CONFIG.getOldPassword(session)%></th>
					<td><input type="password" class="text"
						name="<%=CONFIG.PARAM_OLD_PASSWORD%>" tabindex="1" value="" /></td>
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

            </table>
        </div><!-- End of Table Content area-->
    </form>

    <% }%>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>