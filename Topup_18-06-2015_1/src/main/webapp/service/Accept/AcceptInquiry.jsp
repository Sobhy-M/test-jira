

<%@page import="com.masary.utils.BuildUUID"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.util.Date"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.masary.XMLGregorianCalendarConverter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.integration.dto.AcceptInquiryResponse"%>



<%

session = request.getSession();

request.setCharacterEncoding("UTF-8");
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	if (role == null) {
		response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
		return;
	}


String operationType=(String)session.getAttribute("operationType");
AcceptInquiryResponse acceptInquiryResponse = (AcceptInquiryResponse)request.getAttribute("inquiryResponse");

%>
<script>
    function goBack() {
        window.history.back();
    }
</script>
<script type="text/javascript">
	function gotoHome() {
		document.getElementById("homeForm").submit()
	}
</script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=operationType%></title>

    </head>
    <body class="body" >

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <font style="color: red; font-size: 15px;">${ErrorCode}</font>

    <form name="acceptPayment" action="AcceptPaymentController" method="POST" >
         <input type="hidden" name="operationtype" id="operationtype" value="<%=operationType%>" />
         <input type="hidden" name="validationId" id="validationId" value="<%=acceptInquiryResponse.getValidationId()%>" />

        <div class="content_body"  >
            <fieldset style="width:50%; direction: rtl;" align="right">  
             <legend align="right" ><font size="3"><%=operationType + "  "%> - <%="  " + CONFIG.get_acceptTransactionData(session)%></font></legend>               
            
                <table  border="2" width="100%">
                <th></th>
                    <tr>
                        <td width="90%">
                         <p align="right"><%=CONFIG.get_ReferenceNumber(session)%> : 
                                <input type="text"  size="25" name="ReferenceNumber" readonly tabindex="1" id="ReferenceNumber" value="<%=acceptInquiryResponse.getInquiryReferenceId() %>" style="background-color: #EDEDED; float: left;"/>
                            </p>
                           
                            <p align="right"><%=CONFIG.get_acceptCustomerName(session)%> : 
                                <input type="text"  size="25" name="Customer_name" readonly tabindex="1" id="Customer_name" value="<%=acceptInquiryResponse.getClientName() %>" style="background-color: #EDEDED; float: left;"/>
                            </p>
                             <p align="right"><%=CONFIG.get_acceptTransactionData(session)%> :
                                <input   size="25" readonly tabindex="1" id="transactionData" type="text"  name="transactionData"  value="<%=acceptInquiryResponse.getOrderName() %>" style="background-color: #EDEDED; float: left;"/>
                            </p>                      
                           
                            <p align="right"><%=CONFIG.get_acceptTransactionType(session)%> :
                                <input   size="25" readonly tabindex="1" id="transactionType" type="text"  name="transactionType"  value="<%=acceptInquiryResponse.getBillerName() %>" style="background-color: #EDEDED; float: left;"/>
                            </p>                      
                           <p align="right"><%=CONFIG.get_acceptAmount(session)%> : 
                                <input type="text"  size="25" name="amount" readonly tabindex="1" id="amount" value="<%=acceptInquiryResponse.getDueAmount() %>" style="background-color: #EDEDED; float: left;"/>
                            </p>
                            <p align="right"><%=CONFIG.get_acceptAppliedFees(session)%> :
                                <input   size="25" readonly tabindex="1" id="appliedFees" type="text"  name="appliedFees"  value="<%=acceptInquiryResponse.getAppliedFees() + acceptInquiryResponse.getTax() %>" style="background-color: #EDEDED; float: left;"/>
                            </p>                      
                           <p align="right"><%=CONFIG.get_acceptTotalAmount(session)%> : 
                                <input  size="25" type="text" name="totalAmount" readonly tabindex="1" id="totalAmount" value="<%=acceptInquiryResponse.getToBepaid() %>" style="background-color: #EDEDED; float: left;"/>
                            </p>
                           
                              </td>

                    </tr>
                  
                    <tr>
                        <td colspan="3">
                        <div align="center">
                            <input type="submit" name="btnSubmit"  tabindex="4" id="buttonSubmit" value="<%=CONFIG.get_TEDataPayment(session)%>"     class="Btn"  onclick=""                 style="float: right;" >
                            <input type="button" name="btncancel"  tabindex="4" id="buttonSubmit" value="<%=CONFIG.get_AcceptCancel(session) %>"     class="Btn"  onclick="gotoHome()"       align="center">
                            <input type="button" name="Back"       tabindex="4" id="buttonSubmit" value="<%=CONFIG.getBack(session)%>"               class="Btn"  onclick="history.go(-1);"  style="float: left;" >
                   		</div>
                        </td>
                            
                   

                    </tr>
                </table>
     <details open="open">
                    <summary></summary>
برجاء التأكد من بيانات العمليه قبل تأكيد الدفع 
                </details>
            </fieldset> 
        </div><!-- End of Table Content area-->
    </form>
    <form action=<%=CONFIG.APP_ROOT + role + ".jsp"%> method="post"
		name="homeForm" id="homeForm"></form>
</div><!-- End content body -->

<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>