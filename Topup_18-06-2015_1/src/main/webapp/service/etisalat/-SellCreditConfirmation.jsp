<%--
    Document   : ReCharge
    Created on : 27/06/2009, 01:09:17 م
    Author     : Melad
--%>

<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%
        String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
        if (role == null) {
            response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
            return;
        }
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>تاكييد</title>
    </head>
    <BODY class="body">

        <div class="main_body" align="center">
            <div class="top_header" align="center" style="background:url(img/header_bg.png) no-repeat"><!-- Header div-->
                <div class="top_banner">

                    <IMG SRC="img/1x.jpg" WIDTH="646" ALT="" border="0">
                    <jsp:include page="../../img/welcome.jsp"></jsp:include>

                </div>
            </div>
            <div class="content_body">
                <div class="left_menu"><!-- left menu -->
                    <a href="<%=CONFIG.APP_ROOT +role+ ".jsp"%>"><IMG SRC="img/home.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a><br>
                    <jsp:include page="../../img/menuList.jsp"></jsp:include>
                    <a href="<%=CONFIG.APP_ROOT%>Web"><IMG SRC="img/logout.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>
                </div>
                <div class="content_body"><br><br>
                    <form action="<%=CONFIG.APP_ROOT%>Web" method=post  name="customer_form" id="saveform">



                    </form>
                    <!-- content body -->
                </div><!-- End of Table Content area-->
            </div><!-- End content body -->



            <div style="clear: both;">&nbsp;</div>

                        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->

    </body>
</html>
