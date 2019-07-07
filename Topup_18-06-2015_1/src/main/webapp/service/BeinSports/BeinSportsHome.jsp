<%@page import="java.util.HashMap"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.masary.database.dto.RatePlanDTO"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   session = request.getSession();


%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
	if (role == null) {
		response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
		return;
	}
	session = request.getSession();
%>
<link
	href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css"
	rel="stylesheet" type="text/css">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="img/CheckForms.js"></script>
<script src="./js/BeINSportsJS/beINSports.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="./js/AddNewWalletJS/notify.js"></script>

<title><%=CONFIG.get_beInSports(session)%></title>
<style>
#notice {
	background: transparent;
	border-top: transparent !important;
	border-left: transparent !important;
	border-right: transparent !important;
	border-bottom: transparent !important;
}
</style>


</head>

<body class="body" onload="">
	<div>
		<%
			if (request.getSession().getAttribute(CONFIG.lang).equals("")) {
		%>
		<jsp:include page="../../img/menuList.jsp"></jsp:include>
		<%
			} else {
		%>
		<jsp:include page="../../img/menuListar.jsp"></jsp:include>
		<%
			}
		%>
	</div>

	<font style="color: red; font-size: 15px;">${ErrorCode}</font>

     <form id="form" name="BeinSportsHomePage" action="BeinSportsConfirmationController" method="POST">
                 
     
            <table style="width: auto ; margin-left: auto ; margin-right: auto"  >
                <tr>
                    <td  style="text-align: center"><%=CONFIG.get_beInSports(session)%></td>
                </tr>
                <tr>
                    <td>
                        <table>
                                  <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.get_PhoneNumberBeinSports(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color:  transparent">
                                    <div>
                                        <div >
                                            <input style="padding-left: auto" name="PhoneNumber" required id="PhoneNumber"   maxlength="11" type="text" >

                                        </div>
                                    </div>
                                   
                                </td>
                            </tr>
                            
                            <tr>
                                <td style="border: none ; background-color: transparent">
                                    <p><%=CONFIG.get_beInSportsSubscribtion(session)%>:</p>
                                </td>
                                <td style="border: none ; background-color:  transparent">
                                    <div >
                                        <div >
                                            <input style="padding-left: auto" name="subscribtion" required id="subscribtion"   type="text"  >

                                        </div>
                                    </div>
                                   
                                </td>
                            </tr>
                          
                        </table>
                    </td>
                 
                </tr>
                <tr>
                    <td  style="text-align: center">
                        <input type="submit"  onclick="return formSubmit()" value="<%=CONFIG.get_beInSportsInquiry(session)%>" name="btnSubmit"  id="btnSubmit" />
                    </td>
                   
                   	</td>
                   
                </tr>
            </table>
        </form>
	</div>
	<!-- End content body -->

	<div style="clear: both;">&nbsp;</div>
	<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
	</div>
	<!-- End of Main body-->
</body>
</html>