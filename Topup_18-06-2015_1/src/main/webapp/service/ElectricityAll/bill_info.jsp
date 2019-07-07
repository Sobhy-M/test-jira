<%-- 
    Document   : bill_inquiry
    Created on : 9/07/2017, 01:10:57 م
    Author     : Mustafa
--%>

<%@page import="com.masary.utils.BuildUUID"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.integration.dto.ElectricityBillsRepresentation"%>
<%@page import="com.masary.integration.dto.ElectricityInquiryResponse"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    ElectricityInquiryResponse electricityInquiryRepresentation=(ElectricityInquiryResponse)request.getSession().getAttribute("electricityInquiryRepresentation");
    

%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getElectricityServiceName(request.getSession())%></title>
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
        <div class="content_body">
            <form name="ElectricityCommission" action="Electricity_Payment_Controller" method="POST">
                <input type="hidden" name="action" value="Commession_inquiry"/>
                 <input type="hidden" name="electricityInquiryRepresentation" value="<%=electricityInquiryRepresentation%>"/>
                <table style="width: 40%">
                    
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <p align="center"><%=CONFIG.getMemberName(request.getSession())%>:  <%= electricityInquiryRepresentation.getClientName()%></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <p align="center"><%=CONFIG.GetElectronic_pay_number(request.getSession())%> : <%=electricityInquiryRepresentation.getAccountNumber() %></p>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <p align="right"><%="عنوان المشترك "%></p>
                        </td>
                        <td>
                            <input id="Address" type="text" value="<%=electricityInquiryRepresentation.getAddress()%>"  name="Address" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.ElectricityTitle(request.getSession())%>
                        </td>
                    </tr>
                    
                    <tr>
                        <td><p align="center"><%=CONFIG.getDuDate(session)%></p></td>
                       <td><p align="center"><%=CONFIG.getIndebtednessName(session)%></p></td>
                    </tr>
                    
                   
                    
                    
                        <%for (ElectricityBillsRepresentation electricityBillsRepresentation : electricityInquiryRepresentation.getBills() ) {%>
                     
                        <tr >
                        <td>
                            <p align="right"><%=  electricityBillsRepresentation.getBillDate()%></p>
                        </td>
                        <td>
                            <input id="" type="text" value="<%=electricityBillsRepresentation.getBillAmount()%>"  name="ImediaServiceCost" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                   
                        <%}%> 

                    <tr>
                        <td colspan="2" style="text-align: center">
                            <input type="submit" name="btnSubmit"  value="<%=CONFIG.getpayment(session)%>" class="Btn" >

                            <input type="button" name="btnBack"  value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1)">
                             <input type="button" value="الغاء"  id="submitbutton" onclick="history.go(-2);"  />

                        </td>
                    </tr>
                </table>
            </form>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
