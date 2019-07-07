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
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null ) {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }
%>
<%
            session = request.getSession();
            List<ProviderDTO> providers = MasaryManager.getInstance().getVoucherProviders();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Upload</title>
        <script type="text/javascript">
            function loadCategories(providerID){
                var catDiv = document.getElementById("category");
                var str='';
            <% for (ProviderDTO provider : providers) {%>

                    if(providerID==<%=provider.getId()%>)   {

             <%
                 for (CategoryDTO category : provider.getCategories()) {
            %>
                        str+="<input type='checkbox' value='<%=category.getId()%>' name='<%=CONFIG.PARAM_CATEGORY_ID%>'><%=category.getName()%> <br>";
            <%
                 }
            %>
                        str+="</select>";
                    }
            <% }%>
                    catDiv.innerHTML=str;
                }
        </script>
    </head>
    <BODY class="body" onload="loadCategories(<%=providers.get(0).getId()%>);">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>
    <br>
    <br>
    <%       String conf;
                try {
                    conf = (String) request.getAttribute(CONFIG.CONFIRM);
                } catch (NullPointerException ex) {
                    conf = null;
                }
                if (CONFIG.CONFIRM.equals(conf)) {
                    out.println("Voucher Upload Done.");
                } else {
    %>

    <FORM ENCTYPE='multipart/form-data' method='post' action='Upload'>
        <table>
            <TR>

                <td><INPUT TYPE='file' NAME='testFile'></td>
                <td><select id="<%=CONFIG.PARAM_PROVIDER_ID%>" name="<%=CONFIG.PARAM_PROVIDER_ID%>" onchange="loadCategories(document.getElementById('PARAM_PROVIDER_ID').value);">
                        <%
                                            for (ProviderDTO p : providers) {
                        %>
                        <option value="<%=p.getId()%>" ><%=p.getName()%></option>
                        <%
                                            }
                        %>
                    </select></td>

                    <td id="category" align="left">
                </td>
                <td><INPUT TYPE='submit' VALUE='upload'></td>
            </TR>
        </table>

    </FORM><%}%>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>


