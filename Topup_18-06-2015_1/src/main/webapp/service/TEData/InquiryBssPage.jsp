<%-- 
    Document   : InquiryBssPage
    Created on : Feb 24, 2019, 1:11:22 PM
    Author     : user
--%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<fmt:bundle basename="Bundle">
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="Title_TeDataBills_Inquiry"></fmt:message></title>
                <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
                <script>
                    function cancel() {
                        document.getElementById("HOMEFORM").submit();
                    }
                    $(document).ready(function () {
                        $("#amount").on("keypress keyup blur change", function (event) {
                            $(this).val($(this).val().replace(/[^0-9\.]/g, ''));
                            if (((event.which !== 46 || (event.which === 46 && $(this).val() === '')) ||
                                    $(this).val().indexOf('.') !== -1) && (event.which < 48 || event.which > 57)) {
                                event.preventDefault();
                            }
                        });
                    });
                    function InvalidMsg(textbox) {
                        if (textbox.value === '') {
                            textbox.setCustomValidity('برجاء ادخال مبلغ صحيح');
                        }else if (textbox.value < 5 || textbox.value > 10000) {
                            textbox.setCustomValidity('برجاء ادخال مبلغ صحيح');
                        } else {
                            textbox.setCustomValidity('');
                        }
                        return true;
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
                    p{
                        font-size: 17px;
                        font-weight: bold;
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
                <form id="HOMEFORM" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" style="display: none;" ></form>
                <form action="TeDataBssConfirmation" method="POST" >
                    <fieldset style="width: 30%; direction: rtl;" align="right">  
                        <table border="1" width="100%">
                            <tr>
                                <td colspan="2">
                                    <p align="right">
                                        <fmt:message key="Lable_TeDataBills_PhoneNumber"/>
                                        <input type="tel" id="phoneNumber" name="phoneNumber" value="${param.BILLING_ACCOUNT}" style="float: left;background-color: #EDEDED;" readonly />
                                    </p>
                                    <p align="right">
                                        <fmt:message key="Lable_TeDataBills_Amount"/>
                                        <input type="number" id="amount" min="5" max="10000" step="0.01" oninvalid="InvalidMsg(this);" name="amount"style="float: left;width: 67%" required />
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <div align="center">
                                        <input type="submit"  value="<fmt:message key="confirm" />"   style="float: right;" class="btn">
                                        <input type="button"  value="<fmt:message key="cancel"/>" style="float: left;"  onclick="cancel();" class="btn">
                                        <input type="button"  value="<fmt:message key="back"/>"   onclick="history.go(-1);"  class="btn">
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </fieldset> 
                </form>
            </div><!-- End of Table Content area-->
            <div style="clear: both;">&nbsp;</div>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
            </body>
        </html>
</fmt:bundle>