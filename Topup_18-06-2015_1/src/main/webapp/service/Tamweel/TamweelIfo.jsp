<%-- 
    Document   : TamweelIfo
    Created on : Feb 20, 2019, 12:42:47 PM
    Author     : omar.abdellah
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }

    session = request.getSession();

%>
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.OrangeCorporateBills_SERVICE_NAME%></title>

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
                var billAmount = document.getElementById("totalOpenAmount").value;

                if (checkbox.checked === true) {
                    if (parseFloat(paidAmount) > parseFloat(billAmount) ) {
                        document.getElementById("amountMessage").innerHTML = "<%= CONFIG.MESSAGE_OrangeCorporateBills_PaidAmountOutOfTotalAmountRangeAr%>";
                        return false;
                    } else if (parseFloat(paidAmount) <= 0) {
                        document.getElementById("amountMessage").innerHTML = "<%= CONFIG.MESSAGE_OrangeCorporateBills_InvalidPaidAmountAr%>";
                        return false;
                    } else {
                        return true;
                    }
                }
            }

        </script>


        <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


</head>
<body class="body" >
    <div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <form id="HOMEFORM" action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="display: none;" ></form>
    <form name="dobillinquiry" action="OrangeCorporateBillsConfirmationController" method="POST" >
        <div class="content_body"  >
            <fieldset style="width:50%; direction: rtl;" align="right">  
                <legend align="center" ><font size="5"><%= CONFIG.Tamweel%></font></legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getTamweelDataInfo(session)%></th>
                    <tr>
                        <td width="10%">
                            <p align="right"><%=CONFIG.getTamweelCustomerName(session)%> : 
                                <input type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.accountNumber}" name="<%=CONFIG.PARAM_MSISDN%>" style="background-color: #EDEDED; float: left;">
                            </p>
                            <p align="right"><%=CONFIG.getTamweelInstallmentType(session)%> :.
                                <input type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.accountNumber}" name="InstallmentType" style="background-color: #EDEDED; float: left;">
                            </p>
                            <p align="right"><%=CONFIG.getTamweelCodeNumber(session)%> :
                                <input id="CodeNumber" name="CodeNumber" type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.totalOpenAmount}" style="background-color: #EDEDED; float: left;">
                            </p>
                             <p align="right"><%=CONFIG.getTamweelInstallmentDate(session)%> :
                                <input id="InstallmentDate" name="InstallmentDate" type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.totalOpenAmount}" style="background-color: #EDEDED; float: left;">
                            </p>
                            
                            <p align="right"><%=CONFIG.TamweelAmountAR%> :
                                <input id="Amount" name="Amount" type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.totalOpenAmount}" style="background-color: #EDEDED; float: left;">
                            </p>
                            
                              <p align="right"><%=CONFIG.getTamweelLateCharge(session)%> :
                                <input id="LateCharge" name="LateCharge" type="text" readonly tabindex="1" value="${orangeCorporateInquiryResponse.totalOpenAmount}" style="background-color: #EDEDED; float: left;">
                            </p>
                             <p align="right">
                                <input id="checkbox" type="checkbox" name="checkbox" onclick="onCheckboxClicked();" >
                                <%=CONFIG.getTamweelTotalAmount(session)%> :
                              
                            </p>
                            
                            <p align="right">
                                <input id="checkbox" type="checkbox" name="checkbox" onclick="onCheckboxClicked();" >
                                <%=CONFIG.OrangeCorporateBills_Partial_Payment%> :
                                <input id="paidAmount" name="partialAmount" type="text" tabindex="1"  style="float: left;">
                            </p>
                            <div id="amountMessage" style="color: red;font-size: 15px;" />
                        </td>

                    </tr>

                    <tr>
                        <td style="text-align: right">
                            <div align="center">
                                <input type="submit" name="btnSubmit" tabindex="1"  value="<%=CONFIG.getpayment(session)%>"   style="float: right;" class="btn" onclick="return validatePaidAmount();">
                                <input type="button" name="Back" tabindex="4" value="<%=CONFIG.getBack(session)%>"   onclick="goBack();" style="float: left;" class="btn">
                                <input type="button" name="Cancel" tabindex="6"  value="<%=CONFIG.YallaPayCancelLabel%>"  onclick="cancel();" class="btn">
                            </div>
                    </tr>
                </table>

            </fieldset> 
        </div><!-- End of Table Content area-->

    </form>

    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</body>
</html>
