<%-- 
    Document   : ValuPaymentConfirmation
    Created on : Oct 29, 2017, 1:53:32 PM
    Author     : amira
--%>

<%@page import="com.masary.integration.dto.ValuPaymentResponse"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
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
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
%>
<%
    ValuPaymentResponse paymentResponse = (ValuPaymentResponse) request.getSession().getAttribute("PaymentResponse");
    String globalTrxID = paymentResponse.getGlobalTrxId();
    String phoneNumber = paymentResponse.getMobilNumber();
    double feesVAT = paymentResponse.getAppliedFees() + paymentResponse.getTax();
    double toBePaid = paymentResponse.getToBepaid(); 
    double transactionAmount = paymentResponse.getTransactionAmount();
    double merchantCommission = paymentResponse.getMerchantCommission();
    String paymentrReferenceNumber = paymentResponse.getPaymentrReferenceNumber();
    double dueAmount = paymentResponse.getDueAmount();
%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
        <script>
            function setDivPrint() {

                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/valu.png"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">فاليو</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=globalTrxID%></td><td style="text-align: right;padding-right:25px;width: 50%;">:رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%>  <%=time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ وتوقيت العملية</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=paymentrReferenceNumber%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:رقم المرجع </td></tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=phoneNumber%></td><td style = "text-align: right;padding-right:25px;width:50%;">:رقم موبايل العميل</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=dueAmount%></td><td style = "text-align: right;padding-right:25px;width:50%;">:إجمالي قيمة الأقساط </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=feesVAT%></td><td style = "text-align: right;padding-right:25px;width:50%;">:تكلفة الخدمة شامل ضريبة القيمة المضافة </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= toBePaid%></td><td style = "text-align: right;padding-right:25px;width:50%;">:اجمالى المبلغ المدفوع </td> </tr><tr>');

                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> قد يستغرق تنفيذ العملية ساعتين فى حالة بطئ الشبكات <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
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
        <div>
            <script type ="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>

            <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT + role + ".jsp"%>" method="POST" >
            <div>
                <table cellspacing="10" style="font-size: 12px; font-weight: 900;"  > 
                    <tr><td colspan="2" style="text-align: center;"><%=CONFIG.ValuServiceName%></td> </tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"> إجمالي قيمة الأقساط المدفوعة </td><td style="padding-left: 10px;"><%= dueAmount%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">سيتم خصم من محفظتك   </td><td style="padding-left: 10px;"><%= transactionAmount%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">عموله التاجر </td><td style="padding-left: 10px;"><%= merchantCommission%></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم العملية  </td><td style="padding-left: 10px;"><%= globalTrxID%></td></tr>
                    <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;"> حالة العملية</td><td style="padding-left: 10px;">ناجحة</td></tr>                
                    <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">تاريخ العملية</td><td style="padding-left: 10px;"><%=date1%></td></tr>
                    <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">توقيت العملية</td><td style="padding-left: 10px;"><%=time%></td></tr>                
                    <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="إغلاق وطباعة" onclick="setDivPrint()" /></td></tr>
                </table>
            </div>
            <img src="./img/valu.png" style="visibility: hidden;"/>
        </form>
        <!--</form>-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>