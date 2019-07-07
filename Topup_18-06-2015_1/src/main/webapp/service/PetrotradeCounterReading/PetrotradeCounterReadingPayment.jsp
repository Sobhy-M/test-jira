<%@page import="com.masary.integration.dto.PetrotradeCounterReadingPayment"%>
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
    session.setAttribute("ErrorCode", "");
    MasaryManager.logger.info("BillPayment " + "PetroTrade");
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);
    PetrotradeCounterReadingPayment counterReadingPayment = (PetrotradeCounterReadingPayment) session.getAttribute("petrotradeCounterReadingPayment");
    long ledgerTrxId=counterReadingPayment.getLedgerTrxId();
    String subscriberName=counterReadingPayment.getSubscriberName();
    double currentReading=counterReadingPayment.getCurrentReading();
    double appliedFees=counterReadingPayment.getAppliedFees();
    double toBepaid=counterReadingPayment.getToBepaid();
    double transactionAmount=counterReadingPayment.getTransactionAmount();
    
    
%>
<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 450px" >');
                printwindow.document.write('<tr><th colspan="2"><img src="./img/petroTrade.png" alt="’Masary Icon"></th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>بتروتريد</td></tr >');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=ledgerTrxId%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=subscriberName%></td><td style="text-align: right;padding-right:25px;width: 50%;">اسم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=currentReading%></td><td style="text-align: right;padding-right:25px;width: 50%;">القراءة المسجلة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=appliedFees%></td><td style="text-align: right;padding-right:25px;width: 50%;">تكلفة الخدمة شاملة ضريبة القيمة المضافة</td></tr>');
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
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    
            <%}%>
            
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
                            <p align="right">القراءة المسجلة</p>
                        </td>
                        <td>
                            <input id="amount" type="text" value="<%= currentReading%>"  name="amount" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">تكلفة الخدمة</p>
                        </td>
                        <td>
                            <input id="fees" type="text" value="<%= appliedFees%>"  name="fees" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">اجمالى القيمة المطلوبة للعميل:</p>
                        </td>
                        <td>
                            <input id="ToBepaid" type="text" value="<%= toBepaid%>"  name="ToBepaid()" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">سيتم خصم</p>
                        </td>
                        <td>
                            <input id="IsDeddued" type="text" value="<%=transactionAmount%>" required name="IsDeddued" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">رقم العملية</p>
                        </td>
                        <td>
                            <input id="OperationID" value="<%=ledgerTrxId%>" type="text" required name="OperationID" readonly style="background-color: #EDEDED;">
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
                            <p align="right">حالة العملية</p>
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