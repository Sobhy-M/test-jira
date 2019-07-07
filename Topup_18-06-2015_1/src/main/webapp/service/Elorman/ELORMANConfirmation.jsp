<%-- 
    Document   : LongLifeEgyptConfirm
    Created on : Mar 15, 2016, 11:37:21 AM
    Author     : Masary
--%>

<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custId = null;

    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), session.getAttribute(CONFIG.PARAM_PIN).toString());
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    String ProjectName = "";
    int OperationID = Integer.parseInt(session.getAttribute("OPERATION_ID").toString());
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
    }
    if (OperationID == 7) {
        ProjectName = CONFIG.getSakOfImportedMeat(session);
    }
    if (OperationID == 8) {
        ProjectName = CONFIG.getSaksmallClaves(session);
    }
    if (OperationID == 9) {
        ProjectName = CONFIG.getSakOldCalvesMeat(session);
    }
    if (OperationID == 104) {
        ProjectName = CONFIG.getSakOfImportedMeat(session);

    }
    if (OperationID == 105) {
        ProjectName = CONFIG.getSakOldCalvesMeat(session);

    }
    if (OperationID == 106) {
        ProjectName = CONFIG.getSaksmallClaves(session);
    }
    if (OperationID ==211) {
        ProjectName = CONFIG.getBoxRamdan90(session);
    }
    if (OperationID ==212) {
        ProjectName = CONFIG.getBoxRamdan150(session);
    }
    if (OperationID ==213) {
        ProjectName = CONFIG.getBoxRamdan220(session);
    }
    if (OperationID == 151) {
        ProjectName = CONFIG.getSakMostawradSa8er1950(session);
    }
    if (OperationID == 152) {
        ProjectName = CONFIG.getSakMostawradKaber2550(session);
    }
    Double Amount = Double.parseDouble(request.getParameter("donationValue"));
    Double ServiceCost = ratePlan.getApplied_fees() + ratePlan.getApplied_fees_per() * Amount / 100;
    Double DonationVal = Double.parseDouble(request.getParameter("donationValue")) + ServiceCost;
    Double commission = ratePlan.getFixedAmount() + ((ratePlan.getCommission() / 100) * Amount);
    Double DedducedAmount = Amount + ServiceCost - commission;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getElormanDescription(session)%></title>
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />

        <script type="text/javascript">
            function disableDoubleSubmission() {
                document.getElementById("submitbutton").disabled = true;
            }
        </script>

    </head>
    <body>
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%> 
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        <form action="ElormanPayController" method="POST" style="font-weight: bold" onsubmit="disableDoubleSubmission()">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ELORMAN_PAY%>"/>
            <table style="width: 70% ; margin-left: auto ; margin-right: auto">
                <%if (OperationID == 6 || OperationID == 7 || OperationID == 8 || OperationID == 9 || OperationID == 211 || OperationID == 212 || OperationID == 213 || OperationID == 151 || OperationID == 152) {%>
                <table style="width: 70% ; margin-left: auto ; margin-right: auto">
                    <input type="hidden" name="TotalAmountPayable" value="<%=DonationVal%>" readonly style="background-color: #EDEDED;" >
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
                                                    <input  name="donationValue" id="donationValue" value="<%=request.getParameter("donationValue")%>"  autocomplete="off" maxlength="100" type="text" readonly style="background-color: #EDEDED;">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="border: none ; background-color: transparent">
                                                    <p><%= CONFIG.getDonorname(session)%>:</p>
                                                </td>
                                                <td style="border: none ; background-color: transparent">
                                                    <input  name="DonatorName" id="DonatorName" readonly value="<%=new String(request.getParameter("DonatorName").getBytes("ISO-8859-1"), "utf-8")%>" type="text" style="background-color: #EDEDED;">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="border: none ; background-color: transparent">
                                                    <p><%= CONFIG.getDontaorPhoneNumber(session)%>:</p>
                                                </td>
                                                <td style="border: none">
                                                    <input style="background-color: #EDEDED" name="DonatorPhone" type="text" id="DonatorPhone" value="<%=request.getParameter("DonatorPhone")%>" >
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
                                        <input style="padding-left: auto;background-color: #EDEDED" value="<%=commission%>" name="DonatorName" id="DonatorName" autocomplete="off"  type="text" readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border: none ; background-color: transparent">
                                        <p><%= CONFIG.getDeductedAmount(session)%>:</p>
                                    </td>
                                    <td style="border: none ; background-color: transparent">
                                        <input style="padding-left: auto;background-color: #EDEDED" value="<%=request.getParameter("DeductedAmount")%>"  name="DeductedAmount" id="DeductedAmount" type="text" readonly >
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <%} else {%>
                    <table style="width: 50% ; margin-left: auto ; margin-right: auto">
                        <tr>
                            <th colspan="2" style="text-align: center"><p><%=CONFIG.getElormanDescription2(request.getSession()) + ProjectName%></p></th>
                        </tr>
                        <tr class="secondTD">
                            <td colspan="2" class="secondTD">
                                <table>
                                    <tr>
                                        <td style="border: none ; background-color: transparent; padding: 0px" class="secondTD">
                                            <p><%=CONFIG.getWillDonateTO(session)%></p>
                                        </td>
                                        <td style="border: none ; background-color: transparent; padding: 0px" class="secondTD">
                                            <label>&nbsp;<%=CONFIG.getToElorman(request.getSession()) + ProjectName%></label>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p><%=CONFIG.getTheDonationValue(session)%>:</p>
                            </td>
                            <td>
                                <div>
                                    <div>
                                        <input type="text" name="donationValue" value="<%=request.getParameter("donationValue")%>" readonly style="background-color: #EDEDED;" >
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p><%=CONFIG.getServiceCost(session)%></p>
                            </td>
                            <td>
                                <input type="text" name="ServiceCost" value="<%=ServiceCost%>" readonly style="background-color: #EDEDED;" >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p><%=CONFIG.getCommessionMaan(session)%></p>
                            </td>
                            <td>
                                <input type="text" name="commission" value="<%=commission%>" readonly style="background-color: #EDEDED;" >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p><%= CONFIG.getWillbededucted(session)%>:</p>
                            </td>
                            <td>
                                <input type="text" name="DedducedAmount" value="<%=DedducedAmount%>" readonly style="background-color: #EDEDED;"  >
                            </td>
                        </tr>
                        <tr>         
                            <td>
                                <p><%=CONFIG.getTotalAmountPayable(session)%></p>
                            </td>
                            <td>
                                <div>
                                    <div>
                                        <input type="text" name="TotalAmountPayable" value="<%=DonationVal%>" readonly style="background-color: #EDEDED;" >
                                        <div>
                                        </div>
                                        </td>

                                        </tr>
                                        <tr>
                                            <td>
                                                <p><%= CONFIG.getDonorname(session)%>:</p>
                                            </td>
                                            <td>
                                                <input   type="text" name="DonatorName" value="<%=new String(request.getParameter("DonatorName").getBytes("ISO-8859-1"), "utf-8")%>" readonly style="background-color: #EDEDED;" >
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <p><%= CONFIG.getDontaorPhoneNumber(session)%>:</p>
                                            </td>
                                            <td>
                                                <input type="text" readonly name="DonatorPhone" value="<%=request.getParameter("DonatorPhone")%>" style="background-color: #EDEDED;" >
                                            </td>
                                        </tr>
                                        <%}%>
                                        <tr>
                                            <td>
                                                <div>
                                                    <div>
                                                        <input type="submit" value="<%=CONFIG.getGo(session)%>"  id="submitbutton" onclick="" />
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <input type="button" value="<%=CONFIG.getBack(session)%>"  id="submitbutton" onclick="history.go(-1);"  />
                                                </div>
                                            </td>

                                        </tr>
                                        </table>
                                        </form>
                                        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

                                        </body>
                                        </html>