<%--
    Document   : bill_info
    Created on : 01/05/2012, 07:11:19 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
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
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(session.getAttribute("serv_id").toString(), agent.getPin());
    DonationAgentPaymentRespponseDto bill_response = (DonationAgentPaymentRespponseDto) session.getAttribute("bill_Response");
    ServiceDTO serviceValues = MasaryManager.getInstance().getService(request.getSession().getAttribute("serv_id").toString());
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <title><%=CONFIG.getCustomerBillHead(session)%>&nbsp;<%= serviceValues.getStrServiceName(session)%></title>
        <script>
            function onValueChanged() {
                var Amount = document.getElementById("custAmountID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
                Commession.value = (<%= ratePlan.getFixedAmount()%> + (Number(Amount.value) * <%= ratePlan.getCommission()%> / 100)).toFixed(1);
                Balance_Diff.value = (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>).toFixed(1);
                Fees.value = (<%= ratePlan.getApplied_fees()%> + (Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100)).toFixed(1);
                DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value)).toFixed(1);
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

    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return validateBillBayCredit(1)">

        <input type="hidden" name="action" value="<%=CONFIG.ACTION_AgentPayment_Inquiry%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="${serv_id}" />
        <input type="hidden" name="Fees" value="${bill_Response.FEES}"/>

        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=CONFIG.getCustomerBillHead(session)%>&nbsp;<%=serviceValues.getStrServiceName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td>

                            <p align="right"><%=CONFIG.getBillRefNumORCN(session)%> <%=CONFIG.getBillSecretCode(session)%> : 
                                <input type="text" name="AGENT_IDENTIFIER" readonly tabindex="1" id="AGENT_IDENTIFIER" value="${bill_Response.AGENT_IDENTIFIER}" style="background-color: #EDEDED; float: left;"/>
                            </p>

                            <p align="right"><%=CONFIG.getAssign_v(session)%> : 
                                <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="${bill_Response.AMOUNT}"  onchange="onValueChanged();" readonly style='background-color: #EDEDED; float: left;'>
                            </p>
                            <p align="right"><%=CONFIG.getcodeDetails(session)%> : 
                                <input type="text" name="CODE_DETAILS" readonly tabindex="1" id="CODE_DETAILS" value="${bill_Response.CODE_DETAILS}" style="background-color: #EDEDED; float: left;"/>
                            </p>         
                                
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td><%=CONFIG.getAllowedBalance(session)%> : <%= myFormatter.format(customerBalance)%></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4" value="<%=CONFIG.getpayment(session)%>" class="Btn" <%=customerBalance == 0 || (customerBalance < bill_response.getAMOUNT()) || bill_response.getAMOUNT() <= 0 ? "disabled='true'" : " "%> >
                            <input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	تأكد جيدا من البيانات المعروضة امامك. 
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