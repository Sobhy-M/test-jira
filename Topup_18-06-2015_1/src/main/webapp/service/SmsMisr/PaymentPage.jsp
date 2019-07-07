<%-- 
    Document   : PaymentPage
    Created on : Jul 12, 2017, 1:29:57 PM
    Author     : user
--%>
<%@page import="com.masary.integration.dto.SmsMisrRequest"%>
<%@page import="com.masary.integration.dto.SmsMisrPaymentDTO"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
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
    String pinNumber = loginInfo.getAgent().getPin();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);

%>
<%    SmsMisrPaymentDTO smsMisrPaymentResponse = (SmsMisrPaymentDTO) request.getSession().getAttribute("smsMisrPaymentResponse");
    SmsMisrRequest smsMisrRequest = (SmsMisrRequest) request.getSession().getAttribute("smsMisrRequest");
    String globalTrxId = smsMisrPaymentResponse.getGlobalTrxId();
    double commission = smsMisrPaymentResponse.getMerchantCommission();
    double trxAmount = smsMisrPaymentResponse.getTransactionAmount();
    double toBePaid = smsMisrPaymentResponse.getToBepaid();
    String customerCode = smsMisrRequest.getCustomerCode();
    String trxId = smsMisrRequest.getTransactionId();


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
                printwindow.document.write('<tr><td colspan="2" style="text-align: left; float: left;"><img src="img/SmsMisr.jpg"  width="170px" height="70px"></td><td><img src="img/newlogo.png"  width="185" height="50"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">إس إم إس مصر</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=globalTrxId%></td><td style="text-align: right;padding-right:25px;width: 50%;">: رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%>  <%=time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ وتوقيت العملية </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=pinNumber%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >:رقم التاجر </td> </tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=customerCode%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >:رقم العميل </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trxId%></td><td style = "text-align: right;padding-right:25px;width:50%;">:الرقم المرجعى </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=toBePaid%></td><td style = "text-align: right;padding-right:25px;width:50%;">:اجمالى قيمة العملية شاملة تكلفة الخدمة وضريبة القيمة المضافة </td></tr><tr>');
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
            <%if (request.getSession()
                        .getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <h3 style="color: red; font-size: 18px">عملية ناجحة</h3>
            <div>
                <table cellspacing="10" style="font-size: 12px; font-weight: 900; width: 45%" > 
                    <tr><td colspan="2" style="text-align: center; font-weight: bold; font-size: x-large"><%=CONFIG.SMSMISR_SERVICE_NAME%></td> </tr>

                <tr><td class="td"><p style="font-size: large">رقم العملية :</p> </td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=globalTrxId%>" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td class="td"><p style="font-size: large">عمولتك هى :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=commission%> جنيه" style="background-color: #EDEDED; float: right;" /></td></tr>
                <tr><td class="td"><p style="font-size: large"> تم خصم:</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=trxAmount%> جنيه" style="background-color: #EDEDED; float: right;"/></td></tr>
                <tr><td class="td"><p style="font-size: large"> رصيدك الحالى:</p></td><td><input type="text" readonly tabindex="1" value="<%=customerBalance%> جنيه" style="background-color: #EDEDED; float: right;"/></td></tr>                
                <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="إغلاق وطباعة" onclick="setDivPrint()" /></td></tr>

            </table>
        </div>
        <img src="./img/newlogo.png" style="visibility: hidden"/>
        <img src="./img/SmsMisr.jpg" style="visibility: hidden"/>       
        <!--</form>-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>