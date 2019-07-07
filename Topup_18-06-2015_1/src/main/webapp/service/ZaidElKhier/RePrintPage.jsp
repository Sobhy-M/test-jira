<%-- 
    Document   : PaymentPage
    Created on : Apr 30, 2018
    Author     : Ahmed Khaled
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.masary.controllers.ZaidElKhier.ZaidElKhierProperties"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><%=ZaidElKhierProperties.Param_ZaidElKhier_ServiceName%></title>
        <script type="text/javascript">
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 700 + ',height=' + 450 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 600; width: 400px" >');
                printwindow.document.write('<tr><th colspan="2"><img src="./img/zaidElKhier-masary.jpg" alt="’Masary Icon"></th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>زايد الخير</td></tr >');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.globalTrxID}</td><td style="text-align: right;padding-right:25px;width: 50%;"> رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.transaction_date}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >تاريخ وتوقيت العملية </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.accountId}</td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">--------------------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 60%;">${receipt.subscriberName}</td><td style = "text-align: right;padding-right:25px;width:50%;">نوع التبرع</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.amount}</td><td style = "text-align: right;padding-right:25px;width:50%;">مبلغ التبرع</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${receipt.toBePaid}</td><td style = "text-align: right;padding-right:25px;width:50%;">اجمالى المبلغ المدفوع</td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">--------------------------------------------</th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.print();
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.close();
            }
        </script>
        <link href="img/style${lang}.css" rel="stylesheet" type="text/css">
    </head>

    <body onload="setDivPrint()">
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
        <div class="content_body">
            <form action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>"  method="POST" >
                <table >
                    <tr>
                        <td colspan="2" style="text-align: center; font-weight: bold; font-size: medium">
                            <img src="img/zaidElKhier.jpg"  width="60" height="60" style="float: left;">
                            <p align="right"> <%=ZaidElKhierProperties.Param_ZaidElKhier_ServiceName%>
                            </p>

                        </td> 
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center; font-weight: bold; font-size: medium">
                            <p align="right"> <%=ZaidElKhierProperties.Param_ZaidElKhier_Confirmation%>
                                ${receipt.subscriberName}
                            </p>

                        </td> 
                    </tr>
                    <tr>
                        <td>
                            <p align="right">قيمة التبرع بالجنيه المصرى</p>
                        </td>
                        <td>
                            <input type="text" value="${receipt.amount}" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">تكلفة الخدمة</p>
                        </td>
                        <td>
                            <input type="text" value="${receipt.fees}"  readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">عمولة التاجر</p>
                        </td>
                        <td>
                            <input type="text" value="${receipt.merchantCommission}" readonly style="background-color: #EDEDED;" >
                        </td>                           
                    </tr>
                    <tr>
                        <td>
                            <p align="right">سيتم خصم</p>
                        </td>
                        <td>
                            <input type="text" value="${receipt.transactionAmount}" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">اجمالى المبلغ المستحق الدفع بالجنيه</p>
                        </td>
                        <td>
                            <input type="text" value="${receipt.toBePaid}" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">رقم موبايل المتبرع</p>
                        </td>
                        <td>
                            <input type="text" value="${receipt.phone}" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>

                    <tr>

                        <td style="text-align: center" colspan="2">
                            <input type="submit" name="btnSubmit" value="اغلاق"  class="Btn" >
                        </td>

                    </tr>    

                </table>
            </form>
        </div><!-- End content body -->
        <img src="./img/zaidElKhier-masary.jpg" style="visibility: hidden">

        <div style="clear: both;">&nbsp;</div>

        <div id="footer">
            <jsp:include page="../../img/timeout.jsp"></jsp:include>
        </div>
    </body>
</html>



