<%--
    Document   : printGuide
    Created on : 22/04/2013
    Author     : Hammad
--%>


<%@page import="com.masary.database.dto.*"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.PAGE_LOGIN);
        return;
    }
//  session = request.getSession();
    //  LoginDto login=MasaryManager.getInstance().customerLogin((String)session.getAttribute(CONFIG.PARAM_PIN), (String)session.getAttribute("ppp"));
    //  session.removeAttribute("Login");
    //  session.setAttribute("Login", login);
//  AgentDTO agent = (AgentDTO) ((LoginDto) session.getAttribute("Login")).getAgent();

%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAgent(session)%></title>
    </head>
    <BODY class="body">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="img/menuListar.jsp"></jsp:include>                         <%}%>

        </div>
        <div id="content">

            <iframe id="pdfFile" align="middle" type='text/pdf' width='600' height='300' src='InstallingPrinter.pdf' frameborder='0'></iframe>
            <br/>
            <br/>
            <br/>
                <div align="center"><font size="4">تحميل الدليل: <a href="InstallingPrinter.pdf">PDF </a> - <a href="InstallingPrinter.doc">Word</a></font></div>
            <br/>
            <br/>
                     <div align="center"><font size="4">لتحميل الجافا <a href="http://e-masary.net//app//getJava">اضغط هنا </a></font></div>
            <br/>
  <div align="center"> <font size="4">لتحميل  برنامج تعريف طابعة مصارى <a href="http://e-masary.net//app//getDriver">اضغط هنا </a></font></div>
            <br/>
            <br/>
            <br/>
            <iframe id="video1" align="middle" type='text/html' width='400' height='300' src='http://www.youtube.com/v/xuulW2UrIRo&feature=related' frameborder='0'></iframe>
            <br/>
            <br/>
            <iframe id="video2" align="middle" type='text/html' width='400' height='300' src='http://www.youtube.com/v/hlGCGtGnY9A&feature=related' frameborder='0'></iframe>
        </div>


        <div style="clear: both;">&nbsp;</div>

        <div id="footer"><jsp:include page="img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>
