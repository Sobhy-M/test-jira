<%-- 
    Document   : ValuInfo
    Created on : Oct 29, 2017, 1:52:57 PM
    Author     : Ahmed Khaled
--%>


<%@page import="java.util.Date"%>
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
            <title><fmt:message key="ConfirmationLabel"></fmt:message></title>

            <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <script>
            window.onload = partialPaymentValue;
            function goBack() {
                window.history.back();
            }

            function cancel() {
                document.getElementById("HOMEFORM").submit();
            }

            function partialPaymentValue() {
                var checkbox = document.getElementById("checkbox").checked;
                if (checkbox === false) {
                    document.getElementById("paidAmount").value = "";
                }
            }
        </script>
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
        <form name="dobillinquiry" action="OrangeCorporateBillsPaymentController" method="POST" >
            <div class="content_body"  >
                <fieldset style="width:70%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><fmt:message key="ConfirmationLabel"></fmt:message></font></legend>               
                    <table  border="1" width="100%">
                        <th><fmt:message key="requiredInfo"></fmt:message></th>
                        <th><fmt:message key="account"></fmt:message></th>
                        <tr>
                            <td>
                                <p align="right"><fmt:message key="MobileNumber"></fmt:message> : 
                                    <input type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.msisdn}" name="<%=CONFIG.PARAM_MSISDN%>" style="background-color: #EDEDED; float: left;">
                                </p>
                                <p align="right"><fmt:message key="CorporateAccountNumber"></fmt:message> :
                                    <input type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.accountNumber}" name="AccountNumber" style="background-color: #EDEDED; float: left;">
                                </p>

                                <p align="right"><fmt:message key="TotalOpenAmount"></fmt:message> :
                                    <input type="text" readonly tabindex="1"  value="${orangeCorporateInquiryResponse.totalOpenAmount}" style="background-color: #EDEDED; float: left;">
                                </p>
                                <p align="right">
                                    <input id="checkbox" type="checkbox" ${orangeCorporateRequest.partialPayment ? "checked" : ""} onClick="return false;" >
                                    <fmt:message key="PartialPayment"></fmt:message> :
                                    <input id="paidAmount" type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.paidAmount}" style="background-color: #EDEDED; float: left;">
                                </p>
                                <div id="amountMessage" style="color: red;font-size: 15px;" />
                            </td>
                            <td>
                                <p align="right"><fmt:message key="AppliedFees"></fmt:message> :<input type="text" name="Fees" value="${orangeCorporateInquiryResponse.appliedFees}"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><fmt:message key="Commission"></fmt:message> :<input type="text"  value="${orangeCorporateInquiryResponse.merchantCommission}"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><fmt:message key="DeductedAmount"></fmt:message> :<input type="text"  value="${orangeCorporateInquiryResponse.transactionAmount}"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="3">
                                <div align="center">
                                    <input type="submit" name="btnSubmit" tabindex="1"  value="<fmt:message key="confirm"></fmt:message>"   style="float: right;" class="btn">
                                    <input type="button" name="Cancel" tabindex="6"  value="<fmt:message key="cancel"></fmt:message>"  onclick="cancel();" class="btn" style="float: left;">
                                    <input type="button" name="Back" tabindex="4" value="<fmt:message key="back"></fmt:message>"  onclick="goBack();"  class="btn">

                                </div>
                            </td>
                        </tr>
                    </table>
                </fieldset> 
            </div><!-- End of Table Content area-->

        </form>

        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </body>
    </html>
</fmt:bundle>