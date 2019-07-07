<%--
    Document   : EtisalatTopup
    Created on : 06/06/2018, 01:09:17 م
    Author     : Abdelsabor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
    
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.integration.dto.CommonTopupRepresentationDTO"%>
<%@page import="com.masary.controlers.OrangeTopup.OrangeTopupPayment"%>
<%@page import="com.masary.controlers.OrangeTopup.OrangeTopupProperties"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

	CommonTopupRepresentationDTO commonTopupRequestDTO = (CommonTopupRepresentationDTO) request.getAttribute("transactionDTO");

    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    Date trxDate = new Date(commonTopupRequestDTO.getTransactionTime());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String trxDateStr = dateFormat.format(trxDate);
    DecimalFormat myFormatter = CONFIG.getFormater(session);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=CONFIG.getTransactionReport(session)%></title>
         <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">         
    </head>
            <script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/Masary.jpg"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">شحن  أورانج</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=commonTopupRequestDTO.getGlobalTrxId()%></td><td style="text-align: right;padding-right:25px;width: 50%;">:رقم العملية</td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=trxDateStr%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:تاريخ وتوقيت العملية</td></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=commonTopupRequestDTO.getProviderTrxId()%> </td><td style ="text-align: right;padding-right:25px;width: 50%;" >:الرقم المرجعي</td></tr>');

                printwindow.document.write('<tr><th colspan="2">----------------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=commonTopupRequestDTO.getMsisdn()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >:رقم  العميل</td> </tr>');
                
                <c:if test="${transactionDTO.appliedFees != 0.0}">
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=commonTopupRequestDTO.getAppliedFees()%></td><td style = "text-align: right;padding-right:25px;width:50%;">:تكلفة الخدمة</td> </tr><tr>');
                </c:if>
                
                <c:if test="${transactionDTO.tax != 0.0}">
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=commonTopupRequestDTO.getTax()%></td><td style = "text-align: right;padding-right:25px;width:50%;">ضريبة القيمة المضافة:</td> </tr><tr>');
                </c:if>
                
                printwindow.document.write('<tr><th colspan="2">إجمالي المبلغ المدفوع شامل ضريبة القيمة المضافة و تكلفة الخدمة :<%=commonTopupRequestDTO.getToBePaid()%></th></tr>');
                printwindow.document.write('<tr><th colspan="2">----------------------------------------</th></tr>');
                
                <c:if test="${transactionDTO.adsWord != ''}">
                printwindow.document.write('<tr><td colspan="2" style="text-align:center;padding-left:15px;width: 50%;"><%=commonTopupRequestDTO.getAdsWord()%></td></tr>');
                printwindow.document.write('<tr><th colspan="2">----------------------------------------</th></tr>');
            	</c:if>
            	
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" > قد يستغرق تنفيذ العملية 24 ساعة في حالة بطء الشبكة</th> </tr >');
                printwindow.document.write('<tr><th colspan="2">----------------------------------------</th></tr>');                
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" > شكرا لاستخدامكم مصاري لخدمات الدفع الذكية </th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" > خدمة عملاء مصاري:16994 </th> </tr >');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; ">www.e-masary.com </th> </tr>');
                printwindow.document.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; ">= نسخة العميل= </th> </tr> </table> ');
                printwindow.location.reload();
                printwindow.document.close();
                printwindow.focus();
                printwindow.print();
                printwindow.close();
                window.location.replace("2.jsp");
            }
        </script>
        
    <BODY class="body" >
        <div>
              <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <div class="content_body">
                <fieldset style="width: 50%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%=CONFIG.getTransactionReport(session)%></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
                <table border="1" style="width: 100%">
                    <tr>
                        <!-- رقم العمليه -->
                        <th scope="col"><%=CONFIG.getTransactionNumber(session)%></th>
                        <td><%=commonTopupRequestDTO.getGlobalTrxId()%> </td>
                    </tr>
                    
                    <tr>
                        <th scope="col"><%=CONFIG.getTo(session)%></th>
                        <td><%=commonTopupRequestDTO.getMsisdn()%></td>
                    </tr>
                     <tr>
                        <th scope="col"><%=CONFIG.getType(session)%></th>
                        <td><%=OrangeTopupProperties.getOrangeTopUp(session)%></td>
                    </tr>
                    <tr>
                        <th scope="col"><%=CONFIG.getStatus(session)%></th>
                        <td><%=CONFIG.GetSuccessfulTransaction(session)%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getRechargeAmount(session)%></th>
                        <td><%=myFormatter.format(commonTopupRequestDTO.getToBePaid())%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getTaxValue(session)%></th>
                        <td><%=myFormatter.format(commonTopupRequestDTO.getTax())%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getUserAmount(session)%></th>
                        <td><%=myFormatter.format(commonTopupRequestDTO.getTransactionAmount())%></td>
                    </tr>

                    <tr>
                        <th scope="col"><%=CONFIG.getDate(session)%></th>
                        <td><%=trxDateStr%></td>
                    </tr>

                    <tr>
                    
                    <tr style="display: none">
				<th colspan="2"><img src="./img/Masary.jpg" width="50"
					height="50"></th>
			</tr>
			
                    <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%>  method="post"  name="admin_form" id="saveform">
                        <td colspan="2" align="center">
                            <input type="submit" name="btnSubmit" tabindex="5" onclick="setDivPrint()" value="OK" class="Btn">
                        </td>
                    </form>
                    </tr>
                </table>
                <details open="open">
                    <summary>
                        "قد يستغرق تنفيذ العملية بعض الوقت." : "يتم طباعة الكارت أو إرساله فى رسالة للعميل.
                    </summary>
                </details>
            </fieldset>
        </div><!-- End of Table Content area-->
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>