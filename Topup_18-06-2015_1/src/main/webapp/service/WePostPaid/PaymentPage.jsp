<%-- 
    Document   : PaymentPage
    Created on : Mar 28, 2018, 4:52:15 PM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.controlers.WEPostPaid.WePostPaidProperties"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://www.java.com/js/deployJava.js"></script>
        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 450px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="./img/masarylogoo.jpg"  width="150" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="3">We</th> </tr>');
                printwindow.document.write('<tr><th colspan="3">خدمات المحمول</th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${paymentResponse.globalTrxId}</td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${date1} ${time}</td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ وتوقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${paymentResponse.ledgerTrxId}</td><td style = "text-align: right;padding-right:25px;width:50%;">الرقم المرجعى</td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${paymentResponse.msisdn}</td><td style="text-align: right;padding-right:25px;width: 50%;">رقم العميل</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${paymentResponse.totalOutStandingFee}</td><td style="text-align: right;padding-right:25px;width: 50%;">قيمة الفاتورة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${paymentResponse.appliedFees}</td><td style="text-align: right;padding-right:25px;width: 50%;">تكلفه الخدمه</td></tr>');

             <%if(Double.parseDouble(request.getParameter("tax").toString())!=0){%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${paymentResponse.tax}</td><td style="text-align: right;padding-right:25px;width: 50%;">ضريبة القيمة المضافة</td></tr>');<%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${paymentResponse.toBepaid}</td><td style="text-align: right;padding-right:25px;width: 50%;">اجمالى المبلغ المدفوع</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>عملية ناجحة</td></tr >');
                <%if(request.getParameter("advStatment")!=null){%>

                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" >${paymentResponse.advStatment}</td></tr >'); <%}%>
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>عند البطء فى الشبكة قد يستغرق تنفيذ العملية 24 ساعة</td></tr >');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>  شكرا لاستخدامكم مصاري لخدمات الدفع الذكية</td></tr >');
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

        <title><%=WePostPaidProperties.WePostPaid_SERVICE_NAME%></title>
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
                    <td>
                        <p align="right">حالة العملية</p>
                    </td>
                    <td>
                        <input type="text" value="<%=WePostPaidProperties.WePostPaidTrxStatus%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right">تاريخ ووقت العملية</p>
                    </td>
                    <td>
                        <input type="text" value="${date1} ${time}"  readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right">رقم العملية</p>
                    </td>
                    <td>
                        <input type="text" value="${paymentResponse.globalTrxId}" readonly style="background-color: #EDEDED;" >
                    </td>                           
                </tr>
                <tr>
                    <td>
                        <p align="right">رقم الموبايل</p>
                    </td>
                    <td>
                        <input type="text" value="${paymentResponse.msisdn}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right">المبلغ المسدد</p>
                    </td>
                    <td>
                        <input type="text" value="${paymentResponse.totalOutStandingFee}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right">تكلفة الخدمة</p>
                    </td>
                    <td>
                        <input type="text" value="${paymentResponse.appliedFees}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p align="right">اجمالى المبلغ المطلوب</p>
                    </td>
                    <td>
                        <input type="text" value="${paymentResponse.toBepaid}" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr> 
                    <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</td>

                </tr>
                <tr>
                    <td colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</td>
                </tr>
                <tr>
                    <td style="text-align: center" colspan="2">
                        <input type="submit" name="btnSubmit" value="اغلاق وطباعه"  class="Btn" onclick="setDivPrint()" >
                    </td>
                </tr>
                <tr style="display: none">
                    <th colspan="2"><img src="./img/masarylogoo.jpg"  width="50" height="50"></th>
                </tr>
            </table>
        </form>
    </div>
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

</body>
</html>
