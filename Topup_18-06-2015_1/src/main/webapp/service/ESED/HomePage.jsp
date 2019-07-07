<%-- 
    Document   : HomePage
    Created on : Jul 5, 2017, 11:05:19 AM
    Author     : Ahmed Khaled
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="com.masary.common.CONFIG"%>
<%@page import="java.text.DecimalFormat"%>
<%
    String role = (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE);
    if (role == null) {
        response.sendRedirect(CONFIG.APP_ROOT + CONFIG.PAGE_LOGIN);
        return;
    }
    session = request.getSession();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link href="https://cdn.e-masary.net/app/img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/jquery-1.11.3.min.js"></script>
        <title><%=CONFIG.ESED_SERVICE_NAME%></title>
        <style>
            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }
        </style>
        <script type="text/javascript">
            window.onload = onRadioButtonChange;

            function onRadioButtonChange() {
                var nationalIdIsChecked = document.getElementById("nationalIDRadio").checked;
                var clientKeyIsChecked = document.getElementById("clientKeyRadio").checked;
                if (nationalIdIsChecked === true) {
                    document.getElementById("clientkeyleft").disabled = true;
                    document.getElementById("clientkeyright").disabled = true;
                    document.getElementById("nationalID").disabled = false;
                    document.getElementById("clientKeyValidation").innerHTML = '';
                    document.getElementById("clientkeyleft").value = '';
                    document.getElementById("clientkeyright").value = '';

                } else if (clientKeyIsChecked === true) {
                    document.getElementById("clientkeyleft").disabled = false;
                    document.getElementById("clientkeyright").disabled = false;
                    document.getElementById("nationalID").disabled = true;
                    document.getElementById("nationalValidation").innerHTML = '';
                    document.getElementById("nationalID").value = '';
                }

            }

            function completeLeftKey() {

                var leftKeyValue = document.getElementById("clientkeyleft").value;
                var rightKeyValue = document.getElementById("clientkeyright").value;

                if (leftKeyValue !== "" || leftKeyValue !== null || rightKeyValue !== "" || rightKeyValue !== null) {
                    getKey(leftKeyValue, rightKeyValue);

                }


                function getKey(left, right) {
                    $.ajax({
                        url: 'CodeCreatorAjaxRequest',
                        data: {
                            LEFTKEY: left,
                            RIGHTKEY: right
                        },
                        type: 'get'
                    }).done(function (responseData) {
                        if (responseData === '')
                        {
                            ajaxReturn = false;
                            return;
                        } else
                        {
                            ajaxReturn = true;
                        }
                        var arr = responseData.split('-');


                        $('#clientkeyleft').val(arr[0]);
                        $('#clientkeyright').val(arr[1]);

                    }).fail(function () {
                        ajaxReturn = false;
                        console.log('Failed');
                    });
                }

            }

            function checkLength() {
                var nationalID = document.getElementById("nationalID").value;
                var left = document.getElementById("clientkeyleft").value;
                var right = document.getElementById("clientkeyright").value;


                var nationalIdIsChecked = document.getElementById("nationalIDRadio").checked;
                var clientKeyIsChecked = document.getElementById("clientKeyRadio").checked;
                if (nationalIdIsChecked === true) {

                    if (nationalID.length < 14) {
                        document.getElementById("nationalValidation").innerHTML = 'من فضلك اكمل عدد الرقم القومى ليصبح 14 رقم ';
                        return false;
                    }
                    return true;
                } else if (clientKeyIsChecked === true) {
                    if (left.length + right.length < 11) {
                        document.getElementById("clientKeyValidation").innerHTML = 'من فضلك اكمل كتابة الكود ليصبح 11 رقم';
                        return false;
                    }
                    return true;


                }



            }

        </script>
        <style>
            div.validation{
                color: #ff0000;
                font-size : 19px;
            }
            input.btn{
                font-size: 16px;
                margin: auto;
                display: block;
            }
            p{
                font-size: 16px;

            }
        </style>


    </head>
    <body class="body">
        <script type="text/javascript" src="https://cdn.e-masary.net/app/img/CheckForms.js"></script>
        <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
        <jsp:include page="../../img/menuList.jsp"></jsp:include>
        <%} else {%>
        <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
        </div>
        <font style="color: red; font-size: 15px;">${ErrorCode}</font>     
    <div class="content_body"  >
        <form name="dobillinquiry" action="ESEDHomeController" method="POST" onsubmit="return checkLength();">

            <input type="hidden" name="action" value="<%=CONFIG.ACTION_ESED_INFO%>" />
            <input type="hidden" name="CLIENTKEY_LEFT"  id="LEFTKEY" />
            <input type="hidden" name="CLIENTKEY_RIGHT"  id="RIGHTKEY" />
            <fieldset style="width: 35%; direction: rtl;" align="right">  
                <legend align="center" ><font size="3">
                        &nbsp;<%= CONFIG.ESED_SERVICE_NAME%></font></legend>
                <table border="0" width="100%">
                    <tr>
                        <td>
                            <p align="right"><input type="radio" name="radio"  id="nationalIDRadio" onclick="onRadioButtonChange();"  checked><%=CONFIG.ESED_NationalID%>
                                <input id="nationalID" type="text" required   name="<%=CONFIG.PARAM_NATIONAL_ID%>" maxlength="14" tabindex="2"></p>
                            <div id="nationalValidation"  class="validation"></div>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p align="center" style="font-weight: bold">أو</p>
                            <p align="right"><input type="radio" name="radio"  id="clientKeyRadio" onclick="onRadioButtonChange();" ><%=CONFIG.ESED_ClientKey%>
                                <input id="clientkeyright" type="text" maxlength="7"  style="text-align:left;" dir="ltr" name="Right"  tabindex="2" size="12" onblur="completeLeftKey();">   \   
                                <input id="clientkeyleft" type="text" maxlength="3"  style="text-align:left;" dir="ltr"  name="Left"   tabindex="2" size="3" onblur="completeLeftKey();">    
                            <div id="clientKeyValidation"  class="validation"></div>
                            </p>

                        </td>
                    </tr>

                    <tr>
                        <td><input type="submit" name="btnSubmit" tabindex="3" align="center" value="<%=CONFIG.getCheck(session)%>" class="btn"></td>
                    </tr>
                </table>
            </fieldset> 
        </form>
    </div><!-- End of Table Content area-->
</div><!-- End content body -->
<div style="clear: both;">&nbsp;</div>
<div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
</div><!-- End of Main body-->
</body>
</html>
