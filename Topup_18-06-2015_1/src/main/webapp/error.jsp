<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Result</title>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
    </head>
    <BODY class="body">
        <%
             session = request.getSession();
             if(session.getAttribute("ErrorMessage") == null)
             {
                 session.setAttribute("ErrorMessage", "يرجى إعادة المحاولة وإعادة اختيار الخدمة و تأكد من قيمة الشحن أكبر من 0");
             }
           
            if (CONFIG.PARAM_FROM_MOBILE.equals(request.getSession().getAttribute(CONFIG.PARAM_FROM_MOBILE)))
                out.println("<h4 style='color:#3366EF'>" + session.getAttribute("ErrorMessage")
                        + "</h4><br><input type='button' name='btn' value='" + CONFIG.getBack(session)
                        + "' style='color:#3366EF' onclick='history.go( -1 );return true;'> ");
            else {
        %>

        <%--<script type="text/javascript" src="img/CheckForms.js"></script>--%>
        <% if (role != null) {%>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="/img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="/img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

    <%} else {%>
    <div class="main_body" align="center">
        <div class="top_header" align="center" style="background:url(img/header_bg_new.png) no-repeat"><!-- Header div-->
            <div class="top_banner">

                <!--                <IMG SRC="img/1x.jpg" WIDTH="646" ALT="" border="0">-->

            </div>

        </div>
        <div class="content_body">
            <% if (role != null) {%>
            <div class="left_menu"><!-- left menu -->
                <a href="<%=CONFIG.APP_ROOT + role + ".jsp"%>"><IMG SRC="img/home.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a><br>
                    <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>                         <jsp:include page="img/menuList.jsp"></jsp:include>                         <%} else {%>                         <jsp:include page="img/menuListar.jsp"></jsp:include>                         <%}%>
                <a href="<%=CONFIG.APP_ROOT%>Login_Controller"><IMG SRC="img/logout.png" ALT="" WIDTH=201 HEIGHT=26 border="0"></a>
            </div>
            <%}%>
            <%}%>


            <div class="content_body"><br><br><!--Table Content area -->
                <table>
                    <tbody>
                        <tr>
                            <th  scope="row"><%=CONFIG.getError(session)%> </th>
                            <td> <%=  session.getAttribute("ErrorMessage")%>  
                                <%=session.getAttribute("ErrorMessage") != null ? (session.getAttribute("ErrorMessage").equals(CONFIG.getAccountNotAcctiveMessage(session))
                                        ? ",على رقم: 16994 " : "") : ""%>
                            </td>

                        </tr>
                    </tbody>
                </table>
            </div><!-- End of Table Content area-->
        </div><!-- End content body -->



        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->

    </body>
<%}%>
</html>


