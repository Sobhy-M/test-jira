<%-- 
    Document   : ConfirmationPage
    Created on : Dec 31, 2018, 3:07:43 PM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.masary.controllers.VodafoneDSL.VodafoneDSLProperties"%>

<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style${lang}.css"
              rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            function gotoHome() {
                document.getElementById("homeForm").submit();
            }
        </script>
        <title><%=VodafoneDSLProperties.Title_VodafoneDsl_Confirmation%></title>
        <style>
            input[type=text] {
                width: 50%;
                background-color: #EDEDED;
                float: right;
                font-size: 16px;
            }

            p {
                font-size: 13.7px;
                font-weight: bold;
            }

            input.btn {
                font-size: 16px;
            }
        </style>
    </head>
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

        <div class="content_body">
            <form action="VodafonetopupDslPayment" method="POST">
                <input type="hidden" name="denominationId" value="${param.denominationId}" />
                <fieldset   style="direction: rtl;" align="right">
                    <legend align="right" ><font size="5"><%=VodafoneDSLProperties.Title_VodafoneDsl_Confirmation%></font><img src="https://cdn.e-masary.net/app/img/CashIn.ico"  width="20" height="20" > </legend> 
                    <table border="1" style="width: 100%">
                        <th style="text-align: right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_RequiredInfo%></th>
                        <th style="text-align: right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_Calculations%></th>
                        <tr>
                            <td>
                                <p align="right">
                                    <%=VodafoneDSLProperties.Lable_VodafoneDsl_Amount%>:
                                    <input type="text" readonly value="${param.denominationName}" style="background-color: #EDEDED; float: left; width: 50%" />
                                </p>

                                <p align="right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_Landline%>:
                                    <input type="text" name="landline" readonly value="${param.landline}" style="background-color: #EDEDED; float: left; width: 50%">
                                </p>

                            </td>
                            <td>
                                <p align="right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_AppliedFees%>:
                                    <input type="text" value="${param.Fees}" readonly style="background-color: #EDEDED; float: left; width: 50%" />
                                </p>
                                <p align="right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_Commission%>:
                                    <input type="text" value="${param.Commession}" readonly style="background-color: #EDEDED; float: left; width: 50%" />
                                </p>
                                <p align="right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_Tax%>:
                                    <input type="text" value="${param.servicetax}" readonly style="background-color: #EDEDED; float: left; width: 50%" />
                                </p>
                                <p align="right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_DeductedAmount%>:
                                    <input type="text" value="${param.DeductedAmount}" readonly style="background-color: #EDEDED; float: left; width: 50%" />
                                </p>
                                <p align="right"><%=VodafoneDSLProperties.Lable_VodafoneDsl_ToBePaid%>:
                                    <input type="text" value="${param.UserAmount}" readonly style="background-color: #EDEDED; float: left; width: 50%" />
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="1">
                                <div align="center">
                                    <input type="submit" name="btnSubmit" tabindex="4" value="<%=VodafoneDSLProperties.Button_VodafoneDsl_ConfirmAndPrint%>"  style="float: right;">
                                    <input type="button" name="btnBack" tabindex="4" value="<%=VodafoneDSLProperties.Button_VodafoneDsl_Back%>" onclick="history.go(-1);" style="float: left;">
                                </div>
                            </td>
                            <td>
                                <input type="button" name="btnSubmit" tabindex="4" value="<%=VodafoneDSLProperties.Button_VodafoneDsl_Cancel%>" onclick="gotoHome()" style="float: left;">
                            </td>
                        </tr>

                    </table>
                </fieldset>


            </form>
            <form action=<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%> method="post" name="homeForm" id="homeForm"></form>
        </div>
        <!-- End of Table Content area-->

    </div>
    <!-- End content body -->

    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div>
<!-- End of Main body-->
</body>
</html>
