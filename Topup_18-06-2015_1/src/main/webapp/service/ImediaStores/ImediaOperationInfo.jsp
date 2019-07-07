<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

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
    MasaryManager.logger.info("BillInquiry_Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    Masary_Bill_Type BTC = (Masary_Bill_Type) request.getSession().getAttribute("BTC");
    RatePlanDTO ratePlan = (RatePlanDTO) request.getSession().getAttribute("ratePlan");
        
    Double Amount = Double.parseDouble(request.getAttribute("ImediaBillValue").toString());
    Double Commession = (ratePlan.getFixedAmount()+ (Amount * ratePlan.getCommission()/ 100));
    
    Bill_Response bill_response = (Bill_Response) session.getAttribute("bill_Response");
    
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.GetImediaInquiry(request.getSession())%></title>
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
            <form name="ImediaOperationInfo" action="ImediaPaymentController" method="POST">
                <input type="hidden" name="commession" id="commession" value="<%=Commession%>" />
                <table style="width: 40%">
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.GetImediaStoreText(request.getSession())%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.GetOperationID(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="OperationID" value="<%=request.getParameter("OperationID")%>" title="<%=CONFIG.GetOperationIdTitle_Ar(request.getSession())%>" type="text" required name="OperationID" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.GetImediaBillValue(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="ImediaBillValue" type="text" value="<%=bill_response.getAMOUNT()%>" name="ImediaBillValue" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getServiceCost(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="ImediaServiceCost" type="text" value="<%=bill_response.getFEES()%>"  name="ImediaServiceCost" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
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
