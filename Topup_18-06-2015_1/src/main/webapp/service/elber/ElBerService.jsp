<%-- 
    Document   : Mersal Service
    Created on : Dec 04, 2016, 12:44:08 PM
    Author     : hammad
--%>

<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
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
        ProjectName = CONFIG.ELBER_CHARITY_HOSPITAL_AR;
    } else if (Operation.equals("2")) {
        ProjectName = CONFIG.ELBER_CHARITY_MOSQUE_AR;
    } else if (Operation.equals("3")) {
        ProjectName = CONFIG.ELBER_CHARITY_WELL_AR;
    } else if (Operation.equals("4")) {
        ProjectName = CONFIG.ELBER_ALMS_HELP_AR;
    } else if (Operation.equals("5")) {
        ProjectName = CONFIG.ELBER_ALMS_THERAPY_AR;
    } else if (Operation.equals("6")) {
        ProjectName = CONFIG.ELBER_ALMS_MONEY_AR;
    } else if (Operation.equals("8")) {
        ProjectName = CONFIG.ELBER_RAMDON_BASKET_AR;
    } else if (Operation.equals("9")) {
        ProjectName = CONFIG.AFTAR_SAEAM_AR;
    } else if (Operation.equals("101")) {
        ProjectName = CONFIG.getResalaResalaOdhaya(session);
    } else if (Operation.equals("102")) {
        ProjectName = CONFIG.LHOOM_SADAKAT_AR;
    }  else {
        ProjectName = CONFIG.ELBER_ALMS_HOUSE_AR;
    }

    DonationAgentPaymentRespponseDto respponseDto = new DonationAgentPaymentRespponseDto();

    respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="./js/LongLiveEgyptJS/LongLiveDonationController.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        <title><%=CONFIG.ELBER_SERVICE%></title>

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
            function checkValueSadakat() {
                var value = document.getElementById('donationValue').value;
                if (value < 100 && <%= Operation%> === 102) {
                    return false;
                } else {
                    return true;
                }

            }
        </script>

    </head>
    <body onload="trueAmount()">
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>

        <form action="ElBerConfirmationController" id="DonationForm" method="POST" style="font-weight: bold">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ELBER_CONFIRMATION%>" />
            <input type="hidden" name="ProjectName" value="<%=ProjectName%>" />
            <h2 align="center" ><%=CONFIG.ELBER_SERVICE%></h2>
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
                                <input name="DonatorPhone" required="" autocomplete="off" type="text" id="DonatorPhone" maxlength="11" title="<%=CONFIG.DonatorPhoneTitle(session)%>"  onblur="ThePhoneNotMatch(this.id);" >
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
                                <%if (respponseDto.getFIXED_AMOUNT() == 0.0) {%>
                                <input name="donationValue" autocomplete="off" id="donationValue" type="text" onchange="checkValueSadakat(this);" required title="<%=CONFIG.DonationValueTitle(session)%>" onblur="AmountNotMatch(this.id);" >
                                <%} else {%>
                                <input name="donationValue" autocomplete="off" id="donationValue" type="text"     value="<%=respponseDto.getFIXED_AMOUNT().intValue()%>" readonly onload="trueAmount()" style="background-color: #EDEDED;">

                                <%}%>
                            </div>
                        </div>
                    </td>
                    <td style="border: none">
                        <img name="AboutAmount" style="visibility: hidden;cursor: pointer" id="AboutAmount"  class="AboutAmount" title="<%=CONFIG.AboutDonationAmount(session)%>" src="./img/help.png">
                    </td>
                </tr>
                <%if (respponseDto.getFIXED_AMOUNT() == 0.0) {%>
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
                    </td>
                    <td style="border: none">
                        <img name="AboutAmount" style="visibility: hidden;cursor: pointer" id="AboutAmount" class="AboutAmount" title="<%=CONFIG.AboutDonationAmount(session)%>" src="./img/help.png">
                    </td>
                </tr>

                <tr>
                    <td colspan="3" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton"  />
                    </td>
                </tr>
                <%} else {%>
                <tr>
                    <td colspan="3" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton" onclick="return SubmitButtonPhone()"  />
                    </td>
                </tr><%}%>
            </table>

        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
