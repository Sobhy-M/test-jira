<%@page import="com.masary.controllers.AlexWater.AlexWaterProperities"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link
            href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css"
            rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=AlexWaterProperities.serviceName%></title>
        <style>
            input[type=text] {
                width: 70%;
                background-color: #EDEDED;
                float: left;
                font-size: 16px;
            }
        </style>
    </head>
    <body class="body">
        <div>
            <%
                if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
            %>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%
            } else {
            %>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%
                }
            %>
        </div>
        <div class="content_body" >
            <form  action="AlexWaterPayment" method="POST" >
                <input type="hidden" name="validationId" value="${alexWaterInquiryResponse.validationId}">
                <table border="1" width="76%">
                    <th><%=AlexWaterProperities.billInformation%></th>     
                    <th><%=AlexWaterProperities.commissionInformation%></th>    
                    <tr>
                        <td>
                            <p align="right"> <%=AlexWaterProperities.subscriptionTitle%>: 
                                <input type="text" readonly value="${alexWaterInquiryResponse.elecNo}">
                            </p>
                            <p align="right"><%=AlexWaterProperities.agentNameString%>:  
                                <input type="text" readonly value="${alexWaterInquiryResponse.customerName}">
                            </p>
                            <p align="right"><%=AlexWaterProperities.billDateString%>: 
                                <input type="text" readonly value="${alexWaterInquiryResponse.billingrunName}">
                            </p>
                            <p align="right"><%=AlexWaterProperities.billNumberString%>:  
                                <input type="text" readonly value="${alexWaterInquiryResponse.billId}">
                            </p>
                            <p align="right"><%=AlexWaterProperities.debtString%>:  
                                <input type="text" readonly value="${alexWaterInquiryResponse.totalDueAmount}">
                            </p>
                        </td>
                        <td style="width: 50%">
                            <p align="right"><%=CONFIG.getCommessionMaan(request.getSession())%>:
                                <input type="text"  value="${alexWaterInquiryResponse.merchantCommission}"  readonly style="width: 50%">
                            </p> 
                            <p align="right"><%=CONFIG.getDeductedAmountMaan(request.getSession())%>:
                                <input type="text"  value="${alexWaterInquiryResponse.transactionAmount}"  readonly style="width: 50%">
                            </p> 
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" height="50%">
                            <div align="center">
                                <p align="right" style="color: red">
                                    برجاء العلم ان اجمالى المديونية هو اجمالى مديونيتك لدى شركة مياة الاسكندرية
                                </p>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div align="center">
                                <input type="submit" name="btnSubmit" tabindex="1"  value="<%=CONFIG.getPay(session)%>"   style="float: right;" class="btn">
                                <input type="button" name="Cancel" tabindex="6"  value="<%=AlexWaterProperities.editButtonString%>"  onclick="history.go(-1);" class="btn">
                                <input type="button" name="Back" tabindex="4" value="<%=AlexWaterProperities.cancelButtonString%>"   onclick="window.location.href = '<%=CONFIG.APP_ROOT + (String) session.getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>';" style="float: left;" class="btn">
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
            <div style="clear: both;">&nbsp;</div>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div>
    </body>
</html>