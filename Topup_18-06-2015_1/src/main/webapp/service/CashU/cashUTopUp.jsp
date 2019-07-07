<%-- 
    Document   : CashUTopUp
    Created on : Oct 2, 2014, 11:04:51 AM
    Author     : Aya
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.logging.Level"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.masary.database.dto.Product"%>
<%@page import="com.masary.database.dto.BlaBlaProducts_Res"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="com.masary.BlaBla_Manager"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.CurrencyConvertRate"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = MasaryManager.getInstance().getAgent(custId);
    RatePlanDTO ratePlan = (RatePlanDTO) request.getSession().getAttribute("ratePlan");

%>

<%
    CurrencyConvertRate coRate = new CurrencyConvertRate();
    double rate = coRate.SendRequestandConvert(1);

    List<Integer> values = MasaryManager.getInstance().getAmounts(ratePlan.getServiceID());

%>


<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <script type="text/javascript" src="../../img/CheckForms.js"></script>

        <title><%=CONFIG.getCustomerCashUTopUp(request.getSession())%></title>
        <script>
            function onValueChanged() {
                var am = document.getElementById("ENTERAM");
                var Amount = document.getElementById("select");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
                document.getElementById("custTopUpDalanceDiv").style.color = "red";
                if (document.getElementById("CustomerselectAmount").checked | document.getElementById("CustomerEnterAmount").checked) {
                    if (!isNaN(Amount.value)) {
                        Commession.value = (<%= ratePlan.getFixedAmount()%> + ((Number(Amount.value) * <%= ratePlan.getCommission()%>) / 100));
                        Balance_Diff.value = (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>);
                        Fees.value = (<%= ratePlan.getApplied_fees()%> + Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100);
                        DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value));

                    } else if (!isNaN(am.value)) {
                        Commession.value = (<%= ratePlan.getFixedAmount()%> + ((Number(am.value) * <%= ratePlan.getCommission()%>) / 100));
                        Balance_Diff.value = (Number(am.value) * <%=(ratePlan.getMasary_commission() / 100)%>);
                        Fees.value = (<%= ratePlan.getApplied_fees()%> + Number(am.value) * <%= ratePlan.getApplied_fees_per()%> / 100);
                        DeductedAmount.value = (Number(am.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value));
                        if (am.value < 0 || am.value > 850) {
                            document.getElementById("custTopUpDalanceDiv").innerHTML = "<br>المبلغ الأقصى فى العملية الواحدة 850 جنيه.</br>";
                            return false;
                        }
                        if (!IsInt(am.value)) {
                            document.getElementById("custTopUpDalanceDiv").innerHTML = "<br>من فضلك ادخل المبغ الصحيح.</br>";
                            return false;
                        }

                    }
                } else {
                    Commession.value = "0";
                    Balance_Diff.value = "0"
                    Fees.value = "0";
                    DeductedAmount.value = "0";
                    document.getElementById("custTopUpDalanceDiv").innerHTML = "<br>من فضلك قم باختيار او كتابة المبلغ المراد الشحن به </br>";
                    return false;
                }

            }
            function IsInt(n) {
                if (Number(n) === n || n % 1 === 0) {
                    return true;
                }
            }
            function selectAmount(id) {
                if (id == "ENTERAM") {
                    onValueChanged();
                    document.getElementById("select").disabled = true;
                    document.getElementById("CustomerEnterAmount").checked = true;
                } else {
                    document.getElementById("ENTERAM").disabled = true;
                    document.getElementById("CustomerselectAmount").checked = true;
                }

            }
            function onchangeInput(id) {
                if (id == "CustomerAccountChooseTest") {
                    document.getElementById("CustomerChooseAccount").checked = true;
                    document.getElementById("CustomerChooseEmailTest").disabled = true;
                    document.getElementById("CustomerChooseUserNameTest").disabled = true;
                } else if (id == "CustomerChooseEmailTest") {
                    document.getElementById("CustomerChooseEmail").checked = true;
                    document.getElementById("CustomerAccountChooseTest").disabled = true;
                    document.getElementById("CustomerChooseUserNameTest").disabled = true;

                }
                else {
                    document.getElementById("CustomerChooseUserName").checked = true;
                    document.getElementById("CustomerAccountChooseTest").disabled = true;
                    document.getElementById("CustomerChooseEmailTest").disabled = true;

                }
            }
            function changeChecked(id) {
                if (id == "CustomerChooseAccount") {
                    if (document.getElementById(id).checked) {
                        document.getElementById("CustomerChooseUserNameTest").disabled = true;
                        document.getElementById("CustomerChooseEmailTest").disabled = true;
                        document.getElementById("CustomerAccountChooseTest").disabled = false;
                        document.getElementById("CustomerAccountChooseTest").focus();
//                        document.getElementById("CustomerAccountChooseTest").checked=true;
                    }
                } else if (id == "CustomerChooseUserName") {
                    if (document.getElementById(id).checked) {
                        document.getElementById("CustomerAccountChooseTest").disabled = true;
                        document.getElementById("CustomerChooseEmailTest").disabled = true;
                        document.getElementById("CustomerChooseUserNameTest").disabled = false;
                        document.getElementById("CustomerChooseUserNameTest").focus();

                    }
                } else if (id == "CustomerChooseEmail") {
                    if (document.getElementById(id).checked) {
                        document.getElementById("CustomerAccountChooseTest").disabled = true;
                        document.getElementById("CustomerChooseUserNameTest").disabled = true;
                        document.getElementById("CustomerChooseEmailTest").disabled = false;
                        document.getElementById("CustomerChooseEmailTest").focus();

                    }
                }
                else if (id == "CustomerselectAmount") {
                    if (document.getElementById(id).checked) {
                        document.getElementById("ENTERAM").disabled = true;
                        document.getElementById("select").disabled = false;
                    }
                }
                else if (id == "CustomerEnterAmount") {
                    if (document.getElementById(id).checked) {
                        document.getElementById("select").disabled = true;
                        document.getElementById("ENTERAM").disabled = false;
                    }
                }

            }
        </script>
    </head>
    <body class="body"  >
        <!--    <BODY class="body">-->
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>   
    <form name="BayCreditCustomer" action="CashUTopUpController" method="POST" onsubmit="return onValueChanged();">
        <input type="hidden" name="action" value="<%=CONFIG.CashUTopUpConfirmation%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%= 40%>" />
        <input type="hidden" name="ServiceBalance" value="<%=agent.getServiceBalance(1000)%>" id="ServiceBalance"/>
