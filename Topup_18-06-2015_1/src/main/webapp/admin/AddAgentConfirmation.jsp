<%-- 
    Document   : AddAgent.jsp
    Created on : 06/05/2009, 11:09:49 ص
    Author     : Melad
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="com.masary.database.dto.ServiceDTO"%>
<%@page import="java.util.List"%>

<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null || !role.equals("1")) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
%>
<%
    List<ServiceDTO> serviceList = MasaryManager.getInstance().getAllServices();
    session = request.getSession();
%>
<html>
    <head>
        <style  type="text/css">
            #servDiv {
                overflow: auto;
                azimuth: leftwards;
                height: 500px;
                width: 250px;
                overflow-y: scroll;
                border:1px ; 
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAddAgent(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (session.getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <DIV align="center">
            <th><h3>
                <%=CONFIG.getPleaseConfirmYourData(session)%> </h3></th>
    </DIV>
    <form name="AddAgent" action="<%=CONFIG.APP_ROOT%>admin" method="POST" onsubmit="return ValidateFormAddAgent()">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_ADD_AGENT%>" />
        <div class="content_body"><br>
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getName(session)%></th>
                    <td><input readonly style="border:0" type="text" class="text"  value="<%=request.getParameter(CONFIG.PARAM_USERNAME)%>"  name="<%=CONFIG.PARAM_USERNAME%>" tabindex="1"  /></td>
                    <th rowspan="<%=serviceList.size() + 6%>"><input type="submit" name="btnSubmit" id="btnSubmit" tabindex="6"
                                                                     value="<%=CONFIG.getSave(session)%>" class="Btn">
                        <br/>
                        <br/>
                        <input type="button" dir="rtl" name="btnSubmit" tabindex="3"
                               value="<%=CONFIG.getBack(session)%>" class="Btn" onclick="history.go(-1);
            return true;"></th>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getArabicName(session)%></th>
                    <td><input readonly style="border:0" type="text" class="text"  value="<%=session.getAttribute(CONFIG.PARAM_USERNAME_ARABIC)%>" name="<%=CONFIG.PARAM_USERNAME_ARABIC%>" tabindex="4"  />
                        <div id="AdCustNameDiv"></div>
                    </td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getMobileNumber(session)%></th>
                    <td><input  type="text" class="text" value="<%=request.getParameter(CONFIG.PARAM_MSISDN)%>" name="<%=CONFIG.PARAM_MSISDN%>" tabindex="3"  readonly style="border:0" /></td>
                </tr>
                <tr>
                    <th scope="col">
                        <%=CONFIG.getIsRetailGroup(session)%>
                    </th>
                    <td>
                        <input  type="checkbox" disabled <%=("on".equals(request.getParameter(CONFIG.PARAM_RETAIL_GROUP))) ? "checked" : ""%> name="<%=CONFIG.PARAM_RETAIL_GROUP%>"  >
                        <INPUT type="hidden" name="<%=CONFIG.PARAM_RETAIL_GROUP%>" value="<%=("on".equals(request.getParameter(CONFIG.PARAM_RETAIL_GROUP)) ? "on" : "off")%>">

                    </td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getSecurityQuestion(session)%></th>
                    <td><input readonly type="text" class="text"  value="<%=request.getParameter(CONFIG.PARAM_QUESTION)%>" name="<%=CONFIG.PARAM_QUESTION%>" tabindex="4" readonly style="border:0"  /></td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getAnswer(session)%></th>
                    <td> <input readonly type="text" class="text" value="<%=request.getParameter(CONFIG.PARAM_ANSWER)%>"  name="<%=CONFIG.PARAM_ANSWER%>"  tabindex="5" readonly style="border:0" /></td>
                </tr>
                <tr >
                    <td colspan="2" >                     
                        <div class="displayTableFrame"  name="servDiv" id="servDiv" style="width: 100%">
                            <table border="0" style="width: 100%"  >
                                <tr>
                                    <th colspan="2">
                                        <%=CONFIG.getServices(session)%>
                                    </th>
                                </tr>
                                <%
                                    int i = 0;
                                    for (ServiceDTO service : serviceList) {%>
                                <tr>
                                    <th>
                                        <%=service.getStrServiceName(session)%>
                                    </th>
                                    <td>
                                        <input  name="<%=service.getIdService()%>" type="checkbox"   disabled <%=("on".equals(request.getParameter(String.valueOf(service.getIdService()))) ? "checked" : "")%> >
                                        <INPUT type="hidden" name="<%=service.getIdService()%>" value="<%=("on".equals(request.getParameter(String.valueOf(service.getIdService()))) ? "on" : "off")%>">
                                    </td>
                                </tr>
                                <%
                                        i++;
                                    }
                                %>
                            </table>
                        </div>
                    </td>
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