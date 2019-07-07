<%--
    Document   : printGuide
    Created on : 22/04/2013
    Author     : Hammad
--%>


<%@page import="com.masary.database.manager.MasaryManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.masary.database.dto.*"%>
<%@page  contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
    ArrayList<VfSurveyDTO> surveyQuestions = MasaryManager.getInstance().getVodaQuestions();

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
            <%for (int i = 0; i < surveyQuestions.size(); i++) {%>
                if (document.getElementById(<%=surveyQuestions.get(i).getQuestionID()%> + "op1").checked
                        && document.getElementById(<%=surveyQuestions.get(i).getQuestionID()%> + "op1").value === "<%=surveyQuestions.get(i).getAnswer()%>") {
                    qAnswers.push(document.getElementById(<%=surveyQuestions.get(i).getQuestionID()%> + "op1").value);

                } else if (document.getElementById(<%=surveyQuestions.get(i).getQuestionID()%> + "op2").checked
                        && document.getElementById(<%=surveyQuestions.get(i).getQuestionID()%> + "op2").value === "<%=surveyQuestions.get(i).getAnswer()%>") {
                    qAnswers.push(document.getElementById(<%=surveyQuestions.get(i).getQuestionID()%> + "op2").value);

                } else if (document.getElementById(<%=surveyQuestions.get(i).getQuestionID()%> + "op3").checked
                        && document.getElementById(<%=surveyQuestions.get(i).getQuestionID()%> + "op3").value === "<%=surveyQuestions.get(i).getAnswer()%>") {
                    qAnswers.push(document.getElementById(<%=surveyQuestions.get(i).getQuestionID()%> + "op3").value);

                } else {
                    qAnswers.push("no");
//                    alert('Please review and complete the survey, then submit your answers');
                    res = false;
                }
            <%}%>
                if (res === false) {
                 alert('بعض الإجابات خاطئة ، من فضلك راجع فيديو التدريب مرة أخرى للإجابة بصورة صحيحة ، ثم اتصل بخدمة عملاء مصارى 0233362799 – 01110447711 لإضافة الخدمة لمحفظتك.');
                } else {
                    alert('جميع إجاباتك صحيحة ، من فضلك اتصل بخدمة عملاء مصارى 0233362799 – 01110447711 لإضافة الخدمة لمحفظتك.');
                }
//                alert("res4: " + qAnswers);
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
        <div id="content">
            <iframe id="video2" align="middle" type='text/html' width='400' height='300' src='https://www.youtube.com/v/p4Gsgy3c6Rk&feature=youtu.be' frameborder='0'></iframe>
            <br/>
            <br/>
            <h3>
                    بعد مشاهدة الفيديو السابق، برجاء الاجابه على الأسئله التاليه لتفعيل خدمة فودافون كاش
            </h3>
            <div align="right">
                <form action="<%=CONFIG.APP_ROOT%>walletServices" method="post" onsubmit="return checkTerms(this);">
                <!--<form action="2.jsp" method="post" onsubmit="return checkTerms(this);">-->
                <input type="hidden" name="action" value="<%= CONFIG.ACTION_OPEN_VFCASH%>" > 
                <input type="hidden" name="subaction" value="ToDataPage" >
                <input type="hidden" name="role" value="<%=role%>">
                <fieldset style="width: 70%; direction: rtl;" align="right">  
                    <legend align="right" ><font size="5"></font><img src="img/CashIn.ico"  width="20" height="20" > </legend> 
                            <%
                                VfSurveyDTO singleQuestion = null;
                                for (int i = 0; i < surveyQuestions.size(); i++) {
                                    singleQuestion = surveyQuestions.get(i);
                            %>
                            <%=singleQuestion.getQuestionID() + ". " + singleQuestion.getQuestionText()%>
                    <br/>
                    <input type="radio" id="<%=singleQuestion.getQuestionID() + "op1"%>" name="<%=singleQuestion.getQuestionID()%>" value="op1"><%=singleQuestion.getOption1()%>
                    <br/>
                    <input type="radio" id="<%=singleQuestion.getQuestionID() + "op2"%>" name="<%=singleQuestion.getQuestionID()%>" value="op2"><%=singleQuestion.getOption2()%>
                    <br/>
                    <input type="radio" id="<%=singleQuestion.getQuestionID() + "op3"%>" name="<%=singleQuestion.getQuestionID()%>" value="op3"><%=singleQuestion.getOption3()%>
                    <br/>
                    <br/>
                    <%
                        }
                    %>
                              <input type="submit" id="questions" value="تقييم الاجابات">
                </fieldset> 
            </form>
        </div>
    </div>


    <div style="clear: both;">&nbsp;</div>

    <div id="footer"><jsp:include page="img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->

</body>
</html>
