<%-- 
    Document   : 1
    Created on : 06/05/2009, 09:17:51 ص
    Author     : Melad
--%>


<%@page import="com.masary.database.dto.LoginDto"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("1")) {
        response.sendRedirect(CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();

%>




<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAdmin(session)%></title>

        <link type="text/css" rel="stylesheet" href="img/erichynds_style.css" />
        <link type="text/css" rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/redmond/jquery-ui.css" />
        <!--<link type="text/css" rel="stylesheet" href="ui.notify.css" />-->
        <style type="text/css">form input { display:block; width:250px; margin-bottom:5px }</style>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <style type="text/css">
            .ui-notify { width:350px; position:fixed; bottom:10px; right:10px; }
            .ui-notify-message { padding:10px; margin-bottom:15px; -moz-border-radius:8px; -webkit-border-radius:8px; border-radius:8px }
            .ui-notify-message h1 { font-size:14px; margin:0; padding:0 }
            .ui-notify-message p { margin:3px 0; padding:0; line-height:18px }
            .ui-notify-message:last-child { margin-bottom:0 }
            .ui-notify-message-style { background:#000; background:#F5FAFA; -moz-box-shadow: 0 0 6px #000; -webkit-box-shadow: 0 0 6px #000; box-shadow: 0 0 6px #000; }
            .ui-notify-message-style h1 { color:#797268; font-weight:bold }
            .ui-notify-message-style p { color:#797268 }
            .ui-notify-close { color:#fff; text-decoration:underline }
            .ui-notify-click { cursor:pointer }
            .ui-notify-cross { margin-top:-4px; float:right; cursor:pointer; text-decoration:none; font-size:12px; font-weight:bold; text-shadow:0 1px 1px #fff; padding:2px }
            .ui-notify-cross:hover { color:#ffffab }
            .ui-notify-cross:active { position:relative; top:1px }</style>
            <%} else {%>
        <style type="text/css">
            .ui-notify { width:350px; position:fixed; bottom:10px; left:10px; }
            .ui-notify-message { padding:10px; margin-bottom:15px; -moz-border-radius:8px; -webkit-border-radius:8px; border-radius:8px }
            .ui-notify-message h1 { font-size:14px; margin:0; padding:0 }
            .ui-notify-message p { margin:3px 0; padding:0; line-height:18px }
            .ui-notify-message:last-child { margin-bottom:0 }
            .ui-notify-message-style { background:#000; background:#F5FAFA;; -moz-box-shadow: 0 0 6px #000; -webkit-box-shadow: 0 0 6px #000; box-shadow: 0 0 6px #000; }
            .ui-notify-message-style h1 { color:#797268; font-weight:bold }
            .ui-notify-message-style p { color:#797268 }
            .ui-notify-close { color:#fff; text-decoration:underline }
            .ui-notify-click { cursor:pointer }
            .ui-notify-cross { margin-top:-4px; float:right; cursor:pointer; text-decoration:none; font-size:12px; font-weight:bold; text-shadow:0 1px 1px #fff; padding:2px }
            .ui-notify-cross:hover { color:#ffffab }
            .ui-notify-cross:active { position:relative; top:1px }</style>
            <%}%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js" type="text/javascript"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.js" type="text/javascript"></script>
        <script src="src/jquery.notify.js" type="text/javascript"></script>

        <script type="text/javascript">
            function create(template, vars, opts) {
                return $container.notify("create", template, vars, opts);
            }

            $(function () {
                $container = $("#container").notify();
                create("withIcon", {title: 'Warning!', text: 'The <code>custom</code> option is set to false for this notification, which prevents the widget from imposing it\'s own coloring.  With this option off, you\'re free to style however you want without changing the original widget\'s CSS.', icon: 'alert.png'}, {expires: false});
                create("withIcon2", {title: 'Warning!', text: 'The <code>custom</code> option is set to false for this notification, which prevents the widget from imposing it\'s own coloring.  With this option off, you\'re free to style however you want without changing the original widget\'s CSS.', icon: 'alert.png'}, {expires: false});
                create("themeroller", {title: 'Warning!', text: 'The <code>custom</code> option is set to false for this notification, which prevents the widget from imposing it\'s own coloring.  With this option off, you\'re free to style however you want without changing the original widget\'s CSS.'}, {custom: true, expires: false});
            });
        </script>
        <script type="text/javascript">
            window.$zopim || (function (d, s) {
                var z = $zopim = function (c) {
                    z._.push(c)
                }, $ = z.s =
                        d.createElement(s), e = d.getElementsByTagName(s)[0];
                z.set = function (o) {
                    z.set.
                            _.push(o)
                };
                z._ = [];
                z.set._ = [];
                $.async = !0;
                $.setAttribute('charset', 'utf-8');
                $.src = '//v2.zopim.com/?1oLIvwgOpZCq3GRTohDFfhLMbyh5KLw2';
                z.t = +new Date;
                $.
                        type = 'text/javascript';
                e.parentNode.insertBefore($, e)
            })(document, 'script');
        </script>
        <script>
            var loc = (window.self === window.top);
            if (!loc) {
                window.top.location.href = "https://e-masary.net";
            }

        </script>
        <script>
            function checkBrowser() {

                // Firefox 1.0+
                var isFirefox = typeof InstallTrigger !== 'undefined';
                if (isFirefox) {
                    document.getElementById("PrintingMessage").innerHTML = "برجاء مراجعه دليل طباعه الكروت";
                    document.getElementById("PrintingMessage").style = "font-size:20px;color:red;"
                }

            }
        </script>
    </head>
    <BODY class="body" onload="checkBrowser()">



        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="img/menuListar.jsp"></jsp:include>
        <%}%>
    </div>

    <div class="content_body"><br><br>
        <br><br>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->

<div id="content">
    <div id="container" style="display:none">
        <%
            String Warning = agent.getWarning();
            if (Warning.equals("") || Warning.equals("no")) {
            } else {
        %>
        <div id="themeroller" class="ui-state-error" style="padding:10px; -moz-box-shadow:0 0 6px #980000; -webkit-box-shadow:0 0 6px #980000; box-shadow:0 0 6px #980000;">
            <a class="ui-notify-close" href="#"><span class="ui-icon ui-icon-close" style="float:right"></span></a>
            <span style="float:left; margin:0 5px 0 0;" class="ui-icon ui-icon-alert"></span>
            <h1>تحذير</h1>
            <p><%=Warning%></p>
            <p style="text-align:center"><a class="ui-notify-close" href="#">اغلاق</a></p>
        </div>
        <%}%>
        <%
            String Notification = agent.getNotifications();
            if (Notification.equals("") || Notification.equals("no")) {
            } else {
        %>
        <div id="withIcon">
            <a class="ui-notify-close ui-notify-cross" href="#">x</a>
            <div style="float:left;margin:0 10px 0 0"><img src="alert.png" alt="Notification" /></div>
            <h1>أنتبه</h1>
            <p><%=Notification%></p>
            <p style="text-align:center"><a class="ui-notify-close" href="#">اغلاق</a></p>
        </div>
        <%}%>
        <%
                double etisalatComm = MasaryManager.getInstance().getClubCommision(Integer.parseInt(session.getAttribute(CONFIG.PARAM_PIN).toString()));
                String clubResult = MasaryManager.getInstance().getClubMessage();
                String clubResultArray[] = clubResult.split("@@");
                String message = clubResultArray[0].replace("%$", String.valueOf(etisalatComm));
                String isEnable = clubResultArray[1];

                if (isEnable.equals("1") && !message.equals("") && etisalatComm > 0) {
            %>
            <div id="withIcon2">
                <a class="ui-notify-close ui-notify-cross" href="#">x</a>
                <div style="float:left;margin:0 10px 0 0"><img src="alert.png" alt="Notification" /></div>
                <h1>أنتبه</h1>
                <p><%=message%></p>
                <p style="text-align:center"><a class="ui-notify-close" href="#">اغلاق</a></p>
            </div>
            <%
                }
            %>
    </div>
</div>
<a id="PrintingMessage"></a>
<div style="clear: both;">&nbsp;</div>

<div id="footer"><jsp:include page="img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>



