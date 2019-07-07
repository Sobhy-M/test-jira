<%@page import="java.util.logging.Level"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.integration.dto.TEData_Payment_Response"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	session = request.getSession();
	
	TEData_Payment_Response tedataPaymentResponse = (TEData_Payment_Response) request.getAttribute("tedataPaymentResponse");
	String date1=null;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	SimpleDateFormat date1Format = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	Date date = new Date(tedataPaymentResponse.getUpdateDate());
	 date1 = dateFormat.format(date);
	 String date2 = date1Format.format(date);
	 String time = timeFormat.format(date);
//	 String toBePaid = Double.toString(tedataPaymentResponse.getToBepaid());

%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=CONFIG.get_TEDataName(session)%></title>
<link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
<script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10" style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/masarylogoo.jpg"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2"><p align="center" style="display: inline-block;vertical-align: center;"> الانترنت المنزلى WE</p> <p style="display: inline-block;vertical-align: center;">شحن باقات  </p></th></tr>');
                printwindow.document.write('<tr><th colspan="2">_____________________________________</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"> عملية ناجحة </td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.getServiceState(session)%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%=tedataPaymentResponse.getGlobalTrxId()%>   </td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.getAgentPaymentTrx(session)%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%=tedataPaymentResponse.getAccountId()%>  </td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_TEDataMerchantID(session)%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%=date1%>  </td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_TEDataDateAndTime(session)%></td> </tr>');
                printwindow.document.write('<tr><th colspan="2">_____________________________________</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%=tedataPaymentResponse.getAdslNumber()%>  </td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_TEDataPhone(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%=tedataPaymentResponse.getPackageQuota()%>  </td><td style = "text-align: right;padding-right:25px;width:50%;">GB <%=CONFIG.get_Tedata(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%=tedataPaymentResponse.getPackageAmount()%>  </td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_TedataChargingCategory(session)%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%=tedataPaymentResponse.getAppliedFees()%>  </td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_TEDataCost(session)%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;">  <%=tedataPaymentResponse.getToBepaid()%> </td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_TEDataTotal(session)%></td> </tr>');
		        printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
		        printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size: 16px;" ><%=CONFIG.get_TEDataThanks(session)%></th> </tr >');
		        printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size: 16px;" ><%=CONFIG.get_TEDataCustomer(session)%></th> </tr >');
		        printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size:16px; "><%=CONFIG.get_TEDataWebSite(session)%></th> </tr> </table>');
				printwindow.document.close();
				printwindow.focus();
				printwindow.print();
				printwindow.close();
	}
</script>
</head>
<body>
	<div>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%
			if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
		%>
		<jsp:include page="../../img/menuList.jsp"></jsp:include>
		<%
			} else {
		%>
		<jsp:include page="../../img/menuListar.jsp"></jsp:include>
		<%
			}
		%>
	</div>
	<form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST"
		style="font-weight: bold">
		<table style="width: 40%; margin-left: auto; margin-right: auto">


			<tr>
				<td colspan="2">

					<p align="center"><%=CONFIG.get_TEDataWeBonusCharge(request.getSession())%>
					</p>

				</td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.getAgentPaymentTrx(session)%>:
					</p>
				</td>
				<td><input type="text" readonly value="<%=tedataPaymentResponse.getGlobalTrxId()%>"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.getServiceDate(session)%>:
					</p>
				</td>
				<td><input type="text" name="ServiceDate" value="<%=date2%>" readonly></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.getServiceTime(session)%>:
					</p>
				</td>
				<td><input type="text" name="ServiceTime" value="<%=time%>" readonly></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.get_TEDataMerchantID(session)%>:
					</p>
				</td>
				<td><input type="text" name="MerchantID" value="<%=tedataPaymentResponse.getAccountId()%>" readonly></td>
			</tr>

			<tr>
				<td>
					<p><%=CONFIG.get_TedataPhoneNumber(session)%>:
					</p>
				</td>
				<td><input type="text" name="PhoneNumber" value="<%=tedataPaymentResponse.getAdslNumber()%>" readonly></td>
			</tr>

			<tr>
				<td>
					<p><%=CONFIG.getO_AMOUNT(session)%>:
					</p>
				</td>
				<td><input type="text" name="Amount" value="<%=tedataPaymentResponse.getPackageAmount()%>" readonly></td>
			</tr>

			<tr>
				<td>
					<p><%=CONFIG.getServiceCost(session)%>:
					</p>
				</td>
				<td><input type="text" name="ServiceCost" value="<%=tedataPaymentResponse.getAppliedFees()%>" readonly></td>
			</tr>

			<tr>
				<td>
					<p><%=CONFIG.get_TEDataTotalPrice(session)%>:
					</p>
				</td>
				<td><input type="text" name="TotalPrice" value="<%=tedataPaymentResponse.getToBepaid()%>" readonly></td>
			</tr>

			<tr>
				<td colspan="2" align="center">
				<p align="center" style="display: inline-block;vertical-align: center;">شكرا لاستخدامك مصارى لخدمات الدفع الذكي</p> <p style="display: inline-block;vertical-align: center;">16994</p>
				</td>

			</tr>
			<tr>
				<td colspan="2">
					<p align="center">WWW.E-MASARY.COM</p>
				</td>


			</tr>

			<tr>
				<td colspan="2" class="secondTD"><input type="button"
					value="<%=CONFIG.getPrinting(session)%>" id="submitbutton"
					onclick="setDivPrint()" /></td>
			</tr>
			<tr style="display: none">
				<th colspan="2"><img src="./img/masarylogoo.jpg" width="50"
					height="50"></th>
			</tr>
		</table>
		<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</body>
</html>