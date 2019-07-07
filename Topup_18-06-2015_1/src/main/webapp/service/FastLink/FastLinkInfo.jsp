
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.integration.dto.FastLinkInquiryResponse"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>


<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>

<%
    session = request.getSession(); 
    
    FastLinkInquiryResponse fastLinkInquiryResponse=(FastLinkInquiryResponse) request.getAttribute("inquiryResponse");
    
   String customerName= fastLinkInquiryResponse.getClientName();
   double amount= fastLinkInquiryResponse.getBillAmount();
    String phoneNumber=fastLinkInquiryResponse.getPhoneNumber();
 //   String date=fastLinkInquiryResponse.getEndDate();
   double appliedFees= fastLinkInquiryResponse.getAppliedFees();
   double merchantCommission=fastLinkInquiryResponse.getMerchantCommission();
   double deductedAmount=fastLinkInquiryResponse.getTransactionAmount();
    
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Date date = new Date (fastLinkInquiryResponse.getEndDate());
	String date1 = dateFormat.format(date);

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

    <form name="doPayment" action="FastLinkPaymentController" method="POST" >
             <input type="hidden" name="validationId" value="<%=fastLinkInquiryResponse.getValidationId()%>" />
     

        <div class="content_body">
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.get_FastLinkInquiryAR(session)%>&nbsp;</font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th><%=CONFIG.getAbuElRish_Data(session)%></th>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getCustomerName(session)%> : 
                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=customerName%>" style="background-color: #EDEDED; float: left;"/>
                            </p>
                            <p align="right"><%=CONFIG.get_FastLinkAmount(session)%> : 
                                <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1" readonly id="custAmountID" value="<%=amount %>"   style="background-color: #EDEDED; float: left;">
                            </p>
                            <p align="right"><%=CONFIG.getinternetEgyptTitle(session)%>: 
                                <input  readonly tabindex="1" id="custTopUpDalanceID" type="text"  name="<%=CONFIG.PARAM_MSISDN%>"  value="<%=phoneNumber %>" style="background-color: #EDEDED; float: left;"/>
                            </p>

                            <p align="right"><%=CONFIG.FastLinkexpirationDate(session)%> : <input type="text" name="DUE_DATE" readonly tabindex="1" id="DUE_DATE" value="<%=date1 %>" style="background-color: #EDEDED; float: left;"/></p>
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getFees(session)%> :<input type="text" name="Fees" value="<%=appliedFees %>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getCommession(session)%> :<input type="text" name="Commession" value="<%=merchantCommission %>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                            <p align="right"><%=CONFIG.getDeductedAmount(session)%> :<input type="text" name="DeductedAmount" value="<%=deductedAmount %>"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>

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