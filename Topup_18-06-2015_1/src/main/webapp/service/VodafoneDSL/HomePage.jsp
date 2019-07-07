<%-- 
    Document   : HomePage
    Created on : Dec 31, 2018, 11:10:35 AM
    Author     : Ahmed Khaled
--%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.controllers.VodafoneDSL.VodafoneDSLProperties"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=VodafoneDSLProperties.Title_VodafoneDsl_Home%></title>
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
        <style>

            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }

            p {
                font-size: medium;
                font-weight: bold;
            }
        </style>
        <script>
            $(window).bind("pageshow", function () {
                resetInputs();
            });
            function resetInputs() {
                var amountDiv = document.getElementById("amountDiv");
                amountDiv.innerHTML = '';
                document.getElementById("topupAmount").value = '';
                document.getElementById("landline").value = '';
                document.getElementById("landlineConfirmation").value = '';
            }
            function resetValues() {
                var amountDiv = document.getElementById("amountDiv");
                amountDiv.innerHTML = '';
                document.getElementById("validationDiv").innerHTML = '';
                document.getElementById("submitbutton").disabled = false;

            }
            $(document).ready(function () {
                $("#form").submit(function () {
                    var phone = document.getElementById("landline").value;
                    var phoneConfirmation = document.getElementById("landlineConfirmation").value;
                    var validformat = /^[^a-zA-Z]+$/;
                    if (!validformat.test(phone)) {
                        document.getElementById("validationDiv").innerHTML = 'برجاء التأكد من كتابة الرقم الارضى بشكل صحيح';
                        return false;
                    } else if (phone.length < 10) {
                        document.getElementById("validationDiv").innerHTML = 'برجاء التأكد من الرقم الارضى حيث يتكون من 10 او 11 رقم';
                        return false;
                    } else if (phone !== phoneConfirmation) {
                        document.getElementById("validationDiv").innerHTML = 'برجاء التأكد من تطابق الرقم الأرضى';
                        return false;
                    } else if (phone.toString().startsWith("010") || phone.toString().startsWith("011") || phone.toString().startsWith("012") || phone.toString().startsWith("015")) {
                        document.getElementById("validationDiv").innerHTML = 'الرقم الارضى يبدا بكود المحافظة وليس رقم المحمول';
                        return false;
                    } else {
                        return true;
                    }
                });
            });
            $(document).ready(function () {
                $("#cancelbutton").click(function () {
                    document.getElementById("HOMEFORM").submit();
                });
            });
            $(document).ready(function () {
                $("#topupAmount").change(function () {
                    resetValues();
                    var amount = document.getElementById("topupAmount").value;
                    $.ajax({
                        url: 'VodafoneTopupDslAjaxRequest',
                        data: {
                            amount: amount
                        },
                        type: 'get'
                    }).done(function (responseData) {
                        if (responseData === '') {
                            document.getElementById("validationDiv").innerHTML = 'لا يوجد فئة شحن بهذه القيمة';
                            document.getElementById("submitbutton").disabled = true;
                        } else {
                            var arr = responseData.split(",");
                            document.getElementById("denominationName").value = arr[1];
                            callRatePlan(1010, arr[0], amount);
                            resetValues();
                        }
                    }).fail(function () {
                        console.log('Failed to get denomantions types');
                    });
                });
            });

            function callRatePlan(id, denomenationId, amount) {
                document.getElementById("denominationId").value = denomenationId;
                $.ajax({
                    url: 'RatePlanCommissions',
                    data: {
                        SERVICE_ID: id,
                        AMOUNT: amount
                    },
                    type: 'get'
                }).done(function (responseData) {
                    if (responseData !== "") {
                        var arr = responseData.split('-');
                        $('#Fees').val(arr[6]);
                        $('#Commession').val(arr[3]);
                        $('#servicetax').val(arr[7]);
                        $('#DeductedAmount').val(arr[8]);
                        $('#UserAmount').val(arr[11]);
                    } else {
                        return;
                    }
                }).fail(function () {
                    return;
                });
            }
        </script>
    </head>
    <body class="body" onload="resetValues()">
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
        <form id="HOMEFORM" action=<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%> method="POST" style="display: none;" ></form>
        <form id="form" action="VodafonetopupDslConfirmation" method="POST">
            <input type="hidden" name="denominationId" id="denominationId" />
            <input type="hidden" name="denominationName" id="denominationName" />
            <input type="hidden" name="Fees" id="Fees" />
            <input type="hidden" name="Commession" id="Commession" />
            <input type="hidden" name="servicetax" id="servicetax" />
            <input type="hidden" name="DeductedAmount" id="DeductedAmount" />
            <input type="hidden" name="UserAmount" id="UserAmount" />
            <fieldset style="width: 40%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=VodafoneDSLProperties.Title_VodafoneDsl_Home%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
                <table border="1" style="width: 100%">
                    <th><%=VodafoneDSLProperties.Lable_VodafoneDsl_RequiredInfo%></th>
                    <tr>
                        <td>
                            <p align="right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_Amount%> :
                                <input type="number" id="topupAmount"  name="topupAmount"  required  style="float: left;" />
                            </p>
                            <div id="amountDiv">
                            </div>
                            <p align="right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_Landline%> : 
                                <input id="landline" type="text" name="landline" required maxlength="10" style="float: left;">
                            </p>
                            <p align="right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_LandlineConfirmation%> :
                                <input id="landlineConfirmation" type="text"  required maxlength="10" style="float: left;">
                            </p>
                            <div id="validationDiv" style="color: red;font-weight: bold;font-size: medium"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" id="submitbutton" value="<%=VodafoneDSLProperties.Button_VodafoneDsl_Execute%>" class="Btn">
                            <input type="button" name="btnCancel" id="cancelbutton" value="<%=VodafoneDSLProperties.Button_VodafoneDsl_Cancel%>" class="Btn" style="float: left">
                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	من فضلك ادخل المبلغ المراد شحنه ورقم الهاتف الارضى</br>            

                    ملاحظات :- </br>
                    -المبالغ التى يمكن شحنها فى شحن فودافون ADSL يجب ان تكون مبالغ صحيحة بدون كسور وهى: من 5 جنيه الى 500 جنيه  
                    </br>
                    - يجب ادخل رقم التليفون الارضى يبدا بكود المحافظة مثل 02 ف القاهرة ثم تنفيذ


                </details>
            </fieldset> 
        </form>

    </div><!-- End content body -->



    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>