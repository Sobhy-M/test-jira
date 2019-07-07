
<%@page import="java.util.logging.Level"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.integration.dto.SECIResponseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	session = request.getSession();

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	SECIResponseDTO seciResponseDTO = (SECIResponseDTO) request.getAttribute("seciResponseDTO");
	Date date = new Date(seciResponseDTO.getUpdateDate());
	String date1 = dateFormat.format(date);
%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=CONFIG.get_SECI(session)%></title>
<link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
</head>
<script>
            function setDivPrint() {
                var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                printwindow.document.write('<table cellspacing="10"  style="font-size: 20px; font-weight: 700; width: 350px" >');
                printwindow.document.write('<tr><td colspan="2" style="text-align: center"><img src="img/Masary.jpg"  width="200" height="60"></td></tr>');
                printwindow.document.write('<tr><th colspan="2">مستشفى 2020</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=seciResponseDTO.getGlobalTrxId()%></td><td style = "text-align: right;padding-right:25px;width:50%;">رقم العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=date1%></td><td style = "text-align: right;padding-right:25px;width:50%;">تاريخ وتوقيت العملية</td> </tr><tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=session.getAttribute(CONFIG.PARAM_PIN).toString()%> </td><td style = "text-align: right;padding-right:25px;width:50%;" >رقم التاجر</td> </tr>');

                printwindow.document.write('<tr><th colspan="2">-----------------------------------</th></tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=new String(request.getParameter("seciid").toString().getBytes("ISO-8859-1"), "utf-8")%></td><td style="text-align: right;padding-right:25px;width: 50%;">نوع التبرع </td> </tr>');

                
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=request.getParameter("Amount")%></td><td style="text-align: right;padding-right:25px;width: 50%;">مبلغ التبرع </td> </tr>');
                printwindow.document.write('<tr><td style="padding-left:15px;width: 50%;"><%=seciResponseDTO.getPaymentAmount()%></td><td style="text-align: right;padding-right:25px;width: 50%;" >اجمالى المبلغ المدفوع</td> </tr>');
		printwindow.document
				.write('<tr><th colspan="2">-----------------------------------</th></tr>');
		printwindow.document
				.write('<tr> <th colspan = "2" style = "text-align:center;font-size: 16px;" >شكرا لاستخدامك مصارى لخدمات الدفع الذكي</th> </tr >');
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
					<img src="img/SECI2.jpg" width="100" height="50"
					style="float: left;">
					<p align="right"><%=CONFIG.get_SECI(request.getSession())%>
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
					value="<%=request.getParameter("Amount")%>"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.getServiceCost(session)%>:
					</p>
				</td>
				<td><input type="text" name="ServiceCost"
					value="<%=request.getParameter("Fees")%>" readonly
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td>
					<p><%=CONFIG.getThetotalamountpaid(session)%>:
					</p>
				</td>
				<td><input type="text" name="TotalAmountPayable"
					value="<%=request.getParameter("totalAmount")%>" readonly
					style="background-color: #EDEDED;"></td>
			</tr>
			<tr>
				<td style="border: none">
					<p><%=CONFIG.getIsDeddued(session)%>:
					</p>
				</td>

				<td><input type="text" name="DedducedAmount"
					value="<%=request.getParameter("deductedAmount")%>" readonly
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
				<td><label><%=date1%></label></td>
			</tr>

			<tr>
				<td colspan="2" class="secondTD"><input type="submit"
					value="<%=CONFIG.getPrinting(session)%>" id="submitbutton"
					onclick="setDivPrint()" /></td>
			</tr>
			<tr style="display: none">
				<th colspan="2"><img src="./img/Masary.jpg" width="50"
					height="50"></th>
			</tr>
		</table>
		<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</body>
</html>