<%@page import="com.masary.integration.TopupServiceIntegrationStatus"%>
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
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double etisalatBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 6);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
    request.setAttribute("etisalatIntegrationStatus", TopupServiceIntegrationStatus.etisalatserviceStatus);
    request.setAttribute("topupType", request.getAttribute("topupType"));
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
        <title><%=CONFIG.getCustomerEtisalatTopUp(request.getSession())%></title>
        <script>
            var ajaxFlag = false;
            $(window).load(LoadTaxValue);

            function LoadTaxValue() {
                console.log(<%=request.getParameter("SERVICE_ID")%>);
                var urlStr;
                if (<%=request.getAttribute("vodafoneIntegrationStatus")%>) {
                    console.log(<%=request.getAttribute("vodafoneIntegrationStatus")%>);
                    urlStr = 'RatePlanCommissions';
                } else {
                    urlStr = 'RatePlanServiceInformation';
                }
                $("#custTopUpDalanceID").on('change', function postinput() {
                    $.ajax({
                        url: urlStr,
                        data: {
                            SERVICE_ID: <%=request.getParameter("SERVICE_ID")%>,
                            CUSTOMER_ID: <%=agent.getPin()%>,
                            AMOUNT: $('#custTopUpDalanceID').val(),
                            TopupAmount: $('#TopupDenmo').val()
                        },
                        type: 'get'
                    }).done(function (responseData) {
                        ajaxFlag = true;
                        console.log("done :- " + responseData);
                        if (responseData !== "") {
                            var arr = responseData.split('-');
                            $('#Fees').val(arr[6]);
                            $('#Commession').val(arr[3]);
                            $('#servicetax').val(arr[7]);
                            $('#DeductedAmount').val(arr[8]);
                            $('#UserAmount').val(arr[11]);
                            $('#Balance_Diff').val(Number(arr[10]) + Number(arr[8]) * arr[6]);
                        } else {
                            ajaxFlag = false;
                            return;
                        }
                    }).fail(function () {
                        ajaxFlag = false;
                        return;
                    });
                });

            }
            function LoadselectTaxValue(id) {
                console.log(<%=request.getParameter("SERVICE_ID")%>);
                console.log(id);
                var urlStr;
                if (<%=request.getAttribute("vodafoneIntegrationStatus")%>) {
                    console.log(<%=request.getAttribute("vodafoneIntegrationStatus")%>);
                    urlStr = 'RatePlanCommissions';
                } else {
                    urlStr = 'RatePlanServiceInformation';
                }
                $.ajax({
                    url: urlStr,
                    data: {
                        SERVICE_ID: <%=request.getParameter("SERVICE_ID")%>,
                        CUSTOMER_ID: <%=agent.getPin()%>,
                        AMOUNT: $('#custTopUpDalanceID').val(),
                        TopupAmount: $('#TopupDenmo').val()
                    },
                    type: 'get'
                }).done(function (responseData) {
                    console.log("done" + ajaxFlag);
                    ajaxFlag = true;
                    if (responseData !== "") {
                        console.log("done :- " + responseData);
                        var arr = responseData.split('-');
                        $('#Fees').val(arr[6]);
                        $('#Commession').val(arr[3]);
                        $('#servicetax').val(arr[7]);
                        $('#DeductedAmount').val(arr[8]);
                        $('#UserAmount').val(arr[11]);
                        $('#Balance_Diff').val(Number($('#custTopUpDalanceID').val()) + Number(arr[3]));
                    } else {
                        ajaxFlag = false;
                        console.log("else" + ajaxFlag);
                        return;
                    }
                }).fail(function () {
                    ajaxFlag = false;
                    console.log("fail" + ajaxFlag);
                    console.log(ajaxFlag);
                    return;
                });
//                });

            }
            function AjaxRequestReturn()
            {
                if (!ajaxFlag)
                {
                    window.location.replace("error.jsp");
                    return false;
                }
                return true;
            }
            function onValueChanged() {
                var Amount = document.getElementById("custTopUpDalanceID");
                if (Amount.value === "5") {
                    document.getElementById("custTopUpbalanceDiv").style.display = 'block';
                } else {
                    document.getElementById("custTopUpbalanceDiv").style.display = 'none';
                }
                if (Amount.value === "10") {
                    document.getElementById("custTopUpbalanceDiv2").style.display = 'block';
                } else {
                    document.getElementById("custTopUpbalanceDiv2").style.display = 'none';
                }
                if (Amount.value === "15") {
                    document.getElementById("custTopUpbalanceDiv3").style.display = 'block';
                } else {
                    document.getElementById("custTopUpbalanceDiv3").style.display = 'none';
                }
                if (Amount.value === "25") {
                    document.getElementById("custTopUpbalanceDiv4").style.display = 'block';
                } else {
                    document.getElementById("custTopUpbalanceDiv4").style.display = 'none';
                }
                if (Amount.value === "7") {
                    document.getElementById("custTopUpbalanceDiv5").style.display = 'block';
                } else {
                    document.getElementById("custTopUpbalanceDiv5").style.display = 'none';
                }
                document.getElementById("TopupDenmo").value = document.getElementById("custTopUpDalanceID").value;
            }
            function onselectbutton(id) {
                if (id === '5' || id === '5.00') {
                    document.getElementById("TopupDenmo").value = "5";
                    LoadselectTaxValue(id);
                } else if (id === '5.01') {
                    document.getElementById("TopupDenmo").value = "5.01";
                    LoadselectTaxValue(id);
                } else if (id === '5.02') {
                    document.getElementById("TopupDenmo").value = "5.02";
                    LoadselectTaxValue(id);

                } else if (id === '10' || id === '10.00') {
                    document.getElementById("TopupDenmo").value = "10";
                    LoadselectTaxValue(id);
                } else if (id === '10.01') {
                    document.getElementById("TopupDenmo").value = "10.01";
                    LoadselectTaxValue(id);
                } else if (id === '10.02') {
                    document.getElementById("TopupDenmo").value = "10.02";
                    LoadselectTaxValue(id);
                } else if (id === '15' || id === '15.00') {
                    document.getElementById("TopupDenmo").value = "15";
                    LoadselectTaxValue(id);
                } else if (id === '15.01') {
                    document.getElementById("TopupDenmo").value = "15.01";
                    LoadselectTaxValue(id);
                } else if (id === '15.02') {
                    document.getElementById("TopupDenmo").value = "15.02";
                    LoadselectTaxValue(id);
                } else if (id === '25' || id === '25.00') {
                    document.getElementById("TopupDenmo").value = "25";
                    LoadselectTaxValue(id);
                } else if (id === '25.01') {
                    document.getElementById("TopupDenmo").value = "25.01";
                    LoadselectTaxValue(id);
                } else if (id === '25.02') {
                    document.getElementById("TopupDenmo").value = "25.02";
                    LoadselectTaxValue(id);
                }
                else if (id === '7' || id === '7.00') {
                    document.getElementById("TopupDenmo").value = "7";
                    LoadselectTaxValue(id);
                } else if (id === '7.01') {
                    document.getElementById("TopupDenmo").value = "7.01";
                    LoadselectTaxValue(id);
                } else if (id === '7.02') {
                    document.getElementById("TopupDenmo").value = "7.02";
                    LoadselectTaxValue(id);
                }
            }
        </script>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>  

        <form name="BayCreditCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="POST" onsubmit="return validateBayCreditCustomer()">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_TOPUP%>" />
        <input type="hidden" name="TopupDenmo" id="TopupDenmo" />
        <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=request.getParameter("SERVICE_ID")%>" />
        <fieldset style="width: 70%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%=CONFIG.getCustomerEtisalatTopUp(request.getSession())%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
            <table border="1" style="width: 100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>"  required="" tabindex="1" id="custTopUpDalanceID" onkeyup="onValueChanged();" pattern="(?:\d*\.)?\d+" style="float: left;" autocomplete ="OFF" />
                        <div id="custTopUpbalanceDiv" style="display: none">

                            <input type="radio" name="custAmountValueDiv" id="5.00" onclick="onselectbutton(this.id);" />رصيد
                            <P ><b> -> أقوى كارت في مصر: </b></p>
                            <input type="radio" name="custAmountValueDiv" id="5.01" onclick="onselectbutton(this.id);" />باقة ميكس
                            <input type="radio" name="custAmountValueDiv" id="5.02" onclick="onselectbutton(this.id);" />باقة دقائق

                        </div>
                        <div id="custTopUpbalanceDiv2" style="display: none">

                            <input type="radio" name="custAmountValueDiv" id="10.00" onclick="onselectbutton(this.id);" />رصيد
                            <P ><b> -> أقوى كارت في مصر: </b></p>
                            <input type="radio" name="custAmountValueDiv" id="10.01" onclick="onselectbutton(this.id);" />باقة ميكس
                            <input type="radio" name="custAmountValueDiv" id="10.02" onclick="onselectbutton(this.id);" />باقة دقائق

                        </div>
                        <div id="custTopUpbalanceDiv3" style="display: none">

                            <input type="radio" name="custAmountValueDiv" id="15.00" onclick="onselectbutton(this.id);" />رصيد
                            <P ><b> -> أقوى كارت في مصر: </b></p>
                            <input type="radio" name="custAmountValueDiv" id="15.01" onclick="onselectbutton(this.id);" />باقة ميكس
                            <input type="radio" name="custAmountValueDiv" id="15.02" onclick="onselectbutton(this.id);" />باقة دقائق

                        </div>
                        <div id="custTopUpbalanceDiv4" style="display: none">

                            <input type="radio" name="custAmountValueDiv" id="25.00" onclick="onselectbutton(this.id);" />رصيد
                            <P ><b> -> أقوى كارت في مصر: </b></p>
                            <input type="radio" name="custAmountValueDiv" id="25.01" onclick="onselectbutton(this.id);" />باقة ميكس
                            <input type="radio" name="custAmountValueDiv" id="25.02" onclick="onselectbutton(this.id);" />باقة دقائق

                        </div>
                        <div id="custTopUpbalanceDiv5" style="display: none">

                            <input type="radio" name="custAmountValueDiv" id="7.00" onclick="onselectbutton(this.id);" />رصيد
                            <P ><b> -> أقوى كارت في مصر: </b></p>
                            <input type="radio" name="custAmountValueDiv" id="7.01" onclick="onselectbutton(this.id);" />باقة ميكس
                            <input type="radio" name="custAmountValueDiv" id="7.02" onclick="onselectbutton(this.id);" />باقة دقائق

                        </div>
                        <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> : <input id="custTopUpMobileId" maxlength="12" autocomplete="off" type="text" name="<%=CONFIG.PARAM_MSISDN%>" required="" tabindex="2" style="float: left;">
                        <div id="custTopUpMobileDiv"  ></div></p>
                        <p align="right"><%=CONFIG.getConfirm(request.getSession())%> <%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custMobileConfirmation" maxlength="12" autocomplete="off" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  required="" tabindex="3" style="float: left;">
                        <div id="custMobileConfirmationDiv" name="custMobileConfirmationDiv"></div></p>
                    </td>
                    <td>
                        <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getServiceTaxe(request.getSession())%> :<input type="text" name="servicetax" value="0"  readonly tabindex="6" id="servicetax" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        <p align="right"><%=CONFIG.getUserAmount(request.getSession())%> :<input type="text" name="UserAmount" value="0"  readonly tabindex="7" id="UserAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </br>
                    </td>
                </tr>
                <tr>
                    <td > <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getGo(request.getSession())%>" class="Btn" <%=etisalatBalance == 0 ? "disabled='true'" : " "%> onclick="return checkPhoneWithAreaCode()"></td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(etisalatBalance)%></td>
                </tr>
            </table>
            <details open="open">
                <summary></summary>
                1-	من فضلك ادخل المبلغ المراد شحنه و رقم الموبايل. </br>            
                2-                يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .</br></br>

                ملحوظة :- </br>
                •المبالغ التى يمكن شحنها فى إتصالات يجب أن تكون مبالغ صحيحة بدون كسور و هى: من 3 ج إلى 10 جنيه بزيادة 1 جنيه مثل 4 ج و 5 ج و هكذا ثم من 15 ج إلى 250 ج بزيادة 5 جنيه مثل 20 و 25 و 30 و هكذا.  	
                <br/>
                •يجب كتابة رقم الموبايل بالكامل (11 رقم) مثل 01112345678.
                أرقام موبايل فودافون غالباً تبدأ بـ011
                إذا كان رقم الموبايل المراد شحنه قد قام بتغيير الشبكة، يجب أن يتم شحنه بإستخدام الخدمة التى انتقل إليها.

                <br/>
                •مثال إذا كنت تريد شحن رقم الموبايل 01221234567 و الذى انتقل إلى شبكة إتصالات يجب الشحن بإستخدام خدمة شحن إتصالات و ليس شحن موبينيل.

            </details>
        </fieldset> 
    </form>
</div><!-- End of Table Content area-->


<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
