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
    session = request.getSession();
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(String.valueOf(session.getAttribute("SERVICE_ID").toString()));
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    DonationAgentPaymentRespponseDto paymentRespponseDto = (DonationAgentPaymentRespponseDto) request.getAttribute("donationPaymentResponse");
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/wasletkher.png"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">مؤسسة وصلة خير</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=paymentRespponseDto.getTRANSACTION_ID()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ التبرع</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت التبرع</td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
                printwindow.document.write('<tr><td  style="padding-left:15px;width: 50%;"><%=new String(request.getParameter("DonationType").getBytes("ISO-8859-1"), "utf-8")%></td><td style ="text-align: right;padding-right:25px;width: 50%;" >نوع التبرع</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= request.getParameter("Amount")%></td><td style="text-align: right;padding-right:25px;width: 50%;">مبلغ التبرع </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=Double.parseDouble(request.getParameter("Fees").toString())%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >تكلفة الخدمة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= Double.parseDouble(request.getParameter("Amount").toString()) + Double.parseDouble(request.getParameter("Fees").toString())%> </td><td style="text-align: right;padding-right:25px;width: 50%;" >إجمالي المبلغ</td> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994 </th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
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
                    <td colspan="2" style="text-align: center"><p>وصلة خير </p></td> 
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center"><%= new String(request.getParameter("DonationType").getBytes("ISO-8859-1"), "utf-8")%></td> 
                </tr>
                <tr>
                    <td>حاله العملية</td>
                    <td style="padding-left: 10px;">عملية ناجحة</td>
                </tr>
                <tr><td>تاريخ التبرع</td>
                    <td style="padding-left: 10px;"><%=date1%>
                    </td>
                </tr>
                <tr><td>توقيت التبرع</td>
                    <td><%=time%></td>
                </tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">قيمة الصك /قيمة التبرع:  </td><td style="padding-left: 10px;"><%= request.getParameter("Amount")%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%=Double.parseDouble(request.getParameter("Fees").toString())%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;"><%= Double.parseDouble(request.getParameter("Amount").toString()) + Double.parseDouble(request.getParameter("Fees").toString())%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم موبايل </td><td style="padding-left: 10px;"><%= request.getParameter("PhoneNumber")%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المحفظة </td><td style="padding-left: 10px;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td></tr>
                <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</td> </tr>
                <tr> <td colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</td> </tr>
                <tr>
                    <td colspan="2" style="text-align:center">
                        <input type="submit" id="print" value="طباعه" onclick="setDivPrint();" />
                    </td>
                </tr>
                <tr style="display: none">
                    <th colspan="2"><img src="./img/wasletkher.png" alt="wasletKher" width="50" height="50"></th>
                </tr>
            </table>
        </form>
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
