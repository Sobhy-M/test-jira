<%-- 
    Document   : bill_inquiry
    Created on : 01/05/2012, 07:10:57 م
    Author     : Michael
--%>
<%@page import="com.masary.integration.dto.TransactionRepresentation"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    session = request.getSession();
    MasaryManager.logger.info("BillPayment " + "PetroTrade");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
//    Masary_Bill_Type BTC = (Masary_Bill_Type) session.getAttribute("BTC");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    TransactionRepresentation transactionResponse = (TransactionRepresentation) session.getAttribute("transactionResponse");
    double commession = transactionResponse.getMerchantCommission();
    double fees = transactionResponse.getAppliedFees() + transactionResponse.getTax();
    double totalAmount = transactionResponse.getToBepaid();
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 450px" >');
                printwindow.document.write('<tr><th colspan="2"><img src="./img/petroTrade.png" alt="’Masary Icon"></th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>بتروتريد</td></tr >');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=transactionResponse.getGlobalTrxId()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم البائع</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=transactionResponse.getSubscriberName()%></td><td style="text-align: right;padding-right:25px;width: 50%;">اسم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=transactionResponse.getSubscriberNumber()%></td><td style="text-align: right;padding-right:25px;width: 50%;">رقم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=transactionResponse.getBillsNumber()%></td><td style="text-align: right;padding-right:25px;width: 50%;">عدد الفواتير</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= transactionResponse.getAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%;">اجمالي قيمة الفواتير</td> </tr>');
//                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=fees%></td><td style="text-align: right;padding-right:15px;width: 50%;">تكلفة الخدمة شامله ضريبه القيمة المضافه  </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=totalAmount%></td><td style="text-align: right;padding-right:15px;width: 50%;">اجمالي المبلغ المدفوع شامل ضريبه القيمة المضافه  </td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>  شكرا لاستخدامكم مصاري لخدماتالدفع الذكية</td></tr >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>خدمة عملاء مصاري: 16994</td></tr >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size:16px;"><p>www.e-masary.com</td></tr>');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();


            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>بتروتريد</title>
    </head>
    <body class="body" >
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
        <div  class="content_body">
            <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%>  method="POST">
                <table  style="width: 40%">
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.YallaPayInfoLabel%>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getTotalBillsAmount(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="amount" type="text" value="<%= transactionResponse.getAmount()%>"  name="amount" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getServiceCost(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="fees" type="text" value="<%= transactionResponse.getAppliedFees()%>"  name="fees" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getUserAmount(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="ToBepaid" type="text" value="<%= transactionResponse.getToBepaid()%>"  name="ToBepaid()" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getIsDeddued(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="IsDeddued" type="text" value="<%=transactionResponse.getTransactionAmount()%>" required name="IsDeddued" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.GetOperationID(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="OperationID" value="<%=transactionResponse.getGlobalTrxId()%>" type="text" required name="OperationID" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getTransactionDate(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="TransactionDate" value="<%=date1%>" type="text" required name="TransactionDate" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getTransactionTime(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="Transactiontime" value="<%=time%>" type="text" required name="Transactiontime" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getSTATUS_CODE(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="TransactionStatus" value="<%=CONFIG.GetSuccessfulTransaction(request.getSession())%>" type="text" required name="TransactionStatus" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center" colspan="2">
                            <input type="submit" name="btnSubmit" value="اغلاق وطباعه"  class="Btn" onclick="setDivPrint()" >
                        </td>
                    </tr>
                    <tr style="display: none">
                        <th colspan="2"><img src="./img/petroTrade.png" alt="skills Bank Icon" width="50" height="50"></th>
                    </tr>
                </table>
            </form>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>