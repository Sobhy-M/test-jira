<%--
        Document   : bill_internet_egypt_info
    Created on : 03/04/2017, 01:11:19 م
    Author     : Mustafa
--%>

<%@page import="com.masary.integration.dto.InternetEgyptInquiryResponse"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    InternetEgyptInquiryResponse inquiryResponse = (InternetEgyptInquiryResponse) session.getAttribute("inquiryResponse");
    String custmerName = inquiryResponse.getClientName();
    String phoneNumber = inquiryResponse.getPhoneNumber();
    String endDate = inquiryResponse.getEndDate();
    double billAmount = inquiryResponse.getBillAmount();
    double merchantCommission = inquiryResponse.getMerchantCommission();
    double appliedFees = inquiryResponse.getAppliedFees();
    double tax =inquiryResponse.getTax();
    double costService=appliedFees+tax;

    double toBepaid = inquiryResponse.getToBepaid();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.getInternetEgyptServiceName(session)%></title>



    </head>
    <body class="body" onload="onValueChanged();">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="doPayment" action="InternetEgyptPaymentController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTIONInternetEgyptPayment%>" />


        <div class="content_body">
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.getInternetEgyptServiceName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getCustomerName(session)%> : 
                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=custmerName%>" style="background-color: #EDEDED; float: left;"/>
                            </p>
                            <p align="right"><%=CONFIG.getbillAmount(session)%> : 
                                <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1" readonly id="custAmountID" value="<%=billAmount%>"   style="background-color: #EDEDED; float: left;">
                            </p>
                            <p align="right"><%=CONFIG.getinternetEgyptTitle(session)%>: 
                                <input  readonly tabindex="1" id="custTopUpDalanceID" type="text"  name="<%=CONFIG.PARAM_MSISDN%>"  value="<%=phoneNumber%>" style="background-color: #EDEDED; float: left;"/>
                            </p>

                            <p align="right"><%=CONFIG.getDuDate(session)%> : <input type="text" name="DUE_DATE" readonly tabindex="1" id="DUE_DATE" value="<%=endDate%>" style="background-color: #EDEDED; float: left;"/></p>
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="<%=costService%> "  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="<%=merchantCommission%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="0.0"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="<%=(toBepaid-merchantCommission)%>"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4" value="<%=CONFIG.getpayment(session)%>">
                            <input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                        </td>
                    </tr>
                </table>
                <details open="open">
                    <summary></summary>
                    1-	تأكد أن رقم التليفون الذى تم إدخاله صحيح . 
                </details>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>