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
    String donationType = "";
    String operationID = request.getParameter("OPERATION_ID");
    if (operationID.equals("87")) {
        donationType = CONFIG.getResalaDarAetam(session);
    } else if (operationID.equals("88")) {
        donationType = CONFIG.getResalazakahMal(session);
    } else if (operationID.equals("86")) {
        donationType = CONFIG.getResalaKfartSaem(session);
   
    } else if (operationID.equals("89")) {
        donationType = CONFIG.getResalaCriticalSituations(session);
    } else if (operationID.equals("90")) {
        donationType = CONFIG.getResalaOngoingcharity(session);
    } else if (operationID.equals("91")) {
        donationType = CONFIG.getResalaBahiaCenter(session);
    } else if (operationID.equals("92")) {
        donationType = CONFIG.getResalaAftarSaem(session);
    } else if (operationID.equals("93")) {
        donationType = CONFIG.getResalaRamdanBox(session);
    } else if (operationID.equals("110")) {
        donationType = CONFIG.getResalaResalaOdhaya(session);
    } else if (operationID.equals("95")) {
        donationType = CONFIG.getResalaZakahEftar(session);
    } else if (operationID.equals("1000")) {
        donationType = CONFIG.getResalaZakahEftar(session);
    }else {
        donationType = CONFIG.getResalaLhoomSadakat(session);
    }
    session = request.getSession();
    DonationAgentPaymentRespponseDto donationAgentPaymentRespponseDto = null;
    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(String.valueOf(809), agent.getPin());
//    Bill_Response bill_response = (Bill_Response) session.getAttribute("bill_Response");
    if (request.getParameter(CONFIG.OPERATION_ID).equals("110") || request.getParameter(CONFIG.OPERATION_ID).equals("92") || request.getParameter(CONFIG.OPERATION_ID).equals("93")) {
        donationAgentPaymentRespponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");

    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

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
                var Amount = document.getElementById("Amount");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
                Commession.value = (<%= ratePlan.getFixedAmount()%> + (Number(Amount.value) * <%= ratePlan.getCommission()%> / 100)).toFixed(1);
                Fees.value = (<%= ratePlan.getApplied_fees()%> + (Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100)).toFixed(2);
                DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value));
                Balance_Diff.value = (Number(Amount.value) + Number(Fees.value)).toFixed(2);

            }
            
        </script>
    </script>
</head>

<body class="body" onload="onValueChanged()">
    <div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include><%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="WasletKhierHome" action="ResalaConfirmationController" method="POST" >
        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">
                <input type="hidden" name="OperationID" value="<%=request.getParameter("OPERATION_ID")%>" />
                <legend align="right" ><font size="5"><%= CONFIG.getCustomerdonationHead(session)%>&nbsp;</font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td>
                            <p  align="right"><%= CONFIG.getWKDonationType(session)%>:<input name="DonationType" value="<%=donationType%>" autocomplete="off" type="text" id="DonationType" maxlength="20" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>
                            <p align="right"><%= CONFIG.getWKPhoneNumber(session)%>:<input name="PhoneNumber"  autocomplete="off" type="text" id="PhoneNumber" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left"></p>
                                <%if (request.getParameter(CONFIG.OPERATION_ID).equals("110") || request.getParameter(CONFIG.OPERATION_ID).equals("92") || request.getParameter(CONFIG.OPERATION_ID).equals("93")) {%>
                            <p align="right"><%= CONFIG.getWKAmount(session)%>:<input name="Amount" readonly=""  autocomplete="off" type="text" id="Amount" value="<%= new Double(donationAgentPaymentRespponseDto.getFIXED_AMOUNT()).intValue()%>" maxlength="11" onblur="onValueChanged()" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>
                                <%} else {%>
                            <p align="right"><%= CONFIG.getWKAmount(session)%>:<input name="Amount"  autocomplete="off" type="text" id="Amount" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left" onchange="onValueChanged()"></p>
                            <%}%>                        </td>
                        <td>
                            <p align="right"><%= CONFIG.getFees(session)%>:<input name="Fees"  autocomplete="off" type="text" id="Fees" maxlength="20" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>                                                                         
                            <p align="right"><%= CONFIG.getCommessionMaan(session)%>:<input name="Commession"  autocomplete="off" type="text" id="Commession" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>                                                                                      
                            <p align="right"><%= CONFIG.getTotalAmountMaan(session)%>:<input name="Balance_Diff"  autocomplete="off" type="text" id="Balance_Diff" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>
                            <p align="right"><%=CONFIG.getDeductedAmountMaan(session)%> :<input type="text" name="DeductedAmount" value=""  readonly tabindex="6" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
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
                    <p>2-أقل قيمة للتبرع خمسة جنيهات بدون كسور</p>
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
