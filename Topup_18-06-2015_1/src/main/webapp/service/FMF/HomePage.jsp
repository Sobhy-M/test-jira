<%-- 
    Document   : HomePage
    Created on : Jul 12, 2017, 12:57:50 PM
    Author     : Ahmed Khaled
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FMF</title>
        <style>
            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }
            input.btn{
                font-size: 14px;
            }
            p{
                font-size: 14px;
                font-weight: bold;
            }
        </style>
        <script>
            function checkCodeLength() {
                var customerCode = document.getElementById("code").value;
                if (customerCode.length < 14)
                {
                    document.getElementById("errorMessage").innerHTML = 'كود العميل يجب ان يكون مكون من 14 رقم';
                    return false;
                }
            }
        </script>
    </head>
    <body class="body">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
    <div class="content_body"  >
        <form name="dobillinquiry" action="FMFHomeController" method="POST" onsubmit="return checkCodeLength();">

            <input type="hidden" name="action" value="<%=CONFIG.ACTION_FMF_GETINFO%>" />
            <fieldset style="width: 28%; direction: rtl;" align="right">  
                <legend align="center" ><font size="4" style="font-weight: bold"><%= CONFIG.FMF_SERVICE_NAME%></font></legend>
                <table border="1" width="100%">
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.FMF_CUSTOMER_CODE%> :
                                <input id="code" maxlength="14" type="number" required name="CUSTOMERCODE" tabindex="2">
                            </p>
                            <div id="errorMessage" style="color: red; font-size: 12.5px;"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="btnSubmit" tabindex="3" style="display: block; margin: auto;" value="<%=CONFIG.getCheck(session)%>" class="btn" ></td>
                    </tr>
                </table>
            </fieldset> 
        </form>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>