<%--
    Document   : bill_info
    Created on : 01/05/2012, 07:11:19 م
    Author     : Michael
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
    MasaryManager.logger.info("BillInquiry_Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    // MasaryManager manager=new MasaryManager();
%>
<%
    session = request.getSession();
    String custId = null;
    if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(request.getSession().getAttribute("serv_id").toString());
//    //System.out.println(BTC.getBILL_TYPE_ID());
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = MasaryManager.getInstance().getAgent(custId);
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(String.valueOf(BTC.getBILL_TYPE_ID()), custId);
    List<Integer> values = MasaryManager.getInstance().getAmounts(BTC.getBILL_TYPE_ID());
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <title><%=CONFIG.getTELECOMEGYPT_Resharge(session)%></title>
        <script>
            function onValueChanged() {
                var Amount = document.getElementById("custAmountID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
                if (!isNaN(Amount.value)) {
                    Commession.value = (<%= ratePlan.getFixedAmount()%> + ((Number(Amount.value) * <%= ratePlan.getCommission()%>) / 100)).toFixed(1);
                    Balance_Diff.value = (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>).toFixed(1);
                    Fees.value = (<%= ratePlan.getApplied_fees()%> + Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100).toFixed(1);
                    DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value)).toFixed(1);
                } else {
                    Commession.value = "0";
                    Balance_Diff.value = "0"
                    Fees.value = "0";
                    DeductedAmount.value = "0";
                }
                if (Number(Amount.value) == 10) {
                    document.getElementById("Quata").value = "1";
                } else if (Number(Amount.value) == 25) {
                    document.getElementById("Quata").value = "5";
                } else if (Number(Amount.value) == 60) {
                    document.getElementById("Quata").value = "15";
                } else if (Number(Amount.value) == 75) {
                    document.getElementById("Quata").value = "25";
                } else {
                    document.getElementById("Quata").value = "0";
                }
            }
        </script>

    </head>
    <body class="body" onload="onValueChanged();">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return checkPhoneWithAreaCode()">

        <input type="hidden" name="action" value="<%=CONFIG.ACTION_Get_Recharge_Inquiry_Res%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="${serv_id}" />
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>   

        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td> 
                            <p align="right"><%=BTC.get_BILL_LABLE(session)%> : <input id="custTopUpMobileId" type="text" required name="<%=CONFIG.PARAM_MSISDN%>" tabindex="2"></p>
                            <p align="right"><%=CONFIG.getConfirm(request.getSession())%> <%=CONFIG.getPhoneNumber(session)%> :<input id="custMobileConfirmation" type="text" name="BILLING_ACCOUNT" style="margin-right:70px;" required="" tabindex="3">
                            <div id="custMobileConfirmationDiv" name="custMobileConfirmationDiv"></div></p>
                            <p align="right"><%=CONFIG.get_E_amount(session)%> : 
                                <select onchange="onValueChanged();"  style="margin-right:40px;" id="custAmountID" name="<%=CONFIG.MONEY_PAID%>"  >
                                    <option ><%=CONFIG.getSelectAmount(session)%></option>
                                    <% for (int i = 0; i < values.size(); i++) {%>
                                    <option   ><%=values.get(i)%></option>
                                    <%}%>
                                </select>
                            </p>
                            <p align="right"><%=CONFIG.getQuota(session)%> : <input type="text" name="<%=CONFIG.QUOTA%>" readonly value="0"  tabindex="1" id="Quata" style="width:80px"  />&nbsp<%=CONFIG.getGigaByte(session)%>&nbsp</p>
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="7" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="8" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </td>
                    </tr>
                    <tr><td colspan="2"><p align="right"><%=CONFIG.getAddetionalInfo(session)%> : <input type="text" name="ADDITIONAL_INFO" value="${bill_Response.ADDITIONAL_INFO}"  readonly tabindex="7" id="${bill_Response.ADDITIONAL_INFO}" style="background-color: #EDEDED; float: left; width: 80%"/></p></td></tr>

                    <tr>
                        <td >
                            <p align="right"><%=CONFIG.getNotifyMobile(session)%> : <input type="text" name="<%=CONFIG.NotifyMobile%>" tabindex="1" value="" id="NotifyMobile" /><div id="NotifyMobileDiv" style="color: red; font-size: 12.5px;"></div></p></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn"  >
                            <!--<input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">-->
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
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>