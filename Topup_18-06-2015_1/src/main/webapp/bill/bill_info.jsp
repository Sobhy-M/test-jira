<%--
    Document   : bill_info
    Created on : 01/05/2012, 07:11:19 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
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
    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(String.valueOf(BTC.getBILL_TYPE_ID()), agent.getPin());
    Bill_Response bill_response = (Bill_Response) session.getAttribute("bill_Response");
    double billBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 1000);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
    String commFees = "";
    if (BTC.getBILL_TYPE_ID() == 117||BTC.getBILL_TYPE_ID() == 111||BTC.getBILL_TYPE_ID() == 112) {
   // if (BTC.getBILL_TYPE_ID() == 117) {
        commFees = session.getAttribute("commFees").toString();
//        //System.out.println("rrrrrr" + commFees);
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
                var commFeeValue = '<%=commFees%>';
                if (<%=request.getParameter("SERVICE_ID")%> === 117|| <%=request.getParameter("SERVICE_ID")%> === 112 ||<%=request.getParameter("SERVICE_ID")%> === 111 ) {
                    var arr = commFeeValue.split('-');
                    Fees.value = arr[1];
                    Commession.value = arr[0];
                    Balance_Diff.value = arr[1] - arr[0];
                    DeductedAmount.value = Number(Amount.value) + Number(Balance_Diff.value);
                } else {
                    Commession.value = (<%= ratePlan.getFixedAmount()%> + (Number(Amount.value) * <%= ratePlan.getCommission()%> / 100)).toFixed(1);
                    Balance_Diff.value = (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>).toFixed(1);
                    Fees.value = (<%= ratePlan.getApplied_fees()%> + (Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100)).toFixed(2);
                    DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value)).toFixed(2);
                }
            }
        </script>
    </head>
    <body class="body" onload="onValueChanged();">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return validateBillBayCredit(<%=BTC.isIS_FRAC_ACC()%>)">

        <input type="hidden" name="action" value="<%=CONFIG.ACTION_payment_Inquiry_Req%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="${serv_id}" />
        <input type="hidden" name="ServiceBalance" value="<%=billBalance%>" id="ServiceBalance"/>
        

        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=(BTC.getBILL_TYPE_ID() == 55551 || BTC.getBILL_TYPE_ID() == 55552 || BTC.getBILL_TYPE_ID() == 55555) ? "" : CONFIG.getCustomerBillHead(session)%>&nbsp;<%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td>
                            <% if (BTC.getBILL_TYPE_ID() != 112 && BTC.getBILL_TYPE_ID() != 111 && BTC.getBILL_TYPE_ID() != 55551 && BTC.getBILL_TYPE_ID() != 55552) {
                            %>
                            <p align="right"><%=(BTC.getBILL_TYPE_ID() == 55551 || BTC.getBILL_TYPE_ID() == 55552 || BTC.getBILL_TYPE_ID() == 55555) ? CONFIG.getStudentName(session) : CONFIG.getCustomerName(session)%> : 

                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="${bill_Response.CUSTOMER_NAME}" style="background-color: #EDEDED; float: left;"/>

                            </p>
                            <%}
                                if (BTC.getBILL_TYPE_ID() == 55551 || BTC.getBILL_TYPE_ID() == 55552) {%>
                            <p align="right"><%=CONFIG.getAssign_v(session)%> : 
                                <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value=""  onchange="onValueChanged();" <%=BTC.isIS_ADV_ACC() || BTC.isIS_OVER_ACC() || BTC.isIS_PART_ACC() ? " style='float: left;'" : " style='background-color: #EDEDED; float: left;'"%> >
                            </p>

                            <%} else {%>
                            <p align="right"><%=CONFIG.getAssign_v(session)%> : 
                                <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="${bill_Response.AMOUNT}"  onchange="onValueChanged();" <%=BTC.isIS_ADV_ACC() || BTC.isIS_OVER_ACC() || BTC.isIS_PART_ACC() ? " style='float: left;'" : "readonly style='background-color: #EDEDED; float: left;'"%> >
                            </p><%}%>
                            <p align="right"><%=BTC.get_BILL_LABLE(session)%> : <input type="text" name="BILLING_ACCOUNT" readonly tabindex="1" id="BILLING_ACCOUNT" value="${bill_Response.BILLING_ACCOUNT}" style="background-color: #EDEDED; float: left;"/></p>         
                                <% if (BTC.getBILL_TYPE_ID() == 117) {
                                %>
                            <p align="right"><%=CONFIG.getExDate(session)%> : <input type="text" name="DUE_DATE" readonly tabindex="1" id="DUE_DATE" value="${bill_Response.BILL_DATE}" style="background-color: #EDEDED; float: left;"/></p>                            
                                <%
                                } else {
                                %>    
                                <%if (BTC.getBILL_TYPE_ID() != 112 && BTC.getBILL_TYPE_ID() != 111 && BTC.getBILL_TYPE_ID() != 55551 && BTC.getBILL_TYPE_ID() != 55552) {%>
                            <p align="right"><%=CONFIG.getDuDate(session)%> : <input type="text" name="DUE_DATE" readonly tabindex="1" id="DUE_DATE" value="${bill_Response.BILL_DATE}" style="background-color: #EDEDED; float: left;"/></p>
                                <%}%>
                                <%if (BTC.getBILL_TYPE_ID() == 99007) {%>
                            <p align="right"><%=CONFIG.getAddress1(session)%> : <input type="text" name="Adtress" readonly tabindex="1" id="DUE_DATE" value="${bill_Response.ADDRESS}" style="background-color: #EDEDED; float: left;"/></p>
                                <%}
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
                    <tr><td colspan="2"><p align="right"><%=CONFIG.getAddetionalInfo(session)%> : <input type="text" name="ADDITIONAL_INFO" value="${bill_Response.ADDITIONAL_INFO}"  readonly tabindex="7" id="${bill_Response.ADDITIONAL_INFO}" style="background-color: #EDEDED; float: left; width: 80%"/></p></td></tr>


                    <tr>
                        <td >
                            <p align="right"><%=CONFIG.getNotifyMobile(session)%> : <input type="text" name="<%=CONFIG.NotifyMobile%>" tabindex="1" value="" id="NotifyMobile" /><div id="NotifyMobileDiv" style="color: red; font-size: 12.5px;"></div></p></td>
                        <td><%=CONFIG.getAllowedBalance(session)%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(billBalance)%></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <% if (BTC.getBILL_TYPE_ID() == 55551 || BTC.getBILL_TYPE_ID() == 55552 || BTC.getBILL_TYPE_ID() == 55555) {
                            %>
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn"  >

                            <%} else {%><input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn" <%=billBalance == 0 || (billBalance < bill_response.getAMOUNT() && !BTC.isIS_PART_ACC()) || bill_response.getAMOUNT() <= 0 ? "disabled='true'" : " "%> ><%}%>
                            <input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	تأكد أن رقم التليفون الذى تم إدخاله صحيح . 
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