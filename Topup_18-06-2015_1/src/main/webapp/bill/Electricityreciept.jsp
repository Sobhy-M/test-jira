<%-- 
    Document   : ElectricityReciept
    Created on : 18/07/2017, 02:00:00 PM
    Author     : Abdelsabor
--%>

<%@page import="com.masary.database.dto.Bill_Provider_Response"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
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
    String custId = null;
    if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }

    DecimalFormat myFormatter = CONFIG.getFormater(session);
    Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
    receipt = (Masary_Bill_Receipt) session.getAttribute("receipt");

    String date1 = "";
    String time1 = "";
    try {
        date1 = receipt.getTransaction_date().substring(0, 10);
        time1 = receipt.getTransaction_date().substring(11, 16);
    } catch (Exception e) {
        ////System.out.println(e);
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
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><th colspan="2">كهرباء جنوب القاهرة</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getTransaction_id()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time1%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%;">مبلغ العملية </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getFees()%></td><td style ="text-align: right;padding-right:25px;width: 50%;" >تكلفة الخدمة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= receipt.getAmount() + receipt.getFees()%> </td><td style="text-align: right;padding-right:25px;width: 50%;" >إجمالي المبلغ</td> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994 </th> </tr >');
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
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <div id="menu">
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <div>
            <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="font-weight: bold">
                <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900; width: auto" >
                    <tr>
                        <td>رقم العملية</td>
                        <td style="padding-left: 10px;"><%=receipt.getTransaction_id()%></td>
                    </tr>
                    <tr>
                        <td>حاله العملية</td>
                        <td style="padding-left: 10px;">عملية ناجحة</td>
                    </tr>
                    <tr><td>تاريخ العملية</td>
                        <td style="padding-left: 10px;"><%=date1%>
                        </td>
                    </tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">القيمة </td><td style="padding-left: 10px;"><%=receipt.getAmount()%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%=receipt.getFees()%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;"><%= receipt.getAmount() + receipt.getFees()%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم موبايل </td><td style="padding-left: 10px;"><%=receipt.getPhone()%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المحفظة </td><td style="padding-left: 10px;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td></tr>
                    <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</td> </tr>
                    <tr> <td colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</td> </tr>
                    <tr>
                        <td colspan="2" style="text-align:center">
                            <input type="submit" id="print" value="طباعه" onclick="setDivPrint();" />
                        </td>
                    </tr>
                </table> 

            </form>
        </div><!-- End content body -->

        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
        <!-- End of Main body-->
    </body>
</html>
