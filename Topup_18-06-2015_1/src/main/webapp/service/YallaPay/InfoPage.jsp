<%-- 
    Document   : InfoPage
    Created on : Jul 9, 2017, 10:16:06 AM
    Author     : Ahmed Khaled
--%>


<%@page import="com.masary.integration.dto.YallaPayInquiryResponse"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.util.Date"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }

   
    YallaPayInquiryResponse yallaPayInquiryResponse = (YallaPayInquiryResponse) request.getSession().getAttribute("yallaPayInquiryResponse");
    String customerName = yallaPayInquiryResponse.getClientName();
    String trxId = yallaPayInquiryResponse.getTransactionId();
    String balanceType = yallaPayInquiryResponse.getBalanceType();
    int billAmount = yallaPayInquiryResponse.getBillAmount();
    double appliedFees = yallaPayInquiryResponse.getAppliedFees();
    double toBePaid = yallaPayInquiryResponse.getToBepaid();

//    double deductedAmount = (double) df2.setRoundingMode(RoundingMode.UP);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>&nbsp;<%= CONFIG.YallaPayServiceName%></title>
        <script>
            function cancel() {
                document.getElementById("HOMEFORM").submit();
            }
        </script>
        <style>
            input[type=text] {
                width: 60%;
                background-color: #EDEDED;
                float: left;
                font-size: 16px;
            }
            p.label{
                font-size: 17px;
                font-weight: bold;
            }
            p.details{
                font-size: 15px;

            }
            input.btn{
                font-size: 16px;
            }

        </style>
    </head>
    <body class="body" >

        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>

            <form id="HOMEFORM" action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="display: none;" ></form>
        <form name="dobillinquiry" action="YallaPayController" method="POST">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_YALLAPAY_PAYMENT%>" />

            <div class="content_body"  >
                <fieldset style="width: 70%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%= CONFIG.YallaPayServiceName%> - <%= CONFIG.YallaPayInfoLabel%> </font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                    <table  border="1" width="100%">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <tr>
                            <td width="50%">
                                <p align="right" class="label"><%=CONFIG.getCustomerName(session)%> : 
                                    <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=customerName%>" style="float: left;"/>
                                </p>
                                <p align="right" class="label"><%=CONFIG.YallaPayTrxID%>  
                                    <input readonly type="text" name="TRXID" tabindex="1"  id="custAmountID" value="<%=trxId%>"  onchange="onValueChanged();"  >
                                </p>
                                <p align="right" class="label"><%=CONFIG.YallaPayTrxType%> 
                                    <input  readonly tabindex="1" id="TRXTYPE" type="text"  value="<%=balanceType%>" />
                                </p>
                                <p align="right" class="label"><%=CONFIG.YallaPayTrxAmount%> 
                                    <input  readonly tabindex="1" id="TRXAMOUNT" type="text"  value="<%=billAmount%>" />
                                </p>
                                <p align="right" class="label"><%=CONFIG.YallaPayAppliedFees%>  
                                    <input type="text"  readonly tabindex="1" id="APPLIED_FEES" value="<%=appliedFees%>" />
                                </p>
                                <p align="right" class="label"><%=CONFIG.YallaPayToBePaid%>  
                                    <input type="text"  readonly tabindex="1" id="DESIRED_AMOUNT" value="<%=toBePaid%>" />
                                </p>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="3">
                                <div align="center">
                                    <input type="submit" name="btnSubmit" tabindex="1"  value="<%=CONFIG.getpayment(session)%>"   style="float: right;" class="btn">
                                    <input type="button" name="Cancel" tabindex="6"  value="<%=CONFIG.YallaPayCancelLabel%>"  onclick="cancel();" class="btn">
                                    <input type="button" name="Back" tabindex="4" value="<%=CONFIG.getBack(session)%>"   onclick="history.go(-1);" style="float: left;" class="btn">
                                </div>
                            </td>
                        </tr>
                    </table>
                    <details open="open">
                        <summary></summary>
                        <p class="details">برجاء التأكد من بيانات العملية قبل تأكيد الدفع</p>

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