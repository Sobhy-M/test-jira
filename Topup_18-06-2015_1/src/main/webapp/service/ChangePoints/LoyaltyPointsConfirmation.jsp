<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
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
        <script>
            function gotoHome() {
                document.getElementById("HOMEFORM").submit();
            }
        </script>
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
        <form id="HOMEFORM" action=<%=CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp"%> method="POST" style="display: none;" ></form>
        <form id="form" action="ChangePointsController" method="POST">
            <input type="hidden" name="loyaltyPoints" id="loyaltyPoints" value="${loyaltyPointsRepresentation}" />
            <legend align="center" ><font size="5"></font><img src="img/Masary.jpg"  width="80" height="60" ></legend>               
            <div class="content_body">
                <!-- String -->
                <p align="center"><font size="3" style="font-weight: bold"><%=CONFIG.get_changePointsString(session)%></font></p>
                <input id="AllowedNumbers" maxlength="20"  style="background-color: #EDEDED;text-align:center;"   type="text" readonly name="selectedPoints" autocomplete="off" tabindex="2" 
                value="${selectedPoints}" >

                <br>
                <br>
                <input type="submit" name="btnSubmit" tabindex="7" id="buttonSubmit" value="<%=CONFIG.getGo_SECI(request.getSession())%>"  align ="right" width="100" height="80" >
                <input type="button" name="cancel" tabindex="7" id="cancel" value="<%=CONFIG.get_TEDataCancel(request.getSession())%>"  onclick="gotoHome()" align ="left" width="100" height="80">

            </div>
        </form>  <!-- End content body -->

        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>

    </body>  <!-- End of Main body-->
</html>