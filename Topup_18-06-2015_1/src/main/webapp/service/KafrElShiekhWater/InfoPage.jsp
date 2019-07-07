<%-- 
    Document   : InfoPage
    Created on : Apr 4, 2019, 12:03:26 PM
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
            <title><fmt:message key="Title_KafrElShiekhWater_Info"/></title>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
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
                input[type=text] {
                    width: 50%;
                    background-color: #EDEDED;
                    float: right;
                    font-size: 16px;
                }
            </style>
            <script>
                function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }
                $(document).ready(function () {
                    $("#form").submit(function () {

                    });
                });
                function changeselection(id) {

                    if (id === "fullPaymentRadio") {
                        document.getElementById("PaidAmount").value = document.getElementById("billsTotalAmountEGP").value;
                        document.getElementById("partialAmount").disabled = true;

                    } else {
                        document.getElementById("partialAmount").disabled = false;
                        document.getElementById("PaidAmount").value = document.getElementById("partialAmount").value;
                    }

                }
                $(document).ready(function () {
                    $("#partialAmount").on("keypress keyup blur change", function (event) {
                        $(this).val($(this).val().replace(/[^0-9\.]/g, ''));
                        if (((event.which !== 46 || (event.which === 46 && $(this).val() === '')) ||
                                $(this).val().indexOf('.') !== -1) && (event.which < 48 || event.which > 57)) {
                            event.preventDefault();
                        }
                    });
                });
                function formValidation() {
                    var amount = document.getElementById("partialAmount");
                    var fullPaymentRadio = document.getElementById("fullPaymentRadio").checked;
                    if (fullPaymentRadio === false) {
                        if ((/^[0-9]+$/.test(amount.value))) {
                            return true;

                        } else {
                            document.getElementById("amountMessage").innerHTML = 'خطا فى المبلغ المدخل';
                            return false;
                        }
                    }
                }
            </script>

        </head>
        <body class="body" onload="changeselection('fullPaymentRadio')">

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
                <form  id="form" name="doinquiry" action="KafrElShiekhWaterConfirmation" method="POST" >
                    <input type="hidden" name="PaidAmount" id="PaidAmount" />
                    <input type="hidden" name="validationId" id="validationId" value="${kafrElShiekhWaterInquiryResponse.validationId}"/>
                    <input type="hidden" name="subscriberName" id="subscriberName" value="${kafrElShiekhWaterInquiryResponse.subscriberName}"/>
                    <input type="hidden" name="billsNumber" id="billsNumber" value="${kafrElShiekhWaterInquiryResponse.billsNumber}"/>
                    <input type="hidden" name="appliedFees" id="appliedFees" value="${kafrElShiekhWaterInquiryResponse.appliedFees}"/>
                    <input type="hidden" name="toBepaid" id="toBepaid" value="${kafrElShiekhWaterInquiryResponse.toBepaid}"/>
                    <fieldset style="width: 55%; direction: rtl;" align="right">  
                        <legend align="right" ><font size="5"><fmt:message key="Header_KafrElShiekhWater_Info"/></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>
                        <table border="1" width="100%">
                            <th><fmt:message key="requiredInfo"></fmt:message></th>
                                <tr>
                                    <td colspan="2">
                                        <p align="right"><fmt:message key="Lable_KafrElShiekhWater_subscriberName"/>:
                                        <input type="text" id="subscriberName" name="subscriberName" value="${kafrElShiekhWaterInquiryResponse.subscriberName}" style="float: left;" required readonly />
                                    </p>
                                    <p align="right"><fmt:message key="Lable_KafrElShiekhWater_billsCount"/>:
                                        <input type="text" id="billsCount" name="billsCount" value="${kafrElShiekhWaterInquiryResponse.billsNumber}"  style="float: left;" required readonly/>
                                    </p>
                                    <p align="right"><fmt:message key="Lable_KafrElShiekhWater_billsTotalAmountEGP"/>:
                                        <input type="text" id="billsTotalAmountEGP" name="billsTotalAmountEGP" value="${kafrElShiekhWaterInquiryResponse.serviceAmount}" style="float: left;" required readonly/>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"> 
                                    <p align="right">
                                        <input type="radio" required  name="radio"  id="fullPaymentRadio" onchange="changeselection(this.id)" checked value="full">
                                        <fmt:message key="Lable_KafrElShiekhWater_fullPayment"/>
                                    <p align="right">
                                        <input type="radio" required  name="radio"  onchange="changeselection(this.id)" id="partialPaymentRadio" value="partial">
                                        <fmt:message key="Lable_KafrElShiekhWater_partialPayment"/>
                                        <input id="partialAmount" required type="number" onchange="changeselection('CustomerPaymentWayPart')" name="partialAmount">
                                    </p>
                                    <div id="amountMessage" style="color: red;font-size: 15px;" />
                                </td> 
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div align="center">
                                        <input type="submit"  value="<fmt:message key="confirm" />"   style="float: right;" onclick="return formValidation();" class="btn">
                                        <input type="button"  value="<fmt:message key="cancel"/>" style="float: left;"  onclick="cancel();" class="btn">
                                        <input type="button"  value="<fmt:message key="back"/>"   onclick="history.go(-1);"  class="btn">
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <details open="open" style="font-size: medium">
                            <summary></summary>
                            1- فى حاله اختيار دفع كلي سيتم دفع جميع الفواتير المستحقه.<br>
                            2- فى حاله اختيار (القيمة المراد دفعها) سيتم تفعيل الخانه المقابله لاقتراح قيمه مراد دفعها ليتم ارشادك بعد ذلك باقرب قيم صحيحه يمكن دفعها.
                        </details>
                    </fieldset> 
                </form>
            </div><!-- End of Table Content area-->
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

        </body>
    </html>
</fmt:bundle>
