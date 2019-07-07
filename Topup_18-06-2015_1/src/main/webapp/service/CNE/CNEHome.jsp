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
            <script type="text/javascript" src="img/CheckForms.js"></script>
            <script src="./js/BeINSportsJS/beINSports.js"></script>
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
            <script src="./js/AddNewWalletJS/notify.js"></script>
            <title><fmt:message key="Title_CNE_Home"/></title>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script >
                function validateMobileNumber() {

                    var phone = document.getElementById("PhoneNumber").value;
                   
                    var validformat = /^[^a-zA-Z]+$/;
                    if (phone == '') {
                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_CNEI_MobileNumberIsEmpty"></fmt:message>";
                        return false;
                    } else if (!validformat.test(phone)) {

                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_CNEI_MobileNumberIswrong"></fmt:message>";
                        return false;
                    } else if (phone.length < 11) {

                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_CNEI_MobileNumberIswrong"></fmt:message>";
                        return false;
                    } else if (!phone.toString().startsWith("010") && !phone.toString().startsWith("011") && !phone.toString().startsWith("012") && !phone.toString().startsWith("015")) {

                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_CNEI_MobileNumberIswrong"></fmt:message>";
                        return false;
                    } else {

                        document.getElementById("MobileNumberDiv").innerHTML = "";
                        document.getElementById("MobileNumberDiv").disabled = true;


                    }

                    var val = document.getElementById("subscribtion").value;

                    if (val == '') {
                        document.getElementById("subscribtionDiv").innerHTML = "<fmt:message key="MESSAGE_CNEI_Subscribtion"></fmt:message>";
                        return false;

                    } else if (!/^[0-9]+$/.test(val)) {

                        document.getElementById("subscribtionDiv").innerHTML = "<fmt:message key="MESSAGE_CNEI_SubscribtionIswrong"></fmt:message>";
                        return false;

                    } else {

                        document.getElementById("subscribtionDiv").innerHTML = "";
                        document.getElementById("subscribtionDiv").disabled = true;
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
                <font style="color: red; font-size: 15px;">${ErrorCode}</font>
                <form id="HOMEFORM" action="<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%>" method="POST" style="display: none;" ></form>
                <form name="doinquiry" action="CNEConfirmationController" method="POST" >
                    <table style="width: auto ; margin-left: auto ; margin-right: auto"  >
                        <tr>
                            <td  style="text-align: center"><fmt:message key="Title_CNE_Home"/></td>
                        </tr>
                        <tr>
                            <td>
                                <table>
                                    <tr>
                                          <td colspan="2" style="border: none ; background-color: transparent"> 
                                       
                                           <p align="right"><fmt:message key="CNE_MobileNumber"/>:
                                      
                                 

                                            <input type="text" id="PhoneNumber" name="PhoneNumber" maxlength="11"  style="float: left;" required />
                                    </p>
                                    <div id="MobileNumberDiv" style="color: red; font-size: 12.5px;"></div>
                                   

                                   
                                              <p align="right"> <fmt:message key="Lable_CNE_subscriptionNumber"/>:
                                        
                                      
                                            <input style="padding-left: auto" name="subscribtion" required id="subscribtion"   type="text"  >



                                            </p> <div id="subscribtionDiv" style="color: red; font-size: 12.5px;"></div>
                        </tr>

                    </table>
                    </td>

                    </tr>
                    <tr>
                        <td  style="text-align: center">


                            <input type="submit"  onclick="return validateMobileNumber()"value="<fmt:message key="inquire"/>" name="btnSubmit"  id="btnSubmit" />

                        </td>

                        </td>

                    </tr>
                    </table>
                </form>
            </div><!-- End of Table Content area-->
        </div><!-- End content body -->
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

        </body>
    </html>
</fmt:bundle>
