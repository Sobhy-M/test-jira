<%-- 
    Document   : electricity 
    Created on : july 9, 2017, 4:11:11 PM
    Author     :Mustafa 
--%>
<%@page import="com.masary.integration.dto.ElectricityInquiryResponse"%>
<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
<%@page import="com.masary.database.dto.DonationRespponseDto"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    String rolePage = CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp";
    ElectricityInquiryResponse electricityInquiryRepresentation = (ElectricityInquiryResponse) request.getSession().getAttribute("electricityInquiryRepresentation");
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <script type="text/javascript" src="img/jquery-1.11.3.min.js"></script>
        <title><%= CONFIG.getElectricityServiceName(session)%></title>
    </head>

    <body class="body" onload="onValueChanged();">
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include><%}%>
            </div>

        <form name="Electricity_Payment" action="Electricity_Payment_Controller" method="POST" >
          <input type="hidden" name="action" value="<%=CONFIG.ACTION_Delta_Electricity_payConfirm %>" />
            <div class="content_body"  >
                <fieldset style="width: 70%; direction: rtl;" align="right">
                    <table  border="1" width="100%">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=CONFIG.getMerchantCommession(session)%></th>
                        <tr>
                            <td>
                                <p align="right"><%= CONFIG.GetElectronic_pay_number(session)%>:<input name="PhoneNumber" readonly autocomplete="off" value="<%= electricityInquiryRepresentation.getAccountNumber() %>" type="text" id="PhoneNumber" maxlength="11"  style="float: left;background-color: #EDEDED;"></p>
                                <p align="right"><%= CONFIG.getCustomerName(session)%>:<input name="Amount" readonly autocomplete="off" type="text" value="<%= electricityInquiryRepresentation.getClientName()%>" id="name" maxlength="11"  style="float: left;background-color: #EDEDED;"></p>
                                <p align="right"><%= CONFIG.getDuDate(session)%>:<input name="Date" readonly autocomplete="off" type="text" value="<%= electricityInquiryRepresentation.getBillDate()%>" id="date" maxlength="11"  style="float: left;background-color: #EDEDED;"></p>
                                <p align="right"><%= CONFIG.GetImediaBillValue(session)%>:<input name="Date" readonly autocomplete="off" type="text" value="<%= electricityInquiryRepresentation.getBillAmount()%>" id="Amount" maxlength="11" style="float: left;background-color: #EDEDED;"></p>
                                <p align="right"><%= CONFIG.TotalAmount%>:<input name="Date" readonly autocomplete="off" type="text" value="<%= electricityInquiryRepresentation.getToBepaid()%>" id="TotalAmount" maxlength="11" style="float: left;background-color: #EDEDED;"></p>
                            </td>
                            <td>
                                <p align="right"><%= CONFIG.getMerchantCommession(session)%>:<input value="<%= electricityInquiryRepresentation.getMerchantCommission() %>" readonly  name="Commession"  autocomplete="off" type="text" id="Commession" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>                                                                                      
                                 <br /> 
                                <p align="right"><%= CONFIG.getDeductedAmount(session)%>:<input value="<%= electricityInquiryRepresentation.getTransactionAmount()%>" readonly name="Balance_Diff"  autocomplete="off" type="text" id="Balance_Diff" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center">
                                <input type="submit" name="btnSubmit" value="<%=CONFIG.getpayment(session)%>" class="Btn" >
                                <input type="button" name="Editting" tabindex="1" value="<%=CONFIG.getEditting(session)%>"  onclick="history.go(-2);">
                                <input type="button" value="الغاء"  id="submitbutton" onclick="window.location.href='<%=rolePage %>';"  />
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
