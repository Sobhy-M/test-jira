<%-- 
    Document   : ValuInquiry
    Created on : Oct 29, 2017, 1:52:28 PM
    Author     : amira
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
<script>

    function validateMobileNum() {
        var val = document.getElementById("cusValuID").value;
        if (/^01\d{9}$/i.test(val)) {

            document.getElementById("custValuDiv").innerHTML = "";
            document.getElementById("custValuDiv").disabled = true;
            return true;

//if (/^\d{10}$/.test(val)) {
//    return true;
//   


        }
        // value is ok, use it
        else {
            document.getElementById("custValuDiv").innerHTML = "<%= CONFIG.getErrorValuMobileNumAr(session)%>";
            //alert("Invalid number; must be ten digits")

            return false;
        }
    }
</script>


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
        <form name="doinquiry" action="ValuInquiryController" method="POST" >

            <input type="hidden" name="action" value="<%=CONFIG.ACTION_VALU_GETINFO%>" />
            <fieldset style="width: 28%; direction: rtl;" align="right">  
                <legend align="center" ><font size="3"><%=CONFIG.ValuDataEntry%></font></legend>
                <table border="1" width="100%">
                    <tr>
                        <td colspan="2"><p align="right"><%=CONFIG.ValuLabel%>  <input id="cusValuID" name="<%=CONFIG.PARAM_MSISDN%>" tabindex="2" style="float: left;width: 165px; " required /></p>
                            <div id="custValuDiv" style="color: red; font-size: 12.5px;"></div>
                        </td>
                    </tr>
                    <tr>
                        <td ><input style="text-align: right;" type="submit" name="btnSubmit"  onclick="return validateMobileNum();" value="<%=CONFIG.getCheck(session)%>" class="Btn"></td>
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
