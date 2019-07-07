<%@page import="com.masary.database.dto.LoginDto"%>
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
        DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
        AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
        double vfCashBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 2003);
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getVodafone_Cash(session)%>-<%=CONFIG.getVC_CashIn(session)%>-<%=CONFIG.getVC_CashIn_Confirmation(session)%></title>
        <script type="text/javascript">
            var ray = {
                ajax: function()
                {
                    document.getElementById("btnSubmit").disabled = true;
                    this.show('load');
                },
                hide: function()
                {
                    var load = Document.getElementById("load");
                    load.style = "display:none;";
                },
                show: function(el)
                {
                    this.getID(el).style.display = '';
                },
                getID: function(el)
                {
                    return document.getElementById(el);
                }
            }
        </script> 
    </head>
    <BODY class="body" onload="ray.hide();">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <div id="content">
            <center>
                <form name="formElem" id="formElem" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST" onsubmit="return ray.ajax()">
                <fieldset style="width: 60%; direction: rtl;" align="right">  

                    <legend align="right" ><font size="5"><%=CONFIG.getVC_CashIn_Confirmation(session)%>&nbsp; </font><img src="img/CashIn.ico"  width="20" height="20" style="background: url('CashIn.ico') no-repeat scroll 100% 50% transparent;"> </legend> 
                    <input type="hidden" name="action" value="<%=CONFIG.CHECKIN_CONFIRMATION%>" />
                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                    <table border="1" width="100%">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=CONFIG.getMerchantCommession(session)%></th>
                        <tr>
                            <td><p align="right"><%=CONFIG.getAmount(session)%> : <input type="text" name="<%=CONFIG.AMOUNT%>" readonly tabindex="1" id="custAmountID" value="<%= request.getParameter(CONFIG.AMOUNT)%>" style="background-color: #EDEDED; float: left;"/>
                                <div id="custAmountDiv" name="custAmountDiv"></div></p> 
                                <p align="right"><%=CONFIG.getMobileNumber(session)%> :<input id="custMobile" type="text" name="<%=CONFIG.PARAM_MSISDN%>" readonly value="<%= request.getParameter(CONFIG.PARAM_MSISDN)%>" tabindex="2" style="background-color: #EDEDED; float: left;">
                                <div id="custMobileDiv"  ></div></p></br>
                            </td>
                            <td>
                                <p align="right"><%=CONFIG.getFees(session)%> :<input type="text"   name="Fees" readonly tabindex="1" id="Fees"  value="<%= request.getParameter("Fees")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession"  readonly tabindex="1" id="Commession" value="<%= request.getParameter("Commession")%>" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text"  name="Balance_Diff" readonly tabindex="1" id="Balance_Diff" value="<%= request.getParameter("Balance_Diff")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text"  name="DeductedAmount" readonly tabindex="1" id="DeductedAmount" value="<%= request.getParameter("DeductedAmount")%>" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                </br>
                            </td>
                        </tr>
                        <tr><td>
                                <input type="submit" name="btnSubmit" id="btnSubmit" tabindex="3" value="<%=CONFIG.getConfirm(session)%>" style="float: right" >
                                <input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(request.getSession())%>"  style="float: left" onclick="history.go(-1);">
                            </td>
                            <td><%=CONFIG.getNewBalance(request.getSession())%> : <%=myFormatter.format(vfCashBalance - Double.parseDouble(request.getParameter("DeductedAmount")))%></td></tr>
                    </table>
                    <details open="open">
                        <summary></summary>
                        1-	سوف يظهر لك الرقم الذى تم إدخاله للإيداع له.  </br>
                        2-	سوف يظهر لك المبلغ المراد ايداعه . </br>
                        3-	و أيضاً سيظهر تكلفة الخدمة و هى (0) و عمولة الإيداع للتاجر الخاصة بالعملية و بالتالى المبلغ الذى سوف يتم خصمه من حساب التاجر للقيام بتلك العملية. </br>
                        4-	لتأكيد تنفيذ العملية، اضغط على زرار التاكيد أما للرجوع للتعديل اضغط على زرار الرجوع . </br></br>
                        ملحوظه :-  </br>
                        عمولة الإيداع يتم الحصول عليها من مصارى وليس من العميل بمعنى أن لكى يقوم العميل بإيداع مبلغ 100 جنيه مثلاً لن يقوم بدفع مبلغ أكثر من 100 جنيه بدون إضافة أى تكلفة خدمة.

                    </details>

                </fieldset> 
            </form>
        </center>
    </div>
    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

