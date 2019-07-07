<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.AgentDTO"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    String rolePage = CONFIG.APP_ROOT + (String) session.getAttribute(CONFIG.PARAM_ROLE) + ".jsp";
    
    
   String operationType=(String)session.getAttribute("operationType");
   
%>

<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="./js/AddNewWalletJS/notify.js"></script>
        <script src="./js/AcceptJS/accept.js"></script>
        
        
        <title><%=operationType%></title>
        <style>
            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }
        </style>
 </head>
    <body class="body" onload="">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
    <div class="content_body"  >
   
	     <form id="form" name="AcceptHomePage" action="AcceptInquiryController" method="POST">
         <input type="hidden" name="operationType" id="operationType" value="<%=operationType%>" />
                 
     
            <table style="width: auto ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <td  style="text-align: center"><%=operationType%></td>
                </tr>
                <tr>
                    <td>
                        <table>
                        
                          <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.getMobileNumber(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color:  transparent">
                                    <div>
                                        <div >
                                            <input style="padding-left: auto" name="PhoneNumber"  id="PhoneNumber"   maxlength="11" type="text"  required>
                            <div id="custAcceptDiv" style="color: red; font-size: 12.5px;"></div>

                                        </div>
                                    </div>
                                   
                                </td>
                            </tr>
                                  <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.ReferenceNumber%>:</p>
                                </td>
                                <td style="border: none ; background-color:  transparent">
                                    <div>
                                        <div >
                                            <input style="padding-left: auto" name="ReferenceNumber"  id="ReferenceNumber"   maxlength="15" type="text"  required>

                                        </div>
                                    </div>
                                   
                                </td>
                            </tr>
                            
                            
                            
                               
                                   
                        </table>
                    </td>
                 
                </tr>
                <tr>
                    <td  style="text-align: center">
  
                       <input style="text-align: right;" type="submit" name="btnSubmit"  onclick="return formSubmit();" value="<%=CONFIG.getCheck(session)%>" class="Btn">

               
                    </td>
                   
                   	</td>
                   
                </tr>
            </table>
        </form>
        
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
