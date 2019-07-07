<%-- 
    Document   : PayLongLifeEgypt
    Created on : Mar 15, 2016, 12:25:35 PM
    Author     : Masary
--%>

<%@page import="com.masary.integration.dto.O2groupPayementResponse"%>
<%@page import="com.masary.integration.dto.HataxiPaymentResponse"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
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
    String date1 = dateFormat.format(date);
    String time = timeFormat.format(date);


    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    
    
    O2groupPayementResponse o2groupPayementResponse = (O2groupPayementResponse) request.getAttribute("o2groupPayementResponse");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getO2Group(session)%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />

        <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/o2Share.png"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">شركة مصاري</th></tr>');
                printwindow.document.write('<tr><th colspan="2"><%=CONFIG.getO2Group(session)%></th></tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=session.getAttribute("PIN").toString()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم البائع</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=o2groupPayementResponse.getGlobalTrxId()%></td><td style="text-align: right;padding-right:25px;width: 50%;" >رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ العمليه</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=time%></td><td style = "text-align: right;padding-right:25px;width:50%;">وقت العمليه</td> </tr>');
                printwindow.document.write('<tr><td colspan="2">---------------------------------------------------</td></tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("ClientName")%></td><td style = "text-align: right;padding-right:25px;width:50%;">إسم صاحب الحساب</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("MSISDN")%></td><td style="text-align: right;padding-right:25px;width: 50%;">رقم الحساب</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("VoucherValue")%></td><td style = "text-align: right;padding-right:25px;width:50%;">المبلغ</td> </tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=o2groupPayementResponse.getAppliedFees()%></td><td style = "text-align: right;padding-right:25px;width:50%;">تكلفة الخدمة</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=o2groupPayementResponse.getToBepaid()%></td><td style = "text-align: right;padding-right:25px;width:50%;">إجمالي المبلغ</td> </tr>');


                printwindow.document.write('<tr><td colspan="2">---------------------------------------------------</td></tr>');
                printwindow.document.write('<tr> <td colspan = "2" style = "text-align:center;font-size: 16px;" >شكرا لإستخدامك خدمة مصاري لخدمات الدفع الذكية</td> </tr >');

                printwindow.document.write('<tr> <td colspan = "2" style = "text-align:center;font-size: 16px;" >خدمة عملاء مصاري 16994</td> </tr >');
                printwindow.document.write('<tr> <td colspan = "2" style = "text-align:center;font-size:16px; ">www.e-masary.com </td> </tr> </table> ');

                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
                window.location.replace("2.jsp");
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
            
            <h2 align="center" ><%=CONFIG.getO2Group(session)%></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >       
                <tr>
                    <td>
                        <p><%= CONFIG.getO2GroupClientName(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="ClientName" value="<%=request.getParameter("ClientName")%>" readonly style="background-color: #EDEDED;" >
                    </td>
                </tr>
                <tr>
                    <td>
                        <p><%= CONFIG.getO2GroupPhoneNumber(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="DedducedAmount" value="<%=request.getParameter("MSISDN")%>" readonly style="background-color: #EDEDED;"  >
                    </td>
                </tr>

                <tr>
                    <td>
                        <p><%= CONFIG.getO2GroupChargeAmount(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="DedducedAmount" value="<%=request.getParameter("VoucherValue")%>" readonly style="background-color: #EDEDED;"  >
                    </td>
                </tr>

                <tr>
                    <td>
                        <p><%= CONFIG.getIsDeddued(session)%>:</p>
                    </td>
                    <td>
                        <input type="text" name="DedducedAmount" value="<%=o2groupPayementResponse.getTransactionAmount() %>" readonly style="background-color: #EDEDED;"  >
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="secondTD">
                        <input type="submit" value="<%=CONFIG.getPrinting(session)%>"  id="submitbutton" onclick="setDivPrint()"  />
                    </td>
                </tr>
                <tr style="display: none">
                    <th colspan="2"><img src="./img/o2Share.png" alt="wasletKher" width="50" height="50"></th>
                </tr>
            </table>
            <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
