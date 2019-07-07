<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.eFinance_Inq_Res_amounts"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
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
    //  MasaryManager.logger.info("BillInquiry_Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();

    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    // Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
    RatePlanDTO ratePlan = (RatePlanDTO) request.getSession().getAttribute("ratePlan");

    //Double Amount = Double.parseDouble(request.getAttribute("ImediaBillValue").toString());
    //Double Commession = (ratePlan.getFixedAmount()+ (Amount * ratePlan.getCommission()/ 100));
    Bill_Response bill_response = (Bill_Response) session.getAttribute("bill_Response");

%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.GetElectricity_Inquiry(request.getSession())%></title>
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
            <form name="ElectricityInfo" action="Electricity_PaymentController" method="POST">
                <input type="hidden" name="action" value="Commession_inquiry"/>
                <input type="hidden" name="Fees" value="<%= bill_response.getFEES()%>"/>
                <input type="hidden" name="COMMESSION" value="<%= bill_response.getCOMMESSION()%>"/>
                <input type="hidden" name="Bill_Date" value="<%= bill_response.getBILL_DATE()%>"/>
                <table style="width: 40%">
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.GetElectricity_Inquiry(request.getSession())%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getAccountNumber(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="AccountNumber" value="<%=request.getParameter("AccountNumber")%>"  type="text" required name="AccountNumber" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getMemberName(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="CUSTOMER_NAME" type="text" value="<%= bill_response.getCUSTOMER_NAME()%>" name="CUSTOMER_NAME" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%="عنوان المشترك "%></p>
                        </td>
                        <td>
                            <input id="Address" type="text" value="<%=bill_response.getADDRESS()%>"  name="Address" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td><p align="right"><%=CONFIG.getOldestBill(session)%> : </p></td>
                        <td>  <input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" readonly style="background-color: #EDEDED;" value="${bill_Response.AMOUNT}" required >

                    </tr>
                </table>
                <table width="40%" style="border: 1px #000000 solid;margin: 4px;">
                    <%for (eFinance_Inq_Res_amounts amountObj : bill_response.getAmounts()) {%>

                    <tr style="border: 1px #000000 solid">
                        <td style="border: 1px #000000 solid" >
                            <p align="right" ><%="فاتورة"%>&nbsp;<%=  amountObj.getMonth()%></p>
                        </td>
                        <td style="border: 1px #000000 solid" >
                            <input id="" type="text" value="<%=amountObj.getAmt()%>"  name="ImediaServiceCost" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>

                    <%}%> 

                    <tr>
                        <td style="text-align: right">
                            <input type="submit" name="btnSubmit"  value="<%=CONFIG.getpayment(session)%>" class="Btn" >
                        </td>
                        <td style="text-align: left">
                            <input type="button" name="btnBack"  value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1)">
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
