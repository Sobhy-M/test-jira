<%-- 
    Document   : ConfirmationPage
    Created on : Apr 15, 2019, 10:52:38 AM
    Author     : omar.abdellah
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
            <title><fmt:message key="Title_EtisalatGifts_Confirmation"/></title>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script>
                function cancel() {
                    document.getElementById("HOMEFORM").submit();
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
                <form action="EtisalatGiftsPayment" method="POST" >
                    <input type="hidden" name="validationId"  value="${inquiryRepresentation.validationId}"/>
                    <fieldset style="width: 45%; direction: rtl;" align="right">  
                        <table border="1" width="100%">
                            <tr>
                                <td colspan="2">
                                    <p align="right">
                                        <fmt:message key="Lable_EtisalatGifts_MobileNumber"/>
                                        <input readonly  id="phoneNumber" name="phoneNumber" style="background-color: #EDEDED; float: left;" value="${inquiryRepresentation.mobileNumber}" style="float: left;"  />
                                    </p>

                                    <p align="right">
                                        <fmt:message key="Lable_EtisalatGifts_GiftAmount"/>
                                        <input readonly  id="GiftAmount" style="background-color: #EDEDED; float: left;" name="GiftAmount" value=" <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value=" ${inquiryRepresentation.voucherAmount}" />"   style="float: left;"  />
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <div align="center">
                                        <input type="submit"  value="<fmt:message key="redeem" />"   style="float: right;" class="btn">
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