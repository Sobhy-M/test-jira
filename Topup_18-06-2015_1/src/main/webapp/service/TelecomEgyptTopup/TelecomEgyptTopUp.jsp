<%@page import="com.masary.utils.BuildUUID"%>
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

    BuildUUID build_uuid =new BuildUUID ();
    String uuid =build_uuid.CreateUUID();
//    session.setAttribute("uuid", uuid);
    
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double vodafonBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 10);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
   
   
    uuid = uuid + request.getParameter("SERVICE_ID") ;
    session.setAttribute("uuid", uuid) ;
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getCustomerTelecomTopUp(request.getSession())%></title>
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
        <script>
            var ajaxFlag = false;
            $(window).load(LoadTaxValue);
            function LoadTaxValue() {
                console.log(<%=request.getParameter("SERVICE_ID")%>);
                var urlStr = 'RatePlanCommissions';

                $.ajax({
                    url: urlStr,
                    data: {
                        SERVICE_ID: <%=request.getParameter("SERVICE_ID")%>,
                        CUSTOMER_ID: <%=agent.getPin()%>,
                        AMOUNT: $('#custTopUpDalanceID').val()
                    },
                    type: 'get'
                }).done(function (responseData) {
                    console.log("done" + ajaxFlag);
                    ajaxFlag = true;
                    if (responseData !== "") {
                        console.log("done" + responseData);
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
            }
//            function AjaxRequestReturn()
//            {
//                if (!ajaxFlag)
//                {
//                    window.location.replace("error.jsp");
//                    return false;
//                }
//                return true;
//            }

//            function checkLength() {
//                var phone = document.getElementById("custTopUpMobileId").value;
//                var phoneConfirm = document.getElementById("custMobileConfirmation").value;
//                if (phone.length !== 11) {
//                    document.getElementById("custTopUpMobileDiv").innerHTML = 'برجاء كتابة رقم الهاتف بشكل صحيح';
//                    document.getElementById("custMobileConfirmationDiv").innerHTML = '';
//                    return false;
//                } else if (phoneConfirm.length !== 11) {
//                    document.getElementById("custTopUpMobileDiv").innerHTML = '';
//                    document.getElementById("custMobileConfirmationDiv").innerHTML = 'برجاء كتابة تاكيد الرقم بشكل صحيح';
//                    return false;
//                } else {
//                    return true;
//                }
//
//
//            }

            function checkEquality() {
                var phone = document.getElementById("custTopUpMobileId").value;
                var phoneConfirm = document.getElementById("custMobileConfirmation").value;

                if (phone !== phoneConfirm) {
                    document.getElementById("custMobileConfirmationDiv").innerHTML = 'برجاء التاكد من تطابق رقمى الهاتف';
                    document.getElementById("custTopUpMobileDiv").innerHTML = '';
                    return false;
                } else {
                    document.getElementById("custTopUpMobileDiv").innerHTML = '';
                    document.getElementById("custMobileConfirmationDiv").innerHTML = '';
                    return true;
                }
            }


            function checkAmount() {
                var amount = document.getElementById("custTopUpDalanceID").value;

                if (amount % 1 !== 0) {

                    document.getElementById("custTopUpAmountDiv").innerHTML = 'برجاء ادخال القيمة بدون كسور';
                    return false;
                }
                if (amount > 250) {
                    document.getElementById("custTopUpAmountDiv").innerHTML = 'برجاء العلم ان الحد الاقصى هو 250 جنيه';
                    return false;
                }
                if (amount < 1) {
                    document.getElementById("custTopUpAmountDiv").innerHTML = 'برجاء العلم ان الحد الادنى هو جنيه';
                    return false;
                } else {
                    document.getElementById("custTopUpAmountDiv").innerHTML = '';
                    return true;
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
        <form name="BayCreditCustomer" action="TelecomEgyptTopupConf" method="POST" onsubmit="return checkEquality();">
            <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>"  id="<%=CONFIG.TOPUP_TYPE%>" value="<%=request.getParameter("SERVICE_ID")%>" />
        <fieldset style="width: 70%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%=CONFIG.getCustomerTelecomTopUp(request.getSession())%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
            <table border="1" style="width: 100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><p align="right"><%=CONFIG.getAmount(request.getSession())%> : <input type="text" name="<%=CONFIG.AMOUNT%>"  required tabindex="1" id="custTopUpDalanceID" onkeyup="LoadTaxValue();" onblur="return checkAmount();" pattern="(?:\d*\.)?\d+" style="float: left;" title="برجاء ادخال قيمة الشحن" autocomplete ="OFF" />
                        <div id="custTopUpAmountDiv"  style="color: #ff0000;font-size: 17px;"></div>
                        </p>
                        <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> : <input id="custTopUpMobileId" type="text" required name="<%=CONFIG.PARAM_MSISDN%>" autocomplete="off"  maxlength="11" title="برجاء ادخال رقم الموبايل صحيح" tabindex="2" style="float: left;">
                        <div id="custTopUpMobileDiv"  style="color: #ff0000;font-size: 17px;"></div></p>
                        <p align="right"><%=CONFIG.getConfirm(request.getSession())%> <%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custMobileConfirmation" autocomplete="off" maxlength="11" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  required tabindex="3" style="float: left;">
                        <div id="custMobileConfirmationDiv" style="color: #ff0000;font-size: 17px;"></div>
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
                    <td > <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getGo(request.getSession())%>" class="Btn" onclick="return checkAmount();"></td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(vodafonBalance)%></td>
                </tr>
            </table>
            <details open="open">
                <summary></summary>
                1-من فضلك إدخل المبلغ المراد شحنه ورقم الموبايل  </br>            
                2-                يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .</br></br>

                ملحوظة :- </br>
                - المبالغ التى يمكن شحنها فى موبايل المصرية للإتصالات يجب أن تكون مبالغ صحيحة بدون كسور و هى: من 1 ج إلى 250 جنيه بزيادة 1 جنيه مثل 2 ج و 3.
                - يجب كتابة رقم الموبايل بالكامل (11 رقم) مثل 01511111111. أرقام موبايل المصرية للإتصالات غالباً تبدأ بـ015  إذا كان رقم الموبايل المراد شحنه قد قام بتغيير الشبكة، يجب أن يتم شحنه بإستخدام الخدمة التى انتقل إليها . 
                - مثال إذا كنت تريد شحن رقم الموبايل 01221234567 و الذى انتقل إلى شبكة موبايل المصرية للإتصالات يجب الشحن بإستخدام خدمة شحن موبايل المصرية للإتصالات و ليس شحن موبينيل.

            </details>
        </fieldset> 
    </form>

</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

