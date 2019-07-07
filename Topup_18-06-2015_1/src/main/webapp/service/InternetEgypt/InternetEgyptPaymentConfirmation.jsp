<%-- 
        Document   : bill_internet_egypt_info
    Created on : 03/04/2017, 01:11:19 م
    Author     : Mustafa
--%>

<%@page import="com.masary.integration.dto.InternetEgyptPaymentResponse"%>
<%@page import="com.masary.integration.dto.InternetEgyptInquiryResponse"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
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
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
  InternetEgyptPaymentResponse paymentResponse = (InternetEgyptPaymentResponse)  request.getSession().getAttribute(("paymentResponse"));
    double tax = paymentResponse.getTax();
    double appliedFees = paymentResponse.getAppliedFees()+tax;
  
   // double serviceCommission = appliedFees + tax;
  //  double paidAmount = transactionAmount + appliedFees;
    //double PaidAmountEGP = Double.valueOf(df2.format(paidAmount));
//    double deductedAmount = Double.valueOf(df2.format(originalDeductedAmount));
    double merchantcommission = paymentResponse.getMerchantCommission();
    double txnAmount = paymentResponse.getBillAmount();
    double transactionAmount = txnAmount + appliedFees - merchantcommission;
   // String trxStatus = paymentResponse.getRequestStatus();
    String globalTrxID = paymentResponse.getGlobalTrxId();
    String phoneNumber = paymentResponse.getPhoneNumber();
    String clientName = paymentResponse.getClientName();
    double toBePaid = paymentResponse.getToBepaid();
   
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
                printwindow.document.write('<tr><td></td><td><img src="./img/Masary.jpg" style="float: right;"/></td></tr>');
                printwindow.document.write('<tr><th colspan="2">انترنت ايجيبت </th></tr>');
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
            <h3 style="color: red; font-size: 18px"><%=CONFIG.getInternetEgyptReciept(session)%><%=paymentResponse.getGlobalTrxId()%><br></h3>
        <div>
            <table cellspacing="10" style="font-size: 20px; font-weight: 900;" > 
                <tr><!--<th colspan="2">شركة مصاري</th> --></tr>
                <tr><td colspan="2" style="text-align: center"><%=CONFIG.getInternetEgyptServiceName(session)%></td> </tr>
                
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">قيمة الفاتورة </td><td style="padding-left: 10px;"><%= request.getParameter(CONFIG.AMOUNT)%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%=Double.parseDouble(request.getParameter("Fees").toString())%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">اجمالي المبلغ المدفوع بالجنيه</td><td style="padding-left: 10px;"><%= Double.parseDouble(request.getParameter(CONFIG.AMOUNT).toString()) + Double.parseDouble(request.getParameter("Fees").toString())%></td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">سيتم خصم</td><td style="padding-left: 10px;"><%= Double.parseDouble(request.getParameter("DeductedAmount").toString())%></td></tr>

                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">حاله العملية</td><td style="padding-left: 10px;">عملية ناجحة</td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">تاريخ العملية</td><td style="padding-left: 10px;"><%=date1%></td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">توقيت العملية</td><td style="padding-left: 10px;"><%=time%></td></tr>
                <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="طباعه" onclick="setDivPrint();" /></td></tr>
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
