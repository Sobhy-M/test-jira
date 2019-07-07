<%-- 
    Document   : BlaBlaTopUpResult
    Created on : May 14, 2014, 1:16:38 PM
    Author     : Aya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.AgentDTO"%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    session = request.getSession();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getCustomerBlaBlaTopUp(session)%>-<%=CONFIG.getError(session)%></title>
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <div id="content">
            <center>
                <fieldset style="width: 60%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%=CONFIG.getError(session)%>&nbsp; </font><img src="img/CashOut.ico"  width="20" height="20" style="background: url('CashOut.ico') no-repeat scroll 100% 50% transparent;"> </legend> 
                    <table border="1" style="width: 100%;" >
                        <tr><td colspan="2"><p align="center"><%=request.getSession().getAttribute("Result")%></p></td></tr>
                    </table> 
                    <details open="open">
                        <summary></summary>

<!--                        1-	عندما تظهر جملة أنه تم ارسال الطلب بنجاح ، يجب على العميل تاكيد عملية السحب و يجب عليك الإحتفاظ برقم الطلب للإستعلام به.</br>
                        2-	يجب على العميل الموافقة على طلب السحب بأن يقوم بالإتصال بـ (#1*7000*) من اليسار لليمين لإدخال الرقم السرى الخاص به و الموافقة على إتمام العملية
-->

                    </details>
                </fieldset>
        </center>
    </div>
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

