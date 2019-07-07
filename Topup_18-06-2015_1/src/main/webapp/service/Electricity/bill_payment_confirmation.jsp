<%-- 
    Document   : bill_payment_confirmation
    Created on : 01/05/2012, 07:12:20 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.ElectricityBillPaymentResult"%>
<%@page import="com.masary.database.dto.ElectricityBillWithCommissionsRepresentation"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Payment_Response"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<% response.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    ElectricityBillPaymentResult billResonse = (ElectricityBillPaymentResult) session.getAttribute("PayTxn");
    double billValue = Double.parseDouble(session.getAttribute("billValue").toString());
    double fees = Double.parseDouble(session.getAttribute("fees").toString());
    double totalAmount = billValue+fees;
%>
<html>
    <head>
        <script>
            function checkBrowser() {
                var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
                if (isOpera) {
                    //                    alert(isOpera);
                    setDivPrintChrome();
                }
                // Firefox 1.0+
                var isFirefox = typeof InstallTrigger !== 'undefined';
                if (isFirefox) {
                    setDivPrintFireFox();
                }
                // At least Safari 3+: "[object HTMLElementConstructor]"
                var isSafari = Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0;
                if (isSafari) {
                    setDivPrintChrome();
                }
                // Internet Explorer 6-11
                var isIE = /*@cc_on!@*/false || !!document.documentMode;
                if (isIE) {
                    setDivPrintIE();
                }
                // Chrome 1+
                var isChrome = !!window.chrome && !!window.chrome.webstore;
                if (isChrome) {
                    setDivPrintChrome();
                }
                var isEdge = window.navigator.userAgent.indexOf("Edge") > -1;
                if(isEdge){
                    setDivPrintIE();
                }
            }

            function setDivPrintChrome() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="7"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 23px;"  ><p>شركة مصارى</p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 18px;"  ><p>فاتورة كهرباء جنوب الدلتا</p></th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=billResonse.getTRANSACTION_ID()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" > رقم العملية </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= request.getParameter("BILLING_ACCOUNT")%> </td><td style="text-align: right;padding-right:25px;width: 50%;">رقم المشترك</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= billValue %></td><td style = "text-align: right;padding-right:25px;width:50%;">القيمة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= fees %> </td><td style = "text-align: right;padding-right:25px;width:50%;" > تكلفة الخدمة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= totalAmount %> </td><td style = "text-align: right;padding-right:25px;width:50%;" > المبلغ الاجمالي </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%> </td><td style="text-align: right;padding-right:25px;width: 50%;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style ="text-align: right;padding-right:25px;width: 50%;" > الوقت </td></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>شكرا لاستخدامك خدمات مصارى 16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"  ><p> تأكد من طباعة الفاتورة امامك </p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
//                window.location.replace("2.jsp");

            }

            function setDivPrintFireFox() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="7"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 23px;"  ><p>شركة مصارى</p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 18px;"  ><p>فاتورة كهرباء جنوب الدلتا</p></th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=billResonse.getTRANSACTION_ID()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" > رقم العملية </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= request.getParameter("BILLING_ACCOUNT")%> </td><td style="text-align: right;padding-right:25px;width: 50%;">رقم المشترك</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= billValue %></td><td style = "text-align: right;padding-right:25px;width:50%;">القيمة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= fees %> </td><td style = "text-align: right;padding-right:25px;width:50%;" > تكلفة الخدمة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= totalAmount %> </td><td style = "text-align: right;padding-right:25px;width:50%;" > المبلغ الاجمالي </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%> </td><td style="text-align: right;padding-right:25px;width: 50%;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style ="text-align: right;padding-right:25px;width: 50%;" > الوقت </td></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>شكرا لاستخدامك خدمات مصارى 16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"  ><p> تأكد من طباعة الفاتورة امامك </p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
//                window.location.replace("2.jsp");
                try {
                    // set portrait orientation
                    jsPrintSetup.setOption('orientation', jsPrintSetup.kPortraitOrientation);
                    // set top margins in millimeters
                    jsPrintSetup.setOption('marginTop', 0);
                    jsPrintSetup.setOption('marginBottom', 0);
                    jsPrintSetup.setOption('marginLeft', 0);
                    jsPrintSetup.setOption('marginRight', 0);
                    // set page header
                    jsPrintSetup.setOption('headerStrLeft', '');
                    jsPrintSetup.setOption('headerStrCenter', '');
                    jsPrintSetup.setOption('headerStrRight', '');
                    // set empty page footer
                    jsPrintSetup.setOption('footerStrLeft', '');
                    jsPrintSetup.setOption('footerStrCenter', '');
                    jsPrintSetup.setOption('footerStrRight', '');
                    // Suppress print dialog
                    jsPrintSetup.setSilentPrint(true);
                    // Do Print
                    jsPrintSetup.print();
                    // Restore print dialog
                    setTimeout(function () {
                        //                        console.log('print timeout');
                        printwindow.close();
                        window.location.replace("2.jsp");
                    }, 3000);
                }
                catch (err) {
                    alert(err);
                    window.location.replace("2.jsp");
                }
            }

            function setDivPrintIE() {
                document.getElementById('print').disabled = true;
            }

        </script>
    </head>
    <body onload="checkBrowser()">
        
    <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">

        <div>
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <div>
            <p style="color: red; font-size: 18px"><%=CONFIG.getelectricityMess(session)%><%= billResonse.getTRANSACTION_ID()%></p>
        </div>
    <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900;">
        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">حاله العملية</td><td style="padding-left: 10px;">عملية ناجحة</td></tr>
        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المشترك </td><td style="padding-left: 10px;"><%= request.getParameter("BILLING_ACCOUNT")%></td></tr>
        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تاريخ العملية </td><td style="padding-left: 10px;"><%=date1%></td></tr>
        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">توقيت العملية</td><td style="padding-left: 10px;"><%=time%></td></tr>
        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم العملية </td><td style="padding-left: 10px;"><%= billResonse.getTRANSACTION_ID()%></td></tr>
        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">القيمة </td><td style="padding-left: 10px;"><%= billValue %></td></tr>
        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%= fees %></td></tr>
        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;"><%= totalAmount %></td></tr>
        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المحفظة </td><td style="padding-left: 10px;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td></tr>
        <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</th> </tr>
        <tr> <th colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</th> </tr>
    </table>
        <input type="submit" id="print" onclick="window.location.replace('2.jsp');" align="middle" value="تم" />
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</body>
</html>

