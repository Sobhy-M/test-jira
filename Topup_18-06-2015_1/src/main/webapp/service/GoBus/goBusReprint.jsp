<%--
    Document   : GoBusPayment.jsp
    Created on : 26/06/2016, 11:09:49 ص
    Author     : Abdelsabor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.controlers.GoBus.GoBusProperties"%>
<%@page import="com.masary.integration.dto.GoBusPaymentRepresentation"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
if (role == null) {
    response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
    return;
    
    
    
}

GoBusPaymentRepresentation paymentRepresentation = (GoBusPaymentRepresentation) request.getAttribute("goBusPaymentRepresentation");
Date trxDate = new Date(paymentRepresentation.getUpdateDate());
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
String trxDateStr = dateFormat.format(trxDate);

%>


<html>


	<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=GoBusProperties.getGoBus(session)%></title>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    </head>
    
    
             <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center; float: center;"><img src="./img/Masary.jpg"  width="170px" height="70px"></td></tr>');

                printwindow.document.write('<tr><th colspan="2">جو باص</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${requestScope.goBusPaymentRepresentation.globalTrxId}</td><td style="text-align: right;padding-right:25px;width: 50%;">:رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trxDateStr%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ وتوقيت العملية</td></tr>');
                printwindow.document.write('<tr><th colspan="2">----------------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${requestScope.goBusPaymentRepresentation.providerTransactionNumber}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:الرقم المرجع</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${requestScope.goBusPaymentRepresentation.customerName}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:إسم العميل</td></tr>');
                
                <c:if test="${not empty requestScope.goBusPaymentRepresentation.goDate}">
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">ذهاب</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:نوع الرحلة</td></tr>');
        		printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${requestScope.goBusPaymentRepresentation.goDate}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ الرحلة</td></tr>');
        		printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${requestScope.goBusPaymentRepresentation.goDetails}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تفاصيل الرحلة</td></tr>');
        		printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${requestScope.goBusPaymentRepresentation.goSeatsNumber}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:رقم المقعد/المقاعد</td></tr>');

                </c:if>
                
                <c:if test="${not empty requestScope.goBusPaymentRepresentation.backDate}">
            		printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">عودة</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:نوع الرحلة</td></tr>');
            		printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${requestScope.goBusPaymentRepresentation.backDate}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ الرحلة</td></tr>');
            		printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${requestScope.goBusPaymentRepresentation.backDetails}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تفاصيل الرحلة</td></tr>');
            		printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">${requestScope.goBusPaymentRepresentation.backSeatsNumber}</td><td style ="text-align: right;padding-right:25px;width: 50%;" >:رقم المقعد/المقاعد</td></tr>');

            	</c:if>                       
                
                printwindow.document.write('<tr><th colspan="2">:اجمالي المبلغ المدفوع شامل ضريبه القيمه المضافه وتكلفة الخدمة  ${requestScope.goBusPaymentRepresentation.toBepaid}</th></tr>');
                printwindow.document.write('<tr><th colspan="2">----------------------------------------</th></tr>');        
                printwindow.document.write('<tr><th colspan="2">------------------------------</th></tr>');
                
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" > شكرا لاستخدامكم مصارى لخدمات الدفع الذكية </th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" > خدمة عملاء مصارى:16994 </th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; ">www.e-masary.com </th> </tr>');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
                window.location.replace("2.jsp");
            }
        </script>
        


    <BODY class="body"  >
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>  

           
            
            <div class="content_body">
                <table>
                <tr>
                <td colspan="2"></td>
                	<%=CONFIG.getSUCCESSFUL(session) %>
                </tr>
                    <tr>
                        <td><%=CONFIG.getAgentPaymentTrx(session)%></td>
                        <td>
                            <div class="requriedclass">
                                <div>
                                    <input type="text" value="${requestScope.goBusPaymentRepresentation.globalTrxId}" id ="MSISDN" readonly style="background-color: #EDEDED; float: left;"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                     <tr>
                        <td><%=CONFIG.getPaidAmountAr(session)%></td>
                        <td>
                            <div class="requriedclass">
                                <div>
                                    <input type="text" value="${requestScope.goBusPaymentRepresentation.transactionAmount}" readonly style="background-color: #EDEDED; float: left;"/>
                                </div>
                            </div>
                        </td>
                    </tr> 
                    
                                        <tr style="display: none">
				<th colspan="2"><img src="./img/Masary.jpg" width="50"
					height="50"></th>
			</tr>
			
                </table>

		<form action=<%=CONFIG.APP_ROOT + role + ".jsp"%>  method="post"  name="admin_form" id="saveform">
                        <td colspan="2" align="center">
                            <input type="submit" name="btnSubmit" tabindex="5" onclick="setDivPrint()" value="<%=GoBusProperties.getPrintAndClose(session)%>" class="Btn">
                        </td>
			</form>

            </div><!-- End of Table Content area-->

    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
