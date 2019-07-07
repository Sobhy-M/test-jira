
<%-- 
    Document   : ValuInfo
    Created on : Oct 29, 2017, 1:52:57 PM
    Author     : amira
--%>
<%@page import="com.masary.utils.BuildUUID"%>
<%@page import="com.masary.integration.dto.ValuInquiryResponse"%>
<%@page import="java.math.RoundingMode"%>
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

    ValuInquiryResponse valuInquiryResponse = (ValuInquiryResponse) request.getSession().getAttribute("inquiryCommessionResponse");
    String phoneNumber = valuInquiryResponse.getMobileNo();
    double paidAmount = Double.parseDouble(request.getParameter("paidAmount"));
    double merchantCommission = valuInquiryResponse.getMerchantCommission();
    String inquiryReferenceId = valuInquiryResponse.getInquiryReferenceId();
%>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= CONFIG.ValuServiceName%></title>

    </head>
    <body class="body" >

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="ValuPaymentController" method="POST" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_VALU_Payment%>" />
        <input type="hidden" name="InquiryReferenceId" id="inquiryReferenceId" value="<%=inquiryReferenceId%>" />

        <div class="content_body"  >
            <fieldset style="width:47%; direction: rtl;" align="right">  
                <legend align="center" ><font size="5"><%= CONFIG.ValuPaymentConfirmation%></font></legend>               
                <table  border="1" width="100%">
                    <th><%=CONFIG.getINFO_Required(session)%></th>
                    <tr>
                        <td width="50%">
                            <p align="right"><%=CONFIG.getMobileNoAr(session)%> : 
                                <input type="text" name="phoneNumber" readonly tabindex="1" id="phoneNumber" value="<%=phoneNumber%>" style="background-color: #EDEDED; float: left;"/>
                            </p>

                            <p align="right"><%=CONFIG.getmerchantCommissionAR(session)%> :
                                <input  readonly tabindex="1" id="merchantCommission" type="text"  name="merchantCommission"  value="<%=merchantCommission%>" style="background-color: #EDEDED; float: left;"/>
                            </p>


                            <p align="right"><%=CONFIG.getPaidAmountAr(session)%>: 
                                <input  readonly tabindex="1" id="paidAmount" type="text"  name="paidAmount"  value="<%=paidAmount%>" style="background-color: #EDEDED; float: left;"/>
                            </p>

                        </td>

                    </tr>

                    <tr>
                        <td style="text-align: right">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getPaymentAr(session)%>" class="Btn"  >
                            <input type="button" name="btncancel" onclick="goBack()" tabindex="3" value="<%=CONFIG.getBackAr(session)%>" class="Btn"></td>



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