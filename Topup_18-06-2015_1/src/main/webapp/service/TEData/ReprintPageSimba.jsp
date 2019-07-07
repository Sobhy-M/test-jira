<%-- 
    Document   : ReprintPageSimba
    Created on : Feb 25, 2019, 5:11:08 PM
    Author     : Ahmed Khaled
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
            <title><fmt:message key="rePrint"></fmt:message></title>
                <script>
                    function setDivPrint() {
                        var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                        printwindow.document.write('<table cellspacing="7"  style="font-size: 15px; font-weight: 700; width: 350px" >');
                        printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="150" height="60"></th></tr>');
                        printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center"><fmt:message key="WeServiceName"/></th></tr>');
                        printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center"><fmt:message key="WeHomeInternet"/></th></tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;">${reprintResponse.landLine}</td><td style="text-align: right;padding-right:25px;width: 60%;"><fmt:message key="Lable_TeDataBills_PhoneNumber"/></td> </tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.totalDueForRenewal}</td><td style = "text-align: right;padding-right:25px;width:50%;"><fmt:message key="Lable_TeDataBills_BillAmount"/></td> </tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.appliedFees}</td><td style="text-align: right;padding-right:25px;width: 50%;"><fmt:message key="Lable_TeDataBills_AppliedFees"/></td> </tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.toBepaid}</td><td style="text-align: right;padding-right:25px;width: 50%;"><fmt:message key="Lable_TeDataBills_TotalAmount"/></td> </tr>');
                        printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%;" colspan="2">--------------------------------------</th></tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.newExpiryDateAfterRenewal}</td><td style="text-align: right;padding-right:20px;width: 50%;"><fmt:message key="Lable_TeDataBills_BillDueDate"/></td></tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.globalTrxId}</td><td style = "text-align: right;padding-right:25px;width:50%;"><fmt:message key="Lable_TeDataBills_TrxId"/></td> </tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${reprintResponse.accountId}</td><td style="text-align: right;padding-right:25px;width: 50%; "><fmt:message key="Lable_TeDataBills_Account"/></td> </tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${date}</td><td style="text-align: right;padding-right:25px;width: 50%;" ><fmt:message key="TrxDate"/></td> </tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${time}</td><td style ="text-align: right;padding-right:25px;width: 50%;" ><fmt:message key="TrxTime"/></td></tr>');
                        printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%;" colspan="2">--------------------------------------</th></tr>');
                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;direction: rtl" ><p><fmt:message key="WeAdvertismentPhrase"/><p></th> </tr >');
                        printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%;" colspan="2">--------------------------------------</th></tr>');
                        printwindow.document.write('<tr> <th style="font-size:14px;text-align: center;" colspan="2"><fmt:message key="messagePrint5"/></th> </tr>');
                        printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier;text-align: center;width: 100%" colspan="3"><fmt:message key="messagePrint1"/></th> </tr>');
                        printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3"><fmt:message key="messagePrint2"/></th></tr></table>');
                        printwindow.location.reload();
                        printwindow.document.close();
                        printwindow.focus();
                        printwindow.print();
                        printwindow.close();
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