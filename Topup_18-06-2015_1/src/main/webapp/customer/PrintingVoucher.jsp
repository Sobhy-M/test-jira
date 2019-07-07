<%-- 
    Document   : PrintingVoucher
    Created on : Dec 7, 2015, 12:45:46 PM
    Author     : aya
--%>
<%@page import="com.masary.utils.HelperFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.SellVoucherResponse"%>
<%@page import="com.masary.database.dto.GenericSellVoucherResponse"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="java.util.Map"%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting(session)%></title>
        <style>
            .line{
                display: inline;
            }
        </style>
        <%

            Map<String, String> requestParameters
                    = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);

            String printingMessage;
            if (Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID)) == 17) {
                printingMessage = CONFIG.getprintingMessage2();
            } else {
                printingMessage = CONFIG.getprintingMessage1();
            }
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }
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
            ArrayList<String> voucherPins = new ArrayList<String>();
            ArrayList<String> voucherSerials = new ArrayList<String>();
            ArrayList<String> isOffers = new ArrayList<String>();
            ArrayList<String> amounts = new ArrayList<String>();

            if (request.getAttribute("second") != null && (String) request.getAttribute("second") == "second") {
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
                        if (i != voucherResponse.getVoucherPin().size() - 1) {
                            voucherPins = voucherResponse.getVoucherPin();
                            voucherSerials = voucherResponse.getVoucherSerial();
                        } else {
                            voucherPins = voucherResponse.getVoucherPin();
                            voucherSerials = voucherResponse.getVoucherSerial();
                        }
                    }
                }
            } else {
                GenericSellVoucherResponse voucherResponse = (GenericSellVoucherResponse) request.getAttribute("voucherResponse");
                if (session.getAttribute("howSell").equals("Printing")) {
                    for (int i = 0; i < voucherResponse.getVouchersData().size(); i++) {
                        if (i != voucherResponse.getVouchersData().size() - 1) {
                            voucherPins.add(voucherResponse.getVouchersData().get(i).getVoucherPin());
                            voucherSerials.add(voucherResponse.getVouchersData().get(i).getVoucherSn());
                            isOffers.add(voucherResponse.getVouchersData().get(i).getIsOffer().toString());
                            amounts.add(voucherResponse.getVouchersData().get(i).getAmount().toString());
                        } else {
                            voucherPins.add(voucherResponse.getVouchersData().get(i).getVoucherPin());
                            voucherSerials.add(voucherResponse.getVouchersData().get(i).getVoucherSn());
                            isOffers.add(voucherResponse.getVouchersData().get(i).getIsOffer().toString());
                            amounts.add(voucherResponse.getVouchersData().get(i).getAmount().toString());
                        }
                    }
                }
            }
        %>


        <script>
            function gotoHome()
            {
                window.location.replace("2.jsp");
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
//                var isChrome = !!window.chrome && !!window.chrome.webstore;
                var isChrome = !!window.chrome && (!!window.chrome.webstore || !!window.chrome.runtime);
                if (isChrome || (isOpera === false && isFirefox === false && isSafari === false && isIE === false)) {
                    setDivPrintChrome();
                }
            }

            function setDivPrintChrome() {
            <%
                for (int n = 0; n < voucherPins.size(); n++) {
            %>
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="7"  style="font-size: 20px; font-weight: 700; width: 350px" >');

            <% if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 31) {%>
                printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="150" height="60"></th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">We</th></tr>');
                printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">الخط الارضى</th></tr>');
            <% } else {%>
                printwindow.document.write('<tr><th colspan="3" style="font-size:18px;text-align:center">شركة مصاري</th></tr>');
            <%}%>

                printwindow.document.write('<tr><th colspan="3"><%= trans.getType().toLowerCase().contains("orange") ? "كوبون اورنچ" : (trans.getType().toLowerCase().contains("voda") ? "كوبون فودافون" : trans.getType().toLowerCase().contains("etisa") ? "كوبون اتصالات" : trans.getType().toLowerCase().contains("marh") ? "" : trans.getType().toLowerCase().contains("card") ? "كوبون وان كارد" : "كارت كاش يو")%></th> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getTransId()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" > رقم العملية </td> </tr>');
                //printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getCustomerPayerName()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" > البائع </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getDate().substring(0, 10)%> </td><td style="text-align: right;padding-right:25px;width: 50%;" > التاريخ </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trans.getDate().substring(11)%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" > الوقت </td></tr>');
            <%if (amounts.get(n).equals("5.01")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=5%></td><td style = "text-align: right;padding-right:25px;width:50%;">قيمة الكوبون</td> </tr>');
            <% } else if ((requestParameters.get("topupdemo").equals("5.01") || requestParameters.get("topupdemo").equals("10.01") || requestParameters.get("topupdemo").equals("25.01") || requestParameters.get("topupdemo").equals("15.01")) && trans.getType().toLowerCase().contains("etisa")) {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=amounts.get(n)%></td><td style = "text-align: right;padding-right:25px;width:50%;">قيمة الكوبون</td> </tr>');
            <%} else {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= (amounts.get(n).equals("10.01") || amounts.get(n).equals("10.02") || amounts.get(n).equals("10.03")) || amounts.get(n).equals("10.05") ? "10" : amounts.get(n).equals("5.05") ? "5" : amounts.get(n)%></td><td style = "text-align: right;padding-right:25px;width:50%;">قيمة الكارت</td> </tr>');<%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%= voucherSerials.get(n)%> </td><td style="text-align: right;padding-right:25px;width: 50%;"> الرقم المسلسل</td> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >----------------------------------------------</th> </tr >');
            <%if (amounts.get(n).equals("5.01")) {%>
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">صوت لكارت ال5ج الجديد</td></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >----------------------------------------------</th> </tr >');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 60%;"> <%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%></td><td style="padding-right:25px;text-align: right;width: 40%; " >رقم الكوبون</td > </tr> ');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >----------------------------------------------</th> </tr >');
            <%} else {%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 60%;"> <%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%></td><td style="padding-right:25px;text-align: right;width: 40%; " >كود الشحن</td > </tr> ');<%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%=requestParameters.get("Fees")%></td><td style="text-align: right;padding-right:25px;width: 60%; ">تكلفه الخدمة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%= (amounts.get(n).equals("10.01") || amounts.get(n).equals("10.02") || amounts.get(n).equals("10.03")) ? "10" : amounts.get(n)%></td><td style="text-align: right;padding-right:25px;width: 60%; ">اجمالي المبلغ </td> </tr>');
            <%if (amounts.get(n).equals("10.05")) {%>
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"><p style="display: inline-block;vertical-align: top;">دقيقة لكل الشبكات صالحة لمدة اسبوع اطلب</p> <p style="display: inline-block;vertical-align: top;">450</p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*1*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"><p style="display: inline-block;vertical-align: top;">فليكس لكل الشبكات صالحة لمدة اسبوع اطلب</p> <p style="display: inline-block;vertical-align: top;">450</p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*2*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">رصيد عادى اطلب</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
            <% } else if (amounts.get(n).equals("10.01")) {%>
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"> اطلب </th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"> *858*كودالشحن# </th> </tr>');
            <% } else if (amounts.get(n).equals("5.05")) {%>
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"><p style="display: inline-block;vertical-align: top;">دقيقة لكل الشبكات صالحة لمدة 3 ايام اطلب </p> <p style="display: inline-block;vertical-align: top;">160</p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*1*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"><p style="display: inline-block;vertical-align: top;">فليكس صالحة لمدة 3 ايام اطلب</p> <p style="display: inline-block;vertical-align: top;">160</p></th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*1*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">رصيد عادى اطلب</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*2*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
            <% } else if (amounts.get(n).equals("3.5") && Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID)) == 15) {%>

                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">لشحن 150 دقيقة لارقام فودافون اطلب</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">لإستخدام جزء من دقائق الكارت في ميجابايتس اطلب </th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*86#</th> </tr>');
            <% } else if (amounts.get(n).equals("1.5") && Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID)) == 15) {%>

                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">لشحن 25 دقيقة لارقام فودافون اطلب</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">لاستخدام جزء من دقائق الكارت فى ميجابايتس اطلب</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*86#</th> </tr>');
            <% } else if (amounts.get(n).equals("2.0") && Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID)) == 15) {%>

                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">لشحن 30 دقيقة أو وحدة وكمان 20 ميجابايت واتس اب طول اليوم اطلب </th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">للتحكم في الكارت اطلب  </th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*86#</th> </tr>');
            <% } else if (amounts.get(n).equals("4.0") && Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID)) == 15) {%>

                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">لشحن 175 دقيقة أو وحدة وكمان 50 ميجابايت واتس اب طول اليوم اطلب </th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*858*<%= new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%>#</th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">للتحكم في الكارت اطلب  </th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;">*86#</th> </tr>');
            <% }%>
                //tesal 
            <%if (requestParameters.get("topupdemo").equals("5.01")) {%>
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">معلومات اضافيه</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*شحن 5 جنيه رصيد # الكود *556</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">لشحن 160 ميكس صالحة 3 ايام</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*556*1*الكود#</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">لشحن 160 دقيقة صالحة 3 ايام </td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*556*2*الكود#</td></tr>');
            <%}%>


            <%if (requestParameters.get("topupdemo").equals("15.01")) {%>
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">معلومات اضافيه</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*شحن 15 جنيه رصيد # الكود *556</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">لشحن 750 ميكس صالحة 7 ايام  </td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*556*1*الكود#</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">لشحن 750 دقيقة صالحة 7 ايام </td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*556*2*الكود#</td></tr>');
            <%}%>


            <%if (requestParameters.get("topupdemo").equals("25.01")) {%>
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">معلومات اضافيه</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*شحن 25 جنيه رصيد # الكود *556</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">لشحن 1100 ميكس صالحة 14 ايام  </td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*556*1*الكود#</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">لشحن 1100 دقيقة صالحة 14 ايام </td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*556*2*الكود#</td></tr>');
            <%}%>


            <%if (amounts.get(n).equals("5.01")) {%>
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">معلومات اضافيه</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">للشحن اطلب</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">لشحن كارت الكرم اطلب </td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">*8*1*xxxxxxxxx#</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">لشحن كارت  الصحبه اطلب </td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">*8*2*xxxxxxxxx#</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">لشحن كارت الحظ اطلب </td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">*8*3*xxxxxxxxx#</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">  رصيد الكروت يستخدم في دقايق, رسايل لكل الشبكات و كمان انترنت</td></tr>');
                printwindow.document.write('<tr> <td style ="font-size:15px;font-family:courier; text-align: center;width: 100%" colspan="3">    بسعر الوحدة 20 قرش </td></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >----------------------------------------------</th> </tr >');<%}%>
                printwindow.document.write('<%if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 15) {%> <tr> <th colspan ="2" style ="text-align:center;font-size:16px;" ><%=MasaryManager.getInstance().getDenomerationMessage("Vodafone", amounts.get(n))%> </th></tr>');
                printwindow.document.write('<%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 17) {%> <tr> <th colspan ="2"  style ="text-align:center;font-size:16px;direction:rtl;" ><%=MasaryManager.getInstance().getDenomerationMessage("Etisalat", amounts.get(n))%> </th> </tr>');
                printwindow.document.write('<%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 31) {%><tr><td style="font-size:16px;font-family:courier;text-align: center;width: 100%" colspan="3"><%=MasaryManager.getInstance().getDenomerationMessage("Marhaba", amounts.get(n))%> </td> </tr>');
                printwindow.document.write('<%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 16) {%> <tr><th colspan = "2" style ="text-align:center;font-size:16px;" ><%=MasaryManager.getInstance().getDenomerationMessage("Mobinil", amounts.get(n))%> </th> </tr>');
                printwindow.document.write('<%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 34) {%><tr><th colspan = "2" style ="text-align:center;font-size:16px;" ><%=MasaryManager.getInstance().getDenomerationMessage("cashu", amounts.get(n))%> </th> </tr>');
                printwindow.document.write('<%} else {%><tr><th colspan = "2" style ="text-align:center;font-size:16px;" ><%=MasaryManager.getInstance().getDenomerationMessage("one card", amounts.get(n))%> </th> </tr><%}%>');
            <%if (amounts.contains("0.5") || (amounts.contains("1.0") && Integer.parseInt((String) session.getAttribute(CONFIG.PARAM_SERVICE_ID)) == 17) || amounts.contains("3.0")) {%>
                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >--------------------------------------------</th> </tr >');
                        printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">*لشحن الكارت اطلب #556</td></tr>');
                        printwindow.document.write('<tr> <td style ="font-size:17px;font-family:courier; text-align: center;width: 100%" colspan="3">اشحن اقوى كارت 5 جنيه و استمتع 160 دقيقة صالحة 3 ايام</td></tr>');
            <%}%>

            <% if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 31) {%>
                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >------------------------------------------</th> </tr >');

                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> te.eg للاشتراك حدث بياناتك من خلال   <p></th> <p></th> </tr >');
            <%}%>;


                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >------------------------------------------</th> </tr >');
                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"  > تأكد من طباعة الكارت امامك </th> </tr>');
                        printwindow.document.write(' <tr> <th colspan = "2" style = "text-align:center;font-size: 16px;"> الكوبون صالح للشحن مره واحده فقط والقيمه لا تسترد </th> </tr>');
                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 15px;" >السعر يشمل ضريبه المبيعات </th> </tr >');
                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 15px;" >شكرا لاستخدامك خدمات مصارى لخدمات الدفع الذكية </th> </tr >');
                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 15px;" >خدمة عملاء مصاري  : 16994</th> </tr >');
                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; ">www.e-masary.com</th> </tr> </table> ');
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
            <% if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 31) {%>
                        printwindow.document.write('<tr><th colspan="2"><img src="./img/Masary.jpg" alt="’Masary Icon" width="150" height="60"></th></tr>');
                        printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">We</th></tr>');
                        printwindow.document.write('<tr><th colspan="3" style="font-family:courier;font-size:18px;text-align:center">الخط الارضى</th></tr>');
            <% } else {%>
                        printwindow.document.write('<tr><th colspan="3" style="font-size:16px;text-align:center">شركة مصاري</th></tr>');
            <%}%>

                        printwindow.document.write('<tr><th colspan="3"><%= trans.getType().toLowerCase().contains("orange") ? "كوبون اورنچ" : (trans.getType().toLowerCase().contains("voda") ? "كوبون فودافون" : trans.getType().toLowerCase().contains("etis") ? "كوبون اتصالات" : trans.getType().toLowerCase().contains("marhaba") ? "" : trans.getType().toLowerCase().contains("card") ? "كوبون وان كارد" : "كارت كاش يو")%></th> </tr>');
                        printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"> <%=  new HelperFunctions().stringFormat(voucherPins.get(n), 4, ' ')%></td><td style="font-family:courier;font-size:18px;text-align: right;width: 40%; " >كود الشحن</td > </tr> ');
                        printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:16px;text-align:left;font-weight:bold;"> عملية ناجحة</td><td style="font-family:courier;font-size:16px;text-align: right;">حاله العملية</td> </tr>');
                        printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;""><%= voucherSerials.get(n)%> </td><td style="font-family:courier;font-size:18px;text-align: right;">رقم المسلسل </td> </tr>');
                        printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;""><%=trans.getDate().substring(11)%> </td><td style ="font-family:courier;font-size:18px;text-align: right;" > الوقت </td></tr>');
                        printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=trans.getDate().substring(0, 10)%> </td><td style="font-family:courier;font-size:18px;text-align: right;" > التاريخ </td> </tr>');
                        printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%=trans.getTransId()%> </td><td style="font-family:courier;font-size:18px;text-align: right;" > رقم العملية </td> </tr>');
                        printwindow.document.write('<tr><td colspan="2" style="font-family:courier;font-size:18px;text-align:left;"><%= (amounts.get(n).equals("10.01") || amounts.get(n).equals("10.02") || amounts.get(n).equals("10.03")) ? "10" : amounts.get(n)%></td><td style = "font-family:courier;font-size:18px;text-align: right;">قيمة الكارت</td> </tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%= requestParameters.get("Fees")%></td><td style="text-align: right;padding-right:25px;width: 60%; ">تكلفه الخدمة</td> </tr>');
                        printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"><%= (amounts.get(n).equals("10.01") || amounts.get(n).equals("10.02") || amounts.get(n).equals("10.03")) ? "10" : amounts.get(n)%></td><td style="text-align: right;padding-right:25px;width: 60%; ">المبلغ المطلوب</td> </tr>');
                        printwindow.document.write('<tr> <td style ="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3">(شامل القيمة المضافه وضريبه المبيعات والجدول)</td></tr>');
                        printwindow.document.write('<%if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 15) {%> <tr> <td  style="font-size:14px;font-family:courier; " colspan="3"><%=MasaryManager.getInstance().getDenomerationMessage("Vodafone", amounts.get(n))%> </td></tr>');
                        printwindow.document.write('<%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 17) {%> <tr> <td style="font-size: 16px;font-family:courier;" colspan="3"><%=MasaryManager.getInstance().getDenomerationMessage("Etisalat", amounts.get(n))%>  </td></tr>');
                        printwindow.document.write('<%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 16) {%> <tr><td style="font-size:16px;font-family:courier;text-align: center;width: 100%" colspan="3"><%=MasaryManager.getInstance().getDenomerationMessage("Mobinil", amounts.get(n))%> </td> </tr>');
                        printwindow.document.write('<%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 31) {%><tr><td style="font-size:16px;font-family:courier;text-align: center;width: 100%" colspan="3"><%=MasaryManager.getInstance().getDenomerationMessage("Marhaba", amounts.get(n))%> </td> </tr>');
                        printwindow.document.write('<%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 34) {%><tr><td style="font-size:16px;font-family:courier;text-align: center;width: 100%" colspan="3"><%=MasaryManager.getInstance().getDenomerationMessage("cashu", amounts.get(n))%> </td> </tr>');
                        printwindow.document.write('<%} else {%><tr><td style="font-size:16px;font-family:courier;text-align: center;width: 100%" colspan="3"><%=MasaryManager.getInstance().getDenomerationMessage("one card", amounts.get(n))%> </td> </tr>');

                        printwindow.document.write('<tr> <td style ="font-size:14px;font-family:courier; text-align: center;width: 100%" colspan="3">تأكد من طباعة الكارت امامك </td></tr>');
                        printwindow.document.write('<%}%> <tr><td style= "font-family:courier;font-size: 14px;text-align: center;width: 100%" colspan="3"> الكوبون صالح للشحن مره واحده فقط والقيمه لا تسترد </td> </tr>');
            <% if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 31) {%>
                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >------------------------------------------</th> </tr >');


                        printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> te.eg للاشتراك حدث بياناتك من خلال   <p></th> <p></th> </tr >');
            <%}%>;


                        printwindow.document.write('<tr> <td style ="font-size:14px;font-family:courier;text-align: center;width: 100%" colspan="3">شكرا لاستخدامك خدمات مصارى 16994</td> </tr >');
                        printwindow.document.write('<tr><td style="font-family:courier;font-size:18px;text-align: center;width: 100%; " colspan="3" >www.e-masary.com</td > </tr></table>  ');
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
                                printwindow.close();
                            }, 3000);
                        } catch (err) {
                        }
            <%}%>
                    }
                    function setDivPrintIE() {
                        document.getElementById('print').disabled = true;
                    }

        </script>



    </head>
    <body class="body"  onload="checkBrowser()">
        <div>
            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
        <from action="<%=CONFIG.APP_ROOT + role + ".jsp"%>" method="POST" >
        <% for (int i = 0; i < voucherPins.size(); i++) {
        %>
        <div>
            <table cellspacing="10" cellpadding="4" style="font-size: 20px; font-weight: 900;" >
                <tr><td style="text-align: center" colspan="2">شركة مصاري</td></tr>
                <tr><td style="text-align: center" colspan="2"><%= trans.getType().toLowerCase().contains("orange") ? "كوبون اورنچ" : (trans.getType().toLowerCase().contains("voda") ? "كوبون فودافون" : trans.getType().toLowerCase().contains("etisalat") ? "كوبون اتصالات" : trans.getType().toLowerCase().contains("marhaba") ? "" : trans.getType().toLowerCase().contains("card") ? "كوبون وان كارد" : "كارت كاش يو")%></td></tr>
                <tr><td  style="  width: 50% ; padding-right: 30px;text-align: right;">حاله العملية</td><td style="padding-left: 10px;">عملية ناجحة</td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">رقم المسلسل</td><td style="padding-left: 10px;"><%= voucherSerials.get(i)%></td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">التاريخ</td><td style="padding-left: 10px;"><%=trans.getDate().substring(11)%></td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">الوقت  </td><td style="padding-left: 10px;"><%=trans.getDate().substring(0, 10)%></td></tr>
                <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;">رقم العملية </td><td style="padding-left: 10px;"><%=trans.getTransId()%></td></tr>
                    <%if (amounts.get(i).equals("5.01") || amounts.get(i).equals("5.05")) {%>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">قيمة الكارت </td><td style="padding-left: 10px;"><%= 5%></td></tr>
                    <% } else if (amounts.get(i).equals("10.05")) {%>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">قيمة الكارت </td><td style="padding-left: 10px;"><%= 10%></td></tr>
                    <%} else {%>
                <tr><td style="width: 50% ; padding-right: 30px;text-align: right;">قيمة الكارت </td><td style="padding-left: 10px;"><%= (amounts.get(i).equals("10.01") || amounts.get(i).equals("10.02") || amounts.get(i).equals("10.03")) ? "10" : amounts.get(i)%></td></tr><%}%>
                    <%if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 15) {%>
                <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px;"><%=MasaryManager.getInstance().getDenomerationMessage("Vodafone", amounts.get(i))%> </td> </tr>
                <%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 17) {%>
                <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px"><%=MasaryManager.getInstance().getDenomerationMessage("Etisalat", amounts.get(i))%> </td>
                </tr><%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 31) {%>
                <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">
                        <%=MasaryManager.getInstance().getDenomerationMessage("Marhaba", amounts.get(i))%> </td> </tr> 
                        <%} else if (Integer.parseInt(requestParameters.get(CONFIG.TOPUP_TYPE).toString()) == 16) {%>
                <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">
                        <%=MasaryManager.getInstance().getDenomerationMessage("Mobinil", amounts.get(i))%> </td> </tr><%} else {%> 
                <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">
                        <%=MasaryManager.getInstance().getDenomerationMessage("cashU", amounts.get(i))%> </td> </tr><%}%>
                <tr> <td colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">الكوبون صالح للشحن مره واحده فقط والقيمه لا تسترد </td> </tr>
                <tr> <td  colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">شكرا لاستخدامك خدمات مصارى 16994</td> </tr>
                <tr> <td  colspan="2" style="text-align:right;font-size: 16px; padding-right: 30px">تأكد من طباعة الكارت امامك</td> </tr>
                <tr> <td colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</td> </tr>
                <tr style="display: none">
                    <th colspan="2"><img src="./img/Masary.jpg" alt="TE Data" width="50" height="50"></th>
                </tr>

            </table>
        </div><%

            }%>
        <form name="PrintingVouchers" action="<%=CONFIG.APP_ROOT + role + ".jsp"%>" method="POST">
            <input type="submit" name="btnSubmit"   id="print"  value="OK" />

        </form>

        <% session.removeAttribute(CONFIG.REQUEST_PARAMETERS);%>

        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
    </div><!-- End of Main body-->
</body>

</html>
