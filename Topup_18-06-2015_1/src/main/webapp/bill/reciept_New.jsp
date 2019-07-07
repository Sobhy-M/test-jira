<%-- 
    Document   : bill_payment_confirmation
    Created on : 01/05/2012, 07:12:20 م
    Author     : Michael
--%>

<%@page import="com.masary.integration.dto.TransactionsMenuResponse"%>
<%@page import="com.masary.database.dto.Bill_Provider_Response"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.dto.Bill_Response"%>
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
    MasaryManager manager = new MasaryManager();
%>
<%
    session = request.getSession();
    String custId = null;
    if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }

    DecimalFormat myFormatter = CONFIG.getFormater(session);
    AgentDTO agent = manager.getAgent(custId);
    TransactionsMenuResponse reciept = (TransactionsMenuResponse) request.getSession().getAttribute("receipt");
    String time = null;
    String date = null;
    try {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate = new Date(reciept.getTrxTime());
        date = dt.format(dueDate);
        SimpleDateFormat dt2 = new SimpleDateFormat("HH:mm");
        time = dt2.format(dueDate);
    } catch (Exception e) {
        MasaryManager.logger.error(e.getMessage(), e);
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
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
            <%
                if (reciept.getServiceId() == 777) {
            %>
                printwindow.document.write('<tr><td colspan="2" style="text-align: left; float: left;"><img src="img/SmsMisr.jpg"  width="170px" height="70px"></td><td><img src="img/newlogo.png"  width="185" height="50"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">إس إم إس مصر</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=reciept.getGlobalTrxId()%></td><td style="text-align: right;padding-right:25px;width: 50%;">: رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date%>  <%=time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ وتوقيت العملية </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=agent.getPin()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >:رقم التاجر </td> </tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=reciept.getPayed()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >:رقم العميل </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=reciept.getLedgerTrxId()%></td><td style = "text-align: right;padding-right:25px;width:50%;">:الرقم المرجعى </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=reciept.getAmount()%></td><td style = "text-align: right;padding-right:25px;width:50%;">:اجمالى قيمة العملية شاملة تكلفة الخدمة وضريبة القيمة المضافة </td></tr><tr>');
            <%
            } else if (reciept.getServiceId() == 777) {
            %>
                     printwindow.document.write('<tr><td colspan="2" style="text-align: left; float: left;"><img src="img/fmf.png"  width="170px" height="70px"></td><td><img src="img/newlogo.png"  width="185" height="50"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">المؤسسة الأولى للتمويل متناهى الصغر</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=reciept.getGlobalTrxId()%></td><td style="text-align: right;padding-right:25px;width: 50%;">: رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date%>  <%=time%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ وتوقيت العملية </td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=reciept.getPayer()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >:رقم التاجر </td> </tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=reciept.getAmount()%></td><td style = "text-align: right;padding-right:25px;width:50%;">:اجمالى القسط</td></tr><tr>');

            <% }%>
                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
                window.location.replace("2.jsp");
            }
        </script>
    </head>
    <body class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession()
                    .getAttribute(CONFIG.lang).equals("")) {%>
        <div id="menu">
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>
            <div align="menu">
                <form name="BillTransactions" action="<%=CONFIG.APP_ROOT + role + ".jsp"%>" method="POST" >
                <h3 style="color: red; font-size: 18px">عملية ناجحة</h3>
                <div>
                    <table cellspacing="10" style="font-size: 12px; font-weight: 900; width: 45%" > 

                        <%

                            if (reciept.getServiceId()
                                    == 777) {
                        %>
                        <tr><td colspan="2" style="text-align: center; font-weight: bold; font-size: x-large"><%=CONFIG.SMSMISR_SERVICE_NAME%></td> </tr>

                        <tr><td class="td"><p style="font-size: large">رقم العملية :</p> </td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=reciept.getGlobalTrxId()%>" style="background-color: #EDEDED; float: right;"/></td></tr>
                        <tr><td class="td"><p style="font-size: large"> كود العميل:</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=reciept.getPayed()%>" style="background-color: #EDEDED; float: right;"/></td></tr>
                        <tr><td class="td"><p style="font-size: large"> رصيدك الحالى:</p></td><td><input type="text" readonly tabindex="1" value="<%=MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance()%> جنيه" style="background-color: #EDEDED; float: right;"/></td></tr>                
                                <% } else if (reciept.getServiceId() == 623) {%>
                        <tr><td class="td"><p>حالة العملية :</p> </td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="ناجحة" style="background-color: #EDEDED; float: right;"/></td></tr>
                        <tr><td class="td"><p>كود العميل :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=reciept.getPayed()%>" style="background-color: #EDEDED; float: right;"/></td></tr>
                        <tr><td class="td"> <p>اجمالى المبلغ المطلوب من العميل :</p></td><td style="padding-left: 10px;"><input type="text" readonly tabindex="1" value="<%=reciept.getAmount()%>" style="background-color: #EDEDED; float: right;"/></td></tr>               
                                <%}%>
                        <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="إغلاق وطباعة" onclick="setDivPrint()" /></td></tr>

                    </table>
                </div>
                <img src="./img/newlogo.png" style="visibility: hidden"/>
                <img src="./img/SmsMisr.jpg" style="visibility: hidden"/>
                <img src="./img/fmf.png" style="visibility: hidden"/>   
            </form>
        </div><!-- End content body -->

        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
        <!-- End of Main body-->
    </body>
</html>
