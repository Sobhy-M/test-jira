
<%@page import="com.masary.database.dto.Bill_Provider_Response"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.dto.Masary_Bill_Receipt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }

    session = request.getSession();
    Masary_Bill_Type BTC = MasaryManager.getInstance().getBTC(String.valueOf(request.getSession().getAttribute("serv_id").toString()));

    String custId = null;
    if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }

    DecimalFormat myFormatter = CONFIG.getFormater(session);
    Masary_Bill_Receipt receipt = new Masary_Bill_Receipt();
    receipt = (Masary_Bill_Receipt) session.getAttribute("receipt");

    String date1 = "";
    String time1 = "";

    try {
       
            date1 = receipt.getTransaction_date().substring(0, 9);
            time1 = receipt.getTransaction_date().substring(9, 16);
     
    } catch (Exception e) {
        MasaryManager.logger.error(e.getMessage());
    }

%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getREPRINT(session)%></title>

        <script>
            function setDivPrintChrome() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 450px" >');
                printwindow.document.write('<tr><th colspan="2"><img src="./img/petroTrade.png" alt="’Masary Icon"></th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>بتروتريد</td></tr >');
                <%if (request.getSession().getAttribute("serv_id").toString().equals("618")) {%>

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getGlobalTrxID()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time1%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم البائع</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getSubscriberName()%></td><td style="text-align: right;padding-right:25px;width: 50%;">اسم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getSubscriberNumber()%></td><td style="text-align: right;padding-right:25px;width: 50%;">رقم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getBillsNumber()%></td><td style="text-align: right;padding-right:25px;width: 50%;">عدد الفواتير</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= receipt.getAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%;">اجمالي قيمة الفواتير</td> </tr>');
//                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getFees()%></td><td style="text-align: right;padding-right:15px;width: 50%;">تكلفة الخدمة شامله ضريبه القيمة المضافه  </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getToBePaid()%></td><td style="text-align: right;padding-right:15px;width: 50%;">اجمالي المبلغ المدفوع شامل ضريبه القيمة المضافه  </td> </tr>');
                <% }%>
                <%if (request.getSession().getAttribute("serv_id").toString().equals("619")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getLedgerTrxId()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time1%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getSubscriberName()%></td><td style="text-align: right;padding-right:25px;width: 50%;">اسم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getCurrentReading()%></td><td style="text-align: right;padding-right:25px;width: 50%;">القراءة المسجلة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getFees()%></td><td style="text-align: right;padding-right:25px;width: 50%;">تكلفة الخدمة شاملة ضريبة القيمة المضافة</td></tr>');
                <% }%>
                
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
            function setDivPrintFireFox() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 450px" >');
                printwindow.document.write('<tr><th colspan="2"><img src="./img/petroTrade.png" alt="’Masary Icon"></th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>بتروتريد</td></tr >');
                <%if (request.getSession().getAttribute("serv_id").toString().equals("618")) {%>

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getGlobalTrxID()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time1%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم البائع</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getSubscriberName()%></td><td style="text-align: right;padding-right:25px;width: 50%;">اسم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getSubscriberNumber()%></td><td style="text-align: right;padding-right:25px;width: 50%;">رقم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getBillsNumber()%></td><td style="text-align: right;padding-right:25px;width: 50%;">عدد الفواتير</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= receipt.getAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%;">اجمالي قيمة الفواتير</td> </tr>');
//                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getFees()%></td><td style="text-align: right;padding-right:15px;width: 50%;">تكلفة الخدمة شامله ضريبه القيمة المضافه  </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getToBePaid()%></td><td style="text-align: right;padding-right:15px;width: 50%;">اجمالي المبلغ المدفوع شامل ضريبه القيمة المضافه  </td> </tr>');
                <% }%>
                <%if (request.getSession().getAttribute("serv_id").toString().equals("619")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getLedgerTrxId()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= time1%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getSession().getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getSubscriberName()%></td><td style="text-align: right;padding-right:25px;width: 50%;">اسم المشترك</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getCurrentReading()%></td><td style="text-align: right;padding-right:25px;width: 50%;">القراءة المسجلة</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=receipt.getFees()%></td><td style="text-align: right;padding-right:25px;width: 50%;">تكلفة الخدمة شاملة ضريبة القيمة المضافة</td></tr>');
                <% }%>
                
                printwindow.document.write('<tr><th colspan="2">-------------------------------------------------------- </th></tr>');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>  شكرا لاستخدامكم مصاري لخدماتالدفع الذكية</td></tr >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size: 16px;" ><p>خدمة عملاء مصاري: 16994</td></tr >');
                printwindow.document.write('<tr><td colspan="2" style = "text-align:center;font-size:16px;"><p>www.e-masary.com</td></tr>');
               

                try {
                    // set portrait orientation
                    jsPrintSetup.setOption('orientation', jsPrintSetup.kPortraitOrientation);
                    // set top margins in millimeters
                    jsPrintSetup.setOption('marginTop', 0);
                    jsPrintSetup.setOption('marginBottom', 0);
                    jsPrintSetup.setOption('marginLeft', 0);
                    jsPrintSetup.setOption('marginRight', 0);
                    // set page header
                    jsPrintSetup.setOption('headerStrLeft', '');
                    jsPrintSetup.setOption('headerStrCenter', '');
                    jsPrintSetup.setOption('headerStrRight', '');
                    // set empty page footer
                    jsPrintSetup.setOption('footerStrLeft', '');
                    jsPrintSetup.setOption('footerStrCenter', '');
                    jsPrintSetup.setOption('footerStrRight', '');
                    // Suppress print dialog
                    jsPrintSetup.setSilentPrint(true);
                    // Do Print
                    jsPrintSetup.print();
                    // Restore print dialog
                    setTimeout(function () {
//                        console.log('print timeout');
                        printwindow.close();
                    }, 3000);
                } catch (err) {
                    alert(err);
                }
