<%-- 
    Document   : HomePage
    Created on : Jul 9, 2017, 10:09:15 AM
    Author     : Ahmed Khaled
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
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
        <title><%=CONFIG.getCustomerBillHead(request.getSession())%></title>
        <style>
            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }
            input.btn{
                font-size: 16px;
            }
        </style>
        <script>
//            window.onload = function () {
//                var myInput = document.getElementById('TRXID');
//                myInput.onpaste = function (e) {
//                    e.preventDefault();
//                }
//            }

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
        <form name="dobillinquiry" action="YallaPayController" method="POST">

            <input type="hidden" name="action" value="<%=CONFIG.ACTION_YALLAPAY_INFO%>" />
            <fieldset style="width: 28%; direction: rtl;" align="right">  
                <legend align="center" ><font size="4" style="font-weight: bold">
                        &nbsp;<%= CONFIG.YallaPayServiceName%></font></legend>
                <table border="1" width="100%">
                    <tr>
                        <td><p align="right" style="font-size: 14px;font-weight: bold"><%=CONFIG.YallaPayTrxLabel%> : <input id="TRXID" type="number" required name="TRXID" tabindex="2"></p>

                            <div id="custTopUpDalanceDiv" style="color: red; font-size: 12.5px;"></div>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="btnSubmit" style="display: block; margin: auto;" tabindex="3" value="<%=CONFIG.getCheck(session)%>" class="Btn"></td>
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