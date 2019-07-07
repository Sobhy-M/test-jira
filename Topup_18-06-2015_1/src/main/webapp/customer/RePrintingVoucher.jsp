<%-- 
    Document   : PrintingVoucher
    Created on : Dec 7, 2015, 12:45:46 PM
    Author     : aya
--%>

<%@page import="java.util.Map"%>
<%@page import="com.masary.utils.HelperFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.SellVoucherResponse"%>
<%@page import="com.masary.database.dto.GenericSellVoucherResponse"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting(session)%></title>

        <%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }
            Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
        %>
        <%
            session = request.getSession();
            String transId = (String) session.getAttribute(CONFIG.PARAM_Transaction_ID);
            TransactionDTO trans;
            try {
                trans = MasaryManager.getInstance().getTransaction(transId);
            } catch (Exception ex) {
                session.setAttribute("ErrorMessage", "Detailed error code is:" + ex.getMessage());
                response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
                ex.printStackTrace();
                return;
            }

        %>
        <%//            if (request.getAttribute("second") != null && (String) request.getAttribute("second") == "second") {
            transId = (String) requestParameters.get(CONFIG.PARAM_Transaction_ID);
            try {
                trans = MasaryManager.getInstance().getTransaction(transId);
            } catch (Exception ex) {
                session.setAttribute("ErrorMessage", "Detailed error code is:" + ex.getMessage());
                response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
                ex.printStackTrace();
                return;
            }
        %>
        <%
            ArrayList<String> voucherPins = new ArrayList<String>();
            ArrayList<String> voucherSerials = new ArrayList<String>();
            ArrayList<String> isOffers = new ArrayList<String>();
            ArrayList<String> amounts = new ArrayList<String>();
