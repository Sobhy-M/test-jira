        <%-- 
    Document   : TamweelPaymentPage
    Created on : Feb 25, 2019, 12:26:19 PM
    Author     : omar.abdellah
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
            <title><fmt:message key="messagePrint4"/></title>
            <script>
                
                function setDivPrint() {

                    var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                    printwindow.document.write('<table cellspacing="5" frame="box" style="font-size: 20px; font-weight: 600; width: 5%;direction: rtl;" >');
                    printwindow.document.write('<tr><td colspan="2" style = "text-align:center;"><img src="img/Masary.jpg"  width="200" height="60" ></td></tr>');
                    printwindow.document.write('<tr><td style = "text-align:center;"><fmt:message key="Title_Tamweel_Print"/> ${tamweelPaymentRepresentation.installmentType}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="TamweelTransactionID"/>: ${tamweelPaymentRepresentation.globalTrxId}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="TamweelTransactionDate"/>: <fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm"/> </td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="TamweelWalletID"/>: ${tamweelPaymentRepresentation.accountId}        </td></tr>');
                    printwindow.document.write('<tr><th  colspan = "2">________________________________________ </th></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="TamweelCustomerNameAr"/>: ${tamweelPaymentRepresentation.customerName}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_Tamweel_code"/>: ${tamweelPaymentRepresentation.customerCode}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="TamweelInstallmentDateAR"/>:  ${tamweelPaymentRepresentation.installmentDate}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="TamweelAmountPrint"/>: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${tamweelPaymentRepresentation.installmentAmount}" /> </td></tr>');
                    if (${tamweelPaymentRepresentation.partialPayment} !== 0) {
                        printwindow.document.write('<tr><td><fmt:message key="TamweelPartialPayment"/>: <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${tamweelPaymentRepresentation.serviceAmount}" /> </td></tr>');
                    }
                    printwindow.document.write('<tr><td><fmt:message key="TamweelPaymenttax"/>: <fmt:formatNumber type = "number"   minFractionDigits="2" maxIntegerDigits = "3" value = "${tamweelPaymentRepresentation.appliedFees}" /> </td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="TamweelPaymenttaxandservice"/>: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${tamweelPaymentRepresentation.toBepaid}" /> </td></tr>');

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
        <body class="body" >
            <div> 

                <   <c:choose>
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
                    <h3 style=" font-size: 18px ;text-decoration: underline;" ><fmt:message key="successTrx"/></h3>
                    <table cellspacing="10" style="font-size: 12px; font-weight: 900;"  > 
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="TamweelTransactionID"/></td><td style="padding-left: 10px;">${tamweelPaymentRepresentation.accountId}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="TamweelMerchantCommission"/></td><td style="padding-left: 10px;">${tamweelPaymentRepresentation.merchantCommission}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="TamweelTrasactionAmount"/></td><td style="padding-left: 10px;">${tamweelPaymentRepresentation.transactionAmount}</td></tr>

                      

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