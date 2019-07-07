<%-- 
    Document   : PaymentPage
    Created on : Jul 5, 2017, 11:06:17 AM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.integration.dto.EsedPaymentResponse"%>
<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
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
    LoginDto loginInfo = (LoginDto) session.getAttribute("Login");
    double clientBalance = loginInfo.getAgent().getBalance();
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
    EsedPaymentResponse esedPaymentResponse = (EsedPaymentResponse) request.getSession().getAttribute("esedPaymentResponse");
    String globalTrxId = esedPaymentResponse.getGlobalTrxId();
    double merchantCommission = esedPaymentResponse.getMerchantCommission();
    double toBePaid = esedPaymentResponse.getToBepaid();
    String nationalID = esedPaymentResponse.getNationalId();
    String codeNumber = esedPaymentResponse.getCodeNumber();
    String nationalCode = "";
    if (nationalID == null) {
        nationalCode = codeNumber;
    } else {
        nationalCode = nationalID;
    }

    double deductedAmount = esedPaymentResponse.getTransactionAmount();

%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
        <style>
            input[type=text] {
                font-size: 14px;
            }
            p{
                font-size: 15px;
                font-weight: bold;
                text-align: center;
            }
            input.btn{
                font-size: 16px;
            }
            td.table{
                width: 60%;
                padding-right: 30px;
                text-align: right;
            }
            p.title{
                font-size: 16px;
                font-weight: bold;
                text-align: center;
                text-decoration: underline;
            }

        </style>
        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: left; float: left;"><img src="img/esed.jpg"  width="70px" height="70px"></td><td><img src="img/Masary.jpg"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">الجمعية المصرية لمساعدة صغار</th></tr>');
                printwindow.document.write('<tr><th colspan="2">الصناع والحرفيين</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=pinNumber%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >: رقم البائع</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=globalTrxId%></td><td style = "text-align: right;padding-right:25px;width:50%;">:رقم العملية </td></tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= date1%>  <%= time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ وتوقيت العملية </td></tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=nationalCode%></td><td style="text-align: right;padding-right:25px;width: 50%;">: الرقم القومى  / كود العميل</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=toBePaid%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >:المبلغ المدفوع شامل تكلفة الخدمة وضريبة القيمة المضافة</td> </tr>');
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
    </head>
    <body class="body">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <div>
                <table cellspacing="10" style="font-size: 12px; font-weight: 900; width: 45%" > 
                    <tr><td colspan="2" ><p class="title">عملية ناجحة</p></td></tr>
                    <tr><td class="table"><p>رقم العملية :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=globalTrxId%>" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td class="table"><p>عمولتك هى :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=merchantCommission%>" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td class="table"><p>تم خصم :</p></td><td><input type="text" readonly tabindex="1" value="<%=deductedAmount%>" style="background-color: #EDEDED; float: right;"/></td></tr>                
                <tr><td class="table"><p>رصيدك الحالى :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=clientBalance%>" style="background-color: #EDEDED; float: right;"/></td></tr>               
                <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="إغلاق وطباعة" onclick="setDivPrint()" class="btn"/></td></tr>

            </table>
        </div>
        <img src="./img/Masary.jpg" style="visibility: hidden"/>
        <img src="./img/esed.jpg" style="visibility: hidden"/>
        <!--</form>-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>