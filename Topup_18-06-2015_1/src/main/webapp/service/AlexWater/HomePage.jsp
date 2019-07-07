
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.controllers.AlexWater.AlexWaterProperities"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>

<%
    final String lang = (String) request.getSession().getAttribute("lang");

    MasaryManager.logger.info("Alex Water Main Page jsp entered");

    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);

    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    String rolePage = CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp";

%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
    <head>
        <title><%=AlexWaterProperities.serviceName%></title>
        <style>
            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }
        </style>
        <script>
            function validateElectricityNumber() {
                var electricityNumber = document.getElementById("electricityNumber").value;
                if (electricityNumber.length < 1 || electricityNumber.length > 14)
                {
                    document.getElementById("errorMessage").innerHTML = 'اقل رقم يمكن قبوله رقم واحد , واكبر عدد ارقام يمكن قبولها 14 رقم';
                    return false;
                } else {
                    
                    return true;
                }
            }
        </script>
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <%if (lang.equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <div class="content_body"  >
                <form action= "AlexWaterInquiry" method="POST" style="font-weight: bold" onsubmit="return validateElectricityNumber();">
                    <fieldset style="width: 30%; direction: rtl;" align="right">  
                        <legend align="center" ><font size="4" style="font-weight: bold"><%= CONFIG.INFORNEEDED%></font></legend>
                    <table border="1" width="100%">
                        <tr>
                            <td>
                                <p align="right"><%=AlexWaterProperities.subscriptionTitle%> :
                                    <input id="electricityNumber" name="electricityNumber" type="number">
                                </p>
                                <div id="errorMessage" style="color: red; font-size: 12.5px;"></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" name="btnSubmit" tabindex="1"  value="<%=AlexWaterProperities.inquiryButtonString%>">
                                <input type="button" name="btnCancel" tabindex="2" style="float: left" value="<%=AlexWaterProperities.cancelButtonString%>" onclick="window.location.href = '<%=rolePage%>';">
                            </td>
                        </tr>
                    </table>
                </fieldset> 
            </form>
        </div>

        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>