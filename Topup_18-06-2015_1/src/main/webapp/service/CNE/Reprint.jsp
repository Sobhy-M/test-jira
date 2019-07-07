<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="Bundle">


    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="Title_CNE_Home"/></title>
            <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        </head>


        <script>
            function setDivPrint() {

                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="5" frame="box" style="font-size: 20px; font-weight: 600; width: 5%;direction: rtl;" >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;"><img src="img/Masary.jpg"  width="200" height="60" ></td></tr>');
                printwindow.document.write('<tr><td style = "text-align:center;"><fmt:message key="Title_CNE_Home"/> </td></tr>');
                printwindow.document.write('<tr><td><fmt:message key="CNETransactionIdAr"/>: ${paymentResponse.transactionId}</td></tr>');

                printwindow.document.write('<tr><td><fmt:message key="CNEDateAr"/>: <fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm"/>        </td></tr>');
                printwindow.document.write('<tr><td><fmt:message key="CNEMerchantAr"/>:  ${paymentResponse.accountId} </td></tr>');
                printwindow.document.write('<tr><th  colspan = "2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><td><fmt:message key="CNEClientNameAr"/>: ${paymentResponse.clientName}</td></tr>');
                printwindow.document.write('<tr><td><fmt:message key="CNEPackageNameAr"/>: ${paymentResponse.programName}</td></tr>');

                printwindow.document.write('<tr><td><fmt:message key="CNEAmountAr"/>: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${paymentResponse.billAmount}" /> </td></tr>');
                printwindow.document.write('<tr><td><fmt:message key="CNETotalAmountAr"/>: ${paymentResponse.appliedFees}</td></tr>');
                printwindow.document.write('<tr><th  colspan = "2">------------------------------------------------------------</th></tr>');
                printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size: 16px;" ><p><fmt:message key="messagePrint1"/><p></th> </tr >');
                printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size: 16px;" ><p><fmt:message key="messagePrint2"/><p></th> </tr >');
                printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size:16px; "><p><fmt:message key="messagePrint3"/><p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
                document.getElementById("paymentForm").submit();
            }

        </script>
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
            <form id="form" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" >


                <div>
                    <table> 
                        <tr>
                            <td>
                                <fmt:message key="messageReprint"></fmt:message>
                                </td>
                            </tr>

                        </table>
                    </div>
                    <img src="./img/Masary.jpg" style="visibility: hidden;"/>
                </form>
                <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

            </body>
        </html>
</fmt:bundle>
