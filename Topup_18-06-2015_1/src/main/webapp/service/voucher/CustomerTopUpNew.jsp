<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.VoucherDenoDTO"%>
<%@page import="com.masary.CurrencyConvertRate"%>
<%@page import="com.masary.database.dto.LoginDto"%>
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
    session = request.getSession();
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    int serviceId = Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID));
    ProviderDTO provider = null;
    String finalOwner = "1";
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), serviceId);
    provider = MasaryManager.getInstance().getProviderForVoucherService(serviceId);
    String serviceName = request.getSession().getAttribute(CONFIG.lang).equals("") ? provider.getServiceNameEn() : provider.getServiceNameAr();
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(Integer.toString(serviceId), agent.getPin());
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
    CurrencyConvertRate coRate = new CurrencyConvertRate();
    double rate = 0;
    if (serviceName.equals("28")) {
        rate = coRate.SendRequestandConvert(1);
    }
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
        <title><%=serviceName%></title>
        <script type="text/javascript">

            var ajaxReturn = false;
            function loadNewValues() {
                var valuesDT = document.getElementById("values");
                var str = '<select name="<%=CONFIG.AMOUNT%>" tabindex="1" id="custTopUpDalanceID">';
            <%
                for (VoucherDenoDTO value : MasaryManager.getInstance().getAvailableDenomination(serviceId)) {
                    if (value.getAvailable() == 1) {
            %>
                str += "<option> <%=value.getDenomenation()%> </option>";
            <%
                    }
                }
            %>
                str += "</select>";
                valuesDT.innerHTML = str;
            }

            function onChangeVoucherValueValidation() {


                //var amount=document.getElementById("custTopUpDalanceID").value;
                if ((document.getElementById("custTopUpDalanceID").value === "50" || document.getElementById("custTopUpDalanceID").value === "100") && (<%=request.getParameter("SERVICE_ID")%> === 15 || <%=request.getParameter("SERVICE_ID")%> === 17)) {
                    document.getElementById("custTopUpVoucherNODiv").innerHTML = "";
                    document.getElementById("voucherNoValidate").style.display = "block";

                    document.getElementById("voucherNo").style.display = "none";

                } else {

                    document.getElementById("voucherNoValidate").style.display = "none";

                    document.getElementById("voucherNo").style.display = "block";


                }
            }


            function onChangeVoucherValue() {



                if (document.getElementById("custTopUpDalanceID").value === "5.01") {
                    document.getElementById("TopupDenmo").value = "5.01";
                }
                if (document.getElementById("custTopUpDalanceID").value === "5.01" &&<%=request.getParameter("SERVICE_ID")%> === 17) {
                    document.getElementById("TopupDenmo").value = "5";
                    document.getElementById("topupdemo").value = "5.01";
//                        alert( document.getElementById("topupdemo").value);
                }
                if (document.getElementById("custTopUpDalanceID").value === "10.01" &&<%=request.getParameter("SERVICE_ID")%> === 15) {
                    document.getElementById("TopupDenmo").value = "10";
                    document.getElementById("topupdemo").value = "10.01";
//                        alert( document.getElementById("topupdemo").value);
                } else if (document.getElementById("custTopUpDalanceID").value === "10.02" &&<%=request.getParameter("SERVICE_ID")%> === 15) {
                    document.getElementById("TopupDenmo").value = "10";
                    document.getElementById("topupdemo").value = "10.02";
//                        alert( document.getElementById("topupdemo").value);
                } else if (document.getElementById("custTopUpDalanceID").value === "10.03" &&<%=request.getParameter("SERVICE_ID")%> === 15) {
                    document.getElementById("TopupDenmo").value = "10";
                    document.getElementById("topupdemo").value = "10.03";
//                        alert( document.getElementById("topupdemo").value);
                } else if (document.getElementById("custTopUpDalanceID").value === "5.05" &&<%=request.getParameter("SERVICE_ID")%> === 15) {
                    document.getElementById("TopupDenmo").value = "5";
                    document.getElementById("topupdemo").value = "5.05";
                } else if (document.getElementById("custTopUpDalanceID").value === "10.05" &&<%=request.getParameter("SERVICE_ID")%> === 15) {
                    document.getElementById("TopupDenmo").value = "10";
                    document.getElementById("topupdemo").value = "10.05";
                } else {
                    document.getElementById("TopupDenmo").value = document.getElementById("custTopUpDalanceID").value;
                }

                if (document.getElementById("custTopUpDalanceID").value === "15.01" &&<%=request.getParameter("SERVICE_ID")%> === 17) {
                    document.getElementById("TopupDenmo").value = "15";
                    document.getElementById("topupdemo").value = "15.01";
                }
                if (document.getElementById("custTopUpDalanceID").value === "25.01" &&<%=request.getParameter("SERVICE_ID")%> === 17) {
                    document.getElementById("TopupDenmo").value = "25";
                    document.getElementById("topupdemo").value = "25.01";
                }
                if (document.getElementById("custTopUpDalanceID").value === "10.01" &&<%=request.getParameter("SERVICE_ID")%> === 17) {
                    document.getElementById("TopupDenmo").value = "10";
                    document.getElementById("topupdemo").value = "10.01";
                }
                if (document.getElementById("custTopUpDalanceID").value === "7.01" &&<%=request.getParameter("SERVICE_ID")%> === 17) {
                    document.getElementById("TopupDenmo").value = "7";
                    document.getElementById("topupdemo").value = "7.01";
                }
                var vouchersAmount = "";
                if ((document.getElementById("custTopUpDalanceID").value === "50" || document.getElementById("custTopUpDalanceID").value === "100") && (<%=request.getParameter("SERVICE_ID")%> === 15 || <%=request.getParameter("SERVICE_ID")%> === 17)) {
                    vouchersAmount = parseInt(document.getElementById("custTopUpVoucherNoValidate").value);
                    document.getElementById("vouchersNumber").value = vouchersAmount;


                } else {
                    vouchersAmount = parseInt(document.getElementById("custTopUpVoucherNo").value);
                    document.getElementById("vouchersNumber").value = vouchersAmount;
                }

                $.ajax({
                    url: 'RatePlanServiceInformation',
                    data: {
                        SERVICE_ID: <%=request.getParameter("SERVICE_ID")%>,
                        CUSTOMER_ID: <%=agent.getPin()%>,
                        AMOUNT: $('#custTopUpDalanceID').val(),
                        TopupDenmo: document.getElementById("TopupDenmo").value
                    },
                    type: 'get'
                }).done(function (responseData) {
                    if (responseData === '')
                    {

                        ajaxReturn = false;
                        return;
                    } else
                    {
                        ajaxReturn = true;
                    }



                    var arr = responseData.split('-');
                    $('#Fees').val(arr[6] * vouchersAmount);
                    $('#Commession').val(arr[3] * vouchersAmount);
                    $('#AddedTax').val(arr[7] * vouchersAmount);
                    if (arr[7] !== null && arr[7] !== "" && arr[3] !== null && arr[3] !== "" && arr[6] !== null && arr[6] !== "") {
                        $('#DeductedAmount').val(arr[8] * vouchersAmount);
                        $('#UserAmount').val(arr[11] * vouchersAmount);
                        $('#Balance_Diff').val(Number(arr[10]) + Number(arr[8]) * arr[6] * vouchersAmount);
                    } else {
                        $('#DeductedAmount').val("");
                        $('#Balance_Diff').val("");
                    }
                }).fail(function () {
                    ajaxReturn = false;
                    console.log('Failed');
                });
            }
            function show() {
                document.getElementById("colName").innerHTML = '<%=CONFIG.getMobileNumber(request.getSession())%>';
                if (document.getElementById('Mobile').style.display === 'none') {
                    document.getElementById('Mobile').style.display = 'block';
                }
//                if (document.getElementById('voucherNo').style.visibility === "visible" || document.getElementById('voucherNoValidate').style.visibility === "visible") {
//                    document.getElementById('voucherNo').style.visibility = "hidden";
//                    document.getElementById('voucherNoValidate').style.visibility = "hidden";
//
//                }
            }
            function hide() {

                document.getElementById("colName").innerHTML = '<%=CONFIG.getVoucherNO(request.getSession())%>';
                if (document.getElementById('Mobile').style.display === 'block') {
                    document.getElementById('Mobile').style.display = 'none';
                }
                if (document.getElementById('voucherNo').style.visibility === "hidden" || document.getElementById('voucherNoValidate').style.visibility === "hidden") {
                    document.getElementById('voucherNo').style.visibility = "visible";
                    document.getElementById('voucherNoValidate').style.visibility = "visible";

                }
            }
            function hideAll() {
                document.getElementById("colName").innerHTML = '';
                document.getElementById("colName").innerHTML = '';
                document.getElementById('Mobile').style.display = 'none';
                document.getElementById('voucherNo').style.visibility = "hidden";
                document.getElementById('voucherNoValidate').style.visibility = "hidden";

            }
            function setAvailableMobileNO()
            {
                document.getElementById("Mobile").hide();
            }


            function checkCount() {

                var voucherNo = document.getElementById("custTopUpVoucherNo").value;
                var voucherNoValidate = document.getElementById("custTopUpVoucherNoValidate").value;
                var amount = document.getElementById("custTopUpDalanceID").value;
                if (voucherNoValidate > 2 && (amount === "100" || amount === "50") && (<%=request.getParameter("SERVICE_ID")%> === 15 || <%=request.getParameter("SERVICE_ID")%> === 17)) {
                    document.getElementById("custTopUpVoucherNODiv").innerHTML = 'من فضلك ادخل رقم اقل من او يساوى 2 ';
                    return false;
                } else if (voucherNo > 10 && (amount !== "100" || amount !== "50") && (<%=request.getParameter("SERVICE_ID")%> === 15 || <%=request.getParameter("SERVICE_ID")%> === 17)) {
                    document.getElementById("custTopUpVoucherNODiv").innerHTML = 'من فضلك ادخل رقم اقل من او يساوى 10 ';
                    return false;
                }
                document.getElementById("custTopUpVoucherNODiv").innerHTML = "";
                document.getElementById("custTopUpVoucherNODiv").disabled = true;
                return true;
            }
            function onValueChanged() {


            <% if (serviceId == 15 || serviceId == 16 || serviceId == 17 || serviceId == 30 || serviceId == 28 || serviceId == 18 || serviceId == 31) {%>
                if (Number(document.getElementById("custTopUpDalanceID").value) < 20) {
                    hide();
                    document.getElementById('sms').disabled = true;
                    document.getElementById('Printing').checked = true;
                } else {
                    show();
                    document.getElementById('sms').disabled = false;
                }
            <% if (serviceId == 28) {%>
                document.getElementById('dolaramount').value = Number(Math.floor(Amount.value /<%= (rate)%>));
            <% }
                }%>
            }

            function AjaxRequestReturn()
            {
                if (ajaxReturn === false)
                {

                    window.location.replace("error.jsp");
                    return false;
                }
                return true;
            }



        </script>

    </head>
    <BODY class="body" onload="onChangeVoucherValueValidation()" >
        <div>        
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <form name="BayCreditCustomer" action="<%=CONFIG.APP_ROOT%>Web" method="post" onsubmit="return checkCount();">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_TOPUP%>" />
            <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=serviceId%>" />
            <input type="hidden" name="TopupDenmo" id="TopupDenmo" />
            <input type="hidden" name="topupdemo" id="topupdemo" />
            <input type="hidden" name="vouchersNumber" id="vouchersNumber" />
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=serviceName%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
                <table style="width: 100% ; margin-left: auto ; margin-right: auto ;border: #000;border-width: 1" border="1" >
                    <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                    <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                    <tr>
                        <td><table style="width: 100%">
                                <th colspan="2" ><%=CONFIG.getAmount_V(request.getSession())%></th>
                                <th style='display:<%=agent.isAutoAllocate().equals("F") ? " none" : "block"%>' ><%=CONFIG.getAvailableAmount(request.getSession())%></th>
                                <tr>
                                    <td>
                                        <select   name="<%=CONFIG.AMOUNT%>" tabindex="1" id="custTopUpDalanceID" onchange="onChangeVoucherValueValidation();
                                                onChangeVoucherValue();
                                                onValueChanged()" >

                                            <option value=" " ><%=CONFIG.getSelectAmount(request.getSession())%></option>
                                            <%
                                                if (serviceId != 28) {
                                                    ArrayList<VoucherDenoDTO> values = MasaryManager.getInstance().getAvailableDenomination(serviceId);
                                                    //  for (VoucherDenoDTO value : MasaryManager.getInstance().getAvailableDenomination(serviceId)) {
                                                    if (!values.isEmpty()) {
                                                        for (VoucherDenoDTO value : values) {
                                                            //if (value.getAvailable() == 1) {

                                                            if (value.getDenomenation().equals("10.01")) {  %>
                                            <option value="10.01">مارد الدقايق </option> 
                                            <%} else if (value.getDenomenation().equals("10.02")) {  %>
                                            <option value="10.02">ماردالشبكات</option> 
                                            <%} else if (value.getDenomenation().equals("10.03")) {  %>
                                            <option value="10.03">مارد النت</option> 
                                            <%} else if (value.getDenomenation().equals("5.01")) {  %>
                                            <option value="5.01"> 5 الجديد</option> 
                                            <%} else if (value.getDenomenation().equals("5") && serviceId == 17) {  %>
                                            <option value="5"> 5</option> 

                                            <%} else if (value.getDenomenation().equals("5.05")) {  %>
                                            <option value="5.05"> 5</option> 
                                            <%} else if (value.getDenomenation().equals("10.05")) {  %>
                                            <option value="10.05"> 10</option> 

                                            <!--   <option  value="5.01"  > 5ميكس ودقايق</option> -->
                                            <%} else if (value.getDenomenation().equals("10") && serviceId == 17) {  %>
                                            <option value="10"> 10</option> 
                                            <!--  <option value="10.01"  > 10ميكس ودقايق</option> -->
                                            <%} else if (value.getDenomenation().equals("15") && serviceId == 17) {  %>
                                            <option value="15"> 15</option> 
                                            <!--  <option value="15.01" > 15ميكس ودقايق</option>-->
                                            <%} else if (value.getDenomenation().equals("25") && serviceId == 17) {  %>
                                            <option value="25"> 25</option> 
                                            <!--  <option value="25.01" > 25ميكس ودقايق</option>-->
                                            <%} else {%>
                                            <option><%= value.getDenomenation()%></option>
                                            <%                                      }
                                                }%>
                                            <%if (serviceId == 17) {  %> 
                                            <option  disabled >//أقوى كارت في مصر\\</option>
                                            <% }%>
                                            <%for (VoucherDenoDTO value : values) {
                                                    if (value.getDenomenation().equals("5") && serviceId == 17) {  %>
                                            <option  value="5.01"  > 5ميكس ودقايق</option> 
                                            <%} else if (value.getDenomenation().equals("7") && serviceId == 17) {  %>
                                            <option value="7.01"  > 7ميكس ودقايق</option> 
                                            <%} else if (value.getDenomenation().equals("10") && serviceId == 17) {  %>
                                            <option value="10.01"  > 10ميكس ودقايق</option> 
                                            <%} else if (value.getDenomenation().equals("15") && serviceId == 17) {  %>
                                            <option value="15.01" > 15ميكس ودقايق</option>
                                            <%} else if (value.getDenomenation().equals("25") && serviceId == 17) {  %>
                                            <option value="25.01" > 25ميكس ودقايق</option>
                                            <%}
                                                    }
                                                }
                                            } else {
                                                for (Object value : MasaryManager.getInstance().getVoucherValuesInternational(serviceId)) {%>
                                            <option <%=agent.isAutoAllocate().equals("F") ? (MasaryManager.getInstance().getvouchercount(serviceId, Integer.parseInt("1"), Double.parseDouble(value.toString())) == 0 ? "disabled" : "") : ""%>  ><%=value%></option>

                                            <%   }
                                                }
                                            %>

                                        </select> <%= CONFIG.getCurrency(session)%> </td>
                                    <td width="50%" align="center"><%if (!agent.isAutoAllocate().equals("F")) {%> 
                                        <div  id="aval_amount" readonly style="background-color: #EDEDED; width: 20%;">0</div>
                                        <%} else if (serviceId == 28) {%>
                                        <p  align="right" style="display: inline; font: 14 px;"> <%= CONFIG.getAmountByDolar(session)%></p> <input id="dolaramount" type="text" readonly style="background-color: #EDEDED; width: 40%; float: left; "> 
                                    </td>
                                    <%}%>
                                </tr>
                                <% if (serviceId == 15 || serviceId == 16 || serviceId == 17 || serviceId == 28 || serviceId == 30 || serviceId == 18 || serviceId == 31) {%>
                                <th><%=CONFIG.getSellType(session)%></th>
                                <th  id="colName" colspan="2"><%=CONFIG.getVoucherNO(request.getSession())%></th>
                                <tr>
                                    <td>
                                        <input type="radio" name="HowToSell" id="Printing"  value="Printing" checked onclick=" hide();"><%=CONFIG.getPrinting(session)%>
                                    </td>
                                    <td>
                                        <% if (serviceId == 16) {%>



                                        <div style="visibility: visible"  id="voucherNoValidate">
                                            <input id="custTopUpVoucherNoValidate"  value="1" name="<%=CONFIG.PARAM_VOUCHERNO%>"   type="number"  disabled="disabled" maxlength="2"  onchange="onChangeVoucherValue()">


                                        </div>

                                        <div style="visibility: visible"   id="voucherNo">
                                            <input id="custTopUpVoucherNo" value="1" name="<%=CONFIG.PARAM_VOUCHERNO%>"   type="number" disabled="disabled"  onchange="onChangeVoucherValue()"  >
                                        </div>



                                        <%} else {%>


                                        <div style="visibility: visible"  id="voucherNoValidate">
                                            <input id="custTopUpVoucherNoValidate"  value="1" name="<%=CONFIG.PARAM_VOUCHERNO%>" min="1" max="2"  type="number"  onchange="onChangeVoucherValue()">


                                        </div>

                                        <div style="visibility: visible"   id="voucherNo">
                                            <input id="custTopUpVoucherNo" value="1" name="<%=CONFIG.PARAM_VOUCHERNO%>" min="1" max="10"  type="number"  onchange="onChangeVoucherValue()"  >
                                        </div>
                                        <%}%>
                                        <div id="custTopUpVoucherNODiv" style="color: red; font-size: 12.5px;"></div>                                           

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
                                    <td colspan="2">
                                        <input type="radio" name="HowToSell" value="view"  onclick=" hideAll();"><%=CONFIG.getView(session)%>
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


                                    </td>
                                </tr>                              
                                <%}%>


                            </table>
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            <p align="right"><%=CONFIG.getAddedTax(request.getSession())%> :<input type="text" name="AddedTax" value="0"  readonly tabindex="7" id="AddedTax" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            <p align="right"><%=CONFIG.getUserAmount(request.getSession())%> :<input type="text" name="UserAmount" value="0"  readonly tabindex="7" id="UserAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
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
                        <td > <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getGo(request.getSession())%>" onclick="return AjaxRequestReturn()" class="Btn" <%=serviceBalance == 0 ? "disabled='true'" : " "%> ></td>
                        <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(serviceBalance)%></td>
                    </tr>

                </table>
                <details open="open">
                    <summary></summary>
                        <% if (serviceId == 31) {%>
                    1-اختر قيمة الكارت ثم اختر طريقة إصدار الكارت: </br>
                    إما أن تقوم المحفظة بإرسال بيانات الكارت فى رسالة SMS إلى رقم موبايل العميل. أو يتم طباعة الكارت على طابعة مصارى، و فى حالة طباعة الكارت يمكنك إصدار أكثر من كارت مختلف بنفس القيمة ، أو طلب ظهور الكارت ع الشاشة و لكن هذه الطريقة الأخيرة هى فقط لمحافظ الأفراد و ليس للتجار. 
                    يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .
                    </br>
                    ملحوظة :- </br>
                    كروت مرحبا هى بالفئات 5 ج و 10 ج و 20 ج و 30 ج و 50 ج و 100 ج و لكل كارت صلاحية لمدة تختلف عن الكارت الآخر كالتالى:</br>
                    <table border="1" width="40%"> 
                        <tr>   
                            <td style="padding:0px 0px;"> الكارت</td>  
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
                    <% } else if (serviceId == 28) {%>
                    </br>   من فضلك اختر فئة الكارت الى تريده من خانة (الكارت بالجنيه المصرى).</br>

                    سيظهر لك الرصيد بالدولار المكافئ للمبلغ بالجنيه المصرى الذى قمت بإختياره ، و بالإضافة لتكلفة الخدمة (5 جنيه) سيظهر لك إجمالى المبلغ المستحق على العميل بالجنيه المصرى.

                    توجد أربع فئات من رصيد كاش يو و هى تعادل فئات الرصيد التالية بالدولار 10 و 30 و 50 و 100 دولار، ثم اضغط زرار (تنفيذ).

                    فى حالة وجود فئة كارت غير منشطة فهذا يعنى أن هذه الفئة غير متوفرة حالياً، و يمكنك شحن كاش يو مباشرةً بنفس قيمة الكارت الغير متوفر.

                    </br>
                    ملحوظة: إن أسعار كروت كاش يو بالجنيه المصرى قد تختلف من يوم لآخر تبعاً لسعر الصرف و تبعاً لسياسة التسعير فى شركة كاش يو.</br>
                    <%} else {%>

                    1-	اختر قيمة الكارت ثم اختر طريقة إصدار الكارت:    </br>
                    إما أن تقوم المحفظة بإرسال بيانات الكارت فى رسالة SMS إلى رقم موبايل العميل.
                    أو يتم طباعة الكارت على طابعة مصارى، و فى حالة طباعة الكارت يمكنك إصدار أكثر من كارت مختلف بنفس القيمة ، أو طلب ظهور الكارت ع الشاشة و لكن هذه الطريقة الأخيرة هى فقط لمحافظ الأفراد و ليس للتجار.
                    </br>
                    2-                يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى يدفعها العميل بالإضافة إلى المبلغ و يظهر لك عمولتك على المبلغ بالجنيه و أيضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .</br></br>

                    ملحوظة :- </br>
                    •	كروت فودافون الفكة التى تمنحك عدد من الدقائق و غيره هى الكروت 1 ج و 2 ج و 3 ج و 4 ج فقط أما باقى الفئات فهى فئات عادية تمنحك رصيد.
                    </br>
                    •كروت اتصالات باقى التى تمنحك عدد من الدقائق و غيره هى الكروت (نص جنيه) و (1 جنيه) و (جنيه و نص)  فقط أما باقى الفئات فهى فئات عادية تمنحك رصيد.	
                    </br>
                    •إذا كان رقم الموبايل الذى تريد إرسال بيانات الكارت له قد قام بالإنتقال من شبكته إلى شبكة أخرى، لن يمكن إرسال رسالة الكارت له، من فضلك اطلب منه رقم موبايل آخر يكون على نفس شبكته دون تغيير.	

                    <%}%>

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