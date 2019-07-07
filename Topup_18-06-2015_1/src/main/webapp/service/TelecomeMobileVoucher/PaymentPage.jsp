<%-- 
    Document   : PaymentPage
    Created on : Sep 13, 2017, 12:03:19 PM
    Author     : Ahmed Khaled
--%>
<%@page import="com.masary.utils.BuildUUID"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.masary.integration.dto.TelecomeVoucherPaymentResponse"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    BuildUUID build_uuid = new BuildUUID();
    String uuid = build_uuid.CreateUUID();
    session.setAttribute("uuid", uuid);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);

    TelecomeVoucherPaymentResponse telecomeVoucherPaymentResponse = (TelecomeVoucherPaymentResponse) request.getSession().getAttribute("telecomeVoucherPaymentResponse");
    String globalTrxId = telecomeVoucherPaymentResponse.getGlobalTrxId();
    double appliedFees = telecomeVoucherPaymentResponse.getAppliedFees();
    double toBePaid = telecomeVoucherPaymentResponse.getToBepaid();
    String voucherSerial = telecomeVoucherPaymentResponse.getVoucherSerial();
    String validationDate = telecomeVoucherPaymentResponse.getValidationDate();
    String voucherWord = telecomeVoucherPaymentResponse.getVoucherWord();

    StringBuilder str = new StringBuilder(telecomeVoucherPaymentResponse.getVoucherPin());
    int idx = str.length() - 4;

    while (idx > 0) {
        str.insert(idx, "-");
        idx = idx - 4;
    }

    String voucherPin = str.toString();

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Result</title>
        <script type="text/javascript">
            function cancel() {
                document.getElementById("HOMEFORM").submit();
            }


            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 450 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 600; width: 300px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/masarylogoo.jpg"  width="150" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="3">We</th> </tr>');
                printwindow.document.write('<tr><th colspan="3">خدمات المحمول</th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= globalTrxId%></td><td style="text-align: right;padding-right:25px;width: 50%;"> رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= date1%>  <%= time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >تاريخ وتوقيت العملية </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= voucherSerial%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >الرقم المسلسل </td> </tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=voucherPin%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم الكارت</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                <% if (telecomeVoucherPaymentResponse.getVoucherWord()!=null) {%>
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 15px;direction:rtl;" ><p><%=voucherWord.replace("&", "<br>")%><p></th> <p></th> </tr >');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
            <%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= session.getAttribute("voucherAmount").toString()%></td><td style = "text-align: right;padding-right:25px;width:50%;">قيمة الكارت </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=appliedFees%></td><td style = "text-align: right;padding-right:25px;width:50%;">تكلفة الخدمة </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=toBePaid%></td><td style = "text-align: right;padding-right:25px;width:50%;">اجمالى المبلغ المطلوب دفعه شامل ضريبه القيمه المضافه</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=validationDate%></td><td style = "text-align: right;padding-right:25px;width:50%;"> سارى حتى</td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>: ملاحظات للعميل<p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>تأكد من طباعة الكارت أمامك<p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>للشحن اطلب<p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>*555*<%=voucherPin%>#<p></th> </tr >');
//                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"></td><td style = "text-align: right;padding-right:25px;width:50%;">: ملاحظات للعميل</td> </tr><tr>');
//                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"></td><td style = "text-align: right;padding-right:25px;width:50%;">تأكد من طباعة الكارت أمامك</td> </tr><tr>');
//                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"></td><td style = "text-align: right;padding-right:25px;width:50%;">للشحن اطلب</td> </tr><tr>');
//                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"></td><td style = "text-align: right;padding-right:25px;width:50%;">*555*<%=voucherPin%>#</td> </tr><tr>');
                
            

                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> te.eg للاشتراك حدث بياناتك من خلال   <p></th> <p></th> </tr >');


                
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');

                printwindow.print();

                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
//                printwindow.print();
                printwindow.close();
                cancel();
                window.location.replace("2.jsp");
            }
        </script>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
    </head>
    <form id="HOMEFORM" action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="display: none;" ></form>
    <body class="body" onload="setDivPrint()">
        <%
            session = request.getSession();


        %>

        <%--<script type="text/javascript" src="img/CheckForms.js"></script>--%>
        <% if (role != null) {%>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="/img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="/img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

    <%}%>


    <div class="content_body"><br><br><!--Table Content area -->
        <table>
            <tbody>
                <tr>
                    <th  scope="row"><%=CONFIG.getError(session)%> </th>
                    <td> <%=  session.getAttribute("message")%>  
                    </td>
                </tr>
                <tr>
            <img src="./img/masarylogoo.jpg" style="visibility: hidden"/>  
            </tr>
            </tbody>
        </table>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->



<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>



