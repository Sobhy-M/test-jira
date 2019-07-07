<%-- 
    Document   : ReCharge
    Created on : 27/06/2009, 01:09:17 م
    Author     : Melad
--%>

<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%
            String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
            if (role == null)
            {
                response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
                return;
            }
%>
<%
    String result=MasaryManager.getInstance().getEtisalatCredits();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>خدمات  اتصالات   </title>
    </head>
    <BODY class="body">

   <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
    </div>
    <div class="content_body"><br><br>
               <H3><%=CONFIG.CHECKCREIDET%></H3>
               <H3><%=result%></H3>
                          </form>
                    <!-- content body -->
                </div><!-- End of Table Content area-->
            </div><!-- End content body -->



            <div style="clear: both;">&nbsp;</div>

                        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
        </div><!-- End of Main body-->

    </body>
</html>
