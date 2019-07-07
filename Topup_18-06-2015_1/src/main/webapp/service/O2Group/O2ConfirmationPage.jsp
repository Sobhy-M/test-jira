<%--
    Document   : AddCustomer.jsp
    Created on : 05/03/2017, 11:09:49 ص
    Author     : Pora
--%>

<%@page import="com.masary.integration.dto.O2groupAcountDetailsResponse"%>
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
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    
    O2groupAcountDetailsResponse o2groupAcountDetailsResponse =(O2groupAcountDetailsResponse) request.getAttribute("o2groupAcountDetailsResponse");

%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getO2Group(session)%></title>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    </head>


    <BODY class="body"  >
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>

            <form name="AddCustomer" action="O2ChooseAmountController" method="POST">
                <div class="content_body">
                    <table>
                        <tr>
                            <td>
                                <p><%= CONFIG.getO2GroupClientName(session)%>:</p>
                        </td>
                        <td>                      
                            <input type="text" autocomplete="off" name="ClientName" value="<%=o2groupAcountDetailsResponse.getClientName()%>"   id ="ClientName" readonly style="background-color: #EDEDED;"/>                   
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p><%= CONFIG.getO2GroupPhoneNumber(session)%>:</p>
                        </td>
                        <td>                      
                            <input type="text" autocomplete="off" name="MSISDN"  value="<%=o2groupAcountDetailsResponse.getClientNumber()%>" id ="MSISDN" readonly style="background-color: #EDEDED;"/>                   
                        </td>
                    </tr>
                    <tr>
                    <td>
                        <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton" onclick="return SubmitButton()"  />
                    </td>
                    <td>
                        <input type="button" value="<%=CONFIG.getBack(session)%>"  id="submitbutton" onclick="history.go(-2)"/>
                    </td>
                </tr>
                </table>

            </div><!-- End of Table Content area-->
        </form>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
