<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.integration.dto.BeINSportsPaymentResponseDTO"%>
<%@page import="com.masary.integration.dto.BeINSportsInquiryResponseDTO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
BeINSportsInquiryResponseDTO beINSportsInquiryResponse=null;
BeINSportsPaymentResponseDTO beINSportsPaymentResponse=null;
String date1=null;
if(request.getAttribute("action").equals("confirmation")) {
      beINSportsInquiryResponse = (BeINSportsInquiryResponseDTO) request.getAttribute("inquiryResponse");
}
else if(request.getAttribute("action").toString().equals("reciept")){
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	 beINSportsPaymentResponse = (BeINSportsPaymentResponseDTO) request.getAttribute("beINSportsPaymentResponse");
	Date date = new Date(beINSportsPaymentResponse.getUpdateDate());
	 date1 = dateFormat.format(date);
}
   
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=CONFIG.get_beInSports(session)%></title>
<link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
</head>
<%if(request.getAttribute("action").equals("reciept")) {%>
<script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 450px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/logomasary.jpg"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">beIN</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getTransactionId()%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_beInSportsTransactionId(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_beInSportsDate(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getAccountId()%></td><td style = "text-align: right;padding-right:25px;width:50%;" ><%=CONFIG.get_beInSportsMerchant(session)%></td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_beInSportsClientName(session)%> </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 60%;"><%=beINSportsPaymentResponse.getClientName()%></td><td style="text-align: right;padding-right:25px;width: 60%;"></td> </tr>');

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getProgramName()%></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_beInSportsPackageName(session)%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getBillAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_beInSportsAmount(session)%></td> </tr>');
                <%if(beINSportsPaymentResponse.getAppliedFees()!=0.0){%>

                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getAppliedFees()%></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_beInSportsAppliesFees(session)%></td> </tr>');<%}%>
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getToBepaid()%></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_beInSportsToBePaid(session)%></td> </tr>');

		printwindow.document
				.write('<tr><th colspan="2">-----------------------------------</th></tr>');
		printwindow.document
				.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >شكرا لاستخدامك مصارى لخدمات الدفع الذكي</th> </tr >');
		printwindow.document
				.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >خدمة عملاء مصارى: 16994 </th> </tr >');
		printwindow.document
				.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr>');
		printwindow.document
				.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>Powered by e-finance <p></th> </tr> </table> ');

		printwindow.document.close();
		printwindow.focus();
		printwindow.print();
		printwindow.close();
		 document.getElementById("paymentForm").submit();   
	}
                
</script>
<body onload="setDivPrint()">
<%
			}

		%>

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
	<%if(request.getAttribute("action").equals("confirmation")) {%>
	<form action="BeinSportsPaymentController" method="POST"
		style="font-weight: bold">
		<input type="hidden" name="validationId" id="validationId"
			value="<%=beINSportsInquiryResponse.getValidationId()%>" />
			
		<input type="hidden" name="programName" id="programName"
			value="<%=beINSportsInquiryResponse.getProgramName()%>" />	
		
		<input type="hidden" name="inqueryAmount" id="inqueryAmount"
			value="<%=beINSportsInquiryResponse.getAmount()%>" />		


		<table style="width: 50%; margin-left: auto; margin-right: auto">

			<tr class="secondTD">
				<td colspan="2" class="secondTD">
					<table>
						<tr>
							<td
								style="border: none; background-color: transparent; padding: 0px"
								class="secondTD"><label>&nbsp;<%=CONFIG.get_beInSportsSubscriber(request.getSession())+" "+CONFIG.get_beInSports(request.getSession())%></label></td>

							<td
								style="border: none; background-color: transparent; padding: 0px"
								class="secondTD"><label>&nbsp;<%=CONFIG.get_beInSportsPackage(request.getSession())+" "+beINSportsInquiryResponse.getProgramName()%></label></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr class="secondTD">
				<td colspan="2" class="secondTD">

					<p align="center"><%=CONFIG.get_beInSportsInfo(session)%></p>
				</td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsSubscriberName(session)%></p>
				</td>
				<td>
					<div>
						<div>
							<input type="text" name="subscriberName" size="30px"
								value="<%=beINSportsInquiryResponse.getClientName()%>" readonly
								style="background-color: #EDEDED;">
						</div>
					</div>
				</td>
			</tr>
			<tr>

				<td>
					<p><%=CONFIG.get_PhoneNumberBeinSports(session)%></p>
				</td>
				<td><input type="text" name="mobile" size="30px"
					value="<%=beINSportsInquiryResponse.getMsisdn()%>" readonly
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsPackage(session)%></p>
				</td>
				<td><input type="text" name="package" size="30px"
					value="<%=beINSportsInquiryResponse.getProgramName()%>" readonly
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsPackageDate(session)%></p>
				</td>
				<td><input type="text" name="packageDate" size="30px"
					value="<%=beINSportsInquiryResponse.getCoverageEndDate()%>"
					readonly style="background-color: #EDEDED;"></td>

			</tr>

			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsRequiredAmount(session)%></p>
				</td>
				<td><input type="text" readonly name="requiredAmount" size="30px"
					value="<%=beINSportsInquiryResponse.getAmount()%>"
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsServiceCost(session)%></p>
				</td>
				<td><input type="text" readonly name="cost" size="30px"
					value="<%=beINSportsInquiryResponse.getAppliedFees()%>"
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsMerchantCommission(session)%></p>
				</td>
				<td><input type="text" readonly name="commission" size="30px"
					value="<%=beINSportsInquiryResponse.getMerchantCommission()%>"
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsDeductedAmount(session)%></p>
				</td>
				<td><input type="text" readonly name="deductedAmount" size="30px"
					value="<%=beINSportsInquiryResponse.getTransactionAmount()%>"
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsTotalAmount(session)%></p>
				</td>
				<td><input type="text" readonly name="totalAmount" size="30px"
					value="<%=beINSportsInquiryResponse.getToBepaid()%>"
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>

				<td colspan="2"><input type="submit"
					value="<%=CONFIG.get_beInSportsConfirm(session)%>"
					id="submitbutton" onclick="setDivPrint()" style="float: right" />
					<input type="button" value="<%=CONFIG.getBack(session)%>"
					id="submitbutton" onclick="history.go(-1);" style="float: left" />

				</td>

			</tr>
		
		</table>
	</form>
	<%} else if(request.getAttribute("action").equals("reciept")){ %>


	<form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST"
		style="font-weight: bold" id="paymentForm">
						
<table>
     <tr style="display: none">
				<th colspan="2"><img src="./img/logomasary.jpg" width="50"
					height="50"></th>
			</tr>
			</table>
	</form>

	<%} %>

	<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

</body>
</html>
