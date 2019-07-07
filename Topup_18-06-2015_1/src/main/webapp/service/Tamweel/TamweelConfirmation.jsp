<%-- 
    Document   : TamweelConfirmation
    Created on : Feb 25, 2019, 11:58:43 AM
    Author     : omar.abdellah
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="com.masary.common.CONFIG"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%request.setCharacterEncoding("UTF-8");%>
<fmt:bundle basename="Bundle">
    <!DOCTYPE html>
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="Title_Tamweel_Info"></fmt:message></title>

                <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <script>


                function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }
                function validateMobileNum() {
                    var val = document.getElementById("MobileNumber").value;
                    if (/^01\d{9}$/i.test(val)) {

                        document.getElementById("MobileNumberDiv").innerHTML = "";
                        document.getElementById("MobileNumberDiv").disabled = true;
                        return true;
                    } else {
                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="errorValuMobileNum"></fmt:message>";


                        return false;
                    }
                }

            </script>
        </head>
        <body class="body">

            <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
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
        <div class="content_body"  >
            <form id="HOMEFORM" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" style="display: none;" ></form>
            <form  action="TamweelControllerPaymant" method="POST" >
                <div class="content_body"  >
                    <fieldset style="width:70%; direction: rtl;" align="right">  
                        <legend align="right" ><font size="5"><fmt:message key="Title_Tamweel_Print"/>${tamweelInquiryRepresentation.installmentType}</font></legend>               
                            <table  border="1" width="100%">
                                <th><fmt:message key="Tamweel_ConfirmationLabel"></fmt:message></th>
                            <th><fmt:message key="account"></fmt:message></th>
                                <tr>
                                    <td>
                                        <p align="right"><fmt:message key="TamweelAmountRequired"></fmt:message> : 
                                        <input type="text" readonly tabindex="1" value="${tamweelInquiryRepresentation.installmentAmount}" name="installmentAmount" id="installmentAmount" style="background-color: #EDEDED; float: left;">
                                    </p>
                                    <p align="right"><fmt:message key="AppliedFees"></fmt:message> :
                                    <input type="text" name="Fees" value="${tamweelInquiryRepresentation.appliedFees}"  readonly tabindex="5" id="Fees" style="background-color: #EDEDED; float: left;"/><div></div>
                                    </p> 

                                    <p align="right"><fmt:message key="TamweelTotalOpenAmount"></fmt:message> :
                                        <input type="text" readonly tabindex="1" name="appliedFees" id="appliedFees" value="${tamweelInquiryRepresentation.toBepaid}" style="background-color: #EDEDED; float: left;">
                                    </p>
                                    <p align="right">  <fmt:message key="TamweelCustomerMobileNumber"></fmt:message> :


                                        <input id="MobileNumber" name="MobileNumber" type="text"    tabindex="1" style=" float: left;" required>
                                        <div id="MobileNumberDiv" style="color: red; font-size: 12.5px;"></div>
                                        <input id="validationId" name="validationId" type="hidden"   value="${tamweelInquiryRepresentation.validationId}">
                                    </p>
                                    <div id="amountMessage" style="color: red;font-size: 15px;" />
                                </td>
                                <td>

                                    <p align="right"><fmt:message key="Commission"></fmt:message> :<input type="text"  value="${tamweelInquiryRepresentation.merchantCommission}"  readonly tabindex="6" id="Commession" style="background-color: #EDEDED; float: left;"/><div></div></p> 
                                    <p align="right"><fmt:message key="Lable_Tamweel_toBeDeducted"></fmt:message> :<input type="text"  value="${tamweelInquiryRepresentation.transactionAmount}"  readonly tabindex="7" id="DeductedAmount" style="background-color: #EDEDED; float: left;"/><div></div></p>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="3">
                                        <div align="center">
                                            <input type="submit" name="btnSubmit" tabindex="1" onclick="return validateMobileNum();" value="<fmt:message key="pay"></fmt:message>"  style="float: right;" class="btn">
                                        <input type="button" name="Cancel" tabindex="6"  value="<fmt:message key="cancel"></fmt:message>"  onclick="cancel();" class="btn" style="float: left;">
                                        <input type="button"  value="<fmt:message key="Modify"/>"   onclick="history.go(-1);"  > 

                                    </div>
                                </td>
                            </tr>
                        </table>
                    </fieldset> 
                </div><!-- End of Table Content area-->

            </form>

        </div>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </body>
    </html>
</fmt:bundle>