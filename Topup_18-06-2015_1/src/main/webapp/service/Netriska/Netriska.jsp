<%-- 
    Document   : Neriska Service
    Created on :  30,04,2017, 12:44:08 PM
    Author     : Mustafa
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
        <title><%=CONFIG.NETRISKA_SERVICE %></title>
        
        <script language='javascript' type='text/javascript'>
            
            function ValidateMaxAmount() {
                var amount = document.getElementById("donationValue"); 
                var netAmount = Number(amount.value);
                if (netAmount < 50) {
                    amount.setCustomValidity('اصغر قيمه للدفع هي 50 جنيه');
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
        
        <form action="NetriskaController" id="DonationForm" method="POST" style="font-weight: bold">
             <input type="hidden" name="action" value="<%=CONFIG.ACTION_NETRISKA_CONFIRM%>" />
             
            <table style="width: 50% ; margin-left: auto ; margin-right: auto"  >
               
                 <tr>
                        <td colspan="2" style="text-align: center">
                            <%=CONFIG.NETRISKA_SERVICE%>
                        </td>
                    </tr>
                  
                    <tr>
                        <td><%=CONFIG.getServiceType(session)%></td>
                        <td>
                            <select required name="EduServiceType" id="EduServiceType">
                                <option selected  value="" ><%= CONFIG.getServiceType(session)%></option>
                                <option  value="COURSES" ><%= CONFIG.NETRISKA_COURSES_AR%></option>
                                <option  value="SERVICES" ><%= CONFIG.NETRISKA_SERVICES_AR%></option>
                            </select> 
                        </td>
                    </tr>
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
