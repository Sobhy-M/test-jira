<%-- 
    Document   : MostafaMahmoudConfirmationPage
    Created on : Jun 4, 2017, 2:11:11 PM
    Author     : Ahmed Khaled
--%>
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
<!DOCTYPE html>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <script type="text/javascript" src="img/jquery-1.11.3.min.js"></script>
        <title><%=CONFIG.getMostafaMahmoudRecieptMess1(session)%></title>
    </head>

    <body class="body" >
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include><%}%>
            </div>

            <font style="color: red; font-size: 15px;">${ErrorCode}</font>

        <form name="MostafaMahmoudConfirmation" action="MostafaMahmoudPayController" method="POST" >
            <div class="content_body"  >
                <fieldset style="width: 70%; direction: rtl;" align="right">
                    <input type="hidden" name="ServiceID" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                    <input type="hidden" name="OPERATION_ID" value="<%=request.getParameter(CONFIG.OPERATION_ID)%>" />
                    <legend align="right" ><font size="5"><%= CONFIG.getConfirm(session)%>&nbsp;<%= CONFIG.getCustomerdonationHead(session)%>&nbsp;</font><img src="img/CashIn.ico"  width="20" height="20" > </legend>               
                    <table  border="1" width="100%">
                        <th><%=CONFIG.getINFO_Required(session)%></th>
                        <th><%=CONFIG.getMerchantCommession(session)%></th>
                        <tr>
                            <td>
                                <p align="right"><%= CONFIG.getWKPhoneNumber(session)%>:<input name="PhoneNumber" readonly autocomplete="off" value="<%=request.getParameter("PhoneNumber")%>" type="text" id="PhoneNumber" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>
                                <p align="right"><%= CONFIG.getWKAmount(session)%>:<input name="Amount" readonly autocomplete="off" type="text" value="<%=request.getParameter("Amount")%>" id="Amount" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>
                            </td>
                            <td>
                                <p align="right"><%= CONFIG.getFees(session)%>:<input name="Fees" value="<%=request.getParameter("Fees")%>" readonly autocomplete="off" type="text" id="Fees" maxlength="20" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>                                                                         
                                <p align="right"><%= CONFIG.getCommessionMaan(session)%>:<input value="<%=request.getParameter("Commession")%>" readonly  name="Commession"  autocomplete="off" type="text" id="Commession" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>                                                                                      
                                <p align="right"><%= CONFIG.getTotalAmountMaan(session)%>:<input value="<%=request.getParameter("Balance_Diff")%>" readonly name="Balance_Diff"  autocomplete="off" type="text" id="Balance_Diff" maxlength="11" title="<%=CONFIG.getWKDonationType(session)%>" style="float: left;background-color: #EDEDED;"></p>
                                <p align="right"><%=CONFIG.getDeductedAmountMaan(session)%> :<input value="<%=request.getParameter("DeductedAmount")%>" type="text" name="DeductedAmount" value=""  readonly tabindex="6" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" name="btnSubmit" id="btnSubmit" tabindex="4"  value="<%=CONFIG.getpayment(session)%>" class="Btn" >
                                <input type="button" name="Back" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);">
                            </td>
                        </tr>
                    </table>
                    <details open="open">
                        <summary>

                        </summary>
                        <p><%= CONFIG.getWKThiredInstruction(session)%></p>
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
