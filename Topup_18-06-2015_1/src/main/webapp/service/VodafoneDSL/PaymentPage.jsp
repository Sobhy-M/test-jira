<%-- 
    Document   : PaymentPage
    Created on : Dec 31, 2018, 3:39:01 PM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.masary.controllers.VodafoneDSL.VodafoneDSLProperties"%>

<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style${lang}.css"
              rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=VodafoneDSLProperties.Title_VodafoneDsl_Payment%></title>
        <script type="text/javascript">
            function cancel() {
                document.getElementById("HOMEFORM").submit();
            }

            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 700 + ',height=' + 450 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="5" frame="box" style="font-size: 20px; font-weight: 600; width: 100px;direction: rtl;" >');
                printwindow.document.write('<tr><th><img src="./img/masarylogoo.jpg" alt="’Masary Icon"></th></tr>');
                printwindow.document.write('<tr><td style = "text-align:center;"><p>شحن فودافون DSL</td></tr >');
                printwindow.document.write('<tr><td> رقم العملية: ${vodafonetopupDslPayment.globalTrxId}</td></tr>');
                printwindow.document.write('<tr><td>تاريخ وتوقيت العملية: ${date} ${time}</td></tr>');
                printwindow.document.write('<tr><td>الرقم المرجعي: ${vodafonetopupDslPayment.ledgerTransactionId}</td></tr>');
                printwindow.document.write('<tr><th>__________________________________________________________</th></tr>');
                printwindow.document.write('<tr><td>رقم العميل: ${vodafonetopupDslPayment.targetNumber}</td></tr><tr>');
            <c:if test="${vodafonetopupDslPayment.appliedFees != 0.0}">
                printwindow.document.write('<tr><td>تكلفة الخدمة: ${vodafonetopupDslPayment.appliedFees}</td></tr>');
            </c:if>

                printwindow.document.write('<tr><td>اجمالى المبلغ المطلوب دفعه شامل ضريبه القيمة المضافة: ${vodafonetopupDslPayment.toBePaid}</td><tr>');
                printwindow.document.write('<tr><th>--------------------------------------------------------------------------------------</th></tr>');
                printwindow.document.write('<tr><th style = "text-align:center;" ><p>عملية ناجحة<p></th> </tr >');
                printwindow.document.write('<tr><th colspan="2">--------------------------------------------------------------------------------------</th></tr>');
            <c:if test="${vodafonetopupDslPayment.advertisementStatment != ''}">
                printwindow.document.write('<tr><th style = "text-align:center;" ><p>${vodafonetopupDslPayment.advertisementStatment}<p></th> </tr >');
                printwindow.document.write('<tr><th>--------------------------------------------------------------------------------------</th></tr>');
            </c:if>
                printwindow.document.write('<tr><th style = "text-align:center;font-size: 16px;" ><p>عند البطء فى الشبكة قد يستغرق تنفيذ العمليه 24 ساعة<p></th> </tr >');
                printwindow.document.write('<tr><th>--------------------------------------------------------------------------------------</th></tr>');
                printwindow.document.write('<tr><th style = "text-align:center;" ><p> شكراً لإستخدامكم مصاري لخدمات الدفع الذكية<p></th> </tr >');
                printwindow.document.write('<tr><th style = "text-align:center;" ><p>خدمة عملاء مصاري:16994 <p></th> </tr >');
                printwindow.document.write('<tr><th style = "text-align:center;"><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.print();
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
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
                        لقد تم تنفيذ العملية بنجاح وطباعة الايصال

                    </td>
                </tr>
            </table>

            <form action=<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%> method="post" name="homeForm" id="HOMEFORM"></form>
        </div>
        <!-- End of Table Content area-->

    </div>
    <img src="./img/masarylogoo.jpg" style="visibility: hidden"/>
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div>
<!-- End of Main body-->
</body>
</html>