<%-- 
    Document   : PaymentPage
    Created on : Jul 9, 2017, 10:17:39 AM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.integration.dto.YallaPayPaymentResponse"%>
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

    LoginDto loginInfo =(LoginDto)  session.getAttribute("Login");
    String pinNumber = loginInfo.getAgent().getPin();
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
    YallaPayPaymentResponse yallaPayPaymentResponse = (YallaPayPaymentResponse) request.getSession().getAttribute("yallaPayPaymentResponse");
    String customerName = yallaPayPaymentResponse.getClientName();
    String trxId = yallaPayPaymentResponse.getTransactionId();
    String balanceType = yallaPayPaymentResponse.getBalanceType();
    double appliedFees = yallaPayPaymentResponse.getAppliedFees();
    double toBePaid = Double.valueOf(df2.format(yallaPayPaymentResponse.getToBepaid()));
    String globalTrxID = yallaPayPaymentResponse.getGlobalTrxId();
    int billAmount = yallaPayPaymentResponse.getBillAmount();
    double deductedAmount = Double.valueOf(df2.format(toBePaid - yallaPayPaymentResponse.getMerchantCommission()));
    

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
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/newlogo.png"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">يلا باى</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= globalTrxID%></td><td style="text-align: right;padding-right:25px;width: 50%;">: رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= date1%>  <%= time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ وتوقيت العملية </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=pinNumber%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >:رقم التاجر </td> </tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=customerName %> </td><td style = "text-align: right;padding-right:25px;width:50%;" >: اسم العميل</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=balanceType %></td><td style = "text-align: right;padding-right:25px;width:50%;">:نوع العملية </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=billAmount%></td><td style = "text-align: right;padding-right:25px;width:50%;">:قيمة العملية </td></tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=appliedFees %></td><td style = "text-align: right;padding-right:25px;width:50%;">:تكلفة الخدمة شاملة ضريبة القيمة المضافة </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=toBePaid %></td><td style = "text-align: right;padding-right:25px;width:50%;">:اجمالى المبلغ المطلوب </td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
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
         <style>
            input[type=text] {
                font-size: 14px;
            }
            p{
                font-size: 12px;
                font-weight: bold;
                text-align: center;
            }
            input.btn{
                font-size: 16px;
            }
            td.td{
                width: 60%;
                padding-right: 30px;
                text-align: right;
            }
            

        </style>
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <h3 style="color: red; font-size: 18px"><%=CONFIG.getYallaPayRecieptMess1(session)%><br><%=globalTrxID%></h3>
            <div>
            <table cellspacing="10" style="font-size: 12px; font-weight: 900; width: 45%" > 
                <tr><td colspan="2" style="text-align: center; font-weight: bold; font-size: x-large"><%=CONFIG.YallaPayServiceName%></td> </tr>

                <tr><td class="td"><p>حالة العملية :</p> </td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="ناجحة" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td class="td"><p>رقم العملية :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=trxId %>" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td class="td"><p>قيمة العملية :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=billAmount%>" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td class="td"><p>تكلفة الخدمة شاملة ضريبة القيمة المضافة :</p></td><td><input type="text" readonly tabindex="1" value="<%=appliedFees%>" style="background-color: #EDEDED; float: right;"/></td></tr>                
                <tr><td class="td"><p>اجمالى المبلغ المدفوع :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=toBePaid%>" style="background-color: #EDEDED; float: right;"/></td></tr>               
                <tr><td class="td"> <p>المبلغ المخصوم من محفظتك :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=deductedAmount%>" style="background-color: #EDEDED; float: right;"/></td></tr>               
                <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="إغلاق وطباعة" onclick="setDivPrint()" /></td></tr>
                
            </table>
        </div>
            <img src="./img/newlogo.png" style="visibility: hidden"/>       
        <!--</form>-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>