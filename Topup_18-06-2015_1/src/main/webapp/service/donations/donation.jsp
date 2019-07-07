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
    if (session.getAttribute("SERVICE_ID").toString().equals("601")) {
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <script type="text/javascript" src="img/jquery-1.11.3.min.js"></script>
        <title><%=CONFIG.getdonationName(session)%></title>
        <script>
            $(window).load(function () {
                var servicId =<%=request.getSession().getAttribute("SERVICE_ID").toString()%>;
                if (servicId == 602) {
                    $("#custAmountID").on('change', function postinput() {
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
                            if (arr[0] != null && arr[0] != "" && arr[1] != null && arr[1] != "") {
                                $('#DeductedAmount').val(Number($('#custAmountID').val()) + Number(arr[1] - arr[0]));
                                $('#Balance_Diff').val(Number($('#custAmountID').val()) + Number(arr[1]));
                            } else {
                                $('#DeductedAmount').val("");
                                $('#Balance_Diff').val("");
                            }
                        }).fail(function () {
                            console.log('Failed');
                        });
                    });
                }
            });</script>
        <script>
            function onValueChanged() {
                var Amount = document.getElementById("custAmountID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
            <%
                if (!session.getAttribute("SERVICE_ID").toString().equals("602")) {%>
                Commession.value = Number(Fees.value) / 2;
                if (Amount.value != null && Amount.value != "") {
                    Balance_Diff.value = (Number(Amount.value) + Number(Fees.value));
                    DeductedAmount.value = (Number(Amount.value) + (Number(Fees.value) - Number(Commession.value)));
                } else {
                    Balance_Diff.value = "0";
                    DeductedAmount.value = "0";
                }
            <%}%>
            }


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
                }
                else {
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
                }
                else {
                    return true;
                }
            }

            function Validatename() {
                var errChk = 0;
                var name = document.getElementById("Customer_name");
                var nameDiv = document.getElementById("CustomerNameDiv");
                if (name.value != "") {
                    nameDiv.innerHTML = "";
                } else {
                    errChk = 1;
                    nameDiv.innerHTML = "<br>برجاء ادخال اسم المتبرع</br>";
                }
                if (errChk == 1) {
                    return false;
                }
                else {
                    return true;
                }
            }
            function ValidateAmount() {
                var errChk = 0;
                var amount = document.getElementById("custAmountID");
                var balanceDiv = document.getElementById("custTopUpDalanceDiv");
                var pattern = "\d+(\.\d{1,2})?";
                if (amount.value != "" && amount.value.match(pattern)) {
                    onValueChanged();
                    balanceDiv.innerHTML = "";
                    balanceDiv.disable = true;
                }
                if (amount.value == "" || amount.value == null || Number(amount.value) == 0 || Number(amount.value) < 0) {
//                    alert("invalid amount");
                    errChk = 1;
                    balanceDiv.innerHTML = "برجاء ادخال قيمة التبرع الصحيحة";
                }
                if (errChk == 1) {
                    return false;
                }
                else {
                    return true;
                }
            }
            function ValidateMaxAmount() {
                var amount = document.getElementById("custAmountID");
                var balanceDiv = document.getElementById("custTopUpDalanceDiv");
                var errChk = 0;
                var pattern = /\d+(\.\d{1,2})?/;
                if (amount.value != "" && amount.value.match(pattern)&&Number(amount.value) <=250) {
                    onValueChanged();
                    balanceDiv.innerHTML = "";
                    balanceDiv.disable = true;
                }
                if (amount.value == "" || amount.value == null || Number(amount.value) == 0 || Number(amount.value) < 0) {
//                    alert("invalid amount");
                    errChk = 1;
                    balanceDiv.innerHTML = "برجاء ادخال قيمة التبرع الصحيحة";
                }
                if (Number(amount.value) > 250) {
                    errChk = 1;
                    balanceDiv.innerHTML = "برجاء ادخال قيمة اقل من او يساوي 250";
                }
                if (errChk == 1) {
                    return false;
                }
                else {
                    return true;
                }
            }
            function ValidateForm() {
                if ((ValidateAmount() && ValidateMaxAmount()) && ValidateMobileNo() && Validatename()) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>

    </head>

    <body class="body" onload="onValueChanged();">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" >

        <input type="hidden" name="action" value="<%=CONFIG.ACTION_GET_DONATION_CONFIRMATION%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("SERVICE_ID").toString()%>" />
        <% if (session.getAttribute("SERVICE_ID").toString().equals("601")) {%>
        <input type="hidden" name="<%=CONFIG.OPERATION_ID%>" value="<%=request.getSession().getAttribute("OPERATION_ID").toString()%>" />
        <%}%>
        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.getCustomerdonationHead(session)%>&nbsp;</font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>

                    <tr>
                        <td>
                            <% if (session.getAttribute("SERVICE_ID").toString().equals("601")) {%>
                            <p align="right"><%=CONFIG.getprogram(session)%> : <input type="text" name="<%=CONFIG.PROGRAM%>" readonly tabindex="1" id="program" value="<%= respponseDto.getOPERATION_NAME()%>" style="background-color: #EDEDED; float: left;"/></p>
                                <%}%>
                                <% if (session.getAttribute("SERVICE_ID").toString().equals("602")) {%>
                            <p align="right"><%= CONFIG.getvolunteerName(session)%> : <input type="text" name="Customer_name" autocomplete="off"  tabindex="2" id="Customer_name" autofocus style="float: left;" /></p>
                                <%} else {%>
                            <p align="right"><%= CONFIG.getvolunteerName(session)%> : <input type="text" name="Customer_name" autocomplete="off" onchange="Validatename()" required title="برجاء ادخال اسم المتبرع" tabindex="3" id="Customer_name" style="float: left;" /><div id="CustomerNameDiv" style="color: red; font-size: 12.5px;"></div></p>
                                <%}%>

                            <% if (session.getAttribute("SERVICE_ID").toString().equals("601")) {
                                    if (respponseDto.getFIXED_AMOUNT() <= 0.0) {
//                                        //System.out.println("da5al1" + respponseDto.getFIXED_AMOUNT());
                            %>
                            <p  align = "right" > <%= CONFIG.getAssign_v(session)%> :  <input type="text" name="<%=CONFIG.AMOUNT%>"  tabindex="4" maxlength="8"  id="custAmountID" pattern="^[1-9]\d*(\.\d{1,9})?" style="float: left;" onchange="ValidateMaxAmount();"><div id="custTopUpDalanceDiv" style="color: red; font-size: 12.5px;"></div></p>
                            <p align="right"><%= CONFIG.getPhoneNumber(session)%> : <input type="tel" name="BILLING_ACCOUNT" required title="برجاء ادخال رقم التليفون" autocomplete="off" tabindex="5" onblur="ValidateMobileNo();" id="BILLING_ACCOUNT" style="float: left;" /> <div id="custTopUpPhoneDiv" style="color: red; font-size: 12.5px;"></div></p>      

                            <%} else {%>     
                            <p align="right"><%= CONFIG.getAssign_v(session)%> :  <input type="text" name="<%=CONFIG.AMOUNT%>"  tabindex="4"  id="custAmountID" maxlength="8" value="<%=respponseDto.getFIXED_AMOUNT()%>" readonly style="float: left;background-color: #EDEDED;" onchange="onValueChanged();"></p> 
                            <p align="right"><%= CONFIG.getPhoneNumber(session)%> : <input type="tel" name="BILLING_ACCOUNT" required title="برجاء ادخال رقم التليفون" autocomplete="off" tabindex="5" onblur="ValidateMobileNo();" id="BILLING_ACCOUNT" style="float: left;" /> <div id="custTopUpPhoneDiv" style="color: red; font-size: 12.5px;"></div></p>      

                            <%}
                            } else {%>
                            <p align="right"><%= CONFIG.getAssign_v(session)%> :  <input type="text" name="<%=CONFIG.AMOUNT%>"
                                                                                         maxlength="8" tabindex="4" title="برجاء ادخال قيمة التبرع الصحيحة" id="custAmountID" value="" pattern="^[1-9]\d*(\.\d{1,9})?" style="float: left;" required="" onchange="onValueChanged();
                                                                                                 ValidateAmount();"><div id="custTopUpDalanceDiv" style="color: red; font-size: 12.5px;"></div></p> 
                            <p align="right"><%= CONFIG.getPhoneNumber(session)%> : <input type="tel" name="BILLING_ACCOUNT"  tabindex="5"  onblur="ValidateMobileNo1();" id="BILLING_ACCOUNT" style="float: left;" /> <div id="custTopUpPhoneDiv" style="color: red; font-size: 12.5px;"></div></p>      

                            <%}%>


                            <p align="right"><%=CONFIG.getGovernorate(session)%> : 
                                <% if (session.getAttribute("SERVICE_ID").toString().equals("601")) {%>
                                <select required title="برجاء اختار المحافظة" name="governrate" id="governrate" style="float: left;">
                                    <%} else {%> 
                                    <select name="governrate" id="governrate" style="float: left;">
                                        <%}%>
                                        <option selected value="" ><%= CONFIG.getGovernrate(session)%></option>
                                        <%for (String gov : list.values()) {%>
                                        <option ><%= gov%></option>
                                        <%}%>

                                    </select>
                            </p>
                        </td>
                        <td>
                            <%  if (session.getAttribute("SERVICE_ID").toString().equals("601")) {%>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="<%= respponseDto.getFEES()%>"  readonly tabindex="6" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            <p align="right"><%=CONFIG.getCommessionMaan(session)%> :<input type="text" name="Commession" value="<%= respponseDto.getFEES() / 2%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/></p>
                            <p align="right"><%=CONFIG.getTotalAmountMaan(session)%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="7" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmountMaan(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="8" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                <%} else {%>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            <p align="right"><%=CONFIG.getCommessionMaan(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getTotalAmountMaan(session)%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmountMaan(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            <%}%></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <% if (session.getAttribute("SERVICE_ID").toString().equals("602")) {%>
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getConfirm(session)%>" class="Btn" onclick="return !!(ValidateAmount() & ValidateMobileNo1());" >

                            <%} else {%>
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getConfirm(session)%>" onclick="return ValidateForm();" class="Btn" >

                            <%}%>


                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                        <% if (session.getAttribute("SERVICE_ID").toString().equals("602")) {%>
                    1-	برجاء العلم أن اسم المتبرع ، رقم التليفون ، المحافظة هي بيانات اختيارية.
                    <%} else {%>
                    1-	برجاء ادخال رقم الموبايل واسم المتبرع واختر المحافظة وكتابه القيمة 
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
