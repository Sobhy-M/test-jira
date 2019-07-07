<%--
    Document   : ViewAgentAccount.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.*"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%!    int startOfLoop = 0;
    int endOfLoop = 10;
    List transFromAg;
    List transToAg;
    int length;
    int al;
%>
<%
    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
    String xx = null;
    try {
        xx = requestParameters.get("al");
        if (xx != null) {
            endOfLoop = Integer.parseInt(xx);
        }
    } catch (NullPointerException ex) {
    }
    startOfLoop = endOfLoop - 10;

    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("3")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
    session = request.getSession();
    EmployeeDTO emp = null;
    if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        emp = MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID));
    }
    transFromAg = (List) session.getAttribute("transFromAg");
    transToAg = (List) session.getAttribute("transToAg");
    double totalAmount = 0;
    DecimalFormat myFormatter = CONFIG.getFormater(session);
    String isFromEmployeeOnly = request.getAttribute("fromEmployeeOnly").toString();
    double customerBalance = MasaryManager.getInstance().getCustomerInfo(agent.getPin()).getCurrentBalance();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAccountInformation(session)%></title>
    </head>
    <BODY class="body">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <div class="content_body"><br><br>
            <table >
                <h3> 
                    <th><%=CONFIG.getBalance(session)%></th>

                <%if (!session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1") && MasaryManager.getInstance().getEmployee((String) session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID)).isShowMyBalance()) {%>
                <td ><%=myFormatter.format(emp.getCurBalance())%> </td>
                <%} else {%>
                <%=myFormatter.format(customerBalance)%> 
                <%}%>
                <br/>
                <th></th></h3>
        </table>
        <table border="0">
            <thead>
                <tr>
                    <td>

                        <form name="getTransFromAg" action="<%=CONFIG.APP_ROOT%>walletServices" method="post" >

                            <table border="0">

                                <tr>
                                    <td>
                                        من تاريخ
                                    </td>
                                    <td>
                                        <select name="dayfrom">
                                            <option value="01">
                                                1
                                            </option>
                                            <option value="02">
                                                2
                                            </option>
                                            <option value="03">
                                                3
                                            </option>
                                            <option value="04">
                                                4
                                            </option>
                                            <option value="05">
                                                5
                                            </option>
                                            <option value="06">
                                                6
                                            </option>
                                            <option value="07">
                                                7
                                            </option>
                                            <option value="08">
                                                8
                                            </option>
                                            <option value="09">
                                                9
                                            </option>
                                            <option value="10">
                                                10
                                            </option>
                                            <option value="11">
                                                11
                                            </option>
                                            <option value="12">
                                                12
                                            </option>
                                            <option value="13">
                                                13
                                            </option>
                                            <option value="14">
                                                14
                                            </option>
                                            <option value="15">
                                                15
                                            </option>
                                            <option value="16">
                                                16
                                            </option>
                                            <option value="17">
                                                17
                                            </option>
                                            <option value="18">
                                                18
                                            </option>
                                            <option value="19">
                                                19
                                            </option>
                                            <option value="20">
                                                20
                                            </option>
                                            <option value="21">
                                                21
                                            </option>
                                            <option value="22">
                                                22
                                            </option>
                                            <option value="23">
                                                23
                                            </option>
                                            <option value="24">
                                                24
                                            </option>
                                            <option value="25">
                                                25
                                            </option>
                                            <option value="26">
                                                26
                                            </option>
                                            <option value="27">
                                                27
                                            </option>
                                            <option value="28">
                                                28
                                            </option>
                                            <option value="29">
                                                29
                                            </option>
                                            <option value="30">
                                                30
                                            </option>
                                            <option value="31">
                                                31
                                            </option>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="monthfrom">
                                            <option value="jan">
                                                يناير
                                            </option>
                                            <option value="feb">
                                                فبراير
                                            </option>
                                            <option value="mar">
                                                مارس
                                            </option>
                                            <option value="apr">
                                                ابريل
                                            </option>
                                            <option value="may">
                                                مايو
                                            </option>
                                            <option value="jun">
                                                يونيه
                                            </option>
                                            <option value="jul">
                                                يوليه
                                            </option>
                                            <option value="aug">
                                                اغسطس
                                            </option>
                                            <option value="sep">
                                                سبتمبر
                                            </option>
                                            <option value="oct">
                                                اكتوبر
                                            </option>
                                            <option value="nov">
                                                نوفمبر
                                            </option>
                                            <option value="dec">
                                                ديسمبر
                                            </option>
                                        </select>
                                    </td>

                                    <td>
                                        <select name="yearfrom">
                                            <option value="2010">
                                                2010
                                            </option>
                                            <option value="2011">
                                                2011
                                            </option>
                                            <option value="2012">
                                                2012
                                            </option>
                                            <option value="2013">
                                                2013
                                            </option>
                                            <option value="2014">
                                                2014
                                            </option>
                                            <option value="2015">
                                                2015
                                            </option>

                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        الى تاريخ
                                    </td>
                                    <td>
                                        <select name="dayto">
                                            <option value="01">
                                                1
                                            </option>
                                            <option value="02">
                                                2
                                            </option>
                                            <option value="03">
                                                3
                                            </option>
                                            <option value="04">
                                                4
                                            </option>
                                            <option value="05">
                                                5
                                            </option>
                                            <option value="06">
                                                6
                                            </option>
                                            <option value="07">
                                                7
                                            </option>
                                            <option value="08">
                                                8
                                            </option>
                                            <option value="09">
                                                9
                                            </option>
                                            <option value="10">
                                                10
                                            </option>
                                            <option value="11">
                                                11
                                            </option>
                                            <option value="12">
                                                12
                                            </option>
                                            <option value="13">
                                                13
                                            </option>
                                            <option value="14">
                                                14
                                            </option>
                                            <option value="15">
                                                15
                                            </option>
                                            <option value="16">
                                                16
                                            </option>
                                            <option value="17">
                                                17
                                            </option>
                                            <option value="18">
                                                18
                                            </option>
                                            <option value="19">
                                                19
                                            </option>
                                            <option value="20">
                                                20
                                            </option>
                                            <option value="21">
                                                21
                                            </option>
                                            <option value="22">
                                                22
                                            </option>
                                            <option value="23">
                                                23
                                            </option>
                                            <option value="24">
                                                24
                                            </option>
                                            <option value="25">
                                                25
                                            </option>
                                            <option value="26">
                                                26
                                            </option>
                                            <option value="27">
                                                27
                                            </option>
                                            <option value="28">
                                                28
                                            </option>
                                            <option value="29">
                                                29
                                            </option>
                                            <option value="30">
                                                30
                                            </option>
                                            <option value="31">
                                                31
                                            </option>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="monthto">
                                            <option value="jan">
                                                يناير
                                            </option>
                                            <option value="feb">
                                                فبراير
                                            </option>
                                            <option value="mar">
                                                مارس
                                            </option>
                                            <option value="apr">
                                                ابريل
                                            </option>
                                            <option value="may">
                                                مايو
                                            </option>
                                            <option value="jun">
                                                يونيه
                                            </option>
                                            <option value="jul">
                                                يوليه
                                            </option>
                                            <option value="aug">
                                                اغسطس
                                            </option>
                                            <option value="sep">
                                                سبتمبر
                                            </option>
                                            <option value="oct">
                                                اكتوبر
                                            </option>
                                            <option value="nov">
                                                نوفمبر
                                            </option>
                                            <option value="dec">
                                                ديسمبر
                                            </option>
                                        </select>
                                    </td>

                                    <td>
                                        <select name="yearto">
                                            <option value="2010">
                                                2010
                                            </option>
                                            <option value="2011">
                                                2011
                                            </option>
                                            <option value="2012">
                                                2012
                                            </option>
                                            <option value="2013">
                                                2013
                                            </option>
                                            <option value="2014">
                                                2014
                                            </option>
                                            <option value="2015">
                                                2015
                                            </option>

                                        </select>
                                    </td>
                                </tr>

                            </table>

                            <input type="hidden" name="action" value="<%=CONFIG.ACTION_INCLUDE_TRANS_FROMCUST%>" />
                            <% if (isFromEmployeeOnly.equals("yes")) {%>
                            <input type="hidden" name="fromEmployeeOnly" value="yes" />
                            <% } else {%>
                            <input type="hidden" name="fromEmployeeOnly" value="no" />
                            <%}%>
                            <input type="submit" name="btnSubmit" value="<%=CONFIG.getTransactionsFrom(session)%>"/>
                        </form>
                    </td>
                    <td>

                        <% if (session.getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {%>
                        <form name="getTransToAg" action="<%=CONFIG.APP_ROOT%>walletServices" method="post" >

                            <table border="0">

                                <tr>
                                    <td>
                                        من تاريخ
                                    </td>
                                    <td>
                                        <select name="dayfrom">
                                            <option value="01">
                                                1
                                            </option>
                                            <option value="02">
                                                2
                                            </option>
                                            <option value="03">
                                                3
                                            </option>
                                            <option value="04">
                                                4
                                            </option>
                                            <option value="05">
                                                5
                                            </option>
                                            <option value="06">
                                                6
                                            </option>
                                            <option value="07">
                                                7
                                            </option>
                                            <option value="08">
                                                8
                                            </option>
                                            <option value="09">
                                                9
                                            </option>
                                            <option value="10">
                                                10
                                            </option>
                                            <option value="11">
                                                11
                                            </option>
                                            <option value="12">
                                                12
                                            </option>
                                            <option value="13">
                                                13
                                            </option>
                                            <option value="14">
                                                14
                                            </option>
                                            <option value="15">
                                                15
                                            </option>
                                            <option value="16">
                                                16
                                            </option>
                                            <option value="17">
                                                17
                                            </option>
                                            <option value="18">
                                                18
                                            </option>
                                            <option value="19">
                                                19
                                            </option>
                                            <option value="20">
                                                20
                                            </option>
                                            <option value="21">
                                                21
                                            </option>
                                            <option value="22">
                                                22
                                            </option>
                                            <option value="23">
                                                23
                                            </option>
                                            <option value="24">
                                                24
                                            </option>
                                            <option value="25">
                                                25
                                            </option>
                                            <option value="26">
                                                26
                                            </option>
                                            <option value="27">
                                                27
                                            </option>
                                            <option value="28">
                                                28
                                            </option>
                                            <option value="29">
                                                29
                                            </option>
                                            <option value="30">
                                                30
                                            </option>
                                            <option value="31">
                                                31
                                            </option>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="monthfrom">
                                            <option value="jan">
                                                يناير
                                            </option>
                                            <option value="feb">
                                                فبراير
                                            </option>
                                            <option value="mar">
                                                مارس
                                            </option>
                                            <option value="apr">
                                                ابريل
                                            </option>
                                            <option value="may">
                                                مايو
                                            </option>
                                            <option value="jun">
                                                يونيه
                                            </option>
                                            <option value="jul">
                                                يوليه
                                            </option>
                                            <option value="aug">
                                                اغسطس
                                            </option>
                                            <option value="sep">
                                                سبتمبر
                                            </option>
                                            <option value="oct">
                                                اكتوبر
                                            </option>
                                            <option value="nov">
                                                نوفمبر
                                            </option>
                                            <option value="dec">
                                                ديسمبر
                                            </option>
                                        </select>
                                    </td>

                                    <td>
                                        <select name="yearfrom">
                                            <option value="2010">
                                                2010
                                            </option>
                                            <option value="2011">
                                                2011
                                            </option>
                                            <option value="2012">
                                                2012
                                            </option>
                                            <option value="2013">
                                                2013
                                            </option>
                                            <option value="2014">
                                                2014
                                            </option>
                                            <option value="2015">
                                                2015
                                            </option>

                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        الى تاريخ
                                    </td>
                                    <td>
                                        <select name="dayto">
                                            <option value="01">
                                                1
                                            </option>
                                            <option value="02">
                                                2
                                            </option>
                                            <option value="03">
                                                3
                                            </option>
                                            <option value="04">
                                                4
                                            </option>
                                            <option value="05">
                                                5
                                            </option>
                                            <option value="06">
                                                6
                                            </option>
                                            <option value="07">
                                                7
                                            </option>
                                            <option value="08">
                                                8
                                            </option>
                                            <option value="09">
                                                9
                                            </option>
                                            <option value="10">
                                                10
                                            </option>
                                            <option value="11">
                                                11
                                            </option>
                                            <option value="12">
                                                12
                                            </option>
                                            <option value="13">
                                                13
                                            </option>
                                            <option value="14">
                                                14
                                            </option>
                                            <option value="15">
                                                15
                                            </option>
                                            <option value="16">
                                                16
                                            </option>
                                            <option value="17">
                                                17
                                            </option>
                                            <option value="18">
                                                18
                                            </option>
                                            <option value="19">
                                                19
                                            </option>
                                            <option value="20">
                                                20
                                            </option>
                                            <option value="21">
                                                21
                                            </option>
                                            <option value="22">
                                                22
                                            </option>
                                            <option value="23">
                                                23
                                            </option>
                                            <option value="24">
                                                24
                                            </option>
                                            <option value="25">
                                                25
                                            </option>
                                            <option value="26">
                                                26
                                            </option>
                                            <option value="27">
                                                27
                                            </option>
                                            <option value="28">
                                                28
                                            </option>
                                            <option value="29">
                                                29
                                            </option>
                                            <option value="30">
                                                30
                                            </option>
                                            <option value="31">
                                                31
                                            </option>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="monthto">
                                            <option value="jan">
                                                يناير
                                            </option>
                                            <option value="feb">
                                                فبراير
                                            </option>
                                            <option value="mar">
                                                مارس
                                            </option>
                                            <option value="apr">
                                                ابريل
                                            </option>
                                            <option value="may">
                                                مايو
                                            </option>
                                            <option value="jun">
                                                يونيه
                                            </option>
                                            <option value="jul">
                                                يوليه
                                            </option>
                                            <option value="aug">
                                                اغسطس
                                            </option>
                                            <option value="sep">
                                                سبتمبر
                                            </option>
                                            <option value="oct">
                                                اكتوبر
                                            </option>
                                            <option value="nov">
                                                نوفمبر
                                            </option>
                                            <option value="dec">
                                                ديسمبر
                                            </option>
                                        </select>
                                    </td>

                                    <td>
                                        <select name="yearto">
                                            <option value="2010">
                                                2010
                                            </option>
                                            <option value="2011">
                                                2011
                                            </option>
                                            <option value="2012">
                                                2012
                                            </option>
                                            <option value="2013">
                                                2013
                                            </option>
                                            <option value="2014">
                                                2014
                                            </option>
                                            <option value="2015">
                                                2015
                                            </option>

                                        </select>
                                    </td>
                                </tr>

                            </table>

                            <input type="hidden" name="action" value="<%=CONFIG.ACTION_INCLUDE_TRANS_TOCUST%>" />
                            <input type="submit" name="btnSubmit" value="<%=CONFIG.getTransactionsTo(session)%>"/>
                        </form>
                        <% } else {
                            }
                        %>
                    </td>
                </tr>
            </thead>
        </table>
        <%
            try {
                if (session.getAttribute("include") == "transFromAg") {
                    length = transFromAg.size();
        %>
        <jsp:include page="../admin/TransactionFromAg.jsp?al=<%=endOfLoop%>"></jsp:include>

        <%
        } else if (session.getAttribute("include") == "transToAg") {
            length = transToAg.size();
        %>
        <jsp:include page="../admin/TransactionToAg.jsp?al=<%=endOfLoop%>"></jsp:include>
        <%                        }
            } catch (NullPointerException ex) {
            }
            endOfLoop = 10;
        %>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>