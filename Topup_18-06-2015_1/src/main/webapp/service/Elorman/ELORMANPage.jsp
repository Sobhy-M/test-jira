<%-- 
    Document   : LongLiveEgyptDonation
    Created on : Mar 13, 2016, 3:44:08 PM
    Author     : Masary
--%>

<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
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
    RatePlanDTO ratePlan;
    DonationAgentPaymentRespponseDto respponseDto;
    String ProjectName = "";
    Double DeductedAmount=0.0;
    Double commission=0.0;
    Double DonationVal=0.0;
    int OperationID = Integer.parseInt(request.getParameter("OPERATION_ID"));
    if (OperationID == 1) {
        ProjectName = CONFIG.getElormanSocityProject(session);
    }
    if (OperationID == 2) {
        ProjectName = CONFIG.getElormanZakahProject(session);
    }
    if (OperationID == 3) {
        ProjectName = CONFIG.getElormanSadkahProject(session);
    }
    if (OperationID == 4) {
        ProjectName = CONFIG.getElormanHospitalProject(session);
    }
    if (OperationID == 5) {
        ProjectName = CONFIG.getElormanBuffaloProject(session);
    }
    if (OperationID == 6) {
        ProjectName = CONFIG.getElormanSadkahMeatProject(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
   
    if (OperationID == 7) {
        ProjectName = CONFIG.getSakOfImportedMeat(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 8) {
        ProjectName = CONFIG.getSaksmallClaves(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 9) {
        ProjectName = CONFIG.getSakOldCalvesMeat(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 104) {
        ProjectName = CONFIG.getSakOfImportedMeat(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 105) {
        ProjectName = CONFIG.getSakOldCalvesMeat(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 106) {
        ProjectName = CONFIG.getSaksmallClaves(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 211) {
        ProjectName = CONFIG.getBoxRamdan90(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 212) {
        ProjectName = CONFIG.getBoxRamdan150(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 213) {
        ProjectName = CONFIG.getBoxRamdan220(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 151) {
        ProjectName = CONFIG.getSakMostawradSa8er1950(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
    if (OperationID == 152) {
        ProjectName = CONFIG.getSakMostawradKaber2550(session);
        respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
        ratePlan = (RatePlanDTO)session.getAttribute("ratePlan");
        commission = ratePlan.getFixedAmount();
        DeductedAmount = respponseDto.getFIXED_AMOUNT() -  ratePlan.getFixedAmount();
        DonationVal = respponseDto.getFIXED_AMOUNT();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="./js/ELormanJS/ElormanJS.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        <title><%=CONFIG.getElormanDescription(session)%></title>
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

        <form action="ElormanConfirmationController" id="DonationForm" method="POST" style="font-weight: bold">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ELORMAN_Confirmation%>" />
              <input type="hidden" name="OPERATION_ID" value="<%=ProjectName%>" />
            <%if (OperationID == 6 || OperationID == 7 || OperationID == 8 || OperationID == 9 || OperationID == 104 || OperationID == 105 || OperationID == 106 ||OperationID == 211 || OperationID == 212 ||OperationID == 213 ||OperationID == 151 ||OperationID == 152) {%>
            <table style="width: 70% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <td  style="text-align: center"><%=CONFIG.getINFO_Required(request.getSession())%></td>
                    <td  style="text-align: center"><%=CONFIG.getMerchantCommession(request.getSession())%></td>
                </tr>
                <tr>
                    <td>
                        <table style="width: 100%">
                            <tr>
                                <td style=" border: none ; background-color: transparent">
                                    <table style="width: 100%">
                                        <tr>
                                            <td style="border: none ; background-color: transparent">
                                                <p><%= CONFIG.getToKindOFDonation(session)%>:</p>
                                            </td>
                                            <td style="border: none ; background-color: transparent">
                                                <input  name="KindOfDonation" id="KindOfDonation"  autocomplete="off" value="<%=ProjectName%>" readonly maxlength="100" type="text" style="background-color: #EDEDED;">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border: none ; background-color: transparent">
                                                <p><%= CONFIG.getTheDonationVal(session)%>:</p>
                                            </td>
                                            <td style="border: none ; background-color: transparent">
                                                <input  name="donationValue" id="donationValue"  value="<%=DonationVal%>"  autocomplete="off" maxlength="100" type="text" readonly style="background-color: #EDEDED;"  onsubmit="setAmountTrue()"  onload="setAmountTrue();">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border: none ; background-color: transparent">
                                                <p><%= CONFIG.getDonorname(session)%>:</p>
                                            </td>
                                            <td style="border: none ; background-color: transparent">
                                                <input  name="DonatorName" id="DonatorName" autofocus="true" autocomplete="off" maxlength="100" type="text" onblur="setAmountTrue();NameNotMatch(this.id)">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="border: none ; background-color: transparent">
                                                <p><%= CONFIG.getDontaorPhoneNumber(session)%>:</p>
                                            </td>
                                            <td style="border: none">
                                                <div class="requriedclass">
                                                    <div>
                                                        <input name="DonatorPhone"  autocomplete="off" type="text" id="DonatorPhone" maxlength="11" title="<%=CONFIG.DonatorPhoneTitle(session)%>"  onblur="ThePhoneNotMatch(this.id);" >
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td style=" background-color: transparent">
                        <table>
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%= CONFIG.getCommession(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color: transparent">
                                    <input style="padding-left: auto;;background-color: #EDEDED" value="<%=commission%>" name="DonatorName" id="DonatorName" autocomplete="off"  type="text" readonly>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%= CONFIG.getDeductedAmount(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color: transparent">
                                    <input style="padding-left: auto;background-color: #EDEDED" value="<%=DeductedAmount%>"  name="DeductedAmount" id="DeductedAmount" autocomplete="off"  type="text" readonly >
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <%} else {%>
                <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <th colspan="3" style="text-align: center"><p><%=CONFIG.getElormanDescription2(request.getSession()) + ProjectName%></p></th>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getDonorname(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <input name="DonatorName" id="DonatorName" autofocus="true" autocomplete="off" maxlength="100" type="text" onblur="NameNotMatch(this.id)">
                    </td>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getDontaorPhoneNumber(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="DonatorPhone"  autocomplete="off" type="text" id="DonatorPhone" maxlength="11" title="<%=CONFIG.DonatorPhoneTitle(session)%>"  onblur="ThePhoneNotMatch(this.id);" >
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
                                <input name="donationValue" autocomplete="off" id="donationValue" type="text" required title="<%=CONFIG.getElzakaHomeDonationValTitle(session)%>" onblur="AmountNotMatch(this.id);" >
                            </div>
                        </div>
                    </td>
                    <td style="border: none">
                        <img name="AboutAmount" style="visibility: hidden;cursor: pointer" id="AboutAmount" class="AboutAmount" title="<%=CONFIG.getElzakaHomeDonationValTitle(session)%>" src="./img/help.png">
                    </td>
                </tr>
                <%}%>
                <tr>
                    <td colspan="3" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getConfirm(session)%>"  id="submitbutton" onclick="return SubmitButton()"  />
                    </td>
                </tr>
            </table>
        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
