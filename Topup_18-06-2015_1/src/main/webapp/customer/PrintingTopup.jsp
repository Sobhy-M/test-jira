<%-- 
    Document   : PrintingVoucher
    Created on : Dec 7, 2015, 12:45:46 PM
    Author     : aya
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.utils.HelperFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.SellVoucherResponse"%>
<%@page import="com.masary.database.dto.GenericSellVoucherResponse"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


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
            String transId = (String) session.getAttribute(CONFIG.PARAM_Transaction_ID);
            String taxAmount = (String) session.getAttribute(CONFIG.PARAM_TAX_AMOUNT);
            taxAmount = taxAmount == null ? "0" : taxAmount;
            TransactionDTO trans;
            try {
                trans = MasaryManager.getInstance().getTransaction(transId);
                

            } catch (Exception ex) {
                session.setAttribute("ErrorMessage", "Detailed error code is:" + ex.getMessage());
                response.sendRedirect(CONFIG.APP_ROOT + "error.jsp");
                ex.printStackTrace();
                return;
            }
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
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:30px;text-align:center">شركة مصاري</th></tr>');
                printwindow.document.write('<tr><th colspan="3"><%= trans.getType().toLowerCase().contains("orange") ? " اورنچ" : (trans.getType().toLowerCase().contains("voda") ? " فودافون" : trans.getType().toLowerCase().contains("etisa") ? " اتصالات" : "")%></th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getTransId()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" > رقم العملية </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getDate().substring(0, 10)%> </td><td style="text-align: right;padding-right:25px;width: 50%;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getDate().substring(11)%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" > الوقت </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=session.getAttribute(CONFIG.PARAM_PIN)%> </td><td style="text-align: right;padding-right:25px;width: 50%;" > رقم البائع </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> <%=trans.getAmount()%></td><td style="text-align: right;padding-right:25px;width: 60%; ">قيمة الشحن</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%=request.getParameter("UserAmount")%></td><td style="text-align: right;padding-right:25px;width: 60%; ">المبلغ المطلوب دفعه</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%=trans.getCustomerPayedName()%></td><td style="text-align: right;padding-right:25px;width: 60%; ">رقم الموبايل</td> </tr>');
                printwindow.document.write('<tr> <td style ="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3">المبلغ شامل الضريبة المضافة و ضريبة الجدول</td></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>شكرا لاستخدامك خدمات مصارى 16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>Powered by مصاري <p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();

                window.location.replace("2.jsp");

            }

            function setDivPrintFireFox() {

                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="5"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="3" style="font-family:courier;font-size:25px;text-align:center">شركة مصاري</td></tr>');
                printwindow.document.write('<tr><th colspan="3"><%= trans.getType().toLowerCase().contains("orange") ? " اورنچ" : (trans.getType().toLowerCase().contains("voda") ? " فودافون" : trans.getType().toLowerCase().contains("etis") ? " اتصالات" : "")%></th> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=trans.getTransId()%> </td><td style="font-family:courier;font-size:18px;text-align: right;" > رقم العملية </td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=trans.getDate().substring(0, 10)%> </td><td style="font-family:courier;font-size:18px;text-align: right;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;""><%=trans.getDate().substring(11)%> </td><td style ="font-family:courier;font-size:18px;text-align: right;" > الوقت </td></tr>');
                printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:16px;text-align:left;font-weight:bold;"> <%=session.getAttribute(CONFIG.PARAM_PIN)%></td><td style="font-family:courier;font-size:16px;text-align: right;">رقم البائع</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> <%=trans.getAmount()%></td><td style="text-align: right;padding-right:25px;width: 60%; ">قيمة الشحن</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%=request.getParameter("UserAmount")%></td><td style="text-align: right;padding-right:25px;width: 60%; ">المبلغ المطلوب دفعه</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%=trans.getCustomerPayedName()%></td><td style="text-align: right;padding-right:25px;width: 60%; ">رقم الموبايل</td> </tr>');
                printwindow.document.write('<tr> <td style ="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3">(شامل القيمة المضافه وضريبه المبيعات والجدول)</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:14px;font-family:courier;text-align: center;width: 100%" colspan="3">شكرا لاستخدامك خدمات مصارى 16994</td> </tr >');
                printwindow.document.write('<tr><td style="font-family:courier;font-size:18px;text-align: center;width: 100%; " colspan="3" >Powered by مصاري </td > </tr></table>  ');
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
                        window.location.replace("2.jsp");
                    }, 3000);
                } catch (err) {
                    alert(err);
                    window.location.replace("2.jsp");
                }

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
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <div>
                <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900;" >
                    <tr><td style="text-align: center" colspan="2">شركة مصاري</td></tr>
                    <tr><td style="text-align: center" colspan="2"><%= trans.getType().toLowerCase().contains("orange") ? " اورنچ" : (trans.getType().toLowerCase().contains("voda") ? " فودافون" : trans.getType().toLowerCase().contains("etisalat") ? " اتصالات" : "")%></td></tr>
                <tr>
                    <th scope="col"><%=CONFIG.getFrom(session)%></th>
                        <%  if (session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {%>
                    <td><%=trans.getCustomerPayerName()%></td>
                    <% } else {%>
                    <td> <%=MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).getEmployeeName(session)%></td>
                    <% }%>
                </tr>
                <%if ((session.getAttribute("howSell") == null) || (session.getAttribute("howSell").equals("sms"))) {%>
                <tr>
                    <th scope="col"><%=CONFIG.getTo(session)%></th>
                    <td><%=trans.getCustomerPayedName()%></td>
                </tr>
                <% }%>
                <tr>
                    <th scope="col"><%=CONFIG.getType(session)%></th>
                    <td><%=trans.getType()%></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getStatus(session)%></th>
                    <td><%=trans.getStatus()%></td>
                </tr>

                <tr>
                    <th scope="col"><%=CONFIG.getRechargeAmount(session)%></th>
                    <td><%=myFormatter.format(trans.getAmount())%></td>
                </tr>

                <tr>
                    <th scope="col"><%=CONFIG.getTaxValue(session)%></th>
                    <td><%=request.getParameter("servicetax")%></td>
                </tr>

                <tr>
                    <th scope="col"><%=CONFIG.getUserAmount(session)%></th>
                    <td><%=request.getParameter("UserAmount")%></td>
                </tr>

                <tr>
                    <th scope="col"><%=CONFIG.getDate(session)%></th>
                    <td><%=trans.getDate()%></td>
                </tr>
                <tr>
            </table>
        </div>
        <input type="submit" id="print"  value="OK" onclick="gotoHome()" />

        <!--</form>-->
    </div><!-- End content body -->

    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>

</html>
