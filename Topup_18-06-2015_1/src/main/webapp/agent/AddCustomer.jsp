<%--
    Document   : AddCustomer.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="java.util.HashMap"%>
<%@page import="com.masary.database.dto.Governorate"%>
<%@page import="com.masary.database.dto.Country"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("2")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }

%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAddCustomer(session)%></title>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="./js/AddNewWalletJS/AddNewWalletJS.js"></script>
        <script src="./js/AddNewWalletJS/notify.js"></script>   
    </head>


    <BODY class="body"  >
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>

            <form name="AddCustomer" action="AddNewWalletController" method="POST">
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_ADD_CUSTOMER%>" />
            <input type="hidden" name="<%=CONFIG.VALID%>" value="<%=CONFIG.VALID%>" />
            <div class="content_body">
                <table >
                    <div id="AdCustPhoneHelp" 
                         <p style="font:message-box"><%=session.getAttribute("ErrorMessage") == null ? "" : session.getAttribute("ErrorMessage")%></p>
                    </div>
                    <div id="AdCustPhoneDiv"></div>
                    <tr>
                        <th><%=CONFIG.getMobileNumber(session)%> *</th>
                        <td>
                            <div class="requriedclass">
                                <div>
                                    <input type="text" maxlength="11" autocomplete="off" name="<%=CONFIG.PARAM_MSISDN%>"   id ="MSISDN"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center" colspan="2">
                            <input type="submit" name="btnSubmit" value="<%=CONFIG.getNext(session)%>" onclick="return BtnSubmit()">
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
