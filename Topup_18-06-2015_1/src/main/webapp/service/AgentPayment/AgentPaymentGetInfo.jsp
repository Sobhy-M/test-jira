<%-- 
    Document   : AgentPaymentGetInfo
    Created on : Aug 22, 2016, 12:35:04 PM
    Author     : y
--%>

<%@page import="com.masary.database.dto.RatePlanDTO"%>
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
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), session.getAttribute(CONFIG.PARAM_PIN).toString());
//    Double comm = ratePlan.getCommission();
//    Double comm = ratePlan.getFixedAmount() + ((ratePlan.getCommission() / 100) * Double.parseDouble(request.getParameter("donationValue")));
    String companyCode = session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString();
    String rolePage = CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp";
%>
<!DOCTYPE html>
<html>
    <head>
        <title><%=CONFIG.getCompaniesProceeds(session)%></title>
        <script src="./js/agentDeltaValidation.js"></script>
        <script>
            function calclulateCommession() {
                var amount = document.getElementById("amount").value;
                if (amount === "")
                {
                    return;
                } else
                {
                    var doubledAmount = parseFloat(amount);
                    var doubledComm = ((<%= ratePlan.getCommission()/100%> * Number(doubledAmount)) + <%= ratePlan.getFixedAmount()%>);
                    document.getElementById("commission").value = doubledComm;
                    document.getElementById("valueFees").value = (<%= ratePlan.getApplied_fees_per()/100%> * Number(doubledAmount) + <%= ratePlan.getApplied_fees()%>) + Number(doubledAmount);
                    document.getElementById("payedAmount").value =((<%= ratePlan.getApplied_fees_per()/100%> * Number(doubledAmount) + <%= ratePlan.getApplied_fees()%>) + Number(doubledAmount) - doubledComm).toFixed(3);
                }
            }
        </script>
        <script></script>
    </head>
    <body onload="">
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        <form action="agentPaymetGetInfoController" id="getInfoForm" method="POST" style="font-weight: bold">
            <font style="color: red; font-size: 15px;">${ErrorCode}</font>
            <input type="hidden" name="companyCode" id="companyCode" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>"/>
            <input type="hidden" name="valueFees" id="valueFees" />
            <table style="width: auto ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <td  style="text-align: center"><%=CONFIG.getAgentPaymentInfo(session)%></td>
                    <td  style="text-align: center"><%=CONFIG.getAgentPaymentComputeCommission(session)%></td>
                </tr>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <% if (companyCode.equals("18235")) {%>
                                    <p><%=CONFIG.getCustomerCode(session)%>:</p>
                                    <% } else {%>
                                    <p><%=CONFIG.getRepresentativeCode(session)%>:</p>
                                    <% }%>
                                </td>
                                <td style="border: none ; background-color: transparent">

                                    <div class="requriedclass">
                                        <div >
                                            <input  type="text"   style="padding-left: auto"  name="repCode" id="repCode"autocomplete="on" maxlength="100" onblur="validateRepCode()"  >
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.getAgentPaymentAmountAr(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color:  transparent">
                                    <div class="requriedclass">
                                        <div >
                                            <input style="padding-left: auto" name="amount" id="amount" value=""  title="يجب ادخال المبلغ" autofocus="true" autocomplete="on" maxlength="6" type="text"   onblur="validateAmount(), calclulateCommession()" >

                                        </div>
                                    </div>
                                    <div id="AmountMessage">

                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.getAgentPaymentConfirmAmount(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color: transparent">
                                    <div class="requriedclass">
                                        <div >
                                            <input style="padding-left: auto" name="amountConfirom" id="amountConfirom"  title="يجب تأكيد المبلغ" autofocus="true" autocomplete="on" maxlength="6" type="text" onblur="validateAmountConfirmation()" >
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table>
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.getAgentPaymentCommission(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color: transparent">
                                    <input readonly style="padding-left: auto;background-color: #EDEDED" name="commission" readonly id="commission"  autocomplete="off" maxlength="100" type="text">
                                </td>
                            </tr>
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.getAgentPaymentWillDeducted(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color: transparent">
                                    <input  style="padding-left: auto;background-color: #EDEDED" name="payedAmount" id="payedAmount" readonly autocomplete="off" maxlength="100" type="text" >
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td  style="text-align: center">
                        <input type="submit"  onclick="return validateBeforeSubmit()" value="<%=CONFIG.getpayment(session)%>" name="payButton"  id="payButton" />
                    </td>
                    <td  style="text-align: center" >
                        <input type="button" value="الغاء"  id="submitbutton" onclick="window.location.href = '<%=rolePage%>';"  />
                    </td>
                </tr>
            </table>
        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>