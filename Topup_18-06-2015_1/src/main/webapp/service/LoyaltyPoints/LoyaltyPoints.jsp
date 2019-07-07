<%-- 
    Document   : LoyaltyPoints
    Created on : Nov 6, 2017, 3:12:48 PM
    Author     : Danah
--%>


 
 <%@page import="com.masary.database.dto.LoginDto"%>
 <%@page import="com.masary.database.dto.AgentDTO"%>
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%@page import="com.masary.common.CONFIG"%>
 <%@page import="com.masary.database.manager.MasaryManager"%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
 
 <%
 
     session = request.getSession();
     AgentDTO agent = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
     String loyaltyPoints = MasaryManager.getInstance().getCustomerLoyaltyPoints(agent.getPin());
 %>
 
 
 
 
 <link href="../../img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
 
 <html>
     <head>
         <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
         <title><%=CONFIG.getLoyaltyPointsName(session)%></title>
     </head>
     <BODY class="body">
     <div>
         <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
         <jsp:include page="../../img/menuList.jsp"></jsp:include>
         <%} else {%>
         <jsp:include page="../../img/menuListar.jsp"></jsp:include>
         <%}%>
     </div>
 
     <div class="content_body"><br><br>
                 <table style="width: 40%">
                     
                 
                     
                     <tr>
                         <td>
                             <p align="center"><%=CONFIG.getLoyaltyPointsName(session)%></p>
                         </td>
                         <td>
                             <p align="center"><%= loyaltyPoints %></p>
                         </td>
                     </tr>
                     
                     
 
                     
                 </table>
             
     </div><!-- End of Table Content area-->
 </div><!-- End content body -->
 
 <div id="content">
     <div id="container" style="display:none">
       
     </div>
 </div>
 <a id="PrintingMessage"></a>
 <div style="clear: both;">&nbsp;</div>
 
 <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
 </div><!-- End of Main body-->
 
 </body>
 </html>
 
 
 