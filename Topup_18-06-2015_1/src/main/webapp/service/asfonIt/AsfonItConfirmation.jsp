<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>

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
    MasaryManager.logger.info("Edu centers Confirmation Page");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    String serviceName = "اصفون للتدريب و تقنية المعلومات";
    
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getEduCentersServic(request.getSession())%></title>
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
             
        <div class="content_body">
            <form name="AsfonController" action="AsfonController" method="POST">
                <input type="hidden" name="<%=CONFIG.itemId%>" id="itemId" value="<%= request.getParameter(CONFIG.itemId) %>"/>
                <input type="hidden" name="action" value="Reserve_Exam" />
                <table>

                    <tr>
                        <td  style="text-align: center"><%=CONFIG.getAgentPaymentInfo(session)%></td>
                        <td  style="text-align: center"><%=CONFIG.getAgentPaymentComputeCommission(session)%></td>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <p align="right"><%=CONFIG.getExam(request.getSession())%></p>
                                    </td>
                                    <td>
                                        <input id="EduCode" value="<%=request.getParameter("itemCode")%>" maxlength="30" title="<%=CONFIG.getExam(request.getSession())%>"   type="text" required name="EduCode"   readonly style="background-color: #EDEDED;">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p><%= CONFIG.getAmountFees(session)%></p>
                                    </td>
                                    <td>
                                        <input name="EduAmount" value="<%=request.getParameter(CONFIG.AMOUNT)%>" autocomplete="off" id="EduAmount" type="text" required title="<%=CONFIG.getEduAmountTitle(session)%>" readonly style="background-color: #EDEDED;">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <p align="right"><%=CONFIG.getStudetNId(request.getSession())%></p>
                                    </td>
                                    <td>
                                        <input id="nationalId" readonly maxlength="14" title="<%=CONFIG.getStudetNId(request.getSession())%>" type="text" value="<%=request.getParameter("nationalId")%>" name="nationalId" autocomplete="off" autofocus style="background-color: #EDEDED;">
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            <table>
                                <tr>
                                    <td style="border: none ; background-color: transparent">
                                        <p><%=CONFIG.getAgentPaymentCommission(session)%>:</p>
                                    </td>
                                    <td style="border: none ; background-color: transparent">
                                        <input readonly style="padding-left: auto;background-color: #EDEDED" name="commission" readonly id="commission" value="<%=request.getParameter("commission")%>"  autocomplete="off" maxlength="100" type="text">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border: none ; background-color: transparent">
                                        <p><%=CONFIG.getAgentPaymentWillDeducted(session)%>:</p>
                                    </td>
                                    <td style="border: none ; background-color: transparent">
                                        <input  style="padding-left: auto;background-color: #EDEDED" name="payedAmount" id="payedAmount" value="<%=request.getParameter("payedAmount")%>"  autocomplete="off" maxlength="100" type="text" >
                                    </td>
                                </tr>
                            </table>
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
