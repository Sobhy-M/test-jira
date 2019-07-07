<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.integration.dto.GoBusInquiryRepresentation"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.controlers.GoBus.GoBusProperties"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
if (role == null) {
    response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
    return;
}

GoBusInquiryRepresentation inquiryRepresentation = (GoBusInquiryRepresentation) request.getAttribute("inquiryRepresentation");

%>


<html>


	<link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=GoBusProperties.getGoBus(session)%></title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  function gotoHome(){
	  document.getElementById("homeForm").submit()
  }
  
  </script>
    </head>


    <BODY class="body"  >
        <div>
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>  

            <form name="AddCustomer" action="GoBusPaymentController" method="POST">
            <input type="hidden" name="validationId" value="${requestScope.inquiryRepresentation.validationId}">
            <div class="content_body">
            
                        <table style="width: 75%"> 
            
                <th colspan="2"><%=CONFIG.getINFO_Required(request.getSession())%></th>
                <th><%=CONFIG.getMerchantCommession(request.getSession())%></th>
                
                
                <tr>
                    <td colspan="2">
                    
                     <table
							style="width: 100%; border: none; border-color: transparent;">
							
							
							<tr style="background: none; border-color: transparent;">
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getClientName(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input
									id="custMobileConfirmation" maxlength="12" autocomplete="off"
									type="text" name="<%=GoBusProperties.getClientName(session)%>"
									readonly style="background-color: #EDEDED; float: left;"
									value="${requestScope.inquiryRepresentation.customerName}">

								</td>

							</tr>
							
							
							<c:if test="${not empty requestScope.inquiryRepresentation.goDate}">
							
							<tr style="background: none; border-color: transparent;">
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getTripKind(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input
									type="text" readonly style="background-color: #EDEDED; float: left;"
									value="<%=GoBusProperties.getGoTrip(session)%>">
									
								</td>

							</tr>

							<tr>
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getTripDate(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input
									type="text"	readonly style="background-color: #EDEDED; float: left;"
									value="${requestScope.inquiryRepresentation.goDate}">

								</td>

							</tr>


							<tr style="background: none; border-color: transparent;">
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getTripDEtails(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><textarea
										readonly style="background-color: #EDEDED; float: left;width: 151px"
										>${requestScope.inquiryRepresentation.goDetails}</textarea></td>

							</tr>

							<tr>
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getSeatNum(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input
									 type="text" tabindex="3" readonly style="background-color: #EDEDED; float: left;"
									value="${requestScope.inquiryRepresentation.goSeatsNumber}">
								</td>

							</tr>
							
							</c:if>
							
							
							<c:if test="${not empty requestScope.inquiryRepresentation.backDate}">
							
							
							<tr style="background: none; border-color: transparent;">
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getTripKind(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input									
									type="text" readonly style="background-color: #EDEDED; float: left;"
									value="<%=GoBusProperties.getBackTrip(session)%>">
								</td>

							</tr>

							<tr>
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getTripDate(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input
									type="text" readonly style="background-color: #EDEDED; float: left;"
									value="${requestScope.inquiryRepresentation.backDate}">
								</td>

							</tr>


							<tr style="background: none; border-color: transparent;">
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getTripDEtails(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><textarea
										id="custMobileConfirmation" type="text" readonly
										style="background-color: #EDEDED; float: left;;width: 151px"
										>${requestScope.inquiryRepresentation.backDetails}</textarea></td>

							</tr>

							<tr>
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getSeatNum(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input
									id="custMobileConfirmation"  type="text"
									 tabindex="3"
									readonly style="background-color: #EDEDED; float: left;"
									value="${requestScope.inquiryRepresentation.getBackSeatsNumber()}">

								</td>

							</tr>
											
							</c:if>
							
						 <tr>
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getReservationAmount(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input
									type="text"tabindex="3" readonly style="background-color: #EDEDED; float: left;"
									value="${requestScope.inquiryRepresentation.jurneyAmount}">

								</td>
						</tr>
							
							<tr>
								<td style="background: none; border-color: transparent;"><%=CONFIG.getFees(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input
									type="text" tabindex="3"
									readonly style="background-color: #EDEDED; float: left;"
									value="${requestScope.inquiryRepresentation.appliedFees}">

								</td>

							</tr>
							
							<tr>
								<td style="background: none; border-color: transparent;"><%=GoBusProperties.getReservationAmountAll(session)%>
								</td>
								<td style="background: none; border-color: transparent;"><input
									type="text" tabindex="3" readonly style="background-color: #EDEDED; float: left;"
									value="${requestScope.inquiryRepresentation.toBepaid}">

								</td>

							</tr>

						</table>
                                            
                    </td>
                    
                    <td>
                        <p align="right"><%=CONFIG.getCommession(request.getSession())%> :<input type="text" name="commession" value="${requestScope.inquiryRepresentation.merchantCommission}"   tabindex="6" id="Commession" readonly style="background-color: #EDEDED; float: left;"/><div></div></p> 
                        <p align="right"><%=CONFIG.getDeductedAmount(request.getSession())%> :<input type="text" name="DeductedAmount"  value="${requestScope.inquiryRepresentation.transactionAmount}"   readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                        </br>
                    </td>
                </tr>



                <tr>
                    <td> <input type="submit" name="btnSubmit" tabindex="4" id="buttonSubmit"  value="<%=CONFIG.getpayment(request.getSession())%>" onclick=""></td>
                    <td> <input type="button" name="btnSubmit" tabindex="4" id="buttonSubmit"  value="<%=CONFIG.getEditting(request.getSession())%>" onclick="history.go(-1);"></td>
                    <td> <input type="button" name="btnSubmit" tabindex="4" id="buttonSubmit"  value="<%=GoBusProperties.getCancel(request.getSession())%>" onclick="gotoHome()"></td>
                </tr>
                </table>
            </div><!-- End of Table Content area-->
        </form>
        
        <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%>  method="post"  name="homeForm" id="homeForm">
			</form>
			
    </div><!-- End content body -->
    <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
