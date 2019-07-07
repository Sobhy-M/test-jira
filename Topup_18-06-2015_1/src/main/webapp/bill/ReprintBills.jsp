<%-- 
    Document   : ReprintBills
    Created on : Sep 27, 2017, 9:56:26 AM
    Author     : amira
--%>
<%@page import="com.masary.database.dto.Bill_Provider_Response"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Bill_Receipt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(String.valueOf(request.getSession().getAttribute("serv_id").toString()));
    String custId = null;
    if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    DecimalFormat myFormatter = new DecimalFormat(".#");
    Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
    receipt = (Masary_Bill_Receipt) session.getAttribute("receipt");
    String date1 = "";
    String time1 = "";
    try {

        date1 = receipt.getTransaction_date().substring(0, 10);
        time1 = receipt.getTransaction_date().substring(11, 16);

    } catch (Exception e) {
        MasaryManager.logger.error(e.getMessage());
    }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getREPRINT(session)%></title>

        <script>
            function setDivPrintChrome() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="7"  style="font-size: 15px; font-weight: 700; width: 350px" >');
            <%if (request.getSession().getAttribute("serv_id").toString().equals("124")) {%>
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="150" height="60"></th></tr>');

                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">We</th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">الخط الارضى</th></tr>');
            <%} else {%>
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="auto" height="auto"></th></tr>');
            <%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> <%= receipt.getPhone()%></td><td style="text-align: right;padding-right:25px;width: 60%;">رقم التليفون</td> </tr>');
            <% if (!request.getSession().getAttribute("serv_id").toString().equals("112") && !request.getSession().getAttribute("serv_id").toString().equals("111") && !request.getSession().getAttribute("serv_id").toString().equals("117")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> <%=new String(request.getParameter("Customer_name").getBytes("ISO-8859-1"), "UTF-8")%> </td><td style="text-align: right;padding-right:25px;width: 60%; ">اسم العميل</td> </tr>');
            <%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=myFormatter.format(receipt.getAmount())%></td><td style = "text-align: right;padding-right:25px;width:50%;">قيمة الفاتورة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getFees()%> </td><td style="text-align: right;padding-right:25px;width: 50%;">تكلفة الخدمة </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=myFormatter.format(receipt.getAmount() + receipt.getFees())%> </td><td style="text-align: right;padding-right:25px;width: 50%;">المبلغ الكلي </td> </tr>');
                printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%;" colspan="2">------------------------------</th></tr>');
            <%if (request.getSession().getAttribute("serv_id").toString().equals("124")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= receipt.getDue_date()%></td><td style="text-align: right;padding-right:20px;width: 50%;" > تاريخ الفاتورة </td></tr>');
            <%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> <%= receipt.getTransaction_id()%></td><td style = "text-align: right;padding-right:25px;width:50%;"> رقم العملية </td> </tr>');
            <%if (request.getSession().getAttribute("serv_id").toString().equals("111")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= receipt.getProviderResponse().getPROVIDER_TRANSACTION_ID()%></td><td style="text-align:right;padding-right:20px;width: 50%; "> رقم الطلب </td></tr>');
            <%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%= request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td><td style="text-align: right;padding-right:25px;width: 50%; ">البائع</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> <%= date1%></td><td style="text-align: right;padding-right:25px;width: 50%;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time1%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" > الوقت </td></tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                printwindow.document.write('<tr> <th style="font-size:14px;text-align: center;" colspan="2">قد يستغرق تنفيذ العمليه ساعتين في حاله بطئ الشبكات</th> </tr>');
                printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier;text-align: center;width: 100%" colspan="3">شكرا لاستخدامكم مصارى لخدمات الدفع الذكية</th> </tr>');
                printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3">خدمة عملاء مصارى: 16994</th></tr></table>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                //       printwindow.document.write('<tr> <th style="font-family:courier;font-size:18px;text-align:left;" colspan="2">< new String(advertismentWord.getBytes("ISO-8859-1"), "UTF-8") %></th> </tr>');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
            }
            function setDivPrintFireFox() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="5"  style="font-size: 18px; font-weight: 700; width: 350px" >');

            <% if (request.getSession().getAttribute("serv_id").toString().equals("124")) {%>
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="150" height="60"></th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">We</th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">الخط الارضى</th></tr>');
            <%} else {%>
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="auto" height="auto"></th></tr>');
            <%}%>
                printwindow.document.write('<tr><th colspan="3"><%=BTC.getStrBTCName(session)%></th></tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"> <%= receipt.getPhone()%></td><td style="font-family:courier;font-size:18px;text-align: right;">رقم التليفون </td> </tr>');
            <%if (!request.getSession().getAttribute("serv_id").toString().equals("112") && !request.getSession().getAttribute("serv_id").toString().equals("111") && !request.getSession().getAttribute("serv_id").toString().equals("117")) {%>
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=new String(request.getParameter("Customer_name").getBytes("ISO-8859-1"), "UTF-8")%> </td><td style="font-family:courier;font-size:18px;text-align: right;" > اسم العميل </td></tr>');
            <%}%>
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=myFormatter.format(receipt.getAmount())%></td><td style = "font-family:courier;font-size:18px;text-align: right;">القيمة</td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=receipt.getFees()%></td><td style = "font-family:courier;font-size:18px;text-align: right;">تكلفة الخدمة</td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=myFormatter.format(receipt.getAmount() + receipt.getFees())%></td><td style="font-family:courier;font-size:18px;text-align: right;">المبلغ الاجمالي</td> </tr>');
            <%if (request.getSession().getAttribute("serv_id").toString().equals("124")) {%>
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%= receipt.getDue_date()%></td><td style="font-family:courier;font-size:18px;text-align: right;" > تاريخ الفاتورة </td></tr>');
            <%} else if (request.getSession().getAttribute("serv_id").toString().equals("111")) {%>
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%= receipt.getProviderResponse().getPROVIDER_TRANSACTION_ID()%></td><td style="font-family:courier;font-size:18px;text-align: right;" > رقم الطلب </td></tr>');
            <%}%>
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;">  <%= request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td><td style ="font-family:courier;font-size:18px;text-align: right;" >البائع</td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%= time1%></td><td style="font-family:courier;font-size:18px;text-align: right;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%= date1%></td><td style ="font-family:courier;font-size:18px;text-align: right;" > الوقت </td></tr>');
                printwindow.document.write('<tr> <th style="font-family:courier;font-size:18px;text-align:left;" colspan="2">قد يستغرق تنفيذ العمليه ساعتين في حاله بطئ الشبكات</th> </tr> </table> ');
                printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier;text-align: center;width: 100%" colspan="3">شكرا لاستخدامكم مصارى لخدمات الدفع الذكية</th> </tr >');
                printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3">خدمة عملاء مصارى: 16994</th></tr>');
                printwindow.document.write('<tr><th style="font-family:courier;font-size:18px;text-align: center;width: 100%; " colspan="3" >www.e-masary.com</th > </tr></table>  ');
//               printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                //   printwindow.document.write('<tr> <th style="font-family:courier;font-size:18px;text-align:left;" colspan="2"> new String(advertismentWord.getBytes("ISO-8859-1"), "UTF-8") %></th> </tr>');
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
                    }, 3000);
                } catch (err) {
                    alert(err);
                }
