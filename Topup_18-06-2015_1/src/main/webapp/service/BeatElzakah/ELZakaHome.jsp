<%-- 
    Document   : BeatELZakaHome
    Created on : Jul 17, 2016, 10:06:24 AM
    Author     : AYA
--%>

<%@page import="com.masary.database.dto.Bill_Response"%>
<%@page import="java.util.Date"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    Masary_Bill_Type BTC = (Masary_Bill_Type) session.getAttribute("BTC");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    Bill_Response billResonse = (Bill_Response) request.getSession().getAttribute("bill_Response");
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
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
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=billResonse.getTRANSACTION_ID()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= request.getParameter("BILLING_ACCOUNT")%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم موبايل</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ التبرع</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت التبرع</td> </tr><tr>');

                printwindow.document.write('<th colspan = "2" ><p style="text-align:center;font-size: 16px;">الخط الساخن  لبيت الزكاة والصدقات المصري:-  15111 </th> </tr>');
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
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
        <H3 style="color: red; font-size: 18px"><%=CONFIG.getElzakHomeRecieptMess1(session)%><%=billResonse.getTRANSACTION_ID()%><br><%=CONFIG.getBillRecieptMess3(session)%><%=billResonse.getPROVIDER_TRANSACTION_ID()%></H3>
    <div>
        <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900;" > 
            <tr><td colspan="2"><p><%=BTC.getSERVICE_AR_NAME()%></p></td></tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">حاله العملية</td><td style="padding-left: 10px;">عملية ناجحة</td></tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">تاريخ التبرع</td><td style="padding-left: 10px;"><%=date1%></td></tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">توقيت التبرع</td><td style="padding-left: 10px;"><%=time%></td></tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">رقم العملية </td><td style="padding-left: 10px;"><%=billResonse.getTRANSACTION_ID()%></td></tr>
            <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">القيمة </td><td style="padding-left: 10px;"><%= request.getParameter(CONFIG.AMOUNT)%></td></tr>
            <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%=Double.parseDouble(request.getParameter("Fees").toString())%></td></tr>
            <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;"><%= Double.parseDouble(request.getParameter(CONFIG.AMOUNT).toString()) + Double.parseDouble(request.getParameter("Fees").toString())%></td></tr>
            <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم موبايل </td><td style="padding-left: 10px;"><%= request.getParameter("BILLING_ACCOUNT")%></td></tr>
            <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المحفظة </td><td style="padding-left: 10px;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td></tr>
            <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</th> </tr>
            <th colspan = "2" ><p style="text-align:center;font-size: 16px;">الخط الساخن  لبيت الزكاة والصدقات المصري:-  15111 </th> </tr>
            <tr> <th colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</th> </tr>
        </table>
    </div>
    <input type="submit" id="print" value="طباعه" onclick="setDivPrint();" />
    <!--</form>-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
