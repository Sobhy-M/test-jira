<%-- 
    Document   : PrintingVoucher
    Created on : Dec 7, 2015, 12:45:46 PM
    Author     : aya
--%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.integration.dto.TETopupResponse"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.utils.HelperFunctions"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
    <head>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting(session)%></title>
        <script type="text/javascript" src="http://jqueryjs.googlecode.com/files/jquery-1.3.1.min.js" ></script> 
        <%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }
            session = request.getSession();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            String date1 = dateFormat.format(date);
            String time = timeFormat.format(date);
            TETopupResponse topupPresentation = (TETopupResponse) session.getAttribute("CommessionResponse");
            DecimalFormat myFormatter = CONFIG.getFormater(session);
        %>

        <script>
            function gotoHome()
            {
                window.location.replace("2.jsp");
            }
            function checkBrowser() {
                var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
                if (isOpera) {
//                    alert(isOpera);
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
//                var isEdge = !isIE && !!window.StyleMedia;
                // Chrome 1+
                var isChrome = !!window.chrome && !!window.chrome.webstore;
                if (isChrome) {
                    setDivPrintChrome();
                }
                // Blink engine detection
//                var isBlink = (isChrome || isOpera) && !!window.CSS;


            }
            function setDivPrintChrome() {

                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="7"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/masarylogoo.jpg"  width="150" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="3">We</th> </tr>');
                printwindow.document.write('<tr><th colspan="3">خدمات المحمول</th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= topupPresentation.getGlobalTrxId()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" > رقم العملية </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%> </td><td style="text-align: right;padding-right:25px;width: 50%;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" > الوقت </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=topupPresentation.getProviderReferenceNumber()%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" > الرقم المرجعى </td></tr>');
                printwindow.document.write('<tr><th colspan="3">---------------------------</th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> <%=request.getParameter(CONFIG.PARAM_MSISDN)%></td><td style="text-align: right;padding-right:25px;width: 60%; ">رقم العميل</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%=topupPresentation.getServiceAmount()%></td><td style="text-align: right;padding-right:25px;width: 60%; ">قيمة الشحن</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%=topupPresentation.getAppliedFees()%></td><td style="text-align: right;padding-right:25px;width: 60%; ">تكلفة الخدمة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%=request.getParameter("UserAmount")%></td><td style="text-align: right;padding-right:25px;width: 60%; ">اجمالي مبلغ الخدمه شامل ضريبه القيمه المضافه</td> </tr>');
                printwindow.document.write('<tr><th colspan="3">-----------------------------------</th> </tr>');
            <% if (topupPresentation.getAdvertisement() != null) {%>
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;direction:rtl;" ><p><%=topupPresentation.getAdvertisement().replace("&", "<br>")%><p></th> <p></th> </tr >');
                printwindow.document.write('<tr><th colspan="3">-----------------------------------</th> </tr>');
            <%}%>
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> te.eg للاشتراك حدث بياناتك من خلال   <p></th> <p></th> </tr >');


                printwindow.document.write('<tr><th colspan="2" style = "text-align:center;font-size: 16px;">عند البطء في الشبكة قد يستغرق تنفيذ العملية 24 ساعة</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
            }

            function setDivPrintFireFox() {

                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="5"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/masarylogoo.jpg"  width="150" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="3">WE</th> </tr>');
                printwindow.document.write('<tr><th colspan="3">خدمات المحمول</th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=topupPresentation.getGlobalTrxId()%> </td><td style="font-family:courier;font-size:18px;text-align: right;width:50%;padding-right:25px" > رقم العملية </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%> </td><td style="font-family:courier;font-size:18px;text-align: right;width:50%;padding-right:25px" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=time%> </td><td style ="font-family:courier;font-size:18px;text-align: right;width:50%;padding-right:25px" > الوقت </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=topupPresentation.getGlobalTrxId()%> </td><td style ="font-family:courier;font-size:18px;text-align: right;width:50%;padding-right:25px" > الرقم المرجعى </td></tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=topupPresentation.getMobileNumber()%></td><td style="text-align: right;padding-right:25px;width: 50%">رقم العميل</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> <%=topupPresentation.getTransactionAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%; ">قيمة الشحن</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> <%=topupPresentation.getServiceAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%; ">تكلفة الخدمة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("UserAmount")%></td><td style="text-align: right;padding-right:25px;width: 50%; ">اجمالى المبلغ المطلوب دفعه شامل ضريبه القيمه المضافه</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
            <% if (topupPresentation.getAdvertisement() != null) {%>
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;direction:rtl;" ><p><%=topupPresentation.getAdvertisement().replace("&", "<br>")%><p></th> <p></th> </tr >');
                printwindow.document.write('<tr><th colspan="3">-----------------------------------</th> </tr>');
            <%}%>
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> te.eg للاشتراك حدث بياناتك من خلال   <p></th> <p></th> </tr >');


                printwindow.document.write('<tr><th colspan="2" style = "text-align:center;font-size: 16px;">عند البطء في الشبكة قد يستغرق تنفيذ العملية 24 ساعة</th> </tr>');
                
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.print();
                printwindow.close();

//                try {
//                    // set portrait orientation
//                    jsPrintSetup.setOption('orientation', jsPrintSetup.kPortraitOrientation);
//                    // set top margins in millimeters
//                    jsPrintSetup.setOption('marginTop', 0);
//                    jsPrintSetup.setOption('marginBottom', 0);
//                    jsPrintSetup.setOption('marginLeft', 0);
//                    jsPrintSetup.setOption('marginRight', 0);
//                    // set page header
//                    jsPrintSetup.setOption('headerStrLeft', '');
//                    jsPrintSetup.setOption('headerStrCenter', '');
//                    jsPrintSetup.setOption('headerStrRight', '');
//                    // set empty page footer
//                    jsPrintSetup.setOption('footerStrLeft', '');
//                    jsPrintSetup.setOption('footerStrCenter', '');
//                    jsPrintSetup.setOption('footerStrRight', '');
//                    // Suppress print dialog
//                    jsPrintSetup.setSilentPrint(true);
//                    // Do Print
//                    jsPrintSetup.print();
//                    // Restore print dialog
////                    setTimeout(function () {
//////                        console.log('print timeout');
////                        printwindow.close();
////                        window.location.replace("2.jsp");
////                    }, 3000);
//                } catch (err) {
//                    // alert(err);
////                    window.location.replace("2.jsp");
//                }

            }
            function setDivPrintIE() {
                document.getElementById('print').disabled = true;
            }

        </script>



    </head>
    <body class="body"  onload="checkBrowser()">
        <div>
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="font-weight: bold">

            <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900; width: auto">
                <tr><td style="text-align: center" colspan="2" style="font-size: 30px;font-weight: bold;" >لقد تم تنفيذ العمليه بنجاح </td></tr>
                <tr>
                    <th scope="col"><%=CONFIG.getpayer(session)%></th>
                    <td><%= topupPresentation.getAccountId()%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getStatus(session)%></th>
                    <td>عملية ناجحة</td>
                </tr>

                <tr>
                    <th scope="col">المبلغ المخصوم من محفظة التاجر</th>
                    <td><%= topupPresentation.getTransactionAmount()%></td>
                </tr>

                <tr>
                    <th scope="col"><%=CONFIG.getTaxValue(session)%></th>
                    <td><%=topupPresentation.getTax()%></td>
                </tr>

                <tr>
                    <th scope="col"><%=CONFIG.getUserAmount(session)%></th>
                    <td><%=request.getParameter("UserAmount")%></td>
                </tr>

                <tr>
                    <th scope="col"><%=CONFIG.getDate(session)%></th>
                    <td><%=date1%></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:center">
                        <input type="submit" id="print"  value="OK" onclick="gotoHome()" />
                    </td>
                </tr>
                <tr style="display: none">
                    <th colspan="2"><img src="./img/masarylogoo.jpg" alt="wasletKher" width="50" height="50"></th>
                </tr>
            </table>

        </form>
        <!--</form>-->
    </div><!-- End content body -->

    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>

</html>
