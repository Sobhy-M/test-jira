<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.integration.dto.TEData_Inquiry_Response"%>

<% 
    request.setCharacterEncoding("UTF-8");
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	if (role == null) {
		response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
		return;
	}

	TEData_Inquiry_Response tedataInquiryResponse = (TEData_Inquiry_Response) request.getAttribute("tedataInquiryResponse");

%>
<link
	href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css"
	rel="stylesheet" type="text/css">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="img/CheckForms.js"></script>
<script src="./js/TedataJS/tedata.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="./js/AddNewWalletJS/notify.js"></script>

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
<script>

   function loadNewValues() {
		 document.getElementById("category").value="";
		 document.getElementById("amount").value="";

		 


            }

   </script>


<script>


function onchangeValue(id){
		
	
	var amount = document.getElementById(id).value;
	document.getElementById("amount").value = amount;
	var quota = $("#category").children(":selected").attr("id");
	document.getElementById("quota").value = quota;
	
}
	
</script>
</head>

<body class="body" onload="loadNewValues()">
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

	<form id="form" name="TEDataHomePage" action="TEDataConfirmationController"
		method="POST">
<input type="hidden" name="tedataDenomination" id="tedataDenomination"
			value="${tedataDenominationRepresentation}" />
			<input type="hidden" name="quota" id="quota"
			 />
			
		<div class="content_body">
			<fieldset style="width: 30%; direction: rtl;" align="right">
				<legend align="right">
					<font size="2" style="font-weight: bold"><%=CONFIG.get_TEDataName2(session)%></font>
				</legend>

				<table border="1" width="100%">
					<tr>
						<td >
						<p align="right"><%=CONFIG.get_TEDataSubscriberNumber(session)%>:<input
									name="SubscriberNumber" required type="text" id="SubscriberNumber"
									maxlength="20" style="float: left">
							</p>
							 <p align="right" > <%=CONFIG.get_TEDataCategory(session)%> :
                                    <select  id="category" required name="category"onchange="onchangeValue(this.id)" >
                                        <option value=""  selected ><%=CONFIG.get_TEDataChooseValue(session)%></option>
                                        <c:forEach var="menu" items="${tedataDenominationRepresentation}">
                                            <option id="${menu.quota}"  value="${menu.amount}" >${menu.quota}</option>
                                            
                                        </c:forEach>
                                    </select>
                                </p>
							
							<p align="right"><%=CONFIG.get_TEDataAmount(session)%>:<input
									name="amount" type="text"
									id="amount"  disabled value=""style="float: left; background-color: #EDEDED;">
							</p>
							
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" name="btnSubmit" tabindex="4"
							value="<%=CONFIG.get_TEDataInquiry(session)%>"
							onclick="return formSubmit()" class="Btn"></td>
						
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