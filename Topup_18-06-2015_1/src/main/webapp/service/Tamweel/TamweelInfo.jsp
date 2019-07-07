<%-- 
    Document   : TamweelIfo
    Created on : Feb 20, 2019, 12:42:47 PM
    Author     : omar.abdellah
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.masary.common.CONFIG"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%request.setCharacterEncoding("UTF-8");%>
<fmt:bundle basename="Bundle">


    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="Title_Tamweel_Info"/></title>

            <script>
                     function InvalidMsg(textbox) {
    if (textbox.value == '') {
        textbox.setCustomValidity('يرجي إدخال المبلغ الجزئي');
    }
    
    else {
       textbox.setCustomValidity('');
    }
    return true;
}    
function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }

                function onCheckboxClicked() {
                    var checkbox = document.getElementById("PartialpaymenCheckbox");
                    var paidAmount = document.getElementById("partialAmount");

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
                    var checkbox = document.getElementById("PartialpaymenCheckbox");
                    var partialAmount = document.getElementById("partialAmount").value;
                    var lateCharge = document.getElementById("LateCharge").value;
                    var TotalAmount = document.getElementById("TotalAmount").value;
                    var totalAmountAndLate = parseFloat(TotalAmount) + parseFloat(lateCharge)
                    document.getElementById("paymentType").value = 'toltallpayment';
                    if (checkbox.checked === true) {
                        document.getElementById("paymentType").value = 'Partialpayment';
                        if (parseFloat(partialAmount) < parseFloat(lateCharge) || parseFloat(partialAmount) <= 0) {

                            document.getElementById("amountMessage").innerHTML = "<fmt:message key="MESSAGE_Tamweel_PartialAmountless"></fmt:message>";
                            return false;
                        } else if (parseFloat(partialAmount) > parseFloat(totalAmountAndLate)) {
                            document.getElementById("amountMessage").innerHTML = "<fmt:message key="MESSAGE_Tamweel_PartialAmountlessMoreThanLateChargeAndAmount"></fmt:message>";
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                $('#dobillinquiry').validate({
                    rules: {
                        requestType: {
                            required: true
                        }
                    }
                });
                </script>


                <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


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
            <form action="TamweeControllerlConfirmation" method="POST" >

                <input type="hidden" name="validationId" value="${tamweelInquiryRepresentation.validationId}" />
                <fieldset style="width:50%; direction: rtl;" align="right">  
                    <legend align="center" ><font size="5"><fmt:message key="Title_Tamweel_Print"/>${tamweelInquiryRepresentation.installmentType}</font></legend>               
                    <table  border="1" width="100%">
                        <th><fmt:message key="Lable_Tamweel_code"/></th>
                        <tr>
                            <td width="10%">
                                <p align="right"><fmt:message key="TamweelCustomerNameAr"/> : 
                                    <input type="text" readonly tabindex="1" value="${tamweelInquiryRepresentation.customerName}" name="customerName" id="customerName" style="background-color: #EDEDED; float: left;"/>

                                </p>
                                <p align="right"><fmt:message key="TamweelInstallmentTypeAr"/> :
                                    <input type="text" readonly tabindex="1" value="${tamweelInquiryRepresentation.installmentType}" name="InstallmentType"  id="InstallmentType" style="background-color: #EDEDED; float: left;"/>
                                </p>
                                <p align="right"><fmt:message key="Lable_Tamweel_code"/> :
                                    <input id="CodeNumber" name="CodeNumber" id="CodeNumber" type="text" readonly tabindex="1" value="${tamweelInquiryRepresentation.customerCode}" style="background-color: #EDEDED; float: left;"/>
                                </p>
                                <p align="right"><fmt:message key="TamweelInstallmentDateAR"/> :
                                    <input id="InstallmentDate" name="InstallmentDate" type="text" readonly tabindex="1" value="${tamweelInquiryRepresentation.installmentDate}" style="background-color: #EDEDED; float: left;"/>
                                </p>

                                <p align="right"><fmt:message key="TamweelAmountAR"/>   :
                                    <input id="TotalAmount" name="TotalAmount" type="text" readonly tabindex="1" value="${tamweelInquiryRepresentation.installmentAmount}" style="background-color: #EDEDED; float: left;"/>
                                </p>

                                <p align="right"><fmt:message key="TamweelLateChargeAr"/> :
                                    <input id="LateCharge" name="LateCharge" type="text" readonly tabindex="1" value="${tamweelInquiryRepresentation.lateCharge}" style="background-color: #EDEDED; float: left;"/>
                                </p>

                                <p align="right">
                                    <input id="checkbox" type="radio" name="checkbox" checked onclick="onCheckboxClicked();"/>

                                    <fmt:message key="TamweelTotalAmountAr"/> :

                                </p>

                                <p align="right">
                                    <input id="PartialpaymenCheckbox" type="radio" name="checkbox" required    onclick="onCheckboxClicked();"/>
                                    <fmt:message key="TamweelPartialpaymentAR"/> :
                                    <input  name="partialAmount" id="partialAmount" type="text" disabled oninvalid="InvalidMsg(this);" oninput="InvalidMsg(this);"  tabindex="1"  style=" float: left;" />
                                    <input type="hidden" name="paymentType"  id="paymentType" />
                                </p>

                                <div id="amountMessage" style="color: red;font-size: 15px;" />


                        </tr>
                        <input type="hidden"   value="${tamweelInquiryRepresentation.merchantCommission}" name="merchantCommission" id="merchantCommission" />
                        <input type="hidden"   value="${tamweelInquiryRepresentation.appliedFees}" name="appliedFees" id="appliedFees" />
                        <input type="hidden"   value="${tamweelInquiryRepresentation.toBepaid}" name="toBepaid" id="toBepaid" />
                        <input type="hidden"   value="${tamweelInquiryRepresentation.transactionAmount}" name="transactionAmount" id="transactionAmount" />

                        <input type="hidden"   value="${tamweelInquiryRepresentation.serviceAmount}" name="serviceAmount" id="serviceAmount" />
                        <tr>
                            <td style="text-align: right">
                                <div align="center">
                                    <input type="submit" name="btnSubmit" tabindex="1"  value="<fmt:message key="confirm"/>"   style="float: right;" class="btn" onclick="return validatePaidAmount();"/>
                                    <input type="button" name="Back" tabindex="4" value="<fmt:message key="back"/>"  onclick="history.go(-1);"  style="float: left;" class="btn"/>
                                    <input type="button" name="Cancel" tabindex="6"  value="<fmt:message key="cancel"/>"  onclick="cancel();" class="btn"/>
                                </div>
                        </tr>
                    </table>

                </fieldset> 
            </form>
        </div>


        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </body>

    </html>
</fmt:bundle>