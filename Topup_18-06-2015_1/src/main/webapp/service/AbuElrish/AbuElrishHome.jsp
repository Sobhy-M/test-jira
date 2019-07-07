<%@page import="java.util.HashMap"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	if (role == null) {
		response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
		return;
	}
	session = request.getSession();
%>
<link
	href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css"
	rel="stylesheet" type="text/css">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="img/CheckForms.js"></script>
<script src="./js/AbuElreshJS/abuElResh.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="./js/AddNewWalletJS/notify.js"></script>

<title><%=CONFIG.get_AbuElRish(session)%></title>
<style>
#notice {
	background: transparent;
	border-top: transparent !important;
	border-left: transparent !important;
	border-right: transparent !important;
	border-bottom: transparent !important;
}
</style>

<script>
	$(document).ready(
			function() {
				$("#Amount").keyup(
						function() {
							var servicId = 599;
							$.ajax({
								url : 'RatePlanCommissions',
								data : {
									SERVICE_ID : 599,
									AMOUNT : $('#Amount').val()
								},
								type : 'get'
							}).done(
									function(responseData) {
										var arr = responseData.split('-');
										$('#Fees').val(arr[6]);
										$('#Commession').val(arr[3]);
										if (arr[0] !== null && arr[0] !== ""
												&& arr[1] !== null
												&& arr[1] !== "") {
											$('#totalAmount').val(arr[11]);
											$('#deductedAmount').val(
													Number(arr[8]));
										} else {
											$('#totalAmount').val("");
											$('#deductedAmount').val("");
										}
									}).fail(function() {
								console.log('Failed');
							});
						});
			});
</script>

</head>

<body class="body" onload="">
	<div>
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

	<font style="color: red; font-size: 15px;">${ErrorCode}</font>

	<form id="form" name="AbuElRishHomePage"
		action="AbuElrishConfirmationController" method="POST">

		<div class="content_body">
			<fieldset style="width: 70%; direction: rtl;" align="right">


				<legend align="right">
					<font size="5"></font><img src="img/aboelrishlogo.jpeg" width="120"
						height="40">&nbsp;<img src="img/CashIn.ico" width="30"
						height="30">
				</legend>


				<table border="1" width="100%">
					<th><%=CONFIG.getINFO_Required(session)%></th>
					<th><%=CONFIG.getAbuElRish_Data(session)%></th>
					<tr>
						<td>
							<p align="right"><%=CONFIG.getMenu_AbuElRish(session)%>: <select
									name="abuelrish" id="abuelrish">
									<c:forEach items="${donationTypesMap}" var="entry">
										<option value="${entry.key}">${entry.value}</option>
									</c:forEach>
								</select>
							</p>
							<p align="right"><%=CONFIG.get_PhoneNumberAbuElRish(session)%>:<input
									name="PhoneNumber" required type="text" id="PhoneNumber"
									maxlength="11" style="float: left">
							</p>
							<p align="right"><%=CONFIG.get_AmountDonationAbuElRish(session)%>:<input
									name="Amount" type="text" id="Amount" required maxlength="11"
									style="float: left">
							</p>
							<p style="color: red;">
								تنويه: <input id="notice" value="الحد الأدنى للتبرع 5 جنيه"
									readonly style="color: black; font-size: small;">

							</p>

						</td>
						<td>
							<p align="right"><%=CONFIG.getFees(session)%>:<input
									name="Fees" readonly type="text" id="Fees" maxlength="20"
									style="float: left; background-color: #EDEDED;">
							</p>
							<p align="right"><%=CONFIG.getCommession(session)%>:<input
									name="Commession" readonly type="text" id="Commession"
									maxlength="11" style="float: left; background-color: #EDEDED;">
							</p>
							<p align="right"><%=CONFIG.getDeductedAmount(session)%>:<input
									name="deductedAmount" readonly type="text" id="deductedAmount"
									maxlength="11" style="float: left; background-color: #EDEDED;">
							</p>
							<p align="right"><%=CONFIG.getTotalAmountManAbuElRish(session)%>
								:<input type="text" name="totalAmount" value="" readonly
									tabindex="6" id="totalAmount"
									style="background-color: #EDEDED; float: left;" />
							<div></div>
							</p>
						</td>
					</tr>
					<tr>
						<td><input type="submit" name="btnSubmit" tabindex="4"
							value="<%=CONFIG.get_Done_AbuElRish(session)%>"
							onclick="return formSubmit()" class="Btn"></td>
						<td><input type="button" name="btncancel" onclick="goBack()"
							tabindex="1" value="<%=CONFIG.getBackAr(session)%>" class="Btn"></td>
						</td>
					</tr>
				</table>
				<details open="open"> <summary> </summary> </details>
			</fieldset>
		</div>
		<!-- End of Table Content area-->
	</form>
	</div>
	<!-- End content body -->

	<div style="clear: both;">&nbsp;</div>
	<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
	</div>
	<!-- End of Main body-->
</body>
</html>