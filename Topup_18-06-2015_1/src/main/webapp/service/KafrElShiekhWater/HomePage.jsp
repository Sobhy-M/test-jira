<%-- 
    Document   : HomePage
    Created on : Apr 4, 2019, 12:03:08 PM
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
            <title><fmt:message key="Title_KafrElShiekhWater_Home"/></title>
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
            </style>
            <script>
                function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }
                $(document).ready(function () {
                    $("#form").submit(function () {
                        var mobileNumber = document.getElementById("mobileNumber");
                        var subscriptionNumber = document.getElementById("subscriptionNumber");
                        if (!(mobileNumber.value.toString().startsWith("010") || mobileNumber.value.toString().startsWith("011") || mobileNumber.value.toString().startsWith("012") || mobileNumber.toString().startsWith("015"))) {
                            document.getElementById("validationDiv").innerHTML = "رقم الموبايل يجب ان يبدا ب010 ,011 ,012, 015";
                            return false;
                        } else if (mobileNumber.value.toString() === '') {
                            mobileNumber.setCustomValidity('يرجى ادخال رقم الموبايل');
                            return false;
                        } else if (subscriptionNumber.value.toString() === '') {
                            subscriptionNumber.setCustomValidity('يرجى ادخال رقم الاشتراك');
                            return false;
                        } else if (mobileNumber.value.toString().length !== 11) {
                            document.getElementById("validationDiv").innerHTML = "رقم الموبايل يجب ان يكون 11 رقم";
                            return false;
                        } else if (subscriptionNumber.value.toString().length < 1 || subscriptionNumber.value.toString().length > 14) {
                            document.getElementById("validationDiv").innerHTML = "رقم الإشتراك يجب ان يكون اعلى من 1 واقل من 14";
                            return false;
                        } else {
                            document.getElementById("validationDiv").innerHTML = "";
                            return true;
                        }

                    });
                });

                function onVaildMobileNumber() {
                    var mobileNumber = document.getElementById("mobileNumber");
                    if (mobileNumber.value.toString() === '') {
                        mobileNumber.setCustomValidity('يرجى ادخال رقم الموبايل');
                    } else {
                        mobileNumber.setCustomValidity('');
                    }
                }
                function onVaildSubscriptionNumber() {
                    var subscriptionNumber = document.getElementById("subscriptionNumber");
                    if (subscriptionNumber.value.toString() === '') {
                        subscriptionNumber.setCustomValidity('يرجى ادخال رقم الاشتراك');
                    } else {
                        subscriptionNumber.setCustomValidity('');
                    }
                }
            </script>

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
                <form  id="form" name="doinquiry" action="KafrElShiekhWaterInfo" method="POST" >
                    <fieldset style="width: 30%; direction: rtl;" align="right">  
                        <legend align="center" ><font size="5"><fmt:message key="Title_KafrElShiekhWater_Payment"/></font></legend>
                        <table border="1" width="100%">
                            <tr>
                                <td colspan="2">
                                    <p align="right"><fmt:message key="Lable_KafrElShiekhWater_mobileNumber"/>:
                                        <input type="number" id="mobileNumber" name="mobileNumber" oninvalid="onVaildMobileNumber();" maxlength="11" style="float: left;" required />
                                    </p>
                                    <p align="right"><fmt:message key="Lable_KafrElShiekhWater_subscriptionNumber"/>:
                                        <input type="number" id="subscriptionNumber" name="subscriptionNumber" oninvalid="onVaildSubscriptionNumber();" style="float: left;" required />
                                    </p>
                                    <div id="validationDiv" style="color: red; font-size: 12.5px;font-size: medium"></div>
                                </td>
                            <tr>
                                <td colspan="2">
                                    <div align="center">
                                        <input style="float: right" type="submit" onclick="return validateForm();" value="<fmt:message key="inquire"/>" class="Btn">
                                        <input style="float: left" type="button"  value="<fmt:message key="cancel"/>"  onclick="cancel();" class="btn">
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </fieldset> 
                </form>
            </div><!-- End of Table Content area-->
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

        </body>
    </html>
</fmt:bundle>
