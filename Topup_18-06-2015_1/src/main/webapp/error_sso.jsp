<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Result</title>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
    </head>
    <BODY class="body">
        <%
            session = request.getSession();  
        %>

        <%--<script type="text/javascript" src="img/CheckForms.js"></script>--%>
       
    <div class="main_body" align="center">
        <div class="top_header" align="center" style="background:url(img/header_bg_new.png) no-repeat"><!-- Header div-->
            <div class="top_banner">

                <!--                <IMG SRC="img/1x.jpg" WIDTH="646" ALT="" border="0">-->

            </div>
        </div>
            <div class="content_body"><br><br><!--Table Content area -->
                <table>
                    <tbody>
                        <tr>
                            <td> <%=  request.getAttribute("ErrorMessage")%>  
                                <%=session.getAttribute("ErrorMessage") != null ? (session.getAttribute("ErrorMessage").equals(CONFIG.getAccountNotAcctiveMessage(session))
                                        ? ",على رقم: 16994 " : "") : ""%>
                            </td>
                            
                            <td>
                                 <a href="<%=CONFIG.APP_ROOT%>Login_Controller?action=<%=CONFIG.ACTION_LOGOUT%>" >
                                     <input type="button" value="<%=CONFIG.getLogout(session)%>" />
                                </a>
                                
                            </td>

                        </tr>
                    </tbody>
                </table>
            </div><!-- End of Table Content area-->
   
           <div style="clear: both;">&nbsp;</div>

        </div><!-- End of Main body-->

    </body>
</html>


