<%-- 
    Document   : PartialConfirmationPage
    Created on : Jul 12, 2017, 1:29:37 PM
    Author     : user
--%>

<%@page import="com.masary.integration.dto.FMFPaymentResponse"%>
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
    DecimalFormat df2 = new DecimalFormat(".##");
    //total payment
    FMFPaymentResponse fmfPaymentResponse = (FMFPaymentResponse) request.getSession().getAttribute("fmfPaymentResponse");
    String customerCode = fmfPaymentResponse.getCustomerCode();
    String customerName = fmfPaymentResponse.getCustomerName();
    String installmentDueDate = fmfPaymentResponse.getInstallmentDueDate();
    double amount = fmfPaymentResponse.getInstallmentAmount();
    double appliedFees = fmfPaymentResponse.getAppliedFees();
    double toBePaid = fmfPaymentResponse.getToBepaid();
    Long identificationNo = fmfPaymentResponse.getIdentificationNo();
    double merchantCommission = fmfPaymentResponse.getMerchantCommission();

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.FMF_SERVICE_NAME%></title>
        <style>
            input[type=text] {
                width: 45%;
                background-color: #EDEDED;
                float: left;
                padding-left: 40px;
                margin-left: 50px;
            }
            p{
                font-size: 17px;
                margin-right: 10px;
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

    <form name="dobillinquiry" action="FMFPaymentController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_FMF_PAYMENT%>" />
        <input type="hidden" name="IDENTIFICATIONNO" value="<%=identificationNo%>" />
        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="4"><%= CONFIG.FMF_SERVICE_NAME%></font><img src="img/CashIn.ico"  width="20" height="20" ></legend>               
                <table  border="1" width="100%">
                    <tr>
                        <td width="50%">
                            <p align="right"><%=CONFIG.FMF_CUSTOMER_CODE%> : 
                                <input type="text" name="CUSTOMER_CODE" readonly tabindex="1" id="CUSTOMER_CODE" value="<%=customerCode%>" />
                            </p>
                            <p align="right"><%=CONFIG.FMF_CUSTOMER_NAME%> : 
                                <input readonly type="text" name="CUSTOMER_NAME" tabindex="1"  id="CUSTOMER_NAME" value="<%=customerName%>"  onchange="onValueChanged();"  >
                            </p>
                            <p align="right"><%=CONFIG.FMF_DUE_DATE%>: 
                                <input  readonly tabindex="1" id="DUE_DATE" type="text"  name="DUEDATE"  value="<%=installmentDueDate%>" />
                            </p>

                            <p align="right">
                                <%=CONFIG.FMF_TO_BE_PAID%> : 
                                <input type="text" name="AMOUNT" readonly tabindex="1" id="AMOUNT" value="<%=amount%>" />
                            </p>

                            <p align="right">
                                <%=CONFIG.FMF_APPLIED_FEES%> : 
                                <input type="text" name="APPLIEDFEES" readonly tabindex="1" id="APPLIEDFEES" value="<%=appliedFees%>" />
                            </p>
                            <p align="right">
                                عمولة التاجر : 
                                <input type="text" name="APPLIEDFEES" readonly tabindex="1" id="APPLIEDFEES" value="<%=merchantCommission%>" />
                            </p>

                            <p align="right">
                                <%=CONFIG.FMF_TOTAL_TOBEPAID%> : 
                                <input type="text" name="TOBEPAID" readonly tabindex="1" id="TOBEPAID" value="<%=toBePaid%>" />
                            </p>

                        </td>

                    </tr>


                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn"  >
                            <input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
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

