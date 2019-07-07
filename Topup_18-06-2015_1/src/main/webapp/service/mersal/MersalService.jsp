<%-- 
    Document   : Mersal Service
    Created on : Dec 04, 2016, 12:44:08 PM
    Author     : hammad
--%>

<%@page import="com.masary.common.CONFIG"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    String Operation = session.getAttribute(CONFIG.OPERATION_ID).toString();
    String ProjectName = "";
    if (Operation.equals("1")) {
        ProjectName = CONFIG.MERSAL_CANCER_AR;
    } else if (Operation.equals("2")) {
        ProjectName = CONFIG.MERSAL_THERAPY_AR;
    } else if (Operation.equals("3")) {
        ProjectName = CONFIG.MERSAL_VOICE_AR;
    } else if (Operation.equals("4")) {
        ProjectName = CONFIG.MERSAL_CANCER_SUP_AR;
    } else if (Operation.equals("6")) {
        ProjectName = CONFIG.MERSAL_ZAKAT_FITR_AR;
    } else if (Operation.equals("7")) {
        ProjectName = CONFIG.MERSAL_ZAKAT_MAL_AR;
    } else {
        ProjectName = CONFIG.MERSAL_INISTITUTION_AR;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="./js/LongLiveEgyptJS/LongLiveDonationController.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        <title><%=CONFIG.MERSAL_SERVICE%></title>

        <script language='javascript' type='text/javascript'>
            function checkValue(input) {
                if (input.value !== document.getElementById('donationValue').value) {
                    input.setCustomValidity('القيم المدخله ليست متساويه');
                    return false;
                } else {
                    //input.setCustomValidity('');
                    return true;
                }
            }

            // function ValidateMaxAndMinAmount() {
            //   var amount = document.getElementById("donationValue");

            // if ( Number(amount.value) < 5) {
            //   amount.setCustomValidity('الحد الادنى للتبرع هو 5 جنيهات');

            // return false;
            //}else if (Number(amount.value) > 10000){
            // amount.setCustomValidity('الحد الاقصى للتبرع هو 10000 جنيه في العمليه الواحده');
            //return false;
            //}else{
            // amount.setCustomValidity("");

            //return true;
            // }
            function ValidateMaxAndMinAmount() {
                var amount = document.getElementById("donationValue");
                if (Number(amount.value) < 5) {
                    document.getElementById("donationValueDiv").innerHTML = "الحد الادنى للتبرع هو 5 جنيهات";
                    return false;

                } else if (Number(amount.value) > 10000) {

                    document.getElementById("donationValueDiv").innerHTML = "الحد الاقصى للتبرع هو 10000 جنيه في العمليه الواحده";
                    return false;
                } else {
                    document.getElementById("donationValueDiv").innerHTML = "";
                    document.getElementById("donationValueDiv").disabled = true;
                    return true;

                }
            }
            function validateFractionAmount() {
                var amount = document.getElementById("donationValue").value;
                var decimalOnly = /\d\.\d/;
                if (amount.match(decimalOnly)) {
                    alert('ادخل قيمة التبرع بدون كسور');
                    return false;
                } else {
                    return true;
                }
            }
            function isInt(n) {
                return Number(n) === n && n % 1 === 0;
            }
        </script>

    </head>
    <body onload="onloadFunction()">
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>

        <form action="MersalConfirmationController" id="DonationForm" method="POST"  style="font-weight: bold" onsubmit="return validateFractionAmount()">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_MERSAL_CONFIRMATION%>" />
            <h2 align="center" ><%=CONFIG.MERSAL_SERVICE%></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <th colspan="3" style="text-align: center"><p><%=ProjectName%></p></th>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getDonorPhone(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="DonatorPhone"  autocomplete="off" type="text" id="DonatorPhone" maxlength="11" title="<%=CONFIG.DonatorPhoneTitle(session)%>"   >
                            </div>
                        </div>
                    </td>
                    <td style="border: none">
                        <img name="AboutPhoneNumber" style="visibility: hidden;cursor: pointer" id="AboutPhoneNumber" class="AboutPhoneNumber" title="<%=CONFIG.getAboutPhoneNo(session)%>" src="./img/help.png">
                    </td>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getDonationValue(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input id="donationValue" name="donationValue" autocomplete="off"  type="text" required title="<%=CONFIG.DonationValueTitle(session)%>"  >


                            </div>
                        </div>
                    </td>
                    <td style="border: none">
                        <img name="AboutAmount" style="visibility: hidden;cursor: pointer" id="AboutAmount" class="AboutAmount" title="<%=CONFIG.AboutDonationAmount(session)%>" src="./img/help.png">
                    </td>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getDonationValueCon(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="valueConfirm" autocomplete="off" id="valueConfirm" type="text" required title="<%=CONFIG.DonationValueTitle(session)%>"  onblur="checkValue(this); AmountNotMatch(this.id);" >
                            </div>
                        </div>
                        <div id="donationValueDiv" style="color: red; font-size: 12.5px;"></div>
                    </td>
                    <td style="border: none">
                        <img name="AboutAmount" style="visibility: hidden;cursor: pointer" id="AboutAmount" class="AboutAmount" title="<%=CONFIG.AboutDonationAmount(session)%>" src="./img/help.png">
                    </td>
                </tr>
                <tr>
                    <td colspan="3" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton" onclick="return ValidateMaxAndMinAmount();"  />
                    </td>
                </tr>
            </table>

        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
