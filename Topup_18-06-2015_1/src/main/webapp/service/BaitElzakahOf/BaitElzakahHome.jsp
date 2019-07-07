<%-- 
    Document   : donation
    Created on : Sep 9, 2015, 12:41:15 PM
    Author     : Pora
--%>

<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
<%@page import="com.masary.database.dto.DonationRespponseDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    session = request.getSession();
//    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
//    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(String.valueOf(811), agent.getPin());
//    Bill_Response bill_response = (Bill_Response) session.getAttribute("bill_Response");
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <script src="./js/MostafaQabasJS/mostafaQabas.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="./js/AddNewWalletJS/notify.js"></script>   
        <title><%=CONFIG.getdonationName(session)%></title>
        <script>
            function onValueChanged() {
                {
                    $.ajax({
                        url: 'ElbakyatElsalhatAJaxRequest',
                        data: {
                            SERVICE_ID: <%=request.getSession().getAttribute("SERVICE_ID").toString()%>,
                            CUSTOMER_ID: <%=agent.getPin()%>,
                            AMOUNT: $('#Amount').val()
                        },
                        type: 'get'
                    }).done(function (responseData) {
                        var arr = responseData.split('-');
                        $('#Fees').val(arr[1]);
                        $('#Commession').val(arr[0]);
                        $('#DeductedAmount').val(Number($('#Amount').val()) - (Number($('#Amount').val()) * 0.5 / 100));
                        $('#Balance_Diff').val(Number($('#Amount').val()) + Number(arr[1]));

                    }).fail(function () {
                        console.log('Failed');
                    });
                }


            }
            function validateAmount() {
                var regexp = /^\d+\.\d{0,2}$/;
                var amount = document.getElementById("Amount").value;
                
                if(regexp.test(amount)){
                    document.getElementById("validation").innerHTML = 'ادخل القيمة بدون كسور';
                    return false;
                }
                else {
                    return true;
                }
            }

        </script>
    </script>
</head>

<body class="body" >
    <div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include><%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

        <form name="WasletKhierHome" action="BaitElzakahConfirmationController" method="POST" >
        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">
                <input type="hidden" id="OperationID" name="OperationID" value="<%=request.getParameter("OPERATION_ID")%>" />
                <legend align="right" ><font size="5"><%= CONFIG.getCustomerdonationHead(session)%>&nbsp;</font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td>
                            <p align="right"><%= CONFIG.getWKPhoneNumber(session)%>:<input name="PhoneNumber"  autocomplete="off" type="text" id="PhoneNumber" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left"></p>
                            <p align="right"><%= CONFIG.getWKAmount(session)%>:<input name="Amount" required  autocomplete="off" type="number"  
                                                                <% if(request.getParameter("OPERATION_ID").toString().equals("97")){%> min="13"   oninvalid="this.setCustomValidity('برجاء العلم ان اقل مبلغ للتبرع هو 13 جنيه')" <%}%>                       
                                                                     id="Amount" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left" oninput="onValueChanged();setCustomValidity('');"></p>
                            <div id="validation" style="color: red;"/>
                        </td>
                        <td>
                            <p align="right"><%= CONFIG.getFees(session)%>:<input name="Fees" readonly autocomplete="off" type="text" id="Fees" maxlength="20" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>                                                                         
                            <p align="right"><%= CONFIG.getCommessionMaan(session)%>:<input name="Commession" readonly autocomplete="off" type="text" id="Commession" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>                                                                                      
                            <p align="right"><%= CONFIG.getTotalAmountMaan(session)%>:<input name="Balance_Diff" readonly  autocomplete="off" type="text" id="Balance_Diff" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>
                            <p align="right"><%=CONFIG.getDeductedAmountMaan(session)%> :<input type="text" readonly name="DeductedAmount" value=""   tabindex="6" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getConfirm(session)%>" onclick="return BtnSubmit()" class="Btn" >
                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary>

                    </summary>
                    <p>1-<%= CONFIG.getWKFirstInstruction(session)%></p>
                </details>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
