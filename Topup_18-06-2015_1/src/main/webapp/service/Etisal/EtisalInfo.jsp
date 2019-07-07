<%-- 
    Document   : EtisalInfo
    Created on : Mar 28, 2017, 2:12:03 PM
    Author     : Ahmed Khaled
--%>


<%@page import="java.math.RoundingMode"%>
<%@page import="com.masary.integration.dto.EtisalInquiryResponse"%>
<%@page import="java.util.Date"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Bill_Amounts"%>
<%@page import="com.masary.database.dto.Masary_Bill_Response"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<% 
    DecimalFormat df2 = new DecimalFormat(".##");
    EtisalInquiryResponse etisalInquiryResponse = (EtisalInquiryResponse) request.getSession().getAttribute("etisalInquiryResponse");
    double tobepaid = etisalInquiryResponse.getToBepaid();
    double appliedFees = etisalInquiryResponse.getAppliedFees();
    double merchantcommission = etisalInquiryResponse.getMerchantCommission();
    double tax = etisalInquiryResponse.getTax();
    double txnValue =  appliedFees - merchantcommission;
    double txnAmount = etisalInquiryResponse.getBillAmount();
    String phoneNumber = etisalInquiryResponse.getPhoneNumber();
    String customerName = etisalInquiryResponse.getClientName();
    String endDate = etisalInquiryResponse.getEndDate();
    double originalDeductedAmount = txnAmount + appliedFees - merchantcommission;
    double serviceCommission = appliedFees + tax ;
    double deductedAmount = Double.valueOf(df2.format(originalDeductedAmount));
//    double deductedAmount = (double) df2.setRoundingMode(RoundingMode.UP);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.EtisalServiceName%></title>

    </head>
    <body class="body" >

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="EtisalContoller" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTIONEtisalPayment%>" />

        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.EtisalServiceName%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td width="50%">
                            <p align="right"><%=CONFIG.getCustomerName(session)%> : 
                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=customerName%>" style="background-color: #EDEDED; float: left;"/>
                            </p>
                            <p align="right"><%=CONFIG.EtisalAmount%> : 
                                <input readonly type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%=txnAmount%>"  onchange="onValueChanged();"  style="background-color: #EDEDED; float: left;">
                            </p>
                            <p align="right"><%=CONFIG.EtisalMSISDN%>: 
                                <input  readonly tabindex="1" id="custTopUpDalanceID" type="text"  name="<%=CONFIG.PARAM_MSISDN%>"  value="<%=phoneNumber%>" style="background-color: #EDEDED; float: left;"/>
                            </p>

                            <p align="right"><%=CONFIG.EtisalExpiryDate%> : <input type="text" name="DUE_DATE" readonly tabindex="1" id="DUE_DATE" value="<%=endDate%>" style="background-color: #EDEDED; float: left;"/></p>
                        </td>
                        <td >
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="<%=serviceCommission%>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="<%=merchantcommission%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getBalance_Diff(session)%> :<input type="text" name="Balance_Diff" value="<%=0.0%>"  readonly tabindex="6" id="Balance_Diff" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="<%=deductedAmount%>"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>

                        </td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn"  >
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