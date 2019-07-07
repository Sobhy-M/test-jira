<%-- 
    Document   : Receipt
    Created on : Apr 22, 2019, 12:46:31 PM
    Author     : omar.abdellah
--%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="Bundle">
    <!DOCTYPE html>
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="ElMesbahElModeaLabel"/></title>

        </head>
        <body class="body" >
            <div> 

                <   <c:choose>
                    <c:when test="${lang== ''}">
                        <jsp:include page="../../img/menuList.jsp"></jsp:include>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="../../img/menuListar.jsp"></jsp:include>
                    </c:otherwise>
                </c:choose>
            </div> 

            <form id="HOMEFORM" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" style="display: none;" ></form>
            <form name="doinquiry" action="ElMesbahElModeaControllerPayment" method="POST" >
                <div>
                    <input type="hidden" id="MobileNumber" name="MobileNumber"  value="${representation.mobileNumber}"/>
                    <input type="hidden" id="amount" name="amount"  value="${representation.paymentAmount}"/>
                    <input type="hidden" id="governorateId" name="governorateId"  value="${governorateId}"/>


                    <table cellspacing="10" style="font-size: 12px; font-weight: 900;"  > 
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;" colspan="2 "><fmt:message key="ElMesbahElModea_DonateToAr"/> &nbsp;<fmt:message key="ElMesbahElModeaLabel"/>  </td></tr>


                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="Donations_Value"/></td><td style="padding-left: 10px;">${representation.paymentAmount}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="AppliedFees"/></td><td style="padding-left: 10px;">${representation.appliedFees}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="Donations_TotalAmountPayable"/></td><td style="padding-left: 10px;">${representation.toBepaid}</td></tr>
                        <tr><td style="width: 50% ; padding-right: 30px;text-align: right;"><fmt:message key="Donations_DonorPhoneAr"/></td><td style="padding-left: 10px;">${representation.mobileNumber}</td></tr>


                        <tr><td colspan="2" style="text-align: center">

                                <input type="submit" id="print" value=" <fmt:message key="Donations_confirmAr"/>" class="Btn"/>
                                <input style="float: left" type="button"  value="<fmt:message key="back"/>"  onclick="history.go(-1);" class="btn">
                            </td></tr>
                    </table>
                </div>
            </form>
            <img src="./img/Masary.jpg" style="visibility: hidden;"/>

            <!--</form>-->
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->  
    </body>
</html>
</fmt:bundle>
