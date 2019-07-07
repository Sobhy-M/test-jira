<%--
    Document   : bill_info
    Created on : 01/05/2012, 07:11:19 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Bill_Amounts"%>
<%@page import="com.masary.database.dto.Masary_Bill_Response"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
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
    double billBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1000);
    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(String.valueOf(BTC.getBILL_TYPE_ID()), agent.getPin());
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<%
    Masary_Bill_Response bill_Response = new Masary_Bill_Response();
    try {
        bill_Response = (Masary_Bill_Response) session.getAttribute("bill_Info");
    } catch (Exception e) {
    }
    double billamount = 0;
    String Cur = "";
    double fees = 0;
    String feesCur = "";
    for (Masary_Bill_Amounts amount : bill_Response.getAmounts()) {
        if (amount.getBILL_SUMM_AMT_CODE().equals("TotalAmtDue")) {
            billamount = amount.getCUR_AMOUNT();
            Cur = amount.getCUR_CODE();
        }
        if (amount.getBILL_SUMM_AMT_CODE().equals("Fees")) {
            fees = amount.getCUR_AMOUNT();
            feesCur = amount.getCUR_CODE();
        }
    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <title><%=BTC.getBILLER_ID() == 33 ? CONFIG.getUniv_BillHead(session) : CONFIG.getCustomerBillHead(session)%>&nbsp;<%= BTC.getStrBTCName(session)%></title>
        <script>
            function onValueChanged() {
                var Amount = document.getElementById("custAmountID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
                Commession.value = (<%= ratePlan.getFixedAmount()%> + (Number(Amount.value) * <%= ratePlan.getCommission()%> / 100)).toFixed(3);
                Balance_Diff.value = (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>).toFixed(3);
                Fees.value = (<%= ratePlan.getApplied_fees()%> + (Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100)).toFixed(3);
                DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value)).toFixed(3);
            }
        </script>
    </head>
    <body class="body" onload="onValueChanged();">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
    <%try {
             String Error = request.getSession().getAttribute("ErrorCode").toString();
%>
    <font style="color: red; font-size: 15px;"><%=request.getSession().getAttribute("ErrorCode").toString()%></font>
        <%
              request.getSession().removeAttribute("ErrorCode");
            } catch (Exception e) {
            }
        %>
    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return validateBillBayCredit(<%=BTC.isIS_FRAC_ACC()%>)">

        <input type="hidden" name="action" value="<%=CONFIG.ACTION_payment_Inquiry_Req_Old%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("serv_id").toString()%>" />
        <input type="hidden" name="ServiceBalance" value="<%=billBalance%>" id="ServiceBalance"/>
        <input type="hidden" name="Fees" value="<%=fees%>"/>
        <input type="hidden" name="LOWER" value="<%=bill_Response.getLOWER_AMOUNT()%>" />
        <input type="hidden" name="UPPER" value="<%=bill_Response.getUPPER_AMOUNT()%>" />
        <div class="content_body"  >
            <fieldset style="width: 60%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=BTC.getBILLER_ID() == 33 ? CONFIG.getUniv_BillHead(session) : CONFIG.getCustomerBillHead(session)%>&nbsp;<%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td><p align="right"><%=BTC.getBILLER_ID() == 33 ? CONFIG.getStudentName(session) : CONFIG.getCustomerName(session)%> : 
                                <%if (bill_Response.getCustomer_name().equals(null) || bill_Response.getCustomer_name().toLowerCase().contains("not available")) {
                                    } else {%>
                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=bill_Response.getCustomer_name()%>" style="background-color: #EDEDED; float: left;"/>
                                <%}%>
                            </p>
                            <p align="right"><%=CONFIG.getAssign_v(session)%> : 
                                <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%=billamount%>"  onchange="onValueChanged();" <%=BTC.isIS_ADV_ACC() || BTC.isIS_OVER_ACC() || BTC.isIS_PART_ACC() ? "" : "readonly style='background-color: #EDEDED; float: left;'"%> >
                            </p>
                            <p align="right"><%=BTC.get_BILL_LABLE(session)%> : <input type="text" name="BILLING_ACCOUNT" readonly tabindex="1" id="BILLING_ACCOUNT" value="<%=bill_Response.getBILLING_ACCOUNT()%>" style="background-color: #EDEDED; float: left;"/></p>
                                <%try {
                                        if (bill_Response.getDUE_DATE().equals(null) || bill_Response.getDUE_DATE().equals("2012-07-01")) {
                                        } else {
                                %>
                            <p align="right"><%=CONFIG.getDuDate(session)%> : <input type="text" name="DUE_DATE" readonly tabindex="1" id="DUE_DATE" value="<%=bill_Response.getDUE_DATE()%>" style="background-color: #EDEDED; float: left;"/></p>
                                <%}
                                    } catch (Exception c) {
                                    }%>
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <%=CONFIG.getPartial(session)%>&nbsp;<input type="checkbox" name="checkname" onClick="return false;" <%=BTC.isIS_PART_ACC() ? "checked" : ""%>>
                            &nbsp;<%=CONFIG.getOver(session)%>&nbsp;<input type="checkbox" name="checkname" onClick="return false;" <%=BTC.isIS_OVER_ACC() ? "checked" : ""%>>
                            &nbsp;<%=CONFIG.getFraction(session)%>&nbsp;<input type="checkbox" name="checkname" onClick="return false;" <%=BTC.isIS_FRAC_ACC() ? "checked" : ""%>>
                            &nbsp;<%=CONFIG.getAdvanced(session)%>&nbsp;<input type="checkbox" name="checkname" onClick="return false;" <%=BTC.isIS_ADV_ACC() ? "checked" : ""%>>
                        </td>
                    </tr>
                    <%
                        try {
                            if (!bill_Response.getAdditional_Info().equals(null)) {%>
                    <tr><td colspan="2"><p align="right"><%=CONFIG.getAddetionalInfo(session)%> : <%=bill_Response.getAdditional_Info()%></p></td></tr>
                    <%
                            }
                        } catch (Exception c) {
                        }%>

                    <tr>
                        <td >
                            <p align="right"><%=CONFIG.getNotifyMobile(session)%> : <input type="text" name="<%=CONFIG.NotifyMobile%>" tabindex="1" value="" id="NotifyMobile" /><div id="NotifyMobileDiv" style="color: red; font-size: 12.5px;"></div></p></td>
                        <td><%=CONFIG.getAllowedBalance(session)%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(billBalance)%></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn" <%=billBalance == 0 || (billBalance < billamount && !BTC.isIS_PART_ACC()) || !bill_Response.getBILL_STATUS().equals("BillUnpaid") || billamount <= 0 ? "disabled='true'" : " "%> >
                            <input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	تأكد أن رقم الموبايل الذى تم إدخاله صحيح . 
                </details>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>