<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.masary.controlers.CashatMerchant.CashatMerchantProperties"%>
<%@page import="com.masary.common.CONFIG"%>
<%
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	if (role == null) {
		response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
		return;
	}
%>

<html>
<head>
<link href="https://cdn.e-masary.net/app/img/style${lang}.css"
	rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function gotoHome() {
		document.getElementById("homeForm").submit()
	}
</script>
<title><%=CashatMerchantProperties.Param_CashatMerchant_ServiceName%></title>
<style>
input[type=text] {
	width: 50%;
	background-color: #EDEDED;
	float: right;
	font-size: 16px;
}

p {
	font-size: 13.7px;
	font-weight: bold;
}

input.btn {
	font-size: 16px;
}
</style>
</head>
<body class="body">
	<div>
		<c:choose>
			<c:when test="${lang== ''}">
				<jsp:include page="../../img/menuList.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="../../img/menuListar.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="content_body">
		<form action="CashatMerchantPayment" method="POST">
		<input type="hidden" name="validationId" id="validationId"
			value="${cashatInquiryResponse.validationId}" />

			<fieldset   style="direction: rtl;" align="right">
				<table border="1" style="width: 100%">
					<th style="text-align: center"><%=CONFIG.getAgentPaymentInfo(request.getSession())%></th>
					<th style="text-align: center"><%=CONFIG.ESED_Confirmation_Commission_Label%></th>
					<tr>
						<td >
							<p align="right">
								<%=CashatMerchantProperties.Param_CashatMerchant_CompanyNameLabel%>
								<input type="text" readonly value="${cashatInquiryResponse.companyNameAr}"
									style="background-color: #EDEDED; float: left; width: 50%" />
							</p>

							<p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_DelegateNameLabel%>
								<input type="text" name="delegateName" readonly value="${cashatInquiryResponse.repName}"
									style="background-color: #EDEDED; float: left; width: 50%">
							</p>

							<p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_toBePaidLabel%>
								<input type="text" name="toBePaid" readonly maxlength="9" value="${cashatInquiryResponse.toBepaid}"
									style="background-color: #EDEDED; float: left; width: 50%" >
							</p>
						</td>
						<td >
							<p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_CommissionLabel%>
								<input type="text" value="${cashatInquiryResponse.merchantCommission}" readonly
									style="background-color: #EDEDED; float: left; width: 50%" />
							</p>
							<p align="right"><%=CashatMerchantProperties.Param_CashatMerchant_DeductedAmountLabel%>
								<input type="text" maxlength="9" value="${cashatInquiryResponse.transactionAmount}" readonly
									style="background-color: #EDEDED; float: left; width: 50%" />
							</p>
						</td>
					</tr>

					<tr>

						<td colspan="3">
							<div align="center">
								<input type="submit" name="btnSubmit" tabindex="4"
									id="buttonSubmit"
									value="<%=CONFIG.getConfirmation(request.getSession())%>"
									onclick="" style="float: right;"> <input type="button"
									name="btnSubmit" tabindex="4" id="buttonSubmit"
									value="<%=CashatMerchantProperties.Action_CashatMerchant_EditLabel%>"
									onclick="history.go(-1);" align="center"> <input
									type="button" name="btnSubmit" tabindex="4" id="buttonSubmit"
									value="<%=CashatMerchantProperties.Action_CashatMerchant_CancelLabel%>"
									onclick="gotoHome()" style="float: left;">
							</div>
						</td>

					</tr>

				</table>
			</fieldset>


		</form>
		<form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="post"
		name="homeForm" id="homeForm"></form>
	</div>
	<!-- End of Table Content area-->

	</div>
	<!-- End content body -->

	<div style="clear: both;">&nbsp;</div>
	<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
	</div>
	<!-- End of Main body-->
</body>
</html>