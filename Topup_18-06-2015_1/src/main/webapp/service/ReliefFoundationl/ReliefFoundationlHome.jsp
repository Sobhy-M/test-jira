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
    DonationAgentPaymentRespponseDto donationAgentPaymentRespponseDto = null;
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    String donationType = "";
    if (request.getParameter(CONFIG.OPERATION_ID).equals("126")||request.getParameter(CONFIG.OPERATION_ID).equals("111")) {
        donationAgentPaymentRespponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");

    }
    String operationID = request.getParameter("OPERATION_ID");
    if (operationID.equals("1")) {
        donationType = CONFIG.getReliefFoundationlZakah(session);
    } else if (operationID.equals("2")) {
        donationType = CONFIG.getReliefFoundationlSadkah(session);
    } else if (operationID.equals("3")) {
        donationType = CONFIG.getReliefFoundationlFood(session);
    } else if (operationID.equals("4")) {
        donationType = CONFIG.getReliefFoundationlEye(session);
    } else if (operationID.equals("5")) {
        donationType = CONFIG.getReliefFoundationlVirusC(session);
    } else if (operationID.equals("6")) {
        donationType = CONFIG.getReliefFoundationlRamdan(session);
    } else if (operationID.equals("111")) {
        donationType = CONFIG.getResalaResalaOdhaya(session);
    } else if (operationID.equals("8")) {
        donationType = CONFIG.getReliefEftarSaem(session);
    
    } else if (operationID.equals("126")) {
        donationType = CONFIG.getResalaLhoomSadakat(session);
    }
     else if (operationID.equals("510")) {
        donationType = CONFIG.getReliefFoundationlFairRelief(session);
    }
      else if (operationID.equals("511")) {
        donationType = CONFIG.getReliefFoundationlEducation(session);
    }
       else if (operationID.equals("512")) {
        donationType = CONFIG.getReliefFoundationlEmaarApoorvillage(session);
    }
        else if (operationID.equals("513")) {
        donationType = CONFIG.getReliefFoundationlRamadancartoon(session);
    }
         else if (operationID.equals("514")) {
        donationType = CONFIG.getReliefFoundationlBreakfastFasting(session);
    }
       else {
        donationType = CONFIG.getReliefFoundationlZakahEftar(session);
    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <script src="./js/reliefFoundation/reliefFoundation.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="./js/AddNewWalletJS/notify.js"></script>   

        <title><%=CONFIG.getReliefFoundationlService(session)%></title>
        <script>
            $(window).load(function () {
                var servicId =<%=request.getSession().getAttribute("SERVICE_ID").toString()%>;
                {
                    $("#Amount").on('blur', function postinput() {
                        $.ajax({
                            url: 'RFajaxRequest',
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
                            if (arr[0] !== null && arr[0] !== "" && arr[1] !== null && arr[1] !== "") {
                                $('#DeductedAmount').val(Number($('#Amount').val()) + Number(arr[1] - arr[0]));
                                $('#Balance_Diff').val(Number($('#Amount').val()) + Number(arr[1]));
                            } else {
                                $('#DeductedAmount').val("");
                                $('#Balance_Diff').val("");
                            }
                        }).fail(function () {
                            console.log('Failed');
                        });
                    });
                }
            });


        </script>
    </head>

    <body class="body" onload="onValueChanged();">
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include><%}%>
            </div>

            <font style="color: red; font-size: 15px;">${ErrorCode}</font>

        <form name="RFConfirmation" action="RFConfirmationController" method="POST" >
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
                                    <%if (request.getParameter(CONFIG.OPERATION_ID).equals("126")||request.getParameter(CONFIG.OPERATION_ID).equals("111")) {%>
                                <p align="right"><%= CONFIG.getWKAmount(session)%>:<input name="Amount" readonly=""  autocomplete="off" type="text" id="Amount" value="<%= new Double(donationAgentPaymentRespponseDto.getFIXED_AMOUNT()).intValue()%>" maxlength="11" onblur="onValueChanged()" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left"></p>
                                    <%} else {%>
                                <p align="right"><%= CONFIG.getWKAmount(session)%>:<input name="Amount" <%if (request.getParameter(CONFIG.OPERATION_ID).equals("510")||request.getParameter(CONFIG.OPERATION_ID).equals("511")||request.getParameter(CONFIG.OPERATION_ID).equals("513")||request.getParameter(CONFIG.OPERATION_ID).equals("512")||request.getParameter(CONFIG.OPERATION_ID).equals("514")) { %>  min="10"   oninvalid="this.setCustomValidity('برجاء العلم ان اقل مبلغ للتبرع هو 10 جنيه')"  oninput="setCustomValidity('');" <%}%> autocomplete="off" type="number" id="Amount" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left" onblur="onValueChanged()"></p><%}%>
                            </td>
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
                        <p>2-<%= CONFIG.getWKSecondInstruction(session)%></p>
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
