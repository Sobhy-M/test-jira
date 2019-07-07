<%-- 
    Document   : Orange croporate bills payment
    Created on : Oct 29, 2017, 1:53:32 PM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="Bundle">


    <!DOCTYPE html>
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="messagePrint4"></fmt:message></title>
            <script>
                function setDivPrint() {

                    var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                    printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 400px" >');
                    printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/orangeCroporate.jpg"  width="200" height="60"></td></tr>');
                    printwindow.document.write('<tr><th colspan="2"><fmt:message key="OrangeCorpBill"></fmt:message></th></tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporatePaymentResponse.ledgerTrxId}</td><td style="text-align: right;padding-right:25px;width: 50%;">:<fmt:message key="TrxId"></fmt:message></td> </tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${date}  ${time}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:<fmt:message key="TrxDateAndTime"></fmt:message></td></tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporatePaymentResponse.accountId}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >: <fmt:message key="CustomerId"></fmt:message></td></tr>');
                    printwindow.document.write('<tr><th colspan="2">--------------------------------------------------------</th></tr>');

                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporatePaymentResponse.msisdn}</td><td style = "text-align: right;padding-right:25px;width:50%;">:<fmt:message key="MobileNumber"></fmt:message> </td> </tr><tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporatePaymentResponse.accountNumber}</td><td style = "text-align: right;padding-right:25px;width:50%;">:<fmt:message key="CorporateAccountNumber"></fmt:message> </td> </tr><tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporatePaymentResponse.paidAmount}</td><td style = "text-align: right;padding-right:25px;width:50%;">: <fmt:message key="ToBePaid"></fmt:message></td> </tr><tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporatePaymentResponse.appliedFees}</td><td style = "text-align: right;padding-right:25px;width:50%;">: <fmt:message key="FeesAndTax"></fmt:message></td> </tr><tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporatePaymentResponse.toBepaid}</td><td style = "text-align: right;padding-right:25px;width:50%;">: <fmt:message key="TotalAmount"></fmt:message></td> </tr><tr>');
                    //                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                    printwindow.document.write('<tr><th colspan="2">--------------------------------------------------------</th></tr>');
                    //                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> قد يستغرق تنفيذ العملية ساعتين فى حالة بطئ الشبكات <p></th> </tr >');
                    printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> <fmt:message key="messagePrint1"></fmt:message><p></th> </tr >');
                    printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> <fmt:message key="messagePrint2"></fmt:message> <p></th> </tr >');
                    printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p><fmt:message key="messagePrint3"></fmt:message> <p></th> </tr> </table> ');
                    printwindow.location.reload();
                    printwindow.document.close();
                    printwindow.focus();
                    printwindow.print();
                    printwindow.close();

                }
            </script>
        </head>
        <body class="body">
            <div>
                <c:choose>
                    <c:when test="${lang== ''}">
                        <jsp:include page="../../img/menuList.jsp"></jsp:include>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="../../img/menuListar.jsp"></jsp:include>
                    </c:otherwise>
                </c:choose>
            </div>

            <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" >
                <div>
                    <table cellspacing="10" style="font-size: 12px; font-weight: 900;"  > 
                        <tr><td colspan="2" style="text-align: center;"><fmt:message key="ServiceName"></fmt:message></td> </tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> <fmt:message key="trxStatus"></fmt:message></td><td style="padding-left: 10px;"><fmt:message key="success"></fmt:message></td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> <fmt:message key="TrxDateAndTime"></fmt:message></td><td style="padding-left: 10px;">${date}  ${time}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> <fmt:message key="TrxId"></fmt:message></td><td style="padding-left: 10px;">${orangeCorporatePaymentResponse.ledgerTrxId}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> <fmt:message key="MobileNumber"></fmt:message></td><td style="padding-left: 10px;">${orangeCorporatePaymentResponse.msisdn}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> <fmt:message key="CorporateAccountNumber"></fmt:message></td><td style="padding-left: 10px;">${orangeCorporatePaymentResponse.accountNumber}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> <fmt:message key="ToBePaid"></fmt:message></td><td style="padding-left: 10px;">${orangeCorporatePaymentResponse.paidAmount}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> <fmt:message key="AppliedFees"></fmt:message></td><td style="padding-left: 10px;">${orangeCorporatePaymentResponse.appliedFees}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> <fmt:message key="TotalAmount"></fmt:message></td><td style="padding-left: 10px;">${orangeCorporatePaymentResponse.toBepaid}</td></tr>
                        <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px"> <fmt:message key="messagePrint1"></fmt:message></td> </tr>
                        <tr> <td colspan="2" style="text-align:center;font-size:16px; padding-right: 30px"> <fmt:message key="messagePrint3"></fmt:message></td> </tr>

                        <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value=" <fmt:message key="printAndClose"></fmt:message>" onclick="setDivPrint()" /></td></tr>
                    </table>
                </div>
                <img src="./img/orangeCroporate.jpg" style="visibility: hidden;"/>
            </form>
            <!--</form>-->
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->
    </body>
</html>
</fmt:bundle>