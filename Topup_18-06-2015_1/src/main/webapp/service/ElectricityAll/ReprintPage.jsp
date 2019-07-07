<%-- 
    Document   : ElectricityPayment
    Created on : Jul 27, 2018, 10:22:56 AM
    Author     : Ahmed Khaled
--%>
<%@page import="com.masary.common.CONFIG"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getElectricityServiceName(request.getSession())%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />

        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
            <c:choose>
                <c:when test="${receipt.transactionId == '99012'}">
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/MiddleEgyptElectricity.jpg"  width="100" height="60"/></td>');
                </c:when>
                <c:when test="${receipt.transactionId == '99013'}">
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/UpperEgyptElectricity.png"  width="100" height="60"/></td>');
                </c:when>
                <c:when test="${receipt.transactionId == '99014'}">
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/canalElectricity.png"  width="180" height="60"/></td>');
                </c:when>
                <c:when test="${receipt.transactionId == '99015'}">
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/NorthDeltaElectricity.jpg"  width="180" height="60"/></td>');
                </c:when>
                <c:when test="${receipt.transactionId == '99018'}">
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/SouthDeltaElectricity.jpg"  width="170" height="60"/></td>');
                </c:when>
                <c:when test="${receipt.transactionId == '99019'}">
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/AlexElectricity.jpg"  width="100" height="60"/></td>');
                </c:when>
                <c:when test="${receipt.transactionId == '99021'}">
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/NorthElectricity.jpg"  width="100" height="60"/></td>');
                </c:when>

                <c:when test="${receipt.transactionId == '99022'}">
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/BehiraElectricity.jpg"  width="100" height="60"/></td>');
                </c:when>
            </c:choose>
                printwindow.document.write('<td><img src="./img/Masary.jpg"  width="160" height="60"/></td></tr>');
                printwindow.document.write('<tr><th colspan="2">شركة مصاري</th></tr>');
                printwindow.document.write('<tr><th colspan="2"><%=CONFIG.getElectricityServiceName(session)%></th></tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.globalTrxId}</td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.insertDate}</td><td style="text-align: right;padding-right:25px;width: 50%;" >تاريخ و توقيت العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;">${receipt.accountId}</td><td style="text-align: right;padding-right:25px;width: 60%; ">رقم التاجر </td> </tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.clientName}</td><td style = "text-align: right;padding-right:25px;width:50%;">اسم المشترك </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.accountNumber}</td><td style = "text-align: right;padding-right:25px;width:50%;">رقم السداد الالكتروني </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.billDate}</td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ الفاتورة  </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.billAmount}</td><td style = "text-align: right;padding-right:25px;width:50%;">اجمالي قيمه الفاتورة   </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.tax + receipt.appliedFees}</td><td style = "text-align: right;padding-right:25px;width:50%;">تكلفه الخدمه شامله ضريبه القيمه المضافه  </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.toBepaid}</td><td style = "text-align: right;padding-right:25px;width:50%;">اجمالي المبلغ المدفوع شامل مصاريف الخدمة وضريبة القيمة المضافة </td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">---------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr>  ');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> Powered by e-finance <p></th> </tr > </table>');
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
            }
        </script>

    </head>
    <body>
        <div>
            <c:if test="${ROLE != null}">
                <c:choose>
                    <c:when test="${lang== ''}">
                        <jsp:include page="../../img/menuList.jsp"></jsp:include>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="../../img/menuListar.jsp"></jsp:include>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
        <form action="<%=CONFIG.APP_ROOT%>${ROLE}.jsp" method="POST" style="font-weight: bold">
            <h2 align="center" ><%=CONFIG.GetSuccessfulTransaction(session)%></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >

                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم العملية </td><td style="padding-left: 10px;">${receipt.globalTrxId}</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المشترك </td><td style="padding-left: 10px;">${receipt.accountNumber}</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">القيمة </td><td style="padding-left: 10px;">${receipt.billAmount}</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;">${receipt.appliedFees}</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">عمولتك هي :   </td><td style="padding-left: 10px;">${receipt.merchantCommission}</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;">${receipt.toBepaid}</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المحفظة </td><td style="padding-left: 10px;">${receipt.accountId}</td></tr>
                <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</th> </tr>
                <tr> <th colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</th> </tr>

                <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="إغلاق و طباعه" onclick="setDivPrint();" /></td></tr>


                <tr style="display: none">
                    <td><img src="./img/AlexElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/BehiraElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/NorthElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/canalElectricity.png"  width="100" height="60"/></td>
                    <td><img src="./img/NorthDeltaElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/SouthDeltaElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/MiddleEgyptElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/UpperEgyptElectricity.png"  width="100" height="60"/></td>
                    <td><img src="./img/Masary.jpg"  width="80" height="60"/></td>
                </tr>
            </table>
        </form>

        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
