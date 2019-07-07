
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Not Implemented</title>
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
                    <a href="<%=CONFIG.APP_ROOT + "3.jsp"%>"><IMG SRC="img/home.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a><br>
                    <jsp:include page="../../img/menuList.jsp"></jsp:include>
                    <a href="<%=CONFIG.APP_ROOT%>Web"><IMG SRC="img/logout.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>
                </div>
                <div class="content_body"><br><br>
                    <h4>Not Yet Implemented</h4>
                </div><!-- End of Table Content area-->
            </div><!-- End content body -->
            <div style="clear: both;">&nbsp;</div>
                        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->
    </body>
</html>



