<%-- 
    Document   : HomePage
    Created on : Mar 17, 2019, 11:18:40 AM
    Author     : user
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
            <title><fmt:message key="Title_RealEstate_Home"/></title>
            <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
            <script>

                function cancel() {
                    document.getElementById("HOMEFORM").submit();
                }
                function InvalidMsg(textbox) {
                    textbox.setCustomValidity('');
                    if (textbox.value == '') {
                        textbox.setCustomValidity('<fmt:message key="Error_RealEstate_accountNumber"/>');
                    } else {
                        textbox.setCustomValidity('');
                    }
                    return true;
                }
                window.onload = function () {
                    var input = document.getElementsByTagName("input");
                    for (var i = 0; i < input.length; i++) {
                        var input = input[i];
                        input.onmouseover = function () {
                            this.setAttribute("org_title", this.title);
                            this.title = "";
                        };
                        input.onmouseout = function () {
                            this.title = this.getAttribute("org_title");
                        };
                    }
                };
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
                <form name="doinquiry" action="RealEstateConfirmation" method="POST"  >
                    <fieldset style="width: 30%; direction: rtl;" align="right">  
                        <table border="1" width="100%">
                            <tr>
                                <td colspan="2">
                                    <p align="right"><fmt:message key="Lable_RealEstate_accountNumber"/>
                                        <input type="text"   pattern = "[0-9]+"   title="<fmt:message key="Error_RealEstate_Number"/>"   id="accountNumber" name="accountNumber" oninput="InvalidMsg(this);" style="float: left;" required />
                                    </p>
                                    <div id="codeDiv" style="color: red; font-size: 12.5px;"></div>
                                </td>
                            <tr>
                                <td colspan="2">
                                    <div align="center">
                                        <input style="float: right" type="submit"  value="<fmt:message key="inquire"/>" class="Btn">
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