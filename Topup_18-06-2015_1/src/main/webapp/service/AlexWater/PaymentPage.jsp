<%-- 
    Document   : alex water payment
    Author     : Ahmed Khaled
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.masary.controllers.AlexWater.AlexWaterProperities"%>
<%@page import="com.masary.integration.dto.AlexWaterPaymentResponse"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% response.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://www.java.com/js/deployJava.js"></script>
        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 450px" >');
                printwindow.document.write('<tr><th colspan="2"><img src="./img/alexWater.png" alt="’Masary Icon"></th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>شركة مياه الاسكندرية</td></tr >');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${alexWaterPaymentResponse.ledgerTrxId}</td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${date1}</td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${time}</td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${alexWaterPaymentResponse.accountId}</td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم البائع</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${alexWaterPaymentResponse.customerName}</td><td style="text-align: right;padding-right:25px;width: 50%;">اسم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${alexWaterInquiryResponse.elecNo}</td><td style="text-align: right;padding-right:25px;width: 50%;">رقم الدفع الالكترونى</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${alexWaterPaymentResponse.billingrunName}</td><td style="text-align: right;padding-right:25px;width: 50%;">تاريخ الاصدار</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${alexWaterPaymentResponse.totalDueAmount}</td><td style="text-align: right;padding-right:25px;width: 50%;">اجمالى قيمة الفاتورة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${alexWaterPaymentResponse.appliedFees}</td><td style="text-align: right;padding-right:15px;width: 50%;">تكلفة الخدمة شاملة ضريبة القيمة المضافة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${alexWaterPaymentResponse.toBepaid}</td><td style="text-align: right;padding-right:15px;width: 50%;">اجمالى المبلغ المطلوب دفعه</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>  شكرا لاستخدامكم مصاري لخدماتالدفع الذكية</td></tr >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>خدمة عملاء مصاري: 16994</td></tr >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size:16px;"><p>www.e-masary.com</td></tr>');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
            }
        </script>
        <style type="text/css">
            #load{
                position:absolute;
                top:50%;
                z-index:1;
                border:3px double #999;
                width:300px;
                height:300px;
                margin-top:-150px;
                margin-left:-150px;
                background:#ffffff;
                top:50%;
                left:50%;
                text-align:center;
                line-height:300px;
                font-family:"Trebuchet MS", verdana, arial,tahoma;
                font-size:18pt;
                background-image:url(img/loading.gif);
                background-position:50% 40%;
            }
        </style>

        <title><%=AlexWaterProperities.serviceName%></title>
    </head>
    <body onload="ray.hide();">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <div id="load" style="display:none;"><%=CONFIG.getPleaseWait(session)%></div>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <div class="content_body">
            <form action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>"  method="POST">
            <table  style="width: 40%">
                <tr>
                    <td colspan="2" style="text-align: center">
                        عملية ناجحة
                    </td>
                </tr>

                <tr>
                    <td>
                        <p align="right">رقم العملية</p>
                    </td>
                    <td>
                        <input type="text" value="${alexWaterPaymentResponse.ledgerTrxId}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right">عمولتك هى</p>
                    </td>
                    <td>
                        <input type="text" value="${alexWaterPaymentResponse.merchantCommission}"  readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right">تم خصم</p>
                    </td>
                    <td>
                        <input type="text" value="${alexWaterPaymentResponse.transactionAmount}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center" colspan="2">
                        <input type="submit" name="btnSubmit" value="اغلاق وطباعه"  class="Btn" onclick="setDivPrint()" >
                    </td>
                </tr>
                <tr style="display: none">
                    <th colspan="2"><img src="./img/alexWater.png"  width="50" height="50"></th>
                </tr>
            </table>
        </form>
    </div>
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

</body>
</html>
