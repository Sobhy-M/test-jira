<%-- 
    Document   : ConfirmationPaymentPage
    Created on : Jul 5, 2017, 11:06:45 AM
    Author     : Ahmed Khaled
--%>



<%@page import="com.masary.integration.dto.EsedInquiryResponse"%>
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
    EsedInquiryResponse esedInquiryResponse = (EsedInquiryResponse) request.getSession().getAttribute("esedInquiryResponse");
    double appliedFees = esedInquiryResponse.getAppliedFees();
    double toBePaid = esedInquiryResponse.getToBepaid();
    double deductedAmount = esedInquiryResponse.getTransactionAmount();
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
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.EtisalServiceName%></title>
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

    <form name="dobillinquiry" action="ESEDPaymentController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_ESED_PAYMENT%>" />
        <input type="hidden" name="NATIONALCODENUMBER" value="<%=nationalCodeNumber%>" />

        <div class="content_body"  >
            <fieldset style="width: 70%;  direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.ESED_Confirmation%></font></legend>               
                <table  border="1" width="100%" style="height: available">
                    <th><%=CONFIG.ESED_Confirmation_Label%></th>
                    <th><%=CONFIG.ESED_Confirmation_Commission_Label%></th>
                    <tr>
                        <td width="40%">
                            <p align="right"><%=CONFIG.ESED_ClientKey_National%> / <%=CONFIG.ESED_ClientKey%> 
                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=nationalCodeNumber%>" style="background-color: #EDEDED; float: left; width: 60%;"/>
                            </p>
                            <p align="right"><%=CONFIG.ESED_Confirmation_DesiredAmount1%><br><%=CONFIG.ESED_Confirmation_DesiredAmount2%><br><%=CONFIG.ESED_Confirmation_DesiredAmount3%>:
                                <input readonly type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%=toBePaid%>"  onchange="onValueChanged();"  style="background-color: #EDEDED; float: left; width: 60%;">
                            </p>
                        </td>
                        <td width="35%">
                            <p align="right"><%=CONFIG.ESED_Confirmation_Commission%> :<input type="text" name="Fees" value="<%=appliedFees%>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.ESED_Confirmation_DeductedAmount%> :<input type="text" name="Commession" value="<%=deductedAmount%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 

                        </td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getConfirm(session)%>" class="Btn"  >
                            <input type="button" name="Back" tabindex="3" value="<%=CONFIG.ESED_Confirmation_Cancel%>"  style="float: left" onclick="history.go(-1);">
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