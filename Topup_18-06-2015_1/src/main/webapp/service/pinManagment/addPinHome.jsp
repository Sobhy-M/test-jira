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

		var pinCode = document.getElementById("newPinCodehidden").value;
		var pinCodeConfirm = document.getElementById("newPinCodeConfirmationHidden").value;
		
		if(pinCode !== pinCodeConfirm){
			document.getElementById("errorMessage").innerHTML = "<%=PinManagmentProperties.getPinAndConfirmationNotEqual(session)%>";
			return false;
		}
		return true;
	}

	function onKeyUpNewPin(te) {

		var val = document.getElementById("newPinCode").value.replace(/\x2a/g,
				'%');
		alert(val);

	}

	function newPinCodeMask() {

		if(document.getElementById("newPinCode").value === "" || document.getElementById("newPinCode").value.length === 0 ){
			document.getElementById("newPinCodehidden").value = "";
		}
		
		var textLenght = document.getElementById("newPinCode").value.length;
		
		var textVal = document.getElementById("newPinCodehidden").value;
		
		textVal+=document.getElementById("newPinCode").value.charAt(textLenght-1);
		
		document.getElementById("newPinCodehidden").value = textVal;
		
		var val = document.getElementById("newPinCode").value.replace(/[\S]/g,'*');
		
		document.getElementById("newPinCode").value = val;
	}
	
	function newPinCodeConfirmationMask() {

		if(document.getElementById("newPinCodeConfirmation").value === "" || document.getElementById("newPinCodeConfirmation").value.length === 0 ){
			document.getElementById("newPinCodeConfirmationHidden").value = "";
		}
		
		var textLenght = document.getElementById("newPinCodeConfirmation").value.length;
		
		var textVal = document.getElementById("newPinCodeConfirmationHidden").value;
		
		textVal+=document.getElementById("newPinCodeConfirmation").value.charAt(textLenght-1);
		
		document.getElementById("newPinCodeConfirmationHidden").value = textVal;
		
		var val = document.getElementById("newPinCodeConfirmation").value.replace(/[\S]/g,'*');
		
		document.getElementById("newPinCodeConfirmation").value = val;
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

	<form name="AddCustomer" action="CreatePinConfirmation" method="POST">

		<input type="hidden" maxlength="4" name="newPinCodehidden"
			id="newPinCodehidden" />
			
		<input type="hidden" maxlength="4" name="newPinCodeConfirmationHidden"
			id="newPinCodeConfirmationHidden" />	

		<fieldset style="width: 50%; direction: rtl;" align="right">

			<div id="errorMessage"></div>
			<table style="width: 100%">
				<tr>
					<th><%=PinManagmentProperties.getInsertPinCode(session)%> *</th>
					<td>
						<div class="requriedclass">
							<div>
								<input onkeyup="newPinCodeMask()" type="text" maxlength="4"
									autocomplete="off" name="newPinCode" id="newPinCode" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th><%=PinManagmentProperties.getInsertPinCodeConfirmation(session)%>*</th>
					<td>
						<div class="requriedclass">
							<div>
								<input type="text"onkeyup="newPinCodeConfirmationMask()"  maxlength="4" autocomplete="off"
									name="newPinCodeConfirmation" id="newPinCodeConfirmation" />
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

			<details open="open"> <%=PinManagmentProperties.getCreatePinHint(session)%></br>
			</details>
		</fieldset>

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
