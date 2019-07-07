<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.integration.dto.TEData_Inquiry_Response"%>
<%@page import="com.masary.integration.dto.TEData_Inquiry_Request"%>
<%@page import="com.masary.integration.dto.TedataDenominationRepresentation"%>


<%
    request.setCharacterEncoding("UTF-8");
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	if (role == null) {
		response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
		return;
	}

	TEData_Inquiry_Response tedataInquiryResponse = (TEData_Inquiry_Response) request
			.getAttribute("tedataInquiryResponse");	

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
<script type="text/javascript">
	function gotoHome() {
		document.getElementById("homeForm").submit()
	}
</script>


<title><%=CONFIG.get_TEDataName(session)%></title>
<style>
#notice {
	background: transparent;
	border-top: transparent !important;
	border-left: transparent !important;
	border-right: transparent !important;
	border-bottom: transparent !important;
}
</style>

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

	<form id="form" name="TEDataConfirmationPage"
		action="TEDataReceiptController" method="POST">
		<input type="hidden" name="validationId" id="validationId"
			value="<%=tedataInquiryResponse.getValidationId()%>" />

		<div class="content_body">
			<fieldset style="width: 70%; direction: rtl;" align="right">

				<table border="1" width="100%">
					<th><%=CONFIG.get_TEDataConfirmedData(session)%></th>
					<th><%=CONFIG.getAbuElRish_Data(session)%></th>
					<tr>
						<td>

							<p align="right"><%=CONFIG.getCustomerName(session)%>:<input
									name="clientName" type="text" id="clientName"
									value="<%=tedataInquiryResponse.getCustomerName()%>"
									style="float: left; background-color: #EDEDED;">
							</p>

							<p align="right"><%=CONFIG.get_TEDataSubscriberNumber(session)%>:<input
									name="SubscriberNumber" readonly type="text"
									id="SubscriberNumber" maxlength="10"
									value="<%=tedataInquiryResponse.getAdslNumber()%>"
									style="float: left; background-color: #EDEDED;">
							</p>

							<p align="right">
								<%=CONFIG.get_TEDataCategory(session)%>
								: <input
									name="denominationId" readonly type="text" id="<%=tedataInquiryResponse.getDenominationId()%>"
									value="<%=request.getParameter("quota")%>"
									style="float: left; background-color: #EDEDED;">
							
							</p>

							<p align="right"><%=CONFIG.get_TEDataAmount(session)%>:<input
									name="Amount" readonly type="text" id="Amount"
									value="<%=tedataInquiryResponse.getAmount()%>"
									style="float: left; background-color: #EDEDED;">
							</p>

						</td>
						<td>
							<p align="right"><%=CONFIG.getFees(session)%>:<input
									name="Fees" readonly type="text" id="Fees" maxlength="20"
									value="<%=tedataInquiryResponse.getAppliedFees()%>"
									style="float: left; background-color: #EDEDED;">
							</p>

							<p align="right"><%=CONFIG.getCommession(session)%>:<input
									name="Commession" readonly type="text" id="Commession"
									maxlength="20"
									value="<%=tedataInquiryResponse.getMerchantCommission()%>"
									style="float: left; background-color: #EDEDED;">
							</p>
							<p align="right"><%=CONFIG.getDeductedAmount(session)%>:<input
									name="deductedAmount" readonly type="text" id="deductedAmount"
									value="<%=tedataInquiryResponse.getTransactionAmount()%>"
									maxlength="11" style="float: left; background-color: #EDEDED;">
							</p>
							<p align="right"><%=CONFIG.get_TEDataRequiredAmount(session)%>
								:<input type="text" name="totalAmount"
									value="<%=tedataInquiryResponse.getToBepaid()%>" readonly
									tabindex="6" id="totalAmount"
									value="<%=tedataInquiryResponse.getToBepaid()%>"
									style="background-color: #EDEDED; float: left;" />
							<div></div>
							</p>
						</td>
					</tr>
					<tr>

						<td colspan="3">
							<div align="center">
								<input type="submit" name="btnSubmit" tabindex="4"
									id="buttonSubmit"
									value="<%=CONFIG.get_TEDataPayment(request.getSession())%>"
									onclick="" style="float: right;"> <input type="button"
									name="btnSubmit" tabindex="4" id="buttonSubmit"
									value="<%=CONFIG.getBack(request.getSession())%>"
									onclick="history.go(-1);" align="center"> <input
									type="button" name="btnSubmit" tabindex="4" id="buttonSubmit"
									value="<%=CONFIG.get_TEDataCancel(request.getSession())%>"
									onclick="gotoHome()" style="float: left;">
							</div>
						</td>

					</tr>
				</table>
				<details open="open"> <summary> </summary> </details>
			</fieldset>
		</div>
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