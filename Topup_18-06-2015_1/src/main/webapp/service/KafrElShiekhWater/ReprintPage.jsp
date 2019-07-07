<%-- 
    Document   : ReprintPage
    Created on : Apr 4, 2019, 12:03:59 PM
    Author     : user
--%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="Bundle">
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="messagePrint4"/></title>
            <script>
                function setDivPrint() {

                    var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                    printwindow.document.write('<table cellspacing="5" frame="box" style="font-size: 20px; font-weight: 600; width: 5%;direction: rtl;" >');
                    printwindow.document.write('<tr><td colspan="2" style = "text-align:center;"><img src="img/Masary.jpg"  width="200" height="60" ></td></tr>');
                    printwindow.document.write('<tr><td style = "text-align:center;"><fmt:message key="kafrElShiekhWaterServiceName"/></td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="TrxId"/>: ${kafrElShiekhWaterPaymentResponse.globalTrxId}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="TrxDateAndTime"/>: <fmt:formatDate value="${date}" pattern="dd-MM-YYYY HH:mm"/></td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="CustomerId"/>: ${kafrElShiekhWaterPaymentResponse.accountId}</td></tr>');
                    printwindow.document.write('<tr><th  colspan = "2">________________________________________ </th></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_KafrElShiekhWater_subscriberName"/>: ${kafrElShiekhWaterPaymentResponse.subscriberName}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_KafrElShiekhWater_electronicId"/>: ${kafrElShiekhWaterPaymentResponse.subscriptionNumber}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_KafrElShiekhWater_billDate"/>:  ${kafrElShiekhWaterPaymentResponse.billDate}</td> </tr><tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_KafrElShiekhWater_billsPaid"/>:  ${kafrElShiekhWaterPaymentResponse.serviceAmount}</td> </tr><tr>');
                    printwindow.document.write('<tr><td><fmt:message key="FeesAndTax"/>:  ${kafrElShiekhWaterPaymentResponse.appliedFees}</td> </tr><tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_KafrElShiekhWater_totalAmountPaid"/>:  ${kafrElShiekhWaterPaymentResponse.toBepaid}</td> </tr><tr>');
                    printwindow.document.write('<tr><th  colspan = "2">------------------------------------------------------------</th></tr>');
                    printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size: 16px;" ><p><fmt:message key="messagePrint1"/><p></th> </tr >');
                    printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size: 16px;" ><p><fmt:message key="messagePrint2"/><p></th> </tr >');
                    printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size:16px; "><p><fmt:message key="messagePrint3"/><p></th> </tr> </table> ');
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

            <form action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" >
                <div>
                    <table cellspacing="10" style="font-size: 12px; font-weight: 900;"  > 
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="Lable_KafrElShiekhWater_totalBillsAmount"/></td><td style="padding-left: 10px;">${kafrElShiekhWaterPaymentResponse.serviceAmount}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="FeesAndTax"/></td><td style="padding-left: 10px;">${kafrElShiekhWaterPaymentResponse.appliedFees}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="Lable_KafrElShiekhWater_amountRequiredFromCustomer"/></td><td style="padding-left: 10px;">${kafrElShiekhWaterPaymentResponse.toBepaid}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="TrxId"/></td><td style="padding-left: 10px;">${kafrElShiekhWaterPaymentResponse.globalTrxId}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="trxStatus"/></td><td style="padding-left: 10px;"><fmt:message key="success"/></td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="Lable_KafrElShiekhWater_trxDate"/></td><td style="padding-left: 10px;"><fmt:formatDate value="${date}" pattern="dd-MM-YYYY"/></td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="Lable_KafrElShiekhWater_trxTime"/></td><td style="padding-left: 10px;"><fmt:formatDate value="${date}" pattern="HH:mm"/></td></tr>
                        <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value=" <fmt:message key="printAndClose"/>" onclick="setDivPrint()" /></td></tr>
                    </table>
                </div>
                <img src="./img/Masary.jpg" style="visibility: hidden;"/>
            </form>
            <!--</form>-->
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->
    </body>
</html>
</fmt:bundle>