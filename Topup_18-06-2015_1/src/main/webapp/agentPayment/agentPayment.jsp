<%-- 
    Document   : agentPayment
    Created on : Sep 6, 2015, 12:01:08 PM
    Author     : kyanni
--%>

<%@page import="javax.servlet.jsp.jstl.core.Config"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>


<%
    MasaryManager.logger.info("Agent Payment");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double serviceBalance = MasaryManager.getInstance().getCustomerServiceBal(agent.getPin(), Integer.parseInt(request.getSession().getAttribute("SERVICE_ID").toString()));
    ServiceDTO service = MasaryManager.getInstance().getService(request.getSession().getAttribute("SERVICE_ID").toString());
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), agent.getPin());
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=service.getStrServiceName(session)%></title>
        <script>
            function IsWrongInupt(input)
            {
                document.getElementById(input).style.backgroundColor = "yellow";
                document.getElementById(input).style.borderColor = "red";
            }
            function IsRightInput(input)
            {
                document.getElementById(input).style.backgroundColor = "";
                document.getElementById(input).style.borderColor = "";
            }
            function validateNationalID()
            {
                var val = document.getElementById("national_ID").value;

                var validformat = /^\d{14}$/i;
                var PhoneConfDiv = document.getElementById("nationalID_Dev");
                if (validformat.test(val))
                {
                    //IsRightInput("national_ID");
                    PhoneConfDiv.innerHTML = "";
                    return true;
                } else
                {
                    //IsWrongInupt("national_ID");
                    document.getElementById("national_ID").focus();
                    PhoneConfDiv.innerHTML = "إدخل الرقم القومي صحيح 14 رقم";
                    return false;
                }
            }
            function onValueChanged() {
                var Amount = document.getElementById("custAmountID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");
                Commession.value = (<%= ratePlan.getFixedAmount()%> + (Number(Amount.value) * <%= ratePlan.getCommission()%> / 100)).toFixed(3);
                Balance_Diff.value = (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>).toFixed(3);
                Fees.value = (<%= ratePlan.getApplied_fees()%> + (Number(Amount.value) * <%= ratePlan.getApplied_fees_per()%> / 100)).toFixed(3);
                DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value)).toFixed(3);
            }
            function validatePaymentBill()
            {
                var Mobile = document.getElementById("custTopUpMSISDNID");
                var Mobile_Confirmation = document.getElementById("custMobileConfirmation");
                var PhoneConfDiv = document.getElementById("custMobileConfirmationDiv");
                if (Mobile.value !== Mobile_Confirmation.value)
                {
                    PhoneConfDiv.innerHTML = "ارقام غير متطابقه";
                    Mobile_Confirmation.focus();
                    return false;
                }
                else
                {
                    PhoneConfDiv.innerHTML = "";

                    return true;
                }
            }
            function Validations()
            {
                var ID = validateNationalID();
                var bill = validatePaymentBill();
                if (ID === false || bill === false)
                {
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <div>
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include> <%}%>
            </div>
        <%try {
                String Error = request.getSession().getAttribute("ErrorCode").toString();
        %>
        <font style="color: red; font-size: 15px;"><%=request.getSession().getAttribute("ErrorCode").toString()%></font>
        <%
                request.getSession().removeAttribute("ErrorCode");
            } catch (Exception e) {
            }
        %>
        <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" onsubmit="return Validations();">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_Do_Agent_Payment%>" />
            <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("SERVICE_ID").toString()%>" />
            <input type="hidden" name="<%=CONFIG.PROVIDER_ID%>" value="<%=request.getSession().getAttribute("PROVIDER_ID").toString()%>" />
            <input type="hidden" name="<%=CONFIG.OPERATION_ID%>" value="<%=request.getSession().getAttribute("OPERATION_ID").toString()%>" />

            <div class="content_body"  >
                <fieldset style="width: 68%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%=service.getStrServiceName(session)%></font><img src="img/CashIn.ico"  width="20" height="20"> </legend> 
                    <table border="1">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=CONFIG.getMerchantCommession(session)%></th>
                        <tr>
                            <td>
                                <table>
                                    <tr>
                                        <td style="border: none ; background-color: transparent">
                                            <p align="right"><%=CONFIG.getBillRefNumORCN(session)%> :</p>
                                        </td>
                                        <td style="border: none ; background-color: transparent">
                                            <input id="custTopUpMSISDNID" name="<%=CONFIG.PARAM_MSISDN%>" autocomplete="off" type="text"  required="" tabindex="1" style="float: left;"> 
                                            <div id="custMobileDiv"></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="border: none ; background-color: transparent">
                                            <p align="right"><%=CONFIG.getConfirm(session)%> <%=CONFIG.getBillRefNumORCN(session)%> :</p>
                                        </td>
                                        <td style="border: none ; background-color: transparent">
                                            <input id="custMobileConfirmation" type="text" name="<%=CONFIG.PARAM_MSISDN_CONFIRMATION%>" autocomplete="off"  required="" tabindex="2" style="float: left;">
                                            <div id="custMobileConfirmationDiv" name="custMobileConfirmationDiv"></div>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td style="border: none ; background-color: transparent">
                                            <p align="right"><%=CONFIG.getNationalID(session)%> :</p> 
                                        </td>
                                        <td style="border: none ; background-color: transparent">
                                            <input type="text" name="national_ID" required="" tabindex="3" id="national_ID" maxlength="14" autocomplete="off"  style="float: left;" autocomplete ="OFF" />
                                            <div id="nationalID_Dev" name="nationalID_Dev"></div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="border: none ; background-color: transparent">
                                            <p align="right"><%=CONFIG.getAmount(session)%> :</p> 
                                        </td>
                                        <td style="border: none ; background-color: transparent">
                                            <input type="text" name="<%=CONFIG.AMOUNT%>" required="" tabindex="3" id="custAmountID" autocomplete="off" onchange="onValueChanged();" style="float: left;" autocomplete ="OFF" />
                                            <div id="custAmountDiv" name="custAmountDiv"></div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td>
                                <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="0"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                </br>
                            </td>
                        </tr>
                        <tr>
                            <td > <input type="submit" name="btnSubmit" onclick="" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn" <%=serviceBalance == 0 ? "disabled='true'" : " "%> ></td>
                            <td><%=CONFIG.getAllowedBalance(session)%> : <%=agent.isAutoAllocate().equals("F") ? myFormatter.format(customerBalance) : myFormatter.format(serviceBalance)%></td>
                        </tr>
                    </table>
                    <details open="open">
                        <summary></summary>
                        1- ادخل رقم الفاتورة او رقم العميل لدي الوكيل و قيمة الفاتورة.
                    </details>
                </fieldset> 
            </div><!-- End of Table Content area-->
        </form>
    </body>
</html>
