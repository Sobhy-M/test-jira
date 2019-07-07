<%-- 
    Document   : ConfirmationPaymentPage
    Created on : Jul 5, 2017, 11:06:45 AM
    Author     : Ahmed Khaled
--%>



<%@page import="com.masary.integration.dto.SmsMisrRequest"%>
<%@page import="com.masary.integration.dto.SmsMisrInquiryDTO"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.util.Date"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>


<%
    SmsMisrInquiryDTO smsMisrInquiryResponse = (SmsMisrInquiryDTO) request.getSession().getAttribute("smsMisrInquiryResponse");
    SmsMisrRequest smsMisrRequest = (SmsMisrRequest) request.getSession().getAttribute("smsMisrRequest");
    String customerCode = smsMisrRequest.getCustomerCode();
    String confirmationCode = smsMisrInquiryResponse.getConfirmationCode();
    double toBePaid = smsMisrInquiryResponse.getToBepaid();
    double commission = smsMisrInquiryResponse.getMerchantCommission();
    double deductedAmount = smsMisrInquiryResponse.getTransactionAmount();
    String transactionId = smsMisrInquiryResponse.getProviderTransactionId();
    String rolePage = CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.SMSMISR_SERVICE_NAME%></title>
        <style>
            input[type=text] {
                width: 50%;
                background-color: #EDEDED;
                float: right;
                font-size: 16px;
            }
            p{
                font-size: 13.7px;
                font-weight: bold;
            }
            input.btn{
                font-size: 16px;
            }

        </style>
    </head>
    <body class="body" >

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="SMSMISRController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_SMSMISR_PAYMENT%>" />

        <div class="content_body"  >
            <fieldset style="width: 70%;  direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.SMSMISR_SERVICE_NAME%></font></legend>               
                <table  border="1" width="100%" style="height: available">
                    <th><%=CONFIG.SMSMISR_Confirmation_Label%></th>
                    <th><%=CONFIG.ESED_Confirmation_Commission_Label%></th>
                    <tr>
                        <td width="40%">
                            <p align="right"><%=CONFIG.SMSMISR_Customer_Code%> :
                                <input type="text" name="customerCode" readonly tabindex="1"  value="<%=customerCode%>" style="background-color: #EDEDED; float: left; width: 60%;"/>
                            </p>
                            <p align="right"><%=CONFIG.SMSMISR_Confirmation_Code%> :
                                <input type="text" name="ConfirmationCode" readonly tabindex="1"  value="<%=confirmationCode%>" style="background-color: #EDEDED; float: left; width: 60%;"/>
                            </p>

                            <p align="right"><%=CONFIG.SMSMISR_Confirmation_DesiredAmount1%><br><%=CONFIG.SMSMISR_Confirmation_DesiredAmount2%> :
                                <input readonly type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"   value="<%=toBePaid%>"   style="background-color: #EDEDED; float: left; width: 60%;">
                            </p>
                            <input readonly type="text" name="transactionId" value="<%=transactionId%>"   style="display: none">
                        </td>
                        <td width="35%">
                            <p align="right"><%=CONFIG.SMSMISR_Commission%> :<input type="text" name="Fees" value="<%=commission%>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.SMSMISR_Deducted_Amount%> :<input type="text" name="Commession" value="<%=deductedAmount%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">
                            <div align="center">
                                <input type="submit" name="btnSubmit" tabindex="1"  value="<%=CONFIG.getPay(session)%>" style="float: right" class="Btn"  >
                                <input type="button" name="Back" tabindex="2" value="تعديل"  onclick="history.go(-1);">
                                <input type="button" name="btnBack" tabindex="3"  value="الغاء" onclick="window.location.href = '<%=rolePage%>';"  class="Btn" style="float: left;">
                            </div>

                        </td>
                    </tr>
                </table>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>