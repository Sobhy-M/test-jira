<%-- 
    Document   : bill_payment_confirmation
    Created on : 01/05/2012, 07:12:20 م
    Author     : Michael
--%>

<%@page import="com.masary.integration.dto.TEDataBillsResponse"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%
    response.setCharacterEncoding("windows-1256");
    response.setContentType("text/html;charset=windows-1256");
    response.setHeader("Content-Language" , "ar");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    TEDataBillsResponse paymentRespponseDto = (TEDataBillsResponse)session.getAttribute("tedataPaymentResponse");   
%>

<% 
   // Date dueDate = new Date(paymentRespponseDto.getNewExpiryDateAfterRenewal());
  //  String dueDateString = dateFormat.format(dueDate);
%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
        <script charset="UTF-8">
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="150" height="60"></th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">We</th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">الانترنت المنزلى</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=paymentRespponseDto.getGlobalTrxId()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= session.getAttribute(CONFIG.PARAM_PIN).toString() %> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ الدفع</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت الدفع</td> </tr><tr>');                       
//                printwindow.document.write('<tr><td style="style="padding-left:15px;width: 50%;"><%= paymentRespponseDto.getNewExpiryDateAfterRenewal()%></td><td style="text-align: right;padding-right:25px;width: 50%;" > تاريخ الفاتورة </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=paymentRespponseDto.getReceiptNumber()%></td><td style="text-align:right;padding-right:20px;width: 50%; "> رقم الطلب </td></tr>');
                printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= paymentRespponseDto.getLandLine() %></td><td style="text-align: right;padding-right:25px;width: 50%;">رقم العميل </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= paymentRespponseDto.getTotalDueForRenewal() %></td><td style="text-align: right;padding-right:25px;width: 50%;">قيمة الفاتورة </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= paymentRespponseDto.getAppliedFees() %> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >تكلفة الخدمة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= paymentRespponseDto.getToBepaid() %> </td><td style="text-align: right;padding-right:25px;width: 50%;" >إجمالي المبلغ شامل الضريبة</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------- </th></tr><br>');

                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;direction: rtl;" ><p>لمزيد من المعلومات عن برنامج المكافأت (4U) المقدم من وى للعملاء الحاليين والجدد يرجى زيارة موقعنا www.te.eg أو الاتصال ب 111<p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> te.eg للاشتراك حدث بياناتك من خلال   <p></th> <p></th> </tr >');

                   printwindow.document.write('<tr><th colspan="2">----------------------------------- </th></tr><br>');
                printwindow.document.write('<tr> <th style="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3">خدمة عملاء مصارى: 16994</th></tr>');
                printwindow.document.write('<tr><th style="font-family:courier;font-size:18px; " colspan="3" >www.e-masary.com</th > </tr></table>  ');                
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
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="font-weight: bold">
            <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900; width: auto" >            
                <tr>
                    <td>حاله العملية</td>
                    <td style="padding-left: 10px;">عملية ناجحة</td>
                </tr>
                <tr><td>تاريخ الدفع </td>
                    <td style="padding-left: 10px;"><%=date1%>
                    </td>
                </tr>
                <tr><td>توقيت الدفع</td>
                    <td><%=time%></td>
                </tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> رقم الفاتورة  </td><td style="padding-left: 10px;"><%= paymentRespponseDto.getReceiptNumber() %></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> قيمة الفاتورة  </td><td style="padding-left: 10px;"><%= paymentRespponseDto.getTotalDueForRenewal() %></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%=paymentRespponseDto.getAppliedFees()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;"><%= paymentRespponseDto.getToBepaid() %></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم التليفون </td><td style="padding-left: 10px;"><%= paymentRespponseDto.getLandLine() %></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المحفظة </td><td style="padding-left: 10px;"><%=paymentRespponseDto.getAccountId()%></td></tr>
                <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</td> </tr>
                <tr> <td colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</td> </tr>
                <tr>
                    <td colspan="2" style="text-align:center">
                        <input type="submit" id="print" value="اغلاق وطباعه" onclick="setDivPrint();" />
                    </td>
                </tr>
                <tr style="display: none">
                    <th colspan="2"><img src="./img/Masary.jpg" alt="TE Data" width="50" height="50"></th>
                </tr>
            </table>
        </form>
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
