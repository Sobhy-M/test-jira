
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.integration.dto.AcceptPaymentResponse"%>
<%@page import="com.masary.integration.dto.AcceptInquiryResponse"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>


<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }

session = request.getSession();

AcceptPaymentResponse acceptPaymentResponse = (AcceptPaymentResponse)request.getAttribute("acceptPaymentResponse");


String date1=null;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
Date date = new Date(acceptPaymentResponse.getUpdateDate());
date1 = dateFormat.format(date);
String operationType=(String)session.getAttribute("operationType");
String serviceId=(String) request.getAttribute("trxTypeID");

   
%>

<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.getPrinting_Receipt(request.getSession())%></title>
        <script>
            function setDivPrint() {

                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/logomasary.jpg"  width="200" height="60"></td></tr>');
                <% if(serviceId.equals("23111")){ %>
                printwindow.document.write('<tr><th colspan="2"><%=CONFIG.get_acceptPayments(session)%></th></tr>');
                printwindow.document.write('<tr><th colspan="2"><%=acceptPaymentResponse.getBillerName()%></th></tr>');
                <%}else{ %>
                printwindow.document.write('<tr><th colspan="2"><%=operationType%></th></tr>');
                <%} %>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=acceptPaymentResponse.getGlobalTrxId()%></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.GetOperationID(session)%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style ="text-align: right;padding-right:25px;width: 50%;" ><%=CONFIG.acceptTransactionDate%></td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=acceptPaymentResponse.getAccountId()%></td><td style ="text-align: right;padding-right:25px;width: 50%;" ><%=CONFIG.get_acceptMerchantNumber(session)%></td></tr>');
                printwindow.document.write('<tr><th colspan="2">________________________________________ </th></tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=acceptPaymentResponse.getInquiryReferenceId()%></td><td style ="text-align: right;padding-right:25px;width: 50%;" ><%=CONFIG.getLedgerID(session)%></td></tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=acceptPaymentResponse.getClientName()%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_acceptCustomerName(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=acceptPaymentResponse.getDueAmount()%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_acceptAmount(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=acceptPaymentResponse.getAppliedFees() + acceptPaymentResponse.getTax()%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_acceptAppliedFees(session)%></td> </tr><tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=acceptPaymentResponse.getToBepaid()%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_AcceptTotalPaidAmount(session)%></td> </tr><tr>');

                printwindow.document.write('<tr><th colspan="2">------------------------------------------------------------ </th></tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> شكرا لاستخدامكم مصارى لخدمات الدفع الذكية <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" ><p> خدمة عملاء مصارى:16994 <p></th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();

            }
        </script>
    </head>
    <body class="body">
        <div>
            <script type ="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>    <%}%>
            </div>

            <form name="dobillinquiry" action="<%=CONFIG.APP_ROOT + role + ".jsp"%>" method="POST" >
            
            
           
			
            <div>
                <table cellspacing="6" style="font-size: 12px; font-weight: 900;"  > 
                <% if(serviceId.equals("23111")){ %>
                    <tr><td colspan="2" style="text-align: center;"><%=CONFIG.get_acceptPayments(session)+ " - "  + acceptPaymentResponse.getBillerName()%> </tr></td>
                   <%}else{ %>
                     <tr><td colspan="2" style="text-align: center;"><%=operationType%> </tr></td>
                   
                   <%} %>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><%=CONFIG.getServiceState(session)%></td><td><input type="text" readonly style="background-color: #EDEDED;"value="<%=CONFIG.GetSuccessfulTransaction(session)%>"></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><%=CONFIG.GetOperationID(session)%></td><td><input type="text" readonly style="background-color: #EDEDED;"value="<%=acceptPaymentResponse.getGlobalTrxId()%>"></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><%=CONFIG.get_acceptAmount(session)%></td><td><input type="text" readonly style="background-color: #EDEDED;"value="<%=acceptPaymentResponse.getDueAmount()%>"></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><%=CONFIG.get_acceptAppliedFees(session)%></td><td ><input type="text" readonly style="background-color: #EDEDED;" value="<%=acceptPaymentResponse.getAppliedFees() + acceptPaymentResponse.getTax()%>"></td></tr>
                    <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;"><%=CONFIG.get_AcceptTotalPaidAmount(session)%></td><td ><input type="text" readonly style="background-color: #EDEDED;" value="<%=acceptPaymentResponse.getToBepaid()%>"></td></tr>    
                    <tr><td style="  width: 50% ; padding-right: 30px;text-align: right;"><%=CONFIG.get_acceptDeductedAmount(session) %></td><td><input type="text" readonly style="background-color: #EDEDED;" value="<%=acceptPaymentResponse.getTransactionAmount()%>"></td></tr>                
                                
                     <tr><td colspan="2" style="text-align: center"><input type="submit" id="print" value="إغلاق وطباعة" onclick="setDivPrint()" /></td></tr>
                     <tr style="display: none">
				<th colspan="2"><img src="./img/logomasary.jpg" width="50"
					height="50"></th>
			</tr>
                </table>
            </div>
        </form>
        <!--</form>-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>