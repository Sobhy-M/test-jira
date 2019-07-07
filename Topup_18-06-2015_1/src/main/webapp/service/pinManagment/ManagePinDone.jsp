<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.masary.common.CONFIG"%>
<%@page
	import="com.masary.controlers.pinMangamnet.PinManagmentProperties"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

		var pinCode = document.getElementById("homeForm").value;
	}

	function validateOnlyNumber(str) {
		var binRegex = /^\d{4}$/;

		if (binRegex.test(str)) {
			return true;
		}
		return false;
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

	<form name="AddCustomer" action="<%=CONFIG.APP_ROOT + role + ".jsp"%>"
		method="POST">
		<div class="content_body">
			<div>
				<h4>
					<c:choose>
						<c:when test="${param.newStatus=='LOCKED'}">
							<%=PinManagmentProperties.getLockPinDone(request.getSession())%>
						</c:when>
						<c:otherwise>
							<%=PinManagmentProperties.getUnLockPinDone(request.getSession())%>
						</c:otherwise>
					</c:choose>

				</h4>
			</div>
			<div>
				<input type="submit" value="<%=CONFIG.GetOk(session)%>"
					id="submitbutton" onclick="goHome()" />

			</div>

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
