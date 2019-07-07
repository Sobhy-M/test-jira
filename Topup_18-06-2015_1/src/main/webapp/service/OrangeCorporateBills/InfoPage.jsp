<%-- 
    Document   : Orange croporate bills inquiry
    Created on : Oct 29, 2017, 1:52:57 PM
    Author     : Ahmed Khaled
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="Bundle">
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="inquireFor"></fmt:message>&nbsp;<fmt:message key="ServiceName"></fmt:message></title>
                <script>
                    window.onload = onCheckboxClicked;

                    function goBack() {
                        window.history.back();
                    }

                    function cancel() {
                        document.getElementById("HOMEFORM").submit();
                    }

                    function onCheckboxClicked() {
                        var checkbox = document.getElementById("checkbox");
                        var paidAmount = document.getElementById("paidAmount");

                        if (checkbox.checked === true) {
                            paidAmount.disabled = false;
                            paidAmount.style.backgroundColor = "#ffffff";
                            paidAmount.required = true;
                        } else {
                            paidAmount.disabled = true;
                            paidAmount.style.backgroundColor = "#EDEDED";
                            paidAmount.value = "";
                        }
                    }
                    function validatePaidAmount() {
                        var checkbox = document.getElementById("checkbox");
                        var paidAmount = document.getElementById("paidAmount").value;


                        if (checkbox.checked === true) {
                            if (parseFloat(paidAmount) <= 0) {
                                document.getElementById("amountMessage").innerHTML = "<fmt:message key="MESSAGE_OrangeCorporateBills_InvalidPaidAmountAr"></fmt:message>";
                                return false;
                            } else {
                                return true;
                            }
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
            <body class="body" >
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

            <form id="HOMEFORM" action=<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%> method="POST" style="display: none;" ></form>
            <form name="dobillinquiry" action="OrangeCorporateBillsConfirmationController" method="POST" >
                <div class="content_body"  >
                    <fieldset style="width:50%; direction: rtl;" align="right">  
                        <legend align="center" ><font size="5"><fmt:message key="InquiryLabel"></fmt:message></font></legend>               
                            <table  border="1" width="100%">
                                <th><%=CONFIG.getINFO_Required(session)%></th>
                            <tr>
                                <td width="10%">
                                    <p align="right"><fmt:message key="MobileNumber"></fmt:message> : 
                                        <input type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.msisdn}" name="<%=CONFIG.PARAM_MSISDN%>" style="background-color: #EDEDED; float: left;">
                                    </p>
                                    <p align="right"><fmt:message key="CorporateAccountNumber"></fmt:message> :
                                        <input type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.accountNumber}" name="AccountNumber" style="background-color: #EDEDED; float: left;">
                                    </p>
                                    <p align="right"><fmt:message key="TotalOpenAmount"></fmt:message> :
                                        <input id="totalOpenAmount" name="totalOpenAmount" type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.totalOpenAmount}" style="background-color: #EDEDED; float: left;">
                                    </p>
                                    <p align="right">
                                        <input id="checkbox" type="checkbox" name="checkbox" onclick="onCheckboxClicked();" >
                                        <fmt:message key="PartialPayment"></fmt:message> :
                                            <input id="paidAmount" name="partialAmount" type="number" min="0" tabindex="1"  style="float: left;">
                                        </p>
                                        <div id="amountMessage" style="color: red;font-size: 15px;" />
                                    </td>

                                </tr>

                                <tr>
                                    <td style="text-align: right">
                                        <div align="center">
                                            <input type="submit" name="btnSubmit" tabindex="1"  value="<fmt:message key="pay"></fmt:message>"   style="float: right;" class="btn" onclick="return validatePaidAmount();">
                                        <input type="button" name="Back" tabindex="4" value="<fmt:message key="back"></fmt:message>"   onclick="goBack();"  class="btn">
                                        <input type="button" name="Cancel" tabindex="6"  value="<fmt:message key="cancel"></fmt:message>"  onclick="cancel();" class="btn" style="float: left;">
                                        </div>
                                </tr>
                            </table>

                            <details open="open">
                                <summary></summary>
                                <p style="font-size: medium">
                                <fmt:message key="SummeryInfo1"></fmt:message>
                                    <br>
                                <fmt:message key="SummeryInfo2"></fmt:message>
                                    <br>
                                <fmt:message key="SummeryInfo3"></fmt:message>
                                </p>

                            </details>
                        </fieldset> 
                    </div><!-- End of Table Content area-->

                </form>

                <div style="clear: both;">&nbsp;</div>
                <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
            </body>
        </html>
</fmt:bundle>