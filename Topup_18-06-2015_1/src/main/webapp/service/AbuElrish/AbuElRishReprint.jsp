<%@page import="java.util.logging.Level"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.integration.dto.AbuElRishResponseDTO"%>
<%@page import="com.masary.database.dto.Masary_Bill_Receipt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	session = request.getSession();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	Masary_Bill_Receipt abuelrishResponseDTO = (Masary_Bill_Receipt) request.getAttribute("receipt");
	
	request.getParameter("abuelrishType");
%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=CONFIG.get_AbuElRish(session)%></title>
<link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
</head>
<script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/Abuelrish.jpg"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">ابو الريش اليابانى</th></tr>');
                printwindow.document.write('<tr><th colspan="2">مستشفى الاطفال الجامعى التخصصى</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=abuelrishResponseDTO.getGlobalTrxID()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=abuelrishResponseDTO.getTransaction_date()%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ وتوقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=abuelrishResponseDTO.getAccountId()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');
                printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=abuelrishResponseDTO.getPayment_type_name()%></td><td style="text-align: right;padding-right:25px;width: 50%;">نوع  التبرع </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=abuelrishResponseDTO.getAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%;">مبلغ التبرع </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=abuelrishResponseDTO.getToBePaid()%></td><td style="text-align: right;padding-right:25px;width: 50%;"> اجمالى المبلغ المطلوب دفعه</td> </tr>');

		printwindow.document
				.write('<tr><th colspan="2">-----------------------------------</th></tr>');
		printwindow.document
				.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >شكرا لاستخدامك مصارى لخدمات الدفع الذكي  </th> </tr >');
		printwindow.document
				.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >خدمة عملاء مصارى: 16994 </th> </tr >');
		printwindow.document
				.write('<tr> <th colspan = "2" style = "text-align:center;font-size:16px; "><p>www.e-masary.com <p></th> </tr> </table> ');
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
	<form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="POST"
		style="font-weight: bold">
		<table style="width: 50%; margin-left: auto; margin-right: auto">
			<tr>
				<td colspan="2"
					style="text-align: center; font-weight: bold; font-size: medium">
					<img src="img/aboelrishlogo.jpeg" width="185" height="50"
					style="float: left;">
					<p align="right"><%=CONFIG.getName_AbuElRish(request.getSession())%>
					</p>

				</td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.getTheDonationAmountInPounds(session)%>:
					</p>
				</td>
				<td><input type="text" readonly
					style="background-color: #EDEDED;"
					value="<%=abuelrishResponseDTO.getAmount()%>"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.getServiceCost(session)%>:
					</p>
				</td>
				<td><input type="text" name="ServiceCost"
					value="<%=abuelrishResponseDTO.getFees()%>" readonly
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.getThetotalamountpaid(session)%>:
					</p>
				</td>
				<td><input type="text" name="TotalAmountPayable"
					value="<%=abuelrishResponseDTO.getToBePaid()%>" readonly
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td style="border: none">
					<p><%=CONFIG.getIsDeddued(session)%>:
					</p>
				</td>

				<td><input type="text" name="DedducedAmount"
					value="<%=abuelrishResponseDTO.getTransactionAmount()%>" readonly
					style="background-color: #EDEDED;"></td>

			</tr>

			<tr>
				<td>
					<p><%=CONFIG.getServiceState(session)%>:
					</p>
				</td>
				<td><label><%=CONFIG.getSuccessful(session)%></label></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.getServiceDate(session)%>:
					</p>
				</td>
				<td><label><%=abuelrishResponseDTO.getTransaction_date()%></label></td>
			</tr>

			<tr>
				<td colspan="2" class="secondTD"><input type="submit"
					value="<%=CONFIG.getPrinting(session)%>" id="submitbutton"
					onclick="setDivPrint()" /></td>
			</tr>
			<tr style="display: none">
				<th colspan="2"><img src="./img/Abuelrish.jpg" width="50"
					height="50"></th>
			</tr>
		</table>
		<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</body>
</html>