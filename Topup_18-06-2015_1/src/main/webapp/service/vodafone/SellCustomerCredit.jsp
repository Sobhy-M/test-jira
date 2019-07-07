

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double vodafonTransferBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 11);  
    RatePlanDTO ratePlan = (RatePlanDTO) request.getSession().getAttribute("ratePlan");
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getCustomerVodafoneTransfer(request.getSession())%></title>
        <script>
            function onValueChanged() {
                var Amount = document.getElementById("custTopUpDalanceID");
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
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="BayCreditCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="POST" onsubmit="return validateBayCreditCustomer()">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_TOPUP%>" />
        <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=request.getParameter("SERVICE_ID")%>" />
        <fieldset style="width: 55%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%=CONFIG.getCustomerVodafoneTransfer(request.getSession())%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
            <table border="1" width="100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>" required="" tabindex="1" id="custTopUpDalanceID" onchange="onValueChanged();" style="float: left;" autocomplete ="OFF" />
                        <div id="custTopUpDalanceDiv" name="custAmountDiv"></div></p> 
                        <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> : <input id="custTopUpMobileId" type="text" name="<%=CONFIG.PARAM_MSISDN%>" required="" tabindex="2" style="float: left;">
                        <div id="custTopUpMobileDiv"  ></div></p>
                        <p align="right"><%=CONFIG.getConfirm(request.getSession())%> <%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custMobileConfirmation" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  required="" tabindex="3" style="float: left;">
                        <div id="custMobileConfirmationDiv" name="custMobileConfirmationDiv"></div></p>
                    </td>
                    <td>
                        <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </br>
                    </td>
                </tr>
                <tr>
                    <td > <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getGo(request.getSession())%>" class="Btn" <%=vodafonTransferBalance == 0 ? "disabled='true'" : " "%> ></td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(vodafonTransferBalance)%></td>
                </tr>
            </table>
            <details open="open">
                <summary></summary>
                1-	من فضلك ادخل المبلغ المراد شحنه و رقم الموبايل. </br>            
                2-                يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .</br></br>

                ملحوظة :- </br>
                •	المبالغ التى يمكن شحنها فى فودافون يجب أن تكون مبالغ صحيحة بدون كسور و هى: من 1 ج إلى 10 جنيه بزيادة 1 جنيه مثل 2 ج و 3 ج و هكذا ثم من 15 ج إلى 200 ج بزيادة 5 جنيه مثل 20 و 25 و 30 و هكذا.  

            </details>
        </fieldset> 
    </form>
</div><!-- End of Table Content area-->
<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

