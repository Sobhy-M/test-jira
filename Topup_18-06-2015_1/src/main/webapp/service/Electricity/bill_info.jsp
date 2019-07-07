<%--
    Document   : bill_info
    Created on : 01/05/2012, 07:11:19 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.GenericElectricityBill"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.masary.database.dto.ElectricityBillWithCommissionsRepresentation"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Bill_Amounts"%>
<%@page import="com.masary.database.dto.Masary_Bill_Response"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%request.setCharacterEncoding("utf-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
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
%>
<%
    session = request.getSession();
    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
    GenericElectricityBill electricityBills = (GenericElectricityBill) request.getSession().getAttribute("EBWCR");
//        GenericElectricityBill electricityBills = (GenericElectricityBill) request.getAttribute("EBWCR");

    double billValue = Double.parseDouble(request.getSession().getAttribute("billValue").toString());
    long customerId = Long.parseLong(request.getSession().getAttribute(CONFIG.PARAM_PIN).toString());
    double fees = Double.parseDouble(request.getSession().getAttribute("fees").toString());
    String participantName = request.getSession().getAttribute("participantName").toString().getBytes("utf-8").toString();
    String reformattedStr;
//    String reformattedStr = ((electricityInfo[0].getIssueDate().length() % 2 == 0) ? (electricityInfo[0].getIssueDate().substring(0, 2) + "-" + electricityInfo[0].getIssueDate().substring(2)) : (electricityInfo[0].getIssueDate().substring(0, 1) + "-" + electricityInfo[0].getIssueDate().substring(1)));
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <title><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= BTC.getStrBTCName(session)%></title>
    </head>
    <body class="body">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="dobillinquiry" action="SouthDeltaController" method="POST" >

        <input type="hidden" name="action" value="<%=CONFIG.ACTION_Delta_Electricity_pay%>" />
        <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="${serv_id}" />

        <div class="content_body"  >
            <fieldset style="width: 50%; direction: rtl;" align="right">  
                <legend align="right" ><font size="5"><%= CONFIG.getCustomerBillHead(session)%>&nbsp;<%= BTC.getStrBTCName(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                <table  border="1" width="100%">
                    <th style="text-align: center" colspan="2"><%=CONFIG.getINFO_Required(session)%></th>
                    <tr> <td><p align="right"><%=CONFIG.getMemberNumber(session)%>:</p></td><td><input type="text" name="BILLING_ACCOUNT" readonly tabindex="1" id="BILLING_ACCOUNT" value="<%= electricityBills.getAcquiredBills().get(0).getParticipantNumber()%>" style="background-color: #EDEDED;float: right;"/>  </td></tr>      
                    <tr> <td><p align="right"> <%=CONFIG.getMemberName(session)%>:</p></td><td><input type="text" name="Customer_name" readonly tabindex="1" id="Customer_name"  value="<%= electricityBills.getAcquiredBills().get(0).getParticipantName()%>" style="background-color: #EDEDED;float: right;"/></td></tr>

                    <%
                        reformattedStr = "";
                        for (int i = 0; i < 1; i++) {
                            reformattedStr = (electricityBills.getAcquiredBills().get(i).getIssueDate());
                            StringBuilder date = new StringBuilder();
                            SimpleDateFormat fromUser = new SimpleDateFormat("MM-yyyy");
                            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");
                            try {
                                reformattedStr = String.format("%06d", Integer.parseInt(reformattedStr));
                                date.append(reformattedStr);
                                date.insert(2, "-");
                                reformattedStr = myFormat.format(fromUser.parse(date.toString()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                    %>
                    <tr> <td><p align="right"><%=CONFIG.getbillDate(session)%>:</p></td><td><input type="text" name="DUE_DATE" readonly tabindex="1" id="DUE_DATE" value="<%=reformattedStr%>" style="background-color: #EDEDED;float: right; "/></td></tr>
                    <tr> <td> <p align="right"><%=CONFIG.getbillAmount(session)%>:</p></td><td><input type="text" name="<%=CONFIG.AMOUNT%>" tabindex="1"  id="custAmountID" value="<%= electricityBills.getAcquiredBills().get(0).getBillValue()%>"   style="background-color: #EDEDED;float: right; "></td></tr> 
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn"  >
                            <input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                        </td>
                    </tr>
            </fieldset>
            </table>
            <%
                if (i == 0) {
            %>
            <%
                }
            %>
            <%
                }
            %>
            <details open="open">
                <summary></summary>
                    <%=CONFIG.getmakeSureParticipantNumber(session)%>
            </details>
            </fieldset> 
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>