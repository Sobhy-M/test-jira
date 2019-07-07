<%-- 
    Document   : ZaidElKhierHomePage
    Created on : Jun 26, 2018, 30:30:00 PM
    Author     : Ahmed Khaled
--%>


<%@page import="com.masary.controllers.ZaidElKhier.ZaidElKhierProperties"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<link href="img/style${lang}.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="./js/AbuElreshJS/abuElResh.js"></script>
        <script src="img/jquery.typewatch.js"></script>
        <title><%=ZaidElKhierProperties.Param_ZaidElKhier_MainMenu%></title>
        <style>
            #notice{
                background: transparent;
                border-top: transparent !important;
                border-left: transparent !important;
                border-right: transparent !important;
                border-bottom: transparent !important;
            }
            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }
            p{
                font-size: medium;
                font-weight: bold;
            }
        </style>
        <script>

            function cancel() {
                document.getElementById("HOMEFORM").submit();
            }

            $(document).ready(function () {
                $("#subCategory").change(function () {
                    document.getElementById("subServiceId").value = $(this).children(":selected").attr("id");
                    ;
                });
            });
            function resetValues() {
                var amountAttribute = document.getElementById("amount");
                amountAttribute.value = '';
                amountAttribute.readonly = false;
                amountAttribute.style.backgroundColor = "white";
                $('input[id=notice]').val('حد ادنى للتبرع 3 جنيه');
            }

            $(document).ready(function () {
                $("#category").change(function () {
                    var val = $(this).children(":selected").attr("id");
                    resetValues();
                    $.ajax({
                        url: 'ZaidElKhierAjax',
                        data: {
                            VALUE: val
                        },
                        type: 'get'
                    }).done(function (responseData) {
                        var arr = responseData.split('-');
                        document.getElementById("subCategory").options.length = 0;
                        document.getElementById("serviceId").value = $("#category").children(":selected").attr("id");
                        $("#subCategory").append('<option selected disabled><%=ZaidElKhierProperties.Param_ZaidElKhier_Select%></option>');
                        for (i = 0; i < arr.length; i++) {
                            var arr2 = arr[i].split(",");
                            if (arr.length === 1) {
                                document.getElementById("subServiceId").value = arr2[1];
                                document.getElementById("subCategoryDiv").style.display = "none";
                            } else {
                                document.getElementById("subCategoryDiv").style.display = "block";
                                $("#subCategory").append('<option  id="' + arr2[1] + '" ' + 'value="' + arr2[0] + '">' + arr2[0] + '</option>');
                            }

                        }

                    }).fail(function () {
                        console.log('Failed to update selected category type');
                    });
                });
            });
            $(document).ready(function () {
                $("#subCategory").change(function () {
                    var subCategoryId = $("#subCategory").children(":selected").attr("id");
                    var subCategory = $("#subCategory").children(":selected").attr("value");
                    var amount;
                    var amountAttribute = document.getElementById("amount");
                    if (subCategoryId === "5986" || subCategoryId === "5987") {
                        var arr = subCategory.split(" ");
                        amount = arr[1];
                        amountAttribute.value = arr[1];
                        amountAttribute.readonly = true;
                        amountAttribute.style.backgroundColor = "#EDEDED";
                        $('input[id=notice]').val('');
                    } else if (subCategoryId === "5988") {
                        var arr = subCategory.split(" ");
                        amount = arr[2];
                        amountAttribute.value = arr[2];
                        amountAttribute.readonly = true;
                        amountAttribute.style.backgroundColor = "#EDEDED";
                        $('input[id=notice]').val('');
                    } else {
                        resetValues();
                        amount = amountAttribute.value;
                    }

                    getRatePlan(document.getElementById("subServiceId").value, amount);
                });
            });
            $(document).ready(function () {
                $("#amount").typeWatch({
                    wait: 300,
                    captureLength: 1,
                    callback: function (value) {
                        var subCategory = $("#subCategory").children(":selected").attr("id");
                        var amount = parseFloat($(this).val());
                        if (subCategory === "5988" && amount < parseFloat(120))
                        {
                            alert("حد ادنى للتبرع 120 جنيه");
                            return false;
                        } else if (amount < parseFloat(3)) {
                            $('input[id=notice]').val('حد ادنى للتبرع 3 جنيه');
                            alert("حد ادنى للتبرع 3 جنيه");
                            return false;
                        } else {
                            getRatePlan(document.getElementById("subServiceId").value, $(this).val());
                        }
                    }
                });
            });
            function getRatePlan(serviceId, amount) {
                $.ajax({
                    url: 'RatePlanCommissions',
                    data: {
                        SERVICE_ID: serviceId,
                        AMOUNT: amount

                    },
                    type: 'get'
                }).done(function (responseData) {
                    if (responseData === '')
                    {
                        ajaxReturn = false;
                        return;
                    } else
                    {
                        ajaxReturn = true;
                    }

                    var arr = responseData.split('-');
                    $('#Fees').val(arr[6]);
                    $('#Commession').val(arr[3]);
                    $('#DeductedAmount').val(arr[8]);
                    $('#ToBePaid').val(arr[11]);
                }).fail(function () {
                    ajaxReturn = false;
                    console.log('Failed');
                });
            }

            $(document).ready(function () {
                $("#form").submit(function () {
                    if ($("#subServiceId").val() === "" || $("#subServiceId").val() === null) {
                        alert("برجاء التاكد من اختيار نوع التبرع");
                        return false;
                    } else if ($("#PhoneNumber").val().length !== parseInt(11)) {
                        alert("ادخل رقم الهاتف صحيح");
                        return false;
                    } else {
                        return true;
                    }
                });
            });
            function validateAmount() {
                var amount = document.getElementById("amount").value;
                var subCategory = $("#subCategory").children(":selected").attr("id");
                if (amount < parseFloat(3)) {
                    return false;
                } else {
                    if (subCategory === "5988" && amount < parseFloat(120)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            function validateMobileNumber() {
                if (validatePhoneNo()) {
                    return true;
                } else {
                    alert('رقم الموبيل يجب ان يبدا ب01');
                    return false;
                }
            }
        </script>
    </head>
    <body class="body">
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
        <form id="HOMEFORM" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" style="display: none;" ></form>
        <form id="form" action="ZaidElKhierPayment" method="POST" onsubmit="return validateAmount()">
            <input type="hidden" name="serviceId" id="serviceId" />
            <input type="hidden" name="subServiceId" id="subServiceId" />
            <input type="hidden" name="action" value="confirm" />
            <div class="content_body"  >
                <fieldset style="width: 70%; direction: rtl;" align="right">
                    <legend align="right" ><font size="5"></font><img src="img/zaidElKhier.jpg"  width="60" height="60" >&nbsp;<img src="img/CashIn.ico"  width="20" height="20" ></legend>               
                    <table  border="1" width="100%">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=ZaidElKhierProperties.Param_ZaidElKhier_Commission%></th>
                        <tr>
                            <td>
                                <p align="right" > <%=ZaidElKhierProperties.Param_ZaidElKhier_MainMenu%> :
                                    <select id="category">
                                        <option value="0" selected disabled><%=ZaidElKhierProperties.Param_ZaidElKhier_Select%></option>
                                        <c:forEach var="menu" items="${inquiryResponse}">
                                            <option id="${menu.categoryId}" value="${menu.categoryName}">${menu.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </p>
                                <div id="subCategoryDiv">
                                    <p align="right" ><%=ZaidElKhierProperties.Param_ZaidElKhier_Type%> :
                                        <select id="subCategory" required >
                                        </select>
                                    </p>
                                </div>

                                <p align="right" ><%=ZaidElKhierProperties.Param_ZaidElKhier_Msisdn%> :
                                    <input type="text" id="PhoneNumber" name="msisdn" required maxlength="11">
                                </p>
                                <p align="right" ><%=ZaidElKhierProperties.Param_ZaidElKhier_Amount%> :
                                    <input type="number" id="amount" name="amount" required >
                                </p>
                                <p align="right" ><%=ZaidElKhierProperties.Param_ZaidElKhier_Notice%> :
                                    <input id="notice" value="الحد الادنى للتبرع 3 جنيهات" readonly style="color: red;font-size: medium;">
                                </p>
                            </td>
                            <td>
                                <p align="right"><%=CONFIG.getFees(session)%> :
                                    <input type="text" name="Fees"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;" class="commissionPanel"/>
                                </p> 
                                <p align="right"><%=CONFIG.getCommession(session)%> :
                                    <input type="text" name="Commession"   readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;" class="commissionPanel"/>
                                </p> 
                                <p align="right"><%=CONFIG.getDeductedAmount(session)%> :
                                    <input type="text" name="DeductedAmount"   readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;" class="commissionPanel"/>
                                </p>
                                <p align="right"><%=CONFIG.getTotalAmountMaan(session)%>:
                                    <input  name="ToBePaid"  autocomplete="off" readonly type="text" id="ToBePaid" maxlength="11" style="float: left;background-color: #EDEDED;" class="commissionPanel">
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit"  tabindex="4"  value="<%=ZaidElKhierProperties.Param_ZaidElKhier_Execute%>" class="Btn" onclick="return validateMobileNumber();">
                            </td>
                            <td>
                                <input type="button"  tabindex="4"  value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="cancel()" style="float: left;">
                            </td>
                        </tr>
                    </table>
                    <details open="open">
                        <summary>

                        </summary>
                    </details>
                </fieldset> 
            </div><!-- End of Table Content area-->
        </form>

        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>

