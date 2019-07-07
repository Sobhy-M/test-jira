<%-- 
    Document   : mubasherVoucher
    Created on : Oct 26, 2016, 2:45:49 PM
    Author     : AYA
--%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.MunasherItemsInfo"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.ProviderDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getCustomerMubasherVo(request.getSession())%></title>
        <%
            int serviceId = Integer.parseInt(session.getAttribute(CONFIG.PARAM_SERVICE_ID).toString());
            ProviderDTO provider = null;
            provider = MasaryManager.getInstance().getProviderForVoucherService(serviceId);
            String serviceName = request.getSession().getAttribute(CONFIG.lang).equals("") ? provider.getServiceNameEn() : provider.getServiceNameAr();
            MunasherItemsInfo[] availableVo = MasaryManager.getInstance().getMubasherList();
            AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
            RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(Integer.toString(serviceId), agent.getPin());

        %>
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>

        <script>
            function onselectChanged(sel) {

                var value = sel.value;
                if (value !== null && value !== "" && value !== "0") {
                    document.getElementById("custTopUpDalanceID").value = value;
                    document.getElementById("itemId").value = sel.options[sel.selectedIndex].id;
                    document.getElementById("itemCode").value = sel.options[sel.selectedIndex].text;
                    onValueChanged();
                } else {

                    document.getElementById("custTopUpMobileDiv").innerHTML = "برجاء اختيار فئة الكارت ";
                }
            }
            function onValueChanged() {
                var Amount = document.getElementById("custTopUpDalanceID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var DeductedAmount = document.getElementById("DeductedAmount");
                var UserAmount = document.getElementById("UserAmount");
                Commession.value = (<%= ratePlan.getFixedAmount()%> + (Number(Amount.value) * <%= ratePlan.getCommission() %> / 100)).toFixed(2);
                Fees.value = (<%= ratePlan.getApplied_fees()%> + (Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100)).toFixed(2);
                DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value)).toFixed(1);
                UserAmount.value = (Number(Amount.value) + Number(Fees.value)).toFixed(2);
            }
            function validateVoucherCustomer() {
                var Amount = document.getElementById("custTopUpDalanceID");
                var phone = document.getElementById("custTopUpMobileId");
                var phoneconfirmation = document.getElementById("custMobileConfirmation");
                var donomenation = document.getElementById("DenmonationVoucher");
                if (Amount.value === null || phone.value === null || phoneconfirmation.value === null ||
                        donomenation.value === null || Amount.value === "" || phone.value === "" || phoneconfirmation.value === ""
                        || donomenation.value === "" || donomenation.value === "0" || phoneconfirmation.value !== phone.value) {
                    document.getElementById("custTopUpMobileDiv").innerHTML = "برجاءالتاكد من اختيارك لفئة الكارت وادخال ارقام التليفون صحيحه ومتطابقه ";
                    return false;

                } else {

                    return true;
                }


            }

        </script>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
    </head>

    <BODY class="body" >
        <div>        
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <form name="BayCreditCustomer" action="MubasherVoucher" method="post" onsubmit="return validateVoucherCustomer()">
                <input type="hidden" name="action" value="<%=CONFIG.ACTION_CUSTOMER_TOPUP%>" />
            <input type="hidden" name="<%=CONFIG.TOPUP_TYPE%>" value="<%=serviceId%>" />
            <input type="hidden" name="<%=CONFIG.itemId%>" id="itemId" />
            <input type="hidden" name="<%=CONFIG.itemCode%>" id="itemCode" />
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%=serviceName%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
                <table style="width: 100% ; margin-left: auto ; margin-right: auto ;border: #000;border-width: 1" border="1" >
                    <th><%=CONFIG.getINFO_Required(request.getSession())%></th>
                    <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getSelectDenmonationVoucher(request.getSession())%>
                                <select required="true" name="DenmonationVoucher" id="DenmonationVoucher" style="float: left;" onchange="onselectChanged(this);" >
                                    <option value="0"  id="0"><%=CONFIG.getSelectAmount(request.getSession())%></option>

                                    <%for (MunasherItemsInfo i : availableVo) {%>
                                    <option value="<%= i.getPRICE()%>" id="<%= i.getITEM_ID()%>" ><%=i.getITEM_DESC()%></option><%}%>
                                </select></p>
                            <p align="right"><%=CONFIG.getSelectValueVoucher(request.getSession())%><input id="custTopUpDalanceID" readonly  type="text"  name="<%=CONFIG.AMOUNT%>"  tabindex="2" style="background-color: #EDEDED; float: left;" ></p>


                            <p align="right"><%=CONFIG.getMobileNumber(request.getSession())%>
                                <input id="custTopUpMobileId"  type="text"  name="<%=CONFIG.PARAM_MSISDN%>" required tabindex="2"  style="float: left;">
                            <div id="custTopUpMobileDiv" style="color: red;">تنبيه :يتم ارسال رساله ببيانات الكارت على الرقم المدخل</div></p>
                            <p align="right"><%=CONFIG.getConfirm(request.getSession())%> <%=CONFIG.getMobileNumber(request.getSession())%> :<input id="custMobileConfirmation" autocomplete="off" maxlength="12" required name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>"  tabindex="3" style="float: left;">
                            <div id="custMobileConfirmationDiv" ></div>


                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(request.getSession())%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            <p align="right"><%=CONFIG.getUserAmount(request.getSession())%> :<input type="text" name="UserAmount" value="0"  readonly tabindex="7" id="UserAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            </br>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getGo(request.getSession())%>"  class="Btn" />
                        </td>
                    </tr>
                </table>

            </fieldset> 
        </form>
    </div><!-- End content body -->
    <br>
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

