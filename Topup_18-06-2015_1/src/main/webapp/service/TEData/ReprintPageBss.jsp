<%-- 
    Document   : ReprintPageBss
    Created on : Feb 18, 2019, 2:52:39 PM
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
            <title><fmt:message key="messagePrint4"></fmt:message></title>
                <script>
                    function setDivPrint() {
                         var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                    printwindow.document.write('<table cellspacing="5" frame="box" style="font-size: 20px; font-weight: 600; width: 50%;direction: rtl;" >');
                    printwindow.document.write('<tr><td colspan="2" style = "text-align:center;"><img src="img/Masary.jpg"  width="200" height="60" ></td></tr>');
                    printwindow.document.write('<tr><td style = "text-align:center;"><fmt:message key="WeServiceName"/></td></tr>');
                    printwindow.document.write('<tr><th colspan = "2" style="text-align:center"><fmt:message key="WeHomeInternet"/></th></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_TeDataBills_TrxId"/>: ${reprintResponse.globalTrxId}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_TeDataBills_RequestNumber"/>: ${reprintResponse.bssProviderRequestId}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_TeDataBills_Account"/>: ${reprintResponse.accountId}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_TeDataBills_PaymentDate"/>: ${date}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_TeDataBills_PaymentTiming"/>: ${time}</td></tr>');
                    printwindow.document.write('<tr><th>-----------------------------------------------------</th></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_TeDataBills_PhoneNumber"/>: ${reprintResponse.landLine}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_TeDataBills_AmountCharged"/>: ${reprintResponse.amount}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="Lable_TeDataBills_AppliedFees"/>: ${reprintResponse.appliedFees}</td> </tr><tr>');
                    printwindow.document.write('<tr><td><fmt:message key="totalAmountPlusVat"/>: ${reprintResponse.toBepaid}</td> </tr><tr>');
                    printwindow.document.write('<tr><th>-----------------------------------------------------</th></tr>');
                    printwindow.document.write('<tr><td colspan = "2" style = "text-align:center;">${reprintResponse.advertisingStatement}</td> </tr><tr>');
                    printwindow.document.write('<tr><th>-----------------------------------------------------</th></tr>');
                    printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size:16px;"><p><fmt:message key="MasaryComplainMessagePrint"/><p></th> </tr >');
                    printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size:16px;"><p><fmt:message key="MasaryHotline"/><p></th> </tr >');
                    printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size:16px;"><p><fmt:message key="messagePrint3"/><p></th> </tr> </table> ');
                    printwindow.location.reload();
                    printwindow.document.close();
                    printwindow.focus();
                    printwindow.print();
                    printwindow.close();
                        document.getElementById("form").submit();
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
                <!--</form>-->
            </div><!-- End content body -->
            <div style="clear: both;">&nbsp;</div>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->
    </body>
</html>
</fmt:bundle>
