<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link href="img/style${lang}.css" rel="stylesheet" type="text/css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <!-- <script src="./js/TedataJS/tedata.js"></script> -->
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="./js/AddNewWalletJS/notify.js"></script>

        <style>
            #notice {
                background: transparent;
                border-top: transparent !important;
                border-left: transparent !important;
                border-right: transparent !important;
                border-bottom: transparent !important;
            }
        </style>
        <title><%=CONFIG.get_changePointsName(session)%></title>
    </head>

    <body class="body" onload="">
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
        <form id="form" action="LoyaltyPointsConfirmation" method="POST">
            <input type="hidden" name="loyaltyPoints" id="loyaltyPoints" value="${loyaltyPointsRepresentation.walletPoints}" />
            <legend align="center"><font size="5"></font><img src="img/Masary.jpg" width="80" height="60"></legend>
            <div class="content_body">
                <!-- String -->
                <p align="center">
                    <font size="3" style="font-weight: bold"><%=CONFIG.get_changePointsAllowedPoints(session)%></font>
                </p>
                <input id="AllowedNumbers" maxlength="20" style="text-align: center; background-color: #EDEDED;" type="text" 
                       readonly name="<%=CONFIG.PARAM_MSISDN%>" autocomplete="off" tabindex="2" value="${loyaltyPointsRepresentation.walletPoints}">
                <p align="center">
                    <font size="3" style="font-weight: bold"><%=CONFIG.get_changePointsString(session)%></font>
                </p>

                <select id="points" required name="points" onchange="onchangeValue(this.id)" style="width: 200px; height: 30px;">
                    <option value="" selected disabled>اختر من القائمة</option>
                    <c:forEach var="menu" items="${points}">
                        <option id="${menu}" value="${menu}">${menu}</option>
                    </c:forEach>
                </select> 
                <br> 
                <br> 
             <p align="center">   
             <input type="submit" name="btnSubmit" tabindex="7" id="buttonSubmit" value="<%=CONFIG.get_changePointsButtonValue(request.getSession())%>"
                       align="center" style="width: 130px; height: 25px;"></p>

                <p align="center" style="width: 1165px; height: 20px;"><%=CONFIG.changePointStatementAR%></p>
                <p align="middle" style="width: 1165px; height: 20px;"><%=CONFIG.changePointStatement2AR%></p>
            </div>
        </form>
        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

    </body>
    <!-- End of Main body-->
</html>