<!--        <input type="hidden" name="<%=CONFIG.AMOUNTEGP%>" value="" />-->

        <fieldset style="width: 55%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%=CONFIG.getCustomerCashUTopUp(request.getSession())%></font> </legend> 
            <table border="1" width="100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td>
                        <p align="right"><input type="radio" onclick="changeChecked(this.id)"   name="radio"  id="CustomerChooseAccount"><%=CONFIG.getCustomerCashUNum(request.getSession())%> : <input id="CustomerAccountChooseTest" type="text"  name="<%=CONFIG.AccountCahsU%>" onkeydown="onchangeInput(this.id)" required=""  style="width: 103px; margin-right: 5px;" ></p>
                        <p align="right"><input type="radio" onclick="changeChecked(this.id)"  name="radio" id="CustomerChooseUserName"><%=CONFIG.getCustomerCashUName(request.getSession())%> <input id="CustomerChooseUserNameTest" type="text"  name="<%=CONFIG.UserNameCahsU%>" onkeydown="onchangeInput(this.id)" required=""  style="width: 105px;" ></p>
                        <p align="right"><input type="radio" onclick="changeChecked(this.id)"  name="radio" id="CustomerChooseEmail"><%=CONFIG.getEmailAddress(request.getSession())%> : <input id="CustomerChooseEmailTest" type="text"  name="<%=CONFIG.Email%>" required="" onkeydown="onchangeInput(this.id)" style="width: 105px; margin-right: 20px;" ></p>
                        <p align="right"><%=CONFIG.getAmount(request.getSession())%> :<input type="radio" id="CustomerselectAmount" name="amountselect" value="<%=CONFIG.getEnterAMOUNT(request.getSession())%>" onclick="changeChecked(this.id);">
                            <select id="select" onchange="onValueChanged();" onclick="selectAmount(this.id)" name="<%=CONFIG.AMOUNT%>" >
                                <option  ><%=CONFIG.getSelectAmount(request.getSession())%></option>
                                <%for (int i = 0; i < values.size(); i++) {%>
                                <option ><%=(int) Math.ceil(Math.ceil(values.get(i)))%></option>
                                <%}%>
                            </select> او
                            <input type="radio" value="<%=CONFIG.getEnterAMOUNT(request.getSession())%>"  name="amountselect" id="CustomerEnterAmount" onclick="changeChecked(this.id);"> 
                            <input type="text" id="ENTERAM"  placeholder="<%=CONFIG.getEnterAMOUNT(request.getSession())%>" name="<%=CONFIG.AMOUNTEGP%>" onkeydown="selectAmount(this.id)" style="width: 50px;" >
                        <div id="custTopUpDalanceDiv" name="custAmountDiv"></div>    </p>
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
                    <td > <input type="submit" name="btnSubmit" tabindex="4"   value="<%=CONFIG.getGo(request.getSession())%>" class="Btn" <%=agent.getServiceBalance(33) == 0 ? "disabled='true'" : " "%> ></td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(agent.getBalance()) : myFormatter.format(agent.getServiceBalance(33))%></td>
                </tr>
            </table>
            <details open="open"><summary>  </br> 
                    1-من فضلك ادخل احدي بيانات تعريف حسابك في كاش يو (رقم حسابك في كاش يو) او (اسم مستخدم حسابك في كاش يو) او (ايميل حسابك في كاش يو) حيث ان رقم حسابك في كاش يو يظهر علي جانب الشاشة بعد الدخول ويتكون من 16 رقم ويبدأإ برقم6276
                    </br>       2-في حالة كتابة ايميل حسابك في كاش يو يجب كتابه الايميل بالكامل
                    </br> 3-ثم اختر المبلغ الذي تريد شحنه بالجنيه المصري من الفئات الظاهرة امامك او اختر الاختيار (اكتب المبلغ) لتكتب المبلغ الذي تريد شحنه بالجنيه المصري .ودائما سيظهر لك في الشاشة الرصيد المكافيء له بالدولار  لهذا المبلغ المدخل بالجنيه المصري والذي سيتم شحنه لحسابك زثم اضغط زرار تنفيذ
                    </br></summary>
                يو  ملحوظة :ان اسعار رصيد كاش يو بالجنه المصري قد تختلف من يوم لاخر تبعا لسعر الصرف وتبعا لسياسه التسعير في كاش 
                </br>  
            </details>
        </fieldset> 
    </form>

</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

