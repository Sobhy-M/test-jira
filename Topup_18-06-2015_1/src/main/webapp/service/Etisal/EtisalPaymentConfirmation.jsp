<%-- 
    Document   : EtisalPaymentConfirmation
    Created on : Mar 28, 2017, 3:58:39 PM
    Author     : Ahmed Khaled
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.integration.dto.EtisalPaymentResponse"%>
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
//    Masary_Bill_Type BTC = (Masary_Bill_Type) session.getAttribute("BTC");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
//    Bill_Response billResonse = (Bill_Response) request.getSession().getAttribute("bill_Response");
%>
<%
    DecimalFormat df2 = new DecimalFormat(".##");
    EtisalPaymentResponse etisalPaymentResponse = (EtisalPaymentResponse) request.getSession().getAttribute("etisalPaymentResponse");
    int transactionAmount = etisalPaymentResponse.getTransactionAmount().intValue();
    double appliedFees = etisalPaymentResponse.getAppliedFees();
    double tax = etisalPaymentResponse.getTax();
    double serviceCommission = appliedFees + tax;
    double paidAmount = transactionAmount + appliedFees;
    double PaidAmountEGP = Double.valueOf(df2.format(paidAmount));
//    double deductedAmount = Double.valueOf(df2.format(originalDeductedAmount));
    double merchantcommission = etisalPaymentResponse.getMerchantCommission();
    double txnAmount = etisalPaymentResponse.getBillAmount();
    double originalDeductedAmount = txnAmount + appliedFees - merchantcommission;
    String trxStatus = etisalPaymentResponse.getRequestStatus();
    String globalTrxID = etisalPaymentResponse.getGlobalTrxId();
    String phoneNumber = etisalPaymentResponse.getPhoneNumber();
    String clientName = etisalPaymentResponse.getClientName();
    double toBePaid = etisalPaymentResponse.getToBepaid();
    double deductedAmount = Double.valueOf(df2.format(originalDeductedAmount));
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
                printwindow.document.write('<tr><td></td><td><img src="./img/Masary.jpg" " style="float: right;"/></td></tr>');
                printwindow.document.write('<tr><th colspan="2">إتصال</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= globalTrxID%></td><td style="text-align: right;padding-right:25px;width: 50%;">:رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= date1%>  <%= time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ وتوقيت العملية</td></tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=phoneNumber%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >:رقم التليفون</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=clientName%></td><td style = "text-align: right;padding-right:25px;width:50%;">:اسم العميل</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= txnAmount%></td><td style = "text-align: right;padding-right:25px;width:50%;">:المبلغ بالجنيه</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= appliedFees%></td><td style = "text-align: right;padding-right:25px;width:50%;">:تكلفة الخدمة</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=toBePaid%></td><td style = "text-align: right;padding-right:25px;width:50%;">:المبلغ الاجمالى</td> </tr><tr>');
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
                window.location.replace("2.jsp");
            }
        </script>
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <h3 style="color: red; font-size: 18px"><%=CONFIG.getEtisalRecieptMess1(session)%><br><%=globalTrxID%></h3>
        <div>
            <table cellspacing="10" style="font-size: 12px; font-weight: 900;" > 
                <tr><!--<th colspan="2">شركة مصاري</th> --></tr>
                <tr><td colspan="2" style="text-align: center"><%=CONFIG.EtisalServiceName%></td> </tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">قيمة الفاتورة </td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%= transactionAmount%>" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%= appliedFees%>" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">اجمالى المبلغ مدفوع بالجنيه </td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%= toBePaid %>" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">سيتم خصم:</td><td><input type="text" readonly tabindex="1" value="<%=deductedAmount%>" style="background-color: #EDEDED; float: right;"/><span style="float: left;">  من رصيد محفظتك</span></td></tr>                
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">حالة العملية</td><td style="padding-left: 10px;">ناجحة</td></tr>                
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">تاريخ العملية</td><td style="padding-left: 10px;"><%=date1%></td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">توقيت العملية</td><td style="padding-left: 10px;"><%=time%></td></tr>                
                <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="طباعه" onclick="setDivPrint()" /></td></tr>
				<tr style="visibility: hidden"><td><img src="./img/Masary.jpg" style="float: start"/></td></tr>                
            </table>
        </div>
        <!--</form>-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>