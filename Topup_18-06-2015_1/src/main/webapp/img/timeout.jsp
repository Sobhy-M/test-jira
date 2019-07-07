<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<%    
    int timeOut = 4;
    try {
        if ((String) request.getSession().getAttribute(CONFIG.PARAM_PIN) == null || ((String) request.getSession().getAttribute(CONFIG.PARAM_PIN)).equals("")) {
            timeOut = 4;
        } else {
            timeOut = MasaryManager.getInstance().getSessionTimeOut(Integer.parseInt((String) request.getSession().getAttribute(CONFIG.PARAM_PIN)));
//            timeOut = timeOut == -1 ? 16 : timeOut;
        }
    } catch (Exception ex) {
        MasaryManager.logger.error(ex);
        timeOut = 4;
    }
%>

<script type="text/javascript">
    
    function sessionTimoutPrint() {
        var min = '' + parseInt(sessionTimoutSeconds / 60);
        var sec = '' + sessionTimoutSeconds % 60;
        if (sec.length == 1)
            sec = 0 + '' + sec;
        if (sessionTimoutSeconds > 0)
            document.getElementById("footer").innerHTML = "<FONT COLOR=GREEN>Session Timeout: </FONT> <FONT COLOR='#3366FF'> " + min + ":" + sec + " Minutes  </FONT> © 2013 E-Masary.com <br/><img alt=\"\"  src=\"img/ssl_godaddy.png\" align=\"left\" />";
        else
            document.location.href =<%=CONFIG.APP_ROOT%> + "Login.jsp"
    }
    
    var sessionTimoutSeconds = <%=timeOut%> * 60;
    sessionTimoutPrint();
    
    function sessionTimoutDisplay() {
        if (sessionTimoutSeconds > 0) {
            sessionTimoutSeconds -= 1;
        }
        sessionTimoutPrint();
        setTimeout("sessionTimoutDisplay()", 1000);
    }
    
    sessionTimoutDisplay();
    
</script>
