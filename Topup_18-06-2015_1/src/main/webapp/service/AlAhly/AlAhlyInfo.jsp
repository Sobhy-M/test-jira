<%-- 
    Document   : EtisalInfo
    Created on : Mar 28, 2017, 2:12:03 PM
    Author     : Ahmed Khaled
--%>


<%@page import="com.masary.integration.dto.AlAhlyResponse"%>
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
    AlAhlyResponse alAhlyInquiryResponse = (AlAhlyResponse) request.getSession().getAttribute("inquiryCommessionResponse");
    double tobepaid = alAhlyInquiryResponse.getToBepaid();
    double appliedFees = alAhlyInquiryResponse.getAppliedFees();
    double merchantcommission = alAhlyInquiryResponse.getMerchantCommission();
    double tax = alAhlyInquiryResponse.getTax();
    double txnAmount = alAhlyInquiryResponse.getInstallmentAmount();
    String customerName = alAhlyInquiryResponse.getCustomerName();
    String phoneNumber=alAhlyInquiryResponse.getCustomerNumber();
    double serviceCommission = appliedFees + tax;
    double originalDeductedAmount = txnAmount + serviceCommission - merchantcommission;
    double deductedAmount = Double.valueOf(df2.format(originalDeductedAmount));
    String customerPoNumber = alAhlyInquiryResponse.getCustomerPoNumber();
    String rolePage = CONFIG.APP_ROOT + (String) session.getAttribute(CONFIG.PARAM_ROLE) + ".jsp";
    String dueDateString = null;
    
    try{
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    Date dueDate = new Date(alAhlyInquiryResponse.getDueDate());
    dueDateString = dt.format(dueDate);
    }catch(Exception e)
    {
        MasaryManager.logger.error(e.getMessage(), e);
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.ALAhlyServiceName %></title>

    </head>
    <body class="body" >

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="AlAhlyPaymentController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTIONEtisalPayment%>" />
        
        <input type="hidden" name="customerPoNumber" id="customerPoNumber" value="<%=customerPoNumber %>" />
        
        <div class="content_body"  >
            <fieldset style="width: 70%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.ALAhlyServiceName %></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <th colspan="2"><%=CONFIG.getMerchantCommession(session)%></th>
                    <tr>
                        <td width="50%">
                            <p align="right"><%=CONFIG.getCustomerName(session)%> : 
                                <input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=customerName%>" style="background-color: #EDEDED; float: left;"/>
                            </p>
                            <p align="right"><%=CONFIG.ALAhalyAmount%> : 
                                <input readonly type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%=txnAmount%>"  onchange="onValueChanged();"  style="background-color: #EDEDED; float: left;">
                            </p>
                            <p align="right"><%=CONFIG.getVoucherCustTo(session)%>: 
                                <input  readonly tabindex="1" id="custTopUpDalanceID" type="text"  name="<%=CONFIG.PARAM_MSISDN%>"  value="<%=phoneNumber%>" style="background-color: #EDEDED; float: left;"/>
                            </p>
                            
                            <p align="right">تاريخ الاستحقاق: <input type="text" name="dueDate" value="<%=dueDateString  %>"  readonly tabindex="5" id="dueDate" style="background-color: #EDEDED; float: left;"/></p> 
                        </td>
                        <td colspan="2">
                            
                            <p align="right"><%=CONFIG.getAgentPaymentCommission(session)%> :<input type="text" name="Fees" value="<%=merchantcommission%>"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/></p> 
                            <p align="right"><%=CONFIG.getDeductedAmountMaan(session)%> :<input type="text" name="Commession" value="<%=deductedAmount%>"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/></p> 
                        </td>
                    </tr>

                    <tr>
                        <td style="text-align: right">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn"  ></td>
                        
                        <td style="text-align: right">
                            <input type="button" name="btnBack"  value="<%=CONFIG.getEditting(session)%>" class="Btn" onclick="history.go(-1)">
                        </td>
                        <td style="text-align: left;"><input type="button" name="btncancel" onclick="window.location.href = '<%=rolePage%>';" tabindex="3" value="<%=CONFIG.GetClose(session)%>" class="Btn"></td>

                 
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