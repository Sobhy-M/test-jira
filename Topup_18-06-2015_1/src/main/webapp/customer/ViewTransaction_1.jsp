<%--
    Document   : ViewTransaction_1.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="com.masary.database.dto.SellVoucherResponse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.TransactionDTO"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
    String transId = (String) request.getAttribute(CONFIG.PARAM_Transaction_ID);
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=CONFIG.getTransactionReport(session)%></title>
        <script src="http://www.java.com/js/deployJava.js"></script>
        <script type="text/javascript">
            //var oJavaDetect = deployJava.versionCheck("1.5") ; 
            function check() {
                var oJavaDetect = null;
                oJavaDetect = deployJava.getJREs();
                var element = document.getElementById('javaLink');
                if (oJavaDetect.length === 0)
                {
                    document.getElementById('javaMsg').innerHTML = "<%=CONFIG.getjavaMessge(session)%>";
                    element.innerHTML = "<%=CONFIG.getjavaMessge(session)%>";
                    element.innerHTML = "<img src='img/javaicon.png' />";
                    element.setAttribute('onclick', 'window.location.href=\'http://e-masary.net//app//getJava\'');
                }
            }
        </script> 
        <script type="text/javascript">
            function zPrint(oTgt)
            {
                oTgt.focus();
                oTgt.print();
            }
        </script>
    </head>
    <BODY class="body" onload="check()">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div class="content_body"><br><br>
        <%
            SellVoucherResponse voucherResponse = (SellVoucherResponse) session.getAttribute("voucherResponse");
            if (voucherResponse.getVoucherPin().size() != 0 && voucherResponse.getVoucherSerial().size() != 0) {
                String vV = "";
                String cmd = "";
                String voucherPins = "";
                String voucherSerials = "";
                for (int i = 0; i < voucherResponse.getVoucherPin().size(); i++) {
                    if (i != voucherResponse.getVoucherPin().size() - 1) {
                        voucherPins = voucherPins.concat(voucherResponse.getVoucherPin().get(i) + "-");
                        voucherSerials = voucherSerials.concat(voucherResponse.getVoucherSerial().get(i) + "-");
                    } else {
                        voucherPins = voucherPins.concat(voucherResponse.getVoucherPin().get(i));
                        voucherSerials = voucherSerials.concat(voucherResponse.getVoucherSerial().get(i));
                    }
                }
                if (!request.getParameter(CONFIG.PARAM_SELL_TYPE).equals("View on screen")) {

        %>
        <th><H3 id="javaMsg" style="color: red; font-size: 18px"></H3></th>
        <th><a style="color: red; font-size: 18px" id="javaLink"></a></th>
        <th><H3 style="color: red; font-size: 18px"><%=CONFIG.getVoucherMessge2(session)%><a href='http://e-masary.net/app/getJava'> اضغط هنا للتحميل</a><%=CONFIG.getVoucherMessge3(session)%></H3></th>
        <br/>
        <th><H3 style="color: red; font-size: 18px"><%=CONFIG.getVoucherMessge4(session)%> </h3></th>    
        <div>
            <applet  code="printApplet" name="0" archive="PrintApp.jar" width="0" height="0" > 
                <param name="serviceType" value="<%=trans.getType().toLowerCase().contains("voda")
                        ? "vodafone" : trans.getType().toLowerCase().contains("mobi") ? "mobinil" : trans.getType().toLowerCase().contains("etis")
                        ? "etisalat" : trans.getType().toLowerCase().contains("blablavo") ? "blablavo"  : trans.getType().toLowerCase().contains("onecard") ? "onecard" : trans.getType().toLowerCase().contains("marhaba")
                        ? "marhaba" : trans.getType().toLowerCase().contains("BlaBlaTopUp") ? "BlaBlaTopUp" : "CashU_Voucher"%>"/>
                <param name="v" value="<%=vV%>"/>
                <param name="cmd" value="<%=cmd%>" />
                <param name="voch" value="<%=voucherPins%>"/>
                <param name="serial" value="<%= voucherSerials%>"/>
                <param name="amount" value="<%= Double.toString(voucherResponse.getVoucherInfo().getDenom()).indexOf(".0") != -1 ? Double.toString(voucherResponse.getVoucherInfo().getDenom()).substring(0, Double.toString(voucherResponse.getVoucherInfo().getDenom()).indexOf(".0")) : Double.toString(voucherResponse.getVoucherInfo().getDenom())%>"/>          
               <%if(trans.getType().toLowerCase().contains("CashU_Voucher")){%>
                <param name="TotalAmount" value="<%= (int)(Double.parseDouble(request.getParameter("FEES")) +  Double.parseDouble(request.getParameter("AMOUNT")))%>"/>
                <param name="FEES" value="<%= (int)Double.parseDouble(request.getParameter("FEES")) %>"/><%}%>
                <param name="TID" value="<%=trans.getTransId()%>"/>
                <param name="from" value="<%=trans.getCustomerPayerName().trim().contains(" ") ? trans.getCustomerPayerName().trim().substring(0, trans.getCustomerPayerName().indexOf(" ")) : trans.getCustomerPayerName()%>"/>
                <param name="time" value="<%=trans.getDate().substring(11)%>"/>
                <param name="date" value="<%=trans.getDate().substring(0, 10)%>"/>
                <param name="vNo" value="<%=request.getParameter("secondPrinted")%>"/>
            </applet>
            <%} else {%>
            <table id="t1">
                <thead>
                <th scope="col"> <%=CONFIG.getVoucherType(session)%></th>
                <th> <%=CONFIG.getAmount_V(session)%></th>
                <th><%= CONFIG.getRecharge_No(session)%></th>
                <th><%= CONFIG.getSerial(session)%></th>
                <th><%= CONFIG.getRecharge_Type(session)%></th>
                </thead>
                <tbody>
                    <tr>
                        <td scope="row"><%=voucherResponse.getVoucherInfo().getService_Name()%></td>
                        <td><%=voucherResponse.getVoucherInfo().getDenom()%></td>
                        <td><%=voucherResponse.getVoucherPin().get(0)%></td>
                        <td><%=voucherResponse.getVoucherSerial().get(0)%></td>
                        <td><%= trans.getType().toLowerCase().contains("voda") ? "<img alt='' height='30' width='100' src='img/Vodafone_recharge.png' />"
                                : (trans.getType().toLowerCase().contains("mobi") ? "<img alt='' height='30' width='100' src='img/Mobinil_recharge.png' />"
                                : (trans.getType().toLowerCase().contains("etis") ? "<img alt='' height='30' width='100' src='img/Etisalat_recharge.png' />" : ""))%></td>
                    </tr>
                </tbody></table>
                <%}
                    }%>

        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>