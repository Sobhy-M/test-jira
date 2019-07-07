<%-- 
    Document   : PayLongLifeEgypt
    Created on : Mar 15, 2016, 12:25:35 PM
    Author     : Masary
--%>

<%@page import="java.util.logging.Level"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.database.dto.DonationAgentPaymentRespponseDto"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Masary_Bill_Type"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    session = request.getSession();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    //get current date time with Date()
    Date date = new Date();
    String trxDate = dateFormat.format(date);
    String trxTime = timeFormat.format(date);
    String paramTitle = "تحصيلات شركات دلتا  ";
    String paramCompayName = "اسم الشركه  : ";
    String paramRepCode = "كود المندوب : ";
    String paramAmount = "المبلغ بالجنيه:";
    String paramPIN = "كود نقطه التحصيل : ";
    String paramTRXNum = "رقم العملية: ";
    String paramDate = "تاريخ العمليه : ";
    String paramTime = "توقيت العمليه : ";

    String compayName = session.getAttribute("companyName").toString();
    String repCode = session.getAttribute("repCode").toString();
    String amount = request.getParameter("amount");
    String pin = session.getAttribute(CONFIG.PARAM_PIN).toString();
    String trx = session.getAttribute(CONFIG.PARAM_Transaction_ID).toString();
//    String trxDate = session.getAttribute("trxDate").toString();
//    String trxTime = String.valueOf(new Date().getHours()) + " : " + String.valueOf(new Date().getMinutes());
%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getCompaniesProceeds(session)%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
    </head>
    <script>
        function setDivPrint() {
            var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
            printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
        <%if (request.getSession().getAttribute("SERVICE_ID").toString().equals("210")) {%>
            printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/misr.png"  width="180" height="60"></td></tr>');
            printwindow.document.write('<tr><th colspan="2">ايداعات المندوبين </th></tr>');

        <% }else if (request.getSession().getAttribute("SERVICE_ID").toString().equals("18235")) {%>
            printwindow.document.write('<tr><th colspan="2"><img src="./img/multi.bmp" alt="’Masary Icon" width="auto" height="auto"></th></tr>');
        <%} 
                else {%>
            printwindow.document.write('<tr><th colspan="2">شركة مصاري</th></tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 40%;"> عملية ناجحة</td><td style="text-align: right;padding-right:25px;width: 60%; ">حاله العملية</td> </tr>');
        <%}%>
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trx%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=pin%></td><td style="text-align: right;padding-right:25px;width: 50%;" >كود نقطه التحصيل </td> </tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trxDate%></td><td style = "text-align: right;padding-right:25px;width:50%;" >تاريخ العمليه</td> </tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trxTime%></td><td style = "text-align: right;padding-right:25px;width:50%;">توقيت العمليه </td> </tr><tr>');
            printwindow.document.write('<tr><th colspan="2">----------------------------- </th></tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=compayName%></td><td style="text-align: right;padding-right:25px;width: 50%;">اسم الشركة </td> </tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=repCode%></td><td style ="text-align: right;padding-right:25px;width: 50%;" >كود المندوب</td></tr>');
            printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=amount%></td><td style ="text-align: right;padding-right:25px;width: 50%;" >المبلغ المودع  </td></tr>');
               printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("valueFees")%></td><td style ="text-align: right;padding-right:16px;width: 50%;" >لمبلغ المدفوع شامل رسوم الخدمة و ضريبة القيمة المضافة </td></tr>');
            printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p>شكرا لاستخدامك مصارى لخدمات الدفع الذكي 16994 <p></th> </tr >');
            printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
            printwindow.location.reload();
            printwindow.document.close();
            printwindow.focus();
            printwindow.print();
            printwindow.close();
            window.location.replace("2.jsp");
        }
    </script>
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
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <%if (request.getSession().getAttribute("SERVICE_ID").toString().equals("210")) {%>
                <tr><td colspan="2" style="text-align: center"><img src="img/misr.png"  width="180" height="60"></td></tr><%}%>
                <tr>
                    <td>
                        <p><%= CONFIG.getAgentPaymentCompanyName(session)%>:</p>
                    </td>
                    <td>
                        <input  type="text" readonly style="background-color: #EDEDED;" value="<%=compayName%>" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getRepresentativeCode(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="ServiceCost" value="<%=repCode%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getAgentPaymentAmountLE(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="ServiceCost" value="<%=amount%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td><%= CONFIG.getAgentPaymentPinCode(session)%>:</td>
                    <td>
                        <input type="text" name="ServiceCost" value="<%=pin%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td><%= CONFIG.getAgentPaymentTrx(session)%>:</td>
                    <td>
                        <input type="text" name="ServiceCost" value="<%=trx%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td><%= CONFIG.getAgentPaymentTrxDate(session)%>:</td>
                    <td>
                        <input type="text" name="ServiceCost" value="<%=trxDate%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>

                <tr>
                    <td><%= CONFIG.getAgentPaymentTrxTime(session)%>:</td>
                    <td>
                        <input type="text" name="ServiceCost" value="<%=trxTime%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr> <th colspan="2" style="text-align:center;font-size:16px; padding-right: 30px">www.e-masary.com</th> </tr>
                <tr>
                    <td colspan="2" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getPrinting(session)%>"  id="submitbutton" onclick="setDivPrint()"  />
                    </td>
                </tr>
                <tr style="display: none">
                        <th colspan="2"><img src="./img/multi.bmp" alt=" Masary Icon" width="50" height="50"></th>
                    </tr>
            </table>
        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>