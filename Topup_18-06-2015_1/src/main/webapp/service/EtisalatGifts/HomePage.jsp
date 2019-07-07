<%-- 
    Document   : HomePage
    Created on : Apr 15, 2019, 10:52:38 AM
    Author     : omar.abdellah
--%>


<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<fmt:bundle basename="Bundle">
    <html>
        <head>
            <link href="https://cdn.e-masary.net/app/img/style${lang}.css" rel="stylesheet" type="text/css">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="Title_EtisalatGifts_Home"/></title>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script>
                function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }



                function validateMobileNumber() {
                    var phone = document.getElementById("MobileNumber").value;
                    var validformat = /^[^a-zA-Z]+$/;
                    if (phone == '') {
                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_EtisalatGifts_MobileNumberIsEmpty"></fmt:message>";
                        return false;
                    } else if (!validformat.test(phone)) {

                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_EtisalatGifts_MobileNumberIswrong"></fmt:message>";
                        return false;
                    } else if (phone.length < 11) {

                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_EtisalatGifts_MobileNumberIswrong"></fmt:message>";
                        return false;
                    } else if (!phone.toString().startsWith("010") && !phone.toString().startsWith("011") && !phone.toString().startsWith("012") && !phone.toString().startsWith("015")) {

                        document.getElementById("MobileNumberDiv").innerHTML = "<fmt:message key="MESSAGE_EtisalatGifts_MobileNumberIswrong"></fmt:message>";
                        return false;
                    } else {

                        document.getElementById("MobileNumberDiv").innerHTML = "";
                        document.getElementById("MobileNumberDiv").disabled = true;


                    }

                    var val = document.getElementById("code").value;

                    if (val == '') {
                        document.getElementById("codeDiv").innerHTML = "<fmt:message key="MESSAGE_EtisalatGifts_Code"></fmt:message>";
                        return false;

                    } else if (!/^[0-9]+$/.test(val)) {

                        document.getElementById("codeDiv").innerHTML = "<fmt:message key="MESSAGE_EtisalatGifts_CodeIswrong"></fmt:message>";
                        return false;

                    } else {

                        document.getElementById("codeDiv").innerHTML = "";
                        document.getElementById("codeDiv").disabled = true;
                        return true;

                    }

                }





                </script>

                <style>
                    input[type=number]::-webkit-inner-spin-button, 
                    input[type=number]::-webkit-outer-spin-button { 
                        -webkit-appearance: none;
                        -moz-appearance: none;
                        appearance: none;
                        margin: 0; 
                    }
                    input[type=number] {
                        -moz-appearance:textfield;
                    }
                    p{
                        font-size: 17px;
                        font-weight: bold;
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
                <form name="doinquiry" action="EtisalatGiftsConfirmation" method="POST"  onsubmit="validateMobileNumber();">
                    <fieldset style="width: 30%; direction: rtl;" align="right">  
                        <table border="1" width="100%">
                            <tr>
                                <td colspan="2">
                                    <p align="right"><fmt:message key="Lable_EtisalatGifts_MobileNumber"/>
                                        <input type="text" pattern = "[0-9]+" title="<fmt:message key="MESSAGE_EtisalatGifts_MobileNumberIsEmpty"/>" maxlength="11"  id="MobileNumber" name="MobileNumber"  style="float: left;"  required  oninput="InvalidMsg(this);" />
                                    <div id="MobileNumberDiv" style="color: red; font-size: 12.5px;"></div>   
                                    </p>


                                    <p align="right"><fmt:message key="Lable_EtisalatGifts_code"/>
                                        <input type="text" pattern = "[0-9]+" title="<fmt:message key="MESSAGE_EtisalatGifts_Code"/>"  id="code" name="code"  style="float: left;"  required  oninput="InvalidMsg(this);" />
                                    <div id="codeDiv" style="color: red; font-size: 12.5px;"></div> 
                                    </p>


                                    <p align="right"><fmt:message key="Lable_EtisalatGifts_GiftAmount"/>

                                        <select id="giftAmount"   name="giftAmount"  style="float: left; " required >
                                            <option  value="3"> 3</option>
                                            <option  value="5"> 5</option>
                                            <option  value="7" > 7</option>
                                            <option  value="10" > 10</option>
                                            <option  value="15" > 15</option>
                                            <option  value="20" > 20</option>
                                            <option  value="25"> 25</option>
                                        </select>

                                    <div id="codeDiv" style="color: red; font-size: 12.5px;"></div>
                                    </p>

                                </td>



                            <tr>
                                <td colspan="2">
                                    <div align="center">
                                        <input style="float: right" type="submit" onclick="return  validateMobileNumber();
                                               " value="<fmt:message key="inquire"/>" class="Btn">
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