//            if (request.getAttribute("second") != null && (String) request.getAttribute("second") == "second") {
            transId = (String) requestParameters.get(CONFIG.PARAM_Transaction_ID);
            try {
                trans = MasaryManager.getInstance().getTransaction(transId);
            } catch (Exception ex) {
                session.setAttribute("ErrorMessage", "Detailed error code is:" + ex.getMessage());
                response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
                ex.printStackTrace();
                return;
            }
            SellVoucherResponse voucherResponse = (SellVoucherResponse) session.getAttribute("voucherResponse");
            if (voucherResponse.getVoucherPin().size() != 0 && voucherResponse.getVoucherSerial().size() != 0) {

                for (int i = 0; i < voucherResponse.getVoucherPin().size(); i++) {
//                    if (i != voucherResponse.getVoucherPin().size() - 1) {
                    voucherPins.add(voucherResponse.getVoucherPin().get(i));
                    voucherSerials.add(voucherResponse.getVoucherSerial().get(i));
                    amounts.add(Double.toString(voucherResponse.getVoucherInfo().getDenom()));
//                    } else {
//                        voucherPins.addAll(voucherResponse.getVoucherPin());
//                        voucherSerials.addAll(voucherResponse.getVoucherSerial());
//                        }
//                    }
                }
            }
        %>


        <script>
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
                //                var isEdge = !isIE && !!window.StyleMedia;
                // Chrome 1+
                var isChrome = !!window.chrome && (!!window.chrome.webstore || !!window.chrome.runtime);
                if (isChrome || (isOpera === false && isFirefox === false && isSafari === false && isIE === false)) {
                    setDivPrintChrome();
                }
                // Blink engine detection
                //                var isBlink = (isChrome || isOpera) && !!window.CSS;


            }

            function setDivPrintChrome() {
            <%
                for (int n = 0; n < voucherPins.size(); n++) {
            %>
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="7"  style="font-size: 20px; font-weight: 700; width: 350px" >');
            <%if (trans.getType().toLowerCase().contains("marhaba")) {%>
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="150" height="60"></th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-size:18px;text-align:center">We</th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-size:18px;text-align:center">الخط الارضى</th></tr>');
            <%} else {%>
                printwindow.document.write('<tr><td colspan="3" style="font-family:courier;font-size:25px;text-align:center">شركة مصاري</td></tr>');
            <%}%>
                printwindow.document.write('<tr><th colspan="3"><%= trans.getType().toLowerCase().contains("orange") ? "كوبون اورنچ" : (trans.getType().toLowerCase().contains("voda") ? "كوبون فودافون" : trans.getType().toLowerCase().contains("etis") ? "كوبون اتصالات" : trans.getType().toLowerCase().contains("card") ? "كوبون وان كارد" : "كارت كاش يو")%></th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 60%;"> <%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%></td><td style="padding-right:25px;text-align: right;width: 40%; " >كود الشحن</td > </tr> ');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= voucherSerials.get(n)%> </td><td style="text-align: right;padding-right:25px;width: 50%;">رقم المسلسل </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getDate().substring(11)%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" > الوقت </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getDate().substring(0, 10)%> </td><td style="text-align: right;padding-right:25px;width: 50%;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getTransId()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" > رقم العملية </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= (amounts.get(n).equals("10.01") || amounts.get(n).equals("10.02") || amounts.get(n).equals("10.03")) ? "10" : amounts.get(n)%></td><td style = "text-align: right;padding-right:25px;width:50%;">القيمة</td> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"><p> الكوبون صالح للشحن مره واحده فقط والقيمه لا تسترد </p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>شكرا لاستخدامك خدمات مصارى 16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"  ><p> تأكد من طباعة الكارت امامك </p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
            <%}%>
            }

            function setDivPrintFireFox() {

            <%
                for (int n = 0; n < voucherPins.size(); n++) {
            %>
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="5"  style="font-size: 20px; font-weight: 700; width: 350px" >');
            <%if (trans.getType().toLowerCase().contains("marhaba")) {%>
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="150" height="60"></th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-size:18px;text-align:center">We</th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-size:18px;text-align:center">الخط الارضى</th></tr>');
            <%} else {%>
                printwindow.document.write('<tr><td colspan="3" style="font-family:courier;font-size:18px;text-align:center">شركة مصاري</td></tr>');
            <%}%>
                printwindow.document.write('<tr><th colspan="3"><%= trans.getType().toLowerCase().contains("orange") ? "كوبون اورنچ" : (trans.getType().toLowerCase().contains("voda") ? "كوبون فودافون" : trans.getType().toLowerCase().contains("etis") ? "كوبون اتصالات" : trans.getType().toLowerCase().contains("card") ? "كوبون وان كارد" : "كارت كاش يو")%></th> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"> <%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%></td><td style="font-family:courier;font-size:18px;text-align: right;width: 40%; " >كود الشحن</td > </tr> ');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:16px;text-align:left;"> عملية ناجحة</td><td style="font-family:courier;font-size:16px;text-align: right;">حاله العملية</td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;""><%= voucherSerials.get(n)%> </td><td style="font-family:courier;font-size:18px;text-align: right;">رقم المسلسل </td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;""><%=trans.getDate().substring(11)%> </td><td style ="font-family:courier;font-size:18px;text-align: right;" > الوقت </td></tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=trans.getDate().substring(0, 10)%> </td><td style="font-family:courier;font-size:18px;text-align: right;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=trans.getTransId()%> </td><td style="font-family:courier;font-size:18px;text-align: right;" > رقم العملية </td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%= (amounts.get(n).equals("10.01") || amounts.get(n).equals("10.02") || amounts.get(n).equals("10.03")) ? "10" : amounts.get(n)%></td><td style = "font-family:courier;font-size:18px;text-align: right;">القيمة</td> </tr>');
                printwindow.document.write('<tr><td style= "font-family:courier;font-size: 14px;text-align: center;width: 100%" colspan="3"> الكوبون صالح للشحن مره واحده فقط والقيمه لا تسترد </td> </tr>');
                printwindow.document.write('<tr> <td style ="font-size:14px;font-family:courier;text-align: center;width: 100%" colspan="3">شكرا لاستخدامك خدمات مصارى 16994 </td> </tr >');
                printwindow.document.write('<tr> <td style ="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3">تأكد من طباعة الكارت امامك </td></tr>');
                printwindow.document.write('<tr><td style="font-family:courier;font-size:18px;text-align: center;width: 100%; " colspan="3" >www.e-masary.com</td > </tr></table>  ');
                //                printwindow.document.write('<tr> <th style="font-family:courier;font-size:18px;text-align:left;" colspan="2">www.e-masary.com </th> </tr> </table> ');

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
                printwindow.close();
            <%}%>
            }
            function setDivPrintIE() {
                document.getElementById('btnSubmit').disabled = true;
                /*window.location.replace("2.jsp");*/
            }
        </script>

    </head>
    <body class="body"  onload="checkBrowser()">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
        </div>
        <div style="font-size: 20px;font-weight: bold; ">برجاء الضغط علي زر الطباعة في اسفل الصفحة </div>


    <% for (int i = 0; i < voucherPins.size(); i++) {%>
    <div>
        <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900;">
            <tr><th colspan="2" >شركة مصاري</th> </tr>
            <tr><th colspan="2"><%= trans.getType().toLowerCase().contains("orange") ? "كوبون اورنچ" : (trans.getType().toLowerCase().contains("voda") ? "كوبون فودافون" : trans.getType().toLowerCase().contains("etisalat") ? "كوبون اتصالات" : trans.getType().toLowerCase().contains("marhaba") ? "" : trans.getType().toLowerCase().contains("card") ? "كوبون وان كارد" : "كارت كاش يو")%></th> </tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">حاله العملية</td><td style="padding-left: 10px;">عملية ناجحة</td></tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">رقم المسلسل</td><td style="padding-left: 10px;"><%= voucherSerials.get(i)%></td></tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">التاريخ</td><td style="padding-left: 10px;"><%=trans.getDate().substring(11)%></td></tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">الوقت</td><td style="padding-left: 10px;"><%=trans.getDate().substring(0, 10)%></td></tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">رقم العملية</td><td style="padding-left: 10px;"><%=trans.getTransId()%></td></tr>
            <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">القيمة</td><td style="padding-left: 10px;"><%= (amounts.get(i).equals("10.01") || amounts.get(i).equals("10.02") || amounts.get(i).equals("10.03")) ? "10" : amounts.get(i)%></td></tr>
            <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">نسخة مكررة </th> </tr>
            <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">الكوبون صالح للشحن مره واحده فقط والقيمه لا تسترد </th> </tr>
            <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك خدمات مصارى 16994</th> </tr>
            <tr> <th colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">تأكد من طباعة الكارت امامك</th> </tr>
            <tr> <th colspan="2" style="text-align:center; padding-right: 30px">www.e-masary.com</th> </tr>
            <tr style="display: none">
                <th colspan="2"><img src="./img/Masary.jpg" alt="Masary Icon" width="50" height="50"></th>
            </tr>
        </table>
    </div><%}%>
    <form name="PrintingVouchers" action="<%=CONFIG.APP_ROOT + role + ".jsp"%>" method="POST">
        <input type="submit" name="btnSubmit"   id="print"  value="OK" />

    </form>
    <!-- End content body -->

    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>

</html>