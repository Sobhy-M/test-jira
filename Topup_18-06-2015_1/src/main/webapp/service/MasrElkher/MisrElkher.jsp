<%-- 
    Document   : donation
    Created on : Sep 9, 2015, 12:41:15 PM
    Author     : KEMO
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
%>
<%
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
//    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), agent.getPin());
    HashMap<String, String> list = MasaryManager.getInstance().getGovernorates("AR", 63);
    session.setAttribute("govlist", list);
    DonationAgentPaymentRespponseDto respponseDto = new DonationAgentPaymentRespponseDto();

    respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");

%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <script type="text/javascript" src="img/jquery-1.11.3.min.js"></script>
        <title><%=CONFIG.getdonationName(session)%></title>
        <script>
            function fixedAmount() {

                $.ajax({
                    url: 'Donations',
                    data: {
                        SERVICE_ID: <%=request.getSession().getAttribute("SERVICE_ID").toString()%>,
                        CUSTOMER_ID: <%=agent.getPin()%>,
                        AMOUNT: $('#custAmountID').val()
                    },
                    type: 'get'
                }).done(function (responseData) {
                    var arr = responseData.split('-');
                    $('#Fees').val(arr[1]);
                    $('#Commession').val(arr[0]);
                    if (arr[0] != null && arr[0] !== "" && arr[1] !== null && arr[1] !== "") {
                        $('#DeductedAmount').val(Number($('#custAmountID').val()) + Number(arr[1] - arr[0]));
                        $('#Balance_Diff').val(Number($('#custAmountID').val()) + Number(arr[1]));
                    } else {
                        $('#DeductedAmount').val("");
                        $('#Balance_Diff').val("");
                    }
                }).fail(function () {
                    console.log('Failed');
                });
            }


        </script>
        <script>

            function ValidateMobileNo() {
                var errChk = 0;
                var regexObj = /^01\d{9}$/i;
                var phoneDiv = document.getElementById("custTopUpPhoneDiv");
                var mobileNo = document.getElementById("BILLING_ACCOUNT");


                if (mobileNo.value.match(regexObj) && mobileNo.value !== null) {
                    phoneDiv.innerHTML = "";
                    phoneDiv.disabled = true;

                } else {
                    errChk = 1;
                    phoneDiv.innerHTML = "<br>رقم الموبايل غير صحيح برجاء ادخال الرقم الصحيح</br>";

                }

                if (errChk === 1) {
                    return false;
                } else {
                    return true;
                }
            }
            function ValidateMobileNo1() {

                var errChk = 0;
                var regexObj = /01\d\d\d\d\d\d\d\d\d/;
                var phoneDiv = document.getElementById("custTopUpPhoneDiv");
                var mobileNo = document.getElementById("BILLING_ACCOUNT");
                if (mobileNo.value.match(regexObj) || mobileNo.value == "") {
                    phoneDiv.innerHTML = "";
                    phoneDiv.disabled = true;
                } else {
                    errChk = 1;
                    phoneDiv.innerHTML = "<br>رقم الموبايل غير صحيح برجاء ادخال الرقم الصحيح او عدم ادخاله</br>";
                }

                if (errChk == 1) {
                    return false;
                } else {
                    return true;
                }
            }


            function ValidateMaxAmount() {
                var amount = document.getElementById("custAmountID");
                var balanceDiv = document.getElementById("custTopUpDalanceDiv");
                var errChk = 0;
                var pattern = /^\+?(0|[1-9]\d*)$/;
                if (amount.value !== "" && amount.value.match(pattern) && Number(amount.value) <= 10000) {
//                    onValueChanged();
                    balanceDiv.innerHTML = "";
                    balanceDiv.disable = true;
                }
                if (amount.value === "" || amount.value === null || Number(amount.value) === 0 || Number(amount.value) < 0) {
//                    alert("invalid amount");
                    errChk = 1;
                    balanceDiv.innerHTML = "برجاء ادخال قيمة التبرع الصحيحة";
                }
                if (Number(amount.value) <= 5 && <%=request.getParameter("OPERATION_ID").toString()%> !== 98) {
                    balanceDiv.innerHTML = "برجاء ادخال قيمة التبرع اكبر من 6 ";
                    errChk = 1;
                }
                if (Number(amount.value) > 10000 && <%=request.getParameter("OPERATION_ID").toString()%> !== 98) {
                    balanceDiv.innerHTML = "برجاء ادخال قيمة التبرع اقل من 10000 ";
                    errChk = 1;
                }
                if (errChk === 1) {
                    return false;
                } else {
                    return true;
                }
            }
            function ValidateForm() {
                if (ValidateMaxAmount() && ValidateMobileNo() && Validatename()) {
                    return true;
                } else {
                    return false;
                }
            }

        </script>

    </head>

    <body class="body" onload="fixedAmount();">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="MisrElKheirController" method="POST" >

        <input type="hidden" name="action" value="<%=CONFIG.ACTION_GET_DONATION_CONFIRMATION%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("SERVICE_ID").toString()%>" />
        <input type="hidden" name="<%=CONFIG.OPERATION_ID%>" value="<%=request.getParameter("OPERATION_ID").toString()%>" />
        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.getCustomerdonationHead(session)%>&nbsp;</font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getprogram(session)%> : <input type="text" name="<%=CONFIG.PROGRAM%>" readonly tabindex="1" id="program" value="<%= respponseDto.getOPERATION_NAME()%>" style="background-color: #EDEDED; float: left;"/></p>
                                <%if (respponseDto.getFIXED_AMOUNT() <= 0.0) {%>
                            <p align="right"><%= CONFIG.getPhoneNumber(session)%> : <input type="tel" name="BILLING_ACCOUNT" required title="برجاء ادخال رقم التليفون" autocomplete="on" maxlength="13" tabindex="5" onblur="ValidateMobileNo();" id="BILLING_ACCOUNT" style="float: left;" /> <div id="custTopUpPhoneDiv" style="color: red; font-size: 12.5px;"></div></p>      
                            <p  align = "right" > <%= CONFIG.getTheDonationValue(session)%> :  <input type="text" name="<%=CONFIG.AMOUNT%>" required  tabindex="4" maxlength="8" title="برجاا ادخال المبلغ رقم صحيح"  id="custAmountID" pattern="^(0|[1-9][0-9]*)$" style="float: left;" onchange="ValidateMaxAmount();
                                    fixedAmount();"><div id="custTopUpDalanceDiv" style="color: red; font-size: 12.5px;"></div></p>

                            <%} else {%>     
                            <p align="right"><%= CONFIG.getPhoneNumber(session)%> : <input type="tel" name="BILLING_ACCOUNT" required title="برجاء ادخال رقم التليفون" autocomplete="on" maxlength="13" tabindex="5" onblur="ValidateMobileNo();" id="BILLING_ACCOUNT" style="float: left;" /> <div id="custTopUpPhoneDiv" style="color: red; font-size: 12.5px;"></div></p>      
                            <p align="right"><%= CONFIG.getTheDonationVal(session)%> :  <input type="text" name="<%=CONFIG.AMOUNT%>"  tabindex="4"  id="custAmountID" maxlength="8" value="<%=respponseDto.getFIXED_AMOUNT()%>" readonly style="float: left;background-color: #EDEDED;" ></p> 

                            <%}%>

                        </td>
                        <td>

                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="0"  readonly tabindex="6" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            <p align="right"><%=CONFIG.getCommessionMaan(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/></p>
                            <p align="right"><%=CONFIG.getTotalAmountMaan(session)%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="7" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmountMaan(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="8" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">

                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getConfirm(session)%>" class="Btn" onclick="return !!(ValidateMaxAmount() & ValidateMobileNo1());" >
                        </td>
                    </tr>
                </table>
                <details open="open">

                    <summary>


                    </summary>

                    1-	برجاء ادخال رقم الموبايل وكتابه القيمة </br>

                    <% if (!request.getParameter("OPERATION_ID").toString().equals("98")) {%>

                    اقل مبلغ مقبول للتبرع 6 جنيهات وارقام صحيحه
                    <%}%>
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
