<%-- 
    Document   : MersalConfirmation
    Created on : Dec 04, 2016, 12:44:08 PM
    Author     : hammad
--%>

<%@page import="com.masary.integration.dto.OneCardInquiryResponse"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }

    OneCardInquiryResponse validationResponse = (OneCardInquiryResponse) request.getSession().getAttribute("validationResp");
    double totalAmount = validationResponse.getToBepaid();
    double fees = validationResponse.getAppliedFees();
    double commission = validationResponse.getMerchantCommission();
    double tax = validationResponse.getTax();
    double txnValue = totalAmount - fees - tax;
    double txnAmount = validationResponse.getTransactionAmount();
    String mobile = request.getSession().getAttribute("accountId").toString();

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.ONE_CARD_SERVICE%></title>
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
    </head>
    <body>
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        <form action="OneCardController" method="POST" style="font-weight: bold">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ONE_CARD_PAY%>" />
            <h2 align="center" ><%=CONFIG.ONE_CARD_SERVICE%></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                
                <tr>
                                    <td>
                                        <p>رقم حساب العميل: </p>
                                    </td>
                                    <td>
                                        <input type="text" readonly name="DonatorPhone" id="DonatorPhone" value="<%=mobile%>" style="background-color: #EDEDED;" >
                                    </td>
                                </tr>
                                
                                <tr>         
                    <td>
                        <p>المبلغ المطلوب دفعه
</p>
                    </td>
                    <td>
                        <div>
                            <div>
                                <input type="text" name="TotalAmountPayable" value="<%=totalAmount%>" readonly style="background-color: #EDEDED;" >
                                <div>
                                </div>
                                </td>

                                </tr>
                                
                                 <tr>

                    <td>
                        <p>الرسوم + الضريبه</p>
                    </td>
                    <td>
                        <input type="text" name="ServiceCost" value="<%=fees + tax%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                        
                 <tr>
                    <td>
                        <p><%=CONFIG.getCommessionMaan(session)%></p>
                    </td>
                    <td>
                        <input type="text" name="Commession" value="<%=commission%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <p>سيتم خصم: </p>
                    </td>
                    <td>
                        <div>
                            <div>
                                <input type="text" name="TheDonationVal" id="TheDonationVal" value="<%=txnAmount %>" readonly style="background-color: #EDEDED;" >
                            </div>
                        </div>
                    </td>
                </tr>
               
               
                
            <input type="hidden" name="txnValue" id="txnValue" value="<%=txnValue %>" />
                                
                                <tr>
                                    <td>
                                        <div>
                                            <div>
                                                <input type="submit" value="تأكيد"  id="submitbutton" onclick="" />
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div>
                                            <input type="button" value="تعديل"  id="submitbutton" onclick="history.go(-1);"  />
                                        </div>
                                    </td>

                                    <td>
                                        <div>
                                            <input type="button" value="الغاء"  id="submitbutton" onclick="history.go(-2);"  />
                                        </div>
                                    </td>
                                </tr>
                                </table>
                                </form>
                                <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

                                </body>
                                </html>
