<%-- 
    Document   : LongLifeEgyptConfirm
    Created on : Mar 15, 2016, 11:37:21 AM
    Author     : Masary
--%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=CONFIG.get_SECI(session)%></title>
<script type="text/javascript" src="img/CheckForms.js"></script>
<link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />

<script type="text/javascript">
	function disableDoubleSubmission() {
		document.getElementById("submitbutton").disabled = true;
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
	<form action="SECIReceipt" method="POST" style="font-weight: bold"
		onsubmit="disableDoubleSubmission()">
		<input type="hidden" name="seciid" value="<%=new String(request.getParameter("seciid").toString().getBytes("ISO-8859-1"), "utf-8") %>">
		<table style="width: 70%; margin-left: auto; margin-right: auto">
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


				<tr class="secondTD">
					<td colspan="2" class="secondTD">
						<table>
							<tr>
								<td
									style="border: none; background-color: transparent; padding: 0px"
									class="secondTD">
									<p><%=CONFIG.getWillDonateTO(session)%></p>
								</td>
								<td
									style="border: none; background-color: transparent; padding: 0px"
									class="secondTD"><label>&nbsp;<%=CONFIG.get_SECIDescription(request.getSession()) + "  "  + new String(request.getParameter("seciid").toString().getBytes("ISO-8859-1"), "utf-8") %></label></td>									
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<p><%=CONFIG.getTheDonationValue(session)%>:
						</p>
					</td>
					<td>
						<div>
							<div>
								<input type="text" name="Amount"
									value="<%=request.getParameter("Amount")%>" readonly
									style="background-color: #EDEDED;">
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<p><%=CONFIG.getServiceCost(session)%></p>
					</td>
					<td><input type="text" name="Fees"
						value="<%=request.getParameter("Fees")%>" readonly
						style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p><%=CONFIG.getCommessionMaan(session)%></p>
					</td>
					<td><input type="text" name="Commession"
						value="<%=request.getParameter("Commession")%>" readonly
						style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p><%=CONFIG.getWillbededucted(session)%>:
						</p>
					</td>
					<td><input type="text" name="deductedAmount"
						value="<%=request.getParameter("deductedAmount")%>" readonly
						style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<p><%=CONFIG.getTotalAmountPayable(session)%></p>
					</td>
					<td>
						<div>
							<div>
								<input type="text" name="totalAmount"
									value="<%=request.getParameter("totalAmount")%>" readonly
									style="background-color: #EDEDED;">
								<div></div>
					</td>

				</tr>
				<tr>
					<td>
						<p><%=CONFIG.getDontaorPhoneNumber(session)%>:
						</p>
					</td>
					<td><input type="text" readonly name="PhoneNumber"
						value="<%=request.getParameter("PhoneNumber")%>"
						style="background-color: #EDEDED;"></td>
				</tr>
				<tr>
					<td>
						<div>
							<div>
								<input type="submit" value="<%=CONFIG.getGo_SECI(session)%>"
									id="submitbutton" onclick="" />
							</div>
						</div>
					</td>
					<td>
						<div>
							<input type="button" value="<%=CONFIG.getBack(session)%>"
								id="submitbutton" onclick="history.go(-1);" />
						</div>
					</td>

				</tr>
			</table>
			</form>
			<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</body>
</html>