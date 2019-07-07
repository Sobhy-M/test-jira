<%-- 
    Document   : HomePage
    Created on : Mar 28, 2018, 3:55:30 PM
    Author     : Ahmed Khaled
--%>

<%@page import="com.masary.controlers.WEPostPaid.WePostPaidProperties"%>
<%@page import="com.masary.common.CONFIG"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="img/style<%=request.getSession().getAttribute(CONFIG.lang)%>.css" rel="stylesheet" type="text/css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="img/CheckForms.js"></script>
        <title><%=WePostPaidProperties.WePostPaid_SERVICE_NAME%></title>
        <style>
            input[type=number]::-webkit-inner-spin-button, 
            input[type=number]::-webkit-outer-spin-button { 
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                margin: 0; 
            }
        </style>
        <script>

            $(function () {
                $('.input').keyup(function () {
                    if (!this.value.match(/[0-9]/)) {
                        this.value = this.value.replace(/[^0-9]/g, '');
                    }
                });
            });
        </script>
        <script>

    function validateMobileNum() {
        var val = document.getElementById("msisdn").value;
        if (/^01\d{9}$/i.test(val)) {

            document.getElementById("errorMessage").innerHTML = "";
            document.getElementById("errorMessage").disabled = true;
            return true;

//if (/^\d{10}$/.test(val)) {
//    return true;
//   


        }
        // value is ok, use it
        else {
            document.getElementById("errorMessage").innerHTML = "<%= CONFIG.getErrorValuMobileNumAr(session)%>";
            //alert("Invalid number; must be ten digits")

            return false;
        }
    }
</script>
        
    </head>
    <body class="body">
        <div>

            <%if (request.getSession().getAttribute("lang").equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>                         <%}%>
            </div>
            <div class="content_body"  >
                <form action= "WeBillsInfo" method="POST" style="font-weight: bold">
                
                    <fieldset style="width: 30%; direction: rtl;" align="right">  
                        <legend align="right" ><font size="3" style="font-weight: bold">&nbsp;<%=WePostPaidProperties.WePostPaid_Inquiry%></font><img src="img/weIcon.jpg"  width="25" height="25"></font></legend>
                    <table border="1" width="100%">
                        <tr>
                            <td>
                                <p align="right"><%=WePostPaidProperties.WePostPaid_Mobile_Number%> :
                                    <input name="msisdn" id="msisdn" class="input" maxlength="11" type="text" required autocomplete="off">
                                </p>
                                <div id="errorMessage" style="color: red; font-size: 12.5px;"></div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" name="btnSubmit" tabindex="1"  onclick="return validateMobileNum();" value="<%=WePostPaidProperties.WePostPaid_Execute%>">
                            </td>
                        </tr>
                    </table>
                    <details open="open">
                        <summary></summary>
                        ادخل رقم الموبايل المراد الاستعلام عن فاتورته

                    </details>
                </fieldset>

            </form>
        </div>

        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
