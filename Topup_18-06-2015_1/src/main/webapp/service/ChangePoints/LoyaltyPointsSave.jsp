<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link
            href="img/style${lang}.css"
            rel="stylesheet" type="text/css">

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
        <input type="hidden" name="loyaltyPoints" id="loyaltyPoints" value="${loyaltyPointsRepresentation}" />
    <legend align="center" ><font size="5"></font><img src="img/Masary.jpg"  width="80" height="60" ></legend>               
    <div class="content_body">
        <!-- String -->
         <p align="center"><font size="3" style="font-weight: bold"></font>  تم تسجيل الطلب الخاص باستبدال
النقاط وسيتم الاستبدال بعد اسبوع من انتهاء مدة تقديم الطلبات</p>
        <p align="center" dir="ltr"><font size="3" style="font-weight: bold"></font><%=CONFIG.getLedgerID(session)%> </br> ${loyaltyPointsResponse.referenceNumber}</p>
       
        <br>
        <br>

    </div>


    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

</body>  <!-- End of Main body-->
</html>