//                printwindow.print();

//                

            }

            function checkBrowser() {
                var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
                if (isOpera) {
                    setDivPrintChrome();
                }
                // Firefox 1.0+
                var isFirefox = typeof InstallTrigger !== 'undefined';
                if (isFirefox) {
                    setDivPrintFireFox();
                }
                // At least Safari 3+: "[object HTMLElementConstructor]"
                var isSafari = Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0;
                if (isSafari) {
                    setDivPrintChrome();
                }
                // Internet Explorer 6-11
                var isIE = /*@cc_on!@*/false || !!document.documentMode;
                if (isIE) {
                    setDivPrintIE();
                }
                // Edge 20+
                // Chrome 1+
                var isChrome = !!window.chrome && !!window.chrome.webstore;
                if (isChrome) {
                    setDivPrintChrome();
                }
            }

            function setDivPrintIE() {
                document.getElementById('print').disabled = true;
            }

        </script>
        </head>
    <body class="body" onload="checkBrowser()">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <div>
            <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="font-weight: bold">
         
                <table  style="width: 40%">
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.YallaPayInfoLabel%>
                        </td>
                        
                    </tr>
                                      <%if ( request.getSession().getAttribute("serv_id").toString().equals("618")) {%>
                       
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getTotalBillsAmount(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="amount" type="text" value="<%= receipt.getAmount()%>"  name="amount" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getServiceCost(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="fees" type="text" value="<%= receipt.getFees()%>"  name="fees" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.getUserAmount(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="ToBepaid" type="text" value="<%= receipt.getToBePaid()%>"  name="ToBepaid()" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                  
                    <tr>
                        <td>
                            <p align="right"><%=CONFIG.GetOperationID(request.getSession())%></p>
                        </td>
                        <td>
                            <input id="OperationID" value="<%=receipt.getGlobalTrxID()%>" type="text" required name="OperationID" readonly style="background-color: #EDEDED;">
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
                            <input id="Transactiontime" value="<%=time1%>" type="text" required name="Transactiontime" readonly style="background-color: #EDEDED;">
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
                                    <%} else if ( request.getSession().getAttribute("serv_id").toString().equals("619")) {%>
                                    
                    <tr>
                        <td>
                            <p align="right">القراءة المسجلة</p>
                        </td>
                        <td>
                            <input id="amount" type="text" value="<%=receipt.getCurrentReading() %>"  name="amount" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">تكلفة الخدمة</p>
                        </td>
                        <td>
                            <input id="fees" type="text" value="<%=receipt.getFees()%>"  name="fees" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">اجمالى القيمة المطلوبة للعميل:</p>
                        </td>
                        <td>
                            <input id="ToBepaid" type="text" value="<%=receipt.getToBePaid()%>"  name="ToBepaid()" readonly style="background-color: #EDEDED;" >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">سيتم خصم</p>
                        </td>
                        <td>
                            <input id="IsDeddued" type="text" value="<%=receipt.getAmount()%>" required name="IsDeddued" readonly style="background-color: #EDEDED;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="right">رقم العملية</p>
                        </td>
                        <td>
                            <input id="OperationID" value="<%=receipt.getLedgerTrxId()%>" type="text" required name="OperationID" readonly style="background-color: #EDEDED;">
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
                            <input id="Transactiontime" value="<%=time1%>" type="text" required name="Transactiontime" readonly style="background-color: #EDEDED;">
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
                                      <%}%>
                    
                    <tr>
                        <td style="text-align: center" colspan="2">
                            <input type="submit" name="btnSubmit" value="اغلاق"  class="Btn" onclick="setDivPrint()" >
                        </td>
                    </tr>
                    <tr style="display: none">
                        <th colspan="2"><img src="./img/petroTrade.png" alt="skills Bank Icon" width="50" height="50"></th>
                    </tr>
                </table>
            </form>
        </div>
    <!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
