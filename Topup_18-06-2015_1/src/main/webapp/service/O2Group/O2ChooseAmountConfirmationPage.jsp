<%--
    Document   : AddCustomer.jsp
    Created on : 05/03/2017, 11:09:49 ص
    Author     : Pora
--%>

<%@page import="com.masary.integration.dto.O2groupInquiryResponse"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.masary.database.dto.Governorate"%>
<%@page import="com.masary.database.dto.Country"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.dto.CustomerServiceDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }

    O2groupInquiryResponse o2groupInquiryResponse = (O2groupInquiryResponse) request.getAttribute("o2groupInquiryResponse");
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getO2Group(session)%></title>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="./js/AddNewWalletJS/AddNewWalletJS.js"></script>
        <script src="./js/AddNewWalletJS/notify.js"></script>   
    </head>


    <BODY class="body"  >
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>

        <form name="AddCustomer" action="O2PaymentController" method="POST">
            <input type="hidden" id="ClientName" name="ClientName" value="<%= request.getParameter("ClientName")%>"/>
            <input type="hidden" id="VoucherValue" name="VoucherValue" value="<%= request.getParameter("VoucherValue")%>"/>
            <input type="hidden" id="voucherID" name="voucherID" value="<%= request.getParameter("voucherID")%>"/>
            <div class="content_body">
                <table style="width: auto">
                    <th  colspan="2"><%=CONFIG.getINFO_Required(request.getSession())%></th>
                    <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                    <tr>
                        <td colspan="2">
                            <p align="right"><%=CONFIG.getO2GroupPhoneNumber(request.getSession())%>  <input id="MSISDN" type="text" name="MSISDN" autocomplete="off" value="<%=request.getParameter("MSISDN")%>"  tabindex="2" readonly style="float: left;background-color: #EDEDED">
                            <p align="right"><%=CONFIG.getO2GroupPaidAmount(request.getSession())%>  <input id="PaidAmount" type="text" name="PaidAmount" autocomplete="off" value="<%=o2groupInquiryResponse.getToBepaid()%>" tabindex="2" readonly style="float: left;background-color: #EDEDED">
                        </td>
                        <td>
                            <p align="right"><%=CONFIG.getAgentPaymentCommission(request.getSession())%>  <input id="commession" type="text" name="commession" value="<%=o2groupInquiryResponse.getMerchantCommission()%>" autocomplete="off"  tabindex="2" readonly style="float: left;background-color: #EDEDED">
                            <p align="right"><%=CONFIG.getO2GroupDeducedAmount(request.getSession())%>  <input id="DeducedAmount" type="text" name="DeducedAmount" value="<%=o2groupInquiryResponse.getToBepaid() - o2groupInquiryResponse.getMerchantCommission()%>" autocomplete="off"  tabindex="2" readonly style="float: left;background-color: #EDEDED">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="<%=CONFIG.getConfirm(request.getSession())%>"  id="submitbutton" onclick="" />
                        </td>
                        <td>
                            <input type="button" value="<%=CONFIG.getEditting(request.getSession())%>"  id="submitbutton" onclick="history.go(-1);"  />
                        </td>

                        <td>
                            <input type="button" value="<%=CONFIG.getVC_Cancel(request.getSession())%>" id="submitbutton" onclick="history.go(-2);"  />
                        </td>
                    </tr>
                </table>
            </div><!-- End of Table Content area-->
        </form>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
