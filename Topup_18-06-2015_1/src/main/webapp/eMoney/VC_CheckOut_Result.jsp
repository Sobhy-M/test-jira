<%@page import="com.masary.database.dto.Vodafone_Cash_Receipt"%>
<%@page  contentType="text/html;charset=UTF-8"%>
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
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getVodafone_Cash(session)%>-<%=CONFIG.getVodafone_cash_OUT(session)%>-<%=CONFIG.getError(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>

        <div id="content">
            <center>
                <form name="formElem" id="formElem" action="" method="POST">
                <%if (!request.getSession().getAttribute("Result").toString().contains("MAS ")) {%>
                <th><H3 style="color: red; font-size: 18px">
                    <%=CONFIG.getVC_CashOut_Notefication1(session)%>
                    <a href='<%=CONFIG.APP_ROOT%>eMoneyControler?action=<%=CONFIG.VODAFONE_CASH%>&<%=CONFIG.PARAM_SERVICE_ID%>=<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>&<%=CONFIG.PARAM_OPERATION_TYPE%>=3'><%=CONFIG.getVC_Reports_Inquiry(session)%></a>
                    <%=CONFIG.getVC_CashOut_Notefication2(session)%><font style="background-color:#ECEC1C;"><%=CONFIG.getVC_CashOut_Notefication3(session)%></font>
                    <%=CONFIG.getVC_CashOut_Notefication4(session)%></H3></th>
                    <%}%>
                <fieldset style="width: 60%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%=CONFIG.getError(session)%>&nbsp; </font><img src="img/CashOut.ico"  width="20" height="20" style="background: url('CashOut.ico') no-repeat scroll 100% 50% transparent;"> </legend> 
                    <input type="hidden" name="action" value="<%=CONFIG.CHECKIN_CONFIRMATION%>" />
                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                    <table border="1" style="width: 100%;" >
                        <tr><td colspan="2"><p align="center"><%=request.getSession().getAttribute("Result")%></p></td></tr>
                    </table> 
                    <details open="open">
                        <summary></summary>

                        1-	عندما تظهر جملة أنه تم ارسال الطلب بنجاح ، يجب على العميل تاكيد عملية السحب و يجب عليك الإحتفاظ برقم الطلب للإستعلام به.</br>
                        2-	يجب على العميل الموافقة على طلب السحب بأن يقوم بالإتصال بـ (#1*7000*) من اليسار لليمين لإدخال الرقم السرى الخاص به و الموافقة على إتمام العملية


                    </details>
                </fieldset>
            </form>
        </center>
    </div>
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>

