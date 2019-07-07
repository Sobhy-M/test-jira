<%--

    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.dto.ProviderDTO"%>
<%@page import="com.masary.database.dto.CategoryDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="<%=CONFIG.APP_ROOT%>img/style.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            session = request.getSession();
            session.setAttribute(CONFIG.PARAM_OUT, CONFIG.PARAM_OUT);
            session.setAttribute(CONFIG.lang, "");
            List<ProviderDTO> providers = MasaryManager.getInstance().getVoucherProviders();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Upload</title>
    </head>
    <BODY class="body">
        <div class="main_body" align="center">
            <div>
                <table width="940px" cellspacing="0" cellpadding="5" border="0">

                    <div class="top_header" align="center" style="background:url(<%=CONFIG.APP_ROOT%>img/header_bg.png) no-repeat"><!-- Header div--></div>
                    <div class="content_body"><br><br>
                        <br><br>
                        <%
                                    String conf;
                                    try {
                                        conf = (String) request.getAttribute(CONFIG.CONFIRM);
                                    } catch (NullPointerException ex) {
                                        conf = null;
                                    }
                                    if (CONFIG.CONFIRM.equals(conf)) {
                                        out.println("Voucher Upload Done.");
                                    } else {
                        %>
                        <form ENCTYPE='multipart/form-data' method='post' action='../Upload'>
                            <table>
                                <tr>
                                    <td><INPUT TYPE='file' NAME='testFile'></td>
                                    <td><select id="<%=CONFIG.PARAM_PROVIDER_ID%>" name="<%=CONFIG.PARAM_PROVIDER_ID%>" onchange="loadCategories(document.getElementById('PARAM_PROVIDER_ID').value);">
                                            <%
                                                                                    for (ProviderDTO p : providers) {
                                                                                        if (p.getId() != 14) {
                                                                                            continue;
                                                                                        }
                                            %>
                                            <option value="<%=p.getId()%>" ><%=p.getName()%></option>
                                            <%

                                            %>
                                        </select></td>

                                    <td>

                                        <%for (CategoryDTO category : p.getCategories()) {%>
                                        <input type='checkbox' value='<%=category.getId()%>' name='<%=CONFIG.PARAM_CATEGORY_ID%>'><%=category.getName()%><br>
                                        <%}
                                                                                }%>

                                    </td>
                                    <td><INPUT TYPE='submit' VALUE='upload'></td>
                                </tr>

                            </table>
                        </form>
                        <%}%>
                </table>
                <br><br>
            </div>

            <div style="clear: both;">&nbsp;</div>

            <div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>

        </div><!-- End of Main body-->

    </body>
</html>
