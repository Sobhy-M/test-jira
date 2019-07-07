<%-- 
    Document   : InfoPage
    Created on : Jul 12, 2017, 12:58:42 PM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.integration.dto.FMFInquiryResponse"%>
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
    FMFInquiryResponse fmfInquiryResponse = (FMFInquiryResponse) request.getSession().getAttribute("fmfInquiryResponse");
    String customerCode = fmfInquiryResponse.getCustomerCode();
    String customerName = fmfInquiryResponse.getCustomerName();
    String installmentDate = fmfInquiryResponse.getInstallmentDueDate();
    double installmentAmount = fmfInquiryResponse.getInstallmentAmount();
    Long identificationNo = fmfInquiryResponse.getIdentificationNo();

//    double deductedAmount = (double) df2.setRoundingMode(RoundingMode.UP);
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
            input[type=number] {
                width: 2px;
                background-color: #EDEDED;
                float: left;
                padding-left: 150px;
                margin-left: 160px;
                text-align: right;
            }
            p{
                font-size: 17px;
                margin-right: 20px;
                font-weight: bold;
            }
        </style>
        <script>
            window.onload = onRadioButtonChange;
            
            function onRadioButtonChange() {

                var fullIsChecked = document.getElementById("FULLRADIO").checked;
                var partialIsChecked = document.getElementById("PARTIALRADIO").checked;

                if (partialIsChecked === true) {
                    document.getElementById("PARTIALPAYMENT").disabled = false;
                    document.getElementById("PARTIALPAYMENT").style.backgroundColor="#ffffff";
                } else if (fullIsChecked === true) {
                    document.getElementById("PARTIALPAYMENT").disabled = true;
                    document.getElementById("PARTIALPAYMENT").style.backgroundColor="#EDEDED";
                    document.getElementById("PARTIALPAYMENT").value="";

                }
            }



        </script>
    </head>
    <body class="body" >

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="FMFPaymentController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_FMF_CONFIRMATION%>" />
        <input type="hidden" name="IDENTIFICATIONNO" value="<%=identificationNo%>" />
        <div class="content_body"  >
            <fieldset style="width: 50%; direction: rtl;" align="right">  
                <legend align="right" ><font size="4"><%= CONFIG.FMF_SERVICE_NAME%></font><img src="img/CashIn.ico"  width="20" height="20" ></legend>               
                <table  border="1" width="100%">
                    <tr>
                        <td width="50%">
                            <p align="right"><%=CONFIG.FMF_CUSTOMER_CODE%> : 
                                <input type="text" name="CUSTOMERCODE" readonly tabindex="1" id="CUSTOMER_CODE" value="<%=customerCode%>" />
                            </p>
                            <p align="right"><%=CONFIG.FMF_CUSTOMER_NAME%> : 
                                <input readonly type="text" name="CUSTOMER_NAME" tabindex="1"  id="CUSTOMER_NAME" value="<%=customerName%>"  onchange="onValueChanged();"  >
                            </p>
                            <p align="right"><%=CONFIG.FMF_DUE_DATE%> : 
                                <input  readonly tabindex="1" id="DUE_DATE" type="text"  name="DUE_DATE"  value="<%=installmentDate%>" />
                            </p>

                            <p align="right">
                                <%=CONFIG.FMF_INSTALLMENT_AMOUNT%> : 
                                <input type="text" name="INSTALLMENTAMOUNT" readonly tabindex="1" id="INSTALLMENT_AMOUNT" value="<%=installmentAmount%>" />
                            </p>

                            <hr size="10" style="background-color: #4f6b72;width: 101%">

                            <p align="right">
                                <input type="radio" name="radio"  id="FULLRADIO"  checked onclick="onRadioButtonChange();">
                                <%=CONFIG.FMF_FULL_PAYMENT%>
                            </p>
                            <p align="right">
                                <input type="radio" name="radio"  id="PARTIALRADIO" onclick="onRadioButtonChange();" >
                                <%=CONFIG.FMF_PARTIAL_PAYMENT%>
                                <input id="PARTIALPAYMENT" type="text"  style="text-align:right;" dir="ltr" name="PARTIALPAYMENT"  tabindex="2" size="12" >

                            </p>

                        </td>

                    </tr>


                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getConfirm(session)%>" class="Btn"  >
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
