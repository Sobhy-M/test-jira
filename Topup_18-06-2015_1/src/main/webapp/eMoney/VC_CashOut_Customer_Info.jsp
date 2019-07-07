<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
        <title><%=CONFIG.getVodafone_Cash(session)%>-<%=CONFIG.getVodafone_cash_OUT(session)%>-<%=CONFIG.getCustomer_Info(session)%></title>
        <script language="javascript" type="text/javascript" src="bill/datetimepicker.js"></script>
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
                <form name="formElem" id="formElem" action="<%=CONFIG.APP_ROOT%>eMoneyControler" method="POST" onsubmit="return checkVodafoneCashForm(this);">
                <fieldset style="width: 28%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"><%=CONFIG.getCustomer_Info(session)%>&nbsp; </font><img src="img/CashOut.ico"  width="20" height="20" style="background: url('CashOut.ico') no-repeat scroll 100% 50% transparent;"> </legend> 
                    <input type="hidden" name="action" value="<%=CONFIG.CHECKOUT_Customer_Info%>" />
                    <input type="hidden" name="<%=CONFIG.PARAM_SERVICE_ID%>" value="<%=request.getParameter(CONFIG.PARAM_SERVICE_ID)%>" />
                    <input type="hidden" name="<%=CONFIG.AMOUNT%>" value="<%=request.getParameter(CONFIG.AMOUNT)%>" />
                    <input type="hidden" name="<%=CONFIG.PARAM_MSISDN%>" value="<%=request.getParameter(CONFIG.PARAM_MSISDN)%>" />
                    <input type="hidden" name="Fees" value="<%=request.getParameter("Fees")%>" />
                    <input type="hidden" name="Commession" value="<%=request.getParameter("Commession")%>" />
                    <input type="hidden" name="DeductedAmount" value="<%=request.getParameter("DeductedAmount")%>" />
                    <table border="1" style="width: 100%;">
                        <th colspan="2"><%=CONFIG.getCustomer_Info(session)%></th>
                        <tr>
                            <td colspan="2">
                                <p align="right"><%=CONFIG.getCustomerName(session)%> : <input type="text" name="CustomerName"  tabindex="1" id="CustomerName"  style="float: left;" autocomplete ="OFF" />
                                <div id="CustomerNameDiv" ></div></p> 
                                <p align="right"><%=CONFIG.getNationalID(session)%> : <input id="NationalID" type="text" name="<%= CONFIG.PARAM_NATIONAL_ID%>"  tabindex="2" style="float: left;">
                                <div id="NationalIDDiv"  ></div></p>
                                <p align="right"><%=CONFIG.getBirthDate(session)%> :<input id="BirthDate" name="<%= CONFIG.PARAM_BIRTH_DATE%>" type="text" size="15" style="float: left;"/>
                                    <a href="javascript:NewCal('BirthDate','ddmmyyyy')"><img src="bill/cal.gif" width="16" height="16" border="0" alt="Pick a date" /></a>
                                <div id="BirthDateDiv"></div></p>
                                <p align="right"><%=CONFIG.getLand_Line(session)%> :<input id="Land_Line" type="text" name="<%= CONFIG.PARAM_Land_Line%>"  tabindex="2" style="float: left;">
                                <div id="Land_LineDiv"></div></p>

                            </td>
                        </tr>
                        <tr><td><input type="submit" name="btnSubmit" tabindex="4" value="<%=CONFIG.getGo(session)%>"></td>
                            <td><input type="button" name="MoreInfo" tabindex="3" value="<%=CONFIG.getBack(session)%>"  style="float: left" onclick="history.go(-1);"></td></tr>
                    </table>
                    <details open="open">
                        <summary></summary>
                        <p>طبقاً لتعليمات البنك المركزى يجب عليك إما إدخال البيانات التالية أونلاين  من الصفحه التاليه أو ملء الإستمارة الورقية و إرسالها لمصارى.</p>
                    </details>
                </fieldset> 
            </form>
        </center>

        <div style="clear: both;">&nbsp;</div>
        <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
    </div><!-- End of Main body-->

</body>
</html>

