<%-- 
    Document   : Mersal Service
    Created on : Dec 04, 2016, 12:44:08 PM
    Author     : hammad
--%>

<%@page import="com.masary.common.CONFIG"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String custId = null;
    if (!request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID).equals("-1")) {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_EMPLOYEE_ID);
    } else {
        custId = (String) request.getSession().getAttribute(CONFIG.PARAM_PIN);
    }
    String rolePage = CONFIG.APP_ROOT + (String) request.getSession().getAttribute(CONFIG.PARAM_ROLE) + ".jsp";
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="./js/LongLiveEgyptJS/LongLiveDonationController.js"></script>
        <link type="text/css" rel="stylesheet" href="./css/InsuranceCss.css" />
        <title><%=CONFIG.HATAXI_SERVICE %></title>
        
        <script language='javascript' type='text/javascript'>
            
            function ValidateMaxAmount() {
                var amount = document.getElementById("donationValue"); 
                var netAmount = Number(amount.value);
                if (netAmount < 20 || netAmount > 100) {
                    amount.setCustomValidity('القيمه المسموح بها اكبر من 20 و اقل من 100');
                } else if (netAmount%1 !== 0){
                    amount.setCustomValidity('هذه الخدمه لا تقبل كسور الجنيه');
                }else{
                   amount.setCustomValidity('');       
                }
            }
            
        </script>

    </head>
       <body onload="onloadFunction()">
        <div>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%if (request.getSession().getAttribute(CONFIG.lang).equals("")) {%>
            <jsp:include page="../../img/menuList.jsp"></jsp:include>
            <%} else {%>
            <jsp:include page="../../img/menuListar.jsp"></jsp:include>
            <%}%>
        </div>
        
        <form action="HataxiController" id="DonationForm" method="POST" style="font-weight: bold">
             <input type="hidden" name="action" value="<%=CONFIG.ACTION_HATAXI_CONFIRM%>" />
             <h2 align="center" ><%=CONFIG.HATAXI_SERVICE %></h2>
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
                <tr>
                     <td style="border: none">
                        <p>رقم المحمول:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="DonatorPhone"  autocomplete="off" type="text" id="DonatorPhone" maxlength="11" title="<%=CONFIG.DonatorPhoneTitle(session)%>"  onblur="ThePhoneNotMatch(this.id);" >
                            </div>
                        </div>
                    </td>
                     <td style="border: none">
                       <img name="AboutPhoneNumber" style="visibility: hidden;cursor: pointer" id="AboutPhoneNumber" class="AboutPhoneNumber" title="<%=CONFIG.getAboutPhoneNo(session)%>" src="./img/help.png">
                     </td>
                </tr>
                <tr>
                    <td style="border: none">
                        <p>المبلغ المراد دفعه:</p>
                    </td>
                    <td style="border: none">
                        <div class="requriedclass">
                            <div>
                                <input name="donationValue" id="donationValue" autocomplete="off" id="donationValue" type="text" required onchange="ValidateMaxAmount();">
                            </div>
                        </div>
                    </td>
                    <td style="border: none">
                      
                    </td>
                </tr>
                <tr>
                    
                    <td  class="secondTD">
                        <input type="button" value="الغاء"  id="submitbutton" onclick="window.location.href='<%=rolePage %>';"  />
                    </td>
                    <td  class="secondTD">
                        <input type="submit" value="<%=CONFIG.getPay(session)%>"  id="submitbutton" />
                    </td>
                </tr>
            </table>

        </form>
        <div id="footer"><jsp:include page="../../img/timeout.jsp"></jsp:include></div>
    </body>
</html>
