<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="Bundle">


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=CONFIG.get_beInSports(session)%></title>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
    </head>
   <%if(request.getAttribute("action").equals("reciept")) {%>
    <script>
        function setDivPrint() {
           
                    var printwindow = window.open('', '', 'left=0,top=0,width=' + 600 + ',height=' + 600 + ',toolbar=0,scrollbars=0,status=0');
                    printwindow.document.write('<table cellspacing="5" frame="box" style="font-size: 20px; font-weight: 600; width: 5%;direction: rtl;" >');
                    printwindow.document.write('<tr><td colspan="2" style = "text-align:center;"><img src="img/Masary.jpg"  width="200" height="60" ></td></tr>');
                    printwindow.document.write('<tr><td style = "text-align:center;"><fmt:message key="Title_CNE_Home"/> </td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="CNETransactionIdAr"/>: ${paymentResponse.transactionId}</td></tr>');

                    printwindow.document.write('<tr><td><fmt:message key="CNEDateAr"/>: <fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm"/>        </td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="CNEMerchantAr"/>:  ${paymentResponse.accountId} </td></tr>');
                    printwindow.document.write('<tr><th  colspan = "2">________________________________________ </th></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="CNEClientNameAr"/>: ${paymentResponse.clientName}</td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="CNEPackageNameAr"/>: ${paymentResponse.programName}</td></tr>');
                 
                    printwindow.document.write('<tr><td><fmt:message key="CNEAmountAr"/>: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${paymentResponse.billAmount}" /> </td></tr>');
                    printwindow.document.write('<tr><td><fmt:message key="CNETotalAmountAr"/>: ${paymentResponse.appliedFees}</td></tr>');
                    printwindow.document.write('<tr><th  colspan = "2">------------------------------------------------------------</th></tr>');
                    printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size: 16px;" ><p><fmt:message key="messagePrint1"/><p></th> </tr >');
                    printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size: 16px;" ><p><fmt:message key="messagePrint2"/><p></th> </tr >');
                    printwindow.document.write('<tr><th colspan = "2" style = "text-align:center;font-size:16px; "><p><fmt:message key="messagePrint3"/><p></th> </tr> </table> ');
                    printwindow.location.reload();
                    printwindow.document.close();
                    printwindow.focus();
                    printwindow.print();
                    printwindow.close();
                    document.getElementById("paymentForm").submit();  
        }

    </script>
    <body onload="setDivPrint()" >
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
         <form id="form" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" >
                <div>
                    <table> 
                        <tr>
                            <td>
                                <fmt:message key="CNEIReprintReprint"></fmt:message>
                                </td>
                            </tr>

                        </table>
                    </div>
                    <img src="./img/Masary.jpg" style="visibility: hidden;"/>
                </form>
        
        <%
	} else if(request.getAttribute("action").equals("confirmation")){

		%>
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
            <form id="HOMEFORM" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" style="display: none;" ></form>
            
            <form name="doinquiry" action="CNEPaymentController" method="POST" >

                <div> 
                    <input type="hidden" name="programName" id="programName"
			 value="${inquiryResponse.programName}" />	
		
		<input type="hidden" name="inqueryAmount" id="inqueryAmount"
			 value="${inquiryResponse.amount}" />	
                    <input type="hidden" id="validationId" name="validationId"  value="${inquiryResponse.validationId}"/>


                    <table cellspacing="10" style="font-size: 12px; font-weight: 900;"> 
                        
                         <tr><td style="width: 50% ; padding-right: 30px;text-align: right;" colspan="2 ">  &nbsp; <fmt:message key="CNEsubscribeAr"/>  &nbsp;<fmt:message key="Title_CNE_Home"/>  &nbsp;<fmt:message key="CNEPackageAr"/>  ${inquiryResponse.programName}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: center;" colspan="2 "><fmt:message key="CNEInfo"/>  </td></tr>

                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;" ><fmt:message key="CNESubscriberNameAr"/>  </td><td style="padding-left: 10px;">  ${inquiryResponse.clientName} </td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;" ><fmt:message key="CNE_MobileNumber"/> </td><td style="padding-left: 10px;">  ${inquiryResponse.msisdn} </td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="CNEPackageAr"/></td><td style="padding-left: 10px;">  ${inquiryResponse.programName} </td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="CNEPackagedateAr"/></td><td style="padding-left: 10px;"> ${inquiryResponse.coverageEndDate}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="CNERequiredAmountAr"/></td><td style="padding-left: 10px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${inquiryResponse.amount}"/></td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="CNECosteAr"/></td><td style="padding-left: 10px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${inquiryResponse.appliedFees}"/></td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="CNEcommissionAr"/></td><td style="padding-left: 10px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${inquiryResponse.merchantCommission}"/></td></tr>
                       <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="CNEDeductedAmountAr"/></td><td style="padding-left: 10px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${inquiryResponse.transactionAmount}"/></td></tr>
                    <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="CNETotalAmountAr"/></td><td style="padding-left: 10px;"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${inquiryResponse.toBepaid}"/></td></tr>

                        <tr><td colspan="2" style="text-align: center">
                                <input type="submit" id="print" value=" <fmt:message key="CNEconfirmAr"/>" class="Btn"/>
                                <input style="float: left" type="button"  value="<fmt:message key="back"/>"  onclick="history.go(-1);" class="btn">
                            </td></tr>


                    </table>

                </div>
            </form>
          <%} %>


    

                <img src="./img/Masary.jpg" style="visibility: hidden;"/>
           
            <!--</form>-->
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->  
    </body>
</html>
</fmt:bundle>
