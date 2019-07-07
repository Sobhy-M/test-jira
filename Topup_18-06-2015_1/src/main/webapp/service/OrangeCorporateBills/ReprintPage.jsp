<%-- 
    Document   : Orange croporate bills reprint
    Created on : Jan 9, 2019, 1:39:38 PM
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
                function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }
                function setDivPrint() {

                    var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                    printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 400px" >');
                    printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/orangeCroporate.jpg"  width="200" height="60"></td></tr>');
                    printwindow.document.write('<tr><th colspan="2"><fmt:message key="OrangeCorpBill"></fmt:message></th></tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporateReprintResponse.ledgerTrxId}</td><td style="text-align: right;padding-right:25px;width: 50%;">:<fmt:message key="TrxId"></fmt:message></td> </tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${date}  ${time}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:<fmt:message key="TrxDateAndTime"></fmt:message></td></tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporateReprintResponse.accountId}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:<fmt:message key="CustomerId"></fmt:message> </td></tr>');
                    printwindow.document.write('<tr><th colspan="2">--------------------------------------------------------</th></tr>');

                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporateReprintResponse.msisdn}</td><td style = "text-align: right;padding-right:25px;width:50%;">:<fmt:message key="MobileNumber"></fmt:message> </td> </tr><tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporateReprintResponse.accountNumber}</td><td style = "text-align: right;padding-right:25px;width:50%;">:<fmt:message key="CorporateAccountNumber"></fmt:message></td> </tr><tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporateReprintResponse.paidAmount}</td><td style = "text-align: right;padding-right:25px;width:50%;">: <fmt:message key="ToBePaid"></fmt:message></td> </tr><tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporateReprintResponse.appliedFees}</td><td style = "text-align: right;padding-right:25px;width:50%;">: <fmt:message key="FeesAndTax"></fmt:message></td> </tr><tr>');
                    printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${orangeCorporateReprintResponse.toBepaid}</td><td style = "text-align: right;padding-right:25px;width:50%;">: <fmt:message key="TotalAmount"></fmt:message></td> </tr><tr>');
                    printwindow.document.write('<tr><th colspan="2">--------------------------------------------------------</th></tr>');
                    printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> <fmt:message key="messagePrint1"></fmt:message><p></th> </tr >');
                    printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> <fmt:message key="messagePrint2"></fmt:message><p></th> </tr >');
                    printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p><fmt:message key="messagePrint3"></fmt:message> <p></th> </tr> </table> ');
                    printwindow.location.reload();
                    printwindow.document.close();
                    printwindow.focus();
                    printwindow.print();
                    printwindow.close();
                    cancel();

                }
            </script>
        </head>
        <body class="body" onload="setDivPrint()">
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

            <div class="content_body">


                <table>
                    <tr>
                        <td>
                            تم اعادة الطباعة بنجاح

                        </td>
                    </tr>
                </table>

                <form action=<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%> method="post" name="homeForm" id="HOMEFORM"></form>
                <img src="./img/orangeCroporate.jpg" style="visibility: hidden;"/>
            </div>
            <div style="clear: both;">&nbsp;</div>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
            </div><!-- End of Main body-->
        </body>
    </html>
</fmt:bundle>