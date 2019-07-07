<%-- 
    Document   : ElectricityPayment
    Created on : Jul 10, 2017, 10:22:56 AM
    Author     : user
--%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.integration.dto.ElectricityPaymentResponse"%>
<%@page import="com.masary.integration.dto.ElectricityPaymentRequest"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    String custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    session = request.getSession();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    ElectricityPaymentResponse payResponse = (ElectricityPaymentResponse) request.getSession().getAttribute("electricityPaymentResponse");
    double serviceCost = payResponse.getAppliedFees();
    double commission = payResponse.getMerchantCommission();
    double totalAmount = payResponse.getToBepaid();
    double deductedAmount = payResponse.getTransactionAmount();
    AgentDTO agent = MasaryManager.getInstance().getAgent(custId);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getElectricityServiceName(session)%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />

        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');

            <%if (session.getAttribute("SERVICE_ID").equals(99019)) {%>
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/AlexElectricity.jpg"  width="100" height="60"/></td>');
                printwindow.document.write('<td><img src="./img/Masary.jpg"  width="160" height="60"/></td></tr>');
            <%} else if (session.getAttribute("SERVICE_ID").equals(99021)) {%>
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/NorthElectricity.jpg"  width="100" height="60"/></td>');
                printwindow.document.write('<td><img src="./img/Masary.jpg"  width="160" height="60"/></td></tr>');
            <%} else if (session.getAttribute("SERVICE_ID").equals(99022)) {%>
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/BehiraElectricity.jpg"  width="100" height="60"/></td>');
                   <%} else if(session.getAttribute("SERVICE_ID").equals(99012)) {%>
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/MiddleEgyptElectricity.jpg"  width="100" height="60"/></td>');
                printwindow.document.write('<td><img src="./img/Masary.jpg"  width="160" height="60"/></td></tr>');
                   <%} else if(session.getAttribute("SERVICE_ID").equals(99018)){%>
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/SouthDeltaElectricity.jpg"  width="170" height="60"/></td>');       
                printwindow.document.write('<td><img src="./img/Masary.jpg"  width="160" height="60"/></td></tr>');
            <%} else if (session.getAttribute("SERVICE_ID").equals(99014)) {%>
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/canalElectricity.png"  width="180" height="60"/></td>');
                printwindow.document.write('<td><img src="./img/Masary.jpg"  width="160" height="60"/></td></tr>');
            <%} else if (session.getAttribute("SERVICE_ID").equals(99015)) {%>
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/NorthDeltaElectricity.jpg"  width="180" height="60"/></td>');
                printwindow.document.write('<td><img src="./img/Masary.jpg"  width="160" height="60"/></td></tr>');
                <%} else if (session.getAttribute("SERVICE_ID").equals(99013)) {%>
                printwindow.document.write('<tr><td style="text-align: center"><img src="./img/UpperEgyptElectricity.png"  width="100" height="60"/></td>');
                printwindow.document.write('<td><img src="./img/Masary.jpg"  width="160" height="60"/></td></tr>');
                <%}%>
                printwindow.document.write('<tr><th colspan="2">شركة مصاري</th></tr>');
                printwindow.document.write('<tr><th colspan="2"><%=CONFIG.getElectricityServiceName(session)%></th></tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=payResponse.getTransactionId()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= date1%>  <%= time%></td><td style="text-align: right;padding-right:25px;width: 50%;" >تاريخ و توقيت العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%=custId%></td><td style="text-align: right;padding-right:25px;width: 60%; ">رقم التاجر </td> </tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=payResponse.getClientName()%></td><td style = "text-align: right;padding-right:25px;width:50%;">اسم المشترك </td> </tr><tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=payResponse.getAccountNumber()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم السداد الالكتروني </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=payResponse.getBillDate()%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ الفاتورة  </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=payResponse.getBillAmount()%></td><td style = "text-align: right;padding-right:25px;width:50%;">اجمالي قيمه الفاتورة   </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= (payResponse.getAppliedFees() + payResponse.getTax())%></td><td style = "text-align: right;padding-right:25px;width:50%;">تكلفه الخدمه شامله ضريبه القيمه المضافه  </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=payResponse.getToBepaid()%></td><td style = "text-align: right;padding-right:25px;width:50%;">اجمالي المبلغ المدفوع شامل مصاريف الخدمة وضريبة القيمة المضافة </td> </tr><tr>');
                printwindow.document.write('<tr><th colspan="2">---------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr>  ');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------- </th></tr>');

                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> Powered by e-finance <p></th> </tr > </table>');


//                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------- </th></tr>');
//                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size:16px;"><p>Powered by e-finance </td></tr> ');

                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
//                window.location.replace("2.jsp");
            }
        </script>

    </head>
    <body>
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="font-weight: bold">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_Delta_Electricity_payConfirm%>" />
            <h2 align="center" ><%=CONFIG.GetSuccessfulTransaction(session)%></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >

                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم العملية </td><td style="padding-left: 10px;"><%=payResponse.getTransactionId()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المشترك </td><td style="padding-left: 10px;"><%= payResponse.getAccountNumber()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">القيمة </td><td style="padding-left: 10px;"><%= payResponse.getBillAmount()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">تكلفة الخدمة </td><td style="padding-left: 10px;"><%= payResponse.getAppliedFees()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">عمولتك هي :   </td><td style="padding-left: 10px;"><%= commission%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">المبلغ الاجمالي </td><td style="padding-left: 10px;"><%= payResponse.getToBepaid()%></td></tr>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">رقم المحفظة </td><td style="padding-left: 10px;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%></td></tr>
                <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994</th> </tr>
                <tr> <th colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</th> </tr>

                <!--                <tr><td>
                                        <p><%= CONFIG.getDiscountName(session)%></p>
                                    </td><td>
                                        <input type="text" name="TotalAmountPayable" value="<%=totalAmount%>" readonly style="background-color: #EDEDED;" >
                                    </td>
                                </tr>
                                
                                
                                <tr><td>
                                        <p><%= CONFIG.getBalanceCurrentName(session)%></p>
                                    </td><td>
                                        <input type="text" name="TotalAmountPayable" value="<%= agent.getBalance()%>" readonly style="background-color: #EDEDED;" >
                                    </td>
                                </tr>-->
                <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="إغلاق و طباعه" onclick="setDivPrint();" /></td></tr>


                <tr style="display: none">
                        <td><img src="./img/AlexElectricity.jpg"  width="100" height="60"/></td>
                        <td><img src="./img/BehiraElectricity.jpg"  width="100" height="60"/></td>
                        <td><img src="./img/NorthElectricity.jpg"  width="100" height="60"/></td>

                        <td><img src="./img/Masary.jpg"  width="80" height="60"/></td>
                    <td><img src="./img/AlexElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/BehiraElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/NorthElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/canalElectricity.png"  width="100" height="60"/></td>
                    <td><img src="./img/NorthDeltaElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/SouthDeltaElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/MiddleEgyptElectricity.jpg"  width="100" height="60"/></td>
                    <td><img src="./img/UpperEgyptElectricity.png"  width="100" height="60"/></td>
                    

                    <td><img src="./img/Masary.jpg"  width="80" height="60"/></td>
                </tr>
            </table>


            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
