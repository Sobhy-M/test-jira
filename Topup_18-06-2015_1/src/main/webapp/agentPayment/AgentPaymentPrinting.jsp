<%-- 
    Document   : bill_payment_confirmation
    Created on : 01/05/2012, 07:12:20 م
    Author     : Michael
--%>

<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
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
//    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(String.valueOf(request.getSession().getAttribute("SERVICE_ID").toString()));
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    DonationAgentPaymentRespponseDto paymentRespponseDto = (DonationAgentPaymentRespponseDto) session.getAttribute("donationPaymentResponse");
    String serviceId = (String) session.getAttribute("SERVICE_ID");
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
    </head>
    <script>
        function setDivPrint() {
            var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
            printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
            printwindow.document.write('<tr><th colspan="2">شركة مصاري</th></tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= request.getParameter(CONFIG.AMOUNT)%></td><td style="text-align: right;padding-right:25px;width: 50%;">المبلغ بالجنيه </td> </tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=Double.parseDouble(request.getParameter("Fees").toString())%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" > تكلفة الخدمة </td></tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= Double.parseDouble(request.getParameter(CONFIG.AMOUNT).toString()) + Double.parseDouble(request.getParameter("Fees").toString())%> </td><td style="text-align: right;padding-right:25px;width: 50%;" > المبلغ الاجمالي </td> </tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" > رقم المحفظة</td> </tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=paymentRespponseDto.getTRANSACTION_ID()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
            printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>خدمة تحصيل شركات  - تحصيلات دلتا<p></th> </tr >');
            printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994 <p></th> </tr >');
            printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
            printwindow.location.reload();
            printwindow.document.close();
            printwindow.focus();
            printwindow.print();
            printwindow.close();
            window.location.replace("2.jsp");
        }
    </script>
    <body class="body">
        <div>
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <H3 style="color: red; font-size: 18px"><%=CONFIG.getRecieptMessAgentPayment(session)%><%=paymentRespponseDto.getTRANSACTION_ID()%><br><%=CONFIG.getBillRecieptMess3(session)%><%=paymentRespponseDto.getPROVIDER_TRANSACTION_ID()%></H3>
            <div>
                <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900;" > 
                    <tr><!--<th colspan="2">شركة مصاري</th> --></tr>
                    <%if (session.getAttribute("SERVICE_ID").toString() == "18234") {%>  
                    <tr><th colspan="2"><%= new String(request.getParameter(CONFIG.PROGRAM).getBytes("ISO-8859-1"), "utf-8")%></th> </tr><%}%>
                    <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">حاله العملية</td><td style="padding-left: 10px;">عملية ناجحة</td></tr>
                    <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">تاريخ التبرع</td><td style="padding-left: 10px;"><%=date1%></td></tr>
                    <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">توقيت التبرع</td><td style="padding-left: 10px;"><%=time%></td></tr>
                    <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">رقم العملية </td><td style="padding-left: 10px;"><%=paymentRespponseDto.getTRANSACTION_ID()%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">القيمة </td><td style="padding-left: 10px;"><%= request.getParameter(CONFIG.AMOUNT)%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%=Double.parseDouble(request.getParameter("Fees").toString())%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;"><%= Double.parseDouble(request.getParameter(CONFIG.AMOUNT).toString()) + Double.parseDouble(request.getParameter("Fees").toString())%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المحفظة </td><td style="padding-left: 10px;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td></tr>
                    <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</th> </tr>
                            <%if (session.getAttribute("SERVICE_ID").toString().equals("18234")) {%>
                            <%}%>
                    <tr> <th colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</th> </tr>
                </table>
            </div>
            <input type="submit" style="alignment-adjust: central" id="print" value="طباعه" onclick="setDivPrint();" />
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