//                printwindow.print();
//                
            }
            function checkBrowser() {
                var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
                if (isOpera) {
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
                // Edge 20+
                // Chrome 1+
                var isChrome = !!window.chrome && !!window.chrome.webstore;
                if (isChrome) {
                    setDivPrintChrome();
                }
            }
            function setDivPrintIE() {
                document.getElementById('print').disabled = true;
            }
        </script>
    </head>
    <body class="body" onload="checkBrowser()">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <div>
            <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="font-weight: bold">
            <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900;" >
                <tr><th colspan="2" style="text-align:center;">شركة مصاري</th> </tr>
                <tr><th colspan="2"  style="text-align:center;font-size: 16px;"><%=BTC.getStrBTCName(session)%></th> </tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم التليفون</td><td style="padding-left: 10px;"><%= receipt.getPhone()%></td></tr>
                    <%if (!request.getSession().getAttribute("serv_id").toString().equals("112") && !request.getSession().getAttribute("serv_id").toString().equals("111")) {%>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">اسم العميل </td><td style="padding-left: 10px;"><%= new String(request.getParameter("Customer_name").getBytes("ISO-8859-1"), "UTF-8")%></td></tr><%}%>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">قيمة الفاتورة </td><td style="padding-left: 10px;"><%= receipt.getAmount()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%=receipt.getFees()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;"><%=receipt.getAmount() + receipt.getFees()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">حاله العملية</td><td style="padding-left: 10px;">عملية ناجحة</td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم العملية </td><td style="padding-left: 10px;"><%= receipt.getTransaction_id()%></td></tr>
                    <%if (request.getSession().getAttribute("serv_id").toString().equals("124")) {%>
                <tr> <td style = "width: 50% ; padding-right: 30px;text-align: right;" > تاريخ الفاتورة</td > <td style = "padding-left: 10px;" > <%= receipt.getDue_date()%></td></tr>
                <% } else if (request.getSession().getAttribute("serv_id").toString().equals("117")) {%>
                <tr> <td style = "width: 50% ; padding-right: 30px;text-align: right;" > تاريخ انتهاء الفاتورة</td > <td style = "padding-left: 10px;" ><%=receipt.getDue_date()%></td></tr>
                    <%}%>
                    <%if (request.getSession().getAttribute("serv_id").toString().equals("111")) {%>
                <tr> <td style = "width: 50% ; padding-right: 30px;text-align: right;" > رقم الطلب</td > <td style = "padding-left: 10px;" ><%= receipt.getProviderResponse().getPROVIDER_TRANSACTION_ID()%></td></tr>
                    <%}%>
                <tr><td style="padding-left:15px;width: 50%;"> البائع</td><td style="text-align: right;padding-right:25px;width: 50%; "><%= request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td> </tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">التاريخ</td><td style="padding-left: 10px;"><%=time1%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">الوقت  </td><td style="padding-left: 10px;"><%=date1%></td></tr>
                <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">قد يستغرق تنفيذ العمليه ساعتين في حاله بطئ الشبكات</th> </tr>
                <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامكم مصارى لخدمات الدفع الذكية</th> </tr>
                <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">خدمة عملاء مصارى: 16994</th> </tr>
                <tr> <th colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</th> </tr>
                <tr style="display: none">
                    <th colspan="2"><img src="./img/WeLandLine.jpg" alt="Masary Icon" width="50" height="50"></th>
                    <th colspan="2"><img src="./img/WeInternet.jpg" alt="Masary Icon" width="50" height="50"></th>
                    <th colspan="2"><img src="./img/Masary.jpg" alt="Masary Icon" width="50" height="50"></th>
                </tr>
            </table>
            <input type="submit" id="print"  value="OK" />
            
            <input type="submit" id="printButton"  value="print" onclick="checkBrowser()" />
        </form>
    </div>
    <!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>