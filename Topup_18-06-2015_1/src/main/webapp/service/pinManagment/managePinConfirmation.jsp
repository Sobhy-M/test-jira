<%--
    Document   : AddCustomer.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.masary.common.CONFIG"%>
<%@page
	import="com.masary.controlers.pinMangamnet.PinManagmentProperties"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	if (role == null) {
		response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
		return;
	}
%>


<html>


<link
	href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css"
	rel="stylesheet" type="text/css">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title><%=PinManagmentProperties.getManageYourBalance(session)%></title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>

<script type="text/javascript">
	function gotoHome() {
		document.getElementById("homeForm").submit();
	}

	function validatePinCode() {

		var pinCode = document.getElementById("pinCode").value;
		
		if(pinCode === "" || pinCode.length !== 4){
			document.getElementById("errorMessage").innerHTML = "<%=PinManagmentProperties.getPleaseInsertPin(session)%>";
			return false;
		}
		return true;
	}
	
	function newPinCodeMask() {

		if (document.getElementById("pinCode").value === ""
				|| document.getElementById("pinCode").value.length === 0) {
			document.getElementById("pinCodehidden").value = "";
		}

		var textLenght = document.getElementById("pinCode").value.length;

		var textVal = document.getElementById("pinCodehidden").value;

		textVal += document.getElementById("pinCode").value
				.charAt(textLenght - 1);

		document.getElementById("pinCodehidden").value = textVal;

		var val = document.getElementById("pinCode").value.replace(/[\S]/g,
				'*');

		document.getElementById("pinCode").value = val;
	}
</script>

<BODY class="body">
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

	<form name="ManagePinAction" action="ManagePinAction" method="POST">

		<input type="hidden" maxlength="4" name="newStatus"
			value="${param.newStatus}" /> <input type="hidden" maxlength="4"
			name="pinCodehidden" id="pinCodehidden" />


		<div id="errorMessage"></div>
		<table style="width: 50%">
			<tr>
				<th><%=PinManagmentProperties.getTempPin(session)%> *</th>
				<td>
					<div class="requriedclass">
						<div>
							<input type="text" maxlength="4" autocomplete="off" onkeyup="newPinCodeMask()"
								name="pinCode" id="pinCode" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align: center"><input type="submit"
					name="btnSubmit"
					value="<%=PinManagmentProperties.getConfirm(session)%>"
					onclick="return validatePinCode()"></td>

				<td style="text-align: center"><input type="button"
					name="btnSubmit"
					value="<%=PinManagmentProperties.getCancel(session)%>"
					onclick="gotoHome()"></td>

			</tr>
		</table>


		<!-- End of Table Content area-->
	</form>


	<form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="post"
		name="homeForm" id="homeForm"></form>

	</div>
	<!-- End content body -->
	<div style="clear: both;">&nbsp;</div>
	<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
	</div>
	<!-- End of Main body-->
</body>
</html>
