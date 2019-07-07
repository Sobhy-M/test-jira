<%--
    Document   : sendMailRequest.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>

<%@page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@page import="com.masary.database.dto.EmployeeDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    Map<String, String> requestParameters = (Map<String, String>) session.getAttribute(CONFIG.REQUEST_PARAMETERS);
%>
<%
    session = request.getSession();
    String text = CONFIG.getRequestType(session, requestParameters.get("TYPE"));
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=text%> </title>
        <script type="text/javascript">
            function submitValue() {
                var body = document.getElementById('messageBody').value;
                var msg = document.getElementById("msg");
                if ((body == null) || (body == "")) {
                    msg.innerHTML = "من فضلك ادخل النص<br/>";
                    return false;
                } else {
                    return true;
                }
            }
        </script> 
    </head>
    <BODY class="body">
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="Manage agent" action="<%=CONFIG.APP_ROOT%>walletServices" method="POST"  onsubmit="return submitValue()" >
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_SEND_MAIL_REQUEST%>" />
        <input type="hidden" name="TYPE" value="<%= requestParameters.get("TYPE")%>" />
        <div class="content_body"><br><br>
            <th><H3><%=text%></H3></th>
            <table>
                <tr>
                    <th scope="col" colspan="2" style="font-size:77%"><%=CONFIG.getMessageText(session)%></th>       
                </tr>
                <tr>
                    <th><textarea id="messageBody" name="messageBody" rows="4" cols="50"></textarea>                          
                </tr>
                <tr>   
                <h2><div id="msg" style="color: #C00000 ;"></div> <h2>
                        <th scope="col" colspan="2"><div align="center"><input type="submit" name="btnSubmit" tabindex="5" align="middle"
                                                                               value="<%=CONFIG.getSend(session)%>" class="Btn"></div></th></h2>
                    </tr>  
            </table>
        </div><!-- End of Table Content area-->
    </form>
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>


