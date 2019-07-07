<%-- 
    Document   : VC_CashIn
    Created on : Nov 4, 2013, 3:33:40 PM
    Author     : Aya
--%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.VC_Commission_Structure"%>
<%@page import="com.masary.database.dto.COMMISSION"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.dto.Vodafone_Cash_Transactions"%>
<%@page import="com.masary.database.dto.Transaction"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    double vfCashBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 2003);
    RatePlanDTO ratePlan = (RatePlanDTO) request.getSession().getAttribute("ratePlan");
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getVodafone_Cash(session)%>-<%=CONFIG.getVC_CashIn(session)%></title>
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
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div id="content">
            <center>
                <form name="formElem" id="formElem" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST" onsubmit="return checkVodafoneCashForm(this);">
                <fieldset style="width: 60%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%=CONFIG.getVC_CashIn(session)%>&nbsp; </font><img src="img/CashIn.ico"  width="20" height="20" style="background: url('CashIn.ico') no-repeat scroll 100% 50% transparent;"> </legend> 
                    <input type="hidden" name="action" value="<%=CONFIG.CHECKIN%>" />
                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                    <table border="1" width="100%">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=CONFIG.getMerchantCommession(session)%></th>
                        <tr>
                            <td><p align="right"><%=CONFIG.getAmount(session)%> : <input type="text" name="<%=CONFIG.AMOUNT%>" required="" tabindex="1" id="custAmountID" onchange="onValueChanged();" style="float: left;" autocomplete ="OFF" />
                                <div id="custAmountDiv" name="custAmountDiv"></div></p> 
                                <p align="right"><%=CONFIG.getMobileNumber(session)%> : <input id="custMobile" type="text" name="<%=CONFIG.PARAM_MSISDN%>" required="" tabindex="2" style="float: left;">
                                <div id="custMobileDiv"  ></div></p>
                                <p align="right"><%=CONFIG.getMobileNumberConfirmation(session)%> :<input id="custMobileConfirmation" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  required="" tabindex="3" style="float: left;">
                                <div id="custMobileConfirmationDiv" name="custMobileConfirmationDiv"></div></p>
                            </td>
                            <td>
                                <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                </br>
                            </td>
                        </tr>
                        <tr><td><input type="submit" name="btnSubmit" tabindex="4" value="<%=CONFIG.getGo(session)%>" ></td>
                            <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(vfCashBalance)%></td>
                        </tr>
                    </table>
                    <details open="open">
                        <summary></summary>
                        1-	من فضلك اكتب المبلغ المراد إيداعه فى خانة المبلغ. </br>
                        2-	اكتب رقم الموبايل المراد الإيداع له فى خانة رقم الموبايل.  </br>
                        3-	اكتب رقم الموبايل مره ثانيه للتاكيد.   </br>
                        4-	يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على مبلغ الإيداع  بالنسبه المئويه والجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك لإيداع المبلغ.</br></br>
                        ملحوظة :- </br>
                        •	عمولة الإيداع يتم الحصول عليها من مصارى وليس من العميل بمعنى أن لكى يقوم العميل بإيداع مبلغ 100 جنيه مثلاً لن يقوم بدفع مبلغ أكثر من 100 جنيه بدون إضافة أى تكلفة خدمة . </br>
                        •	الحد الأدنى للعملية الواحدة 5 جنيه و الحد الأقصى 3000 جنيه. 

                    </details>
                </fieldset> 
            </form>
        </center>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
    </div>
</body>
</html>

