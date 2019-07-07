<%-- 
    Document   : donation
    Created on : Sep 9, 2015, 12:41:15 PM
    Author     : KEMO
--%>

<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
<%@page import="com.masary.database.dto.DonationRespponseDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
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
    RatePlanDTO ratePlan = MasaryManager.getInstance().getCustomerCommAndFees(request.getSession().getAttribute("SERVICE_ID").toString(), agent.getPin());
    HashMap<String, String> list = MasaryManager.getInstance().getGovernorates("AR", 63);
    session.setAttribute("govlist", list);
    DonationAgentPaymentRespponseDto respponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationResponse");
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <title><%=CONFIG.getdonationName(session)%></title>
        <script>
            function onValueChanged() {
                var Amount = document.getElementById("custAmountID");
                var Fees = document.getElementById("Fees");
                var Commession = document.getElementById("Commession");
                var Balance_Diff = document.getElementById("Balance_Diff");
                var DeductedAmount = document.getElementById("DeductedAmount");

                Commession.value = Number(Fees.value) / 2;
                Balance_Diff.value = (Number(Amount.value) * <%=(ratePlan.getMasary_commission() / 100)%>).toFixed(1);
                DeductedAmount.value = (Number(Amount.value) + Number(Fees.value) - Number(Commession.value) + Number(Balance_Diff.value)).toFixed(1);
            }
            function ValidateMobileNo() {
                var errChk = 0;
                var regexObj = /01\d\d\d\d\d\d\d\d\d/;
                var balanceDiv = document.getElementById("custTopUpDalanceDiv");
                var mobileNo = document.getElementById("BILLING_ACCOUNT");
                if (mobileNo.value.match(regexObj)) {

                } else {
                    errChk = 1;
                    balanceDiv.innerHTML = "<br>رقم الموبايل غير صحيح برجاء ادخال الرقم الصحيح</br>";
                }
                if (errChk == 1) {
                    return false;
                }
                else {
                    return true;
                }
            }
        </script>
    </head>

    <body class="body" onload="onValueChanged();">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT%>Bill_Controler" method="POST" >

        <input type="hidden" name="action" value="<%=CONFIG.ACTION_GET_DONATION_CONFIRMATION%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getSession().getAttribute("SERVICE_ID").toString()%>" />
        <input type="hidden" name="<%=CONFIG.OPERATION_ID%>" value="<%=request.getSession().getAttribute("OPERATION_ID").toString()%>" />


        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.getCustomerdonationHead(session)%>&nbsp;</font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getprogram(session)%> : <input type="text" name="<%=CONFIG.PROGRAM%>" readonly tabindex="1" id="program" value="<%= respponseDto.getOPERATION_NAME()%>" style="background-color: #EDEDED; float: left;"/></p>
                            <p align="right"><%= CONFIG.getCustomerName(session)%> : <input type="text" name="Customer_name" required="true" tabindex="1" id="Customer_name" style="float: left;" /></p>
                                <%
                                    if (respponseDto.getFIXED_AMOUNT() <= 0.0) {
                                        //System.out.println("da5al1" + respponseDto.getFIXED_AMOUNT());
                                %>
                            <p  align = "right" > <%= CONFIG.getAssign_v(session)%> :  <input type="text" name="<%=CONFIG.AMOUNT%>" required tabindex="1"  id="custAmountID"  style="float: left;" onchange="onValueChanged();"></p>
                                <%} else {%>
                            <p align="right"><%= CONFIG.getAssign_v(session)%> :  <input type="text" name="<%=CONFIG.AMOUNT%>"  tabindex="1"  id="custAmountID" value="<%= respponseDto.getFIXED_AMOUNT()%>"   readonly style="float: left;background-color: #EDEDED;" onchange="onValueChanged();"></p> 
                                <%}%>

                            <p align="right"><%= CONFIG.getPhoneNumber(session)%> : <input type="text" name="BILLING_ACCOUNT"  tabindex="1"  id="BILLING_ACCOUNT" style="float: left;" />   <div id="custTopUpDalanceDiv" style="color: red; font-size: 12.5px;"></div></p>      

                            <p align="right"><%=CONFIG.getGovernorate(session)%> : 
                                <select  name="governrate" style="float: left;">
                                    <c:forEach items="${govlist}" var ="governrate">
                                        <option >${governrate.value}</option>
                                    </c:forEach>
                                    <!--<option></option>-->
                                </select>
                            </p>

                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="<%= respponseDto.getFEES()%>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="0"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="0"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getConfirm(session)%>" onclick="return ValidateMobileNo();" class="Btn" >

                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	برجاء ادخال رقم الموبايل واسم المتبرع واختر المحافظة وكتابه القيمة في حين طلبت. 
                </details>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
