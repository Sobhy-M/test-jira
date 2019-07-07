<%-- 
    Document   : InfoPage
    Created on : Jul 5, 2017, 11:05:46 AM
    Author     : Ahmed Khaled
--%>

<%@page import="java.math.RoundingMode"%>
<%@page import="com.masary.integration.dto.EsedInquiryResponse"%>
<%@page import="java.util.Date"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>


<%
    DecimalFormat df2 = new DecimalFormat(".##");
    EsedInquiryResponse esedInquiryResponse = (EsedInquiryResponse) request.getSession().getAttribute("esedInquiryResponse");
    double txnAmount = esedInquiryResponse.getServiceAmount();
    String dueDate = esedInquiryResponse.getDueDate().toString();
    
    String customerName = esedInquiryResponse.getName();
    
     String nationalCodeNumber = "";
    if (esedInquiryResponse.getNationalId() == null) {
        nationalCodeNumber = esedInquiryResponse.getCodeNumber();
    } else {
        nationalCodeNumber = esedInquiryResponse.getNationalId();
    }


//    double deductedAmount = (double) df2.setRoundingMode(RoundingMode.UP);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= CONFIG.ESED_Confirmation_Label%></title>
        <style>
            input[type=text] {
                width: 50%;
                background-color: #EDEDED;
                float: right;
                font-size: 16px;
            }
            p{
                font-size: 17px;
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

    <form name="dobillinquiry" action="ESEDPaymentController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_ESED_PAYMENT_CONFIRMATION%>" />

        <div class="content_body"  >
            <fieldset style="width: 45%; direction: rtl;" align="right">  
                <legend align="center" ><font size="5"><%= CONFIG.ESED_Info_Lavel%></font></legend>               
                <table  border="1" width="100%" align="right" style="float: right;">
                    <tr>
                        <td width="50%" align="right">
                            <div align="right">
                                <p align="right"><%=CONFIG.getCustomerName(session)%> : 
                                    <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=customerName%>" style="background-color: #EDEDED; float: left;"/>
                                </p>
                                <p align="right"><%=CONFIG.ESED_ClientKey_National%> / <%=CONFIG.ESED_ClientKey%>  
                                    <input readonly type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%=nationalCodeNumber%>"  onchange="onValueChanged();"  style="background-color: #EDEDED; float: left;">
                                </p>
                                <p align="right"><%=CONFIG.ESED_DueDate%>: 
                                    <input  readonly tabindex="1" id="DUE_DATE" type="text"  name="DUE_DATE"  value="<%=dueDate%>" style="background-color: #EDEDED; float: left;"/>
                                </p>

                                <p align="right"><%=CONFIG.ESED_TxnAmount%> : <input type="text" name="TRXAMOUNT" readonly tabindex="1" id="TRXAMOUNT" value="<%=txnAmount%>" style="background-color: #EDEDED; float: left;"/></p>
                            </div>
                        </td>

                    </tr>

                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="btn"  >
                            <input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);" class="btn">
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