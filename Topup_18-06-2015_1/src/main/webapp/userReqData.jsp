<%-- 
    Document   : userReqData
    Created on : May 15, 2014, 5:27:41 PM
    Author     : Hammad
--%>

<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.masary.common.CONFIG"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.PAGE_LOGIN);
        return;
    }
    String userID = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    AgentDTO cdto = (AgentDTO) ((LoginDto) request.getSession().getAttribute("Login")).getAgent();
%>
<link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title><%=CONFIG.getAgent(session)%></title>
        <script>
            function checkTerms(myForm) {
                var res = true;
                var qAnswers = [];
                if (document.getElementById("cname").value.trim() === "" || document.getElementById("pname").value.trim() === ""
                        || document.getElementById("address").value.trim() === "" || document.getElementById("gov").value.trim() === ""
                        || document.getElementById("file1").value.trim() === "" || document.getElementById("file2").value.trim() === "") {
                    res = false;
                } else {
                    res = true;
                }
                if (res === false) {
                    alert('Please review and complete the data, then submit it');
                } else {
                    alert('VF Cash service will be activated for your account within minutes. Thank You.');
                }
                return res;
            }
        </script>
    </head>
    <BODY class="body">

        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <fieldset style="width: 70%; direction: rtl;" align="right">  
            <legend align="right"><h3>أكمل جميع البيانات التاليه</h3><font size="5"></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
            <div id="content">
                <div align="right">
                <form action="UploadServlet" method="post" onsubmit="return checkTerms(this);" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="<%= CONFIG.ACTION_OPEN_VFCASH%>" >
                    <input type="hidden" name="subaction" value="ToUploadPage" >
                    <input type="hidden" name="role" value="<%=role%>">
                    <input type="hidden" name="aid" value="<%=userID%>">
                    <input type="hidden" name="aname" value="<%=cdto.getName()%>">
                    <input type="hidden" name="amobile" value="<%=cdto.getPhone()%>">
                    اسم المحل
                    <input type="text" name="cname" id="cname">
                    <br/>
                    اسم صاحب المحل
                    <input type="text" name="pname" id="pname">
                    <br/>
                    عنوان المحل
                    <input type="text" name="address" id="address">
                    <br/>
                    المحافظه
                    <input type="text" name="gov" id="gov">
                    <br/>
                    <br/>
                    الان قم بتحميل استمارة الاشتراك، اطبعها، أكمل البيانات الموجوده بها، ثم أخيرا قم بعمل مسح ضوئي للاستماره و البطاقه الشخصيه و أرفقهم من خلال الحقلين التاليين
                    <br/>
                    <font size="4">تحميل الاستماره <a href="AMLforms.doc">بصيغة Word</a></font>
                    <br/>
                    رفع استمارة الاشتراك
                    <input type="file" name="file1" id="file1" size="50" />
                    <br />
                    رفع صوره ضوئيه من البطاقه الشخصيه
                    <input type="file" name="file2" id="file2" size="50" />
                    <br />
                    <input type="submit" id="data" value="ارسل البيانات و الملفات">
                </form>
            </div>
        </div>
    </fieldset> 
    <div style="clear: both;">&nbsp;</div>
    <div id="footer"><jsp:include page="img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
