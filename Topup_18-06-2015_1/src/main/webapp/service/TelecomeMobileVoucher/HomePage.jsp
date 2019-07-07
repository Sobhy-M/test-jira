<%@page import="com.masary.utils.BuildUUID"%>
<%@page import="com.masary.integration.dto.TelecomeDenominationsDTO"%>
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


    int serviceId = Integer.parseInt(request.getParameter(CONFIG.PARAM_SERVICE_ID));
    request.getSession().setAttribute("SERVICE_ID", serviceId);
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    DecimalFormat myFormatter = CONFIG.getFormater(request.getSession());
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
    double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), serviceId);

    TelecomeDenominationsDTO[] telecomeDenominationsDTO = (TelecomeDenominationsDTO[]) request.getSession().getAttribute("telecomeDenominationsDTO");

%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
        <title><%=CONFIG.TelecomEgyptServiceName%></title>
        <style>
            p{
                width: 105%;
            }
        </style>
        <script type="text/javascript">


            var ajaxReturn = false;

            function onChangeVoucherValue() {
                var amount = document.getElementById("custTopUpDalanceID").value;
                var x = document.getElementById("custTopUpDalanceID").selectedIndex;
                var y = document.getElementById("custTopUpDalanceID").options;
                var id = y[x].id;
                document.getElementById("denoId").value = id;
                if (amount === null || amount === '' || amount === '<%= CONFIG.getSelectAmount(request.getSession())%>' || amount === "0")
                {
                    return false;


                } else {
                    getCommission(amount, id);

                }

                function getCommission(amount, id) {
                    $.ajax({
                        url: 'TelecomeRatePlanRequest',
                        data: {
                            SERVICE_ID: <%=request.getParameter("SERVICE_ID")%>,
                            AMOUNT: amount,
                            DENOMINATION_ID: id

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
                        $('#Fees').val(arr[0]);
                        $('#Commession').val(arr[1]);
                        $('#AddedTax').val(arr[3]);
                        $('#DeductedAmount').val(arr[4]);
                        $('#UserAmount').val(arr[5]);
                        $('#Balance_Diff').val(arr[2]);

                    }).fail(function () {
                        ajaxReturn = false;
                        console.log('Failed');
                    });
                }




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

            function checkValidations() {

                var amount = document.getElementById("custTopUpDalanceID").value;

                if (amount === null || amount === '' || amount === '<%= CONFIG.getSelectAmount(request.getSession())%>' || amount === "0")
                {
                    document.getElementById("custTopUpVoucherNODiv").innerHTML = 'تاكد من اختيار الفئة الصحيحة';
                    return false;


                }

                return true;

            }
        </script>

    </head>
    <BODY class="body" >
        <div>        
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>

            <form name="BayCreditCustomer" action="<%=CONFIG.APP_ROOT%>TelecomeEgyptVoucherController" method="post" onsubmit="return checkValidations();" >
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_TELECOMEEGYPT_CONFIRMATION%>" />
            <input type="hidden" name="AMOUNT" id="Amount" />
            <input type="hidden" name="SERVICE_ID" id="ServiceId" />
            <input type="hidden" name="DENOMINATION_ID" id="denoId" />
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=CONFIG.TelecomEgyptServiceName%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
                <table style="width: 100% ; margin-left: auto ; margin-right: auto ;border: #000;border-width: 1" border="1" >
                    <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                    <th>حسابك</th>
                    <tr>
                        <td style="padding-bottom: 15%"><table style="width: 100%">
                                <th><%=CONFIG.getAmount_V(request.getSession())%></th>
                                <tr>
                                    <td>
                                        <select required="true"  name="<%=CONFIG.AMOUNT%>" tabindex="1" id="custTopUpDalanceID" onchange="onChangeVoucherValue();">

                                            <option  value="0"  id="0"><%=CONFIG.getSelectAmount(request.getSession())%></option>

                                            <%for (TelecomeDenominationsDTO t : telecomeDenominationsDTO) {%>
                                            <option name="selected" id="<%= t.getDenominationId()%>" value="<%= t.getDenominationpPrice()%>"><%=Double.valueOf(t.getDenominationpPrice()) / 100%></option><%}%>

                                        </select> <%= CONFIG.getCurrency(session)%> </td>
                                        <%if (!agent.isAutoAllocate().equals("F")) {%> 
                                <div  id="aval_amount" readonly style="background-color: #EDEDED; width: 20%;"></div>
                                <%}%>

                    </tr>
                    <th><%=CONFIG.getSellType(session)%></th>
                    <tr>
                        <td>
                            <input type="radio" name="HowToSell" id="Printing"  value="Printing" checked ><%=CONFIG.getPrinting(session)%>
                        </td>
                    <div id="custTopUpVoucherNODiv" style="color:  #ff0000; font-size: large;"></div>
                    </tr>

                </table>
                </td>
                <td style="padding-left: 7%;width: 65%;">
                    <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                    <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                    <p align="right"><%=CONFIG.getBalance_Diff(request.getSession())%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p>
                    <p align="right"><%=CONFIG.getAddedTax(request.getSession())%> :<input type="text" name="AddedTax" value="0"  readonly tabindex="7" id="AddedTax" style="background-color: #EDEDED; float: left;"/><div></div></p>
                    <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                    <p align="right"><%=CONFIG.getUserAmount(request.getSession())%> :<input type="text" name="UserAmount" value="0"  readonly tabindex="7" id="UserAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                    </br>
                </td>
                </tr>
                <tr>
                    <td > <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getGo(request.getSession())%>"  class="Btn" ></td>
                    <td><%=CONFIG.getAllowedBalance(request.getSession())%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(serviceBalance)%></td>
                </tr>
                </table>
                <details open="open">
                    <summary>
                        <br>
                        1-	اختر قيمة الكارت
                        <br>
                        2-	يظهر لك فى الجهة اليسرى من الشاشة التكلفة التى سيدفعها العميل بالإضافة إلى المبلغ، ويظهر لك عمولتك على المبلغ بالجنيه، وايضاً يظهر لك المبلغ الذى سوف يتم خصمه من حسابك .

                    </summary>
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