<%@page import="com.masary.database.dto.VC_Commission_Structure"%>
<%@page import="com.masary.database.dto.COMMISSION"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.dto.Vodafone_Cash_Transactions"%>
<%@page import="com.masary.database.dto.Transaction"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
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
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getVodafone_Cash(session)%>-<%=CONFIG.getVodafone_cash_OUT(session)%></title>
        <%
            VC_Commission_Structure commission_Structure = MasaryManager.getInstance().VC_Get_Deducted_Amount();
//            MasaryManager.getInstance().setMasary_Money_Comm(commission_Structure, agent.getPin());
            List<COMMISSION> COMMISSIONS_list = commission_Structure.getCOMMISSIONS();
//            double Masary_Money_Comm = commission_Structure.getMM_COMM();
//            double Masary_Money_Fixed = commission_Structure.getMM_Fixed();
        %>
        <script>
            function onValueChanged_Check_out() {
                var Amount = document.getElementById("Amount_Check_out");
                var Fees = document.getElementById("Fees_Check_out");
                var Commession = document.getElementById("Commession_Check_out");
                var DeductedAmount = document.getElementById("DeductedAmount_Check_out");
            <%
                for (COMMISSION comm : COMMISSIONS_list) {

            %>
                if (<%=comm.getOPERATION_TYPE()%> > 1 && Number(Amount.value) <= <%=comm.getMAXIMUM()%> && Number(Amount.value) >=<%=comm.getMINIMUM()%>) {

                    if (<%=comm.getMERCHANT_SHARE_TYPE()%> > 0) {
                        Commession.value = ((Number(Amount.value) *<%=comm.getMERCHANT_SHARE()%>) / 100).toFixed(3);
//                        console.log(Amount.value);
//                        console.log(Commession.value);
                    } else {
                        Commession.value = (<%=comm.getMERCHANT_SHARE()%>).toFixed(3);
                    }
                    if (<%=comm.getFEES_TYPE()%> > 0) {
                        Fees.value = ((Number(Amount.value) *<%=comm.getFEES()%>) / 100).toFixed(3);
                    } else {
                        Fees.value = (<%=comm.getFEES()%>).toFixed(3);
//                        console.log(Fees.value);
                    }
                    DeductedAmount.value = (Number(Amount.value) - Number(Fees.value) + Number(Commession.value)).toFixed(3);
//                    console.log(Number(Amount.value) + Number(Fees.value));
                }

            <%}%>
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

                <form name="formElem" id="formElem" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST" onsubmit="return checkVodafoneCheck_outForm(this);">
                <fieldset style="width: 54%; direction: rtl;" align="right">  
                    <!--<details open="open">-->

                    <legend align="right" ><font size="5"><%=CONFIG.getVodafone_cash_OUT(session)%>&nbsp; </font><img src="img/CashOut.ico"  width="20" height="20" style="background: url('CashOut.ico') no-repeat scroll 100% 50% transparent;"> </legend> 
                    <input type="hidden" name="action" value="<%=CONFIG.CHECKOUT%>" />
                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                    <table border="1">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=CONFIG.getMerchantCommession(session)%></th>
                        <tr>
                            <td><p align="right"><%=CONFIG.getAmount(session)%> : <input type="text" name="<%=CONFIG.AMOUNT%>" required="" tabindex="1" id="Amount_Check_out" onchange="onValueChanged_Check_out();" style="float: left;" autocomplete ="OFF" />
                                <div id="custAmountDiv_Check_out" name="custAmountDiv"></div></p> 
                                <p align="right"><%=CONFIG.getMobileNumber(session)%> : <input id="custMobile_Check_out" type="text" name="<%=CONFIG.PARAM_MSISDN%>" required="" tabindex="2" style="float: left;">
                                <div id="custMobileDiv_Check_out"  ></div></p>
                                <p align="right"><%=CONFIG.getMobileNumberConfirmation(session)%> :<input id="custMobileConfirmation_Check_out" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  required="" tabindex="3" style="float: left;">
                                <div id="custMobileConfirmationDiv_Check_out" name="custMobileConfirmationDiv"></div></p></td>
                            <td>
                                <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees_Check_out" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession_Check_out" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getAddAmount(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount_Check_out" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                </br>
                            </td>
                        </tr>

                        <tr><td colspan="2"> <input type="submit" name="btnSubmit" tabindex="4" value="<%=CONFIG.getGo(session)%>" ></td></tr>
                    </table>
                    <details open="open">
                        <summary></summary>
                        

1-	من فضلك اكتب المبلغ المراد سحبه فى خانة المبلغ. </br>
2-	اكتب رقم الموبايل المراد السحب منه فى خانة رقم الموبايل . </br>
3-	اكتب رقم الموبايل مرة ثانية للتاكيد. </br>
4-	سوف يظهر لك فى الجهة اليسرى من الشاشة تكلفة خدمة السحب على العميل و عمولتك على مبلغ السحب بالنسبه المئويه    و الجنيه و المبلغ الذى سوف يضاف إلى الحساب لتنفيذ عملية السحب من موبايل العميل. </br>
5-	ثم اضغط على زرار التنفيذ. </br></br>
ملحوظة :- </br>
عند طلب عمليه سحب يحصل العميل على المبلغ كاملا (مثال : عند طلب عمليه سحب ب 100 جنيه يستلم العميل من التاجر ال 100 جنيه نقدا بالكامل)</br>
عمولة السحب للتاجر يتم أخذها من مصارى وليس من العميل. </br>

                    </details>
                </fieldset> 
                    
            </form>
        </center>
        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
    </div>
</body>
</html>

