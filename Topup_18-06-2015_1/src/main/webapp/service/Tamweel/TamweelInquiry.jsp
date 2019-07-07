<%-- 
    Document   : TamweelInquiry
    Created on : Feb 18, 2019, 2:51:59 PM
    Author     : omar.abdellah
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
    String rolePage = CONFIG.APP_ROOT + (String) session.getAttribute(CONFIG.PARAM_ROLE) + ".jsp";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script>

    function validateCodeNum() {
        var val = document.getElementById("cusTamweelID").value;
            if (/^[0-9]+$/.test(val)) {

            document.getElementById("cusTamweelDiv").innerHTML = "";
            document.getElementById("cusTamweelDiv").disabled = true;
            return true;

        }
        // value is ok, use it
        else {
            document.getElementById("cusTamweelDiv").innerHTML = "<%= CONFIG.getErrorTamweelCodeNumAr(session)%>";
            //alert("Invalid number; must be ten digits")

            return false;
        }
    }
</script>

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
        </style>

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
        <form name="doinquiry" action="TamweelInquiryController" method="POST" >

            <input type="hidden" name="action" value="<%=CONFIG.ACTION_TAMWEEL_GETINFO%>" />
            <fieldset style="width: 28%; direction: rtl;" align="right">  
                <legend align="center" ><font size="3"><%=CONFIG.TamweelDataEntry%></font></legend>
                <table border="1" width="100%">
                    <tr>
                        <td colspan="2"><p align="right"><%=CONFIG.getTamweelCodeNumber(session)%>  
                                <input id="cusTamweelID" name="<%=CONFIG.PARAM_MSISDN%>" tabindex="2" style="float: left;width: 165px; " required /></p>
                            <div id="cusTamweelDiv" style="color: red; font-size: 12.5px;"></div>
                        </td>
                    </tr>
                    <tr>
                        <td >
                            <input style="text-align: right;" type="submit" name="btnSubmit"  onclick="return validateCodeNum();" value="<%=CONFIG.getCheck(session)%>" class="Btn"></td>
                            <td > <input  style="text-align: left;" type="button" name="btncancel" onclick="window.location.href = '<%=rolePage%>';"  value="<%=CONFIG.GetClose(session)%>" class="Btn"></td>

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
