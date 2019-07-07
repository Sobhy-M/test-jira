<%-- 
    Document   : MostafaMahmoudHomePage
    Created on : Jun 4, 2017, 12:58:38 PM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
<%@page import="com.masary.database.dto.DonationRespponseDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
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
    DonationAgentPaymentRespponseDto donationAgentPaymentRespponseDto = null;
    session = request.getSession();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    if (request.getParameter(CONFIG.OPERATION_ID).equals("121") || request.getParameter(CONFIG.OPERATION_ID).equals("122") || request.getParameter(CONFIG.OPERATION_ID).equals("114") || request.getParameter(CONFIG.OPERATION_ID).equals("123")) {
        donationAgentPaymentRespponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");

    }
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

        <title><%=CONFIG.getQabasnoorRecieptMess1(session)%></title>
        <script>
          
               function onChangeAmount() {
                var servicId =<%=request.getSession().getAttribute("SERVICE_ID").toString()%>;
                        $.ajax({
                            url: 'QNajaxRequest',
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
              
            }

           
        </script>
    </head>

    <body class="body" onload="onChangeAmount();">
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include><%}%>
            </div>

            <font style="color: red; font-size: 15px;">${ErrorCode}</font>

        <form name="QNHomePage" action="QabasnoorConfirmationController" method="POST" >
            <div class="content_body"  >
                <fieldset style="width: 70%; direction: rtl;" align="right">
                    <input type="hidden" name="ServiceID" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                     <input type="hidden" name="OPERATION_ID" value="<%=request.getParameter(CONFIG.OPERATION_ID)%>" />
                    <legend align="right" ><font size="5"><%= CONFIG.getCustomerdonationHead(session)%>&nbsp;</font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                    <table  border="1" width="100%">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=CONFIG.getMerchantCommession(session)%></th>
                        <tr>
                            <td>
                                <p align="right"><%= CONFIG.getWKPhoneNumber(session)%>:<input name="PhoneNumber"  autocomplete="off" type="text" id="PhoneNumber" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left"></p>               
                                <%if (request.getParameter(CONFIG.OPERATION_ID).equals("121") || request.getParameter(CONFIG.OPERATION_ID).equals("122") || request.getParameter(CONFIG.OPERATION_ID).equals("123")  || request.getParameter(CONFIG.OPERATION_ID).equals("114")) {%>
                                <p align="right"><%= CONFIG.getWKAmount(session)%>:<input name="Amount" readonly=""  autocomplete="off" type="text" id="Amount" value="<%= new Double(donationAgentPaymentRespponseDto.getFIXED_AMOUNT()).intValue()%>" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left"></p>
                                    <%} else {%>
                                <p align="right"><%= CONFIG.getWKAmount(session)%>:<input name="Amount"  autocomplete="off" type="text" id="Amount" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" onchange="onChangeAmount();" style="float: left"></p>


                                <%}%>
                            </td>
                            <td>
                                <p align="right"><%= CONFIG.getFees(session)%>:<input name="Fees" readonly autocomplete="off" type="text" id="Fees" maxlength="20" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>                                                                         
                                <p align="right"><%= CONFIG.getCommessionMaan(session)%>:<input name="Commession" readonly autocomplete="off" type="text" id="Commession" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>                                                                                      
                                <p align="right"><%= CONFIG.getTotalAmountMaan(session)%>:<input name="Balance_Diff" readonly autocomplete="off" type="text" id="Balance_Diff" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>
                                <p align="right"><%=CONFIG.getDeductedAmountMaan(session)%> :<input type="text"  name="DeductedAmount" value=""  readonly tabindex="6" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
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

