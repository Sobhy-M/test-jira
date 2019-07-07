<%-- 
    Document   : ReprintBills
    Created on : Sep 27, 2017, 9:56:26 AM
    Author     : amira
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="img/style${lang}.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getREPRINT(session)%></title>

        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><th colspan="2">شركة مصاري</th></tr>');
                printwindow.document.write('<tr><th colspan="2">${receipt.payment_type_name}</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.transaction_id}</td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${date}</td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${PIN}</td><td style="text-align: right;padding-right:25px;width: 50%;">التاجر</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">--------------------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.phone}</td><td style = "text-align: right;padding-right:25px;width:50%;">رقم موبايل العميل</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.transactionAmount}</td><td style="text-align: right;padding-right:25px;width: 50%;" >قيمة العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.totalDueAmount}</td><td style="text-align: right;padding-right:25px;width: 50%;" >اجمالى المبلغ المدفوع</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">--------------------------------------------</th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');

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
            <c:if test="${ROLE != null}">
                <c:choose>
                    <c:when test="${lang== ''}">
                        <jsp:include page="../img/menuList.jsp"></jsp:include>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="../img/menuListar.jsp"></jsp:include>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
        <form action=<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%> method="POST" style="font-weight: bold">
            <table style="width: 45% ; margin-left: auto ; margin-right: auto" >
                <tr>
                    <td colspan="2" style="text-align: center; font-weight: bold; font-size: medium">
                        <p align="center">${receipt.payment_type_name}</p>
                    </td> 
                </tr>
                <tr>
                    <td style="border: none">
                        <p>رقم العملية:</p>
                    </td>
                    <td style="border: none">
                        <input  type="text" readonly style="background-color: #EDEDED;" value="${receipt.transaction_id}" >
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
                <c:if test="${receipt.phone != null}">
                    <tr>
                        <td>
                            <p>رقم العميل:</p>
                        </td>
                        <td>
                            <input type="text" name="TotalAmountPayable" value="${receipt.phone}" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        <p>تكلفة الخدمة:</p>
                    </td>
                    <td>
                        <input type="text" name="ServiceCost" value="${receipt.fees}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>ضريبة القيمة المضافة:</p>
                    </td>
                    <td>
                        <input type="text" name="ServiceTax" value="${receipt.tax}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td style="border: none">
                        <p><%= CONFIG.getWillbededucted(session)%>:</p>
                    </td>

                    <td style="border: none">
                        <p><input  type="text" readonly style="background-color: #EDEDED;" value="${receipt.transactionAmount}" >
                            <%= CONFIG.getFromTheBalanceOfYouWallet(session)%></p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>المبلغ الاجمالى:</p>
                    </td>
                    <td>
                        <input type="text" name="TotalAmountPayable" value="${receipt.totalDueAmount}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getServiceDate(session)%>:</p>
                    </td>
                    <td>
                        <label>${date}</label>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center" colspan="2">
                        <input type="submit" value="<%=CONFIG.getPrinting(session)%>"  id="submitbutton" onclick="setDivPrint()"  />
                    </td>
                </tr>
            </table>
        </form>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
