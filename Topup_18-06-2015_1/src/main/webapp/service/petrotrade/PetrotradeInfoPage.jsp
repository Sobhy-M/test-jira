<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

<%@page import="com.masary.integration.dto.BillsDetailsRepresentation"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    MasaryManager.logger.info("PetroTrade Info");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();

    BillsDetailsRepresentation inquiryCommessionResponse = (BillsDetailsRepresentation) session.getAttribute("inquiryCommessionResponse");

%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

        <title><%=CONFIG.getPetrotradeInquiryInfo(request.getSession())%></title>
        <script>
            function changeselection(id) {

                if (id === "CustomerPaymentWayAll") {
                    document.getElementById("PaidWay").value = 'all';
                    document.getElementById("PaidAmount").value = document.getElementById("custAmountID").value;
                    document.getElementById("CustomerPaymentWayParttext").disabled = true;

                } else {
//                    document.getElementById("CustomerPaymentWayParttext").prop('required', true);
                    document.getElementById("PaidWay").value = 'part';
                    document.getElementById("CustomerPaymentWayParttext").disabled = false;
                    var a = document.getElementById("CustomerPaymentWayParttext").value;
                    document.getElementById("PaidAmount").value = a;
                }

            }
            function validateAmount() {
                var paidAmount = document.getElementById("CustomerPaymentWayParttext");
                var partialPayment = document.getElementById("CustomerPaymentWayPart").checked;
                if (partialPayment === true) {
                    if (parseFloat(paidAmount.value) <= 0) {
                        document.getElementById("amountMessage").innerHTML = 'القيمة المراد دفعها يجب ان تكون اعلى من 0 وليست بالسالب';
                        return false;
                    } else {
                        return true;
                    }
                }

            }
        </script>
        <style type="text/css">
            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }
        </style>
    </head>
    <body class="body">
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
        <div class="content_body">
            <form name="petrotradeInfo" action="PetroTradeConfirmation" method="POST">
                <input type="hidden" name="PaidWay" id="PaidWay" />
                <input type="hidden" name="PaidAmount" id="PaidAmount" />
                <input type="hidden" value="<%=request.getParameter("MemberNumber")%>" name="MemberNumber"/>
                <table style="width: 40%">
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.getPetrotradeInquiryInfo(request.getSession())%>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getMemberName(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="CUSTOMER_NAME" type="text" value="<%= inquiryCommessionResponse.getCustomerName()%>" name="CUSTOMER_NAME" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%= CONFIG.getNumberOfBills(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="BillsNum" type="text" value="<%= inquiryCommessionResponse.getBillsNumber()%>"  name="BillsNum" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td><p align="right"><%=CONFIG.getTotalBillsAmount(session)%> : </p></td>
                        <td>  <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" readonly style="background-color: #EDEDED;" value="<%= inquiryCommessionResponse.getTotalBillsAmount()%>" >
                    </tr>



                    <tr><td colspan="2"> <p align="right"><input type="radio" required   name="radio"  id="CustomerPaymentWayAll" onchange="changeselection(this.id)"><%=CONFIG.getPetrotradeTotalAmountWay(request.getSession())%> </td>  </tr>
                    <tr> <td colspan="2">  <p align="right"><input type="radio" required  name="radio"  onchange="changeselection(this.id)" id="CustomerPaymentWayPart"><%=CONFIG.getPetrotradePartAmountWay(session)%> 
                                <input id="CustomerPaymentWayParttext" required type="number" onchange="changeselection('CustomerPaymentWayPart')" name="CustomerPaymentWayParttext"  style="width: 105px;" >
                            </p>
                            <div id="amountMessage" style="color: red;font-size: 15px;" />
                        </td> 
                    </tr>

                    <tr>
                        <td style="text-align: right">
                            <input type="submit" name="btnSubmit"  value="<%=CONFIG.getConfirm(session)%>" class="Btn" onclick="return validateAmount();">
                        </td>
                        <td style="text-align: left">
                            <input type="button" name="btnBack"  value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1)">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            1-في حاله اختيار دفع كلي سيتم دفع جميع الفواتير المستحقه</br>
                            2-فى حالة اختيار (القيمة المراد دفعها) سيتم تفعيل الخانه المقابلة لاقتراح قيمة مراد دفعها ليتم ارشادك بعد ذلك باقرب قيم صحيحه يمكن دفعها
                        </td>
                    </tr>
                </table>

            </form>

        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
