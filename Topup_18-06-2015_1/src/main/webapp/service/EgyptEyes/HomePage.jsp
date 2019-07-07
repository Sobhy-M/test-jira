<%-- 
    Document   : HomePage
    Created on : Apr 22, 2019, 12:44:33 PM
    Author     : omar.abdellah
--%>


<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<fmt:bundle basename="Bundle">
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="Donation_EgyptEyesLAbel"/></title>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script>
                function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }
                function validateMobileNumber() {

                    var phone = document.getElementById("MobileNumber").value;
                    var validformat = /^[^a-zA-Z]+$/;
                    if (phone == '') {
                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_Donation_MobileNumberIsEmpty"></fmt:message>";
                        return false;
                    } else if (!validformat.test(phone)) {

                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_Donation_MobileNumberIswrong"></fmt:message>";
                        return false;
                    } else if (phone.length < 11) {

                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_Donation_MobileNumberIswrong"></fmt:message>";
                        return false;
                    } else if (!phone.toString().startsWith("010") && !phone.toString().startsWith("011") && !phone.toString().startsWith("012") && !phone.toString().startsWith("015")) {

                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_Donation_MobileNumberIswrong"></fmt:message>";
                        return false;
                    } else {

                        document.getElementById("MobileNumberDiv").innerHTML = "";
                        document.getElementById("MobileNumberDiv").disabled = true;


                    }

                    var val = document.getElementById("amount").value;

                    if (val == '') {
                        document.getElementById("amountDiv").innerHTML = "<fmt:message key="MESSAGE_Donation_amount"></fmt:message>";
                        return false;

                    } else if (!/^[0-9]+$/.test(val)) {

                        document.getElementById("amountDiv").innerHTML = "<fmt:message key="MESSAGE_DonationAmountIswrong"></fmt:message>";
                        return false;

                    } else {

                        document.getElementById("amountDiv").innerHTML = "";
                        document.getElementById("amountDiv").disabled = true;
                        return true;

                    }

                }

                </script>
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
                <form name="doinquiry" action="EgyptEyesConfirmationController" method="POST">
                    <fieldset style="width: 30%; direction: rtl;" align="right">  
                        <legend align="right" ><font size="5"><fmt:message key="Donation_EgyptEyesLAbel"/></font><img src="img/CashIn.ico"  width="20" height="20" > </legend>
                        <table border="1" width="100%">
                            <tr>
                                <td colspan="2">
                                    <p align="right"><fmt:message key="Donation_mainmenuLAbel"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                        <select name="donationTypeId" id="donationTypeId"   required="">
                                            <c:if test="${donationType.typesRepresentitions != null}">
                                                <c:forEach items="${donationType.typesRepresentitions}" var="role">
                                                       <option value="${role.id}">  ${role.typeName}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>


                                    </p>
                                    <div id="mainmenurDiv" style="color: red; font-size: 12.5px;"></div>

                                    <p align="right"><fmt:message key="Donation_MobileNumber"/>
                                         <input type="text" id="MobileNumber" name="MobileNumber" maxlength="11"  style="float: left;" required />
                                    </p>
                                    <div id="MobileNumberDiv" style="color: red; font-size: 12.5px;"></div>

                                    <p align="right"><fmt:message key="Donation_amount"/>
                                        <input type="number" id="amount" name="amount"  min="10" style="float: left;" required 
                                               oninvalid="this.setCustomValidity('برجاء إدخال قيمة اكبر  من  10 جنيه')" oninput="setCustomValidity('')"/>
                                    </p>
                                    <div id="amountDiv" style="color: red; font-size: 12.5px;"></div>


                                    <p style="color: red;">
                                        <fmt:message key="Donations_Notice"/>: <input id="notice" value="${donationType.warnMessage}"
                                                 readonly style="color: black; font-size: small; width: 200px; ">

                                    </p>
                                </td>



                                </td>
                            <tr>
                                <td colspan="2">
                                    <div align="center">
                                        <input style="float: right" type="submit"  onclick="return  validateMobileNumber();"  value="<fmt:message key="execute"/>" class="Btn">
                                        <input style="float: left" type="button"  value="<fmt:message key="cancel"/>"  onclick="cancel();" class="btn">
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </fieldset> 
                </form>
            </div><!-- End of Table Content area-->
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

        </body>

    </html>
</fmt:bundle>



