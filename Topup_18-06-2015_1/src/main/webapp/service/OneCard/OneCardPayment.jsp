<%-- 
    Document   : PayLongLifeEgypt
    Created on : Mar 15, 2016, 12:25:35 PM
    Author     : Masary
--%>

<%@page import="com.masary.integration.dto.OneCardPaymentResponse"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    session = request.getSession();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    OneCardPaymentResponse payResponse = (OneCardPaymentResponse) request.getSession().getAttribute("payResponse");
    double serviceCost = payResponse.getAppliedFees();
    double tax = payResponse.getTax();
    double commission = payResponse.getMerchantCommission();
    double deductedAmount = payResponse.getTransactionAmount();
    double totalAmount = payResponse.getToBePaid();
    String txnId = payResponse.getGlobalTrxId();
    String mobile = payResponse.getOneCardAccountId().toString();

    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.ONE_CARD_SERVICE%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />

        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td><img src="./img/onecard.png"  width="100" height="60"/></td><td><img src="./img/Masary.jpg"  width="80" height="60"/></td></tr>');
                printwindow.document.write('<tr><th colspan="2">شركة مصاري</th></tr>');
                printwindow.document.write('<tr><th colspan="2"><%=CONFIG.ONE_CARD_SERVICE%></th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=txnId%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العمليه</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=time%></td><td style = "text-align: right;padding-right:25px;width:50%;">وقت العمليه</td> </tr><tr>');
                printwindow.document.write('<tr><td  style="padding-left:15px;width: 40%;"> عملية شحن</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="padding-left:15px;width: 40%;"> عملية ناجحة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=mobile%></td><td style="text-align: right;padding-right:25px;width: 50%;" >رقم الحساب</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=totalAmount%></td><td style="text-align: right;padding-right:25px;width: 50%;" >المبلغ بالجنيه</td> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >-------------------------------------------</th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >السعر يشمل الضريبة</th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >-------------------------------------------</th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >خدمة عملاء مصاري 16994</th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; ">www.e-masary.com </th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
            }
        </script>

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
        <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="font-weight: bold">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ONE_CARD_PAY%>" />
            <h2 align="center" ><%=CONFIG.ONE_CARD_SERVICE%></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >

                <tr>
                    <td style="border: none">
                        <p>رقم العمليه: </p>
                    </td>
                    <td style="border: none">
                        <input  type="text" readonly style="background-color: #EDEDED;" value="<%=txnId%>" >
                    </td>
                </tr>

                <tr>
                    <td>
                        <p><%= CONFIG.getThetotalamountpaid(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="TotalAmountPayable" value="<%=totalAmount%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getWillbededucted(session)%>:</p>
                    </td>
                    <td style="border: none">
                        <input type="text" name="DedducedAmount" value="<%=deductedAmount%>" readonly style="background-color: #EDEDED;"  >
                    </td>


                </tr>

                <tr>
                    <td>
                        <p>عمولتك: </p>
                    </td>
                    <td>
                        <input type="text" name="TotalAmountPayable" value="<%=commission%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>

                <tr>
                    <td>
                        <p><%= CONFIG.getServiceState(session)%>:</p>
                    </td>
                    <td>
                        <label><%=CONFIG.getSuccessful(session)%></label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getServiceDate(session)%>:</p>
                    </td>
                    <td>
                        <label><%=date1%></label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getServiceTime(session)%>:</p>
                    </td>
                    <td>
                        <label><%=time%></label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getPrinting(session)%>"  id="submitbutton" onclick="setDivPrint()"  />
                    </td>
                </tr>
                <tr style="display: none">
                    <td><img src="./img/onecard.png"  width="100" height="60"/></td>
                    <td><img src="./img/Masary.jpg"  width="80" height="60"/></td>
                </tr>
            </table>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
