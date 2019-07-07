<%-- 
    Document   : ConfirmationPage
    Created on : Mar 17, 2019, 11:18:53 AM
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
            <title><fmt:message key="Title_RealEstate_Confirmation"></fmt:message></title>
                <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
                <script>
                    function cancel() {
                        document.getElementById("HOMEFORM").submit();
                    }
                </script>
                <style>
                    p{
                        font-size: 17px;
                        font-weight: bold;
                    }
                    input[type=text]{
                        background-color: #EDEDED;
                        float: left;
                        width: 50%;
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
            <div class="content_body">
                <form id="HOMEFORM" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" style="display: none;" ></form>
                <form action="RealEstatePayment" method="POST" >
                    <fieldset style="width: 73%; direction: rtl;" align="right">  
                        <table border="1" width="100%">
                            <th><fmt:message key="Header_RealEstate_PaymentConfirmation"/></th>     
                            <th><fmt:message key="Header_RealEstate_Calculations"/></th>    
                            <input type="hidden" id="validationId" name="validationId" value="${realEstateInquiryRepresentation.validationId}" />
                            <tr>
                                <td>
                                    <p align="right"><fmt:message key="Lable_RealEstate_ownerName"/> :
                                        <input type="text" readonly name="customerName" value="${realEstateInquiryRepresentation.customerName}" />
                                    </p>

                                    <p align="right"><fmt:message key="Lable_RealEstate_estateType"/>:
                                        <input type="text"  name="estateType"  value="${realEstateInquiryRepresentation.realEstateType}" readonly />
                                    </p>
                                    <p align="right"><fmt:message key="Lable_RealEstate_address"/>:
                                        <input type="text"  name="address" readonly value="${realEstateInquiryRepresentation.customerAddress}" />
                                    </p>
                                    <p align="right"><fmt:message key="Lable_RealEstate_requiredAmount"/>:
                                        <input type="text"  name="requiredAmount" readonly  value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${realEstateInquiryRepresentation.serviceAmount}"/>" />
                                    </p>
                                    <p align="right"><fmt:message key="Lable_RealEstate_appliedFees"/>:
                                        <input type="text"  name="appliedFees" readonly  value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${realEstateInquiryRepresentation.appliedFees}"/>" />
                                    </p>
                                    <p align="right"><fmt:message key="Lable_RealEstate_toBePaid"/>:
                                        <input type="text"  name="toBePaid" readonly 
                                        value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${realEstateInquiryRepresentation.toBepaid}"/>"/>
                                    </p>
                                </td>
                                <td>
                                    <p align="right"><fmt:message key="Lable_RealEstate_commession"/>:
                                        <input type="text" name="commission" readonly 
                                               value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${realEstateInquiryRepresentation.merchantCommission}"/>"   />
                                    </p> 
                                    <p align="right"><fmt:message key="Lable_RealEstate_toBeDeducted"/>:
                                        <input type="text" name="deductedAmount"  readonly 
                                                value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${realEstateInquiryRepresentation.transactionAmount}"/>" />
                                    </p> 
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <div align="center">
                                        <input type="submit"  value="<fmt:message key="pay" />"   style="float: right;" class="btn">
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
