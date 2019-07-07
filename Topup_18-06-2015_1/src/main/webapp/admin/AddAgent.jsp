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
        <script type="text/javascript">
            var fieldID = 'chkName';
            function selectall() {
                var i = document.AddAgent.elements.length;
                var e = document.AddAgent.elements;
                var name = new Array();
                var value = new Array();
                var j = 0;
                for (var k = 0; k < i; k++)
                {
                    if (document.AddAgent.elements[k].id == fieldID)
                    {
                        if (document.AddAgent.elements[k].checked == true) {
                            value[j] = document.AddAgent.elements[k].value;
                            j++;
                        }
                    }
                }
                checkSelect();
            }
            function selectCheck(obj)
            {
                var i = document.AddAgent.elements.length;
                for (var k = 0; k < i; k++)
                {
                    if (document.AddAgent.elements[k].id == fieldID)
                    {
                        document.AddAgent.elements[k].checked = obj;
                    }
                }
                selectall();
            }
            function selectallMe()
            {
                if (document.AddAgent.allCheck.checked == true)
                {
                    selectCheck(true);
                }
                else
                {
                    selectCheck(false);
                }
            }
            function checkSelect()
            {
                var i = document.AddAgent.elements.length;
                var berror = true;
                for (var k = 0; k < i; k++)
                {
                    if (document.AddAgent.elements[k].id == fieldID)
                    {
                        if (document.AddAgent.elements[k].checked == false)
                        {
                            berror = false;
                            break;
                        }
                    }
                }
                if (berror == false)
                {
                    document.AddAgent.allCheck.checked = false;
                }
                else
                {
                    document.AddAgent.allCheck.checked = true;
                }
            }
            var mydiv = document.getElementById('mydiv');
            mydiv.onscroll = function(event) {
                console.log('Scroll position: ' + mydiv.scrollTop);
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAddAgent(session)%></title>
    </head>
    <BODY class="body">
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <form name="AddAgent" action="<%=CONFIG.APP_ROOT%>admin" method="post" onsubmit="return ValidateFormAddAgent()">
        <input type="hidden" name="action" value="<%=CONFIG.ACTION_ADD_AGENT%>" />
        <input type="hidden" name="<%=CONFIG.CONFIRM%>" value="<%=CONFIG.CONFIRM%>" />
        <div class="content_body"><br><br>
            <table >
                <tr>
                    <th scope="col"><%=CONFIG.getName(session)%></th>
                    <td>
                        <input type="text" class="text"  value="" name="<%=CONFIG.PARAM_USERNAME%>" tabindex="1"  />
                        <div id="nameDiv">
                        </div>
                    </td>
                    <th rowspan="<%=serviceList.size() + 7%>"><input type="submit" name="btnSubmit" tabindex="<%=serviceList.size() + 6%>"                                                                     value="<%=CONFIG.getGo(session)%>" class="Btn" ></th>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getArabicName(session)%></th>
                    <td><input type="text" class="text"  value="" name="<%=CONFIG.PARAM_USERNAME_ARABIC%>" tabindex="1"  />
                        <div id="AdCustNameDiv"></div>
                    </td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getMobileNumber(session)%></th>
                    <td><input  type="text" class="text" value="" name="<%=CONFIG.PARAM_MSISDN%>" tabindex="3" />
                        <br/>
                        <div id="AdAgPhoneHelp">
                            <%--<p style="font:message-box">Example: (0020123456789) Or (0123456789)</p>--%>
                        </div>

                        <div id="phoneDiv">

                        </div>
                    </td>
                </tr>
                <tr>
                    <th scope="col">
                        <%=CONFIG.getIsRetailGroup(session)%>
                    </th>
                    <td>
                        <input type="checkbox" name="<%=CONFIG.PARAM_RETAIL_GROUP%>"  >
                    </td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getSecurityQuestion(session)%></th>
                    <td>
                        <%--<input  type="text" class="text"  value="" name="<%=CONFIG.PARAM_QUESTION%>" tabindex="4" />--%>
                        <select name="<%=CONFIG.PARAM_QUESTION%>" id="secquestion" class="text" tabindex="4">
                            <option value="" selected><%=CONFIG.getSelectOne(session)%> </option>
                            <option value="Where did you meet your spouse?">Where did you meet your spouse?</option>
                            <option value="What was the name of your first school?">What was the name of your first school?</option>
                            <option value="Who was your childhood hero?">Who was your childhood hero?</option>
                            <option value="What is your favorite pastime?">What is your favorite pastime?</option>
                            <option value="What is your favorite sports team?">What is your favorite sports team?</option>
                            <option value="What is your father middle name?">What is your father's middle name?</option>
                            <option value="What was your high school mascot?">What was your high school mascot?</option>
                            <option value="What make was your first car or bike?">What make was your first car or bike?</option>
                            <option value="What is your pets name?">What is your pet's name?</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th scope="col"><%=CONFIG.getAnswer(session)%></th>
                    <td> <input  type="text" class="text" value=""  name="<%=CONFIG.PARAM_ANSWER%>"  tabindex="5"/></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <%=CONFIG.getServices(session)%>
                    </th>
                </tr>
                <tr >
                    <td colspan="2" >                     
                        <div class="displayTableFrame"  name="servDiv" id="servDiv" style="width: 100%">
                            <table border="0"   style="width: 100%" id="servTab" name="servTab" >
                                <tr>
                                    <th>
                                        <input type="checkbox" checked  name="allCheck"  onClick="selectallMe()" >               
                                    </th>
                                    <td>
                                        <%=CONFIG.getAllServices(session)%>
                                    </td>
                                </tr>
                                <%
                                    int i = 0;
                                    for (ServiceDTO service : serviceList) {
                                %>
                                <tr>
                                    <th>
                                        <input id="chkName" onselect="selectall()" type="checkbox" checked   name="<%=service.getIdService()%>" tabindex="<%=5 + i%>">
                                    </th>
                                    <td>
                                        <%=service.getStrServiceName(session)%>
                                    </td>
                                </tr>
                                <%    }
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



