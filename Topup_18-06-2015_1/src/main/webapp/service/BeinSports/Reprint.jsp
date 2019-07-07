<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.integration.dto.BeINSportsPaymentResponseDTO"%>
<%@page import="com.masary.integration.dto.BeINSportsInquiryResponseDTO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);

String date1=null;

SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
BeINSportsPaymentResponseDTO beINSportsPaymentResponse = (BeINSportsPaymentResponseDTO)request.getAttribute("beINSportsPaymentResponse");
Date date = new Date(beINSportsPaymentResponse.getUpdateDate());
date1 = dateFormat.format(date);

 

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=CONFIG.get_beInSports(session)%></title>
<link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
</head>

<script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 450px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/logomasary.jpg"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">beIN</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getTransactionId()%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_beInSportsTransactionId(session)%> </td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;"><%=CONFIG.get_beInSportsDate(session)%></td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getAccountId()%></td><td style = "text-align: right;padding-right:25px;width:50%;" ><%=CONFIG.get_beInSportsMerchant(session)%></td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_beInSportsClientName(session)%> </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 60%;"><%=beINSportsPaymentResponse.getClientName()%></td><td style="text-align: right;padding-right:25px;width: 70%;"></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getProgramName()%></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_beInSportsPackageName(session)%></td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=beINSportsPaymentResponse.getBillAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%;"><%=CONFIG.get_beInSportsAmount(session)%> </td> </tr>');
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
	}
</script>

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
	<form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST" style="font-weight: bold">
		
		
		<table style="width: 50%; margin-left: auto; margin-right: auto">
		<tr class="secondTD">
				<td colspan="2" class="secondTD">

					<p align="center"><%=CONFIG.getSUCCESSFUL(session) %></p>
				</td>
			</tr>
					<tr>
				<td>
					<p><%=CONFIG.get_beInSportsTransactionId(session)%></p>
				</td>
				<td>
					<div>
						<div>
							<input type="text" name="transactionId" size="30px"
								value="<%=beINSportsPaymentResponse.getTransactionId()%>" readonly
								style="background-color: #EDEDED;">
						</div>
					</div>
				</td>
			</tr>
			
				<tr>
				<td>
					<p><%=CONFIG.get_beInSportsDate(session)%></p>
				</td>
				<td>
					<div>
						<div>
							<input type="text" name="date" size="30px"
								value="<%=date1%>" readonly
								style="background-color: #EDEDED;">
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsMerchant(session)%></p>
				</td>
				<td>
					<div>
						<div>
							<input type="text" name="accountId" size="30px"
								value="<%=beINSportsPaymentResponse.getAccountId()%>" readonly
								style="background-color: #EDEDED;">
						</div>
					</div>
				</td>
			</tr>
		
		
		<tr>
				<td>
					<p><%=CONFIG.get_beInSportsClientName(session)%></p>
				</td>
				<td>
					<div>
						<div>
							<input type="text" name="clentName" size="30px"
								value="<%=beINSportsPaymentResponse.getClientName()%>" readonly
								style="background-color: #EDEDED;">
						</div>
					</div>
				</td>
			</tr>
		
		
		<tr>
				<td>
					<p><%=CONFIG.get_beInSportsPackageName(session)%></p>
				</td>
				<td>
					<div>
						<div>
							<input type="text" name="programeName" size="30px"
								value="<%=beINSportsPaymentResponse.getProgramName()%>" readonly
								style="background-color: #EDEDED;">
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<p><%=CONFIG.get_beInSportsAmount(session)%></p>
				</td>
				<td>
					<div>
						<div>
							<input type="text" name="billAmount" size="30px"
								value="<%=beINSportsPaymentResponse.getBillAmount()%>" readonly
								style="background-color: #EDEDED;">
						</div>
					</div>
				</td>
			</tr>
			
			
				<tr>
				<td>
					<p><%=CONFIG.get_beInSportsAppliesFees(session)%></p>
				</td>
				<td>
					<div>
						<div>
							<input type="text" name="appliedFees" size="30px"
								value="<%=beINSportsPaymentResponse.getAppliedFees()%>" readonly
								style="background-color: #EDEDED;">
						</div>
					</div>
				</td>
			</tr>
			
				<tr>
				<td>
					<p><%=CONFIG.get_beInSportsToBePaid(session)%></p>
				</td>
				<td>
					<div>
						<div>
							<input type="text" name="toBePaid" size="30px"
								value="<%=beINSportsPaymentResponse.getToBepaid()%>" readonly
								style="background-color: #EDEDED;">
						</div>
					</div>
				</td>
			</tr>
			
						<tr>	
				<td colspan="2">
					
							<input type="submit"
								value="<%=CONFIG.get_beInSportsConfirm(session)%>"
								id="submitbutton" onclick="setDivPrint()" style="float:right"/>
						
				
					
						<input type="button" value="<%=CONFIG.getBack(session)%>"
							id="submitbutton" onclick="history.go(-1);" style="float:left"/>
					
				</td>

			</tr>
				<tr style="display: none">
				<th colspan="2"><img src="./img/logomasary.jpg" width="50"
					height="50"></th>
			</tr>
		</table>
	</form>
	<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

</body>
</html>
