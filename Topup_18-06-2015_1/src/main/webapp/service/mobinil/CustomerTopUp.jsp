<%@page import="java.util.ArrayList"%>
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
<%@page import="com.masary.integration.TopupServiceIntegrationStatus" %>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double mobinilBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), 8);
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
    request.setAttribute("orangeIntegrationStatus", TopupServiceIntegrationStatus.orangeserviceStatus);
    request.setAttribute("topupType", request.getAttribute("topupType"));
    ArrayList<Double> orangeDenominations = (ArrayList<Double>)session.getAttribute("orangeDenominations");
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getCustomerMobinilTopUp(request.getSession())%></title>
        <script type="text/javascript" src="img/jquery-1.11.3.min.js"></script>
        <script>
            var ajaxFlag = false;
            var canSubmit = true;
            $(window).load(function () {
                console.log(<%=request.getParameter("SERVICE_ID")%>);
                var urlStr;
                if (<%=request.getAttribute("orangeIntegrationStatus")%>) {
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
                            AMOUNT: $('#custTopUpDalanceID').val()
                        },
                        type: 'get'
                    }).done(function (responseData) {
                        ajaxFlag = true;
                        if(parseInt($('#custTopUpDalanceID').val(), 10) > 250){
                            $('#text1').show();
                            $('#text1').text("الحد الاقصى المسموح للشحن في العمليه الواحده هو 250");
                            $('#nearestLess').hide();
                            $('#nearestMore').hide();
                        }else{
                            if (responseData !== "") {
                                var arr = responseData.split('-');
                                $('#Fees').val(arr[6]);
                                $('#Commession').val(arr[3]);
                                $('#servicetax').val(arr[7]);
                                $('#DeductedAmount').val(arr[8]);
                                $('#UserAmount').val(arr[11]);
                                $('#Balance_Diff').val(Number($('#custTopUpDalanceID').val()) + Number(arr[3]));
                                if(arr[12] !== "0.0"){
                                    $('#nearestLess').show();
                                    $('#nearestMore').show();
                                    $('#text1').show();
                                    $('#text2').show();
                                    $('#nearestLess').text(arr[12]);
                                    $('#nearestMore').text(arr[13]);
                                    $('#Balance_Diff').val("0.0");
                                    canSubmit = false;
                                }else{
                                    $('#nearestLess').hide();
                                    $('#nearestMore').hide();
                                    $('#text1').hide();
                                    $('#text2').hide();
                                }
                            } else {
                                ajaxFlag = false;
                                return;
                            }
                        }
                    }).fail(function () {
                        ajaxFlag = false;
                        return;
                    });
                });
            });
            function AjaxRequestReturn(){
                if (!ajaxFlag){
                    window.location.replace("error.jsp");
                    return false;
                } else if(!canSubmit){
                    return false;
                }
                return true;
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
        <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=request.getParameter("SERVICE_ID")%>" />

        <fieldset style="width: 70%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%=CONFIG.getCustomerMobinilTopUp(request.getSession())%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
            <table border="1" style="width: 100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><p align="right"><%=CONFIG.getAmount(request.getSession())%> : 
                            <input type="text" name="<%=CONFIG.AMOUNT%>" required="" tabindex="1" id="custTopUpDalanceID"  style="float: left;" autocomplete ="OFF" />
                            <div id="custTopUpDalanceDiv" name="custAmountDiv"></div>
                            <div id="text1" style="color: #D12F19; display: none;">قيمة الشحن المدخله غير متاحه طبقا لسياسات شركة اورانج، </div>
                            <div id="text2" style="color: #D12F19; display: none;">اقرب فئات متاحه للشحن هي</div>
                            <div id="nearestLess" style="color: #D12F19; display: none;"></div>
                            <div id="nearestMore" style="color: #D12F19; display: none;"></div>
                        </p> 
                        <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%> : <input id="custTopUpMobileId" autocomplete="off" type="text" name="<%=CONFIG.PARAM_MSISDN%>" maxlength="12" required="" tabindex="2" style="float: left;">
                        <div id="custTopUpMobileDiv"  ></div></p>
                        <p align="right"><%=CONFIG.getConfirm(request.getSession())%> <%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custMobileConfirmation" autocomplete="off" maxlength="12" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  required="" tabindex="3" style="float: left;">
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
                    <td > <input type="submit" name="btnSubmit" tabindex="4" onclick="AjaxRequestReturn()" value="<%=CONFIG.getGo(request.getSession())%>" class="Btn" ></td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(mobinilBalance)%></td>
                </tr>
            </table>
            <details open="open">
                <summary></summary>
                1-	من فضلك ادخل المبلغ المراد شحنه و رقم الموبايل. </br>            
                2-                يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .
                <br/>
                <br/>
                •يجب كتابة رقم الموبايل بالكامل (11 رقم) مثل 01201234567.
                أرقام موبايل فودافون غالباً تبدأ بـ012 
                إذا كان رقم الموبايل المراد شحنه قد قام بتغيير الشبكة، يجب أن يتم شحنه بإستخدام الخدمة التى انتقل إليها.

                <br/>
                •مثال إذا كنت تريد شحن رقم الموبايل 01121234567 و الذى انتقل إلى شبكة اورنچ يجب الشحن بإستخدام خدمة شحن اورنچ و ليس شحن إتصالات.

                <br/>
                <br/>
                فئات الشحن المتاحه من قبل شركة اورانج
                <br/>
              من 5 جنيهات حتى 250 جنيه بزياده  1 جنيه
            </details>
        </fieldset> 
    </form>
</div><!-- End of Table Content area-->
<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>