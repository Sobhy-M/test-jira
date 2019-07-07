<%-- 
    Document   : AddEmployeeConfirmation
    Created on : Mar 25, 2012, 9:54:03 AM
    Author     : Hammad
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("3")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);

%>

<%    session = request.getSession();
%>
<html>
    <head>


        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAddCustomerConfirmation(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="AddCustomer" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST" onsubmit="return ValidateAddCustForm()" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_ADD_EMPLOYEE%>" />
        <div class="content_body"><br><br>
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getName(session)%></th>
                    <td><input type="text" class="text"  value="<%=requestParameters.get(CONFIG.PARAM_USERNAME)%>" name="<%=CONFIG.PARAM_USERNAME%>" tabindex="3"  readonly style="border:0" />
                        <div id="AdCustNameDiv"></div>
                    </td>
                    <th rowspan="3"><input type="submit" name="btnSubmit" tabindex="1"value="<%=CONFIG.getSave(session)%>" class="Btn">
                        <br/>
                        <br/>
                        <input type="button" dir="rtl" name="btnSubmit" tabindex="2"
                               value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1);return true;"></th>

                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getArabicName(session)%></th>
                    <td><input type="text" class="text"  value="<%=session.getAttribute(CONFIG.PARAM_USERNAME_ARABIC)%>" name="<%=CONFIG.PARAM_USERNAME_ARABIC%>" tabindex="4"  readonly style="border:0"/>
                        <div id="AdCustNameDiv"></div>
                    </td>
                </tr>


                <tr>
                    <th scope="col"><%=CONFIG.getMobileNumber(session)%></th>
                    <td><input type="text" class="text"  value="<%=requestParameters.get(CONFIG.PARAM_MSISDN)%>" name="<%=CONFIG.PARAM_MSISDN%>" tabindex="3" readonly style="border:0"/>
                        <div id="AdCustPhoneDiv"></div>
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