<%-- 
    Document   : Orange croporate bills home
    Created on : Oct 29, 2017, 1:52:28 PM
    Author     : Ahmed Khaled
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<fmt:bundle basename="Bundle">
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="inquireFor"></fmt:message></title>
                <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
                <script>
                    $(document).ready(function () {
                        $("#accountNumber").on("keypress keyup blur change", function (event) {
                            $(this).val($(this).val().replace(/[^0-9\.]/g, ''));
                            if ((event.which !== 46) && (event.which < 48 || event.which > 57)) {
                                event.preventDefault();
                            }
                        });
                    });
                    $(document).ready(function () {
                        $("#msisdn").on("keypress keyup blur change", function (event) {
                            $(this).val($(this).val().replace(/^[a-zA-Z]+(([_][a-zA-Z])?[a-zA-Z]*)*$/i, ''));
                            if ((event.which !== 46) && (event.which < 48 || event.which > 57)) {
                                event.preventDefault();
                            }
                        });
                    });
                    function validateMobileNum() {
                        var val = document.getElementById("msisdn").value;
                        if (/^012\d{8}$/i.test(val)) {

                            document.getElementById("msisdnDiv").innerHTML = "";
                            document.getElementById("msisdnDiv").disabled = true;
                            return true;

                        } else {
                            document.getElementById("msisdnDiv").innerHTML = "<fmt:message key="MESSAGE_OrangeCorporateBills_NonPaymentResponsibleAccount6665Ar"></fmt:message>";
                            return false;
                        }
                    }
                </script>
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
                <div>
                <c:choose>
                    <c:when test="${lang== ''}">
                        <jsp:include page="../../img/menuList.jsp"></jsp:include>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="../../img/menuListar.jsp"></jsp:include>
                    </c:otherwise>
                </c:choose>
            </div>   
            <div class="content_body"  >
                <form name="doinquiry" action="OrangeCorporateBillsController" method="POST" >

                    <input type="hidden" name="action" value="<fmt:message key="ACTION_OrangeCorporateBills_GETINFO"></fmt:message>" />
                        <fieldset style="width: 28%; direction: rtl;" align="right">  
                            <legend align="right" ><font size="3"><fmt:message key="InquiryLabel"></fmt:message></font></legend>
                            <table border="1" width="100%">
                                <tr>
                                    <td colspan="2"><p align="right"><fmt:message key="MobileNumber"></fmt:message> :
                                        <input id="msisdn" name="<%=CONFIG.PARAM_MSISDN%>" maxlength="11" tabindex="2" style="float: left;" required /></p>
                                    <div id="msisdnDiv" style="color: red; font-size: 12.5px;"></div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><p align="right"><fmt:message key="CorporateAccountNumber"></fmt:message> :
                                        <input id="accountNumber" name="AccountNumber" tabindex="2" style="float: left;" required type="text" maxlength="150"/></p>
                                        <div id="accountNumberDiv" style="color: red; font-size: 12.5px;"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td ><input style="text-align: right;" type="submit" name="btnSubmit"  onclick="return validateMobileNum();" value="<fmt:message key="inquire"></fmt:message>" class="Btn"></td>
                                </tr>
                            </table>
                            <details open="open">
                                <summary></summary>
                                <fmt:message key="SummeryHome"></fmt:message>
                            </details>
                        </fieldset> 
                    </form>
                </div><!-- End of Table Content area-->
            </div><!-- End content body -->
            <div style="clear: both;">&nbsp;</div>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div>

    </body>
</html>
</fmt:bundle>
