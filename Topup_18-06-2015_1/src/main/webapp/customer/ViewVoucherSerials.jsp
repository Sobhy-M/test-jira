<%--
    Document   : ViewTransaction_1.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="java.util.Map"%>
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
    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
%>

<%
    SellVoucherResponse voucherResponse = (SellVoucherResponse) session.getAttribute("voucherResponse");
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>View Serials</title>
    </head>

    <script type="text/javascript">
        function submitFormVoucher(voucher_serial) {
            document.getElementsByName('voucher_serial')[0].value = voucher_serial;
            document.form.submit();
        }
    </script> 

    <script type="text/javascript">
        function submitFormAllVoucher(voucher_serial) {
            document.getElementsByName('voucher_serial')[0].value = voucher_serial;
            document.form.submit();
        }
    </script> 

    <BODY class="body">
        <form  action="<%=CONFIG.APP_ROOT%>walletServices" method="post">
            <input type="hidden" name="action" value="<%=CONFIG.ACTION_Print_Vouvher_2%>" />
            <input type="hidden" name="voucher_serial" />  
            <input type="hidden" name="<%=CONFIG.PARAM_SELL_TYPE%>" value="<%=requestParameters.get(CONFIG.PARAM_SELL_TYPE)%>"/>  
            <input type="hidden" name="<%=CONFIG.PARAM_Transaction_ID%>" value="<%=requestParameters.get(CONFIG.PARAM_Transaction_ID)%>"/>  
            <input type="hidden" name="<%=CONFIG.PARAM_TAX_AMOUNT%>" value="<%=requestParameters.get(CONFIG.PARAM_TAX_AMOUNT)%>"/>  
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <div class="content_body"><br><br>
                <table id="t1" >
                    <thead>
                    <th scope="col"> <%=CONFIG.getVoucherType(session)%></th>
                <th> <%=CONFIG.getAmount_V(session)%></th>
                <th><%= CONFIG.getSerial(session)%></th>
                <th></th>
                </thead>
                <tbody>
                    <%  for (int i = 0; i < voucherResponse.getVoucherPin().size(); i++) {%>
                    <tr>
                        <td scope="row"><%=voucherResponse.getVoucherInfo().getService_Name()%></td>
                        <td><%=voucherResponse.getVoucherInfo().getDenom()%></td>
                        <td><%=voucherResponse.getVoucherSerial().get(i)%></td>
                        <td><button  type='submit' name='btnSubmit' alt='Print reciept copy' tabindex='3' value='Print' class='Btn' onclick="submitFormVoucher('<%=voucherResponse.getVoucherSerial().get(i)%>')"><img src='img/report.png' alt='Print reciept copy' height='30' width='30'/></button></td>
                    </tr>
                    <%}%>
                <td colspan="3" scope="row"><%=CONFIG.getTransactionsAll(session)%></td>
                <td ><button  type='submit' name='btnSubmit' alt='Print reciept copy' tabindex='3' value='Print' class='Btn' onclick="submitFormAllVoucher('All')"><img src='img/report.png' alt='Print reciept copy' height='30' width='30'/></button></td>
                </tbody></table>


        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</form>
</body>
</html>