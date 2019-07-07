<%@page import="com.masary.CurrencyConvertRate"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="com.masary.BlaBla_Manager"%>
<%@page import="com.masary.database.dto.BlaBlaProducts_Res"%>
<%@page import="com.masary.database.dto.Product"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.EmployeeDTO"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="com.masary.database.dto.ProviderDTO"%>
<%@page import="com.masary.database.dto.CategoryDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    String custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    String empID = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    AgentDTO agent = null;
    EmployeeDTO empAgent = null;
    int serviceId = Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID));
    ProviderDTO provider = null;
    String finalOwner = "1";
    if (empID.equals("-1")) {
        finalOwner = custId;
        agent = MasaryManager.getInstance().getAgent(finalOwner);
    } else {
        finalOwner = empID;
        empAgent = MasaryManager.getInstance().getEmployee(finalOwner);
    }
    provider = MasaryManager.getInstance().getProviderForVoucherService(serviceId);
    String serviceName = request.getSession().getAttribute(CONFIG.lang).equals("") ? provider.getServiceNameEn() : provider.getServiceNameAr();
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(Integer.toString(serviceId), custId);
%>
<% CurrencyConvertRate coRate = new CurrencyConvertRate();
    double rate = coRate.SendRequestandConvert(1);
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=serviceName%></title>
        <script type="text/javascript">

            function loadNewValues(catId) {
                var valuesDT = document.getElementById("values");
                var str = '<select name="<%=CONFIG.AMOUNT%>" tabindex="1" id="custTopUpDalanceID">';
            <% for (CategoryDTO category : provider.getCategories()) {%>
                if (catId == "<%=category.getId()%>") {
            <%
                for (Object value : category.getAvilableValues()) {
            %>
                    str += "<option> <%=value%> </option>";
            <%
                }
            %>
                    str += "</select>";
                }
            <%      }%>
                valuesDT.innerHTML = str;
            }

        </script>
        <script type="text/javascript">
            function getAvaialableAmount()
            {
                var v = document.getElementById("custTopUpDalanceID").value;
            <% for (Object value : ((CategoryDTO) provider.getCategories().get(0)).getAvilableValues()) {%>
                if (v ==<%=value%>)
                {
            <% int count = MasaryManager.getInstance().getvouchercount(serviceId, Integer.parseInt(finalOwner), Double.parseDouble(value.toString()));%>
                    var val = "<%=count%>";
                    document.getElementById("aval_amount").innerHTML = val;
            <%if (count < 6) {%>
                    document.getElementById("custTopUpVoucherNo").max = <%=agent.isAutoAllocate().equals("F") ? "6" : count%>;
            <%} else {%>
                    document.getElementById("custTopUpVoucherNo").max = "6";
            <%}%>
                }
            <%}%>
                onValueChanged();
            }
        </script>
        <script type="text/javascript">
            function show() {
                document.getElementById("colName").innerHTML = '<%=CONFIG.getMobileNumber(request.getSession())%>';
                if (document.getElementById('Mobile').style.display == 'none') {
                    document.getElementById('Mobile').style.display = 'block';
                }
                if (document.getElementById('voucherNo').style.display == 'block') {
                    document.getElementById('voucherNo').style.display = 'none';
                }
            }
            function hide() {
                document.getElementById("colName").innerHTML = '<%=CONFIG.getVoucherNO(request.getSession())%>';
                if (document.getElementById('Mobile').style.display == 'block') {
                    document.getElementById('Mobile').style.display = 'none';
                }
                if (document.getElementById('voucherNo').style.display == 'none') {
                    document.getElementById('voucherNo').style.display = 'block';
                }
            }

            function hideAll() {
                document.getElementById("colName").innerHTML = '';
                document.getElementById("colName").innerHTML = '';
                document.getElementById('Mobile').style.display = 'none';
                document.getElementById('voucherNo').style.display = 'none';
            }

            function setAvailableMobileNO()
            {
                document.getElementById("Mobile").hide();
            }
        </script>
        <script>
            function checkCount() {
                var v = document.getElementById("custTopUpVoucherNo").value;
                if (v > 6) {
                    document.getElementById("custTopUpVoucherNODiv").innerHTML = 'من فضلك ادخل رقم اقل من 7 ';
                    return false;
                }
                return true;
            }
        </script>
        <script>
            function onValueChanged() {
                var v = document.getElementById("custTopUpVoucherNo").value;
                var Amount = document.getElementById("custTopUpDalanceID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
                Commession.value = (Number(v) * (<%= ratePlan.getFixedAmount()%> + (Number(Amount.value) * <%= ratePlan.getCommission()%> / 100))).toFixed(3);
                Fees.value = (Number(v) * (<%= ratePlan.getApplied_fees()%> + (Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100))).toFixed(3);
                Balance_Diff.value = (Number(v) * (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>)).toFixed(3);
                DeductedAmount.value = (Number(v) * (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value))).toFixed(3);


            <% if (serviceId == 15 || serviceId == 16 || serviceId == 17 || serviceId == 30 || serviceId == 34) {%>
                if (Number(Amount.value) < 20) {
                    hide();
                    document.getElementById('sms').disabled = true;
                    document.getElementById('Printing').checked = true;
                } else {
                    document.getElementById('sms').disabled = false;
                }
            <% }%>
            }
        </script>

    </head>
    <BODY class="body" >
        <%--<script type="text/javascript" src="img/CheckForms.js"></script>--%>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <form name="BayCreditCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="post" onsubmit="return checkCount();
                validateVoucherCustomer()" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_TOPUP%>" />
        <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=serviceId%>" />
        <fieldset style="width: 60%; direction: rtl;" align="right">  
            <legend align="right" ><font size="5"><%=serviceName%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
            <table border="1" width="100%">
                <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                <tr>
                    <td><table width="100%" >
                            <th colspan=<%=agent.isAutoAllocate().equals("F") ? "2" : "1"%> ><%=CONFIG.getAmount_V(request.getSession())%></th>
                            <th style='display:<%=agent.isAutoAllocate().equals("F") ? " none" : "block"%>' ><%=CONFIG.getAvailableAmount(request.getSession())%></th>
                            <tr>
                                <td width="50%">
                                    <select   name="<%=CONFIG.AMOUNT%>" tabindex="1" id="custTopUpDalanceID" <%=agent.isAutoAllocate().equals("F") ? "onchange='onValueChanged()'" : "onchange='getAvaialableAmount()'"%>>
                                        <option ><%=CONFIG.getSelectAmount(request.getSession())%></option>
                                        <%
                                            for (Object value : MasaryManager.getInstance().getVoucherValuesInternational(serviceId)) {
                                       
                                        %>

                                        <option <%=agent.isAutoAllocate().equals("F") ? (MasaryManager.getInstance().getvouchercount(serviceId, Integer.parseInt("1"), Double.parseDouble(value.toString())) == 0 ? "disabled" : "") : ""%>  >
                                            <%=(int) Math.ceil(Double.parseDouble(value.toString()) )%>
                                        </option>
                                        <%}%>
                                    </select> 
                                </td>
                                <td width="50%" align="center"><%if (!agent.isAutoAllocate().equals("F")) {%> 
                                    <div  id="aval_amount" readonly style="background-color: #EDEDED; width: 20%;">0</div>
                                    <p  align="right" style="display: inline; font: 14 px;"> <%= CONFIG.getAmountByDolar(session)%></p> <input id="dolaramount" type="text" readonly style="background-color: #EDEDED; width: 40%; float: left; "> 

                                    <%}%></td>
                            </tr>
                            <% if (serviceId == 15 || serviceId == 16 || serviceId == 17 || serviceId == 30 || serviceId == 34 || serviceId == 31) {%>
                            <th><%=CONFIG.getSellType(session)%></th>
                            <th  id="colName" colspan="2"><%=CONFIG.getVoucherNO(request.getSession())%></th>
                            <tr>
                                <td>
                                    <input type="radio" name="HowToSell" id="Printing"  value="Printing" checked onclick=" hide();"><%=CONFIG.getPrinting(session)%>
                                </td>
                                <td>
                                    <div style="display:block;"  id="voucherNo">
                                        <input id="custTopUpVoucherNo" value="1" name="<%=CONFIG.PARAM_VOUCHERNO%>"  tabindex="2" type="number" min="1"  max="6" onchange="onValueChanged();" >
                                        <div id="custTopUpVoucherNODiv"></div>
                                    </div>
                                </td >
                            </tr>
                            <tr>
                                <td>
                                    <input type="radio" name="HowToSell" value="sms" disabled  id="sms" onclick="show();"><%=CONFIG.getSMS(session)%>
                                </td>
                                <td>
                                    <div style="display:none;"  id="Mobile">
                                        <input id="custTopUpMobileId" type="text" size="11" name="<%=CONFIG.PARAM_MSISDN%>" tabindex="2" >
                                        <div id="custTopUpMobileDiv"></div>
                                    </div>
                                </td>
                            </tr>  
                            <tr>
                                <td>
                                    <input type="radio" name="HowToSell" value="view"  onclick=" hideAll();"><%=CONFIG.getView(session)%>
                                </td>
                                <td>

                                </td>
                            </tr>
                            <% } else {%>  
                            <th  id="colName" colspan="2"><%=CONFIG.getMobileNumber(request.getSession())%></th>
                            <tr>
                                <td>
                                    <div style="display:block;"  id="Mobile">
                                        <input id="custTopUpMobileId"  type="text"  name="<%=CONFIG.PARAM_MSISDN%>"  tabindex="2" >
                                        <div id="custTopUpMobileDiv"></div>
                                    </div>
                                    <div style="display:none;"  id="voucherNo">
                                        <input id="custTopUpVoucherNo" value="1" name="<%=CONFIG.PARAM_VOUCHERNO%>" type="hidden"  >
                                    </div>
                                </td>
                            </tr>                              
                            <%}%>
                        </table>
                    </td>
                    <td>
                        <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </br>
                    </td>
                </tr>
                <% if (provider.getCategories().size() > 1) {%>
                <tr>
                    <th>
                        <%=CONFIG.getCategory(request.getSession())%>
                    </th>
                    <td align="left">
                        <%
                            for (CategoryDTO category : provider.getCategories()) {
                        %>
                        <input type="radio" value="<%=category.getId()%>" name="<%=CONFIG.PARAM_CATEGORY_ID%>" checked onclick="loadNewValues(<%=category.getId()%>);"><%=category.getName()%><br>
                        <%}%>
                    </td>
                </tr>
                <%} else {%>
                <input type="hidden" value="<%=((CategoryDTO) provider.getCategories().get(0)).getId()%>" name="<%=CONFIG.PARAM_CATEGORY_ID%>" >
                <%}%>
                <tr>
                    <td > <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getGo(request.getSession())%>" class="Btn" <%=agent.getServiceBalance(serviceId) == 0 ? "disabled='true'" : " "%> ></td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(agent.getBalance()) : myFormatter.format(agent.getServiceBalance(serviceId))%></td>
                </tr>
            </table>
            <details open="open">

                <summary></summary>
                    <% if (serviceId == 34) {%>
                1-	اختر قيمة الكارت ثم اختر طريقة إصدار الكارت: </br>
                إما أن تقوم المحفظة بإرسال بيانات الكارت فى رسالة SMS إلى رقم موبايل العميل أو يتم طباعة الكارت على طابعة مصارى، و فى حالة طباعة الكارت يمكنك إصدار أكثر من كارت مختلف بنفس القيمة ، أو طلب ظهور الكارت ع الشاشة و لكن هذه الطريقة الأخيرة هى فقط لمحافظ الأفراد و ليس للتجار. 

                </br>
                2- - يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .               يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .</br></br>

                ملحوظة :- </br>
                فئات كروت BlaBla هى:
                </br>
                <table border="1" width="40%"> 



                    <tr>
                        <%
                            for (Object value : ((CategoryDTO) provider.getCategories().get(0)).getAvilableValues()) {
                        %>

                        <td style="padding:0px 0px;"><%=(int) Math.ceil(Double.parseDouble(value.toString()) * rate)%></td>

                    </tr>
                    <%}%>        
                </table>  	
                </br>
                إذا كان رقم الموبايل الذى تريد إرسال بيانات الكارت له قد قام بالإنتقال من شبكته إلى شبكة أخرى، لن يمكن إرسال رسالة الكارت له، من فضلك اطلب منه رقم موبايل آخر يكون على نفس شبكته دون تغيير.

                <%} else {%>
                <% if (serviceId == 31)%>

                <%{%>
                1-اختر قيمة الكارت ثم اختر طريقة إصدار الكارت: </br>
                إما أن تقوم المحفظة بإرسال بيانات الكارت فى رسالة SMS إلى رقم موبايل العميل. أو يتم طباعة الكارت على طابعة مصارى، و فى حالة طباعة الكارت يمكنك إصدار أكثر من كارت مختلف بنفس القيمة ، أو طلب ظهور الكارت ع الشاشة و لكن هذه الطريقة الأخيرة هى فقط لمحافظ الأفراد و ليس للتجار. 
                يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .
                </br>

                ملحوظة :- </br>
                كروت مرحبا هى بالفئات 5 ج و 10 ج و 20 ج و 30 ج و 50 ج و 100 ج و لكل كارت صلاحية لمدة تختلف عن الكارت الآخر كالتالى:</br>
                <table border="1" width="40%"> 

                    <tr>   <td style="padding:0px 0px;"> الكارت</td>  
                        <td style="padding:0px 0px;">الرصيد</td>  
                        <td style="padding:0px 0px;">الصلاحية منذ اول استخدام</td>  </tr> 
                    <tr>
                        <td style="padding:0px 0px;">مرحبا 5</td>  
                        <td style="padding:0px 0px;">5جنية </td>
                        <td style="padding:0px 0px;">شهر</td>  
                    </tr> 
                    <tr>
                        <td style="padding:0px 0px;">مرحبا 10</td>  
                        <td style="padding:0px 0px;">10جنية </td>
                        <td style="padding:0px 0px;">شهرين</td>  
                    </tr>
                    <tr>
                        <td style="padding:0px 0px;">مرحبا 20</td>  
                        <td style="padding:0px 0px;">20جنية </td>
                        <td style="padding:0px 0px;">ثلاثة شهور</td>  
                    </tr>
                    <tr>
                        <td style="padding:0px 0px;">مرحبا 30</td>  
                        <td style="padding:0px 0px;">30جنية </td>
                        <td style="padding:0px 0px;">اربعة اشهر</td>  
                    </tr>
                    <tr>
                        <td style="padding:0px 0px;">مرحبا 50</td>  
                        <td style="padding:0px 0px;">50 جنية</td>
                        <td style="padding:0px 0px;">خمسة اشهر</td>  
                    </tr>
                    <tr>
                        <td style="padding:0px 0px;">مرحبا 100</td>  
                        <td style="padding:0px 0px;">100 جنية</td>
                        <td style="padding:0px 0px;">سبعة اشهر</td>  
                    </tr>

                </table>
                </br>
                إذا كان رقم الموبايل الذى تريد إرسال بيانات الكارت له قد قام بالإنتقال من شبكته إلى شبكة أخرى، لن يمكن إرسال رسالة الكارت له، من فضلك اطلب منه رقم موبايل آخر يكون على نفس شبكته دون تغيير.
                </br>

                <%}
                    }%>
            </details>
        </fieldset> 
    </form>
</div><!-- End content body -->
<br>
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

